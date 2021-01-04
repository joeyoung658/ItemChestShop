package io.github.joeyoung658.listeners;

import io.github.joeyoung658.data.ChestShopData;
import io.github.joeyoung658.data.Data;
import io.github.joeyoung658.ItemChestShop;
import io.github.joeyoung658.utli.ItemChestShopServerMessages;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class chestShopDisable implements Listener {

    @EventHandler
    public void blockBreakEvent(BlockBreakEvent event){
        Material material = event.getBlock().getType();
        if (material == Material.OAK_SIGN || material == Material.OAK_WALL_SIGN) {
            Location signLoc = event.getBlock().getLocation();
            ChestShopData chestShopData = new ChestShopData();
            if (chestShopData.chestShopLoaded(signLoc)) {
                chestShopData.removeChestStop(signLoc);
                event.getPlayer().sendMessage(new ItemChestShopServerMessages().getServerPrefix()
                        + "The chest shop has been removed successfully!");
            } else {
                if (new Data(ItemChestShop.plugin).chestShopFileExists(signLoc)){
                    chestShopData.removeChestStop(signLoc);
                    event.getPlayer().sendMessage(new ItemChestShopServerMessages().getServerPrefix()
                            + "The chest shop has been removed successfully!");
                }
            }
            return;
        }
        if (event.getBlock().getType() == Material.CHEST){
            Chest chest = (Chest) event.getBlock().getState();
            //todo check if a sign is attached to the chest if so remove the chest shop
        }
    }
}
