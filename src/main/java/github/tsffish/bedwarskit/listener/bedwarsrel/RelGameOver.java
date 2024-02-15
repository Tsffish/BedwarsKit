package github.tsffish.bedwarskit.listener.bedwarsrel;

import github.tsffish.bedwarskit.util.RelCurrentStat;
import io.github.bedwarsrel.events.BedwarsGameOverEvent;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.Team;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Map;

import static github.tsffish.bedwarskit.util.teamshop.RelTeamEffect.setDefaultTeamEff;
import static github.tsffish.bedwarskit.util.teamshop.RelTeamEnchant.setDefaultTeamEnch;

public class RelGameOver implements Listener {



    @EventHandler
    public void on(BedwarsGameOverEvent event) {

        for (Player player : event.getGame().getPlayers()){
            if (player != null){

                String playerName = player.getName();
                RelCurrentStat.setDefaultPlayerStat(playerName);

            }
        }


        Game game = event.getGame();

        World world = game.getRegion().getWorld();
        WorldBorder worldBorder = world.getWorldBorder();
        worldBorder.setCenter(game.getLobby());
        worldBorder.setSize(9999);

        Map<String, Team> teams = game.getTeams();

        String mapName = game.getRegion().getName();
        for (Team team : teams.values()) {

            String teamName = team.getName();
            setDefaultTeamEnch(mapName,teamName);
            setDefaultTeamEff(mapName,teamName);
        }

    }
}
