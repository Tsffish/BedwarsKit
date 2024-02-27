package github.tsffish.bedwarskit.listener;

import github.tsffish.bedwarskit.Main;
import github.tsffish.bedwarskit.util.RelCurrentStat;
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

import static github.tsffish.bedwarskit.Main.isDebug;
import static github.tsffish.bedwarskit.config.main.MainConfigHandler.lobbyWorld;
import static github.tsffish.bedwarskit.util.RelCurrentStat.setDefaultPlayerStat;
import static github.tsffish.bedwarskit.util.misc.MessSender.l;

public class RelGameOver implements Listener {
    private static final Main plugin = Main.getInstance();
    private static final Material bed = Material.BED;
    private static final double maxBord = 9999;
    @EventHandler
    public void on(final BedwarsGameOverEvent event) {
        for (Player player : event.getGame().getPlayers()){
            if (player != null){

                UUID uuid = player.getUniqueId();
                setDefaultPlayerStat(uuid);
            }
        }


        for (Player player : event.getWinner().getPlayers()){

            SoundPlayer.LEVEL_UP(player, 1);
            //player.sendMessage("&b&l==============================================");
            //player.sendMessage("&6&l胜者: &f{team}");
            //player.sendMessage("&f");
            //player.sendMessage("&6击杀第一名: ");
            //player.sendMessage("&e击杀第二名: ");
            //player.sendMessage("&a击杀第三名: ");
            //player.sendMessage("&f");
            //player.sendMessage("&b&l==============================================");
        }


        Game game = event.getGame();

        World world = game.getRegion().getWorld();
        WorldBorder worldBorder = world.getWorldBorder();
        worldBorder.setCenter(game.getLobby());
        worldBorder.setSize(maxBord);


    }
    @EventHandler
    public void on(final BedwarsGameEndEvent event){
        Game game = event.getGame();
        World world = game.getRegion().getWorld();
        for (Team team : game.getPlayingTeams()){
            if (team.getHeadTarget().getType() != bed){
                world.getBlockAt(team.getTargetHeadBlock()).setType(bed, true);
            }
            if (team.getFeetTarget().getType() != bed){
                world.getBlockAt(team.getTargetFeetBlock()).setType(bed, true);
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