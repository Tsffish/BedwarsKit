package github.tsffish.bedwarskit.listener;

import org.bukkit.entity.Enderman;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;

import static github.tsffish.bedwarskit.config.main.MainConfigHandler.noEndermanGriefing;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class RelMobGriefing implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void on(EntityChangeBlockEvent event) {
        if (!noEndermanGriefing) {
            return;
        }
            if (event.getEntity() instanceof Enderman) {
                event.setCancelled(true);
            }
        }
}
