package io.github.joeyoung658.events;

import io.github.joeyoung658.ChestShop.ChestShop;
import org.bukkit.Location;
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
    private Location playerLocation;
    private ChestShop chestShop;

    public buyItemSetEvent(ItemStack buyItem, Location playerLocation, ChestShop chestShop){
        this.buyItem = buyItem;
        this.playerLocation = playerLocation;
        this.chestShop = chestShop;
    }

    public ItemStack getBuyItem() {
        return buyItem;
    }

    public Location getPlayerLocation() {
        return playerLocation;
    }

    public ChestShop getChestShop() {
        return chestShop;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
