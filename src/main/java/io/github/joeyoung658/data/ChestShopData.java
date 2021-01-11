package io.github.joeyoung658.data;

import io.github.joeyoung658.ChestShop.ChestShop;
import io.github.joeyoung658.ItemChestShop;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.Map;

public class ChestShopData {
    private static Map<Location, ChestShop> chestShopHashMap = new HashMap<>();

    /**
     *
     * @param location
     * @return
     */
    public ChestShop getChestShop(Location location){
        if (chestShopHashMap.containsKey(location)) {
            return chestShopHashMap.get(location);
        }
        return null;
    }

    /**
     *
     * @return
     */
    public Map<Location, ChestShop> getAllChestShops(){
        return chestShopHashMap;
    }

    /**
     *
     * @param location
     * @param chestShop
     */
    public void setChestShop(Location location, ChestShop chestShop){
        chestShopHashMap.put(location, chestShop);
    }

    /**
     *
     * @param loc
     * @return
     */
    public boolean chestShopLoaded(Location loc){
        return chestShopHashMap.containsKey(loc);
    }

    /**
     * Deletes a chestshop completely
     * @param location
     */
    public void removeChestStop(Location location){
        Data data = new Data(ItemChestShop.plugin);
        if (chestShopHashMap.containsKey(location)) {
            chestShopHashMap.remove(location);
        }
        data.deleteChestShopFile(location);
    }


    /**
     * Saves & removes single chestshop from array
     * @param chestShop
     */
    public void saveChestShop(Location chestShop){
        Data data = new Data(ItemChestShop.plugin);
        if (chestShopHashMap.containsKey(chestShop)) {
            data.createChestShopFile(chestShopHashMap.get(chestShop));
            chestShopHashMap.remove(chestShop);
        }
    }

    /**
     * Saves all chestshops in active array
     */
    public void saveData(){
        Data data = new Data(ItemChestShop.plugin);
        for (Location signLoc : chestShopHashMap.keySet()) {
            data.createChestShopFile(chestShopHashMap.get(signLoc));
        }
        chestShopHashMap.clear();
    }

    /**
     *
     * @param location
     * @return
     */
    public boolean loadChestShop(Location location) {
        Data data = new Data(ItemChestShop.plugin);
        try {
            ChestShop chestShop = new ChestShop();
            chestShop.setChestShopOwnerByUUID(data.loadChestShopOwnerUUID(location));
            int qtyForSale = data.loadChestShopQtyForSale(location);
            int qtyToBuy = data.loadChestShopQtyToBuy(location);
            chestShop.setQtyForSale(qtyForSale);
            chestShop.setQtyToBuy(qtyToBuy);
            chestShop.setSaleItem(data.loadChestShopSaleItem(location));
            chestShop.setPurchaseItem(data.loadChestShopPurchaseItem(location));
            chestShop.setChestShopLoc(location);
            this.setChestShop(location, chestShop);
        } catch (Exception e){
            return false;
        }
        return true;
    }
}
