package github.tsffish.bedwarskit.listener;

import github.tsffish.bedwarskit.config.main.MainConfigHandler;
import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.game.*;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.Objects;

import static github.tsffish.bedwarskit.config.main.MainConfigHandler.*;
import static github.tsffish.bedwarskit.util.misc.PlayerSender.sendTitle;
import static github.tsffish.bedwarskit.util.misc.SendActionBar.sendActionBar;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;
/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class RelPlaceCorrect implements Listener {
    @EventHandler
    public void on(final BlockPlaceEvent e) {

        Player player = e.getPlayer();
        boolean isRushWorld = player.getWorld().getName().contains(rushWorld);
        GameManager gameManager = BedwarsRel.getInstance().getGameManager();

        if (placeCorrect_notInGame) {
            if (placeCorrect_notInGame_OpBypass) {
                if (!player.isOp()) {
                    String worldName = player.getWorld().getName();
                    for (Game list : gameManager.getGames()) {
                        if (list.getRegion().getWorld().getName().equals(worldName)
                                && list.getState() != GameState.RUNNING) {
                            e.setCancelled(true);
                            break;
                        }
                    }
                }
            }
        }
        if(isRushWorld) {

            Game game = gameManager.getGameOfPlayer(player);
            if (game != null && game.getState() == GameState.RUNNING) {

                Player p = e.getPlayer();
                Location blockLocation = e.getBlock().getLocation();
                if (placeCorrect_ResSpawner) {

                    if (gameManager.getGameOfPlayer(player).getState() == GameState.RUNNING) {
                        for (ResourceSpawner rs :
                                gameManager.getGameOfPlayer(player).getResourceSpawners()) {
                            Location spawnerLocation = rs.getLocation();
                            double distance = spawnerLocation.distance(blockLocation);

                            if (distance <= placeCorrect_ResSpawner_dis) {
                                e.setCancelled(true);

                                if (!Objects.equals(MainConfigHandler.placeCorrect_ResSpawner_mess_chat, "")) {
                                    p.sendMessage(t(MainConfigHandler.placeCorrect_ResSpawner_mess_chat));
                                }
                                if (!Objects.equals(MainConfigHandler.placeCorrect_ResSpawner_mess_title, "")) {
                                    String titleReal = t(MainConfigHandler.placeCorrect_ResSpawner_mess_title);
                                    if (!Objects.equals(MainConfigHandler.placeCorrect_ResSpawner_mess_subtitle, "")) {
                                        String subtitleReal = t(MainConfigHandler.placeCorrect_ResSpawner_mess_subtitle);

                                        sendTitle(p,titleReal, subtitleReal);
                                    }
                                } else if (!Objects.equals(MainConfigHandler.placeCorrect_ResSpawner_mess_subtitle, "")) {
                                    String titleReal = " ";
                                    String subtitleReal = t(MainConfigHandler.placeCorrect_ResSpawner_mess_subtitle);

                                    sendTitle(p,titleReal, subtitleReal);
                                }
                                if (!Objects.equals(MainConfigHandler.placeCorrect_ResSpawner_mess_actionbar, "")) {
                                    sendActionBar(p, t(MainConfigHandler.placeCorrect_ResSpawner_mess_actionbar));
                                }


                                return;
                            }
                        }
                    }
                }

                if (placeCorrect_PlayerSpawnLoc) {
                    if (gameManager.getGameOfPlayer(player).getState() == GameState.RUNNING) {
                        for (Team team : gameManager.getGameOfPlayer(player).getPlayingTeams()) {
                            Location teamspawnLocation = team.getSpawnLocation();
                            double distance = teamspawnLocation.distance(blockLocation);

                            if (distance <= placeCorrect_PlayerSpawnLoc_dis) {
                                e.setCancelled(true);

                                if (!Objects.equals(MainConfigHandler.placeCorrect_PlayerSpawnLoc_mess_chat, "")) {
                                    p.sendMessage(t(MainConfigHandler.placeCorrect_PlayerSpawnLoc_mess_chat));
                                }
                                if (!Objects.equals(MainConfigHandler.placeCorrect_PlayerSpawnLoc_mess_title, "")) {
                                    String titleReal = t(MainConfigHandler.placeCorrect_PlayerSpawnLoc_mess_title);
                                    if (!Objects.equals(MainConfigHandler.placeCorrect_PlayerSpawnLoc_mess_subtitle, "")) {
                                        String subtitleReal = t(MainConfigHandler.placeCorrect_PlayerSpawnLoc_mess_subtitle);

                                        sendTitle(p,titleReal, subtitleReal);
                                    }
                                } else if (!Objects.equals(MainConfigHandler.placeCorrect_PlayerSpawnLoc_mess_subtitle, "")) {
                                    String titleReal = " ";
                                    String subtitleReal = t(MainConfigHandler.placeCorrect_PlayerSpawnLoc_mess_subtitle);

                                    sendTitle(p,titleReal, subtitleReal);
                                }
                                if (!Objects.equals(MainConfigHandler.placeCorrect_PlayerSpawnLoc_mess_actionbar, "")) {
                                    sendActionBar(p, t(MainConfigHandler.placeCorrect_PlayerSpawnLoc_mess_actionbar));
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