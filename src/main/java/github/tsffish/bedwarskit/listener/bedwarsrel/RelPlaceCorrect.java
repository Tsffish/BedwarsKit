package github.tsffish.bedwarskit.listener.bedwarsrel;

import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.game.GameManager;
import io.github.bedwarsrel.game.GameState;
import io.github.bedwarsrel.game.ResourceSpawner;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.ArrayList;
import java.util.List;

import static github.tsffish.bedwarskit.config.MainConfigHandler.rushWorld;
import static github.tsffish.bedwarskit.util.ColorString.t;

public class RelPlaceCorrect implements Listener {
    @EventHandler
    public void on(BlockPlaceEvent e) {

        Player player = e.getPlayer();
        boolean isRushWorld = player.getWorld().getName().contains(rushWorld);
        if(isRushWorld) {
            GameManager gm = BedwarsRel.getInstance().getGameManager();
            if (gm.getGameOfPlayer(player).getState() != GameState.RUNNING && !player.isOp()) {
                e.setCancelled(true);
            }

            if (gm.getGameOfPlayer(player).getState() == GameState.RUNNING) {
                Location loc = e.getBlock().getLocation();
                List<Object> proloc = new ArrayList<>();

                for (ResourceSpawner rs : gm.getGameOfPlayer(player).getResourceSpawners()) {
                    proloc.add(rs.getLocation().add(0.0, 1.0, 0.0));
                }

                boolean isResourceSpawnPoint = false;
                for (Object obj : proloc) {
                    Location ploc = (Location) obj;
                    if (ploc.equals(loc)) {
                        isResourceSpawnPoint = true;
                        break;
                    }
                }

                if (isResourceSpawnPoint) {
                    e.setCancelled(true);
                    player.sendMessage(t("&b起床战争 &7>> &c无法在此处放置方块!"));
                }
            }

        }
    }
}