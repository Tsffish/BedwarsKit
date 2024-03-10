package github.tsffish.bedwarskit.listener;

import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;

import static github.tsffish.bedwarskit.config.main.MainConfigHandler.noOpenInventory;
import static github.tsffish.bedwarskit.config.main.MainConfigHandler.noOpenInventoryTypeList;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class RelOpenInventory implements Listener {
    @EventHandler(priority = EventPriority.HIGH)
    public void on(InventoryOpenEvent event) {
        if (!noOpenInventory) {
            return;
        }
        if (event.getPlayer() instanceof Player) {
            Player player = (Player) event.getPlayer();
            if (BedwarsRel.getInstance() == null) {
                return;
            }
            BedwarsRel bedwarsRel = BedwarsRel.getInstance();
            if (bedwarsRel.getGameManager() == null) {
                return;
            }
            GameManager gameManager = bedwarsRel.getGameManager();
            if (gameManager.getGameOfPlayer(player) == null) {
                return;
            }

            Game game = gameManager.getGameOfPlayer(player);

            if (game.isSpectator(player) || game.getRespawnProtections().containsKey(player)) {
                event.setCancelled(true);
                return;
            }
            InventoryType currentType = event.getInventory().getType();
            if (currentType != null) {
                for (InventoryType blockType : noOpenInventoryTypeList) {
                    if (currentType == blockType) {
                        event.setCancelled(true);
                        break;
                    }
                }
            }
        }
    }
}
