package io.github.joeyoung658.listeners;

import io.github.joeyoung658.ItemChestShop;
import io.github.joeyoung658.Runnables.unloadChestShopByChunk;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.bukkit.scheduler.BukkitTask;

public class onChunkUnloadEvent implements Listener {

    @EventHandler
    public void onChunkUnloadEvent(ChunkUnloadEvent e){

        BukkitTask updatePlayerFile =
                new unloadChestShopByChunk(e.getChunk().getChunkSnapshot())
                        .runTaskAsynchronously(ItemChestShop.plugin);
    }
}
