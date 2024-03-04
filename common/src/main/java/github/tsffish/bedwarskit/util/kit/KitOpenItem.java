package github.tsffish.bedwarskit.util.kit;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static github.tsffish.bedwarskit.config.kit.KitConfigHandler.*;
import static github.tsffish.bedwarskit.util.kit.KitMenu.loadKitMenu;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;
/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class KitOpenItem
{

    public static ItemStack kitMenuItem;
    public static void loadKitMenuItem() {
        kitMenuItem = new ItemStack(kitMenuItemType, kitMenuItemAmount);
        ItemMeta kitMenuItemMeta = kitMenuItem.getItemMeta();
        kitMenuItemMeta.setDisplayName(t(kitMenuItemName));
        kitMenuItem.setItemMeta(kitMenuItemMeta);

        loadKitMenu();
    }
}
