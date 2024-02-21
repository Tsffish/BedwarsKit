package github.tsffish.bedwarskit.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static github.tsffish.bedwarskit.util.misc.MessSender.le;

public class RelCurrentStat {
    static ConcurrentHashMap<String, Integer> playerKill = new ConcurrentHashMap<>(100);
    static ConcurrentHashMap<String, Integer> playerFKill = new ConcurrentHashMap<>(100);
    static ConcurrentHashMap<String, Integer> playerDeath = new ConcurrentHashMap<>(100);
    static ConcurrentHashMap<String, Integer> playerBreakBed = new ConcurrentHashMap<>(100);
    static ConcurrentHashMap<String, Double> playerKD = new ConcurrentHashMap<>(100);
    static ConcurrentHashMap<String, Integer> playerOHKill = new ConcurrentHashMap<>(100);


    public static int getPlayerOHKill(String playerName){
        return playerOHKill.get(playerName);
    }
    public static int getPlayerKill(String playerName){
        return playerKill.get(playerName);
    }

    public static int getPlayerFinalKill(String playerName){
        return playerFKill.get(playerName);
    }

    public static int getPlayerDeath(String playerName){
        return playerDeath.get(playerName);
    }
    public static int getPlayerBreakBed(String playerName){
        return playerBreakBed.get(playerName);
    }
    public static double getPlayerKD(String playerName){
        return playerKD.get(playerName);
    }

    public static void updatePlayerStat(String playerName, String pd, int value){
        
        if (playerName == null || pd == null){
            return;
        }

        switch (pd.toLowerCase()){
            case "k":
                int k = playerKill.get(playerName);
                playerKill.put(playerName, k + value);
                break;
            case "d":
                int d = playerDeath.get(playerName);
                playerDeath.put(playerName, d + value);
                break;
            case "b":
                int b = playerBreakBed.get(playerName);
                playerBreakBed.put(playerName, b + value);
                break;
            case "f":
                int f = playerFKill.get(playerName);
                playerFKill.put(playerName, f + value);
                break;
            case "ohk":
                int ohk = playerOHKill.getOrDefault(playerName, 0);
                playerOHKill.put(playerName, ohk + value);
                break;
            case "setohk":
                playerOHKill.put(playerName, value);
                break;
            default:
                le("RelCurrentStat","updatePlayerStat Error: pd : "
                        + pd + " is a wrong vaule!");
                break;
        }

        
        int kills = playerKill.getOrDefault(playerName, 0);
        int deaths = playerDeath.getOrDefault(playerName, 0);
        double kd = deaths != 0 ? kills / (double) deaths : kills;
        playerKD.put(playerName, kd);
    }

    public static void removePlayerStat(String playerName){
        
        playerKill.remove(playerName);
        playerFKill.remove(playerName);
        playerDeath.remove(playerName);
        playerBreakBed.remove(playerName);
        playerKD.remove(playerName);
        playerOHKill.remove(playerName);
    }

    public static void setDefaultPlayerStat(String playerName){
        
        playerKill.put(playerName, 0);
        playerFKill.put(playerName, 0);
        playerDeath.put(playerName, 0);
        playerBreakBed.put(playerName, 0);
        playerKD.put(playerName, 0.0);
        playerOHKill.put(playerName, 0);
    }



    static List<String> playerIsOut = new ArrayList<>(100);
    public static boolean PlayerisOut(String playerName){
        return playerIsOut.contains(playerName);
    }
    public static void addPlayerIsOut(String playerName){
        playerIsOut.add(playerName);
    }
    public static void removePlayerIsOut(String playerName){
        playerIsOut.remove(playerName);
    }

}
