package github.tsffish.bedwarskit.util.misc;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class MathUtil {
    public static double roundToOneDecimalPlace(double value) {
        return Math.round(value * 10) / 10.0;
    }

    public static String formatTime(int seconds) {
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;

        String formattedTime = String.format("%02d:%02d", minutes, remainingSeconds);
        return formattedTime;
    }
}
