package io.github.joeyoung658.Runnables;
import io.github.joeyoung658.Data.Data;
import io.github.joeyoung658.ItemChestShop;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
