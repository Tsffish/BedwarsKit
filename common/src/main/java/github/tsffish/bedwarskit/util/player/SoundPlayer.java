package github.tsffish.bedwarskit.util.player;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Locale;

import static github.tsffish.bedwarskit.config.lang.LangConfigHandler.cantFoundSupport;
import static github.tsffish.bedwarskit.util.PluginState.serverVersion;
import static github.tsffish.bedwarskit.util.misc.MessSender.le;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class SoundPlayer {
    private static final String className = SoundPlayer.class.getSimpleName();
    private static final String className1_8 = "github.tsffish.bedwarskit.com.v1_8_8." + className;
    private static final String className1_12 = "github.tsffish.bedwarskit.com.v1_12_2." + className;
    private static final String className1_16 = "github.tsffish.bedwarskit.com.v1_16_5." + className;

    public static void CLICK(Player player, int pitch) {
        if (serverVersion().contains("1.8")) {
            try {
                Class<?> aClass = Class.forName(className1_8);

                Field field = aClass.getField("CLICK");

                Sound sound = (Sound)field.get(null);

                Location location = player.getLocation();
                player.playSound(location,sound,1,pitch);

            } catch (Exception e) {
                le(className, e);
            }
        } else if (serverVersion().contains("1.12")
                || serverVersion().contains("1.11")
                || serverVersion().contains("1.10")
                || serverVersion().contains("1.9")) {
            try {
                Class<?> aClass = Class.forName(className1_12);

                Field field = aClass.getField("CLICK");

                Sound sound = (Sound)field.get(null);

                Location location = player.getLocation();
                player.playSound(location,sound,1,pitch);
            } catch (Exception e) {
                le(className, e);
            }
        } else if (serverVersion().contains("1.16")
                || serverVersion().contains("1.15")
                || serverVersion().contains("1.14")
                || serverVersion().contains("1.13")) {
            try {
                Class<?> aClass = Class.forName(className1_16);

                Field field = aClass.getField("CLICK");

                Sound sound = (Sound)field.get(null);

                Location location = player.getLocation();
                player.playSound(location,sound,1,pitch);
            } catch (Exception e) {
                le(className, e);
            }
        } else {

            try {
                Class<?> aClass = Class.forName(className1_16);

                Field field = aClass.getField("CLICK");

                Sound sound = (Sound)field.get(null);

                Location location = player.getLocation();
                player.playSound(location,sound,1,pitch);
            } catch (Exception e) {
                le(className, e);
            }
        }
    }

    public static void LEVEL_UP(Player player, int pitch) {
        if (serverVersion().contains("1.8")) {
            try {
                Class<?> aClass = Class.forName(className1_8);

                Field field = aClass.getField("LEVEL_UP");

                Sound sound = (Sound)field.get(null);

                Location location = player.getLocation();
                player.playSound(location,sound,1,pitch);

            } catch (Exception e) {
                le(className, e);
            }
        } else if (serverVersion().contains("1.12")
                || serverVersion().contains("1.11")
                || serverVersion().contains("1.10")
                || serverVersion().contains("1.9")) {
            try {
                Class<?> aClass = Class.forName(className1_12);

                Field field = aClass.getField("LEVEL_UP");

                Sound sound = (Sound)field.get(null);

                Location location = player.getLocation();
                player.playSound(location,sound,1,pitch);
            } catch (Exception e) {
                le(className, e);
            }
        } else if (serverVersion().contains("1.16")
                || serverVersion().contains("1.15")
                || serverVersion().contains("1.14")
                || serverVersion().contains("1.13")) {
            try {
                Class<?> aClass = Class.forName(className1_16);

                Field field = aClass.getField("LEVEL_UP");

                Sound sound = (Sound)field.get(null);

                Location location = player.getLocation();
                player.playSound(location,sound,1,pitch);
            } catch (Exception e) {
                le(className, e);
            }
        } else {

            try {
                Class<?> aClass = Class.forName(className1_16);

                Field field = aClass.getField("LEVEL_UP");

                Sound sound = (Sound)field.get(null);

                Location location = player.getLocation();
                player.playSound(location,sound,1,pitch);
            } catch (Exception e) {
                le(className, e);
            }
        }
    }

    public static void ITEM_PICKUP(Player player, int pitch) {
        if (serverVersion().contains("1.8")) {
            try {
                Class<?> aClass = Class.forName(className1_8);

                Field field = aClass.getField("ITEM_PICKUP");

                Sound sound = (Sound)field.get(null);

                Location location = player.getLocation();
                player.playSound(location,sound,1,pitch);

            } catch (Exception e) {
                le(className, e);
            }
        } else if (serverVersion().contains("1.12")
                || serverVersion().contains("1.11")
                || serverVersion().contains("1.10")
                || serverVersion().contains("1.9")) {
            try {
                Class<?> aClass = Class.forName(className1_12);

                Field field = aClass.getField("ITEM_PICKUP");

                Sound sound = (Sound)field.get(null);

                Location location = player.getLocation();
                player.playSound(location,sound,1,pitch);
            } catch (Exception e) {
                le(className, e);
            }
        } else if (serverVersion().contains("1.16")
                || serverVersion().contains("1.15")
                || serverVersion().contains("1.14")
                || serverVersion().contains("1.13")) {
            try {
                Class<?> aClass = Class.forName(className1_16);

                Field field = aClass.getField("ITEM_PICKUP");

                Sound sound = (Sound)field.get(null);

                Location location = player.getLocation();
                player.playSound(location,sound,1,pitch);
            } catch (Exception e) {
                le(className, e);
            }
        } else {

            try {
                Class<?> aClass = Class.forName(className1_16);

                Field field = aClass.getField("ITEM_PICKUP");

                Sound sound = (Sound)field.get(null);

                Location location = player.getLocation();
                player.playSound(location,sound,1,pitch);
            } catch (Exception e) {
                le(className, e);
            }
        }
    }

    public static void ITEM_BREAK(Player player, int pitch) {
        if (serverVersion().contains("1.8")) {
            try {
                Class<?> aClass = Class.forName(className1_8);

                Field field = aClass.getField("ITEM_BREAK");

                Sound sound = (Sound)field.get(null);

                Location location = player.getLocation();
                player.playSound(location,sound,1,pitch);

            } catch (Exception e) {
                le(className, e);
            }
        } else if (serverVersion().contains("1.12")
                || serverVersion().contains("1.11")
                || serverVersion().contains("1.10")
                || serverVersion().contains("1.9")) {
            try {
                Class<?> aClass = Class.forName(className1_12);

                Field field = aClass.getField("ITEM_BREAK");

                Sound sound = (Sound)field.get(null);

                Location location = player.getLocation();
                player.playSound(location,sound,1,pitch);
            } catch (Exception e) {
                le(className, e);
            }
        } else if (serverVersion().contains("1.16")
                || serverVersion().contains("1.15")
                || serverVersion().contains("1.14")
                || serverVersion().contains("1.13")) {
            try {
                Class<?> aClass = Class.forName(className1_16);

                Field field = aClass.getField("ITEM_BREAK");

                Sound sound = (Sound)field.get(null);

                Location location = player.getLocation();
                player.playSound(location,sound,1,pitch);
            } catch (Exception e) {
                le(className, e);
            }
        } else {

            try {
                Class<?> aClass = Class.forName(className1_16);

                Field field = aClass.getField("ITEM_BREAK");

                Sound sound = (Sound)field.get(null);

                Location location = player.getLocation();
                player.playSound(location,sound,1,pitch);
            } catch (Exception e) {
                le(className, e);
            }
        }
    }

    public static void CHICKEN_EGG_POP(Player player, int pitch) {
        if (serverVersion().contains("1.8")) {
            try {
                Class<?> aClass = Class.forName(className1_8);

                Field field = aClass.getField("CHICKEN_EGG_POP");

                Sound sound = (Sound)field.get(null);

                Location location = player.getLocation();
                player.playSound(location,sound,1,pitch);

            } catch (Exception e) {
                le(className, e);
            }
        } else if (serverVersion().contains("1.12")
                || serverVersion().contains("1.11")
                || serverVersion().contains("1.10")
                || serverVersion().contains("1.9")) {
            try {
                Class<?> aClass = Class.forName(className1_12);

                Field field = aClass.getField("CHICKEN_EGG_POP");

                Sound sound = (Sound)field.get(null);

                Location location = player.getLocation();
                player.playSound(location,sound,1,pitch);
            } catch (Exception e) {
                le(className, e);
            }
        } else if (serverVersion().contains("1.16")
                || serverVersion().contains("1.15")
                || serverVersion().contains("1.14")
                || serverVersion().contains("1.13")) {
            try {
                Class<?> aClass = Class.forName(className1_16);

                Field field = aClass.getField("CHICKEN_EGG_POP");

                Sound sound = (Sound)field.get(null);

                Location location = player.getLocation();
                player.playSound(location,sound,1,pitch);
            } catch (Exception e) {
                le(className, e);
            }
        } else {

            try {
                Class<?> aClass = Class.forName(className1_16);

                Field field = aClass.getField("CHICKEN_EGG_POP");

                Sound sound = (Sound)field.get(null);

                Location location = player.getLocation();
                player.playSound(location,sound,1,pitch);
            } catch (Exception e) {
                le(className, e);
            }
        }
    }

    public static void ENDERMAN_TELEPORT(Player player, int pitch) {
        if (serverVersion().contains("1.8")) {
            try {
                Class<?> aClass = Class.forName(className1_8);

                Field field = aClass.getField("ENDERMAN_TELEPORT");

                Sound sound = (Sound)field.get(null);

                Location location = player.getLocation();
                player.playSound(location,sound,1,pitch);

            } catch (Exception e) {
                le(className, e);
            }
        } else if (serverVersion().contains("1.12")
                || serverVersion().contains("1.11")
                || serverVersion().contains("1.10")
                || serverVersion().contains("1.9")) {
            try {
                Class<?> aClass = Class.forName(className1_12);

                Field field = aClass.getField("ENDERMAN_TELEPORT");

                Sound sound = (Sound)field.get(null);

                Location location = player.getLocation();
                player.playSound(location,sound,1,pitch);
            } catch (Exception e) {
                le(className, e);
            }
        } else if (serverVersion().contains("1.16")
                || serverVersion().contains("1.15")
                || serverVersion().contains("1.14")
                || serverVersion().contains("1.13")) {
            try {
                Class<?> aClass = Class.forName(className1_16);

                Field field = aClass.getField("ENDERMAN_TELEPORT");

                Sound sound = (Sound)field.get(null);

                Location location = player.getLocation();
                player.playSound(location,sound,1,pitch);
            } catch (Exception e) {
                le(className, e);
            }
        } else {

            try {
                Class<?> aClass = Class.forName(className1_16);

                Field field = aClass.getField("ENDERMAN_TELEPORT");

                Sound sound = (Sound)field.get(null);

                Location location = player.getLocation();
                player.playSound(location,sound,1,pitch);
            } catch (Exception e) {
                le(className, e);
            }
        }
    }

}
