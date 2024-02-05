package github.tsffish.bedwarskit.config;

import github.tsffish.bedwarskit.util.RelShopLevelUp;
import org.bukkit.Material;

import java.util.HashMap;

import static github.tsffish.bedwarskit.util.misc.InfoLogger.l;

public class MainConfigDefault
{
    public static void loadMainConfigDefault () {
l("loading Main Config Default");

            MainConfigHandler.breakTitle = true;
            MainConfigHandler.noHunger = true;
            MainConfigHandler.noPearlDamage = true;
            MainConfigHandler.breakBedCheck = true;
            MainConfigHandler.deathGameMode = true;
            MainConfigHandler.startKitCompass = false;
            MainConfigHandler.customScoreboard = true;
            MainConfigHandler.antiDrop = true;
            MainConfigHandler.grassPaneWalk = true;
            MainConfigHandler.cleanBottle = true;
            MainConfigHandler.levelupShopDelayOpen = true;
            MainConfigHandler.kill_res = true;
            MainConfigHandler.NoItemBreak = true;
            MainConfigHandler.levelupShop = true;


            MainConfigHandler.chainPrice = 35;
            MainConfigHandler.ironPrice = 12;
            MainConfigHandler.diamondPrice = 6;
            MainConfigHandler.sharp1Cost2v2 = 4;
            MainConfigHandler.prot1Cost2v2 = 2;
            MainConfigHandler.prot2Cost2v2 = 4;
            MainConfigHandler.prot3Cost2v2 = 8;
            MainConfigHandler.prot4Cost2v2 = 16;
            MainConfigHandler.sharp1Cost4v4 = 8;
            MainConfigHandler.prot1Cost4v4 = 5;
            MainConfigHandler.prot2Cost4v4 = 10;
            MainConfigHandler.prot3Cost4v4 = 20;
            MainConfigHandler.prot4Cost4v4 = 30;
            MainConfigHandler.maxFoodLevel = 20;
            MainConfigHandler.respawnDelay = 5;
            MainConfigHandler.tpDis = 0.74;



            MainConfigHandler.breakTitleAll = "{BreakTeamColor}{BreakTeamName}&c的床已被摧毁";
            MainConfigHandler.breakSubTitleAll = "&7摧毁者:{BreakPlayerTeamColor}{BreakPlayerName}";
            MainConfigHandler.breakTitleBreakPlayer = "{BreakTeamColor}{BreakTeamName}";
            MainConfigHandler.breakSubTitleBreakPlayer = "&a床已被你摧毁";
            MainConfigHandler.breakTitleBreakTeam = "&c床已被摧毁";
            MainConfigHandler.breakSubTitleBreakTeam = "&7摧毁者:{BreakPlayerTeamColor}{BreakPlayerName}";
            MainConfigHandler.rushWorld = "rush";
            MainConfigHandler.rushWorld2v2 = "2v2rush";
            MainConfigHandler.rushWorld4v4 = "4v4rush";
            MainConfigHandler.lobbyWorld = "lobby";
            MainConfigHandler.respawnTitle = "&e{timeleft}&a秒后复活";
            MainConfigHandler.respawnSubTitle = " ";
            MainConfigHandler.respawnSuccTitle = "&a已回城";
            MainConfigHandler.respawnSuccSubTitle = " ";
            MainConfigHandler.meanTeamBedYes = "&a&l✔";
            MainConfigHandler.meanTeamNone = "&c&l✘";
            MainConfigHandler.meanTeamBedNo = "{aliveCount}";
            MainConfigHandler.meanYou = "{teamColor}→";
            MainConfigHandler.meanNotYou = " ";
            MainConfigHandler.serverIp = "&eMCBBS@the_starfish";
            MainConfigHandler.relTeamName_Red = "红之队";
            MainConfigHandler.relTeamName_Blue = "蓝之队";
            MainConfigHandler.relTeamName_Green = "绿之队";
            MainConfigHandler.relTeamName_Yellow = "黄之队";
            MainConfigHandler.relTeamName_Aqua = "青之队";
            MainConfigHandler.relTeamName_White = "白之队";
            MainConfigHandler.relTeamName_Gray = "灰之队";
            MainConfigHandler.relTeamName_Pink = "粉之队";
            MainConfigHandler.teamEnchItemName_Sharp1 = "锋利 I";
            MainConfigHandler.teamEnchItemName_Prot1 = "保护 I";
            MainConfigHandler.teamEnchItemName_Prot2 = "保护 II";
            MainConfigHandler.teamEnchItemName_Prot3 = "保护 III";
            MainConfigHandler.teamEnchItemName_Prot4 = "保护 IV";
            MainConfigHandler.shopLevelup = "升级商店";
            MainConfigHandler.shopItem = "物品商店";
            MainConfigHandler.meanDiamond = "&b钻石";
            MainConfigHandler.messLevelUpSharp1 = "&6{player} &a购买了团队升级 &6锋利 I";
            MainConfigHandler.messLevelUpProt1 = "&6{player} &a购买了团队升级 &6保护 I";
            MainConfigHandler.messLevelUpProt2 = "&6{player} &a购买了团队升级 &6保护 II";
            MainConfigHandler.messLevelUpProt3 = "&6{player} &a购买了团队升级 &6保护 III";
            MainConfigHandler.messLevelUpProt4 = "&6{player} &a购买了团队升级 &6保护 IV";
            MainConfigHandler.messLevelUpFailed = "&c资源不够或已拥有更高级的附魔!";
            MainConfigHandler.meanBedwars = "起床战争";
            MainConfigHandler.mean2v2Mode = "双人模式";
            MainConfigHandler.mean4v4Mode = "4v4v4v4模式";
            MainConfigHandler.messreloadnow = "正在重新加载配置文件";
            MainConfigHandler.messreloadsucc = "成功重载配置文件";
            MainConfigHandler.levelupShopOpenMode = "click on item";
            MainConfigHandler.levelupShopOpenModeEntityName = "物品商店";

            MainConfigHandler.damagefb_Title = true;
            MainConfigHandler.damagefb_attackTitle = " ";
            MainConfigHandler.damagefb_attackSubTitle = "&3伤害 - &c{damage}";
            MainConfigHandler.damagefb_attackBlood = true;
            MainConfigHandler.damagefb_attackBloodMode = "box";


            MainConfigHandler.ScoreBoard2v2Title = "&e{bw}";

            MainConfigHandler.ScoreBoard2v2Line01 = "&7{date} &8{mode}";
            MainConfigHandler.ScoreBoard2v2Line02 = "&a{timeleft-s}&f秒之后&b游戏结束";
            MainConfigHandler.ScoreBoard2v2Line03 = "&f";
            MainConfigHandler.ScoreBoard2v2Line04 = "{redTeamIsMe} &c{RedTeamName} &a&l{redTeamStat} &7(&f&l{redTeamPlayer}&7)";
            MainConfigHandler.ScoreBoard2v2Line05 = "{blueTeamIsMe} &9{BlueTeamName} &a&l{blueTeamStat} &7(&f&l{blueTeamPlayer}&7)";
            MainConfigHandler.ScoreBoard2v2Line06 = "{greenTeamIsMe} &a{GreenTeamName} &a&l{greenTeamStat} &7(&f&l{greenTeamPlayer}&7)";
            MainConfigHandler.ScoreBoard2v2Line07 = "{yellowTeamIsMe} &e{YellowTeamName} &a&l{yellowTeamStat} &7(&f&l{yellowTeamPlayer}&7)";
            MainConfigHandler.ScoreBoard2v2Line08 = "{pinkTeamIsMe} &d{PinkTeamName} &a&l{pinkTeamStat} &7(&f&l{pinkTeamPlayer}&7)";
            MainConfigHandler.ScoreBoard2v2Line09 = "{aquaTeamIsMe} &b{AquaTeamName} &a&l{aquaTeamStat} &7(&f&l{aquaTeamPlayer}&7)";
            MainConfigHandler.ScoreBoard2v2Line10 = "{grayTeamIsMe} &7{GrayTeamName} &a&l{grayTeamStat} &7(&f&l{grayTeamPlayer}&7)";
            MainConfigHandler.ScoreBoard2v2Line11 = "{whiteTeamIsMe} &f{WhiteTeamName} {whiteTeamStat} &7(&f&l{whiteTeamPlayer}&7)";
            MainConfigHandler.ScoreBoard2v2Line12 = "&f ";
            MainConfigHandler.ScoreBoard2v2Line13 = "&e{ip}";
            MainConfigHandler.ScoreBoard2v2Line14 = "";
            MainConfigHandler.ScoreBoard2v2Line15 = "";
            MainConfigHandler.ScoreBoard2v2Line16 = "";

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
            MainConfigHandler.ScoreBoard4v4Title = "&e{bw}";

            MainConfigHandler.ScoreBoard4v4Line01 = "&7{date} &8{mode}";
            MainConfigHandler.ScoreBoard4v4Line02 = "&a{timeleft-s}&f秒之后&b游戏结束";
            MainConfigHandler.ScoreBoard4v4Line03 = "&f";
            MainConfigHandler.ScoreBoard4v4Line04 = "{redTeamIsMe} &c{RedTeamName} {redTeamStat} &7(&f&l{redTeamPlayer}&7)";
            MainConfigHandler.ScoreBoard4v4Line05 = "{blueTeamIsMe} &9{BlueTeamName} {blueTeamStat} &7(&f&l{blueTeamPlayer}&7)";
            MainConfigHandler.ScoreBoard4v4Line06 = "{greenTeamIsMe} &a{GreenTeamName} {greenTeamStat} &7(&f&l{greenTeamPlayer}&7)";
            MainConfigHandler.ScoreBoard4v4Line07 = "{yellowTeamIsMe} &e{YellowTeamName} {yellowTeamStat} &7(&f&l{yellowTeamPlayer}&7)";
            MainConfigHandler.ScoreBoard4v4Line08 = "&f ";
            MainConfigHandler.ScoreBoard4v4Line09 = "&f击杀数: &a{kill}";
            MainConfigHandler.ScoreBoard4v4Line10 = "&f破坏床数: &a{bed}";
            MainConfigHandler.ScoreBoard4v4Line11 = "&f最终击杀数: &a{fkill}";
            MainConfigHandler.ScoreBoard4v4Line12 = "&f  ";
            MainConfigHandler.ScoreBoard4v4Line13 = "&e{ip}";
            MainConfigHandler.ScoreBoard4v4Line14 = "";
            MainConfigHandler.ScoreBoard4v4Line15 = "";
            MainConfigHandler.ScoreBoard4v4Line16 = "";

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

            MainConfigHandler.meanSelKitSucc = "&a成功选择职业!";
            MainConfigHandler.LevelupItemType = Material.BOOK_AND_QUILL;
            MainConfigHandler.chainPriceType = Material.IRON_INGOT;
            MainConfigHandler.ironPriceType = Material.GOLD_INGOT;
            MainConfigHandler.diamondPriceType = Material.EMERALD;
            MainConfigHandler.upToChainArmor = Material.CHAINMAIL_CHESTPLATE;
            MainConfigHandler.upToIronArmor = Material.IRON_CHESTPLATE;
            MainConfigHandler.upToDiamondArmor = Material.DIAMOND_CHESTPLATE;












        MainConfigHandler.NoMoveList.add(Material.LEATHER_HELMET);
        MainConfigHandler.NoMoveList.add(Material.LEATHER_CHESTPLATE);
        MainConfigHandler.NoMoveList.add(Material.LEATHER_LEGGINGS);
        MainConfigHandler.NoMoveList.add(Material.LEATHER_BOOTS);
        MainConfigHandler.NoMoveList.add(Material.CHAINMAIL_HELMET);
        MainConfigHandler.NoMoveList.add(Material.CHAINMAIL_CHESTPLATE);
        MainConfigHandler.NoMoveList.add(Material.CHAINMAIL_LEGGINGS);
        MainConfigHandler.NoMoveList.add(Material.CHAINMAIL_BOOTS);
        MainConfigHandler.NoMoveList.add(Material.IRON_HELMET);
        MainConfigHandler.NoMoveList.add(Material.IRON_CHESTPLATE);
        MainConfigHandler.NoMoveList.add(Material.IRON_LEGGINGS);
        MainConfigHandler.NoMoveList.add(Material.IRON_BOOTS);
        MainConfigHandler.NoMoveList.add(Material.DIAMOND_HELMET);
        MainConfigHandler.NoMoveList.add(Material.DIAMOND_CHESTPLATE);
        MainConfigHandler.NoMoveList.add(Material.DIAMOND_LEGGINGS);
        MainConfigHandler.NoMoveList.add(Material.DIAMOND_BOOTS);

        l("<MainConfigLoad> Finish Load DefaultConfig");

            RelShopLevelUp.loadLevelUpInv();
        KitConfigLoad.loadKitConfig();
    }
}
