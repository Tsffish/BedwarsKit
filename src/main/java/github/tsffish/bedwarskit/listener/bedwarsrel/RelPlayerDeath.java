package github.tsffish.bedwarskit.listener.bedwarsrel;

import github.tsffish.bedwarskit.Main;
import github.tsffish.bedwarskit.config.MainConfigHandler;
import github.tsffish.bedwarskit.util.misc.ColorString;
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
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import static github.tsffish.bedwarskit.listener.bedwarsrel.RelPlayerRespawn.playerrespawn;
import static github.tsffish.bedwarskit.util.RelCurrentStat.ups;

public class RelPlayerDeath implements Listener {
    static Plugin plugin = Main.getProvidingPlugin(Main.class);
    @EventHandler
    public void on(PlayerDeathEvent event) {
        Player p = event.getEntity().getPlayer();
        if (p == null || !p.isOnline()) return;
        GameManager gm = BedwarsRel.getInstance().getGameManager();
        if (gm == null) return;
        Game game = gm.getGameOfPlayer(p);
        if (game == null) return;
        if (MainConfigHandler.deathGameMode)
        {
        deathplayer(p, 20L);
        }

    }
    public static void deathplayer(Player p,long dealy)
    {

        GameManager gm = BedwarsRel.getInstance().getGameManager();
        Game game = gm.getGameOfPlayer(p);
        Location spawnLocation = gm.getGameOfPlayer(p).getPlayerTeam(p).getSpawnLocation();

        PlayerInventory inventory = p.getInventory();
        inventory.clear();
        inventory.clear(36); // 清理头盔位置
        inventory.clear(37); // 清理胸甲位置
        inventory.clear(38); // 清理护腿位置
        inventory.clear(39); // 清理靴子位置
        p.getActivePotionEffects().clear();
        ups(p,"d",1);
        new BukkitRunnable() {
            int x;

            {
                x = MainConfigHandler.respawnDelay + 1;
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
                    playerrespawn(p, 1L);
                    Vector playerDirection = p.getLocation().getDirection();
                    Location spawnLocationNew = spawnLocation.setDirection(playerDirection);
                    p.teleport(spawnLocationNew);
                    this.cancel();
                }

                if (p.isOnline()) {
                    if (game.getTimeLeft() == game.getLength() || game.getState() == GameState.WAITING) {
                        p.setGameMode(GameMode.SURVIVAL);
                        this.cancel();
                    }
                }else {
                    this.cancel();
                }
            }
        }.runTaskTimer(plugin, dealy, 20L);
    }

}
