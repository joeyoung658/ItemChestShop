package io.github.joeyoung658.ChestShop;

import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class ChestShopTransactions {

    Player player;
    ChestShop chestShop;

    public ChestShopTransactions(Player player, ChestShop chestShop) {
        this.player = player;
        this.chestShop = chestShop;
    }

    public void completeTransaction() {
        this.giveChestBuyItem(this.chestShop.getPurchaseItem());
        this.giveTargetSaleItems(this.chestShop.getSaleItem());
        player.sendMessage("Check the chest for eggs!");
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

    public void giveTargetSaleItems(ItemStack saleItems) {
        if (!(this.isTargetInvenFull(this.player))){
            this.player.getInventory().addItem(saleItems);
        }
    }

    public void giveChestBuyItem(ItemStack saleItems){
        Inventory chest = this.chestShop.getChest();
        if (!(this.isChestShopFull(chest))){
            chest.addItem(saleItems);
        }
    }

    public boolean isChestShopFull(Inventory chest){
        return !Arrays.asList(chest.getStorageContents()).contains(null);
    }

    public boolean isTargetInvenFull(Player p){
        return !Arrays.asList(p.getInventory().getStorageContents()).contains(null);
    }

}
