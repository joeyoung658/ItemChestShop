package io.github.joeyoung658.Listeners;

import io.github.joeyoung658.ChestShop.ChestShop;
import io.github.joeyoung658.ChestShop.ChestShopTransactions;
import io.github.joeyoung658.Data.ChestShopData;
import io.github.joeyoung658.ItemChestShopServerMessages;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;


public class chestShopTransaction implements Listener {
    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent e){
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK){
            Material material = e.getClickedBlock().getType();
           if  (material == Material.OAK_SIGN){
               ChestShopData chestShopData = new ChestShopData();
               Location signLoc = e.getClickedBlock().getLocation();
               if (chestShopData.chestShopLoaded(signLoc)){
                   ChestShop chestshop = chestShopData.getChestShop(signLoc);
                   ChestShopTransactions chestShopTransactions = new ChestShopTransactions(e.getPlayer(), chestshop);
                   chestShopTransactions.completeTransaction();
                   return;
               } else {
                   //todo run async
                   e.getPlayer().sendMessage(new ItemChestShopServerMessages().getServerPrefix() + "ChestShop loading...");
                   if (chestShopData.loadChestShop(e.getClickedBlock().getLocation())){
                       e.getPlayer().sendMessage(new ItemChestShopServerMessages().getServerPrefix() + "The Chest shop has successfully loaded, please try again!");
                   } else {
                       e.getPlayer().sendMessage(new ItemChestShopServerMessages().getServerPrefix() + "A Chest shop does not exist at this location!");
                   }
                   return;
//                   Sign sign = (Sign) e.getClickedBlock().getState();
//                   Player p = e.getPlayer();
//                   String[] signText = sign.getLines();
//                   signText[0] = signText[0].substring(2);
//                   signText[1] = signText[1].substring(2);
//                   signText[2] = signText[2].substring(2);
//                   signText[3] = signText[3].substring(2);
//
//
//                   //Qty For Sale
//                   String lineOne = signText[0];
//                   //Sale Item
//                   String lineTwo = signText[1];
//                   //Amount to buy
//                   String lineThree = signText[2];
//                   //buy item
//                   String lineFour = signText[3];
//
//                   if (!(isValidSign(signText, e.getClickedBlock(), signLoc))){
//                       return;
//                   }
//                       int qtyForSale;
//                       int qtyToBuy;
//                       try {
//                           qtyForSale = Integer.parseInt(lineOne);
//                           qtyToBuy = Integer.parseInt(lineThree);
//                       } catch (Exception ex){
//                           return;
//                       }
//
//                       ItemStack saleItem;
//                       ItemStack purchaseItem;
//                       try {
//                           saleItem = new ItemStack(Material.getMaterial(lineTwo), qtyForSale);
//                           purchaseItem = new ItemStack(Material.getMaterial(lineFour), qtyToBuy);
//                       } catch (Exception ex){
//                           return;
//                       }
//                       ChestShop chestShop = new ChestShop();
////                       chestShop.setChestShopOwner(p);
//                        chestShop.setQtyToBuy(qtyToBuy);
//                       chestShop.setQtyForSale(qtyForSale);
//                       chestShop.setSaleItem(saleItem);
//                       chestShop.setPurchaseItem(purchaseItem);
//                       chestShop.setChestShopLoc(signLoc);
//
//                       chestShopData.setChestShop(signLoc, chestShop);
               }
           }
        }
    }
}
