package io.github.joeyoung658.Data;

import io.github.joeyoung658.ChestShop.ChestShop;
import io.github.joeyoung658.ItemChestShop;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class ChestShopData {
    private static Map<Location, ChestShop> chestShopHashMap = new HashMap<>();


    /**
     *
     * @param location
     * @return
     */
    public ChestShop getChestShop(Location location){
        if (chestShopHashMap.containsKey(location)) {
            return chestShopHashMap.get(location);
        }
        return null;
    }

    /**
     *
     * @return
     */
    public Map<Location, ChestShop> getAllChestShops(){
        return chestShopHashMap;
    }

    /**
     *
     * @param location
     * @param chestShop
     */
    public void setChestShop(Location location, ChestShop chestShop){
        chestShopHashMap.put(location, chestShop);
    }

    /**
     *
     * @param loc
     * @return
     */
    public boolean chestShopLoaded(Location loc){
        return chestShopHashMap.containsKey(loc);
    }

    /**
     * Deletes a chestshop completely
     * @param location
     */
    public void removeChestStop(Location location){
        if (chestShopHashMap.containsKey(location)) {
            chestShopHashMap.remove(location);
        } else {
            //todo delete from database
        }
    }


    /**
     * Saves & removes single chestshop from array
     * @param chestShop
     */
    public void saveChestShop(Location chestShop){
        Data data = new Data(ItemChestShop.plugin);
        if (chestShopHashMap.containsKey(chestShop)) {
            data.createChestShopFile(chestShopHashMap.get(chestShop));
            chestShopHashMap.remove(chestShop);
        }
    }

    /**
     * Saves all chestshops in active array
     */
    public void saveData(){
        Data data = new Data(ItemChestShop.plugin);
        for (Location signLoc : chestShopHashMap.keySet()) {
            data.createChestShopFile(chestShopHashMap.get(signLoc));
        }
        chestShopHashMap.clear();
    }


    /**
     *
     * @param location
     * @return
     */

    //todo debug the below

//    [15:45:32 ERROR]: Could not pass event PlayerInteractEvent to ItemChestShop v1.0
//    java.lang.NullPointerException: null
//    at io.github.joeyoung658.Data.Data.loadChestShopFileData(Data.java:184) ~[?:?]
//    at io.github.joeyoung658.Data.ChestShopData.loadChestShop(ChestShopData.java:98) ~[?:?]
//    at io.github.joeyoung658.Listeners.chestShopTransaction.onPlayerInteractEvent(chestShopTransaction.java:31) ~[?:?]
//    at com.destroystokyo.paper.event.executor.asm.generated.GeneratedEventExecutor37.execute(Unknown Source) ~[?:?]
//    at org.bukkit.plugin.EventExecutor.lambda$create$1(EventExecutor.java:69) ~[patched_1.16.4.jar:git-Paper-322]
//    at co.aikar.timings.TimedEventExecutor.execute(TimedEventExecutor.java:80) ~[patched_1.16.4.jar:git-Paper-322]
//    at org.bukkit.plugin.RegisteredListener.callEvent(RegisteredListener.java:70) ~[patched_1.16.4.jar:git-Paper-322]
//    at org.bukkit.plugin.SimplePluginManager.callEvent(SimplePluginManager.java:607) ~[patched_1.16.4.jar:git-Paper-322]
//    at org.bukkit.craftbukkit.v1_16_R3.event.CraftEventFactory.callPlayerInteractEvent(CraftEventFactory.java:503) ~[patched_1.16.4.jar:git-Paper-322]
//    at net.minecraft.server.v1_16_R3.PlayerInteractManager.a(PlayerInteractManager.java:464) ~[patched_1.16.4.jar:git-Paper-322]
//    at net.minecraft.server.v1_16_R3.PlayerConnection.a(PlayerConnection.java:1534) ~[patched_1.16.4.jar:git-Paper-322]
//    at net.minecraft.server.v1_16_R3.PacketPlayInUseItem.a(PacketPlayInUseItem.java:27) ~[patched_1.16.4.jar:git-Paper-322]
//    at net.minecraft.server.v1_16_R3.PacketPlayInUseItem.a(PacketPlayInUseItem.java:5) ~[patched_1.16.4.jar:git-Paper-322]
//    at net.minecraft.server.v1_16_R3.PlayerConnectionUtils.lambda$ensureMainThread$1(PlayerConnectionUtils.java:23) ~[patched_1.16.4.jar:git-Paper-322]
//    at net.minecraft.server.v1_16_R3.TickTask.run(SourceFile:18) ~[patched_1.16.4.jar:git-Paper-322]
//    at net.minecraft.server.v1_16_R3.IAsyncTaskHandler.executeTask(IAsyncTaskHandler.java:136) ~[patched_1.16.4.jar:git-Paper-322]
//    at net.minecraft.server.v1_16_R3.IAsyncTaskHandlerReentrant.executeTask(SourceFile:23) ~[patched_1.16.4.jar:git-Paper-322]
//    at net.minecraft.server.v1_16_R3.IAsyncTaskHandler.executeNext(IAsyncTaskHandler.java:109) ~[patched_1.16.4.jar:git-Paper-322]
//    at net.minecraft.server.v1_16_R3.MinecraftServer.bb(MinecraftServer.java:1133) ~[patched_1.16.4.jar:git-Paper-322]
//    at net.minecraft.server.v1_16_R3.MinecraftServer.executeNext(MinecraftServer.java:1126) ~[patched_1.16.4.jar:git-Paper-322]
//    at net.minecraft.server.v1_16_R3.IAsyncTaskHandler.awaitTasks(IAsyncTaskHandler.java:119) ~[patched_1.16.4.jar:git-Paper-322]
//    at net.minecraft.server.v1_16_R3.MinecraftServer.sleepForTick(MinecraftServer.java:1087) ~[patched_1.16.4.jar:git-Paper-322]
//    at net.minecraft.server.v1_16_R3.MinecraftServer.w(MinecraftServer.java:1001) ~[patched_1.16.4.jar:git-Paper-322]
//    at net.minecraft.server.v1_16_R3.MinecraftServer.lambda$a$0(MinecraftServer.java:178) ~[patched_1.16.4.jar:git-Paper-322]
//    at java.lang.Thread.run(Thread.java:834) [?:?]

    public boolean loadChestShop(Location location) {
        Data data = new Data(ItemChestShop.plugin);
        Map<String, String> chestShopData = data.loadChestShopFileData(location);
        if (chestShopData == null){
            return false;
        }
        ChestShop chestShop = new ChestShop();
        chestShop.setChestShopOwnerByUUID(chestShopData.get("owner"));
        int qtyForSale = Integer.parseInt(chestShopData.get("qtyForSale"));
        int qtyToBuy = Integer.parseInt(chestShopData.get("qtyToBuy"));
        chestShop.setQtyForSale(qtyForSale);
        chestShop.setQtyToBuy(qtyToBuy);
        ItemStack saleItem;
        ItemStack purchaseItem;
        try {
            saleItem = new ItemStack(Material.getMaterial(chestShopData.get("saleItem")), qtyForSale);
            purchaseItem = new ItemStack(Material.getMaterial(chestShopData.get("purchaseItem")), qtyToBuy);
        } catch (Exception e){
            return false;
        }
        chestShop.setSaleItem(saleItem);
        chestShop.setPurchaseItem(purchaseItem);
        chestShop.setChestShopLoc(location);
        this.setChestShop(location, chestShop);
        return true;
    }
}
