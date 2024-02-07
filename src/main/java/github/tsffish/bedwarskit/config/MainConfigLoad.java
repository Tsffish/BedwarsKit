package github.tsffish.bedwarskit.config;

import github.tsffish.bedwarskit.Main;
import github.tsffish.bedwarskit.listener.PlayerDamageHandler;
import github.tsffish.bedwarskit.listener.PlayerClickHandler;
import github.tsffish.bedwarskit.listener.bedwarsrel.*;
import github.tsffish.bedwarskit.util.misc.ColorString;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;

import static github.tsffish.bedwarskit.Main.checkUpdate;
import static github.tsffish.bedwarskit.Main.language;
import static github.tsffish.bedwarskit.config.MainConfigHandler.*;
import static github.tsffish.bedwarskit.util.RelShopLevelUp.loadLevelUpInv;
import static github.tsffish.bedwarskit.util.RelTeamEnchant.loadMapTeam;
import static github.tsffish.bedwarskit.util.misc.ConfigVersionCheck.ccv;
import static github.tsffish.bedwarskit.util.misc.ErrorLogger.le;
import static github.tsffish.bedwarskit.util.misc.InfoLogger.l;

public class MainConfigLoad{
    private static final String name = "MainConfigLoad";
    private static final String reason = "vaule is null";
    private static final Plugin plugin = JavaPlugin.getProvidingPlugin(Main.class);
    public static void loadMainConfig(CommandSender executer, boolean firstload) {

        c = Bukkit.getPluginManager().getPlugin("BedwarsKit").getConfig();
        if (c == null) {
            if (language.equalsIgnoreCase("zh")){
                Bukkit.getPluginManager().getPlugin("BedwarsKit").saveResource("/zh/org/config.yml", true);
            }else {

                Bukkit.getPluginManager().getPlugin("BedwarsKit").saveDefaultConfig();
            }
            c = Bukkit.getPluginManager().getPlugin("BedwarsKit").getConfig();

            Bukkit.getPluginManager().getPlugin("BedwarsKit").reloadConfig();

        }

        if (c == null) {
            le("Unable to find configuration file");
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

            if (firstload){
                if (c.getString(MainConfigPath.path_update_checker) != null) {
                    boolean update_checker = c.getBoolean(MainConfigPath.path_update_checker);
                    if (update_checker) {
                        checkUpdate(105616);
                    }
                } else {
                    ErrorConfigHandler.er(name, MainConfigPath.path_update_checker, reason);
                    checkUpdate(105616);
                }
            }

            if (c.getString(MainConfigPath.path_update_reportOp) != null) {
                update_reportOp = c.getBoolean(MainConfigPath.path_update_reportOp);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_update_reportOp, reason);
            }

            if (c.getString(MainConfigPath.path_grassPaneWalk) != null) {
                grassPaneWalk = c.getBoolean(MainConfigPath.path_grassPaneWalk);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_grassPaneWalk, reason);
            }

            if (c.getString(MainConfigPath.path_noHunger) != null) {
                noHunger = c.getBoolean(MainConfigPath.path_noHunger);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_noHunger, reason);
            }

            if (c.getString(MainConfigPath.path_noPearlDamage) != null) {
                noPearlDamage = c.getBoolean(MainConfigPath.path_noPearlDamage);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_noPearlDamage, reason);
            }

            if (c.getString(MainConfigPath.path_breakBedCheck) != null) {
                breakBedCheck = c.getBoolean(MainConfigPath.path_breakBedCheck);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_breakBedCheck, reason);
            }

            if (c.getString(MainConfigPath.path_levelupShop) != null) {
                levelupShop = c.getBoolean(MainConfigPath.path_levelupShop);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_levelupShop, reason);
            }

            if (c.getString(MainConfigPath.path_deathGameMode) != null) {
                deathGameMode = c.getBoolean(MainConfigPath.path_deathGameMode);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_deathGameMode, reason);
            }

            if (c.getString(MainConfigPath.path_startKitCompass) != null) {
                startKitCompass = c.getBoolean(MainConfigPath.path_startKitCompass);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_startKitCompass, reason);
            }

            if (c.getString(MainConfigPath.path_customScoreboard) != null) {
                customScoreboard = c.getBoolean(MainConfigPath.path_customScoreboard);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_customScoreboard, reason);
            }

            if (c.getString(MainConfigPath.path_antiDrop) != null) {
                antiDrop = c.getBoolean(MainConfigPath.path_antiDrop);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_antiDrop, reason);
            }
            if (c.getString(MainConfigPath.path_NoItemBreak) != null) {
                NoItemBreak = c.getBoolean(MainConfigPath.path_NoItemBreak);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_NoItemBreak, reason);
            }


            if (c.getString(MainConfigPath.path_kill_res) != null) {
                kill_res = c.getBoolean(MainConfigPath.path_kill_res);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_kill_res, reason);
            }

            if (c.getString(MainConfigPath.path_meanBedwars) != null) {
                meanBedwars = c.getString(MainConfigPath.path_meanBedwars);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_meanBedwars, reason);
            }
            if (c.getString(MainConfigPath.path_mean2v2Mode) != null) {
                mean2v2Mode = c.getString(MainConfigPath.path_mean2v2Mode);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_mean2v2Mode, reason);
            }
            if (c.getString(MainConfigPath.path_mean4v4Mode) != null) {
                mean4v4Mode = c.getString(MainConfigPath.path_mean4v4Mode);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_mean4v4Mode, reason);
            }

            if (c.getString(MainConfigPath.path_breakTitle) != null) {
                breakTitle = c.getBoolean(MainConfigPath.path_breakTitle);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_breakTitle, reason);
            }

            if (c.getString(MainConfigPath.path_levelupShopOpenMode) != null) {
                levelupShopOpenMode = c.getString(MainConfigPath.path_levelupShopOpenMode);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_levelupShopOpenMode, reason);
            }

            if (c.getString(MainConfigPath.path_levelupShopOpenModeEntityName) != null) {
                levelupShopOpenModeEntityName = c.getString(MainConfigPath.path_levelupShopOpenModeEntityName);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_levelupShopOpenModeEntityName, reason);
            }

            if (c.getString(MainConfigPath.path_ScoreBoard2v2Title) != null) {
                ScoreBoard2v2Title = c.getString(MainConfigPath.path_ScoreBoard2v2Title);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_ScoreBoard2v2Title, reason);
            }

            if (c.getString(MainConfigPath.path_ScoreBoard2v2Line01) != null) {
                ScoreBoard2v2Line01 = c.getString(MainConfigPath.path_ScoreBoard2v2Line01);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_ScoreBoard2v2Line01, reason);
            }

            if (c.getString(MainConfigPath.path_ScoreBoard2v2Line02) != null) {
                ScoreBoard2v2Line02 = c.getString(MainConfigPath.path_ScoreBoard2v2Line02);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_ScoreBoard2v2Line02, reason);
            }
            if (c.getString(MainConfigPath.path_ScoreBoard2v2Line03) != null) {
                ScoreBoard2v2Line03 = c.getString(MainConfigPath.path_ScoreBoard2v2Line03);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_ScoreBoard2v2Line03, reason);
            }
            if (c.getString(MainConfigPath.path_ScoreBoard2v2Line04) != null) {
                ScoreBoard2v2Line04 = c.getString(MainConfigPath.path_ScoreBoard2v2Line04);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_ScoreBoard2v2Line04, reason);
            }
            if (c.getString(MainConfigPath.path_ScoreBoard2v2Line05) != null) {
                ScoreBoard2v2Line05 = c.getString(MainConfigPath.path_ScoreBoard2v2Line05);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_ScoreBoard2v2Line05, reason);
            }
            if (c.getString(MainConfigPath.path_ScoreBoard2v2Line06) != null) {
                ScoreBoard2v2Line06 = c.getString(MainConfigPath.path_ScoreBoard2v2Line06);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_ScoreBoard2v2Line06, reason);
            }
            if (c.getString(MainConfigPath.path_ScoreBoard2v2Line07) != null) {
                ScoreBoard2v2Line07 = c.getString(MainConfigPath.path_ScoreBoard2v2Line07);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_ScoreBoard2v2Line07, reason);
            }
            if (c.getString(MainConfigPath.path_ScoreBoard2v2Line08) != null) {
                ScoreBoard2v2Line08 = c.getString(MainConfigPath.path_ScoreBoard2v2Line08);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_ScoreBoard2v2Line08, reason);
            }
            if (c.getString(MainConfigPath.path_ScoreBoard2v2Line09) != null) {
                ScoreBoard2v2Line09 = c.getString(MainConfigPath.path_ScoreBoard2v2Line09);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_ScoreBoard2v2Line09, reason);
            }
            if (c.getString(MainConfigPath.path_ScoreBoard2v2Line10) != null) {
                ScoreBoard2v2Line10 = c.getString(MainConfigPath.path_ScoreBoard2v2Line10);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_ScoreBoard2v2Line10, reason);
            }
            if (c.getString(MainConfigPath.path_ScoreBoard2v2Line11) != null) {
                ScoreBoard2v2Line11 = c.getString(MainConfigPath.path_ScoreBoard2v2Line11);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_ScoreBoard2v2Line11, reason);
            }
            if (c.getString(MainConfigPath.path_ScoreBoard2v2Line12) != null) {
                ScoreBoard2v2Line12 = c.getString(MainConfigPath.path_ScoreBoard2v2Line12);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_ScoreBoard2v2Line12, reason);
            }
            if (c.getString(MainConfigPath.path_ScoreBoard2v2Line13) != null) {
                ScoreBoard2v2Line13 = c.getString(MainConfigPath.path_ScoreBoard2v2Line13);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_ScoreBoard2v2Line13, reason);
            }
            if (c.getString(MainConfigPath.path_ScoreBoard2v2Line14) != null) {
                ScoreBoard2v2Line14 = c.getString(MainConfigPath.path_ScoreBoard2v2Line14);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_ScoreBoard2v2Line14, reason);
            }
            if (c.getString(MainConfigPath.path_ScoreBoard2v2Line15) != null) {
                ScoreBoard2v2Line15 = c.getString(MainConfigPath.path_ScoreBoard2v2Line15);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_ScoreBoard2v2Line15, reason);
            }
            if (c.getString(MainConfigPath.path_ScoreBoard2v2Line10) != null) {
                ScoreBoard2v2Line16 = c.getString(MainConfigPath.path_ScoreBoard2v2Line16);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_ScoreBoard2v2Line16, reason);
            }


            if (c.getString(MainConfigPath.path_ScoreBoard4v4Title) != null) {
                ScoreBoard4v4Title = c.getString(MainConfigPath.path_ScoreBoard4v4Title);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_ScoreBoard4v4Title, reason);
            }

            if (c.getString(MainConfigPath.path_ScoreBoard4v4Line01) != null) {
                ScoreBoard4v4Line01 = c.getString(MainConfigPath.path_ScoreBoard4v4Line01);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_ScoreBoard4v4Line01, reason);
            }

            if (c.getString(MainConfigPath.path_ScoreBoard4v4Line02) != null) {
                ScoreBoard4v4Line02 = c.getString(MainConfigPath.path_ScoreBoard4v4Line02);
            } else {
                ErrorConfigHandler.er("Main", MainConfigPath.path_ScoreBoard4v4Line02, reason);
            }
            if (c.getString(MainConfigPath.path_ScoreBoard4v4Line03) != null) {
                ScoreBoard4v4Line03 = c.getString(MainConfigPath.path_ScoreBoard4v4Line03);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_ScoreBoard4v4Line03, reason);
            }
            if (c.getString(MainConfigPath.path_ScoreBoard4v4Line04) != null) {
                ScoreBoard4v4Line04 = c.getString(MainConfigPath.path_ScoreBoard4v4Line04);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_ScoreBoard4v4Line04, reason);
            }
            if (c.getString(MainConfigPath.path_ScoreBoard4v4Line05) != null) {
                ScoreBoard4v4Line05 = c.getString(MainConfigPath.path_ScoreBoard4v4Line05);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_ScoreBoard4v4Line05, reason);
            }
            if (c.getString(MainConfigPath.path_ScoreBoard4v4Line06) != null) {
                ScoreBoard4v4Line06 = c.getString(MainConfigPath.path_ScoreBoard4v4Line06);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_ScoreBoard4v4Line06, reason);
            }
            if (c.getString(MainConfigPath.path_ScoreBoard4v4Line07) != null) {
                ScoreBoard4v4Line07 = c.getString(MainConfigPath.path_ScoreBoard4v4Line07);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_ScoreBoard4v4Line07, reason);
            }
            if (c.getString(MainConfigPath.path_ScoreBoard4v4Line08) != null) {
                ScoreBoard4v4Line08 = c.getString(MainConfigPath.path_ScoreBoard4v4Line08);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_ScoreBoard4v4Line08, reason);
            }
            if (c.getString(MainConfigPath.path_ScoreBoard4v4Line09) != null) {
                ScoreBoard4v4Line09 = c.getString(MainConfigPath.path_ScoreBoard4v4Line09);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_ScoreBoard4v4Line09, reason);
            }
            if (c.getString(MainConfigPath.path_ScoreBoard4v4Line10) != null) {
                ScoreBoard4v4Line10 = c.getString(MainConfigPath.path_ScoreBoard4v4Line10);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_ScoreBoard4v4Line10, reason);
            }
            if (c.getString(MainConfigPath.path_ScoreBoard4v4Line11) != null) {
                ScoreBoard4v4Line11 = c.getString(MainConfigPath.path_ScoreBoard4v4Line11);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_ScoreBoard4v4Line11, reason);
            }
            if (c.getString(MainConfigPath.path_ScoreBoard4v4Line12) != null) {
                ScoreBoard4v4Line12 = c.getString(MainConfigPath.path_ScoreBoard4v4Line12);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_ScoreBoard4v4Line12, reason);
            }
            if (c.getString(MainConfigPath.path_ScoreBoard4v4Line13) != null) {
                ScoreBoard4v4Line13 = c.getString(MainConfigPath.path_ScoreBoard4v4Line13);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_ScoreBoard4v4Line13, reason);
            }
            if (c.getString(MainConfigPath.path_ScoreBoard4v4Line14) != null) {
                ScoreBoard4v4Line14 = c.getString(MainConfigPath.path_ScoreBoard4v4Line14);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_ScoreBoard4v4Line14, reason);
            }
            if (c.getString(MainConfigPath.path_ScoreBoard4v4Line15) != null) {
                ScoreBoard4v4Line15 = c.getString(MainConfigPath.path_ScoreBoard4v4Line15);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_ScoreBoard4v4Line15, reason);
            }
            if (c.getString(MainConfigPath.path_ScoreBoard4v4Line10) != null) {
                ScoreBoard4v4Line16 = c.getString(MainConfigPath.path_ScoreBoard4v4Line16);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_ScoreBoard4v4Line16, reason);
            }


            if (c.getString(MainConfigPath.path_meanSelKitSucc) != null) {
                meanSelKitSucc = c.getString(MainConfigPath.path_meanSelKitSucc);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_meanSelKitSucc, reason);
            }

            if (c.getString(MainConfigPath.path_cleanBottle) != null) {
                cleanBottle = c.getBoolean(MainConfigPath.path_cleanBottle);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_cleanBottle, reason);
            }



            if (c.getString(MainConfigPath.path_chainPrice) != null) {
                chainPrice = c.getInt(MainConfigPath.path_chainPrice);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_chainPrice, reason);
            }
            if (c.getString(MainConfigPath.path_ironPrice) != null) {
                ironPrice = c.getInt(MainConfigPath.path_ironPrice);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_ironPrice, reason);
            }
            if (c.getString(MainConfigPath.path_diamondPrice) != null) {
                diamondPrice = c.getInt(MainConfigPath.path_diamondPrice);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_diamondPrice, reason);
            }


            if (c.getString(MainConfigPath.path_sharp1Cost2v2) != null) {
                sharp1Cost2v2 = c.getInt(MainConfigPath.path_sharp1Cost2v2);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_sharp1Cost2v2, reason);
            }

            if (c.getString(MainConfigPath.path_respawnDelay) != null) {
                respawnDelay = c.getInt(MainConfigPath.path_respawnDelay);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_respawnDelay, reason);
            }

            if (c.getString(MainConfigPath.path_maxFoodLevel) != null) {
                maxFoodLevel = c.getInt(MainConfigPath.path_maxFoodLevel);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_maxFoodLevel, reason);
            }

            if (c.getString(MainConfigPath.path_prot1Cost2v2) != null) {
                prot1Cost2v2 = c.getInt(MainConfigPath.path_prot1Cost2v2);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_prot1Cost2v2, reason);
            }

            if (c.getString(MainConfigPath.path_prot2Cost2v2) != null) {
                prot2Cost2v2 = c.getInt(MainConfigPath.path_prot2Cost2v2);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_prot2Cost2v2, reason);
            }

            if (c.getString(MainConfigPath.path_prot3Cost2v2) != null) {
                prot3Cost2v2 = c.getInt(MainConfigPath.path_prot3Cost2v2);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_prot3Cost2v2, reason);
            }

            if (c.getString(MainConfigPath.path_prot4Cost2v2) != null) {
                prot4Cost2v2 = c.getInt(MainConfigPath.path_prot4Cost2v2);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_prot4Cost2v2, reason);
            }

            if (c.getString(MainConfigPath.path_sharp1Cost4v4) != null) {
                sharp1Cost4v4 = c.getInt(MainConfigPath.path_sharp1Cost4v4);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_sharp1Cost4v4, reason);
            }

            if (c.getString(MainConfigPath.path_prot1Cost4v4) != null) {
                prot1Cost4v4 = c.getInt(MainConfigPath.path_prot1Cost4v4);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_prot1Cost4v4, reason);
            }

            if (c.getString(MainConfigPath.path_prot2Cost4v4) != null) {
                prot2Cost4v4 = c.getInt(MainConfigPath.path_prot2Cost4v4);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_prot2Cost4v4, reason);
            }

            if (c.getString(MainConfigPath.path_prot3Cost4v4) != null) {
                prot3Cost4v4 = c.getInt(MainConfigPath.path_prot3Cost4v4);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_prot3Cost4v4, reason);
            }

            if (c.getString(MainConfigPath.path_prot4Cost4v4) != null) {
                prot4Cost4v4 = c.getInt(MainConfigPath.path_prot4Cost4v4);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_prot4Cost4v4, reason);
            }

            if (c.getString(MainConfigPath.path_tpDis) != null) {
                tpDis = c.getDouble(MainConfigPath.path_tpDis);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_tpDis, reason);
            }

            if (c.getString(MainConfigPath.path_levelupShopDelayOpen) != null) {
                levelupShopDelayOpen = c.getBoolean(MainConfigPath.path_levelupShopDelayOpen);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_levelupShopDelayOpen, reason);
            }



            if (c.getString(MainConfigPath.path_LevelupItemType) != null) {
                LevelupItemType = Material.getMaterial(c.getString(MainConfigPath.path_LevelupItemType).toUpperCase());
            } else {

                ErrorConfigHandler.er(name, MainConfigPath.path_LevelupItemType, reason);
            }

            if (c.getString(MainConfigPath.path_chainPriceType) != null) {
                chainPriceType = Material.getMaterial(c.getString(MainConfigPath.path_chainPriceType));
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_chainPriceType, reason);
            }
            if (c.getString(MainConfigPath.path_ironPriceType) != null) {
                ironPriceType = Material.getMaterial(c.getString(MainConfigPath.path_ironPriceType));
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_ironPriceType, reason);
            }
            if (c.getString(MainConfigPath.path_diamondPriceType) != null) {
                diamondPriceType = Material.getMaterial(c.getString(MainConfigPath.path_diamondPriceType));
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_diamondPriceType, reason);
            }

            if (c.getString(MainConfigPath.path_upToChainArmor) != null) {
                upToChainArmor = Material.getMaterial(c.getString(MainConfigPath.path_upToChainArmor));
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_upToChainArmor, reason);
            }
            if (c.getString(MainConfigPath.path_upToIronArmor) != null) {
                upToIronArmor = Material.getMaterial(c.getString(MainConfigPath.path_upToIronArmor));
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_upToIronArmor, reason);
            }
            if (c.getString(MainConfigPath.path_upToDiamondArmor) != null) {
                upToDiamondArmor = Material.getMaterial(c.getString(MainConfigPath.path_upToDiamondArmor));
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_upToDiamondArmor, reason);
            }
            if (NoMoveList == null){
                NoMoveList = new ArrayList<>(25);
            }else {
                NoMoveList.clear();
            }
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
            if (NoBreakList == null){
                NoBreakList = new ArrayList<>(16);
            }else {
                NoBreakList.clear();
            }

            NoBreakList.add(Material.WOOD_SWORD);
            NoBreakList.add(Material.STONE_SWORD);
            NoBreakList.add(Material.IRON_SWORD);
            NoBreakList.add(Material.GOLD_SWORD);
            NoBreakList.add(Material.DIAMOND_SWORD);
            NoBreakList.add(Material.WOOD_PICKAXE);
            NoBreakList.add(Material.STONE_PICKAXE);
            NoBreakList.add(Material.IRON_PICKAXE);
            NoBreakList.add(Material.GOLD_PICKAXE);
            NoBreakList.add(Material.DIAMOND_PICKAXE);
            NoBreakList.add(Material.WOOD_AXE);
            NoBreakList.add(Material.STONE_AXE);
            NoBreakList.add(Material.IRON_AXE);
            NoBreakList.add(Material.GOLD_AXE);
            NoBreakList.add(Material.DIAMOND_AXE);
            NoBreakList.add(Material.WOOD_SPADE);
            NoBreakList.add(Material.STONE_SPADE);
            NoBreakList.add(Material.IRON_SPADE);
            NoBreakList.add(Material.GOLD_SPADE);
            NoBreakList.add(Material.DIAMOND_SPADE);
            NoBreakList.add(Material.WOOD_HOE);
            NoBreakList.add(Material.STONE_HOE);
            NoBreakList.add(Material.IRON_HOE);
            NoBreakList.add(Material.GOLD_HOE);
            NoBreakList.add(Material.DIAMOND_HOE);

            if (c.getString(MainConfigPath.path_messLevelUpFailed) != null) {
                messLevelUpFailed = c.getString(MainConfigPath.path_messLevelUpFailed);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_messLevelUpFailed, reason);
            }

            if (c.getString(MainConfigPath.path_messLevelUpSharp1) != null) {
                messLevelUpSharp1 = c.getString(MainConfigPath.path_messLevelUpSharp1);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_messLevelUpSharp1, reason);
            }

            if (c.getString(MainConfigPath.path_messLevelUpProt1) != null) {
                messLevelUpProt1 = c.getString(MainConfigPath.path_messLevelUpProt1);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_messLevelUpProt1, reason);
            }

            if (c.getString(MainConfigPath.path_messLevelUpProt2) != null) {
                messLevelUpProt2 = c.getString(MainConfigPath.path_messLevelUpProt2);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_messLevelUpProt2, reason);
            }

            if (c.getString(MainConfigPath.path_messLevelUpProt3) != null) {
                messLevelUpProt3 = c.getString(MainConfigPath.path_messLevelUpProt3);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_messLevelUpProt3, reason);
            }

            if (c.getString(MainConfigPath.path_messLevelUpProt4) != null) {
                messLevelUpProt4 = c.getString(MainConfigPath.path_messLevelUpProt4);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_messLevelUpProt4, reason);
            }

            if (c.getString(MainConfigPath.path_meanIron) != null) {
                meanIron = c.getString(MainConfigPath.path_meanIron);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_meanIron, reason);
            }
            if (c.getString(MainConfigPath.path_meanGold) != null) {
                meanGold = c.getString(MainConfigPath.path_meanGold);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_meanGold, reason);
            }


            if (c.getString(MainConfigPath.path_meanDiamond) != null) {
                meanDiamond = c.getString(MainConfigPath.path_meanDiamond);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_meanDiamond, reason);
            }

            if (c.getString(MainConfigPath.path_meanEmerlad) != null) {
                meanEmerlad = c.getString(MainConfigPath.path_meanEmerlad);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_meanEmerlad, reason);
            }

            if (c.getString(MainConfigPath.path_shopLevelup) != null) {
                shopLevelup = c.getString(MainConfigPath.path_shopLevelup);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_shopLevelup, reason);
            }

            if (c.getString(MainConfigPath.path_LevelupItemName) != null) {
                LevelupItemName = c.getString(MainConfigPath.path_LevelupItemName);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_LevelupItemName, reason);
            }
            if (c.getString(MainConfigPath.path_teamEnchItemName_Sharp1) != null) {
                teamEnchItemName_Sharp1 = c.getString(MainConfigPath.path_teamEnchItemName_Sharp1);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_teamEnchItemName_Sharp1, reason);
            }

            if (c.getString(MainConfigPath.path_teamEnchItemName_Prot1) != null) {
                teamEnchItemName_Prot1 = c.getString(MainConfigPath.path_teamEnchItemName_Prot1);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_teamEnchItemName_Prot1, reason);
            }

            if (c.getString(MainConfigPath.path_teamEnchItemName_Prot2) != null) {
                teamEnchItemName_Prot2 = c.getString(MainConfigPath.path_teamEnchItemName_Prot2);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_teamEnchItemName_Prot2, reason);
            }

            if (c.getString(MainConfigPath.path_teamEnchItemName_Prot3) != null) {
                teamEnchItemName_Prot3 = c.getString(MainConfigPath.path_teamEnchItemName_Prot3);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_teamEnchItemName_Prot3, reason);
            }

            if (c.getString(MainConfigPath.path_teamEnchItemName_Prot4) != null) {
                teamEnchItemName_Prot4 = c.getString(MainConfigPath.path_teamEnchItemName_Prot4);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_teamEnchItemName_Prot4, reason);
            }

            if (c.getString(MainConfigPath.path_relTeamName_Red) != null) {
                relTeamName_Red = c.getString(MainConfigPath.path_relTeamName_Red);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_relTeamName_Red, reason);
            }

            if (c.getString(MainConfigPath.path_relTeamName_Blue) != null) {
                relTeamName_Blue = c.getString(MainConfigPath.path_relTeamName_Blue);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_relTeamName_Blue, reason);
            }

            if (c.getString(MainConfigPath.path_relTeamName_Green) != null) {
                relTeamName_Green = c.getString(MainConfigPath.path_relTeamName_Green);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_relTeamName_Green, reason);
            }

            if (c.getString(MainConfigPath.path_relTeamName_Yellow) != null) {
                relTeamName_Yellow = c.getString(MainConfigPath.path_relTeamName_Yellow);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_relTeamName_Yellow, reason);
            }

            if (c.getString(MainConfigPath.path_relTeamName_Aqua) != null) {
                relTeamName_Aqua = c.getString(MainConfigPath.path_relTeamName_Aqua);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_relTeamName_Aqua, reason);
            }

            if (c.getString(MainConfigPath.path_relTeamName_White) != null) {
                relTeamName_White = c.getString(MainConfigPath.path_relTeamName_White);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_relTeamName_White, reason);
            }

            if (c.getString(MainConfigPath.path_relTeamName_Gray) != null) {
                relTeamName_Gray = c.getString(MainConfigPath.path_relTeamName_Gray);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_relTeamName_Gray, reason);
            }

            if (c.getString(MainConfigPath.path_relTeamName_Pink) != null) {
                relTeamName_Pink = c.getString(MainConfigPath.path_relTeamName_Pink);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_relTeamName_Pink, reason);
            }

            if (c.getString(MainConfigPath.path_breakTitleAll) != null) {
                breakTitleAll = c.getString(MainConfigPath.path_breakTitleAll);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_breakTitleAll, reason);
            }

            if (c.getString(MainConfigPath.path_breakSubTitleAll) != null) {
                breakSubTitleAll = c.getString(MainConfigPath.path_breakSubTitleAll);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_breakSubTitleAll, reason);
            }

            if (c.getString(MainConfigPath.path_breakTitleBreakPlayer) != null) {
                breakTitleBreakPlayer = c.getString(MainConfigPath.path_breakTitleBreakPlayer);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_breakTitleBreakPlayer, reason);
            }

            if (c.getString(MainConfigPath.path_breakSubTitleBreakPlayer) != null) {
                breakSubTitleBreakPlayer = c.getString(MainConfigPath.path_breakSubTitleBreakPlayer);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_breakSubTitleBreakPlayer, reason);
            }

            if (c.getString(MainConfigPath.path_breakTitleBreakTeam) != null) {
                breakTitleBreakTeam = c.getString(MainConfigPath.path_breakTitleBreakTeam);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_breakTitleBreakTeam, reason);
            }

            if (c.getString(MainConfigPath.path_breakSubTitleBreakTeam) != null) {
                breakSubTitleBreakTeam = c.getString(MainConfigPath.path_breakSubTitleBreakTeam);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_breakSubTitleBreakTeam, reason);
            }

            if (c.getString(MainConfigPath.path_rushWorld) != null) {
                rushWorld = c.getString(MainConfigPath.path_rushWorld);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_rushWorld, reason);
            }

            if (c.getString(MainConfigPath.path_rushWorld2v2) != null) {
                rushWorld2v2 = c.getString(MainConfigPath.path_rushWorld2v2);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_rushWorld2v2, reason);
            }

            if (c.getString(MainConfigPath.path_rushWorld4v4) != null) {
                rushWorld4v4 = c.getString(MainConfigPath.path_rushWorld4v4);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_rushWorld4v4, reason);
            }

            if (c.getString(MainConfigPath.path_lobbyWorld) != null) {
                lobbyWorld = c.getString(MainConfigPath.path_lobbyWorld);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_lobbyWorld, reason);
            }

            if (c.getString(MainConfigPath.path_respawnTitle) != null) {
                respawnTitle = c.getString(MainConfigPath.path_respawnTitle);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_respawnTitle, reason);
            }

            if (c.getString(MainConfigPath.path_respawnSubTitle) != null) {
                respawnSubTitle = c.getString(MainConfigPath.path_respawnSubTitle);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_respawnSubTitle, reason);
            }

            if (c.getString(MainConfigPath.path_respawnChat) != null) {
                respawnChat = c.getString(MainConfigPath.path_respawnChat);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_respawnChat, reason);
            }

            if (c.getString(MainConfigPath.path_respawnActionBar) != null) {
                respawnActionBar = c.getString(MainConfigPath.path_respawnActionBar);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_respawnActionBar, reason);
            }



            if (c.getString(MainConfigPath.path_respawnSuccTitle) != null) {
                respawnSuccTitle = c.getString(MainConfigPath.path_respawnSuccTitle);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_respawnSuccTitle, reason);
            }

            if (c.getString(MainConfigPath.path_respawnSuccSubTitle) != null) {
                respawnSuccSubTitle = c.getString(MainConfigPath.path_respawnSuccSubTitle);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_respawnSuccSubTitle, reason);
            }

            if (c.getString(MainConfigPath.path_respawnSuccChat) != null) {
                respawnSuccChat = c.getString(MainConfigPath.path_respawnSuccChat);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_respawnSuccChat, reason);
            }

            if (c.getString(MainConfigPath.path_respawnSuccActionBar) != null) {
                respawnSuccActionBar = c.getString(MainConfigPath.path_respawnSuccActionBar);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_respawnSuccActionBar, reason);
            }

            if (c.getString(MainConfigPath.path_meanTeamBedYes) != null) {
                meanTeamBedYes = c.getString(MainConfigPath.path_meanTeamBedYes);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_meanTeamBedYes, reason);
            }

            if (c.getString(MainConfigPath.path_meanTeamNone) != null) {
                meanTeamNone = c.getString(MainConfigPath.path_meanTeamNone);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_meanTeamNone, reason);
            }

            if (c.getString(MainConfigPath.path_meanTeamBedNo) != null) {
                meanTeamBedNo = c.getString(MainConfigPath.path_meanTeamBedNo);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_meanTeamBedNo, reason);
            }

            if (c.getString(MainConfigPath.path_serverIp) != null) {
                serverIp = c.getString(MainConfigPath.path_serverIp);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_serverIp, reason);
            }

            if (c.getString(MainConfigPath.path_meanYou) != null) {
                meanYou = c.getString(MainConfigPath.path_meanYou);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_meanYou, reason);
            }

            if (c.getString(MainConfigPath.path_meanNotYou) != null) {
                meanNotYou = c.getString(MainConfigPath.path_meanNotYou);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_meanNotYou, reason);
            }

            if (c.getString(MainConfigPath.path_damagefb_attackactionbar) != null) {
                damagefb_attackactionbar = c.getString(MainConfigPath.path_damagefb_attackactionbar);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_damagefb_attackactionbar, reason);
            }

            if (c.getString(MainConfigPath.path_damagefb_attackchat) != null) {
                damagefb_attackchat = c.getString(MainConfigPath.path_damagefb_attackchat);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_damagefb_attackchat, reason);
            }

            if (c.getString(MainConfigPath.path_damagefb_attackmess) != null) {
                damagefb_attackmess = c.getBoolean(MainConfigPath.path_damagefb_attackmess);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_damagefb_attackmess, reason);
            }

            if (c.getString(MainConfigPath.path_damagefb_attackTitle) != null) {
                damagefb_attackTitle = c.getString(MainConfigPath.path_damagefb_attackTitle);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_damagefb_attackTitle, reason);
            }
            if (c.getString(MainConfigPath.path_damagefb_attackSubTitle) != null) {
                damagefb_attackSubTitle = c.getString(MainConfigPath.path_damagefb_attackSubTitle);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_damagefb_attackSubTitle, reason);
            }

            if (c.getString(MainConfigPath.path_damagefb_attackBlood) != null) {
                damagefb_attackBlood = c.getBoolean(MainConfigPath.path_damagefb_attackBlood);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_damagefb_attackBlood, reason);
            }

            if (c.getString(MainConfigPath.path_damagefb_attackBloodMode) != null) {
                damagefb_attackBloodMode = c.getString(MainConfigPath.path_damagefb_attackBloodMode);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_damagefb_attackBloodMode, reason);
            }

            if (c.getString(MainConfigPath.path_damagefb_attackactionbar) != null) {
                damagefb_attackactionbar = c.getString(MainConfigPath.path_damagefb_attackactionbar);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_damagefb_attackactionbar, reason);
            }

            if (c.getString(MainConfigPath.path_damagefb_attackBloodType) != null) {
                damagefb_attackBloodType = c.getInt(MainConfigPath.path_damagefb_attackBloodType);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_damagefb_attackBloodType, reason);
            }


            if (c.getString(MainConfigPath.path_startmess) != null) {
                startmess = c.getBoolean(MainConfigPath.path_startmess);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_startmess, reason);
            }



            if (c.getString(MainConfigPath.path_startmess_all_chat) != null) {
                startmess_all_chat = c.getString(MainConfigPath.path_startmess_all_chat);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_startmess_all_chat, reason);
            }
            if (c.getString(MainConfigPath.path_startmess_all_title) != null) {
                startmess_all_title = c.getString(MainConfigPath.path_startmess_all_title);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_startmess_all_title, reason);
            }
            if (c.getString(MainConfigPath.path_startmess_all_subtitle) != null) {
                startmess_all_subtitle = c.getString(MainConfigPath.path_startmess_all_subtitle);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_startmess_all_subtitle, reason);
            }

            if (c.getString(MainConfigPath.path_startmess_all_actionbar) != null) {
                startmess_all_actionbar = c.getString(MainConfigPath.path_startmess_all_actionbar);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_startmess_all_actionbar, reason);
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


            if (c.getString(MainConfigPath.path_gametask) != null) {
                gametask = c.getBoolean(MainConfigPath.path_gametask);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask, reason);
            }

            if (c.getString(MainConfigPath.path_gametask_spawntime) != null) {
                gametask_spawntime = c.getBoolean(MainConfigPath.path_gametask_spawntime);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_spawntime, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_spawntime_iron_base) != null) {
                gametask_spawntime_iron_base = c.getInt(MainConfigPath.path_gametask_spawntime_iron_base);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_spawntime_iron_base, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_spawntime_iron_1) != null) {
                gametask_spawntime_iron_1 = c.getInt(MainConfigPath.path_gametask_spawntime_iron_1);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_spawntime_iron_1, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_spawntime_iron_2) != null) {
                gametask_spawntime_iron_2 = c.getInt(MainConfigPath.path_gametask_spawntime_iron_2);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_spawntime_iron_2, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_spawntime_iron_3) != null) {
                gametask_spawntime_iron_3 = c.getInt(MainConfigPath.path_gametask_spawntime_iron_3);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_spawntime_iron_3, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_spawntime_iron_4) != null) {
                gametask_spawntime_iron_4 = c.getInt(MainConfigPath.path_gametask_spawntime_iron_4);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_spawntime_iron_4, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_spawntime_gold_base) != null) {
                gametask_spawntime_gold_base = c.getInt(MainConfigPath.path_gametask_spawntime_gold_base);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_spawntime_gold_base, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_spawntime_gold_1) != null) {
                gametask_spawntime_gold_1 = c.getInt(MainConfigPath.path_gametask_spawntime_gold_1);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_spawntime_gold_1, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_spawntime_gold_2) != null) {
                gametask_spawntime_gold_2 = c.getInt(MainConfigPath.path_gametask_spawntime_gold_2);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_spawntime_gold_2, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_spawntime_gold_3) != null) {
                gametask_spawntime_gold_3 = c.getInt(MainConfigPath.path_gametask_spawntime_gold_3);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_spawntime_gold_3, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_spawntime_gold_4) != null) {
                gametask_spawntime_gold_4 = c.getInt(MainConfigPath.path_gametask_spawntime_gold_4);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_spawntime_gold_4, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_spawntime_diamond_base) != null) {
                gametask_spawntime_diamond_base = c.getInt(MainConfigPath.path_gametask_spawntime_diamond_base);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_spawntime_diamond_base, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_spawntime_diamond_1) != null) {
                gametask_spawntime_diamond_1 = c.getInt(MainConfigPath.path_gametask_spawntime_diamond_1);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_spawntime_diamond_1, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_spawntime_diamond_2) != null) {
                gametask_spawntime_diamond_2 = c.getInt(MainConfigPath.path_gametask_spawntime_diamond_2);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_spawntime_diamond_2, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_spawntime_diamond_3) != null) {
                gametask_spawntime_diamond_3 = c.getInt(MainConfigPath.path_gametask_spawntime_diamond_3);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_spawntime_diamond_3, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_spawntime_diamond_4) != null) {
                gametask_spawntime_diamond_4 = c.getInt(MainConfigPath.path_gametask_spawntime_diamond_4);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_spawntime_diamond_4, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_spawntime_emerald_base) != null) {
                gametask_spawntime_emerald_base = c.getInt(MainConfigPath.path_gametask_spawntime_emerald_base);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_spawntime_emerald_base, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_spawntime_emerald_1) != null) {
                gametask_spawntime_emerald_1 = c.getInt(MainConfigPath.path_gametask_spawntime_emerald_1);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_spawntime_emerald_1, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_spawntime_emerald_2) != null) {
                gametask_spawntime_emerald_2 = c.getInt(MainConfigPath.path_gametask_spawntime_emerald_2);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_spawntime_emerald_2, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_spawntime_emerald_3) != null) {
                gametask_spawntime_emerald_3 = c.getInt(MainConfigPath.path_gametask_spawntime_emerald_3);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_spawntime_emerald_3, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_spawntime_emerald_4) != null) {
                gametask_spawntime_emerald_4 = c.getInt(MainConfigPath.path_gametask_spawntime_emerald_4);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_spawntime_emerald_4, reason);
            }



            if (c.getString(MainConfigPath.path_gametask_finalbattle) != null) {
                gametask_finalbattle = c.getBoolean(MainConfigPath.path_gametask_finalbattle);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_finalbattle, reason);
            }

            if (c.getString(MainConfigPath.path_gametask_finalbattle_boundaries_time) != null) {
                gametask_finalbattle_boundaries_time = c.getLong(MainConfigPath.path_gametask_finalbattle_boundaries_time);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_finalbattle_boundaries_time, reason);
            }

            if (c.getString(MainConfigPath.path_gametask_finalbattle_boundaries_size) != null) {
                gametask_finalbattle_boundaries_size = c.getDouble(MainConfigPath.path_gametask_finalbattle_boundaries_size);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_finalbattle_boundaries_size, reason);
            }

            if (c.getString(MainConfigPath.path_gametask_boundaries_size) != null) {
                gametask_boundaries_size = c.getDouble(MainConfigPath.path_gametask_boundaries_size);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_boundaries_size, reason);
            }




            if (c.getString(MainConfigPath.path_placeCorrect_notInGame_OpBypass) != null) {
                placeCorrect_notInGame_OpBypass = c.getBoolean(MainConfigPath.path_placeCorrect_notInGame_OpBypass);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_placeCorrect_notInGame_OpBypass, reason);
            }

            if (c.getString(MainConfigPath.path_breakCorrect_notInGame_OpBypass) != null) {
                breakCorrect_notInGame_OpBypass = c.getBoolean(MainConfigPath.path_breakCorrect_notInGame_OpBypass);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_breakCorrect_notInGame_OpBypass, reason);
            }



            if (c.getString(MainConfigPath.path_gametask_spawntime_tasks_iron1) != null) {
                gametask_spawntime_tasks_iron1 = c.getInt(MainConfigPath.path_gametask_spawntime_tasks_iron1);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_spawntime_tasks_iron1, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_spawntime_tasks_iron2) != null) {
                gametask_spawntime_tasks_iron2 = c.getInt(MainConfigPath.path_gametask_spawntime_tasks_iron2);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_spawntime_tasks_iron2, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_spawntime_tasks_iron3) != null) {
                gametask_spawntime_tasks_iron3 = c.getInt(MainConfigPath.path_gametask_spawntime_tasks_iron3);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_spawntime_tasks_iron3, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_spawntime_tasks_iron4) != null) {
                gametask_spawntime_tasks_iron4 = c.getInt(MainConfigPath.path_gametask_spawntime_tasks_iron4);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_spawntime_tasks_iron4, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_spawntime_tasks_gold1) != null) {
                gametask_spawntime_tasks_gold1 = c.getInt(MainConfigPath.path_gametask_spawntime_tasks_gold1);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_spawntime_tasks_gold1, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_spawntime_tasks_gold2) != null) {
                gametask_spawntime_tasks_gold2 = c.getInt(MainConfigPath.path_gametask_spawntime_tasks_gold2);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_spawntime_tasks_gold2, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_spawntime_tasks_gold3) != null) {
                gametask_spawntime_tasks_gold3 = c.getInt(MainConfigPath.path_gametask_spawntime_tasks_gold3);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_spawntime_tasks_gold3, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_spawntime_tasks_gold4) != null) {
                gametask_spawntime_tasks_gold4 = c.getInt(MainConfigPath.path_gametask_spawntime_tasks_gold4);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_spawntime_tasks_gold4, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_spawntime_tasks_diamond1) != null) {
                gametask_spawntime_tasks_diamond1 = c.getInt(MainConfigPath.path_gametask_spawntime_tasks_diamond1);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_spawntime_tasks_diamond1, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_spawntime_tasks_diamond2) != null) {
                gametask_spawntime_tasks_diamond2 = c.getInt(MainConfigPath.path_gametask_spawntime_tasks_diamond2);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_spawntime_tasks_diamond2, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_spawntime_tasks_diamond3) != null) {
                gametask_spawntime_tasks_diamond3 = c.getInt(MainConfigPath.path_gametask_spawntime_tasks_diamond3);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_spawntime_tasks_diamond3, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_spawntime_tasks_diamond4) != null) {
                gametask_spawntime_tasks_diamond4 = c.getInt(MainConfigPath.path_gametask_spawntime_tasks_diamond4);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_spawntime_tasks_diamond4, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_spawntime_tasks_emerald1) != null) {
                gametask_spawntime_tasks_emerald1 = c.getInt(MainConfigPath.path_gametask_spawntime_tasks_emerald1);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_spawntime_tasks_emerald1, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_spawntime_tasks_emerald2) != null) {
                gametask_spawntime_tasks_emerald2 = c.getInt(MainConfigPath.path_gametask_spawntime_tasks_emerald2);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_spawntime_tasks_emerald2, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_spawntime_tasks_emerald3) != null) {
                gametask_spawntime_tasks_emerald3 = c.getInt(MainConfigPath.path_gametask_spawntime_tasks_emerald3);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_spawntime_tasks_emerald3, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_spawntime_tasks_emerald4) != null) {
                gametask_spawntime_tasks_emerald4 = c.getInt(MainConfigPath.path_gametask_spawntime_tasks_emerald4);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_spawntime_tasks_emerald4, reason);
            }







            if (c.getString(MainConfigPath.path_preventloadworld) != null) {
                preventloadworld = c.getBoolean(MainConfigPath.path_preventloadworld);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_preventloadworld, reason);
            }

            if (c.getString(MainConfigPath.path_CleanHostileOnStart) != null) {
                CleanHostileOnStart = c.getBoolean(MainConfigPath.path_CleanHostileOnStart);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_CleanHostileOnStart, reason);
            }

            if (c.getString(MainConfigPath.path_placeCorrect_ResSpawner) != null) {
                placeCorrect_ResSpawner = c.getBoolean(MainConfigPath.path_placeCorrect_ResSpawner);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_placeCorrect_ResSpawner, reason);
            }

            if (c.getString(MainConfigPath.path_placeCorrect_ResSpawner_mess_chat) != null) {
                placeCorrect_ResSpawner_mess_chat = c.getString(MainConfigPath.path_placeCorrect_ResSpawner_mess_chat);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_placeCorrect_ResSpawner_mess_chat, reason);
            }


            if (c.getString(MainConfigPath.path_placeCorrect_ResSpawner_mess_title) != null) {
                placeCorrect_ResSpawner_mess_title = c.getString(MainConfigPath.path_placeCorrect_ResSpawner_mess_title);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_placeCorrect_ResSpawner_mess_title, reason);
            }

            if (c.getString(MainConfigPath.path_placeCorrect_ResSpawner_mess_subtitle) != null) {
                placeCorrect_ResSpawner_mess_subtitle = c.getString(MainConfigPath.path_placeCorrect_ResSpawner_mess_subtitle);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_placeCorrect_ResSpawner_mess_subtitle, reason);
            }

            if (c.getString(MainConfigPath.path_placeCorrect_ResSpawner_mess_actionbar) != null) {
                placeCorrect_ResSpawner_mess_actionbar = c.getString(MainConfigPath.path_placeCorrect_ResSpawner_mess_actionbar);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_placeCorrect_ResSpawner_mess_actionbar, reason);
            }










            if (c.getString(MainConfigPath.path_placeCorrect_PlayerSpawnLoc) != null) {
                placeCorrect_PlayerSpawnLoc = c.getBoolean(MainConfigPath.path_placeCorrect_PlayerSpawnLoc);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_placeCorrect_PlayerSpawnLoc, reason);
            }



            if (c.getString(MainConfigPath.path_placeCorrect_PlayerSpawnLoc_mess_chat) != null) {
                placeCorrect_PlayerSpawnLoc_mess_chat = c.getString(MainConfigPath.path_placeCorrect_PlayerSpawnLoc_mess_chat);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_placeCorrect_PlayerSpawnLoc_mess_chat, reason);
            }


            if (c.getString(MainConfigPath.path_placeCorrect_PlayerSpawnLoc_mess_title) != null) {
                placeCorrect_PlayerSpawnLoc_mess_title = c.getString(MainConfigPath.path_placeCorrect_PlayerSpawnLoc_mess_title);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_placeCorrect_PlayerSpawnLoc_mess_title, reason);
            }

            if (c.getString(MainConfigPath.path_placeCorrect_PlayerSpawnLoc_mess_subtitle) != null) {
                placeCorrect_PlayerSpawnLoc_mess_subtitle = c.getString(MainConfigPath.path_placeCorrect_PlayerSpawnLoc_mess_subtitle);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_placeCorrect_PlayerSpawnLoc_mess_subtitle, reason);
            }

            if (c.getString(MainConfigPath.path_placeCorrect_PlayerSpawnLoc_mess_actionbar) != null) {
                placeCorrect_PlayerSpawnLoc_mess_actionbar = c.getString(MainConfigPath.path_placeCorrect_PlayerSpawnLoc_mess_actionbar);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_placeCorrect_PlayerSpawnLoc_mess_actionbar, reason);
            }








            if (c.getString(MainConfigPath.path_breakCorrect_notInGame) != null) {
                breakCorrect_notInGame = c.getBoolean(MainConfigPath.path_breakCorrect_notInGame);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_breakCorrect_notInGame, reason);
            }


            if (c.getString(MainConfigPath.path_placeCorrect_notInGame) != null) {
                placeCorrect_notInGame = c.getBoolean(MainConfigPath.path_placeCorrect_notInGame);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_placeCorrect_notInGame, reason);
            }



            if (c.getString(MainConfigPath.path_killfb_sendmess) != null) {
                killfb_sendmess = c.getBoolean(MainConfigPath.path_killfb_sendmess);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_killfb_sendmess, reason);
            }

            if (c.getString(MainConfigPath.path_killfb_sendmess_chat) != null) {
                killfb_sendmess_chat = c.getString(MainConfigPath.path_killfb_sendmess_chat);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_killfb_sendmess_chat, reason);
            }
            if (c.getString(MainConfigPath.path_killfb_sendmess_title) != null) {
                killfb_sendmess_title = c.getString(MainConfigPath.path_killfb_sendmess_title);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_killfb_sendmess_title, reason);
            }
            if (c.getString(MainConfigPath.path_killfb_sendmess_subtitle) != null) {
                killfb_sendmess_subtitle = c.getString(MainConfigPath.path_killfb_sendmess_subtitle);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_killfb_sendmess_subtitle, reason);
            }
            if (c.getString(MainConfigPath.path_killfb_sendmess_actionbar) != null) {
                killfb_sendmess_actionbar = c.getString(MainConfigPath.path_killfb_sendmess_actionbar);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_killfb_sendmess_actionbar, reason);
            }

            if (c.getString(MainConfigPath.path_kill_res_chat) != null) {
                kill_res_chat = c.getString(MainConfigPath.path_kill_res_chat);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_killfb_sendmess_actionbar, reason);
            }


            if (c.getString(MainConfigPath.path_gametask_finalbattle_time) != null) {
                gametask_finalbattle_time = c.getInt(MainConfigPath.path_gametask_finalbattle_time);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_finalbattle_time, reason);
            }



            if (c.getString(MainConfigPath.path_meanGameEnd) != null) {
                meanGameEnd = c.getString(MainConfigPath.path_meanGameEnd);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_meanGameEnd, reason);
            }

            if (c.getString(MainConfigPath.path_meanSecond) != null) {
                meanSecond = c.getString(MainConfigPath.path_meanSecond);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_meanSecond, reason);
            }




            if (c.getString(MainConfigPath.path_gametask_name_iron1) != null) {
                gametask_name_iron1 = c.getString(MainConfigPath.path_gametask_name_iron1);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_name_iron1, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_name_iron2) != null) {
                gametask_name_iron2 = c.getString(MainConfigPath.path_gametask_name_iron2);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_name_iron2, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_name_iron3) != null) {
                gametask_name_iron3 = c.getString(MainConfigPath.path_gametask_name_iron3);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_name_iron3, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_name_iron4) != null) {
                gametask_name_iron4 = c.getString(MainConfigPath.path_gametask_name_iron4);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_name_iron4, reason);
            }

            if (c.getString(MainConfigPath.path_gametask_name_gold1) != null) {
                gametask_name_gold1 = c.getString(MainConfigPath.path_gametask_name_gold1);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_name_gold1, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_name_gold2) != null) {
                gametask_name_gold2 = c.getString(MainConfigPath.path_gametask_name_gold2);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_name_gold2, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_name_gold3) != null) {
                gametask_name_gold3 = c.getString(MainConfigPath.path_gametask_name_gold3);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_name_gold3, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_name_gold4) != null) {
                gametask_name_gold4 = c.getString(MainConfigPath.path_gametask_name_gold4);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_name_gold4, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_name_diamond1) != null) {
                gametask_name_diamond1 = c.getString(MainConfigPath.path_gametask_name_diamond1);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_name_diamond1, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_name_diamond2) != null) {
                gametask_name_diamond2 = c.getString(MainConfigPath.path_gametask_name_diamond2);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_name_diamond2, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_name_diamond3) != null) {
                gametask_name_diamond3 = c.getString(MainConfigPath.path_gametask_name_diamond3);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_name_diamond3, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_name_diamond4) != null) {
                gametask_name_diamond4 = c.getString(MainConfigPath.path_gametask_name_diamond4);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_name_diamond4, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_name_emerald1) != null) {
                gametask_name_emerald1 = c.getString(MainConfigPath.path_gametask_name_emerald1);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_name_emerald1, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_name_emerald2) != null) {
                gametask_name_emerald2 = c.getString(MainConfigPath.path_gametask_name_emerald2);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_name_emerald2, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_name_emerald3) != null) {
                gametask_name_emerald3 = c.getString(MainConfigPath.path_gametask_name_emerald3);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_name_emerald3, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_name_emerald4) != null) {
                gametask_name_emerald4 = c.getString(MainConfigPath.path_gametask_name_emerald4);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_name_emerald4, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_name_finalbattle) != null) {
                gametask_name_finalbattle = c.getString(MainConfigPath.path_gametask_name_finalbattle);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_name_finalbattle, reason);
            }







            if (c.getString(MainConfigPath.path_gametask_mess_finalbattle_chat) != null) {
                gametask_mess_finalbattle_chat = c.getString(MainConfigPath.path_gametask_mess_finalbattle_chat);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_finalbattle_chat, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_mess_finalbattle_title) != null) {
                gametask_mess_finalbattle_title = c.getString(MainConfigPath.path_gametask_mess_finalbattle_title);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_finalbattle_title, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_mess_finalbattle_subtitle) != null) {
                gametask_mess_finalbattle_subtitle = c.getString(MainConfigPath.path_gametask_mess_finalbattle_subtitle);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_finalbattle_subtitle, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_mess_finalbattle_actionbar) != null) {
                gametask_mess_finalbattle_actionbar = c.getString(MainConfigPath.path_gametask_mess_finalbattle_actionbar);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_finalbattle_actionbar, reason);
            }


            if (c.getString(MainConfigPath.path_gametask_mess_iron1_chat) != null) {
                gametask_mess_iron1_chat = c.getString(MainConfigPath.path_gametask_mess_iron1_chat);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_iron1_chat, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_mess_iron1_title) != null) {
                gametask_mess_iron1_title = c.getString(MainConfigPath.path_gametask_mess_iron1_title);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_iron1_title, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_mess_iron1_subtitle) != null) {
                gametask_mess_iron1_subtitle = c.getString(MainConfigPath.path_gametask_mess_iron1_subtitle);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_iron1_subtitle, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_mess_iron1_actionbar) != null) {
                gametask_mess_iron1_actionbar = c.getString(MainConfigPath.path_gametask_mess_iron1_actionbar);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_iron1_actionbar, reason);
            }

            if (c.getString(MainConfigPath.path_gametask_mess_iron2_chat) != null) {
                gametask_mess_iron2_chat = c.getString(MainConfigPath.path_gametask_mess_iron2_chat);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_iron2_chat, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_mess_iron2_title) != null) {
                gametask_mess_iron2_title = c.getString(MainConfigPath.path_gametask_mess_iron2_title);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_iron2_title, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_mess_iron2_subtitle) != null) {
                gametask_mess_iron2_subtitle = c.getString(MainConfigPath.path_gametask_mess_iron2_subtitle);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_iron2_subtitle, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_mess_iron2_actionbar) != null) {
                gametask_mess_iron2_actionbar = c.getString(MainConfigPath.path_gametask_mess_iron2_actionbar);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_iron2_actionbar, reason);
            }

            if (c.getString(MainConfigPath.path_gametask_mess_iron3_chat) != null) {
                gametask_mess_iron3_chat = c.getString(MainConfigPath.path_gametask_mess_iron3_chat);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_iron3_chat, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_mess_iron3_title) != null) {
                gametask_mess_iron3_title = c.getString(MainConfigPath.path_gametask_mess_iron3_title);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_iron3_title, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_mess_iron3_subtitle) != null) {
                gametask_mess_iron3_subtitle = c.getString(MainConfigPath.path_gametask_mess_iron3_subtitle);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_iron3_subtitle, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_mess_iron3_actionbar) != null) {
                gametask_mess_iron3_actionbar = c.getString(MainConfigPath.path_gametask_mess_iron3_actionbar);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_iron3_actionbar, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_mess_iron4_chat) != null) {
                gametask_mess_iron4_chat = c.getString(MainConfigPath.path_gametask_mess_iron4_chat);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_iron4_chat, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_mess_iron4_title) != null) {
                gametask_mess_iron4_title = c.getString(MainConfigPath.path_gametask_mess_iron4_title);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_iron4_title, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_mess_iron4_subtitle) != null) {
                gametask_mess_iron4_subtitle = c.getString(MainConfigPath.path_gametask_mess_iron4_subtitle);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_iron4_subtitle, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_mess_iron4_actionbar) != null) {
                gametask_mess_iron4_actionbar = c.getString(MainConfigPath.path_gametask_mess_iron4_actionbar);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_iron4_actionbar, reason);
            }

            if (c.getString(MainConfigPath.path_gametask_mess_gold1_chat) != null) {
                gametask_mess_gold1_chat = c.getString(MainConfigPath.path_gametask_mess_gold1_chat);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_gold1_chat, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_mess_gold1_title) != null) {
                gametask_mess_gold1_title = c.getString(MainConfigPath.path_gametask_mess_gold1_title);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_gold1_title, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_mess_gold1_subtitle) != null) {
                gametask_mess_gold1_subtitle = c.getString(MainConfigPath.path_gametask_mess_gold1_subtitle);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_gold1_subtitle, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_mess_gold1_actionbar) != null) {
                gametask_mess_gold1_actionbar = c.getString(MainConfigPath.path_gametask_mess_gold1_actionbar);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_gold1_actionbar, reason);
            }

            if (c.getString(MainConfigPath.path_gametask_mess_gold2_chat) != null) {
                gametask_mess_gold2_chat = c.getString(MainConfigPath.path_gametask_mess_gold2_chat);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_gold2_chat, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_mess_gold2_title) != null) {
                gametask_mess_gold2_title = c.getString(MainConfigPath.path_gametask_mess_gold2_title);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_gold2_title, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_mess_gold2_subtitle) != null) {
                gametask_mess_gold2_subtitle = c.getString(MainConfigPath.path_gametask_mess_gold2_subtitle);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_gold2_subtitle, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_mess_gold2_actionbar) != null) {
                gametask_mess_gold2_actionbar = c.getString(MainConfigPath.path_gametask_mess_gold2_actionbar);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_gold2_actionbar, reason);
            }

            if (c.getString(MainConfigPath.path_gametask_mess_gold3_chat) != null) {
                gametask_mess_gold3_chat = c.getString(MainConfigPath.path_gametask_mess_gold3_chat);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_gold3_chat, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_mess_gold3_title) != null) {
                gametask_mess_gold3_title = c.getString(MainConfigPath.path_gametask_mess_gold3_title);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_gold3_title, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_mess_gold3_subtitle) != null) {
                gametask_mess_gold3_subtitle = c.getString(MainConfigPath.path_gametask_mess_gold3_subtitle);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_gold3_subtitle, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_mess_gold3_actionbar) != null) {
                gametask_mess_gold3_actionbar = c.getString(MainConfigPath.path_gametask_mess_gold3_actionbar);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_gold3_actionbar, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_mess_gold4_chat) != null) {
                gametask_mess_gold4_chat = c.getString(MainConfigPath.path_gametask_mess_gold4_chat);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_gold4_chat, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_mess_gold4_title) != null) {
                gametask_mess_gold4_title = c.getString(MainConfigPath.path_gametask_mess_gold4_title);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_gold4_title, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_mess_gold4_subtitle) != null) {
                gametask_mess_gold4_subtitle = c.getString(MainConfigPath.path_gametask_mess_gold4_subtitle);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_gold4_subtitle, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_mess_gold4_actionbar) != null) {
                gametask_mess_gold4_actionbar = c.getString(MainConfigPath.path_gametask_mess_gold4_actionbar);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_gold4_actionbar, reason);
            }

            if (c.getString(MainConfigPath.path_gametask_mess_diamond1_chat) != null) {
                gametask_mess_diamond1_chat = c.getString(MainConfigPath.path_gametask_mess_diamond1_chat);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_diamond1_chat, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_mess_diamond1_title) != null) {
                gametask_mess_diamond1_title = c.getString(MainConfigPath.path_gametask_mess_diamond1_title);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_diamond1_title, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_mess_diamond1_subtitle) != null) {
                gametask_mess_diamond1_subtitle = c.getString(MainConfigPath.path_gametask_mess_diamond1_subtitle);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_diamond1_subtitle, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_mess_diamond1_actionbar) != null) {
                gametask_mess_diamond1_actionbar = c.getString(MainConfigPath.path_gametask_mess_diamond1_actionbar);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_diamond1_actionbar, reason);
            }

            if (c.getString(MainConfigPath.path_gametask_mess_diamond2_chat) != null) {
                gametask_mess_diamond2_chat = c.getString(MainConfigPath.path_gametask_mess_diamond2_chat);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_diamond2_chat, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_mess_diamond2_title) != null) {
                gametask_mess_diamond2_title = c.getString(MainConfigPath.path_gametask_mess_diamond2_title);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_diamond2_title, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_mess_diamond2_subtitle) != null) {
                gametask_mess_diamond2_subtitle = c.getString(MainConfigPath.path_gametask_mess_diamond2_subtitle);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_diamond2_subtitle, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_mess_diamond2_actionbar) != null) {
                gametask_mess_diamond2_actionbar = c.getString(MainConfigPath.path_gametask_mess_diamond2_actionbar);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_diamond2_actionbar, reason);
            }

            if (c.getString(MainConfigPath.path_gametask_mess_diamond3_chat) != null) {
                gametask_mess_diamond3_chat = c.getString(MainConfigPath.path_gametask_mess_diamond3_chat);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_diamond3_chat, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_mess_diamond3_title) != null) {
                gametask_mess_diamond3_title = c.getString(MainConfigPath.path_gametask_mess_diamond3_title);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_diamond3_title, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_mess_diamond3_subtitle) != null) {
                gametask_mess_diamond3_subtitle = c.getString(MainConfigPath.path_gametask_mess_diamond3_subtitle);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_diamond3_subtitle, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_mess_diamond3_actionbar) != null) {
                gametask_mess_diamond3_actionbar = c.getString(MainConfigPath.path_gametask_mess_diamond3_actionbar);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_diamond3_actionbar, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_mess_diamond4_chat) != null) {
                gametask_mess_diamond4_chat = c.getString(MainConfigPath.path_gametask_mess_diamond4_chat);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_diamond4_chat, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_mess_diamond4_title) != null) {
                gametask_mess_diamond4_title = c.getString(MainConfigPath.path_gametask_mess_diamond4_title);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_diamond4_title, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_mess_diamond4_subtitle) != null) {
                gametask_mess_diamond4_subtitle = c.getString(MainConfigPath.path_gametask_mess_diamond4_subtitle);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_diamond4_subtitle, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_mess_diamond4_actionbar) != null) {
                gametask_mess_diamond4_actionbar = c.getString(MainConfigPath.path_gametask_mess_diamond4_actionbar);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_diamond4_actionbar, reason);
            }


            if (c.getString(MainConfigPath.path_gametask_mess_emerald1_chat) != null) {
                gametask_mess_emerald1_chat = c.getString(MainConfigPath.path_gametask_mess_emerald1_chat);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_emerald1_chat, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_mess_emerald1_title) != null) {
                gametask_mess_emerald1_title = c.getString(MainConfigPath.path_gametask_mess_emerald1_title);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_emerald1_title, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_mess_emerald1_subtitle) != null) {
                gametask_mess_emerald1_subtitle = c.getString(MainConfigPath.path_gametask_mess_emerald1_subtitle);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_emerald1_subtitle, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_mess_emerald1_actionbar) != null) {
                gametask_mess_emerald1_actionbar = c.getString(MainConfigPath.path_gametask_mess_emerald1_actionbar);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_emerald1_actionbar, reason);
            }

            if (c.getString(MainConfigPath.path_gametask_mess_emerald2_chat) != null) {
                gametask_mess_emerald2_chat = c.getString(MainConfigPath.path_gametask_mess_emerald2_chat);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_emerald2_chat, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_mess_emerald2_title) != null) {
                gametask_mess_emerald2_title = c.getString(MainConfigPath.path_gametask_mess_emerald2_title);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_emerald2_title, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_mess_emerald2_subtitle) != null) {
                gametask_mess_emerald2_subtitle = c.getString(MainConfigPath.path_gametask_mess_emerald2_subtitle);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_emerald2_subtitle, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_mess_emerald2_actionbar) != null) {
                gametask_mess_emerald2_actionbar = c.getString(MainConfigPath.path_gametask_mess_emerald2_actionbar);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_emerald2_actionbar, reason);
            }

            if (c.getString(MainConfigPath.path_gametask_mess_emerald3_chat) != null) {
                gametask_mess_emerald3_chat = c.getString(MainConfigPath.path_gametask_mess_emerald3_chat);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_emerald3_chat, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_mess_emerald3_title) != null) {
                gametask_mess_emerald3_title = c.getString(MainConfigPath.path_gametask_mess_emerald3_title);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_emerald3_title, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_mess_emerald3_subtitle) != null) {
                gametask_mess_emerald3_subtitle = c.getString(MainConfigPath.path_gametask_mess_emerald3_subtitle);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_emerald3_subtitle, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_mess_emerald3_actionbar) != null) {
                gametask_mess_emerald3_actionbar = c.getString(MainConfigPath.path_gametask_mess_emerald3_actionbar);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_emerald3_actionbar, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_mess_emerald4_chat) != null) {
                gametask_mess_emerald4_chat = c.getString(MainConfigPath.path_gametask_mess_emerald4_chat);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_emerald4_chat, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_mess_emerald4_title) != null) {
                gametask_mess_emerald4_title = c.getString(MainConfigPath.path_gametask_mess_emerald4_title);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_emerald4_title, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_mess_emerald4_subtitle) != null) {
                gametask_mess_emerald4_subtitle = c.getString(MainConfigPath.path_gametask_mess_emerald4_subtitle);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_emerald4_subtitle, reason);
            }
            if (c.getString(MainConfigPath.path_gametask_mess_emerald4_actionbar) != null) {
                gametask_mess_emerald4_actionbar = c.getString(MainConfigPath.path_gametask_mess_emerald4_actionbar);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_mess_emerald4_actionbar, reason);
            }



            if (c.getString(MainConfigPath.path_gametask_finalbattle_boundaries_warnidis) != null) {
                gametask_finalbattle_boundaries_warnidis = c.getInt(MainConfigPath.path_gametask_finalbattle_boundaries_warnidis);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_finalbattle_boundaries_warnidis, reason);
            }


            if (c.getString(MainConfigPath.path_gametask_finalbattle_boundaries_damagebuffer) != null) {
                gametask_finalbattle_boundaries_damagebuffer = c.getDouble(MainConfigPath.path_gametask_finalbattle_boundaries_damagebuffer);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_finalbattle_boundaries_damagebuffer, reason);
            }

            if (c.getString(MainConfigPath.path_gametask_finalbattle_boundaries_damage) != null) {
                gametask_finalbattle_boundaries_damage = c.getDouble(MainConfigPath.path_gametask_finalbattle_boundaries_damage);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_gametask_finalbattle_boundaries_damage, reason);
            }


            if (c.getString(MainConfigPath.path_shopItem) != null) {
                shopItem = c.getString(MainConfigPath.path_shopItem);
            } else {
                ErrorConfigHandler.er(name, MainConfigPath.path_shopItem, reason);
            }

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

                pm.registerEvents(new PlayerClickHandler(), plugin);
                pm.registerEvents(new RelClickInventory(), plugin);
                pm.registerEvents(new RelEnchant(), plugin);

                pm.registerEvents(new RelFoodLock(), plugin);

                pm.registerEvents(new RelGameOver(), plugin);

                pm.registerEvents(new RelGameStarted(), plugin);

                pm.registerEvents(new RelKillPlayer(),plugin);
                pm.registerEvents(new RelPlaceCorrect(), plugin);
                pm.registerEvents(new RelNoItemBreak(), plugin);

                pm.registerEvents(new RelPlayerDeath(), plugin);
                pm.registerEvents(new RelPlayerDrop(), plugin);
                pm.registerEvents(new RelPlayerJoin(), plugin);

                pm.registerEvents(new RelPlayerLeave(), plugin);
                pm.registerEvents(new RelPlayerMove(), plugin);
                pm.registerEvents(new RelPlayerOpenShop(),plugin);

                pm.registerEvents(new RelPlayerTeleport(), plugin);


                pm.registerEvents(new PlayerDamageHandler(), plugin);

                Main.console.sendMessage(Main.pluginNameConsole  +  " " + ChatColor.GREEN + "BedwarsRel found, related support enable");
            }
        }
    }
}