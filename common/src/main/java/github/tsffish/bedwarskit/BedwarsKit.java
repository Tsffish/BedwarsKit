package github.tsffish.bedwarskit;

import org.bukkit.plugin.java.JavaPlugin;

import static github.tsffish.bedwarskit.util.misc.MessSender.l;
import static github.tsffish.bedwarskit.util.misc.PluginStartUp.startup;

public class BedwarsKit extends JavaPlugin {
    private static BedwarsKit instance;
    public static BedwarsKit getInstance() {
        return instance;
    }
    public void onEnable() {
        instance = this;
        startup();
    }
        public void onDisable ()
        {
            l("Disabled.");
        }
}