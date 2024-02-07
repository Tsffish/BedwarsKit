package github.tsffish.bedwarskit;

import github.tsffish.bedwarskit.command.CommandInfo;
import github.tsffish.bedwarskit.config.MainConfigLoad;
import github.tsffish.bedwarskit.util.update.UpdateChecker;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Locale;

import static github.tsffish.bedwarskit.config.MainConfigHandler.isLastestVersion;
import static github.tsffish.bedwarskit.util.misc.InfoLogger.l;


public class Main extends JavaPlugin
{
    public static final String pluginName = "BedwarsKit";
    public static final String pluginVersion = "1.9.51";
    public static String pluginNameConsole = "[BedwarsKit]";
    public static boolean isDebug = false;
    private static String v;
    public static ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
    private static JavaPlugin plugin;
    public static String language;
    public void onEnable()
    {

        Locale currentLocale = Locale.getDefault();
        language = currentLocale.getLanguage();


        plugin = this;



        int pluginId = 20914;
        Metrics metrics = new Metrics(this, pluginId);


        metrics.addCustomChart(new Metrics.SimplePie("chart_id", () -> "My value"));

        PluginManager pm = Bukkit.getPluginManager();
        if (pm.getPlugin("BedwarsRel") != null)
        {

            console.sendMessage(pluginNameConsole  + ChatColor.GREEN + " ================================");
            console.sendMessage(pluginNameConsole  + " ");
            console.sendMessage( pluginNameConsole  + " " + ChatColor.WHITE + pluginName + " " + ChatColor.AQUA + pluginVersion);
            console.sendMessage(pluginNameConsole  + " ");
            console.sendMessage(pluginNameConsole  + " " + ChatColor.WHITE + "Author: " + ChatColor.YELLOW  + "Tsffish");
            console.sendMessage(pluginNameConsole  + " ");
            console.sendMessage(pluginNameConsole  + ChatColor.GREEN + " ================================");

            MainConfigLoad.loadMainConfig(null, true);
            getCommand("bwk").setExecutor(new CommandInfo());
            l("Command registered");

        }
        else
        {
            console.sendMessage(pluginNameConsole  + ChatColor.RED + " ================================");
            console.sendMessage(pluginNameConsole  + " ");
            console.sendMessage( pluginNameConsole  + " " + ChatColor.WHITE + pluginName + " " + ChatColor.AQUA + pluginVersion);
            console.sendMessage(pluginNameConsole  + " ");
            console.sendMessage(pluginNameConsole  + " " + ChatColor.WHITE + "Author: " + ChatColor.YELLOW  + "Tsffish");
            console.sendMessage(pluginNameConsole  + " ");
            console.sendMessage(pluginNameConsole  + ChatColor.RED + " ================================");

            console.sendMessage(pluginNameConsole  +  " " + ChatColor.RED + "BedwarsRel not found, unable to enable related support");
        }

    }
        public void onDisable ()
        {
            l("uninstalling");
        }

        private void getServerVerison()
        {
            v = Bukkit.getServer().getClass().getPackage().getName();
            v = v.substring(v.lastIndexOf('.') + 1);
        }

        public static void checkUpdate(int resId)
        {
            new UpdateChecker(plugin, resId).getVersion(version ->
            {
                if (plugin.getDescription().getVersion().equals(version))
                {
                    if (language.equalsIgnoreCase("zh")) {
                        console.sendMessage(pluginNameConsole + "你正在使用最新版本！");
                    } else
                    {
                        console.sendMessage(pluginNameConsole + "You are using the latest version!");
                    }
                    isLastestVersion = true;
                    } else
                    {
                    isLastestVersion = false;
                    if (language.equalsIgnoreCase("zh")) {
                        console.sendMessage(pluginNameConsole + ChatColor.YELLOW + " ================================");
                        console.sendMessage(pluginNameConsole + " ");
                        console.sendMessage(pluginNameConsole + " " + ChatColor.WHITE + pluginName + " 发现新版本！");
                        console.sendMessage(pluginNameConsole + " ");
                        console.sendMessage(pluginNameConsole + " " + ChatColor.WHITE + "在此处下载: https://www.spigotmc.org/resources/bedwarskit.105616/");
                        console.sendMessage(pluginNameConsole + " ");
                        console.sendMessage(pluginNameConsole + ChatColor.YELLOW + " ================================");
                    } else
                    {
                        console.sendMessage(pluginNameConsole + ChatColor.YELLOW + " ================================");
                        console.sendMessage(pluginNameConsole + " ");
                        console.sendMessage(pluginNameConsole + " " + ChatColor.WHITE + pluginName + " Found a new version!");
                        console.sendMessage(pluginNameConsole + " ");
                        console.sendMessage(pluginNameConsole + " " + ChatColor.WHITE + "Download here: https://www.spigotmc.org/resources/bedwarskit.105616/");
                        console.sendMessage(pluginNameConsole + " ");
                        console.sendMessage(pluginNameConsole + ChatColor.YELLOW + " ================================");
                    }
                }
            });
        }

}