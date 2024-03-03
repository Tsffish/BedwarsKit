package github.tsffish.bedwarskit.util.misc.bstats;

import github.tsffish.bedwarskit.BedwarsKit;
import org.bstats.bukkit.Metrics;

import static github.tsffish.bedwarskit.BedwarsKit.*;
import static github.tsffish.bedwarskit.util.misc.MessSender.le;

public class StartMetrics {
    private static final BedwarsKit plugin = BedwarsKit.getInstance();
    public static void startMetrics(){
        try {
            Metrics metrics = new Metrics(plugin, 20914);

            metrics.addCustomChart(new Metrics.SimplePie("server_version", () -> plugin.getServer().getVersion()));
            metrics.addCustomChart(new Metrics.SimplePie("server_language", BedwarsKit::language));
            metrics.addCustomChart(new Metrics.SimplePie("server_auth_mode", () -> String.valueOf(plugin.getServer().getOnlineMode())));
            metrics.addCustomChart(new Metrics.SimplePie("bungeecord_enabled", () -> String.valueOf(isBungeeMode())));
            metrics.addCustomChart(new Metrics.SimplePie("plugin_version", BedwarsKit::pluginVersion));
            metrics.addCustomChart(new Metrics.SimplePie("os_name", () -> System.getProperty("os.name")));

        } catch (RuntimeException e) {
            le("BedwarsKit", "bstats error:" + e);
        }
    }
}
