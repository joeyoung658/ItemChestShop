package io.github.joeyoung658.ChestShop;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Sign;

import java.io.Serializable;
import java.util.UUID;

public class ChestShop implements Serializable {


    private Player player;
    private int qtyForSale;
    private int qtyToBuy;
    private ItemStack saleItem;
    private ItemStack purchaseItem;
    private Location chestShopLoc;


    public ChestShop(){

    }


//    /**
//     * Create a chestshop for the sale of items
//     * @param player
//     * @param qtyForSale
//     * @param qtyToBuy
//     * @param saleItem
//     * @param purchaseItem
//     * @param chestShopLoc
//     */
//    public ChestShop(Player player, int qtyForSale,int qtyToBuy, ItemStack saleItem, ItemStack purchaseItem, Location chestShopLoc){
//        this.player = player;
//        this.qtyForSale = qtyForSale;
//        this.qtyToBuy = qtyToBuy;
//        this.saleItem = saleItem;
//        this.purchaseItem = purchaseItem;
//        this.chestShopLoc = chestShopLoc;
//    }

    public int getQtyForSale(){
        return this.qtyForSale;
    }

    public int getQtyToBuy(){
        return this.qtyToBuy;
    }

    public ItemStack getSaleItem(){
        return this.saleItem;
    }

    public ItemStack getPurchaseItem(){
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


    
//    //Todo debug the below
//    public Inventory getChest(){
//        Sign sign = (Sign) this.chestShopLoc.getBlock().getState();
//
//
//
//        Location possLoc = sign.getLocation();
//        possLoc.setY(possLoc.getY() - 1);
//        if (isChest(possLoc)){
//           return getChestInven(possLoc);
//        }
//
//        possLoc = sign.getLocation();
//        possLoc.setZ(possLoc.getBlockZ()+1);
//        if (isChest(possLoc)){
//            return getChestInven(possLoc);
//        }
//
//        possLoc.setZ(possLoc.getBlockZ()-2);
//        if (isChest(possLoc)) {
//            return getChestInven(possLoc);
//        }
//
//        possLoc = sign.getLocation();
//        possLoc.setX(possLoc.getBlockX()+1);
//        if (isChest(possLoc)){
//            return getChestInven(possLoc);
//        }
//
//        possLoc.setX(possLoc.getBlockX()-2);
//        if (isChest(possLoc)){
//            return getChestInven(possLoc);
//        }
//        return null;
//    }
//
//    private boolean isChest(Location location){
//        return location.getBlock().getType() == Material.CHEST;
//    }
//
//    private Inventory getChestInven(Location location){
//        Chest chest = (Chest) location.getBlock().getState();
//        return chest.getInventory();
//    }


    @SuppressWarnings( "deprecation" )
    public Inventory getChest(){
        Sign sign = (Sign) this.chestShopLoc.getBlock().getState().getData();
        Block attached = this.chestShopLoc.getBlock().getRelative(sign.getAttachedFace());
        if (attached.getType() != Material.CHEST) return null;
        org.bukkit.block.Chest chest = (org.bukkit.block.Chest)  attached.getState();
        Inventory inventory = chest.getInventory();
        return inventory;
    }

    public void setQtyForSale(int qtyForSale) {
        this.qtyForSale = qtyForSale;
    }

    public void setQtyToBuy(int QtyToBuy){
        this.qtyToBuy = QtyToBuy;
    }

    public void setSaleItem(ItemStack saleItem){
        this.saleItem = saleItem;
    }

    public void setPurchaseItem(ItemStack purchaseItem){
        this.purchaseItem = purchaseItem;
    }

    public void setChestShopOwner(Player chestShopOwner){
        this.player = chestShopOwner;
    }

    public void setChestShopLoc(Location chestShopLoc){this.chestShopLoc = chestShopLoc;}



    public String toString(){
        return "Player: " + this.player.toString()
                + "qtyForSale: " + this.getQtyForSale()
                + "qtyToBuy: "  + this.getQtyToBuy()
                + "saleItem: " + this.getSaleItem().toString()
                + "purchaseItem: " + this.getPurchaseItem().toString()
                + "chestShopLoc: " + this.getChestLocation().toString();
    }
    
}
