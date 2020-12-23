package io.github.joeyoung658.Listeners;

import io.github.joeyoung658.ChestShop.ChestShop;
import io.github.joeyoung658.ChestShop.ChestShopTransactions;
import io.github.joeyoung658.Data.ChestShopData;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import static io.github.joeyoung658.Listeners.chestShopSetUp.isValidSign;


public class chestShopTransaction implements Listener {
    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent e){
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK){
            Material material = e.getClickedBlock().getType();
           if  (material == Material.OAK_SIGN){
               ChestShopData chestShopData = new ChestShopData();
               Location signLoc = e.getClickedBlock().getLocation();
               if (chestShopData.chestShopExists(signLoc)){
                   ChestShop chestshop = chestShopData.getChestShop(signLoc);
                   ChestShopTransactions chestShopTransactions = new ChestShopTransactions(e.getPlayer(), chestshop);
                   chestShopTransactions.completeTransaction();
               } else {
                   Sign sign = (Sign) e.getClickedBlock().getState();
                   Player p = e.getPlayer();
                   String[] signText = sign.getLines();
                   //Qty For Sale
                   String lineOne = signText[0];
                   //Sale Item
                   String lineTwo = signText[1].toUpperCase();
                   //Amount to buy
                   String lineThree = signText[2];
                   //buy item
                   String lineFour = signText[3].toUpperCase();

                   if (!(isValidSign(signText, e.getClickedBlock(), signLoc))){
                       return;
                   }
                       int qtyForSale;
                       int qtyToBuy;
                       try {
                           qtyForSale = Integer.parseInt(lineOne);
                           qtyToBuy = Integer.parseInt(lineThree);
                       } catch (Exception ex){
                           return;
                       }

                       ItemStack saleItem;
                       ItemStack purchaseItem;
                       try {
                           saleItem = new ItemStack(Material.getMaterial(lineTwo), qtyForSale);
                           purchaseItem = new ItemStack(Material.getMaterial(lineFour), qtyToBuy);
                       } catch (Exception ex){
                           return;
                       }
                       ChestShop chestShop = new ChestShop();
                       chestShop.setChestShopOwner(p);
                       chestShop.setQtyForSale(qtyForSale);
                       chestShop.setSaleItem(saleItem);
                       chestShop.setPurchaseItem(purchaseItem);
                       chestShop.setChestShopLoc(signLoc);

                       chestShopData.setChestShop(signLoc, chestShop);
               }
           }
        }
    }
}
