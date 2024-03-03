package github.tsffish.bedwarskit.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import static github.tsffish.bedwarskit.config.main.MainConfigHandler.*;

public class RelPlayerFoodLevel implements Listener {
    @EventHandler
    public void on(final FoodLevelChangeEvent event) {
        if (event.getEntity() == null) {
            return;
        }
        if (noHunger
                && event.getEntity().getWorld().getName().contains(rushWorld)
                && event.getFoodLevel() != maxFoodLevel) {
            event.setFoodLevel(maxFoodLevel);
        }

    }
}
