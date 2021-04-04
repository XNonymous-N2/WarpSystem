package de.xnonymous.warpsystem.config.impl;

import de.xnonymous.warpsystem.config.Config;
import lombok.Getter;
import lombok.Setter;

@Getter
public class DefaultConfig extends Config {
    public DefaultConfig() {
        super("config");
    }

    private String prefix;

    @Override
    public void onStart() {
        getCfg().addDefault("prefix", "&7 |");
        getCfg().addDefault("not-player", "Sorry, you must be a player.");
        getCfg().addDefault("warp-created", "%warp% has been created.");
        getCfg().addDefault("warp-teleport", "You has been teleported to %warp%.");
        getCfg().addDefault("warp-not-exist", "%warp% does not exist.");
        getCfg().addDefault("warp-deleted", "%warp% has been deleted.");
        getCfg().addDefault("syntax-error", "Error. Wrong Syntax! Inspected more arguments.");
        getCfg().options().copyDefaults(true);
        save();

        prefix = getCfg().getString("prefix");
        prefix = prefix.replaceAll("&", "§") + " ";
    }
}
