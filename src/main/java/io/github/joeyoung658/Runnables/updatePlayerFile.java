package io.github.joeyoung658.Runnables;

import io.github.joeyoung658.ItemChestShop;
import io.github.joeyoung658.data.Data;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class updatePlayerFile extends BukkitRunnable {

    Player player;
    ItemChestShop plugin;
    public updatePlayerFile(ItemChestShop plugin, Player player){
        this.plugin = plugin;
        this.player = player;
    }

    @Override
    public void run() {
        Data data = new Data(this.plugin);
        data.createPlayerFile(this.player);
    }
}
