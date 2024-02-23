package github.tsffish.bedwarskit.listener;

import github.tsffish.bedwarskit.util.kit.Menu;
import github.tsffish.bedwarskit.util.kit.MenuItem;
import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameManager;
import io.github.bedwarsrel.game.GameState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import static github.tsffish.bedwarskit.config.main.MainConfigHandler.*;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;
import static github.tsffish.bedwarskit.util.player.GetItemInHand.getItemInHand;
import static github.tsffish.bedwarskit.util.teamshop.RelShopLevelUp.openForPlayer2v2;
import static github.tsffish.bedwarskit.util.teamshop.RelShopLevelUp.openForPlayer4v4;

public class PlayerClickHandler implements Listener {
    @EventHandler
    public void on(final PlayerInteractEvent event)
    {
        Player player = event.getPlayer();
        if (player == null || !player.isOnline()) {
            return;
        }

        GameManager gameManager = BedwarsRel.getInstance().getGameManager();
        if (gameManager == null) {
            return;
        }
        Game game = gameManager.getGameOfPlayer(player);
        if (game == null) {
            return;
        }
        if (game.getState() != GameState.WAITING){
            return;
        }

        if (event.getAction() == Action.RIGHT_CLICK_AIR
                || event.getAction() == Action.RIGHT_CLICK_BLOCK)
        {
            if (getItemInHand(player) == null) return;
            ItemStack item = getItemInHand(player);
            if (item == null || item.getItemMeta() == null || !item.getItemMeta().hasDisplayName()) {
                return;
            }

            ItemMeta itemMeta = item.getItemMeta();
            String itemName = itemMeta.getDisplayName();

            if (itemName.equalsIgnoreCase(MenuItem.kitMenuItem.getItemMeta().getDisplayName())) {
                player.openInventory(Menu.kitMenu);
            }

            if(lobbyleaveTeam) {
                if (game.getPlayerTeam(player) == null) return;
                if (itemName.contains(game.getPlayerTeam(player).getName())) {

                    player.sendMessage(t(lobbyleaveTeamMess
                                    .replace("{teamColor}",game.getPlayerTeam(player).getChatColor().toString())
                                    .replace("{teamName}",game.getPlayerTeam(player).getName())))
                   ;


                    game.playerLeave(player, false);
                    player.setVelocity(new Vector(0, 0, 0));
                    game.playerJoins(player);


                }
            }



    }


    }
@EventHandler
        public void on(final PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        if (player == null || !player.isOnline()) return;
    if(levelupShop) {
        String worldName = player.getWorld().getName();
        if (BedwarsRel.getInstance() == null) return;
        GameManager gameManager = BedwarsRel.getInstance().getGameManager();
        if (gameManager == null) return;
        Game game = gameManager.getGameOfPlayer(player);
        if (game == null) return;
        if(levelupShopOpenMode.equals("click on entity"))
        {
                if (event.getRightClicked().getCustomName().equals(levelupShopOpenModeEntityName))
                {
                    if (worldName.contains(rushWorld2v2))
                    {
                        openForPlayer2v2(player, game);
                    }
                    else if (worldName.contains(rushWorld4v4))
                    {
                        openForPlayer4v4(player, game);
                    }
                }

        }
    }
}
}