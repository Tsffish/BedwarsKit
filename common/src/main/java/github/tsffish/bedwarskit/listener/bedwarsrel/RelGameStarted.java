package github.tsffish.bedwarskit.listener.bedwarsrel;

import github.tsffish.bedwarskit.Main;
import github.tsffish.bedwarskit.config.kit.KitConfigHandler;
import github.tsffish.bedwarskit.util.gametask.*;
import github.tsffish.bedwarskit.util.player.SoundPlayer;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static github.tsffish.bedwarskit.config.main.MainConfigHandler.*;
import static github.tsffish.bedwarskit.config.task.TaskConfigHandler.*;
import static github.tsffish.bedwarskit.util.DisableVillagerAI.disableVillagerAI;
import static github.tsffish.bedwarskit.util.RelArmorList.*;
import static github.tsffish.bedwarskit.util.RelCurrentStat.removePlayerIsOut;
import static github.tsffish.bedwarskit.util.RelCurrentStat.setDefaultPlayerStat;
import static github.tsffish.bedwarskit.util.RelPlayerIsRespawn.removePlayerRespawn;
import static github.tsffish.bedwarskit.util.RelPlayerKit.applykit;
import static github.tsffish.bedwarskit.util.RelPlayerKit.applykitforce;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;
import static github.tsffish.bedwarskit.util.player.SendActionBar.sendActionBar;

public class RelGameStarted implements Listener {
    private static final Main plugin = Main.getInstance();
    static final Material compass = Material.COMPASS;
    @EventHandler
    public void on(final BedwarsGameStartedEvent event) {

        Game game = event.getGame();

        if (game == null) return;

        World world = game.getRegion().getWorld();
        world.setThundering(false);
        world.setWeatherDuration(0);
        world.setGameRuleValue("doWeatherCycle", "false");

        WorldBorder worldBorder = world.getWorldBorder();
        worldBorder.setCenter(game.getLobby());
        worldBorder.setSize(9999);


        String worldName = world.getName();
        String gameName = game.getName();

        Map<String, Team> teamMap = game.getTeams();
        List<Team> teamList = new ArrayList<>(teamMap.values());

        if (worldName.contains(rushWorld)){
            if (worldName.contains(rushWorld2v2)) {

                ListHaste.setTeamDatasDefault(gameName, teamList);
                ListHeal.setTeamDatasDefault(gameName, teamList);
                ListSharp.setTeamDatasDefault(gameName, teamList);
                ListProt.setTeamDatasDefault(gameName, teamList);

            } else if (worldName.contains(rushWorld4v4)) {

                ListHaste.setTeamDatasDefault(gameName, teamList);
                ListHeal.setTeamDatasDefault(gameName, teamList);
                ListSharp.setTeamDatasDefault(gameName, teamList);
                ListProt.setTeamDatasDefault(gameName, teamList);
            }
        }
        if (gametask) {
            if (gametask_spawntime) {

                List<ResourceSpawner> resourceSpawner = game.getResourceSpawners();

                for (ResourceSpawner list : resourceSpawner) {
                    if (list != null) {
                        for (ItemStack item : list.getResources()) {
                            if (item != null) {
                                String itemName = item.getType().toString().toUpperCase();
                                if (itemName.contains("IRON")) {
                                    list.setInterval(gametask_spawntime_iron_base);
                                } else if (itemName.contains("GOLD")) {
                                    list.setInterval(gametask_spawntime_gold_base);
                                } else if (itemName.contains("DIAMOND")) {
                                    list.setInterval(gametask_spawntime_diamond_base);
                                } else if (itemName.contains("EMERALD")) {
                                    list.setInterval(gametask_spawntime_emerald_base);
                                }
                            }
                            break;
                        }
                    }
                }


                if (gametask_finalbattle) {
                    if (gametask_finalbattle_time > 0) {
                        TaskFinalBattle.runTask(game);
                    }
                }
            }

            if (gametask_spawntime_tasks_iron1 > 0) {
                TaskIron1.runTask(game);
            }

            if (gametask_spawntime_tasks_iron2 > 0) {
                TaskIron2.runTask(game);
            }

            if (gametask_spawntime_tasks_iron3 > 0) {
                TaskIron3.runTask(game);
            }
            if (gametask_spawntime_tasks_iron4 > 0) {
                TaskIron4.runTask(game);
            }

            if (gametask_spawntime_tasks_gold1 > 0) {
                TaskGold1.runTask(game);
            }

            if (gametask_spawntime_tasks_gold2 > 0) {
                TaskGold2.runTask(game);
            }

            if (gametask_spawntime_tasks_gold3 > 0) {
                TaskGold3.runTask(game);
            }
            if (gametask_spawntime_tasks_gold4 > 0) {
                TaskGold4.runTask(game);
            }

            if (gametask_spawntime_tasks_diamond1 > 0) {
                TaskDiamond1.runTask(game);
            }

            if (gametask_spawntime_tasks_diamond2 > 0) {
                TaskDiamond2.runTask(game);
            }

            if (gametask_spawntime_tasks_diamond3 > 0) {
                TaskDiamond3.runTask(game);
            }
            if (gametask_spawntime_tasks_diamond4 > 0) {
                TaskDiamond4.runTask(game);
            }


            if (gametask_spawntime_tasks_emerald1 > 0) {
                TaskEmerald1.runTask(game);
            }

            if (gametask_spawntime_tasks_emerald2 > 0) {
                TaskEmerald2.runTask(game);
            }

            if (gametask_spawntime_tasks_emerald3 > 0) {
                TaskEmerald3.runTask(game);
            }
            if (gametask_spawntime_tasks_emerald4 > 0) {
                TaskEmerald4.runTask(game);
            }

        }

        List<Player> players = event.getGame().getPlayers();
        if (startmess) {
            for (Player player : players) {
                SoundPlayer.LEVEL_UP(player, 1);
                if (!Objects.equals(startmess_all_chat, "")) {
                    player.sendMessage(startmess_all_chat);
                }
                if (!Objects.equals(startmess_all_title, "")) {
                    String titleReal = t(startmess_all_title);
                    if (!Objects.equals(startmess_all_subtitle, "")) {
                        String subtitleReal = t(startmess_all_subtitle);

                        player.sendTitle(titleReal, subtitleReal);
                    }
                } else if (!startmess_all_subtitle.isEmpty()) {
                    String titleReal = " ";
                    String subtitleReal = t(startmess_all_subtitle);

                    player.sendTitle(titleReal, subtitleReal);
                }
                if (!startmess_all_actionbar.isEmpty()) {
                    sendActionBar(player, t(startmess_all_actionbar));

                }
            }
        }









        players.forEach(player -> {
            String playerName = player.getName();
            setDefaultPlayerStat(playerName);
            removePlayerRespawn(playerName);
            ScoreboardManager manager = Bukkit.getScoreboardManager();
            Scoreboard scoreboard = manager.getNewScoreboard();
            Objective obj = scoreboard.registerNewObjective("load", "dummy");
            obj.setDisplayName(t("                "));
            obj.setDisplaySlot(DisplaySlot.SIDEBAR);
            obj.getScore(" ").setScore(1);
            obj.getScore("  ").setScore(2);
            obj.getScore("   ").setScore(3);
            obj.getScore("    ").setScore(4);
            obj.getScore("      ").setScore(5);
            obj.getScore("        ").setScore(6);
            player.setScoreboard(scoreboard);
        });


        if (CleanHostileOnStart) {
            Difficulty difficultyOrg;
            if (world.getDifficulty() != Difficulty.PEACEFUL) {
                difficultyOrg = world.getDifficulty();
            } else {
                difficultyOrg = Difficulty.NORMAL;
            }
            new BukkitRunnable() {
                int h;
                {
                    h = 3;
                }

                public void run() {
                    if (h != 0) {
                        world.setDifficulty(Difficulty.PEACEFUL);
                        --h;
                    }

                    if (h == 0) {
                        world.setDifficulty(difficultyOrg);
                        cancel();
                    }
                }
            }.runTaskTimer(plugin, 1L, 20L);
        }

        if (lobbyWorld != null && !lobbyWorld.isEmpty()) {
            World lobby = Bukkit.getWorld(lobbyWorld);
            Location loc = lobby.getSpawnLocation();
            event.getGame().setMainLobby(loc);

        }
        event.getGame().getPlayers().forEach((player) -> {

            String playerName = player.getName();

            if (KitConfigHandler.kitenable) {
                if (KitConfigHandler.kitForce) {
                    applykitforce(player, KitConfigHandler.kitForceKit);
                } else {
                    applykit(player);
                }
            }
            removePlayerIsOut(playerName);

            removeArmorChain(playerName);
            removeArmorIron(playerName);
            removeArmorDiamond(playerName);

            if (startKitCompass) {
                player.getInventory().addItem(new ItemStack(compass));
            }
            disableVillageAI(worldName);

        });
    }
    void disableVillageAI(String worldName) {
        disableVillagerAI(worldName);
    }
}
