package github.tsffish.bedwarskit.util;

import java.util.ArrayList;
import java.util.List;

public class RelPlayerIsRespawn {
    static List<String> playerRespawn = new ArrayList<>(100);
    public static boolean getPlayerRespawn(String playerName){
        return playerRespawn.contains(playerName);
    }

    public static void addPlayerRespawn(String playerName){
        playerRespawn.add(playerName);
    }

    public static void removePlayerRespawn(String playerName){
        playerRespawn.remove(playerName);
    }
}
