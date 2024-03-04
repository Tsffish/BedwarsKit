package github.tsffish.bedwarskit.com.v1_12_r2.listener;

import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class RelPickupItem implements Listener {
    @EventHandler
    public void on(EntityPickupItemEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
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

            if (game.isSpectator(player)) {
                event.setCancelled(true);
            }
        }
    }
}
