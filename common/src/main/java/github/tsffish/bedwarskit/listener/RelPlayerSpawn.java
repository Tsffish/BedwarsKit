package github.tsffish.bedwarskit.listener;

import github.tsffish.bedwarskit.BedwarsKit;
import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameManager;
import io.github.bedwarsrel.game.Team;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

import static github.tsffish.bedwarskit.config.main.MainConfigHandler.deathGameMode_tpto;
/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class RelPlayerSpawn implements Listener {
    private static final BedwarsKit plugin = BedwarsKit.getInstance();
    @EventHandler
    public void on(PlayerRespawnEvent event){

        Player player = event.getPlayer();

        if (player == null || !player.isOnline()) {
            return;
        }

        GameManager gm = BedwarsRel.getInstance().getGameManager();
        if (gm == null) {
            return;
        }

        if (gm.getGameOfPlayer(player) == null){
            return;
        }
        Game game = gm.getGameOfPlayer(player);
        if (game.getPlayerTeam(player) == null){
            return;
        }

            new BukkitRunnable() {
                @Override
                public void run() {

                    if (game.isSpectator(player)){
                    Team team = game.getPlayerTeam(player);
                    Location spawnLocation = team.getSpawnLocation();
                    Location lobbyLoc = game.getLobby();

                    player.setFallDistance(0);
                    if (!deathGameMode_tpto.equalsIgnoreCase("none")){
                        if (deathGameMode_tpto.equalsIgnoreCase("team")){
                            player.teleport(spawnLocation);
                        }  else if (deathGameMode_tpto.equalsIgnoreCase("lobby")){
                            player.teleport(lobbyLoc);
                        }
                    }
                }

                }
            }.runTaskLater(plugin, 40L);
    }
}
