package io.github.joeyoung658.gui.listeners;

import io.github.joeyoung658.ChestShop.ChestShop;
import io.github.joeyoung658.gui.setupGUI;
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
public class setupInven implements Listener {
    @EventHandler
    public void clickEvent(InventoryClickEvent event){
        if(event.getView().getTitle().equalsIgnoreCase(ChatColor.GOLD + "Sale Item")) {
            event.setCancelled(true);
            ItemStack saleItem = null;
            Player p = (Player) event.getWhoClicked();
            if (event.getCurrentItem().getType().equals(Material.BEDROCK)){
                if (saleItem == null){
                    return;
                }
                ChestShop chestShop = new ChestShop();
                chestShop.setPurchaseItem(saleItem);

                p.closeInventory();
                setupGUI setupGUI = new setupGUI(p);
                setupGUI.setUpGUI("Buy Item", "Buy");
                return;
            }

            //todo make a temp place for setting up a chest shop
            if (!event.getCurrentItem().getType().equals(Material.BARRIER)){
                saleItem = event.getCurrentItem();
            }
        }
    }
}
