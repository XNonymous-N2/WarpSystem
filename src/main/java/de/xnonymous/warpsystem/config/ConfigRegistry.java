package de.xnonymous.warpsystem.config;

import de.xnonymous.warpsystem.config.impl.DefaultConfig;
import de.xnonymous.warpsystem.config.impl.WarpsConfig;
import de.xnonymous.warpsystem.structure.NameableRegistry;

public class ConfigRegistry extends NameableRegistry<Config> {

    public ConfigRegistry() {
        register(new WarpsConfig());
        register(new DefaultConfig());



        getObjects().forEach(Config::onStart);
    }

    public void reload() {
        getObjects().forEach(Config::reload);
    }

}
