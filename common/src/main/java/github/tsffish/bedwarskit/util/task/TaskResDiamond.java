package github.tsffish.bedwarskit.util.task;

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

import java.util.Objects;

import static github.tsffish.bedwarskit.config.res.ResConfigHandler.*;
import static github.tsffish.bedwarskit.util.PluginState.isDebug;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;
import static github.tsffish.bedwarskit.util.misc.MessSender.l;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class TaskResDiamond {
    private BedwarsKit plugin = BedwarsKit.getInstance();
    private Game game;
    private World world;
    private GameState running = GameState.RUNNING;
    private int resTime;
    private boolean isRunning;
    public TaskResDiamond(Game game) {
        this.game = game;
        this.world = game.getRegion().getWorld();
        this.resTime = 0;
    }

    public void runTask() {
        for (ResourceSpawner resourceSpawner : game.getResourceSpawners()) {
            if (resourceSpawner.getResources().get(0).getType() == Material.DIAMOND) {
                Location resOrgLoc = resourceSpawner.getLocation();

                runCountDownTask(resourceSpawner);
                if (resBlock_Diamond) {
                    if (resBlock_Diamond_spinSpeed < 1) {
                        resBlock_Diamond_spinSpeed = 1L;
                    }
                    Location location = resOrgLoc.clone().add(0, resBlock_Diamond_y, 0);

                    ArmorStand armorStand = world.spawn(location, ArmorStand.class);
                    armorStand.setGravity(false);
                    armorStand.setCanPickupItems(false);
                    armorStand.setVisible(false);
                    ItemStack item = new ItemStack(Material.DIAMOND_BLOCK);

                    armorStand.setHelmet(item);

                    new BukkitRunnable() {
                        float angle = 0;

                        @Override
                        public void run() {
                            if (armorStand == null) {
                                if (isDebug()) {
                                    l("entity is null, cancel Diamond Block entity Task(get kill @e?)");
                                }
                                cancel();
                            }
                            if (game.getState() != running) {
                                if (isDebug()) {
                                    l("is run = false,remove Diamond Block entity");
                                }
                                armorStand.remove();
                                cancel();
                            }

                            if (resBlock_Diamond_isSmall) {
                                armorStand.setSmall(true);
                            } else {
                                armorStand.setSmall(false);
                            }


                            angle += resBlock_Diamond_yawPerTick;
                            if (angle >= 360) {
                                angle = angle - 360;
                            }


                            Location currentLocation = armorStand.getLocation();
                            currentLocation.setYaw(angle);
                            armorStand.teleport(currentLocation);

                        }
                    }.runTaskTimer(plugin, 0L, resBlock_Diamond_spinSpeed);


                }
                if (!Objects.equals(resText1_Diamond, "")) {
                    Location location = resOrgLoc.clone().add(0, resText1_Diamond_y, 0);
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
                                    l("entity is null, cancel Diamond Text1 entity Task(get kill @e?)");
                                }
                                cancel();
                            }
                            if (game.getState() != running) {
                                if (isDebug()) {
                                    l("is run = false,remove Diamond Text1 entity");
                                }
                                armorStand.remove();
                                cancel();
                            }
                            armorStand.setCustomName(t(resText1_Diamond.replace("{s}", resTime + "")));
                        }
                    }.runTaskTimer(plugin, 0L, 20L);
                }
                if (!Objects.equals(resText2_Diamond, "")) {

                    Location location = resOrgLoc.clone().add(0, resText2_Diamond_y, 0);
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
                                    l("entity is null, cancel Diamond Text2 entity Task(get kill @e?)");
                                }
                                cancel();
                            }
                            if (game.getState() != running) {
                                if (isDebug()) {
                                    l("is run = false,remove Diamond Text2 entity");
                                }
                                armorStand.remove();
                                cancel();
                            }

                            armorStand.setCustomName(t(resText2_Diamond.replace("{s}", resTime + "")));

                        }
                    }.runTaskTimer(plugin, 0L, 20L);
                }
                if (!Objects.equals(resText3_Diamond, "")) {

                    Location location = resOrgLoc.clone().add(0, resText3_Diamond_y, 0);
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
                                    l("entity is null, cancel Diamond Text3 entity Task(get kill @e?)");
                                }
                                cancel();
                            }
                            if (game.getState() != running) {
                                if (isDebug()) {
                                    l("is run = false,remove Diamond Text3 entity");
                                }
                                armorStand.remove();
                                cancel();
                            }

                            armorStand.setCustomName(t(resText3_Diamond.replace("{s}", resTime + "")));
                        }
                    }.runTaskTimer(plugin, 0L, 20L);
                }

            }
        }
    }

    private void runCountDownTask(ResourceSpawner resourceSpawner) {
        if (!isRunning) {
            isRunning = true;

            resTime = resourceSpawner.getInterval() / 1000;
            new BukkitRunnable() {

                @Override
                public void run() {

                    if (game.getState() != running) {
                        cancel();
                    }

                    resTime = resTime - 1;

                    if (resTime <= 0) {
                        resTime = resourceSpawner.getInterval() / 1000;
                    }

                }
            }.runTaskTimer(plugin, 0L, 20L);
        }
    }
}
