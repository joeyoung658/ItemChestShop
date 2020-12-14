package io.github.joeyoung658;

import io.github.joeyoung658.Listeners.chestShopSetUp;
import io.github.joeyoung658.Listeners.chestShopTransaction;
import org.bukkit.plugin.java.JavaPlugin;

//todo can now only use the shop once can't spam for some reason?


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
        getServer().getPluginManager().registerEvents(new chestShopTransaction(), this);
    }
}
