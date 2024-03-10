package github.tsffish.bedwarskit.util.task;

import github.tsffish.bedwarskit.BedwarsKit;
import github.tsffish.bedwarskit.config.rel.RelConfigLoad;
import io.github.bedwarsrel.BedwarsRel;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class TaskLoadRelConfig {
    private static final BedwarsKit plugin = BedwarsKit.getInstance();

    public static void loadRelConfigTask() {
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
    }
}
