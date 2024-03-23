package github.tsffish.bedwarskit.listener;


import github.tsffish.bedwarskit.util.player.SoundPlayer;
import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameManager;
import io.github.bedwarsrel.game.Team;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import static github.tsffish.bedwarskit.config.main.MainConfigHandler.resSp;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class RelPickupItem implements Listener {
    @EventHandler(priority = EventPriority.HIGH)
    public void on(PlayerPickupItemEvent event) {
        if (!resSp){
            return;
        }

        Player player = event.getPlayer();
        if (BedwarsRel.getInstance() == null) {
            return;
        }
        BedwarsRel bedwarsRel = BedwarsRel.getInstance();
        if (bedwarsRel.getGameManager() == null) {
            return;
        }
        GameManager gameManager = bedwarsRel.getGameManager();
        if (gameManager.getGameOfPlayer(player) == null) {
            return;
        }

        Game game = gameManager.getGameOfPlayer(player);

        if (game.isSpectator(player) || game.getRespawnProtections().containsKey(player)) {
            event.setCancelled(true);
            return;
        }

        ItemStack item = event.getItem().getItemStack();
        Material itemType = item.getType();

        if (
                           itemType == Material.IRON_INGOT
                        || itemType == Material.GOLD_INGOT
        ) {
            Location current = event.getItem().getLocation();
            if (game.getPlayerTeam(player) != null) {
                Team playerTeam = game.getPlayerTeam(player);

                for (Player list : game.getPlayers()) {

                    Location listLoc = list.getLocation();

                    if (listLoc.distance(current) < 2.9) {
                        if (
                                game.getPlayerTeam(list) == playerTeam
                                        && list != player
                        ) {
                            PlayerInventory lpi = list.getInventory();
                            lpi.addItem(item);
                            playPickUpSound(list);

                        }
                    }

                }
            }
        }


    }

    void playPickUpSound(Player player) {
        SoundPlayer.ITEM_PICKUP(player, 1);
    }
}
