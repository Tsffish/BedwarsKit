package github.tsffish.bedwarskit.util;

import github.tsffish.bedwarskit.BedwarsKit;
import github.tsffish.bedwarskit.command.BaseCommand;
import github.tsffish.bedwarskit.command.ShoutCommand;
import github.tsffish.bedwarskit.listener.*;
import github.tsffish.bedwarskit.listener.misc.PluginDisable;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.Locale;
import java.util.UUID;

import static github.tsffish.bedwarskit.config.main.MainConfigLoad.loadMainConfig;
import static github.tsffish.bedwarskit.util.PluginState.*;
import static github.tsffish.bedwarskit.util.misc.ChatColor.*;
import static github.tsffish.bedwarskit.util.misc.MessSender.l;
import static github.tsffish.bedwarskit.util.misc.StartMetrics.startMetrics;
import static github.tsffish.bedwarskit.util.misc.StringMgr.*;
import static github.tsffish.bedwarskit.util.player.RelEditGame.removeEditGamePlayer;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class PluginInit {
    private static final BedwarsKit plugin = BedwarsKit.getInstance();
    private static boolean trigSend = false;
    public static void sendStartUpInfo() {
        l(green + msgline);
        l(" ");
        l(white + pluginName + " " + aqua + pluginVersion());
        l(" ");
        l(white + meanAuthor + ": " + yellow + getAuthor());
        l(" ");
        l(green + msgline);
    }

    public static void regCommand() {
        plugin.getCommand("bwk").setExecutor(new BaseCommand());
        plugin.getCommand("bwk reload").setExecutor(new BaseCommand());
        plugin.getCommand("shout").setExecutor(new ShoutCommand());
        plugin.getCommand("bwk edit").setExecutor(new BaseCommand());
        l(meanCommandReged);
    }

    public static void startup() {
        setUpBungeeCord();

        Locale locale = Locale.getDefault();

        String currentLang = locale.getLanguage();
        String currentCountry = locale.getCountry();
        String currentLanguageFull = currentLang + "_" + currentCountry;

        setLanguage(currentLang);

        setCountry(currentCountry);

        setformatLanguage(currentLanguageFull);

        loadCurrentLang(currentLang);
        sendStartUpInfo();
        trySetUpServerVersion();

        l(meanConfigLoad);
        loadMainConfig(null, true);



        regCommand();
        regListener();

        startMetrics();

    }

    private static void setUpBungeeCord() {
        File configFile = new File("spigot.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);

        boolean isBungeeEnabled = config.getBoolean("settings.bungeecord");
        setIsBungeeMode(isBungeeEnabled);
    }

    public static void regListener() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new RelMobGriefing(), plugin);
        pluginManager.registerEvents(new PluginDisable(), plugin);

        pluginManager.registerEvents(new RelBreakBed(), plugin);
        pluginManager.registerEvents(new RelMapProtect(), plugin);
        pluginManager.registerEvents(new RelBreakItem(), plugin);

        pluginManager.registerEvents(new RelClickInventory(), plugin);

        pluginManager.registerEvents(new RelGameOver(), plugin);

        pluginManager.registerEvents(new RelGameStarted(), plugin);

        pluginManager.registerEvents(new RelOpenInventory(), plugin);
        pluginManager.registerEvents(new RelOpenShop(), plugin);


        pluginManager.registerEvents(new RelPickupItem(), plugin);
        pluginManager.registerEvents(new RelPlaceBlockCorrect(), plugin);

        pluginManager.registerEvents(new RelClick(), plugin);
        pluginManager.registerEvents(new RelPlayerDamage(), plugin);

        pluginManager.registerEvents(new RelPlayerDeath(), plugin);

        pluginManager.registerEvents(new RelPlayerDrop(), plugin);

        pluginManager.registerEvents(new RelFoodLevelLock(), plugin);
        pluginManager.registerEvents(new RelPlayerJoin(), plugin);
        pluginManager.registerEvents(new RelPlayerKilled(), plugin);


        pluginManager.registerEvents(new RelPlayerLeave(), plugin);

        pluginManager.registerEvents(new RelPlayerTeleport(), plugin);

        pluginManager.registerEvents(new RelPlayerToggleFly(), plugin);

        RelPlayerSp.setupPacketListener();
    }

    public static void tipHaveChinese() {
        boolean funEnable = true;
        if (!funEnable){
            return;
        }

        if (!trigSend) {
            trigSend = true;
            File file = new File(plugin.getDataFolder(), "no_tip");

            if (!file.exists()) {
                l("\u751f\u6210\u4e86\u65b0\u7684\u914d\u7f6e\u6587\u4ef6\uff0c\u5e76\u4e14\u9ed8\u8ba4\u8bed\u8a00\u662f\u007a\u0068\uff0c\u5373\u5c06\u53d1\u9001\u63d0\u793a\u002e\u002e\u002e");
                new BukkitRunnable() {
                    public void run() {
                        l("\n"
                                + green + msgline + msgline + "\n" + "\n"
                                + aqua + "\u0028\u4ee5\u9632\u4f60\u4e0d\u77e5\u9053\u0029" + white + "\u0042\u0065\u0064\u0077\u0061\u0072\u0073\u004b\u0069\u0074\u652f\u6301\u4e2d\u6587\uff01\u4f46\u9ed8\u8ba4\u4fdd\u5b58\u7684\u914d\u7f6e\u662f\u82f1\u6587" + "\n" + "\n"
                                + white + "\u8bbe\u7f6e\u4e2d\u6587\u7684\u65b9\u6cd5\u003a" + "\n"
                                + white + "\u0031\u002e\u7528\u538b\u7f29\u8f6f\u4ef6\u6253\u5f00\u63d2\u4ef6\u672c\u4f53\uff0c" + "\n"
                                + white + "\u0032\u002e\u5c06\u007a\u0068\u6587\u4ef6\u5939\u5185\u7684\u6587\u4ef6\u90fd\u590d\u5236\u5230\u0070\u006c\u0075\u0067\u0069\u006e\u0073\u002f\u0042\u0065\u0064\u0077\u0061\u0072\u0073\u004b\u0069\u0074\u6587\u4ef6\u5939\u5e76\u8986\u76d6\uff0c" + "\n"
                                + white + "\u0033\u002e\u518d\u5728\u63a7\u5236\u53f0\u8f93\u5165" + yellow + "bwk reload" + white + "\u5c31\u5b8c\u6210\u4e86\uff01" + "\n" + "\n"
                                + gray + "\u0028\u6b64\u63d0\u793a\u4f1a\u5728\u751f\u6210\u65b0\u7684\u914d\u7f6e\u6587\u4ef6\u65f6\u53d1\u9001\u0029" + "\n"
                                + gray + "\u0028\u5728\u0042\u0065\u0064\u0077\u0061\u0072\u0073\u004b\u0069\u0074\u76ee\u5f55\u5185\u65b0\u5efa\u4e00\u4e2a\u540d\u4e3a\u006e\u006f\u005f\u0074\u0069\u0070\u7684\u6587\u4ef6\u53ef\u4ee5\u7981\u7528\u6b64\u63d0\u793a\u0029" + "\n" + "\n"
                                + green + msgline + msgline
                        );
                    }
                }.runTaskLater(plugin, 90L);
            }
        }
    }

    private static void trySetUpServerVersion() {
        String version = Bukkit.getServer().getVersion();
        String[] parts = version.split("MC: ");
        if (parts.length > 1) {
            String mcVersion = parts[1].split("\\)")[0];
            String formattedVersion = mcVersion.replace(".", "_");

            setServerVersion(version);
        }


        String serverPackageVersion = Bukkit.getServer().getClass().getPackage().getName().substring(23);
        setServerPackageVersion(serverPackageVersion);

    }
    public static void d(){
        l(pluginDisable);
        for (Player player : Bukkit.getOnlinePlayers()){
            UUID playerUUID = player.getUniqueId();
            removeEditGamePlayer(playerUUID);
        }
    }
}
