package github.tsffish.bedwarskit.listener.bedwars1058;

import com.andrei1058.bedwars.api.events.player.PlayerReSpawnEvent;
import github.tsffish.bedwarskit.config.MainConfigHandler;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class Yi058PlayerRespawn implements Listener {
    public boolean deathGamemode;
    public String lobbyWorld;

    public Yi058PlayerRespawn() {
        this.deathGamemode = MainConfigHandler.deathGameMode;
        this.lobbyWorld = MainConfigHandler.lobbyWorld;
    }

    @EventHandler
    public void on(PlayerReSpawnEvent e) {
        boolean isInRushWorld = e.getPlayer().getWorld().getName().contains(this.lobbyWorld);
        if (this.deathGamemode && !isInRushWorld) {
            e.getPlayer().setGameMode(GameMode.SURVIVAL);
        }

    }
}
