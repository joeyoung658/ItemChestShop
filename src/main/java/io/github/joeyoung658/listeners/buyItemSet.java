package io.github.joeyoung658.listeners;

import io.github.joeyoung658.ChestShop.ChestShop;
import io.github.joeyoung658.data.ChestShopData;
import io.github.joeyoung658.data.ChestShopSetUpData;
import io.github.joeyoung658.events.buyItemSetEvent;
import io.github.joeyoung658.gui.setupGUI;
import io.github.joeyoung658.utli.ItemChestShopServerMessages;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * @author Joseph Young on 11/01/2021
 * @github https://github.com/joeyoung658
 */
public class buyItemSet implements Listener {



    @EventHandler
    public void onBuyItemSet(buyItemSetEvent event){



        Player player = event.getPlayer();
        ChestShopSetUpData chestShopSetUpData = new ChestShopSetUpData();
        ChestShop chestShop = chestShopSetUpData.getChestShop(player.getLocation());

        chestShopSetUpData.removeChestShop(player.getLocation());

        chestShop.setPurchaseItem(event.getBuyItem());
        chestShop.setQtyToBuy(event.getBuyItem().getAmount());

        ChestShopData chestShopData = new ChestShopData();

        chestShopData.setChestShop(chestShop.getChestShopLoc(), chestShop);

        player.sendMessage(new ItemChestShopServerMessages().getServerPrefix()
                + " Your new chest shop has been successfully created!");


    }
}
