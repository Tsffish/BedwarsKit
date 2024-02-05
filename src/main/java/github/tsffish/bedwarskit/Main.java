package github.tsffish.bedwarskit;

import github.tsffish.bedwarskit.command.CommandInfo;
import github.tsffish.bedwarskit.config.MainConfigLoad;
import github.tsffish.bedwarskit.listener.PlayerDamageHandler;
import github.tsffish.bedwarskit.listener.bedwarsrel.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import static github.tsffish.bedwarskit.util.misc.ErrorLogger.le;
import static github.tsffish.bedwarskit.util.misc.InfoLogger.l;


public class Main extends JavaPlugin implements Listener {
    public static String pluginName = "BedwarsKit";
    public static String pluginVersion = "1.9.5";
    public static String pluginNameConsole = "[BedwarsKit]";
    public static boolean isDebug = false;
    public static ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
    public void onEnable() {
        console.sendMessage(pluginNameConsole  + ChatColor.GREEN + " ================================");
        console.sendMessage(pluginNameConsole  + " ");
        console.sendMessage( pluginNameConsole  + " " + ChatColor.WHITE + pluginName + " " + ChatColor.AQUA + pluginVersion);
        console.sendMessage(pluginNameConsole  + " ");
        console.sendMessage(pluginNameConsole  + " " + ChatColor.WHITE + "Author: " + ChatColor.YELLOW  + "Tsffish");
        console.sendMessage(pluginNameConsole  + " ");
        console.sendMessage(pluginNameConsole  + ChatColor.GREEN + " ================================");
        PluginManager pm = Bukkit.getPluginManager();

        if (pm.getPlugin("BedwarsRel") != null) {
            MainConfigLoad.loadMainConfig(null, true);
            getCommand("bwk").setExecutor(new CommandInfo());
            l("Command registered");

        } else {
            le("BedwarsRel not found, unable to enable related support");
        }

    }
        public void onDisable () {
            l("uninstalling");
        }

}