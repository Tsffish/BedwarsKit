package github.tsffish.bedwarskit.listener.bedwarsrel;

import github.tsffish.bedwarskit.Main;
import github.tsffish.bedwarskit.config.KitConfigHandler;
import github.tsffish.bedwarskit.config.MainConfigHandler;
import github.tsffish.bedwarskit.util.gametask.*;
import io.github.bedwarsrel.events.BedwarsGameStartedEvent;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.ResourceSpawner;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.List;
import java.util.Objects;

import static github.tsffish.bedwarskit.config.MainConfigHandler.gametask_finalbattle;
import static github.tsffish.bedwarskit.util.RelArmorList.*;
import static github.tsffish.bedwarskit.util.RelCurrentStat.playerIsOut;
import static github.tsffish.bedwarskit.util.RelCurrentStat.sdps;
import static github.tsffish.bedwarskit.util.RelPlayerKit.setforcekit;
import static github.tsffish.bedwarskit.util.RelPlayerKit.setkit;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;
import static io.github.bedwarsrel.com.v1_8_r3.ActionBar.sendActionBar;

public class RelGameStarted implements Listener {
    public Plugin plugin = JavaPlugin.getPlugin(Main.class);
    @EventHandler
    public void on(BedwarsGameStartedEvent e) {
        Game game = e.getGame();

        if (MainConfigHandler.gametask) {
            if (MainConfigHandler.gametask_spawntime) {

                List<ResourceSpawner> resourceSpawner = game.getResourceSpawners();

                for (ResourceSpawner list : resourceSpawner) {
                    if (list != null) {
                        for (ItemStack item : list.getResources()) {
                            if (item != null) {
                                String itemName = item.getType().toString();
                                if (itemName.contains("IRON")) {
                                    list.setInterval(MainConfigHandler.gametask_spawntime_iron_base);
                                }else if (itemName.contains("GOLD")){
                                    list.setInterval(MainConfigHandler.gametask_spawntime_gold_base);
                                }else if (itemName.contains("DIAMOND")){
                                    list.setInterval(MainConfigHandler.gametask_spawntime_diamond_base);
                                } else if (itemName.contains("EMERALD")) {
                                    list.setInterval(MainConfigHandler.gametask_spawntime_emerald_base);
                                }

                            }
                        }
                    }
                }

                if (MainConfigHandler.gametask_spawntime_tasks_iron1 > 0){
                    TaskIron1.runTask(game);
                }

                if (MainConfigHandler.gametask_spawntime_tasks_iron2 > 0){
                    TaskIron2.runTask(game);
                }

                if (MainConfigHandler.gametask_spawntime_tasks_iron3 > 0){
                    TaskIron3.runTask(game);
                }
                if (MainConfigHandler.gametask_spawntime_tasks_iron4 > 0){
                    TaskIron4.runTask(game);
                }


                if (MainConfigHandler.gametask_spawntime_tasks_gold1 > 0){
                    TaskGold1.runTask(game);
                }

                if (MainConfigHandler.gametask_spawntime_tasks_gold2 > 0){
                    TaskGold2.runTask(game);
                }

                if (MainConfigHandler.gametask_spawntime_tasks_gold3 > 0){
                    TaskGold3.runTask(game);
                }
                if (MainConfigHandler.gametask_spawntime_tasks_gold4 > 0){
                    TaskGold4.runTask(game);
                }


                if (MainConfigHandler.gametask_spawntime_tasks_diamond1 > 0){
                    TaskDiamond1.runTask(game);
                }

                if (MainConfigHandler.gametask_spawntime_tasks_diamond2 > 0){
                    TaskDiamond2.runTask(game);
                }

                if (MainConfigHandler.gametask_spawntime_tasks_diamond3 > 0){
                    TaskDiamond3.runTask(game);
                }
                if (MainConfigHandler.gametask_spawntime_tasks_diamond4 > 0){
                    TaskDiamond4.runTask(game);
                }


                if (MainConfigHandler.gametask_spawntime_tasks_emerald1 > 0){
                    TaskEmerald1.runTask(game);
                }

                if (MainConfigHandler.gametask_spawntime_tasks_emerald2 > 0){
                    TaskEmerald2.runTask(game);
                }

                if (MainConfigHandler.gametask_spawntime_tasks_emerald3 > 0){
                    TaskEmerald3.runTask(game);
                }
                if (MainConfigHandler.gametask_spawntime_tasks_emerald4 > 0){
                    TaskEmerald4.runTask(game);
                }


                if (MainConfigHandler.gametask_finalbattle) {
                    if (MainConfigHandler.gametask_finalbattle_time > 0) {
                        TaskFinalBattle.runTask(game);
                    }
                }

            }

            World world = game.getRegion().getWorld();
            world.setThundering(false);
            world.setWeatherDuration(0);
            world.setGameRuleValue("doWeatherCycle", "false");

            WorldBorder worldBorder = world.getWorldBorder();
            worldBorder.setCenter(game.getLobby());
            worldBorder.setSize(MainConfigHandler.gametask_boundaries_size);

        }

        List<Player> players = e.getGame().getPlayers();
        if (MainConfigHandler.startmess){
            for (Player player : players){


                if (!Objects.equals(MainConfigHandler.startmess_all_chat, "")) {
                    player.sendMessage(MainConfigHandler.startmess_all_chat);
                }
                if (!Objects.equals(MainConfigHandler.startmess_all_title, "")) {
                    String titleReal = t(MainConfigHandler.startmess_all_title);
                    if (!Objects.equals(MainConfigHandler.startmess_all_subtitle, "")) {
                        String subtitleReal = t(MainConfigHandler.startmess_all_subtitle);

                        player.sendTitle(titleReal, subtitleReal);
                    }
                } else if (!Objects.equals(MainConfigHandler.startmess_all_subtitle, "")) {
                    String titleReal = " ";
                    String subtitleReal = t(MainConfigHandler.startmess_all_subtitle);

                    player.sendTitle(titleReal, subtitleReal);
                }
                if (!Objects.equals(MainConfigHandler.startmess_all_actionbar, "")) {
                    sendActionBar(player, t(MainConfigHandler.startmess_all_actionbar));

                }
            }
        }

            players.forEach(player -> {
                sdps(player);


                ScoreboardManager manager = Bukkit.getScoreboardManager();
                Scoreboard scoreboard = manager.getNewScoreboard();
                Objective obj = scoreboard.registerNewObjective("load", "dummy");
                obj.setDisplayName(t("load"));
                obj.setDisplaySlot(DisplaySlot.SIDEBAR);
                player.setScoreboard(scoreboard);
            });


        if (MainConfigHandler.CleanHostileOnStart) {
            World world = e.getGame().getRegion().getWorld();
            Difficulty difficultyOrg;
            if (world.getDifficulty() != Difficulty.PEACEFUL){
                difficultyOrg = world.getDifficulty();
            }else {
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
        e.getGame().getPlayers().forEach((player) -> {

            if (KitConfigHandler.kitenable){
                if (KitConfigHandler.kitForce){
                    setforcekit(player,KitConfigHandler.kitForceKit);
                }else {
                    setkit(player);
                }
            }
            playerIsOut.remove(player);

            e.getGame().getPlayerSettings(player).setOneStackPerShift(true);
            String playerName = player.getName();
            armorChain.remove(playerName);
            armorIron.remove(playerName);
            armorDiamond.remove(playerName);

            if (MainConfigHandler.startKitCompass) {
                player.getInventory().addItem(new ItemStack(Material.COMPASS));
            }

            });
    }
}


