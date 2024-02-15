package github.tsffish.bedwarskit.listener.bedwarsrel;

import github.tsffish.bedwarskit.config.main.MainConfigHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class RelFoodLock implements Listener {
    @EventHandler
    public void on(FoodLevelChangeEvent e) {
        if (e.getEntity() == null) return;
        if (MainConfigHandler.noHunger && e.getEntity().getWorld().getName().contains(MainConfigHandler.rushWorld) && e.getFoodLevel() != MainConfigHandler.maxFoodLevel) {
            e.setFoodLevel(MainConfigHandler.maxFoodLevel);
        }

    }
}
