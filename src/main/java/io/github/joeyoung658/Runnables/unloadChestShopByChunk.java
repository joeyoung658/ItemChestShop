package io.github.joeyoung658.Runnables;

import io.github.joeyoung658.ChestShop.ChestShop;
import io.github.joeyoung658.data.ChestShopData;
import org.bukkit.ChunkSnapshot;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Map;

/**
 * @author Joseph Young on 24/12/2020
 * @github https://github.com/joeyoung658
 */
public class unloadChestShopByChunk extends BukkitRunnable {

    //todo debug & test (Changed to chunk snap shot
    ChunkSnapshot chunkSnapshot;
    private static Map<Location, ChestShop> chestShopHashMap = new ChestShopData().getAllChestShops();
    public unloadChestShopByChunk(ChunkSnapshot chunk){
        this.chunkSnapshot = chunk;
    }

    @Override
    public void run() {
        ChestShopData data = new ChestShopData();
        for (Location signLoc:chestShopHashMap.keySet()) {
            Sign sign = (Sign) signLoc.getBlock().getState();
            if (sign.getChunk().equals(this.chunkSnapshot)){
                data.saveChestShop(signLoc);
            }
        }
    }
}
