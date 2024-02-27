package github.tsffish.bedwarskit.util;

import github.tsffish.bedwarskit.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

import static github.tsffish.bedwarskit.config.kit.KitConfigHandler.*;
import static github.tsffish.bedwarskit.config.main.MainConfigHandler.startKitCompass;
import static github.tsffish.bedwarskit.util.RelArmorList.*;
import static github.tsffish.bedwarskit.util.RelPlayerIsRespawn.removePlayerRespawn;
import static github.tsffish.bedwarskit.util.RelPlayerKit.applykit;
import static github.tsffish.bedwarskit.util.RelPlayerKit.applykitforce;

public class RelPlayerRespawn {
    private static final Main plugin = Main.getInstance();
    private static final ItemStack chain1 = new ItemStack(Material.CHAINMAIL_LEGGINGS);
    private static final ItemStack chain2 = new ItemStack(Material.CHAINMAIL_BOOTS);
    private static final ItemStack iron1 = new ItemStack(Material.IRON_LEGGINGS);
    private static final ItemStack iron2 = new ItemStack(Material.IRON_BOOTS);
    private static final ItemStack dm1 = new ItemStack(Material.DIAMOND_LEGGINGS);
    private static final ItemStack dm2 = new ItemStack(Material.DIAMOND_BOOTS);
    private static final ItemStack compass = new ItemStack(Material.COMPASS);
    private static final GameMode survival = GameMode.SURVIVAL;
    protected static void playerrespawn(UUID uuid, long delay){
        Player player = Bukkit.getPlayer(uuid);
        new BukkitRunnable() {
                        @Override
                        public void run() {
                            PlayerInventory pi = player.getInventory();
                            if (kitenable){
                                if (kitForce){
                                    applykitforce(uuid,kitForceKit);
                                }else {
                                    applykit(uuid);
                                }
                            }

                            if (startKitCompass) {
                                pi.addItem(compass);
                            }

                            if (hasArmorChain(uuid)) {
                                if (!hasArmorIron(uuid) && !hasArmorDiamond(uuid)) {
                                    pi.setLeggings(chain1);
                                    pi.setBoots(chain2);
                                }
                            }

                            if (hasArmorIron(uuid)) {
                                if (!hasArmorChain(uuid)) {
                                    pi.setLeggings(iron1);
                                    pi.setBoots(iron2);
                                }
                            }
                            if (hasArmorDiamond(uuid)) {
                                pi.setLeggings(dm1);
                                pi.setBoots(dm2);
                            }

                            player.setGameMode(survival);
                            player.setHealth(player.getMaxHealth());

                            removePlayerRespawn(uuid);

                        }
                    }.runTaskLater(plugin, delay);
                }
}
