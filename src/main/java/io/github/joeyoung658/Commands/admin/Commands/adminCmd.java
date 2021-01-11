package io.github.joeyoung658.Commands.admin.Commands;

import io.github.joeyoung658.Commands.admin.AdminCommandInterface;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class adminCmd implements AdminCommandInterface
{

    //The command should be automatically created.
    @Override
    public boolean onCommand(CommandSender sender, Command cmd,
                             String commandLabel, String[] args) {
       sender.sendMessage(ChatColor.AQUA + "-=-=-=-=-=-=-=" + ChatColor.RED + "ChestStops" + ChatColor.AQUA + "-=-=-=-=-=-=-=-=-");
       sender.sendMessage(ChatColor.AQUA + "Admin Vote commands - Please note this will not change eCoin Balance");
       sender.sendMessage(ChatColor.LIGHT_PURPLE + "/achestshop save - Saves all Chest Shops to database");
       sender.sendMessage(ChatColor.AQUA + "-=-=-=-=-=-=-=-=-=-==-=-=-=-=-=-=-==-=-=-=-=-=-=-=-=-");
       return false;

    }

}
