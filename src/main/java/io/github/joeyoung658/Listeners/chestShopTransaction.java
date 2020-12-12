package io.github.joeyoung658.Listeners;

import io.github.joeyoung658.ChestShop.ChestShopTransactions;
import io.github.joeyoung658.ChestShop.ChestShopVaildator;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class chestShopTransaction implements Listener {
    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent e){
        if (e.getAction() == Action.LEFT_CLICK_BLOCK){
            ItemStack sign = new ItemStack(Material.OAK_SIGN);
           if  (e.getClickedBlock() == sign){
               ChestShopVaildator chestShopVaildator = new ChestShopVaildator();
               if (chestShopVaildator.chestShopExists(e.getClickedBlock().getLocation())){
                   ChestShopTransactions chestShopTransactions = new ChestShopTransactions(e.getPlayer());
                   chestShopTransactions.completeTransaction();
                   return;
               }
           }
        }
    }
}
