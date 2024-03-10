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
public class ResEmeraldTextTask {
    private BedwarsKit plugin = BedwarsKit.getInstance();
    private Game game;
    private World world;
    private Location resOrgLoc;
    private GameState running = GameState.RUNNING;
    private HashMap<Material, Integer> resTime;
    private boolean isRunning;

    public ResEmeraldTextTask(Game game) {
        this.game = game;
        this.world = game.getRegion().getWorld();
        this.resTime = new HashMap<>();
    }

    public void runTask() {
        for (ResourceSpawner resourceSpawner : game.getResourceSpawners()) {
            if (resourceSpawner.getResources().get(0).getType() == Material.EMERALD) {
                resOrgLoc = resourceSpawner.getLocation();

                runCountDownTask(resourceSpawner);
                if (resBlock_Emerald) {
                    if (resBlock_Emerald_spinSpeed < 1) {
                        resBlock_Emerald_spinSpeed = 1L;
                    }
                    Location location = resOrgLoc.clone().add(0, resBlock_Emerald_y, 0);

                    ArmorStand armorStand = world.spawn(location, ArmorStand.class);
                    armorStand.setGravity(false);
                    armorStand.setCanPickupItems(false);
                    armorStand.setVisible(false);
                    ItemStack item = new ItemStack(Material.EMERALD_BLOCK);
                    armorStand.setHelmet(item);

                    new BukkitRunnable() {
                        float angle = 0;

                        @Override
                        public void run() {
                            if (armorStand == null) {
                                if (isDebug()) {
                                    l("entity is null, cancel Emerald Block entity Task(get kill @e?)");
                                }
                                cancel();
                            }
                            if (game.getState() != running) {
                                if (isDebug()) {
                                    l("is run = false,remove Emerald Block entity");
                                }
                                armorStand.remove();
                                cancel();
                            }

                            angle += resBlock_Emerald_yawPerTick;
                            if (angle >= 360) {
                                angle = angle - 360;
                            }
                            if (resBlock_Emerald_isSmall){
                                armorStand.setSmall(true);
                            }else {
                                armorStand.setSmall(false);
                            }
                            Location currentLocation = armorStand.getLocation();
                            currentLocation.setYaw(angle);
                            armorStand.teleport(currentLocation);


                        }
                    }.runTaskTimer(plugin, 0L, resBlock_Emerald_spinSpeed);


                }
                if (!Objects.equals(resText1_Emerald, "")) {
                    Location location = resOrgLoc.clone().add(0, resText1_Emerald_y, 0);
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
                                    l("entity is null, cancel Emerald Text1 entity Task(get kill @e?)");
                                }
                                cancel();
                            }
                            if (game.getState() != running) {
                                if (isDebug()) {
                                    l("is run = false,remove Emerald Text1 entity");
                                }
                                armorStand.remove();
                                cancel();
                            }

                            armorStand.setCustomName(t(resText1_Emerald.replace("{s}", resTime.get(Material.EMERALD) + "")));
                        }
                    }.runTaskTimer(plugin, 0L, 20L);
                }
                if (!Objects.equals(resText2_Emerald, "")) {

                    Location location = resOrgLoc.clone().add(0, resText2_Emerald_y, 0);
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
                                    l("entity is null, cancel Emerald Text2 entity Task(get kill @e?)");
                                }
                                cancel();
                            }
                            if (game.getState() != running) {
                                if (isDebug()) {
                                    l("is run = false,remove Emerald Text2 entity");
                                }
                                armorStand.remove();
                                cancel();
                            }

                            armorStand.setCustomName(t(resText2_Emerald.replace("{s}", resTime.get(Material.EMERALD) + "")));

                        }
                    }.runTaskTimer(plugin, 0L, 20L);
                }
                if (!Objects.equals(resText3_Emerald, "")) {

                    Location location = resOrgLoc.clone().add(0, resText3_Emerald_y, 0);
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
                                    l("entity is null, cancel Emerald Text3 entity Task(get kill @e?)");
                                }
                                cancel();
                            }
                            if (game.getState() != running) {
                                if (isDebug()) {
                                    l("is run = false,remove Emerald Text3 entity");
                                }
                                armorStand.remove();
                                cancel();
                            }

                            armorStand.setCustomName(t(resText3_Emerald.replace("{s}", resTime.get(Material.EMERALD) + "")));
                        }
                    }.runTaskTimer(plugin, 0L, 20L);
                }

            }
        }
    }

    private void runCountDownTask(ResourceSpawner resourceSpawner) {
        if (!isRunning) {
            isRunning = true;
            resTime.put(Material.EMERALD, resourceSpawner.getInterval() / 1000);
            new BukkitRunnable() {

                @Override
                public void run() {

                    if (game.getState() != running) {
                        cancel();
                    }

                    resTime.put(Material.EMERALD, resTime.get(Material.EMERALD) - 1);

                    if (resTime.get(Material.EMERALD) <= 0) {
                        resTime.put(Material.EMERALD, resourceSpawner.getInterval() / 1000);
                    }

                }
            }.runTaskTimer(plugin, 0L, 20L);
        }
    }
}
