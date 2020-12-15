package io.github.joeyoung658.Listeners;

import io.github.joeyoung658.ChestShop.ChestShop;
import io.github.joeyoung658.Data.ChestShopData;
import io.github.joeyoung658.ItemChestShopServerMessages;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
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


        if (!(isValidSign(signText, event.getBlock()))){
            return;
        }

        Location signLoc = event.getBlock().getLocation();
        ChestShopData chestShopData = new ChestShopData();
        if (chestShopData.chestShopExists(signLoc)) {
            //Should never get here but just case
            p.sendMessage(new ItemChestShopServerMessages(p).getServerPrefix() + "A chest shop already exists in this location!");
            return;
        } else {
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
                saleItem = new ItemStack(Material.getMaterial(lineTwo), qtyToBuy);
                purchaseItem = new ItemStack(Material.getMaterial(lineFour), qtyForSale);
            } catch (Exception e){
                return;
            }
            event.setLine(0, ChatColor.GREEN + lineOne);
            event.setLine(1, ChatColor.GREEN + lineTwo);
            event.setLine(2, ChatColor.RED + lineThree);
            event.setLine(3, ChatColor.RED + lineFour);
            ChestShop chestShop = new ChestShop(p, qtyForSale, qtyToBuy, saleItem, purchaseItem, signLoc);
            chestShopData.setChestShop(signLoc, chestShop);
            p.sendMessage(new ItemChestShopServerMessages(p).getServerPrefix() + " Your new chest shop has been successfully created!");
        }
    }

    private boolean isValidSign(String[] lineText, Block sign){

        if (!(sign.getType() == Material.OAK_SIGN)){
            return false;
        }

        if (lineText[0].isEmpty()
                || lineText[2].isEmpty()
                || lineText[3].isEmpty() || lineText[1].isEmpty()){
            return false;
        }
        //Qty For Sale
        String lineOne = lineText[0];
        //Amount to buy
        String lineThree = lineText[2];
        //Sale Item
        String lineTwo = lineText[1];
        //buy item
        String lineFour = lineText[3];

        int qtyForSale;
        int qtyToBuy;
        ItemStack saleItem;
        ItemStack purchaseItem;
        try {
            qtyForSale = Integer.parseInt(lineOne);
            qtyToBuy = Integer.parseInt(lineThree);
            saleItem = new ItemStack(Material.getMaterial(lineTwo), qtyToBuy);
            purchaseItem = new ItemStack(Material.getMaterial(lineFour), qtyForSale);
        } catch (Exception e){
            return false;
        }

        //todo find a chest

        return true;
    }
}
