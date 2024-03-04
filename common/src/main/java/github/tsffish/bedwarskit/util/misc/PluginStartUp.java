package github.tsffish.bedwarskit.util.misc;

import github.tsffish.bedwarskit.BedwarsKit;
import github.tsffish.bedwarskit.command.CommandInfo;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.Locale;

import static github.tsffish.bedwarskit.config.main.MainConfigLoad.loadMainConfig;
import static github.tsffish.bedwarskit.listener.procol.PlayerVisibility.setupPacketListener;
import static github.tsffish.bedwarskit.util.misc.ChatColor.*;
import static github.tsffish.bedwarskit.util.misc.MessSender.l;
import static github.tsffish.bedwarskit.util.misc.PluginState.*;
import static github.tsffish.bedwarskit.util.misc.StringMgr.msgline;
import static github.tsffish.bedwarskit.util.misc.StringMgr.pluginName;
import static github.tsffish.bedwarskit.util.misc.bstats.StartMetrics.startMetrics;
/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class PluginStartUp {
    private static final BedwarsKit plugin = BedwarsKit.getInstance();
    public static void sendPluginStartUpInfo(){
        l(green + msgline);
        l(" ");
        l(white + pluginName + " " + aqua + pluginVersion());
        l(" ");
        l(white + "Author: " + yellow  + getAuthor());
        l(" ");
        l(green + msgline);
    }
    public static void regCommand(){
        plugin.getCommand("bwk").setExecutor(new CommandInfo());
        plugin.getCommand("bwk reload").setExecutor(new CommandInfo());
        l("Command registered");
    }
    public static void startup(){
        sendPluginStartUpInfo();
        File configFile = new File("spigot.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);

        boolean isBungeeEnabled = config.getBoolean("settings.bungeecord");
        setIsBungeeMode(isBungeeEnabled);

        setLanguage(Locale.getDefault().getLanguage());
        setServerVersion(plugin.getServer().getVersion());

        loadMainConfig(null, true);
        regCommand();

        setupPacketListener();

        if (!isDebug()) {startMetrics();}
    }
}
