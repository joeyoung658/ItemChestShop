package io.github.joeyoung658.listeners;

import io.github.joeyoung658.ItemChestShop;
import io.github.joeyoung658.Runnables.updatePlayerFile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitTask;

/**
 * @author Joseph Young on 23/12/2020
 * @github https://github.com/joeyoung658
 */
public class playerJoinEvent implements Listener {
    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        BukkitTask updatePlayerFile =
                new updatePlayerFile
                        (ItemChestShop.plugin, event.getPlayer())
                        .runTaskAsynchronously(ItemChestShop.plugin);
    }
}