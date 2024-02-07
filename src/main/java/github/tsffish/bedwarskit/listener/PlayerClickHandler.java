package github.tsffish.bedwarskit.listener;

import github.tsffish.bedwarskit.config.MainConfigHandler;
import github.tsffish.bedwarskit.config.kit.Menu;
import github.tsffish.bedwarskit.config.kit.MenuItem;
import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameManager;
import io.github.bedwarsrel.game.GameState;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

import static github.tsffish.bedwarskit.config.MainConfigHandler.levelupShopOpenMode;
import static github.tsffish.bedwarskit.config.MainConfigHandler.levelupShopOpenModeEntityName;
import static github.tsffish.bedwarskit.listener.bedwarsrel.RelItemShop.openShop;

public class PlayerClickHandler implements Listener
{
    @EventHandler
    public void on(PlayerInteractEvent event)
    {
        Player player = event.getPlayer();
        if (player == null || !player.isOnline()) return;

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
            if (i == null || i.getItemMeta() == null || !i.getItemMeta().hasDisplayName()) return;

            if (Objects.equals(i.getItemMeta().getDisplayName(), MenuItem.kitMenuItem.getItemMeta().getDisplayName()))
            {

                player.openInventory(Menu.kitMenu);
            }
    }


    }
@EventHandler
        public void on(PlayerInteractEntityEvent event) {
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