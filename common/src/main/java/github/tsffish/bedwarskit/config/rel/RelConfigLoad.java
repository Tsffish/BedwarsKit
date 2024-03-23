package github.tsffish.bedwarskit.config.rel;

import github.tsffish.bedwarskit.BedwarsKit;
import github.tsffish.bedwarskit.config.main.MainConfigHandler;
import io.github.bedwarsrel.BedwarsRel;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.Objects;

import static github.tsffish.bedwarskit.config.lang.LangConfigHandler.*;
import static github.tsffish.bedwarskit.config.misc.ErrorConfigHandler.er;
import static github.tsffish.bedwarskit.config.rel.RelConfigHandler.currentLocale;
import static github.tsffish.bedwarskit.config.rel.RelConfigHandler.shoutPrefix;
import static github.tsffish.bedwarskit.util.PluginState.isDebug;
import static github.tsffish.bedwarskit.util.misc.MessSender.l;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class RelConfigLoad {
    private static final String className = RelConfigLoad.class.getSimpleName();
    private static final String reason = vauleIsNull;
    private static final BedwarsKit plugin = BedwarsKit.getInstance();

    private static void extracted(FileConfiguration c) {
        boolean isChangeRelCfg = false;
        if (c.get(RelConfigPath.path_currentLocale) != null) {
            currentLocale = c.getString(RelConfigPath.path_currentLocale);
        } else {
            currentLocale = BedwarsRel.getInstance().getFallbackLocale();
            sendError(RelConfigPath.path_currentLocale);
        }

        File file = new File(plugin.getDataFolder().getPath() + File.separator + "locale", currentLocale + ".yml");

        if (file.exists()) {
            FileConfiguration langC = YamlConfiguration.loadConfiguration(file);
            BedwarsRel._l("");

        }

        if (c.getStringList(RelConfigPath.path_shoutPrefix).get(0) != null) {
            shoutPrefix = c.getStringList(RelConfigPath.path_shoutPrefix).get(0);
        } else {
            sendError(RelConfigPath.path_shoutPrefix);
        }

        if (MainConfigHandler.preventLoadWorld) {
            if (c.get(RelConfigPath.path_die_on_void) != null) {
                if (c.getBoolean(RelConfigPath.path_die_on_void) == true) {
                    c.set(RelConfigPath.path_die_on_void, false);
                    if (!Objects.equals(needIsWrong_preventLoadWorld, "")) {

                        String willSend = needIsWrong_preventLoadWorld.
                                replace("{path}", RelConfigPath.path_die_on_void);
                        l(willSend);
                    }
                    isChangeRelCfg = true;
                }
            } else {
                sendError(RelConfigPath.path_die_on_void);
            }

            if (c.get(RelConfigPath.path_spectation_enabled) != null) {
                if (c.getBoolean(RelConfigPath.path_spectation_enabled) == false) {
                    c.set(RelConfigPath.path_spectation_enabled, true);
                    if (!Objects.equals(needIsWrong_preventLoadWorld, "")) {

                        String willSend = needIsWrong_preventLoadWorld.
                                replace("{path}", RelConfigPath.path_spectation_enabled);
                        l(willSend);
                    }
                    isChangeRelCfg = true;
                }
            }
        }

        if (MainConfigHandler.killfb_oneHealthKill) {
            if (c.get(RelConfigPath.path_keep_inventory_on_death) != null) {
                if (c.getBoolean(RelConfigPath.path_keep_inventory_on_death) == false) {
                    c.set(RelConfigPath.path_keep_inventory_on_death, true);
                    if (!Objects.equals(needIsWrong_killfb_oneHealthKill, "")) {

                        String willSend = needIsWrong_killfb_oneHealthKill.
                                replace("{path}", RelConfigPath.path_keep_inventory_on_death);
                        l(willSend);
                    }
                    isChangeRelCfg = true;
                }
            } else {
                sendError(RelConfigPath.path_keep_inventory_on_death);
            }
        }


        if (isChangeRelCfg) {
            if (!Objects.equals(relConfigIsChange_tryToSave, "")) {
                l(relConfigIsChange_tryToSave);
            }
            BedwarsRel.getInstance().saveConfig();
            if (!Objects.equals(relConfigIsChange_Saved, "")) {
                l(relConfigIsChange_Saved);
            }
        }
    }

    private static void sendError(String path) {
        er(className, path, reason);
    }

    public static void loadRelConfig() {

        FileConfiguration c = BedwarsRel.getInstance().getConfig();

        extracted(c);

        if (isDebug()) {
            l("<" + className + "> " + finishLoadConfig);
        }

    }
}