package github.tsffish.bedwarskit.config.scb;

import github.tsffish.bedwarskit.BedwarsKit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;

import static github.tsffish.bedwarskit.config.lang.LangConfigHandler.finishLoadConfig;
import static github.tsffish.bedwarskit.config.misc.ConfigVersionChecker.checkAndRenameConfig;
import static github.tsffish.bedwarskit.config.scb.ScbConfigHandler.*;
import static github.tsffish.bedwarskit.util.PluginInit.tipHaveChinese;
import static github.tsffish.bedwarskit.util.PluginState.isDebug;
import static github.tsffish.bedwarskit.util.PluginState.language;
import static github.tsffish.bedwarskit.util.misc.MessSender.l;
import static github.tsffish.bedwarskit.util.misc.MessSender.le;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class ScbConfigLoad {
    private static final String className = ScbConfigLoad.class.getSimpleName();
    private static final BedwarsKit plugin = BedwarsKit.getInstance();

    public static void loadScbConfig() {

        String fileName = "scoreboard.yml";
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
        customScoreboard = c.getBoolean(ScbConfigPath.path_customScoreboard);

        if (customScoreboard) {

            meanTeamStat_Alive = c.getString(ScbConfigPath.path_meanTeamStat_Alive);

            meanTeamStat_Dead = c.getString(ScbConfigPath.path_meanTeamStat_Dead);

            meanBedStat_Yes = c.getString(ScbConfigPath.path_meanBedStat_Yes);

            meanBedStat_No = c.getString(ScbConfigPath.path_meanBedStat_No);

            teamStat_BedYes = c.getString(ScbConfigPath.path_teamStat_BedYes);

            teamStat_None = c.getString(ScbConfigPath.path_teamStat_None);

            teamStat_BedNo = c.getString(ScbConfigPath.path_teamStat_BedNo);

            serverIp = c.getString(ScbConfigPath.path_serverIp);

            meanYou = c.getString(ScbConfigPath.path_meanYou);

            meanNotYou = c.getString(ScbConfigPath.path_meanNotYou);

            meanBedwars = c.getString(ScbConfigPath.path_meanBedwars);

            mean2v2Mode = c.getString(ScbConfigPath.path_mean2v2Mode);

            mean4v4Mode = c.getString(ScbConfigPath.path_mean4v4Mode);

            meanGameEnd = c.getString(ScbConfigPath.path_meanGameEnd);

            ScoreBoard2v2Title = c.getString(ScbConfigPath.path_ScoreBoard2v2Title);

            ScoreBoard2v2Line01 = c.getString(ScbConfigPath.path_ScoreBoard2v2Line01);
            ScoreBoard2v2Line02 = c.getString(ScbConfigPath.path_ScoreBoard2v2Line02);
            ScoreBoard2v2Line03 = c.getString(ScbConfigPath.path_ScoreBoard2v2Line03);
            ScoreBoard2v2Line04 = c.getString(ScbConfigPath.path_ScoreBoard2v2Line04);
            ScoreBoard2v2Line05 = c.getString(ScbConfigPath.path_ScoreBoard2v2Line05);
            ScoreBoard2v2Line06 = c.getString(ScbConfigPath.path_ScoreBoard2v2Line06);
            ScoreBoard2v2Line07 = c.getString(ScbConfigPath.path_ScoreBoard2v2Line07);
            ScoreBoard2v2Line08 = c.getString(ScbConfigPath.path_ScoreBoard2v2Line08);
            ScoreBoard2v2Line09 = c.getString(ScbConfigPath.path_ScoreBoard2v2Line09);
            ScoreBoard2v2Line10 = c.getString(ScbConfigPath.path_ScoreBoard2v2Line10);
            ScoreBoard2v2Line11 = c.getString(ScbConfigPath.path_ScoreBoard2v2Line11);
            ScoreBoard2v2Line12 = c.getString(ScbConfigPath.path_ScoreBoard2v2Line12);
            ScoreBoard2v2Line13 = c.getString(ScbConfigPath.path_ScoreBoard2v2Line13);
            ScoreBoard2v2Line14 = c.getString(ScbConfigPath.path_ScoreBoard2v2Line14);
            ScoreBoard2v2Line15 = c.getString(ScbConfigPath.path_ScoreBoard2v2Line15);
            ScoreBoard2v2Line16 = c.getString(ScbConfigPath.path_ScoreBoard2v2Line16);

            ScoreBoard4v4Title = c.getString(ScbConfigPath.path_ScoreBoard4v4Title);

            ScoreBoard4v4Line01 = c.getString(ScbConfigPath.path_ScoreBoard4v4Line01);
            ScoreBoard4v4Line02 = c.getString(ScbConfigPath.path_ScoreBoard4v4Line02);
            ScoreBoard4v4Line03 = c.getString(ScbConfigPath.path_ScoreBoard4v4Line03);
            ScoreBoard4v4Line04 = c.getString(ScbConfigPath.path_ScoreBoard4v4Line04);
            ScoreBoard4v4Line05 = c.getString(ScbConfigPath.path_ScoreBoard4v4Line05);
            ScoreBoard4v4Line06 = c.getString(ScbConfigPath.path_ScoreBoard4v4Line06);
            ScoreBoard4v4Line07 = c.getString(ScbConfigPath.path_ScoreBoard4v4Line07);
            ScoreBoard4v4Line08 = c.getString(ScbConfigPath.path_ScoreBoard4v4Line08);
            ScoreBoard4v4Line09 = c.getString(ScbConfigPath.path_ScoreBoard4v4Line09);
            ScoreBoard4v4Line10 = c.getString(ScbConfigPath.path_ScoreBoard4v4Line10);
            ScoreBoard4v4Line11 = c.getString(ScbConfigPath.path_ScoreBoard4v4Line11);
            ScoreBoard4v4Line12 = c.getString(ScbConfigPath.path_ScoreBoard4v4Line12);
            ScoreBoard4v4Line13 = c.getString(ScbConfigPath.path_ScoreBoard4v4Line13);
            ScoreBoard4v4Line14 = c.getString(ScbConfigPath.path_ScoreBoard4v4Line14);
            ScoreBoard4v4Line15 = c.getString(ScbConfigPath.path_ScoreBoard4v4Line15);
            ScoreBoard4v4Line16 = c.getString(ScbConfigPath.path_ScoreBoard4v4Line16);


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
    }
}