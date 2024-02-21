package github.tsffish.bedwarskit.util.gametask;

import github.tsffish.bedwarskit.Main;
import github.tsffish.bedwarskit.config.main.MainConfigHandler;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameState;
import io.github.bedwarsrel.game.Team;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import static github.tsffish.bedwarskit.Main.isDebug;
import static github.tsffish.bedwarskit.config.main.MainConfigHandler.*;
import static github.tsffish.bedwarskit.util.RelScoreBoard.getAllTask;
import static github.tsffish.bedwarskit.util.RelScoreBoard.removeTask;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;
import static github.tsffish.bedwarskit.util.misc.MessSender.l;
import static io.github.bedwarsrel.com.v1_8_r3.ActionBar.sendActionBar;

public class TaskFinalBattle {
    private static final Main plugin = Main.getInstance();
    public static int taskTimeLeft;
    public static String  taskName;
    public static String gameName;
    public static int x;
    public static int gameTaskTime;

    static ConcurrentHashMap<String, Integer> taskTimeLeftMap = new ConcurrentHashMap<>(16);
    public static void setTaskTimeLeft(String gameName, int timeLeft) {
        if (isDebug()){
        l( "taskTimeLeftMap : " + gameName + " :" + timeLeft);
        }
        taskTimeLeftMap.put(gameName, timeLeft);
    }
    public static int getTaskTimeLeft(String gameName) {
        if (isDebug()){
        l("getTaskTimeLeft" + " :" + gameName);
        }
        return taskTimeLeftMap.getOrDefault(gameName, 0);
    }
    public static void runTask(Game game){
        gameName = game.getName();
        gameTaskTime = gametask_finalbattle_time;

        x = gameTaskTime;

        World world = game.getRegion().getWorld();
        WorldBorder worldBorder = world.getWorldBorder();
        worldBorder.setCenter(game.getLobby());

        new BukkitRunnable() {

            public void run() {

                if (x != 0){
                    x--;
                    setTaskTimeLeft(gameName, x);
                }

                if (x <= 0){

                    for (Map.Entry<Integer, String> entry : getAllTask().entrySet()) {
                        if (entry.getValue().equals(gametask_name_finalbattle)) {
                            removeTask(entry.getKey());
                            taskTimeLeftMap.remove(entry.getValue());
                        }
                    }
                    worldBorder.setSize(gametask_boundaries_size,0);
                worldBorder.setSize(gametask_finalbattle_boundaries_size,gametask_finalbattle_boundaries_time);
                worldBorder.setWarningDistance(gametask_finalbattle_boundaries_warnidis);
                worldBorder.setDamageAmount(gametask_finalbattle_boundaries_damage);
                worldBorder.setDamageBuffer(gametask_finalbattle_boundaries_damagebuffer);
                    if (game.getPlayers() != null){
                        for(Player p : game.getPlayers()) {
                            if (p != null && p.isOnline()){
                                if (!Objects.equals(MainConfigHandler.gametask_mess_finalbattle_chat, "")) {
                                    p.sendMessage(t(MainConfigHandler.gametask_mess_finalbattle_chat));
                                }
                                if (!Objects.equals(MainConfigHandler.gametask_mess_finalbattle_title, "")) {
                                    String titleReal = t(MainConfigHandler.gametask_mess_finalbattle_title);
                                    if (!Objects.equals(MainConfigHandler.gametask_mess_finalbattle_subtitle, "")) {
                                        String subtitleReal = t(MainConfigHandler.gametask_mess_finalbattle_subtitle);

                                        p.sendTitle(titleReal, subtitleReal);
                                    }
                                } else if (!Objects.equals(MainConfigHandler.gametask_mess_finalbattle_subtitle, "")) {
                                    String titleReal = " ";
                                    String subtitleReal = t(MainConfigHandler.gametask_mess_finalbattle_subtitle);

                                    p.sendTitle(titleReal, subtitleReal);
                                }
                                if (!Objects.equals(MainConfigHandler.gametask_mess_finalbattle_actionbar, "")) {
                                    sendActionBar(p, t(MainConfigHandler.gametask_mess_finalbattle_actionbar));
                                }
                            }
                        }
                    }

                    for (Team team :  game.getPlayingTeams()){
                        if (team.getHeadTarget() != null){
                        if (team.getHeadTarget().getType().toString().contains("BED")){
                            world.getBlockAt(team.getTargetHeadBlock()).setType(Material.AIR, true);
                        }
                    }

                        if (team.getFeetTarget() != null){
                            if (team.getFeetTarget().getType().toString().contains("BED")){
                                world.getBlockAt(team.getTargetFeetBlock()).setType(Material.AIR, true);
                            }
                        }

                    }

                    cancel();
                }

                if (game.getState() == null || game.getState() != GameState.RUNNING){
                    for (Map.Entry<Integer, String> entry : getAllTask().entrySet()) {
                        if (entry.getValue().equals(gametask_name_finalbattle)) {
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
