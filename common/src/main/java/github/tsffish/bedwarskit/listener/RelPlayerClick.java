package github.tsffish.bedwarskit.listener;

import github.tsffish.bedwarskit.BedwarsKit;
import github.tsffish.bedwarskit.util.kit.KitMenu;
import github.tsffish.bedwarskit.util.kit.KitOpenItem;
import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameManager;
import io.github.bedwarsrel.game.GameState;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import static github.tsffish.bedwarskit.config.main.MainConfigHandler.*;
import static github.tsffish.bedwarskit.util.misc.GetItemInHand.getItemInHand;
import static github.tsffish.bedwarskit.util.misc.MessSender.consoleSendCommand;
import static github.tsffish.bedwarskit.util.teamshop.ShopMenu.openForPlayer2v2;
import static github.tsffish.bedwarskit.util.teamshop.ShopMenu.openForPlayer4v4;
/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class RelPlayerClick implements Listener {
    private static final BedwarsKit plugin = BedwarsKit.getInstance();
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
            if (item == null || item.getItemMeta() == null) {
                return;
            }

            ItemMeta itemMeta = item.getItemMeta();
            String itemName = itemMeta.getDisplayName();

            if (itemName.equalsIgnoreCase(KitOpenItem.kitMenuItem.getItemMeta().getDisplayName())) {
                player.openInventory(KitMenu.kitMenu);
            }


            String itemTypeText = item.getType().toString();

            if (dieOutGameItem_playAgain) {
                if (itemTypeText.equals(dieOutGameItem_playAgain_ItemType.toString())
                        && item.getItemMeta().getDisplayName().equals(dieOutGameItem_playAgain_ItemName)) {
                    String willSend = dieOutGameItem_playAgain_ClickSendCommand.replace("{player}", player.getName());
                    consoleSendCommand(willSend);
                    return;
                }
            }


            if(lobbyleaveTeam) {
                if (game.getPlayerTeam(player) == null) {
                    return;
                }
                if (itemName.contains(game.getPlayerTeam(player).getName())) {

                    game.playerLeave(player, false);
                    player.setSprinting(false);
                    player.setVelocity(new Vector(0, 0, 0));
                    Location location = player.getLocation();
                    location.setDirection(player.getLocation().getDirection());

                    game.playerJoins(player);
                    new BukkitRunnable() {

                        public void run() {


                            player.teleport(player);
                        }
                    }.runTaskLater(plugin, 1L);
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
        if(levelupShopOpenMode.equals("click on entity")) {
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