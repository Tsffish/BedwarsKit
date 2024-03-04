package github.tsffish.bedwarskit.config.task;

import github.tsffish.bedwarskit.BedwarsKit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

import static github.tsffish.bedwarskit.config.misc.ErrorConfigHandler.er;
import static github.tsffish.bedwarskit.config.task.TaskConfigHandler.*;
import static github.tsffish.bedwarskit.util.misc.MessSender.l;
import static github.tsffish.bedwarskit.util.misc.StringMgr.finishLoadConfig;
import static github.tsffish.bedwarskit.util.misc.StringMgr.vauleIsNull;
/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class TaskConfigLoad{
    private static final String name = "TaskConfigLoad";
    private static final String reason = vauleIsNull;
    private static final BedwarsKit plugin = BedwarsKit.getInstance();
    public static YamlConfiguration config;

    public static void loadTaskConfig(){

        File file = new File(plugin.getDataFolder(), "task.yml");

        if (!file.exists()) {
                plugin.saveResource("task.yml", false);
        }


        FileConfiguration c = YamlConfiguration.loadConfiguration(file);


        if (c.getString(TaskConfigPath.path_gametask) != null) {
            gametask = c.getBoolean(TaskConfigPath.path_gametask);
        } else {
            sendError( TaskConfigPath.path_gametask);
        }

        if (c.getString(TaskConfigPath.path_gametask_spawntime) != null) {
            gametask_spawntime = c.getBoolean(TaskConfigPath.path_gametask_spawntime);
        } else {
            sendError( TaskConfigPath.path_gametask_spawntime);
        }
        if (c.getString(TaskConfigPath.path_gametask_spawntime_iron_base) != null) {
            gametask_spawntime_iron_base = c.getInt(TaskConfigPath.path_gametask_spawntime_iron_base);
        } else {
            sendError( TaskConfigPath.path_gametask_spawntime_iron_base);
        }
        if (c.getString(TaskConfigPath.path_gametask_spawntime_iron_1) != null) {
            gametask_spawntime_iron_1 = c.getInt(TaskConfigPath.path_gametask_spawntime_iron_1);
        } else {
            sendError( TaskConfigPath.path_gametask_spawntime_iron_1);
        }
        if (c.getString(TaskConfigPath.path_gametask_spawntime_iron_2) != null) {
            gametask_spawntime_iron_2 = c.getInt(TaskConfigPath.path_gametask_spawntime_iron_2);
        } else {
            sendError( TaskConfigPath.path_gametask_spawntime_iron_2);
        }
        if (c.getString(TaskConfigPath.path_gametask_spawntime_iron_3) != null) {
            gametask_spawntime_iron_3 = c.getInt(TaskConfigPath.path_gametask_spawntime_iron_3);
        } else {
            sendError( TaskConfigPath.path_gametask_spawntime_iron_3);
        }
        if (c.getString(TaskConfigPath.path_gametask_spawntime_iron_4) != null) {
            gametask_spawntime_iron_4 = c.getInt(TaskConfigPath.path_gametask_spawntime_iron_4);
        } else {
            sendError( TaskConfigPath.path_gametask_spawntime_iron_4);
        }
        if (c.getString(TaskConfigPath.path_gametask_spawntime_gold_base) != null) {
            gametask_spawntime_gold_base = c.getInt(TaskConfigPath.path_gametask_spawntime_gold_base);
        } else {
            sendError( TaskConfigPath.path_gametask_spawntime_gold_base);
        }
        if (c.getString(TaskConfigPath.path_gametask_spawntime_gold_1) != null) {
            gametask_spawntime_gold_1 = c.getInt(TaskConfigPath.path_gametask_spawntime_gold_1);
        } else {
            sendError( TaskConfigPath.path_gametask_spawntime_gold_1);
        }
        if (c.getString(TaskConfigPath.path_gametask_spawntime_gold_2) != null) {
            gametask_spawntime_gold_2 = c.getInt(TaskConfigPath.path_gametask_spawntime_gold_2);
        } else {
            sendError( TaskConfigPath.path_gametask_spawntime_gold_2);
        }
        if (c.getString(TaskConfigPath.path_gametask_spawntime_gold_3) != null) {
            gametask_spawntime_gold_3 = c.getInt(TaskConfigPath.path_gametask_spawntime_gold_3);
        } else {
            sendError( TaskConfigPath.path_gametask_spawntime_gold_3);
        }
        if (c.getString(TaskConfigPath.path_gametask_spawntime_gold_4) != null) {
            gametask_spawntime_gold_4 = c.getInt(TaskConfigPath.path_gametask_spawntime_gold_4);
        } else {
            sendError( TaskConfigPath.path_gametask_spawntime_gold_4);
        }
        if (c.getString(TaskConfigPath.path_gametask_spawntime_diamond_base) != null) {
            gametask_spawntime_diamond_base = c.getInt(TaskConfigPath.path_gametask_spawntime_diamond_base);
        } else {
            sendError( TaskConfigPath.path_gametask_spawntime_diamond_base);
        }
        if (c.getString(TaskConfigPath.path_gametask_spawntime_diamond_1) != null) {
            gametask_spawntime_diamond_1 = c.getInt(TaskConfigPath.path_gametask_spawntime_diamond_1);
        } else {
            sendError( TaskConfigPath.path_gametask_spawntime_diamond_1);
        }
        if (c.getString(TaskConfigPath.path_gametask_spawntime_diamond_2) != null) {
            gametask_spawntime_diamond_2 = c.getInt(TaskConfigPath.path_gametask_spawntime_diamond_2);
        } else {
            sendError( TaskConfigPath.path_gametask_spawntime_diamond_2);
        }
        if (c.getString(TaskConfigPath.path_gametask_spawntime_diamond_3) != null) {
            gametask_spawntime_diamond_3 = c.getInt(TaskConfigPath.path_gametask_spawntime_diamond_3);
        } else {
            sendError( TaskConfigPath.path_gametask_spawntime_diamond_3);
        }
        if (c.getString(TaskConfigPath.path_gametask_spawntime_diamond_4) != null) {
            gametask_spawntime_diamond_4 = c.getInt(TaskConfigPath.path_gametask_spawntime_diamond_4);
        } else {
            sendError( TaskConfigPath.path_gametask_spawntime_diamond_4);
        }
        if (c.getString(TaskConfigPath.path_gametask_spawntime_emerald_base) != null) {
            gametask_spawntime_emerald_base = c.getInt(TaskConfigPath.path_gametask_spawntime_emerald_base);
        } else {
            sendError( TaskConfigPath.path_gametask_spawntime_emerald_base);
        }
        if (c.getString(TaskConfigPath.path_gametask_spawntime_emerald_1) != null) {
            gametask_spawntime_emerald_1 = c.getInt(TaskConfigPath.path_gametask_spawntime_emerald_1);
        } else {
            sendError( TaskConfigPath.path_gametask_spawntime_emerald_1);
        }
        if (c.getString(TaskConfigPath.path_gametask_spawntime_emerald_2) != null) {
            gametask_spawntime_emerald_2 = c.getInt(TaskConfigPath.path_gametask_spawntime_emerald_2);
        } else {
            sendError( TaskConfigPath.path_gametask_spawntime_emerald_2);
        }
        if (c.getString(TaskConfigPath.path_gametask_spawntime_emerald_3) != null) {
            gametask_spawntime_emerald_3 = c.getInt(TaskConfigPath.path_gametask_spawntime_emerald_3);
        } else {
            sendError( TaskConfigPath.path_gametask_spawntime_emerald_3);
        }
        if (c.getString(TaskConfigPath.path_gametask_spawntime_emerald_4) != null) {
            gametask_spawntime_emerald_4 = c.getInt(TaskConfigPath.path_gametask_spawntime_emerald_4);
        } else {
            sendError( TaskConfigPath.path_gametask_spawntime_emerald_4);
        }



        if (c.getString(TaskConfigPath.path_gametask_finalbattle) != null) {
            gametask_finalbattle = c.getBoolean(TaskConfigPath.path_gametask_finalbattle);
        } else {
            sendError( TaskConfigPath.path_gametask_finalbattle);
        }

        if (c.getString(TaskConfigPath.path_gametask_finalbattle_boundaries_time) != null) {
            gametask_finalbattle_boundaries_time = c.getLong(TaskConfigPath.path_gametask_finalbattle_boundaries_time);
        } else {
            sendError( TaskConfigPath.path_gametask_finalbattle_boundaries_time);
        }

        if (c.getString(TaskConfigPath.path_gametask_finalbattle_boundaries_size) != null) {
            gametask_finalbattle_boundaries_size = c.getDouble(TaskConfigPath.path_gametask_finalbattle_boundaries_size);
        } else {
            sendError( TaskConfigPath.path_gametask_finalbattle_boundaries_size);
        }

        if (c.getString(TaskConfigPath.path_gametask_boundaries_size) != null) {
            gametask_boundaries_size = c.getDouble(TaskConfigPath.path_gametask_boundaries_size);
        } else {
            sendError( TaskConfigPath.path_gametask_boundaries_size);
        }


        if (c.getString(TaskConfigPath.path_gametask_spawntime_tasks_iron1) != null) {
            gametask_spawntime_tasks_iron1 = c.getInt(TaskConfigPath.path_gametask_spawntime_tasks_iron1);
        } else {
            sendError( TaskConfigPath.path_gametask_spawntime_tasks_iron1);
        }
        if (c.getString(TaskConfigPath.path_gametask_spawntime_tasks_iron2) != null) {
            gametask_spawntime_tasks_iron2 = c.getInt(TaskConfigPath.path_gametask_spawntime_tasks_iron2);
        } else {
            sendError( TaskConfigPath.path_gametask_spawntime_tasks_iron2);
        }
        if (c.getString(TaskConfigPath.path_gametask_spawntime_tasks_iron3) != null) {
            gametask_spawntime_tasks_iron3 = c.getInt(TaskConfigPath.path_gametask_spawntime_tasks_iron3);
        } else {
            sendError( TaskConfigPath.path_gametask_spawntime_tasks_iron3);
        }
        if (c.getString(TaskConfigPath.path_gametask_spawntime_tasks_iron4) != null) {
            gametask_spawntime_tasks_iron4 = c.getInt(TaskConfigPath.path_gametask_spawntime_tasks_iron4);
        } else {
            sendError( TaskConfigPath.path_gametask_spawntime_tasks_iron4);
        }
        if (c.getString(TaskConfigPath.path_gametask_spawntime_tasks_gold1) != null) {
            gametask_spawntime_tasks_gold1 = c.getInt(TaskConfigPath.path_gametask_spawntime_tasks_gold1);
        } else {
            sendError( TaskConfigPath.path_gametask_spawntime_tasks_gold1);
        }
        if (c.getString(TaskConfigPath.path_gametask_spawntime_tasks_gold2) != null) {
            gametask_spawntime_tasks_gold2 = c.getInt(TaskConfigPath.path_gametask_spawntime_tasks_gold2);
        } else {
            sendError( TaskConfigPath.path_gametask_spawntime_tasks_gold2);
        }
        if (c.getString(TaskConfigPath.path_gametask_spawntime_tasks_gold3) != null) {
            gametask_spawntime_tasks_gold3 = c.getInt(TaskConfigPath.path_gametask_spawntime_tasks_gold3);
        } else {
            sendError( TaskConfigPath.path_gametask_spawntime_tasks_gold3);
        }
        if (c.getString(TaskConfigPath.path_gametask_spawntime_tasks_gold4) != null) {
            gametask_spawntime_tasks_gold4 = c.getInt(TaskConfigPath.path_gametask_spawntime_tasks_gold4);
        } else {
            sendError( TaskConfigPath.path_gametask_spawntime_tasks_gold4);
        }
        if (c.getString(TaskConfigPath.path_gametask_spawntime_tasks_diamond1) != null) {
            gametask_spawntime_tasks_diamond1 = c.getInt(TaskConfigPath.path_gametask_spawntime_tasks_diamond1);
        } else {
            sendError( TaskConfigPath.path_gametask_spawntime_tasks_diamond1);
        }
        if (c.getString(TaskConfigPath.path_gametask_spawntime_tasks_diamond2) != null) {
            gametask_spawntime_tasks_diamond2 = c.getInt(TaskConfigPath.path_gametask_spawntime_tasks_diamond2);
        } else {
            sendError( TaskConfigPath.path_gametask_spawntime_tasks_diamond2);
        }
        if (c.getString(TaskConfigPath.path_gametask_spawntime_tasks_diamond3) != null) {
            gametask_spawntime_tasks_diamond3 = c.getInt(TaskConfigPath.path_gametask_spawntime_tasks_diamond3);
        } else {
            sendError( TaskConfigPath.path_gametask_spawntime_tasks_diamond3);
        }
        if (c.getString(TaskConfigPath.path_gametask_spawntime_tasks_diamond4) != null) {
            gametask_spawntime_tasks_diamond4 = c.getInt(TaskConfigPath.path_gametask_spawntime_tasks_diamond4);
        } else {
            sendError( TaskConfigPath.path_gametask_spawntime_tasks_diamond4);
        }
        if (c.getString(TaskConfigPath.path_gametask_spawntime_tasks_emerald1) != null) {
            gametask_spawntime_tasks_emerald1 = c.getInt(TaskConfigPath.path_gametask_spawntime_tasks_emerald1);
        } else {
            sendError( TaskConfigPath.path_gametask_spawntime_tasks_emerald1);
        }
        if (c.getString(TaskConfigPath.path_gametask_spawntime_tasks_emerald2) != null) {
            gametask_spawntime_tasks_emerald2 = c.getInt(TaskConfigPath.path_gametask_spawntime_tasks_emerald2);
        } else {
            sendError( TaskConfigPath.path_gametask_spawntime_tasks_emerald2);
        }
        if (c.getString(TaskConfigPath.path_gametask_spawntime_tasks_emerald3) != null) {
            gametask_spawntime_tasks_emerald3 = c.getInt(TaskConfigPath.path_gametask_spawntime_tasks_emerald3);
        } else {
            sendError( TaskConfigPath.path_gametask_spawntime_tasks_emerald3);
        }
        if (c.getString(TaskConfigPath.path_gametask_spawntime_tasks_emerald4) != null) {
            gametask_spawntime_tasks_emerald4 = c.getInt(TaskConfigPath.path_gametask_spawntime_tasks_emerald4);
        } else {
            sendError( TaskConfigPath.path_gametask_spawntime_tasks_emerald4);
        }

        if (c.getString(TaskConfigPath.path_gametask_finalbattle_time) != null) {
            gametask_finalbattle_time = c.getInt(TaskConfigPath.path_gametask_finalbattle_time);
        } else {
            sendError( TaskConfigPath.path_gametask_finalbattle_time);
        }



        if (c.getString(TaskConfigPath.path_gametask_name_iron1) != null) {
            gametask_name_iron1 = c.getString(TaskConfigPath.path_gametask_name_iron1);
        } else {
            sendError( TaskConfigPath.path_gametask_name_iron1);
        }
        if (c.getString(TaskConfigPath.path_gametask_name_iron2) != null) {
            gametask_name_iron2 = c.getString(TaskConfigPath.path_gametask_name_iron2);
        } else {
            sendError( TaskConfigPath.path_gametask_name_iron2);
        }
        if (c.getString(TaskConfigPath.path_gametask_name_iron3) != null) {
            gametask_name_iron3 = c.getString(TaskConfigPath.path_gametask_name_iron3);
        } else {
            sendError( TaskConfigPath.path_gametask_name_iron3);
        }
        if (c.getString(TaskConfigPath.path_gametask_name_iron4) != null) {
            gametask_name_iron4 = c.getString(TaskConfigPath.path_gametask_name_iron4);
        } else {
            sendError( TaskConfigPath.path_gametask_name_iron4);
        }

        if (c.getString(TaskConfigPath.path_gametask_name_gold1) != null) {
            gametask_name_gold1 = c.getString(TaskConfigPath.path_gametask_name_gold1);
        } else {
            sendError( TaskConfigPath.path_gametask_name_gold1);
        }
        if (c.getString(TaskConfigPath.path_gametask_name_gold2) != null) {
            gametask_name_gold2 = c.getString(TaskConfigPath.path_gametask_name_gold2);
        } else {
            sendError( TaskConfigPath.path_gametask_name_gold2);
        }
        if (c.getString(TaskConfigPath.path_gametask_name_gold3) != null) {
            gametask_name_gold3 = c.getString(TaskConfigPath.path_gametask_name_gold3);
        } else {
            sendError( TaskConfigPath.path_gametask_name_gold3);
        }
        if (c.getString(TaskConfigPath.path_gametask_name_gold4) != null) {
            gametask_name_gold4 = c.getString(TaskConfigPath.path_gametask_name_gold4);
        } else {
            sendError( TaskConfigPath.path_gametask_name_gold4);
        }
        if (c.getString(TaskConfigPath.path_gametask_name_diamond1) != null) {
            gametask_name_diamond1 = c.getString(TaskConfigPath.path_gametask_name_diamond1);
        } else {
            sendError( TaskConfigPath.path_gametask_name_diamond1);
        }
        if (c.getString(TaskConfigPath.path_gametask_name_diamond2) != null) {
            gametask_name_diamond2 = c.getString(TaskConfigPath.path_gametask_name_diamond2);
        } else {
            sendError( TaskConfigPath.path_gametask_name_diamond2);
        }
        if (c.getString(TaskConfigPath.path_gametask_name_diamond3) != null) {
            gametask_name_diamond3 = c.getString(TaskConfigPath.path_gametask_name_diamond3);
        } else {
            sendError( TaskConfigPath.path_gametask_name_diamond3);
        }
        if (c.getString(TaskConfigPath.path_gametask_name_diamond4) != null) {
            gametask_name_diamond4 = c.getString(TaskConfigPath.path_gametask_name_diamond4);
        } else {
            sendError( TaskConfigPath.path_gametask_name_diamond4);
        }
        if (c.getString(TaskConfigPath.path_gametask_name_emerald1) != null) {
            gametask_name_emerald1 = c.getString(TaskConfigPath.path_gametask_name_emerald1);
        } else {
            sendError( TaskConfigPath.path_gametask_name_emerald1);
        }
        if (c.getString(TaskConfigPath.path_gametask_name_emerald2) != null) {
            gametask_name_emerald2 = c.getString(TaskConfigPath.path_gametask_name_emerald2);
        } else {
            sendError( TaskConfigPath.path_gametask_name_emerald2);
        }
        if (c.getString(TaskConfigPath.path_gametask_name_emerald3) != null) {
            gametask_name_emerald3 = c.getString(TaskConfigPath.path_gametask_name_emerald3);
        } else {
            sendError( TaskConfigPath.path_gametask_name_emerald3);
        }
        if (c.getString(TaskConfigPath.path_gametask_name_emerald4) != null) {
            gametask_name_emerald4 = c.getString(TaskConfigPath.path_gametask_name_emerald4);
        } else {
            sendError( TaskConfigPath.path_gametask_name_emerald4);
        }
        if (c.getString(TaskConfigPath.path_gametask_name_finalbattle) != null) {
            gametask_name_finalbattle = c.getString(TaskConfigPath.path_gametask_name_finalbattle);
        } else {
            sendError( TaskConfigPath.path_gametask_name_finalbattle);
        }







        if (c.getString(TaskConfigPath.path_gametask_mess_finalbattle_chat) != null) {
            gametask_mess_finalbattle_chat = c.getString(TaskConfigPath.path_gametask_mess_finalbattle_chat);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_finalbattle_chat);
        }
        if (c.getString(TaskConfigPath.path_gametask_mess_finalbattle_title) != null) {
            gametask_mess_finalbattle_title = c.getString(TaskConfigPath.path_gametask_mess_finalbattle_title);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_finalbattle_title);
        }
        if (c.getString(TaskConfigPath.path_gametask_mess_finalbattle_subtitle) != null) {
            gametask_mess_finalbattle_subtitle = c.getString(TaskConfigPath.path_gametask_mess_finalbattle_subtitle);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_finalbattle_subtitle);
        }
        if (c.getString(TaskConfigPath.path_gametask_mess_finalbattle_actionbar) != null) {
            gametask_mess_finalbattle_actionbar = c.getString(TaskConfigPath.path_gametask_mess_finalbattle_actionbar);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_finalbattle_actionbar);
        }


        if (c.getString(TaskConfigPath.path_gametask_mess_iron1_chat) != null) {
            gametask_mess_iron1_chat = c.getString(TaskConfigPath.path_gametask_mess_iron1_chat);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_iron1_chat);
        }
        if (c.getString(TaskConfigPath.path_gametask_mess_iron1_title) != null) {
            gametask_mess_iron1_title = c.getString(TaskConfigPath.path_gametask_mess_iron1_title);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_iron1_title);
        }
        if (c.getString(TaskConfigPath.path_gametask_mess_iron1_subtitle) != null) {
            gametask_mess_iron1_subtitle = c.getString(TaskConfigPath.path_gametask_mess_iron1_subtitle);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_iron1_subtitle);
        }
        if (c.getString(TaskConfigPath.path_gametask_mess_iron1_actionbar) != null) {
            gametask_mess_iron1_actionbar = c.getString(TaskConfigPath.path_gametask_mess_iron1_actionbar);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_iron1_actionbar);
        }

        if (c.getString(TaskConfigPath.path_gametask_mess_iron2_chat) != null) {
            gametask_mess_iron2_chat = c.getString(TaskConfigPath.path_gametask_mess_iron2_chat);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_iron2_chat);
        }
        if (c.getString(TaskConfigPath.path_gametask_mess_iron2_title) != null) {
            gametask_mess_iron2_title = c.getString(TaskConfigPath.path_gametask_mess_iron2_title);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_iron2_title);
        }
        if (c.getString(TaskConfigPath.path_gametask_mess_iron2_subtitle) != null) {
            gametask_mess_iron2_subtitle = c.getString(TaskConfigPath.path_gametask_mess_iron2_subtitle);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_iron2_subtitle);
        }
        if (c.getString(TaskConfigPath.path_gametask_mess_iron2_actionbar) != null) {
            gametask_mess_iron2_actionbar = c.getString(TaskConfigPath.path_gametask_mess_iron2_actionbar);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_iron2_actionbar);
        }

        if (c.getString(TaskConfigPath.path_gametask_mess_iron3_chat) != null) {
            gametask_mess_iron3_chat = c.getString(TaskConfigPath.path_gametask_mess_iron3_chat);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_iron3_chat);
        }
        if (c.getString(TaskConfigPath.path_gametask_mess_iron3_title) != null) {
            gametask_mess_iron3_title = c.getString(TaskConfigPath.path_gametask_mess_iron3_title);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_iron3_title);
        }
        if (c.getString(TaskConfigPath.path_gametask_mess_iron3_subtitle) != null) {
            gametask_mess_iron3_subtitle = c.getString(TaskConfigPath.path_gametask_mess_iron3_subtitle);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_iron3_subtitle);
        }
        if (c.getString(TaskConfigPath.path_gametask_mess_iron3_actionbar) != null) {
            gametask_mess_iron3_actionbar = c.getString(TaskConfigPath.path_gametask_mess_iron3_actionbar);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_iron3_actionbar);
        }
        if (c.getString(TaskConfigPath.path_gametask_mess_iron4_chat) != null) {
            gametask_mess_iron4_chat = c.getString(TaskConfigPath.path_gametask_mess_iron4_chat);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_iron4_chat);
        }
        if (c.getString(TaskConfigPath.path_gametask_mess_iron4_title) != null) {
            gametask_mess_iron4_title = c.getString(TaskConfigPath.path_gametask_mess_iron4_title);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_iron4_title);
        }
        if (c.getString(TaskConfigPath.path_gametask_mess_iron4_subtitle) != null) {
            gametask_mess_iron4_subtitle = c.getString(TaskConfigPath.path_gametask_mess_iron4_subtitle);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_iron4_subtitle);
        }
        if (c.getString(TaskConfigPath.path_gametask_mess_iron4_actionbar) != null) {
            gametask_mess_iron4_actionbar = c.getString(TaskConfigPath.path_gametask_mess_iron4_actionbar);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_iron4_actionbar);
        }

        if (c.getString(TaskConfigPath.path_gametask_mess_gold1_chat) != null) {
            gametask_mess_gold1_chat = c.getString(TaskConfigPath.path_gametask_mess_gold1_chat);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_gold1_chat);
        }
        if (c.getString(TaskConfigPath.path_gametask_mess_gold1_title) != null) {
            gametask_mess_gold1_title = c.getString(TaskConfigPath.path_gametask_mess_gold1_title);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_gold1_title);
        }
        if (c.getString(TaskConfigPath.path_gametask_mess_gold1_subtitle) != null) {
            gametask_mess_gold1_subtitle = c.getString(TaskConfigPath.path_gametask_mess_gold1_subtitle);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_gold1_subtitle);
        }
        if (c.getString(TaskConfigPath.path_gametask_mess_gold1_actionbar) != null) {
            gametask_mess_gold1_actionbar = c.getString(TaskConfigPath.path_gametask_mess_gold1_actionbar);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_gold1_actionbar);
        }

        if (c.getString(TaskConfigPath.path_gametask_mess_gold2_chat) != null) {
            gametask_mess_gold2_chat = c.getString(TaskConfigPath.path_gametask_mess_gold2_chat);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_gold2_chat);
        }
        if (c.getString(TaskConfigPath.path_gametask_mess_gold2_title) != null) {
            gametask_mess_gold2_title = c.getString(TaskConfigPath.path_gametask_mess_gold2_title);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_gold2_title);
        }
        if (c.getString(TaskConfigPath.path_gametask_mess_gold2_subtitle) != null) {
            gametask_mess_gold2_subtitle = c.getString(TaskConfigPath.path_gametask_mess_gold2_subtitle);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_gold2_subtitle);
        }
        if (c.getString(TaskConfigPath.path_gametask_mess_gold2_actionbar) != null) {
            gametask_mess_gold2_actionbar = c.getString(TaskConfigPath.path_gametask_mess_gold2_actionbar);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_gold2_actionbar);
        }

        if (c.getString(TaskConfigPath.path_gametask_mess_gold3_chat) != null) {
            gametask_mess_gold3_chat = c.getString(TaskConfigPath.path_gametask_mess_gold3_chat);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_gold3_chat);
        }
        if (c.getString(TaskConfigPath.path_gametask_mess_gold3_title) != null) {
            gametask_mess_gold3_title = c.getString(TaskConfigPath.path_gametask_mess_gold3_title);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_gold3_title);
        }
        if (c.getString(TaskConfigPath.path_gametask_mess_gold3_subtitle) != null) {
            gametask_mess_gold3_subtitle = c.getString(TaskConfigPath.path_gametask_mess_gold3_subtitle);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_gold3_subtitle);
        }
        if (c.getString(TaskConfigPath.path_gametask_mess_gold3_actionbar) != null) {
            gametask_mess_gold3_actionbar = c.getString(TaskConfigPath.path_gametask_mess_gold3_actionbar);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_gold3_actionbar);
        }
        if (c.getString(TaskConfigPath.path_gametask_mess_gold4_chat) != null) {
            gametask_mess_gold4_chat = c.getString(TaskConfigPath.path_gametask_mess_gold4_chat);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_gold4_chat);
        }
        if (c.getString(TaskConfigPath.path_gametask_mess_gold4_title) != null) {
            gametask_mess_gold4_title = c.getString(TaskConfigPath.path_gametask_mess_gold4_title);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_gold4_title);
        }
        if (c.getString(TaskConfigPath.path_gametask_mess_gold4_subtitle) != null) {
            gametask_mess_gold4_subtitle = c.getString(TaskConfigPath.path_gametask_mess_gold4_subtitle);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_gold4_subtitle);
        }
        if (c.getString(TaskConfigPath.path_gametask_mess_gold4_actionbar) != null) {
            gametask_mess_gold4_actionbar = c.getString(TaskConfigPath.path_gametask_mess_gold4_actionbar);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_gold4_actionbar);
        }

        if (c.getString(TaskConfigPath.path_gametask_mess_diamond1_chat) != null) {
            gametask_mess_diamond1_chat = c.getString(TaskConfigPath.path_gametask_mess_diamond1_chat);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_diamond1_chat);
        }
        if (c.getString(TaskConfigPath.path_gametask_mess_diamond1_title) != null) {
            gametask_mess_diamond1_title = c.getString(TaskConfigPath.path_gametask_mess_diamond1_title);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_diamond1_title);
        }
        if (c.getString(TaskConfigPath.path_gametask_mess_diamond1_subtitle) != null) {
            gametask_mess_diamond1_subtitle = c.getString(TaskConfigPath.path_gametask_mess_diamond1_subtitle);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_diamond1_subtitle);
        }
        if (c.getString(TaskConfigPath.path_gametask_mess_diamond1_actionbar) != null) {
            gametask_mess_diamond1_actionbar = c.getString(TaskConfigPath.path_gametask_mess_diamond1_actionbar);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_diamond1_actionbar);
        }

        if (c.getString(TaskConfigPath.path_gametask_mess_diamond2_chat) != null) {
            gametask_mess_diamond2_chat = c.getString(TaskConfigPath.path_gametask_mess_diamond2_chat);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_diamond2_chat);
        }
        if (c.getString(TaskConfigPath.path_gametask_mess_diamond2_title) != null) {
            gametask_mess_diamond2_title = c.getString(TaskConfigPath.path_gametask_mess_diamond2_title);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_diamond2_title);
        }
        if (c.getString(TaskConfigPath.path_gametask_mess_diamond2_subtitle) != null) {
            gametask_mess_diamond2_subtitle = c.getString(TaskConfigPath.path_gametask_mess_diamond2_subtitle);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_diamond2_subtitle);
        }
        if (c.getString(TaskConfigPath.path_gametask_mess_diamond2_actionbar) != null) {
            gametask_mess_diamond2_actionbar = c.getString(TaskConfigPath.path_gametask_mess_diamond2_actionbar);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_diamond2_actionbar);
        }

        if (c.getString(TaskConfigPath.path_gametask_mess_diamond3_chat) != null) {
            gametask_mess_diamond3_chat = c.getString(TaskConfigPath.path_gametask_mess_diamond3_chat);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_diamond3_chat);
        }
        if (c.getString(TaskConfigPath.path_gametask_mess_diamond3_title) != null) {
            gametask_mess_diamond3_title = c.getString(TaskConfigPath.path_gametask_mess_diamond3_title);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_diamond3_title);
        }
        if (c.getString(TaskConfigPath.path_gametask_mess_diamond3_subtitle) != null) {
            gametask_mess_diamond3_subtitle = c.getString(TaskConfigPath.path_gametask_mess_diamond3_subtitle);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_diamond3_subtitle);
        }
        if (c.getString(TaskConfigPath.path_gametask_mess_diamond3_actionbar) != null) {
            gametask_mess_diamond3_actionbar = c.getString(TaskConfigPath.path_gametask_mess_diamond3_actionbar);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_diamond3_actionbar);
        }
        if (c.getString(TaskConfigPath.path_gametask_mess_diamond4_chat) != null) {
            gametask_mess_diamond4_chat = c.getString(TaskConfigPath.path_gametask_mess_diamond4_chat);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_diamond4_chat);
        }
        if (c.getString(TaskConfigPath.path_gametask_mess_diamond4_title) != null) {
            gametask_mess_diamond4_title = c.getString(TaskConfigPath.path_gametask_mess_diamond4_title);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_diamond4_title);
        }
        if (c.getString(TaskConfigPath.path_gametask_mess_diamond4_subtitle) != null) {
            gametask_mess_diamond4_subtitle = c.getString(TaskConfigPath.path_gametask_mess_diamond4_subtitle);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_diamond4_subtitle);
        }
        if (c.getString(TaskConfigPath.path_gametask_mess_diamond4_actionbar) != null) {
            gametask_mess_diamond4_actionbar = c.getString(TaskConfigPath.path_gametask_mess_diamond4_actionbar);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_diamond4_actionbar);
        }


        if (c.getString(TaskConfigPath.path_gametask_mess_emerald1_chat) != null) {
            gametask_mess_emerald1_chat = c.getString(TaskConfigPath.path_gametask_mess_emerald1_chat);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_emerald1_chat);
        }
        if (c.getString(TaskConfigPath.path_gametask_mess_emerald1_title) != null) {
            gametask_mess_emerald1_title = c.getString(TaskConfigPath.path_gametask_mess_emerald1_title);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_emerald1_title);
        }
        if (c.getString(TaskConfigPath.path_gametask_mess_emerald1_subtitle) != null) {
            gametask_mess_emerald1_subtitle = c.getString(TaskConfigPath.path_gametask_mess_emerald1_subtitle);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_emerald1_subtitle);
        }
        if (c.getString(TaskConfigPath.path_gametask_mess_emerald1_actionbar) != null) {
            gametask_mess_emerald1_actionbar = c.getString(TaskConfigPath.path_gametask_mess_emerald1_actionbar);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_emerald1_actionbar);
        }

        if (c.getString(TaskConfigPath.path_gametask_mess_emerald2_chat) != null) {
            gametask_mess_emerald2_chat = c.getString(TaskConfigPath.path_gametask_mess_emerald2_chat);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_emerald2_chat);
        }
        if (c.getString(TaskConfigPath.path_gametask_mess_emerald2_title) != null) {
            gametask_mess_emerald2_title = c.getString(TaskConfigPath.path_gametask_mess_emerald2_title);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_emerald2_title);
        }
        if (c.getString(TaskConfigPath.path_gametask_mess_emerald2_subtitle) != null) {
            gametask_mess_emerald2_subtitle = c.getString(TaskConfigPath.path_gametask_mess_emerald2_subtitle);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_emerald2_subtitle);
        }
        if (c.getString(TaskConfigPath.path_gametask_mess_emerald2_actionbar) != null) {
            gametask_mess_emerald2_actionbar = c.getString(TaskConfigPath.path_gametask_mess_emerald2_actionbar);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_emerald2_actionbar);
        }

        if (c.getString(TaskConfigPath.path_gametask_mess_emerald3_chat) != null) {
            gametask_mess_emerald3_chat = c.getString(TaskConfigPath.path_gametask_mess_emerald3_chat);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_emerald3_chat);
        }
        if (c.getString(TaskConfigPath.path_gametask_mess_emerald3_title) != null) {
            gametask_mess_emerald3_title = c.getString(TaskConfigPath.path_gametask_mess_emerald3_title);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_emerald3_title);
        }
        if (c.getString(TaskConfigPath.path_gametask_mess_emerald3_subtitle) != null) {
            gametask_mess_emerald3_subtitle = c.getString(TaskConfigPath.path_gametask_mess_emerald3_subtitle);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_emerald3_subtitle);
        }
        if (c.getString(TaskConfigPath.path_gametask_mess_emerald3_actionbar) != null) {
            gametask_mess_emerald3_actionbar = c.getString(TaskConfigPath.path_gametask_mess_emerald3_actionbar);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_emerald3_actionbar);
        }
        if (c.getString(TaskConfigPath.path_gametask_mess_emerald4_chat) != null) {
            gametask_mess_emerald4_chat = c.getString(TaskConfigPath.path_gametask_mess_emerald4_chat);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_emerald4_chat);
        }
        if (c.getString(TaskConfigPath.path_gametask_mess_emerald4_title) != null) {
            gametask_mess_emerald4_title = c.getString(TaskConfigPath.path_gametask_mess_emerald4_title);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_emerald4_title);
        }
        if (c.getString(TaskConfigPath.path_gametask_mess_emerald4_subtitle) != null) {
            gametask_mess_emerald4_subtitle = c.getString(TaskConfigPath.path_gametask_mess_emerald4_subtitle);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_emerald4_subtitle);
        }
        if (c.getString(TaskConfigPath.path_gametask_mess_emerald4_actionbar) != null) {
            gametask_mess_emerald4_actionbar = c.getString(TaskConfigPath.path_gametask_mess_emerald4_actionbar);
        } else {
            sendError( TaskConfigPath.path_gametask_mess_emerald4_actionbar);
        }



        if (c.getString(TaskConfigPath.path_gametask_finalbattle_boundaries_warnidis) != null) {
            gametask_finalbattle_boundaries_warnidis = c.getInt(TaskConfigPath.path_gametask_finalbattle_boundaries_warnidis);
        } else {
            sendError( TaskConfigPath.path_gametask_finalbattle_boundaries_warnidis);
        }


        if (c.getString(TaskConfigPath.path_gametask_finalbattle_boundaries_damagebuffer) != null) {
            gametask_finalbattle_boundaries_damagebuffer = c.getDouble(TaskConfigPath.path_gametask_finalbattle_boundaries_damagebuffer);
        } else {
            sendError( TaskConfigPath.path_gametask_finalbattle_boundaries_damagebuffer);
        }

        if (c.getString(TaskConfigPath.path_gametask_finalbattle_boundaries_damage) != null) {
            gametask_finalbattle_boundaries_damage = c.getDouble(TaskConfigPath.path_gametask_finalbattle_boundaries_damage);
        } else {
            sendError( TaskConfigPath.path_gametask_finalbattle_boundaries_damage);
        }
        l("<" + name + "> " + finishLoadConfig);

    }
    private static void sendError(String path){
        er(name, path, reason);
    }
}