package de.xnonymous.warpsystem;

import de.xnonymous.warpsystem.commands.DelWarpCommand;
import de.xnonymous.warpsystem.commands.SetWarpCommand;
import de.xnonymous.warpsystem.commands.WarpCommand;
import de.xnonymous.warpsystem.config.ConfigRegistry;
import de.xnonymous.warpsystem.warp.WarpManager;
import fr.minuskube.inv.InventoryManager;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
@Setter
public class Main extends JavaPlugin {

    @Getter
    private static Main instance;

    private InventoryManager inventoryManager;
    private ConfigRegistry configRegistry;
    private WarpManager warpManager;

    @Override
    public void onEnable() {
        instance = this;

        configRegistry = new ConfigRegistry();
        warpManager = new WarpManager();
        inventoryManager = new InventoryManager(this);
        inventoryManager.init();

        registerCommands();
    }

    @Override
    public void onDisable() {

    }

    private void registerCommands() {
        getCommand("setwarp").setExecutor(new SetWarpCommand());
        getCommand("warp").setExecutor(new WarpCommand());
        getCommand("delwarp").setExecutor(new DelWarpCommand());
    }
}
