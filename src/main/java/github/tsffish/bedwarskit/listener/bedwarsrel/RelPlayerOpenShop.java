package github.tsffish.bedwarskit.listener.bedwarsrel;

import github.tsffish.bedwarskit.Main;
import github.tsffish.bedwarskit.config.MainConfigHandler;
import github.tsffish.bedwarskit.util.RelShopLevelUp;
import io.github.bedwarsrel.events.BedwarsOpenShopEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

import static github.tsffish.bedwarskit.config.MainConfigHandler.levelupShopOpenMode;

public class RelPlayerOpenShop implements Listener {
    static Plugin plugin = Main.getProvidingPlugin(Main.class);
    @EventHandler
    public void on(BedwarsOpenShopEvent event){
        if(MainConfigHandler.levelupShop)
        {

            if(Objects.equals(levelupShopOpenMode, "click on entity")){
                if (Objects.equals(event.getEntity().getCustomName(), levelupShopOpenMode)){
                    event.setCancelled(true);
                }
            }
        }
    }

    public static void openShop(Player player, long delay) {
        if (player == null) {
            return;
        }

        String worldName = player.getWorld().getName();
        if (worldName.contains(MainConfigHandler.rushWorld)) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (worldName.contains(MainConfigHandler.rushWorld2v2)) {
                        player.openInventory(RelShopLevelUp.i2);
                    } else if (worldName.contains(MainConfigHandler.rushWorld4v4)) {
                        player.openInventory(RelShopLevelUp.i4);
                    }
                }
            }.runTaskLater(plugin, delay);
        }
    }


}
