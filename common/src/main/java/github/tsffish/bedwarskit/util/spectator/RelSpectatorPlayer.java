package github.tsffish.bedwarskit.util.spectator;

import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static github.tsffish.bedwarskit.BedwarsKit.isDebug;
import static github.tsffish.bedwarskit.util.misc.MessSender.l;

public class RelSpectatorPlayer {
    private static List<UUID> spectatorPlayer = new ArrayList<>(101);
    public static void clearSpectatorPlayers(){
        spectatorPlayer.clear();
    }
    public static boolean getPlayerIsSp(UUID uuid){
        return spectatorPlayer.contains(uuid);
    }
    public static void addPlayer(UUID uuid) {
        if (isDebug()) {
            l("add " + Bukkit.getPlayer(uuid).getName() + " to spectatorPlayer");
        }
        if (!RelSpectatorPlayer.getPlayerIsSp(uuid)) {
            spectatorPlayer.add(uuid);
        }
    }

    public static void removePlayer(UUID uuid){
        spectatorPlayer.remove(uuid);
    }
}
