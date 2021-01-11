package io.github.joeyoung658.listeners;

import io.github.joeyoung658.ChestShop.ChestShop;
import io.github.joeyoung658.events.buyItemSetEvent;
import io.github.joeyoung658.events.saleItemSetEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

/**
 * @author Joseph Young on 10/01/2021
 * @github https://github.com/joeyoung658
 */
public class setUpChestShop implements Listener {

    @EventHandler
    public void clickEvent(InventoryClickEvent event){
        Player p = (Player) event.getWhoClicked();
        if(event.getView().getTitle().equalsIgnoreCase(ChatColor.GOLD + "Sale Item")) {
            if (event.getCurrentItem().getType().equals(Material.BEDROCK)){
                event.setCancelled(true);
                ItemStack saleItem = event.getInventory().getItem(7);
                if (saleItem == null){
                    return;
                }

                //todo load from temp store place
                ChestShop chestShop = new ChestShop();

                Bukkit.getServer().getPluginManager().callEvent(new saleItemSetEvent(saleItem, p.getLocation(), chestShop));
                p.closeInventory();
                return;
            }

            if (event.getCurrentItem().getType().equals(Material.BARRIER)){
                event.setCancelled(true);
            }
        }
        if (event.getView().getTitle().equalsIgnoreCase(ChatColor.GOLD + "Buy Item")){
            if (event.getCurrentItem().getType().equals(Material.BEDROCK)){
                event.setCancelled(true);
                ItemStack buyItem = event.getInventory().getItem(7);
                if (buyItem == null){
                    return;
                }

                //todo load from temp store place
                ChestShop chestShop = new ChestShop();

                Bukkit.getServer().getPluginManager().callEvent(new buyItemSetEvent(buyItem, p.getLocation(), chestShop));
                p.closeInventory();
                return;
            }

            if (event.getCurrentItem().getType().equals(Material.BARRIER)){
                event.setCancelled(true);
            }
        }
    }
}
