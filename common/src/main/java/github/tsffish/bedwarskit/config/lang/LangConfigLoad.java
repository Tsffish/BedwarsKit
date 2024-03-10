package github.tsffish.bedwarskit.config.lang;

import github.tsffish.bedwarskit.BedwarsKit;
import github.tsffish.bedwarskit.config.kit.KitConfigLoad;
import github.tsffish.bedwarskit.config.rel.RelConfigLoad;
import github.tsffish.bedwarskit.config.res.ResConfigLoad;
import github.tsffish.bedwarskit.config.scb.ScbConfigLoad;
import github.tsffish.bedwarskit.config.task.TaskConfigLoad;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

import static github.tsffish.bedwarskit.config.lang.LangConfigHandler.*;
import static github.tsffish.bedwarskit.config.misc.ConfigVersionChecker.checkAndRenameConfig;
import static github.tsffish.bedwarskit.config.misc.ErrorConfigHandler.er;
import static github.tsffish.bedwarskit.util.misc.MessSender.l;
import static github.tsffish.bedwarskit.util.misc.PluginStartUp.tipHaveChinese;
import static github.tsffish.bedwarskit.util.misc.PluginState.isDebug;
import static github.tsffish.bedwarskit.util.misc.PluginState.language;
import static github.tsffish.bedwarskit.util.misc.StringMgr.finishLoadConfig;
import static github.tsffish.bedwarskit.util.misc.StringMgr.vauleIsNull;
import static github.tsffish.bedwarskit.util.teamshop.ShopMenu.loadLevelUpInv;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class LangConfigLoad {
    private static final String name = "LangConfigLoad";
    private static final String reason = vauleIsNull;
    private static final BedwarsKit plugin = BedwarsKit.getInstance();

    public static void loadLangConfig() {


        String fileName = "lang.yml";
        String use_config_version = "1.9.57";


        File file = new File(plugin.getDataFolder(), fileName);

        if (!file.exists()) {
            plugin.saveResource(fileName, false);
            if (language().equals("zh")) {
                tipHaveChinese();
            }
        }

        checkAndRenameConfig(file, use_config_version);

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


        if (c.get(LangConfigPath.path_needIsWrong_preventLoadWorld) != null) {
            needIsWrong_preventLoadWorld = c.getString(LangConfigPath.path_needIsWrong_preventLoadWorld);
        } else {
            sendError(LangConfigPath.path_needIsWrong_preventLoadWorld);
        }

        if (c.get(LangConfigPath.path_needIsWrong_killfb_oneHealthKill) != null) {
            needIsWrong_killfb_oneHealthKill = c.getString(LangConfigPath.path_needIsWrong_killfb_oneHealthKill);
        } else {
            sendError(LangConfigPath.path_needIsWrong_killfb_oneHealthKill);
        }

        if (c.get(LangConfigPath.path_relConfigIsChange_tryToSave) != null) {
            relConfigIsChange_tryToSave = c.getString(LangConfigPath.path_relConfigIsChange_tryToSave);
        } else {
            sendError(LangConfigPath.path_relConfigIsChange_tryToSave);
        }

        if (c.get(LangConfigPath.path_relConfigIsChange_Saved) != null) {
            relConfigIsChange_Saved = c.getString(LangConfigPath.path_relConfigIsChange_Saved);
        } else {
            sendError(LangConfigPath.path_relConfigIsChange_Saved);
        }


        if (c.get(LangConfigPath.path_meanInvalidInventoryType) != null) {
            meanInvalidInventoryType = c.getString(LangConfigPath.path_meanInvalidInventoryType);
        } else {
            sendError(LangConfigPath.path_meanInvalidInventoryType);
        }

        loadLevelUpInv();
        RelConfigLoad.loadRelConfig();
        KitConfigLoad.loadKitConfig();
        TaskConfigLoad.loadTaskConfig();
        ResConfigLoad.loadResConfig();
        ScbConfigLoad.loadScbConfig();

        if (isDebug()) {
            l("<" + name + "> " + finishLoadConfig);
        }

    }

    private static void sendError(String path) {
        er(name, path, reason);
    }
}