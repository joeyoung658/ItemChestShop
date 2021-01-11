package io.github.joeyoung658.data;

import io.github.joeyoung658.ChestShop.ChestShop;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Joseph Young on 11/01/2021
 * @github https://github.com/joeyoung658
 */
public class ChestShopSetUpData {

    /**
     * Location = Player Loc
     * Chestshop = chestshop
     */
    private static Map<Location, ChestShop> chestShopSetUpHashMap = new HashMap<>();


    public ChestShop getChestShop(Location location){
        return chestShopSetUpHashMap.get(location);
    }

    /**
     * Sets chestshop to array whilst being set up
     * @param location Location of player so chest shop can be located through objects
     * @param chestShop Chestshop that is being set up
     */
    public void setChestShop(Location location, ChestShop chestShop){
        chestShopSetUpHashMap.put(location, chestShop);
    }


    public void removeChestShop(Location location){
        chestShopSetUpHashMap.remove(location);
    }

}
