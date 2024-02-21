package github.tsffish.bedwarskit.util.gametask;

import github.tsffish.bedwarskit.Main;
import github.tsffish.bedwarskit.config.main.MainConfigHandler;
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

import static github.tsffish.bedwarskit.config.main.MainConfigHandler.gametask_name_iron2;
import static github.tsffish.bedwarskit.util.RelScoreBoard.getAllTask;
import static github.tsffish.bedwarskit.util.RelScoreBoard.removeTask;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;
import static io.github.bedwarsrel.com.v1_8_r3.ActionBar.sendActionBar;

public class TaskIron2 {
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
        gameTaskTime = MainConfigHandler.gametask_spawntime_tasks_iron2;

        x = gameTaskTime;

        new BukkitRunnable() {

            public void run() {

                if (x != 0){
                    x--;
                    setTaskTimeLeft(gameName, x);
                }

                if (x <= 0){

                    for (Map.Entry<Integer, String> entry : getAllTask().entrySet()) {
                        if (entry.getValue().equals(gametask_name_iron2)) {
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
                                    if (itemType == Material.IRON_INGOT) {
                                        list.setInterval(MainConfigHandler.gametask_spawntime_iron_2);
                                    }
                                }
                            }
                        }
                    }

                    if (game.getPlayers() != null){
                        for(Player p : game.getPlayers()) {
                            if (p != null && p.isOnline()){
                                if (!Objects.equals(MainConfigHandler.gametask_mess_iron2_chat, "")) {
                                    p.sendMessage(MainConfigHandler.gametask_mess_iron2_chat);
                                }
                                if (!Objects.equals(MainConfigHandler.gametask_mess_iron2_title, "")) {
                                    String titleReal = t(MainConfigHandler.gametask_mess_iron2_title);
                                    if (!Objects.equals(MainConfigHandler.gametask_mess_iron2_subtitle, "")) {
                                        String subtitleReal = t(MainConfigHandler.gametask_mess_iron2_subtitle);

                                        p.sendTitle(titleReal, subtitleReal);
                                    }
                                } else if (!Objects.equals(MainConfigHandler.gametask_mess_iron2_subtitle, "")) {
                                    String titleReal = " ";
                                    String subtitleReal = t(MainConfigHandler.gametask_mess_iron2_subtitle);

                                    p.sendTitle(titleReal, subtitleReal);
                                }
                                if (!Objects.equals(MainConfigHandler.gametask_mess_iron2_actionbar, "")) {
                                    sendActionBar(p, t(MainConfigHandler.gametask_mess_iron2_actionbar));
                                }
                            }
                        }
                    }
                    cancel();
                }

                if (game.getState() == null || game.getState() != GameState.RUNNING){
                    for (Map.Entry<Integer, String> entry : getAllTask().entrySet()) {
                        if (entry.getValue().equals(gametask_name_iron2)) {
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
