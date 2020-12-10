package io.github.joeyoung658;

import io.github.joeyoung658.Listeners.chestShopSetUp;
import org.bukkit.plugin.java.JavaPlugin;

public class ItemChestShop extends JavaPlugin {

    @Override
    public void onEnable() {
        registerListeners();
        getLogger().info("ItemChestShop is enabled.");
    }
    @Override
    public void onDisable() {
        //Save ChestShops
    }


    private void registerListeners(){
        getServer().getPluginManager().registerEvents(new chestShopSetUp(), this);
    }


}
