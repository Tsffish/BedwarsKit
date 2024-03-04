package github.tsffish.bedwarskit.listener;

import github.tsffish.bedwarskit.BedwarsKit;
import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameManager;
import io.github.bedwarsrel.game.GameState;
import io.github.bedwarsrel.game.Team;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.scheduler.BukkitRunnable;

import static github.tsffish.bedwarskit.config.main.MainConfigHandler.*;
import static github.tsffish.bedwarskit.util.misc.GetBlockType.BED_BLOCK;
import static github.tsffish.bedwarskit.util.misc.MessSender.le;

public class RelBreakCorrect implements Listener {
    private static final String className = "RelBreakCorrect";
    static final Material bed_block = BED_BLOCK();
    private static final BedwarsKit plugin = BedwarsKit.getInstance();

    @EventHandler
    public void on(final BlockBreakEvent e) {
        Block block = e.getBlock();
        if (block == null) return;
        GameManager gm = BedwarsRel.getInstance().getGameManager();
        Player player = e.getPlayer();
        if (player == null || !player.isOnline()) return;
        Game game = gm.getGameOfPlayer(player);
try {
    if (breakCorrect_notInGame) {
        if (breakCorrect_notInGame_OpBypass) {
            if (!player.isOp()) {
                String worldName = player.getWorld().getName();
                for (Game list : gm.getGames()) {
                    if (list.getRegion().getWorld().getName().equals(worldName) && list.getState() != GameState.RUNNING) {
                        e.setCancelled(true);
                        break;
                    }
                }
            }
        }
    }
}catch (Exception exception){
    le(className,exception);
}
            if (block.getType() == bed_block) {
                if (game == null) return;

                Team playerTeam = game.getPlayerTeam(player);
                if (playerTeam == null) return;

                Block playerTeamBlock = playerTeam.getTargetHeadBlock().getBlock();
                if (playerTeamBlock == null) return;

                if (breakBedCheck) {
                    Player p = e.getPlayer();
                    Location playerLocation = p.getLocation();

                    Location teleportLocation = p.getLocation().add(0.0, tpDis, 0.0);

                    playerLocation.setY(playerLocation.getY() - 0.07);

                    Material blockType = playerLocation.getWorld().getBlockAt(playerLocation).getType();
                    if (game.getPlayerTeam(p) != null) {
                        Block playerTeamBlockHead = game.getPlayerTeam(p).getTargetHeadBlock().getBlock();
                        if (playerTeamBlockHead != null) {
                            Material breakblock = e.getBlock().getType();
                            if (blockType == bed_block
                                    && breakblock == bed_block
                                    && playerLocation.getBlock().getLocation() == e.getBlock().getLocation())

                                new BukkitRunnable() {
                                    @Override
                                    public void run() {
                                        p.teleport(teleportLocation);
                                        p.setFallDistance(0.0f);
                                    }
                                }.runTaskLater(plugin, 20L);
                        }
                    }
                }
            }
        }
    }

