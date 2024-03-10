package github.tsffish.bedwarskit.config.scb;

import github.tsffish.bedwarskit.BedwarsKit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;

import static github.tsffish.bedwarskit.config.misc.ConfigVersionChecker.checkAndRenameConfig;
import static github.tsffish.bedwarskit.config.misc.ErrorConfigHandler.er;
import static github.tsffish.bedwarskit.config.scb.ScbConfigHandler.*;
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
public class ScbConfigLoad {
    private static final String name = "ScbConfigLoad";
    private static final String reason = vauleIsNull;
    private static final BedwarsKit plugin = BedwarsKit.getInstance();

    public static void loadScbConfig() {

        String fileName = "scoreboard.yml";
        String use_config_version = "1.9.57";
        //String currentLanguage = language();
        //ResourceExtractor.copyResource(fileName, currentLanguage);

        //File file = new File(plugin.getDataFolder() + fileName);

        //FileConfiguration c = YamlConfiguration.loadConfiguration(file);

        File file = new File(plugin.getDataFolder(), fileName);

        if (!file.exists()) {
            plugin.saveResource(fileName, false);
            if (language().equals("zh")) {
                tipHaveChinese();
            }
        }

        checkAndRenameConfig(file, use_config_version);

        FileConfiguration c = YamlConfiguration.loadConfiguration(file);

        if (c.getString(ScbConfigPath.path_customScoreboard) != null) {
            customScoreboard = c.getBoolean(ScbConfigPath.path_customScoreboard);
        } else {
            sendError(ScbConfigPath.path_customScoreboard);
        }

        if (customScoreboard) {

            //
            //
            //
            //START HERE
            //
            //
            //

            if (c.getString(ScbConfigPath.path_meanTeamStat_Alive) != null) {
                meanTeamStat_Alive = c.getString(ScbConfigPath.path_meanTeamStat_Alive);
            } else {
                sendError(ScbConfigPath.path_meanTeamStat_Alive);
            }

            if (c.getString(ScbConfigPath.path_meanTeamStat_Dead) != null) {
                meanTeamStat_Dead = c.getString(ScbConfigPath.path_meanTeamStat_Dead);
            } else {
                sendError(ScbConfigPath.path_meanTeamStat_Dead);
            }

            if (c.getString(ScbConfigPath.path_meanBedStat_Yes) != null) {
                meanBedStat_Yes = c.getString(ScbConfigPath.path_meanBedStat_Yes);
            } else {
                sendError(ScbConfigPath.path_meanBedStat_Yes);
            }

            if (c.getString(ScbConfigPath.path_meanBedStat_No) != null) {
                meanBedStat_No = c.getString(ScbConfigPath.path_meanBedStat_No);
            } else {
                sendError(ScbConfigPath.path_meanBedStat_No);
            }

            if (c.getString(ScbConfigPath.path_teamStat_BedYes) != null) {
                teamStat_BedYes = c.getString(ScbConfigPath.path_teamStat_BedYes);
            } else {
                sendError(ScbConfigPath.path_teamStat_BedYes);
            }

            if (c.getString(ScbConfigPath.path_teamStat_None) != null) {
                teamStat_None = c.getString(ScbConfigPath.path_teamStat_None);
            } else {
                sendError(ScbConfigPath.path_teamStat_None);
            }

            if (c.getString(ScbConfigPath.path_teamStat_BedNo) != null) {
                teamStat_BedNo = c.getString(ScbConfigPath.path_teamStat_BedNo);
            } else {
                sendError(ScbConfigPath.path_teamStat_BedNo);
            }

            if (c.getString(ScbConfigPath.path_serverIp) != null) {
                serverIp = c.getString(ScbConfigPath.path_serverIp);
            } else {
                sendError(ScbConfigPath.path_serverIp);
            }

            if (c.getString(ScbConfigPath.path_meanYou) != null) {
                meanYou = c.getString(ScbConfigPath.path_meanYou);
            } else {
                sendError(ScbConfigPath.path_meanYou);
            }

            if (c.getString(ScbConfigPath.path_meanNotYou) != null) {
                meanNotYou = c.getString(ScbConfigPath.path_meanNotYou);
            } else {
                sendError(ScbConfigPath.path_meanNotYou);
            }


            if (c.getString(ScbConfigPath.path_meanBedwars) != null) {
                meanBedwars = c.getString(ScbConfigPath.path_meanBedwars);
            } else {
                sendError(ScbConfigPath.path_meanBedwars);
            }
            if (c.getString(ScbConfigPath.path_mean2v2Mode) != null) {
                mean2v2Mode = c.getString(ScbConfigPath.path_mean2v2Mode);
            } else {
                sendError(ScbConfigPath.path_mean2v2Mode);
            }
            if (c.getString(ScbConfigPath.path_mean4v4Mode) != null) {
                mean4v4Mode = c.getString(ScbConfigPath.path_mean4v4Mode);
            } else {
                sendError(ScbConfigPath.path_mean4v4Mode);
            }


            if (c.getString(ScbConfigPath.path_meanGameEnd) != null) {
                meanGameEnd = c.getString(ScbConfigPath.path_meanGameEnd);
            } else {
                sendError(ScbConfigPath.path_meanGameEnd);
            }


            if (c.getString(ScbConfigPath.path_ScoreBoard2v2Title) != null) {
                ScoreBoard2v2Title = c.getString(ScbConfigPath.path_ScoreBoard2v2Title);
            } else {
                sendError(ScbConfigPath.path_ScoreBoard2v2Title);
            }

            if (c.getString(ScbConfigPath.path_ScoreBoard2v2Line01) != null) {
                ScoreBoard2v2Line01 = c.getString(ScbConfigPath.path_ScoreBoard2v2Line01);
            } else {
                sendError(ScbConfigPath.path_ScoreBoard2v2Line01);
            }

            if (c.getString(ScbConfigPath.path_ScoreBoard2v2Line02) != null) {
                ScoreBoard2v2Line02 = c.getString(ScbConfigPath.path_ScoreBoard2v2Line02);
            } else {
                sendError(ScbConfigPath.path_ScoreBoard2v2Line02);
            }
            if (c.getString(ScbConfigPath.path_ScoreBoard2v2Line03) != null) {
                ScoreBoard2v2Line03 = c.getString(ScbConfigPath.path_ScoreBoard2v2Line03);
            } else {
                sendError(ScbConfigPath.path_ScoreBoard2v2Line03);
            }
            if (c.getString(ScbConfigPath.path_ScoreBoard2v2Line04) != null) {
                ScoreBoard2v2Line04 = c.getString(ScbConfigPath.path_ScoreBoard2v2Line04);
            } else {
                sendError(ScbConfigPath.path_ScoreBoard2v2Line04);
            }
            if (c.getString(ScbConfigPath.path_ScoreBoard2v2Line05) != null) {
                ScoreBoard2v2Line05 = c.getString(ScbConfigPath.path_ScoreBoard2v2Line05);
            } else {
                sendError(ScbConfigPath.path_ScoreBoard2v2Line05);
            }
            if (c.getString(ScbConfigPath.path_ScoreBoard2v2Line06) != null) {
                ScoreBoard2v2Line06 = c.getString(ScbConfigPath.path_ScoreBoard2v2Line06);
            } else {
                sendError(ScbConfigPath.path_ScoreBoard2v2Line06);
            }
            if (c.getString(ScbConfigPath.path_ScoreBoard2v2Line07) != null) {
                ScoreBoard2v2Line07 = c.getString(ScbConfigPath.path_ScoreBoard2v2Line07);
            } else {
                sendError(ScbConfigPath.path_ScoreBoard2v2Line07);
            }
            if (c.getString(ScbConfigPath.path_ScoreBoard2v2Line08) != null) {
                ScoreBoard2v2Line08 = c.getString(ScbConfigPath.path_ScoreBoard2v2Line08);
            } else {
                sendError(ScbConfigPath.path_ScoreBoard2v2Line08);
            }
            if (c.getString(ScbConfigPath.path_ScoreBoard2v2Line09) != null) {
                ScoreBoard2v2Line09 = c.getString(ScbConfigPath.path_ScoreBoard2v2Line09);
            } else {
                sendError(ScbConfigPath.path_ScoreBoard2v2Line09);
            }
            if (c.getString(ScbConfigPath.path_ScoreBoard2v2Line10) != null) {
                ScoreBoard2v2Line10 = c.getString(ScbConfigPath.path_ScoreBoard2v2Line10);
            } else {
                sendError(ScbConfigPath.path_ScoreBoard2v2Line10);
            }
            if (c.getString(ScbConfigPath.path_ScoreBoard2v2Line11) != null) {
                ScoreBoard2v2Line11 = c.getString(ScbConfigPath.path_ScoreBoard2v2Line11);
            } else {
                sendError(ScbConfigPath.path_ScoreBoard2v2Line11);
            }
            if (c.getString(ScbConfigPath.path_ScoreBoard2v2Line12) != null) {
                ScoreBoard2v2Line12 = c.getString(ScbConfigPath.path_ScoreBoard2v2Line12);
            } else {
                sendError(ScbConfigPath.path_ScoreBoard2v2Line12);
            }
            if (c.getString(ScbConfigPath.path_ScoreBoard2v2Line13) != null) {
                ScoreBoard2v2Line13 = c.getString(ScbConfigPath.path_ScoreBoard2v2Line13);
            } else {
                sendError(ScbConfigPath.path_ScoreBoard2v2Line13);
            }
            if (c.getString(ScbConfigPath.path_ScoreBoard2v2Line14) != null) {
                ScoreBoard2v2Line14 = c.getString(ScbConfigPath.path_ScoreBoard2v2Line14);
            } else {
                sendError(ScbConfigPath.path_ScoreBoard2v2Line14);
            }
            if (c.getString(ScbConfigPath.path_ScoreBoard2v2Line15) != null) {
                ScoreBoard2v2Line15 = c.getString(ScbConfigPath.path_ScoreBoard2v2Line15);
            } else {
                sendError(ScbConfigPath.path_ScoreBoard2v2Line15);
            }
            if (c.getString(ScbConfigPath.path_ScoreBoard2v2Line10) != null) {
                ScoreBoard2v2Line16 = c.getString(ScbConfigPath.path_ScoreBoard2v2Line16);
            } else {
                sendError(ScbConfigPath.path_ScoreBoard2v2Line16);
            }


            if (c.getString(ScbConfigPath.path_ScoreBoard4v4Title) != null) {
                ScoreBoard4v4Title = c.getString(ScbConfigPath.path_ScoreBoard4v4Title);
            } else {
                sendError(ScbConfigPath.path_ScoreBoard4v4Title);
            }

            if (c.getString(ScbConfigPath.path_ScoreBoard4v4Line01) != null) {
                ScoreBoard4v4Line01 = c.getString(ScbConfigPath.path_ScoreBoard4v4Line01);
            } else {
                sendError(ScbConfigPath.path_ScoreBoard4v4Line01);
            }

            if (c.getString(ScbConfigPath.path_ScoreBoard4v4Line02) != null) {
                ScoreBoard4v4Line02 = c.getString(ScbConfigPath.path_ScoreBoard4v4Line02);
            } else {
                sendError(ScbConfigPath.path_ScoreBoard4v4Line02);
            }
            if (c.getString(ScbConfigPath.path_ScoreBoard4v4Line03) != null) {
                ScoreBoard4v4Line03 = c.getString(ScbConfigPath.path_ScoreBoard4v4Line03);
            } else {
                sendError(ScbConfigPath.path_ScoreBoard4v4Line03);
            }
            if (c.getString(ScbConfigPath.path_ScoreBoard4v4Line04) != null) {
                ScoreBoard4v4Line04 = c.getString(ScbConfigPath.path_ScoreBoard4v4Line04);
            } else {
                sendError(ScbConfigPath.path_ScoreBoard4v4Line04);
            }
            if (c.getString(ScbConfigPath.path_ScoreBoard4v4Line05) != null) {
                ScoreBoard4v4Line05 = c.getString(ScbConfigPath.path_ScoreBoard4v4Line05);
            } else {
                sendError(ScbConfigPath.path_ScoreBoard4v4Line05);
            }
            if (c.getString(ScbConfigPath.path_ScoreBoard4v4Line06) != null) {
                ScoreBoard4v4Line06 = c.getString(ScbConfigPath.path_ScoreBoard4v4Line06);
            } else {
                sendError(ScbConfigPath.path_ScoreBoard4v4Line06);
            }
            if (c.getString(ScbConfigPath.path_ScoreBoard4v4Line07) != null) {
                ScoreBoard4v4Line07 = c.getString(ScbConfigPath.path_ScoreBoard4v4Line07);
            } else {
                sendError(ScbConfigPath.path_ScoreBoard4v4Line07);
            }
            if (c.getString(ScbConfigPath.path_ScoreBoard4v4Line08) != null) {
                ScoreBoard4v4Line08 = c.getString(ScbConfigPath.path_ScoreBoard4v4Line08);
            } else {
                sendError(ScbConfigPath.path_ScoreBoard4v4Line08);
            }
            if (c.getString(ScbConfigPath.path_ScoreBoard4v4Line09) != null) {
                ScoreBoard4v4Line09 = c.getString(ScbConfigPath.path_ScoreBoard4v4Line09);
            } else {
                sendError(ScbConfigPath.path_ScoreBoard4v4Line09);
            }
            if (c.getString(ScbConfigPath.path_ScoreBoard4v4Line10) != null) {
                ScoreBoard4v4Line10 = c.getString(ScbConfigPath.path_ScoreBoard4v4Line10);
            } else {
                sendError(ScbConfigPath.path_ScoreBoard4v4Line10);
            }
            if (c.getString(ScbConfigPath.path_ScoreBoard4v4Line11) != null) {
                ScoreBoard4v4Line11 = c.getString(ScbConfigPath.path_ScoreBoard4v4Line11);
            } else {
                sendError(ScbConfigPath.path_ScoreBoard4v4Line11);
            }
            if (c.getString(ScbConfigPath.path_ScoreBoard4v4Line12) != null) {
                ScoreBoard4v4Line12 = c.getString(ScbConfigPath.path_ScoreBoard4v4Line12);
            } else {
                sendError(ScbConfigPath.path_ScoreBoard4v4Line12);
            }
            if (c.getString(ScbConfigPath.path_ScoreBoard4v4Line13) != null) {
                ScoreBoard4v4Line13 = c.getString(ScbConfigPath.path_ScoreBoard4v4Line13);
            } else {
                sendError(ScbConfigPath.path_ScoreBoard4v4Line13);
            }
            if (c.getString(ScbConfigPath.path_ScoreBoard4v4Line14) != null) {
                ScoreBoard4v4Line14 = c.getString(ScbConfigPath.path_ScoreBoard4v4Line14);
            } else {
                sendError(ScbConfigPath.path_ScoreBoard4v4Line14);
            }
            if (c.getString(ScbConfigPath.path_ScoreBoard4v4Line15) != null) {
                ScoreBoard4v4Line15 = c.getString(ScbConfigPath.path_ScoreBoard4v4Line15);
            } else {
                sendError(ScbConfigPath.path_ScoreBoard4v4Line15);
            }
            if (c.getString(ScbConfigPath.path_ScoreBoard4v4Line10) != null) {
                ScoreBoard4v4Line16 = c.getString(ScbConfigPath.path_ScoreBoard4v4Line16);
            } else {
                sendError(ScbConfigPath.path_ScoreBoard4v4Line16);
            }


            ScbConfigHandler.ScoreBoard2v2Line = new HashMap<>(18);
            ScbConfigHandler.ScoreBoard2v2Line.put(100, ScbConfigHandler.ScoreBoard2v2Title);
            ScbConfigHandler.ScoreBoard2v2Line.put(16, ScbConfigHandler.ScoreBoard2v2Line01);
            ScbConfigHandler.ScoreBoard2v2Line.put(15, ScbConfigHandler.ScoreBoard2v2Line02);
            ScbConfigHandler.ScoreBoard2v2Line.put(14, ScbConfigHandler.ScoreBoard2v2Line03);
            ScbConfigHandler.ScoreBoard2v2Line.put(13, ScbConfigHandler.ScoreBoard2v2Line04);
            ScbConfigHandler.ScoreBoard2v2Line.put(12, ScbConfigHandler.ScoreBoard2v2Line05);
            ScbConfigHandler.ScoreBoard2v2Line.put(11, ScbConfigHandler.ScoreBoard2v2Line06);
            ScbConfigHandler.ScoreBoard2v2Line.put(10, ScbConfigHandler.ScoreBoard2v2Line07);
            ScbConfigHandler.ScoreBoard2v2Line.put(9, ScbConfigHandler.ScoreBoard2v2Line08);
            ScbConfigHandler.ScoreBoard2v2Line.put(8, ScbConfigHandler.ScoreBoard2v2Line09);
            ScbConfigHandler.ScoreBoard2v2Line.put(7, ScbConfigHandler.ScoreBoard2v2Line10);
            ScbConfigHandler.ScoreBoard2v2Line.put(6, ScbConfigHandler.ScoreBoard2v2Line11);
            ScbConfigHandler.ScoreBoard2v2Line.put(5, ScbConfigHandler.ScoreBoard2v2Line12);
            ScbConfigHandler.ScoreBoard2v2Line.put(4, ScbConfigHandler.ScoreBoard2v2Line13);
            ScbConfigHandler.ScoreBoard2v2Line.put(3, ScbConfigHandler.ScoreBoard2v2Line14);
            ScbConfigHandler.ScoreBoard2v2Line.put(2, ScbConfigHandler.ScoreBoard2v2Line15);
            ScbConfigHandler.ScoreBoard2v2Line.put(1, ScbConfigHandler.ScoreBoard2v2Line16);

            ScbConfigHandler.ScoreBoard4v4Line = new HashMap<>(18);
            ScbConfigHandler.ScoreBoard4v4Line.put(100, ScbConfigHandler.ScoreBoard4v4Title);
            ScbConfigHandler.ScoreBoard4v4Line.put(16, ScbConfigHandler.ScoreBoard4v4Line01);
            ScbConfigHandler.ScoreBoard4v4Line.put(15, ScbConfigHandler.ScoreBoard4v4Line02);
            ScbConfigHandler.ScoreBoard4v4Line.put(14, ScbConfigHandler.ScoreBoard4v4Line03);
            ScbConfigHandler.ScoreBoard4v4Line.put(13, ScbConfigHandler.ScoreBoard4v4Line04);
            ScbConfigHandler.ScoreBoard4v4Line.put(12, ScbConfigHandler.ScoreBoard4v4Line05);
            ScbConfigHandler.ScoreBoard4v4Line.put(11, ScbConfigHandler.ScoreBoard4v4Line06);
            ScbConfigHandler.ScoreBoard4v4Line.put(10, ScbConfigHandler.ScoreBoard4v4Line07);
            ScbConfigHandler.ScoreBoard4v4Line.put(9, ScbConfigHandler.ScoreBoard4v4Line08);
            ScbConfigHandler.ScoreBoard4v4Line.put(8, ScbConfigHandler.ScoreBoard4v4Line09);
            ScbConfigHandler.ScoreBoard4v4Line.put(7, ScbConfigHandler.ScoreBoard4v4Line10);
            ScbConfigHandler.ScoreBoard4v4Line.put(6, ScbConfigHandler.ScoreBoard4v4Line11);
            ScbConfigHandler.ScoreBoard4v4Line.put(5, ScbConfigHandler.ScoreBoard4v4Line12);
            ScbConfigHandler.ScoreBoard4v4Line.put(4, ScbConfigHandler.ScoreBoard4v4Line13);
            ScbConfigHandler.ScoreBoard4v4Line.put(3, ScbConfigHandler.ScoreBoard4v4Line14);
            ScbConfigHandler.ScoreBoard4v4Line.put(2, ScbConfigHandler.ScoreBoard4v4Line15);
            ScbConfigHandler.ScoreBoard4v4Line.put(1, ScbConfigHandler.ScoreBoard4v4Line16);


        }

        if (isDebug()) {
            l("<" + name + "> " + finishLoadConfig);
        }

    }

    private static void sendError(String path) {
        er(name, path, reason);
    }
}