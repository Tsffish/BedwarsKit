package github.tsffish.bedwarskit.listener;

import github.tsffish.bedwarskit.util.player.SendActionBar;
import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.game.*;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.Objects;

import static github.tsffish.bedwarskit.config.main.MainConfigHandler.*;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;
import static github.tsffish.bedwarskit.util.player.PlayerSender.sendMessage;
import static github.tsffish.bedwarskit.util.player.PlayerSender.sendTitle;
import static github.tsffish.bedwarskit.util.player.SendActionBar.sendActionBar;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class RelPlaceBlock implements Listener {
    private static final GameState running = GameState.RUNNING;

    @EventHandler(priority = EventPriority.HIGHEST)
    public void on(final BlockPlaceEvent event) {

        if (event.getPlayer() == null) {
            return;
        }
        Player player = event.getPlayer();
        World world = player.getWorld();
        String worldName = world.getName();
        boolean isRushWorld = worldName.contains(gameWorld);

        if (event.getBlock() == null) {
            return;
        }

        if (BedwarsRel.getInstance() == null) {
            return;
        }

        BedwarsRel bedwarsRel = BedwarsRel.getInstance();

        if (bedwarsRel.getGameManager() == null) {
            return;
        }
        GameManager gameManager = bedwarsRel.getGameManager();

        if (placeCorrect_notInGame) {
            for (Game game : gameManager.getGames()) {
                String gameWorldName = game.getRegion().getWorld().getName();

                if (gameWorldName.equals(worldName)
                        && game.getState() != running) {

                    if (placeCorrect_notInGame_OpBypass) {
                        if (!player.isOp()) {
                            event.setCancelled(true);
                        }
                    }
                    return;
                }
            }
        }

        if (isRushWorld) {

            Game game = gameManager.getGameOfPlayer(player);
            if (game != null && game.getState() == running) {

                Location blockLocation = event.getBlock().getLocation();
                if (placeCorrect_ResSpawner) {

                    if (game.getState() == running) {
                        for (ResourceSpawner rs :
                                game.getResourceSpawners()) {
                            Location spawnerLocation = rs.getLocation();
                            double distance = spawnerLocation.distance(blockLocation);

                            if (distance <= placeCorrect_ResSpawner_dis) {
                                event.setCancelled(true);
                                if (!Objects.equals(placeCorrect_ResSpawner_mess_chat, "")) {
                                    sendMessage(player, placeCorrect_ResSpawner_mess_chat);
                                }
                                if (!Objects.equals(placeCorrect_ResSpawner_mess_title, "")) {
                                    String titleReal = placeCorrect_ResSpawner_mess_title;
                                    if (!Objects.equals(placeCorrect_ResSpawner_mess_subtitle, "")) {
                                        String subtitleReal = placeCorrect_ResSpawner_mess_subtitle;

                                        sendTitle(player, titleReal, subtitleReal);
                                    }
                                } else if (!Objects.equals(placeCorrect_ResSpawner_mess_subtitle, "")) {
                                    String titleReal = " ";
                                    String subtitleReal = placeCorrect_ResSpawner_mess_subtitle;

                                    sendTitle(player, titleReal, subtitleReal);
                                }
                                if (!Objects.equals(placeCorrect_ResSpawner_mess_actionbar, "")) {
                                    SendActionBar.sendActionBar(player, placeCorrect_ResSpawner_mess_actionbar);
                                }
                                return;
                            }
                        }
                    }
                }

                if (placeCorrect_PlayerSpawnLoc) {
                    if (game.getState() == running) {
                        for (Team team : game.getPlayingTeams()) {
                            Location teamspawnLocation = team.getSpawnLocation();
                            double distance = teamspawnLocation.distance(blockLocation);

                            if (distance <= placeCorrect_PlayerSpawnLoc_dis) {
                                event.setCancelled(true);
                                if (!placeCorrect_PlayerSpawnLoc_mess_chat.isEmpty()) {
                                    sendMessage(player, placeCorrect_PlayerSpawnLoc_mess_chat);
                                }
                                if (!placeCorrect_PlayerSpawnLoc_mess_title.isEmpty()) {
                                    String titleReal = placeCorrect_PlayerSpawnLoc_mess_title;
                                    if (!placeCorrect_PlayerSpawnLoc_mess_subtitle.isEmpty()) {
                                        String subtitleReal = t(placeCorrect_PlayerSpawnLoc_mess_subtitle);

                                        sendTitle(player, titleReal, subtitleReal);
                                    }
                                } else if (!placeCorrect_PlayerSpawnLoc_mess_subtitle.isEmpty()) {
                                    String titleReal = " ";
                                    String subtitleReal = t(placeCorrect_PlayerSpawnLoc_mess_subtitle);

                                    sendTitle(player, titleReal, subtitleReal);
                                }
                                if (!placeCorrect_PlayerSpawnLoc_mess_actionbar.isEmpty()) {
                                    sendActionBar(player, placeCorrect_PlayerSpawnLoc_mess_actionbar);
                                }
                                return;
                            }
                        }
                    }
                }

            }
        }
    }
}