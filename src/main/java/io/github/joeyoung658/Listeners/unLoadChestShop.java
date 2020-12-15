package io.github.joeyoung658.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkUnloadEvent;

public class unLoadChestShop implements Listener {

    @EventHandler
    public void onChunkUnloadEvent(ChunkUnloadEvent e){
        //todo implement function
        //Unload any chestshops that do not need to be within array
        //Do Async
        //Save any new to file
    }
}
