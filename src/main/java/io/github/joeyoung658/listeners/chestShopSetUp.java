package io.github.joeyoung658.listeners;

import io.github.joeyoung658.ChestShop.ChestShop;
import io.github.joeyoung658.ChestShop.ChestShopValidator;
import io.github.joeyoung658.data.ChestShopData;
import io.github.joeyoung658.data.ChestShopSetUpData;
import io.github.joeyoung658.events.buyItemSetEvent;
import io.github.joeyoung658.events.saleItemSetEvent;
import io.github.joeyoung658.gui.setupGUI;
import io.github.joeyoung658.utli.ItemChestShopServerMessages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

public class chestShopSetUp implements Listener {


    @EventHandler
    public void signChangeEvent(SignChangeEvent event){
        String[] signText = event.getLines();


        if (!(ChestShopValidator.isValidSign(signText, event.getBlock(), event.getBlock().getLocation()))){
            return;
        }

        ChestShopData chestShopData = new ChestShopData();

        Player p = event.getPlayer();
        if (chestShopData.chestShopLoaded(event.getBlock().getLocation())) {
            p.sendMessage(new ItemChestShopServerMessages().getServerPrefix() + "A chest shop already exists in this location!");
            return;
        }


        ChestShop chestShop = new ChestShop();
        chestShop.setChestShopOwner(p);
        chestShop.setChestShopLoc(event.getBlock().getLocation());


        ChestShopSetUpData chestShopSetUpData = new ChestShopSetUpData();
        chestShopSetUpData.setChestShop(p.getLocation(), chestShop);

        new setupGUI(p).saleItemGUI();
    }

    @EventHandler
    public void clickEvent(InventoryClickEvent event){
        if(event.getView().getTitle().equalsIgnoreCase(ChatColor.RED + "Sale Item")) {
            Player p = (Player) event.getWhoClicked();
            event.setCancelled(true);
            ItemStack saleItem = event.getCurrentItem();

            if (saleItem == null || saleItem.getType() == Material.BARRIER){
                return;
            }
            p.closeInventory();
            Bukkit.getServer().getPluginManager().callEvent(new saleItemSetEvent(saleItem, p));
            return;
        }
        if (event.getView().getTitle().equalsIgnoreCase(ChatColor.GREEN + "Buy Item")){
            Player p = (Player) event.getWhoClicked();
            event.setCancelled(true);
            ItemStack buyItem = event.getCurrentItem();

            if (buyItem == null || buyItem.getType() == Material.BEDROCK){
                return;
            }
            p.closeInventory();
            Bukkit.getServer().getPluginManager().callEvent(new buyItemSetEvent(buyItem, p));
            return;
        }
        return;
    }


    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event){
        if(event.getView().getTitle().equalsIgnoreCase(ChatColor.RED + "Sale Item")) {
            ChestShopSetUpData chestShopSetUpData = new ChestShopSetUpData();
            chestShopSetUpData.removeChestShop(event.getPlayer().getLocation());
        }
        if(event.getView().getTitle().equalsIgnoreCase(ChatColor.GREEN + "Buy Item")) {
            ChestShopSetUpData chestShopSetUpData = new ChestShopSetUpData();
            chestShopSetUpData.removeChestShop(event.getPlayer().getLocation());
        }
    }


}
