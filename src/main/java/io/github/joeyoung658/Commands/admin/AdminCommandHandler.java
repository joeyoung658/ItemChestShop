package io.github.joeyoung658.Commands.admin;

import io.github.joeyoung658.utli.ItemChestShopServerMessages;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdminCommandHandler implements CommandExecutor
{

    private static HashMap<String, AdminCommandInterface> commands = new HashMap<String, AdminCommandInterface>();


    public void register(String name, AdminCommandInterface cmd) {
        commands.put(name, cmd);
    }


    public boolean exists(String name) {
        return commands.containsKey(name);
    }


    public AdminCommandInterface getExecutor(String name) {
        return commands.get(name);
    }

    public List<String> getCommands(){
        List<String> commandsList = new ArrayList<>();
        for (String i:
             commands.keySet()) {
            commandsList.add(i);
        }
        commandsList.remove("achestshop");
        return commandsList;
    }

    //This will be a template. All commands will have this in common.
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!(sender.hasPermission("admin.chestshop"))) {
            sender.sendMessage(new ItemChestShopServerMessages().getServerPrefix()
                    + ChatColor.AQUA + "You do not have permission to do this! If you think this is a mistake please contact an admin!");
            return true;
        }
            if(args.length == 0) {
                getExecutor("achestshop").onCommand(sender, cmd, commandLabel, args);
                return true;
            }
            if(args.length > 0) {
                if(exists(args[0])){
                    getExecutor(args[0]).onCommand(sender, cmd, commandLabel, args);
                    return true;
                } else {
                    sender.sendMessage(new ItemChestShopServerMessages().getServerPrefix()
                            + ChatColor.AQUA + "That's not something you do yet, if you think this is an error please contact an admin!");
                    return true;
                }
            }
        return false;
    }

}
