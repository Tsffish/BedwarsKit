package github.tsffish.bedwarskit.listener.bedwarsrel;

import io.github.bedwarsrel.events.BedwarsPlayerLeaveEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static github.tsffish.bedwarskit.util.RelArmorList.*;

public class RelPlayerLeave implements Listener {

    @EventHandler
    public void on(BedwarsPlayerLeaveEvent e) {
            String playerName = e.getPlayer().getName();
            armorChain.remove(playerName);
            armorIron.remove(playerName);
            armorDiamond.remove(playerName);
    }
}