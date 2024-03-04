package github.tsffish.bedwarskit.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import static github.tsffish.bedwarskit.config.main.MainConfigHandler.*;
/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
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
