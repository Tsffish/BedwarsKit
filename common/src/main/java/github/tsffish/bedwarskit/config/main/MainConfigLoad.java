package github.tsffish.bedwarskit.config.main;

import github.tsffish.bedwarskit.BedwarsKit;
import github.tsffish.bedwarskit.config.kit.KitConfigLoad;
import github.tsffish.bedwarskit.config.lang.LangConfigLoad;
import github.tsffish.bedwarskit.config.task.TaskConfigLoad;
import github.tsffish.bedwarskit.listener.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import static github.tsffish.bedwarskit.BedwarsKit.spigotId;
import static github.tsffish.bedwarskit.config.main.MainConfigHandler.*;
import static github.tsffish.bedwarskit.config.misc.ErrorConfigHandler.er;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;
import static github.tsffish.bedwarskit.util.misc.MessSender.l;
import static github.tsffish.bedwarskit.util.misc.StringMgr.*;
import static github.tsffish.bedwarskit.util.misc.update.StartCheck.checkUpdate;
import static github.tsffish.bedwarskit.util.teamshop.ShopMenu.loadLevelUpInv;

public class MainConfigLoad {
    private static final String name = "MainConfigLoad";
    private static final String reason = vauleIsNull;
    private static final BedwarsKit plugin = BedwarsKit.getInstance();
    private static void regListener(){
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new PluginDisable(), plugin);

        pluginManager.registerEvents(new RelBreakBed(), plugin);
        pluginManager.registerEvents(new RelBreakCorrect(), plugin);

        pluginManager.registerEvents(new RelClickInventory(), plugin);

        pluginManager.registerEvents(new RelPlayerFoodLevel(), plugin);

        pluginManager.registerEvents(new RelGameOver(), plugin);

        pluginManager.registerEvents(new RelGameStarted(), plugin);

        pluginManager.registerEvents(new RelKillPlayer(),plugin);
        pluginManager.registerEvents(new RelBreakItem(), plugin);

        pluginManager.registerEvents(new RelPlaceCorrect(), plugin);
        pluginManager.registerEvents(new RelPlayerDeath(), plugin);

        pluginManager.registerEvents(new RelPlayerDrop(), plugin);
        pluginManager.registerEvents(new RelPlayerJoin(), plugin);

        pluginManager.registerEvents(new RelPlayerLeave(), plugin);
        pluginManager.registerEvents(new RelPlayerMove(), plugin);
        pluginManager.registerEvents(new RelPlayerOpenShop(),plugin);

        pluginManager.registerEvents(new RelPlayerTeleport(), plugin);

        pluginManager.registerEvents(new RelPlayerClick(), plugin);
        pluginManager.registerEvents(new RelPlayerDamage(), plugin);
        pluginManager.registerEvents(new RelPlayerGameMode(), plugin);

        pluginManager.registerEvents(new RelPickupItem(), plugin);
    }
    private static void sendError(String path){
        er(name, path, reason);
    }
    public static void loadMainConfig(CommandSender executer, boolean firstload) {

        File file = new File(plugin.getDataFolder(), "config.yml");

        if (!file.exists()) {
                plugin.saveResource("config.yml", false);
        }

        FileConfiguration c = YamlConfiguration.loadConfiguration(file);

        if (c.getString(MainConfigPath.path_messreloadnow) != null) {
            messreloadnow = c.getString(MainConfigPath.path_messreloadnow);
        } else {
            messreloadnow = t("&b" + pluginName + " &7>> &eReloading configuration file");
            sendError(MainConfigPath.path_messreloadnow);
        }

        if (c.getString(MainConfigPath.path_messreloadsucc) != null) {
            messreloadsucc = c.getString(MainConfigPath.path_messreloadsucc);
        } else {
            messreloadsucc = t("&b" + pluginName + " &7>> &aSuccessfully reloaded configuration file");
            sendError(MainConfigPath.path_messreloadsucc);
        }

        if (!firstload) {
            if (executer != null) {
                executer.sendMessage(t(messreloadnow));
            }
        }

        if (firstload) {
            if (c.getString(MainConfigPath.path_update_checker) != null) {
                boolean update_checker = c.getBoolean(MainConfigPath.path_update_checker);
                if (update_checker) {
                    checkUpdate(spigotId());
                }
            } else {
                sendError(MainConfigPath.path_update_checker);
                checkUpdate(spigotId());
            }
        }

        if (c.getString(MainConfigPath.path_update_reportOp) != null) {
            update_reportOp = c.getBoolean(MainConfigPath.path_update_reportOp);
        } else {
            sendError(MainConfigPath.path_update_reportOp);
        }

        if (c.getString(MainConfigPath.path_grassPaneWalk) != null) {
            grassPaneWalk = c.getBoolean(MainConfigPath.path_grassPaneWalk);
        } else {
            sendError(MainConfigPath.path_grassPaneWalk);
        }

        if (c.getString(MainConfigPath.path_noHunger) != null) {
            noHunger = c.getBoolean(MainConfigPath.path_noHunger);
        } else {
            sendError(MainConfigPath.path_noHunger);
        }

        if (c.getString(MainConfigPath.path_noPearlDamage) != null) {
            noPearlDamage = c.getBoolean(MainConfigPath.path_noPearlDamage);
        } else {
            sendError(MainConfigPath.path_noPearlDamage);
        }

        if (c.getString(MainConfigPath.path_killfb_oneHealthKill) != null) {
            killfb_oneHealthKill = c.getBoolean(MainConfigPath.path_killfb_oneHealthKill);


        } else {
            sendError(MainConfigPath.path_killfb_oneHealthKill);
        }
        if (c.getString(MainConfigPath.path_killfb_oneHealthKill_itemType) != null) {
            killfb_oneHealthKill_itemType = Material.getMaterial(
                    c.getString(MainConfigPath.path_killfb_oneHealthKill_itemType));
        } else {
            sendError(MainConfigPath.path_killfb_oneHealthKill_itemType);
        }
        if (c.getString(MainConfigPath.path_killfb_oneHealthKill_itemName) != null) {
            killfb_oneHealthKill_itemName = c.getString(MainConfigPath.path_killfb_oneHealthKill_itemName);
        } else {
            sendError(MainConfigPath.path_killfb_oneHealthKill_itemName);
        }


        if (c.getString(MainConfigPath.path_noPearlDamage_TPSound) != null) {
            noPearlDamage_TPSound = c.getBoolean(MainConfigPath.path_noPearlDamage_TPSound);
        } else {
            sendError( MainConfigPath.path_noPearlDamage_TPSound);
        }

        if (c.getString(MainConfigPath.path_breakBedCheck) != null) {
            breakBedCheck = c.getBoolean(MainConfigPath.path_breakBedCheck);
        } else {
            sendError( MainConfigPath.path_breakBedCheck);
        }

        if (c.getString(MainConfigPath.path_levelupShop) != null) {
            levelupShop = c.getBoolean(MainConfigPath.path_levelupShop);
        } else {
            sendError( MainConfigPath.path_levelupShop);
        }

        if (c.getString(MainConfigPath.path_deathGameMode) != null) {
            deathGameMode = c.getBoolean(MainConfigPath.path_deathGameMode);
        } else {
            sendError( MainConfigPath.path_deathGameMode);
        }

        if (c.getString(MainConfigPath.path_startKitCompass) != null) {
            startKitCompass = c.getBoolean(MainConfigPath.path_startKitCompass);
        } else {
            sendError( MainConfigPath.path_startKitCompass);
        }

        if (c.getString(MainConfigPath.path_customScoreboard) != null) {
            customScoreboard = c.getBoolean(MainConfigPath.path_customScoreboard);
        } else {
            sendError( MainConfigPath.path_customScoreboard);
        }

        if (c.getString(MainConfigPath.path_antiDrop) != null) {
            antiDrop = c.getBoolean(MainConfigPath.path_antiDrop);
        } else {
            sendError( MainConfigPath.path_antiDrop);
        }
        if (c.getString(MainConfigPath.path_NoItemBreak) != null) {
            NoItemBreak = c.getBoolean(MainConfigPath.path_NoItemBreak);
        } else {
            sendError( MainConfigPath.path_NoItemBreak);
        }


        if (c.getString(MainConfigPath.path_kill_res) != null) {
            kill_res = c.getBoolean(MainConfigPath.path_kill_res);
        } else {
            sendError( MainConfigPath.path_kill_res);
        }

        if (c.getString(MainConfigPath.path_meanBedwars) != null) {
            meanBedwars = c.getString(MainConfigPath.path_meanBedwars);
        } else {
            sendError( MainConfigPath.path_meanBedwars);
        }
        if (c.getString(MainConfigPath.path_mean2v2Mode) != null) {
            mean2v2Mode = c.getString(MainConfigPath.path_mean2v2Mode);
        } else {
            sendError( MainConfigPath.path_mean2v2Mode);
        }
        if (c.getString(MainConfigPath.path_mean4v4Mode) != null) {
            mean4v4Mode = c.getString(MainConfigPath.path_mean4v4Mode);
        } else {
            sendError( MainConfigPath.path_mean4v4Mode);
        }

        if (c.getString(MainConfigPath.path_breakTitle) != null) {
            breakTitle = c.getBoolean(MainConfigPath.path_breakTitle);
        } else {
            sendError( MainConfigPath.path_breakTitle);
        }

        if (c.getString(MainConfigPath.path_levelupShopOpenMode) != null) {
            levelupShopOpenMode = c.getString(MainConfigPath.path_levelupShopOpenMode);
        } else {
            sendError( MainConfigPath.path_levelupShopOpenMode);
        }

        if (c.getString(MainConfigPath.path_levelupShopOpenModeEntityName) != null) {
            levelupShopOpenModeEntityName = c.getString(MainConfigPath.path_levelupShopOpenModeEntityName);
        } else {
            sendError( MainConfigPath.path_levelupShopOpenModeEntityName);
        }

        if (c.getString(MainConfigPath.path_ScoreBoard2v2Title) != null) {
            ScoreBoard2v2Title = c.getString(MainConfigPath.path_ScoreBoard2v2Title);
        } else {
            sendError( MainConfigPath.path_ScoreBoard2v2Title);
        }

        if (c.getString(MainConfigPath.path_ScoreBoard2v2Line01) != null) {
            ScoreBoard2v2Line01 = c.getString(MainConfigPath.path_ScoreBoard2v2Line01);
        } else {
            sendError( MainConfigPath.path_ScoreBoard2v2Line01);
        }

        if (c.getString(MainConfigPath.path_ScoreBoard2v2Line02) != null) {
            ScoreBoard2v2Line02 = c.getString(MainConfigPath.path_ScoreBoard2v2Line02);
        } else {
            sendError( MainConfigPath.path_ScoreBoard2v2Line02);
        }
        if (c.getString(MainConfigPath.path_ScoreBoard2v2Line03) != null) {
            ScoreBoard2v2Line03 = c.getString(MainConfigPath.path_ScoreBoard2v2Line03);
        } else {
            sendError( MainConfigPath.path_ScoreBoard2v2Line03);
        }
        if (c.getString(MainConfigPath.path_ScoreBoard2v2Line04) != null) {
            ScoreBoard2v2Line04 = c.getString(MainConfigPath.path_ScoreBoard2v2Line04);
        } else {
            sendError( MainConfigPath.path_ScoreBoard2v2Line04);
        }
        if (c.getString(MainConfigPath.path_ScoreBoard2v2Line05) != null) {
            ScoreBoard2v2Line05 = c.getString(MainConfigPath.path_ScoreBoard2v2Line05);
        } else {
            sendError( MainConfigPath.path_ScoreBoard2v2Line05);
        }
        if (c.getString(MainConfigPath.path_ScoreBoard2v2Line06) != null) {
            ScoreBoard2v2Line06 = c.getString(MainConfigPath.path_ScoreBoard2v2Line06);
        } else {
            sendError( MainConfigPath.path_ScoreBoard2v2Line06);
        }
        if (c.getString(MainConfigPath.path_ScoreBoard2v2Line07) != null) {
            ScoreBoard2v2Line07 = c.getString(MainConfigPath.path_ScoreBoard2v2Line07);
        } else {
            sendError( MainConfigPath.path_ScoreBoard2v2Line07);
        }
        if (c.getString(MainConfigPath.path_ScoreBoard2v2Line08) != null) {
            ScoreBoard2v2Line08 = c.getString(MainConfigPath.path_ScoreBoard2v2Line08);
        } else {
            sendError( MainConfigPath.path_ScoreBoard2v2Line08);
        }
        if (c.getString(MainConfigPath.path_ScoreBoard2v2Line09) != null) {
            ScoreBoard2v2Line09 = c.getString(MainConfigPath.path_ScoreBoard2v2Line09);
        } else {
            sendError( MainConfigPath.path_ScoreBoard2v2Line09);
        }
        if (c.getString(MainConfigPath.path_ScoreBoard2v2Line10) != null) {
            ScoreBoard2v2Line10 = c.getString(MainConfigPath.path_ScoreBoard2v2Line10);
        } else {
            sendError( MainConfigPath.path_ScoreBoard2v2Line10);
        }
        if (c.getString(MainConfigPath.path_ScoreBoard2v2Line11) != null) {
            ScoreBoard2v2Line11 = c.getString(MainConfigPath.path_ScoreBoard2v2Line11);
        } else {
            sendError( MainConfigPath.path_ScoreBoard2v2Line11);
        }
        if (c.getString(MainConfigPath.path_ScoreBoard2v2Line12) != null) {
            ScoreBoard2v2Line12 = c.getString(MainConfigPath.path_ScoreBoard2v2Line12);
        } else {
            sendError( MainConfigPath.path_ScoreBoard2v2Line12);
        }
        if (c.getString(MainConfigPath.path_ScoreBoard2v2Line13) != null) {
            ScoreBoard2v2Line13 = c.getString(MainConfigPath.path_ScoreBoard2v2Line13);
        } else {
            sendError( MainConfigPath.path_ScoreBoard2v2Line13);
        }
        if (c.getString(MainConfigPath.path_ScoreBoard2v2Line14) != null) {
            ScoreBoard2v2Line14 = c.getString(MainConfigPath.path_ScoreBoard2v2Line14);
        } else {
            sendError( MainConfigPath.path_ScoreBoard2v2Line14);
        }
        if (c.getString(MainConfigPath.path_ScoreBoard2v2Line15) != null) {
            ScoreBoard2v2Line15 = c.getString(MainConfigPath.path_ScoreBoard2v2Line15);
        } else {
            sendError( MainConfigPath.path_ScoreBoard2v2Line15);
        }
        if (c.getString(MainConfigPath.path_ScoreBoard2v2Line10) != null) {
            ScoreBoard2v2Line16 = c.getString(MainConfigPath.path_ScoreBoard2v2Line16);
        } else {
            sendError( MainConfigPath.path_ScoreBoard2v2Line16);
        }


        if (c.getString(MainConfigPath.path_ScoreBoard4v4Title) != null) {
            ScoreBoard4v4Title = c.getString(MainConfigPath.path_ScoreBoard4v4Title);
        } else {
            sendError( MainConfigPath.path_ScoreBoard4v4Title);
        }

        if (c.getString(MainConfigPath.path_ScoreBoard4v4Line01) != null) {
            ScoreBoard4v4Line01 = c.getString(MainConfigPath.path_ScoreBoard4v4Line01);
        } else {
            sendError( MainConfigPath.path_ScoreBoard4v4Line01);
        }

        if (c.getString(MainConfigPath.path_ScoreBoard4v4Line02) != null) {
            ScoreBoard4v4Line02 = c.getString(MainConfigPath.path_ScoreBoard4v4Line02);
        } else {
            sendError(MainConfigPath.path_ScoreBoard4v4Line02);
        }
        if (c.getString(MainConfigPath.path_ScoreBoard4v4Line03) != null) {
            ScoreBoard4v4Line03 = c.getString(MainConfigPath.path_ScoreBoard4v4Line03);
        } else {
            sendError( MainConfigPath.path_ScoreBoard4v4Line03);
        }
        if (c.getString(MainConfigPath.path_ScoreBoard4v4Line04) != null) {
            ScoreBoard4v4Line04 = c.getString(MainConfigPath.path_ScoreBoard4v4Line04);
        } else {
            sendError( MainConfigPath.path_ScoreBoard4v4Line04);
        }
        if (c.getString(MainConfigPath.path_ScoreBoard4v4Line05) != null) {
            ScoreBoard4v4Line05 = c.getString(MainConfigPath.path_ScoreBoard4v4Line05);
        } else {
            sendError( MainConfigPath.path_ScoreBoard4v4Line05);
        }
        if (c.getString(MainConfigPath.path_ScoreBoard4v4Line06) != null) {
            ScoreBoard4v4Line06 = c.getString(MainConfigPath.path_ScoreBoard4v4Line06);
        } else {
            sendError( MainConfigPath.path_ScoreBoard4v4Line06);
        }
        if (c.getString(MainConfigPath.path_ScoreBoard4v4Line07) != null) {
            ScoreBoard4v4Line07 = c.getString(MainConfigPath.path_ScoreBoard4v4Line07);
        } else {
            sendError( MainConfigPath.path_ScoreBoard4v4Line07);
        }
        if (c.getString(MainConfigPath.path_ScoreBoard4v4Line08) != null) {
            ScoreBoard4v4Line08 = c.getString(MainConfigPath.path_ScoreBoard4v4Line08);
        } else {
            sendError( MainConfigPath.path_ScoreBoard4v4Line08);
        }
        if (c.getString(MainConfigPath.path_ScoreBoard4v4Line09) != null) {
            ScoreBoard4v4Line09 = c.getString(MainConfigPath.path_ScoreBoard4v4Line09);
        } else {
            sendError( MainConfigPath.path_ScoreBoard4v4Line09);
        }
        if (c.getString(MainConfigPath.path_ScoreBoard4v4Line10) != null) {
            ScoreBoard4v4Line10 = c.getString(MainConfigPath.path_ScoreBoard4v4Line10);
        } else {
            sendError( MainConfigPath.path_ScoreBoard4v4Line10);
        }
        if (c.getString(MainConfigPath.path_ScoreBoard4v4Line11) != null) {
            ScoreBoard4v4Line11 = c.getString(MainConfigPath.path_ScoreBoard4v4Line11);
        } else {
            sendError( MainConfigPath.path_ScoreBoard4v4Line11);
        }
        if (c.getString(MainConfigPath.path_ScoreBoard4v4Line12) != null) {
            ScoreBoard4v4Line12 = c.getString(MainConfigPath.path_ScoreBoard4v4Line12);
        } else {
            sendError( MainConfigPath.path_ScoreBoard4v4Line12);
        }
        if (c.getString(MainConfigPath.path_ScoreBoard4v4Line13) != null) {
            ScoreBoard4v4Line13 = c.getString(MainConfigPath.path_ScoreBoard4v4Line13);
        } else {
            sendError( MainConfigPath.path_ScoreBoard4v4Line13);
        }
        if (c.getString(MainConfigPath.path_ScoreBoard4v4Line14) != null) {
            ScoreBoard4v4Line14 = c.getString(MainConfigPath.path_ScoreBoard4v4Line14);
        } else {
            sendError( MainConfigPath.path_ScoreBoard4v4Line14);
        }
        if (c.getString(MainConfigPath.path_ScoreBoard4v4Line15) != null) {
            ScoreBoard4v4Line15 = c.getString(MainConfigPath.path_ScoreBoard4v4Line15);
        } else {
            sendError( MainConfigPath.path_ScoreBoard4v4Line15);
        }
        if (c.getString(MainConfigPath.path_ScoreBoard4v4Line10) != null) {
            ScoreBoard4v4Line16 = c.getString(MainConfigPath.path_ScoreBoard4v4Line16);
        } else {
            sendError( MainConfigPath.path_ScoreBoard4v4Line16);
        }




        if (c.getString(MainConfigPath.path_cleanBottle) != null) {
            cleanBottle = c.getBoolean(MainConfigPath.path_cleanBottle);
        } else {
            sendError( MainConfigPath.path_cleanBottle);
        }


        if (c.getString(MainConfigPath.path_chainPrice) != null) {
            chainPrice = c.getInt(MainConfigPath.path_chainPrice);
        } else {
            sendError( MainConfigPath.path_chainPrice);
        }
        if (c.getString(MainConfigPath.path_ironPrice) != null) {
            ironPrice = c.getInt(MainConfigPath.path_ironPrice);
        } else {
            sendError( MainConfigPath.path_ironPrice);
        }
        if (c.getString(MainConfigPath.path_diamondPrice) != null) {
            diamondPrice = c.getInt(MainConfigPath.path_diamondPrice);
        } else {
            sendError( MainConfigPath.path_diamondPrice);
        }


        if (c.getString(MainConfigPath.path_sharp1Cost2v2) != null) {
            sharp1Cost2v2 = c.getInt(MainConfigPath.path_sharp1Cost2v2);
        } else {
            sendError( MainConfigPath.path_sharp1Cost2v2);
        }

        if (c.getString(MainConfigPath.path_sharp2Cost2v2) != null) {
            sharp2Cost2v2 = c.getInt(MainConfigPath.path_sharp2Cost2v2);
        } else {
            sendError(MainConfigPath.path_sharp2Cost2v2);
        }
        if (c.getString(MainConfigPath.path_sharp3Cost2v2) != null) {
            sharp3Cost2v2 = c.getInt(MainConfigPath.path_sharp3Cost2v2);
        } else {
            sendError(MainConfigPath.path_sharp3Cost2v2);
        }
        if (c.getString(MainConfigPath.path_sharp4Cost2v2) != null) {
            sharp4Cost2v2 = c.getInt(MainConfigPath.path_sharp4Cost2v2);
        } else {
            sendError(MainConfigPath.path_sharp4Cost2v2);
        }

        if (c.getString(MainConfigPath.path_sharp1Cost4v4) != null) {
            sharp1Cost4v4 = c.getInt(MainConfigPath.path_sharp1Cost4v4);
        } else {
            sendError( MainConfigPath.path_sharp1Cost4v4);
        }

        if (c.getString(MainConfigPath.path_sharp2Cost4v4) != null) {
            sharp2Cost4v4 = c.getInt(MainConfigPath.path_sharp2Cost4v4);
        } else {
            sendError(MainConfigPath.path_sharp2Cost4v4);
        }
        if (c.getString(MainConfigPath.path_sharp3Cost4v4) != null) {
            sharp3Cost4v4 = c.getInt(MainConfigPath.path_sharp3Cost4v4);
        } else {
            sendError(MainConfigPath.path_sharp3Cost4v4);
        }
        if (c.getString(MainConfigPath.path_sharp4Cost4v4) != null) {
            sharp4Cost4v4 = c.getInt(MainConfigPath.path_sharp4Cost4v4);
        } else {
            sendError(MainConfigPath.path_sharp4Cost4v4);
        }

        if (c.getString(MainConfigPath.path_respawnDelay) != null) {
            respawnDelay = c.getInt(MainConfigPath.path_respawnDelay);
        } else {
            sendError( MainConfigPath.path_respawnDelay);
        }

        if (c.getString(MainConfigPath.path_maxFoodLevel) != null) {
            maxFoodLevel = c.getInt(MainConfigPath.path_maxFoodLevel);
        } else {
            sendError( MainConfigPath.path_maxFoodLevel);
        }

        if (c.getString(MainConfigPath.path_prot1Cost2v2) != null) {
            prot1Cost2v2 = c.getInt(MainConfigPath.path_prot1Cost2v2);
        } else {
            sendError( MainConfigPath.path_prot1Cost2v2);
        }

        if (c.getString(MainConfigPath.path_prot2Cost2v2) != null) {
            prot2Cost2v2 = c.getInt(MainConfigPath.path_prot2Cost2v2);
        } else {
            sendError( MainConfigPath.path_prot2Cost2v2);
        }

        if (c.getString(MainConfigPath.path_prot3Cost2v2) != null) {
            prot3Cost2v2 = c.getInt(MainConfigPath.path_prot3Cost2v2);
        } else {
            sendError( MainConfigPath.path_prot3Cost2v2);
        }

        if (c.getString(MainConfigPath.path_prot4Cost2v2) != null) {
            prot4Cost2v2 = c.getInt(MainConfigPath.path_prot4Cost2v2);
        } else {
            sendError( MainConfigPath.path_prot4Cost2v2);
        }

        if (c.getString(MainConfigPath.path_sharp1Cost4v4) != null) {
            sharp1Cost4v4 = c.getInt(MainConfigPath.path_sharp1Cost4v4);
        } else {
            sendError( MainConfigPath.path_sharp1Cost4v4);
        }

        if (c.getString(MainConfigPath.path_prot1Cost4v4) != null) {
            prot1Cost4v4 = c.getInt(MainConfigPath.path_prot1Cost4v4);
        } else {
            sendError( MainConfigPath.path_prot1Cost4v4);
        }

        if (c.getString(MainConfigPath.path_prot2Cost4v4) != null) {
            prot2Cost4v4 = c.getInt(MainConfigPath.path_prot2Cost4v4);
        } else {
            sendError( MainConfigPath.path_prot2Cost4v4);
        }

        if (c.getString(MainConfigPath.path_prot3Cost4v4) != null) {
            prot3Cost4v4 = c.getInt(MainConfigPath.path_prot3Cost4v4);
        } else {
            sendError( MainConfigPath.path_prot3Cost4v4);
        }

        if (c.getString(MainConfigPath.path_prot4Cost4v4) != null) {
            prot4Cost4v4 = c.getInt(MainConfigPath.path_prot4Cost4v4);
        } else {
            sendError( MainConfigPath.path_prot4Cost4v4);
        }

        if (c.getString(MainConfigPath.path_tpDis) != null) {
            tpDis = c.getDouble(MainConfigPath.path_tpDis);
        } else {
            sendError( MainConfigPath.path_tpDis);
        }

        if (c.getString(MainConfigPath.path_levelupShopDelayOpen) != null) {
            levelupShopDelayOpen = c.getBoolean(MainConfigPath.path_levelupShopDelayOpen);
        } else {
            sendError( MainConfigPath.path_levelupShopDelayOpen);
        }


        if (c.getString(MainConfigPath.path_LevelupItemType) != null) {
            LevelupItemType = Material.getMaterial(c.getString(MainConfigPath.path_LevelupItemType).toUpperCase());
        } else {

            sendError( MainConfigPath.path_LevelupItemType);
        }

        if (c.getString(MainConfigPath.path_chainPriceType) != null) {
            chainPriceType = Material.getMaterial(c.getString(MainConfigPath.path_chainPriceType));
        } else {
            sendError( MainConfigPath.path_chainPriceType);
        }
        if (c.getString(MainConfigPath.path_ironPriceType) != null) {
            ironPriceType = Material.getMaterial(c.getString(MainConfigPath.path_ironPriceType));
        } else {
            sendError( MainConfigPath.path_ironPriceType);
        }
        if (c.getString(MainConfigPath.path_diamondPriceType) != null) {
            diamondPriceType = Material.getMaterial(c.getString(MainConfigPath.path_diamondPriceType));
        } else {
            sendError( MainConfigPath.path_diamondPriceType);
        }

        if (c.getString(MainConfigPath.path_upToChainArmor) != null) {
            upToChainArmor = Material.getMaterial(c.getString(MainConfigPath.path_upToChainArmor));
        } else {
            sendError( MainConfigPath.path_upToChainArmor);
        }
        if (c.getString(MainConfigPath.path_upToIronArmor) != null) {
            upToIronArmor = Material.getMaterial(c.getString(MainConfigPath.path_upToIronArmor));
        } else {
            sendError( MainConfigPath.path_upToIronArmor);
        }
        if (c.getString(MainConfigPath.path_upToDiamondArmor) != null) {
            upToDiamondArmor = Material.getMaterial(c.getString(MainConfigPath.path_upToDiamondArmor));
        } else {
            sendError( MainConfigPath.path_upToDiamondArmor);
        }

        if (giveProtEnchList == null){
            giveProtEnchList = new ArrayList<>(4);
        }else {
            giveProtEnchList.clear();
        }

        if (c.getStringList(MainConfigPath.path_giveProtEnchList) != null){
            for (String list : c.getStringList(MainConfigPath.path_giveProtEnchList)){
                giveProtEnchList.add(list.toUpperCase());
            }
        }else {
            sendError(MainConfigPath.path_giveProtEnchList);
        }


        if (giveSharpEnchList == null){
            giveSharpEnchList = new ArrayList<>(4);
        }else {
            giveSharpEnchList.clear();
        }


        if (c.getStringList(MainConfigPath.path_giveSharpEnchList) != null){
            for (String list : c.getStringList(MainConfigPath.path_giveSharpEnchList)){
             giveSharpEnchList.add(list.toUpperCase());
            }
        }else {
            sendError(MainConfigPath.path_giveSharpEnchList);
        }

        if (noMoveList == null){
            noMoveList = new ArrayList<>(4);
        }else {
            noMoveList.clear();
        }

        if (c.getStringList(MainConfigPath.path_noMoveList) != null){
            for (String list : c.getStringList(MainConfigPath.path_noMoveList)){
                noMoveList.add(list.toUpperCase());
            }
        }else {
            sendError(MainConfigPath.path_noMoveList);
        }

        if (nobreakList == null){
            nobreakList = new ArrayList<>(48);
        }else {
            nobreakList.clear();
        }

        if (c.getStringList(MainConfigPath.path_nobreakList) != null){
            for (String list : c.getStringList(MainConfigPath.path_nobreakList)){
                nobreakList.add(list.toUpperCase());
            }
        }else {
            sendError(MainConfigPath.path_nobreakList);
        }

        if (c.getString(MainConfigPath.path_messLevelUpFailed) != null) {
            messLevelUpFailed = c.getString(MainConfigPath.path_messLevelUpFailed);
        } else {
            sendError( MainConfigPath.path_messLevelUpFailed);
        }

        if (c.getString(MainConfigPath.path_messLevelUpSharp1) != null) {
            messLevelUpsharp1 = c.getString(MainConfigPath.path_messLevelUpSharp1);
        } else {
            sendError( MainConfigPath.path_messLevelUpSharp1);
        }
        if (c.getString(MainConfigPath.path_messLevelUpSharp2) != null) {
            messLevelUpsharp2 = c.getString(MainConfigPath.path_messLevelUpSharp2);
        } else {
            sendError( MainConfigPath.path_messLevelUpSharp2);
        }
        if (c.getString(MainConfigPath.path_messLevelUpSharp3) != null) {
            messLevelUpsharp3 = c.getString(MainConfigPath.path_messLevelUpSharp3);
        } else {
            sendError( MainConfigPath.path_messLevelUpSharp3);
        }
        if (c.getString(MainConfigPath.path_messLevelUpSharp4) != null) {
            messLevelUpsharp4 = c.getString(MainConfigPath.path_messLevelUpSharp4);
        } else {
            sendError( MainConfigPath.path_messLevelUpSharp4);
        }


        if (c.getString(MainConfigPath.path_messLevelUpProt1) != null) {
            messLevelUpprot1 = c.getString(MainConfigPath.path_messLevelUpProt1);
        } else {
            sendError( MainConfigPath.path_messLevelUpProt1);
        }

        if (c.getString(MainConfigPath.path_messLevelUpProt2) != null) {
            messLevelUpprot2 = c.getString(MainConfigPath.path_messLevelUpProt2);
        } else {
            sendError( MainConfigPath.path_messLevelUpProt2);
        }

        if (c.getString(MainConfigPath.path_messLevelUpProt3) != null) {
            messLevelUpprot3 = c.getString(MainConfigPath.path_messLevelUpProt3);
        } else {
            sendError( MainConfigPath.path_messLevelUpProt3);
        }

        if (c.getString(MainConfigPath.path_messLevelUpProt4) != null) {
            messLevelUpprot4 = c.getString(MainConfigPath.path_messLevelUpProt4);
        } else {
            sendError( MainConfigPath.path_messLevelUpProt4);
        }

        if (c.getString(MainConfigPath.path_meanIron) != null) {
            meanIron = c.getString(MainConfigPath.path_meanIron);
        } else {
            sendError( MainConfigPath.path_meanIron);
        }
        if (c.getString(MainConfigPath.path_meanGold) != null) {
            meanGold = c.getString(MainConfigPath.path_meanGold);
        } else {
            sendError( MainConfigPath.path_meanGold);
        }


        if (c.getString(MainConfigPath.path_meanDiamond) != null) {
            meanDiamond = c.getString(MainConfigPath.path_meanDiamond);
        } else {
            sendError( MainConfigPath.path_meanDiamond);
        }

        if (c.getString(MainConfigPath.path_meanEmerlad) != null) {
            meanEmerlad = c.getString(MainConfigPath.path_meanEmerlad);
        } else {
            sendError( MainConfigPath.path_meanEmerlad);
        }

        if (c.getString(MainConfigPath.path_shopLevelup) != null) {
            shopLevelup = c.getString(MainConfigPath.path_shopLevelup);
        } else {
            sendError( MainConfigPath.path_shopLevelup);
        }

        if (c.getString(MainConfigPath.path_LevelupItemName) != null) {
            LevelupItemName = c.getString(MainConfigPath.path_LevelupItemName);
        } else {
            sendError( MainConfigPath.path_LevelupItemName);
        }


        if (c.getString(MainConfigPath.path_teamEnchItemName_Sharp1) != null) {
            teamEnchItemName_sharp1 = c.getString(MainConfigPath.path_teamEnchItemName_Sharp1);
        } else {
            sendError( MainConfigPath.path_teamEnchItemName_Sharp1);
        }

        if (c.getString(MainConfigPath.path_teamEnchItemName_Prot1) != null) {
            teamEnchItemName_prot1 = c.getString(MainConfigPath.path_teamEnchItemName_Prot1);
        } else {
            sendError( MainConfigPath.path_teamEnchItemName_Prot1);
        }

        if (c.getString(MainConfigPath.path_teamEnchItemName_Prot2) != null) {
            teamEnchItemName_prot2 = c.getString(MainConfigPath.path_teamEnchItemName_Prot2);
        } else {
            sendError( MainConfigPath.path_teamEnchItemName_Prot2);
        }

        if (c.getString(MainConfigPath.path_teamEnchItemName_Prot3) != null) {
            teamEnchItemName_prot3 = c.getString(MainConfigPath.path_teamEnchItemName_Prot3);
        } else {
            sendError( MainConfigPath.path_teamEnchItemName_Prot3);
        }

        if (c.getString(MainConfigPath.path_teamEnchItemName_Prot4) != null) {
            teamEnchItemName_prot4 = c.getString(MainConfigPath.path_teamEnchItemName_Prot4);
        } else {
            sendError( MainConfigPath.path_teamEnchItemName_Prot4);
        }

        if (c.getString(MainConfigPath.path_relTeamName_Red) != null) {
            relTeamName_Red = c.getString(MainConfigPath.path_relTeamName_Red);
        } else {
            sendError( MainConfigPath.path_relTeamName_Red);
        }

        if (c.getString(MainConfigPath.path_relTeamName_Blue) != null) {
            relTeamName_Blue = c.getString(MainConfigPath.path_relTeamName_Blue);
        } else {
            sendError( MainConfigPath.path_relTeamName_Blue);
        }

        if (c.getString(MainConfigPath.path_relTeamName_Green) != null) {
            relTeamName_Green = c.getString(MainConfigPath.path_relTeamName_Green);
        } else {
            sendError( MainConfigPath.path_relTeamName_Green);
        }

        if (c.getString(MainConfigPath.path_relTeamName_Yellow) != null) {
            relTeamName_Yellow = c.getString(MainConfigPath.path_relTeamName_Yellow);
        } else {
            sendError( MainConfigPath.path_relTeamName_Yellow);
        }

        if (c.getString(MainConfigPath.path_relTeamName_Aqua) != null) {
            relTeamName_Aqua = c.getString(MainConfigPath.path_relTeamName_Aqua);
        } else {
            sendError( MainConfigPath.path_relTeamName_Aqua);
        }

        if (c.getString(MainConfigPath.path_relTeamName_White) != null) {
            relTeamName_White = c.getString(MainConfigPath.path_relTeamName_White);
        } else {
            sendError( MainConfigPath.path_relTeamName_White);
        }

        if (c.getString(MainConfigPath.path_relTeamName_Gray) != null) {
            relTeamName_Gray = c.getString(MainConfigPath.path_relTeamName_Gray);
        } else {
            sendError( MainConfigPath.path_relTeamName_Gray);
        }

        if (c.getString(MainConfigPath.path_relTeamName_Pink) != null) {
            relTeamName_Pink = c.getString(MainConfigPath.path_relTeamName_Pink);
        } else {
            sendError( MainConfigPath.path_relTeamName_Pink);
        }

        if (c.getString(MainConfigPath.path_breakTitleAll) != null) {
            breakTitleAll = c.getString(MainConfigPath.path_breakTitleAll);
        } else {
            sendError( MainConfigPath.path_breakTitleAll);
        }

        if (c.getString(MainConfigPath.path_breakSubTitleAll) != null) {
            breakSubTitleAll = c.getString(MainConfigPath.path_breakSubTitleAll);
        } else {
            sendError( MainConfigPath.path_breakSubTitleAll);
        }

        if (c.getString(MainConfigPath.path_breakTitleBreakPlayer) != null) {
            breakTitleBreakPlayer = c.getString(MainConfigPath.path_breakTitleBreakPlayer);
        } else {
            sendError( MainConfigPath.path_breakTitleBreakPlayer);
        }

        if (c.getString(MainConfigPath.path_breakSubTitleBreakPlayer) != null) {
            breakSubTitleBreakPlayer = c.getString(MainConfigPath.path_breakSubTitleBreakPlayer);
        } else {
            sendError( MainConfigPath.path_breakSubTitleBreakPlayer);
        }

        if (c.getString(MainConfigPath.path_breakTitleBreakTeam) != null) {
            breakTitleBreakTeam = c.getString(MainConfigPath.path_breakTitleBreakTeam);
        } else {
            sendError( MainConfigPath.path_breakTitleBreakTeam);
        }

        if (c.getString(MainConfigPath.path_breakSubTitleBreakTeam) != null) {
            breakSubTitleBreakTeam = c.getString(MainConfigPath.path_breakSubTitleBreakTeam);
        } else {
            sendError( MainConfigPath.path_breakSubTitleBreakTeam);
        }

        if (c.getString(MainConfigPath.path_rushWorld) != null) {
            rushWorld = c.getString(MainConfigPath.path_rushWorld);
        } else {
            sendError( MainConfigPath.path_rushWorld);
        }

        if (c.getString(MainConfigPath.path_rushWorld2v2) != null) {
            rushWorld2v2 = c.getString(MainConfigPath.path_rushWorld2v2);
        } else {
            sendError( MainConfigPath.path_rushWorld2v2);
        }

        if (c.getString(MainConfigPath.path_rushWorld4v4) != null) {
            rushWorld4v4 = c.getString(MainConfigPath.path_rushWorld4v4);
        } else {
            sendError( MainConfigPath.path_rushWorld4v4);
        }

        if (c.getString(MainConfigPath.path_lobbyWorld) != null) {
            lobbyWorld = c.getString(MainConfigPath.path_lobbyWorld);
        } else {
            sendError( MainConfigPath.path_lobbyWorld);
        }

        if (c.getString(MainConfigPath.path_respawnTitle) != null) {
            respawnTitle = c.getString(MainConfigPath.path_respawnTitle);
        } else {
            sendError( MainConfigPath.path_respawnTitle);
        }

        if (c.getString(MainConfigPath.path_respawnSubTitle) != null) {
            respawnSubTitle = c.getString(MainConfigPath.path_respawnSubTitle);
        } else {
            sendError( MainConfigPath.path_respawnSubTitle);
        }

        if (c.getString(MainConfigPath.path_respawnChat) != null) {
            respawnChat = c.getString(MainConfigPath.path_respawnChat);
        } else {
            sendError( MainConfigPath.path_respawnChat);
        }

        if (c.getString(MainConfigPath.path_respawnActionBar) != null) {
            respawnActionBar = c.getString(MainConfigPath.path_respawnActionBar);
        } else {
            sendError( MainConfigPath.path_respawnActionBar);
        }


        if (c.getString(MainConfigPath.path_respawnSuccTitle) != null) {
            respawnSuccTitle = c.getString(MainConfigPath.path_respawnSuccTitle);
        } else {
            sendError( MainConfigPath.path_respawnSuccTitle);
        }

        if (c.getString(MainConfigPath.path_respawnSuccSubTitle) != null) {
            respawnSuccSubTitle = c.getString(MainConfigPath.path_respawnSuccSubTitle);
        } else {
            sendError( MainConfigPath.path_respawnSuccSubTitle);
        }

        if (c.getString(MainConfigPath.path_respawnSuccChat) != null) {
            respawnSuccChat = c.getString(MainConfigPath.path_respawnSuccChat);
        } else {
            sendError( MainConfigPath.path_respawnSuccChat);
        }

        if (c.getString(MainConfigPath.path_respawnSuccActionBar) != null) {
            respawnSuccActionBar = c.getString(MainConfigPath.path_respawnSuccActionBar);
        } else {
            sendError( MainConfigPath.path_respawnSuccActionBar);
        }

        if (c.getString(MainConfigPath.path_meanTeamBedYes) != null) {
            meanTeamBedYes = c.getString(MainConfigPath.path_meanTeamBedYes);
        } else {
            sendError( MainConfigPath.path_meanTeamBedYes);
        }

        if (c.getString(MainConfigPath.path_meanTeamNone) != null) {
            meanTeamNone = c.getString(MainConfigPath.path_meanTeamNone);
        } else {
            sendError( MainConfigPath.path_meanTeamNone);
        }

        if (c.getString(MainConfigPath.path_meanTeamBedNo) != null) {
            meanTeamBedNo = c.getString(MainConfigPath.path_meanTeamBedNo);
        } else {
            sendError( MainConfigPath.path_meanTeamBedNo);
        }

        if (c.getString(MainConfigPath.path_serverIp) != null) {
            serverIp = c.getString(MainConfigPath.path_serverIp);
        } else {
            sendError( MainConfigPath.path_serverIp);
        }

        if (c.getString(MainConfigPath.path_meanYou) != null) {
            meanYou = c.getString(MainConfigPath.path_meanYou);
        } else {
            sendError( MainConfigPath.path_meanYou);
        }

        if (c.getString(MainConfigPath.path_meanNotYou) != null) {
            meanNotYou = c.getString(MainConfigPath.path_meanNotYou);
        } else {
            sendError( MainConfigPath.path_meanNotYou);
        }

        if (c.getString(MainConfigPath.path_damagefb_attackactionbar) != null) {
            damagefb_attackactionbar = c.getString(MainConfigPath.path_damagefb_attackactionbar);
        } else {
            sendError( MainConfigPath.path_damagefb_attackactionbar);
        }

        if (c.getString(MainConfigPath.path_damagefb_attackchat) != null) {
            damagefb_attackchat = c.getString(MainConfigPath.path_damagefb_attackchat);
        } else {
            sendError( MainConfigPath.path_damagefb_attackchat);
        }

        if (c.getString(MainConfigPath.path_damagefb_attackmess) != null) {
            damagefb_attackmess = c.getBoolean(MainConfigPath.path_damagefb_attackmess);
        } else {
            sendError( MainConfigPath.path_damagefb_attackmess);
        }

        if (c.getString(MainConfigPath.path_damagefb_attackTitle) != null) {
            damagefb_attackTitle = c.getString(MainConfigPath.path_damagefb_attackTitle);
        } else {
            sendError( MainConfigPath.path_damagefb_attackTitle);
        }
        if (c.getString(MainConfigPath.path_damagefb_attackSubTitle) != null) {
            damagefb_attackSubTitle = c.getString(MainConfigPath.path_damagefb_attackSubTitle);
        } else {
            sendError( MainConfigPath.path_damagefb_attackSubTitle);
        }

        if (c.getString(MainConfigPath.path_damagefb_attackBlood) != null) {
            damagefb_attackBlood = c.getBoolean(MainConfigPath.path_damagefb_attackBlood);
        } else {
            sendError( MainConfigPath.path_damagefb_attackBlood);
        }

        if (c.getString(MainConfigPath.path_damagefb_attackBloodMode) != null) {
            damagefb_attackBloodMode = c.getString(MainConfigPath.path_damagefb_attackBloodMode);
        } else {
            sendError( MainConfigPath.path_damagefb_attackBloodMode);
        }

        if (c.getString(MainConfigPath.path_damagefb_attackactionbar) != null) {
            damagefb_attackactionbar = c.getString(MainConfigPath.path_damagefb_attackactionbar);
        } else {
            sendError( MainConfigPath.path_damagefb_attackactionbar);
        }

        if (c.getString(MainConfigPath.path_damagefb_attackBloodType) != null) {
            damagefb_attackBloodType = c.getInt(MainConfigPath.path_damagefb_attackBloodType);
        } else {
            sendError( MainConfigPath.path_damagefb_attackBloodType);
        }


        if (c.getString(MainConfigPath.path_startmess) != null) {
            startmess = c.getBoolean(MainConfigPath.path_startmess);
        } else {
            sendError( MainConfigPath.path_startmess);
        }


        if (c.getString(MainConfigPath.path_startmess_all_chat) != null) {
            startmess_all_chat = c.getString(MainConfigPath.path_startmess_all_chat);
        } else {
            sendError( MainConfigPath.path_startmess_all_chat);
        }
        if (c.getString(MainConfigPath.path_startmess_all_title) != null) {
            startmess_all_title = c.getString(MainConfigPath.path_startmess_all_title);
        } else {
            sendError( MainConfigPath.path_startmess_all_title);
        }
        if (c.getString(MainConfigPath.path_startmess_all_subtitle) != null) {
            startmess_all_subtitle = c.getString(MainConfigPath.path_startmess_all_subtitle);
        } else {
            sendError( MainConfigPath.path_startmess_all_subtitle);
        }

        if (c.getString(MainConfigPath.path_startmess_all_actionbar) != null) {
            startmess_all_actionbar = c.getString(MainConfigPath.path_startmess_all_actionbar);
        } else {
            sendError( MainConfigPath.path_startmess_all_actionbar);
        }


        MainConfigHandler.ScoreBoard2v2Line = new HashMap<>(17);
        MainConfigHandler.ScoreBoard2v2Line.put(100, MainConfigHandler.ScoreBoard2v2Title);
        MainConfigHandler.ScoreBoard2v2Line.put(16, MainConfigHandler.ScoreBoard2v2Line01);
        MainConfigHandler.ScoreBoard2v2Line.put(15, MainConfigHandler.ScoreBoard2v2Line02);
        MainConfigHandler.ScoreBoard2v2Line.put(14, MainConfigHandler.ScoreBoard2v2Line03);
        MainConfigHandler.ScoreBoard2v2Line.put(13, MainConfigHandler.ScoreBoard2v2Line04);
        MainConfigHandler.ScoreBoard2v2Line.put(12, MainConfigHandler.ScoreBoard2v2Line05);
        MainConfigHandler.ScoreBoard2v2Line.put(11, MainConfigHandler.ScoreBoard2v2Line06);
        MainConfigHandler.ScoreBoard2v2Line.put(10, MainConfigHandler.ScoreBoard2v2Line07);
        MainConfigHandler.ScoreBoard2v2Line.put(9, MainConfigHandler.ScoreBoard2v2Line08);
        MainConfigHandler.ScoreBoard2v2Line.put(8, MainConfigHandler.ScoreBoard2v2Line09);
        MainConfigHandler.ScoreBoard2v2Line.put(7, MainConfigHandler.ScoreBoard2v2Line10);
        MainConfigHandler.ScoreBoard2v2Line.put(6, MainConfigHandler.ScoreBoard2v2Line11);
        MainConfigHandler.ScoreBoard2v2Line.put(5, MainConfigHandler.ScoreBoard2v2Line12);
        MainConfigHandler.ScoreBoard2v2Line.put(4, MainConfigHandler.ScoreBoard2v2Line13);
        MainConfigHandler.ScoreBoard2v2Line.put(3, MainConfigHandler.ScoreBoard2v2Line14);
        MainConfigHandler.ScoreBoard2v2Line.put(2, MainConfigHandler.ScoreBoard2v2Line15);
        MainConfigHandler.ScoreBoard2v2Line.put(1, MainConfigHandler.ScoreBoard2v2Line16);

        MainConfigHandler.ScoreBoard4v4Line = new HashMap<>(17);
        MainConfigHandler.ScoreBoard4v4Line.put(100, MainConfigHandler.ScoreBoard4v4Title);
        MainConfigHandler.ScoreBoard4v4Line.put(16, MainConfigHandler.ScoreBoard4v4Line01);
        MainConfigHandler.ScoreBoard4v4Line.put(15, MainConfigHandler.ScoreBoard4v4Line02);
        MainConfigHandler.ScoreBoard4v4Line.put(14, MainConfigHandler.ScoreBoard4v4Line03);
        MainConfigHandler.ScoreBoard4v4Line.put(13, MainConfigHandler.ScoreBoard4v4Line04);
        MainConfigHandler.ScoreBoard4v4Line.put(12, MainConfigHandler.ScoreBoard4v4Line05);
        MainConfigHandler.ScoreBoard4v4Line.put(11, MainConfigHandler.ScoreBoard4v4Line06);
        MainConfigHandler.ScoreBoard4v4Line.put(10, MainConfigHandler.ScoreBoard4v4Line07);
        MainConfigHandler.ScoreBoard4v4Line.put(9, MainConfigHandler.ScoreBoard4v4Line08);
        MainConfigHandler.ScoreBoard4v4Line.put(8, MainConfigHandler.ScoreBoard4v4Line09);
        MainConfigHandler.ScoreBoard4v4Line.put(7, MainConfigHandler.ScoreBoard4v4Line10);
        MainConfigHandler.ScoreBoard4v4Line.put(6, MainConfigHandler.ScoreBoard4v4Line11);
        MainConfigHandler.ScoreBoard4v4Line.put(5, MainConfigHandler.ScoreBoard4v4Line12);
        MainConfigHandler.ScoreBoard4v4Line.put(4, MainConfigHandler.ScoreBoard4v4Line13);
        MainConfigHandler.ScoreBoard4v4Line.put(3, MainConfigHandler.ScoreBoard4v4Line14);
        MainConfigHandler.ScoreBoard4v4Line.put(2, MainConfigHandler.ScoreBoard4v4Line15);
        MainConfigHandler.ScoreBoard4v4Line.put(1, MainConfigHandler.ScoreBoard4v4Line16);


        if (c.getString(MainConfigPath.path_placeCorrect_notInGame_OpBypass) != null) {
            placeCorrect_notInGame_OpBypass = c.getBoolean(MainConfigPath.path_placeCorrect_notInGame_OpBypass);
        } else {
            sendError( MainConfigPath.path_placeCorrect_notInGame_OpBypass);
        }

        if (c.getString(MainConfigPath.path_breakCorrect_notInGame_OpBypass) != null) {
            breakCorrect_notInGame_OpBypass = c.getBoolean(MainConfigPath.path_breakCorrect_notInGame_OpBypass);
        } else {
            sendError( MainConfigPath.path_breakCorrect_notInGame_OpBypass);
        }


        if (c.getString(MainConfigPath.path_cleanBed) != null) {
            cleanBed = c.getBoolean(MainConfigPath.path_cleanBed);
        } else {
            sendError(MainConfigPath.path_cleanBed);
        }


        if (c.getString(MainConfigPath.path_preventloadworld) != null) {
            preventloadworld = c.getBoolean(MainConfigPath.path_preventloadworld);
        } else {
            sendError( MainConfigPath.path_preventloadworld);
        }

        if (c.getString(MainConfigPath.path_CleanHostileOnStart) != null) {
            CleanHostileOnStart = c.getBoolean(MainConfigPath.path_CleanHostileOnStart);
        } else {
            sendError( MainConfigPath.path_CleanHostileOnStart);
        }

        if (c.getString(MainConfigPath.path_placeCorrect_ResSpawner) != null) {
            placeCorrect_ResSpawner = c.getBoolean(MainConfigPath.path_placeCorrect_ResSpawner);
        } else {
            sendError( MainConfigPath.path_placeCorrect_ResSpawner);
        }

        if (c.getString(MainConfigPath.path_placeCorrect_ResSpawner_mess_chat) != null) {
            placeCorrect_ResSpawner_mess_chat = c.getString(MainConfigPath.path_placeCorrect_ResSpawner_mess_chat);
        } else {
            sendError( MainConfigPath.path_placeCorrect_ResSpawner_mess_chat);
        }


        if (c.getString(MainConfigPath.path_placeCorrect_ResSpawner_mess_title) != null) {
            placeCorrect_ResSpawner_mess_title = c.getString(MainConfigPath.path_placeCorrect_ResSpawner_mess_title);
        } else {
            sendError( MainConfigPath.path_placeCorrect_ResSpawner_mess_title);
        }

        if (c.getString(MainConfigPath.path_placeCorrect_ResSpawner_mess_subtitle) != null) {
            placeCorrect_ResSpawner_mess_subtitle = c.getString(MainConfigPath.path_placeCorrect_ResSpawner_mess_subtitle);
        } else {
            sendError( MainConfigPath.path_placeCorrect_ResSpawner_mess_subtitle);
        }

        if (c.getString(MainConfigPath.path_placeCorrect_ResSpawner_mess_actionbar) != null) {
            placeCorrect_ResSpawner_mess_actionbar = c.getString(MainConfigPath.path_placeCorrect_ResSpawner_mess_actionbar);
        } else {
            sendError( MainConfigPath.path_placeCorrect_ResSpawner_mess_actionbar);
        }


        if (c.getString(MainConfigPath.path_placeCorrect_PlayerSpawnLoc) != null) {
            placeCorrect_PlayerSpawnLoc = c.getBoolean(MainConfigPath.path_placeCorrect_PlayerSpawnLoc);
        } else {
            sendError( MainConfigPath.path_placeCorrect_PlayerSpawnLoc);
        }


        if (c.getString(MainConfigPath.path_placeCorrect_PlayerSpawnLoc_mess_chat) != null) {
            placeCorrect_PlayerSpawnLoc_mess_chat = c.getString(MainConfigPath.path_placeCorrect_PlayerSpawnLoc_mess_chat);
        } else {
            sendError( MainConfigPath.path_placeCorrect_PlayerSpawnLoc_mess_chat);
        }


        if (c.getString(MainConfigPath.path_placeCorrect_PlayerSpawnLoc_mess_title) != null) {
            placeCorrect_PlayerSpawnLoc_mess_title = c.getString(MainConfigPath.path_placeCorrect_PlayerSpawnLoc_mess_title);
        } else {
            sendError( MainConfigPath.path_placeCorrect_PlayerSpawnLoc_mess_title);
        }

        if (c.getString(MainConfigPath.path_placeCorrect_PlayerSpawnLoc_mess_subtitle) != null) {
            placeCorrect_PlayerSpawnLoc_mess_subtitle = c.getString(MainConfigPath.path_placeCorrect_PlayerSpawnLoc_mess_subtitle);
        } else {
            sendError( MainConfigPath.path_placeCorrect_PlayerSpawnLoc_mess_subtitle);
        }

        if (c.getString(MainConfigPath.path_placeCorrect_PlayerSpawnLoc_mess_actionbar) != null) {
            placeCorrect_PlayerSpawnLoc_mess_actionbar = c.getString(MainConfigPath.path_placeCorrect_PlayerSpawnLoc_mess_actionbar);
        } else {
            sendError( MainConfigPath.path_placeCorrect_PlayerSpawnLoc_mess_actionbar);
        }


        if (c.getString(MainConfigPath.path_breakCorrect_notInGame) != null) {
            breakCorrect_notInGame = c.getBoolean(MainConfigPath.path_breakCorrect_notInGame);
        } else {
            sendError( MainConfigPath.path_breakCorrect_notInGame);
        }


        if (c.getString(MainConfigPath.path_placeCorrect_notInGame) != null) {
            placeCorrect_notInGame = c.getBoolean(MainConfigPath.path_placeCorrect_notInGame);
        } else {
            sendError( MainConfigPath.path_placeCorrect_notInGame);
        }


        if (c.getString(MainConfigPath.path_killfb_sendmess) != null) {
            killfb_sendmess = c.getBoolean(MainConfigPath.path_killfb_sendmess);
        } else {
            sendError( MainConfigPath.path_killfb_sendmess);
        }

        if (c.getString(MainConfigPath.path_killfb_sendmess_chat) != null) {
            killfb_sendmess_chat = c.getString(MainConfigPath.path_killfb_sendmess_chat);
        } else {
            sendError( MainConfigPath.path_killfb_sendmess_chat);
        }
        if (c.getString(MainConfigPath.path_killfb_sendmess_title) != null) {
            killfb_sendmess_title = c.getString(MainConfigPath.path_killfb_sendmess_title);
        } else {
            sendError( MainConfigPath.path_killfb_sendmess_title);
        }
        if (c.getString(MainConfigPath.path_killfb_sendmess_subtitle) != null) {
            killfb_sendmess_subtitle = c.getString(MainConfigPath.path_killfb_sendmess_subtitle);
        } else {
            sendError( MainConfigPath.path_killfb_sendmess_subtitle);
        }
        if (c.getString(MainConfigPath.path_killfb_sendmess_actionbar) != null) {
            killfb_sendmess_actionbar = c.getString(MainConfigPath.path_killfb_sendmess_actionbar);
        } else {
            sendError( MainConfigPath.path_killfb_sendmess_actionbar);
        }

        if (c.getString(MainConfigPath.path_kill_res_chat) != null) {
            kill_res_chat = c.getString(MainConfigPath.path_kill_res_chat);
        } else {
            sendError( MainConfigPath.path_killfb_sendmess_actionbar);
        }


        if (c.getString(MainConfigPath.path_meanGameEnd) != null) {
            meanGameEnd = c.getString(MainConfigPath.path_meanGameEnd);
        } else {
            sendError( MainConfigPath.path_meanGameEnd);
        }

        if (c.getString(MainConfigPath.path_meanSecond) != null) {
            meanSecond = c.getString(MainConfigPath.path_meanSecond);
        } else {
            sendError( MainConfigPath.path_meanSecond);
        }


        if (c.getString(MainConfigPath.path_shopItem) != null) {
            shopItem = c.getString(MainConfigPath.path_shopItem);
        } else {
            sendError( MainConfigPath.path_shopItem);
        }


        if (c.getString(MainConfigPath.path_teamEff_Heal_dis) != null) {
            teamEff_Heal_dis = c.getInt(MainConfigPath.path_teamEff_Heal_dis);
        } else {
            sendError(MainConfigPath.path_teamEff_Heal_dis);
        }


        if (c.getString(MainConfigPath.path_teamEnchInvRow) != null) {
            teamEnchInvRow = c.getInt(MainConfigPath.path_teamEnchInvRow);
        } else {
            sendError(MainConfigPath.path_teamEnchInvRow);
        }


        if (c.getString(MainConfigPath.path_tab) != null) {
            tab = c.getBoolean(MainConfigPath.path_tab);
        } else {
            sendError(MainConfigPath.path_tab);
        }

        if (c.getString(MainConfigPath.path_tab_is_multiLine) != null) {
            tab_is_multiLine = c.getBoolean(MainConfigPath.path_tab_is_multiLine);
        } else {
            sendError(MainConfigPath.path_tab_is_multiLine);
        }

        if (c.getStringList(MainConfigPath.path_tab_headList) != null){
            tab_headList = c.getStringList(MainConfigPath.path_tab_headList);
        }else{
            if (tab_is_multiLine){
                sendError(MainConfigPath.path_tab_headList);
            }
        }

        if (c.getString(MainConfigPath.path_tab_head) != null){
            tab_head = c.getString(MainConfigPath.path_tab_head);
        }else{
            if (!tab_is_multiLine){
                sendError(MainConfigPath.path_tab_head);
            }
        }

        if (c.getStringList(MainConfigPath.path_tab_footList) != null){
            tab_footList = c.getStringList(MainConfigPath.path_tab_footList);
        }else{
            if (tab_is_multiLine){
                sendError(MainConfigPath.path_tab_footList);
            }
        }

        if (c.getString(MainConfigPath.path_tab_foot) != null) {
            tab_foot = c.getString(MainConfigPath.path_tab_foot);
        } else {
            if (!tab_is_multiLine){
                sendError(MainConfigPath.path_tab_foot);
            }
        }


        if (c.getString(MainConfigPath.path_levelupsharpItemType) != null) {
            levelupsharpItemType = Material.getMaterial(c.getString(MainConfigPath.path_levelupsharpItemType));
        } else {
            sendError(MainConfigPath.path_levelupsharpItemType);
        }

        if (c.getString(MainConfigPath.path_levelupsharpItemSlot) != null) {
            levelupsharpItemSlot = c.getInt(MainConfigPath.path_levelupsharpItemSlot);
        } else {
            sendError(MainConfigPath.path_levelupsharpItemSlot);
        }


        if (c.getString(MainConfigPath.path_levelupprotItemType) != null) {
            levelupprotItemType = Material.getMaterial(c.getString(MainConfigPath.path_levelupprotItemType));
        } else {
            sendError(MainConfigPath.path_levelupprotItemType);
        }

        if (c.getString(MainConfigPath.path_levelupprotItemSlot) != null) {
            levelupprotItemSlot = c.getInt(MainConfigPath.path_levelupprotItemSlot);
        } else {
            sendError(MainConfigPath.path_levelupprotItemSlot);
        }

        if (c.getString(MainConfigPath.path_teamEnchItemName_sharp2) != null) {
            teamEnchItemName_sharp2 = c.getString(MainConfigPath.path_teamEnchItemName_sharp2);
        } else {
            sendError(MainConfigPath.path_teamEnchItemName_sharp2);
        }

        if (c.getString(MainConfigPath.path_teamEnchItemName_sharp3) != null) {
            teamEnchItemName_sharp3 = c.getString(MainConfigPath.path_teamEnchItemName_sharp3);
        } else {
            sendError(MainConfigPath.path_teamEnchItemName_sharp3);
        }

        if (c.getString(MainConfigPath.path_teamEnchItemName_sharp4) != null) {
            teamEnchItemName_sharp4 = c.getString(MainConfigPath.path_teamEnchItemName_sharp4);
        } else {
            sendError(MainConfigPath.path_teamEnchItemName_sharp4);
        }

        if (c.getString(MainConfigPath.path_TeamEnchantMaxCost) != null) {
            TeamEnchantMaxCost = c.getString(MainConfigPath.path_TeamEnchantMaxCost);
        } else {
            sendError(MainConfigPath.path_TeamEnchantMaxCost);
        }

        if (c.getString(MainConfigPath.path_teamEnchItemName_sharpMax) != null) {
            teamEnchItemName_sharpMax = c.getString(MainConfigPath.path_teamEnchItemName_sharpMax);
        } else {
            sendError(MainConfigPath.path_teamEnchItemName_sharpMax);
        }

        if (c.getString(MainConfigPath.path_teamEnchItemName_protMax) != null) {
            teamEnchItemName_protMax = c.getString(MainConfigPath.path_teamEnchItemName_protMax);
        } else {
            sendError(MainConfigPath.path_teamEnchItemName_protMax);
        }


        if (c.getString(MainConfigPath.path_leveluphasteItemType) != null) {
            leveluphasteItemType = Material.getMaterial(c.getString(MainConfigPath.path_leveluphasteItemType));
        } else {
            sendError(MainConfigPath.path_leveluphasteItemType);
        }

        if (c.getString(MainConfigPath.path_leveluphasteItemSlot) != null) {
            leveluphasteItemSlot = c.getInt(MainConfigPath.path_leveluphasteItemSlot);
        } else {
            sendError(MainConfigPath.path_leveluphasteItemSlot);
        }


        if (c.getString(MainConfigPath.path_teamEffItemName_haste1) != null) {
            teamEffItemName_haste1 = c.getString(MainConfigPath.path_teamEffItemName_haste1);
        } else {
            sendError(MainConfigPath.path_teamEffItemName_haste1);
        }
        if (c.getString(MainConfigPath.path_teamEffItemName_haste2) != null) {
            teamEffItemName_haste2 = c.getString(MainConfigPath.path_teamEffItemName_haste2);
        } else {
            sendError(MainConfigPath.path_teamEffItemName_haste2);
        }

        if (c.getString(MainConfigPath.path_teamEffItemName_hasteMax) != null) {
            teamEffItemName_hasteMax = c.getString(MainConfigPath.path_teamEffItemName_hasteMax);
        } else {
            sendError(MainConfigPath.path_teamEffItemName_hasteMax);
        }

        if (c.getString(MainConfigPath.path_leveluphealItemType) != null) {
            leveluphealItemType = Material.getMaterial(c.getString(MainConfigPath.path_leveluphealItemType));
        } else {
            sendError(MainConfigPath.path_leveluphealItemType);
        }


        if (c.getString(MainConfigPath.path_leveluphealItemSlot) != null) {
            leveluphealItemSlot = c.getInt(MainConfigPath.path_leveluphealItemSlot);
        } else {
            sendError(MainConfigPath.path_leveluphealItemSlot);
        }
        if (c.getString(MainConfigPath.path_teamEffItemName_heal1) != null) {
            teamEffItemName_heal1 = c.getString(MainConfigPath.path_teamEffItemName_heal1);
        } else {
            sendError(MainConfigPath.path_teamEffItemName_heal1);
        }

        if (c.getString(MainConfigPath.path_teamEffItemName_healMax) != null) {
            teamEffItemName_healMax = c.getString(MainConfigPath.path_teamEffItemName_healMax);
        } else {
            sendError(MainConfigPath.path_teamEffItemName_healMax);
        }


        if (c.getString(MainConfigPath.path_haste1Cost2v2) != null) {
            haste1Cost2v2 = c.getInt(MainConfigPath.path_haste1Cost2v2);
        } else {
            sendError(MainConfigPath.path_haste1Cost2v2);
        }

        if (c.getString(MainConfigPath.path_haste2Cost2v2) != null) {
            haste2Cost2v2 = c.getInt(MainConfigPath.path_haste2Cost2v2);
        } else {
            sendError(MainConfigPath.path_haste2Cost2v2);
        }

        if (c.getString(MainConfigPath.path_heal1Cost2v2) != null) {
            heal1Cost2v2 = c.getInt(MainConfigPath.path_heal1Cost2v2);
        } else {
            sendError(MainConfigPath.path_heal1Cost2v2);
        }


        if (c.getString(MainConfigPath.path_haste1Cost4v4) != null) {
            haste1Cost4v4 = c.getInt(MainConfigPath.path_haste1Cost4v4);
        } else {
            sendError(MainConfigPath.path_haste1Cost4v4);
        }

        if (c.getString(MainConfigPath.path_haste2Cost4v4) != null) {
            haste2Cost4v4 = c.getInt(MainConfigPath.path_haste2Cost4v4);
        } else {
            sendError(MainConfigPath.path_haste2Cost4v4);
        }
        if (c.getString(MainConfigPath.path_heal1Cost4v4) != null) {
            heal1Cost4v4 = c.getInt(MainConfigPath.path_heal1Cost4v4);
        } else {
            sendError(MainConfigPath.path_heal1Cost4v4);
        }

        if (c.getString(MainConfigPath.path_messLevelUpHaste1) != null) {
            messLevelUphaste1 = c.getString(MainConfigPath.path_messLevelUpHaste1);
        } else {
            sendError(MainConfigPath.path_messLevelUpHaste1);
        }

        if (c.getString(MainConfigPath.path_messLevelUpHaste2) != null) {
            messLevelUphaste2 = c.getString(MainConfigPath.path_messLevelUpHaste2);
        } else {
            sendError(MainConfigPath.path_messLevelUpHaste2);
        }

        if (c.getString(MainConfigPath.path_messLevelUpHeal1) != null) {
            messLevelUpheal1 = c.getString(MainConfigPath.path_messLevelUpHeal1);
        } else {
            sendError(MainConfigPath.path_messLevelUpHeal1);
        }


        if (c.getString(MainConfigPath.path_teamEffItemName_Haste1) != null) {
            teamEffItemName_Haste1 = c.getString(MainConfigPath.path_teamEffItemName_Haste1);
        } else {
            sendError(MainConfigPath.path_teamEffItemName_Haste1);
        }

        if (c.getString(MainConfigPath.path_teamEffItemName_Haste2) != null) {
            teamEffItemName_Haste2 = c.getString(MainConfigPath.path_teamEffItemName_Haste2);
        } else {
            sendError(MainConfigPath.path_teamEffItemName_Haste2);
        }

        if (c.getString(MainConfigPath.path_teamEffItemName_Heal1) != null) {
            teamEffItemName_Heal1 = c.getString(MainConfigPath.path_teamEffItemName_Heal1);
        } else {
            sendError(MainConfigPath.path_teamEffItemName_Heal1);
        }


        if (c.getString(MainConfigPath.path_dieOutGameItem_playAgain) != null) {
            dieOutGameItem_playAgain = c.getBoolean(MainConfigPath.path_dieOutGameItem_playAgain);
        } else {
            sendError(MainConfigPath.path_dieOutGameItem_playAgain);
        }

        if (c.getString(MainConfigPath.path_dieOutGameItem_playAgain_ItemName) != null) {
            dieOutGameItem_playAgain_ItemName = c.getString(MainConfigPath.path_dieOutGameItem_playAgain_ItemName);
        } else {
            sendError(MainConfigPath.path_dieOutGameItem_playAgain_ItemName);
        }

        if (c.getString(MainConfigPath.path_dieOutGameItem_playAgain_ItemType) != null) {
            dieOutGameItem_playAgain_ItemType = Material.getMaterial(c.getString(MainConfigPath.path_dieOutGameItem_playAgain_ItemType));
        } else {
            sendError(MainConfigPath.path_teamEffItemName_Heal1);
        }

        if (c.getString(MainConfigPath.path_dieOutGameItem_playAgain_ClickSendCommand) != null) {
            dieOutGameItem_playAgain_ClickSendCommand = c.getString(MainConfigPath.path_dieOutGameItem_playAgain_ClickSendCommand);
        } else {
            sendError(MainConfigPath.path_dieOutGameItem_playAgain_ClickSendCommand);
        }


        if (c.getString(MainConfigPath.path_lobbyjoinTeamMess_chat) != null) {
            lobbyjoinTeamMess_chat = c.getString(MainConfigPath.path_lobbyjoinTeamMess_chat);
        } else {
            sendError(MainConfigPath.path_lobbyjoinTeamMess_chat);
        }
        if (c.getString(MainConfigPath.path_lobbyjoinTeamMess_title) != null) {
            lobbyjoinTeamMess_title = c.getString(MainConfigPath.path_lobbyjoinTeamMess_title);
        } else {
            sendError(MainConfigPath.path_lobbyjoinTeamMess_title);
        }
        if (c.getString(MainConfigPath.path_lobbyjoinTeamMess_subtitle) != null) {
            lobbyjoinTeamMess_subtitle = c.getString(MainConfigPath.path_lobbyjoinTeamMess_subtitle);
        } else {
            sendError(MainConfigPath.path_lobbyjoinTeamMess_subtitle);
        }
        if (c.getString(MainConfigPath.path_lobbyjoinTeamMess_actionbar) != null) {
            lobbyjoinTeamMess_actionbar = c.getString(MainConfigPath.path_lobbyjoinTeamMess_actionbar);
        } else {
            sendError(MainConfigPath.path_lobbyjoinTeamMess_actionbar);
        }

        if (c.getString(MainConfigPath.path_deathGameMode_tpto) != null) {
            deathGameMode_tpto = c.getString(MainConfigPath.path_deathGameMode_tpto);
        } else {
            sendError(MainConfigPath.path_deathGameMode_tpto);
        }


        if (c.getString(MainConfigPath.path_placeCorrect_ResSpawner_dis) != null) {
            placeCorrect_ResSpawner_dis = c.getInt(MainConfigPath.path_placeCorrect_ResSpawner_dis);
        } else {
            sendError(MainConfigPath.path_placeCorrect_ResSpawner_dis);
        }

        if (c.getString(MainConfigPath.path_placeCorrect_PlayerSpawnLoc_dis) != null) {
            placeCorrect_PlayerSpawnLoc_dis = c.getInt(MainConfigPath.path_placeCorrect_PlayerSpawnLoc_dis);
        } else {
            sendError(MainConfigPath.path_placeCorrect_PlayerSpawnLoc_dis);
        }

        if (c.getString(MainConfigPath.path_lobbyleaveTeam) != null) {
            lobbyleaveTeam = c.getBoolean(MainConfigPath.path_lobbyleaveTeam);
        } else {
            sendError( MainConfigPath.path_lobbyleaveTeam);
        }

        if (c.getString(MainConfigPath.path_lobbyleaveTeamMess) != null) {
            lobbyleaveTeamMess = c.getString(MainConfigPath.path_lobbyleaveTeamMess);
        } else {
            sendError( MainConfigPath.path_lobbyleaveTeamMess);
        }

        if (c.getString(MainConfigPath.path_creativeGameModeFix) != null) {
            creativeGameModeFix = c.getBoolean(MainConfigPath.path_creativeGameModeFix);
        } else {
            sendError(MainConfigPath.path_creativeGameModeFix);
        }

        if (c.getString(MainConfigPath.path_bungeeMode) != null) {
            bungeeMode = c.getString(MainConfigPath.path_bungeeMode);
        } else {
            sendError(MainConfigPath.path_bungeeMode);
        }


        if (c.getString(MainConfigPath.path_paneItemLore) != null) {
            paneItemLore = c.getStringList(MainConfigPath.path_paneItemLore);
        } else {
            sendError(MainConfigPath.path_paneItemLore);
        }
        if (c.getString(MainConfigPath.path_paneItemName) != null) {
            paneItemName = c.getString(MainConfigPath.path_paneItemName);
        } else {
            sendError(MainConfigPath.path_paneItemName);
        }


        if (c.getString(MainConfigPath.path_teamEffItemName_res1) != null) {
            teamEffItemName_res1 = c.getString(MainConfigPath.path_teamEffItemName_res1);
        } else {
            sendError(MainConfigPath.path_teamEffItemName_res1);
        }

        if (c.getString(MainConfigPath.path_teamEffItemName_res2) != null) {
            teamEffItemName_res2 = c.getString(MainConfigPath.path_teamEffItemName_res2);
        } else {
            sendError(MainConfigPath.path_teamEffItemName_res2);
        }

        if (c.getString(MainConfigPath.path_teamEffItemName_res3) != null) {
            teamEffItemName_res3 = c.getString(MainConfigPath.path_teamEffItemName_res3);
        } else {
            sendError(MainConfigPath.path_teamEffItemName_res3);
        }

        if (c.getString(MainConfigPath.path_teamEffItemName_res4) != null) {
            teamEffItemName_res4 = c.getString(MainConfigPath.path_teamEffItemName_res4);
        } else {
            sendError(MainConfigPath.path_teamEffItemName_res4);
        }


        if (c.getString(MainConfigPath.path_teamEffItemName_resMax) != null) {
            teamEffItemName_resMax = c.getString(MainConfigPath.path_teamEffItemName_resMax);
        } else {
            sendError(MainConfigPath.path_teamEffItemName_resMax);
        }

        if (c.getString(MainConfigPath.path_levelupresItemType) != null) {
            levelupresItemType = Material.getMaterial(c.getString(MainConfigPath.path_levelupresItemType));
        } else {
            sendError(MainConfigPath.path_levelupresItemType);
        }

        if (c.getString(MainConfigPath.path_levelupresItemSlot) != null) {
            levelupresItemSlot = c.getInt(MainConfigPath.path_levelupresItemSlot);
        } else {
            sendError(MainConfigPath.path_levelupresItemSlot);
        }

        if (c.getString(MainConfigPath.path_dieOutGameItem_playAgain_ItemSlot) != null) {
            dieOutGameItem_playAgain_ItemSlot = c.getInt(MainConfigPath.path_dieOutGameItem_playAgain_ItemSlot);
        } else {
            sendError(MainConfigPath.path_dieOutGameItem_playAgain_ItemSlot);
        }

        l("<" + name + "> " + finishLoadConfig);

        KitConfigLoad.loadKitConfig();
        LangConfigLoad.loadLangConfig();
        TaskConfigLoad.loadTaskConfig();
        loadLevelUpInv();


        if (!firstload) {
            if (executer != null) {
                executer.sendMessage(t(messreloadsucc));
            }
        }else {
            regListener();
        }
    }
}