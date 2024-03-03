package github.tsffish.bedwarskit.listener;

import github.tsffish.bedwarskit.BedwarsKit;
import github.tsffish.bedwarskit.util.RelPlayerIsRespawn;
import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.game.Game;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

import java.util.UUID;

import static github.tsffish.bedwarskit.BedwarsKit.getPlayerIsHide;
import static github.tsffish.bedwarskit.util.RelPlayerIsRespawn.getPlayerRespawn;

public class RelPickupItem implements Listener {
    @EventHandler
    public void on(PlayerPickupItemEvent event){
        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();
        Game game = BedwarsRel.getInstance().getGameManager().getGameOfPlayer(player);

        if (getPlayerIsHide(player)
        || game.isSpectator(player)
        || getPlayerRespawn(playerUUID)){
            event.setCancelled(true);
        }
    }
}
