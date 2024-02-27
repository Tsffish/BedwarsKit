package github.tsffish.bedwarskit.util;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static github.tsffish.bedwarskit.util.misc.MessSender.le;

public class RelCurrentStat {
    protected static final String className = "RelCurrentStat";
    protected static ConcurrentHashMap<UUID, Integer> playerKill = new ConcurrentHashMap<>(100);
    protected static ConcurrentHashMap<UUID, Integer> playerFKill = new ConcurrentHashMap<>(100);
    protected static ConcurrentHashMap<UUID, Integer> playerDeath = new ConcurrentHashMap<>(100);
    protected static ConcurrentHashMap<UUID, Integer> playerBreakBed = new ConcurrentHashMap<>(100);
    protected static ConcurrentHashMap<UUID, Double> playerKD = new ConcurrentHashMap<>(100);
    protected static ConcurrentHashMap<UUID, Integer> playerOHKill = new ConcurrentHashMap<>(100);
    public static int getPlayerOHKill(UUID uuid){
        return playerOHKill.get(uuid);
    }
    public static int getPlayerKill(UUID uuid){
        return playerKill.get(uuid);
    }
    public static int getPlayerFinalKill(UUID uuid){
        return playerFKill.get(uuid);
    }
    public static int getPlayerDeath(UUID uuid){
        return playerDeath.get(uuid);
    }
    public static int getPlayerBreakBed(UUID uuid){
        return playerBreakBed.get(uuid);
    }
    public static double getPlayerKD(UUID uuid){
        return playerKD.get(uuid);
    }

    public static void updatePlayerStat(UUID uuid, String pd, int value){
        
        if (pd == null){
            le(className,"pd is null");
            return;
        }

        switch (pd.toLowerCase()){
            case "k":
                int k = playerKill.get(uuid);
                playerKill.put(uuid, k + value);
                break;
            case "d":
                int d = playerDeath.get(uuid);
                playerDeath.put(uuid, d + value);
                break;
            case "b":
                int b = playerBreakBed.get(uuid);
                playerBreakBed.put(uuid, b + value);
                break;
            case "f":
                int f = playerFKill.get(uuid);
                playerFKill.put(uuid, f + value);
                break;
            case "ohk":
                int ohk = playerOHKill.getOrDefault(uuid, 0);
                playerOHKill.put(uuid, ohk + value);
                break;
            case "setohk":
                playerOHKill.put(uuid, value);
                break;
            default:
                le("RelCurrentStat","updatePlayerStat Error: pd : "
                        + pd + " is a wrong vaule!");
                break;
        }

        
        int kills = playerKill.getOrDefault(uuid, 0);
        int deaths = playerDeath.getOrDefault(uuid, 0);
        double kd = deaths != 0 ? kills / (double) deaths : kills;
        playerKD.put(uuid, kd);
    }

    public static void removePlayerStat(UUID uuid){
        
        playerKill.remove(uuid);
        playerFKill.remove(uuid);
        playerDeath.remove(uuid);
        playerBreakBed.remove(uuid);
        playerKD.remove(uuid);
        playerOHKill.remove(uuid);
    }

    public static void setDefaultPlayerStat(UUID uuid){
        
        playerKill.put(uuid, 0);
        playerFKill.put(uuid, 0);
        playerDeath.put(uuid, 0);
        playerBreakBed.put(uuid, 0);
        playerKD.put(uuid, 0.0);
        playerOHKill.put(uuid, 0);
    }



    protected static List<UUID> playerIsOut = new ArrayList<>(100);
    public static boolean PlayerisOut(UUID uuid){
        return playerIsOut.contains(uuid);
    }
    public static void addPlayerIsOut(UUID uuid){
        playerIsOut.add(uuid);
    }
    public static void removePlayerIsOut(UUID uuid){
        playerIsOut.remove(uuid);
    }

}
