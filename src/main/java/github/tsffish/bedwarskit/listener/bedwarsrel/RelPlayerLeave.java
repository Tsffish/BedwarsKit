package github.tsffish.bedwarskit.listener.bedwarsrel;

import io.github.bedwarsrel.events.BedwarsPlayerLeaveEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import static github.tsffish.bedwarskit.util.RelArmorList.*;
import static github.tsffish.bedwarskit.util.RelCurrentStat.*;

public class RelPlayerLeave implements Listener {

    @EventHandler
    public void on(BedwarsPlayerLeaveEvent event) {

        Player player = event.getPlayer();

            String playerName = player.getName();
            armorChain.remove(playerName);
            armorIron.remove(playerName);
            armorDiamond.remove(playerName);
        sdps(player);
        playerIsOut.remove(player);


    }

    @EventHandler
    public void on(PlayerQuitEvent event){
        Player player = event.getPlayer();
        playerIsOut.remove(player);
        rps(player);
    }

}