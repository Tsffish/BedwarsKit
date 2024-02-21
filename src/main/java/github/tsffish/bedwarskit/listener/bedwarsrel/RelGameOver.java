package github.tsffish.bedwarskit.listener.bedwarsrel;

import github.tsffish.bedwarskit.util.RelCurrentStat;
import io.github.bedwarsrel.events.BedwarsGameEndEvent;
import io.github.bedwarsrel.events.BedwarsGameOverEvent;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.Team;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class RelGameOver implements Listener {

    @EventHandler
    public void on(final BedwarsGameOverEvent event) {

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


    }
    @EventHandler
    public void on(final BedwarsGameEndEvent event){
        Game game = event.getGame();
        World world = game.getRegion().getWorld();
        for (Team team : game.getPlayingTeams()){
            if (team.getHeadTarget().getType() != Material.BED){
                world.getBlockAt(team.getTargetHeadBlock()).setType(Material.BED, true);
            }
            if (team.getFeetTarget().getType() != Material.BED){
                world.getBlockAt(team.getTargetFeetBlock()).setType(Material.BED, true);
            }
        }
    }

}
