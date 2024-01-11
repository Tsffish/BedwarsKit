package github.tsffish.bedwarskit.listener.bedwarsrel;

import github.tsffish.bedwarskit.config.MainConfigHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class RelFoodLock implements Listener {
    String rushWorld;
    boolean noHunger;
    Integer maxFoodLevel;

    public RelFoodLock() {
        this.rushWorld = MainConfigHandler.rushWorld;
        this.noHunger = MainConfigHandler.noHunger;
        this.maxFoodLevel = MainConfigHandler.maxFoodLevel;
    }

    @EventHandler
    public void on(FoodLevelChangeEvent e) {
        if (this.noHunger && e.getEntity().getWorld().getName().contains(this.rushWorld) && e.getFoodLevel() != this.maxFoodLevel) {
            e.setFoodLevel(this.maxFoodLevel);
        }

    }
}
