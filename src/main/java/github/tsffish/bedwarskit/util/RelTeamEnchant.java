package github.tsffish.bedwarskit.util;

import github.tsffish.bedwarskit.Main;
import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameManager;
import io.github.bedwarsrel.game.Team;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class RelTeamEnchant {

    public static Map<String, String[]> teamEnchantListSword = new HashMap<>();
    public static Map<String, String[]> teamEnchantListProt = new HashMap<>();
    public static void loadMapTeam(long time) {
        Plugin plugin = Main.getProvidingPlugin(Main.class);
        new BukkitRunnable() {
            @Override
            public void run() {

                BedwarsRel bedwarsRel = BedwarsRel.getInstance();
                GameManager gameManager = bedwarsRel.getGameManager();

                for (Game game : gameManager.getGames()) {

                    String mapName = game.getRegion().getName();

                    for (Map.Entry<String, Team> entry : game.getTeams().entrySet()) {
                        String teamName = entry.getKey();

                        teamEnchantListSword.put(mapName, new String[]{teamName, "0"});

                        teamEnchantListProt.put(mapName, new String[]{teamName, "0"});
                    }
                }

            }
            // End
        }.runTaskLater(plugin, time);
    }
}

