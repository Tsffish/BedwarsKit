package github.tsffish.bedwarskit.listener.bedwarsrel;

import github.tsffish.bedwarskit.config.KitConfigHandler;
import github.tsffish.bedwarskit.config.MainConfigHandler;
import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameManager;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import static github.tsffish.bedwarskit.util.RelArmorList.*;
import static github.tsffish.bedwarskit.util.RelPlayerKit.setforcekit;
import static github.tsffish.bedwarskit.util.RelPlayerKit.setkit;

public class RelPlayerRespawn implements Listener {
    static Plugin plugin = github.tsffish.bedwarskit.Main.getProvidingPlugin(github.tsffish.bedwarskit.Main.class);

    @EventHandler
    public void on(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
    }

    public static void playerrespawn(Player player,long delay){

        GameManager gm = BedwarsRel.getInstance().getGameManager();
        Game game =gm.getGameOfPlayer(player);
        if (MainConfigHandler.deathGameMode) {
            boolean isInRushWorld = player.getWorld().getName().contains(MainConfigHandler.rushWorld);
            if (isInRushWorld) {

                if (game != null && game.getPlayerTeam(player).getHeadTarget().getType().toString().contains("BED")){
                    new BukkitRunnable() {
                        @Override
                        public void run() {

                            Game game = gm.getGameOfPlayer(player);
                            String playerteam = game.getPlayerTeam(player).getColor().name();

                            if (KitConfigHandler.kitenable){
                                if (KitConfigHandler.kitForce){
                                    setforcekit(player,KitConfigHandler.kitForceKit);
                                }else {
                                    setkit(player);
                                }
                            }

                            if (MainConfigHandler.startKitCompass) {
                                player.getInventory().addItem(new ItemStack(Material.COMPASS));
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

                            player.setGameMode(GameMode.SURVIVAL);
                            player.setHealth(player.getMaxHealth());

                            cancel();//End

                        }
                    }.runTaskLater(plugin, delay);
                }
            }
        }
    }
}
