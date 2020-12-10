package io.github.joeyoung658;

import org.bukkit.entity.Player;

public class ChestShop {

    Player player;
    public ChestShop(Player player){
        this.player = player;
    }

    public boolean isValidSignShop(String[] lineText){

        if (lineText[0].isEmpty()
                || lineText[2].isEmpty()
                || lineText[3].isEmpty()){
            return false;
        }

        if (!(lineText[1].isEmpty())){
            return false;
        }

        lineText.

       return false;
    }


}
