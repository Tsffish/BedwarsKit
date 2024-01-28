package github.tsffish.bedwarskit.config;

import org.bukkit.Bukkit;

public class ErrorConfigHandler {


    public static void er(String path ,String exception){
        Bukkit.getLogger().warning("An error occurred while attempting to load " + path + " : " + exception);
    }
    public static void er(String path ,Exception exception){
        Bukkit.getLogger().warning("An error occurred while attempting to load " + path + " : " + exception);
    }
}
