package github.tsffish.bedwarskit.util.gametask;

import github.tsffish.bedwarskit.Main;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameState;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static github.tsffish.bedwarskit.config.task.TaskConfigHandler.*;
import static github.tsffish.bedwarskit.util.misc.SendActionBar.sendActionBar;

public class FinalBattle {
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
        int gameTaskTime = gametask_spawntime_tasks_diamond1;

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
        World world = game.getRegion().getWorld();
        WorldBorder worldBorder = world.getWorldBorder();
        worldBorder.setCenter(game.getLobby());
        worldBorder.setSize(gametask_boundaries_size,0);
        worldBorder.setSize(gametask_finalbattle_boundaries_size,gametask_finalbattle_boundaries_time);
        worldBorder.setWarningDistance(gametask_finalbattle_boundaries_warnidis);
        worldBorder.setDamageAmount(gametask_finalbattle_boundaries_damage);
        worldBorder.setDamageBuffer(gametask_finalbattle_boundaries_damagebuffer);
    }


    private static void notifyPlayers(Game game) {
        game.getPlayers().stream()
                .filter(Objects::nonNull)
                .filter(Player::isOnline)
                .forEach(player -> {
                    String message = gametask_mess_finalbattle_chat;
                    if (message != null && !message.isEmpty()) {
                        player.sendMessage(message);
                    }

                    String title = Optional.ofNullable(gametask_mess_finalbattle_title).orElse(" ");
                    String subtitle = Optional.ofNullable(gametask_mess_finalbattle_subtitle).orElse("");

                    player.sendTitle(title, subtitle);

                    Optional.ofNullable(gametask_mess_finalbattle_actionbar)
                            .filter(actionBarMessage -> !actionBarMessage.isEmpty())
                            .ifPresent(actionBarMessage -> sendActionBar(player, actionBarMessage));
                });
    }
}
