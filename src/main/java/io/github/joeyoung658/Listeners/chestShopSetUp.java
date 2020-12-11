package io.github.joeyoung658.Listeners;

import io.github.joeyoung658.ChestShop.ChestShop;
import io.github.joeyoung658.ChestShop.ChestShopVaildator;
import io.github.joeyoung658.ItemChestShopServerMessages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Chest;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class chestShopSetUp implements Listener {

    //todo implement function

    @EventHandler
    public void signChangeEvent(SignChangeEvent event){
        Player p = event.getPlayer();

        String[] signText = event.getLines();
        //Qty For Sale
        String lineOne = signText[0];

        //Sale Item
        String lineTwo = signText[1];

        //Amount to buy
        String lineThree = signText[2];

        //buy item
        String lineFour = signText[3];

        ChestShopVaildator chestShopVaildator = new ChestShopVaildator();
        if (!(chestShopVaildator.isValidSign(signText))){
            return;
        }

        int qtyForSale = Integer.parseInt(lineOne);
        int qtyToBuy = Integer.parseInt(lineThree);

        /**
         * Create a chestshop for the sale of items
         * @param player
         * @param qtyForSale
         * @param qtyToBuy
         * @param saleItem
         * @param purchaseItem
         * @param chestShopLoc
         */

        ChestShop chestShop = new ChestShop(p, qtyForSale, qtyToBuy);

        p.sendMessage(new ItemChestShopServerMessages(p).getServerPrefix() + " Your new chest shop has been successfully created!");

    }
}
