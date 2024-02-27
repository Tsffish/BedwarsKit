package github.tsffish.bedwarskit.listener;

import github.tsffish.bedwarskit.config.main.MainConfigHandler;
import github.tsffish.bedwarskit.util.RelPlayerIsRespawn;
import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameManager;
import io.github.bedwarsrel.game.GameState;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.Objects;
import java.util.UUID;

import static github.tsffish.bedwarskit.config.main.MainConfigHandler.*;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;
import static io.github.bedwarsrel.com.v1_8_r3.ActionBar.sendActionBar;

public class RelPlayerDamage implements Listener {
    private static final String bloodModeSingle = "single";
    private static final String bloodModePlayer = "player";
    private static final String bloodModeBox = "box";
    private static final GameState running = GameState.RUNNING;
    private static final GameState waiting = GameState.WAITING;
    private static final EntityDamageEvent.DamageCause damageCauseVoid = EntityDamageEvent.DamageCause.VOID;
    @EventHandler
    public void on(final EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
            if (BedwarsRel.getInstance().getGameManager() == null){
                return;
            }
            GameManager gameManager = BedwarsRel.getInstance().getGameManager();

            Player damagedPlayer = (Player) e.getEntity();
            Player attackingPlayer = (Player) e.getDamager();
            if (gameManager.getGameOfPlayer(damagedPlayer) == null) {
                return;
            }
            if (!attackingPlayer.getWorld().getName().contains(rushWorld)) {
                if (gameManager.getGameOfPlayer(attackingPlayer) == null
                        || gameManager.getGameOfPlayer(attackingPlayer).getState() != GameState.RUNNING) {
                    e.setCancelled(true);
                    return;
                }
            }

            if (damagefb_attackBlood) {
                int parttype = MainConfigHandler.damagefb_attackBloodType;
                switch (damagefb_attackBloodMode.toLowerCase()) {
                    case bloodModeSingle:
                        damagedPlayer.getWorld().playEffect(damagedPlayer.getLocation(), Effect.STEP_SOUND, parttype);
                        break;
                    case bloodModePlayer:
                        damagedPlayer.getWorld().playEffect(damagedPlayer.getLocation(), Effect.STEP_SOUND, parttype);
                        damagedPlayer.getWorld().playEffect(damagedPlayer.getLocation().add(1, 0, 0), Effect.STEP_SOUND, parttype);
                        break;
                    case bloodModeBox:
                        for (int x = -1; x <= 1; x++) {
                            for (int y = -1; y <= 1; y++) {
                                for (int z = -1; z <= 1; z++) {
                                    Location location = damagedPlayer.getLocation().add(x, y, z);
                                    damagedPlayer.getWorld().playEffect(location, Effect.STEP_SOUND, parttype);
                                }
                            }
                        }
                        break;
                    default:
                        break;
                }
            }

            if (damagefb_attackmess) {
                double damage = e.getDamage();
                if (!Objects.equals(damagefb_attackchat, "")) {
                    attackingPlayer.sendMessage(t(MainConfigHandler.damagefb_attackchat).replace("{damage}", damage + ""));
                }
                if (!Objects.equals(MainConfigHandler.damagefb_attackTitle, "")) {
                    String titleReal = t(MainConfigHandler.damagefb_attackTitle).replace("{damage}", damage + "");
                    if (!Objects.equals(MainConfigHandler.damagefb_attackSubTitle, "")) {
                        String subtitleReal = t(MainConfigHandler.damagefb_attackSubTitle).replace("{damage}", damage + "");

                        attackingPlayer.sendTitle(titleReal, subtitleReal);
                    }
                } else if (!Objects.equals(MainConfigHandler.damagefb_attackSubTitle, "")) {
                    String titleReal = " ";
                    String subtitleReal = t(damagefb_attackSubTitle).replace("{damage}", damage + "");

                    attackingPlayer.sendTitle(titleReal, subtitleReal);
                }
                if (!Objects.equals(MainConfigHandler.damagefb_attackactionbar, "")) {
                    sendActionBar(attackingPlayer, t(damagefb_attackactionbar).replace("{damage}", damage + ""));
                }
            }

            UUID uuid = damagedPlayer.getUniqueId();
            if (damagedPlayer.getHealth() - e.getFinalDamage() <= 0) {
                Game game = gameManager.getGameOfPlayer(damagedPlayer);
                if (game == null) {
                    return;
                }
                if (preventloadworld) {
                    if (!RelPlayerIsRespawn.getPlayerRespawn(uuid)) {
                        RelPlayerIsRespawn.addPlayerRespawn(uuid);
                    }
                }
            }
        }
    }


    @EventHandler
    public void on(final EntityDamageEvent event) {

        if (BedwarsRel.getInstance().getGameManager() == null) return;
        GameManager gameManager = BedwarsRel.getInstance().getGameManager();

        if (event.getEntity() instanceof Player) {

            Player player = (Player) event.getEntity();

            if (preventloadworld) {
                Game game = gameManager.getGameOfPlayer(player);
            if (game == null) {
                return;
            }
                EntityDamageEvent.DamageCause damageCause = event.getCause();
            if (game.getState() == running) {
                    if (damageCause == damageCauseVoid) {
                        if (RelPlayerIsRespawn.getPlayerRespawn(player.getUniqueId())) {
                            event.setCancelled(true);
                        }
                        event.setDamage(player.getHealth());
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