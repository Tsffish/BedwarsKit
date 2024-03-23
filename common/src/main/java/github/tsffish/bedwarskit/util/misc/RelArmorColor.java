package github.tsffish.bedwarskit.util.misc;

import org.bukkit.Color;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class RelArmorColor {
    public static Color
            red,
            blue,
            green,
            yellow,
            aqua,
            gray,
            pink,
            white,
            orange;

    static {
        red = Color.fromRGB(255, 0, 0);
        blue = Color.fromRGB(0, 0, 255);
        green = Color.fromRGB(0, 255, 0);
        yellow = Color.fromRGB(255, 255, 0);
        aqua = Color.fromRGB(0, 255, 255);
        gray = Color.fromRGB(190, 190, 190);
        pink = Color.fromRGB(255, 105, 180);
        white = Color.fromRGB(255, 255, 255);
        orange = Color.fromRGB(255, 165, 0);
    }
}