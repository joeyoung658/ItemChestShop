package io.github.joeyoung658.ChestShop;

import io.github.joeyoung658.utli.ItemChestShopServerMessages;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class ChestShopTransactions {

    private Player player;
    private ChestShop chestShop;

    public ChestShopTransactions(Player player, ChestShop chestShop) {
        this.player = player;
        this.chestShop = chestShop;
    }

    public void completeTransaction() {

        if (!(this.targetHasBuyItems())){
            this.player.sendMessage(new ItemChestShopServerMessages().getServerPrefix()
                    + "You do not have enough " + this.chestShop.getPurchaseItem().toString() + " to do this!" );
            return;
        }
;
        if (this.isTargetInvenFull()){
            this.player.sendMessage(new ItemChestShopServerMessages().getServerPrefix()
                    + "You do not have room in your inventory for " + this.chestShop.getSaleItem().toString() + " !");
            return;
        }

//        if (!(this.chestHasSaleItem())){
//            this.player.sendMessage(new ItemChestShopServerMessages().getServerPrefix()
//                    + this.chestShop.getChestShopOwner().getDisplayName() + " chest shop has ran out of " + this.chestShop.getSaleItem().toString() + " !");
//            return;
//        }

        if (!(this.chestHasSaleItem())){
            this.player.sendMessage(new ItemChestShopServerMessages().getServerPrefix()
                     + " chest shop has ran out of " + this.chestShop.getSaleItem().toString() + " !");
            return;
        }

        if (!(this.isChestShopFull())) {
            this.player.sendMessage(new ItemChestShopServerMessages().getServerPrefix()
                    +  " chest shop has ran out of storage!");
            return;
        }

//        if (!(this.isChestShopFull())) {
//            this.player.sendMessage(new ItemChestShopServerMessages().getServerPrefix()
//                    + this.chestShop.getChestShopOwner().getDisplayName() + " chest shop has ran out of storage!");
//            return;
//        }

        this.removeChestSaleItems();
        this.giveChestBuyItem();

        this.removeTargetBuyItems();
        this.giveTargetSaleItems();
        this.player.sendMessage(new ItemChestShopServerMessages().getServerPrefix() + " Transaction successful!");

//        Bukkit.broadcastMessage(this.chestShop.toString());

    }

    public boolean chestHasSaleItem() {
        return this.inventoryHasItem(this.chestShop.getChest(), this.chestShop.getSaleItem(), this.chestShop.getQtyForSale());
//         return this.chestShop.getChest().containsAtLeast(this.chestShop.getSaleItem(), this.chestShop.getQtyForSale());
    }

    public boolean targetHasBuyItems(){
        return this.inventoryHasItem(this.player.getInventory(), this.chestShop.getPurchaseItem(), this.chestShop.getQtyToBuy());
        //return this.player.getInventory().containsAtLeast(this.chestShop.getPurchaseItem(), this.chestShop.getQtyToBuy());
    }

    public void removeChestSaleItems(){
        this.chestShop.getChest().removeItem(this.chestShop.getSaleItem());
    }

    public void removeTargetBuyItems(){
        this.player.getInventory().removeItem(this.chestShop.getPurchaseItem());
    }

    public void giveTargetSaleItems() {
        this.giveInventoryItem(this.player.getInventory(), this.chestShop.getSaleItem(), this.chestShop.getQtyForSale());
       // this.player.getInventory().addItem(this.chestShop.getSaleItem());
    }

    public void giveChestBuyItem(){
        this.giveInventoryItem(this.chestShop.getChest(), this.chestShop.getPurchaseItem(), this.chestShop.getQtyToBuy());
      //  this.chestShop.getChest().addItem(this.chestShop.getPurchaseItem());
    }

    public boolean isChestShopFull(){
        return !Arrays.asList(this.chestShop.getChest()).contains(null);
    }

    public boolean isTargetInvenFull(){
        return !Arrays.asList(this.player.getInventory().getStorageContents()).contains(null);
    }


    private boolean inventoryHasItem(Inventory inventory, ItemStack itemStack, int qty){
        if (inventory.isEmpty()){
            return false;
        }
        int itemAmount = 0;
        for (ItemStack item : inventory.getContents()) {
            if (item != null) {
                if (item.getType() == itemStack.getType()) {
                    itemAmount += item.getAmount();
                    if (itemAmount >= qty) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    private void giveInventoryItem(Inventory inventory, ItemStack itemStack, int qty){
        if (!inventory.contains(itemStack.getType())){
            this.setEmptyIvenSlot(inventory, itemStack, qty);
            return;
        }

        for (int i = 0; i < inventory.getSize(); i++) {
            ItemStack item = inventory.getItem(i);
            if (item != null && item.getAmount() != 64){
//                Bukkit.broadcastMessage(ChatColor.AQUA  + inventory.getItem(i).toString());
                if (item.getType() == itemStack.getType()){
                    int itemAmt = qty + item.getAmount();
                    if (itemAmt > 64) {
                        item.setAmount(64);
                        inventory.setItem(i, item);
                        qty = itemAmt - 64;
                        setEmptyIvenSlot(inventory,item, qty);
                    } else {
                        item.setAmount(itemAmt);
                        inventory.setItem(i, item);
                    }
                    return;
                }

            }
        }
        if (qty != 0){
            setEmptyIvenSlot(inventory,itemStack, qty);
            return;
        }
    }


    private void setEmptyIvenSlot(Inventory inventory, ItemStack itemStack, int qty){
        itemStack.setAmount(qty);
        for (int i = 0; i < inventory.getSize(); i++) {
            if(inventory.getItem(i) == null) {
                inventory.setItem(i, itemStack);
                return;
            }
        }
    }

}
