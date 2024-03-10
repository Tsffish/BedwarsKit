package github.tsffish.bedwarskit.util.kit;

import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static github.tsffish.bedwarskit.config.kit.KitConfigHandler.*;
import static github.tsffish.bedwarskit.config.main.MainConfigHandler.killfb_oneHealthKill_itemType;
import static github.tsffish.bedwarskit.util.RelArmorColor.*;
import static github.tsffish.bedwarskit.util.RelTeamColorName.*;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class KitDefault {
    private static final String className = "KitDefault";
    private static final int EffectTime = 999999;
    public static String description;
    static ItemStack kitItemInMenu;
    static ItemStack wood_sword;
    static ItemStack wood_pickaxe;
    static ItemStack wood_axe;
    private static Material helmetItemType;
    private static Material chestItemType;
    private static Material legItemType;
    private static Material bootItemType;
    private static int helmetItemAmount;
    private static int chestItemAmount;
    private static int legItemAmount;
    private static int bootItemAmount;
    private static boolean coloredleatherArmor;
    private static ItemStack helmetItem;
    private static ItemStack chestItem;
    private static ItemStack legItem;
    private static ItemStack bootItem;

    public static void loadKit(boolean Colored) {
        wood_sword = new ItemStack(Material.WOOD_SWORD, 1);
        wood_pickaxe = new ItemStack(Material.WOOD_PICKAXE, 1);
        wood_axe = new ItemStack(Material.WOOD_AXE, 1);

        kitItemInMenu = new ItemStack(KitDefaultItemType, KitDefaultItemAmount);
        ItemMeta kitItemMeta = kitItemInMenu.getItemMeta();

        List<String> lore = new ArrayList<>(8);

        for (String s : KitDefaultItemLore) {
            lore.add(t(s));
        }

        kitItemMeta.setLore(lore);

        kitItemMeta.setDisplayName(t(KitDefaultItemName));

        kitItemInMenu.setItemMeta(kitItemMeta);

        description = KitDefaultDescription;

        helmetItemAmount = 1;
        chestItemAmount = 1;
        legItemAmount = 1;
        bootItemAmount = 1;

        helmetItemType = Material.LEATHER_HELMET;
        chestItemType = Material.LEATHER_CHESTPLATE;
        legItemType = Material.LEATHER_LEGGINGS;
        bootItemType = Material.LEATHER_BOOTS;

        coloredleatherArmor = Colored;
    }

    public static void setKit(UUID uuid, boolean Colored) {

        Player player = Bukkit.getPlayer(uuid);
        helmetItem = new ItemStack(helmetItemType, helmetItemAmount);
        chestItem = new ItemStack(chestItemType, chestItemAmount);
        legItem = new ItemStack(legItemType, legItemAmount);
        bootItem = new ItemStack(bootItemType, bootItemAmount);

        PlayerInventory pi = player.getInventory();
        pi.setHelmet(helmetItem);
        pi.setChestplate(chestItem);
        pi.setLeggings(legItem);
        pi.setBoots(bootItem);

        if (wood_sword != null) {
            if (pi.getItemInHand().getType() != killfb_oneHealthKill_itemType) {
                pi.setItemInHand(wood_sword);
            } else if (!pi.contains(wood_sword)) {
                pi.addItem(wood_sword);
            }
        }

        if (wood_pickaxe != null) {
            if (!pi.contains(wood_pickaxe)) {
                pi.addItem(wood_pickaxe);
            }
        }

        if (wood_axe != null) {
            if (!pi.contains(wood_axe)) {
                pi.addItem(wood_axe);
            }
        }

        applyKitBoost(player, Colored);
    }

    private static void applyKitBoost(Player player, boolean Colored) {
        coloredleatherArmor = Colored;
        GameManager gameManager = BedwarsRel.getInstance().getGameManager();
        PlayerInventory playerInventory = player.getInventory();
        if (KitDefault_Boost_GiveSpeed_enable) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, EffectTime, KitDefault_Boost_GiveSpeed_level), true);
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
                        case RED_TEAM_COLOR_NAME:
                            helmetMeta.setColor(red);
                            chestplateMeta.setColor(red);
                            leggingsMeta.setColor(red);
                            bootsMeta.setColor(red);
                            break;
                        case BLUE_TEAM_COLOR_NAME:
                            helmetMeta.setColor(blue);
                            chestplateMeta.setColor(blue);
                            leggingsMeta.setColor(blue);
                            bootsMeta.setColor(blue);
                            break;
                        case GREEN_TEAM_COLOR_NAME:
                            helmetMeta.setColor(green);
                            chestplateMeta.setColor(green);
                            leggingsMeta.setColor(green);
                            bootsMeta.setColor(green);
                            break;
                        case YELLOW_TEAM_COLOR_NAME:
                            helmetMeta.setColor(yellow);
                            chestplateMeta.setColor(yellow);
                            leggingsMeta.setColor(yellow);
                            bootsMeta.setColor(yellow);
                            break;
                        case WHITE_TEAM_COLOR_NAME:
                            helmetMeta.setColor(white);
                            chestplateMeta.setColor(white);
                            leggingsMeta.setColor(white);
                            bootsMeta.setColor(white);
                            break;
                        case AQUA_TEAM_COLOR_NAME:
                            helmetMeta.setColor(aqua);
                            chestplateMeta.setColor(aqua);
                            leggingsMeta.setColor(aqua);
                            bootsMeta.setColor(aqua);
                            break;
                        case PINK_TEAM_COLOR_NAME:
                            helmetMeta.setColor(pink);
                            chestplateMeta.setColor(pink);
                            leggingsMeta.setColor(pink);
                            bootsMeta.setColor(pink);
                            break;
                        case GRAY_TEAM_COLOR_NAME:
                            helmetMeta.setColor(gray);
                            chestplateMeta.setColor(gray);
                            leggingsMeta.setColor(gray);
                            bootsMeta.setColor(gray);
                            break;
                        default:
                            break;
                    }
                    helmetItem.setItemMeta(helmetMeta);
                    chestItem.setItemMeta(chestplateMeta);
                    legItem.setItemMeta(leggingsMeta);
                    bootItem.setItemMeta(bootsMeta);

                }
                player.getInventory().setHelmet(helmetItem);
                player.getInventory().setChestplate(chestItem);
                player.getInventory().setLeggings(legItem);
                player.getInventory().setBoots(bootItem);
            }
        }
    }
}
