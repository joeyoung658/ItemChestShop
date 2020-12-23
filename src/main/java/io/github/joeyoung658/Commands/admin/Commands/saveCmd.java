package io.github.joeyoung658.Commands.admin.Commands;

import io.github.joeyoung658.Commands.admin.AdminCommandInterface;
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

        //todo make the chest array unload to file
        return false;
    }
}
