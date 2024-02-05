package github.tsffish.bedwarskit.listener.bedwarsrel;

import github.tsffish.bedwarskit.Main;
import github.tsffish.bedwarskit.config.KitConfigHandler;
import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.events.BedwarsPlayerJoinedEvent;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameManager;
import io.github.bedwarsrel.game.GameState;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import static github.tsffish.bedwarskit.config.MainConfigHandler.*;
import static github.tsffish.bedwarskit.config.kit.MenuItem.kitMenuItem;
import static github.tsffish.bedwarskit.util.RelArmorList.*;
import static github.tsffish.bedwarskit.util.RelCheckSword.gs;
import static github.tsffish.bedwarskit.util.RelCurrentStat.playerIsWaiting;
import static github.tsffish.bedwarskit.util.RelCurrentStat.sdps;
import static github.tsffish.bedwarskit.util.RelIsCheckingPlayer.isCheckingPlayer;
import static github.tsffish.bedwarskit.util.RelPlayerKit.playerKitList;

public class RelPlayerJoin implements Listener {
    static Plugin plugin = Main.getProvidingPlugin(Main.class);

    ItemStack bot = new ItemStack(Material.GLASS_BOTTLE);
    ItemStack bed = new ItemStack(Material.BED);
    @EventHandler
    public void on(BedwarsPlayerJoinedEvent event){
        Game game = event.getGame();
        Player player = event.getPlayer();
        if (game == null || player == null || !player.isOnline()){
            return;
        }
        if (!playerKitList.containsKey(player)) {
            playerKitList.put(player, "Default");
        }

        sdps(player);

        if (game.getState() == GameState.WAITING){
            playerIsWaiting.add(player);
        }

        if (!player.getInventory().contains(kitMenuItem)){
            player.getInventory().addItem(kitMenuItem);
        }

        String worldName = game.getRegion().getWorld().getName();
        if (!isCheckingPlayer.contains(worldName)){
            ItemStack chain = new ItemStack(chainPriceType, chainPrice);
            ItemStack iron = new ItemStack(ironPriceType, ironPrice);
            ItemStack diamond = new ItemStack(diamondPriceType, diamondPrice);
            new BukkitRunnable() {
                public void run() {
                    if (game.getPlayers().isEmpty()){
                        isCheckingPlayer.remove(worldName);
                        this.cancel();
                    } else if (game.getState() != GameState.WAITING && game.getState() != GameState.RUNNING){
                        isCheckingPlayer.remove(worldName);
                        this.cancel();
                    }else {
                        if (game.getState() == GameState.WAITING) {
                            if (KitConfigHandler.kitenable) {
                                if (KitConfigHandler.kitMenuItemGive) {

                                    for (Player player : game.getPlayers()) {
                                        PlayerInventory inventory = player.getInventory();
                                        if (!inventory.contains(kitMenuItem)) {
                                            inventory.addItem(kitMenuItem);
                                        }
                                    }
                                }
                            }
                        } else if (game.getState() == GameState.RUNNING) {
                            for (Player player : game.getPlayers()) {
                                if (event.getPlayer() != null && event.getPlayer().isOnline()) {
                                    String playerName = player.getPlayer().getName();
                                    GameManager gm = BedwarsRel.getInstance().getGameManager();
                                    Game game = gm.getGameOfPlayer(player);
                                    if (game != null && game.getState() == GameState.RUNNING) {

                                        PlayerInventory pi = player.getInventory();

                                        if (player.getHealth() != 0) {

                                            gs(player);

                                            if (cleanBottle) {
                                                if (pi.contains(bot)) {
                                                    pi.remove(bot);
                                                }
                                            }
                                            if (pi.contains(bed)) {
                                                pi.remove(bed);
                                            }


                                            if (pi.contains(upToChainArmor)) {
                                                if (armorChain.contains(playerName) || armorIron.contains(playerName) || armorDiamond.contains(playerName)) {
                                                    pi.remove(upToChainArmor);
                                                    pi.addItem(chain);
                                                    return;
                                                }

                                                armorChain.add(playerName);
                                                pi.remove(upToChainArmor);
                                                player.getInventory().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
                                                player.getInventory().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
                                            }

                                            if (pi.contains(upToIronArmor)) {
                                                if (armorIron.contains(playerName) || armorDiamond.contains(playerName)) {
                                                    pi.remove(upToIronArmor);
                                                    pi.addItem(iron);
                                                    return;
                                                }

                                                armorIron.add(playerName);
                                                armorChain.remove(playerName);
                                                armorDiamond.remove(playerName);
                                                pi.remove(upToIronArmor);
                                                player.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
                                                player.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
                                            }

                                            if (pi.contains(upToDiamondArmor)) {
                                                if (armorDiamond.contains(playerName)) {
                                                    pi.remove(upToDiamondArmor);
                                                    pi.addItem(diamond);
                                                    return;
                                                }

                                                armorDiamond.add(playerName);
                                                armorIron.remove(playerName);
                                                armorChain.remove(playerName);
                                                pi.remove(upToDiamondArmor);
                                                player.getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
                                                player.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
                                            }
                                        }
                                    }
                                }

                            }
                        }

                    }
                }
            }.runTaskTimer(plugin, 0L,20L);


        }


    }
}
