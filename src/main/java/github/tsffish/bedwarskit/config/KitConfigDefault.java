package github.tsffish.bedwarskit.config;

import org.bukkit.Material;

import static github.tsffish.bedwarskit.config.KitConfigHandler.*;
import static github.tsffish.bedwarskit.util.misc.InfoLogger.l;

public class KitConfigDefault
{
    public static void loadKitConfigDefault()
    {
        kitenable = true;
        kitMenuTitle = "选择职业";

        kitMenuItemName = "选择职业";
        kitMenuItemType = Material.IRON_SWORD;
        kitMenuItemAmount = 1;
        kitMenurow = 6;


        KitDefaultItemType = Material.GOLD_HELMET;
        KitDefaultItemAmount = 1;
        KitDefaultItemSlot = 0;
        KitDefaultItemName = "默认";
        KitDefaultItemLore.clear();
        KitDefaultItemLore.add("&b1");
        KitDefaultItemLore.add("&d2");
        KitDefaultName = "默认职业";
        KitDefaultDescription = "默认的职业";

        KitNoneItemType = Material.BARRIER;
        KitNoneItemAmount = 1;
        KitNoneItemSlot = 1;
        KitNoneItemName = "无";
        KitNoneItemLore.clear();
        KitNoneItemLore.add("&b1");
        KitNoneItemLore.add("&d2");
        KitNoneName = "无职业";
        KitNoneDescription = "无职业";

        l("<KitConfigLoad> Finish Load DefaultConfig");
    }
}
