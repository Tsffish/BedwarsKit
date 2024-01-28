package github.tsffish.bedwarskit.listener.bedwarsrel;

import github.tsffish.bedwarskit.config.MainConfigHandler;
import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameManager;
import io.github.bedwarsrel.game.GameState;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import static github.tsffish.bedwarskit.util.RelArmorList.*;
import static github.tsffish.bedwarskit.util.RelCheckSword.gs;

public class RelPlayerMove implements Listener {
    @EventHandler
    public void on(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (MainConfigHandler.grassWalk && player !=null && player.isOnline() && player.getGameMode() != GameMode.SPECTATOR) {
            Location to = event.getTo();

            if (player.getWorld().getBlockAt(to).getTypeId() == 102) {
                // 将玩家位置调整到离开玻璃板的位置
                Location newLocation = to.clone().add(0, 1.05, 0);
                player.teleport(newLocation);
            }
        }


        if (event.getPlayer() != null && event.getPlayer().isOnline()) {
            String playerName = player.getPlayer().getName();
            GameManager gm = BedwarsRel.getInstance().getGameManager();
            Game game = gm.getGameOfPlayer(player);
            if (game != null && game.getState() == GameState.RUNNING) {

                PlayerInventory pi = player.getInventory();
                ItemStack chain = new ItemStack(Material.IRON_INGOT, 35);
                ItemStack iron = new ItemStack(Material.GOLD_INGOT, 30);
                ItemStack diamond = new ItemStack(Material.EMERALD, 10);
                ItemStack bot = new ItemStack(Material.GLASS_BOTTLE);
                ItemStack bed = new ItemStack(Material.BED);

                        if (player.getHealth() != 0) {

                            gs(player);

                            if (pi.contains(bot)) {
                                pi.remove(bot);
                            }

                            if (pi.contains(bed)) {
                                pi.remove(bed);
                            }


                            if (pi.contains(Material.CHAINMAIL_CHESTPLATE)) {
                                if (armorChain.contains(playerName) || armorIron.contains(playerName) || armorDiamond.contains(playerName)) {
                                    pi.remove(Material.CHAINMAIL_CHESTPLATE);
                                    pi.addItem(chain);
                                    return;
                                }

                                armorChain.add(playerName);
                                pi.remove(Material.CHAINMAIL_CHESTPLATE);
                                player.getInventory().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
                                player.getInventory().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
                            }

                            if (pi.contains(Material.IRON_CHESTPLATE)) {
                                if (armorIron.contains(playerName) || armorDiamond.contains(playerName)) {
                                    pi.remove(Material.IRON_CHESTPLATE);
                                    pi.addItem(iron);
                                    return;
                                }

                                armorIron.add(playerName);
                                armorChain.remove(playerName);
                                armorDiamond.remove(playerName);
                                pi.remove(Material.IRON_CHESTPLATE);
                                player.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
                                player.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
                            }

                            if (pi.contains(Material.DIAMOND_CHESTPLATE)) {
                                if (armorDiamond.contains(playerName)) {
                                    pi.remove(Material.DIAMOND_CHESTPLATE);
                                    pi.addItem(diamond);
                                    return;
                                }

                                armorDiamond.add(playerName);
                                armorIron.remove(playerName);
                                armorChain.remove(playerName);
                                pi.remove(Material.DIAMOND_CHESTPLATE);
                                player.getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
                                player.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
                            }
            }}
            }}}

