package github.tsffish.bedwarskit.listener.bedwarsrel;

import github.tsffish.bedwarskit.config.MainConfigHandler;
import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.game.*;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.Objects;

import static github.tsffish.bedwarskit.config.MainConfigHandler.rushWorld;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;
import static io.github.bedwarsrel.com.v1_8_r3.ActionBar.sendActionBar;

public class RelPlaceCorrect implements Listener {
    @EventHandler
    public void on(BlockPlaceEvent e) {

        Player player = e.getPlayer();
        boolean isRushWorld = player.getWorld().getName().contains(rushWorld);
        Game game = null;
        if(isRushWorld) {
            GameManager gm = BedwarsRel.getInstance().getGameManager();
            if (gm != null && gm.getGameOfPlayer(player) != null)
               game = gm.getGameOfPlayer(player);
            if (game != null && game.getState() != GameState.RUNNING) {
                if (MainConfigHandler.placeCorrect_notInGame_OpBypass) {
                    e.setCancelled(!player.isOp());
                }
            }

            if (game != null && game.getState() == GameState.RUNNING) {

                Player p = e.getPlayer();
                if (MainConfigHandler.placeCorrect_ResSpawner) {
                    Location blockLocation = e.getBlock().getLocation();

                    if (gm.getGameOfPlayer(player).getState() == GameState.RUNNING) {
                        for (ResourceSpawner rs : gm.getGameOfPlayer(player).getResourceSpawners()) {
                            Location spawnerLocation = rs.getLocation();
                            double distance = spawnerLocation.distance(blockLocation);

                            if (distance <= 3) {
                                e.setCancelled(true);

                                if (!Objects.equals(MainConfigHandler.placeCorrect_ResSpawner_mess_chat, "")) {
                                    p.sendMessage(t(MainConfigHandler.placeCorrect_ResSpawner_mess_chat));
                                }
                                if (!Objects.equals(MainConfigHandler.placeCorrect_ResSpawner_mess_title, "")) {
                                    String titleReal = t(MainConfigHandler.placeCorrect_ResSpawner_mess_title);
                                    if (!Objects.equals(MainConfigHandler.placeCorrect_ResSpawner_mess_subtitle, "")) {
                                        String subtitleReal = t(MainConfigHandler.placeCorrect_ResSpawner_mess_subtitle);

                                        p.sendTitle(titleReal, subtitleReal);
                                    }
                                } else if (!Objects.equals(MainConfigHandler.placeCorrect_ResSpawner_mess_subtitle, "")) {
                                    String titleReal = " ";
                                    String subtitleReal = t(MainConfigHandler.placeCorrect_ResSpawner_mess_subtitle);

                                    p.sendTitle(titleReal, subtitleReal);
                                }
                                if (!Objects.equals(MainConfigHandler.placeCorrect_ResSpawner_mess_actionbar, "")) {
                                    sendActionBar(p, t(MainConfigHandler.placeCorrect_ResSpawner_mess_actionbar));
                                }


                                return;
                            }
                        }
                    }
                }


                Location blockLocation = e.getBlock().getLocation();
                if (MainConfigHandler.placeCorrect_PlayerSpawnLoc) {
                    if (gm.getGameOfPlayer(player).getState() == GameState.RUNNING) {
                        for (Team team : gm.getGameOfPlayer(player).getPlayingTeams()) {
                            Location teamspawnLocation = team.getSpawnLocation();
                            double distance = teamspawnLocation.distance(blockLocation);

                            if (distance <= 3) {
                                e.setCancelled(true);

                                if (!Objects.equals(MainConfigHandler.placeCorrect_PlayerSpawnLoc_mess_chat, "")) {
                                    p.sendMessage(t(MainConfigHandler.placeCorrect_PlayerSpawnLoc_mess_chat));
                                }
                                if (!Objects.equals(MainConfigHandler.placeCorrect_PlayerSpawnLoc_mess_title, "")) {
                                    String titleReal = t(MainConfigHandler.placeCorrect_PlayerSpawnLoc_mess_title);
                                    if (!Objects.equals(MainConfigHandler.placeCorrect_PlayerSpawnLoc_mess_subtitle, "")) {
                                        String subtitleReal = t(MainConfigHandler.placeCorrect_PlayerSpawnLoc_mess_subtitle);

                                        p.sendTitle(titleReal, subtitleReal);
                                    }
                                } else if (!Objects.equals(MainConfigHandler.placeCorrect_PlayerSpawnLoc_mess_subtitle, "")) {
                                    String titleReal = " ";
                                    String subtitleReal = t(MainConfigHandler.placeCorrect_PlayerSpawnLoc_mess_subtitle);

                                    p.sendTitle(titleReal, subtitleReal);
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