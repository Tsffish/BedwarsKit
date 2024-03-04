package github.tsffish.bedwarskit.util.misc;

public class MathUtil {
    public static double roundToOneDecimalPlace(double value) {
        return Math.round(value * 10) / 10.0;
    }
    public static String formatTime(int seconds)
    {
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;
        return String.format("%2d:%2d", minutes, remainingSeconds);
    }
}
