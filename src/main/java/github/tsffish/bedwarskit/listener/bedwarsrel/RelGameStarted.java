package github.tsffish.bedwarskit.listener.bedwarsrel;

import github.tsffish.bedwarskit.Main;
import github.tsffish.bedwarskit.config.KitConfigHandler;
import github.tsffish.bedwarskit.config.MainConfigHandler;
import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.events.BedwarsGameStartedEvent;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameState;
import io.github.bedwarsrel.game.ResourceSpawner;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.List;

import static github.tsffish.bedwarskit.util.RelArmorList.*;
import static github.tsffish.bedwarskit.util.RelCheckEnchant.checkEnchantArmor;
import static github.tsffish.bedwarskit.util.RelCheckEnchant.checkEnchantSword;
import static github.tsffish.bedwarskit.util.RelCurrentStat.playerIsWaiting;
import static github.tsffish.bedwarskit.util.RelCurrentStat.sdps;
import static github.tsffish.bedwarskit.util.RelPlayerKit.setforcekit;
import static github.tsffish.bedwarskit.util.RelPlayerKit.setkit;
import static github.tsffish.bedwarskit.util.RelScoreBoard.updateScoreBoard;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;

public class RelGameStarted implements Listener {
    public Plugin plugin = Main.getPlugin(Main.class);
    @EventHandler
    public void on(BedwarsGameStartedEvent e) {
        List<Player> players = e.getGame().getPlayers();
        if (MainConfigHandler.startmess){
            String startmess_all_chatReal = t(MainConfigHandler.startmess_all_chat);
            String startmess_all_titleReal = t(MainConfigHandler.startmess_all_title);
            String startmess_all_subtitleReal = t(MainConfigHandler.startmess_all_subtitle);

            for (Player player : players){
                player.sendMessage(startmess_all_chatReal);
                player.sendTitle(startmess_all_titleReal,startmess_all_subtitleReal);
            }
        }

        if (MainConfigHandler.customScoreboard) {
            players.forEach(player -> {
                sdps(player);


                ScoreboardManager manager = Bukkit.getScoreboardManager();
                Scoreboard scoreboard = manager.getNewScoreboard();
                Objective obj = scoreboard.registerNewObjective("load", "dummy");
                obj.setDisplayName(t("load"));
                obj.setDisplaySlot(DisplaySlot.SIDEBAR);
                player.setScoreboard(scoreboard);
            });

            new BukkitRunnable() {
                public void run() {
                    players.forEach(player -> {
                        Game game = BedwarsRel.getInstance().getGameManager().getGameOfPlayer(player);

                        if (game != null) {
                            checkEnchantArmor(game);
                            checkEnchantSword(game);

                            if (player != null && game.getState() == GameState.RUNNING) {
                                updateScoreBoard(player);
                            } else {
                                cancel();
                                updateScoreBoard(player);
                            }

                        } else {
                            cancel();
                            updateScoreBoard(player);
                        }
                    });
                }
            }.runTaskTimer(plugin, 0L, 20L);

            World world = e.getGame().getRegion().getWorld();
                    new BukkitRunnable() {
                int h;

                {
                    h = 3;
                }

                public void run() {
                    if (this.h != 0) {
                        world.setDifficulty(Difficulty.PEACEFUL);
                        --this.h;
                    }

                    if (this.h == 0) {
                        world.setDifficulty(Difficulty.NORMAL);
                        this.cancel();
                    }
                }
            }.runTaskTimer(this.plugin, 20L, 20L);

        }



        e.getGame().getPlayers().forEach((player) -> {

            if (KitConfigHandler.kitenable){
                if (KitConfigHandler.kitForce){
                    setforcekit(player,KitConfigHandler.kitForceKit);
                }else {
                    setkit(player);
                }
            }
            playerIsWaiting.remove(player);

            Game game = e.getGame();
            e.getGame().getPlayerSettings(player).setOneStackPerShift(true);
            String playerteam = game.getPlayerTeam(player).getColor().name();
            String playerName = player.getName();
            armorChain.remove(playerName);
            armorIron.remove(playerName);
            armorDiamond.remove(playerName);

            List<ResourceSpawner> resourceSpawner = game.getResourceSpawners();


            if (MainConfigHandler.startKitCompass) {
                player.getInventory().addItem(new ItemStack(Material.COMPASS));
            }

            });
    }
}


