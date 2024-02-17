package github.tsffish.bedwarskit.listener.bedwarsrel;

import io.github.bedwarsrel.events.BedwarsPlayerLeaveEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

import static github.tsffish.bedwarskit.util.RelArmorList.*;
import static github.tsffish.bedwarskit.util.RelCurrentStat.*;

public class RelPlayerLeave implements Listener {
    private static Plugin plugin = github.tsffish.bedwarskit.Main.getPlugin(github.tsffish.bedwarskit.Main.class);
    @EventHandler
    public void on(final BedwarsPlayerLeaveEvent event) {

        Player player = event.getPlayer();

            String playerName = player.getName();

            removeArmorChain(playerName);
            removeArmorIron(playerName);
            removeArmorDiamond(playerName);

            setDefaultPlayerStat(playerName);

            removePlayerIsOut(playerName);

    }

    @EventHandler
    public void on(PlayerQuitEvent event){
        Player player = event.getPlayer();
        String playerName = player.getName();

        removePlayerIsOut(playerName);
    }

}