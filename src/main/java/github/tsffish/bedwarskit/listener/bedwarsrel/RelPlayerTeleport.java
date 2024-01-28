package github.tsffish.bedwarskit.listener.bedwarsrel;

import github.tsffish.bedwarskit.config.MainConfigHandler;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class RelPlayerTeleport implements Listener {
    @EventHandler
    public void on(PlayerTeleportEvent e) {
        Player p = e.getPlayer();
        String world = p.getWorld().getName();
        boolean isInRushWorld = world.contains(MainConfigHandler.rushWorld);
        // 禁用末影珍珠伤害
        PlayerTeleportEvent.TeleportCause enderpearl = PlayerTeleportEvent.TeleportCause.ENDER_PEARL;
        if (MainConfigHandler.noPearlDamage && isInRushWorld && e.getCause() == enderpearl) {
            e.setCancelled(true);
            p.teleport(e.getTo());
            // 如果不设置摔落距离会导致出现摔落伤害
            p.setFallDistance(0.0f);
        }
    }
    @EventHandler
    public void on(PlayerChangedWorldEvent event){
        if (event.getPlayer().getWorld().getName().contains(MainConfigHandler.lobbyWorld)){
            if (event.getPlayer().getGameMode() != GameMode.SURVIVAL){
            event.getPlayer().setGameMode(GameMode.SURVIVAL);
            }
        }
    }

}
