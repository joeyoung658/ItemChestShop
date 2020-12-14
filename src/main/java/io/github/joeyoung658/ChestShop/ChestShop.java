package io.github.joeyoung658.ChestShop;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class ChestShop {


    Player player;
    int qtyForSale;
    int qtyToBuy;
    ItemStack saleItem;
    ItemStack purchaseItem;
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
    public ChestShop(Player player, int qtyForSale,int qtyToBuy, ItemStack saleItem, ItemStack purchaseItem, Location chestShopLoc){
        this.player = player;
        this.qtyForSale = qtyForSale;
        this.qtyToBuy = qtyToBuy;
        this.saleItem = saleItem;
        this.purchaseItem = purchaseItem;
        this.chestShopLoc = chestShopLoc;
    }

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

    public Inventory getChest(){
        //Implement into a loop
        Location chestLoc = this.chestShopLoc;
        chestLoc.setY(this.chestShopLoc.getBlockY()-1);
        Block block = chestLoc.getBlock();
        if (block.getType() != Material.CHEST) return null;

        org.bukkit.block.Chest chest = (org.bukkit.block.Chest)  block.getState();
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

    
}
