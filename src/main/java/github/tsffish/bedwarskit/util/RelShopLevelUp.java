package github.tsffish.bedwarskit.util;

import github.tsffish.bedwarskit.config.MainConfigHandler;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static github.tsffish.bedwarskit.util.ColorString.t;

public class RelShopLevelUp {
    public static Inventory i2 = Bukkit.getServer().createInventory(null, 9, MainConfigHandler.shopLevelup);
    public static Inventory i4 = Bukkit.getServer().createInventory(null, 9, MainConfigHandler.shopLevelup);
    public static void loadLevelUpInv(){
        loadLevelUpInv2v2();
        loadLevelUpInv4v4();
    }

    public static void loadLevelUpInv2v2(){

        List<String> lore = new ArrayList<>();
        lore.add("ERROR");

        ItemStack sharp1 = new ItemStack(MainConfigHandler.LevelupItem, 1);
        ItemMeta sharp1Real = sharp1.getItemMeta();
        sharp1Real.setDisplayName(t(MainConfigHandler.teamEnchItemName_Sharp1));
        lore.clear();
        lore.add(t("&f" +  MainConfigHandler.sharp1Cost2v2 + " " + MainConfigHandler.meanDiamond));
        sharp1Real.setLore(lore);
        sharp1.setItemMeta(sharp1Real);

        i2.addItem(sharp1);

        ItemStack prot1 = new ItemStack(MainConfigHandler.LevelupItem, 1);
        ItemMeta prot1Real = sharp1.getItemMeta();
        prot1Real.setDisplayName(t(MainConfigHandler.teamEnchItemName_Prot1));
        lore.clear();
        lore.add(t("&f" +  MainConfigHandler.prot1Cost2v2 + " " + MainConfigHandler.meanDiamond));
        prot1Real.setLore(lore);
        prot1.setItemMeta(prot1Real);

        i2.addItem(prot1);

        ItemStack prot2 = new ItemStack(MainConfigHandler.LevelupItem, 2);
        ItemMeta prot2Real = sharp1.getItemMeta();
        prot2Real.setDisplayName(t(MainConfigHandler.teamEnchItemName_Prot2));
        lore.clear();
        lore.add(t("&f" +  MainConfigHandler.prot2Cost2v2 + " " + MainConfigHandler.meanDiamond));
        prot2Real.setLore(lore);
        prot2.setItemMeta(prot2Real);

        i2.addItem(prot2);

        ItemStack prot3 = new ItemStack(MainConfigHandler.LevelupItem, 3);
        ItemMeta prot3Real = sharp1.getItemMeta();
        prot3Real.setDisplayName(t(MainConfigHandler.teamEnchItemName_Prot3));
        lore.clear();
        lore.add(t("&f" +  MainConfigHandler.prot3Cost2v2 + " " + MainConfigHandler.meanDiamond));
        prot3Real.setLore(lore);
        prot3.setItemMeta(prot3Real);

        i2.addItem(prot3);

        ItemStack prot4 = new ItemStack(MainConfigHandler.LevelupItem, 4);
        ItemMeta prot4Real = sharp1.getItemMeta();
        prot4Real.setDisplayName(t(MainConfigHandler.teamEnchItemName_Prot4));
        lore.clear();
        lore.add(t("&f" +  MainConfigHandler.prot4Cost2v2 + " " + MainConfigHandler.meanDiamond));
        prot4Real.setLore(lore);
        prot4.setItemMeta(prot4Real);

        i2.addItem(prot4);

    }
    public static void loadLevelUpInv4v4(){

        List<String> lore = new ArrayList<>();
        lore.add("ERROR");

        ItemStack sharp1 = new ItemStack(MainConfigHandler.LevelupItem, 1);
        ItemMeta sharp1Real = sharp1.getItemMeta();
        sharp1Real.setDisplayName(t(MainConfigHandler.teamEnchItemName_Sharp1));
        lore.clear();
        lore.add(t("&f" +  MainConfigHandler.sharp1Cost4v4 + " " + MainConfigHandler.meanDiamond));
        sharp1Real.setLore(lore);
        sharp1.setItemMeta(sharp1Real);

        i4.addItem(sharp1);

        ItemStack prot1 = new ItemStack(MainConfigHandler.LevelupItem, 1);
        ItemMeta prot1Real = sharp1.getItemMeta();
        prot1Real.setDisplayName(t(MainConfigHandler.teamEnchItemName_Prot1));
        lore.clear();
        lore.add(t("&f" +  MainConfigHandler.prot1Cost4v4 + " " + MainConfigHandler.meanDiamond));
        prot1Real.setLore(lore);
        prot1.setItemMeta(prot1Real);

        i4.addItem(prot1);

        ItemStack prot2 = new ItemStack(MainConfigHandler.LevelupItem, 2);
        ItemMeta prot2Real = sharp1.getItemMeta();
        prot2Real.setDisplayName(t(MainConfigHandler.teamEnchItemName_Prot2));
        lore.clear();
        lore.add(t("&f" +  MainConfigHandler.prot2Cost4v4 + " " + MainConfigHandler.meanDiamond));
        prot2Real.setLore(lore);
        prot2.setItemMeta(prot2Real);

        i4.addItem(prot2);

        ItemStack prot3 = new ItemStack(MainConfigHandler.LevelupItem, 3);
        ItemMeta prot3Real = sharp1.getItemMeta();
        prot3Real.setDisplayName(t(MainConfigHandler.teamEnchItemName_Prot3));
        lore.clear();
        lore.add(t("&f" +  MainConfigHandler.prot3Cost4v4 + " " + MainConfigHandler.meanDiamond));
        prot3Real.setLore(lore);
        prot3.setItemMeta(prot3Real);

        i4.addItem(prot3);

        ItemStack prot4 = new ItemStack(MainConfigHandler.LevelupItem, 4);
        ItemMeta prot4Real = sharp1.getItemMeta();
        prot4Real.setDisplayName(t(MainConfigHandler.teamEnchItemName_Prot4));
        lore.clear();
        lore.add(t("&f" +  MainConfigHandler.prot4Cost4v4 + " " + MainConfigHandler.meanDiamond));
        prot4Real.setLore(lore);
        prot4.setItemMeta(prot4Real);

        i4.addItem(prot4);
    }

}
