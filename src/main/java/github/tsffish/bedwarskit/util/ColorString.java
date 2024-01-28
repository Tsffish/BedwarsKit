package github.tsffish.bedwarskit.util;

import org.bukkit.ChatColor;

public class ColorString {
    public static String t(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}