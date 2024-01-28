package github.tsffish.bedwarskit.config;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static github.tsffish.bedwarskit.config.ErrorConfigHandler.er;
import static github.tsffish.bedwarskit.util.ColorString.t;

public class MainConfigHandler {
    public static boolean breakTitle;
    public static String breakTitleAll;
    public static String breakSubTitleAll;
    public static String breakTitleBreakPlayer;
    public static String breakSubTitleBreakPlayer;
    public static String breakTitleBreakTeam;
    public static String breakSubTitleBreakTeam;
    public static boolean noHunger;
    public static Integer maxFoodLevel;
    public static boolean noPearlDamage;
    public static boolean breakBedCheck;
    public static double tpDis;
    public static boolean deathGameMode;
    public static String rushWorld;
    public static String rushWorld2v2;
    public static String rushWorld4v4;
    public static String lobbyWorld;
    public static boolean startKitCompass;
    public static Integer respawnDelay;
    public static String respawnTitle;
    public static String respawnSubTitle;
    public static String respawnSuccTitle;
    public static String respawnSuccSubTitle;
    public static boolean customScoreboard;
    public static String scoreboardTitle;
    public static boolean antiDrop;
    public static String meanTeamBedYes;
    public static String meanTeamBedNo;
    public static String meanTeamNone;
    public static String meanYou;
    public static String meanNotYou;
    public static String serverIp;
    public static boolean rushModeSpeed;
    public static int rushModeSpeedLevel;
    public static String relTeamColorName_Red;
    public static String relTeamColorName_Blue;
    public static String relTeamColorName_Green;
    public static String relTeamColorName_Yellow;
    public static String relTeamColorName_Aqua;
    public static String relTeamColorName_White;
    public static String relTeamColorName_Gray;
    public static String relTeamColorName_Pink;
    public static List<Material> NoMoveList = new ArrayList<>();
    public static List<Material> NoBreakList = new ArrayList<>();
    public static boolean kill_res;
    public static String dateDisplay;
    public static String teamEnchItemName_Sharp1;
    public static String teamEnchItemName_Prot1;
    public static String teamEnchItemName_Prot2;
    public static String teamEnchItemName_Prot3;
    public static String teamEnchItemName_Prot4;
    public static String shopLevelup;
    public static String shopItem;
    public static String meanDiamond;
    public static String messLevelUpSharp1;
    public static String messLevelUpProt1;
    public static String messLevelUpProt2;
    public static String messLevelUpProt3;
    public static String messLevelUpProt4;
    public static String messLevelUpFailed;
    public static Material LevelupItem;
    public static int sharp1Cost2v2;
    public static int prot1Cost2v2;
    public static int prot2Cost2v2;
    public static int prot3Cost2v2;
    public static int prot4Cost2v2;
    public static int sharp1Cost4v4;
    public static int prot1Cost4v4;
    public static int prot2Cost4v4;
    public static int prot3Cost4v4;
    public static int prot4Cost4v4;
    public static boolean grassWalk;
    public static String messreloadnow;
    public static String messreloadsucc;
    public static Configuration c;
    public static void loadMainConfig(CommandSender executer, boolean firstload) {

        Bukkit.getPluginManager().getPlugin("BedwarsKit").saveDefaultConfig();
        c = Bukkit.getPluginManager().getPlugin("BedwarsKit").getConfig();
        if (c == null) {
            Bukkit.getPluginManager().getPlugin("BedwarsKit").saveDefaultConfig();
            loadDefaultConfig();
            Logger l = Bukkit.getLogger();
            l.warning("Unable to find configuration file, loading default configuration");
        } else {
            if (c.getString("messreloadnow") != null) {
                messreloadnow = c.getString("messreloadnow");
            } else {
                messreloadnow = "正在重新加载配置文件";
            }

            if (c.getString("messreloadsucc") != null) {
                messreloadsucc = c.getString("messreloadsucc");
            } else {
                messreloadsucc = "成功重载配置文件";
            }


            if (!firstload) {
                executer.sendMessage(t(messreloadnow));
            }


            if (c.getString("grassWalk") != null) {
                grassWalk = c.getBoolean("grassWalk");
            } else {
                er(grassWalk + " ", "vaule is null");
            }

            if (c.getString("noHunger") != null) {
                noHunger = c.getBoolean("noHunger");
            } else {
                er(noHunger + " ", "vaule is null");
            }
            if (c.getString("noPearlDamage") != null) {
                noPearlDamage = c.getBoolean("noPearlDamage");
            } else {
                er(noPearlDamage + " ", "vaule is null");
            }
            if (c.getString("breakBedCheck") != null) {
                breakBedCheck = c.getBoolean("breakBedCheck");
            } else {
                er(breakBedCheck + " ", "vaule is null");
            }
            if (c.getString("deathGameMode") != null) {
                deathGameMode = c.getBoolean("deathGameMode");
            } else {
                er(deathGameMode + " ", "vaule is null");
            }
            if (c.getString("startKitCompass") != null) {
                startKitCompass = c.getBoolean("startKitCompass");
            } else {
                er(startKitCompass + " ", "vaule is null");
            }
            if (c.getString("customScoreboard") != null) {
                customScoreboard = c.getBoolean("customScoreboard");
            } else {
                er(customScoreboard + " ", "vaule is null");
            }
            if (c.getString("antiDrop") != null) {
                antiDrop = c.getBoolean("antiDrop");
            } else {
                er(antiDrop + " ", "vaule is null");
            }
            if (c.getString("kill_res") != null) {
                kill_res = c.getBoolean("kill_res");
            } else {
                er(kill_res + " ", "vaule is null");
            }
            if (c.getString("rushModeSpeed") != null) {
                rushModeSpeed = c.getBoolean("rushModeSpeed");
            } else {
                er(rushModeSpeed + " ", "vaule is null");
            }
            if (c.getString("breakTitle") != null) {
                breakTitle = c.getBoolean("breakTitle");
            } else {
                er(breakTitle + " ", "vaule is null");
            }

            if (c.getString("sharp1Cost2v2") != null) {
                sharp1Cost2v2 = c.getInt("sharp1Cost2v2");
            } else {
                er(sharp1Cost2v2 + " ", "vaule is null");
            }

            if (c.getString("rushModeSpeedLevel") != null) {
                rushModeSpeedLevel = c.getInt("rushModeSpeedLevel");
            } else {
                er(rushModeSpeedLevel + " ", "vaule is null");
            }
            if (c.getString("respawnDelay") != null) {
                respawnDelay = c.getInt("respawnDelay");
            } else {
                er(respawnDelay + " ", "vaule is null");
            }
            if (c.getString("maxFoodLevel") != null) {
                maxFoodLevel = c.getInt("maxFoodLevel");
            } else {
                er(maxFoodLevel + " ", "vaule is null");
            }

            if (c.getString("prot1Cost2v2") != null) {
                prot1Cost2v2 = c.getInt("prot1Cost2v2");
            } else {
                er(prot1Cost2v2 + " ", "vaule is null");
            }
            if (c.getString("prot2Cost2v2") != null) {
                prot2Cost2v2 = c.getInt("prot2Cost2v2");
            } else {
                er(prot2Cost2v2 + " ", "vaule is null");
            }
            if (c.getString("prot3Cost2v2") != null) {
                prot3Cost2v2 = c.getInt("prot3Cost2v2");
            } else {
                er(prot3Cost2v2 + " ", "vaule is null");
            }
            if (c.getString("prot4Cost2v2") != null) {
                prot4Cost2v2 = c.getInt("prot4Cost2v2");
            } else {
                er(prot4Cost2v2 + " ", "vaule is null");
            }
            if (c.getString("sharp1Cost4v4") != null) {
                sharp1Cost4v4 = c.getInt("sharp1Cost4v4");
            } else {
                er(sharp1Cost4v4 + " ", "vaule is null");
            }
            if (c.getString("prot1Cost4v4") != null) {
                prot1Cost4v4 = c.getInt("prot1Cost4v4");
            } else {
                er(prot1Cost4v4 + " ", "vaule is null");
            }
            if (c.getString("prot2Cost4v4") != null) {
                prot2Cost4v4 = c.getInt("prot2Cost4v4");
            } else {
                er(prot2Cost4v4 + " ", "vaule is null");
            }
            if (c.getString("prot3Cost4v4") != null) {
                prot3Cost4v4 = c.getInt("prot3Cost4v4");
            } else {
                er(prot3Cost4v4 + " ","vaule is null");
            }

            if (c.getString("prot4Cost4v4") != null) {
                prot4Cost4v4 = c.getInt("prot4Cost4v4");
            } else {
                er(prot4Cost4v4 + " ", "vaule is null");
            }

            if (c.getString("tpDis") != null) {
                tpDis = c.getInt("tpDis");
            } else {
                er(tpDis + " ", "vaule is null");
            }

            LevelupItem = Material.BOOK_AND_QUILL;

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

            NoBreakList.add(Material.WOOD_SWORD);
            NoBreakList.add(Material.STONE_SWORD);
            NoBreakList.add(Material.IRON_SWORD);
            NoBreakList.add(Material.GOLD_SWORD);
            NoBreakList.add(Material.DIAMOND_SWORD);

            if (c.getString("messLevelUpFailed") != null) {
                messLevelUpFailed = c.getString("messLevelUpFailed");
            } else {
                er("messLevelUpFailed", "vaule is null");
            }
            if (c.getString("messLevelUpSharp1") != null) {
                messLevelUpSharp1 = c.getString("messLevelUpSharp1");
            } else {
                er("messLevelUpSharp1", "vaule is null");
            }
            if (c.getString("messLevelUpProt1") != null) {
                messLevelUpProt1 = c.getString("messLevelUpProt1");
            } else {
                er("messLevelUpProt1", "vaule is null");
            }
            if (c.getString("messLevelUpProt2") != null) {
                messLevelUpProt2 = c.getString("messLevelUpProt2");
            } else {
                er("messLevelUpProt2", "vaule is null");
            }
            if (c.getString("messLevelUpProt3") != null) {
                messLevelUpProt3 = c.getString("messLevelUpProt3");
            } else {
                er("messLevelUpProt3", "vaule is null");
            }
            if (c.getString("messLevelUpProt4") != null) {
                messLevelUpProt4 = c.getString("messLevelUpProt4");
            } else {
                er("messLevelUpProt4", "vaule is null");
            }
            if (c.getString("meanDiamond") != null) {
                meanDiamond = c.getString("meanDiamond");
            } else {
                er("meanDiamond", "vaule is null");
            }
            if (c.getString("shopItem") != null) {
                shopItem = c.getString("shopItem");
            } else {
                er("shopItem", "vaule is null");
            }
            if (c.getString("shopLevelup") != null) {
                shopLevelup = c.getString("shopLevelup");
            } else {
                er("shopLevelup", "vaule is null");
            }
            if (c.getString("teamEnchItemName_Sharp1") != null) {
                teamEnchItemName_Sharp1 = c.getString("teamEnchItemName_Sharp1");
            } else {
                er("teamEnchItemName_Sharp1", "vaule is null");
            }

            if (c.getString("teamEnchItemName_Prot1") != null) {
                teamEnchItemName_Prot1 = c.getString("teamEnchItemName_Prot1");
            } else {
                er("teamEnchItemName_Prot1", "vaule is null");
            }
            if (c.getString("teamEnchItemName_Prot2") != null) {
                teamEnchItemName_Prot2 = c.getString("teamEnchItemName_Prot2");
            } else {
                er("teamEnchItemName_Prot2", "vaule is null");
            }
            if (c.getString("teamEnchItemName_Prot3") != null) {
                teamEnchItemName_Prot3 = c.getString("teamEnchItemName_Prot3");
            } else {
                er("teamEnchItemName_Prot3", "vaule is null");
            }
            if (c.getString("teamEnchItemName_Prot4") != null) {
                teamEnchItemName_Prot4 = c.getString("teamEnchItemName_Prot4");
            } else {
                er("teamEnchItemName_Prot4", "vaule is null");
            }
            if (c.getString("dateDisplay") != null) {
                dateDisplay = c.getString("dateDisplay");
            } else {
                er("dateDisplay", "vaule is null");
            }
            if (c.getString("relTeamColorName_Red") != null) {
                relTeamColorName_Red = c.getString("relTeamColorName_Red");
            } else {
                er("relTeamColorName_Red", "vaule is null");
            }
            if (c.getString("relTeamColorName_Blue") != null) {
                relTeamColorName_Blue = c.getString("relTeamColorName_Blue");
            } else {
                er("relTeamColorName_Blue", "vaule is null");
            }
            if (c.getString("relTeamColorName_Green") != null) {
                relTeamColorName_Green = c.getString("relTeamColorName_Green");
            } else {
                er("relTeamColorName_Green", "vaule is null");
            }
            if (c.getString("relTeamColorName_Yellow") != null) {
                relTeamColorName_Yellow = c.getString("relTeamColorName_Yellow");
            } else {
                er("relTeamColorName_Yellow", "vaule is null");
            }
            if (c.getString("relTeamColorName_Aqua") != null) {
                relTeamColorName_Aqua = c.getString("relTeamColorName_Aqua");
            } else {
                er("relTeamColorName_Aqua", "vaule is null");
            }
            if (c.getString("relTeamColorName_White") != null) {
                relTeamColorName_White = c.getString("relTeamColorName_White");
            } else {
                er("relTeamColorName_White", "vaule is null");
            }
            if (c.getString("relTeamColorName_Gray") != null) {
                relTeamColorName_Gray = c.getString("relTeamColorName_Gray");
            } else {
                er("relTeamColorName_Gray", "vaule is null");
            }
            if (c.getString("relTeamColorName_Pink") != null) {
                relTeamColorName_Pink = c.getString("relTeamColorName_Pink");
            } else {
                er("relTeamColorName_Pink", "vaule is null");
            }
            if (c.getString("breakTitleAll") != null) {
                breakTitleAll = c.getString("breakTitleAll");
            } else {
                er("breakTitleAll", "vaule is null");
            }
            if (c.getString("breakSubTitleAll") != null) {
                breakSubTitleAll = c.getString("breakSubTitleAll");
            } else {
                er(breakSubTitleAll, "vaule is null");
            }
            if (c.getString("breakTitleBreakPlayer") != null) {
                breakTitleBreakPlayer = c.getString("breakTitleBreakPlayer");
            } else {
                er(breakTitleBreakPlayer, "vaule is null");
            }
            if (c.getString("breakSubTitleBreakPlayer") != null) {
                breakSubTitleBreakPlayer = c.getString("breakSubTitleBreakPlayer");
            } else {
                er(breakSubTitleBreakPlayer, "vaule is null");
            }
            if (c.getString("breakTitleBreakTeam") != null) {
                breakTitleBreakTeam = c.getString("breakTitleBreakTeam");
            } else {
                er(breakTitleBreakTeam, "vaule is null");
            }
            if (c.getString("breakSubTitleBreakTeam") != null) {
                breakSubTitleBreakTeam = c.getString("breakSubTitleBreakTeam");
            } else {
                er("breakSubTitleBreakTeam", "vaule is null");
            }
            if (c.getString("rushWorld") != null) {
                rushWorld = c.getString("rushWorld");
            } else {
                er("rushWorld", "vaule is null");
            }
            if (c.getString("rushWorld2v2") != null) {
                rushWorld2v2 = c.getString("rushWorld2v2");
            } else {
                er("rushWorld2v2", "vaule is null");
            }
            if (c.getString("rushWorld4v4") != null) {
                rushWorld4v4 = c.getString("rushWorld4v4");
            } else {
                er(rushWorld4v4, "vaule is null");
            }
            if (c.getString("lobbyWorld") != null) {
                lobbyWorld = c.getString("lobbyWorld");
            } else {
                er(lobbyWorld, "vaule is null");
            }
            if (c.getString("respawnTitle") != null) {
                respawnTitle = c.getString("respawnTitle");
            } else {
                er("respawnTitle", "vaule is null");
            }
            if (c.getString("respawnSubTitle") != null) {
                respawnSubTitle = c.getString("respawnSubTitle");
            } else {
                er("respawnSubTitle", "vaule is null");
            }
            if (c.getString("respawnSuccTitle") != null) {
                respawnSuccTitle = c.getString("respawnSuccTitle");
            } else {
                er("respawnSuccTitle", "vaule is null");
            }
            if (c.getString("respawnSuccSubTitle") != null) {
                respawnSuccSubTitle = c.getString("respawnSuccSubTitle");
            } else {
                er("respawnSuccSubTitle", "vaule is null");
            }
            if (c.getString("scoreboardTitle") != null) {
                scoreboardTitle = c.getString("scoreboardTitle");
            } else {
                er("scoreboardTitle", "vaule is null");
            }
            if (c.getString("meanTeamBedYes") != null) {
                meanTeamBedYes = c.getString("meanTeamBedYes");
            } else {
                er("meanTeamBedYes", "vaule is null");
            }
            if (c.getString("meanTeamNone") != null) {
                meanTeamNone = c.getString("meanTeamNone");
            } else {
                er(meanTeamNone, "vaule is null");
            }
            if (c.getString("meanTeamBedNo") != null) {
                meanTeamBedNo = c.getString("meanTeamBedNo");
            } else {
                er("meanTeamBedNo", "vaule is null");
            }
            if (c.getString("serverIp") != null) {
                serverIp = c.getString("serverIp");
            } else {
                er("serverIp", "vaule is null");
            }
            if (c.getString("meanYou") != null) {
                meanYou = c.getString("meanYou");
            } else {
                er("meanYou", "vaule is null");
            }
            if (c.getString("meanNotYou") != null) {
                meanNotYou = c.getString("meanNotYou");
            } else {
                er("meanNotYou", "vaule is null");
            }

            if (!firstload) {
                executer.sendMessage(t(messreloadsucc));
            }
        }
        }
public static void loadDefaultConfig () {

    messreloadnow = "正在重新加载配置文件";
    messreloadsucc = "成功重载配置文件";

    grassWalk = true;

    sharp1Cost2v2 = 4;
    prot1Cost2v2 = 2;
    prot2Cost2v2 = 4;
    prot3Cost2v2 = 8;
    prot4Cost2v2 = 16;

    sharp1Cost4v4 = 8;
    prot1Cost4v4 = 5;
    prot2Cost4v4 = 10;
    prot3Cost4v4 = 20;
    prot4Cost4v4 = 30;

    LevelupItem = Material.BOOK_AND_QUILL;

    messLevelUpFailed = "&c资源不够或已拥有更高级的附魔!";

    messLevelUpSharp1 = "&6{player} &a购买了团队升级 &6锋利 I";
    messLevelUpProt1 = "&6{player} &a购买了团队升级 &6保护 I";
    messLevelUpProt2 = "&6{player} &a购买了团队升级 &6保护 II";
    messLevelUpProt3 = "&6{player} &a购买了团队升级 &6保护 III";
    messLevelUpProt4 = "&6{player} &a购买了团队升级 &6保护 IV";
    meanDiamond = "&b钻石";
    shopItem = "物品商店";
    shopLevelup = "升级商店";
    teamEnchItemName_Sharp1 = "锋利 I";
    teamEnchItemName_Prot1 = "保护 I";
    teamEnchItemName_Prot2 = "保护 II";
    teamEnchItemName_Prot3 = "保护 III";
    teamEnchItemName_Prot4 = "保护 IV";

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

    kill_res = true;
    dateDisplay = "&7{date}";
    relTeamColorName_Red = "红之队";
    relTeamColorName_Blue = "蓝之队";
    relTeamColorName_Green = "绿之队";
    relTeamColorName_Yellow = "黄之队";
    relTeamColorName_Aqua = "青之队";
    relTeamColorName_White = "白之队";
    relTeamColorName_Gray = "灰之队";
    relTeamColorName_Pink = "粉之队";
    rushModeSpeedLevel = 1;
    rushModeSpeed = true;
    breakTitle = true;
    breakTitleAll = "{BreakTeamColor}{BreakTeamName}&c的床已被摧毁";
    breakSubTitleAll = "&7摧毁者:{BreakPlayerTeamColor}{BreakPlayerName}";
    breakTitleBreakPlayer = "{BreakTeamColor}{BreakTeamName}";
    breakSubTitleBreakPlayer = "&a床已被你摧毁";
    breakTitleBreakTeam = "&c床已被摧毁";
    breakSubTitleBreakTeam = "&7摧毁者:{BreakPlayerTeamColor}{BreakPlayerName}";
    noHunger = true;
    maxFoodLevel = 20;
    noPearlDamage = true;
    breakBedCheck = true;
    tpDis = 0.74;
    deathGameMode = true;
    rushWorld = "rush";
    rushWorld2v2 = "2v2rush";
    rushWorld4v4 = "4v4rush";
    lobbyWorld = "lobby";
    startKitCompass = false;
    respawnDelay = 5;
    respawnTitle = "&e{timeleft}&a秒后复活";
    respawnSubTitle = " ";
    respawnSuccTitle = "&a已回城";
    respawnSuccSubTitle = " ";
    customScoreboard = true;
    scoreboardTitle = "&e起床战争";
    antiDrop = true;
    meanTeamBedYes = "&a&l✔";
    meanTeamNone = "&c&l✘";
    meanTeamBedNo = "{aliveCount}";
    serverIp = "&eMCBBS@the_starfish";
    meanYou = "{teamColor}→ ";
    meanNotYou = "  ";
}
   }