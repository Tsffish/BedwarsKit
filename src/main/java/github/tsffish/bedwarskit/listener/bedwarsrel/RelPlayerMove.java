package github.tsffish.bedwarskit.listener.bedwarsrel;

import github.tsffish.bedwarskit.config.main.MainConfigHandler;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class RelPlayerMove implements Listener {
    @EventHandler
    public void on(final PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (MainConfigHandler.grassPaneWalk && player != null && player.isOnline() && player.getGameMode() != GameMode.SPECTATOR) {
            Location to = event.getTo();
            Location from = event.getFrom();

            if (player.getWorld().getBlockAt(to).getTypeId() == 102) {
                Location newLocation = to.clone().add(0, 1.05, 0);
                player.setFallDistance(0.0f);
                player.teleport(newLocation);

                // 计算玩家的方向向量
                Vector direction = to.toVector().subtract(from.toVector()).normalize();

                // 设置玩家的击退速度
                player.setVelocity(direction.multiply(0.2));
            }
        }
    }


}

