package io.github.joeyoung658.ChestShop;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;


/**
 * @author Joseph Young on 30/12/2020
 * @github https://github.com/joeyoung658
 */
public class ChestShopValidator {


    public static boolean isValidSign(String[] lineText, Block sign, Location signLoc){

        if (!(sign.getType() == Material.OAK_SIGN)){
            if (!(sign.getType() == Material.OAK_WALL_SIGN)) {
                return false;
            }
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

            if (qtyForSale > 64 || qtyToBuy > 64){
                return false;
            }

            saleItem = new ItemStack(Material.getMaterial(lineTwo), qtyToBuy);
            purchaseItem = new ItemStack(Material.getMaterial(lineFour), qtyForSale);
        } catch (Exception e){
            return false;
        }
        if (!(detectChest(signLoc))){
            return false;
        }
        return true;
    }


    private static boolean detectChest(Location loc){
        ChestShop testChest = new ChestShop();
        testChest.setChestShopLoc(loc);
        if(testChest.getChest() == null){
            return false;
        }
        return true;
    }


}
