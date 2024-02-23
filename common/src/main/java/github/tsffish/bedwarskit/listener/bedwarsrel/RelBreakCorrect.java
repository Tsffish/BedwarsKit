package github.tsffish.bedwarskit.listener.bedwarsrel;

import github.tsffish.bedwarskit.Main;
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
import static github.tsffish.bedwarskit.util.GetBlockType.BED_BLOCK;

public class RelBreakCorrect implements Listener {
    static final Material air_block = Material.AIR;
    static final Material bed_block = BED_BLOCK();
    private static final Main plugin = Main.getInstance();

    @EventHandler
    public void on(final BlockBreakEvent e) {
        Block block = e.getBlock();
        if (block == null) return;
        GameManager gm = BedwarsRel.getInstance().getGameManager();
        Player player = e.getPlayer();
        if (player == null || !player.isOnline()) return;
        Game game = gm.getGameOfPlayer(player);

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
            if (block.getType() == bed_block) {
                if (game == null) return;

                Team playerTeam = game.getPlayerTeam(player);
                if (playerTeam == null) return;

                Block playerTeamBlock = playerTeam.getTargetHeadBlock().getBlock();
                if (playerTeamBlock == null) return;

                if (breakBedCheck) {
                    Player p = e.getPlayer();
                    Location loc = p.getLocation();
                    Location tele = p.getLocation().add(0.0, tpDis, 0.0);
                    loc.setY(loc.getY() - 0.07);
                    Material blockType = loc.getWorld().getBlockAt(loc).getType();
                    if (game.getPlayerTeam(p) != null) {
                        Block playerTeamBlockHead = game.getPlayerTeam(p).getTargetHeadBlock().getBlock();
                        if (playerTeamBlockHead != null) {
                            Material breakblock = e.getBlock().getType();
                            if (blockType == bed_block
                                    && breakblock == bed_block
                                    && playerTeamBlockHead.getType() != air_block
                                    && loc.getBlock().getLocation() == e.getBlock().getLocation())

                                new BukkitRunnable() {
                                    @Override
                                    public void run() {
                                        p.teleport(tele);
                                        p.setFallDistance(0.0f);
                                    }
                                }.runTaskLater(plugin, 20L);
                        }
                    }
                }
            }
        }
    }

