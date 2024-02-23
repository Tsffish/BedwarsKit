package github.tsffish.bedwarskit.util.gametask;

import github.tsffish.bedwarskit.Main;
import github.tsffish.bedwarskit.config.task.TaskConfigHandler;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameState;
import io.github.bedwarsrel.game.ResourceSpawner;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import static github.tsffish.bedwarskit.config.task.TaskConfigHandler.gametask_name_gold1;
import static github.tsffish.bedwarskit.util.RelScoreBoard.getAllTask;
import static github.tsffish.bedwarskit.util.RelScoreBoard.removeTask;
import static github.tsffish.bedwarskit.util.player.SendActionBar.sendActionBar;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;

public class TaskGold1 {
    private static final Main plugin = Main.getInstance();
    public static int taskTimeLeft;
    public static String  taskName;
    public static String gameName;
    public static int x;
    public static int gameTaskTime;
    static ConcurrentHashMap<String, Integer> taskTimeLeftMap = new ConcurrentHashMap<>(16);
    public static void setTaskTimeLeft(String gameName, int timeLeft) {
        taskTimeLeftMap.put(gameName, timeLeft);
    }
    public static int getTaskTimeLeft(String gameName) {
        return taskTimeLeftMap.getOrDefault(gameName, 0);
    }

    public static void runTask(Game game){
        gameName = game.getName();
        gameTaskTime = TaskConfigHandler.gametask_spawntime_tasks_gold1;

        x = gameTaskTime;

        new BukkitRunnable() {

            public void run() {

                if (x != 0){
                    x--;
                    setTaskTimeLeft(gameName, x);
                }

                if (x <= 0){

                    for (Map.Entry<Integer, String> entry : getAllTask().entrySet()) {
                        if (entry.getValue().equals(gametask_name_gold1)) {
                            removeTask(entry.getKey());
                            taskTimeLeftMap.remove(entry.getValue());
                        }
                    }

                List<ResourceSpawner> resourceSpawner = game.getResourceSpawners();
                for (ResourceSpawner list : resourceSpawner) {
                    if (list != null) {
                        for (ItemStack item : list.getResources()) {
                            if (item != null) {
                                Material itemType = item.getType();
                                if (itemType == Material.GOLD_INGOT) {
                                    list.setInterval(TaskConfigHandler.gametask_spawntime_gold_1);
                                }
                            }
                        }
                    }
                }

                    if (game.getPlayers() != null){
                        for(Player p : game.getPlayers()) {
                            if (p != null && p.isOnline()){
                            if (!Objects.equals(TaskConfigHandler.gametask_mess_gold1_chat, "")) {
                                p.sendMessage(TaskConfigHandler.gametask_mess_gold1_chat);
                            }
                            if (!Objects.equals(TaskConfigHandler.gametask_mess_gold1_title, "")) {
                                String titleReal = t(TaskConfigHandler.gametask_mess_gold1_title);
                                if (!Objects.equals(TaskConfigHandler.gametask_mess_gold1_subtitle, "")) {
                                    String subtitleReal = t(TaskConfigHandler.gametask_mess_gold1_subtitle);

                                    p.sendTitle(titleReal, subtitleReal);
                                }
                            } else if (!Objects.equals(TaskConfigHandler.gametask_mess_gold1_subtitle, "")) {
                                String titleReal = " ";
                                String subtitleReal = t(TaskConfigHandler.gametask_mess_gold1_subtitle);

                                p.sendTitle(titleReal, subtitleReal);
                            }
                            if (!Objects.equals(TaskConfigHandler.gametask_mess_gold1_actionbar, "")) {
                                sendActionBar(p, t(TaskConfigHandler.gametask_mess_gold1_actionbar));
                            }
                            }
                        }
                    }
                    cancel();
                }

                if (game.getState() == null || game.getState() != GameState.RUNNING){
                    for (Map.Entry<Integer, String> entry : getAllTask().entrySet()) {
                        if (entry.getValue().equals(gametask_name_gold1)) {
                            removeTask(entry.getKey());
                            taskTimeLeftMap.remove(entry.getValue());
                        }
                    }
                    cancel();
                }

            }
        }.runTaskTimer(plugin,0L, 20L);
    }
}
