package github.tsffish.bedwarskit.listener;

import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameManager;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;

import static github.tsffish.bedwarskit.config.main.MainConfigHandler.creativeGameModeFix;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class RelPlayerToggleFly implements Listener {
    private static final GameMode creative = GameMode.CREATIVE;

    @EventHandler(priority = EventPriority.HIGH)
    public void on(final PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();
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

        if (creativeGameModeFix && player.getGameMode() == creative) {
            event.setCancelled(true);
            player.setFlying(!player.isFlying());
            return;
        }

        if (game.getRespawnProtections().containsKey(player)) {
            event.setCancelled(true);
            player.setFlying(!player.isFlying());
        }
    }
}
