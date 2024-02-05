package github.tsffish.bedwarskit.config.kit;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static github.tsffish.bedwarskit.config.KitConfigHandler.*;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;

public class Menu
{
    public static Inventory kitMenu;
    public static void loadKitMenu()
    {

        kitMenu = Bukkit.getServer().createInventory(null,kitMenurow * 9, kitMenuTitle);

        ItemStack Default = KitDefault.kitItemInMenu;
        ItemStack None = KitNone.kitItemInMenu;

        kitMenu.setItem(KitDefaultItemSlot,Default);
        kitMenu.setItem(KitNoneItemSlot,None);

    }





}
