package github.tsffish.bedwarskit.util;

import static github.tsffish.bedwarskit.util.misc.StringMgr.pluginName;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class PluginState {
    private static final String pluginVersion = "1.9.6";
    private static final String author = "Tsffish";
    private static final int spigotId = 105616;
    public static String pluginNameConsole = "[" + pluginName + "]";
    private static boolean isDebug = false;
    private static boolean isLastestVersion;
    private static String language;
    private static String country;
    private static String formatLanguage;
    private static boolean isBungeeMode;
    private static String serverVersion;
    private static String serverPackageVersion;
    public static String serverPackageVersion() {
        return serverPackageVersion;
    }

    public static void setServerPackageVersion(String setTo) {
        serverPackageVersion = setTo;
    }

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

        public static String country() {
        return country;
    }

    public static void setCountry(String setTo) {
        country = setTo;
    }
        public static String formatLanguage() {
        return formatLanguage;
    }

    public static void setformatLanguage(String setTo) {
        formatLanguage = setTo;
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
