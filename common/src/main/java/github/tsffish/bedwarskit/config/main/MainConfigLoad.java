package github.tsffish.bedwarskit.config.main;

import github.tsffish.bedwarskit.BedwarsKit;
import github.tsffish.bedwarskit.config.kit.KitConfigLoad;
import github.tsffish.bedwarskit.config.rel.RelConfigLoad;
import github.tsffish.bedwarskit.config.res.ResConfigLoad;
import github.tsffish.bedwarskit.config.scb.ScbConfigLoad;
import github.tsffish.bedwarskit.config.task.TaskConfigLoad;
import github.tsffish.bedwarskit.listener.RelMapProtect;
import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.ResourceSpawner;
import io.github.bedwarsrel.game.Team;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.inventory.InventoryType;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static github.tsffish.bedwarskit.config.lang.LangConfigHandler.*;
import static github.tsffish.bedwarskit.config.lang.LangConfigLoad.loadLangConfig;
import static github.tsffish.bedwarskit.config.main.MainConfigHandler.*;
import static github.tsffish.bedwarskit.config.misc.ConfigVersionChecker.checkAndRenameConfig;
import static github.tsffish.bedwarskit.config.misc.ErrorConfigHandler.er;
import static github.tsffish.bedwarskit.config.misc.TaskLoadRelConfig.loadRelConfigTask;
import static github.tsffish.bedwarskit.util.PluginInit.tipHaveChinese;
import static github.tsffish.bedwarskit.util.PluginState.*;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;
import static github.tsffish.bedwarskit.util.misc.MessSender.l;
import static github.tsffish.bedwarskit.util.misc.MessSender.le;
import static github.tsffish.bedwarskit.util.misc.StartCheck.checkUpdate;
import static github.tsffish.bedwarskit.util.misc.StringMgr.pluginName;
import static github.tsffish.bedwarskit.util.teamshop.ShopMenu.loadLevelUpInv;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class MainConfigLoad {
    private static final String className = MainConfigLoad.class.getSimpleName();
    private static final BedwarsKit plugin = BedwarsKit.getInstance();
    private static String reason;
    private static void sendError(String path) {
        er(className, path, reason);
    }

    public static void loadMainConfig(CommandSender executer, boolean firstload) {
        reason = vauleIsNull;

        String fileName = "config.yml";
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

        if (c.get(MainConfigPath.path_messreloadnow) != null) {
            messreloadnow = c.getString(MainConfigPath.path_messreloadnow);
        } else {
            messreloadnow = t("&b" + pluginName + " &7>> &eReloading configuration file");
            sendError(MainConfigPath.path_messreloadnow);
        }


        if (c.get(MainConfigPath.path_messreloadsucc) != null) {
            messreloadsucc = c.getString(MainConfigPath.path_messreloadsucc);
        } else {
            messreloadsucc = t("&b" + pluginName + " &7>> &aSuccessfully reloaded configuration file");
            sendError(MainConfigPath.path_messreloadsucc);
        }

        if (!firstload) {
            if (executer != null) {
                executer.sendMessage(t(messreloadnow));
            }
        }else {
            loadLangConfig();
        }

        if (firstload) {
            loadRelConfigTask();

            if (c.get(MainConfigPath.path_update_checker) != null) {
                boolean update_checker = c.getBoolean(MainConfigPath.path_update_checker);
                if (update_checker) {
                    checkUpdate(spigotId());
                }
            } else {
                sendError(MainConfigPath.path_update_checker);
                checkUpdate(spigotId());
            }
        }


        if (1 != 1) {
            for (Game game : BedwarsRel.getInstance().getGameManager().getGames()) {
                for (Team team : game.getTeams().values()) {
                    Location location = team.getSpawnLocation();
                    RelMapProtect.removeLocs(location, placeCorrect_PlayerSpawnLoc_dis);
                }
                for (ResourceSpawner spawner : game.getResourceSpawners()) {
                    Location location = spawner.getLocation();
                    RelMapProtect.removeLocs(location, placeCorrect_ResSpawner_dis);
                }
            }
        }

        try {
            extracted(c);
        } catch (Exception e) {
            le(className, e);
        }

        if (1 != 1) {
            for (Game game : BedwarsRel.getInstance().getGameManager().getGames()) {
                for (Team team : game.getTeams().values()) {
                    Location location = team.getSpawnLocation();
                    RelMapProtect.addLocs(location, placeCorrect_PlayerSpawnLoc_dis);
                }
                for (ResourceSpawner spawner : game.getResourceSpawners()) {
                    Location location = spawner.getLocation();
                    RelMapProtect.addLocs(location, placeCorrect_ResSpawner_dis);
                }
            }
        }

        if (isDebug()) {
            l("<" + className + "> " + finishLoadConfig);
        }

        loadLevelUpInv();
        RelConfigLoad.loadRelConfig();
        KitConfigLoad.loadKitConfig();
        TaskConfigLoad.loadTaskConfig();
        ResConfigLoad.loadResConfig();
        ScbConfigLoad.loadScbConfig();

        if (!firstload) {
            if (executer != null) {
                executer.sendMessage(t(messreloadsucc));
            }
        }
    }

    private static void extracted(FileConfiguration c) {
        update_reportOp = c.getBoolean(MainConfigPath.path_update_reportOp);

        noHunger = c.getBoolean(MainConfigPath.path_noHunger);

        noPearlDamage = c.getBoolean(MainConfigPath.path_noPearlDamage);

        killfb_oneHealthKill = c.getBoolean(MainConfigPath.path_killfb_oneHealthKill);

        killfb_oneHealthKill_itemType = Material.getMaterial(c.getString(MainConfigPath.path_killfb_oneHealthKill_itemType));

        killfb_oneHealthKill_itemName = c.getString(MainConfigPath.path_killfb_oneHealthKill_itemName);

        noPearlDamage_TPSound = c.getBoolean(MainConfigPath.path_noPearlDamage_TPSound);

        levelupShop = c.getBoolean(MainConfigPath.path_levelupShop);

        deathGameMode = c.getBoolean(MainConfigPath.path_deathGameMode);

        startKitCompass = c.getBoolean(MainConfigPath.path_startKitCompass);

        antiDrop = c.getBoolean(MainConfigPath.path_antiDrop);

        noItemBreak = c.getBoolean(MainConfigPath.path_NoItemBreak);

        kill_res = c.getBoolean(MainConfigPath.path_kill_res);

        breakTitle = c.getBoolean(MainConfigPath.path_breakTitle);

        levelupShopOpenMode = c.getString(MainConfigPath.path_levelupShopOpenMode);

        levelupShopOpenModeEntityName = c.getString(MainConfigPath.path_levelupShopOpenModeEntityName);

        cleanBottle = c.getBoolean(MainConfigPath.path_cleanBottle);

        chainPrice = c.getInt(MainConfigPath.path_chainPrice);

        ironPrice = c.getInt(MainConfigPath.path_ironPrice);

        diamondPrice = c.getInt(MainConfigPath.path_diamondPrice);

        sharp1Cost2v2 = c.getInt(MainConfigPath.path_sharp1Cost2v2);

        sharp2Cost2v2 = c.getInt(MainConfigPath.path_sharp2Cost2v2);

        sharp3Cost2v2 = c.getInt(MainConfigPath.path_sharp3Cost2v2);

        sharp4Cost2v2 = c.getInt(MainConfigPath.path_sharp4Cost2v2);

        sharp1Cost4v4 = c.getInt(MainConfigPath.path_sharp1Cost4v4);

        sharp2Cost4v4 = c.getInt(MainConfigPath.path_sharp2Cost4v4);

        sharp3Cost4v4 = c.getInt(MainConfigPath.path_sharp3Cost4v4);

        sharp4Cost4v4 = c.getInt(MainConfigPath.path_sharp4Cost4v4);

        respawnDelay = c.getInt(MainConfigPath.path_respawnDelay);

        maxFoodLevel = c.getInt(MainConfigPath.path_maxFoodLevel);

        prot1Cost2v2 = c.getInt(MainConfigPath.path_prot1Cost2v2);

        prot2Cost2v2 = c.getInt(MainConfigPath.path_prot2Cost2v2);

        prot3Cost2v2 = c.getInt(MainConfigPath.path_prot3Cost2v2);

        prot4Cost2v2 = c.getInt(MainConfigPath.path_prot4Cost2v2);

        sharp1Cost4v4 = c.getInt(MainConfigPath.path_sharp1Cost4v4);

        prot1Cost4v4 = c.getInt(MainConfigPath.path_prot1Cost4v4);

        prot2Cost4v4 = c.getInt(MainConfigPath.path_prot2Cost4v4);

        prot3Cost4v4 = c.getInt(MainConfigPath.path_prot3Cost4v4);

        prot4Cost4v4 = c.getInt(MainConfigPath.path_prot4Cost4v4);

        levelupShopDelayOpen = c.getBoolean(MainConfigPath.path_levelupShopDelayOpen);

        LevelupItemType = Material.getMaterial(c.getString(MainConfigPath.path_LevelupItemType).toUpperCase());

        chainPriceType = Material.getMaterial(c.getString(MainConfigPath.path_chainPriceType));

        ironPriceType = Material.getMaterial(c.getString(MainConfigPath.path_ironPriceType));

        diamondPriceType = Material.getMaterial(c.getString(MainConfigPath.path_diamondPriceType));

        upToChainArmor = Material.getMaterial(c.getString(MainConfigPath.path_upToChainArmor));

        upToIronArmor = Material.getMaterial(c.getString(MainConfigPath.path_upToIronArmor));

        upToDiamondArmor = Material.getMaterial(c.getString(MainConfigPath.path_upToDiamondArmor));

        if (giveProtEnchList == null) {
            giveProtEnchList = new ArrayList<>(4);
        } else {
            giveProtEnchList.clear();
        }

        if (!c.getStringList(MainConfigPath.path_giveProtEnchList).isEmpty()) {
            for (String list : c.getStringList(MainConfigPath.path_giveProtEnchList)) {
                giveProtEnchList.add(list.toUpperCase());
            }
        }

        if (giveSharpEnchList == null) {
            giveSharpEnchList = new ArrayList<>(4);
        } else {
            giveSharpEnchList.clear();
        }

        if (!c.getStringList(MainConfigPath.path_giveSharpEnchList).isEmpty()) {
            for (String list : c.getStringList(MainConfigPath.path_giveSharpEnchList)) {
                giveSharpEnchList.add(list.toUpperCase());
            }
        }

        if (noMoveList == null) {
            noMoveList = new ArrayList<>(4);
        } else {
            noMoveList.clear();
        }

        if (!c.getStringList(MainConfigPath.path_noMoveList).isEmpty()) {
            for (String list : c.getStringList(MainConfigPath.path_noMoveList)) {
                noMoveList.add(list.toUpperCase());
            }
        }

        if (nobreakList == null) {
            nobreakList = new ArrayList<>(48);
        } else {
            nobreakList.clear();
        }

        if (!c.getStringList(MainConfigPath.path_nobreakList).isEmpty())
            for (String list : c.getStringList(MainConfigPath.path_nobreakList)) {
                nobreakList.add(list.toUpperCase());
            }

        messLevelUpFailed = c.getString(MainConfigPath.path_messLevelUpFailed);

        messLevelUpsharp1 = c.getString(MainConfigPath.path_messLevelUpSharp1);

        messLevelUpsharp2 = c.getString(MainConfigPath.path_messLevelUpSharp2);

        messLevelUpsharp3 = c.getString(MainConfigPath.path_messLevelUpSharp3);

        messLevelUpsharp4 = c.getString(MainConfigPath.path_messLevelUpSharp4);

        messLevelUpprot1 = c.getString(MainConfigPath.path_messLevelUpProt1);

        messLevelUpprot2 = c.getString(MainConfigPath.path_messLevelUpProt2);

        messLevelUpprot3 = c.getString(MainConfigPath.path_messLevelUpProt3);

        messLevelUpprot4 = c.getString(MainConfigPath.path_messLevelUpProt4);

        meanIron = c.getString(MainConfigPath.path_meanIron);

        meanGold = c.getString(MainConfigPath.path_meanGold);

        meanDiamond = c.getString(MainConfigPath.path_meanDiamond);

        meanEmerlad = c.getString(MainConfigPath.path_meanEmerlad);

        shopLevelup = c.getString(MainConfigPath.path_shopLevelup);

        LevelupItemName = c.getString(MainConfigPath.path_LevelupItemName);

        teamEnchItemName_sharp1 = c.getString(MainConfigPath.path_teamEnchItemName_Sharp1);

        teamEnchItemName_prot1 = c.getString(MainConfigPath.path_teamEnchItemName_Prot1);

        teamEnchItemName_prot2 = c.getString(MainConfigPath.path_teamEnchItemName_Prot2);

        teamEnchItemName_prot3 = c.getString(MainConfigPath.path_teamEnchItemName_Prot3);

        teamEnchItemName_prot4 = c.getString(MainConfigPath.path_teamEnchItemName_Prot4);

        relTeamName_Red = c.getString(MainConfigPath.path_relTeamName_Red);

        relTeamName_Blue = c.getString(MainConfigPath.path_relTeamName_Blue);

        relTeamName_Green = c.getString(MainConfigPath.path_relTeamName_Green);

        relTeamName_Yellow = c.getString(MainConfigPath.path_relTeamName_Yellow);

        relTeamName_Aqua = c.getString(MainConfigPath.path_relTeamName_Aqua);

        relTeamName_White = c.getString(MainConfigPath.path_relTeamName_White);

        relTeamName_Gray = c.getString(MainConfigPath.path_relTeamName_Gray);

        relTeamName_Pink = c.getString(MainConfigPath.path_relTeamName_Pink);

        relTeamName_Orange = c.getString(MainConfigPath.path_relTeamName_Orange);

        breakTitleAll = c.getString(MainConfigPath.path_breakTitleAll);

        breakSubTitleAll = c.getString(MainConfigPath.path_breakSubTitleAll);

        breakTitleBreakPlayer = c.getString(MainConfigPath.path_breakTitleBreakPlayer);

        breakSubTitleBreakPlayer = c.getString(MainConfigPath.path_breakSubTitleBreakPlayer);

        breakTitleBreakTeam = c.getString(MainConfigPath.path_breakTitleBreakTeam);

        breakSubTitleBreakTeam = c.getString(MainConfigPath.path_breakSubTitleBreakTeam);

        gameWorld = c.getString(MainConfigPath.path_rushWorld);

        gameWorld2v2 = c.getString(MainConfigPath.path_rushWorld2v2);

        gameWorld4v4 = c.getString(MainConfigPath.path_rushWorld4v4);

        lobbyWorld = c.getString(MainConfigPath.path_lobbyWorld);

        respawnTitle = c.getString(MainConfigPath.path_respawnTitle);

        respawnSubTitle = c.getString(MainConfigPath.path_respawnSubTitle);

        respawnChat = c.getString(MainConfigPath.path_respawnChat);

        respawnActionBar = c.getString(MainConfigPath.path_respawnActionBar);

        respawnSuccTitle = c.getString(MainConfigPath.path_respawnSuccTitle);

        respawnSuccSubTitle = c.getString(MainConfigPath.path_respawnSuccSubTitle);

        respawnSuccChat = c.getString(MainConfigPath.path_respawnSuccChat);

        respawnSuccActionBar = c.getString(MainConfigPath.path_respawnSuccActionBar);

        damagefb_attackactionbar = c.getString(MainConfigPath.path_damagefb_attackactionbar);

        damagefb_attackchat = c.getString(MainConfigPath.path_damagefb_attackchat);

        damagefb_attackmess = c.getBoolean(MainConfigPath.path_damagefb_attackmess);

        damagefb_attackTitle = c.getString(MainConfigPath.path_damagefb_attackTitle);

        damagefb_attackSubTitle = c.getString(MainConfigPath.path_damagefb_attackSubTitle);

        damagefb_attackBlood = c.getBoolean(MainConfigPath.path_damagefb_attackBlood);

        damagefb_attackBloodMode = c.getString(MainConfigPath.path_damagefb_attackBloodMode);

        damagefb_attackactionbar = c.getString(MainConfigPath.path_damagefb_attackactionbar);

        damagefb_attackBloodType = c.getInt(MainConfigPath.path_damagefb_attackBloodType);

        startmess = c.getBoolean(MainConfigPath.path_startmess);

        startmess_all_chat = c.getString(MainConfigPath.path_startmess_all_chat);

        startmess_all_title = c.getString(MainConfigPath.path_startmess_all_title);

        startmess_all_subtitle = c.getString(MainConfigPath.path_startmess_all_subtitle);

        startmess_all_actionbar = c.getString(MainConfigPath.path_startmess_all_actionbar);

        placeCorrect_notInGame_OpBypass = c.getBoolean(MainConfigPath.path_placeCorrect_notInGame_OpBypass);

        breakCorrect_notInGame_OpBypass = c.getBoolean(MainConfigPath.path_breakCorrect_notInGame_OpBypass);

        cleanBed = c.getBoolean(MainConfigPath.path_cleanBed);

        preventLoadWorld = c.getBoolean(MainConfigPath.path_preventloadworld);

        cleanHostileOnStart = c.getBoolean(MainConfigPath.path_CleanHostileOnStart);

        placeCorrect_ResSpawner = c.getBoolean(MainConfigPath.path_placeCorrect_ResSpawner);

        placeCorrect_ResSpawner_mess_chat = c.getString(MainConfigPath.path_placeCorrect_ResSpawner_mess_chat);

        placeCorrect_ResSpawner_mess_title = c.getString(MainConfigPath.path_placeCorrect_ResSpawner_mess_title);

        placeCorrect_ResSpawner_mess_subtitle = c.getString(MainConfigPath.path_placeCorrect_ResSpawner_mess_subtitle);

        placeCorrect_ResSpawner_mess_actionbar = c.getString(MainConfigPath.path_placeCorrect_ResSpawner_mess_actionbar);

        placeCorrect_PlayerSpawnLoc = c.getBoolean(MainConfigPath.path_placeCorrect_PlayerSpawnLoc);

        placeCorrect_PlayerSpawnLoc_mess_chat = c.getString(MainConfigPath.path_placeCorrect_PlayerSpawnLoc_mess_chat);

        placeCorrect_PlayerSpawnLoc_mess_title = c.getString(MainConfigPath.path_placeCorrect_PlayerSpawnLoc_mess_title);

        placeCorrect_PlayerSpawnLoc_mess_subtitle = c.getString(MainConfigPath.path_placeCorrect_PlayerSpawnLoc_mess_subtitle);

        placeCorrect_PlayerSpawnLoc_mess_actionbar = c.getString(MainConfigPath.path_placeCorrect_PlayerSpawnLoc_mess_actionbar);

        breakCorrect_notInGame = c.getBoolean(MainConfigPath.path_breakCorrect_notInGame);

        placeCorrect_notInGame = c.getBoolean(MainConfigPath.path_placeCorrect_notInGame);

        killfb_sendmess = c.getBoolean(MainConfigPath.path_killfb_sendmess);

        killfb_sendmess_chat = c.getString(MainConfigPath.path_killfb_sendmess_chat);

        killfb_sendmess_title = c.getString(MainConfigPath.path_killfb_sendmess_title);

        killfb_sendmess_subtitle = c.getString(MainConfigPath.path_killfb_sendmess_subtitle);

        killfb_sendmess_actionbar = c.getString(MainConfigPath.path_killfb_sendmess_actionbar);

        kill_res_chat = c.getString(MainConfigPath.path_kill_res_chat);

        meanSecond = c.getString(MainConfigPath.path_meanSecond);

        shopItemEntityName = c.getString(MainConfigPath.path_shopItemEntityName);

        openShopOnCustomEntityName = c.getBoolean(MainConfigPath.path_openShopOnCustomEntityName);

        teamEff_Heal_dis = c.getInt(MainConfigPath.path_teamEff_Heal_dis);

        teamEnchInvRow = c.getInt(MainConfigPath.path_teamEnchInvRow);

        tab = c.getBoolean(MainConfigPath.path_tab);

        tab_is_multiLine = c.getBoolean(MainConfigPath.path_tab_is_multiLine);

        tab_headList = c.getStringList(MainConfigPath.path_tab_headList);

        if (c.get(MainConfigPath.path_tab_head) != null) {
            tab_head = c.getString(MainConfigPath.path_tab_head);
        } else {
            if (!tab_is_multiLine) {
                sendError(MainConfigPath.path_tab_head);
            }
        }

        if (c.getStringList(MainConfigPath.path_tab_footList) != null) {
            tab_footList = c.getStringList(MainConfigPath.path_tab_footList);
        } else {
            if (tab_is_multiLine) {
                sendError(MainConfigPath.path_tab_footList);
            }
        }

        if (c.get(MainConfigPath.path_tab_foot) != null) {
            tab_foot = c.getString(MainConfigPath.path_tab_foot);
        } else {
            if (!tab_is_multiLine) {
                sendError(MainConfigPath.path_tab_foot);
            }
        }

        levelupsharpItemType = Material.getMaterial(c.getString(MainConfigPath.path_levelupsharpItemType));

        levelupsharpItemSlot = c.getInt(MainConfigPath.path_levelupsharpItemSlot);

        levelupprotItemType = Material.getMaterial(c.getString(MainConfigPath.path_levelupprotItemType));

        levelupprotItemSlot = c.getInt(MainConfigPath.path_levelupprotItemSlot);

        teamEnchItemName_sharp2 = c.getString(MainConfigPath.path_teamEnchItemName_sharp2);

        teamEnchItemName_sharp3 = c.getString(MainConfigPath.path_teamEnchItemName_sharp3);

        teamEnchItemName_sharp4 = c.getString(MainConfigPath.path_teamEnchItemName_sharp4);

        TeamEnchantMaxCost = c.getString(MainConfigPath.path_TeamEnchantMaxCost);

        teamEnchItemName_sharpMax = c.getString(MainConfigPath.path_teamEnchItemName_sharpMax);

        teamEnchItemName_protMax = c.getString(MainConfigPath.path_teamEnchItemName_protMax);

        leveluphasteItemType = Material.getMaterial(c.getString(MainConfigPath.path_leveluphasteItemType));

        leveluphasteItemSlot = c.getInt(MainConfigPath.path_leveluphasteItemSlot);

        teamEffItemName_haste1 = c.getString(MainConfigPath.path_teamEffItemName_haste1);

        teamEffItemName_haste2 = c.getString(MainConfigPath.path_teamEffItemName_haste2);

        teamEffItemName_hasteMax = c.getString(MainConfigPath.path_teamEffItemName_hasteMax);

        leveluphealItemType = Material.getMaterial(c.getString(MainConfigPath.path_leveluphealItemType));

        leveluphealItemSlot = c.getInt(MainConfigPath.path_leveluphealItemSlot);

        teamEffItemName_heal1 = c.getString(MainConfigPath.path_teamEffItemName_heal1);

        teamEffItemName_healMax = c.getString(MainConfigPath.path_teamEffItemName_healMax);

        haste1Cost2v2 = c.getInt(MainConfigPath.path_haste1Cost2v2);

        haste2Cost2v2 = c.getInt(MainConfigPath.path_haste2Cost2v2);

        heal1Cost2v2 = c.getInt(MainConfigPath.path_heal1Cost2v2);

        haste1Cost4v4 = c.getInt(MainConfigPath.path_haste1Cost4v4);

        haste2Cost4v4 = c.getInt(MainConfigPath.path_haste2Cost4v4);

        heal1Cost4v4 = c.getInt(MainConfigPath.path_heal1Cost4v4);

        messLevelUphaste1 = c.getString(MainConfigPath.path_messLevelUpHaste1);

        messLevelUphaste2 = c.getString(MainConfigPath.path_messLevelUpHaste2);

        messLevelUpheal1 = c.getString(MainConfigPath.path_messLevelUpHeal1);

        teamEffItemName_Haste1 = c.getString(MainConfigPath.path_teamEffItemName_Haste1);

        teamEffItemName_Haste2 = c.getString(MainConfigPath.path_teamEffItemName_Haste2);

        teamEffItemName_Heal1 = c.getString(MainConfigPath.path_teamEffItemName_Heal1);

        dieOutGameItem_playAgain = c.getBoolean(MainConfigPath.path_dieOutGameItem_playAgain);

        dieOutGameItem_playAgain_ItemName = c.getString(MainConfigPath.path_dieOutGameItem_playAgain_ItemName);

        dieOutGameItem_playAgain_ItemType = Material.getMaterial(c.getString(MainConfigPath.path_dieOutGameItem_playAgain_ItemType));

        dieOutGameItem_playAgain_ClickSendCommand = c.getString(MainConfigPath.path_dieOutGameItem_playAgain_ClickSendCommand);

        lobbyjoinTeamMess_chat = c.getString(MainConfigPath.path_lobbyjoinTeamMess_chat);

        lobbyjoinTeamMess_title = c.getString(MainConfigPath.path_lobbyjoinTeamMess_title);

        lobbyjoinTeamMess_subtitle = c.getString(MainConfigPath.path_lobbyjoinTeamMess_subtitle);

        lobbyjoinTeamMess_actionbar = c.getString(MainConfigPath.path_lobbyjoinTeamMess_actionbar);

        deathGameMode_tpto = c.getString(MainConfigPath.path_deathGameMode_tpto);

        placeCorrect_ResSpawner_dis = c.getInt(MainConfigPath.path_placeCorrect_ResSpawner_dis);

        placeCorrect_PlayerSpawnLoc_dis = c.getInt(MainConfigPath.path_placeCorrect_PlayerSpawnLoc_dis);

        lobbyleaveTeam = c.getBoolean(MainConfigPath.path_lobbyleaveTeam);

        creativeGameModeFix = c.getBoolean(MainConfigPath.path_creativeGameModeFix);

        bungeeMode = c.getString(MainConfigPath.path_bungeeMode);

        paneItemLore = c.getStringList(MainConfigPath.path_paneItemLore);

        paneItemName = c.getString(MainConfigPath.path_paneItemName);

        levelupresItemType = Material.getMaterial(c.getString(MainConfigPath.path_levelupresItemType));

        levelupresItemSlot = c.getInt(MainConfigPath.path_levelupresItemSlot);

        dieOutGameItem_playAgain_ItemSlot = c.getInt(MainConfigPath.path_dieOutGameItem_playAgain_ItemSlot);

        levelupresItemName = c.getString(MainConfigPath.path_levelupresItemName);

        noEndermanGriefing = c.getBoolean(MainConfigPath.path_noEndermanGriefing);

        noOpenInventory = c.getBoolean(MainConfigPath.path_noOpenInventory);

        noWoodSwordDropWhenNothaveSword = c.getBoolean(MainConfigPath.noWoodSwordDropWhenNothaveSword);

        noWoodAxeDropWhenNothaveAxe = c.getBoolean(MainConfigPath.noWoodAxeDropWhenNothaveAxe);

        noWoodPickaxeDropWhenNothavePickaxe = c.getBoolean(MainConfigPath.noWoodPickaxeDropWhenNothavePickaxe);

        resSp = c.getBoolean(MainConfigPath.path_resSp);

        if (noOpenInventory) {
            if (c.getStringList(MainConfigPath.path_noOpenInventoryTypeList) != null) {
                if (!c.getStringList(MainConfigPath.path_noOpenInventoryTypeList).isEmpty()) {
                    List<String> loadList = c.getStringList(MainConfigPath.path_noOpenInventoryTypeList);

                    noOpenInventoryTypeList = Collections.newSetFromMap(new ConcurrentHashMap<>());

                    for (String list : loadList) {
                        String listNew = list.toUpperCase();
                        try {
                            InventoryType type = InventoryType.valueOf(listNew);
                            noOpenInventoryTypeList.add(type);
                        } catch (IllegalArgumentException e) {
                            le(className, meanInvalidInventoryType
                                    .replace("{value}", list)
                            );
                        }
                    }
                }
            } else {
                sendError(MainConfigPath.path_noOpenInventoryTypeList);
            }
        }
    }
}