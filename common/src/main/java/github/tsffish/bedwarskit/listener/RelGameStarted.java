package github.tsffish.bedwarskit.listener;

import github.tsffish.bedwarskit.BedwarsKit;
import github.tsffish.bedwarskit.util.misc.SoundPlayer;
import github.tsffish.bedwarskit.util.task.game.*;
import github.tsffish.bedwarskit.util.teamshop.list.ListHaste;
import github.tsffish.bedwarskit.util.teamshop.list.ListHeal;
import github.tsffish.bedwarskit.util.teamshop.list.ListProt;
import github.tsffish.bedwarskit.util.teamshop.list.ListSharp;
import io.github.bedwarsrel.events.BedwarsGameStartedEvent;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.ResourceSpawner;
import io.github.bedwarsrel.game.Team;
import org.bukkit.*;
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

import static github.tsffish.bedwarskit.config.kit.KitConfigHandler.*;
import static github.tsffish.bedwarskit.config.main.MainConfigHandler.*;
import static github.tsffish.bedwarskit.config.task.TaskConfigHandler.*;
import static github.tsffish.bedwarskit.util.RelArmorList.*;
import static github.tsffish.bedwarskit.util.RelCurrentStat.removePlayerIsOut;
import static github.tsffish.bedwarskit.util.RelCurrentStat.setDefaultPlayerStat;
import static github.tsffish.bedwarskit.util.RelPlayerIsRespawn.removePlayerRespawn;
import static github.tsffish.bedwarskit.util.RelPlayerKit.applykit;
import static github.tsffish.bedwarskit.util.RelPlayerKit.applykitforce;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;
import static github.tsffish.bedwarskit.util.misc.DisableVillagerAI.disableVillagerAI;
import static github.tsffish.bedwarskit.util.misc.PlayerSender.sendTitle;
import static github.tsffish.bedwarskit.util.misc.SendActionBar.sendActionBar;
/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class RelGameStarted implements Listener {
    private static final String className = "RelGameStarted";
    private static final BedwarsKit plugin = BedwarsKit.getInstance();
    private static final ItemStack compass = new ItemStack(Material.COMPASS);
    private static final Difficulty peaceful = Difficulty.PEACEFUL;
    private static final Difficulty normal = Difficulty.NORMAL;
    private static final String doWeatherCycle = "doWeatherCycle";
    private static final String meanfalse = "false";
    private static final int maxSize = 9999;
    private static final DisplaySlot sidebar = DisplaySlot.SIDEBAR;
    @EventHandler
    public void on(final BedwarsGameStartedEvent event) {

        Game game = event.getGame();

        World world = game.getRegion().getWorld();
        world.setThundering(false);
        world.setWeatherDuration(0);
        world.setGameRuleValue(doWeatherCycle, meanfalse);

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
            System.out.println("teamMap is null or empty.");
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

                            if (gametask_spawntime_tasks_iron1 > 0) Iron1.runTask(game);
                            if (gametask_spawntime_tasks_iron2 > 0) Iron2.runTask(game);
                            if (gametask_spawntime_tasks_iron3 > 0) Iron3.runTask(game);
                            if (gametask_spawntime_tasks_iron4 > 0) Iron4.runTask(game);

                            if (gametask_spawntime_tasks_gold1 > 0) Gold1.runTask(game);
                            if (gametask_spawntime_tasks_gold2 > 0) Gold2.runTask(game);
                            if (gametask_spawntime_tasks_gold3 > 0) Gold3.runTask(game);
                            if (gametask_spawntime_tasks_gold4 > 0) Gold4.runTask(game);

                            if (gametask_spawntime_tasks_diamond1 > 0) Diamond1.runTask(game);
                            if (gametask_spawntime_tasks_diamond2 > 0) Diamond2.runTask(game);
                            if (gametask_spawntime_tasks_diamond3 > 0) Diamond3.runTask(game);
                            if (gametask_spawntime_tasks_diamond4 > 0) Diamond4.runTask(game);


                            if (gametask_spawntime_tasks_emerald1 > 0) Emerald1.runTask(game);
                            if (gametask_spawntime_tasks_emerald2 > 0) Emerald2.runTask(game);
                            if (gametask_spawntime_tasks_emerald3 > 0) Emerald3.runTask(game);
                            if (gametask_spawntime_tasks_emerald4 > 0) Emerald4.runTask(game);

                            if (gametask_finalbattle) {
                                if (gametask_finalbattle_time > 0) FinalBattle.runTask(game);
                            }
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

                                    UUID uuid = player.getUniqueId();
                                    SoundPlayer.LEVEL_UP(player, 1);

                                    if (game.getRespawnProtections().containsKey(player)){
                                    game.removeProtection(player);
                                    }


                                    String message = startmess_all_chat;
                                    if (message != null && !message.isEmpty()) {
                                        player.sendMessage(message);
                                    }

                                    String title = Optional.ofNullable(startmess_all_title).orElse(" ");
                                    String subtitle = Optional.ofNullable(startmess_all_subtitle).orElse("");

                                    sendTitle(player, title, subtitle);

                                    Optional.ofNullable(startmess_all_actionbar)
                                            .filter(actionBarMessage -> !actionBarMessage.isEmpty())
                                            .ifPresent(actionBarMessage -> sendActionBar(player, actionBarMessage));

                                    setDefaultPlayerStat(uuid);
                                    removePlayerRespawn(uuid);
                                    if (kitenable) {
                                        if (kitForce) {
                                            applykitforce(uuid, kitForceKit);
                                        } else {
                                            applykit(uuid);
                                        }
                                    }

                                    removePlayerIsOut(uuid);

                                    removeArmorChain(uuid);
                                    removeArmorIron(uuid);
                                    removeArmorDiamond(uuid);

                                    if (startKitCompass) {
                                        player.getInventory().addItem(compass);
                                    }
                                });

            disableVillagerAI(worldName);

        if (CleanHostileOnStart) {
            Difficulty difficultyOrg;
            if (world.getDifficulty() != peaceful) {
                difficultyOrg = world.getDifficulty();
            } else {
                difficultyOrg = normal;
            }
            new BukkitRunnable() {
                int h;
                {h = 3;}

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



    }
}
