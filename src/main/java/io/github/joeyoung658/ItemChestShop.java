package io.github.joeyoung658;

import io.github.joeyoung658.Listeners.chestShopDisable;
import io.github.joeyoung658.Listeners.chestShopSetUp;
import io.github.joeyoung658.Listeners.chestShopTransaction;
import org.bukkit.plugin.java.JavaPlugin;

//Todo
// Debug Transactions
//Save Data to database
//Auto create land claim if within the shopping district
//If you break a chest under the chestshop sign


public class ItemChestShop extends JavaPlugin {

    public static ItemChestShop instance;
    @Override
    public void onEnable() {
        instance = this;
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
        getServer().getPluginManager().registerEvents(new chestShopDisable(), this);
    }
}
