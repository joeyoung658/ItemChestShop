package io.github.joeyoung658.events;

import io.github.joeyoung658.ChestShop.ChestShop;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

/**
 * @author Joseph Young on 11/01/2021
 * @github https://github.com/joeyoung658
 */
public class saleItemSetEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    private ItemStack sellItem;
    private Player player;


    public saleItemSetEvent(ItemStack sellItem, Player player) {
        this.sellItem = sellItem;
        this.player = player;

    }

    public ItemStack getSellItem() {
        return sellItem;
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
