package github.tsffish.bedwarskit.listener.bedwarsrel;

import github.tsffish.bedwarskit.config.MainConfigHandler;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class RelPlayerMove implements Listener
{

    @EventHandler
    public void on(PlayerMoveEvent event)
    {
        Player player = event.getPlayer();
        if (MainConfigHandler.grassPaneWalk && player !=null && player.isOnline() && player.getGameMode() != GameMode.SPECTATOR) {
            Location to = event.getTo();

            if (player.getWorld().getBlockAt(to).getTypeId() == 102)
            {
                
                Location newLocation = to.clone().add(0, 1.05, 0);
                player.teleport(newLocation);
            }
        }
    }
}

