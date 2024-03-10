package github.tsffish.bedwarskit.listener.editgame;

import github.tsffish.bedwarskit.util.player.GetItemInHand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

import static github.tsffish.bedwarskit.command.BaseCommand.toggleEdit;
import static github.tsffish.bedwarskit.config.main.MainConfigHandler.levelupShopOpenModeEntityName;
import static github.tsffish.bedwarskit.config.main.MainConfigHandler.shopItemEntityName;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;
import static github.tsffish.bedwarskit.util.misc.StringMgr.meanEntityNameRemove;
import static github.tsffish.bedwarskit.util.misc.StringMgr.meanEntityNameSetTo;
import static github.tsffish.bedwarskit.util.player.PlayerSender.sendMessage;
import static github.tsffish.bedwarskit.util.player.RelEditGame.isEditGamePlayer;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class EditPlayerClick implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void on(PlayerInteractAtEntityEvent event) {
        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();
        if (isEditGamePlayer(playerUUID)){
            event.setCancelled(true);
        }

        if (event.getRightClicked() != null) {
            Entity entity = event.getRightClicked();
            ItemStack itemInHand = GetItemInHand.getItemInHand(player);

            if (itemInHand != null) {
                switch (itemInHand.getType()) {
                    case NAME_TAG:
                        if (true) {
                            String willSend = meanEntityNameSetTo
                                    .replace("{orgName}", entity.getCustomName() + "")
                                    .replace("{newName}", levelupShopOpenModeEntityName);
                            entity.setCustomName(t(levelupShopOpenModeEntityName));
                            sendMessage(player, willSend);
                        }
                        break;
                    case ITEM_FRAME:
                        if (true) {
                            String willSend = meanEntityNameSetTo
                                    .replace("{orgName}", entity.getCustomName() + "")
                                    .replace("{newName}", shopItemEntityName);
                            entity.setCustomName(t(shopItemEntityName));
                            sendMessage(player, willSend);
                        }
                        break;
                    case REDSTONE:
                        String willSend = meanEntityNameRemove
                                .replace("{orgName}", entity.getCustomName() + "");
                        sendMessage(player, willSend);
                        entity.setCustomName(" ");
                        break;
                    default:
                        break;
                }
            }
        }
    }



    @EventHandler(priority = EventPriority.HIGHEST)
    public void on(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();
        if (isEditGamePlayer(playerUUID)){
            event.setCancelled(true);
        }

            ItemStack itemInHand = GetItemInHand.getItemInHand(player);

            if (itemInHand != null) {
                switch (itemInHand.getType()) {
                    case PAPER:
                        toggleEdit(player);
                        break;
                    default:
                        break;
                }
            }
        }
    }

