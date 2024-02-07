package github.tsffish.bedwarskit.listener.bedwarsrel;

import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameState;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

public class RelPlayerDrop implements Listener {

        @EventHandler
        public void on(PlayerDropItemEvent event) {
            Player player = event.getPlayer();
            Game game = BedwarsRel.getInstance().getGameManager().getGameOfPlayer(player);
            
            if (game != null && player != null && player.isOnline() && game.getState() == GameState.RUNNING && player.getGameMode() == GameMode.SURVIVAL) {

                ItemStack droppedItem = event.getItemDrop().getItemStack();

                if (droppedItem.getType() == Material.WOOD_SWORD) { 
                    boolean hasOtherSword = false; 
                    ItemStack[] inventory = player.getInventory().getContents(); 

                    for (ItemStack item : inventory) {
                        if (item != null && item.getType().name().endsWith("_SWORD") && item != droppedItem) {
                            
                            hasOtherSword = true;
                        }
                    }

                    if (!hasOtherSword) {
                        event.setCancelled(true); 
                    }
                }
            }
            }
        }
