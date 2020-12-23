package io.github.joeyoung658;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class ItemChestShopServerMessages {

    private CommandSender commandSender;
    private String serverPrefix;


    public ItemChestShopServerMessages(){
        this.serverPrefix =  ChatColor.translateAlternateColorCodes('&' , "&e[&4ChestShop&e]&f ");
    }

    public String getServerPrefix(){
        return this.serverPrefix;
    }

    public void setCommandSender(CommandSender sender){
        this.commandSender = sender;
    }

    public CommandSender getCommandSender(){
        if (this.commandSender != null) {
            return this.commandSender;
        }
        return null;
    }
}
