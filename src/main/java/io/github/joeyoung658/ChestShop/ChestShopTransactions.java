package io.github.joeyoung658.ChestShop;

import io.github.joeyoung658.ItemChestShopServerMessages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class ChestShopTransactions {

    Player player;
    ChestShop chestShop;

    public ChestShopTransactions(Player player, ChestShop chestShop) {
        this.player = player;
        this.chestShop = chestShop;
    }

    public void completeTransaction() {

        if (!(this.targetHasBuyItems())){
            this.player.sendMessage(new ItemChestShopServerMessages(this.player).getServerPrefix()
                    + "You do not have enough " + this.chestShop.getPurchaseItem().toString() + " to do this!" );
            return;
        }

        if (this.isTargetInvenFull()){
            this.player.sendMessage(new ItemChestShopServerMessages(this.player).getServerPrefix()
                    + "You do not have room in your inventory for " + this.chestShop.getSaleItem().toString() + " !");
            return;
        }

        if (!(this.chestHasSaleItem())){
            this.player.sendMessage(new ItemChestShopServerMessages(this.player).getServerPrefix()
                    + this.chestShop.getChestShopOwner().getDisplayName() + " chest shop has ran out of " + this.chestShop.getSaleItem().toString() + " !");
            return;
        }

        if (!(this.isChestShopFull())) {
            this.player.sendMessage(new ItemChestShopServerMessages(this.player).getServerPrefix()
                    + this.chestShop.getChestShopOwner().getDisplayName() + " chest shop has ran out of storage!");
            return;
        }

        this.removeChestSaleItems();
        this.giveChestBuyItem();

        this.removeTargetBuyItems();
        this.giveTargetSaleItems();
        this.player.sendMessage(new ItemChestShopServerMessages(this.player).getServerPrefix() + " Transaction successful!");

        Bukkit.broadcastMessage(this.chestShop.toString());

    }

    public boolean chestHasSaleItem() {
         return this.chestShop.getChest().containsAtLeast(this.chestShop.getSaleItem(), this.chestShop.getQtyForSale());
    }

    public boolean targetHasBuyItems(){
        return this.player.getInventory().containsAtLeast(this.chestShop.getPurchaseItem(), this.chestShop.getQtyToBuy());
    }

    public void removeChestSaleItems(){
        this.chestShop.getChest().removeItem(this.chestShop.getSaleItem());
    }

    public void removeTargetBuyItems(){
        this.player.getInventory().removeItem(this.chestShop.getPurchaseItem());
    }

    public void giveTargetSaleItems() {
        Bukkit.broadcastMessage(ChatColor.RED + this.chestShop.getSaleItem().toString());
        this.player.getInventory().addItem(this.chestShop.getSaleItem());
    }

    public void giveChestBuyItem(){
        this.chestShop.getChest().addItem(this.chestShop.getPurchaseItem());
    }

    public boolean isChestShopFull(){
        return !Arrays.asList(this.chestShop.getChest()).contains(null);
    }

    public boolean isTargetInvenFull(){ return !Arrays.asList(this.player.getInventory().getStorageContents()).contains(null);}
}
