package github.tsffish.bedwarskit.util.teamshop;

import github.tsffish.bedwarskit.config.main.MainConfigHandler;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.Team;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static github.tsffish.bedwarskit.config.main.MainConfigHandler.*;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;

public class RelShopLevelUp {
    private static Inventory i2;
    private static Inventory i4;
    private static ItemStack sharp1;
    private static ItemStack sharp2;
    private static ItemStack sharp3;
    private static ItemStack sharp4;
    private static ItemStack sharpMax;
    private static ItemStack prot1;
    private static ItemStack prot2;
    private static ItemStack prot3;
    private static ItemStack prot4;
    private static ItemStack protMax;
    private static ItemStack haste1;
    private static ItemStack haste2;
    private static ItemStack hasteMax;
    private static ItemStack heal1;
    private static ItemStack healMax;
    public static void loadLevelUpInv(){
        loadLevelUpInv2v2();
        loadLevelUpInv4v4();
    }
    private static void loadLevelUpInv2v2(){

        i2 = Bukkit.getServer().createInventory(null, teamEnchInvRow * 9, MainConfigHandler.shopLevelup);

        List<String> lore = new ArrayList<>(6);
        lore.add("ERROR");
        sharp1 = new ItemStack(MainConfigHandler.levelupsharpItemType, 1);
        ItemMeta sharp1Real = sharp1.getItemMeta();
        sharp1Real.setDisplayName(t(MainConfigHandler.teamEnchItemName_sharp1));
        lore.clear();
        lore.add(t("&f" +  MainConfigHandler.sharp1Cost2v2 + " " + MainConfigHandler.meanDiamond));
        sharp1Real.setLore(lore);
        sharp1.setItemMeta(sharp1Real);

        sharp2 = new ItemStack(MainConfigHandler.levelupsharpItemType, 1);
        ItemMeta sharp2Real = sharp2.getItemMeta();
        sharp2Real.setDisplayName(t(MainConfigHandler.teamEnchItemName_sharp2));
        lore.clear();
        lore.add(t("&f" +  MainConfigHandler.sharp2Cost2v2 + " " + MainConfigHandler.meanDiamond));
        sharp2Real.setLore(lore);
        sharp2.setItemMeta(sharp2Real);


        sharp3 = new ItemStack(MainConfigHandler.levelupsharpItemType, 1);
        ItemMeta sharp3Real = sharp3.getItemMeta();
        sharp3Real.setDisplayName(t(MainConfigHandler.teamEnchItemName_sharp3));
        lore.clear();
        lore.add(t("&f" +  MainConfigHandler.sharp3Cost2v2 + " " + MainConfigHandler.meanDiamond));
        sharp3Real.setLore(lore);
        sharp3.setItemMeta(sharp3Real);

        sharp4 = new ItemStack(MainConfigHandler.levelupsharpItemType, 1);
        ItemMeta sharp4Real = sharp4.getItemMeta();
        sharp4Real.setDisplayName(t(MainConfigHandler.teamEnchItemName_sharp4));
        lore.clear();
        lore.add(t("&f" +  MainConfigHandler.sharp4Cost2v2 + " " + MainConfigHandler.meanDiamond));
        sharp4Real.setLore(lore);
        sharp4.setItemMeta(sharp4Real);

        sharpMax = new ItemStack(MainConfigHandler.levelupsharpItemType, 1);
        ItemMeta sharpMaxReal = sharpMax.getItemMeta();
        sharpMaxReal.setDisplayName(t(MainConfigHandler.teamEnchItemName_sharpMax));
        lore.clear();
        lore.add(t("&f" +  MainConfigHandler.TeamEnchantMaxCost));
        sharpMaxReal.setLore(lore);
        sharpMax.setItemMeta(sharpMaxReal);

        prot1 = new ItemStack(MainConfigHandler.levelupprotItemType, 1);
        ItemMeta prot1Real = prot1.getItemMeta();
        prot1Real.setDisplayName(t(MainConfigHandler.teamEnchItemName_prot1));
        lore.clear();
        lore.add(t("&f" +  MainConfigHandler.prot1Cost2v2 + " " + MainConfigHandler.meanDiamond));
        prot1Real.setLore(lore);
        prot1.setItemMeta(prot1Real);

        prot2 = new ItemStack(MainConfigHandler.levelupprotItemType, 1);
        ItemMeta prot2Real = prot2.getItemMeta();
        prot2Real.setDisplayName(t(MainConfigHandler.teamEnchItemName_prot2));
        lore.clear();
        lore.add(t("&f" +  MainConfigHandler.prot2Cost2v2 + " " + MainConfigHandler.meanDiamond));
        prot2Real.setLore(lore);
        prot2.setItemMeta(prot2Real);


        prot3 = new ItemStack(MainConfigHandler.levelupprotItemType, 1);
        ItemMeta prot3Real = prot3.getItemMeta();
        prot3Real.setDisplayName(t(MainConfigHandler.teamEnchItemName_prot3));
        lore.clear();
        lore.add(t("&f" +  MainConfigHandler.prot3Cost2v2 + " " + MainConfigHandler.meanDiamond));
        prot3Real.setLore(lore);
        prot3.setItemMeta(prot3Real);

        prot4 = new ItemStack(MainConfigHandler.levelupprotItemType, 1);
        ItemMeta prot4Real = prot4.getItemMeta();
        prot4Real.setDisplayName(t(MainConfigHandler.teamEnchItemName_prot4));
        lore.clear();
        lore.add(t("&f" +  MainConfigHandler.prot4Cost2v2 + " " + MainConfigHandler.meanDiamond));
        prot4Real.setLore(lore);
        prot4.setItemMeta(prot4Real);

        protMax = new ItemStack(MainConfigHandler.levelupprotItemType, 1);
        ItemMeta protMaxReal = protMax.getItemMeta();
        protMaxReal.setDisplayName(t(MainConfigHandler.teamEnchItemName_protMax));
        lore.clear();
        lore.add(t("&f" +  MainConfigHandler.TeamEnchantMaxCost));
        protMaxReal.setLore(lore);
        protMax.setItemMeta(protMaxReal);

        haste1 = new ItemStack(MainConfigHandler.leveluphasteItemType, 1);
        ItemMeta haste1Real = haste1.getItemMeta();
        haste1Real.setDisplayName(t(MainConfigHandler.teamEffItemName_haste1));
        lore.clear();
        lore.add(t("&f" +  MainConfigHandler.haste1Cost2v2 + " " + MainConfigHandler.meanDiamond));
        haste1Real.setLore(lore);
        haste1.setItemMeta(haste1Real);

        haste2 = new ItemStack(MainConfigHandler.leveluphasteItemType, 1);
        ItemMeta haste2Real = haste2.getItemMeta();
        haste2Real.setDisplayName(t(MainConfigHandler.teamEffItemName_haste2));
        lore.clear();
        lore.add(t("&f" +  MainConfigHandler.haste2Cost2v2 + " " + MainConfigHandler.meanDiamond));
        haste2Real.setLore(lore);
        haste2.setItemMeta(haste2Real);

        hasteMax = new ItemStack(MainConfigHandler.leveluphasteItemType, 1);
        ItemMeta hasteMaxReal = hasteMax.getItemMeta();
        hasteMaxReal.setDisplayName(t(MainConfigHandler.teamEffItemName_hasteMax));
        lore.clear();
        lore.add(t("&f" +  MainConfigHandler.TeamEnchantMaxCost));
        hasteMaxReal.setLore(lore);
        hasteMax.setItemMeta(hasteMaxReal);

        heal1 = new ItemStack(MainConfigHandler.leveluphealItemType, 1);
        ItemMeta heal1Real = heal1.getItemMeta();
        heal1Real.setDisplayName(t(MainConfigHandler.teamEffItemName_heal1));
        lore.clear();
        lore.add(t("&f" +  MainConfigHandler.heal1Cost2v2 + " " + MainConfigHandler.meanDiamond));
        heal1Real.setLore(lore);
        heal1.setItemMeta(heal1Real);

        healMax = new ItemStack(MainConfigHandler.leveluphealItemType, 1);
        ItemMeta healMaxReal = healMax.getItemMeta();
        healMaxReal.setDisplayName(t(MainConfigHandler.teamEffItemName_healMax));
        lore.clear();
        lore.add(t("&f" +  MainConfigHandler.TeamEnchantMaxCost));
        healMaxReal.setLore(lore);
        healMax.setItemMeta(healMaxReal);

    }
    private static void loadLevelUpInv4v4(){

        i2 = Bukkit.getServer().createInventory(null, teamEnchInvRow * 9, MainConfigHandler.shopLevelup);

        List<String> lore = new ArrayList<>(6);
        lore.add("ERROR");
        sharp1 = new ItemStack(MainConfigHandler.levelupsharpItemType, 1);
        ItemMeta sharp1Real = sharp1.getItemMeta();
        sharp1Real.setDisplayName(t(MainConfigHandler.teamEnchItemName_sharp1));
        lore.clear();
        lore.add(t("&f" +  MainConfigHandler.sharp1Cost4v4 + " " + MainConfigHandler.meanDiamond));
        sharp1Real.setLore(lore);
        sharp1.setItemMeta(sharp1Real);

        sharp2 = new ItemStack(MainConfigHandler.levelupsharpItemType, 1);
        ItemMeta sharp2Real = sharp2.getItemMeta();
        sharp2Real.setDisplayName(t(MainConfigHandler.teamEnchItemName_sharp2));
        lore.clear();
        lore.add(t("&f" +  MainConfigHandler.sharp2Cost4v4 + " " + MainConfigHandler.meanDiamond));
        sharp2Real.setLore(lore);
        sharp2.setItemMeta(sharp2Real);


        sharp3 = new ItemStack(MainConfigHandler.levelupsharpItemType, 1);
        ItemMeta sharp3Real = sharp3.getItemMeta();
        sharp3Real.setDisplayName(t(MainConfigHandler.teamEnchItemName_sharp3));
        lore.clear();
        lore.add(t("&f" +  MainConfigHandler.sharp3Cost4v4 + " " + MainConfigHandler.meanDiamond));
        sharp3Real.setLore(lore);
        sharp3.setItemMeta(sharp3Real);

        sharp4 = new ItemStack(MainConfigHandler.levelupsharpItemType, 1);
        ItemMeta sharp4Real = sharp4.getItemMeta();
        sharp4Real.setDisplayName(t(MainConfigHandler.teamEnchItemName_sharp4));
        lore.clear();
        lore.add(t("&f" +  MainConfigHandler.sharp4Cost4v4 + " " + MainConfigHandler.meanDiamond));
        sharp4Real.setLore(lore);
        sharp4.setItemMeta(sharp4Real);

        sharpMax = new ItemStack(MainConfigHandler.levelupsharpItemType, 1);
        ItemMeta sharpMaxReal = sharpMax.getItemMeta();
        sharpMaxReal.setDisplayName(t(MainConfigHandler.teamEnchItemName_sharpMax));
        lore.clear();
        lore.add(t("&f" +  MainConfigHandler.TeamEnchantMaxCost));
        sharpMaxReal.setLore(lore);
        sharpMax.setItemMeta(sharpMaxReal);

        prot1 = new ItemStack(MainConfigHandler.levelupprotItemType, 1);
        ItemMeta prot1Real = prot1.getItemMeta();
        prot1Real.setDisplayName(t(MainConfigHandler.teamEnchItemName_prot1));
        lore.clear();
        lore.add(t("&f" +  MainConfigHandler.prot1Cost4v4 + " " + MainConfigHandler.meanDiamond));
        prot1Real.setLore(lore);
        prot1.setItemMeta(prot1Real);

        prot2 = new ItemStack(MainConfigHandler.levelupprotItemType, 1);
        ItemMeta prot2Real = prot2.getItemMeta();
        prot2Real.setDisplayName(t(MainConfigHandler.teamEnchItemName_prot2));
        lore.clear();
        lore.add(t("&f" +  MainConfigHandler.prot2Cost4v4 + " " + MainConfigHandler.meanDiamond));
        prot2Real.setLore(lore);
        prot2.setItemMeta(prot2Real);


        prot3 = new ItemStack(MainConfigHandler.levelupprotItemType, 1);
        ItemMeta prot3Real = prot3.getItemMeta();
        prot3Real.setDisplayName(t(MainConfigHandler.teamEnchItemName_prot3));
        lore.clear();
        lore.add(t("&f" +  MainConfigHandler.prot3Cost4v4 + " " + MainConfigHandler.meanDiamond));
        prot3Real.setLore(lore);
        prot3.setItemMeta(prot3Real);

        prot4 = new ItemStack(MainConfigHandler.levelupprotItemType, 1);
        ItemMeta prot4Real = prot4.getItemMeta();
        prot4Real.setDisplayName(t(MainConfigHandler.teamEnchItemName_prot4));
        lore.clear();
        lore.add(t("&f" +  MainConfigHandler.prot4Cost4v4 + " " + MainConfigHandler.meanDiamond));
        prot4Real.setLore(lore);
        prot4.setItemMeta(prot4Real);

        protMax = new ItemStack(MainConfigHandler.levelupprotItemType, 1);
        ItemMeta protMaxReal = protMax.getItemMeta();
        protMaxReal.setDisplayName(t(MainConfigHandler.teamEnchItemName_protMax));
        lore.clear();
        lore.add(t("&f" +  MainConfigHandler.TeamEnchantMaxCost));
        protMaxReal.setLore(lore);
        protMax.setItemMeta(protMaxReal);

        haste1 = new ItemStack(MainConfigHandler.leveluphasteItemType, 1);
        ItemMeta haste1Real = haste1.getItemMeta();
        haste1Real.setDisplayName(t(MainConfigHandler.teamEffItemName_haste1));
        lore.clear();
        lore.add(t("&f" +  MainConfigHandler.haste1Cost4v4 + " " + MainConfigHandler.meanDiamond));
        haste1Real.setLore(lore);
        haste1.setItemMeta(haste1Real);

        haste2 = new ItemStack(MainConfigHandler.leveluphasteItemType, 1);
        ItemMeta haste2Real = haste2.getItemMeta();
        haste2Real.setDisplayName(t(MainConfigHandler.teamEffItemName_haste2));
        lore.clear();
        lore.add(t("&f" +  MainConfigHandler.haste2Cost4v4 + " " + MainConfigHandler.meanDiamond));
        haste2Real.setLore(lore);
        haste2.setItemMeta(haste2Real);

        hasteMax = new ItemStack(MainConfigHandler.leveluphasteItemType, 1);
        ItemMeta hasteMaxReal = hasteMax.getItemMeta();
        hasteMaxReal.setDisplayName(t(MainConfigHandler.teamEffItemName_hasteMax));
        lore.clear();
        lore.add(t("&f" +  MainConfigHandler.TeamEnchantMaxCost));
        hasteMaxReal.setLore(lore);
        hasteMax.setItemMeta(hasteMaxReal);

        heal1 = new ItemStack(MainConfigHandler.leveluphealItemType, 1);
        ItemMeta heal1Real = heal1.getItemMeta();
        heal1Real.setDisplayName(t(MainConfigHandler.teamEffItemName_heal1));
        lore.clear();
        lore.add(t("&f" +  MainConfigHandler.heal1Cost4v4 + " " + MainConfigHandler.meanDiamond));
        heal1Real.setLore(lore);
        heal1.setItemMeta(heal1Real);

        healMax = new ItemStack(MainConfigHandler.leveluphealItemType, 1);
        ItemMeta healMaxReal = healMax.getItemMeta();
        healMaxReal.setDisplayName(t(MainConfigHandler.teamEffItemName_healMax));
        lore.clear();
        lore.add(t("&f" +  MainConfigHandler.TeamEnchantMaxCost));
        healMaxReal.setLore(lore);
        healMax.setItemMeta(healMaxReal);

    }


    public static void openForPlayer2v2(Player player, Game game){

        Inventory inventory = Bukkit.getServer().createInventory(null, teamEnchInvRow * 9, MainConfigHandler.shopLevelup);

        Team playerTeam = game.getPlayerTeam(player);
        String mapName = game.getRegion().getName();
        // 检查prot
        String[] protEnchantData = RelTeamEnchant.getMapEnchantprot(mapName);

        if (protEnchantData != null) {
            String enchantLevel = protEnchantData[1];
            switch (enchantLevel) {
                case "0":
                    inventory.setItem(levelupprotItemSlot, prot1);
                    break;
                case "1":
                    inventory.setItem(levelupprotItemSlot, prot2);
                    break;
                case "2":
                    inventory.setItem(levelupprotItemSlot, prot3);
                    break;
                case "3":
                    inventory.setItem(levelupprotItemSlot, prot4);
                    break;
                case "4":
                    inventory.setItem(levelupprotItemSlot, protMax);
                    break;
                default:
                    break;
            }
        }

        String[] sharpEnchantData = RelTeamEnchant.getMapEnchantsharp(mapName);
        if (sharpEnchantData != null) {
            String enchantLevel = sharpEnchantData[1];
            switch (enchantLevel) {
                case "0":
                    inventory.setItem(levelupsharpItemSlot, sharp1);
                    break;
                case "1":
                    inventory.setItem(levelupsharpItemSlot, sharp2);
                    break;
                case "2":
                    inventory.setItem(levelupsharpItemSlot, sharp3);
                    break;
                case "3":
                    inventory.setItem(levelupsharpItemSlot, sharp4);
                    break;
                case "4":
                    inventory.setItem(levelupsharpItemSlot, sharpMax);
                    break;
                default:
                    break;
            }
        }


        String[] hasteEffectData = RelTeamEffect.getMapEffecthaste(mapName);
        if (hasteEffectData != null) {
            String effectLevel = hasteEffectData[1];
            switch (effectLevel) {
                case "0":
                    inventory.setItem(leveluphasteItemSlot, haste1);
                    break;
                case "1":
                    inventory.setItem(leveluphasteItemSlot, haste2);
                    break;
                case "2":
                    inventory.setItem(leveluphasteItemSlot, hasteMax);
                    break;
                default:
                    break;
            }
        }

        String[] healEffectData = RelTeamEffect.getMapEffectheal(mapName);
        if (healEffectData != null) {
            String effectLevel = healEffectData[1];
            switch (effectLevel) {
                case "0":
                    inventory.setItem(leveluphealItemSlot, heal1);
                    break;
                case "1":
                    inventory.setItem(leveluphealItemSlot, healMax);
                    break;
                default:
                    break;
            }
        }










            player.openInventory(inventory);
    }

    public static void openForPlayer4v4(Player player, Game game){

        Inventory inventory = Bukkit.getServer().createInventory(null, teamEnchInvRow * 9, MainConfigHandler.shopLevelup);

        Team playerTeam = game.getPlayerTeam(player);
        String mapName = game.getRegion().getName();
        // 检查prot
        String[] protEnchantData = RelTeamEnchant.getMapEnchantprot(mapName);

        if (protEnchantData != null) {
            String enchantLevel = protEnchantData[1];
            switch (enchantLevel) {
                case "0":
                    inventory.setItem(levelupprotItemSlot, prot1);
                    break;
                case "1":
                    inventory.setItem(levelupprotItemSlot, prot2);
                    break;
                case "2":
                    inventory.setItem(levelupprotItemSlot, prot3);
                    break;
                case "3":
                    inventory.setItem(levelupprotItemSlot, prot4);
                    break;
                case "4":
                    inventory.setItem(levelupprotItemSlot, protMax);
                    break;
                default:
                    break;
            }
        }

        String[] sharpEnchantData = RelTeamEnchant.getMapEnchantsharp(mapName);
        if (sharpEnchantData != null) {
            String enchantLevel = sharpEnchantData[1];
            switch (enchantLevel) {
                case "0":
                    inventory.setItem(levelupsharpItemSlot, sharp1);
                    break;
                case "1":
                    inventory.setItem(levelupsharpItemSlot, sharp2);
                    break;
                case "2":
                    inventory.setItem(levelupsharpItemSlot, sharp3);
                    break;
                case "3":
                    inventory.setItem(levelupsharpItemSlot, sharp4);
                    break;
                case "4":
                    inventory.setItem(levelupsharpItemSlot, sharpMax);
                    break;
                default:
                    break;
            }
        }


        String[] hasteEffectData = RelTeamEffect.getMapEffecthaste(mapName);
        if (hasteEffectData != null) {
            String effectLevel = hasteEffectData[1];
            switch (effectLevel) {
                case "0":
                    inventory.setItem(leveluphasteItemSlot, haste1);
                    break;
                case "1":
                    inventory.setItem(leveluphasteItemSlot, haste2);
                    break;
                case "2":
                    inventory.setItem(leveluphasteItemSlot, hasteMax);
                    break;
                default:
                    break;
            }
        }

        String[] healEffectData = RelTeamEffect.getMapEffectheal(mapName);
        if (healEffectData != null) {
            String effectLevel = healEffectData[1];
            switch (effectLevel) {
                case "0":
                    inventory.setItem(leveluphealItemSlot, heal1);
                    break;
                case "1":
                    inventory.setItem(leveluphealItemSlot, healMax);
                    break;
                default:
                    break;
            }
        }










        player.openInventory(inventory);
    }









}
