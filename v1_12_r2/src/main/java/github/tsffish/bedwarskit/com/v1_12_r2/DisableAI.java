package github.tsffish.bedwarskit.com.v1_12_r2;

import net.minecraft.server.v1_12_R1.EntityVillager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftVillager;
import org.bukkit.entity.Entity;

import java.util.List;
/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class DisableAI {
    public static void disableAI(String worldName){
                World world = Bukkit.getWorld(worldName);
                if (world != null) {
                    List<Entity> entities = world.getEntities();
                    for (Entity entity : entities) {
                        if (entity instanceof CraftVillager) {
                            EntityVillager entityVillager = ((CraftVillager) entity).getHandle();
                            entityVillager.setNoAI(true);
                        }
                    }
                }
    }
}
