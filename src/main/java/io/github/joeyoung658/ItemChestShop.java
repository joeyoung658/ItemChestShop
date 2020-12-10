package io.github.joeyoung658;

import org.bukkit.plugin.java.JavaPlugin;

public class ItemChestShop extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("Itemchest Shop is enabled");
    }
    @Override
    public void onDisable() {
        getLogger().info("onDisable is called!");
    }
}
