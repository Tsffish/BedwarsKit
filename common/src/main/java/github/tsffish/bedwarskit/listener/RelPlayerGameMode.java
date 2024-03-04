package github.tsffish.bedwarskit.listener;

import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameManager;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;

import static github.tsffish.bedwarskit.config.main.MainConfigHandler.creativeGameModeFix;

public class RelPlayerGameMode implements Listener {
    private static final GameMode creative = GameMode.CREATIVE;
    private static final GameMode survival = GameMode.SURVIVAL;
    @EventHandler
    public void on(final PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();
        if (BedwarsRel.getInstance() == null){
            return;
        }
        BedwarsRel bedwarsRel = BedwarsRel.getInstance();
        if (bedwarsRel.getGameManager() == null){
            return;
        }
        GameManager gameManager = bedwarsRel.getGameManager();
        if (gameManager.getGameOfPlayer(player) == null){
            return;
        }

        if (creativeGameModeFix && player.getGameMode() == creative) {
            player.setFlying(!player.isFlying());
            return;
        }
        Game game = gameManager.getGameOfPlayer(player);
        if (game.getRespawnProtections().containsKey(player) && player.getGameMode() == survival){
            if (player.getAllowFlight()){
            player.setFlying(!player.isFlying());
            }
        }
    }
}
