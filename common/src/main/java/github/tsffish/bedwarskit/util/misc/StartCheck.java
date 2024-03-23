package github.tsffish.bedwarskit.util.misc;

import github.tsffish.bedwarskit.BedwarsKit;
import org.bukkit.plugin.java.JavaPlugin;

import static github.tsffish.bedwarskit.config.lang.LangConfigHandler.update_tip;
import static github.tsffish.bedwarskit.util.PluginState.*;
import static github.tsffish.bedwarskit.util.misc.MessSender.l;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class StartCheck {
    public static void checkUpdate(int resId) {
        new UpdateChecker(JavaPlugin.getPlugin(BedwarsKit.class), resId).getVersion(version ->
        {
            setIsLastestVersion(pluginVersion().equals(version));
            if (!isLastestVersion()) {
                if (update_tip != null && !update_tip.isEmpty()) {
                    for (String list : update_tip) {
                        l(list);
                    }
                }
            }
        });
    }
}
