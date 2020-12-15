package io.github.joeyoung658;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ItemChestShopServerMessages {

    Player player;
    String serverPrefix;
    public ItemChestShopServerMessages(Player player){
        this.player = player;
        this.serverPrefix =  ChatColor.translateAlternateColorCodes('&' , "&e[&4ChestShop&e]&f ");
    }

    public String getServerPrefix(){
        return this.serverPrefix;
    }

}
