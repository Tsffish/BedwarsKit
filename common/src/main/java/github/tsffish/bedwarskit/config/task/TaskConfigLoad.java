package github.tsffish.bedwarskit.config.task;

import github.tsffish.bedwarskit.BedwarsKit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

import static github.tsffish.bedwarskit.config.lang.LangConfigHandler.finishLoadConfig;
import static github.tsffish.bedwarskit.config.misc.ConfigVersionChecker.checkAndRenameConfig;
import static github.tsffish.bedwarskit.config.task.TaskConfigHandler.*;
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
public class TaskConfigLoad {
    private static final String className = TaskConfigLoad.class.getSimpleName();
    private static final BedwarsKit plugin = BedwarsKit.getInstance();

    public static void loadTaskConfig() {

        String fileName = "task.yml";
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
        gametask = c.getBoolean(TaskConfigPath.path_gametask);

        gametask_spawntime = c.getBoolean(TaskConfigPath.path_gametask_spawntime);

        gametask_spawntime_iron_base = c.getInt(TaskConfigPath.path_gametask_spawntime_iron_base);
        gametask_spawntime_iron_1 = c.getInt(TaskConfigPath.path_gametask_spawntime_iron_1);
        gametask_spawntime_iron_2 = c.getInt(TaskConfigPath.path_gametask_spawntime_iron_2);
        gametask_spawntime_iron_3 = c.getInt(TaskConfigPath.path_gametask_spawntime_iron_3);
        gametask_spawntime_iron_4 = c.getInt(TaskConfigPath.path_gametask_spawntime_iron_4);

        gametask_spawntime_gold_base = c.getInt(TaskConfigPath.path_gametask_spawntime_gold_base);
        gametask_spawntime_gold_1 = c.getInt(TaskConfigPath.path_gametask_spawntime_gold_1);
        gametask_spawntime_gold_2 = c.getInt(TaskConfigPath.path_gametask_spawntime_gold_2);
        gametask_spawntime_gold_3 = c.getInt(TaskConfigPath.path_gametask_spawntime_gold_3);
        gametask_spawntime_gold_4 = c.getInt(TaskConfigPath.path_gametask_spawntime_gold_4);

        gametask_spawntime_diamond_base = c.getInt(TaskConfigPath.path_gametask_spawntime_diamond_base);
        gametask_spawntime_diamond_1 = c.getInt(TaskConfigPath.path_gametask_spawntime_diamond_1);
        gametask_spawntime_diamond_2 = c.getInt(TaskConfigPath.path_gametask_spawntime_diamond_2);
        gametask_spawntime_diamond_3 = c.getInt(TaskConfigPath.path_gametask_spawntime_diamond_3);
        gametask_spawntime_diamond_4 = c.getInt(TaskConfigPath.path_gametask_spawntime_diamond_4);

        gametask_spawntime_emerald_base = c.getInt(TaskConfigPath.path_gametask_spawntime_emerald_base);
        gametask_spawntime_emerald_1 = c.getInt(TaskConfigPath.path_gametask_spawntime_emerald_1);
        gametask_spawntime_emerald_2 = c.getInt(TaskConfigPath.path_gametask_spawntime_emerald_2);
        gametask_spawntime_emerald_3 = c.getInt(TaskConfigPath.path_gametask_spawntime_emerald_3);
        gametask_spawntime_emerald_4 = c.getInt(TaskConfigPath.path_gametask_spawntime_emerald_4);


        gametask_finalbattle = c.getBoolean(TaskConfigPath.path_gametask_finalbattle);

        gametask_finalbattle_boundaries_time = c.getLong(TaskConfigPath.path_gametask_finalbattle_boundaries_time);

        gametask_finalbattle_boundaries_size = c.getDouble(TaskConfigPath.path_gametask_finalbattle_boundaries_size);

        gametask_boundaries_size = c.getDouble(TaskConfigPath.path_gametask_boundaries_size);

        gametask_spawntime_tasks_iron1 = c.getInt(TaskConfigPath.path_gametask_spawntime_tasks_iron1);
        gametask_spawntime_tasks_iron2 = c.getInt(TaskConfigPath.path_gametask_spawntime_tasks_iron2);
        gametask_spawntime_tasks_iron3 = c.getInt(TaskConfigPath.path_gametask_spawntime_tasks_iron3);
        gametask_spawntime_tasks_iron4 = c.getInt(TaskConfigPath.path_gametask_spawntime_tasks_iron4);

        gametask_spawntime_tasks_gold1 = c.getInt(TaskConfigPath.path_gametask_spawntime_tasks_gold1);
        gametask_spawntime_tasks_gold2 = c.getInt(TaskConfigPath.path_gametask_spawntime_tasks_gold2);
        gametask_spawntime_tasks_gold3 = c.getInt(TaskConfigPath.path_gametask_spawntime_tasks_gold3);
        gametask_spawntime_tasks_gold4 = c.getInt(TaskConfigPath.path_gametask_spawntime_tasks_gold4);


        gametask_spawntime_tasks_diamond1 = c.getInt(TaskConfigPath.path_gametask_spawntime_tasks_diamond1);
        gametask_spawntime_tasks_diamond2 = c.getInt(TaskConfigPath.path_gametask_spawntime_tasks_diamond2);
        gametask_spawntime_tasks_diamond3 = c.getInt(TaskConfigPath.path_gametask_spawntime_tasks_diamond3);
        gametask_spawntime_tasks_diamond4 = c.getInt(TaskConfigPath.path_gametask_spawntime_tasks_diamond4);
        gametask_spawntime_tasks_emerald1 = c.getInt(TaskConfigPath.path_gametask_spawntime_tasks_emerald1);
        gametask_spawntime_tasks_emerald2 = c.getInt(TaskConfigPath.path_gametask_spawntime_tasks_emerald2);
        gametask_spawntime_tasks_emerald3 = c.getInt(TaskConfigPath.path_gametask_spawntime_tasks_emerald3);
        gametask_spawntime_tasks_emerald4 = c.getInt(TaskConfigPath.path_gametask_spawntime_tasks_emerald4);

        gametask_finalbattle_time = c.getInt(TaskConfigPath.path_gametask_finalbattle_time);

        gametask_name_iron1 = c.getString(TaskConfigPath.path_gametask_name_iron1);
        gametask_name_iron2 = c.getString(TaskConfigPath.path_gametask_name_iron2);
        gametask_name_iron3 = c.getString(TaskConfigPath.path_gametask_name_iron3);
        gametask_name_iron4 = c.getString(TaskConfigPath.path_gametask_name_iron4);

        gametask_name_gold1 = c.getString(TaskConfigPath.path_gametask_name_gold1);
        gametask_name_gold2 = c.getString(TaskConfigPath.path_gametask_name_gold2);
        gametask_name_gold3 = c.getString(TaskConfigPath.path_gametask_name_gold3);
        gametask_name_gold4 = c.getString(TaskConfigPath.path_gametask_name_gold4);

        gametask_name_diamond1 = c.getString(TaskConfigPath.path_gametask_name_diamond1);
        gametask_name_diamond2 = c.getString(TaskConfigPath.path_gametask_name_diamond2);
        gametask_name_diamond3 = c.getString(TaskConfigPath.path_gametask_name_diamond3);
        gametask_name_diamond4 = c.getString(TaskConfigPath.path_gametask_name_diamond4);

        gametask_name_emerald1 = c.getString(TaskConfigPath.path_gametask_name_emerald1);
        gametask_name_emerald2 = c.getString(TaskConfigPath.path_gametask_name_emerald2);
        gametask_name_emerald3 = c.getString(TaskConfigPath.path_gametask_name_emerald3);
        gametask_name_emerald4 = c.getString(TaskConfigPath.path_gametask_name_emerald4);

        gametask_name_finalbattle = c.getString(TaskConfigPath.path_gametask_name_finalbattle);
        gametask_mess_finalbattle_chat = c.getString(TaskConfigPath.path_gametask_mess_finalbattle_chat);
        gametask_mess_finalbattle_title = c.getString(TaskConfigPath.path_gametask_mess_finalbattle_title);
        gametask_mess_finalbattle_subtitle = c.getString(TaskConfigPath.path_gametask_mess_finalbattle_subtitle);
        gametask_mess_finalbattle_actionbar = c.getString(TaskConfigPath.path_gametask_mess_finalbattle_actionbar);

        gametask_mess_iron1_chat = c.getString(TaskConfigPath.path_gametask_mess_iron1_chat);
        gametask_mess_iron1_title = c.getString(TaskConfigPath.path_gametask_mess_iron1_title);
        gametask_mess_iron1_subtitle = c.getString(TaskConfigPath.path_gametask_mess_iron1_subtitle);
        gametask_mess_iron1_actionbar = c.getString(TaskConfigPath.path_gametask_mess_iron1_actionbar);
        gametask_mess_iron2_chat = c.getString(TaskConfigPath.path_gametask_mess_iron2_chat);
        gametask_mess_iron2_title = c.getString(TaskConfigPath.path_gametask_mess_iron2_title);
        gametask_mess_iron2_subtitle = c.getString(TaskConfigPath.path_gametask_mess_iron2_subtitle);
        gametask_mess_iron2_actionbar = c.getString(TaskConfigPath.path_gametask_mess_iron2_actionbar);
        gametask_mess_iron3_chat = c.getString(TaskConfigPath.path_gametask_mess_iron3_chat);
        gametask_mess_iron3_title = c.getString(TaskConfigPath.path_gametask_mess_iron3_title);
        gametask_mess_iron3_subtitle = c.getString(TaskConfigPath.path_gametask_mess_iron3_subtitle);
        gametask_mess_iron3_actionbar = c.getString(TaskConfigPath.path_gametask_mess_iron3_actionbar);
        gametask_mess_iron4_chat = c.getString(TaskConfigPath.path_gametask_mess_iron4_chat);
        gametask_mess_iron4_title = c.getString(TaskConfigPath.path_gametask_mess_iron4_title);
        gametask_mess_iron4_subtitle = c.getString(TaskConfigPath.path_gametask_mess_iron4_subtitle);
        gametask_mess_iron4_actionbar = c.getString(TaskConfigPath.path_gametask_mess_iron4_actionbar);

        gametask_mess_gold1_chat = c.getString(TaskConfigPath.path_gametask_mess_gold1_chat);
        gametask_mess_gold1_title = c.getString(TaskConfigPath.path_gametask_mess_gold1_title);
        gametask_mess_gold1_subtitle = c.getString(TaskConfigPath.path_gametask_mess_gold1_subtitle);
        gametask_mess_gold1_actionbar = c.getString(TaskConfigPath.path_gametask_mess_gold1_actionbar);
        gametask_mess_gold2_chat = c.getString(TaskConfigPath.path_gametask_mess_gold2_chat);
        gametask_mess_gold2_title = c.getString(TaskConfigPath.path_gametask_mess_gold2_title);
        gametask_mess_gold2_subtitle = c.getString(TaskConfigPath.path_gametask_mess_gold2_subtitle);
        gametask_mess_gold2_actionbar = c.getString(TaskConfigPath.path_gametask_mess_gold2_actionbar);
        gametask_mess_gold3_chat = c.getString(TaskConfigPath.path_gametask_mess_gold3_chat);
        gametask_mess_gold3_title = c.getString(TaskConfigPath.path_gametask_mess_gold3_title);
        gametask_mess_gold3_subtitle = c.getString(TaskConfigPath.path_gametask_mess_gold3_subtitle);
        gametask_mess_gold3_actionbar = c.getString(TaskConfigPath.path_gametask_mess_gold3_actionbar);
        gametask_mess_gold4_chat = c.getString(TaskConfigPath.path_gametask_mess_gold4_chat);
        gametask_mess_gold4_title = c.getString(TaskConfigPath.path_gametask_mess_gold4_title);
        gametask_mess_gold4_subtitle = c.getString(TaskConfigPath.path_gametask_mess_gold4_subtitle);
        gametask_mess_gold4_actionbar = c.getString(TaskConfigPath.path_gametask_mess_gold4_actionbar);


        gametask_mess_diamond1_chat = c.getString(TaskConfigPath.path_gametask_mess_diamond1_chat);
        gametask_mess_diamond1_title = c.getString(TaskConfigPath.path_gametask_mess_diamond1_title);
        gametask_mess_diamond1_subtitle = c.getString(TaskConfigPath.path_gametask_mess_diamond1_subtitle);
        gametask_mess_diamond1_actionbar = c.getString(TaskConfigPath.path_gametask_mess_diamond1_actionbar);
        gametask_mess_diamond2_chat = c.getString(TaskConfigPath.path_gametask_mess_diamond2_chat);
        gametask_mess_diamond2_title = c.getString(TaskConfigPath.path_gametask_mess_diamond2_title);
        gametask_mess_diamond2_subtitle = c.getString(TaskConfigPath.path_gametask_mess_diamond2_subtitle);
        gametask_mess_diamond2_actionbar = c.getString(TaskConfigPath.path_gametask_mess_diamond2_actionbar);
        gametask_mess_diamond3_chat = c.getString(TaskConfigPath.path_gametask_mess_diamond3_chat);
        gametask_mess_diamond3_title = c.getString(TaskConfigPath.path_gametask_mess_diamond3_title);
        gametask_mess_diamond3_subtitle = c.getString(TaskConfigPath.path_gametask_mess_diamond3_subtitle);
        gametask_mess_diamond3_actionbar = c.getString(TaskConfigPath.path_gametask_mess_diamond3_actionbar);
        gametask_mess_diamond4_chat = c.getString(TaskConfigPath.path_gametask_mess_diamond4_chat);
        gametask_mess_diamond4_title = c.getString(TaskConfigPath.path_gametask_mess_diamond4_title);
        gametask_mess_diamond4_subtitle = c.getString(TaskConfigPath.path_gametask_mess_diamond4_subtitle);
        gametask_mess_diamond4_actionbar = c.getString(TaskConfigPath.path_gametask_mess_diamond4_actionbar);

        gametask_mess_emerald1_chat = c.getString(TaskConfigPath.path_gametask_mess_emerald1_chat);
        gametask_mess_emerald1_title = c.getString(TaskConfigPath.path_gametask_mess_emerald1_title);
        gametask_mess_emerald1_subtitle = c.getString(TaskConfigPath.path_gametask_mess_emerald1_subtitle);
        gametask_mess_emerald1_actionbar = c.getString(TaskConfigPath.path_gametask_mess_emerald1_actionbar);
        gametask_mess_emerald2_chat = c.getString(TaskConfigPath.path_gametask_mess_emerald2_chat);
        gametask_mess_emerald2_title = c.getString(TaskConfigPath.path_gametask_mess_emerald2_title);
        gametask_mess_emerald2_subtitle = c.getString(TaskConfigPath.path_gametask_mess_emerald2_subtitle);
        gametask_mess_emerald2_actionbar = c.getString(TaskConfigPath.path_gametask_mess_emerald2_actionbar);
        gametask_mess_emerald3_chat = c.getString(TaskConfigPath.path_gametask_mess_emerald3_chat);
        gametask_mess_emerald3_title = c.getString(TaskConfigPath.path_gametask_mess_emerald3_title);
        gametask_mess_emerald3_subtitle = c.getString(TaskConfigPath.path_gametask_mess_emerald3_subtitle);
        gametask_mess_emerald3_actionbar = c.getString(TaskConfigPath.path_gametask_mess_emerald3_actionbar);
        gametask_mess_emerald4_chat = c.getString(TaskConfigPath.path_gametask_mess_emerald4_chat);
        gametask_mess_emerald4_title = c.getString(TaskConfigPath.path_gametask_mess_emerald4_title);
        gametask_mess_emerald4_subtitle = c.getString(TaskConfigPath.path_gametask_mess_emerald4_subtitle);
        gametask_mess_emerald4_actionbar = c.getString(TaskConfigPath.path_gametask_mess_emerald4_actionbar);

        gametask_finalbattle_boundaries_warnidis = c.getInt(TaskConfigPath.path_gametask_finalbattle_boundaries_warnidis);
        gametask_finalbattle_boundaries_damagebuffer = c.getDouble(TaskConfigPath.path_gametask_finalbattle_boundaries_damagebuffer);
        gametask_finalbattle_boundaries_damage = c.getDouble(TaskConfigPath.path_gametask_finalbattle_boundaries_damage);

        gametask_time_healthset1 = c.getInt(TaskConfigPath.path_gametask_time_healthset1);
        gametask_time_healthset2 = c.getInt(TaskConfigPath.path_gametask_time_healthset2);
        gametask_time_healthset3 = c.getInt(TaskConfigPath.path_gametask_time_healthset3);
        gametask_time_healthset4 = c.getInt(TaskConfigPath.path_gametask_time_healthset4);


        gametask_name_healthset1 = c.getString(TaskConfigPath.path_gametask_name_healthset1);
        gametask_name_healthset2 = c.getString(TaskConfigPath.path_gametask_name_healthset2);
        gametask_name_healthset3 = c.getString(TaskConfigPath.path_gametask_name_healthset3);
        gametask_name_healthset4 = c.getString(TaskConfigPath.path_gametask_name_healthset4);

        gametask_mess_healthset1_chat = c.getString(TaskConfigPath.path_gametask_mess_healthset1_chat);
        gametask_mess_healthset1_title = c.getString(TaskConfigPath.path_gametask_mess_healthset1_title);
        gametask_mess_healthset1_subtitle = c.getString(TaskConfigPath.path_gametask_mess_healthset1_subtitle);
        gametask_mess_healthset1_actionbar = c.getString(TaskConfigPath.path_gametask_mess_healthset1_actionbar);

        gametask_mess_healthset2_chat = c.getString(TaskConfigPath.path_gametask_mess_healthset2_chat);
        gametask_mess_healthset2_title = c.getString(TaskConfigPath.path_gametask_mess_healthset2_title);
        gametask_mess_healthset2_subtitle = c.getString(TaskConfigPath.path_gametask_mess_healthset2_subtitle);
        gametask_mess_healthset2_actionbar = c.getString(TaskConfigPath.path_gametask_mess_healthset2_actionbar);
        gametask_mess_healthset3_chat = c.getString(TaskConfigPath.path_gametask_mess_healthset3_chat);
        gametask_mess_healthset3_title = c.getString(TaskConfigPath.path_gametask_mess_healthset3_title);
        gametask_mess_healthset3_subtitle = c.getString(TaskConfigPath.path_gametask_mess_healthset3_subtitle);
        gametask_mess_healthset3_actionbar = c.getString(TaskConfigPath.path_gametask_mess_healthset3_actionbar);
        gametask_mess_healthset4_chat = c.getString(TaskConfigPath.path_gametask_mess_healthset4_chat);
        gametask_mess_healthset4_title = c.getString(TaskConfigPath.path_gametask_mess_healthset4_title);
        gametask_mess_healthset4_subtitle = c.getString(TaskConfigPath.path_gametask_mess_healthset4_subtitle);
        gametask_mess_healthset4_actionbar = c.getString(TaskConfigPath.path_gametask_mess_healthset4_actionbar);


        gametask_healthset_1 = c.getDouble(TaskConfigPath.path_gametask_healthset_1);
        gametask_healthset_2 = c.getDouble(TaskConfigPath.path_gametask_healthset_2);
        gametask_healthset_3 = c.getDouble(TaskConfigPath.path_gametask_healthset_3);
        gametask_healthset_4 = c.getDouble(TaskConfigPath.path_gametask_healthset_4);
    }
}