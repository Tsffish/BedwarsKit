package github.tsffish.bedwarskit.util.misc;

import org.bukkit.ChatColor;
/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class ColorString {
    public static String t(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}