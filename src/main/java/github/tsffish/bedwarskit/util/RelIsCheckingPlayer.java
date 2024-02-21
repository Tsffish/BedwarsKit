package github.tsffish.bedwarskit.util;

import java.util.ArrayList;
import java.util.List;

public class RelIsCheckingPlayer {
    static List<String> isCheckingPlayer = new ArrayList<>(100);
    public static void joinCheckList(String worldName){
        isCheckingPlayer.add(worldName);
    }

    public static void leaveCheckList(String worldName){
        isCheckingPlayer.remove(worldName);
    }

    public static boolean isInCheckList(String worldName){
        return isCheckingPlayer.contains(worldName);
    }
}
