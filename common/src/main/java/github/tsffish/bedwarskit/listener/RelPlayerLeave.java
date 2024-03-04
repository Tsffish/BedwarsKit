package github.tsffish.bedwarskit.listener;

import io.github.bedwarsrel.events.BedwarsPlayerLeaveEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

import static github.tsffish.bedwarskit.listener.PluginDisable.pluginIsDisabling;
import static github.tsffish.bedwarskit.util.RelArmorList.*;
import static github.tsffish.bedwarskit.util.RelCurrentStat.*;
import static github.tsffish.bedwarskit.util.RelPlayerIsRespawn.removePlayerRespawn;
import static github.tsffish.bedwarskit.util.misc.MessSender.le;
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
            UUID uuid = player.getUniqueId();

            player.setFallDistance(resFallDis);

            removeArmorChain(uuid);
            removeArmorIron(uuid);
            removeArmorDiamond(uuid);

            setDefaultPlayerStat(uuid);

            removePlayerIsOut(uuid);
            removePlayerStat(uuid);
            removePlayerRespawn(uuid);

        }catch (RuntimeException e){
            if (!pluginIsDisabling){
                le(className, "BedwarsPlayerLeaveEvent error:" + e);
            }
        }

    }


    @EventHandler
    public void on(final PlayerQuitEvent event){
        try {
            Player player = event.getPlayer();
            UUID uuid = player.getUniqueId();

            removePlayerIsOut(uuid);
            removePlayerRespawn(uuid);

        }catch (RuntimeException e){
            if (!pluginIsDisabling){
                le(className, "PlayerQuitEvent error:" + e);
            }
        }
    }

}