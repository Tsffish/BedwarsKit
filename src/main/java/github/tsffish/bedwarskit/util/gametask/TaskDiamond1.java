package github.tsffish.bedwarskit.util.gametask;

import github.tsffish.bedwarskit.Main;
import github.tsffish.bedwarskit.config.main.MainConfigHandler;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameState;
import io.github.bedwarsrel.game.ResourceSpawner;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static github.tsffish.bedwarskit.config.main.MainConfigHandler.gametask_name_diamond1;
import static github.tsffish.bedwarskit.util.RelScoreBoard.*;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;
import static io.github.bedwarsrel.com.v1_8_r3.ActionBar.sendActionBar;

public class TaskDiamond1 {
    private static final Plugin plugin = JavaPlugin.getPlugin(Main.class);
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
        gameTaskTime = MainConfigHandler.gametask_spawntime_tasks_diamond1;

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
                        if (entry.getValue().equals(gametask_name_diamond1)) {
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
                                    list.setInterval(MainConfigHandler.gametask_spawntime_diamond_1);
                                }
                            }
                        }
                    }
                }

                    if (game.getPlayers() != null){
                        for(Player p : game.getPlayers()) {
                            if (p != null && p.isOnline()){
                            if (!Objects.equals(MainConfigHandler.gametask_mess_diamond1_chat, "")) {
                                p.sendMessage(MainConfigHandler.gametask_mess_diamond1_chat);
                            }
                            if (!Objects.equals(MainConfigHandler.gametask_mess_diamond1_title, "")) {
                                String titleReal = t(MainConfigHandler.gametask_mess_diamond1_title);
                                if (!Objects.equals(MainConfigHandler.gametask_mess_diamond1_subtitle, "")) {
                                    String subtitleReal = t(MainConfigHandler.gametask_mess_diamond1_subtitle);

                                    p.sendTitle(titleReal, subtitleReal);
                                }
                            } else if (!Objects.equals(MainConfigHandler.gametask_mess_diamond1_subtitle, "")) {
                                String titleReal = " ";
                                String subtitleReal = t(MainConfigHandler.gametask_mess_diamond1_subtitle);

                                p.sendTitle(titleReal, subtitleReal);
                            }
                            if (!Objects.equals(MainConfigHandler.gametask_mess_diamond1_actionbar, "")) {
                                sendActionBar(p, t(MainConfigHandler.gametask_mess_diamond1_actionbar));
                            }
                            }
                        }
                    }
                    cancel();
                }

                if (game.getState() == null || game.getState() != GameState.RUNNING){
                    for (Map.Entry<Integer, String> entry : getAllTask().entrySet()) {
                        if (entry.getValue().equals(gametask_name_diamond1)) {
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
