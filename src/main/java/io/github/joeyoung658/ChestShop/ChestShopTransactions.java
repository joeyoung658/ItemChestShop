package io.github.joeyoung658.ChestShop;

import io.github.joeyoung658.utli.ItemChestShopServerMessages;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ChestShopTransactions {

    final private Player player;
    final private ChestShop chestShop;

    public ChestShopTransactions(Player player, ChestShop chestShop) {
        this.player = player;
        this.chestShop = chestShop;
    }

    public void completeTransaction() {

        if (!(this.targetHasBuyItems())){
            this.player.sendMessage(new ItemChestShopServerMessages().getServerPrefix()
                    + "You do not have enough " + this.chestShop.getPurchaseItem().getType().toString().toLowerCase() + " to do this!" );
            return;
        }

        if (this.isTargetInvenFull()){
            this.player.sendMessage(new ItemChestShopServerMessages().getServerPrefix()
                    + "You do not have room in your inventory for " + this.chestShop.getSaleItem().getType().toString().toLowerCase() + " !");
            return;
        }

        if (!(this.chestHasSaleItem())){
            if (this.chestShop.getChestShopOwner() == null){
                this.player.sendMessage(new ItemChestShopServerMessages().getServerPrefix()
                        + " chest shop has ran out of " + this.chestShop.getSaleItem().getType().toString().toLowerCase() + " !");
                return;
            }
            this.player.sendMessage(new ItemChestShopServerMessages().getServerPrefix()
                    + this.chestShop.getChestShopOwner().getDisplayName() + " chest shop has ran out of " + this.chestShop.getSaleItem().getType().toString().toLowerCase() + " !");
            return;
        }


        if (this.isChestShopFull()) {
            if (this.chestShop.getChestShopOwner() == null){
                this.player.sendMessage(new ItemChestShopServerMessages().getServerPrefix()
                        + "'s chest shop has ran out of storage!");
                return;
            }
            this.player.sendMessage(new ItemChestShopServerMessages().getServerPrefix()
                    + this.chestShop.getChestShopOwner().getDisplayName() + "'s chest shop has ran out of storage!");
            return;
        }


        this.removeChestSaleItems();
        this.giveChestBuyItem();
        this.removeTargetBuyItems();
        this.giveTargetSaleItems();
        this.player.sendMessage(new ItemChestShopServerMessages().getServerPrefix() + " Transaction successful!");

    }

    public boolean chestHasSaleItem() {
        return this.inventoryHasItem(this.chestShop.getChest(), this.chestShop.getSaleItem(), this.chestShop.getQtyForSale());
    }

    public boolean targetHasBuyItems(){
        return this.inventoryHasItem(this.player.getInventory(), this.chestShop.getPurchaseItem(), this.chestShop.getQtyToBuy());
    }

    public void removeChestSaleItems(){
        this.chestShop.getChest().removeItem(this.chestShop.getSaleItem());
    }

    public void removeTargetBuyItems(){
        this.player.getInventory().removeItem(this.chestShop.getPurchaseItem());
    }

    public void giveTargetSaleItems() {
        this.giveInventoryItem(this.player.getInventory(), this.chestShop.getSaleItem(), this.chestShop.getQtyForSale());
    }

    public void giveChestBuyItem(){
        this.giveInventoryItem(this.chestShop.getChest(), this.chestShop.getPurchaseItem(), this.chestShop.getQtyToBuy());
    }



    public boolean isChestShopFull(){
        return this.isInventoryFull(this.chestShop.getChest().getStorageContents(),this.chestShop.getPurchaseItem().getType(),  this.chestShop.getQtyToBuy());
    }
    
    
    public boolean isTargetInvenFull(){
        return this.isInventoryFull(this.player.getInventory().getStorageContents(), this.chestShop.getSaleItem().getType(), this.chestShop.getQtyForSale());
        //return !Arrays.asList(this.player.getInventory().getStorageContents()).contains(null);
    }


    /**
     *
     * @param storageContents Storage contents of the inventory wished to be checked for space
     * @param transactionItemType Type of item within the transaction
     * @param transactionQtyAmt Transaction currency amt
     * @return If inventory is full or not
     */
    private boolean isInventoryFull(ItemStack[] storageContents,
                                    Material transactionItemType,
                                    int transactionQtyAmt){
        boolean space = false;
        for (ItemStack item: storageContents) {
            if (!(item == null || item.getType() == Material.AIR)){
                if (item.getType() == transactionItemType) {
                    int qty = item.getAmount() + transactionQtyAmt;
                    space = qty > item.getMaxStackSize();
                }
            } else {
                return false;
            }
        }
        return space;
    }

    /**
     *
     * @param inventory Inventory to check
     * @param itemStack Item to check for
     * @param qty Amount to check for
     * @return If given inventory has item
     */
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

    /**
     *
     * @param inventory Inventory to give item too
     * @param itemStack Item to give
     * @param qty qty of item to give
     */

    private void giveInventoryItem(Inventory inventory, ItemStack itemStack, int qty){
        if (!inventory.contains(itemStack.getType())){
            this.setEmptyInvenSlot(inventory, itemStack, qty);
            return;
        }
        for (int i = 0; i < inventory.getSize(); i++) {
            ItemStack item = inventory.getItem(i);
            if (item != null && item.getAmount() != item.getMaxStackSize()){
                if (item.getType() == itemStack.getType()){
                    int itemAmt = qty + item.getAmount();
                    if (itemAmt > item.getMaxStackSize()) {
                        item.setAmount(item.getMaxStackSize());
                        inventory.setItem(i, item);
                        qty = itemAmt - item.getMaxStackSize();
                        setEmptyInvenSlot(inventory,item, qty);
                    } else {
                        item.setAmount(itemAmt);
                        inventory.setItem(i, item);
                    }
                    return;
                }

            }
        }
        if (qty != 0){
            setEmptyInvenSlot(inventory,itemStack, qty);
        }
    }

    /**
     *
     * @param inventory Inventory to set item to empty slot
     * @param itemStack Item to set
     * @param qty amt for item
     */
    private void setEmptyInvenSlot(Inventory inventory, ItemStack itemStack, int qty){
        if (inventory == null){
            return;
        }
        itemStack.setAmount(qty);
        for (int i = 0; i < inventory.getSize(); i++) {
            if(inventory.getItem(i) == null) {
                inventory.setItem(i, itemStack);
                return;
            }
        }
    }

}
