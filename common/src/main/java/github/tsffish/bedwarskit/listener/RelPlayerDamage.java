package github.tsffish.bedwarskit.listener;

import github.tsffish.bedwarskit.BedwarsKit;
import github.tsffish.bedwarskit.config.main.MainConfigHandler;
import github.tsffish.bedwarskit.util.RelPlayerIsRespawn;
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
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

import static github.tsffish.bedwarskit.config.main.MainConfigHandler.*;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;
import static github.tsffish.bedwarskit.util.misc.PlayerSender.sendTitle;
import static github.tsffish.bedwarskit.util.misc.SendActionBar.sendActionBar;

public class RelPlayerDamage implements Listener {
    private static final BedwarsKit plugin = BedwarsKit.getInstance();
    private static final String bloodModeSingle = "single";
    private static final String bloodModePlayer = "player";
    private static final String bloodModeBox = "box";
    private static final GameState running = GameState.RUNNING;
    private static final GameState waiting = GameState.WAITING;
    private static final EntityDamageEvent.DamageCause damageCauseVoid = EntityDamageEvent.DamageCause.VOID;
    @EventHandler
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
            if (RelPlayerIsRespawn.getPlayerRespawn(damagedPlayerUUID)
                    || game.getRespawnProtections().containsKey(damagedPlayer)
                    || game.getRespawnProtections().containsKey(attackingPlayer)) {
                event.setCancelled(true);
            }

            if (damagefb_attackBlood) {
                new BukkitRunnable() {
                    public void run() {
                        if (event.isCancelled()){
                            cancel();
                        }

                        int parttype = MainConfigHandler.damagefb_attackBloodType;
                        switch (damagefb_attackBloodMode.toLowerCase()) {
                            case bloodModeSingle:
                                attackingPlayer.playEffect(damagedPlayer.getLocation(), Effect.STEP_SOUND, parttype);
                                break;
                            case bloodModePlayer:
                                attackingPlayer.playEffect(damagedPlayer.getLocation(), Effect.STEP_SOUND, parttype);
                                attackingPlayer.playEffect(damagedPlayer.getLocation().add(1, 0, 0), Effect.STEP_SOUND, parttype);
                                break;
                            case bloodModeBox:
                                for (int x = -1; x <= 1; x++) {
                                    for (int y = -1; y <= 1; y++) {
                                        for (int z = -1; z <= 1; z++) {
                                            Location location = damagedPlayer.getLocation().add(x, y, z);
                                            attackingPlayer.playEffect(location, Effect.STEP_SOUND, parttype);
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

            if (damagefb_attackmess) {

                double damageOrg = event.getDamage();
                double damage = MathUtil.roundToOneDecimalPlace(damageOrg);

                new BukkitRunnable() {
                    public void run() {
                        if (event.isCancelled()){
                            cancel();
                        }
                            if (!damagefb_attackchat.isEmpty()) {
                                attackingPlayer.sendMessage(t(MainConfigHandler.damagefb_attackchat).
                                        replace("{damage}", damage + ""));
                            }
                            if (!MainConfigHandler.damagefb_attackTitle.isEmpty()) {
                                String titleReal = t(MainConfigHandler.damagefb_attackTitle).
                                        replace("{damage}", damage + "");
                                if (!damagefb_attackSubTitle.isEmpty()) {
                                    String subtitleReal = t(MainConfigHandler.damagefb_attackSubTitle).
                                            replace("{damage}", damage + "");

                                    sendTitle(attackingPlayer,titleReal, subtitleReal);
                                }
                            } else if (!damagefb_attackSubTitle.isEmpty()) {
                                String titleReal = " ";
                                String subtitleReal = t(damagefb_attackSubTitle).
                                        replace("{damage}", damage + "");

                                sendTitle(attackingPlayer,titleReal, subtitleReal);
                            }
                            if (!damagefb_attackactionbar.isEmpty()) {
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
            if (RelPlayerIsRespawn.getPlayerRespawn(playerUUID)) {
                event.setCancelled(true);
            }
            if (preventloadworld) {
                Game game = gameManager.getGameOfPlayer(player);
            if (game == null) {
                return;
            }




                EntityDamageEvent.DamageCause damageCause = event.getCause();
                if (damageCause == damageCauseVoid && game.isSpectator(player)) {
                    Team team = game.getPlayerTeam(player);
                    Location spawnLocation = team.getSpawnLocation();
                    Location lobbyLoc = game.getLobby();

                    player.setFallDistance(0);
                    if (!deathGameMode_tpto.equalsIgnoreCase("none")){
                        if (deathGameMode_tpto.equalsIgnoreCase("team")){
                            player.teleport(spawnLocation);
                        }  else if (deathGameMode_tpto.equalsIgnoreCase("lobby")){
                            player.teleport(lobbyLoc);
                        }
                    }
                    event.setCancelled(true);
                    return;
                }
            if (game.getState() == running) {
                    if (damageCause == damageCauseVoid) {
                        if (game.getRespawnProtections().containsKey(player)){
                            event.setCancelled(true);
                        }else {
                            event.setDamage(player.getHealth());
                        }
                    }
                } else if (game.getState() == waiting) {
                    if (damageCause == damageCauseVoid) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }
}