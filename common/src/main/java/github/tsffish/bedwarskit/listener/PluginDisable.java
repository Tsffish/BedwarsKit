package github.tsffish.bedwarskit.listener;

import github.tsffish.bedwarskit.BedwarsKit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;

import static github.tsffish.bedwarskit.util.misc.MessSender.l;
import static github.tsffish.bedwarskit.util.misc.PluginState.isDebug;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class PluginDisable implements Listener {
    public static boolean pluginIsDisabling = false;
    private final BedwarsKit plugin = BedwarsKit.getInstance();

    @EventHandler
    public void on(final PluginDisableEvent event) {
        if (event.getPlugin() == plugin) {
            if (isDebug()) {
                l("Plugin is Disabled");
            }
            pluginIsDisabling = true;
        }
    }
}
