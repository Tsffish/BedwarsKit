package github.tsffish.bedwarskit.listener.bedwarsrel;

import github.tsffish.bedwarskit.Main;
import github.tsffish.bedwarskit.config.MainConfigHandler;
import github.tsffish.bedwarskit.util.ColorString;
import github.tsffish.bedwarskit.util.RelArmorColor;
import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.events.BedwarsGameStartedEvent;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameState;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.util.Vector;

import java.util.List;

import static github.tsffish.bedwarskit.util.RelArmorList.*;
import static github.tsffish.bedwarskit.util.RelCheckEnchant.checkEnchantArmor;
import static github.tsffish.bedwarskit.util.RelCheckEnchant.checkEnchantSword;
import static github.tsffish.bedwarskit.util.RelScoreBoard.updateScoreBoard;

public class RelGameStarted implements Listener {
    public Plugin plugin = Main.getPlugin(Main.class);

    @EventHandler
    public void on(BedwarsGameStartedEvent e) {

        if (MainConfigHandler.customScoreboard) {
            List<Player> players = e.getGame().getPlayers();
            players.forEach(player -> {
                ScoreboardManager manager = Bukkit.getScoreboardManager();
                Scoreboard scoreboard = manager.getNewScoreboard();
                Objective obj = scoreboard.registerNewObjective("load", "dummy");
                obj.setDisplayName(ColorString.t(MainConfigHandler.scoreboardTitle));
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
                    String h;
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
            Game game = e.getGame();
            e.getGame().getPlayerSettings(player).setOneStackPerShift(true);
            String playerteam = game.getPlayerTeam(player).getColor().name();
            ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
            ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
            ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
            ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
            LeatherArmorMeta helmetMeta = (LeatherArmorMeta) helmet.getItemMeta();
            LeatherArmorMeta chestplateMeta = (LeatherArmorMeta) chestplate.getItemMeta();
            LeatherArmorMeta leggingsMeta = (LeatherArmorMeta) leggings.getItemMeta();
            LeatherArmorMeta bootsMeta = (LeatherArmorMeta) boots.getItemMeta();
            String playerName = player.getName();
            armorChain.remove(playerName);
            armorIron.remove(playerName);
            armorDiamond.remove(playerName);

            switch (playerteam) {
                case "RED":
                    helmetMeta.setColor(RelArmorColor.red);
                    chestplateMeta.setColor(RelArmorColor.red);
                    leggingsMeta.setColor(RelArmorColor.red);
                    bootsMeta.setColor(RelArmorColor.red);
                    break;
                case "BLUE":
                    helmetMeta.setColor(RelArmorColor.blue);
                    chestplateMeta.setColor(RelArmorColor.blue);
                    leggingsMeta.setColor(RelArmorColor.blue);
                    bootsMeta.setColor(RelArmorColor.blue);
                    break;
                case "GREEN":
                    helmetMeta.setColor(RelArmorColor.green);
                    chestplateMeta.setColor(RelArmorColor.green);
                    leggingsMeta.setColor(RelArmorColor.green);
                    bootsMeta.setColor(RelArmorColor.green);
                    break;
                case "YELLOW":
                    helmetMeta.setColor(RelArmorColor.yellow);
                    chestplateMeta.setColor(RelArmorColor.yellow);
                    leggingsMeta.setColor(RelArmorColor.yellow);
                    bootsMeta.setColor(RelArmorColor.yellow);
                    break;
                case "WHITE":
                    helmetMeta.setColor(RelArmorColor.white);
                    chestplateMeta.setColor(RelArmorColor.white);
                    leggingsMeta.setColor(RelArmorColor.white);
                    bootsMeta.setColor(RelArmorColor.white);
                    break;
                case "AQUA":
                    helmetMeta.setColor(RelArmorColor.aqua);
                    chestplateMeta.setColor(RelArmorColor.aqua);
                    leggingsMeta.setColor(RelArmorColor.aqua);
                    bootsMeta.setColor(RelArmorColor.aqua);
                    break;
                case "LIGHT_PURPLE":
                    helmetMeta.setColor(RelArmorColor.pink);
                    chestplateMeta.setColor(RelArmorColor.pink);
                    leggingsMeta.setColor(RelArmorColor.pink);
                    bootsMeta.setColor(RelArmorColor.pink);
                    break;
                case "GRAY":
                    helmetMeta.setColor(RelArmorColor.gray);
                    chestplateMeta.setColor(RelArmorColor.gray);
                    leggingsMeta.setColor(RelArmorColor.gray);
                    bootsMeta.setColor(RelArmorColor.gray);
                    break;
                default:
                    helmetMeta.setColor(Color.fromRGB(255, 165, 0));
                    chestplateMeta.setColor(Color.fromRGB(255, 165, 0));
                    leggingsMeta.setColor(Color.fromRGB(255, 165, 0));
                    bootsMeta.setColor(Color.fromRGB(255, 165, 0));
            }

            helmet.setItemMeta(helmetMeta);
            chestplate.setItemMeta(chestplateMeta);
            leggings.setItemMeta(leggingsMeta);
            boots.setItemMeta(bootsMeta);
            player.getInventory().setHelmet(helmet);
            player.getInventory().setChestplate(chestplate);
            player.getInventory().setLeggings(leggings);
            player.getInventory().setBoots(boots);
            if (MainConfigHandler.startKitCompass) {
                player.getInventory().addItem(new ItemStack(Material.COMPASS));
            }

            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999, 1), true);
        });
    }
}


