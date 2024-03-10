package github.tsffish.bedwarskit.listener;


import github.tsffish.bedwarskit.BedwarsKit;
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
import org.bukkit.event.EventPriority;
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
import static github.tsffish.bedwarskit.util.player.PlayerSender.sendMessage;
import static github.tsffish.bedwarskit.util.player.PlayerSender.sendTitle;
import static github.tsffish.bedwarskit.util.player.RelCurrentStat.addPlayerIsOut;
import static github.tsffish.bedwarskit.util.player.RelCurrentStat.getPlayerisOut;
import static github.tsffish.bedwarskit.util.player.RelPlayerIsRespawn.*;
import static github.tsffish.bedwarskit.util.player.RelPlayerRespawn.playerrespawn;
import static github.tsffish.bedwarskit.util.player.SendActionBar.sendActionBar;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class RelPlayerDeath implements Listener {
    private static final Material bed_block = Material.BED_BLOCK;
    private static final BedwarsKit plugin = BedwarsKit.getInstance();
    private static final GameState waiting = GameState.WAITING;
    private static final PotionEffect invs = new PotionEffect(PotionEffectType.INVISIBILITY, 9999, 0);

    private static String replaceString(
            String text,
            String i) {
        return text.
                replace("{timeleft}", i);
    }

    public static void deathplayer(UUID uuid, long delay) {
        Player player = Bukkit.getPlayer(uuid);

        GameManager gameManager = BedwarsRel.getInstance().getGameManager();
        Game game = gameManager.getGameOfPlayer(player);
        Team team = game.getPlayerTeam(player);
        Location spawnLocation = team.getSpawnLocation();
        Location lobbyLoc = game.getLobby();


        if (!deathGameMode_tpto.equalsIgnoreCase("none")) {
            if (deathGameMode_tpto.equalsIgnoreCase("team")) {
                player.teleport(spawnLocation);
            } else if (deathGameMode_tpto.equalsIgnoreCase("lobby")) {
                player.teleport(lobbyLoc);
            }
        }

        if (deathGameMode) {
            if (!getPlayerRespawn(uuid)) {
                addPlayerRespawn(uuid);
            }
            if (preventLoadWorld) {
                player.setHealth(player.getMaxHealth() - 1);
                player.setLastDamageCause(null);
                game.setPlayerDamager(player, null);
            }
        }

        PlayerInventory pi = player.getInventory();

        for (ItemStack item : pi.getContents()) {
            if (item != null && item.getType() != killfb_oneHealthKill_itemType
            ) {
                pi.remove(item);
            }
        }

        pi.clear(36);
        pi.clear(37);
        pi.clear(38);
        pi.clear(39);

        player.getActivePotionEffects().clear();

        addPlayerRespawn(uuid);

        for (Player list : game.getPlayers()) {
            if (list.getUniqueId() != uuid) {
                game.setPlayerVisibility(player);
            }
        }


        new BukkitRunnable() {
            int x;

            {
                x = respawnDelay + 1;
            }

            public void run() {
                String i;
                if (x != 0) {
                    --x;
                    i = Integer.toString(x);

                    if (!Objects.equals(respawnChat, "")) {
                        sendMessage(player, replaceString(respawnChat, i));
                    }
                    if (!Objects.equals(respawnTitle, "")) {
                        String titleReal = replaceString(respawnTitle, i);
                        if (!Objects.equals(respawnSubTitle, "")) {
                            String subtitleReal = replaceString(respawnSubTitle, i);

                            sendTitle(player, titleReal, subtitleReal);
                        }
                    } else if (!Objects.equals(respawnSubTitle, "")) {
                        String titleReal = " ";
                        String subtitleReal = replaceString(respawnSubTitle, i);

                        sendTitle(player, titleReal, subtitleReal);
                    }
                    if (!Objects.equals(respawnActionBar, "")) {
                        sendActionBar(player, replaceString(respawnActionBar, i));
                    }

                    if (!game.getRespawnProtections().containsKey(player)) {
                        game.addProtection(player);
                    }

                }

                if (x == 0) {

                    if (!getPlayerisOut(uuid)) {
                        game.removeProtection(player);
                        Vector playerDirection = player.getLocation().getDirection();
                        Location spawnLocationNew = spawnLocation.setDirection(playerDirection);
                        player.setFallDistance(0);
                        player.teleport(spawnLocationNew);
                        removePlayerRespawn(uuid);
                        playerrespawn(uuid, 0L);
                    }


                    i = Integer.toString(x);


                    if (!Objects.equals(respawnSuccChat, "")) {
                        sendMessage(player, replaceString(respawnSuccChat, i));
                    }
                    if (!Objects.equals(respawnSuccTitle, "")) {
                        String titleReal = replaceString(respawnSuccTitle, i);
                        if (!Objects.equals(respawnSuccSubTitle, "")) {
                            String subtitleReal = replaceString(respawnSuccSubTitle, i);

                            sendTitle(player, titleReal, subtitleReal);
                        }
                    } else if (!Objects.equals(respawnSuccSubTitle, "")) {
                        String titleReal = " ";
                        String subtitleReal = replaceString(respawnSuccSubTitle, i);

                        sendTitle(player, titleReal, subtitleReal);
                    }
                    if (!Objects.equals(respawnSuccActionBar, "")) {
                        sendActionBar(player, replaceString(respawnSuccActionBar, i));
                    }

                    cancel();
                }

                if (player.isOnline()) {
                    if (
                            game.getState() == waiting) {
                        cancel();
                    }
                } else {
                    cancel();
                }
            }
        }.runTaskTimer(plugin, delay, 20L);
    }

    @EventHandler(priority = EventPriority.HIGH)
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

        if (gameManager.getGameOfPlayer(player) == null) {
            return;
        }
        Game game = gameManager.getGameOfPlayer(player);
        if (game.getPlayerTeam(player) == null) {
            return;
        }

        Team team = game.getPlayerTeam(player);
        Location spawnLocation = team.getSpawnLocation();
        Location lobbyLoc = game.getLobby();

        player.setGameMode(GameMode.SPECTATOR);

        if (!deathGameMode_tpto.equalsIgnoreCase("none")) {
            if (deathGameMode_tpto.equalsIgnoreCase("team")) {
                player.teleport(spawnLocation);
            } else if (deathGameMode_tpto.equalsIgnoreCase("lobby")) {
                player.teleport(lobbyLoc);
            }
        }

        // pd if can repsawn
        if (game.getPlayerTeam(player).getHeadTarget().getType() != bed_block) {
            // cant start respawn

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


            addPlayerIsOut(playerUUID);

        } else {
            // pd can repsawn
            game.addProtection(player);
            player.addPotionEffect(invs);
            player.setAllowFlight(true);
            player.setFlying(true);
            deathplayer(playerUUID, 1L);
        }
    }
}
