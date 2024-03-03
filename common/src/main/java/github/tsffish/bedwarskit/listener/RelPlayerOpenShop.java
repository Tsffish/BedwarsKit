package github.tsffish.bedwarskit.listener;

import github.tsffish.bedwarskit.BedwarsKit;
import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.events.BedwarsOpenShopEvent;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import static github.tsffish.bedwarskit.BedwarsKit.isBungeeMode;
import static github.tsffish.bedwarskit.BedwarsKit.isDebug;
import static github.tsffish.bedwarskit.config.main.MainConfigHandler.*;
import static github.tsffish.bedwarskit.util.misc.MessSender.le;
import static github.tsffish.bedwarskit.util.teamshop.ShopMenu.openForPlayer2v2;
import static github.tsffish.bedwarskit.util.teamshop.ShopMenu.openForPlayer4v4;

public class RelPlayerOpenShop implements Listener {
    private static final BedwarsKit plugin = BedwarsKit.getInstance();
    private static final String clickOnEntityName = "click on entity";
    @EventHandler
    public void on(final BedwarsOpenShopEvent event)
    {
        if(levelupShop) {

            if (levelupShopOpenMode.equalsIgnoreCase(clickOnEntityName)) {
                if (event.getEntity().getCustomName().equalsIgnoreCase(levelupShopOpenMode)) {
                    event.setCancelled(true);
                }
            }
        }
    }

    public static void openShop(Player player, long delay) {
        if (player == null || !player.isOnline()) {
            return;
        }

        String worldName = player.getWorld().getName();
        GameManager gameManager = BedwarsRel.getInstance().getGameManager();
        if (gameManager == null) {
            if (isDebug()){
                le("RelPlayerOpenShop","gameManager == null");
            }
            return;
        }

        Game game = gameManager.getGameOfPlayer(player);
        if (isBungeeMode) {
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

            if (worldName.contains(rushWorld)) {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (worldName.contains(rushWorld2v2)) {
                            openForPlayer2v2(player, game);
                        } else if (worldName.contains(rushWorld4v4)) {
                            openForPlayer4v4(player, game);
                        }
                    }
                }.runTaskLater(plugin, delay);
            }
        }
    }
}
