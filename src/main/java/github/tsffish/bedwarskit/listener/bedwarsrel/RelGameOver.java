package github.tsffish.bedwarskit.listener.bedwarsrel;

import github.tsffish.bedwarskit.util.RelTeamEnchant;
import io.github.bedwarsrel.events.BedwarsGameOverEvent;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.Team;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.HashMap;

import static github.tsffish.bedwarskit.util.RelCurrentStat.sdps;

public class RelGameOver implements Listener {

    @EventHandler
    public void on(BedwarsGameOverEvent event) {

        for (Player p : event.getGame().getPlayers()){
            sdps(p);
        }

        Game game = event.getGame();
        HashMap<String, Team> teams = game.getTeams();

        String mapName = game.getRegion().getName();
        for (Team team : teams.values()) {

            String teamName = team.getName();
            String[] values = RelTeamEnchant.teamEnchantListSword.get(mapName);
            if (values == null) {
                values = new String[2];
            }
            values[0] = teamName;
            values[1] = "0";
            RelTeamEnchant.teamEnchantListSword.put(mapName, values);

            String[] values1 = RelTeamEnchant.teamEnchantListProt.get(mapName);
            if (values1 == null) {
                values1 = new String[2];
            }
            values1[0] = teamName;
            values1[1] = "0";
            RelTeamEnchant.teamEnchantListProt.put(mapName, values1);

        }

    }
}
