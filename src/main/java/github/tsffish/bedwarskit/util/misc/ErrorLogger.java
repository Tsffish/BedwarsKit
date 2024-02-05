package github.tsffish.bedwarskit.util.misc;

import org.bukkit.Bukkit;

import java.util.logging.Logger;

import static github.tsffish.bedwarskit.Main.pluginNameConsole;

public class ErrorLogger {
    public static void le(String string){
        Logger logger = Bukkit.getLogger();
        logger.warning(pluginNameConsole + " " + string);

    }
}
