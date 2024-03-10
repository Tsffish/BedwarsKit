package github.tsffish.bedwarskit.util.misc;

import github.tsffish.bedwarskit.BedwarsKit;
import github.tsffish.bedwarskit.command.BaseCommand;
import github.tsffish.bedwarskit.command.ShoutCommand;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.Locale;

import static github.tsffish.bedwarskit.config.main.MainConfigLoad.loadMainConfig;
import static github.tsffish.bedwarskit.util.misc.ChatColor.*;
import static github.tsffish.bedwarskit.util.misc.MessSender.l;
import static github.tsffish.bedwarskit.util.misc.PluginState.*;
import static github.tsffish.bedwarskit.util.misc.StringMgr.*;
import static github.tsffish.bedwarskit.util.misc.bstats.StartMetrics.startMetrics;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class PluginStartUp {
    private static final BedwarsKit plugin = BedwarsKit.getInstance();
    private static boolean trigSend = false;

    public static void sendPluginStartUpInfo() {
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
        File configFile = new File("spigot.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);

        boolean isBungeeEnabled = config.getBoolean("settings.bungeecord");
        setIsBungeeMode(isBungeeEnabled);

        String currentLang = Locale.getDefault().getLanguage();
        setLanguage(currentLang);
        loadCurrentLang(currentLang);

        sendPluginStartUpInfo();

        setServerVersion(plugin.getServer().getVersion());

        l(meanConfigLoad);
        loadMainConfig(null, true);

        regCommand();

        if (!isDebug()) {
            startMetrics();
        }
    }

    public static void tipHaveChinese() {
        if (!trigSend) {
            trigSend = true;
            File file = new File(plugin.getDataFolder(), "no_tip");

            if (!file.exists()) {
                l("生成了新的配置文件，并且默认语言是zh，即将发送提示...");
                new BukkitRunnable() {
                    public void run() {
                        l("\n"
                                + green + msgline + msgline + "\n" + "\n"
                                + aqua + "(以防你不知道)" + white + " BedwarsKit支持中文！但默认保存的配置是英文" + "\n" + "\n"
                                + white + "设置中文的方法:" + "\n"
                                + white + "1.用压缩软件打开插件本体，" + "\n"
                                + white + "2.将zh文件夹内的文件都复制到plugins/BedwarsKit文件夹并覆盖， " + "\n"
                                + white + "3.再在控制台输入" + yellow + "bwk reload" + white + "就完成了！" + "\n" + "\n"
                                + gray + "(此提示会在生成新的配置文件时发送)" + "\n"
                                + gray + "(在BedwarsKit目录内新建一个名为no_tip的文件可以禁用此提示)" + "\n" + "\n"
                                + green + msgline + msgline
                        );
                    }
                }.runTaskLater(plugin, 90L);
            }
        }
    }
}
