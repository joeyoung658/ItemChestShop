package io.github.joeyoung658.ChestShop;

import io.github.joeyoung658.ItemChestShopServerMessages;
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
            //todo add toggle to send message to shop owner
            return;
        }

        if (!(this.isChestShopFull())) {
            this.player.sendMessage(new ItemChestShopServerMessages(this.player).getServerPrefix()
                    + this.chestShop.getChestShopOwner().getDisplayName() + " chest shop has ran out of storage!");
            //todo add toggle to send message to shop owner
            return;
        }

        this.removeChestSaleItems();
        this.giveChestBuyItem();

        this.removeTargetBuyItems();
        this.giveTargetSaleItems();
        player.sendMessage(new ItemChestShopServerMessages(this.player).getServerPrefix() + " Transaction successful!");
    }

    public boolean chestHasSaleItem() {
         return this.chestShop.getChest().contains(this.chestShop.getSaleItem());
    }

    public boolean targetHasBuyItems(){
        return this.player.getInventory().contains(this.chestShop.getPurchaseItem());
    }

    public void removeChestSaleItems(){
        this.chestShop.getChest().remove(this.chestShop.getSaleItem());
    }

    public void removeTargetBuyItems(){
        this.player.getInventory().remove(this.chestShop.getPurchaseItem());
    }

    public void giveTargetSaleItems() {
        this.player.getInventory().addItem(this.chestShop.getSaleItem());
    }

    public void giveChestBuyItem(){
        this.chestShop.getChest().addItem(this.chestShop.getPurchaseItem());
    }

    public boolean isChestShopFull(){
        return !Arrays.asList(this.chestShop.getChest()).contains(null);
    }

    public boolean isTargetInvenFull(){
        return !Arrays.asList(this.player.getInventory().getStorageContents()).contains(null);
    }
}
