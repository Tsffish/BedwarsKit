package github.tsffish.bedwarskit.config;

import github.tsffish.bedwarskit.config.kit.KitDefault;
import github.tsffish.bedwarskit.config.kit.KitNone;
import org.bukkit.Material;

import static github.tsffish.bedwarskit.config.ErrorConfigHandler.er;
import static github.tsffish.bedwarskit.config.KitConfigHandler.*;
import static github.tsffish.bedwarskit.config.MainConfigHandler.c;
import static github.tsffish.bedwarskit.config.kit.MenuItem.loadKitMenuItem;
import static github.tsffish.bedwarskit.util.misc.InfoLogger.l;

public class KitConfigLoad
{

    public static void loadKitConfig()
    {

        if (c == null){
            KitConfigDefault.loadKitConfigDefault();
            l("unable to found config, loading default kit config");
            return;
        }

        if (c.getString(KitConfigPath.path_kitenable) != null)
        {
            KitConfigHandler.kitenable = c.getBoolean(KitConfigPath.path_kitenable);
        }
        else
        {
            er("KitConfigLoad",KitConfigPath.path_kitenable, "vaule is null");
        }

        if (c.getString(KitConfigPath.path_kitMenuTitle) != null)
        {
            kitMenuTitle = c.getString(KitConfigPath.path_kitMenuTitle);
        }
        else
        {
            er("KitConfigLoad",KitConfigPath.path_kitMenuTitle, "vaule is null");
        }
        if (c.getString(KitConfigPath.path_kitMenuItemName) != null)
        {
            kitMenuItemName = c.getString(KitConfigPath.path_kitMenuItemName);
        }
        else
        {
            er("KitConfigLoad",KitConfigPath.path_kitMenuItemName, "vaule is null");
        }
        if (c.getString(KitConfigPath.path_kitMenuItemType) != null)
        {
            kitMenuItemType =  Material.getMaterial(c.getString(KitConfigPath.path_kitMenuItemType).toUpperCase());
        }
        else
        {
            er("KitConfigLoad",KitConfigPath.path_kitMenuItemType, "vaule is null");
        }
        if (c.getString(KitConfigPath.path_kitMenuItemAmount) != null)
        {
            kitMenuItemAmount = c.getInt(KitConfigPath.path_kitMenuItemAmount);
        }
        else
        {
            er("KitConfigLoad",KitConfigPath.path_kitMenuItemAmount, "vaule is null");
        }
        if (c.getString(KitConfigPath.path_kitMenurow) != null)
        {
            kitMenurow = c.getInt(KitConfigPath.path_kitMenurow);
        }
        else
        {
            er("KitConfigLoad",KitConfigPath.path_kitMenurow, "vaule is null");
        }

        if (c.getString(KitConfigPath.path_KitDefaultItemType) != null)
        {
            KitDefaultItemType = Material.getMaterial(c.getString(KitConfigPath.path_KitDefaultItemType).toUpperCase());
        }
        else
        {
            er("KitConfigLoad",KitConfigPath.path_KitDefaultItemType, "vaule is null");
        }
        if (c.getString(KitConfigPath.path_KitDefaultItemAmount) != null)
        {
            KitDefaultItemAmount = c.getInt(KitConfigPath.path_KitDefaultItemAmount);
        }
        else
        {
            er("KitConfigLoad",KitConfigPath.path_KitDefaultItemAmount, "vaule is null");
        }
        if (c.getString(KitConfigPath.path_KitDefaultItemSlot) != null)
        {
            KitDefaultItemSlot = c.getInt(KitConfigPath.path_KitDefaultItemSlot);
        }
        else
        {
            er("KitConfigLoad",KitConfigPath.path_KitDefaultItemSlot, "vaule is null");
        }
        if (c.getString(KitConfigPath.path_KitDefaultItemName) != null)
        {
            KitDefaultItemName = c.getString(KitConfigPath.path_KitDefaultItemName);
        }
        else
        {
            er("KitConfigLoad",KitConfigPath.path_KitDefaultItemName, "vaule is null");
        }
        if (c.getStringList(KitConfigPath.path_KitDefaultItemLore) != null)
        {
            KitDefaultItemLore = c.getStringList(KitConfigPath.path_KitDefaultItemLore);
        }
        else
        {
            er("KitConfigLoad",KitConfigPath.path_KitDefaultItemLore, "vaule is null");
        }

        if (c.getString(KitConfigPath.path_KitDefaultName) != null)
        {
            KitDefaultName = c.getString(KitConfigPath.path_KitDefaultName);
        }
        else
        {
            er("KitConfigLoad",KitConfigPath.path_KitDefaultName, "vaule is null");
        }
        if (c.getString(KitConfigPath.path_KitDefaultDescription) != null)
        {
            KitDefaultDescription = c.getString(KitConfigPath.path_KitDefaultDescription);
        }
        else
        {
            er("KitConfigLoad",KitConfigPath.path_KitDefaultDescription, "vaule is null");
        }





        if (c.getString(KitConfigPath.path_kitMenuItemType) != null)
        {
            kitMenuItemType = Material.getMaterial(c.getString(KitConfigPath.path_kitMenuItemType).toUpperCase());


        }
        else
        {
            er("KitConfigLoad",KitConfigPath.path_kitMenuItemType, "vaule is null");
        }
        if (c.getString(KitConfigPath.path_kitMenuItemAmount) != null)
        {
            kitMenuItemAmount = c.getInt(KitConfigPath.path_kitMenuItemAmount);
        }
        else
        {
            er("KitConfigLoad",KitConfigPath.path_kitMenuItemAmount, "vaule is null");
        }
        if (c.getString(KitConfigPath.path_kitMenurow) != null)
        {
            kitMenurow = c.getInt(KitConfigPath.path_kitMenurow);
        }
        else
        {
            er("KitConfigLoad",KitConfigPath.path_kitMenurow, "vaule is null");
        }

        if (c.getString(KitConfigPath.path_KitNoneItemType) != null)
        {
            KitNoneItemType =  Material.getMaterial(c.getString(KitConfigPath.path_KitNoneItemType).toUpperCase());
        }
        else
        {
            er("KitConfigLoad",KitConfigPath.path_KitNoneItemType, "vaule is null");
        }
        if (c.getString(KitConfigPath.path_KitNoneItemAmount) != null)
        {
            KitNoneItemAmount = c.getInt(KitConfigPath.path_KitNoneItemAmount);
        }
        else
        {
            er("KitConfigLoad",KitConfigPath.path_KitNoneItemAmount, "vaule is null");
        }
        if (c.getString(KitConfigPath.path_KitNoneItemSlot) != null)
        {
            KitNoneItemSlot = c.getInt(KitConfigPath.path_KitNoneItemSlot);
        }
        else
        {
            er("KitConfigLoad",KitConfigPath.path_KitNoneItemSlot, "vaule is null");
        }
        if (c.getString(KitConfigPath.path_KitNoneItemName) != null)
        {
            KitNoneItemName = c.getString(KitConfigPath.path_KitNoneItemName);
        }
        else
        {
            er("KitConfigLoad",KitConfigPath.path_KitNoneItemName, "vaule is null");
        }
        if (c.getStringList(KitConfigPath.path_KitNoneItemLore) != null)
        {
            KitNoneItemLore = c.getStringList(KitConfigPath.path_KitNoneItemLore);
        }
        else
        {
            er("KitConfigLoad",KitConfigPath.path_KitNoneItemLore, "vaule is null");
        }

        if (c.getString(KitConfigPath.path_KitNoneName) != null)
        {
            KitNoneName = c.getString(KitConfigPath.path_KitNoneName);
        }
        else
        {
            er("KitConfigLoad",KitConfigPath.path_KitNoneName, "vaule is null");
        }
        if (c.getString(KitConfigPath.path_KitNoneDescription) != null)
        {
            KitNoneDescription = c.getString(KitConfigPath.path_KitNoneDescription);
        }
        else
        {
            er("KitConfigLoad",KitConfigPath.path_KitNoneDescription, "vaule is null");
        }
        if (c.getString(KitConfigPath.path_KitDefault_Boost_GiveSpeed_enable) != null)
        {
            KitDefault_Boost_GiveSpeed_enable = c.getBoolean(KitConfigPath.path_KitDefault_Boost_GiveSpeed_enable);
        }
        else
        {
            er("KitConfigLoad",KitConfigPath.path_KitDefault_Boost_GiveSpeed_enable, "vaule is null");
        }
        if (c.getString(KitConfigPath.path_KitDefault_Boost_GiveSpeed_level) != null)
        {
            KitDefault_Boost_GiveSpeed_level = c.getInt(KitConfigPath.path_KitDefault_Boost_GiveSpeed_level);
        }
        else
        {
            er("KitConfigLoad",KitConfigPath.path_KitDefault_Boost_GiveSpeed_level, "vaule is null");
        }


        if (c.getString(KitConfigPath.path_kitForce) != null)
        {
            kitForce = c.getBoolean(KitConfigPath.path_kitForce);
        }
        else
        {
            er("KitConfigLoad",KitConfigPath.path_kitForce, "vaule is null");
        }

        if (c.getString(KitConfigPath.path_kitForceKit) != null)
        {
            kitForceKit = c.getString(KitConfigPath.path_kitForceKit);
        }
        else
        {
            er("KitConfigLoad",KitConfigPath.path_kitForceKit, "vaule is null");
        }
        if (c.getString(KitConfigPath.path_kitMenuItemGive) != null) {
            kitMenuItemGive = c.getBoolean(KitConfigPath.path_kitMenuItemGive);
        } else {
            er("KitConfigLoad",KitConfigPath.path_kitMenuItemGive, "vaule is null");
        }

        l("<KitConfigLoad> Finish Load Config");

        loadKits();
        loadKitMenuItem();
    }

    public static void loadKits()
    {
        KitDefault.loadKit(true);
        KitNone.loadKit();
    }

}
