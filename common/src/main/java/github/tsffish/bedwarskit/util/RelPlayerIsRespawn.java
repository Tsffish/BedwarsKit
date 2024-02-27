package github.tsffish.bedwarskit.util;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RelPlayerIsRespawn {
    static List<UUID> playerRespawn = new ArrayList<>(100);
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
