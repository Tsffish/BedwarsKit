package github.tsffish.bedwarskit.listener;

import github.tsffish.bedwarskit.BedwarsKit;
import github.tsffish.bedwarskit.util.misc.MathUtil;
import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameManager;
import io.github.bedwarsrel.game.GameState;
import io.github.bedwarsrel.game.Team;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;
import java.util.UUID;

import static github.tsffish.bedwarskit.config.main.MainConfigHandler.*;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;
import static github.tsffish.bedwarskit.util.player.PlayerSender.sendTitle;
import static github.tsffish.bedwarskit.util.player.RelPlayerIsRespawn.getPlayerRespawn;
import static github.tsffish.bedwarskit.util.player.SendActionBar.sendActionBar;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class RelPlayerDamage implements Listener {
    private static final BedwarsKit plugin = BedwarsKit.getInstance();
    private static final String bloodModeSingle = "single";
    private static final String bloodModePlayer = "player";
    private static final String bloodModeBox = "box";
    private static final GameState running = GameState.RUNNING;
    private static final GameState waiting = GameState.WAITING;
    private static final Effect step = Effect.STEP_SOUND;
    private static final EntityDamageEvent.DamageCause damageCauseVoid = EntityDamageEvent.DamageCause.VOID;

    @EventHandler(priority = EventPriority.LOW)
    public void on(final EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
            if (BedwarsRel.getInstance().getGameManager() == null) {
                return;
            }
            GameManager gameManager = BedwarsRel.getInstance().getGameManager();

            Player damagedPlayer = (Player) event.getEntity();
            UUID damagedPlayerUUID = damagedPlayer.getUniqueId();
            Player attackingPlayer = (Player) event.getDamager();

            if (gameManager.getGameOfPlayer(damagedPlayer) == null) {
                return;
            }

            if (gameManager.getGameOfPlayer(attackingPlayer) == null) {
                return;
            }

            Game game = gameManager.getGameOfPlayer(attackingPlayer);

            if (getPlayerRespawn(damagedPlayerUUID)
                    || game.getRespawnProtections().containsKey(damagedPlayer)
                    || game.getRespawnProtections().containsKey(attackingPlayer)
            ) {
                event.setCancelled(true);
            }

            if (!event.isCancelled()) {
                if (damagefb_attackBlood) {
                    new BukkitRunnable() {
                        public void run() {

                            int partType = damagefb_attackBloodType;
                            Location damagedPlayerLocation = damagedPlayer.getLocation();
                            switch (damagefb_attackBloodMode.toLowerCase()) {
                                case bloodModeSingle:
                                    attackingPlayer.playEffect(damagedPlayerLocation, step, partType);
                                    break;
                                case bloodModePlayer:
                                    attackingPlayer.playEffect(damagedPlayerLocation, step, partType);
                                    attackingPlayer.playEffect(damagedPlayerLocation.add(1, 0, 0), step, partType);
                                    break;
                                case bloodModeBox:
                                    for (int x = -1; x <= 1; x++) {
                                        for (int y = -1; y <= 1; y++) {
                                            for (int z = -1; z <= 1; z++) {
                                                Location location = damagedPlayerLocation.add(x, y, z);
                                                attackingPlayer.playEffect(location, step, partType);
                                            }
                                        }
                                    }
                                    break;
                                default:
                                    break;
                            }
                        }
                    }.runTaskLater(plugin, 1L);
                }
            }
            if (damagefb_attackmess) {

                double damageOrg = event.getFinalDamage();
                double damage = MathUtil.roundToOneDecimalPlace(damageOrg);

                new BukkitRunnable() {
                    public void run() {
                        if (event.isCancelled()) {
                            cancel();
                        }
                        if (!Objects.equals(damagefb_attackchat, "")) {
                            attackingPlayer.sendMessage(t(damagefb_attackchat).
                                    replace("{damage}", damage + ""));
                        }
                        if (!Objects.equals(damagefb_attackTitle, "")) {
                            String titleReal = t(damagefb_attackTitle).
                                    replace("{damage}", damage + "");
                            if (!Objects.equals(damagefb_attackSubTitle, "")) {
                                String subtitleReal = t(damagefb_attackSubTitle).
                                        replace("{damage}", damage + "");

                                sendTitle(attackingPlayer, titleReal, subtitleReal);
                            }
                        } else if (!Objects.equals(damagefb_attackSubTitle, "")) {
                            String titleReal = " ";
                            String subtitleReal = t(damagefb_attackSubTitle).
                                    replace("{damage}", damage + "");

                            sendTitle(attackingPlayer, titleReal, subtitleReal);
                        }
                        if (!Objects.equals(damagefb_attackactionbar, "")) {
                            sendActionBar(attackingPlayer, t(damagefb_attackactionbar).
                                    replace("{damage}", damage + ""));
                        }
                    }
                }.runTaskLater(plugin, 1L);
            }
        }
    }


    @EventHandler
    public void on(final EntityDamageEvent event) {

        if (BedwarsRel.getInstance().getGameManager() == null) return;
        GameManager gameManager = BedwarsRel.getInstance().getGameManager();

        if (event.getEntity() instanceof Player) {

            Player player = (Player) event.getEntity();
            UUID playerUUID = player.getUniqueId();
            if (getPlayerRespawn(playerUUID)) {
                event.setCancelled(true);
            }
            if (preventLoadWorld) {
                Game game = gameManager.getGameOfPlayer(player);
                if (game == null) {
                    return;
                }

                EntityDamageEvent.DamageCause damageCause = event.getCause();
                if (damageCause == damageCauseVoid && game.getRespawnProtections().containsKey(player)) {
                    Team team = game.getPlayerTeam(player);
                    Location spawnLocation = team.getSpawnLocation();
                    Location lobbyLoc = game.getLobby();

                    player.setFallDistance(0);
                    if (!deathGameMode_tpto.equalsIgnoreCase("none")) {
                        if (deathGameMode_tpto.equalsIgnoreCase("team")) {
                            player.teleport(spawnLocation);
                        } else if (deathGameMode_tpto.equalsIgnoreCase("lobby")) {
                            player.teleport(lobbyLoc);
                        }
                    }
                    event.setCancelled(true);
                    return;
                }
                if (game.getState() == running) {
                    if (damageCause == damageCauseVoid) {
                        if (game.getRespawnProtections().containsKey(player)) {
                            event.setCancelled(true);
                        } else {
                            event.setDamage(player.getHealth());
                        }
                    }
                }
            }
        }
    }
}