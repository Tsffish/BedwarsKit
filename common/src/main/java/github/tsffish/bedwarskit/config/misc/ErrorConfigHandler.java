package github.tsffish.bedwarskit.config.misc;

import org.bukkit.Bukkit;

import java.util.logging.Logger;

import static github.tsffish.bedwarskit.config.lang.LangConfigHandler.meanConfigLoadError;
import static github.tsffish.bedwarskit.util.PluginState.pluginNameConsole;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class ErrorConfigHandler {
    private static final Logger l = Bukkit.getLogger();

    public static void er(String name, String path, String exception) {
        l.warning(pluginNameConsole() + " <" + name + "> " + meanConfigLoadError + " " + path + " : " + exception);
    }

    public static void er(String name, String path, Exception exception) {
        l.warning(pluginNameConsole() + " <" + name + "> " + meanConfigLoadError + " " + path + " : " + exception);
    }
}
