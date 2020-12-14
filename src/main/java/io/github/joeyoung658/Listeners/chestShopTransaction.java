package io.github.joeyoung658.Listeners;

import io.github.joeyoung658.ChestShop.ChestShop;
import io.github.joeyoung658.Data.ChestShopData;
import io.github.joeyoung658.ChestShop.ChestShopTransactions;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class chestShopTransaction implements Listener {
    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent e){

        //Bukkit.broadcastMessage(e.getClickedBlock().getLocation().toString());


        if (e.getAction() == Action.RIGHT_CLICK_BLOCK){
           if  (e.getClickedBlock().getType() == Material.OAK_SIGN){
               ChestShopData chestShopData = new ChestShopData();
               Location signLoc = e.getClickedBlock().getLocation();
               if (chestShopData.chestShopExists(signLoc)){
                   ChestShop chestshop = chestShopData.getChestShop(signLoc);
                   ChestShopTransactions chestShopTransactions = new ChestShopTransactions(e.getPlayer(), chestshop);
                   chestShopTransactions.completeTransaction();
               }
           }
        }
    }
}
