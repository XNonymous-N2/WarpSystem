package de.xnonymous.warpsystem.utils;

import de.xnonymous.warpsystem.Main;
import de.xnonymous.warpsystem.config.impl.DefaultConfig;
import org.bukkit.command.CommandSender;

public class ChatUtils {

    public static void sendMessage(CommandSender commandSender, String message) {
        DefaultConfig defaultConfig = (DefaultConfig) Main.getInstance().getConfigRegistry().getByClass(DefaultConfig.class);
        String prefix = defaultConfig.getPrefix();
        commandSender.sendMessage(prefix + message);
    }

    public static String getText(String text) {
        DefaultConfig defaultConfig = (DefaultConfig) Main.getInstance().getConfigRegistry().getByClass(DefaultConfig.class);
        return defaultConfig.getCfg().getString(text).replaceAll("&", "§");
    }

}
