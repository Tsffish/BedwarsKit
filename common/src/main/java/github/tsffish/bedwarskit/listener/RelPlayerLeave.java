package github.tsffish.bedwarskit.listener;

import io.github.bedwarsrel.events.BedwarsPlayerLeaveEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

import static github.tsffish.bedwarskit.listener.PluginDisable.pluginIsDisabling;
import static github.tsffish.bedwarskit.util.misc.MessSender.le;
import static github.tsffish.bedwarskit.util.player.RelArmorList.*;
import static github.tsffish.bedwarskit.util.player.RelCurrentStat.*;
import static github.tsffish.bedwarskit.util.player.RelPlayerIsRespawn.removePlayerRespawn;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class RelPlayerLeave implements Listener {
    private static final String className = "RelPlayerLeave";
    private static final float resFallDis = 0.0f;

    @EventHandler
    public void on(final BedwarsPlayerLeaveEvent event) {
        try {
            Player player = event.getPlayer();
            UUID playerUUID = player.getUniqueId();

            event.getGame().getPlayers().remove(player);

            player.setFallDistance(resFallDis);

            removeArmorChain(playerUUID);
            removeArmorIron(playerUUID);
            removeArmorDiamond(playerUUID);

            setDefaultPlayerStat(playerUUID);

            removePlayerIsOut(playerUUID);
            removePlayerStat(playerUUID);
            removePlayerRespawn(playerUUID);

            event.getGame().getPlayers().remove(player);
        } catch (RuntimeException e) {
            if (!pluginIsDisabling) {
                le(className, e);
            }
        }

    }


    @EventHandler
    public void on(final PlayerQuitEvent event) {
        try {
            Player player = event.getPlayer();
            UUID playerUUID = player.getUniqueId();

            removePlayerIsOut(playerUUID);
            removePlayerRespawn(playerUUID);

        } catch (RuntimeException e) {
            if (!pluginIsDisabling) {
                le(className, e);
            }
        }
    }

}