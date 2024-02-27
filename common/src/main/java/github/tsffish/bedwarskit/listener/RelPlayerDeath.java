package github.tsffish.bedwarskit.listener;

import github.tsffish.bedwarskit.Main;
import github.tsffish.bedwarskit.config.main.MainConfigHandler;
import github.tsffish.bedwarskit.util.RelPlayerIsRespawn;
import github.tsffish.bedwarskit.util.RelPlayerRespawn;
import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameManager;
import io.github.bedwarsrel.game.GameState;
import io.github.bedwarsrel.game.Team;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Objects;
import java.util.UUID;

import static github.tsffish.bedwarskit.Main.isDebug;
import static github.tsffish.bedwarskit.config.main.MainConfigHandler.*;
import static github.tsffish.bedwarskit.util.RelCurrentStat.*;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;
import static github.tsffish.bedwarskit.util.misc.MessSender.l;
import static github.tsffish.bedwarskit.util.misc.SendActionBar.sendActionBar;

public class RelPlayerDeath extends RelPlayerRespawn implements Listener {
    private static final Main plugin = Main.getInstance();
    private static final Material air = Material.AIR;
    private static final GameState waiting = GameState.WAITING;
    @EventHandler
    public void on(final PlayerDeathEvent event) {
        Player player = event.getEntity().getPlayer();
        if (player == null || !player.isOnline()) return;
        String playerName = player.getName();
        UUID uuid = player.getUniqueId();

        GameManager gm = BedwarsRel.getInstance().getGameManager();
        if (gm == null) return;

        Game game = gm.getGameOfPlayer(player);

        if (game == null) return;

        if (game.getPlayerTeam(player).getHeadTarget() == null
                || game.getPlayerTeam(player).getHeadTarget().getType() == air) {
            addPlayerIsOut(uuid);
            if (isDebug()) {
                l("add " + playerName + " is out");
            }
            return;
        }


        if (!PlayerisOut(uuid)) {

            if (deathGameMode) {
                if (preventloadworld) {
                    player.setHealth(player.getMaxHealth());
                }
                if (!RelPlayerIsRespawn.getPlayerRespawn(uuid)) {
                    RelPlayerIsRespawn.addPlayerRespawn(uuid);

                    deathplayer(uuid, 1L);

                }
            }
        }
    }
    public static void deathplayer(UUID uuid,long dealy)
    {
        Player player = Bukkit.getPlayer(uuid);

        GameManager gm = BedwarsRel.getInstance().getGameManager();
        Game game = gm.getGameOfPlayer(player);
        Team team = game.getPlayerTeam(player);
        Location spawnLocation = team.getSpawnLocation();
        Location lobbyLoc = game.getLobby();

        if (!deathGameMode_tpto.equalsIgnoreCase("none")){
         if (deathGameMode_tpto.equalsIgnoreCase("team")){
             player.teleport(spawnLocation);
         }  else if (deathGameMode_tpto.equalsIgnoreCase("lobby")){
             player.teleport(lobbyLoc);
         }
        }


        PlayerInventory pi = player.getInventory();

        for (ItemStack list : pi.getContents()){
            if (list != null && list.getType() != killfb_oneHealthKill_itemType
            ){
                pi.remove(list);
            }
        }

        pi.clear(36);
        pi.clear(37);
        pi.clear(38);
        pi.clear(39);
        player.getActivePotionEffects().clear();

        updatePlayerStat(uuid,"setohk",0);

        updatePlayerStat(uuid,"d", 1);

        new BukkitRunnable() {
            int x;
            {x = respawnDelay + 1;}

            public void run() {
                String i;
                if (x != 0) {
                    --x;
                    i = Integer.toString(x);

                    if (!MainConfigHandler.respawnChat.isEmpty()) {
                        player.sendMessage(MainConfigHandler.respawnChat.replace("{timeleft}",i));
                    }
                    if (!Objects.equals(MainConfigHandler.respawnTitle, "")) {
                        String titleReal = t(MainConfigHandler.respawnTitle.replace("{timeleft}",i));
                        if (!Objects.equals(MainConfigHandler.respawnSubTitle, "")) {
                            String subtitleReal = t(MainConfigHandler.respawnSubTitle.replace("{timeleft}",i));

                            player.sendTitle(titleReal, subtitleReal);
                        }
                    } else if (!MainConfigHandler.respawnSubTitle.isEmpty()) {
                        String titleReal = " ";
                        String subtitleReal = t(MainConfigHandler.respawnSubTitle.replace("{timeleft}",i));

                        player.sendTitle(titleReal, subtitleReal);
                    }
                    if (!MainConfigHandler.respawnActionBar.isEmpty()) {
                        sendActionBar(player, t(MainConfigHandler.respawnActionBar.replace("{timeleft}",i)));
                    }
                    player.setGameMode(GameMode.SPECTATOR);
                }

                if (x == 0) {
                    i = Integer.toString(x);

                    if (!MainConfigHandler.respawnSuccChat.isEmpty()) {
                        player.sendMessage(MainConfigHandler.respawnSuccChat.replace("{timeleft}",i));
                    }
                    if (!MainConfigHandler.respawnSuccTitle.isEmpty()) {
                        String titleReal = t(MainConfigHandler.respawnSuccTitle.replace("{timeleft}",i));
                        if (!MainConfigHandler.respawnSuccSubTitle.isEmpty()) {
                            String subtitleReal = t(MainConfigHandler.respawnSuccSubTitle.replace("{timeleft}",i));

                            player.sendTitle(titleReal, subtitleReal);
                        }
                    } else if (!MainConfigHandler.respawnSuccSubTitle.isEmpty()) {
                        String titleReal = " ";
                        String subtitleReal = t(MainConfigHandler.respawnSuccSubTitle.replace("{timeleft}",i));

                        player.sendTitle(titleReal, subtitleReal);
                    }
                    if (!respawnSuccActionBar.isEmpty()) {
                        sendActionBar(player, t(MainConfigHandler.respawnSuccActionBar.replace("{timeleft}",i)));
                    }

                    if (!PlayerisOut(uuid)) {

                        playerrespawn(uuid, 1L);
                        Vector playerDirection = player.getLocation().getDirection();
                        Location spawnLocationNew = spawnLocation.setDirection(playerDirection);
                        player.teleport(spawnLocationNew);
                    }
                    cancel();
                }

                if (player.isOnline()) {
                    if (game.getTimeLeft() == game.getLength()
                            || game.getState() == waiting) {
                        cancel();
                    }
                }else {
                    cancel();
                }
            }
        }.runTaskTimer(plugin, dealy, 20L);
    }

}
