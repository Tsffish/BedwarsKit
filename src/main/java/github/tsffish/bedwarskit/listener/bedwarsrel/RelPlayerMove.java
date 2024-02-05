package github.tsffish.bedwarskit.listener.bedwarsrel;

import github.tsffish.bedwarskit.config.MainConfigHandler;
import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameManager;
import io.github.bedwarsrel.game.GameState;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import static github.tsffish.bedwarskit.config.MainConfigHandler.*;
import static github.tsffish.bedwarskit.util.RelArmorList.*;
import static github.tsffish.bedwarskit.util.RelCheckSword.gs;

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
                // 将玩家位置调整到离开玻璃板的位置
                Location newLocation = to.clone().add(0, 1.05, 0);
                player.teleport(newLocation);
            }
        }
    }
}

