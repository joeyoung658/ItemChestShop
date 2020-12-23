package io.github.joeyoung658.Commands.admin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

public class AdminTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender.hasPermission("admin.chestshop"))) {
            return null;
        }
        if (cmd.getName().equalsIgnoreCase("achestshop")){
            if ((sender instanceof Player) && (args.length == 1)){
                AdminCommandHandler StoreCommands = new AdminCommandHandler();
                List<String> newList = StoreCommands.getCommands();
                if (newList.isEmpty()){
                    return null;
                } else {
                    return newList;
                }
            }
        }
        return null;
    }
}
