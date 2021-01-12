package io.github.joeyoung658.gui;

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


    public void saleItemGUI(){
        Inventory gui = Bukkit.createInventory(this.player, 9, ChatColor.RED + "Sale Item");

        ItemStack placeHolder = new ItemStack(Material.BARRIER);
        ItemMeta placeHolderItemMeta = placeHolder.getItemMeta();
        placeHolderItemMeta.setDisplayName(ChatColor.AQUA + "Sale Item");
        ArrayList<String> placeHolderLore = new ArrayList<>();
        placeHolderLore.add(ChatColor.GREEN + "Click the item in your inventory wish to sell!");
        placeHolderItemMeta.setLore(placeHolderLore);
        placeHolder.setItemMeta(placeHolderItemMeta);
        


        gui.setItem(0, placeHolder);
        gui.setItem(1, placeHolder);
        gui.setItem(2, placeHolder);
        gui.setItem(3, placeHolder);
        gui.setItem(4, placeHolder);
        gui.setItem(5, placeHolder);
        gui.setItem(6, placeHolder);
        gui.setItem(7, placeHolder);
        gui.setItem(8, placeHolder);


        this.player.openInventory(gui);
    }


    public void buyItemGUI(ItemStack saleItem){
        Inventory gui = Bukkit.createInventory(this.player, 9, ChatColor.GREEN + "Buy Item");


        ItemStack placeHolder = new ItemStack(Material.BEDROCK);
        ItemMeta placeHolderItemMeta = placeHolder.getItemMeta();
        placeHolderItemMeta.setDisplayName(ChatColor.AQUA + "Buy Item");
        ArrayList<String> placeHolderLore = new ArrayList<>();
        placeHolderLore.add(ChatColor.GREEN + "Click the item in your inventory wish get for "
                +  saleItem.getType().toString().toLowerCase());
        placeHolderItemMeta.setLore(placeHolderLore);
        placeHolder.setItemMeta(placeHolderItemMeta);



        gui.setItem(0, placeHolder);
        gui.setItem(1, placeHolder);
        gui.setItem(2, placeHolder);
        gui.setItem(3, placeHolder);
        gui.setItem(4, placeHolder);
        gui.setItem(5, placeHolder);
        gui.setItem(6, placeHolder);
        gui.setItem(7, placeHolder);
        gui.setItem(8, placeHolder);


        this.player.openInventory(gui);
    }

}
