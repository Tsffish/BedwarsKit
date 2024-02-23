package github.tsffish.bedwarskit.listener.bedwarsrel;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import static github.tsffish.bedwarskit.config.main.MainConfigHandler.grassPaneWalk;

public class RelPlayerMove implements Listener {
    @EventHandler
    public void on(final PlayerMoveEvent event) {
        if (event.getPlayer() == null) {
            return;
        }
        Player player = event.getPlayer();
        if (!player.isOnline()) {
            return;
        }
        if (player.getGameMode() != GameMode.SPECTATOR) {
            return;
        }
        if (grassPaneWalk) {
            Location to = event.getTo();
            Location from = event.getFrom();

            if (player.getWorld().getBlockAt(to).getTypeId() == 102) {
                Location newLocation = to.clone().add(0, 1.05, 0);
                player.setFallDistance(0.0f);
                player.teleport(newLocation);

                Vector direction = to.toVector().subtract(from.toVector()).normalize();

                player.setVelocity(direction.multiply(0.2));
            }
        }
    }
}

