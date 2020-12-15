package io.github.joeyoung658.Listeners;

import io.github.joeyoung658.Data.ChestShopData;
import io.github.joeyoung658.ItemChestShop;
import io.github.joeyoung658.ItemChestShopServerMessages;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.material.Chest;
import org.bukkit.material.Sign;

public class chestShopDisable implements Listener {

    @EventHandler
    public void blockBreakEvent(BlockBreakEvent event){
        if (event.getBlock().getType() == Material.OAK_SIGN) {
            Location signLoc = event.getBlock().getLocation();
            ChestShopData chestShopData = new ChestShopData();
            if (chestShopData.chestShopExists(signLoc)) {
                chestShopData.removeChestStop(signLoc);
                event.getPlayer().sendMessage(new ItemChestShopServerMessages(event.getPlayer()).getServerPrefix()
                        + "The chest shop has been removed successfully!");
            }
            return;
        }
        if (event.getBlock().getType() == Material.CHEST){
            //todo check if a sign is attached to the chest if so remove the chest shop
        }
    }
}
