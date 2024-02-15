package github.tsffish.bedwarskit.listener.bedwarsrel;

import github.tsffish.bedwarskit.Main;
import github.tsffish.bedwarskit.config.main.MainConfigHandler;
import github.tsffish.bedwarskit.util.RelPlayerIsRespawn;
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
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Objects;

import static github.tsffish.bedwarskit.util.RelCurrentStat.*;
import static github.tsffish.bedwarskit.util.RelPlayerRespawn.playerrespawn;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;
import static io.github.bedwarsrel.com.v1_8_r3.ActionBar.sendActionBar;

public class RelPlayerDeath implements Listener {
    private static final Plugin plugin = JavaPlugin.getPlugin(Main.class);
    @EventHandler
    public void on(PlayerDeathEvent event) {
        Player p = event.getEntity().getPlayer();
        if (p == null || !p.isOnline()) return;
        String playerName = p.getName();
        GameManager gm = BedwarsRel.getInstance().getGameManager();
        if (gm == null) return;
        Game game = gm.getGameOfPlayer(p);
        if (game == null) return;
        if (game.getPlayerTeam(p).getHeadTarget() == null || !game.getPlayerTeam(p).getHeadTarget().getType().toString().contains("BED")){
            addPlayerIsOut(playerName);
            return;
        }

        if (!PlayerisOut(playerName)){
            if (MainConfigHandler.deathGameMode) {
                    p.setHealth(0.5);
                if (!RelPlayerIsRespawn.getPlayerRespawn(playerName)){
                    RelPlayerIsRespawn.addPlayerRespawn(playerName);
                    deathplayer(p, 20L);
                }
            }
        }


    }
    public static void deathplayer(Player p,long dealy)
    {

        p.setHealth(0.5);

        String playerName = p.getName();
        GameManager gm = BedwarsRel.getInstance().getGameManager();
        Game game = gm.getGameOfPlayer(p);
        Location spawnLocation = gm.getGameOfPlayer(p).getPlayerTeam(p).getSpawnLocation();

        PlayerInventory inventory = p.getInventory();

        for (ItemStack list : inventory.getContents()){
            if (list != null && list.getType() == MainConfigHandler.killfb_oneHealthKill_itemType
            && list.getItemMeta().getDisplayName().equals(MainConfigHandler.killfb_oneHealthKill_itemName)){

            }else {
                inventory.remove(list);
            }
        }

        inventory.clear(36);
        inventory.clear(37);
        inventory.clear(38);
        inventory.clear(39);
        p.getActivePotionEffects().clear();

        updatePlayerStat(playerName,"setohk",0);

        updatePlayerStat(playerName,"d", 1);

        new BukkitRunnable() {
            int x;

            {
                x = MainConfigHandler.respawnDelay + 1;
            }

            public void run() {
                String i;
                if (x != 0) {
                    --x;
                    i = Integer.toString(x);

                    if (!Objects.equals(MainConfigHandler.respawnChat, "")) {
                        p.sendMessage(MainConfigHandler.respawnChat.replace("{timeleft}",i));
                    }
                    if (!Objects.equals(MainConfigHandler.respawnTitle, "")) {
                        String titleReal = t(MainConfigHandler.respawnTitle.replace("{timeleft}",i));
                        if (!Objects.equals(MainConfigHandler.respawnSubTitle, "")) {
                            String subtitleReal = t(MainConfigHandler.respawnSubTitle.replace("{timeleft}",i));

                            p.sendTitle(titleReal, subtitleReal);
                        }
                    } else if (!Objects.equals(MainConfigHandler.respawnSubTitle, "")) {
                        String titleReal = " ";
                        String subtitleReal = t(MainConfigHandler.respawnSubTitle.replace("{timeleft}",i));

                        p.sendTitle(titleReal, subtitleReal);
                    }
                    if (!Objects.equals(MainConfigHandler.respawnActionBar, "")) {
                        sendActionBar(p, t(MainConfigHandler.respawnActionBar.replace("{timeleft}",i)));
                    }
                    p.setGameMode(GameMode.SPECTATOR);
                }

                if (x == 0) {
                    i = Integer.toString(x);

                    if (!Objects.equals(MainConfigHandler.respawnSuccChat, "")) {
                        p.sendMessage(MainConfigHandler.respawnSuccChat.replace("{timeleft}",i));
                    }
                    if (!Objects.equals(MainConfigHandler.respawnSuccTitle, "")) {
                        String titleReal = t(MainConfigHandler.respawnSuccTitle.replace("{timeleft}",i));
                        if (!Objects.equals(MainConfigHandler.respawnSuccSubTitle, "")) {
                            String subtitleReal = t(MainConfigHandler.respawnSuccSubTitle.replace("{timeleft}",i));

                            p.sendTitle(titleReal, subtitleReal);
                        }
                    } else if (!Objects.equals(MainConfigHandler.respawnSuccSubTitle, "")) {
                        String titleReal = " ";
                        String subtitleReal = t(MainConfigHandler.respawnSuccSubTitle.replace("{timeleft}",i));

                        p.sendTitle(titleReal, subtitleReal);
                    }
                    if (!Objects.equals(MainConfigHandler.respawnSuccActionBar, "")) {
                        sendActionBar(p, t(MainConfigHandler.respawnSuccActionBar.replace("{timeleft}",i)));
                    }


                    playerrespawn(p, 1L);
                    Vector playerDirection = p.getLocation().getDirection();
                    Location spawnLocationNew = spawnLocation.setDirection(playerDirection);
                    p.teleport(spawnLocationNew);
                    cancel();
                }

                if (p.isOnline()) {
                    if (game.getTimeLeft() == game.getLength() || game.getState() == GameState.WAITING) {
                        p.setGameMode(GameMode.SURVIVAL);
                        cancel();
                    }
                }else {
                    cancel();
                }
            }
        }.runTaskTimer(plugin, dealy, 20L);
    }

}
