package io.github.joeyoung658.Data;

import io.github.joeyoung658.ChestShop.ChestShop;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.Map;

public class ChestShopData {
    private static Map<Location, ChestShop> chestShopHashMap = new HashMap<>();


    public ChestShop getChestShop(Location location){
        if (chestShopHashMap.containsKey(location)) {
            return chestShopHashMap.get(location);
        } else {
            return null;
        }
    }
    public void setChestShop(Location location, ChestShop chestShop){
        chestShopHashMap.put(location, chestShop);
    }

    public boolean chestShopExists(Location loc){
        return chestShopHashMap.containsKey(loc);
    }

    public void removeChestStop(Location location){
        if (chestShopHashMap.containsKey(location)) {
            chestShopHashMap.remove(location);
        } else {
            //todo remove from database
        }
    }

    public void saveData(){

    }

    public void loadChestShop(){

    }

}
