package io.github.joeyoung658.listeners;

import io.github.joeyoung658.ItemChestShop;
import io.github.joeyoung658.Runnables.unloadChestShopByChunk;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkUnloadEvent;

public class onChunkUnloadEvent implements Listener {

    @EventHandler
    public void onChunkUnloadEvent(ChunkUnloadEvent e){
                new unloadChestShopByChunk(e.getChunk().getChunkSnapshot())
                        .runTaskAsynchronously(ItemChestShop.plugin);
    }
}
