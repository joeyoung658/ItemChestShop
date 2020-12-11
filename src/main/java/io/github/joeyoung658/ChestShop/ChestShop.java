package io.github.joeyoung658.ChestShop;

import org.bukkit.Location;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ChestShop {

    Player player;
    int qtyForSale;
    int qtyToBuy;
    Item saleItem;
    Item purchaseItem;
    Location chestShopLoc;

    /**
     * Create a chestshop for the sale of items
     * @param player
     * @param qtyForSale
     * @param qtyToBuy
     * @param saleItem
     * @param purchaseItem
     * @param chestShopLoc
     */
    public ChestShop(Player player, int qtyForSale,int qtyToBuy, Item saleItem, Item purchaseItem, Location chestShopLoc){
        this.player = player;
        this.qtyForSale = qtyForSale;
        this.qtyToBuy = qtyToBuy;
        this.saleItem = saleItem;
        this.purchaseItem = purchaseItem;
        this.chestShopLoc = chestShopLoc;
    }


//    public ChestShop getChestShop(Player player){
//        //todo implement function
//
//    }

    public int getQtyForSale(){
        return this.qtyForSale;
    }

    public int getQtyToBuy(){
        return this.qtyToBuy;
    }

    public Item getSaleItem(){
        return this.saleItem;
    }

    public Item getPurchaseItem(){
        return this.purchaseItem;
    }

    public Location getChestLocation(){
        return this.chestShopLoc;
    }

    public UUID getChestShopOwnerUUID(){
        return this.player.getUniqueId();
    }

    public Player getChestShopOwner(){
        return this.player;
    }

    public void setQtyForSale(int qtyForSale) {
        this.qtyForSale = qtyForSale;
    }

    public void setQtyToBuy(int QtyToBuy){
        this.qtyToBuy = QtyToBuy;
    }

    public void setSaleItem(Item saleItem){
        this.saleItem = saleItem;
    }

    public void setPurchaseItem(Item purchaseItem){
        this.purchaseItem = purchaseItem;
    }

    public void setChestShopOwner(Player chestShopOwner){
        this.player = chestShopOwner;
    }

    public void setChestShopLoc(Location chestShopLoc){this.chestShopLoc = chestShopLoc;}

}
