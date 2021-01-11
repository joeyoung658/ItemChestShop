package io.github.joeyoung658.listeners;

import io.github.joeyoung658.ChestShop.ChestShopValidator;
import io.github.joeyoung658.data.ChestShopData;
import io.github.joeyoung658.gui.setupGUI;
import io.github.joeyoung658.utli.ItemChestShopServerMessages;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

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

        setupGUI setupGUI = new setupGUI(p);
        setupGUI.setUpGUI("Sale Item", "Sell");


//        ChestShop chestShop = new ChestShop();
//        chestShop.setChestShopOwner(p);
//        chestShop.setChestShopLoc(event.getBlock().getLocation());
//        chestShop.setQtyForSale(qtyForSale);
//        chestShop.setQtyToBuy(qtyToBuy);
//        chestShop.setSaleItem(saleItem);
//        chestShop.setPurchaseItem(purchaseItem);
//        chestShopData.setChestShop(event.getBlock().getLocation(), chestShop);
//        p.sendMessage(new ItemChestShopServerMessages().getServerPrefix() + " Your new chest shop has been successfully created!");
    }
}
