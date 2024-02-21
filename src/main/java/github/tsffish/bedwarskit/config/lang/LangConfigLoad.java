package github.tsffish.bedwarskit.config.lang;

import github.tsffish.bedwarskit.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

import static github.tsffish.bedwarskit.Main.language;
import static github.tsffish.bedwarskit.Main.msgline;
import static github.tsffish.bedwarskit.config.lang.LangConfigHandler.*;
import static github.tsffish.bedwarskit.config.misc.ErrorConfigHandler.er;
import static github.tsffish.bedwarskit.util.misc.MessSender.l;

public class LangConfigLoad{
    private static final String name = "LangConfigLoad";
    private static final String reason = "vaule is null";
    private static final Plugin plugin = JavaPlugin.getPlugin(Main.class);
    public static YamlConfiguration config;

    public static void loadLangConfig(){

        File langFile = new File(plugin.getDataFolder(), "lang.yml");
        if (!langFile.exists()) {
            plugin.saveResource("lang.yml", false);
        }

        FileConfiguration c = YamlConfiguration.loadConfiguration(langFile);

        if (c.getStringList(LangConfigPath.path_update_tip) != null) {
            update_tip = c.getStringList(LangConfigPath.path_update_tip);
        } else {
            sendError(LangConfigPath.path_update_tip);
        }

        if (c.getStringList(LangConfigPath.path_command_help) != null) {
            command_help = c.getStringList(LangConfigPath.path_command_help);
        } else {
            sendError(LangConfigPath.path_command_help);
        }


        l("<LangConfigLoad> Finish Load Config");


    }
    private static void sendError(String path){
        er(name, path, reason);
    }
}