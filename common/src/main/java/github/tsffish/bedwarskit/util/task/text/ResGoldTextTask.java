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
public class ResGoldTextTask {
    private BedwarsKit plugin = BedwarsKit.getInstance();
    private Game game;
    private World world;
    private Location resOrgLoc;
    private GameState running = GameState.RUNNING;
    private HashMap<Material, Integer> resTime;
    private boolean isRunning;

    public ResGoldTextTask(Game game) {
        this.game = game;
        this.world = game.getRegion().getWorld();
        this.resTime = new HashMap<>();
    }

    public void runTask() {
        for (ResourceSpawner resourceSpawner : game.getResourceSpawners()) {
            if (resourceSpawner.getResources().get(0).getType() == Material.GOLD_INGOT) {
                resOrgLoc = resourceSpawner.getLocation();

                runCountDownTask(resourceSpawner);
                if (resBlock_Gold) {
                    if (resBlock_Gold_spinSpeed < 1) {
                        resBlock_Gold_spinSpeed = 1L;
                    }
                    Location location = resOrgLoc.clone().add(0, resBlock_Gold_y, 0);

                    ArmorStand armorStand = world.spawn(location, ArmorStand.class);
                    armorStand.setGravity(false);
                    armorStand.setCanPickupItems(false);
                    armorStand.setVisible(false);
                    ItemStack item = new ItemStack(Material.GOLD_BLOCK);
                    armorStand.setHelmet(item);

                    new BukkitRunnable() {
                        float angle = 0;

                        @Override
                        public void run() {
                            if (armorStand == null) {
                                if (isDebug()) {
                                    l("entity is null, cancel Gold Block entity Task(get kill @e?)");
                                }
                                cancel();
                            }
                            if (game.getState() != running) {
                                if (isDebug()) {
                                    l("is run = false,remove Gold Block entity");
                                }
                                armorStand.remove();
                                cancel();
                            }


                            if (resBlock_Gold_isSmall){
                                armorStand.setSmall(true);
                            }else {
                                armorStand.setSmall(false);
                            }
                            angle += resBlock_Gold_yawPerTick;
                            if (angle >= 360) {
                                angle = angle - 360;
                            }

                            Location currentLocation = armorStand.getLocation();
                            currentLocation.setYaw(angle);
                            armorStand.teleport(currentLocation);
                        }
                    }.runTaskTimer(plugin, 0L, resBlock_Gold_spinSpeed);


                }
                if (!Objects.equals(resText1_Gold, "")) {
                    Location location = resOrgLoc.clone().add(0, resText1_Gold_y, 0);
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
                                    l("entity is null, cancel Gold Text1 entity Task(get kill @e?)");
                                }
                                cancel();
                            }
                            if (game.getState() != running) {
                                if (isDebug()) {
                                    l("is run = false,remove Gold Text1 entity");
                                }
                                armorStand.remove();
                                cancel();
                            }

                            armorStand.setCustomName(t(resText1_Gold.replace("{s}", resTime.get(Material.GOLD_INGOT) + "")));
                        }
                    }.runTaskTimer(plugin, 0L, 20L);
                }
                if (!Objects.equals(resText2_Gold, "")) {

                    Location location = resOrgLoc.clone().add(0, resText2_Gold_y, 0);
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
                                    l("entity is null, cancel Gold Text2 entity Task(get kill @e?)");
                                }
                                cancel();
                            }
                            if (game.getState() != running) {
                                if (isDebug()) {
                                    l("is run = false,remove Gold Text2 entity");
                                }
                                armorStand.remove();
                                cancel();
                            }

                            armorStand.setCustomName(t(resText2_Gold.replace("{s}", resTime.get(Material.GOLD_INGOT) + "")));

                        }
                    }.runTaskTimer(plugin, 0L, 20L);
                }
                if (!Objects.equals(resText3_Gold, "")) {

                    Location location = resOrgLoc.clone().add(0, resText3_Gold_y, 0);
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
                                    l("entity is null, cancel Gold Text3 entity Task(get kill @e?)");
                                }
                                cancel();
                            }
                            if (game.getState() != running) {
                                if (isDebug()) {
                                    l("is run = false,remove Gold Text3 entity");
                                }
                                armorStand.remove();
                                cancel();
                            }

                            armorStand.setCustomName(t(resText3_Gold.replace("{s}", resTime.get(Material.GOLD_INGOT) + "")));
                        }
                    }.runTaskTimer(plugin, 0L, 20L);
                }

            }
        }
    }

    private void runCountDownTask(ResourceSpawner resourceSpawner) {
        if (!isRunning) {
            isRunning = true;
            resTime.put(Material.GOLD_INGOT, resourceSpawner.getInterval() / 1000);
            new BukkitRunnable() {

                @Override
                public void run() {

                    if (game.getState() != running) {
                        cancel();
                    }

                    resTime.put(Material.GOLD_INGOT, resTime.get(Material.GOLD_INGOT) - 1);

                    if (resTime.get(Material.GOLD_INGOT) <= 0) {
                        resTime.put(Material.GOLD_INGOT, resourceSpawner.getInterval() / 1000);
                    }

                }
            }.runTaskTimer(plugin, 0L, 20L);
        }
    }
}
