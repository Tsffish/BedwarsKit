package github.tsffish.bedwarskit.config;

import org.bukkit.Bukkit;

import static github.tsffish.bedwarskit.Main.pluginNameConsole;

public class ErrorConfigHandler {

    public static void er(String name, String path ,String exception){
        Bukkit.getLogger().warning(pluginNameConsole + " <" + name + "> An error occurred while attempting to load " + path + " : " + exception);
    }
    public static void er(String name, String path ,Exception exception){
        Bukkit.getLogger().warning(pluginNameConsole + " <" + name + "> An error occurred while attempting to load " + path + " : " + exception);
    }
}
