package github.tsffish.bedwarskit.listener.bedwarsrel;

import github.tsffish.bedwarskit.Main;
import github.tsffish.bedwarskit.config.MainConfigHandler;
import github.tsffish.bedwarskit.util.ColorString;
import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameManager;
import io.github.bedwarsrel.game.GameState;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class RelPlayerDeath implements Listener {
    Plugin plugin = Main.getProvidingPlugin(Main.class);
    @EventHandler
    public void on(PlayerDeathEvent e) {
        Player p = e.getEntity().getPlayer();
        GameManager gm = BedwarsRel.getInstance().getGameManager();
        Game game = gm.getGameOfPlayer(p);
        if (MainConfigHandler.deathGameMode && game != null && p != null && p.isOnline()) {
            Location spawnLocation = gm.getGameOfPlayer(p).getPlayerTeam(p).getSpawnLocation();
            new BukkitRunnable() {
                int x;

                {
                 x = MainConfigHandler.respawnDelay;
                }

                public void run() {
                    String i;
                    String respawnSuccTitleReal;
                    String respawnSuccSubtitleReal;
                    if (this.x != 0) {
                        --this.x;
                        i = Integer.toString(this.x);
                        respawnSuccTitleReal = MainConfigHandler.respawnTitle.replace("{timeleft}", i);
                        respawnSuccSubtitleReal = MainConfigHandler.respawnSubTitle.replace("{timeleft}", i);
                        p.sendTitle(ColorString.t(respawnSuccTitleReal), ColorString.t(respawnSuccSubtitleReal));
                        p.setGameMode(GameMode.SPECTATOR);
                    }

                    if (this.x == 0) {
                        i = Integer.toString(this.x);
                        respawnSuccTitleReal = MainConfigHandler.respawnSuccTitle.replace("{timeleft}", i);
                        respawnSuccSubtitleReal = MainConfigHandler.respawnSuccSubTitle.replace("{timeleft}", i);
                        p.sendTitle(ColorString.t(respawnSuccTitleReal), ColorString.t(respawnSuccSubtitleReal));
                        p.setGameMode(GameMode.SURVIVAL);
                        Vector playerDirection = p.getLocation().getDirection();
                        Location spawnLocationNew = spawnLocation.setDirection(playerDirection);
                        p.teleport(spawnLocationNew);
                        this.cancel();
                    }

                    if (p.isOnline()) {
                        if (game.getPlayerTeam(p).getHeadTarget() == null || !game.getPlayerTeam(p).getHeadTarget().getType().toString().contains("BED")|| game.getTimeLeft() == game.getLength() || game.getState() == GameState.WAITING) {
                            p.setGameMode(GameMode.SURVIVAL);
                            this.cancel();
                        }
                    }else {
                        this.cancel();
                    }
                }
            }.runTaskTimer(this.plugin, 20L, 20L);
        }

    }
}
