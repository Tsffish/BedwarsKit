package github.tsffish.bedwarskit.listener.bedwarsrel;

import github.tsffish.bedwarskit.config.MainConfigHandler;
import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameManager;
import io.github.bedwarsrel.game.GameState;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author tsffish
 * @see RelBreakBed
 */
public class RelBreakCorrect implements Listener {
    Plugin plugin = github.tsffish.bedwarskit.Main.getProvidingPlugin(github.tsffish.bedwarskit.Main.class);
    @EventHandler
    public void on(BlockBreakEvent e) {
        Player player = e.getPlayer();
        GameManager gm = BedwarsRel.getInstance().getGameManager();
        if (gm.getGameOfPlayer(player) != null && gm.getGameOfPlayer(player).getState() != GameState.RUNNING){
            e.setCancelled(true);
        }

        if (MainConfigHandler.breakBedCheck) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    Player p = e.getPlayer();
                    Location loc = p.getLocation();
                    Location tele = p.getLocation().add(0.0, MainConfigHandler.tpDis, 0.0);
                    loc.setY(loc.getY() - 0.07);
                    Material block = loc.getWorld().getBlockAt(loc).getType();
                    GameManager gm = BedwarsRel.getInstance().getGameManager();
                    Game game = gm.getGameOfPlayer(p);
                    if (game != null && game.getPlayerTeam(p) != null) {
                        Block playerTeamBlock = game.getPlayerTeam(p).getTargetHeadBlock().getBlock();
                        if (playerTeamBlock != null) {
                            Material breakblock = e.getBlock().getType();
                            if (block == Material.BED_BLOCK && breakblock == Material.BED_BLOCK && playerTeamBlock.getType() != Material.AIR) {
                                p.teleport(tele);
                                p.setFallDistance(0.0f);
                            }
                        }
                }
            }}.runTaskLater(plugin, 1L);
        }
    }
}
