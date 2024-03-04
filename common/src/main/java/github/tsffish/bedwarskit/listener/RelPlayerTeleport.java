package github.tsffish.bedwarskit.listener;

import github.tsffish.bedwarskit.config.main.MainConfigHandler;
import github.tsffish.bedwarskit.util.misc.SoundPlayer;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import static github.tsffish.bedwarskit.config.main.MainConfigHandler.lobbyWorld;
import static github.tsffish.bedwarskit.config.main.MainConfigHandler.noPearlDamage_TPSound;
/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class RelPlayerTeleport implements Listener {
    private static final float resFallDis = 0.0f;
    private static final GameMode survival = GameMode.SURVIVAL;
    @EventHandler
    public void on(final PlayerTeleportEvent e) {
        Player player = e.getPlayer();

        if (player == null || !player.isOnline())
        {
            return;
        }

        World world = e.getPlayer().getWorld();
        String worldName = world.getName();
        boolean isInRushWorld = worldName.contains(MainConfigHandler.rushWorld);
        PlayerTeleportEvent.TeleportCause enderpearl = PlayerTeleportEvent.TeleportCause.ENDER_PEARL;

        Location goTo = e.getTo();
        if (MainConfigHandler.noPearlDamage && isInRushWorld && e.getCause() == enderpearl) {
            if (noPearlDamage_TPSound){
                SoundPlayer.ENDERMAN_TELEPORT(player, 1);
            }
            e.setCancelled(true);
            player.setFallDistance(resFallDis);
            player.teleport(goTo);
        }
    }
    @EventHandler
    public void on(final PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();
        if (player == null || !player.isOnline())
        {
            return;
        }

        if (player.getWorld().getName().contains(lobbyWorld))
        {
            if (player.getGameMode() != survival)
            {
                player.setGameMode(survival);
            }
        }
    }

}
