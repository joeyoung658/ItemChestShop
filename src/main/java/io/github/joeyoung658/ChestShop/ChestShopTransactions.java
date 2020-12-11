package io.github.joeyoung658.ChestShop;

import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ChestShopTransactions {

    Player player;

    public ChestShopTransactions(Player player) {
        this.player = player;
    }

    public void completeTransaction() {
        //todo implement function
    }

    public boolean chestHasSaleItem(Chest chest, ItemStack items) {
        if (chest.getBlockInventory().contains(items)) {
            return true;
        }
        return false;
    }

    public boolean targetHasBuyItems(Player target, ItemStack items){
        if (target.getInventory().contains(items)){
            return true;
        }
        return false;
    }

    public ItemStack getChestSaleItems(){
        //todo implement function
        return new ItemStack(Material.CHEST);
    }

    public ItemStack getTargetBuyItems(){
        //todo implement function
        return new ItemStack(Material.CHEST);
    }

    public void removeChestSaleItems(Chest chest, ItemStack items){
        chest.getBlockInventory().remove(items);
    }

    public void removeTargetBuyItems(ItemStack buyItems){
        this.player.getInventory().remove(buyItems);
    }

    public void giveTargetSaleItems(int numberOfItems, ItemStack saleItems) {
        ChestShopVaildator chestShopVaildator = new ChestShopVaildator();
        if (!(chestShopVaildator.isTargetInvenFull(this.player))){
            this.player.getInventory().setItem(numberOfItems, saleItems);
        }
    }

    public void giveChestBuyItem(Chest chest,int numberOfItems,  ItemStack saleItems){
        ChestShopVaildator chestShopVaildator = new ChestShopVaildator();
        if (!(chestShopVaildator.isChestShopFull(chest))){
            chest.getInventory().setItem(numberOfItems, saleItems);
        }
    }
}
