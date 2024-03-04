package github.tsffish.bedwarskit.listener;


import github.tsffish.bedwarskit.BedwarsKit;
import github.tsffish.bedwarskit.config.main.MainConfigHandler;
import github.tsffish.bedwarskit.util.RelPlayerIsRespawn;
import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameManager;
import io.github.bedwarsrel.game.GameState;
import io.github.bedwarsrel.game.Team;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Objects;
import java.util.UUID;

import static github.tsffish.bedwarskit.config.main.MainConfigHandler.*;
import static github.tsffish.bedwarskit.listener.procol.PlayerVisibility.hidePlayer;
import static github.tsffish.bedwarskit.listener.procol.PlayerVisibility.showPlayer;
import static github.tsffish.bedwarskit.util.RelCurrentStat.*;
import static github.tsffish.bedwarskit.util.RelPlayerRespawn.playerrespawn;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;
import static github.tsffish.bedwarskit.util.misc.GetBlockType.BED_BLOCK;
import static github.tsffish.bedwarskit.util.misc.PlayerSender.sendTitle;
import static github.tsffish.bedwarskit.util.misc.SendActionBar.sendActionBar;
/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class RelPlayerDeath implements Listener{
    private static final Material bed_block = BED_BLOCK();
    private static final BedwarsKit plugin = BedwarsKit.getInstance();
    private static final GameState waiting = GameState.WAITING;
    private static final PotionEffect invs = new PotionEffect(PotionEffectType.INVISIBILITY,9999,0);
    @EventHandler
    public void on(final PlayerDeathEvent event) {

        Player player = event.getEntity().getPlayer();

        if (player == null || !player.isOnline()) {
            return;
        }
        UUID playerUUID = player.getUniqueId();

        GameManager gameManager = BedwarsRel.getInstance().getGameManager();
        if (gameManager == null) {
            return;
        }

        if (gameManager.getGameOfPlayer(player) == null){
            return;
        }
        Game game = gameManager.getGameOfPlayer(player);
        if (game.getPlayerTeam(player) == null){
            return;
        }

        // pd if can repsawn
        if (game.getPlayerTeam(player).getHeadTarget().getType() != bed_block) {
        // cant start respawn

            hidePlayer(player);

            player.addPotionEffect(invs);
            game.addProtection(player);

            player.setAllowFlight(true);
            player.setFlying(true);


            PlayerInventory pi = player.getInventory();
            pi.clear();

            pi.clear(36);
            pi.clear(37);
            pi.clear(38);
            pi.clear(39);

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

            addPlayerIsOut(playerUUID);

        }else{
            // pd can repsawn
            game.addProtection(player);
            player.addPotionEffect(invs);
            player.setAllowFlight(true);
            player.setFlying(true);
            deathplayer(playerUUID, 1L);}
    }
    public static void deathplayer(UUID uuid,long dealy) {
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

        if (deathGameMode) {
            if (!RelPlayerIsRespawn.getPlayerRespawn(uuid)) {
                RelPlayerIsRespawn.addPlayerRespawn(uuid);
            }
            if (preventloadworld){player.setHealth(player.getMaxHealth() - 1);}}

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

        RelPlayerIsRespawn.addPlayerRespawn(uuid);

        hidePlayer(player);
        new BukkitRunnable() {
            int x;
            {x = respawnDelay + 1;}

            public void run() {
                String i;
                if (x != 0) {
                    --x;
                    i = Integer.toString(x);

                    if (!respawnChat.isEmpty()) {
                        player.sendMessage(MainConfigHandler.respawnChat.replace("{timeleft}",i));
                    }
                    if (!respawnTitle.isEmpty()) {
                        String titleReal = t(respawnTitle.replace("{timeleft}",i));
                        if (!Objects.equals(respawnSubTitle, "")) {
                            String subtitleReal = t(respawnSubTitle.replace("{timeleft}",i));

                            sendTitle(player,titleReal, subtitleReal);
                        }
                    } else if (!respawnSubTitle.isEmpty()) {
                        String titleReal = " ";
                        String subtitleReal = t(respawnSubTitle.replace("{timeleft}",i));

                        sendTitle(player,titleReal, subtitleReal);
                    }
                    if (!respawnActionBar.isEmpty()) {
                        sendActionBar(player, t(respawnActionBar.replace("{timeleft}",i)));
                    }
                    if (!game.getRespawnProtections().containsKey(player)){
                        game.addProtection(player);
                    }

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

                            sendTitle(player,titleReal, subtitleReal);
                        }
                    } else if (!MainConfigHandler.respawnSuccSubTitle.isEmpty()) {
                        String titleReal = " ";
                        String subtitleReal = t(MainConfigHandler.respawnSuccSubTitle.replace("{timeleft}",i));

                        sendTitle(player,titleReal, subtitleReal);
                    }
                    if (!respawnSuccActionBar.isEmpty()) {
                        sendActionBar(player, t(MainConfigHandler.respawnSuccActionBar.replace("{timeleft}",i)));
                    }

                    if (!getPlayerisOut(uuid)) {
                        playerrespawn(uuid, 1L);
                        game.removeProtection(player);
                        Vector playerDirection = player.getLocation().getDirection();
                        Location spawnLocationNew = spawnLocation.setDirection(playerDirection);
                        player.setFallDistance(0);
                        player.teleport(spawnLocationNew);
                        showPlayer(player);
                        RelPlayerIsRespawn.removePlayerRespawn(uuid);
                    }
                    cancel();
                }

                if (player.isOnline()) {
                    if (
                            game.getState() == waiting) {
                        showPlayer(player);
                        cancel();
                    }
                }else {
                    cancel();
                }
            }
        }.runTaskTimer(plugin, dealy, 20L);
    }
}
