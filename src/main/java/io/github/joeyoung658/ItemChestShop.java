package io.github.joeyoung658;

import io.github.joeyoung658.Commands.admin.AdminCommandHandler;
import io.github.joeyoung658.Commands.admin.AdminTabCompleter;
import io.github.joeyoung658.Commands.admin.Commands.adminCmd;
import io.github.joeyoung658.Commands.admin.Commands.saveCmd;
import io.github.joeyoung658.data.ChestShopData;
import io.github.joeyoung658.data.Data;
import io.github.joeyoung658.listeners.*;
import org.bukkit.plugin.java.JavaPlugin;

public class ItemChestShop extends JavaPlugin {

    public static ItemChestShop plugin;

    @Override
    public void onEnable() {
        plugin = this;

        Data data = new Data(this);
        data.mkdirMainDataFolder();
        data.mkDirChestDataFolder();
        data.mkDirPlayerDataFolder();

        registerListeners();
        registerCommands();
        getLogger().info(getDescription().getFullName()
                + " Version " + getDescription().getVersion() +" has been enabled.");
    }

    @Override
    public void onDisable() {
        new ChestShopData().saveData();
        getLogger().info(getDescription().getFullName()
                + " Version " + getDescription().getVersion() +" has been disabled.");
    }


    private void registerListeners(){
        getServer().getPluginManager().registerEvents(new chestShopSetUp(), this);
        getServer().getPluginManager().registerEvents(new chestShopTransaction(), this);
        getServer().getPluginManager().registerEvents(new chestShopDisable(), this);
        getServer().getPluginManager().registerEvents(new playerJoinEvent(), this);
        getServer().getPluginManager().registerEvents(new buyItemSet(), this);
        getServer().getPluginManager().registerEvents(new saleItemSet(), this);
    }

    private void registerCommands(){
        registerAdminCommands();
    }

    private void registerAdminCommands(){
        AdminCommandHandler AdminCommands = new AdminCommandHandler();
        AdminCommands.register("achestshop", new adminCmd());
        AdminCommands.register("save", new saveCmd());
        getCommand("achestshop").setExecutor((AdminCommands));
        getCommand("achestshop").setTabCompleter(new AdminTabCompleter());

    }

}
