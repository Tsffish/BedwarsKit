package github.tsffish.bedwarskit.config.kit;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static github.tsffish.bedwarskit.config.KitConfigHandler.*;

public class Menu
{
    public static Inventory kitMenu;
    static void loadKitMenu()
    {

        kitMenu = Bukkit.getServer().createInventory(null,kitMenurow * 9, kitMenuTitle);

        ItemStack Default = KitDefault.kitItemInMenu;
        ItemStack None = KitNone.kitItemInMenu;
        ItemStack Defaultless = KitDefaultless.kitItemInMenu;

        kitMenu.setItem(KitDefaultItemSlot,Default);
        kitMenu.setItem(KitNoneItemSlot,None);
        kitMenu.setItem(KitDefaultlessItemSlot,Defaultless);
    }





}
