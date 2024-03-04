package github.tsffish.bedwarskit.listener;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import static github.tsffish.bedwarskit.config.main.MainConfigHandler.grassPaneWalk;
/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class RelPlayerMove implements Listener {
    private static final int glassPaneId = 102;
    private static final GameMode spectator = GameMode.SPECTATOR;
    private static final float resFallDis = 0.0f;
    @EventHandler
    public void on(final PlayerMoveEvent event) {
        if (!grassPaneWalk) return;

        if (event.getPlayer() == null) {
                return;
            }
            Player player = event.getPlayer();
            if (!player.isOnline()) {
                return;
            }
            if (player.getGameMode() != spectator) {
                return;
            }

            Location to = event.getTo();
            Location from = event.getFrom();

            if (player.getWorld().getBlockAt(to).getTypeId() == glassPaneId) {
                Location newLocation = to.clone().add(0, 1.05, 0);

                player.setFallDistance(resFallDis);
                player.teleport(newLocation);

                Vector direction = to.toVector().subtract(from.toVector()).normalize();

                player.setVelocity(direction.multiply(0.2));
            }
    }
}

