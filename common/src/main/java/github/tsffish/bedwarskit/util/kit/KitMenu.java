package github.tsffish.bedwarskit.util.kit;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static github.tsffish.bedwarskit.config.kit.KitConfigHandler.*;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class KitMenu {
    private static final int perRow = 9;
    public static Inventory kitMenu;

    static void loadKitMenu() {
        kitMenu = Bukkit.getServer().createInventory(
                null,
                kitMenurow * perRow,
                kitMenuTitle);

        ItemStack Default = KitDefault.kitItemInMenu;
        kitMenu.setItem(KitDefaultItemSlot, Default);

        ItemStack None = KitNone.kitItemInMenu;
        kitMenu.setItem(KitNoneItemSlot, None);

        ItemStack Defaultless = KitDefaultless.kitItemInMenu;
        kitMenu.setItem(KitDefaultlessItemSlot, Defaultless);
    }
}
