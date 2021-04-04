package de.xnonymous.warpsystem.commands;

import de.xnonymous.warpsystem.Main;
import de.xnonymous.warpsystem.config.impl.DefaultConfig;
import de.xnonymous.warpsystem.config.impl.WarpsConfig;
import de.xnonymous.warpsystem.utils.ChatUtils;
import de.xnonymous.warpsystem.warp.Warp;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetWarpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player)) {
            ChatUtils.sendMessage(commandSender, ChatUtils.getText("not-player"));
            return true;
        }
        if (args.length == 1) {
            WarpsConfig warpConfig = (WarpsConfig) Main.getInstance().getConfigRegistry().getByClass(WarpsConfig.class);
            warpConfig.getCfg().set(args[0], ((Player) commandSender).getLocation());
            warpConfig.save();
            Main.getInstance().getWarpManager().getWarps().add(new Warp(args[0], ((Player) commandSender).getLocation()));
            ChatUtils.sendMessage(commandSender, ChatUtils.getText("warp-created").replaceAll("%warp%", args[0]));
            return true;
        }
        ChatUtils.sendMessage(commandSender, ChatUtils.getText("syntax-error"));
        return false;
    }
}
