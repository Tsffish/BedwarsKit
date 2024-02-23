package github.tsffish.bedwarskit.listener.bedwarsrel;

import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameManager;
import io.github.bedwarsrel.game.GameState;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class RelPlayerDrop implements Listener {
    Material woodSword = Material.WOOD_SWORD;
    Material stoneSword = Material.STONE_SWORD;
    Material ironSword = Material.IRON_SWORD;
    Material diamondSword = Material.DIAMOND_SWORD;
    ItemStack ws = new ItemStack(woodSword);
        @EventHandler
        public void on(final PlayerDropItemEvent event) {
            Player player = event.getPlayer();
            if (player == null || !player.isOnline()) return;
            if (BedwarsRel.getInstance() == null) return;
            BedwarsRel bedwarsRel = BedwarsRel.getInstance();
            if (bedwarsRel.getGameManager() == null) return;
            GameManager gameManager = bedwarsRel.getGameManager();
            Game game = gameManager.getGameOfPlayer(player);
            if (game == null) return;
            
            if (player.isOnline() && game.getState() == GameState.RUNNING
                    && player.getGameMode() == GameMode.SURVIVAL) {

                ItemStack droppedItem = event.getItemDrop().getItemStack();
                PlayerInventory pi = player.getInventory();
                if (droppedItem.getType() == Material.WOOD_SWORD) {
                    if (!pi.contains(woodSword) && !pi.contains(stoneSword) && !pi.contains(ironSword) && !pi.contains(diamondSword)) {
                        event.setCancelled(true);
                    }
                }
            }
            }
        }
