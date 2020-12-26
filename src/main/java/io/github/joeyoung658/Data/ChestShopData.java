package io.github.joeyoung658.Data;

import io.github.joeyoung658.ChestShop.ChestShop;
import org.bukkit.Location;
import org.bukkit.block.Sign;

import java.util.HashMap;
import java.util.Map;

public class ChestShopData {
    private static Map<Location, ChestShop> chestShopHashMap = new HashMap<>();

    public ChestShop getChestShop(Location location){
        if (chestShopHashMap.containsKey(location)) {
            return chestShopHashMap.get(location);
        }
        return null;
    }

    public Map<Location, ChestShop> getAllChestShops(){
        return chestShopHashMap;
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

    public void saveChestShop(Location chestShop){
        if (chestShopHashMap.containsKey(chestShop)) {
            chestShopHashMap.remove(chestShop);
            //todo save to database
        }
    }

    public void saveData(){
        for (Location signLoc:chestShopHashMap.keySet()) {
            Sign sign = (Sign) signLoc.getBlock().getState();
                this.saveChestShop(signLoc);
        }
    }


    public void loadChestShop(){

    }


}
