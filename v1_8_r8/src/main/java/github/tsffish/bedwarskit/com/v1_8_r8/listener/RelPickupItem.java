package github.tsffish.bedwarskit.com.v1_8_r8.listener;


import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class RelPickupItem implements Listener {
    @EventHandler
    public void on(PlayerPickupItemEvent event){
        Player player = event.getPlayer();
        if (BedwarsRel.getInstance() == null){
            return;
        }
        BedwarsRel bedwarsRel = BedwarsRel.getInstance();
        if (bedwarsRel.getGameManager() == null){
            return;
        }
        GameManager gameManager = bedwarsRel.getGameManager();
        if(gameManager.getGameOfPlayer(player) == null){
            return;
        }

        Game game = gameManager.getGameOfPlayer(player);

        if (game.isSpectator(player)){
            event.setCancelled(true);
        }
    }
}
