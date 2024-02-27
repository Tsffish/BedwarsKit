package github.tsffish.bedwarskit.util.kit;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static github.tsffish.bedwarskit.config.kit.KitConfigHandler.*;

public class KitMenu
{
    public static Inventory kitMenu;
    static void loadKitMenu()
    {

        kitMenu = Bukkit.getServer().createInventory(null,kitMenurow * 9, kitMenuTitle);

        ItemStack Default = KitDefault.kitItemInMenu;
        kitMenu.setItem(KitDefaultItemSlot,Default);

        ItemStack None = KitNone.kitItemInMenu;
        kitMenu.setItem(KitNoneItemSlot,None);

        ItemStack Defaultless = KitDefaultless.kitItemInMenu;
        kitMenu.setItem(KitDefaultlessItemSlot,Defaultless);
    }





}
