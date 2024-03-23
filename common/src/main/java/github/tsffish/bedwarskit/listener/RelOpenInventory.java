package github.tsffish.bedwarskit.listener;

import github.tsffish.bedwarskit.util.GetInventory;
import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameManager;
import io.github.bedwarsrel.game.GameState;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;

import static github.tsffish.bedwarskit.config.main.MainConfigHandler.noOpenInventory;
import static github.tsffish.bedwarskit.config.main.MainConfigHandler.noOpenInventoryTypeList;
import static github.tsffish.bedwarskit.util.GetInventory.getInvType;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class RelOpenInventory implements Listener {
    private static final GameState running = GameState.RUNNING;

    @EventHandler(priority = EventPriority.HIGHEST)
    public void on(final InventoryOpenEvent event) {
        if (!noOpenInventory) {
            return;
        }
        if (BedwarsRel.getInstance() == null) {
                return;
            }
            BedwarsRel bedwarsRel = BedwarsRel.getInstance();
            if (bedwarsRel.getGameManager() == null) {
                return;
            }
            GameManager gameManager = bedwarsRel.getGameManager();

        HumanEntity entity = event.getPlayer();

        if (entity instanceof Player) {
            Player player = (Player) entity;

            if (gameManager.getGameOfPlayer(player) == null) {
                return;
            }

            Game game = gameManager.getGameOfPlayer(player);

            if (       game.getState() != running
                    || player.isSleeping()
                    || !player.isOnline()
                    || player.isDead()) {
                return;
            }

            if (game.isSpectator(player) || game.getRespawnProtections().containsKey(player)) {
                event.setCancelled(true);
                return;
            }

            InventoryType currentType = getInvType(event.getInventory());
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
