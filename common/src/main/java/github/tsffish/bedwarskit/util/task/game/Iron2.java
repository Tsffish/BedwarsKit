package github.tsffish.bedwarskit.util.task.game;

import github.tsffish.bedwarskit.BedwarsKit;
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
import static github.tsffish.bedwarskit.util.misc.PlayerSender.sendMessage;
import static github.tsffish.bedwarskit.util.misc.PlayerSender.sendTitle;

public class Iron2 {
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
        int gameTaskTime = gametask_spawntime_tasks_iron2;

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
                        .filter(item -> item != null && item.getType() == Material.IRON_INGOT)
                        .findFirst()
                        .ifPresent(item -> spawner.setInterval(gametask_spawntime_iron_base)));
    }


    private static void notifyPlayers(Game game) {
        game.getPlayers().stream()
                .filter(Objects::nonNull)
                .filter(Player::isOnline)
                .forEach(player -> {
                    String message = gametask_mess_iron2_chat;
                    if (message != null && !message.isEmpty()) {
                        sendMessage(player,message);
                    }

                    String title = Optional.ofNullable(gametask_mess_iron2_title).orElse(" ");
                    String subtitle = Optional.ofNullable(gametask_mess_iron2_subtitle).orElse("");

                    sendTitle(player, title, subtitle);

                    Optional.ofNullable(gametask_mess_iron2_actionbar)
                            .filter(actionBarMessage -> !actionBarMessage.isEmpty())
                            .ifPresent(actionBarMessage -> sendActionBar(player, actionBarMessage));
                });
    }
}
