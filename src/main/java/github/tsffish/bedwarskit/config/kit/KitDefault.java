package github.tsffish.bedwarskit.config.kit;

import github.tsffish.bedwarskit.config.KitConfigHandler;
import github.tsffish.bedwarskit.util.RelArmorColor;
import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static github.tsffish.bedwarskit.config.KitConfigHandler.*;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;

public class KitDefault {
    public static String kitName;
    public static String description;
    public static Material helmetItemType;
    public static Material chestItemType;
    public static Material legItemType;
    public static Material bootItemType;
    public static int helmetItemAmount;
    public static int chestItemAmount;
    public static int legItemAmount;
    public static int bootItemAmount;
    public static boolean coloredleatherArmor;
    public static Map<Material, Integer> invList = new HashMap<>();
    public static ItemStack helmetItem;
    public static ItemStack chestItem;
    public static ItemStack legItem;
    public static ItemStack bootItem;
    public static ItemStack kitItemInMenu;

    public static void loadKit(boolean Colored) {
        kitItemInMenu = new ItemStack(KitDefaultItemType, KitDefaultItemAmount);
        ItemMeta kitItemMeta = kitItemInMenu.getItemMeta();

        List<String> lore = new ArrayList<>();

        for (String s : KitDefaultItemLore){
            lore.add(t(s));
        }

        kitItemMeta.setLore(lore);

        kitItemMeta.setDisplayName(t(KitDefaultItemName));

        kitItemInMenu.setItemMeta(kitItemMeta);

        kitName = KitDefaultName;
        description = KitDefaultDescription;

        helmetItemAmount = 1;
        chestItemAmount = 1;
        legItemAmount =1;
        bootItemAmount = 1;

        helmetItemType = Material.LEATHER_HELMET;
        chestItemType = Material.LEATHER_CHESTPLATE;
        legItemType = Material.LEATHER_LEGGINGS;
        bootItemType = Material.LEATHER_BOOTS;

        coloredleatherArmor = Colored;
    }

    public static void setKit(Player player, boolean Colored) {
        helmetItem = new ItemStack(helmetItemType, helmetItemAmount);
        chestItem = new ItemStack(chestItemType, chestItemAmount);
        legItem = new ItemStack(legItemType, legItemAmount);
        bootItem = new ItemStack(bootItemType, bootItemAmount);

        PlayerInventory pi = player.getInventory();
        pi.setHelmet(helmetItem);
        pi.setChestplate(chestItem);
        pi.setLeggings(legItem);
        pi.setBoots(bootItem);

        applyKitBoost(player, Colored);
    }

    public static void applyKitBoost(Player player, boolean Colored) {
        coloredleatherArmor = Colored;
        GameManager gameManager = BedwarsRel.getInstance().getGameManager();
        PlayerInventory playerInventory = player.getInventory();
        if (KitDefault_Boost_GiveSpeed_enable) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999, KitDefault_Boost_GiveSpeed_level), true);
        }
        if (gameManager == null) return;

        Game game = gameManager.getGameOfPlayer(player);
        if (game == null) return;

        for (ItemStack itemStack : playerInventory.getArmorContents()) {
            if (itemStack.getType() == null) return;

            if (itemStack.getType().toString().contains("LEATHER")) {
                if (coloredleatherArmor) {
                    String playerteam = game.getPlayerTeam(player).getColor().name();

                    LeatherArmorMeta helmetMeta = (LeatherArmorMeta) helmetItem.getItemMeta();
                    LeatherArmorMeta chestplateMeta = (LeatherArmorMeta) chestItem.getItemMeta();
                    LeatherArmorMeta leggingsMeta = (LeatherArmorMeta) legItem.getItemMeta();
                    LeatherArmorMeta bootsMeta = (LeatherArmorMeta) bootItem.getItemMeta();
                    switch (playerteam) {
                    case "RED":
                        helmetMeta.setColor(RelArmorColor.red);
                        chestplateMeta.setColor(RelArmorColor.red);
                        leggingsMeta.setColor(RelArmorColor.red);
                        bootsMeta.setColor(RelArmorColor.red);
                        break;
                    case "BLUE":
                        helmetMeta.setColor(RelArmorColor.blue);
                        chestplateMeta.setColor(RelArmorColor.blue);
                        leggingsMeta.setColor(RelArmorColor.blue);
                        bootsMeta.setColor(RelArmorColor.blue);
                        break;
                    case "GREEN":
                        helmetMeta.setColor(RelArmorColor.green);
                        chestplateMeta.setColor(RelArmorColor.green);
                        leggingsMeta.setColor(RelArmorColor.green);
                        bootsMeta.setColor(RelArmorColor.green);
                        break;
                    case "YELLOW":
                        helmetMeta.setColor(RelArmorColor.yellow);
                        chestplateMeta.setColor(RelArmorColor.yellow);
                        leggingsMeta.setColor(RelArmorColor.yellow);
                        bootsMeta.setColor(RelArmorColor.yellow);
                        break;
                    case "WHITE":
                        helmetMeta.setColor(RelArmorColor.white);
                        chestplateMeta.setColor(RelArmorColor.white);
                        leggingsMeta.setColor(RelArmorColor.white);
                        bootsMeta.setColor(RelArmorColor.white);
                        break;
                    case "AQUA":
                        helmetMeta.setColor(RelArmorColor.aqua);
                        chestplateMeta.setColor(RelArmorColor.aqua);
                        leggingsMeta.setColor(RelArmorColor.aqua);
                        bootsMeta.setColor(RelArmorColor.aqua);
                        break;
                    case "LIGHT_PURPLE":
                        helmetMeta.setColor(RelArmorColor.pink);
                        chestplateMeta.setColor(RelArmorColor.pink);
                        leggingsMeta.setColor(RelArmorColor.pink);
                        bootsMeta.setColor(RelArmorColor.pink);
                        break;
                    case "GRAY":
                        helmetMeta.setColor(RelArmorColor.gray);
                        chestplateMeta.setColor(RelArmorColor.gray);
                        leggingsMeta.setColor(RelArmorColor.gray);
                        bootsMeta.setColor(RelArmorColor.gray);
                        break;
                    default:
                        break;
                    }
                    helmetItem.setItemMeta(helmetMeta);
                    chestItem.setItemMeta(chestplateMeta);
                    legItem.setItemMeta(leggingsMeta);
                    bootItem.setItemMeta(bootsMeta);

                    player.getInventory().setHelmet(helmetItem);
                    player.getInventory().setChestplate(chestItem);
                    player.getInventory().setLeggings(legItem);
                    player.getInventory().setBoots(bootItem);
                }
            }
        }
    }
}
