package github.tsffish.bedwarskit.config.res;

import github.tsffish.bedwarskit.BedwarsKit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

import static github.tsffish.bedwarskit.config.misc.ConfigVersionChecker.checkAndRenameConfig;
import static github.tsffish.bedwarskit.config.misc.ErrorConfigHandler.er;
import static github.tsffish.bedwarskit.config.res.ResConfigHandler.*;
import static github.tsffish.bedwarskit.util.misc.MessSender.l;
import static github.tsffish.bedwarskit.util.misc.PluginStartUp.tipHaveChinese;
import static github.tsffish.bedwarskit.util.misc.PluginState.isDebug;
import static github.tsffish.bedwarskit.util.misc.PluginState.language;
import static github.tsffish.bedwarskit.util.misc.StringMgr.finishLoadConfig;
import static github.tsffish.bedwarskit.util.misc.StringMgr.vauleIsNull;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class ResConfigLoad {
    private static final String name = "ResConfigLoad";
    private static final String reason = vauleIsNull;
    private static final BedwarsKit plugin = BedwarsKit.getInstance();

    public static void loadResConfig() {


        String fileName = "floating_font.yml";
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

        if (c.getString(ResConfigPath.path_resBlock_Emerald) != null) {
            resBlock_Emerald = c.getBoolean(ResConfigPath.path_resBlock_Emerald);
        } else {
            sendError(ResConfigPath.path_resBlock_Emerald);
        }
        if (c.getString(ResConfigPath.path_resBlock_Emerald_isSmall) != null) {
            resBlock_Emerald_isSmall = c.getBoolean(ResConfigPath.path_resBlock_Emerald_isSmall);
        } else {
            sendError(ResConfigPath.path_resBlock_Emerald_isSmall);
        }

        if (c.getString(ResConfigPath.path_resBlock_Emerald_y) != null) {
            resBlock_Emerald_y = c.getDouble(ResConfigPath.path_resBlock_Emerald_y);
        } else {
            sendError(ResConfigPath.path_resBlock_Emerald_y);
        }
        if (c.getString(ResConfigPath.path_resBlock_Emerald_spinSpeed) != null) {
            resBlock_Emerald_spinSpeed = c.getLong(ResConfigPath.path_resBlock_Emerald_spinSpeed);
        } else {
            sendError(ResConfigPath.path_resBlock_Emerald_spinSpeed);
        }
        if (c.getString(ResConfigPath.path_resBlock_Emerald_yawPerTick) != null) {
            resBlock_Emerald_yawPerTick = Float.parseFloat(c.get(ResConfigPath.path_resBlock_Emerald_yawPerTick).toString());
        } else {
            sendError(ResConfigPath.path_resBlock_Emerald_yawPerTick);
        }

        if (c.getString(ResConfigPath.path_resText1_Emerald) != null) {
            resText1_Emerald = c.getString(ResConfigPath.path_resText1_Emerald);
        } else {
            sendError(ResConfigPath.path_resText1_Emerald);
        }
        if (c.getString(ResConfigPath.path_resText2_Emerald) != null) {
            resText2_Emerald = c.getString(ResConfigPath.path_resText2_Emerald);
        } else {
            sendError(ResConfigPath.path_resText2_Emerald);
        }
        if (c.getString(ResConfigPath.path_resText3_Emerald) != null) {
            resText3_Emerald = c.getString(ResConfigPath.path_resText3_Emerald);
        } else {
            sendError(ResConfigPath.path_resText3_Emerald);
        }
        if (c.getString(ResConfigPath.path_resText1_Emerald_y) != null) {
            resText1_Emerald_y = c.getDouble(ResConfigPath.path_resText1_Emerald_y);
        } else {
            sendError(ResConfigPath.path_resText1_Emerald_y);
        }
        if (c.getString(ResConfigPath.path_resText2_Emerald_y) != null) {
            resText2_Emerald_y = c.getDouble(ResConfigPath.path_resText2_Emerald_y);
        } else {
            sendError(ResConfigPath.path_resText2_Emerald_y);
        }

        if (c.getString(ResConfigPath.path_resText3_Emerald_y) != null) {
            resText3_Emerald_y = c.getDouble(ResConfigPath.path_resText3_Emerald_y);
        } else {
            sendError(ResConfigPath.path_resText3_Emerald_y);
        }


        if (c.getString(ResConfigPath.path_resBlock_Diamond) != null) {
            resBlock_Diamond = c.getBoolean(ResConfigPath.path_resBlock_Diamond);
        } else {
            sendError(ResConfigPath.path_resBlock_Diamond);
        }
        if (c.getString(ResConfigPath.path_resBlock_Diamond_isSmall) != null) {
            resBlock_Diamond_isSmall = c.getBoolean(ResConfigPath.path_resBlock_Diamond_isSmall);
        } else {
            sendError(ResConfigPath.path_resBlock_Diamond_isSmall);
        }
        if (c.getString(ResConfigPath.path_resBlock_Diamond_y) != null) {
            resBlock_Diamond_y = c.getDouble(ResConfigPath.path_resBlock_Diamond_y);
        } else {
            sendError(ResConfigPath.path_resBlock_Diamond_y);
        }
        if (c.getString(ResConfigPath.path_resBlock_Diamond_spinSpeed) != null) {
            resBlock_Diamond_spinSpeed = c.getLong(ResConfigPath.path_resBlock_Diamond_spinSpeed);
        } else {
            sendError(ResConfigPath.path_resBlock_Diamond_spinSpeed);
        }
        if (c.getString(ResConfigPath.path_resBlock_Diamond_yawPerTick) != null) {
            resBlock_Diamond_yawPerTick = Float.parseFloat(c.get(ResConfigPath.path_resBlock_Diamond_yawPerTick).toString());
        } else {
            sendError(ResConfigPath.path_resBlock_Diamond_yawPerTick);
        }

        if (c.getString(ResConfigPath.path_resText1_Diamond) != null) {
            resText1_Diamond = c.getString(ResConfigPath.path_resText1_Diamond);
        } else {
            sendError(ResConfigPath.path_resText1_Diamond);
        }
        if (c.getString(ResConfigPath.path_resText2_Diamond) != null) {
            resText2_Diamond = c.getString(ResConfigPath.path_resText2_Diamond);
        } else {
            sendError(ResConfigPath.path_resText2_Diamond);
        }
        if (c.getString(ResConfigPath.path_resText3_Diamond) != null) {
            resText3_Diamond = c.getString(ResConfigPath.path_resText3_Diamond);
        } else {
            sendError(ResConfigPath.path_resText3_Diamond);
        }
        if (c.getString(ResConfigPath.path_resText1_Diamond_y) != null) {
            resText1_Diamond_y = c.getDouble(ResConfigPath.path_resText1_Diamond_y);
        } else {
            sendError(ResConfigPath.path_resText1_Diamond_y);
        }
        if (c.getString(ResConfigPath.path_resText2_Diamond_y) != null) {
            resText2_Diamond_y = c.getDouble(ResConfigPath.path_resText2_Diamond_y);
        } else {
            sendError(ResConfigPath.path_resText2_Diamond_y);
        }

        if (c.getString(ResConfigPath.path_resText3_Diamond_y) != null) {
            resText3_Diamond_y = c.getDouble(ResConfigPath.path_resText3_Diamond_y);
        } else {
            sendError(ResConfigPath.path_resText3_Diamond_y);
        }


        if (c.getString(ResConfigPath.path_resBlock_Gold) != null) {
            resBlock_Gold = c.getBoolean(ResConfigPath.path_resBlock_Gold);
        } else {
            sendError(ResConfigPath.path_resBlock_Gold);
        }
        if (c.getString(ResConfigPath.path_resBlock_Gold_isSmall) != null) {
            resBlock_Gold_isSmall = c.getBoolean(ResConfigPath.path_resBlock_Gold_isSmall);
        } else {
            sendError(ResConfigPath.path_resBlock_Gold_isSmall);
        }

        if (c.getString(ResConfigPath.path_resBlock_Gold_y) != null) {
            resBlock_Gold_y = c.getDouble(ResConfigPath.path_resBlock_Gold_y);
        } else {
            sendError(ResConfigPath.path_resBlock_Gold_y);
        }
        if (c.getString(ResConfigPath.path_resBlock_Gold_spinSpeed) != null) {
            resBlock_Gold_spinSpeed = c.getLong(ResConfigPath.path_resBlock_Gold_spinSpeed);
        } else {
            sendError(ResConfigPath.path_resBlock_Gold_spinSpeed);
        }
        if (c.getString(ResConfigPath.path_resBlock_Gold_yawPerTick) != null) {
            resBlock_Gold_yawPerTick = Float.parseFloat(c.get(ResConfigPath.path_resBlock_Gold_yawPerTick).toString());
        } else {
            sendError(ResConfigPath.path_resBlock_Gold_yawPerTick);
        }


        if (c.getString(ResConfigPath.path_resText1_Gold) != null) {
            resText1_Gold = c.getString(ResConfigPath.path_resText1_Gold);
        } else {
            sendError(ResConfigPath.path_resText1_Gold);
        }
        if (c.getString(ResConfigPath.path_resText2_Gold) != null) {
            resText2_Gold = c.getString(ResConfigPath.path_resText2_Gold);
        } else {
            sendError(ResConfigPath.path_resText2_Gold);
        }
        if (c.getString(ResConfigPath.path_resText3_Gold) != null) {
            resText3_Gold = c.getString(ResConfigPath.path_resText3_Gold);
        } else {
            sendError(ResConfigPath.path_resText3_Gold);
        }
        if (c.getString(ResConfigPath.path_resText1_Gold_y) != null) {
            resText1_Gold_y = c.getDouble(ResConfigPath.path_resText1_Gold_y);
        } else {
            sendError(ResConfigPath.path_resText1_Gold_y);
        }
        if (c.getString(ResConfigPath.path_resText2_Gold_y) != null) {
            resText2_Gold_y = c.getDouble(ResConfigPath.path_resText2_Gold_y);
        } else {
            sendError(ResConfigPath.path_resText2_Gold_y);
        }

        if (c.getString(ResConfigPath.path_resText3_Gold_y) != null) {
            resText3_Gold_y = c.getDouble(ResConfigPath.path_resText3_Gold_y);
        } else {
            sendError(ResConfigPath.path_resText3_Gold_y);
        }

        if (c.getString(ResConfigPath.path_resBlock_Iron) != null) {
            resBlock_Iron = c.getBoolean(ResConfigPath.path_resBlock_Iron);
        } else {
            sendError(ResConfigPath.path_resBlock_Iron);
        }
        if (c.getString(ResConfigPath.path_resBlock_Iron_isSmall) != null) {
            resBlock_Iron_isSmall = c.getBoolean(ResConfigPath.path_resBlock_Iron_isSmall);
        } else {
            sendError(ResConfigPath.path_resBlock_Iron_isSmall);
        }
        if (c.getString(ResConfigPath.path_resBlock_Iron_y) != null) {
            resBlock_Iron_y = c.getDouble(ResConfigPath.path_resBlock_Iron_y);
        } else {
            sendError(ResConfigPath.path_resBlock_Iron_y);
        }
        if (c.getString(ResConfigPath.path_resBlock_Iron_spinSpeed) != null) {
            resBlock_Iron_spinSpeed = c.getLong(ResConfigPath.path_resBlock_Iron_spinSpeed);
        } else {
            sendError(ResConfigPath.path_resBlock_Iron_spinSpeed);
        }
        if (c.getString(ResConfigPath.path_resBlock_Iron_yawPerTick) != null) {
            resBlock_Iron_yawPerTick = Float.parseFloat(c.get(ResConfigPath.path_resBlock_Iron_yawPerTick).toString());
        } else {
            sendError(ResConfigPath.path_resBlock_Iron_yawPerTick);
        }


        if (c.getString(ResConfigPath.path_resText1_Iron) != null) {
            resText1_Iron = c.getString(ResConfigPath.path_resText1_Iron);
        } else {
            sendError(ResConfigPath.path_resText1_Iron);
        }
        if (c.getString(ResConfigPath.path_resText2_Iron) != null) {
            resText2_Iron = c.getString(ResConfigPath.path_resText2_Iron);
        } else {
            sendError(ResConfigPath.path_resText2_Iron);
        }
        if (c.getString(ResConfigPath.path_resText3_Iron) != null) {
            resText3_Iron = c.getString(ResConfigPath.path_resText3_Iron);
        } else {
            sendError(ResConfigPath.path_resText3_Iron);
        }
        if (c.getString(ResConfigPath.path_resText1_Iron_y) != null) {
            resText1_Iron_y = c.getDouble(ResConfigPath.path_resText1_Iron_y);
        } else {
            sendError(ResConfigPath.path_resText1_Iron_y);
        }
        if (c.getString(ResConfigPath.path_resText2_Iron_y) != null) {
            resText2_Iron_y = c.getDouble(ResConfigPath.path_resText2_Iron_y);
        } else {
            sendError(ResConfigPath.path_resText2_Iron_y);
        }

        if (c.getString(ResConfigPath.path_resText3_Iron_y) != null) {
            resText3_Iron_y = c.getDouble(ResConfigPath.path_resText3_Iron_y);
        } else {
            sendError(ResConfigPath.path_resText3_Iron_y);
        }

        if (isDebug()) {
            l("<" + name + "> " + finishLoadConfig);
        }

    }

    private static void sendError(String path) {
        er(name, path, reason);
    }
}