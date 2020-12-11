package io.github.joeyoung658.ChestShop;

import org.bukkit.Location;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class ChestShopVaildator {



    public boolean isValidSignShop(String[] lineText){
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

    public boolean isActiveChestShop(){
        //todo implement function
        //Check if it's an active chest shop
        return false;
    }

    public boolean isChestShopFull(Chest chest){
        //todo implement function
        return false;
    }

    public boolean isTargetInvenFull(Player p){
        return !Arrays.asList(p.getInventory().getStorageContents()).contains(null);
    }

    public boolean chestShopExists(Location chestShopLoc){
        //todo implement function
        return false;
    }

}
