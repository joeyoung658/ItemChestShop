package io.github.joeyoung658.ChestShop;

import org.bukkit.Location;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class ChestShopVaildator {


    Player player;
    Chest chest;
    public ChestShopVaildator(){

    }

    public boolean isValidSign(String[] lineText){
        if (lineText[0].isEmpty()
                || lineText[2].isEmpty()
                || lineText[3].isEmpty()){
            return false;
        }
        if (!(lineText[1].isEmpty())){
            return false;
        }
        return true;
    }

    public boolean isChestShopFull(Chest chest){
        return !Arrays.asList(chest.getInventory().getStorageContents()).contains(null);
    }

    public boolean isTargetInvenFull(Player p){
        return !Arrays.asList(p.getInventory().getStorageContents()).contains(null);
    }

    public boolean chestShopExists(Location chestShopLoc){
        //todo implement function
        return false;
    }

}
