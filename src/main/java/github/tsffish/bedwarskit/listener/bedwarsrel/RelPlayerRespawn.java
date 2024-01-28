package github.tsffish.bedwarskit.listener.bedwarsrel;

import github.tsffish.bedwarskit.config.MainConfigHandler;
import github.tsffish.bedwarskit.util.RelArmorColor;
import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameManager;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import static github.tsffish.bedwarskit.util.RelArmorList.*;

public class RelPlayerRespawn implements Listener {
    Plugin plugin = github.tsffish.bedwarskit.Main.getProvidingPlugin(github.tsffish.bedwarskit.Main.class);

    @EventHandler
    public void on(PlayerRespawnEvent e) {
        Player p = e.getPlayer();

        GameManager gm = BedwarsRel.getInstance().getGameManager();
        Game game =gm.getGameOfPlayer(p);
        if (MainConfigHandler.deathGameMode) {
            boolean isInRushWorld = p.getWorld().getName().contains(MainConfigHandler.rushWorld);
            if (isInRushWorld) {
                if (game != null && game.getPlayerTeam(p).getHeadTarget().getType().toString().contains("BED")){
                new BukkitRunnable() {
                    @Override
                    public void run() {

                            Player player = e.getPlayer();
                            Game game = gm.getGameOfPlayer(player);
                            String playerteam = game.getPlayerTeam(player).getColor().name();

                            ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
                            ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
                            ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
                            ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);

                            LeatherArmorMeta helmetMeta = (LeatherArmorMeta) helmet.getItemMeta();
                            LeatherArmorMeta chestplateMeta = (LeatherArmorMeta) chestplate.getItemMeta();
                            LeatherArmorMeta leggingsMeta = (LeatherArmorMeta) leggings.getItemMeta();
                            LeatherArmorMeta bootsMeta = (LeatherArmorMeta) boots.getItemMeta();
                        switch (playerteam) {
                            case "RED":
                                helmetMeta.setColor(RelArmorColor.red);
                                chestplateMeta.setColor(RelArmorColor.red);
                                leggingsMeta.setColor(RelArmorColor.red);
                                bootsMeta.setColor(RelArmorColor.red);
                                break;
                            case "BLUE":
                                helmetMeta.setColor(RelArmorColor.blue);
                                chestplateMeta.setColor(RelArmorColor.blue);
                                leggingsMeta.setColor(RelArmorColor.blue);
                                bootsMeta.setColor(RelArmorColor.blue);
                                break;
                            case "GREEN":
                                helmetMeta.setColor(RelArmorColor.green);
                                chestplateMeta.setColor(RelArmorColor.green);
                                leggingsMeta.setColor(RelArmorColor.green);
                                bootsMeta.setColor(RelArmorColor.green);
                                break;
                            case "YELLOW":
                                helmetMeta.setColor(RelArmorColor.yellow);
                                chestplateMeta.setColor(RelArmorColor.yellow);
                                leggingsMeta.setColor(RelArmorColor.yellow);
                                bootsMeta.setColor(RelArmorColor.yellow);
                                break;
                            case "WHITE":
                                helmetMeta.setColor(RelArmorColor.white);
                                chestplateMeta.setColor(RelArmorColor.white);
                                leggingsMeta.setColor(RelArmorColor.white);
                                bootsMeta.setColor(RelArmorColor.white);
                                break;
                            case "AQUA":
                                helmetMeta.setColor(RelArmorColor.aqua);
                                chestplateMeta.setColor(RelArmorColor.aqua);
                                leggingsMeta.setColor(RelArmorColor.aqua);
                                bootsMeta.setColor(RelArmorColor.aqua);
                                break;
                            case "LIGHT_PURPLE":
                                helmetMeta.setColor(RelArmorColor.pink);
                                chestplateMeta.setColor(RelArmorColor.pink);
                                leggingsMeta.setColor(RelArmorColor.pink);
                                bootsMeta.setColor(RelArmorColor.pink);
                                break;
                            case "GRAY":
                                helmetMeta.setColor(RelArmorColor.gray);
                                chestplateMeta.setColor(RelArmorColor.gray);
                                leggingsMeta.setColor(RelArmorColor.gray);
                                bootsMeta.setColor(RelArmorColor.gray);
                                break;
                            default:
                                helmetMeta.setColor(Color.fromRGB(255, 165, 0));
                                chestplateMeta.setColor(Color.fromRGB(255, 165, 0));
                                leggingsMeta.setColor(Color.fromRGB(255, 165, 0));
                                bootsMeta.setColor(Color.fromRGB(255, 165, 0));
                        }


                            helmet.setItemMeta(helmetMeta);
                            chestplate.setItemMeta(chestplateMeta);
                            leggings.setItemMeta(leggingsMeta);
                            boots.setItemMeta(bootsMeta);

                        player.getInventory().setHelmet(helmet);
                            player.getInventory().setChestplate(chestplate);
                            player.getInventory().setLeggings(leggings);
                            player.getInventory().setBoots(boots);


                            if (MainConfigHandler.startKitCompass) {
                                player.getInventory().addItem(new ItemStack(Material.COMPASS));
                            }

                            if (MainConfigHandler.rushModeSpeed) {
                                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999, MainConfigHandler.rushModeSpeedLevel), true);
                            }


                            String playerName = player.getName();

                            if (armorChain.contains(playerName)) {
                                if (!armorIron.contains(playerName) && !armorDiamond.contains(playerName)) {
                                    player.getInventory().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
                                    player.getInventory().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
                                }
                            }

                            if (armorIron.contains(playerName)) {
                                if (!armorIron.contains(playerName)) {
                                    player.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
                                    player.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
                                }
                            }
                            if (armorDiamond.contains(playerName)) {
                                player.getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
                                player.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
                            }

                            cancel();//End





                    }
                }.runTaskLater(plugin, 10L);
            }
        }
        }
    }
}
