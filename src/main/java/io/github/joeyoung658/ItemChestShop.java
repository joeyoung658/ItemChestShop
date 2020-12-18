package io.github.joeyoung658;

import io.github.joeyoung658.Listeners.chestShopDisable;
import io.github.joeyoung658.Listeners.chestShopSetUp;
import io.github.joeyoung658.Listeners.chestShopTransaction;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

//Todo
//Save Data to database
//Auto create land claim if within the shopping district


public class ItemChestShop extends JavaPlugin {

    public static ItemChestShop plugin;

    @Override
    public void onEnable() {
        plugin = this;
        mkdirDataFolder();
        registerListeners();
        getLogger().info(getDescription().getFullName()
                + " Version " + getDescription().getVersion() +" has been enabled.");
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


    private void mkdirDataFolder(){
        File dir = getDataFolder();
        if (!dir.exists()){
            if (!dir.mkdir()){
                getLogger().info(ChatColor.RED + getDescription().getFullName() + " could not create data folder, check permissions and try again.");
                plugin.getServer().getPluginManager().disablePlugin(this);
            }
        }
    }

    public static ItemChestShop getPlugin(){
        return plugin;
    }

}
