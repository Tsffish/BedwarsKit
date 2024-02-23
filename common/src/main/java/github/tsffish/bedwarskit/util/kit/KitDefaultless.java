package github.tsffish.bedwarskit.util.kit;

import github.tsffish.bedwarskit.util.GetItemType;
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
import java.util.List;

import static github.tsffish.bedwarskit.config.kit.KitConfigHandler.*;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;
import static github.tsffish.bedwarskit.util.misc.MessSender.le;

public class KitDefaultless {
    private static final int EffectTime = 999999;
    public static String description;
    private static Material helmetItemType;
    private static Material chestItemType;
    private static int helmetItemAmount;
    private static int chestItemAmount;
    private static boolean coloredleatherArmor;
    private static ItemStack helmetItem;
    private static ItemStack chestItem;
    static ItemStack kitItemInMenu;
    static ItemStack wood_sword;
    public static void loadKit(boolean Colored) {
        Material ws = GetItemType.WOOD_SWORD();
        if (ws != null){
            wood_sword = new ItemStack(ws,1);
        }else {
            le("KitDefault","wood_sword is null,cant set it");
        }
        kitItemInMenu = new ItemStack(KitDefaultlessItemType, KitDefaultlessItemAmount);
        ItemMeta kitItemMeta = kitItemInMenu.getItemMeta();

        List<String> lore = new ArrayList<>(8);

        for (String s : KitDefaultlessItemLore){
            lore.add(t(s));
        }

        kitItemMeta.setLore(lore);

        kitItemMeta.setDisplayName(t(KitDefaultlessItemName));

        kitItemInMenu.setItemMeta(kitItemMeta);

        description = KitDefaultDescription;

        helmetItemAmount = 1;
        chestItemAmount = 1;

        helmetItemType = Material.LEATHER_HELMET;
        chestItemType = Material.LEATHER_CHESTPLATE;

        coloredleatherArmor = Colored;
    }

    public static void setKit(Player player, boolean Colored) {
        helmetItem = new ItemStack(helmetItemType, helmetItemAmount);
        chestItem = new ItemStack(chestItemType, chestItemAmount);

        PlayerInventory pi = player.getInventory();
        pi.setHelmet(helmetItem);
        pi.setChestplate(chestItem);

        if (wood_sword != null){
            pi.setItemInHand(wood_sword);
        }
        applyKitBoost(player, Colored);
    }

    private static void applyKitBoost(Player player, boolean Colored) {
        coloredleatherArmor = Colored;
        GameManager gameManager = BedwarsRel.getInstance().getGameManager();
        PlayerInventory playerInventory = player.getInventory();
        if (KitDefaultless_Boost_GiveSpeed_enable) {
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
                    switch (playerteam) {
                    case "RED":
                        helmetMeta.setColor(RelArmorColor.red);
                        chestplateMeta.setColor(RelArmorColor.red);
                        break;
                    case "BLUE":
                        helmetMeta.setColor(RelArmorColor.blue);
                        chestplateMeta.setColor(RelArmorColor.blue);
                        break;
                    case "GREEN":
                        helmetMeta.setColor(RelArmorColor.green);
                        chestplateMeta.setColor(RelArmorColor.green);
                        break;
                    case "YELLOW":
                        helmetMeta.setColor(RelArmorColor.yellow);
                        chestplateMeta.setColor(RelArmorColor.yellow);
                        break;
                    case "WHITE":
                        helmetMeta.setColor(RelArmorColor.white);
                        chestplateMeta.setColor(RelArmorColor.white);
                        break;
                    case "AQUA":
                        helmetMeta.setColor(RelArmorColor.aqua);
                        chestplateMeta.setColor(RelArmorColor.aqua);
                        break;
                    case "LIGHT_PURPLE":
                        helmetMeta.setColor(RelArmorColor.pink);
                        chestplateMeta.setColor(RelArmorColor.pink);
                        break;
                    case "GRAY":
                        helmetMeta.setColor(RelArmorColor.gray);
                        chestplateMeta.setColor(RelArmorColor.gray);
                        break;
                    default:
                        break;
                    }
                    helmetItem.setItemMeta(helmetMeta);
                    chestItem.setItemMeta(chestplateMeta);

                }
                player.getInventory().setHelmet(helmetItem);
                player.getInventory().setChestplate(chestItem);
            }
        }
    }
}
