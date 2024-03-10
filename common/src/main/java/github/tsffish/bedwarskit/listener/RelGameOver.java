package github.tsffish.bedwarskit.listener;

import github.tsffish.bedwarskit.BedwarsKit;
import github.tsffish.bedwarskit.util.player.SoundPlayer;
import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.events.BedwarsGameEndEvent;
import io.github.bedwarsrel.events.BedwarsGameOverEvent;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameManager;
import io.github.bedwarsrel.game.Team;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static github.tsffish.bedwarskit.config.main.MainConfigHandler.lobbyWorld;
import static github.tsffish.bedwarskit.util.misc.MessSender.l;
import static github.tsffish.bedwarskit.util.misc.PluginState.isDebug;
import static github.tsffish.bedwarskit.util.player.RelCurrentStat.setDefaultPlayerStat;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class RelGameOver implements Listener {
    private static final BedwarsKit plugin = BedwarsKit.getInstance();
    private static final Material bed_block = Material.BED_BLOCK;
    private static final double maxBord = 9999;

    @EventHandler
    public void on(final BedwarsGameOverEvent event) {
        if (event.getGame().getPlayers() != null) {
            if (!event.getGame().getPlayers().isEmpty()) {
                for (Player player : event.getGame().getPlayers()) {
                    if (player != null) {

                        UUID uuid = player.getUniqueId();
                        setDefaultPlayerStat(uuid);
                    }
                }
            }
        }
        Game game = event.getGame();

        if (event.getWinner() != null) {
            if (event.getWinner().getPlayers() != null
                    && !event.getWinner().getPlayers().isEmpty()) {
                for (Player player : event.getWinner().getPlayers()) {
                    SoundPlayer.LEVEL_UP(player, 1);
                }
            }
        }
        World world = game.getRegion().getWorld();
        WorldBorder worldBorder = world.getWorldBorder();
        worldBorder.setCenter(game.getLobby());
        worldBorder.setSize(maxBord);


    }

    @EventHandler
    public void on(final BedwarsGameEndEvent event) {
        Game game = event.getGame();
        World world = game.getRegion().getWorld();
        for (Team team : game.getTeams().values()) {
            if (team.getHeadTarget().getType() != bed_block) {
                world.getBlockAt(team.getTargetHeadBlock()).setType(bed_block, true);
            }
            if (team.getFeetTarget().getType() != bed_block) {
                world.getBlockAt(team.getTargetFeetBlock()).setType(bed_block, true);
            }
        }

        new BukkitRunnable() {
            public void run() {
                if (game.getRegion().getWorld().getPlayers().size() != game.getPlayers().size()) {

                    if (isDebug()) {
                        l("game " + game.getName() + " not match player count, trying to synchronize");
                    }

                    if (Bukkit.getWorld(lobbyWorld) != null) {

                        List<Player> playerList = new ArrayList<>(game.getMaxPlayers() + 1);
                        for (Player player : game.getRegion().getWorld().getPlayers()) {
                            if (player != null && player.isOnline()) {
                                playerList.add(player);
                            }
                        }

                        World lobby = Bukkit.getWorld(lobbyWorld);
                        GameManager gameManager = BedwarsRel.getInstance().getGameManager();
                        for (Player player : playerList) {
                            Location current = player.getLocation();
                            Location newLoc = current.setDirection(player.getLocation().getDirection());

                            if (gameManager.getGameOfPlayer(player) != null) {
                                game.playerLeave(player, false);
                                game.playerJoins(player);
                                player.teleport(newLoc);
                            } else {
                                if (isDebug()) {
                                    l(player.getName() + " joined world with no game,tp to lobby.");
                                }
                                player.teleport(lobby.getSpawnLocation());
                            }
                        }

                    }
                }
            }
        }.runTaskLater(plugin, 10L);

    }


}
