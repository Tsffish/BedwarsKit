package github.tsffish.bedwarskit.util.gametask;

import github.tsffish.bedwarskit.config.main.MainConfigHandler;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameState;
import io.github.bedwarsrel.game.ResourceSpawner;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static github.tsffish.bedwarskit.config.main.MainConfigHandler.gametask_name_diamond2;
import static github.tsffish.bedwarskit.util.RelScoreBoard.*;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;
import static io.github.bedwarsrel.com.v1_8_r3.ActionBar.sendActionBar;

public class TaskDiamond2 {
    private static Plugin plugin = github.tsffish.bedwarskit.Main.getPlugin(github.tsffish.bedwarskit.Main.class);
    public static int taskTimeLeft;
    public static String  taskName;
    public static String gameName;
    public static int x;
    public static int gameTaskTime;
    static Map<String, Integer> taskTimeLeftMap = new HashMap<>(4);
    public static void setTaskTimeLeft(String gameName, int timeLeft) {
        taskTimeLeftMap.put(gameName, timeLeft);
    }
    public static int getTaskTimeLeft(String gameName) {
        return taskTimeLeftMap.getOrDefault(gameName, 0);
    }

    public static void runTask(Game game){
        gameName = game.getName();
        gameTaskTime = MainConfigHandler.gametask_spawntime_tasks_diamond2;

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

                    for (Map.Entry<Integer, String> entry : getAllTask().entrySet()) {
                        if (entry.getValue().equals(gametask_name_diamond2)) {
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
                                    if (itemType == Material.DIAMOND) {
                                        list.setInterval(MainConfigHandler.gametask_spawntime_diamond_2);
                                    }
                                }
                            }
                        }
                    }

                    if (game.getPlayers() != null){
                        for(Player p : game.getPlayers()) {
                            if (p != null && p.isOnline()){
                                if (!Objects.equals(MainConfigHandler.gametask_mess_diamond2_chat, "")) {
                                    p.sendMessage(MainConfigHandler.gametask_mess_diamond2_chat);
                                }
                                if (!Objects.equals(MainConfigHandler.gametask_mess_diamond2_title, "")) {
                                    String titleReal = t(MainConfigHandler.gametask_mess_diamond2_title);
                                    if (!Objects.equals(MainConfigHandler.gametask_mess_diamond2_subtitle, "")) {
                                        String subtitleReal = t(MainConfigHandler.gametask_mess_diamond2_subtitle);

                                        p.sendTitle(titleReal, subtitleReal);
                                    }
                                } else if (!Objects.equals(MainConfigHandler.gametask_mess_diamond2_subtitle, "")) {
                                    String titleReal = " ";
                                    String subtitleReal = t(MainConfigHandler.gametask_mess_diamond2_subtitle);

                                    p.sendTitle(titleReal, subtitleReal);
                                }
                                if (!Objects.equals(MainConfigHandler.gametask_mess_diamond2_actionbar, "")) {
                                    sendActionBar(p, t(MainConfigHandler.gametask_mess_diamond2_actionbar));
                                }
                            }
                        }
                    }
                    cancel();
                }

                if (game.getState() == null || game.getState() != GameState.RUNNING){
                    for (Map.Entry<Integer, String> entry : getAllTask().entrySet()) {
                        if (entry.getValue().equals(gametask_name_diamond2)) {
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
