package github.tsffish.bedwarskit.listener.bedwarsrel;

import github.tsffish.bedwarskit.config.main.MainConfigHandler;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import static github.tsffish.bedwarskit.config.main.MainConfigHandler.noPearlDamage_TPSound;

public class RelPlayerTeleport implements Listener
{
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
            world.playSound(goTo, Sound.ENDERMAN_TELEPORT,1,1);
            }
            e.setCancelled(true);
            player.setFallDistance(0.0f);
            player.teleport(e.getTo());
        }
    }
    @EventHandler
    public void on(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();
        if (player == null || !player.isOnline())
        {
            return;
        }

        if (player.getWorld().getName().contains(MainConfigHandler.lobbyWorld))
        {
            if (player.getGameMode() != GameMode.SURVIVAL)
            {
                player.setGameMode(GameMode.SURVIVAL);
            }
        }
    }

}
