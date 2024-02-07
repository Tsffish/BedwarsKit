package github.tsffish.bedwarskit.listener.bedwarsrel;

import github.tsffish.bedwarskit.config.MainConfigHandler;
import io.github.bedwarsrel.events.BedwarsOpenTeamSelectionEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static github.tsffish.bedwarskit.util.RelTeamSeletorMenu.loadTeamSeletorMenu2v2;
import static github.tsffish.bedwarskit.util.RelTeamSeletorMenu.loadTeamSeletorMenu4v4;

public class RelTeamSeletor implements Listener {
    @EventHandler
    public void on(BedwarsOpenTeamSelectionEvent event){
        event.setCancelled(true);
        Player player = (Player)event.getPlayer();
        if (player == null || !player.isOnline()) return;
        String worldName = player.getWorld().getName();
            if (worldName.contains(MainConfigHandler.rushWorld)) {
                if (worldName.contains(MainConfigHandler.rushWorld2v2)) {
                    event.setCancelled(true);
                    loadTeamSeletorMenu2v2(player);
                } else if (worldName.contains(MainConfigHandler.rushWorld4v4)) {
                    event.setCancelled(true);
                    loadTeamSeletorMenu4v4(player);
                }
        }
    }
}
