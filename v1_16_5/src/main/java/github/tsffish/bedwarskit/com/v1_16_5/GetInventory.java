package github.tsffish.bedwarskit.com.v1_16_5;

import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

/**
 * A Addon for A MC plugin that allows you to quickly edit games in other ways
 * github.com/Tsffish/BedwarsMapCreate
 *
 * @author Tsffish
 */
public class GetInventory {
    public static String getInvTitle(Inventory inventory) {
        return inventory.getViewers().get(0).getOpenInventory().getTitle();
    }

    public static InventoryType getInvType(Inventory inventory) {
        return inventory.getType();
    }
}
