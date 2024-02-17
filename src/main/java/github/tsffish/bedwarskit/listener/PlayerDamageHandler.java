package github.tsffish.bedwarskit.listener;

import github.tsffish.bedwarskit.config.main.MainConfigHandler;
import github.tsffish.bedwarskit.listener.bedwarsrel.RelPlayerDeath;
import github.tsffish.bedwarskit.util.RelPlayerIsRespawn;
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
import org.bukkit.util.Vector;

import java.util.Objects;

import static github.tsffish.bedwarskit.config.main.MainConfigHandler.*;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;
import static io.github.bedwarsrel.com.v1_8_r3.ActionBar.sendActionBar;

public class PlayerDamageHandler implements Listener {

    @EventHandler
    public void on(final EntityDamageByEntityEvent e) {
        GameManager gameManager = BedwarsRel.getInstance().getGameManager();

        if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
            Player damagedPlayer = (Player) e.getEntity();
            Player attackingPlayer = (Player) e.getDamager();

            if (!attackingPlayer.getWorld().getName().contains(rushWorld)){
                if (gameManager.getGameOfPlayer(attackingPlayer) == null || gameManager.getGameOfPlayer(attackingPlayer).getState() != GameState.RUNNING){
                    e.setCancelled(true);
                    return;
                }
            }

            if (damagefb_attackBlood){
                int parttype = MainConfigHandler.damagefb_attackBloodType;
                switch (damagefb_attackBloodMode.toLowerCase()) {
                    case "single":
                        damagedPlayer.getWorld().playEffect(damagedPlayer.getLocation(), Effect.STEP_SOUND, parttype);
                        break;
                    case "player":
                        damagedPlayer.getWorld().playEffect(damagedPlayer.getLocation(), Effect.STEP_SOUND, parttype);
                        damagedPlayer.getWorld().playEffect(damagedPlayer.getLocation().add(1, 0, 0), Effect.STEP_SOUND, parttype);
                        break;
                    case "box":
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

            if (damagefb_attackmess){
                double damage = e.getDamage();
                if (!Objects.equals(damagefb_attackchat, "")) {
                    attackingPlayer.sendMessage(t(MainConfigHandler.damagefb_attackchat).replace("{damage}",damage + ""));
                }
                if (!Objects.equals(MainConfigHandler.damagefb_attackTitle, "")) {
                    String titleReal = t(MainConfigHandler.damagefb_attackTitle).replace("{damage}",damage + "");
                    if (!Objects.equals(MainConfigHandler.damagefb_attackSubTitle, "")) {
                        String subtitleReal = t(MainConfigHandler.damagefb_attackSubTitle).replace("{damage}",damage + "");

                        attackingPlayer.sendTitle(titleReal, subtitleReal);
                    }
                } else if (!Objects.equals(MainConfigHandler.damagefb_attackSubTitle, "")) {
                    String titleReal = " ";
                    String subtitleReal = t(damagefb_attackSubTitle).replace("{damage}",damage + "");

                    attackingPlayer.sendTitle(titleReal, subtitleReal);
                }
                if (!Objects.equals(MainConfigHandler.damagefb_attackactionbar, "")) {
                    sendActionBar(attackingPlayer, t(damagefb_attackactionbar).replace("{damage}",damage + ""));
                }
            }

            String playerName = damagedPlayer.getName();
            if  (damagedPlayer.getHealth() - e.getFinalDamage() <= 0 ){
                Game game = gameManager.getGameOfPlayer(damagedPlayer);
                if (game == null)return;
                Team team = game.getPlayerTeam(damagedPlayer);
                if (preventloadworld) {
                    Location loc = team.getSpawnLocation();
                    Vector v = damagedPlayer.getLocation().getDirection();
                    loc.setDirection(v);
                    damagedPlayer.setFallDistance(0);
                    damagedPlayer.teleport(loc);
                    if (!RelPlayerIsRespawn.getPlayerRespawn(playerName)){
                        RelPlayerIsRespawn.addPlayerRespawn(playerName);
                        RelPlayerDeath.deathplayer(damagedPlayer ,0L);
                    }
                }

            }
        }
    }


    @EventHandler
    public void on(EntityDamageEvent event){

        GameManager gameManager = BedwarsRel.getInstance().getGameManager();
        if (gameManager == null) return;

        if (event.getEntity() instanceof Player){
            Player player = (Player) event.getEntity();
            String playerName = player.getName();
            Game game = gameManager.getGameOfPlayer(player);
            if (game == null)return;
            Team team = game.getPlayerTeam(player);
            EntityDamageEvent.DamageCause cause = event.getCause();
            if (cause == EntityDamageEvent.DamageCause.VOID) {
                if (preventloadworld) {
                    Location loc = team.getSpawnLocation();
                    Vector v = player.getLocation().getDirection();
                    loc.setDirection(v);
                    player.setFallDistance(0);
                    player.teleport(loc);
                    if (!RelPlayerIsRespawn.getPlayerRespawn(playerName)){
                        RelPlayerIsRespawn.addPlayerRespawn(playerName);
                    RelPlayerDeath.deathplayer(player ,0L);
                    }
                }
            }
        }


    }
}
