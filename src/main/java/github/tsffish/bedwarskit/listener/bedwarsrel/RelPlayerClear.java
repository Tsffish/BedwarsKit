package github.tsffish.bedwarskit.listener.bedwarsrel;

import github.tsffish.bedwarskit.util.RelArmorList;
import io.github.bedwarsrel.events.BedwarsPlayerLeaveEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class RelPlayerClear implements Listener {
    public RelPlayerClear() {
    }

    @EventHandler
    public void on(BedwarsPlayerLeaveEvent e) {
        String playerName = e.getPlayer().getName();
        RelArmorList.armorChain.remove(playerName);
        RelArmorList.armorIron.remove(playerName);
        RelArmorList.armorDiamond.remove(playerName);
    }
}