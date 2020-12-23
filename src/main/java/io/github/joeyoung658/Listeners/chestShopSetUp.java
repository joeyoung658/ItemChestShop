package io.github.joeyoung658.Listeners;

import io.github.joeyoung658.ChestShop.ChestShop;
import io.github.joeyoung658.Data.ChestShopData;
import io.github.joeyoung658.Data.Data;
import io.github.joeyoung658.ItemChestShop;
import io.github.joeyoung658.ItemChestShopServerMessages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.inventory.ItemStack;

public class chestShopSetUp implements Listener {

    @EventHandler
    public void signChangeEvent(SignChangeEvent event){
        Player p = event.getPlayer();

        String[] signText = event.getLines();
        //Qty For Sale
        String lineOne = signText[0];

        //Sale Item
        String lineTwo = signText[1].toUpperCase();

        //Amount to buy
        String lineThree = signText[2];

        //buy item
        String lineFour = signText[3].toUpperCase();

        Location signLoc = event.getBlock().getLocation();

        if (!(isValidSign(signText, event.getBlock(), signLoc))){
            return;
        }

        ChestShopData chestShopData = new ChestShopData();
        if (chestShopData.chestShopExists(signLoc)) {
            //Should never get here but just case
            p.sendMessage(new ItemChestShopServerMessages().getServerPrefix() + "A chest shop already exists in this location!");
            return;
        } else {
            int qtyForSale;
            int qtyToBuy;
            try {
                qtyForSale = Integer.parseInt(lineOne);
                qtyToBuy = Integer.parseInt(lineThree);
            } catch (Exception e){
                return;
            }

            ItemStack saleItem;
            ItemStack purchaseItem;
            try {
                saleItem = new ItemStack(Material.getMaterial(lineTwo), qtyForSale);
                purchaseItem = new ItemStack(Material.getMaterial(lineFour), qtyToBuy);
            } catch (Exception e){
                return;
            }
            event.setLine(0, ChatColor.GREEN + lineOne);
            event.setLine(1, ChatColor.GREEN + lineTwo);
            event.setLine(2, ChatColor.RED + lineThree);
            event.setLine(3, ChatColor.RED + lineFour);


            //todo check playtime of player, needs to be more than 6 hours to use chestshops

           // ChestShop chestShop = new ChestShop(p, qtyForSale, qtyToBuy, saleItem, purchaseItem, signLoc);
            ChestShop chestShop = new ChestShop();
            chestShop.setChestShopOwner(p);
            chestShop.setQtyForSale(qtyForSale);
            chestShop.setSaleItem(saleItem);
            chestShop.setPurchaseItem(purchaseItem);
            chestShop.setChestShopLoc(signLoc);

            chestShopData.setChestShop(signLoc, chestShop);

            p.sendMessage(new ItemChestShopServerMessages().getServerPrefix() + " Your new chest shop has been successfully created!");
            giveLandClaim(p.getLocation(), p);
        }
    }

    public static boolean isValidSign(String[] lineText, Block sign, Location signLoc){

        if (!(sign.getType() == Material.OAK_SIGN)){
            return false;
        }


        if (lineText[0].isEmpty()
                || lineText[2].isEmpty()
                || lineText[3].isEmpty() || lineText[1].isEmpty()){
            return false;
        }
        //Qty For Sale
        String lineOne = lineText[0];
        //Amount to buy
        String lineThree = lineText[2];
        //Sale Item
        String lineTwo = lineText[1];
        //buy item
        String lineFour = lineText[3];

        int qtyForSale;
        int qtyToBuy;
        ItemStack saleItem;
        ItemStack purchaseItem;
        try {
            qtyForSale = Integer.parseInt(lineOne);
            qtyToBuy = Integer.parseInt(lineThree);

            if (qtyForSale > 64 || qtyToBuy > 64){
                return false;
                //todo put msg
            }

            saleItem = new ItemStack(Material.getMaterial(lineTwo), qtyToBuy);
            purchaseItem = new ItemStack(Material.getMaterial(lineFour), qtyForSale);
        } catch (Exception e){
            return false;
        }
        if (!(detectChest(signLoc))){
            return false;
        }
        return true;
    }

    private static boolean detectChest(Location loc){
       ChestShop chestShop = new ChestShop();
       chestShop.setChestShopLoc(loc);
       if(chestShop.getChest() == null){
           return false;
       }
       return true;
    }


//    @SuppressWarnings( "deprecation" )
//    private static boolean detectChest(Location loc){
//        Sign sign = (Sign) loc.getBlock().getState().getData();
//        Block attached = loc.getBlock().getRelative(sign.getAttachedFace());
//        return (attached.getType() == Material.CHEST);
//    }


    /**
     * Gives a player extra land claim for their shop!
     * @param loc
     * @param player
     */
    private void giveLandClaim(Location loc, Player player){
        //https://www.spigotmc.org/threads/good-methods-to-check-if-a-player-is-in-an-area.291140/
        double aX = 17845.88928658687;
        double aY  = 134.25842355926702;
        double aZ = 6393.423370564463;
        double bX =17564.491309342233;
        double bY =124.13880882037078;
        double bZ =6671.65865958567;
        Data data = new Data(ItemChestShop.plugin);
        if((loc.getBlockX() > aX) && (loc.getBlockX() < bX)){
                if((loc.getBlockZ() > aZ) && (loc.getBlockZ() < bZ)){
                    if (data.getDataFromPlayerFile(player, "landClaim") == null){
                        data.updatePlayerFileData(player, "landClaim", "true");
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "acb" + player.getName() + "256");
                        player.sendMessage(new ItemChestShopServerMessages().getServerPrefix() + "You have been given 256 more land claim blocks for your chest shop!");
                    }
                }
        }
    }
}
