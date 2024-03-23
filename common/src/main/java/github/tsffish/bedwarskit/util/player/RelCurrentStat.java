package github.tsffish.bedwarskit.util.player;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static github.tsffish.bedwarskit.config.lang.LangConfigHandler.vauleIsWrong;
import static github.tsffish.bedwarskit.util.misc.MessSender.le;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class RelCurrentStat {
    public static final String addKill = "k";
    public static final String addDeath = "d";
    public static final String addBreakBed = "b";
    public static final String addFinalKill = "f";
    public static final String addOneHeathKill = "ohk";
    public static final String setOneHeathKill = "setohk";
    private static final String className = RelCurrentStat.class.getSimpleName();
    private static ConcurrentHashMap<UUID, Integer> playerKill;
    private static ConcurrentHashMap<UUID, Integer> playerFKill;
    private static ConcurrentHashMap<UUID, Integer> playerDeath;
    private static ConcurrentHashMap<UUID, Integer> playerBreakBed;
    private static ConcurrentHashMap<UUID, Double> playerKD;
    private static ConcurrentHashMap<UUID, Integer> playerOHKill;
    private static Set<UUID> playerIsOut;

    static {
        playerKill = new ConcurrentHashMap<>(101);
        playerFKill = new ConcurrentHashMap<>(101);
        playerDeath = new ConcurrentHashMap<>(101);
        playerBreakBed = new ConcurrentHashMap<>(101);
        playerKD = new ConcurrentHashMap<>(101);
        playerOHKill = new ConcurrentHashMap<>(101);
        playerIsOut = Collections.newSetFromMap(new ConcurrentHashMap<>());
    }

    public static int getPlayerOHKill(UUID uuid) {
        return playerOHKill.get(uuid);
    }

    public static int getPlayerKill(UUID uuid) {
        return playerKill.get(uuid);
    }

    public static int getPlayerFinalKill(UUID uuid) {
        return playerFKill.get(uuid);
    }

    public static int getPlayerDeath(UUID uuid) {
        return playerDeath.get(uuid);
    }

    public static int getPlayerBreakBed(UUID uuid) {
        return playerBreakBed.get(uuid);
    }

    public static double getPlayerKD(UUID uuid) {
        return playerKD.get(uuid);
    }

    public static boolean getPlayerisOut(UUID uuid) {
        return playerIsOut.contains(uuid);
    }

    public static void addPlayerIsOut(UUID uuid) {
        playerIsOut.add(uuid);
    }

    public static void removePlayerIsOut(UUID uuid) {
        playerIsOut.remove(uuid);
    }

    public static void updatePlayerStat(UUID uuid, String pd, int value) {

        switch (pd.toLowerCase()) {
            case addKill:
                int k = playerKill.get(uuid);
                playerKill.put(uuid, k + value);
                break;
            case addDeath:
                int d = playerDeath.get(uuid);
                playerDeath.put(uuid, d + value);
                break;
            case addBreakBed:
                int b = playerBreakBed.get(uuid);
                playerBreakBed.put(uuid, b + value);
                break;
            case addFinalKill:
                int f = playerFKill.get(uuid);
                playerFKill.put(uuid, f + value);
                break;
            case addOneHeathKill:
                int ohk = playerOHKill.getOrDefault(uuid, 0);
                playerOHKill.put(uuid, ohk + value);
                break;
            case setOneHeathKill:
                playerOHKill.put(uuid, value);
                break;
            default:
                sendError("updatePlayerStat", pd);
                break;
        }


        int kills = playerKill.getOrDefault(uuid, 0);
        int deaths = playerDeath.getOrDefault(uuid, 0);
        double kd = deaths != 0 ? kills / (double) deaths : kills;
        playerKD.put(uuid, kd);
    }

    public static void removePlayerStat(UUID uuid) {

        playerKill.remove(uuid);
        playerFKill.remove(uuid);
        playerDeath.remove(uuid);
        playerBreakBed.remove(uuid);
        playerKD.remove(uuid);
        playerOHKill.remove(uuid);
    }

    public static void setDefaultPlayerStat(UUID uuid) {

        playerKill.put(uuid, 0);
        playerFKill.put(uuid, 0);
        playerDeath.put(uuid, 0);
        playerBreakBed.put(uuid, 0);
        playerKD.put(uuid, 0.0);
        playerOHKill.put(uuid, 0);
    }

    static void sendError(String method, String pd) {
        le(className, method + " error: pd : "
                + pd + vauleIsWrong);
    }

}
