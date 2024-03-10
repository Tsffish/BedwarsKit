package github.tsffish.bedwarskit.util.task.text;

import github.tsffish.bedwarskit.BedwarsKit;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameState;
import io.github.bedwarsrel.game.ResourceSpawner;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Objects;

import static github.tsffish.bedwarskit.config.res.ResConfigHandler.*;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;
import static github.tsffish.bedwarskit.util.misc.MessSender.l;
import static github.tsffish.bedwarskit.util.misc.PluginState.isDebug;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class ResIronTextTask {
    private BedwarsKit plugin = BedwarsKit.getInstance();
    private Game game;
    private World world;
    private Location resOrgLoc;
    private GameState running = GameState.RUNNING;
    private HashMap<Material, Integer> resTime;
    private boolean isRunning;

    public ResIronTextTask(Game game) {
        this.game = game;
        this.world = game.getRegion().getWorld();
        this.resTime = new HashMap<>();
    }

    public void runTask() {
        for (ResourceSpawner resourceSpawner : game.getResourceSpawners()) {
            if (resourceSpawner.getResources().get(0).getType() == Material.IRON_INGOT) {
                resOrgLoc = resourceSpawner.getLocation();

                runCountDownTask(resourceSpawner);
                if (resBlock_Iron) {
                    if (isDebug()) {
                        l("resBlock_Iron = true");
                    }
                    if (resBlock_Iron_spinSpeed < 1) {
                        resBlock_Iron_spinSpeed = 1L;
                    }
                    if (isDebug()) {
                        l("resBlock_Iron_spinSpeed = " + resBlock_Iron_spinSpeed);
                    }
                    Location location = resOrgLoc.clone().add(0, resBlock_Iron_y, 0);

                    ArmorStand armorStand = world.spawn(location, ArmorStand.class);
                    armorStand.setGravity(false);
                    armorStand.setCanPickupItems(false);
                    armorStand.setVisible(false);
                    ItemStack item = new ItemStack(Material.IRON_BLOCK);
                    armorStand.setHelmet(item);

                    new BukkitRunnable() {
                        float angle = 0;

                        @Override
                        public void run() {
                            if (armorStand == null) {
                                if (isDebug()) {
                                    l("entity is null, cancel Iron Block entity Task(get kill @e?)");
                                }
                                cancel();
                            }
                            if (game.getState() != running) {
                                if (isDebug()) {
                                    l("is run = false,remove Iron Block entity");
                                }
                                armorStand.remove();
                                cancel();
                            }
                            if (resBlock_Iron_isSmall){
                                armorStand.setSmall(true);
                            }else {
                                armorStand.setSmall(false);
                            }
                            angle += resBlock_Iron_yawPerTick;
                            if (angle >= 360) {
                                angle = angle - 360;
                            }

                            Location currentLocation = armorStand.getLocation();
                            currentLocation.setYaw(angle);
                            armorStand.teleport(currentLocation);
                        }
                    }.runTaskTimer(plugin, 0L, resBlock_Iron_spinSpeed);


                }
                if (!Objects.equals(resText1_Iron, "")) {
                    Location location = resOrgLoc.clone().add(0, resText1_Iron_y, 0);
                    ArmorStand armorStand = world.spawn(location, ArmorStand.class);
                    armorStand.setGravity(false);
                    armorStand.setCanPickupItems(false);
                    armorStand.setVisible(false);
                    armorStand.setCustomNameVisible(true);
                    armorStand.setMarker(true);

                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            if (armorStand == null) {
                                if (isDebug()) {
                                    l("entity is null, cancel Iron Text1 entity Task(get kill @e?)");
                                }
                                cancel();
                            }
                            if (game.getState() != running) {
                                if (isDebug()) {
                                    l("is run = false,remove Iron Text1 entity");
                                }
                                armorStand.remove();
                                cancel();
                            }

                            armorStand.setCustomName(t(resText1_Iron.replace("{s}", resTime.get(Material.IRON_INGOT) + "")));
                        }
                    }.runTaskTimer(plugin, 0L, 20L);
                }
                if (!Objects.equals(resText2_Iron, "")) {

                    Location location = resOrgLoc.clone().add(0, resText2_Iron_y, 0);
                    ArmorStand armorStand = world.spawn(location, ArmorStand.class);
                    armorStand.setGravity(false);
                    armorStand.setCanPickupItems(false);
                    armorStand.setVisible(false);
                    armorStand.setCustomNameVisible(true);
                    armorStand.setMarker(true);

                    new BukkitRunnable() {

                        @Override
                        public void run() {
                            if (armorStand == null) {
                                if (isDebug()) {
                                    l("entity is null, cancel Iron Text2 entity Task(get kill @e?)");
                                }
                                cancel();
                            }
                            if (game.getState() != running) {
                                if (isDebug()) {
                                    l("is run = false,remove Iron Text2 entity");
                                }
                                armorStand.remove();
                                cancel();
                            }

                            armorStand.setCustomName(t(resText2_Iron.replace("{s}", resTime.get(Material.IRON_INGOT) + "")));

                        }
                    }.runTaskTimer(plugin, 0L, 20L);
                }
                if (!Objects.equals(resText3_Iron, "")) {

                    Location location = resOrgLoc.clone().add(0, resText3_Iron_y, 0);
                    ArmorStand armorStand = world.spawn(location, ArmorStand.class);
                    armorStand.setGravity(false);
                    armorStand.setCanPickupItems(false);
                    armorStand.setVisible(false);
                    armorStand.setCustomNameVisible(true);
                    armorStand.setMarker(true);

                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            if (armorStand == null) {
                                if (isDebug()) {
                                    l("entity is null, cancel Iron Text3 entity Task(get kill @e?)");
                                }
                                cancel();
                            }
                            if (game.getState() != running) {
                                if (isDebug()) {
                                    l("is run = false,remove Iron Text3 entity");
                                }
                                armorStand.remove();
                                cancel();
                            }

                            armorStand.setCustomName(t(resText3_Iron.replace("{s}", resTime.get(Material.IRON_INGOT) + "")));
                        }
                    }.runTaskTimer(plugin, 0L, 20L);
                }

            }
        }
    }

    private void runCountDownTask(ResourceSpawner resourceSpawner) {
        if (!isRunning) {
            isRunning = true;
            resTime.put(Material.IRON_INGOT, resourceSpawner.getInterval() / 1000);
            new BukkitRunnable() {

                @Override
                public void run() {

                    if (game.getState() != running) {
                        cancel();
                    }

                    resTime.put(Material.IRON_INGOT, resTime.get(Material.IRON_INGOT) - 1);

                    if (resTime.get(Material.IRON_INGOT) <= 0) {
                        resTime.put(Material.IRON_INGOT, resourceSpawner.getInterval() / 1000);
                    }

                }
            }.runTaskTimer(plugin, 0L, 20L);
        }
    }
}
