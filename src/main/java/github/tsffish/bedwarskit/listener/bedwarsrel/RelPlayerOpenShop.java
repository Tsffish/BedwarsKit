package github.tsffish.bedwarskit.listener.bedwarsrel;

import github.tsffish.bedwarskit.Main;
import github.tsffish.bedwarskit.config.main.MainConfigHandler;
import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.events.BedwarsOpenShopEvent;
import io.github.bedwarsrel.game.Game;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import static github.tsffish.bedwarskit.config.main.MainConfigHandler.levelupShopOpenMode;
import static github.tsffish.bedwarskit.util.teamshop.RelShopLevelUp.openForPlayer2v2;
import static github.tsffish.bedwarskit.util.teamshop.RelShopLevelUp.openForPlayer4v4;

public class RelPlayerOpenShop implements Listener {
    private static final Main plugin = Main.getInstance();
    private static final String clickOnEntityName = "click on entity";
    @EventHandler
    public void on(final BedwarsOpenShopEvent event)
    {
        if(MainConfigHandler.levelupShop) {

            if (levelupShopOpenMode.equalsIgnoreCase(clickOnEntityName)) {
                if (event.getEntity().getCustomName().equalsIgnoreCase(levelupShopOpenMode)) {
                    event.setCancelled(true);
                }
            }
        }
    }

    public static void openShop(Player player, long delay)
    {
        if (player == null || !player.isOnline())
        {
            return;
        }

        String worldName = player.getWorld().getName();
        if (worldName.contains(MainConfigHandler.rushWorld))
        {
            new BukkitRunnable()
            {
                @Override
                public void run()
                {
                    Game game = BedwarsRel.getInstance().getGameManager().getGameOfPlayer(player);

                    if (worldName.contains(MainConfigHandler.rushWorld2v2))
                    {
                        openForPlayer2v2(player, game);
                    }
                    else if (worldName.contains(MainConfigHandler.rushWorld4v4))
                    {
                        openForPlayer4v4(player, game);
                    }
                }
            }.runTaskLater(plugin, delay);
        }
    }

}
