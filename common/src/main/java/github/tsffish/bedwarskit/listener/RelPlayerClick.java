package github.tsffish.bedwarskit.listener;

import github.tsffish.bedwarskit.util.kit.KitOpenItem;
import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameManager;
import io.github.bedwarsrel.game.GameState;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

import static github.tsffish.bedwarskit.config.main.MainConfigHandler.*;
import static github.tsffish.bedwarskit.util.kit.KitMenu.kitMenu;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;
import static github.tsffish.bedwarskit.util.misc.MessSender.consoleSendCommand;
import static github.tsffish.bedwarskit.util.player.GetItemInHand.getItemInHand;
import static github.tsffish.bedwarskit.util.player.RelEditGame.isEditGamePlayer;
import static github.tsffish.bedwarskit.util.teamshop.ShopMenu.openForPlayer2v2;
import static github.tsffish.bedwarskit.util.teamshop.ShopMenu.openForPlayer4v4;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class RelPlayerClick implements Listener {
    private static final GameState waiting = GameState.WAITING;
    private static final GameState running = GameState.RUNNING;

    @EventHandler
    public void on(final PlayerInteractEvent event) {
        if (event.getPlayer() == null) {
            return;
        }

        Player player = event.getPlayer();
        if (!player.isOnline()) {
            return;
        }

        String playerName = player.getName();
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

        if (event.getAction() == Action.RIGHT_CLICK_AIR
                || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (getItemInHand(player) == null) {
                return;
            }
            ItemStack item = getItemInHand(player);
            if (item == null || item.getItemMeta() == null) {
                return;
            }

            ItemMeta itemMeta = item.getItemMeta();
            Material itemType = item.getType();
            String itemName = itemMeta.getDisplayName();
            String itemTypeText = itemType.toString().toUpperCase();

            if (game.getState() == running) {
                if (dieOutGameItem_playAgain) {
                    if (itemTypeText.equals(dieOutGameItem_playAgain_ItemType.toString())
                            && item.getItemMeta().getDisplayName().equals(dieOutGameItem_playAgain_ItemName)) {
                        String willSend = dieOutGameItem_playAgain_ClickSendCommand.
                                replace("{player}", playerName);
                        consoleSendCommand(willSend);
                        return;
                    }
                }
            }

            if (game.getState() == waiting) {
                if (itemName.equalsIgnoreCase(KitOpenItem.kitMenuItem.getItemMeta().getDisplayName())) {
                    player.openInventory(kitMenu);
                    return;
                }


                if (lobbyleaveTeam) {
                    if (player.getInventory().getItemInHand().getType() == Material.LEATHER_CHESTPLATE) {
                        if (game.getPlayerTeam(player) == null) {
                            return;
                        }

                        game.playerLeave(player, false);
                        game.playerJoins(player);
                    }
                }
            }
        }
    }

    @EventHandler
    public void on(final PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        if (player == null || !player.isOnline()) return;
        UUID playerUUID = player.getUniqueId();
        if (levelupShop) {
            String worldName = player.getWorld().getName();
            if (BedwarsRel.getInstance() == null) return;
            GameManager gameManager = BedwarsRel.getInstance().getGameManager();
            if (gameManager == null) return;
            Game game = gameManager.getGameOfPlayer(player);
            if (game == null) return;
            if (levelupShopOpenMode.equals("click on entity")) {
                if (!isEditGamePlayer(playerUUID)) {
                    if (event.getRightClicked().getCustomName() != null) {
                        if (event.getRightClicked().getCustomName().equals(t(levelupShopOpenModeEntityName))) {
                            if (worldName.contains(gameWorld2v2) || bungeeMode.equals("2v2")) {
                                openForPlayer2v2(player, game);
                            } else if (worldName.contains(gameWorld4v4) || bungeeMode.equals("4v4")) {
                                openForPlayer4v4(player, game);
                            }
                        }
                    }
                }
            }
        }
    }
}