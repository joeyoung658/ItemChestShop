package io.github.joeyoung658.ChestShop;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.Serializable;
import java.util.UUID;

public class ChestShop implements Serializable {



    private Player player;
    private int qtyForSale;
    private int qtyToBuy;
    private ItemStack saleItem;
    private ItemStack purchaseItem;
    private Location chestShopLoc;


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

    public Location getChestShopLoc(){
        return this.chestShopLoc;
    }

    public UUID getChestShopOwnerUUID(){
        return this.player.getUniqueId();
    }

    public Player getChestShopOwner(){
        return this.player;
    }

//    public Inventory getChest(){
//        Sign sign = (Sign) this.chestShopLoc.getBlock().getState();
//        Location possLoc = sign.getLocation();
//        possLoc.setY(possLoc.getY() - 1);
//        if (isChest(possLoc)){
//           return getChestInven(possLoc);
//        }
//        String wallSignFacing = this.getFacing(sign).name();
//        Bukkit.broadcastMessage(wallSignFacing);
//        switch (wallSignFacing) {
//            case "NORTH":
//                possLoc = sign.getLocation();
//                possLoc.setZ(possLoc.getBlockZ()+1);
//                if (isChest(possLoc)){
//                    return getChestInven(possLoc);
//                }
//                break;
//            case "SOUTH":
//                possLoc = sign.getLocation();
//                possLoc.setZ(possLoc.getBlockZ()-1);
//                if (isChest(possLoc)){
//                    return getChestInven(possLoc);
//                }
//            case "EAST":
//                possLoc = sign.getLocation();
//                possLoc.setX(possLoc.getBlockX()-1);
//                if (isChest(possLoc)){
//                    return getChestInven(possLoc);
//                }
//            case "WEST":
//                possLoc = sign.getLocation();
//                possLoc.setX(possLoc.getBlockX()+1);
//                if (isChest(possLoc)){
//                    return getChestInven(possLoc);
//                }
//            default:
//                return null;
//        }
//        return null;
//    }

    public Inventory getChest(){
        Sign sign = (Sign) this.chestShopLoc.getBlock().getState();

        Location possLoc = sign.getLocation();
        possLoc.setY(possLoc.getY() - 1);
        if (isChest(possLoc)){
            return getChestInven(possLoc);
        }

        possLoc = sign.getLocation();
        possLoc.setZ(possLoc.getBlockZ()+1);
        if (isChest(possLoc)){
            return getChestInven(possLoc);
        }

        possLoc.setZ(possLoc.getBlockZ()-2);
        if (isChest(possLoc)) {
            return getChestInven(possLoc);
        }

        possLoc = sign.getLocation();
        possLoc.setX(possLoc.getBlockX()+1);
        if (isChest(possLoc)){
            return getChestInven(possLoc);
        }

        possLoc.setX(possLoc.getBlockX()-2);
        if (isChest(possLoc)){
            return getChestInven(possLoc);
        }
        return null;
    }


    private BlockFace getFacing(Sign sign){
        Location block2loc = sign.getLocation();
        block2loc.setY(sign.getY() - 1);
        Block block2 = block2loc.getBlock();
        return sign.getBlock().getFace(block2);
    }


    private boolean isChest(Location location){
        return location.getBlock().getType() == Material.CHEST;
    }

    private Inventory getChestInven(Location location){
        Chest chest = (Chest) location.getBlock().getState();
        return chest.getInventory();
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

    public void setChestShopOwnerByUUID(String UUID) {
        Player playerByUUID = Bukkit.getPlayer(UUID);
        if (player == null){
            return;
        }
        this.player = playerByUUID;
    }

    public void setChestShopLoc(Location chestShopLoc){this.chestShopLoc = chestShopLoc;}

    public String toString(){
        return "Player: " + this.player.toString()
                + "qtyForSale: " + this.getQtyForSale()
                + "qtyToBuy: "  + this.getQtyToBuy()
                + "saleItem: " + this.getSaleItem().toString()
                + "purchaseItem: " + this.getPurchaseItem().toString()
                + "chestShopLoc: " + this.getChestShopLoc().toString();
    }
    
}
