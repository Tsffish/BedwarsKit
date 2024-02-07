package github.tsffish.bedwarskit.util.misc;

public class SecondToTime {
    public static String formatSecondsToTimeString(int seconds) {
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;

        return String.format("%02d:%02d", minutes, remainingSeconds);
    }
}
