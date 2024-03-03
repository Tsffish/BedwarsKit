package github.tsffish.bedwarskit.listener;

import github.tsffish.bedwarskit.BedwarsKit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;

import static github.tsffish.bedwarskit.BedwarsKit.isDebug;
import static github.tsffish.bedwarskit.util.misc.MessSender.l;

public class PluginDisable implements Listener {
    private final BedwarsKit plugin = BedwarsKit.getInstance();
    public static boolean pluginIsDisabling = false;
    @EventHandler
    public void on(final PluginDisableEvent event){
        if (event.getPlugin() == plugin) {
            if (isDebug()){l(" plugin is disabled");}
            pluginIsDisabling = true;
        }
    }
}
