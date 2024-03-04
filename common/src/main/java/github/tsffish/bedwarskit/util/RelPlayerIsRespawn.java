package github.tsffish.bedwarskit.util;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class RelPlayerIsRespawn {
    private static Set<UUID> playerRespawn = Collections.newSetFromMap(new ConcurrentHashMap<>());
    public static boolean getPlayerRespawn(UUID uuid){
        return playerRespawn.contains(uuid);
    }
    public static void addPlayerRespawn(UUID uuid){
        playerRespawn.add(uuid);
    }
    public static void removePlayerRespawn(UUID uuid){
        playerRespawn.remove(uuid);
    }
}
