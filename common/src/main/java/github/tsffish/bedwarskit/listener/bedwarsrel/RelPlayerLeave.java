package github.tsffish.bedwarskit.listener.bedwarsrel;

import io.github.bedwarsrel.events.BedwarsPlayerLeaveEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import static github.tsffish.bedwarskit.Main.pluginIsDisabling;
import static github.tsffish.bedwarskit.util.RelArmorList.*;
import static github.tsffish.bedwarskit.util.RelCurrentStat.*;
import static github.tsffish.bedwarskit.util.RelPlayerIsRespawn.removePlayerRespawn;
import static github.tsffish.bedwarskit.util.misc.MessSender.le;

public class RelPlayerLeave implements Listener {
    @EventHandler
    public void on(final BedwarsPlayerLeaveEvent event) {
        try {
            Player player = event.getPlayer();
            player.setFallDistance(0);
            String playerName = player.getName();

            removeArmorChain(playerName);
            removeArmorIron(playerName);
            removeArmorDiamond(playerName);

            setDefaultPlayerStat(playerName);

            removePlayerIsOut(playerName);
            removePlayerStat(playerName);
            removePlayerRespawn(playerName);

        }catch (RuntimeException e){
            if (!pluginIsDisabling){
                le("RelPlayerLeave", "BedwarsPlayerLeaveEvent error:" + e);
            }
        }

    }

    @EventHandler
    public void on(final PlayerQuitEvent event){
        try {
            Player player = event.getPlayer();
            String playerName = player.getName();
            removePlayerIsOut(playerName);
            removePlayerRespawn(playerName);
        }catch (RuntimeException e){
            if (!pluginIsDisabling){
                le("RelPlayerLeave", "PlayerQuitEvent error:" + e);
            }
        }
    }

}