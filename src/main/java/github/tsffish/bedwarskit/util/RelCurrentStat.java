package github.tsffish.bedwarskit.util;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static github.tsffish.bedwarskit.util.misc.ErrorLogger.le;

public class RelCurrentStat {
    public static Map<Player, Integer> playerKill = new HashMap<>(24);
    public static Map<Player, Integer> playerFKill = new HashMap<>(24);
    public static Map<Player, Integer> playerDeath = new HashMap<>(24);
    public static Map<Player, Integer> playerBreakBed = new HashMap<>(24);
    public static Map<Player, Double> playerKD = new HashMap<>(24);

    public static void ups(Player player, String pd, int value){
        
        if (player == null || pd == null){
            return;
        }

        switch (pd.toLowerCase()){
            case "k":
                int k = playerKill.get(player);
                playerKill.put(player, k + value);
                break;
            case "d":
                int d = playerDeath.get(player);
                playerDeath.put(player, d + value);
                break;
            case "b":
                int b = playerBreakBed.get(player);
                playerBreakBed.put(player, b + value);
                break;
            case "f":
                int f = playerFKill.get(player);
                playerFKill.put(player, f + value);
                break;
            default:
                le("RelCurrentStat.ups error: pd : " + pd + " is a wrong vaule!");
                break;
        }

        
        int kills = playerKill.getOrDefault(player, 0);
        int deaths = playerDeath.getOrDefault(player, 0);
        double kd = deaths != 0 ? kills / (double) deaths : kills;
        playerKD.put(player, kd);
    }

    public static void rps(Player player){
        
        playerKill.remove(player);
        playerFKill.remove(player);
        playerDeath.remove(player);
        playerBreakBed.remove(player);
        playerKD.remove(player);
    }

    public static void sdps(Player player){
        
        playerKill.put(player, 0);
        playerFKill.put(player, 0);
        playerDeath.put(player, 0);
        playerBreakBed.put(player, 0);
        playerKD.put(player, 0.0);
    }



    public static List<Player> playerIsOut = new ArrayList<>(24);


}
