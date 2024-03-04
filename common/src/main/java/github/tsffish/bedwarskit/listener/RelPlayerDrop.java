package github.tsffish.bedwarskit.listener;

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

import static github.tsffish.bedwarskit.config.main.MainConfigHandler.noMoveList;

public class RelPlayerDrop implements Listener {
    private static final Material woodSword = Material.WOOD_SWORD;
    private static final Material stoneSword = Material.STONE_SWORD;
    private static final Material ironSword = Material.IRON_SWORD;
    private static final Material diamondSword = Material.DIAMOND_SWORD;
    private static final GameMode spectator = GameMode.SPECTATOR;
    private static final GameState running = GameState.RUNNING;
        @EventHandler
        public void on(final PlayerDropItemEvent event) {
            if (event.getItemDrop().getItemStack() == null){
                return;
            }
            ItemStack item = event.getItemDrop().getItemStack();
            Material itemType = item.getType();
            String itemTypeText = itemType.toString().toUpperCase();
            for (String list : noMoveList){
                    if (itemTypeText.contains(list)){
                        event.setCancelled(true);
                        break;
                    }
            }

            if (event.getPlayer() == null) {
                return;
            }
            Player player = event.getPlayer();
            if (!player.isOnline()) {
                return;
            }
            if (BedwarsRel.getInstance() == null) {
                return;
            }

            BedwarsRel bedwarsRel = BedwarsRel.getInstance();
            if (bedwarsRel.getGameManager() == null) {
                return;
            }
            GameManager gameManager = bedwarsRel.getGameManager();
            if (gameManager.getGameOfPlayer(player) == null){
                return;
            }

            Game game = gameManager.getGameOfPlayer(player);
            
            if (game.getState() == running
                    && player.getGameMode() != spectator) {

                ItemStack droppedItem = event.getItemDrop().getItemStack();
                PlayerInventory pi = player.getInventory();

                if (droppedItem.getType() == woodSword) {
                        event.setCancelled(
                                   !pi.contains(woodSword)
                                && !pi.contains(stoneSword)
                                && !pi.contains(ironSword)
                                && !pi.contains(diamondSword)
                        );
                }
            }
        }
}
