package github.tsffish.bedwarskit.util.task;

import github.tsffish.bedwarskit.BedwarsKit;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameState;
import io.github.bedwarsrel.game.Team;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

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
public class TaskFinalBattle {
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
        int gameTaskTime = gametask_finalbattle_time;

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
        worldBorder.setSize(gametask_boundaries_size, 0);
        worldBorder.setWarningDistance(gametask_finalbattle_boundaries_warnidis);
        worldBorder.setDamageAmount(gametask_finalbattle_boundaries_damage);
        worldBorder.setDamageBuffer(gametask_finalbattle_boundaries_damagebuffer);
        worldBorder.setSize(gametask_finalbattle_boundaries_size, gametask_finalbattle_boundaries_time);
        for (Team team : game.getTeams().values()) {
            Location loc = team.getTargetHeadBlock();
            loc.getBlock().setType(Material.AIR);
        }
    }


    private static void notifyPlayers(Game game) {
        game.getPlayers().stream()
                .filter(Objects::nonNull)
                .filter(Player::isOnline)
                .forEach(player -> {
                    if (!Objects.equals(gametask_mess_finalbattle_chat, "")) {
                        sendMessage(player, gametask_mess_finalbattle_chat);
                    }
                    if (!Objects.equals(gametask_mess_finalbattle_title, "")) {
                        String titleReal = gametask_mess_finalbattle_title;
                        if (!Objects.equals(gametask_mess_finalbattle_subtitle, "")) {
                            String subtitleReal = gametask_mess_finalbattle_subtitle;

                            sendTitle(player, titleReal, subtitleReal);
                        }
                    } else if (!Objects.equals(gametask_mess_finalbattle_subtitle, "")) {
                        String titleReal = " ";
                        String subtitleReal = gametask_mess_finalbattle_subtitle;

                        sendTitle(player, titleReal, subtitleReal);
                    }
                    if (!Objects.equals(gametask_mess_finalbattle_actionbar, "")) {
                        sendActionBar(player, gametask_mess_finalbattle_actionbar);
                    }
                });
    }
}
