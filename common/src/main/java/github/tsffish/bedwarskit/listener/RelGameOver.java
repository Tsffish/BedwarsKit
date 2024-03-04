package github.tsffish.bedwarskit.listener;

import github.tsffish.bedwarskit.BedwarsKit;
import github.tsffish.bedwarskit.util.misc.SoundPlayer;
import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.events.BedwarsGameEndEvent;
import io.github.bedwarsrel.events.BedwarsGameOverEvent;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameManager;
import io.github.bedwarsrel.game.Team;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static github.tsffish.bedwarskit.config.main.MainConfigHandler.lobbyWorld;
import static github.tsffish.bedwarskit.util.RelCurrentStat.setDefaultPlayerStat;
import static github.tsffish.bedwarskit.util.misc.GetBlockType.BED_BLOCK;
import static github.tsffish.bedwarskit.util.misc.MessSender.l;
import static github.tsffish.bedwarskit.util.misc.PluginState.isDebug;
/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class RelGameOver implements Listener {
    private static final BedwarsKit plugin = BedwarsKit.getInstance();
    private static final Material bed_block = BED_BLOCK();
    private static final double maxBord = 9999;
    @EventHandler
    public void on(final BedwarsGameOverEvent event) {
        for (Player player : event.getGame().getPlayers()){
            if (player != null){

                UUID uuid = player.getUniqueId();
                setDefaultPlayerStat(uuid);
            }
        }

        Game game = event.getGame();

        for (Player player : event.getWinner().getPlayers()){
            SoundPlayer.LEVEL_UP(player, 1);
        }

        World world = game.getRegion().getWorld();
        WorldBorder worldBorder = world.getWorldBorder();
        worldBorder.setCenter(game.getLobby());
        worldBorder.setSize(maxBord);


    }
    @EventHandler
    public void on(final BedwarsGameEndEvent event){
        Game game = event.getGame();
        World world = game.getRegion().getWorld();
        for (Team team : game.getTeams().values()){
            if (team.getHeadTarget().getType() != bed_block){
                world.getBlockAt(team.getTargetHeadBlock()).setType(bed_block, true);
            }
            if (team.getFeetTarget().getType() != bed_block){
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
                        for (Player list : game.getRegion().getWorld().getPlayers()) {
                            if (list != null && list.isOnline()) {
                                playerList.add(list);
                            }
                        }

                        World lobby = Bukkit.getWorld(lobbyWorld);
                        GameManager gameManager = BedwarsRel.getInstance().getGameManager();
                        for (Player list : playerList) {
                            if (gameManager.getGameOfPlayer(list) != null) {
                                game.playerLeave(list, false);
                                game.playerJoins(list);
                            } else {
                                if (isDebug()) {
                                    l(list.getName() + " joined world with no game,kicked.");
                                }
                                list.teleport(lobby.getSpawnLocation());
                            }
                        }

                    }
                }
            }
        }.runTaskLater(plugin,10L);

    }












}
