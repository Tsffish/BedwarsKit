package github.tsffish.bedwarskit.listener;

import github.tsffish.bedwarskit.config.main.MainConfigHandler;
import github.tsffish.bedwarskit.util.kit.Menu;
import github.tsffish.bedwarskit.util.kit.MenuItem;
import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameManager;
import io.github.bedwarsrel.game.GameState;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

import static github.tsffish.bedwarskit.config.main.MainConfigHandler.*;
import static github.tsffish.bedwarskit.listener.bedwarsrel.RelItemShop.openShop;
import static github.tsffish.bedwarskit.util.misc.MessSender.consoleSendCommand;

public class PlayerClickHandler implements Listener {
    @EventHandler
    public void on(final PlayerInteractEvent event)
    {
        Player player = event.getPlayer();
        if (player == null || !player.isOnline()) {
            return;
        }

        String playerName = player.getName();
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

        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)
        {

            ItemStack i = event.getPlayer().getItemInHand();
            if (i == null || i.getItemMeta() == null || !i.getItemMeta().hasDisplayName()) {
                return;
            }

            ItemMeta itemMeta = i.getItemMeta();
            String itemName = itemMeta.getDisplayName();
            Material itemType = i.getType();
            String itemTypeText = itemType.toString();


            if (itemName.equalsIgnoreCase(MenuItem.kitMenuItem.getItemMeta().getDisplayName())) {
                player.openInventory(Menu.kitMenu);
            }

            //if(lobbyleaveTeam) {

              //  if (itemName.contains(gameManager.getGameOfPlayer(player).getPlayerTeam(player).getName())) {
               //     player.sendMessage(lobbyleaveTeamMess
               //                     .replace("{teamColor}",game.getPlayerTeam(player).getChatColor().toString())
              //                      .replace("{teamName}",game.getPlayerTeam(player).getName()))
             //      ;
//
             //       game.getPlayerTeam(player).removePlayer(player);
              //      game.playerLeave(player, true);
            //        game.playerJoins(player);
              //  }
           // }



    }


    }
@EventHandler
        public void on(final PlayerInteractEntityEvent event) {
        Player p = event.getPlayer();
        if (p == null || !p.isOnline())
    if(MainConfigHandler.levelupShop)
    {
        if(Objects.equals(levelupShopOpenMode, "click on entity"))
        {
            if (event.getRightClicked() != null && event.getRightClicked().getType() == EntityType.VILLAGER)
            {
                if (Objects.equals(event.getRightClicked().getCustomName(), levelupShopOpenModeEntityName))
                {
                openShop(p);
                }
            }
        }
    }
}
}