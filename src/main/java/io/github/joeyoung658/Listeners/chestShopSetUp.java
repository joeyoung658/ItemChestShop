package io.github.joeyoung658.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class chestShopSetUp implements Listener {

    @EventHandler
    public void signChangeEvent(SignChangeEvent event){
        String[] signText = event.getLines();
        String lineOne = signText[0];
        String lineTwo = signText[1];
        String lineThree = signText[2];
        String lineFour = signText[3];
    }
}
