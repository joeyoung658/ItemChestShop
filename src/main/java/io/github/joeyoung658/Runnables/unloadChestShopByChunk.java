package io.github.joeyoung658.Runnables;

import io.github.joeyoung658.ChestShop.ChestShop;
import io.github.joeyoung658.Data.ChestShopData;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Map;

/**
 * @author Joseph Young on 24/12/2020
 * @github https://github.com/joeyoung658
 */
public class unloadChestShopByChunk extends BukkitRunnable {

    Chunk chunk;
    private static Map<Location, ChestShop> chestShopHashMap = new ChestShopData().getAllChestShops();
    public unloadChestShopByChunk(Chunk chunk){
        this.chunk = chunk;
    }

    @Override
    public void run() {
        ChestShopData data = new ChestShopData();
        for (Location signLoc:chestShopHashMap.keySet()) {
            Sign sign = (Sign) signLoc.getBlock().getState();
            if (sign.getChunk().equals(this.chunk)){
                data.saveChestShop(signLoc);
            }
        }
    }
}
