package io.github.joeyoung658.ChestShop;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.ArrayList;


/**
 * @author Joseph Young on 30/12/2020
 * @github https://github.com/joeyoung658
 */
public class ChestShopValidator {


    public static boolean isValidSign(String[] lineText, Block sign, Location signLoc){

        ArrayList<Material> signTypes = new ArrayList();
        signTypes.add(Material.OAK_SIGN);
        signTypes.add(Material.OAK_WALL_SIGN);
        signTypes.add(Material.SPRUCE_SIGN);
        signTypes.add(Material.SPRUCE_WALL_SIGN);
        signTypes.add(Material.BIRCH_SIGN);
        signTypes.add(Material.BIRCH_WALL_SIGN);
        signTypes.add(Material.JUNGLE_SIGN);
        signTypes.add(Material.JUNGLE_WALL_SIGN);
        signTypes.add(Material.ACACIA_SIGN);
        signTypes.add(Material.ACACIA_WALL_SIGN);
        signTypes.add(Material.DARK_OAK_SIGN);
        signTypes.add(Material.DARK_OAK_WALL_SIGN);
        signTypes.add(Material.CRIMSON_SIGN);
        signTypes.add(Material.CRIMSON_WALL_SIGN);
        signTypes.add(Material.WARPED_SIGN);
        signTypes.add(Material.WARPED_WALL_SIGN);

        if (!signTypes.contains(sign.getType())){
            return false;
        }

        boolean shopSignText = false;
        for (String line : lineText) {
             shopSignText = line.equalsIgnoreCase("[ChestShop]");
        }

        if (!shopSignText){
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
        return testChest.getChest() != null;
    }


}
