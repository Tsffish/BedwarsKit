//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package github.tsffish.bedwarskit.listener.bedwarsrel;

import github.tsffish.bedwarskit.Main;
import github.tsffish.bedwarskit.config.MainConfigHandler;
import github.tsffish.bedwarskit.util.ColorString;
import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameManager;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class RelPlayerDeath implements Listener {
    Plugin plugin = Main.getProvidingPlugin(Main.class);
    public boolean deathGamemode;
    public Integer respawnDelay;
    public String respawnTitle;
    public String respawnSubTitle;
    public String respawnSuccTitle;
    public String respawnSuccSubTitle;

    public RelPlayerDeath() {
        this.deathGamemode = MainConfigHandler.deathGameMode;
        this.respawnDelay = MainConfigHandler.respawnDelay;
        this.respawnTitle = MainConfigHandler.respawnTitle;
        this.respawnSubTitle = MainConfigHandler.respawnSubTitle;
        this.respawnSuccTitle = MainConfigHandler.respawnSuccTitle;
        this.respawnSuccSubTitle = MainConfigHandler.respawnSuccSubTitle;
    }

    @EventHandler
    public void on(PlayerDeathEvent e) {
        final Player p = e.getEntity().getPlayer();
        GameManager gm = BedwarsRel.getInstance().getGameManager();
        final Game game = gm.getGameOfPlayer(p);
        if (this.deathGamemode && game != null) {
            final Location spawnLocation = gm.getGameOfPlayer(p).getPlayerTeam(p).getSpawnLocation();
            (new BukkitRunnable() {
                int x;

                {
                    this.x = RelPlayerDeath.this.respawnDelay;
                }

                public void run() {
                    String i;
                    String respawnSuccTitleReal;
                    String respawnSuccSubtitleReal;
                    if (this.x != 0) {
                        --this.x;
                        i = Integer.toString(this.x);
                        respawnSuccTitleReal = RelPlayerDeath.this.respawnTitle.replace("{timeleft}", i);
                        respawnSuccSubtitleReal = RelPlayerDeath.this.respawnSubTitle.replace("{timeleft}", i);
                        p.sendTitle(ColorString.t(respawnSuccTitleReal), ColorString.t(respawnSuccSubtitleReal));
                    }

                    if (this.x == 0) {
                        i = Integer.toString(this.x);
                        respawnSuccTitleReal = RelPlayerDeath.this.respawnSuccTitle.replace("{timeleft}", i);
                        respawnSuccSubtitleReal = RelPlayerDeath.this.respawnSuccSubTitle.replace("{timeleft}", i);
                        p.sendTitle(ColorString.t(respawnSuccTitleReal), ColorString.t(respawnSuccSubtitleReal));
                        p.setGameMode(GameMode.SURVIVAL);
                        p.teleport(spawnLocation);
                        this.cancel();
                    }

                    if (game.getTimeLeft() == game.getLength()) {
                        p.setGameMode(GameMode.SURVIVAL);
                        this.cancel();
                    }

                }
            }).runTaskTimer(this.plugin, 20L, 20L);
        }

    }
}
