package github.tsffish.bedwarskit.listener;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;

import static github.tsffish.bedwarskit.config.main.MainConfigHandler.creativeGameModeFix;

public class RelPlayerGameMode implements Listener {
    private static final GameMode creative = GameMode.CREATIVE;
    @EventHandler
    public void on(final PlayerToggleFlightEvent event){
        if (!creativeGameModeFix) {
            return;
        }
        Player player = event.getPlayer();
        player.setFlying(
                  !player.isFlying()
                && player.getGameMode() == creative
        );
    }
}