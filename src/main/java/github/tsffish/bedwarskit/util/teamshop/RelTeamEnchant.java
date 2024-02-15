package github.tsffish.bedwarskit.util.teamshop;

import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameManager;
import io.github.bedwarsrel.game.Team;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class RelTeamEnchant {
    private static Plugin plugin = github.tsffish.bedwarskit.Main.getPlugin(github.tsffish.bedwarskit.Main.class);
    private static Map<String, String[]> teamEnchantListsharp = new HashMap<>(16);
    private static Map<String, String[]> teamEnchantListprot = new HashMap<>(16);
    public static void loadMapTeam(long time) {

        new BukkitRunnable() {
            @Override
            public void run() {

                BedwarsRel bedwarsRel = BedwarsRel.getInstance();
                GameManager gameManager = bedwarsRel.getGameManager();


                for (Game game : gameManager.getGames()) {
                    if (game != null){
                    String mapName = game.getRegion().getName();

                    for (Map.Entry<String, Team> entry : game.getTeams().entrySet()) {
                        String teamName = entry.getKey();

                        setDefaultTeamEnch(mapName,teamName);
						break;
                    }
                    }
					break;
                }

            }
            
        }.runTaskLater(plugin, time);
    }

    private static final String defaultValue = "0";
    public static void setDefaultTeamEnch(String mapName,String teamName){
        teamEnchantListsharp.put(mapName, new String[]{teamName, defaultValue});
        teamEnchantListprot.put(mapName, new String[]{teamName, defaultValue});
    }

    public static String[] getMapEnchantsharp(String mapName){
        return teamEnchantListsharp.get(mapName);
    }

    public static String[] getMapEnchantprot(String mapName){
        return teamEnchantListprot.get(mapName);
    }
    public static List<String[]> getAllMapEnchantsharp() {
        return new ArrayList<>(teamEnchantListsharp.values());
    }

    public static List<String[]> getAllMapEnchantprot() {
        return new ArrayList<>(teamEnchantListprot.values());
    }

    public static Map<String, String[]> getTeamEnchantListsharp() {
        return new HashMap<>(teamEnchantListsharp);
    }

    public static Map<String, String[]> getTeamEnchantListprot() {
        return new HashMap<>(teamEnchantListprot);
    }

    public static void updateTeamEnchantListsharp(String mapName, String teamName, String value) {
        teamEnchantListsharp.put(mapName, new String[]{teamName, value});
    }
    public static void updateTeamEnchantListprot(String mapName, String teamName, String value) {
        teamEnchantListprot.put(mapName, new String[]{teamName, value});
    }

    public static boolean isprotValueContains(String mapName, String teamName, String valueToCheck) {
        if (teamEnchantListprot.containsKey(mapName)) {
            String[] values = teamEnchantListprot.get(mapName);
            return values[0].equals(teamName) && values[1].equals(valueToCheck);
        }
        return false;
    }
    public static boolean issharpValueContains(String mapName, String teamName, String valueToCheck) {
        if (teamEnchantListsharp.containsKey(mapName)) {
            String[] values = teamEnchantListsharp.get(mapName);
            return values[0].equals(teamName) && values[1].equals(valueToCheck);
        }
        return false;
    }





}

