package io.github.joeyoung658.listeners;

import io.github.joeyoung658.ChestShop.ChestShop;
import io.github.joeyoung658.ChestShop.ChestShopValidator;
import io.github.joeyoung658.data.ChestShopData;
import io.github.joeyoung658.utli.ItemChestShopServerMessages;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.inventory.ItemStack;

public class chestShopSetUp implements Listener {

    @SuppressWarnings("unused")
    @EventHandler
    public void signChangeEvent(SignChangeEvent event){
        String[] signText = event.getLines();
        //Qty For Sale
        String lineOne = signText[0];

        //Sale Item
        String lineTwo = signText[1].toUpperCase();

        //Amount to buy
        String lineThree = signText[2];

        //buy item
        String lineFour = signText[3].toUpperCase();


        if (!(ChestShopValidator.isValidSign(signText, event.getBlock(), event.getBlock().getLocation()))){
            return;
        }

        ChestShopData chestShopData = new ChestShopData();
        Player p = event.getPlayer();
        if (chestShopData.chestShopLoaded(event.getBlock().getLocation())) {
            p.sendMessage(new ItemChestShopServerMessages().getServerPrefix() + "A chest shop already exists in this location!");
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

            ItemStack saleItem;
            ItemStack purchaseItem;
            try {
                saleItem = new ItemStack(Material.getMaterial(lineTwo), qtyForSale);
                purchaseItem = new ItemStack(Material.getMaterial(lineFour), qtyToBuy);
            } catch (Exception e){
                return;
            }

            event.setLine(0, ChatColor.GREEN + lineOne);
            event.setLine(1, ChatColor.GREEN + lineTwo);
            event.setLine(2, ChatColor.RED + lineThree);
            event.setLine(3, ChatColor.RED + lineFour);


            ChestShop chestShop = new ChestShop();
            chestShop.setChestShopOwner(p);
            chestShop.setQtyForSale(qtyForSale);
            chestShop.setQtyToBuy(qtyToBuy);
            chestShop.setSaleItem(saleItem);
            chestShop.setPurchaseItem(purchaseItem);
            chestShop.setChestShopLoc(event.getBlock().getLocation());
            chestShopData.setChestShop(event.getBlock().getLocation(), chestShop);
            p.sendMessage(new ItemChestShopServerMessages().getServerPrefix() + " Your new chest shop has been successfully created!");

    }
}
