package github.tsffish.bedwarskit.listener;

import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameManager;
import io.github.bedwarsrel.game.GameState;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import static github.tsffish.bedwarskit.config.main.MainConfigHandler.breakCorrect_notInGame;
import static github.tsffish.bedwarskit.config.main.MainConfigHandler.breakCorrect_notInGame_OpBypass;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class RelBreakCorrect implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void on(final BlockBreakEvent event) {
        Block block = event.getBlock();
        if (block == null) {
            return;
        }
        GameManager gm = BedwarsRel.getInstance().getGameManager();
        Player player = event.getPlayer();
        if (player == null || !player.isOnline()) {
            return;
        }
        if (breakCorrect_notInGame) {
            if (breakCorrect_notInGame_OpBypass) {
                if (!player.isOp()) {
                    String worldName = player.getWorld().getName();
                    for (Game list : gm.getGames()) {
                        if (list.getRegion().getWorld().getName().equals(worldName) && list.getState() != GameState.RUNNING) {
                            event.setCancelled(true);
                            break;
                        }
                    }
                }
            }
        }
    }
}

