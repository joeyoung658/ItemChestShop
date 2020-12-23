package io.github.joeyoung658.Commands.admin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public interface AdminCommandInterface {
    boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args);
}
