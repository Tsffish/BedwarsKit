package github.tsffish.bedwarskit.listener;

import github.tsffish.bedwarskit.util.player.SoundPlayer;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import static github.tsffish.bedwarskit.config.main.MainConfigHandler.*;
import static github.tsffish.bedwarskit.util.PluginState.isBungeeMode;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class RelPlayerTeleport implements Listener {
    private static final float resFallDis = 0.0f;
    private static final GameMode survival = GameMode.SURVIVAL;

    @EventHandler(priority = EventPriority.HIGH)
    public void on(final PlayerTeleportEvent event) {
        Player player = event.getPlayer();

        if (player == null || !player.isOnline()) {
            return;
        }

        World world = event.getPlayer().getWorld();
        String worldName = world.getName();
        boolean isInRushWorld = worldName.contains(gameWorld);
        PlayerTeleportEvent.TeleportCause enderpearl = PlayerTeleportEvent.TeleportCause.ENDER_PEARL;

        Location goTo = event.getTo();
        if (noPearlDamage && isInRushWorld && event.getCause() == enderpearl) {
            if (noPearlDamage_TPSound) {
                SoundPlayer.ENDERMAN_TELEPORT(player, 1);
            }
            event.setCancelled(true);
            player.setFallDistance(resFallDis);
            player.teleport(goTo);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void on(final PlayerChangedWorldEvent event) {
        if (isBungeeMode()) {
            return;
        }
        if (event.getPlayer() == null || !event.getPlayer().isOnline()) {
            return;
        }

        Player player = event.getPlayer();

        if (player.getWorld().getName().contains(lobbyWorld)) {
            if (player.getGameMode() != survival) {
                player.setGameMode(survival);
            }
        }
    }

}
