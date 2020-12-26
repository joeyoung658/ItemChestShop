package io.github.joeyoung658.Data;

import io.github.joeyoung658.ChestShop.ChestShop;
import io.github.joeyoung658.ItemChestShop;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;

/**
 * @author Joseph Young on 23/12/2020
 * @github https://github.com/joeyoung658
 */
public class Data {


    ItemChestShop plugin;
    public Data(ItemChestShop plugin){
        this.plugin = plugin;
    }


    /**
     * Creates main data folder
     */
    public void mkdirMainDataFolder(){
        File dir = this.plugin.getDataFolder();
        if (!dir.exists()){
            if (!dir.mkdir()){
                this.plugin.getLogger().info(ChatColor.RED + this.plugin.getDescription().getFullName() + " could not create data folder, check permissions and try again.");
                this.plugin.getServer().getPluginManager().disablePlugin(this.plugin);
            }
        }
    }

    /**
     * Creates Chest Data folder
     */
    public void mkDirChestDataFolder(){
        File folder = new File(this.plugin.getDataFolder() + File.separator + "ChestData");
        if (!folder.exists()) {
            this.plugin.getLogger().log(Level.INFO, "Creating ChestData Folder!");
            try {
                folder.mkdir();
            } catch (SecurityException e) {
                this.plugin.getLogger().log(Level.SEVERE, "Could not create new ChestData folder");
                e.printStackTrace();
                this.plugin.getServer().getPluginManager().disablePlugin(this.plugin);
            }
        }
    }

    /**
     * Creates player data folder
     */
    public void mkDirPlayerDataFolder(){
        File folder = new File(this.plugin.getDataFolder() + File.separator + "PlayerData");
        if (!folder.exists()) {
            this.plugin.getLogger().log(Level.INFO, "Creating PlayerData Folder!");
            try {
                folder.mkdir();
            } catch (SecurityException e) {
                this.plugin.getLogger().log(Level.SEVERE, "Could not create new PlayerData folder");
                e.printStackTrace();
                this.plugin.getServer().getPluginManager().disablePlugin(this.plugin);
            }
        }
    }


    /**
     *
     * @param player
     */
    public void createPlayerFile(Player player){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        File PlayerFile = new File(this.plugin.getDataFolder() + File.separator + "PlayerData" + File.separator + player.getUniqueId() +".yml");
        FileConfiguration PlayerData;
        if (!(PlayerFile.exists())) {
            this.plugin.getLogger().log(Level.INFO, "Registering new PlayerData file for " + player.getName());
            try {
                PlayerFile.createNewFile();
                PlayerData = YamlConfiguration.loadConfiguration(PlayerFile);
                PlayerData.set("FirstJoinDate", dateFormat.format(date));
                try
                {
                    PlayerData.save(PlayerFile);
                } catch (IOException e) {
                    this.plugin.getLogger().log(Level.SEVERE,"Could not save data file for " + player.getName());
                    e.printStackTrace();
                }
            } catch (IOException e) {
                this.plugin.getLogger().log(Level.SEVERE, "Could not create new PlayerData file for !" + player.getName());
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param player
     * @param data
     * @return
     */
    public String getDataFromPlayerFile(Player player, String data) {
        File PlayerFile = new File(this.plugin.getDataFolder() + File.separator + "PlayerData" + File.separator + player.getUniqueId() + ".yml");
        if (!PlayerFile.exists()) {
            return null;
        } else {
            FileConfiguration PlayerData = YamlConfiguration.loadConfiguration(PlayerFile);
            return (PlayerData.getString(data));
        }
    }


    public Boolean updatePlayerFileData(Player player, String loc, String data) {
        File PlayerFile = new File(this.plugin.getDataFolder() + File.separator + "PlayerData" + File.separator + player.getUniqueId() + ".yml");
        if (!PlayerFile.exists()) {
            return false;
        } else {
            FileConfiguration PlayerData = YamlConfiguration.loadConfiguration(PlayerFile);
            PlayerData.set(loc, data);
            try
            {
                PlayerData.save(PlayerFile);
            } catch (IOException e) {
                this.plugin.getLogger().log(Level.SEVERE,"Could not update data for " + player.getName());
                e.printStackTrace();
            }
        }
        return true;
    }



    public void createChestShopFile(ChestShop chestshop){
        File chestShopFile = new File(this.plugin.getDataFolder() + File.separator + "ChestData" + File.separator + chestshop.getChestShopLoc().toString() +".yml");
        FileConfiguration chestShopFileData;
        if (!(chestShopFile.exists())) {
            this.plugin.getLogger().log(Level.INFO, "Registering new ChestData file for ChestShop @ " + chestshop.getChestShopLoc().toString());
            try {
                chestShopFile.createNewFile();
                chestShopFileData = YamlConfiguration.loadConfiguration(chestShopFile);
//                chestShopFileData.set(chestshop.getChestShopLoc().toString(), chestshop);
                chestShopFileData.set("owner", chestshop.getChestShopOwnerUUID().toString());
                chestShopFileData.set("qtyForSale", chestshop.getQtyForSale());
                chestShopFileData.set("qtyToBuy", chestshop.getQtyToBuy());
                chestShopFileData.set("saleItem", chestshop.getSaleItem().toString());
                chestShopFileData.set("purchaseItem", chestshop.getPurchaseItem().toString());
                chestShopFileData.set("chestShopLoc", chestshop.getChestShopLoc().toString());
                try
                {
                    chestShopFileData.save(chestShopFile);
                } catch (IOException e) {
                    this.plugin.getLogger().log(Level.SEVERE,"Could not save data file for chest shop @ " + chestshop.getChestShopLoc().toString());
                    e.printStackTrace();
                }
            } catch (IOException e) {
                this.plugin.getLogger().log(Level.SEVERE, "Could not create new PlayerData file for !" + chestshop.getChestShopLoc().toString());
                e.printStackTrace();
            }
        }
    }



    public Map<String, String> loadChestShopFileData(Location loc){
            Map<String, String> data = null;
            File PlayerFile = new File(this.plugin.getDataFolder() + File.separator + "ChestData" + File.separator + loc.toString() + ".yml");
            if (!PlayerFile.exists()) {
                return null;
            } else {
                FileConfiguration chestShopData = YamlConfiguration.loadConfiguration(PlayerFile);
                data.put("owner", (String) chestShopData.get("owner"));
                data.put("qtyForSale", (String) chestShopData.get("qtyForSale"));
                data.put("qtyToBuy", (String) chestShopData.get("qtyToBuy"));
                data.put("saleItem", (String) chestShopData.get("saleItem"));
                data.put("purchaseItem", (String) chestShopData.get("purchaseItem"));
                data.put("chestShopLoc", (String) chestShopData.get("chestShopLoc"));
            }
            return data;
    }
}
