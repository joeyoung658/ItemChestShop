package io.github.joeyoung658.Listeners;

import io.github.joeyoung658.ChestShop.ChestShop;
import io.github.joeyoung658.ChestShop.ChestShopVaildator;
import io.github.joeyoung658.ItemChestShopServerMessages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.inventory.ItemStack;

public class chestShopSetUp implements Listener {

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


        int qtyForSale;
        int qtyToBuy;
        try {
            qtyForSale = Integer.parseInt(lineOne);
            qtyToBuy = Integer.parseInt(lineThree);
        } catch (Exception e){
            return;
        }
         ItemStack saleItem = new ItemStack(Material.getMaterial(lineTwo), qtyToBuy);
         ItemStack purchaseItem = new ItemStack(Material.getMaterial(lineFour), qtyForSale);


         Location chestLocation = event.getBlock().getLocation();
        ChestShop chestShop = new ChestShop(p, qtyForSale, qtyToBuy, saleItem, purchaseItem, chestLocation);
        p.sendMessage(new ItemChestShopServerMessages(p).getServerPrefix() + " Your new chest shop has been successfully created!");
    }
}
