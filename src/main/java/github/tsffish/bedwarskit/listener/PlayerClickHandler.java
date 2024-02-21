package github.tsffish.bedwarskit.listener;

import github.tsffish.bedwarskit.Main;
import github.tsffish.bedwarskit.config.main.MainConfigHandler;
import github.tsffish.bedwarskit.util.kit.Menu;
import github.tsffish.bedwarskit.util.kit.MenuItem;
import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameManager;
import io.github.bedwarsrel.game.GameState;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import java.util.Objects;

import static github.tsffish.bedwarskit.config.main.MainConfigHandler.*;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;
import static github.tsffish.bedwarskit.util.teamshop.RelShopLevelUp.openForPlayer2v2;
import static github.tsffish.bedwarskit.util.teamshop.RelShopLevelUp.openForPlayer4v4;

public class PlayerClickHandler implements Listener {
    private static final Main plugin = Main.getInstance();
    @EventHandler
    public void on(final PlayerInteractEvent event)
    {
        Player player = event.getPlayer();
        if (player == null || !player.isOnline()) {
            return;
        }

        //String playerName = player.getName();
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
            if (event.getPlayer().getItemInHand() == null) return;
            ItemStack i = event.getPlayer().getItemInHand();
            if (i == null || i.getItemMeta() == null || !i.getItemMeta().hasDisplayName()) {
                return;
            }

            ItemMeta itemMeta = i.getItemMeta();
            String itemName = itemMeta.getDisplayName();

            if (itemName.equalsIgnoreCase(MenuItem.kitMenuItem.getItemMeta().getDisplayName())) {
                player.openInventory(Menu.kitMenu);
            }

            if(lobbyleaveTeam) {
                if (game.getPlayerTeam(player) == null) return;
                if (itemName.contains(game.getPlayerTeam(player).getName())) {
                    player.setVelocity(new Vector(0, 0, 0));

                    player.sendMessage(t(lobbyleaveTeamMess
                                    .replace("{teamColor}",game.getPlayerTeam(player).getChatColor().toString())
                                    .replace("{teamName}",game.getPlayerTeam(player).getName())))
                   ;

                    game.playerLeave(player, false);

                    game.playerJoins(player);


                }
            }



    }


    }
@EventHandler
        public void on(final PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        if (player == null || !player.isOnline()) return;
    if(MainConfigHandler.levelupShop) {
        String worldName = player.getWorld().getName();
        if (BedwarsRel.getInstance() == null) return;
        GameManager gameManager = BedwarsRel.getInstance().getGameManager();
        if (gameManager == null) return;
        Game game = gameManager.getGameOfPlayer(player);
        if (game == null) return;
        if(Objects.equals(levelupShopOpenMode, "click on entity"))
        {
            if (event.getRightClicked() != null && event.getRightClicked().getType() == EntityType.VILLAGER)
            {
                if (Objects.equals(event.getRightClicked().getCustomName(), levelupShopOpenModeEntityName))
                {
                    if (worldName.contains(MainConfigHandler.rushWorld2v2))
                    {
                        openForPlayer2v2(player, game);
                    }
                    else if (worldName.contains(MainConfigHandler.rushWorld4v4))
                    {
                        openForPlayer4v4(player, game);
                    }
                }
            }
        }
    }
}
}