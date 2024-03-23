package github.tsffish.bedwarskit.listener;

import github.tsffish.bedwarskit.BedwarsKit;
import github.tsffish.bedwarskit.config.kit.KitConfigHandler;
import github.tsffish.bedwarskit.util.player.SoundPlayer;
import github.tsffish.bedwarskit.util.task.*;
import github.tsffish.bedwarskit.util.teamshop.haste.ListHaste;
import github.tsffish.bedwarskit.util.teamshop.heal.ListHeal;
import github.tsffish.bedwarskit.util.teamshop.prot.ListProt;
import github.tsffish.bedwarskit.util.teamshop.sharp.ListSharp;
import io.github.bedwarsrel.events.BedwarsGameStartedEvent;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameState;
import io.github.bedwarsrel.game.ResourceSpawner;
import io.github.bedwarsrel.game.Team;
import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.*;

import static github.tsffish.bedwarskit.config.main.MainConfigHandler.*;
import static github.tsffish.bedwarskit.config.task.TaskConfigHandler.*;
import static github.tsffish.bedwarskit.util.DisableAI.disableAI;
import static github.tsffish.bedwarskit.util.PluginState.isDebug;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;
import static github.tsffish.bedwarskit.util.misc.MessSender.le;
import static github.tsffish.bedwarskit.util.player.PlayerSender.sendMessage;
import static github.tsffish.bedwarskit.util.player.PlayerSender.sendTitle;
import static github.tsffish.bedwarskit.util.player.RelArmorList.*;
import static github.tsffish.bedwarskit.util.player.RelCurrentStat.removePlayerIsOut;
import static github.tsffish.bedwarskit.util.player.RelCurrentStat.setDefaultPlayerStat;
import static github.tsffish.bedwarskit.util.player.RelPlayerIsRespawn.removePlayerRespawn;
import static github.tsffish.bedwarskit.util.player.RelPlayerKit.applykit;
import static github.tsffish.bedwarskit.util.player.RelPlayerKit.applykitforce;
import static github.tsffish.bedwarskit.util.player.SendActionBar.sendActionBar;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class RelGameStarted implements Listener {
    private static final String className = RelGameStarted.class.getName();
    private static final BedwarsKit plugin = BedwarsKit.getInstance();
    private static final ItemStack compass = new ItemStack(Material.COMPASS);
    private static final Difficulty peaceful = Difficulty.PEACEFUL;
    private static final Difficulty normal = Difficulty.NORMAL;
    private static final String doWeatherCycle = "doWeatherCycle";
    private static final String meanfalse = "false";
    private static final int maxSize = 9999;
    private static final DisplaySlot sidebar = DisplaySlot.SIDEBAR;
    private static final GameState running = GameState.RUNNING;

    @EventHandler
    public void on(final BedwarsGameStartedEvent event) {

        new BukkitRunnable() {
            public void run() {
                Game game = event.getGame();

                if (game == null) {
                    return;
                }
                if (game.getState() != running) {
                    return;
                }

                World world = game.getRegion().getWorld();
                world.setThundering(false);
                world.setWeatherDuration(0);
                world.setGameRuleValue(doWeatherCycle, meanfalse);

                for (Entity entity : world.getEntities()) {
                    if (entity instanceof ArmorStand) {
                        entity.remove();
                    }
                }

                WorldBorder worldBorder = world.getWorldBorder();
                worldBorder.setCenter(game.getLobby());
                worldBorder.setSize(maxSize);

                String worldName = world.getName();
                String gameName = game.getName();

                Map<String, Team> teamMap = game.getTeams();

                if (teamMap != null && !teamMap.isEmpty()) {
                    List<Team> teamList = new ArrayList<>(teamMap.values());

                    ListHaste.setTeamDatasDefault(gameName, teamList);
                    ListHeal.setTeamDatasDefault(gameName, teamList);
                    ListSharp.setTeamDatasDefault(gameName, teamList);
                    ListProt.setTeamDatasDefault(gameName, teamList);
                } else {
                    if (isDebug()) {
                        le(className, "teamMap is null or empty.");
                    }
                }

                if (gametask) {
                    if (gametask_spawntime) {

                        List<ResourceSpawner> resourceSpawners = game.getResourceSpawners();
                        resourceSpawners.forEach(spawner -> spawner.getResources().stream()
                                .filter(Objects::nonNull)
                                .findFirst()
                                .ifPresent(item -> {
                                    if (item.getType() == Material.IRON_INGOT) {
                                        spawner.setInterval(gametask_spawntime_iron_base);
                                    } else if (item.getType() == Material.GOLD_INGOT) {
                                        spawner.setInterval(gametask_spawntime_gold_base);
                                    } else if (item.getType() == Material.DIAMOND) {
                                        spawner.setInterval(gametask_spawntime_diamond_base);
                                    } else if (item.getType() == Material.EMERALD) {
                                        spawner.setInterval(gametask_spawntime_emerald_base);
                                    }
                                }));

                        if (gametask_spawntime_tasks_iron1 > 0) TaskIron1.runTask(game);
                        if (gametask_spawntime_tasks_iron2 > 0) TaskIron2.runTask(game);
                        if (gametask_spawntime_tasks_iron3 > 0) TaskIron3.runTask(game);
                        if (gametask_spawntime_tasks_iron4 > 0) TaskIron4.runTask(game);

                        if (gametask_spawntime_tasks_gold1 > 0) TaskGold1.runTask(game);
                        if (gametask_spawntime_tasks_gold2 > 0) TaskGold2.runTask(game);
                        if (gametask_spawntime_tasks_gold3 > 0) TaskGold3.runTask(game);
                        if (gametask_spawntime_tasks_gold4 > 0) TaskGold4.runTask(game);

                        if (gametask_spawntime_tasks_diamond1 > 0) TaskDiamond1.runTask(game);
                        if (gametask_spawntime_tasks_diamond2 > 0) TaskDiamond2.runTask(game);
                        if (gametask_spawntime_tasks_diamond3 > 0) TaskDiamond3.runTask(game);
                        if (gametask_spawntime_tasks_diamond4 > 0) TaskDiamond4.runTask(game);


                        if (gametask_spawntime_tasks_emerald1 > 0) TaskEmerald1.runTask(game);
                        if (gametask_spawntime_tasks_emerald2 > 0) TaskEmerald2.runTask(game);
                        if (gametask_spawntime_tasks_emerald3 > 0) TaskEmerald3.runTask(game);
                        if (gametask_spawntime_tasks_emerald4 > 0) TaskEmerald4.runTask(game);

                    }

                    if (gametask_time_healthset1 > 0) TaskHealthSet1.runTask(game);
                    if (gametask_time_healthset2 > 0) TaskHealthSet2.runTask(game);
                    if (gametask_time_healthset3 > 0) TaskHealthSet3.runTask(game);
                    if (gametask_time_healthset4 > 0) TaskHealthSet4.runTask(game);

                    if (gametask_finalbattle) {
                        if (gametask_finalbattle_time > 0) TaskFinalBattle.runTask(game);
                    }
                }

                ScoreboardManager manager = Bukkit.getScoreboardManager();
                Scoreboard scoreboard = manager.getNewScoreboard();
                Objective obj = scoreboard.registerNewObjective("load", "dummy");
                obj.setDisplayName(t("                "));
                obj.setDisplaySlot(sidebar);
                obj.getScore(" ").setScore(1);
                obj.getScore("  ").setScore(2);
                obj.getScore("   ").setScore(3);
                obj.getScore("    ").setScore(4);
                obj.getScore("      ").setScore(5);
                obj.getScore("        ").setScore(6);
                game.getPlayers().stream()
                        .filter(Objects::nonNull)
                        .filter(Player::isOnline)
                        .forEach(player -> player.setScoreboard(scoreboard));


                game.getPlayers().stream()
                        .filter(Objects::nonNull)
                        .filter(Player::isOnline)
                        .forEach(player -> {

                            UUID playerUUID = player.getUniqueId();
                            SoundPlayer.LEVEL_UP(player, 1);

                            if (game.getRespawnProtections() != null) {
                                if (game.getRespawnProtections().containsKey(player)) {
                                    game.removeProtection(player);
                                }
                            }

                            if (!Objects.equals(startmess_all_chat, "")) {
                                sendMessage(player, startmess_all_chat);
                            }
                            if (!Objects.equals(startmess_all_title, "")) {
                                String titleReal = startmess_all_title;
                                if (!Objects.equals(startmess_all_subtitle, "")) {
                                    String subtitleReal = startmess_all_subtitle;

                                    sendTitle(player, titleReal, subtitleReal);
                                }
                            } else if (!Objects.equals(startmess_all_subtitle, "")) {
                                String titleReal = " ";
                                String subtitleReal = startmess_all_subtitle;

                                sendTitle(player, titleReal, subtitleReal);
                            }
                            if (!Objects.equals(startmess_all_actionbar, "")) {
                                sendActionBar(player, startmess_all_actionbar);
                            }

                            setDefaultPlayerStat(playerUUID);
                            removePlayerRespawn(playerUUID);
                            if (KitConfigHandler.kitenable) {
                                if (KitConfigHandler.kitForce) {
                                    applykitforce(playerUUID, KitConfigHandler.kitForceKit);
                                } else {
                                    applykit(playerUUID);
                                }
                            }

                            removePlayerIsOut(playerUUID);

                            removeArmorChain(playerUUID);
                            removeArmorIron(playerUUID);
                            removeArmorDiamond(playerUUID);

                            if (startKitCompass) {
                                player.getInventory().addItem(compass);
                            }
                        });

                disableAI(worldName);

                if (cleanHostileOnStart) {
                    Difficulty difficultyOrg;
                    if (world.getDifficulty() != peaceful) {
                        difficultyOrg = world.getDifficulty();
                    } else {
                        difficultyOrg = normal;
                    }
                    new BukkitRunnable() {
                        int h;

                        {
                            h = 3;
                        }

                        public void run() {
                            if (h != 0) {
                                world.setDifficulty(peaceful);
                                --h;
                            }

                            if (h == 0) {
                                world.setDifficulty(difficultyOrg);
                                cancel();
                            }
                        }
                    }.runTaskTimer(plugin, 1L, 20L);
                }

                TaskResIron taskResIron = new TaskResIron(game);
                taskResIron.runTask();
                TaskResGold taskResGold = new TaskResGold(game);
                taskResGold.runTask();
                TaskResDiamond taskResDiamond = new TaskResDiamond(game);
                taskResDiamond.runTask();
                TaskResEmerald taskResEmerald = new TaskResEmerald(game);
                taskResEmerald.runTask();

                for (Team team : game.getTeams().values()) {
                    Location location = team.getSpawnLocation();
                    RelMapProtect.addLocs(location, placeCorrect_PlayerSpawnLoc_dis);
                }
                for (ResourceSpawner spawner : game.getResourceSpawners()) {
                    Location location = spawner.getLocation();
                    RelMapProtect.addLocs(location, placeCorrect_ResSpawner_dis);
                }
            }
        }.runTaskLater(plugin, 1L);
    }
}
