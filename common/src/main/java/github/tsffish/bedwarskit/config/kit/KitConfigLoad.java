package github.tsffish.bedwarskit.config.kit;

import github.tsffish.bedwarskit.BedwarsKit;
import github.tsffish.bedwarskit.util.kit.KitDefault;
import github.tsffish.bedwarskit.util.kit.KitDefaultless;
import github.tsffish.bedwarskit.util.kit.KitNone;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;

import static github.tsffish.bedwarskit.config.kit.KitConfigHandler.*;
import static github.tsffish.bedwarskit.config.lang.LangConfigHandler.finishLoadConfig;
import static github.tsffish.bedwarskit.config.misc.ConfigVersionChecker.checkAndRenameConfig;
import static github.tsffish.bedwarskit.util.PluginInit.tipHaveChinese;
import static github.tsffish.bedwarskit.util.PluginState.isDebug;
import static github.tsffish.bedwarskit.util.PluginState.language;
import static github.tsffish.bedwarskit.util.kit.KitOpenItem.loadKitMenuItem;
import static github.tsffish.bedwarskit.util.misc.MessSender.l;
import static github.tsffish.bedwarskit.util.misc.MessSender.le;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class KitConfigLoad {
    private static final BedwarsKit plugin = BedwarsKit.getInstance();
    private static final String className = KitConfigLoad.class.getSimpleName();

    public static void loadKitConfig() {

        String fileName = "kit.yml";
        String use_config_version = "1.9.57";

        File file = new File(plugin.getDataFolder(), fileName);

        if (!file.exists()) {
            plugin.saveResource(fileName, false);
            if (language().equals("zh")) {
                tipHaveChinese();
            }
        }

        checkAndRenameConfig(file, use_config_version);

        FileConfiguration c = YamlConfiguration.loadConfiguration(file);

        try {
            extracted(c);
        } catch (Exception e) {
            le(className, e);
        }

        if (isDebug()) {
            l("<" + className + "> " + finishLoadConfig);
        }

        loadKits();
        loadKitMenuItem();
    }

    private static void extracted(FileConfiguration c) {
        meanSelKitSucc = c.getString(KitConfigPath.path_meanSelKitSucc);

        kitDefault = c.getString(KitConfigPath.path_kitDefault);

        kitenable = c.getBoolean(KitConfigPath.path_kitenable);

        kitMenuTitle = c.getString(KitConfigPath.path_kitMenuTitle);

        kitMenuItemName = c.getString(KitConfigPath.path_kitMenuItemName);

        kitMenuItemType = Material.getMaterial(c.getString(KitConfigPath.path_kitMenuItemType).toUpperCase());

        kitMenuItemAmount = c.getInt(KitConfigPath.path_kitMenuItemAmount);

        kitMenurow = c.getInt(KitConfigPath.path_kitMenurow);


        kitMenuItemType = Material.getMaterial(c.getString(KitConfigPath.path_kitMenuItemType).toUpperCase());
        kitMenuItemAmount = c.getInt(KitConfigPath.path_kitMenuItemAmount);
        kitMenurow = c.getInt(KitConfigPath.path_kitMenurow);
        KitNoneItemType = Material.getMaterial(c.getString(KitConfigPath.path_KitNoneItemType).toUpperCase());


        KitDefaultItemType = Material.getMaterial(c.getString(KitConfigPath.path_KitDefaultItemType).toUpperCase());
        KitDefaultItemAmount = c.getInt(KitConfigPath.path_KitDefaultItemAmount);
        KitDefaultItemSlot = c.getInt(KitConfigPath.path_KitDefaultItemSlot);
        KitDefaultItemName = c.getString(KitConfigPath.path_KitDefaultItemName);

        if (KitDefaultItemLore == null) {
            KitDefaultItemLore = new ArrayList<>(10);
        } else {
            KitDefaultItemLore.clear();
        }

        KitDefaultItemLore = c.getStringList(KitConfigPath.path_KitDefaultItemLore);
        KitDefaultName = c.getString(KitConfigPath.path_KitDefaultName);
        KitDefaultDescription = c.getString(KitConfigPath.path_KitDefaultDescription);
        KitDefault_Boost_GiveSpeed_enable = c.getBoolean(KitConfigPath.path_KitDefault_Boost_GiveSpeed_enable);
        KitDefault_Boost_GiveSpeed_level = c.getInt(KitConfigPath.path_KitDefault_Boost_GiveSpeed_level);


        KitDefaultlessItemType = Material.getMaterial(c.getString(KitConfigPath.path_KitDefaultlessItemType).toUpperCase());
        KitDefaultlessItemAmount = c.getInt(KitConfigPath.path_KitDefaultlessItemAmount);
        KitDefaultlessItemSlot = c.getInt(KitConfigPath.path_KitDefaultlessItemSlot);
        KitDefaultlessItemName = c.getString(KitConfigPath.path_KitDefaultlessItemName);

        if (KitDefaultlessItemLore == null) {
            KitDefaultlessItemLore = new ArrayList<>(10);
        } else {
            KitDefaultlessItemLore.clear();
        }

        KitDefaultlessItemLore = c.getStringList(KitConfigPath.path_KitDefaultlessItemLore);
        KitDefaultlessName = c.getString(KitConfigPath.path_KitDefaultlessName);
        KitDefaultlessDescription = c.getString(KitConfigPath.path_KitDefaultlessDescription);
        KitDefaultless_Boost_GiveSpeed_enable = c.getBoolean(KitConfigPath.path_KitDefaultless_Boost_GiveSpeed_enable);
        KitDefaultless_Boost_GiveSpeed_level = c.getInt(KitConfigPath.path_KitDefaultless_Boost_GiveSpeed_level);


        if (c.get(KitConfigPath.path_KitNoneItemAmount) != null) {
            KitNoneItemAmount = c.getInt(KitConfigPath.path_KitNoneItemAmount);
            KitNoneItemSlot = c.getInt(KitConfigPath.path_KitNoneItemSlot);
            KitNoneItemName = c.getString(KitConfigPath.path_KitNoneItemName);

            if (KitNoneItemLore == null) {
                KitNoneItemLore = new ArrayList<>(10);
            } else {
                KitNoneItemLore.clear();
            }

            KitNoneItemLore = c.getStringList(KitConfigPath.path_KitNoneItemLore);
            KitNoneName = c.getString(KitConfigPath.path_KitNoneName);
            KitNoneDescription = c.getString(KitConfigPath.path_KitNoneDescription);

            kitForce = c.getBoolean(KitConfigPath.path_kitForce);

            kitForceKit = c.getString(KitConfigPath.path_kitForceKit);

            kitMenuItemGive = c.getBoolean(KitConfigPath.path_kitMenuItemGive);
        }
    }

    private static void loadKits() {
        KitDefault.loadKit(true);
        KitNone.loadKit();
        KitDefaultless.loadKit(true);
    }

}
