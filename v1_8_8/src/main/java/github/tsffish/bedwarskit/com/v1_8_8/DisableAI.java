package github.tsffish.bedwarskit.com.v1_8_8;

import net.minecraft.server.v1_8_R3.EntityVillager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftVillager;
import org.bukkit.entity.Entity;

import java.util.List;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class DisableAI {
    public static void disableAI(String worldName) {
        try {
            World world = Bukkit.getWorld(worldName);
            if (world != null) {
                List<Entity> entities = world.getEntities();
                for (Entity entity : entities) {
                    if (entity instanceof CraftVillager) {
                        CraftVillager craftVillager = (CraftVillager)entity;
                        EntityVillager entityVillager = craftVillager.getHandle();
                        entityVillager.k(true);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
