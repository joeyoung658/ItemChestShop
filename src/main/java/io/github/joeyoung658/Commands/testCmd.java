package io.github.joeyoung658.Commands;

import io.github.joeyoung658.gui.setupGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author Joseph Young on 10/01/2021
 * @github https://github.com/joeyoung658
 */
public class testCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        if (command.getName().equalsIgnoreCase("testchest")) {
            Player player = (Player) sender;
            setupGUI setupGUI = new setupGUI(player);
            setupGUI.setUpGUI("Sale Item", "Sell");
            return false;
        }

        return false;
    }
}
