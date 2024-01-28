package github.tsffish.bedwarskit.listener.bedwarsrel;

import github.tsffish.bedwarskit.config.MainConfigHandler;
import io.github.bedwarsrel.events.BedwarsPlayerKilledEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.PlayerInventory;
public class RelKillRes implements Listener {
    @EventHandler
    public void on(BedwarsPlayerKilledEvent e) {
        if (MainConfigHandler.kill_res && e.getKiller() != null && e.getKiller() != e.getPlayer()) {
            Player k = e.getKiller();
            PlayerInventory kpi = k.getInventory();

            Player d = e.getPlayer();
            PlayerInventory dpi = d.getInventory();

            if (kpi != null && dpi != null) {
// 将铁锭转移到k的背包中
                for (int i = 0; i < dpi.getSize(); i++) {
                    if (dpi.getItem(i) != null && dpi.getItem(i).getType() == Material.IRON_INGOT) {
                        kpi.addItem(dpi.getItem(i));
                        dpi.setItem(i, null);
                    }
                }

// 将金锭转移到k的背包中
                for (int i = 0; i < dpi.getSize(); i++) {
                    if (dpi.getItem(i) != null && dpi.getItem(i).getType() == Material.GOLD_INGOT) {
                        kpi.addItem(dpi.getItem(i));
                        dpi.setItem(i, null);
                    }
                }

// 将钻石转移到k的背包中
                for (int i = 0; i < dpi.getSize(); i++) {
                    if (dpi.getItem(i) != null && dpi.getItem(i).getType() == Material.DIAMOND) {
                        kpi.addItem(dpi.getItem(i));
                        dpi.setItem(i, null);
                    }
                }

// 将绿宝石转移到k的背包中
                for (int i = 0; i < dpi.getSize(); i++) {
                    if (dpi.getItem(i) != null && dpi.getItem(i).getType() == Material.EMERALD) {
                        kpi.addItem(dpi.getItem(i));
                        dpi.setItem(i, null);
                    }
                }

            }
        }   }
}
