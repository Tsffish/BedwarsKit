package github.tsffish.bedwarskit.util;

import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import java.lang.reflect.Method;

import static github.tsffish.bedwarskit.config.lang.LangConfigHandler.cantFoundSupport;
import static github.tsffish.bedwarskit.util.PluginState.serverVersion;
import static github.tsffish.bedwarskit.util.misc.MessSender.le;

/**
 * A Addon for A MC plugin that allows you to quickly edit games in other ways
 * github.com/Tsffish/BedwarsMapCreate
 *
 * @author Tsffish
 */
public class GetInventory {
    private static final String className = GetInventory.class.getSimpleName();
    private static final String className1_8 = "github.tsffish.bedwarskit.com.v1_8_8." + className;
    private static final String className1_16 = "github.tsffish.bedwarskit.com.v1_16_2." + className;

    public static String getInvTitle(Inventory inventory) {
        if (serverVersion().contains("1.8")
                || serverVersion().contains("1.9")
                || serverVersion().contains("1.10")
                || serverVersion().contains("1.11")
                || serverVersion().contains("1.12")) {
            try {
                Class<?> aClass = Class.forName(className1_8);

                Method method = aClass.getMethod("getInvTitle", Inventory.class);

                return (String) method.invoke(null, inventory);
            } catch (Exception e) {
                le(className, e);
            }
        } else if (serverVersion().contains("1.16")
                || serverVersion().contains("1.15")
                || serverVersion().contains("1.14")
                || serverVersion().contains("1.13")) {
            try {
                Class<?> aClass = Class.forName(className1_16);

                Method method = aClass.getMethod("getInvTitle", Inventory.class);

                return (String) method.invoke(null, inventory);
            } catch (Exception e) {
                le(className, e);
            }
        } else {
            try {
                try {
                    Class<?> aClass = Class.forName(className1_16);

                    Method method = aClass.getMethod("getInvTitle", Inventory.class);

                    return (String) method.invoke(null, inventory);
                } catch (Exception e) {
                    le(className, e);
                }
            } catch (Exception e) {
                le(className, e);
                le(className, cantFoundSupport + serverVersion());
            }
        }
        return null;
    }

    public static InventoryType getInvType(Inventory inventory) {
        if (serverVersion().contains("1.8")
                || serverVersion().contains("1.9")
                || serverVersion().contains("1.10")
                || serverVersion().contains("1.11")
                || serverVersion().contains("1.12")) {
            try {
                Class<?> aClass = Class.forName(className1_8);

                Method method = aClass.getMethod("getInvType", Inventory.class);

                return (InventoryType) method.invoke(null, inventory);
            } catch (Exception e) {
                le(className, e);
            }
        } else if (serverVersion().contains("1.16")
                || serverVersion().contains("1.15")
                || serverVersion().contains("1.14")
                || serverVersion().contains("1.13")) {
            try {
                Class<?> aClass = Class.forName(className1_16);

                Method method = aClass.getMethod("getInvType", Inventory.class);

                return (InventoryType) method.invoke(null, inventory);
            } catch (Exception e) {
                le(className, e);
            }
        } else {
            try {
                try {
                    Class<?> aClass = Class.forName(className1_16);

                    Method method = aClass.getMethod("getInvType", Inventory.class);

                    return (InventoryType) method.invoke(null, inventory);
                } catch (Exception e) {
                    le(className, e);
                }
            } catch (Exception e) {
                le(className, e);
                le(className, cantFoundSupport + serverVersion());
            }
        }
        return null;
    }
}
