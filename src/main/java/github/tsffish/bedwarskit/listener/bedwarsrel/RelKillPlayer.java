package github.tsffish.bedwarskit.listener.bedwarsrel;

import github.tsffish.bedwarskit.config.MainConfigHandler;
import io.github.bedwarsrel.events.BedwarsPlayerKilledEvent;
import io.github.bedwarsrel.game.Game;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.PlayerInventory;

import static github.tsffish.bedwarskit.util.RelCurrentStat.ups;

public class RelKillPlayer implements Listener {
    @EventHandler
    public void on(BedwarsPlayerKilledEvent e) {

        Game game = e.getGame();

        if (MainConfigHandler.kill_res && e.getKiller() != null && e.getKiller() != e.getPlayer()) {
            Player k = e.getKiller();
            PlayerInventory kpi = k.getInventory();
            ups(k,"k",1);

            Player d = e.getPlayer();
            PlayerInventory dpi = d.getInventory();
            ups(d,"d",1);


            if (game.getPlayerTeam(d).getHeadTarget() == null || game.getPlayerTeam(d).getHeadTarget().getType() != Material.BED) {
                ups(k, "f", 1);
            }

            if (kpi != null && dpi != null) {
                for (int i = 0; i < dpi.getSize(); i++) {
                    if (dpi.getItem(i) != null && dpi.getItem(i).getType() == Material.IRON_INGOT) {
                        kpi.addItem(dpi.getItem(i));
                        dpi.setItem(i, null);
                    }
                }
                for (int i = 0; i < dpi.getSize(); i++) {
                    if (dpi.getItem(i) != null && dpi.getItem(i).getType() == Material.GOLD_INGOT) {
                        kpi.addItem(dpi.getItem(i));
                        dpi.setItem(i, null);
                    }
                }

                for (int i = 0; i < dpi.getSize(); i++) {
                    if (dpi.getItem(i) != null && dpi.getItem(i).getType() == Material.DIAMOND) {
                        kpi.addItem(dpi.getItem(i));
                        dpi.setItem(i, null);
                    }
                }

                for (int i = 0; i < dpi.getSize(); i++) {
                    if (dpi.getItem(i) != null && dpi.getItem(i).getType() == Material.EMERALD) {
                        kpi.addItem(dpi.getItem(i));
                        dpi.setItem(i, null);
                    }
                }
            }

            }
        }
}
