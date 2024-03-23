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
import static github.tsffish.bedwarskit.util.PluginInit.tipHaveChinese;
import static github.tsffish.bedwarskit.util.PluginState.isDebug;
import static github.tsffish.bedwarskit.util.PluginState.language;
import static github.tsffish.bedwarskit.util.misc.MessSender.l;
import static github.tsffish.bedwarskit.util.misc.MessSender.le;
import static github.tsffish.bedwarskit.util.teamshop.ShopMenu.loadLevelUpInv;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class LangConfigLoad {
    private static final String className = LangConfigLoad.class.getSimpleName();
    private static final BedwarsKit plugin = BedwarsKit.getInstance();

    public static void loadLangConfig() {

        String fileName = "lang.yml";
        String use_config_version = "1.9.6";


        File file = new File(plugin.getDataFolder(), fileName);

        if (!file.exists()) {
            plugin.saveResource(fileName, false);
            if (language().equals("zh")) {
                tipHaveChinese();
            }
        }

        checkAndRenameConfig(file, use_config_version);

        FileConfiguration c = YamlConfiguration.loadConfiguration(file);

        try {
            extracted(c);
        } catch (Exception e) {
            le(className, e);
        }


        if (isDebug()) {
            l("<" + className + "> " + finishLoadConfig);
        }

    }

    private static void extracted(FileConfiguration c) {
        update_tip = c.getStringList(LangConfigPath.path_update_tip);

        command_help = c.getStringList(LangConfigPath.path_command_help);

        needIsWrong_preventLoadWorld = c.getString(LangConfigPath.path_needIsWrong_preventLoadWorld);

        needIsWrong_killfb_oneHealthKill = c.getString(LangConfigPath.path_needIsWrong_killfb_oneHealthKill);

        relConfigIsChange_tryToSave = c.getString(LangConfigPath.path_relConfigIsChange_tryToSave);

        relConfigIsChange_Saved = c.getString(LangConfigPath.path_relConfigIsChange_Saved);

        meanInvalidInventoryType = c.getString(LangConfigPath.path_meanInvalidInventoryType);

         meanConfigLoadError = c.getString(LangConfigPath.path_meanConfigLoadError);

     vauleIsWrong = c.getString(LangConfigPath.path_vauleIsWrong);
     vauleIsNull = c.getString(LangConfigPath.path_vauleIsNull);
     cantFoundSupport = c.getString(LangConfigPath.path_cantFoundSupport);
     finishLoadConfig = c.getString(LangConfigPath.path_finishLoadConfig);
     meanConfig_renamedTo = c.getString(LangConfigPath.path_meanConfig_renamedTo);
     meanConfig_versionNotMatch = c.getString(LangConfigPath.path_meanConfig_versionNotMatch);
     meanDebugDisable = c.getString(LangConfigPath.path_meanDebugDisable);
     meanDebugEnable = c.getString(LangConfigPath.path_meanDebugEnable);
     meanRegExListener = c.getString(LangConfigPath.path_meanRegExListener);
     meanRegExListenerSucc = c.getString(LangConfigPath.path_meanRegExListenerSucc);
     meanCommandIsPlayerOnly = c.getString(LangConfigPath.path_meanCommandIsPlayerOnly);
     meanEditGameToggleToTrue = c.getString(LangConfigPath.path_meanEditGameToggleToTrue);
     meanEditGameToggleToFalse = c.getString(LangConfigPath.path_meanEditGameToggleToFalse);
     meanJavaAccessDenied = c.getString(LangConfigPath.path_meanJavaAccessDenied);
     meanEntityNameSetTo = c.getString(LangConfigPath.path_meanEntityNameSetTo);
     meanEntityNameRemove = c.getString(LangConfigPath.path_meanEntityNameRemove);
    }
}