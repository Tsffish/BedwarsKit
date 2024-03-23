package github.tsffish.bedwarskit.config.misc;

import github.tsffish.bedwarskit.BedwarsKit;
import github.tsffish.bedwarskit.config.rel.RelConfigLoad;
import io.github.bedwarsrel.BedwarsRel;
import org.bukkit.scheduler.BukkitRunnable;

import static github.tsffish.bedwarskit.util.misc.MessSender.le;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class TaskLoadRelConfig {
    private static final BedwarsKit plugin = BedwarsKit.getInstance();
    private static final String className = TaskLoadRelConfig.class.getSimpleName();

    public static void loadRelConfigTask() {
        try {
            new BukkitRunnable() {
                public void run() {
                    if (BedwarsRel.getInstance() != null) {
                        if (BedwarsRel.getInstance().getConfig() != null) {
                            RelConfigLoad.loadRelConfig();
                            cancel();
                        }
                    }
                }
            }.runTaskTimer(plugin, 0L, 20L);
        } catch (Exception e) {
            le(className, e);
        }
    }
}
