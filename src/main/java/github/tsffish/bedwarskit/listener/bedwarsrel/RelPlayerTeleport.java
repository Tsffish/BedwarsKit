package github.tsffish.bedwarskit.listener.bedwarsrel;

import github.tsffish.bedwarskit.config.MainConfigHandler;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class RelPlayerTeleport implements Listener
{
    @EventHandler
    public void on(PlayerTeleportEvent e)
    {
        Player p = e.getPlayer();

        if (p == null || !p.isOnline())
        {
            return;
        }

        String world = p.getWorld().getName();
        boolean isInRushWorld = world.contains(MainConfigHandler.rushWorld);
        PlayerTeleportEvent.TeleportCause enderpearl = PlayerTeleportEvent.TeleportCause.ENDER_PEARL;
        if (MainConfigHandler.noPearlDamage && isInRushWorld && e.getCause() == enderpearl)
        {
            e.setCancelled(true);
            p.teleport(e.getTo());
            p.setFallDistance(0.0f);
        }
    }
    @EventHandler
    public void on(PlayerChangedWorldEvent event)
    {
        Player p = event.getPlayer();
        if (p == null || !p.isOnline())
        {
            return;
        }

        if (p.getWorld().getName().contains(MainConfigHandler.lobbyWorld))
        {
            if (p.getGameMode() != GameMode.SURVIVAL)
            {
            p.setGameMode(GameMode.SURVIVAL);
            }
        }
    }

}
