package github.tsffish.bedwarskit.config.kit;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static github.tsffish.bedwarskit.config.KitConfigHandler.*;
import static github.tsffish.bedwarskit.config.kit.Menu.loadKitMenu;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;

public class MenuItem
{

    public static ItemStack kitMenuItem;
    public static void loadKitMenuItem()
    {
        kitMenuItem = new ItemStack(kitMenuItemType, kitMenuItemAmount);
        ItemMeta kitMenuItemMeta = kitMenuItem.getItemMeta();
        kitMenuItemMeta.setDisplayName(t(kitMenuItemName));

        kitMenuItem.setItemMeta(kitMenuItemMeta);

        loadKitMenu();
    }
}