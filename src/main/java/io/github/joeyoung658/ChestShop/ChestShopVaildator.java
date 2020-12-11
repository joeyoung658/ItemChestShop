package io.github.joeyoung658.ChestShop;

import org.bukkit.Location;

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
        //Check if it's an active chest shop
        return false;
    }

    public void isChestShopFull(){

    }

    public void isTargetInvenFull(){

    }

    public boolean chestShopExists(Location chestShopLoc){
        return false;
    }

}
