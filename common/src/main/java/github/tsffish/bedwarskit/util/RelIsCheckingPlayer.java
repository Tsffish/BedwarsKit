package github.tsffish.bedwarskit.util;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class RelIsCheckingPlayer {
    private static Set<String> isCheckingPlayer = Collections.newSetFromMap(new ConcurrentHashMap<>());
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
