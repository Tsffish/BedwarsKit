package github.tsffish.bedwarskit.config;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;

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
    public static String scoreboardTitle = "";
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
    public static List<Material> NoDropList = new ArrayList();

    public MainConfigHandler() {
    }

    public static void loadMainConfig() {
        NoDropList.add(Material.LEATHER_HELMET);
        NoDropList.add(Material.LEATHER_CHESTPLATE);
        NoDropList.add(Material.LEATHER_LEGGINGS);
        NoDropList.add(Material.LEATHER_BOOTS);
        NoDropList.add(Material.CHAINMAIL_HELMET);
        NoDropList.add(Material.CHAINMAIL_CHESTPLATE);
        NoDropList.add(Material.CHAINMAIL_LEGGINGS);
        NoDropList.add(Material.CHAINMAIL_BOOTS);
        NoDropList.add(Material.IRON_HELMET);
        NoDropList.add(Material.IRON_CHESTPLATE);
        NoDropList.add(Material.IRON_LEGGINGS);
        NoDropList.add(Material.IRON_BOOTS);
        NoDropList.add(Material.DIAMOND_HELMET);
        NoDropList.add(Material.DIAMOND_CHESTPLATE);
        NoDropList.add(Material.DIAMOND_LEGGINGS);
        NoDropList.add(Material.DIAMOND_BOOTS);
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
        startKitCompass = true;
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
        serverIp = "&eMCBBS@the_starfish";
        meanYou = "{teamColor}→ ";
        meanNotYou = "  ";
    }
}