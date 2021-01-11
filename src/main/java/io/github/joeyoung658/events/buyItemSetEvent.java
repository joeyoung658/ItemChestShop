package io.github.joeyoung658.events;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

/**
 * @author Joseph Young on 11/01/2021
 * @github https://github.com/joeyoung658
 */
public class buyItemSetEvent extends Event {
    private static final HandlerList handlers = new HandlerList();


    private ItemStack buyItem;
    private Player player;


    public buyItemSetEvent(ItemStack buyItem, Player player){
        this.buyItem = buyItem;
        this.player = player;
    }

    public ItemStack getBuyItem() {
        return buyItem;
    }

    public Player getPlayer() {
        return player;
    }


    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
