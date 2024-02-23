package github.tsffish.bedwarskit.config.lang;

import github.tsffish.bedwarskit.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

import static github.tsffish.bedwarskit.config.lang.LangConfigHandler.command_help;
import static github.tsffish.bedwarskit.config.lang.LangConfigHandler.update_tip;
import static github.tsffish.bedwarskit.config.misc.ErrorConfigHandler.er;
import static github.tsffish.bedwarskit.util.misc.StringMgr.finishLoadConfig;
import static github.tsffish.bedwarskit.util.misc.StringMgr.vauleIsNull;
import static github.tsffish.bedwarskit.util.misc.MessSender.l;

public class LangConfigLoad{
    private static final String name = "LangConfigLoad";
    private static final String reason = vauleIsNull;
    private static final Main plugin = Main.getInstance();
    public static YamlConfiguration config;

    public static void loadLangConfig(){

        File file = new File(plugin.getDataFolder(), "lang.yml");

        if (!file.exists()) {
                plugin.saveResource("lang.yml", false);
        }

        FileConfiguration c = YamlConfiguration.loadConfiguration(file);

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


        l("<" + name + "> " + finishLoadConfig);

    }
    private static void sendError(String path){
        er(name, path, reason);
    }
}