//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package github.tsffish.bedwarskit.listener.bedwarsrel;

import github.tsffish.bedwarskit.config.MainConfigHandler;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import static github.tsffish.bedwarskit.util.ColorString.t;

public class RelClickInventory implements Listener {
    public boolean antiDrop;

    public RelClickInventory() {
        this.antiDrop = MainConfigHandler.antiDrop;
    }

    @EventHandler
    public void on(InventoryClickEvent e) {
        if (e.getCurrentItem() != null) {
            Material currentItemType = e.getCurrentItem().getType();
            if (this.antiDrop && currentItemType != null && currentItemType != Material.AIR && MainConfigHandler.NoDropList.contains(e.getCurrentItem().getType())) {
                e.setCancelled(true);
            }
        }

    }
}
