package github.tsffish.bedwarskit;

import github.tsffish.bedwarskit.command.CommandInfo;
import github.tsffish.bedwarskit.config.main.MainConfigLoad;
import github.tsffish.bedwarskit.util.update.UpdateChecker;
import io.github.bedwarsrel.BedwarsRel;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import static github.tsffish.bedwarskit.config.lang.LangConfigHandler.update_tip;
import static github.tsffish.bedwarskit.util.misc.MessSender.l;


public class Main extends JavaPlugin {
    private static final String pluginName = "BedwarsKit";
    public static String pluginName(){
        return pluginName;
    }
    private static final String pluginVersion = "1.9.52";
    public static String pluginVersion(){
        return pluginVersion;
    }
    private static final String pluginNameConsole = "[BedwarsKit]";
    public static String pluginNameConsole(){
        return pluginNameConsole;
    }
    private static final String author = "Tsffish";
    public static String author(){
        return author;
    }
    private static boolean isDebug = false;
    public static boolean isDebug(){
        return isDebug;
    }
    public static void changeIsDebug(){
        isDebug = !isDebug;
    }
    private static boolean isLastestVersion;
    public static boolean isLastestVersion(){
        return isLastestVersion;
    }
    private static PluginManager pluginManager = Bukkit.getPluginManager();
    private static final int spigotId = 105616;
    public static int spigotId(){
        return spigotId;
    }
    private static final int bstatId = 20914;
    static int bstatId(){
        return bstatId;
    }
    private static final String msgline = "================================";
    public void onEnable()
    {
        if (pluginManager.getPlugin("BedwarsRel") != null)
        {
            sendPluginStartUpInfo(ChatColor.GREEN);

            MainConfigLoad.loadMainConfig(null, true);

            getCommand("bwk").setExecutor(new CommandInfo());
            getCommand("bwk reload").setExecutor(new CommandInfo());

            l("Command registered");

            Metrics metrics = new Metrics(this, bstatId);
            metrics.addCustomChart(new Metrics.SimplePie("chart_id", () -> "My value"));
        }
        else
            {
            sendPluginStartUpInfo(red);
            }
    }
     void handlerHub(){
       getCommand("hub").setExecutor(new CommandInfo());
    }
        public void onDisable ()
        {
            l("Disabled.");
        }
        private final ChatColor red = ChatColor.RED;
        private void sendPluginStartUpInfo(ChatColor color){
            l(color + msgline);
            l(" ");
            l(ChatColor.WHITE + pluginName + " " + ChatColor.AQUA + pluginVersion);
            l(" ");
            l(ChatColor.WHITE + "Author: " + ChatColor.YELLOW  + author);
            l(" ");
            l(color + msgline);

            if (color == red){
                l(red + "BedwarsRel not found, unable to enable related support");
            }
        }

        public static void checkUpdate(int resId)
        {
            new UpdateChecker(JavaPlugin.getPlugin(Main.class), resId).getVersion(version ->
            {
                isLastestVersion = pluginVersion.equals(version);
                if (!isLastestVersion){
                    if (update_tip != null){
                        for (String list : update_tip){
                            l(list);
                        }

                    }

                }
            }
            );
        }
}