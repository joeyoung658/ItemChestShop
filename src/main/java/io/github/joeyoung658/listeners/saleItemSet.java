package io.github.joeyoung658.listeners;

import io.github.joeyoung658.ChestShop.ChestShop;
import io.github.joeyoung658.data.ChestShopSetUpData;
import io.github.joeyoung658.events.saleItemSetEvent;
import io.github.joeyoung658.gui.setupGUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * @author Joseph Young on 11/01/2021
 * @github https://github.com/joeyoung658
 */
public class saleItemSet implements Listener {


    @EventHandler
    public void onSaleItemSet(saleItemSetEvent event){

        Player player = event.getPlayer();

        ChestShopSetUpData chestShopSetUpData = new ChestShopSetUpData();
        ChestShop chestShop = chestShopSetUpData.getChestShop(player.getLocation());

        chestShop.setSaleItem(event.getSellItem());
        chestShop.setQtyForSale(event.getSellItem().getAmount());

        chestShopSetUpData.setChestShop(player.getLocation(), chestShop);

        setupGUI setupGUI = new setupGUI(player);
        setupGUI.setUpGUI("Buy Item", "Buy");
    }

}
