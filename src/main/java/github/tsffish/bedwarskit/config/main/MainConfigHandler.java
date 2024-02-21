package github.tsffish.bedwarskit.config.main;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;
import java.util.Map;

public class MainConfigHandler {
    public static FileConfiguration c;
    public static boolean breakTitle;
    public static boolean noHunger;
    public static boolean noPearlDamage;
    public static boolean noPearlDamage_TPSound;
    public static boolean breakBedCheck;
    public static boolean deathGameMode;
    public static boolean startKitCompass;
    public static boolean customScoreboard;
    public static boolean antiDrop;
    public static boolean grassPaneWalk;
    public static boolean cleanBottle;
    public static boolean cleanBed;
    public static boolean levelupShopDelayOpen;
    public static boolean kill_res;
    public static boolean NoItemBreak;
    public static boolean levelupShop;
    public static boolean damagefb_attackBlood;
    public static boolean startmess;


    public static int chainPrice;
    public static int ironPrice;
    public static int diamondPrice;
    public static int sharp1Cost2v2;
    public static int sharp2Cost2v2;
    public static int sharp3Cost2v2;
    public static int sharp4Cost2v2;
    public static int prot1Cost2v2;
    public static int prot2Cost2v2;
    public static int prot3Cost2v2;
    public static int prot4Cost2v2;


    public static int sharp1Cost4v4;
    public static int sharp2Cost4v4;
    public static int sharp3Cost4v4;
    public static int sharp4Cost4v4;
    public static int prot1Cost4v4;
    public static int prot2Cost4v4;
    public static int prot3Cost4v4;
    public static int prot4Cost4v4;
    public static int maxFoodLevel;
    public static int respawnDelay;

    public static double tpDis;



    public static String breakTitleAll;
    public static String breakSubTitleAll;
    public static String breakTitleBreakPlayer;
    public static String breakSubTitleBreakPlayer;
    public static String breakTitleBreakTeam;
    public static String breakSubTitleBreakTeam;

    public static String rushWorld;
    public static String rushWorld2v2;
    public static String rushWorld4v4;
    public static String lobbyWorld;
    public static String respawnTitle;
    public static String respawnSubTitle;
    public static String respawnChat;
    public static String respawnActionBar;

    public static String respawnSuccTitle;
    public static String respawnSuccSubTitle;
    public static String respawnSuccChat;
    public static String respawnSuccActionBar;
    public static String meanTeamBedYes;
    public static String meanTeamBedNo;
    public static String meanTeamNone;
    public static String meanYou;
    public static String meanNotYou;
    public static String serverIp;
    public static String relTeamName_Red;
    public static String relTeamName_Blue;
    public static String relTeamName_Green;
    public static String relTeamName_Yellow;
    public static String relTeamName_Aqua;
    public static String relTeamName_White;
    public static String relTeamName_Gray;
    public static String relTeamName_Pink;
    public static String teamEnchItemName_sharp1;
    public static String teamEnchItemName_prot1;
    public static String teamEnchItemName_prot2;
    public static String teamEnchItemName_prot3;
    public static String teamEnchItemName_prot4;
    public static String shopLevelup;

    public static String LevelupItemName;
    public static String meanIron;
    public static String meanGold;
    public static String meanDiamond;
    public static String meanEmerlad;
    public static String messLevelUpsharp1;
    public static String messLevelUpsharp2;
    public static String messLevelUpsharp3;
    public static String messLevelUpsharp4;
    public static String messLevelUpprot1;
    public static String messLevelUpprot2;
    public static String messLevelUpprot3;
    public static String messLevelUpprot4;
    public static String messLevelUpFailed;
    public static String messreloadnow;
    public static String messreloadsucc;
    public static String meanBedwars;
    public static String mean2v2Mode;
    public static String mean4v4Mode;
    public static String levelupShopOpenMode;
    public static String levelupShopOpenModeEntityName;
    public static String ScoreBoard2v2Title;
    public static String ScoreBoard2v2Line01;
    public static String ScoreBoard2v2Line02;
    public static String ScoreBoard2v2Line03;
    public static String ScoreBoard2v2Line04;
    public static String ScoreBoard2v2Line05;
    public static String ScoreBoard2v2Line06;
    public static String ScoreBoard2v2Line07;
    public static String ScoreBoard2v2Line08;
    public static String ScoreBoard2v2Line09;
    public static String ScoreBoard2v2Line10;
    public static String ScoreBoard2v2Line11;
    public static String ScoreBoard2v2Line12;
    public static String ScoreBoard2v2Line13;
    public static String ScoreBoard2v2Line14;
    public static String ScoreBoard2v2Line15;
    public static String ScoreBoard2v2Line16;
    public static String ScoreBoard4v4Title;
    public static String ScoreBoard4v4Line01;
    public static String ScoreBoard4v4Line02;
    public static String ScoreBoard4v4Line03;
    public static String ScoreBoard4v4Line04;
    public static String ScoreBoard4v4Line05;
    public static String ScoreBoard4v4Line06;
    public static String ScoreBoard4v4Line07;
    public static String ScoreBoard4v4Line08;
    public static String ScoreBoard4v4Line09;
    public static String ScoreBoard4v4Line10;
    public static String ScoreBoard4v4Line11;
    public static String ScoreBoard4v4Line12;
    public static String ScoreBoard4v4Line13;
    public static String ScoreBoard4v4Line14;
    public static String ScoreBoard4v4Line15;
    public static String ScoreBoard4v4Line16;
    public static Map<Integer, String> ScoreBoard2v2Line;
    public static Map<Integer, String> ScoreBoard4v4Line;

    public static String meanSelKitSucc;

    public static Material LevelupItemType;
    public static Material chainPriceType;
    public static Material ironPriceType;
    public static Material diamondPriceType;
    public static Material upToChainArmor;
    public static Material upToIronArmor;
    public static Material upToDiamondArmor;
    public static List<String> noMoveList;
    public static List<String> nobreakList;
    public static List<String> giveSharpEnchList;
    public static List<String> giveProtEnchList;

    public static String damagefb_attackBloodMode;
    public static boolean damagefb_attackmess;
    public static String damagefb_attackchat;
    public static String damagefb_attackTitle;
    public static String damagefb_attackSubTitle;
    public static String damagefb_attackactionbar;
    public static int damagefb_attackBloodType;



    public static String startmess_all_chat;
    public static String startmess_all_title;
    public static String startmess_all_subtitle;
    public static String startmess_all_actionbar;



    public static boolean gametask;
    public static boolean gametask_spawntime;
    public static int gametask_spawntime_iron_base;
    public static int gametask_spawntime_iron_1;
    public static int gametask_spawntime_iron_2;
    public static int gametask_spawntime_iron_3;
    public static int gametask_spawntime_iron_4;

    public static int gametask_spawntime_gold_base;
    public static int gametask_spawntime_gold_1;
    public static int gametask_spawntime_gold_2;
    public static int gametask_spawntime_gold_3;
    public static int gametask_spawntime_gold_4;

    public static int gametask_spawntime_diamond_base;
    public static int gametask_spawntime_diamond_1;
    public static int gametask_spawntime_diamond_2;
    public static int gametask_spawntime_diamond_3;
    public static int gametask_spawntime_diamond_4;

    public static int gametask_spawntime_emerald_base;
    public static int gametask_spawntime_emerald_1;
    public static int gametask_spawntime_emerald_2;
    public static int gametask_spawntime_emerald_3;
    public static int gametask_spawntime_emerald_4;


    public static boolean gametask_finalbattle;

    public static int gametask_finalbattle_boundaries_warnidis;
    public static double gametask_finalbattle_boundaries_damagebuffer;
    public static double gametask_finalbattle_boundaries_damage;
    public static String shopItem;
    public static boolean update_reportOp;


    public static int gametask_spawntime_tasks_iron1;
    public static int gametask_spawntime_tasks_iron2;
    public static int gametask_spawntime_tasks_iron3;
    public static int gametask_spawntime_tasks_iron4;
    public static int gametask_spawntime_tasks_gold1;
    public static int gametask_spawntime_tasks_gold2;
    public static int gametask_spawntime_tasks_gold3;
    public static int gametask_spawntime_tasks_gold4;
    public static int gametask_spawntime_tasks_diamond1;
    public static int gametask_spawntime_tasks_diamond2;
    public static int gametask_spawntime_tasks_diamond3;
    public static int gametask_spawntime_tasks_diamond4;
    public static int gametask_spawntime_tasks_emerald1;
    public static int gametask_spawntime_tasks_emerald2;
    public static int gametask_spawntime_tasks_emerald3;
    public static int gametask_spawntime_tasks_emerald4;

    public static int gametask_finalbattle_time;

    public static boolean preventloadworld;

    public static boolean placeCorrect_ResSpawner;
    public static String placeCorrect_ResSpawner_mess_chat;
    public static String placeCorrect_ResSpawner_mess_title;
    public static String placeCorrect_ResSpawner_mess_subtitle;
    public static String placeCorrect_ResSpawner_mess_actionbar;

    public static boolean placeCorrect_PlayerSpawnLoc;
    public static String placeCorrect_PlayerSpawnLoc_mess_chat;
    public static String placeCorrect_PlayerSpawnLoc_mess_title;
    public static String placeCorrect_PlayerSpawnLoc_mess_subtitle;
    public static String placeCorrect_PlayerSpawnLoc_mess_actionbar;

    public static boolean killfb_sendmess;
    public static String killfb_sendmess_chat;
    public static String killfb_sendmess_title;
    public static String killfb_sendmess_subtitle;
    public static String killfb_sendmess_actionbar;

    public static boolean placeCorrect_notInGame;

    public static boolean breakCorrect_notInGame;

    public static boolean CleanHostileOnStart;


    public static String kill_res_chat;

    public static String meanGameEnd;
    public static double gametask_boundaries_size;
    public static double gametask_finalbattle_boundaries_size;
    public static long gametask_finalbattle_boundaries_time;


    public static boolean placeCorrect_notInGame_OpBypass;
    public static boolean breakCorrect_notInGame_OpBypass;
    public static String meanSecond;
    public static String gametask_name_finalbattle;

    public static String gametask_name_iron1;
    public static String gametask_name_iron2;
    public static String gametask_name_iron3;
    public static String gametask_name_iron4;
    public static String gametask_name_gold1;
    public static String gametask_name_gold2;
    public static String gametask_name_gold3;
    public static String gametask_name_gold4;
    public static String gametask_name_diamond1;
    public static String gametask_name_diamond2;
    public static String gametask_name_diamond3;
    public static String gametask_name_diamond4;
    public static String gametask_name_emerald1;
    public static String gametask_name_emerald2;
    public static String gametask_name_emerald3;
    public static String gametask_name_emerald4;

    public static String gametask_mess_iron1_chat;
    public static String gametask_mess_iron1_title;
    public static String gametask_mess_iron1_subtitle;
    public static String gametask_mess_iron1_actionbar;
    public static String gametask_mess_gold1_chat;
    public static String gametask_mess_gold1_title;
    public static String gametask_mess_gold1_subtitle;
    public static String gametask_mess_gold1_actionbar;
    public static String gametask_mess_diamond1_chat;
    public static String gametask_mess_diamond1_title;
    public static String gametask_mess_diamond1_subtitle;
    public static String gametask_mess_diamond1_actionbar;
    public static String gametask_mess_emerald1_chat;
    public static String gametask_mess_emerald1_title;
    public static String gametask_mess_emerald1_subtitle;
    public static String gametask_mess_emerald1_actionbar;

    public static String gametask_mess_iron2_chat;
    public static String gametask_mess_iron2_title;
    public static String gametask_mess_iron2_subtitle;
    public static String gametask_mess_iron2_actionbar;
    public static String gametask_mess_gold2_chat;
    public static String gametask_mess_gold2_title;
    public static String gametask_mess_gold2_subtitle;
    public static String gametask_mess_gold2_actionbar;
    public static String gametask_mess_diamond2_chat;
    public static String gametask_mess_diamond2_title;
    public static String gametask_mess_diamond2_subtitle;
    public static String gametask_mess_diamond2_actionbar;

    public static String gametask_mess_emerald2_chat;
    public static String gametask_mess_emerald2_title;
    public static String gametask_mess_emerald2_subtitle;
    public static String gametask_mess_emerald2_actionbar;
    public static String gametask_mess_iron3_chat;
    public static String gametask_mess_iron3_title;
    public static String gametask_mess_iron3_subtitle;
    public static String gametask_mess_iron3_actionbar;
    public static String gametask_mess_gold3_chat;
    public static String gametask_mess_gold3_title;
    public static String gametask_mess_gold3_subtitle;
    public static String gametask_mess_gold3_actionbar;
    public static String gametask_mess_diamond3_chat;
    public static String gametask_mess_diamond3_title;
    public static String gametask_mess_diamond3_subtitle;
    public static String gametask_mess_diamond3_actionbar;
    public static String gametask_mess_emerald3_chat;
    public static String gametask_mess_emerald3_title;
    public static String gametask_mess_emerald3_subtitle;
    public static String gametask_mess_emerald3_actionbar;

    public static String gametask_mess_iron4_chat;
    public static String gametask_mess_iron4_title;
    public static String gametask_mess_iron4_subtitle;
    public static String gametask_mess_iron4_actionbar;
    public static String gametask_mess_gold4_chat;
    public static String gametask_mess_gold4_title;
    public static String gametask_mess_gold4_subtitle;
    public static String gametask_mess_gold4_actionbar;
    public static String gametask_mess_diamond4_chat;
    public static String gametask_mess_diamond4_title;
    public static String gametask_mess_diamond4_subtitle;
    public static String gametask_mess_diamond4_actionbar;
    public static String gametask_mess_emerald4_chat;
    public static String gametask_mess_emerald4_title;
    public static String gametask_mess_emerald4_subtitle;
    public static String gametask_mess_emerald4_actionbar;

    public static String gametask_mess_finalbattle_chat;
    public static String gametask_mess_finalbattle_title;
    public static String gametask_mess_finalbattle_subtitle;
    public static String gametask_mess_finalbattle_actionbar;


    public static String lobbyjoinTeamMess_chat;
    public static String lobbyjoinTeamMess_title;
    public static String lobbyjoinTeamMess_subtitle;
    public static String lobbyjoinTeamMess_actionbar;


    public static int teamEnchInvRow;



    public static boolean tab;


    public static boolean tab_is_multiLine;
    public static String tab_head;
    public static List<String> tab_headList;
    public static String tab_foot;
    public static List<String> tab_footList;


    public static Material levelupsharpItemType;
    public static int levelupsharpItemSlot;
    public static Material levelupprotItemType;
    public static int levelupprotItemSlot;
    public static String teamEnchItemName_sharp2;
    public static String teamEnchItemName_sharp3;
    public static String teamEnchItemName_sharp4;
    public static String TeamEnchantMaxCost;

    public static String teamEnchItemName_sharpMax;
    public static String teamEnchItemName_protMax;



    public static Material leveluphasteItemType;
    public static int leveluphasteItemSlot;
    public static String teamEffItemName_haste1;
    public static String teamEffItemName_haste2;
    public static String teamEffItemName_hasteMax;
    public static Material leveluphealItemType;
    public static int leveluphealItemSlot;
    public static String teamEffItemName_heal1;
    public static String teamEffItemName_healMax;
    public static int haste1Cost2v2;
    public static int haste2Cost2v2;
    public static int heal1Cost2v2;

    public static int haste1Cost4v4;
    public static int haste2Cost4v4;
    public static int heal1Cost4v4;

    public static String messLevelUphaste1;
    public static String messLevelUphaste2;
    public static String messLevelUpheal1;


    public static String teamEffItemName_Haste1;
    public static String teamEffItemName_Haste2;
    public static String teamEffItemName_Heal1;

    public static boolean lobbyleaveTeam;
    public static String lobbyleaveTeamMess;

    public static int teamEff_Heal_dis;
    public static boolean dieOutGameItem_playAgain;
    public static String dieOutGameItem_playAgain_ItemName;
    public static Material dieOutGameItem_playAgain_ItemType;
    public static String dieOutGameItem_playAgain_ClickSendCommand;
    public static boolean killfb_oneHealthKill;
    public static Material killfb_oneHealthKill_itemType;
    public static String killfb_oneHealthKill_itemName;
    public static boolean creativeGameModeFix;

}
