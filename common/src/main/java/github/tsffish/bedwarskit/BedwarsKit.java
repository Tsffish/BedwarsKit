package github.tsffish.bedwarskit;

import org.bukkit.plugin.java.JavaPlugin;

import static github.tsffish.bedwarskit.util.PluginInit.d;
import static github.tsffish.bedwarskit.util.PluginInit.startup;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class BedwarsKit extends JavaPlugin {
    private static BedwarsKit instance;

    public static BedwarsKit getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        startup();
    }

    @Override
    public void onDisable() {
        d();
    }
}