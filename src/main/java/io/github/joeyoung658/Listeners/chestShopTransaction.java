package io.github.joeyoung658.Listeners;

import io.github.joeyoung658.ChestShop.ChestShop;
import io.github.joeyoung658.ChestShop.ChestShopTransactions;
import io.github.joeyoung658.Data.ChestShopData;
import io.github.joeyoung658.ItemChestShop;
import io.github.joeyoung658.utli.ItemChestShopServerMessages;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;


public class chestShopTransaction implements Listener {

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent e){
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK){
            Material material = e.getClickedBlock().getType();
           if  (material == Material.OAK_SIGN){
               ChestShopData chestShopData = new ChestShopData();
               Location signLoc = e.getClickedBlock().getLocation();
               if (chestShopData.chestShopLoaded(signLoc)){
                   ChestShop chestshop = chestShopData.getChestShop(signLoc);
                   ChestShopTransactions chestShopTransactions = new ChestShopTransactions(e.getPlayer(), chestshop);
                   chestShopTransactions.completeTransaction();
               } else {
                   e.getPlayer().sendMessage(new ItemChestShopServerMessages().getServerPrefix() + "ChestShop loading...");
                   loadChestShop(e.getClickedBlock().getLocation(), result -> {
                       if (result){
                           e.getPlayer().sendMessage(new ItemChestShopServerMessages().getServerPrefix() + "The Chest shop has successfully loaded, please try again!");
                       } else {
                           e.getPlayer().sendMessage(new ItemChestShopServerMessages().getServerPrefix() + "A Chest shop does not exist at this location!");
                       }
                   });
               }
               return;
           }
        }
    }

    public interface loadChestShopCallBack {
        void onQueryDone(Boolean result);
    }

    /**
     *
     * @param loc
     * @param callback
     */
    public static void loadChestShop(final Location loc,
                            final loadChestShopCallBack callback) {
        Bukkit.getScheduler().runTaskAsynchronously(ItemChestShop.plugin, new Runnable() {
            @Override
            public void run() {
                ChestShopData chestShopData = new ChestShopData();
                boolean result = chestShopData.loadChestShop(loc);
                Bukkit.getScheduler().runTask(ItemChestShop.plugin, new Runnable() {
                    @Override
                    public void run() {
                        callback.onQueryDone(result);
                    }
                });
            }
        });
    }
}
