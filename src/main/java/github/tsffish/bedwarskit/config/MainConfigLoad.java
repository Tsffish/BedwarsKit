package github.tsffish.bedwarskit.config;

import github.tsffish.bedwarskit.Main;
import github.tsffish.bedwarskit.listener.PlayerDamageHandler;
import github.tsffish.bedwarskit.listener.bedwarsrel.*;
import github.tsffish.bedwarskit.util.misc.ColorString;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import static github.tsffish.bedwarskit.config.MainConfigHandler.*;
import static github.tsffish.bedwarskit.util.RelShopLevelUp.loadLevelUpInv;
import static github.tsffish.bedwarskit.util.RelTeamEnchant.loadMapTeam;
import static github.tsffish.bedwarskit.util.misc.ConfigVersionCheck.ccv;
import static github.tsffish.bedwarskit.util.misc.InfoLogger.l;

public class MainConfigLoad extends JavaPlugin {
    static Plugin plugin = Main.getProvidingPlugin(Main.class);
    public static void loadMainConfig(CommandSender executer, boolean firstload) {

        breakTitle = false;
        noHunger = false;
        noPearlDamage = false;
        breakBedCheck = false;
        deathGameMode = false;
        startKitCompass = false;
        customScoreboard = false;
        antiDrop = false;
        grassPaneWalk = false;
        cleanBottle = false;
        levelupShopDelayOpen = false;
        kill_res = false;
        NoItemBreak = false;
        levelupShop = false;
        chainPrice = 0;
        ironPrice = 0;
        diamondPrice = 0;
        sharp1Cost2v2 = 0;
        prot1Cost2v2 = 0;
        prot2Cost2v2 = 0;
        prot3Cost2v2 = 0;
        prot4Cost2v2 = 0;
        sharp1Cost4v4 = 0;
        prot1Cost4v4 = 0;
        prot2Cost4v4 = 0;
        prot3Cost4v4 = 0;
        prot4Cost4v4 = 0;
        maxFoodLevel = 0;
        respawnDelay = 0;
        tpDis = 0;
        breakTitleAll = null;
        breakSubTitleAll = null;
        breakTitleBreakPlayer = null;
        breakSubTitleBreakPlayer = null;
        breakTitleBreakTeam = null;
        breakSubTitleBreakTeam = null;

        rushWorld = null;
        rushWorld2v2 = null;
        rushWorld4v4 = null;
        lobbyWorld = null;
        respawnTitle = null;
        respawnSubTitle = null;
        respawnSuccTitle = null;
        respawnSuccSubTitle = null;
        meanTeamBedYes = null;
        meanTeamBedNo = null;
        meanTeamNone = null;
        meanYou = null;
        meanNotYou = null;
        serverIp = null;
        relTeamName_Red = null;
        relTeamName_Blue = null;
        relTeamName_Green = null;
        relTeamName_Yellow = null;
        relTeamName_Aqua = null;
        relTeamName_White = null;
        relTeamName_Gray = null;
        relTeamName_Pink = null;
        teamEnchItemName_Sharp1 = null;
        teamEnchItemName_Prot1 = null;
        teamEnchItemName_Prot2 = null;
        teamEnchItemName_Prot3 = null;
        teamEnchItemName_Prot4 = null;
        shopLevelup = null;
        shopItem = null;
        meanDiamond = null;
        messLevelUpSharp1 = null;
        messLevelUpProt1 = null;
        messLevelUpProt2 = null;
        messLevelUpProt3 = null;
        messLevelUpProt4 = null;
        messLevelUpFailed = null;
        messreloadnow = null;
        messreloadsucc = null;
        meanBedwars = null;
        mean2v2Mode = null;
        mean4v4Mode = null;
        levelupShopOpenMode = null;
        levelupShopOpenModeEntityName = null;
        ScoreBoard2v2Title = null;
        ScoreBoard2v2Line01 = null;
        ScoreBoard2v2Line02 = null;
        ScoreBoard2v2Line03 = null;
        ScoreBoard2v2Line04 = null;
        ScoreBoard2v2Line05 = null;
        ScoreBoard2v2Line06 = null;
        ScoreBoard2v2Line07 = null;
        ScoreBoard2v2Line08 = null;
        ScoreBoard2v2Line09 = null;
        ScoreBoard2v2Line10 = null;
        ScoreBoard2v2Line11 = null;
        ScoreBoard2v2Line12 = null;
        ScoreBoard2v2Line13 = null;
        ScoreBoard2v2Line14 = null;
        ScoreBoard2v2Line15 = null;
        ScoreBoard2v2Line16 = null;
        ScoreBoard4v4Title = null;
        ScoreBoard4v4Line01 = null;
        ScoreBoard4v4Line02 = null;
        ScoreBoard4v4Line03 = null;
        ScoreBoard4v4Line04 = null;
        ScoreBoard4v4Line05 = null;
        ScoreBoard4v4Line06 = null;
        ScoreBoard4v4Line07 = null;
        ScoreBoard4v4Line08 = null;
        ScoreBoard4v4Line09 = null;
        ScoreBoard4v4Line10 = null;
        ScoreBoard4v4Line11 = null;
        ScoreBoard4v4Line12 = null;
        ScoreBoard4v4Line13 = null;
        ScoreBoard4v4Line14 = null;
        ScoreBoard4v4Line15 = null;
        ScoreBoard4v4Line16 = null;
        ScoreBoard2v2Line = null;
        ScoreBoard4v4Line = null;

        meanSelKitSucc = null;

        LevelupItemType = null;
        chainPriceType = null;
        ironPriceType = null;
        diamondPriceType = null;
        upToChainArmor = null;
        upToIronArmor = null;
        upToDiamondArmor = null;
        NoMoveList = null;
        NoBreakList = null;

        damagefb_attackBlood = false;
        damagefb_attackBloodMode = null;
        damagefb_Title = false;
        damagefb_attackTitle = null;
        damagefb_attackSubTitle = null;

        startmess = false;
        startmess_all_chat = null;
        startmess_all_title = null;
        startmess_all_subtitle = null;


        Bukkit.getPluginManager().getPlugin("BedwarsKit").saveDefaultConfig();

        //if(firstload){
        //MainConfigDefault.loadMainConfigDefault();
        //KitConfigLoad.loadKitConfig();
        //loadMapTeam(100L);

        //}


        c = Bukkit.getPluginManager().getPlugin("BedwarsKit").getConfig();
        if (c == null) {
            Bukkit.getPluginManager().getPlugin("BedwarsKit").saveDefaultConfig();
            c = Bukkit.getPluginManager().getPlugin("BedwarsKit").getConfig();
            Bukkit.getPluginManager().getPlugin("BedwarsKit").reloadConfig();
        }

        if (c == null) {
            MainConfigDefault.loadMainConfigDefault();
            Logger l = Bukkit.getLogger();
            l.warning("Unable to find configuration file, loading default configuration");
        } else {


            Bukkit.getPluginManager().getPlugin("BedwarsKit").reloadConfig();

            ccv(Main.pluginVersion);


                if (c.getString(MainConfigPath.path_messreloadnow) != null) {
                    messreloadnow = c.getString(MainConfigPath.path_messreloadnow);
                } else {
                    messreloadnow = "正在重新加载配置文件";
                }

                if (c.getString(MainConfigPath.path_messreloadsucc) != null) {
                    messreloadsucc = c.getString(MainConfigPath.path_messreloadsucc);
                } else {
                    messreloadsucc = "成功重载配置文件";
                }

            if (!firstload) {
                if (executer != null) {
                    executer.sendMessage(ColorString.t(messreloadnow));
                }
            }

                if (c.getString(MainConfigPath.path_grassPaneWalk) != null) {
                    grassPaneWalk = c.getBoolean(MainConfigPath.path_grassPaneWalk);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_grassPaneWalk, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_noHunger) != null) {
                    noHunger = c.getBoolean(MainConfigPath.path_noHunger);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_noHunger, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_noPearlDamage) != null) {
                    noPearlDamage = c.getBoolean(MainConfigPath.path_noPearlDamage);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_noPearlDamage, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_breakBedCheck) != null) {
                    breakBedCheck = c.getBoolean(MainConfigPath.path_breakBedCheck);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_breakBedCheck, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_levelupShop) != null) {
                    levelupShop = c.getBoolean(MainConfigPath.path_levelupShop);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_levelupShop, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_deathGameMode) != null) {
                    deathGameMode = c.getBoolean(MainConfigPath.path_deathGameMode);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_deathGameMode, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_startKitCompass) != null) {
                    startKitCompass = c.getBoolean(MainConfigPath.path_startKitCompass);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_startKitCompass, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_customScoreboard) != null) {
                    customScoreboard = c.getBoolean(MainConfigPath.path_customScoreboard);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_customScoreboard, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_antiDrop) != null) {
                    antiDrop = c.getBoolean(MainConfigPath.path_antiDrop);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_antiDrop, "vaule is null");
                }
                if (c.getString(MainConfigPath.path_NoItemBreak) != null) {
                    NoItemBreak = c.getBoolean(MainConfigPath.path_NoItemBreak);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_NoItemBreak, "vaule is null");
                }


                if (c.getString(MainConfigPath.path_kill_res) != null) {
                    kill_res = c.getBoolean(MainConfigPath.path_kill_res);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_kill_res, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_meanBedwars) != null) {
                    meanBedwars = c.getString(MainConfigPath.path_meanBedwars);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_meanBedwars, "vaule is null");
                }
                if (c.getString(MainConfigPath.path_mean2v2Mode) != null) {
                    mean2v2Mode = c.getString(MainConfigPath.path_mean2v2Mode);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_mean2v2Mode, "vaule is null");
                }
                if (c.getString(MainConfigPath.path_mean4v4Mode) != null) {
                    mean4v4Mode = c.getString(MainConfigPath.path_mean4v4Mode);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_mean4v4Mode, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_breakTitle) != null) {
                    breakTitle = c.getBoolean(MainConfigPath.path_breakTitle);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_breakTitle, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_levelupShopOpenMode) != null) {
                    levelupShopOpenMode = c.getString(MainConfigPath.path_levelupShopOpenMode);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_levelupShopOpenMode, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_levelupShopOpenModeEntityName) != null) {
                    levelupShopOpenModeEntityName = c.getString(MainConfigPath.path_levelupShopOpenModeEntityName);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_levelupShopOpenModeEntityName, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_ScoreBoard2v2Title) != null) {
                    ScoreBoard2v2Title = c.getString(MainConfigPath.path_ScoreBoard2v2Title);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_ScoreBoard2v2Title, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_ScoreBoard2v2Line01) != null) {
                    ScoreBoard2v2Line01 = c.getString(MainConfigPath.path_ScoreBoard2v2Line01);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_ScoreBoard2v2Line01, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_ScoreBoard2v2Line02) != null) {
                    ScoreBoard2v2Line02 = c.getString(MainConfigPath.path_ScoreBoard2v2Line02);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_ScoreBoard2v2Line02, "vaule is null");
                }
                if (c.getString(MainConfigPath.path_ScoreBoard2v2Line03) != null) {
                    ScoreBoard2v2Line03 = c.getString(MainConfigPath.path_ScoreBoard2v2Line03);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_ScoreBoard2v2Line03, "vaule is null");
                }
                if (c.getString(MainConfigPath.path_ScoreBoard2v2Line04) != null) {
                    ScoreBoard2v2Line04 = c.getString(MainConfigPath.path_ScoreBoard2v2Line04);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_ScoreBoard2v2Line04, "vaule is null");
                }
                if (c.getString(MainConfigPath.path_ScoreBoard2v2Line05) != null) {
                    ScoreBoard2v2Line05 = c.getString(MainConfigPath.path_ScoreBoard2v2Line05);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_ScoreBoard2v2Line05, "vaule is null");
                }
                if (c.getString(MainConfigPath.path_ScoreBoard2v2Line06) != null) {
                    ScoreBoard2v2Line06 = c.getString(MainConfigPath.path_ScoreBoard2v2Line06);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_ScoreBoard2v2Line06, "vaule is null");
                }
                if (c.getString(MainConfigPath.path_ScoreBoard2v2Line07) != null) {
                    ScoreBoard2v2Line07 = c.getString(MainConfigPath.path_ScoreBoard2v2Line07);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_ScoreBoard2v2Line07, "vaule is null");
                }
                if (c.getString(MainConfigPath.path_ScoreBoard2v2Line08) != null) {
                    ScoreBoard2v2Line08 = c.getString(MainConfigPath.path_ScoreBoard2v2Line08);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_ScoreBoard2v2Line08, "vaule is null");
                }
                if (c.getString(MainConfigPath.path_ScoreBoard2v2Line09) != null) {
                    ScoreBoard2v2Line09 = c.getString(MainConfigPath.path_ScoreBoard2v2Line09);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_ScoreBoard2v2Line09, "vaule is null");
                }
                if (c.getString(MainConfigPath.path_ScoreBoard2v2Line10) != null) {
                    ScoreBoard2v2Line10 = c.getString(MainConfigPath.path_ScoreBoard2v2Line10);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_ScoreBoard2v2Line10, "vaule is null");
                }
                if (c.getString(MainConfigPath.path_ScoreBoard2v2Line11) != null) {
                    ScoreBoard2v2Line11 = c.getString(MainConfigPath.path_ScoreBoard2v2Line11);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_ScoreBoard2v2Line11, "vaule is null");
                }
                if (c.getString(MainConfigPath.path_ScoreBoard2v2Line12) != null) {
                    ScoreBoard2v2Line12 = c.getString(MainConfigPath.path_ScoreBoard2v2Line12);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_ScoreBoard2v2Line12, "vaule is null");
                }
                if (c.getString(MainConfigPath.path_ScoreBoard2v2Line13) != null) {
                    ScoreBoard2v2Line13 = c.getString(MainConfigPath.path_ScoreBoard2v2Line13);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_ScoreBoard2v2Line13, "vaule is null");
                }
                if (c.getString(MainConfigPath.path_ScoreBoard2v2Line14) != null) {
                    ScoreBoard2v2Line14 = c.getString(MainConfigPath.path_ScoreBoard2v2Line14);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_ScoreBoard2v2Line14, "vaule is null");
                }
                if (c.getString(MainConfigPath.path_ScoreBoard2v2Line15) != null) {
                    ScoreBoard2v2Line15 = c.getString(MainConfigPath.path_ScoreBoard2v2Line15);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_ScoreBoard2v2Line15, "vaule is null");
                }
                if (c.getString(MainConfigPath.path_ScoreBoard2v2Line10) != null) {
                    ScoreBoard2v2Line16 = c.getString(MainConfigPath.path_ScoreBoard2v2Line16);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_ScoreBoard2v2Line16, "vaule is null");
                }


                if (c.getString(MainConfigPath.path_ScoreBoard4v4Title) != null) {
                    ScoreBoard4v4Title = c.getString(MainConfigPath.path_ScoreBoard4v4Title);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_ScoreBoard4v4Title, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_ScoreBoard4v4Line01) != null) {
                    ScoreBoard4v4Line01 = c.getString(MainConfigPath.path_ScoreBoard4v4Line01);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_ScoreBoard4v4Line01, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_ScoreBoard4v4Line02) != null) {
                    ScoreBoard4v4Line02 = c.getString(MainConfigPath.path_ScoreBoard4v4Line02);
                } else {
                    ErrorConfigHandler.er("Main", MainConfigPath.path_ScoreBoard4v4Line02, "vaule is null");
                }
                if (c.getString(MainConfigPath.path_ScoreBoard4v4Line03) != null) {
                    ScoreBoard4v4Line03 = c.getString(MainConfigPath.path_ScoreBoard4v4Line03);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_ScoreBoard4v4Line03, "vaule is null");
                }
                if (c.getString(MainConfigPath.path_ScoreBoard4v4Line04) != null) {
                    ScoreBoard4v4Line04 = c.getString(MainConfigPath.path_ScoreBoard4v4Line04);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_ScoreBoard4v4Line04, "vaule is null");
                }
                if (c.getString(MainConfigPath.path_ScoreBoard4v4Line05) != null) {
                    ScoreBoard4v4Line05 = c.getString(MainConfigPath.path_ScoreBoard4v4Line05);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_ScoreBoard4v4Line05, "vaule is null");
                }
                if (c.getString(MainConfigPath.path_ScoreBoard4v4Line06) != null) {
                    ScoreBoard4v4Line06 = c.getString(MainConfigPath.path_ScoreBoard4v4Line06);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_ScoreBoard4v4Line06, "vaule is null");
                }
                if (c.getString(MainConfigPath.path_ScoreBoard4v4Line07) != null) {
                    ScoreBoard4v4Line07 = c.getString(MainConfigPath.path_ScoreBoard4v4Line07);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_ScoreBoard4v4Line07, "vaule is null");
                }
                if (c.getString(MainConfigPath.path_ScoreBoard4v4Line08) != null) {
                    ScoreBoard4v4Line08 = c.getString(MainConfigPath.path_ScoreBoard4v4Line08);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_ScoreBoard4v4Line08, "vaule is null");
                }
                if (c.getString(MainConfigPath.path_ScoreBoard4v4Line09) != null) {
                    ScoreBoard4v4Line09 = c.getString(MainConfigPath.path_ScoreBoard4v4Line09);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_ScoreBoard4v4Line09, "vaule is null");
                }
                if (c.getString(MainConfigPath.path_ScoreBoard4v4Line10) != null) {
                    ScoreBoard4v4Line10 = c.getString(MainConfigPath.path_ScoreBoard4v4Line10);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_ScoreBoard4v4Line10, "vaule is null");
                }
                if (c.getString(MainConfigPath.path_ScoreBoard4v4Line11) != null) {
                    ScoreBoard4v4Line11 = c.getString(MainConfigPath.path_ScoreBoard4v4Line11);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_ScoreBoard4v4Line11, "vaule is null");
                }
                if (c.getString(MainConfigPath.path_ScoreBoard4v4Line12) != null) {
                    ScoreBoard4v4Line12 = c.getString(MainConfigPath.path_ScoreBoard4v4Line12);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_ScoreBoard4v4Line12, "vaule is null");
                }
                if (c.getString(MainConfigPath.path_ScoreBoard4v4Line13) != null) {
                    ScoreBoard4v4Line13 = c.getString(MainConfigPath.path_ScoreBoard4v4Line13);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_ScoreBoard4v4Line13, "vaule is null");
                }
                if (c.getString(MainConfigPath.path_ScoreBoard4v4Line14) != null) {
                    ScoreBoard4v4Line14 = c.getString(MainConfigPath.path_ScoreBoard4v4Line14);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_ScoreBoard4v4Line14, "vaule is null");
                }
                if (c.getString(MainConfigPath.path_ScoreBoard4v4Line15) != null) {
                    ScoreBoard4v4Line15 = c.getString(MainConfigPath.path_ScoreBoard4v4Line15);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_ScoreBoard4v4Line15, "vaule is null");
                }
                if (c.getString(MainConfigPath.path_ScoreBoard4v4Line10) != null) {
                    ScoreBoard4v4Line16 = c.getString(MainConfigPath.path_ScoreBoard4v4Line16);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_ScoreBoard4v4Line16, "vaule is null");
                }


                if (c.getString(MainConfigPath.path_meanSelKitSucc) != null) {
                    meanSelKitSucc = c.getString(MainConfigPath.path_meanSelKitSucc);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_meanSelKitSucc, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_cleanBottle) != null) {
                    cleanBottle = c.getBoolean(MainConfigPath.path_cleanBottle);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_cleanBottle, "vaule is null");
                }


                // INt
                if (c.getString(MainConfigPath.path_chainPrice) != null) {
                    chainPrice = c.getInt(MainConfigPath.path_chainPrice);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_chainPrice, "vaule is null");
                }
                if (c.getString(MainConfigPath.path_ironPrice) != null) {
                    ironPrice = c.getInt(MainConfigPath.path_ironPrice);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_ironPrice, "vaule is null");
                }
                if (c.getString(MainConfigPath.path_diamondPrice) != null) {
                    diamondPrice = c.getInt(MainConfigPath.path_diamondPrice);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_diamondPrice, "vaule is null");
                }


                if (c.getString(MainConfigPath.path_sharp1Cost2v2) != null) {
                    sharp1Cost2v2 = c.getInt(MainConfigPath.path_sharp1Cost2v2);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_sharp1Cost2v2, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_respawnDelay) != null) {
                    respawnDelay = c.getInt(MainConfigPath.path_respawnDelay);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_respawnDelay, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_maxFoodLevel) != null) {
                    maxFoodLevel = c.getInt(MainConfigPath.path_maxFoodLevel);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_maxFoodLevel, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_prot1Cost2v2) != null) {
                    prot1Cost2v2 = c.getInt(MainConfigPath.path_prot1Cost2v2);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_prot1Cost2v2, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_prot2Cost2v2) != null) {
                    prot2Cost2v2 = c.getInt(MainConfigPath.path_prot2Cost2v2);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_prot2Cost2v2, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_prot3Cost2v2) != null) {
                    prot3Cost2v2 = c.getInt(MainConfigPath.path_prot3Cost2v2);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_prot3Cost2v2, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_prot4Cost2v2) != null) {
                    prot4Cost2v2 = c.getInt(MainConfigPath.path_prot4Cost2v2);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_prot4Cost2v2, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_sharp1Cost4v4) != null) {
                    sharp1Cost4v4 = c.getInt(MainConfigPath.path_sharp1Cost4v4);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_sharp1Cost4v4, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_prot1Cost4v4) != null) {
                    prot1Cost4v4 = c.getInt(MainConfigPath.path_prot1Cost4v4);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_prot1Cost4v4, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_prot2Cost4v4) != null) {
                    prot2Cost4v4 = c.getInt(MainConfigPath.path_prot2Cost4v4);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_prot2Cost4v4, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_prot3Cost4v4) != null) {
                    prot3Cost4v4 = c.getInt(MainConfigPath.path_prot3Cost4v4);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_prot3Cost4v4, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_prot4Cost4v4) != null) {
                    prot4Cost4v4 = c.getInt(MainConfigPath.path_prot4Cost4v4);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_prot4Cost4v4, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_tpDis) != null) {
                    tpDis = c.getDouble(MainConfigPath.path_tpDis);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_tpDis, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_levelupShopDelayOpen) != null) {
                    levelupShopDelayOpen = c.getBoolean(MainConfigPath.path_levelupShopDelayOpen);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_levelupShopDelayOpen, "vaule is null");
                }

                // Mate

                if (c.getString(MainConfigPath.path_LevelupItemType) != null) {
                    LevelupItemType = Material.getMaterial(c.getString(MainConfigPath.path_LevelupItemType).toUpperCase());
                } else {
                    //LevelupItemType = Material.BOOK_AND_QUILL;
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_LevelupItemType, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_chainPriceType) != null) {
                    chainPriceType = Material.getMaterial(c.getString(MainConfigPath.path_chainPriceType));
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_chainPriceType, "vaule is null");
                }
                if (c.getString(MainConfigPath.path_ironPriceType) != null) {
                    ironPriceType = Material.getMaterial(c.getString(MainConfigPath.path_ironPriceType));
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_ironPriceType, "vaule is null");
                }
                if (c.getString(MainConfigPath.path_diamondPriceType) != null) {
                    diamondPriceType = Material.getMaterial(c.getString(MainConfigPath.path_diamondPriceType));
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_diamondPriceType, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_upToChainArmor) != null) {
                    upToChainArmor = Material.getMaterial(c.getString(MainConfigPath.path_upToChainArmor));
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_upToChainArmor, "vaule is null");
                }
                if (c.getString(MainConfigPath.path_upToIronArmor) != null) {
                    upToIronArmor = Material.getMaterial(c.getString(MainConfigPath.path_upToIronArmor));
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_upToIronArmor, "vaule is null");
                }
                if (c.getString(MainConfigPath.path_upToDiamondArmor) != null) {
                    upToDiamondArmor = Material.getMaterial(c.getString(MainConfigPath.path_upToDiamondArmor));
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_upToDiamondArmor, "vaule is null");
                }

                NoMoveList = new ArrayList<>();
                NoMoveList.add(Material.LEATHER_HELMET);
                NoMoveList.add(Material.LEATHER_CHESTPLATE);
                NoMoveList.add(Material.LEATHER_LEGGINGS);
                NoMoveList.add(Material.LEATHER_BOOTS);
                NoMoveList.add(Material.CHAINMAIL_HELMET);
                NoMoveList.add(Material.CHAINMAIL_CHESTPLATE);
                NoMoveList.add(Material.CHAINMAIL_LEGGINGS);
                NoMoveList.add(Material.CHAINMAIL_BOOTS);
                NoMoveList.add(Material.IRON_HELMET);
                NoMoveList.add(Material.IRON_CHESTPLATE);
                NoMoveList.add(Material.IRON_LEGGINGS);
                NoMoveList.add(Material.IRON_BOOTS);
                NoMoveList.add(Material.DIAMOND_HELMET);
                NoMoveList.add(Material.DIAMOND_CHESTPLATE);
                NoMoveList.add(Material.DIAMOND_LEGGINGS);
                NoMoveList.add(Material.DIAMOND_BOOTS);

                NoBreakList = new ArrayList<>();

                NoBreakList.add(Material.WOOD_SWORD);
                NoBreakList.add(Material.STONE_SWORD);
                NoBreakList.add(Material.IRON_SWORD);
                NoBreakList.add(Material.GOLD_SWORD);
                NoBreakList.add(Material.DIAMOND_SWORD);
                if (c.getString(MainConfigPath.path_messLevelUpFailed) != null) {
                    messLevelUpFailed = c.getString(MainConfigPath.path_messLevelUpFailed);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_messLevelUpFailed, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_messLevelUpSharp1) != null) {
                    messLevelUpSharp1 = c.getString(MainConfigPath.path_messLevelUpSharp1);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_messLevelUpSharp1, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_messLevelUpProt1) != null) {
                    messLevelUpProt1 = c.getString(MainConfigPath.path_messLevelUpProt1);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_messLevelUpProt1, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_messLevelUpProt2) != null) {
                    messLevelUpProt2 = c.getString(MainConfigPath.path_messLevelUpProt2);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_messLevelUpProt2, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_messLevelUpProt3) != null) {
                    messLevelUpProt3 = c.getString(MainConfigPath.path_messLevelUpProt3);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_messLevelUpProt3, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_messLevelUpProt4) != null) {
                    messLevelUpProt4 = c.getString(MainConfigPath.path_messLevelUpProt4);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_messLevelUpProt4, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_meanDiamond) != null) {
                    meanDiamond = c.getString(MainConfigPath.path_meanDiamond);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_meanDiamond, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_shopItem) != null) {
                    shopItem = c.getString(MainConfigPath.path_shopItem);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_shopItem, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_shopLevelup) != null) {
                    shopLevelup = c.getString(MainConfigPath.path_shopLevelup);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_shopLevelup, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_teamEnchItemName_Sharp1) != null) {
                    teamEnchItemName_Sharp1 = c.getString(MainConfigPath.path_teamEnchItemName_Sharp1);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_teamEnchItemName_Sharp1, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_teamEnchItemName_Prot1) != null) {
                    teamEnchItemName_Prot1 = c.getString(MainConfigPath.path_teamEnchItemName_Prot1);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_teamEnchItemName_Prot1, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_teamEnchItemName_Prot2) != null) {
                    teamEnchItemName_Prot2 = c.getString(MainConfigPath.path_teamEnchItemName_Prot2);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_teamEnchItemName_Prot2, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_teamEnchItemName_Prot3) != null) {
                    teamEnchItemName_Prot3 = c.getString(MainConfigPath.path_teamEnchItemName_Prot3);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_teamEnchItemName_Prot3, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_teamEnchItemName_Prot4) != null) {
                    teamEnchItemName_Prot4 = c.getString(MainConfigPath.path_teamEnchItemName_Prot4);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_teamEnchItemName_Prot4, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_relTeamName_Red) != null) {
                    relTeamName_Red = c.getString(MainConfigPath.path_relTeamName_Red);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_relTeamName_Red, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_relTeamName_Blue) != null) {
                    relTeamName_Blue = c.getString(MainConfigPath.path_relTeamName_Blue);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_relTeamName_Blue, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_relTeamName_Green) != null) {
                    relTeamName_Green = c.getString(MainConfigPath.path_relTeamName_Green);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_relTeamName_Green, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_relTeamName_Yellow) != null) {
                    relTeamName_Yellow = c.getString(MainConfigPath.path_relTeamName_Yellow);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_relTeamName_Yellow, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_relTeamName_Aqua) != null) {
                    relTeamName_Aqua = c.getString(MainConfigPath.path_relTeamName_Aqua);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_relTeamName_Aqua, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_relTeamName_White) != null) {
                    relTeamName_White = c.getString(MainConfigPath.path_relTeamName_White);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_relTeamName_White, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_relTeamName_Gray) != null) {
                    relTeamName_Gray = c.getString(MainConfigPath.path_relTeamName_Gray);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_relTeamName_Gray, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_relTeamName_Pink) != null) {
                    relTeamName_Pink = c.getString(MainConfigPath.path_relTeamName_Pink);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_relTeamName_Pink, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_breakTitleAll) != null) {
                    breakTitleAll = c.getString(MainConfigPath.path_breakTitleAll);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_breakTitleAll, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_breakSubTitleAll) != null) {
                    breakSubTitleAll = c.getString(MainConfigPath.path_breakSubTitleAll);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_breakSubTitleAll, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_breakTitleBreakPlayer) != null) {
                    breakTitleBreakPlayer = c.getString(MainConfigPath.path_breakTitleBreakPlayer);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_breakTitleBreakPlayer, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_breakSubTitleBreakPlayer) != null) {
                    breakSubTitleBreakPlayer = c.getString(MainConfigPath.path_breakSubTitleBreakPlayer);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_breakSubTitleBreakPlayer, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_breakTitleBreakTeam) != null) {
                    breakTitleBreakTeam = c.getString(MainConfigPath.path_breakTitleBreakTeam);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_breakTitleBreakTeam, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_breakSubTitleBreakTeam) != null) {
                    breakSubTitleBreakTeam = c.getString(MainConfigPath.path_breakSubTitleBreakTeam);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_breakSubTitleBreakTeam, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_rushWorld) != null) {
                    rushWorld = c.getString(MainConfigPath.path_rushWorld);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_rushWorld, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_rushWorld2v2) != null) {
                    rushWorld2v2 = c.getString(MainConfigPath.path_rushWorld2v2);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_rushWorld2v2, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_rushWorld4v4) != null) {
                    rushWorld4v4 = c.getString(MainConfigPath.path_rushWorld4v4);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_rushWorld4v4, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_lobbyWorld) != null) {
                    lobbyWorld = c.getString(MainConfigPath.path_lobbyWorld);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_lobbyWorld, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_respawnTitle) != null) {
                    respawnTitle = c.getString(MainConfigPath.path_respawnTitle);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_respawnTitle, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_respawnSubTitle) != null) {
                    respawnSubTitle = c.getString(MainConfigPath.path_respawnSubTitle);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_respawnSubTitle, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_respawnSuccTitle) != null) {
                    respawnSuccTitle = c.getString(MainConfigPath.path_respawnSuccTitle);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_respawnSuccTitle, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_respawnSuccSubTitle) != null) {
                    respawnSuccSubTitle = c.getString(MainConfigPath.path_respawnSuccSubTitle);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_respawnSuccSubTitle, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_meanTeamBedYes) != null) {
                    meanTeamBedYes = c.getString(MainConfigPath.path_meanTeamBedYes);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_meanTeamBedYes, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_meanTeamNone) != null) {
                    meanTeamNone = c.getString(MainConfigPath.path_meanTeamNone);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_meanTeamNone, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_meanTeamBedNo) != null) {
                    meanTeamBedNo = c.getString(MainConfigPath.path_meanTeamBedNo);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_meanTeamBedNo, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_serverIp) != null) {
                    serverIp = c.getString(MainConfigPath.path_serverIp);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_serverIp, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_meanYou) != null) {
                    meanYou = c.getString(MainConfigPath.path_meanYou);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_meanYou, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_meanNotYou) != null) {
                    meanNotYou = c.getString(MainConfigPath.path_meanNotYou);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_meanNotYou, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_damagefb_Title) != null) {
                    damagefb_Title = c.getBoolean(MainConfigPath.path_damagefb_Title);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_damagefb_Title, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_damagefb_attackTitle) != null) {
                    damagefb_attackTitle = c.getString(MainConfigPath.path_damagefb_attackTitle);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_damagefb_attackTitle, "vaule is null");
                }
                if (c.getString(MainConfigPath.path_damagefb_attackSubTitle) != null) {
                    damagefb_attackSubTitle = c.getString(MainConfigPath.path_damagefb_attackSubTitle);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_damagefb_attackSubTitle, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_damagefb_attackBlood) != null) {
                    damagefb_attackBlood = c.getBoolean(MainConfigPath.path_damagefb_attackBlood);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_damagefb_attackBlood, "vaule is null");
                }

                if (c.getString(MainConfigPath.path_damagefb_attackBloodMode) != null) {
                    damagefb_attackBloodMode = c.getString(MainConfigPath.path_damagefb_attackBloodMode);
                } else {
                    ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_damagefb_attackBloodMode, "vaule is null");
                }


            if (c.getString(MainConfigPath.path_startmess) != null) {
                startmess = c.getBoolean(MainConfigPath.path_startmess);
            } else {
                ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_startmess, "vaule is null");
            }



            if (c.getString(MainConfigPath.path_startmess_all_chat) != null) {
                startmess_all_chat = c.getString(MainConfigPath.path_startmess_all_chat);
            } else {
                ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_startmess_all_chat, "vaule is null");
            }
            if (c.getString(MainConfigPath.path_startmess_all_title) != null) {
                startmess_all_title = c.getString(MainConfigPath.path_startmess_all_title);
            } else {
                ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_startmess_all_title, "vaule is null");
            }
            if (c.getString(MainConfigPath.path_startmess_all_subtitle) != null) {
                startmess_all_subtitle = c.getString(MainConfigPath.path_startmess_all_subtitle);
            } else {
                ErrorConfigHandler.er("MainConfigLoad", MainConfigPath.path_startmess_all_subtitle, "vaule is null");
            }

                MainConfigHandler.ScoreBoard2v2Line = new HashMap<>();
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

                MainConfigHandler.ScoreBoard4v4Line = new HashMap<>();
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


                l("<MainConfigLoad> Finish Load Config");

                KitConfigLoad.loadKitConfig();
                loadMapTeam(100L);
                loadLevelUpInv();


                if (!firstload) {
                    if (executer != null) {
                        executer.sendMessage(ColorString.t(messreloadsucc));
                    }
                }else {
                    PluginManager pm = Bukkit.getPluginManager();
                    pm.registerEvents(new RelBreakBed(), plugin);
                    pm.registerEvents(new RelBreakCorrect(), plugin);

                    pm.registerEvents(new RelClickHandler(), plugin);
                    pm.registerEvents(new RelClickInventory(), plugin);
                    pm.registerEvents(new RelEnchant(), plugin);

                    pm.registerEvents(new RelFoodLock(), plugin);

                    pm.registerEvents(new RelGameEnd(), plugin);
                    pm.registerEvents(new RelGameOver(), plugin);

                    pm.registerEvents(new RelGameStarted(), plugin);

                    pm.registerEvents(new RelKillPlayer(),plugin);
                    //pm.registerEvents(new RelPlaceCorrect(), this);
                    pm.registerEvents(new RelNoItemBreak(), plugin);

                    pm.registerEvents(new RelPlayerDeath(), plugin);
                    pm.registerEvents(new RelPlayerDrop(), plugin);
                    pm.registerEvents(new RelPlayerJoin(), plugin);

                    pm.registerEvents(new RelPlayerLeave(), plugin);
                    pm.registerEvents(new RelPlayerMove(), plugin);
                    pm.registerEvents(new RelPlayerOpenShop(),plugin);

                    pm.registerEvents(new RelPlayerRespawn(), plugin);
                    pm.registerEvents(new RelPlayerTeleport(), plugin);


                    pm.registerEvents(new PlayerDamageHandler(), plugin);
                    //pm.registerEvents(new RelTeamSeletor(), this);
                    l("BedwarsRel found, related support enable");
                }
            }
        }
    }