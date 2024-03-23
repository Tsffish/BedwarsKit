package github.tsffish.bedwarskit.com.v1_16_5;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * A Addon for A MC plugin that allows you to quickly edit games in other ways
 * github.com/Tsffish/BedwarsMapCreate
 *
 * @author Tsffish
 */
public class GetItemStack {
    public static ItemStack RED_WOOL() {
        return new ItemStack(Material.RED_WOOL, 1);
    }

    public static ItemStack GREEN_WOOL() {
        return new ItemStack(Material.LIME_WOOL, 1);
    }

    public static ItemStack YELLOW_WOOL() {
        return new ItemStack(Material.YELLOW_WOOL);
    }
}
