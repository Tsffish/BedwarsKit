package github.tsffish.bedwarskit.com.v1_8_r8;

import net.minecraft.server.v1_8_R3.EntityVillager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftVillager;
import org.bukkit.entity.Entity;

import java.util.List;

public class DisableVillagerAI {
    public static void disableVillagerAI(String worldName){
                World world = Bukkit.getWorld(worldName);

                if (world != null) {
                    List<Entity> entities = world.getEntities();
                    for (Entity entity : entities) {
                        if (entity instanceof CraftVillager) {
                            EntityVillager entityVillager = ((CraftVillager) entity).getHandle();
                            entityVillager.k(true);
                        }
                    }
                }
    }
}
