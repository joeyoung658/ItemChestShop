package io.github.joeyoung658.Listeners;

import io.github.joeyoung658.ItemChestShop;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.bukkit.scheduler.BukkitTask;
import io.github.joeyoung658.Runnables.unloadChestShopByChunk;

public class onChunkUnloadEvent implements Listener {

    @EventHandler
    public void onChunkUnloadEvent(ChunkUnloadEvent e){
        BukkitTask updatePlayerFile =
                new unloadChestShopByChunk(e.getChunk())
                        .runTaskAsynchronously(ItemChestShop.plugin);
    }
}
