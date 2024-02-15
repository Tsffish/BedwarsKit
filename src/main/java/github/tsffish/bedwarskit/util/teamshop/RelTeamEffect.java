package github.tsffish.bedwarskit.util.teamshop;

import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameManager;
import io.github.bedwarsrel.game.Team;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class RelTeamEffect {
    private static Plugin plugin = github.tsffish.bedwarskit.Main.getPlugin(github.tsffish.bedwarskit.Main.class);
    private static Map<String, String[]> teamEffectListhaste = new HashMap<>(16);
    private static Map<String, String[]> teamEffectListheal = new HashMap<>(16);
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

                        setDefaultTeamEff(mapName,teamName);
						break;
                    }
                    }
					break;
                }

            }
            
        }.runTaskLater(plugin, time);
    }

    private static final String defaultValue = "0";
    public static void setDefaultTeamEff(String mapName,String teamName){
        teamEffectListhaste.put(mapName, new String[]{teamName, defaultValue});
        teamEffectListheal.put(mapName, new String[]{teamName, defaultValue});
    }

    public static String[] getMapEffecthaste(String mapName){
        return teamEffectListhaste.get(mapName);
    }

    public static String[] getMapEffectheal(String mapName){
        return teamEffectListheal.get(mapName);
    }
    public static List<String[]> getAllMapEffecthaste() {
        return new ArrayList<>(teamEffectListhaste.values());
    }

    public static List<String[]> getAllMapEffectheal() {
        return new ArrayList<>(teamEffectListheal.values());
    }

    public static Map<String, String[]> getTeamEffectListhaste() {
        return new HashMap<>(teamEffectListhaste);
    }

    public static Map<String, String[]> getTeamEffectListheal() {
        return new HashMap<>(teamEffectListheal);
    }

    public static void updateTeamEffectListhaste(String mapName, String teamName, String value) {
        teamEffectListhaste.put(mapName, new String[]{teamName, value});
    }
    public static void updateTeamEffectListheal(String mapName, String teamName, String value) {
        teamEffectListheal.put(mapName, new String[]{teamName, value});
    }

    public static boolean ishasteValueContains(String mapName, String teamName, String valueToCheck) {
        if (teamEffectListhaste.containsKey(mapName)) {
            String[] values = teamEffectListhaste.get(mapName);
            return values[0].equals(teamName) && values[1].equals(valueToCheck);
        }
        return false;
    }
    public static boolean ishealValueContains(String mapName, String teamName, String valueToCheck) {
        if (teamEffectListheal.containsKey(mapName)) {
            String[] values = teamEffectListheal.get(mapName);
            return values[0].equals(teamName) && values[1].equals(valueToCheck);
        }
        return false;
    }





}

