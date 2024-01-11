//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package github.tsffish.bedwarskit.listener.bedwarsrel;

import github.tsffish.bedwarskit.Main;
import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.events.BedwarsGameStartedEvent;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class RelPlayerMove implements Listener {
    Plugin plugin = Main.getProvidingPlugin(Main.class);
    public static List<String> armorChain = new ArrayList();
    public static List<String> armorIron = new ArrayList();
    public static List<String> armorDiamond = new ArrayList();

    public RelPlayerMove() {
    }

    @EventHandler
    public void on(BedwarsGameStartedEvent e) {
        final ItemStack chain = new ItemStack(Material.IRON_INGOT, 35);
        final ItemStack iron = new ItemStack(Material.GOLD_INGOT, 30);
        final ItemStack diamond = new ItemStack(Material.EMERALD, 10);
        GameManager gm = BedwarsRel.getInstance().getGameManager();
        Iterator var6 = e.getGame().getPlayers().iterator();

        while(var6.hasNext()) {
            final Player p = (Player)var6.next();
            final String playerName = p.getPlayer().getName();
            final Inventory inv = p.getInventory();
            Game game = gm.getGameOfPlayer(p);
            if (game != null) {
                (new BukkitRunnable() {
                    public void run() {
                        GameManager gm = BedwarsRel.getInstance().getGameManager();
                        Game game = gm.getGameOfPlayer(p);
                        if (game == null || game.getTimeLeft() > game.getLength()) {
                            this.cancel();
                        }

                        if (!inv.contains(Material.WOOD_SWORD) && (!inv.contains(Material.STONE_SWORD) || !inv.contains(Material.IRON_SWORD) || !inv.contains(Material.IRON_SWORD)) && !inv.contains(Material.WOOD_SWORD)) {
                            inv.addItem(new ItemStack(Material.WOOD_SWORD));
                        }

                        if (inv.contains(Material.STONE_SWORD) || inv.contains(Material.IRON_SWORD) || inv.contains(Material.DIAMOND_SWORD)) {
                            inv.removeItem(new ItemStack(Material.WOOD_SWORD));
                        }

                        if (inv.contains(Material.CHAINMAIL_CHESTPLATE)) {
                            if (RelPlayerMove.armorChain.contains(playerName) || RelPlayerMove.armorIron.contains(playerName) || RelPlayerMove.armorDiamond.contains(playerName)) {
                                inv.remove(Material.CHAINMAIL_CHESTPLATE);
                                inv.addItem(chain);
                                return;
                            }

                            RelPlayerMove.armorChain.add(playerName);
                            inv.remove(Material.CHAINMAIL_CHESTPLATE);
                            p.getInventory().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
                            p.getInventory().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
                        }

                        if (inv.contains(Material.IRON_CHESTPLATE)) {
                            if (RelPlayerMove.armorIron.contains(playerName) || RelPlayerMove.armorDiamond.contains(playerName)) {
                                inv.remove(Material.IRON_CHESTPLATE);
                                inv.addItem(iron);
                                return;
                            }

                            RelPlayerMove.armorIron.add(playerName);
                            RelPlayerMove.armorChain.remove(playerName);
                            RelPlayerMove.armorDiamond.remove(playerName);
                            inv.remove(Material.IRON_CHESTPLATE);
                            p.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
                            p.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
                        }

                        if (inv.contains(Material.DIAMOND_CHESTPLATE)) {
                            if (RelPlayerMove.armorDiamond.contains(playerName)) {
                                inv.remove(Material.DIAMOND_CHESTPLATE);
                                inv.addItem(diamond);
                                return;
                            }

                            RelPlayerMove.armorDiamond.add(playerName);
                            RelPlayerMove.armorIron.remove(playerName);
                            RelPlayerMove.armorChain.remove(playerName);
                            inv.remove(Material.DIAMOND_CHESTPLATE);
                            p.getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
                            p.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
                        }

                    }
                }).runTaskTimer(this.plugin, 20L, 20L);
            }
        }

    }
}
