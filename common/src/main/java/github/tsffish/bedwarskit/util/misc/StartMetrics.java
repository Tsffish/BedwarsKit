package github.tsffish.bedwarskit.util.misc;

import github.tsffish.bedwarskit.BedwarsKit;
import github.tsffish.bedwarskit.util.PluginState;
import org.bstats.bukkit.Metrics;

import static github.tsffish.bedwarskit.util.PluginState.isBungeeMode;
import static github.tsffish.bedwarskit.util.misc.MessSender.le;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class StartMetrics {
    private static final BedwarsKit plugin = BedwarsKit.getInstance();
    private static final String className = StartMetrics.class.getSimpleName();

    public static void startMetrics() {
        try {
            Metrics metrics = new Metrics(plugin, 20914);

            metrics.addCustomChart(new Metrics.SimplePie("server_version", () -> plugin.getServer().getVersion()));
            metrics.addCustomChart(new Metrics.SimplePie("server_language", PluginState::formatLanguage));
            metrics.addCustomChart(new Metrics.SimplePie("server_auth_mode", () -> String.valueOf(plugin.getServer().getOnlineMode())));
            metrics.addCustomChart(new Metrics.SimplePie("bungeecord_enabled", () -> String.valueOf(isBungeeMode())));
            metrics.addCustomChart(new Metrics.SimplePie("plugin_version", PluginState::pluginVersion));
            metrics.addCustomChart(new Metrics.SimplePie("os_name", () -> System.getProperty("os.name")));

        } catch (Exception e) {
            le(className, e);
        }
    }
}
