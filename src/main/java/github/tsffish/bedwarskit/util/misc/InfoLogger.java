package github.tsffish.bedwarskit.util.misc;

import org.bukkit.Bukkit;

import java.util.logging.Logger;

import static github.tsffish.bedwarskit.Main.pluginNameConsole;

public class InfoLogger {
    public static void l(String string){
        Logger logger = Bukkit.getLogger();
        logger.info(pluginNameConsole + " " + string);

    }
}
