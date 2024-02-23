package github.tsffish.bedwarskit.util;

import github.tsffish.bedwarskit.Main;
import github.tsffish.bedwarskit.config.kit.KitConfigHandler;
import github.tsffish.bedwarskit.config.main.MainConfigHandler;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scheduler.BukkitRunnable;

import static github.tsffish.bedwarskit.util.RelArmorList.*;
import static github.tsffish.bedwarskit.util.RelPlayerIsRespawn.removePlayerRespawn;
import static github.tsffish.bedwarskit.util.RelPlayerKit.applykit;
import static github.tsffish.bedwarskit.util.RelPlayerKit.applykitforce;

public class RelPlayerRespawn implements Listener {
    private static final Main plugin = Main.getInstance();
    static final ItemStack chain1 = new ItemStack(Material.CHAINMAIL_LEGGINGS);
    static final ItemStack chain2 = new ItemStack(Material.CHAINMAIL_BOOTS);
    static final ItemStack iron1 = new ItemStack(Material.IRON_LEGGINGS);
    static final ItemStack iron2 = new ItemStack(Material.IRON_BOOTS);
    static final ItemStack dm1 = new ItemStack(Material.DIAMOND_LEGGINGS);
    static final ItemStack dm2 = new ItemStack(Material.DIAMOND_BOOTS);
    public static void playerrespawn(Player player,long delay){

        String playerName = player.getName();

        new BukkitRunnable() {
                        @Override
                        public void run() {
                            PlayerInventory pi = player.getInventory();
                            if (KitConfigHandler.kitenable){
                                if (KitConfigHandler.kitForce){
                                    applykitforce(player,KitConfigHandler.kitForceKit);
                                }else {
                                    applykit(player);
                                }
                            }

                            if (MainConfigHandler.startKitCompass) {
                                pi.addItem(new ItemStack(Material.COMPASS));
                            }

                            String playerName = player.getName();


                            if (hasArmorChain(playerName)) {
                                if (!hasArmorIron(playerName) && !hasArmorDiamond(playerName)) {
                                    pi.setLeggings(chain1);
                                    pi.setBoots(chain2);
                                }
                            }

                            if (hasArmorIron(playerName)) {
                                if (!hasArmorChain(playerName)) {
                                    pi.setLeggings(iron1);
                                    pi.setBoots(iron2);
                                }
                            }
                            if (hasArmorDiamond(playerName)) {
                                pi.setLeggings(dm1);
                                pi.setBoots(dm2);
                            }

                            player.setGameMode(GameMode.SURVIVAL);
                            player.setHealth(player.getMaxHealth());

                            removePlayerRespawn(playerName);

                        }
                    }.runTaskLater(plugin, delay);
                }
}
