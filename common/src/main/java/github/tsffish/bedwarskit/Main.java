package github.tsffish.bedwarskit;

import github.tsffish.bedwarskit.command.CommandInfo;
import github.tsffish.bedwarskit.config.main.MainConfigLoad;
import github.tsffish.bedwarskit.util.misc.update.UpdateChecker;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Locale;

import static github.tsffish.bedwarskit.config.lang.LangConfigHandler.update_tip;
import static github.tsffish.bedwarskit.util.misc.bstats.StartMetrics.startMetrics;
import static github.tsffish.bedwarskit.util.misc.MessSender.l;

public class Main extends JavaPlugin implements Listener {
    private static final String pluginName = "BedwarsKit";
    public static String pluginName(){
        return pluginName;
    }
    private static final String pluginVersion = "1.9.56";
    public static String pluginVersion(){
        return pluginVersion;
    }
    private static final String pluginNameConsole = "[" + pluginName + "]";
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
    static PluginManager pluginManager = Bukkit.getPluginManager();
    private static final int spigotId = 105616;
    public static int spigotId(){
        return spigotId;
    }
    public static final String msgline = "================================";
    public static String language;
    public static String language() {
        return language;
    }
    private static Main instance;
    public static Main getInstance() {
        return instance;
    }
    public static boolean isBungeeMode;
    public static boolean isBungeeMode() {
        return isBungeeMode;
    }
    private static String serverVersion;
    public static String serverVersion(){
        return serverVersion;
    }
    private static final String relName = "BedwarsRel";
    public void onEnable() {
        serverVersion = getServer().getVersion();
        Locale currentLocale = Locale.getDefault();
        language = currentLocale.getLanguage();
        if (isDebug){
        l("Server Version: " + serverVersion);
        l("language: " + language);
        }

        instance = this;

        if (pluginManager.getPlugin(relName) != null) {

            sendPluginStartUpInfo(ChatColor.GREEN);

            MainConfigLoad.loadMainConfig(null, true);

            getCommand("bwk").setExecutor(new CommandInfo());
            getCommand("bwk reload").setExecutor(new CommandInfo());

            handlerHub();

            l("Command registered");

            if (!isDebug) {
                startMetrics();
            }
        } else
            {
            sendPluginStartUpInfo(red);
            }
    }
     void handlerHub(){
         FileConfiguration config = Bukkit.spigot().getConfig();
         isBungeeMode = config.getBoolean("settings.bungeecord", false);
         if (isBungeeMode) {
             if (isDebug){
             System.out.println("using BungeeCord");
             }
         } else {
             if (isDebug){
             System.out.println("not using BungeeCord");
             }
         }
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
                l(red + relName + " not found, unable to enable related support");
            }
        }

        public static void checkUpdate(int resId)
        {
            new UpdateChecker(JavaPlugin.getPlugin(Main.class), resId).getVersion(version ->
            {
                isLastestVersion = pluginVersion.equals(version);
                if (!isLastestVersion){
                    if (update_tip != null && !update_tip.isEmpty()){
                        for (String list : update_tip){
                            l(list);
                        }

                    }

                }
            });
        }
        public static boolean pluginIsDisabling = false;
    @EventHandler
    public void on(final PluginDisableEvent event){
        if (event.getPlugin() == this) {
            if (isDebug){
            l("bwk plugin is disabled");
            }
            pluginIsDisabling = true;
        }
    }
}