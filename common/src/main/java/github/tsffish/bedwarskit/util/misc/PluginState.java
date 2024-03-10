package github.tsffish.bedwarskit.util.misc;

import static github.tsffish.bedwarskit.util.misc.StringMgr.pluginName;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class PluginState {
    public static final String pluginNameConsole = "[" + pluginName + "]";
    private static final String pluginVersion = "1.9.57";
    private static final String author = "Tsffish";
    private static final int spigotId = 105616;
    private static boolean isDebug = false;
    private static boolean isLastestVersion;
    private static String language;
    private static boolean isBungeeMode;
    private static String serverVersion;

    public static String pluginVersion() {
        return pluginVersion;
    }

    public static String pluginNameConsole() {
        return pluginNameConsole;
    }

    public static String getAuthor() {
        return author;
    }

    public static boolean isDebug() {
        return isDebug;
    }

    public static void changeIsDebug() {
        isDebug = !isDebug;
    }

    public static boolean isLastestVersion() {
        return isLastestVersion;
    }

    public static void setIsLastestVersion(boolean setTo) {
        isLastestVersion = setTo;
    }

    public static int spigotId() {
        return spigotId;
    }

    public static String language() {
        return language;
    }

    public static void setLanguage(String setTo) {
        language = setTo;
    }

    public static boolean isBungeeMode() {
        return isBungeeMode;
    }

    public static void setIsBungeeMode(boolean setTo) {
        isBungeeMode = setTo;
    }

    public static String serverVersion() {
        return serverVersion;
    }

    public static void setServerVersion(String setTo) {
        serverVersion = setTo;
    }
}
