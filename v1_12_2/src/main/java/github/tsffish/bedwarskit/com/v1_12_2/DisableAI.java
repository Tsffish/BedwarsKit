package github.tsffish.bedwarskit.com.v1_12_2;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;

import java.lang.reflect.Method;
import java.util.List;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class DisableAI {
    public static void disableAI(String worldName) {
        String serverPackageVersion = Bukkit.getServer().getClass().getPackage().getName().substring(23);

        String bukkitClassName = "org.bukkit.craftbukkit." + serverPackageVersion + ".entity.CraftVillager";

        try {
            Class<?> craftVillagerClass = Class.forName(bukkitClassName);

            Method method = craftVillagerClass.getMethod("setAI", boolean.class);

            World world = Bukkit.getWorld(worldName);
            if (world != null) {
                List<Entity> entities = world.getEntities();
                for (Entity entity : entities) {
                    if (craftVillagerClass.isInstance(entity)) {
                        Object craftVillager = craftVillagerClass.cast(entity);

                        method.invoke(craftVillager, false);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
