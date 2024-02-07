package github.tsffish.bedwarskit.config.kit;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static github.tsffish.bedwarskit.config.KitConfigHandler.*;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;

public class KitNone
{
    public static String description;
    static ItemStack kitItemInMenu;
        public static void loadKit() {
        kitItemInMenu = new ItemStack(KitNoneItemType, KitNoneItemAmount);
        ItemMeta kitItemMeta = kitItemInMenu.getItemMeta();
            List<String> lore = new ArrayList<>(8);

            for (String s : KitNoneItemLore){
                lore.add(t(s));
            }

            kitItemMeta.setLore(lore);

            kitItemMeta.setDisplayName(t(KitNoneItemName));

        kitItemInMenu.setItemMeta(kitItemMeta);

            description = KitNoneDescription;
    }
    }
