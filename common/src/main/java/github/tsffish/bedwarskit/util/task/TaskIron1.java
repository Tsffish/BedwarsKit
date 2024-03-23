package github.tsffish.bedwarskit.util.task;

import github.tsffish.bedwarskit.BedwarsKit;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameState;
import io.github.bedwarsrel.game.ResourceSpawner;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import static github.tsffish.bedwarskit.config.task.TaskConfigHandler.*;
import static github.tsffish.bedwarskit.util.player.PlayerSender.sendMessage;
import static github.tsffish.bedwarskit.util.player.PlayerSender.sendTitle;
import static github.tsffish.bedwarskit.util.player.SendActionBar.sendActionBar;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class TaskIron1 {
    private static final BedwarsKit plugin = BedwarsKit.getInstance();
    private static ConcurrentHashMap<String, Integer> taskTimeLeftMap = new ConcurrentHashMap<>();

    public static void setTaskTimeLeft(String gameName, int timeLeft) {
        taskTimeLeftMap.put(gameName, timeLeft);
    }

    public static int getTaskTimeLeft(String gameName) {
        return taskTimeLeftMap.getOrDefault(gameName, 0);
    }

    public static void runTask(Game game) {
        String gameName = game.getName();
        int gameTaskTime = gametask_spawntime_tasks_iron1;

        new BukkitRunnable() {
            int taskTimeLeft = gameTaskTime;

            @Override
            public void run() {

                if (game.getState() != GameState.RUNNING) {
                    cancel();
                }

                if (taskTimeLeft > 0) {
                    taskTimeLeft--;
                    setTaskTimeLeft(gameName, taskTimeLeft);
                } else {
                    executeTask(game);
                    notifyPlayers(game);
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 0L, 20L);
    }

    private static void executeTask(Game game) {
        List<ResourceSpawner> resourceSpawners = game.getResourceSpawners();
        resourceSpawners.stream()
                .filter(Objects::nonNull)
                .forEach(spawner -> spawner.getResources().stream()
                        .filter(item -> item != null && item.getType() == Material.IRON_INGOT)
                        .findFirst()
                        .ifPresent(item -> spawner.setInterval(gametask_spawntime_iron_1)));
    }


    private static void notifyPlayers(Game game) {
        game.getPlayers().stream()
                .filter(Objects::nonNull)
                .filter(Player::isOnline)
                .forEach(player -> {
                    if (!Objects.equals(gametask_mess_iron1_chat, "")) {
                        sendMessage(player, gametask_mess_iron1_chat);
                    }
                    if (!Objects.equals(gametask_mess_iron1_title, "")) {
                        String titleReal = gametask_mess_iron1_title;
                        if (!Objects.equals(gametask_mess_iron1_subtitle, "")) {
                            String subtitleReal = gametask_mess_iron1_subtitle;

                            sendTitle(player, titleReal, subtitleReal);
                        }
                    } else if (!Objects.equals(gametask_mess_iron1_subtitle, "")) {
                        String titleReal = " ";
                        String subtitleReal = gametask_mess_iron1_subtitle;

                        sendTitle(player, titleReal, subtitleReal);
                    }
                    if (!Objects.equals(gametask_mess_iron1_actionbar, "")) {
                        sendActionBar(player, gametask_mess_iron1_actionbar);
                    }
                });
    }
}
