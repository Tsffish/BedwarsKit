package github.tsffish.bedwarskit.util.gametask;

import github.tsffish.bedwarskit.Main;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameState;
import io.github.bedwarsrel.game.ResourceSpawner;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static github.tsffish.bedwarskit.config.task.TaskConfigHandler.*;
import static github.tsffish.bedwarskit.util.misc.SendActionBar.sendActionBar;

public class Diamond4 {
    private static final Main plugin = Main.getInstance();
    private static ConcurrentHashMap<String, Integer> taskTimeLeftMap = new ConcurrentHashMap<>();

    public static void setTaskTimeLeft(String gameName, int timeLeft) {
        taskTimeLeftMap.put(gameName, timeLeft);
    }

    public static int getTaskTimeLeft(String gameName) {
        return taskTimeLeftMap.getOrDefault(gameName, 0);
    }

    public static void runTask(Game game) {
        String gameName = game.getName();
        int gameTaskTime = gametask_spawntime_tasks_diamond4;

        new BukkitRunnable() {
            int taskTimeLeft = gameTaskTime;

            @Override
            public void run() {
                if (taskTimeLeft > 0) {
                    taskTimeLeft--;
                    setTaskTimeLeft(gameName, taskTimeLeft);
                } else {
                    executeTask(game);
                    notifyPlayers(game);
                    cancel();
                }

                if (game.getState() != GameState.RUNNING) {
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
                        .filter(item -> item != null && item.getType() == Material.DIAMOND)
                        .findFirst()
                        .ifPresent(item -> spawner.setInterval(gametask_spawntime_diamond_base)));
    }


    private static void notifyPlayers(Game game) {
        game.getPlayers().stream()
                .filter(Objects::nonNull)
                .filter(Player::isOnline)
                .forEach(player -> {
                    String message = gametask_mess_diamond4_chat;
                    if (message != null && !message.isEmpty()) {
                        player.sendMessage(message);
                    }

                    String title = Optional.ofNullable(gametask_mess_diamond4_title).orElse(" ");
                    String subtitle = Optional.ofNullable(gametask_mess_diamond4_subtitle).orElse("");

                    player.sendTitle(title, subtitle);

                    Optional.ofNullable(gametask_mess_diamond4_actionbar)
                            .filter(actionBarMessage -> !actionBarMessage.isEmpty())
                            .ifPresent(actionBarMessage -> sendActionBar(player, actionBarMessage));
                });
    }
}
