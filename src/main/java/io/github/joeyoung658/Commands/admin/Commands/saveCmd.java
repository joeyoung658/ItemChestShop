package io.github.joeyoung658.Commands.admin.Commands;

import io.github.joeyoung658.Commands.admin.AdminCommandInterface;
import io.github.joeyoung658.ItemChestShop;
import io.github.joeyoung658.data.ChestShopData;
import io.github.joeyoung658.utli.ItemChestShopServerMessages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * @author Joseph Young on 23/12/2020
 * @github https://github.com/joeyoung658
 */
public class saveCmd implements AdminCommandInterface {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        if (args.length > 1) return false;
        saveAllChestShop(result -> {
                sender.sendMessage(new ItemChestShopServerMessages().getServerPrefix() + "All chestshops have been saved to file!");
        });
        return false;
    }



    private interface saveAllChestShop {
        void onQueryDone(Boolean result);
    }

    /**
     *
     * @param callback
     */
    private static void saveAllChestShop(
                                      final saveAllChestShop callback) {
        Bukkit.getScheduler().runTaskAsynchronously(ItemChestShop.plugin, new Runnable() {
            @Override
            public void run() {
                new ChestShopData().saveData();
                Bukkit.getScheduler().runTask(ItemChestShop.plugin, new Runnable() {
                    @Override
                    public void run() {
                        callback.onQueryDone(true);
                    }
                });
            }
        });
    }


}


