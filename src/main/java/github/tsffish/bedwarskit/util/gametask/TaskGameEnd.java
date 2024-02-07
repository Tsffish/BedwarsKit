package github.tsffish.bedwarskit.util.gametask;

import github.tsffish.bedwarskit.Main;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameState;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

import static github.tsffish.bedwarskit.config.MainConfigHandler.gametask_name_finalbattle;
import static github.tsffish.bedwarskit.util.RelScoreBoard.taskMap;

public class TaskGameEnd {
    static Plugin plugin = JavaPlugin.getPlugin(Main.class);
    public static int taskTimeLeft;
    public static String  taskName;
    public static String gameName;
    public static int x;
    public static int gameTaskTime;
    public static Map<String, Integer> taskTimeLeftMap = new HashMap<>(4);
    public static void setTaskTimeLeft(String gameName, int timeLeft) {
        taskTimeLeftMap.put(gameName, timeLeft);
    }
    public static int getTaskTimeLeft(String gameName) {
        return taskTimeLeftMap.getOrDefault(gameName, 0);
    }
    public static void runTask(Game game){
        gameName = game.getName();
        gameTaskTime = game.getTime();

        long startTime = System.currentTimeMillis();
        taskTimeLeft = gameTaskTime - (int) ((System.currentTimeMillis() - startTime) / 1000);

        x = gameTaskTime;

        new BukkitRunnable() {

            public void run() {

                if (x != 0){
                    x--;
                    setTaskTimeLeft(gameName, x);
                }

                if (x <= 0){
                    cancel();
                }

                if (game.getState() == null || game.getState() != GameState.RUNNING){
                    cancel();
                }

            }
        }.runTaskTimer(plugin,0L, 20L);
    }
}
