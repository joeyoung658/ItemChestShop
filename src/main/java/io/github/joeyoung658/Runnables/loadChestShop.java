package io.github.joeyoung658.Runnables;

import io.github.joeyoung658.ItemChestShop;
import io.github.joeyoung658.data.ChestShopData;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author Joseph Young on 04/01/2021
 * @github https://github.com/joeyoung658
 */
public class loadChestShop extends BukkitRunnable {

    final Location loc;
    final loadChestShopCallBack callback;
    public loadChestShop(Location loc, loadChestShopCallBack callback){
        this.loc = loc;
        this.callback = callback;
    }

    public interface loadChestShopCallBack {
        void onQueryDone(Boolean result);
    }

    @Override
    public void run() {
        ChestShopData chestShopData = new ChestShopData();
        boolean result = chestShopData.loadChestShop(loc);
        Bukkit.getScheduler().runTask(ItemChestShop.plugin, () -> callback.onQueryDone(result));
    }
}
