package de.xnonymous.warpsystem.commands;

import de.xnonymous.warpsystem.Main;
import de.xnonymous.warpsystem.config.impl.WarpsConfig;
import de.xnonymous.warpsystem.utils.ChatUtils;
import de.xnonymous.warpsystem.warp.Warp;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DelWarpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 1) {
            WarpsConfig warpConfig = (WarpsConfig) Main.getInstance().getConfigRegistry().getByClass(WarpsConfig.class);
            warpConfig.getCfg().set(strings[0], ((Player) commandSender).getLocation());
            warpConfig.save();
            Main.getInstance().getWarpManager().getWarps().removeIf(warp -> warp.getName().equalsIgnoreCase(strings[0]));
            ChatUtils.sendMessage(commandSender, ChatUtils.getText("warp-deleted").replaceAll("%warp%", strings[0]));
            return true;
        }
        ChatUtils.sendMessage(commandSender, ChatUtils.getText("syntax-error"));
        return false;
    }
}
