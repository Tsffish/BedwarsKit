package github.tsffish.bedwarskit.util.player;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Method;

import static github.tsffish.bedwarskit.config.lang.LangConfigHandler.cantFoundSupport;
import static github.tsffish.bedwarskit.util.PluginState.serverVersion;
import static github.tsffish.bedwarskit.util.misc.MessSender.le;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class GetItemInHand {
    private static final String className = GetItemInHand.class.getSimpleName();
    private static final String className1_8 = "github.tsffish.bedwarskit.com.v1_8_8." + className;
    private static final String className1_12 = "github.tsffish.bedwarskit.com.v1_12_2." + className;
    private static final String className1_16 = "github.tsffish.bedwarskit.com.v1_16_5." + className;

    public static ItemStack getItemInHand(Player player) {
        if (serverVersion().contains("1.8")) {
            try {
                Class<?> aClass = Class.forName(className1_8);

                Method method = aClass.getMethod("getItemInHand", Player.class);

                return (ItemStack) method.invoke(null, player);
            } catch (Exception e) {
                le(className, e);
            }
        } else if (serverVersion().contains("1.12")
                || serverVersion().contains("1.11")
                || serverVersion().contains("1.10")
                || serverVersion().contains("1.9")) {
            try {
                Class<?> aClass = Class.forName(className1_12);

                Method method = aClass.getMethod("getItemInHand", Player.class);

                return (ItemStack) method.invoke(null, player);
            } catch (Exception e) {
                le(className, e);
            }
        } else if (serverVersion().contains("1.16")
                || serverVersion().contains("1.15")
                || serverVersion().contains("1.14")
                || serverVersion().contains("1.13")) {
            try {
                Class<?> aClass = Class.forName(className1_16);

                Method method = aClass.getMethod("getItemInHand", Player.class);

                return (ItemStack) method.invoke(null, player);
            } catch (Exception e) {
                le(className, e);
            }
        } else {
            try {
                Class<?> aClass = Class.forName(className1_16);

                Method method = aClass.getMethod("getItemInHand", Player.class);

                return (ItemStack) method.invoke(null, player);
            } catch (Exception e) {
                le(className, e);
                le(className, cantFoundSupport + serverVersion());
            }
        }
        return null;
    }


}
