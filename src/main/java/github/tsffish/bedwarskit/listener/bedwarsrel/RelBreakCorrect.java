package github.tsffish.bedwarskit.listener.bedwarsrel;

import github.tsffish.bedwarskit.Main;
import github.tsffish.bedwarskit.config.MainConfigHandler;
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
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class RelBreakCorrect implements Listener
{
    Plugin plugin = JavaPlugin.getProvidingPlugin(Main.class);
    @EventHandler
    public void on(BlockBreakEvent e)
    {
        Block block = e.getBlock();
        if (block == null) return;
        GameManager gm = BedwarsRel.getInstance().getGameManager();
        Player player = e.getPlayer();
        if (player == null || !player.isOnline()) return;
        Game game = gm.getGameOfPlayer(player);

        if (MainConfigHandler.breakCorrect_notInGame) {
            if (MainConfigHandler.breakCorrect_notInGame_OpBypass){
            if (player.isOp()){ return;
            }
            }
            String worldName = player.getWorld().getName();
            for (Game list : gm.getGames()) {
                if (list.getRegion().getWorld().getName().equals(worldName) && list.getState() != GameState.RUNNING) {
                    e.setCancelled(true);
                    break;
                }
            }
        }

        if (block.getType().toString().contains("BED")){
            if (game == null) return;

        Team playerTeam = game.getPlayerTeam(player);
        if (playerTeam == null) return;

        Block playerTeamBlock = playerTeam.getTargetHeadBlock().getBlock();
        if (playerTeamBlock == null) return;

        if (MainConfigHandler.breakBedCheck) {
            new BukkitRunnable()
            {
                @Override
                public void run()
                {
                    Player p = e.getPlayer();
                    Location loc = p.getLocation();
                    Location tele = p.getLocation().add(0.0, MainConfigHandler.tpDis, 0.0);
                    loc.setY(loc.getY() - 0.07);
                    Material block = loc.getWorld().getBlockAt(loc).getType();
                    GameManager gm = BedwarsRel.getInstance().getGameManager();
                    Game game = gm.getGameOfPlayer(p);
                    if (game != null && game.getPlayerTeam(p) != null)
                    {
                        Block playerTeamBlock = game.getPlayerTeam(p).getTargetHeadBlock().getBlock();
                        if (playerTeamBlock != null)
                        {
                            Material breakblock = e.getBlock().getType();
                            if (block == Material.BED_BLOCK && breakblock == Material.BED_BLOCK && playerTeamBlock.getType() != Material.AIR)
                            {
                                p.teleport(tele);
                                p.setFallDistance(0.0f);
                            }
                        }
                }
            }
            }.runTaskLater(plugin, 1L);
        }
    }
    }
}
