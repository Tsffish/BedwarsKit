package github.tsffish.bedwarskit.listener;

import github.tsffish.bedwarskit.BedwarsKit;
import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.events.BedwarsOpenShopEvent;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameManager;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

import static github.tsffish.bedwarskit.config.main.MainConfigHandler.*;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;
import static github.tsffish.bedwarskit.util.misc.MessSender.le;
import static github.tsffish.bedwarskit.util.misc.PluginState.isBungeeMode;
import static github.tsffish.bedwarskit.util.misc.PluginState.isDebug;
import static github.tsffish.bedwarskit.util.player.RelEditGame.isEditGamePlayer;
import static github.tsffish.bedwarskit.util.teamshop.ShopMenu.openForPlayer2v2;
import static github.tsffish.bedwarskit.util.teamshop.ShopMenu.openForPlayer4v4;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class RelOpenShop implements Listener {
    private static final String className = "RelOpenShop";
    private static final BedwarsKit plugin = BedwarsKit.getInstance();
    private static final String clickOnEntityName = "click on entity";

    public static void openShop(Player player, long delay) {
        if (player == null || !player.isOnline()) {
            return;
        }

        String worldName = player.getWorld().getName();
        GameManager gameManager = BedwarsRel.getInstance().getGameManager();
        if (gameManager == null) {
            if (isDebug()) {
                le(className, "gameManager == null");
            }
            return;
        }

        Game game = gameManager.getGameOfPlayer(player);
        if (isBungeeMode()) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (bungeeMode.equals("2v2")) {
                        openForPlayer2v2(player, game);
                    } else if (bungeeMode.equals("4v4")) {
                        openForPlayer4v4(player, game);
                    }
                }
            }.runTaskLater(plugin, delay);

        } else {

            if (worldName.contains(gameWorld)) {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (worldName.contains(gameWorld2v2)) {
                            openForPlayer2v2(player, game);
                        } else if (worldName.contains(gameWorld4v4)) {
                            openForPlayer4v4(player, game);
                        }
                    }
                }.runTaskLater(plugin, delay);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void on(final BedwarsOpenShopEvent event) {
        if (event.getPlayer() instanceof Player) {
            Player player = (Player) event.getPlayer();
            UUID playerUUID = player.getUniqueId();
            if (isEditGamePlayer(playerUUID)) {
                event.setCancelled(true);
                return;
            }

            Game game = event.getGame();
            if (game == null || game.isSpectator(player) || player.getGameMode() == GameMode.SPECTATOR) {
                event.setCancelled(true);
                return;
            }

            if (levelupShop) {
                if (event.getEntity().getCustomName() != null) {
                    if (levelupShopOpenMode.equals(clickOnEntityName)) {
                        if (event.getEntity().getCustomName().equals(t(levelupShopOpenMode))) {
                            event.setCancelled(true);
                            return;
                        }
                    }
                }
            }

            if (openShopOnCustomEntityName){
                if (event.getEntity().getCustomName() != null) {
                    if (event.getEntity().getCustomName().equals(t(shopItemEntityName))) {
                        game.openNewItemShop(player);
                    }else {
                        event.setCancelled(true);
                    }
                }else {
                    event.setCancelled(true);
                }
            }
        }
        }

}
