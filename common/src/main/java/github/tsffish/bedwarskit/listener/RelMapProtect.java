package github.tsffish.bedwarskit.listener;

import github.tsffish.bedwarskit.BedwarsKit;
import github.tsffish.bedwarskit.config.main.MainConfigHandler;
import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameManager;
import io.github.bedwarsrel.game.GameState;
import io.github.bedwarsrel.game.Region;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class RelMapProtect implements Listener {
    private static final GameState running = GameState.RUNNING;
    private static final BedwarsKit plugin = BedwarsKit.getInstance();
    private static Set<Location> protLoc;
    private static ItemStack breakerItemStack;
        static boolean funEnable = false;
    static {
        if (1 != 1) {

            protLoc = Collections.newSetFromMap(new ConcurrentHashMap<>());
            breakerItemStack = new ItemStack(Material.DIAMOND_PICKAXE);
            breakerItemStack.addEnchantment(Enchantment.SILK_TOUCH, 1);

            new BukkitRunnable() {
                public void run() {
                    for (Location location : protLoc) {
                        Block block = location.getBlock();
                        if (block != null) {
                            Region region = BedwarsRel.getInstance().getGameManager().getGameByLocation(location).getRegion();

                            if (!region.isPlacedBlock(block)) {
                                block.breakNaturally(breakerItemStack);
                            }
                        }
                    }
                }
            }.runTaskTimer(plugin, 80L, 40L);
        }
    }
    public static void addLocs(Location location, int radius) {

        if (!funEnable){
            return;
        }
        World world = location.getWorld();
        if (world != null) {
            int minX = location.getBlockX() - radius;
            int minY = location.getBlockY() - radius;
            int minZ = location.getBlockZ() - radius;
            int maxX = location.getBlockX() + radius;
            int maxY = location.getBlockY() + radius;
            int maxZ = location.getBlockZ() + radius;

            for (int x = minX; x <= maxX; x++) {
                for (int y = minY; y <= maxY; y++) {
                    for (int z = minZ; z <= maxZ; z++) {
                        Location blockLocation = new Location(world, x, y, z);
                        protLoc.add(blockLocation);
                    }
                }
            }
        }
    }

    public static void removeLocs(Location location, int radius) {
if (!funEnable){
            return;
        }
        World world = location.getWorld();
        if (world != null) {
            int minX = location.getBlockX() - radius;
            int minY = location.getBlockY() - radius;
            int minZ = location.getBlockZ() - radius;
            int maxX = location.getBlockX() + radius;
            int maxY = location.getBlockY() + radius;
            int maxZ = location.getBlockZ() + radius;

            for (int x = minX; x <= maxX; x++) {
                for (int y = minY; y <= maxY; y++) {
                    for (int z = minZ; z <= maxZ; z++) {
                        Location blockLocation = new Location(world, x, y, z);
                        protLoc.remove(blockLocation);
                    }
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void on(final BlockBreakEvent event) {
        Block block = event.getBlock();
        if (block == null) {
            return;
        }
        GameManager gm = BedwarsRel.getInstance().getGameManager();
        Player player = event.getPlayer();
        if (player == null || !player.isOnline()) {
            return;
        }
        if (MainConfigHandler.breakCorrect_notInGame) {
            if (MainConfigHandler.breakCorrect_notInGame_OpBypass) {
                if (!player.isOp()) {
                    String worldName = player.getWorld().getName();
                    for (Game list : gm.getGames()) {
                        if (list.getRegion().getWorld().getName().equals(worldName) && list.getState() != running) {
                            event.setCancelled(true);
                            break;
                        }
                    }
                }
            } else {
                String worldName = player.getWorld().getName();
                for (Game list : gm.getGames()) {
                    if (list.getRegion().getWorld().getName().equals(worldName) && list.getState() != running) {
                        event.setCancelled(true);
                        break;
                    }
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void on(final BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (player == null || !player.isOnline()) {
            return;
        }
        Block block = event.getBlock();
        if (block == null) {
            return;
        }
        GameManager gm = BedwarsRel.getInstance().getGameManager();
        if (MainConfigHandler.placeCorrect_notInGame) {
            if (MainConfigHandler.placeCorrect_notInGame_OpBypass) {
                if (!player.isOp()) {
                    String worldName = player.getWorld().getName();
                    for (Game list : gm.getGames()) {
                        if (list.getRegion().getWorld().getName().equals(worldName) && list.getState() != running) {
                            event.setCancelled(true);
                            break;
                        }
                    }
                }
            } else {
                String worldName = player.getWorld().getName();
                for (Game list : gm.getGames()) {
                    if (list.getRegion().getWorld().getName().equals(worldName) && list.getState() != running) {
                        event.setCancelled(true);
                        break;
                    }
                }
            }
        }
    }
}

