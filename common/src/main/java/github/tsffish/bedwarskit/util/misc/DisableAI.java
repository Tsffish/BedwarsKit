package github.tsffish.bedwarskit.util.misc;

import java.lang.reflect.Method;

import static github.tsffish.bedwarskit.listener.PluginDisable.pluginIsDisabling;
import static github.tsffish.bedwarskit.util.misc.MessSender.le;
import static github.tsffish.bedwarskit.util.misc.PluginState.serverVersion;
import static github.tsffish.bedwarskit.util.misc.StringMgr.cantFoundSupport;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class DisableAI {
    private static final String className = "DisableAI";

    public static void disableAI(String worldName) {
        if (serverVersion().contains("1.8")) {
            try {
                Method method = github.tsffish.bedwarskit.com.v1_8_r8.DisableAI.class.
                        getMethod("disableAI", String.class);
                method.invoke(null, worldName);
            } catch (Exception e) {
                if (!pluginIsDisabling) {
                    le(className, e);
                }
            }
        } else if (serverVersion().contains("1.12")) {
            try {
                Method method = github.tsffish.bedwarskit.com.v1_12_r2.DisableAI.class.
                        getMethod("disableAI", String.class);
                method.invoke(null, worldName);

            } catch (Exception e) {
                if (!pluginIsDisabling) {
                    le(className, e);
                }
            }
        } else if (serverVersion().contains("1.9")
                || serverVersion().contains("1.10")
                || serverVersion().contains("1.11")) {
            try {
                Method method = github.tsffish.bedwarskit.com.v1_12_r2.DisableAI.class.
                        getMethod("disableAI", String.class);
                method.invoke(null, worldName);

            } catch (Exception e) {
                if (!pluginIsDisabling) {
                    le(className, e);
                }
            }
        } else {
            if (!pluginIsDisabling) {
                le(className, cantFoundSupport + serverVersion());
            }
        }
    }
}