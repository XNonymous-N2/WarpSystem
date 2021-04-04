package de.xnonymous.warpsystem.commands;

import de.xnonymous.warpsystem.Main;
import de.xnonymous.warpsystem.inventorys.WarpMenu;
import de.xnonymous.warpsystem.utils.ChatUtils;
import de.xnonymous.warpsystem.warp.Warp;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player)) {
            ChatUtils.sendMessage(commandSender, ChatUtils.getText("not-player"));
            return true;
        }
        if (args.length != 1)
            WarpMenu.INVENTORY.open(((Player) commandSender));
        else {
            Warp warp1 = Main.getInstance().getWarpManager().getWarps().stream().filter(warp -> warp.getName().equalsIgnoreCase(args[0])).findFirst().orElse(null);
            if (warp1 != null) {
                if (!commandSender.hasPermission("warpsystem.no.cooldown"))
                    Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> ((Player) commandSender).teleport(warp1.getLocation()), 20 * 3);
                else
                    ((Player) commandSender).teleport(warp1.getLocation());
                ChatUtils.sendMessage(commandSender, ChatUtils.getText("warp-teleport").replaceAll("%warp%", warp1.getName()));
            } else {
                ChatUtils.sendMessage(commandSender, ChatUtils.getText("warp-not-exist").replaceAll("%warp%", args[0]));
            }
        }
        return false;
    }
}
