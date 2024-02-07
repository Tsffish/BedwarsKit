package github.tsffish.bedwarskit.listener.bedwarsrel;

import io.github.bedwarsrel.BedwarsRel;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static github.tsffish.bedwarskit.util.misc.ColorString.t;

public class RelItemShop {

    public static Inventory ItemShop2v2Menu;
    public static FileConfiguration shopConfig = BedwarsRel.getInstance().getShopConfig();
    public static void openShop(Player player) {
        ItemShop2v2Menu = Bukkit.getServer().createInventory(null,6*9,"Shop");
        for (String category : shopConfig.getConfigurationSection("shop").getKeys(false)) {
            
            if (category.equalsIgnoreCase("schema-version") || category.equalsIgnoreCase("order")) {
                continue;
            }

            ItemStack item = getItemStack(category);
            ItemShop2v2Menu.addItem(item);
        }
        player.openInventory(ItemShop2v2Menu);
        player.sendMessage("Shop opened.");
    }

    public static ItemStack getItemStack(String category) {
        String itemPath = "shop." + category + ".item";
        String namePath = "shop." + category + ".name";
        String lorePath = "shop." + category + ".lore";

        String itemType = shopConfig.getString(itemPath);
        String itemName = shopConfig.getString(namePath);

        Material material = Material.getMaterial(itemType);
        if (material == null) {
            throw new IllegalArgumentException("Invalid material type: " + itemType);
        }

        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(itemName);

        List<String> lore = shopConfig.getStringList(lorePath);
        List<String> formattedLore = new ArrayList<>(10);
        for (String line : lore) {
            formattedLore.add(t(line));
        }
        meta.setLore(formattedLore);

        item.setItemMeta(meta);

        return item;
    }

}
