package io.github.joeyoung658.gui;

import io.github.joeyoung658.utli.ItemChestShopServerMessages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

/**
 * @author Joseph Young on 10/01/2021
 * @github https://github.com/joeyoung658
 */
public class setupGUI {


    final private Player player;
    public setupGUI(Player player){
        this.player = player;
    }

    public Player getPlayer(){
        return this.player;
    }


    public void setUpGUI(String invenName, String type){
        Inventory gui = Bukkit.createInventory(this.player, 9, ChatColor.GOLD + invenName);


        ItemStack confirmItem = new ItemStack(Material.BEDROCK);
        ItemMeta confirmChestItemMeta = confirmItem.getItemMeta();
        confirmChestItemMeta.setDisplayName(ChatColor.GREEN + "Save");
        ArrayList<String> confirmChestLore = new ArrayList<>();
        confirmChestLore.add(ChatColor.AQUA + "Save " + invenName + " to chest shop!");
        confirmChestItemMeta.setLore(confirmChestLore);
        confirmItem.setItemMeta(confirmChestItemMeta);
        
        
        ItemStack placeHolder = new ItemStack(Material.BARRIER);
        ItemMeta placeHolderItemMeta = placeHolder.getItemMeta();
        placeHolderItemMeta.setDisplayName(invenName);
        ArrayList<String> placeHolderLore = new ArrayList<>();
        placeHolderLore.add(ChatColor.AQUA + "Click the item in your inventory wish to " + type + "!");
        placeHolderItemMeta.setLore(placeHolderLore);
        placeHolder.setItemMeta(placeHolderItemMeta);

        gui.setItem(0, placeHolder);
        gui.setItem(1, placeHolder);
        gui.setItem(2, placeHolder);
        gui.setItem(3, placeHolder);
        gui.setItem(4, placeHolder);
        gui.setItem(5, placeHolder);
        gui.setItem(6, placeHolder);

        gui.setItem(8, confirmItem);
        this.player.openInventory(gui);
    }
}
