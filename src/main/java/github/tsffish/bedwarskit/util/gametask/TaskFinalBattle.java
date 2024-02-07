package github.tsffish.bedwarskit.util.gametask;

import github.tsffish.bedwarskit.Main;
import github.tsffish.bedwarskit.config.MainConfigHandler;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameState;
import io.github.bedwarsrel.game.Team;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static github.tsffish.bedwarskit.config.MainConfigHandler.*;
import static github.tsffish.bedwarskit.util.RelScoreBoard.taskMap;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;
import static io.github.bedwarsrel.com.v1_8_r3.ActionBar.sendActionBar;

public class TaskFinalBattle {
    static Plugin plugin = JavaPlugin.getPlugin(Main.class);
    public static int taskTimeLeft;
    public static String  taskName;
    public static String gameName;
    public static int x;
    public static int gameTaskTime;

    public static Map<String, Integer> taskTimeLeftMap = new HashMap<>(4);
    public static void setTaskTimeLeft(String gameName, int timeLeft) {
        if (Main.isDebug){
        Bukkit.getLogger().info( "taskTimeLeftMap : " + gameName + " :" + timeLeft);
        }
        taskTimeLeftMap.put(gameName, timeLeft);
    }
    public static int getTaskTimeLeft(String gameName) {
        if (Main.isDebug){
        Bukkit.getLogger().info("getTaskTimeLeft" + " :" + gameName);
        }
        return taskTimeLeftMap.getOrDefault(gameName, 0);
    }
    public static void runTask(Game game){
        gameName = game.getName();
        gameTaskTime = gametask_finalbattle_time;

        long startTime = System.currentTimeMillis();
        taskTimeLeft = gameTaskTime - (int) ((System.currentTimeMillis() - startTime) / 1000);

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

                    for (Map.Entry<Integer, String> entry : taskMap.entrySet()) {
                        if (entry.getValue().equals(gametask_name_finalbattle)) {
                            taskMap.remove(entry.getKey());
                        }
                    }

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
                            world.getBlockAt(team.getTargetHeadBlock()).setType(Material.AIR);
                        }
                    }

                        if (team.getFeetTarget() != null){
                            if (team.getFeetTarget().getType().toString().contains("BED")){
                                world.getBlockAt(team.getTargetFeetBlock()).setType(Material.AIR);
                            }
                        }

                    }

                    cancel();
                }

                if (game.getState() == null || game.getState() != GameState.RUNNING){
                    for (Map.Entry<Integer, String> entry : taskMap.entrySet()) {
                        if (entry.getValue().equals(gametask_name_finalbattle)) {
                            taskMap.remove(entry.getKey());
                        }
                    }
                    cancel();
                }

            }
        }.runTaskTimer(plugin,0L, 20L);
    }
}
