package github.tsffish.bedwarskit.listener;

import github.tsffish.bedwarskit.util.GetMaterial;
import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameManager;
import io.github.bedwarsrel.game.GameState;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import static github.tsffish.bedwarskit.config.main.MainConfigHandler.*;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class RelPlayerDrop implements Listener {
    private static final Material woodSword = GetMaterial.WOOD_SWORD();
    private static final Material stoneSword = Material.STONE_SWORD;
    private static final Material ironSword = Material.IRON_SWORD;
    private static final Material diamondSword = Material.DIAMOND_SWORD;
    private static final Material woodPickaxe = GetMaterial.WOOD_PICKAXE();
    private static final Material stonePickaxe = Material.STONE_PICKAXE;
    private static final Material ironPickaxe = Material.IRON_PICKAXE;
    private static final Material diamondPickaxe = Material.DIAMOND_PICKAXE;
    private static final Material woodAxe = GetMaterial.WOOD_AXE();
    private static final Material stoneAxe = Material.STONE_AXE;
    private static final Material ironAxe = Material.IRON_AXE;
    private static final Material diamondAxe = Material.DIAMOND_AXE;
    private static final GameState running = GameState.RUNNING;

    @EventHandler
    public void on(final PlayerDropItemEvent event) {
        if (event.getItemDrop().getItemStack() == null) {
            return;
        }
        ItemStack item = event.getItemDrop().getItemStack();
        Material itemType = item.getType();
        String itemTypeText = itemType.toString().toUpperCase();
        for (String list : noMoveList) {
            if (itemTypeText.contains(list)) {
                event.setCancelled(true);
                return;
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
        if (gameManager.getGameOfPlayer(player) == null) {
            return;
        }

        Game game = gameManager.getGameOfPlayer(player);

        if (game.getState() == running
                && !game.isSpectator(player)
                && !game.getRespawnProtections().containsKey(player)) {

            ItemStack droppedItem = event.getItemDrop().getItemStack();
            PlayerInventory pi = player.getInventory();

            if (noWoodSwordDropWhenNothaveSword) {
                if (droppedItem.getType() == woodSword) {
                    if (!pi.contains(woodSword)
                            && !pi.contains(stoneSword)
                            && !pi.contains(ironSword)
                            && !pi.contains(diamondSword)
                    ) {
                        event.setCancelled(true);
                        return;
                    }
                }
            }
            if (noWoodAxeDropWhenNothaveAxe) {
                if (droppedItem.getType() == woodAxe) {
                    if (!pi.contains(woodAxe)
                            && !pi.contains(stoneAxe)
                            && !pi.contains(ironAxe)
                            && !pi.contains(diamondAxe)
                    ) {
                        event.setCancelled(true);
                        return;
                    }
                }
            }
            if (noWoodPickaxeDropWhenNothavePickaxe) {
                if (droppedItem.getType() == woodPickaxe) {
                    if (!pi.contains(woodPickaxe)
                            && !pi.contains(stonePickaxe)
                            && !pi.contains(ironPickaxe)
                            && !pi.contains(diamondPickaxe)
                    ) {
                        event.setCancelled(true);
                        return;
                    }
                }
            }
        }
    }
}
