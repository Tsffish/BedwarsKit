package github.tsffish.bedwarskit.util.teamshop;

import github.tsffish.bedwarskit.config.main.MainConfigHandler;
import github.tsffish.bedwarskit.util.teamshop.list.ListHaste;
import github.tsffish.bedwarskit.util.teamshop.list.ListHeal;
import github.tsffish.bedwarskit.util.teamshop.list.ListProt;
import github.tsffish.bedwarskit.util.teamshop.list.ListSharp;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.Team;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static github.tsffish.bedwarskit.config.main.MainConfigHandler.*;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class ShopMenu {
    private static final String className = "ShopMenu";
    private static final Material glassPane = Material.STAINED_GLASS_PANE;
    private static ItemStack paneItem;
    private static ItemStack sharp12v2;
    private static ItemStack sharp22v2;
    private static ItemStack sharp32v2;
    private static ItemStack sharp42v2;
    private static ItemStack sharpMax2v2;
    private static ItemStack prot12v2;
    private static ItemStack prot22v2;
    private static ItemStack prot32v2;
    private static ItemStack prot42v2;
    private static ItemStack protMax2v2;
    private static ItemStack haste12v2;
    private static ItemStack haste22v2;
    private static ItemStack hasteMax2v2;
    private static ItemStack heal12v2;
    private static ItemStack healMax2v2;
    private static ItemStack sharp14v4;
    private static ItemStack sharp24v4;
    private static ItemStack sharp34v4;
    private static ItemStack sharp44v4;
    private static ItemStack sharpMax4v4;
    private static ItemStack prot14v4;
    private static ItemStack prot24v4;
    private static ItemStack prot34v4;
    private static ItemStack prot44v4;
    private static ItemStack protMax4v4;
    private static ItemStack haste14v4;
    private static ItemStack haste24v4;
    private static ItemStack hasteMax4v4;
    private static ItemStack heal14v4;
    private static ItemStack healMax4v4;
    private static ItemStack res12v2;
    private static ItemStack res14v4;
    private static List<Integer> paneList;

    public static void loadLevelUpInv() {
        loadLevelUpInv2v2();
        loadLevelUpInv4v4();
    }

    private static void loadLevelUpInv2v2() {
        if (paneList == null) {
            paneList = new ArrayList<>(10);
        } else {
            paneList.clear();
        }

        paneList.add(27);
        paneList.add(28);
        paneList.add(29);
        paneList.add(30);

        paneList.add(31);
        paneList.add(32);
        paneList.add(33);
        paneList.add(34);

        paneList.add(35);

        List<String> lore = new ArrayList<>(6);
        lore.add("ERROR");

        paneItem = new ItemStack(glassPane, 1, (short) 7);
        ItemMeta paneItemReal = paneItem.getItemMeta();
        paneItemReal.setDisplayName(t(MainConfigHandler.paneItemName));
        lore.clear();
        for (String list : paneItemLore) {
            if (!list.isEmpty()) {
                lore.add(t(list));
            }
        }
        paneItemReal.setLore(lore);
        paneItem.setItemMeta(paneItemReal);


        sharp12v2 = new ItemStack(MainConfigHandler.levelupsharpItemType, 1);
        ItemMeta sharp1Real = sharp12v2.getItemMeta();
        sharp1Real.setDisplayName(t(MainConfigHandler.teamEnchItemName_sharp1));
        lore.clear();
        lore.add(t("&f" + MainConfigHandler.sharp1Cost2v2 + " " + MainConfigHandler.meanDiamond));
        sharp1Real.setLore(lore);
        sharp12v2.setItemMeta(sharp1Real);

        sharp22v2 = new ItemStack(MainConfigHandler.levelupsharpItemType, 1);
        ItemMeta sharp2Real = sharp22v2.getItemMeta();
        sharp2Real.setDisplayName(t(MainConfigHandler.teamEnchItemName_sharp2));
        lore.clear();
        lore.add(t("&f" + MainConfigHandler.sharp2Cost2v2 + " " + MainConfigHandler.meanDiamond));
        sharp2Real.setLore(lore);
        sharp22v2.setItemMeta(sharp2Real);


        sharp32v2 = new ItemStack(MainConfigHandler.levelupsharpItemType, 1);
        ItemMeta sharp3Real = sharp32v2.getItemMeta();
        sharp3Real.setDisplayName(t(MainConfigHandler.teamEnchItemName_sharp3));
        lore.clear();
        lore.add(t("&f" + MainConfigHandler.sharp3Cost2v2 + " " + MainConfigHandler.meanDiamond));
        sharp3Real.setLore(lore);
        sharp32v2.setItemMeta(sharp3Real);

        sharp42v2 = new ItemStack(MainConfigHandler.levelupsharpItemType, 1);
        ItemMeta sharp4Real = sharp42v2.getItemMeta();
        sharp4Real.setDisplayName(t(MainConfigHandler.teamEnchItemName_sharp4));
        lore.clear();
        lore.add(t("&f" + MainConfigHandler.sharp4Cost2v2 + " " + MainConfigHandler.meanDiamond));
        sharp4Real.setLore(lore);
        sharp42v2.setItemMeta(sharp4Real);

        sharpMax2v2 = new ItemStack(MainConfigHandler.levelupsharpItemType, 1);
        ItemMeta sharpMaxReal = sharpMax2v2.getItemMeta();
        sharpMaxReal.setDisplayName(t(MainConfigHandler.teamEnchItemName_sharpMax));
        lore.clear();
        lore.add(t("&f" + MainConfigHandler.TeamEnchantMaxCost));
        sharpMaxReal.setLore(lore);
        sharpMax2v2.setItemMeta(sharpMaxReal);

        prot12v2 = new ItemStack(MainConfigHandler.levelupprotItemType, 1);
        ItemMeta prot1Real = prot12v2.getItemMeta();
        prot1Real.setDisplayName(t(MainConfigHandler.teamEnchItemName_prot1));
        lore.clear();
        lore.add(t("&f" + MainConfigHandler.prot1Cost2v2 + " " + MainConfigHandler.meanDiamond));
        prot1Real.setLore(lore);
        prot12v2.setItemMeta(prot1Real);

        prot22v2 = new ItemStack(MainConfigHandler.levelupprotItemType, 1);
        ItemMeta prot2Real = prot22v2.getItemMeta();
        prot2Real.setDisplayName(t(MainConfigHandler.teamEnchItemName_prot2));
        lore.clear();
        lore.add(t("&f" + MainConfigHandler.prot2Cost2v2 + " " + MainConfigHandler.meanDiamond));
        prot2Real.setLore(lore);
        prot22v2.setItemMeta(prot2Real);


        prot32v2 = new ItemStack(MainConfigHandler.levelupprotItemType, 1);
        ItemMeta prot3Real = prot32v2.getItemMeta();
        prot3Real.setDisplayName(t(MainConfigHandler.teamEnchItemName_prot3));
        lore.clear();
        lore.add(t("&f" + MainConfigHandler.prot3Cost2v2 + " " + MainConfigHandler.meanDiamond));
        prot3Real.setLore(lore);
        prot32v2.setItemMeta(prot3Real);

        prot42v2 = new ItemStack(MainConfigHandler.levelupprotItemType, 1);
        ItemMeta prot4Real = prot42v2.getItemMeta();
        prot4Real.setDisplayName(t(MainConfigHandler.teamEnchItemName_prot4));
        lore.clear();
        lore.add(t("&f" + MainConfigHandler.prot4Cost2v2 + " " + MainConfigHandler.meanDiamond));
        prot4Real.setLore(lore);
        prot42v2.setItemMeta(prot4Real);

        protMax2v2 = new ItemStack(MainConfigHandler.levelupprotItemType, 1);
        ItemMeta protMaxReal = protMax2v2.getItemMeta();
        protMaxReal.setDisplayName(t(MainConfigHandler.teamEnchItemName_protMax));
        lore.clear();
        lore.add(t("&f" + MainConfigHandler.TeamEnchantMaxCost));
        protMaxReal.setLore(lore);
        protMax2v2.setItemMeta(protMaxReal);

        haste12v2 = new ItemStack(MainConfigHandler.leveluphasteItemType, 1);
        ItemMeta haste1Real = haste12v2.getItemMeta();
        haste1Real.setDisplayName(t(MainConfigHandler.teamEffItemName_haste1));
        lore.clear();
        lore.add(t("&f" + MainConfigHandler.haste1Cost2v2 + " " + MainConfigHandler.meanDiamond));
        haste1Real.setLore(lore);
        haste12v2.setItemMeta(haste1Real);

        haste22v2 = new ItemStack(MainConfigHandler.leveluphasteItemType, 1);
        ItemMeta haste2Real = haste22v2.getItemMeta();
        haste2Real.setDisplayName(t(MainConfigHandler.teamEffItemName_haste2));
        lore.clear();
        lore.add(t("&f" + MainConfigHandler.haste2Cost2v2 + " " + MainConfigHandler.meanDiamond));
        haste2Real.setLore(lore);
        haste22v2.setItemMeta(haste2Real);

        hasteMax2v2 = new ItemStack(MainConfigHandler.leveluphasteItemType, 1);
        ItemMeta hasteMaxReal = hasteMax2v2.getItemMeta();
        hasteMaxReal.setDisplayName(t(MainConfigHandler.teamEffItemName_hasteMax));
        lore.clear();
        lore.add(t("&f" + MainConfigHandler.TeamEnchantMaxCost));
        hasteMaxReal.setLore(lore);
        hasteMax2v2.setItemMeta(hasteMaxReal);

        heal12v2 = new ItemStack(MainConfigHandler.leveluphealItemType, 1);
        ItemMeta heal1Real = heal12v2.getItemMeta();
        heal1Real.setDisplayName(t(MainConfigHandler.teamEffItemName_heal1));
        lore.clear();
        lore.add(t("&f" + MainConfigHandler.heal1Cost2v2 + " " + MainConfigHandler.meanDiamond));
        heal1Real.setLore(lore);
        heal12v2.setItemMeta(heal1Real);

        healMax2v2 = new ItemStack(MainConfigHandler.leveluphealItemType, 1);
        ItemMeta healMaxReal = healMax2v2.getItemMeta();
        healMaxReal.setDisplayName(t(MainConfigHandler.teamEffItemName_healMax));
        lore.clear();
        lore.add(t("&f" + MainConfigHandler.TeamEnchantMaxCost));
        healMaxReal.setLore(lore);
        healMax2v2.setItemMeta(healMaxReal);

        res12v2 = new ItemStack(MainConfigHandler.levelupresItemType, 1);
        ItemMeta res12v2Real = res12v2.getItemMeta();
        res12v2Real.setDisplayName(t(levelupresItemName));
        lore.clear();
        lore.add(t("&f" + MainConfigHandler.heal1Cost2v2 + " " + MainConfigHandler.meanDiamond));
        res12v2Real.setLore(lore);
        res12v2.setItemMeta(res12v2Real);


    }

    private static void loadLevelUpInv4v4() {
        if (paneList == null) {
            paneList = new ArrayList<>(10);
        } else {
            paneList.clear();
        }

        paneList.add(27);
        paneList.add(28);
        paneList.add(29);
        paneList.add(30);

        paneList.add(31);
        paneList.add(32);
        paneList.add(33);
        paneList.add(34);

        paneList.add(35);
        List<String> lore = new ArrayList<>(6);
        lore.add("ERROR");


        paneItem = new ItemStack(glassPane, 1, (short) 7);
        ItemMeta paneItemReal = sharp12v2.getItemMeta();
        paneItemReal.setDisplayName(t(MainConfigHandler.paneItemName));
        lore.clear();
        for (String list : paneItemLore) {
            lore.add(t(list));
        }
        paneItemReal.setLore(lore);
        paneItem.setItemMeta(paneItemReal);

        sharp14v4 = new ItemStack(MainConfigHandler.levelupsharpItemType, 1);
        ItemMeta sharp1Real = sharp14v4.getItemMeta();
        sharp1Real.setDisplayName(t(MainConfigHandler.teamEnchItemName_sharp1));
        lore.clear();
        lore.add(t("&f" + MainConfigHandler.sharp1Cost4v4 + " " + MainConfigHandler.meanDiamond));
        sharp1Real.setLore(lore);
        sharp14v4.setItemMeta(sharp1Real);

        sharp24v4 = new ItemStack(MainConfigHandler.levelupsharpItemType, 1);
        ItemMeta sharp2Real = sharp24v4.getItemMeta();
        sharp2Real.setDisplayName(t(MainConfigHandler.teamEnchItemName_sharp2));
        lore.clear();
        lore.add(t("&f" + MainConfigHandler.sharp2Cost4v4 + " " + MainConfigHandler.meanDiamond));
        sharp2Real.setLore(lore);
        sharp24v4.setItemMeta(sharp2Real);


        sharp34v4 = new ItemStack(MainConfigHandler.levelupsharpItemType, 1);
        ItemMeta sharp3Real = sharp34v4.getItemMeta();
        sharp3Real.setDisplayName(t(MainConfigHandler.teamEnchItemName_sharp3));
        lore.clear();
        lore.add(t("&f" + MainConfigHandler.sharp3Cost4v4 + " " + MainConfigHandler.meanDiamond));
        sharp3Real.setLore(lore);
        sharp34v4.setItemMeta(sharp3Real);

        sharp44v4 = new ItemStack(MainConfigHandler.levelupsharpItemType, 1);
        ItemMeta sharp4Real = sharp44v4.getItemMeta();
        sharp4Real.setDisplayName(t(MainConfigHandler.teamEnchItemName_sharp4));
        lore.clear();
        lore.add(t("&f" + MainConfigHandler.sharp4Cost4v4 + " " + MainConfigHandler.meanDiamond));
        sharp4Real.setLore(lore);
        sharp44v4.setItemMeta(sharp4Real);

        sharpMax4v4 = new ItemStack(MainConfigHandler.levelupsharpItemType, 1);
        ItemMeta sharpMaxReal = sharpMax4v4.getItemMeta();
        sharpMaxReal.setDisplayName(t(MainConfigHandler.teamEnchItemName_sharpMax));
        lore.clear();
        lore.add(t("&f" + MainConfigHandler.TeamEnchantMaxCost));
        sharpMaxReal.setLore(lore);
        sharpMax4v4.setItemMeta(sharpMaxReal);

        prot14v4 = new ItemStack(MainConfigHandler.levelupprotItemType, 1);
        ItemMeta prot1Real = prot14v4.getItemMeta();
        prot1Real.setDisplayName(t(MainConfigHandler.teamEnchItemName_prot1));
        lore.clear();
        lore.add(t("&f" + MainConfigHandler.prot1Cost4v4 + " " + MainConfigHandler.meanDiamond));
        prot1Real.setLore(lore);
        prot14v4.setItemMeta(prot1Real);

        prot24v4 = new ItemStack(MainConfigHandler.levelupprotItemType, 1);
        ItemMeta prot2Real = prot24v4.getItemMeta();
        prot2Real.setDisplayName(t(MainConfigHandler.teamEnchItemName_prot2));
        lore.clear();
        lore.add(t("&f" + MainConfigHandler.prot2Cost4v4 + " " + MainConfigHandler.meanDiamond));
        prot2Real.setLore(lore);
        prot24v4.setItemMeta(prot2Real);


        prot34v4 = new ItemStack(MainConfigHandler.levelupprotItemType, 1);
        ItemMeta prot3Real = prot34v4.getItemMeta();
        prot3Real.setDisplayName(t(MainConfigHandler.teamEnchItemName_prot3));
        lore.clear();
        lore.add(t("&f" + MainConfigHandler.prot3Cost4v4 + " " + MainConfigHandler.meanDiamond));
        prot3Real.setLore(lore);
        prot34v4.setItemMeta(prot3Real);

        prot44v4 = new ItemStack(MainConfigHandler.levelupprotItemType, 1);
        ItemMeta prot4Real = prot44v4.getItemMeta();
        prot4Real.setDisplayName(t(MainConfigHandler.teamEnchItemName_prot4));
        lore.clear();
        lore.add(t("&f" + MainConfigHandler.prot4Cost4v4 + " " + MainConfigHandler.meanDiamond));
        prot4Real.setLore(lore);
        prot44v4.setItemMeta(prot4Real);

        protMax4v4 = new ItemStack(MainConfigHandler.levelupprotItemType, 1);
        ItemMeta protMaxReal = protMax4v4.getItemMeta();
        protMaxReal.setDisplayName(t(MainConfigHandler.teamEnchItemName_protMax));
        lore.clear();
        lore.add(t("&f" + MainConfigHandler.TeamEnchantMaxCost));
        protMaxReal.setLore(lore);
        protMax4v4.setItemMeta(protMaxReal);

        haste14v4 = new ItemStack(MainConfigHandler.leveluphasteItemType, 1);
        ItemMeta haste1Real = haste14v4.getItemMeta();
        haste1Real.setDisplayName(t(MainConfigHandler.teamEffItemName_haste1));
        lore.clear();
        lore.add(t("&f" + MainConfigHandler.haste1Cost4v4 + " " + MainConfigHandler.meanDiamond));
        haste1Real.setLore(lore);
        haste14v4.setItemMeta(haste1Real);

        haste24v4 = new ItemStack(MainConfigHandler.leveluphasteItemType, 1);
        ItemMeta haste2Real = haste24v4.getItemMeta();
        haste2Real.setDisplayName(t(MainConfigHandler.teamEffItemName_haste2));
        lore.clear();
        lore.add(t("&f" + MainConfigHandler.haste2Cost4v4 + " " + MainConfigHandler.meanDiamond));
        haste2Real.setLore(lore);
        haste24v4.setItemMeta(haste2Real);

        hasteMax4v4 = new ItemStack(MainConfigHandler.leveluphasteItemType, 1);
        ItemMeta hasteMaxReal = hasteMax4v4.getItemMeta();
        hasteMaxReal.setDisplayName(t(MainConfigHandler.teamEffItemName_hasteMax));
        lore.clear();
        lore.add(t("&f" + MainConfigHandler.TeamEnchantMaxCost));
        hasteMaxReal.setLore(lore);
        hasteMax4v4.setItemMeta(hasteMaxReal);

        heal14v4 = new ItemStack(MainConfigHandler.leveluphealItemType, 1);
        ItemMeta heal1Real = heal14v4.getItemMeta();
        heal1Real.setDisplayName(t(MainConfigHandler.teamEffItemName_heal1));
        lore.clear();
        lore.add(t("&f" + MainConfigHandler.heal1Cost4v4 + " " + MainConfigHandler.meanDiamond));
        heal1Real.setLore(lore);
        heal14v4.setItemMeta(heal1Real);

        healMax4v4 = new ItemStack(MainConfigHandler.leveluphealItemType, 1);
        ItemMeta healMaxReal = healMax4v4.getItemMeta();
        healMaxReal.setDisplayName(t(MainConfigHandler.teamEffItemName_healMax));
        lore.clear();
        lore.add(t("&f" + MainConfigHandler.TeamEnchantMaxCost));
        healMaxReal.setLore(lore);
        healMax4v4.setItemMeta(healMaxReal);

        res14v4 = new ItemStack(MainConfigHandler.levelupresItemType, 1);
        ItemMeta res14v4Real = res14v4.getItemMeta();
        res14v4Real.setDisplayName(t(levelupresItemName));
        lore.clear();
        lore.add(t("&f" + MainConfigHandler.heal1Cost2v2 + " " + MainConfigHandler.meanDiamond));
        res14v4Real.setLore(lore);
        res14v4.setItemMeta(res14v4Real);
    }

    public static void openForPlayer2v2(Player player, Game game) {

        Inventory inventory = Bukkit.getServer().createInventory(null, teamEnchInvRow * 9, shopLevelup);

        int minSlot = 0;
        int maxSlot = inventory.getSize() - 1;

        for (int i : paneList) {
            ItemStack item = paneItem.clone();
            inventory.setItem(i, item);
        }

        Team team = game.getPlayerTeam(player);

        String teamName = team.getName();
        String gameName = game.getName();

        if (levelupShop) {

            if (leveluphasteItemSlot != -1) {
                int setTo = leveluphasteItemSlot;
                if (setTo < minSlot) {
                    setTo = minSlot;
                } else if (setTo > maxSlot) {
                    setTo = maxSlot;
                }
                Set<String[]> teamDatas = ListHaste.getTeamDatas(gameName);

                for (String[] strings : teamDatas) {
                    if (strings[0].equals(teamName)) {
                        String level = strings[1];
                        switch (level) {
                            case "0":
                                inventory.setItem(setTo, haste12v2);
                                break;
                            case "1":
                                inventory.setItem(setTo, haste22v2);
                                break;
                            case "2":
                                inventory.setItem(setTo, hasteMax2v2);
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        }

        if (levelupShop) {
            if (leveluphealItemSlot != -1) {
                int setTo = leveluphealItemSlot;
                if (setTo < minSlot) {
                    setTo = minSlot;
                } else if (setTo > maxSlot) {
                    setTo = maxSlot;
                }
                Set<String[]> teamDatas = ListHeal.getTeamDatas(gameName);

                for (String[] strings : teamDatas) {
                    if (strings[0].equals(teamName)) {
                        String level = strings[1];
                        switch (level) {
                            case "0":
                                inventory.setItem(setTo, heal12v2);
                                break;
                            case "1":
                                inventory.setItem(setTo, healMax2v2);
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        }

        if (levelupShop) {

            if (levelupprotItemSlot != -1) {
                int setTo = levelupprotItemSlot;
                if (setTo < minSlot) {
                    setTo = minSlot;
                } else if (setTo > maxSlot) {
                    setTo = maxSlot;
                }

                Set<String[]> teamDatas = ListProt.getTeamDatas(gameName);

                for (String[] strings : teamDatas) {
                    if (strings[0].equals(teamName)) {
                        String level = strings[1];
                        switch (level) {
                            case "0":
                                inventory.setItem(setTo, prot12v2);
                                break;
                            case "1":
                                inventory.setItem(setTo, prot22v2);
                                break;
                            case "2":
                                inventory.setItem(setTo, prot32v2);
                                break;
                            case "3":
                                inventory.setItem(setTo, prot42v2);
                                break;
                            case "4":
                                inventory.setItem(setTo, protMax2v2);
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        }

        if (levelupShop) {

            if (levelupsharpItemSlot != -1) {
                int setTo = levelupsharpItemSlot;
                if (setTo < minSlot) {
                    setTo = minSlot;
                } else if (setTo > maxSlot) {
                    setTo = maxSlot;
                }

                Set<String[]> teamDatas = ListSharp.getTeamDatas(gameName);

                for (String[] strings : teamDatas) {
                    if (strings[0].equals(teamName)) {
                        String level = strings[1];
                        switch (level) {
                            case "0":
                                inventory.setItem(setTo, sharp12v2);
                                break;
                            case "1":
                                inventory.setItem(setTo, sharp22v2);
                                break;
                            case "2":
                                inventory.setItem(setTo, sharp32v2);
                                break;
                            case "3":
                                inventory.setItem(setTo, sharp42v2);
                                break;
                            case "4":
                                inventory.setItem(setTo, sharpMax2v2);
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        }

        if (levelupresItemSlot > -1) {
            int setTo = levelupresItemSlot;
            if (setTo < minSlot) {
                setTo = minSlot;
            } else if (setTo > maxSlot) {
                setTo = maxSlot;
            }
            inventory.setItem(setTo, res12v2);
        }

        player.openInventory(inventory);
    }

    public static void openForPlayer4v4(Player player, Game game) {

        Inventory inventory = Bukkit.getServer().createInventory(null, teamEnchInvRow * 9, shopLevelup);

        int minSlot = 0;
        int maxSlot = inventory.getSize() - 1;

        for (int i : paneList) {
            ItemStack item = paneItem.clone();
            inventory.setItem(i, item);
        }

        Team team = game.getPlayerTeam(player);

        String teamName = team.getName();
        String gameName = game.getName();

        if (levelupShop) {

            if (leveluphasteItemSlot != -1) {
                int setTo = leveluphasteItemSlot;
                if (setTo < minSlot) {
                    setTo = minSlot;
                } else if (setTo > maxSlot) {
                    setTo = maxSlot;
                }
                Set<String[]> teamDatas = ListHaste.getTeamDatas(gameName);

                for (String[] strings : teamDatas) {
                    if (strings[0].equals(teamName)) {
                        String level = strings[1];
                        switch (level) {
                            case "0":
                                inventory.setItem(setTo, haste14v4);
                                break;
                            case "1":
                                inventory.setItem(setTo, haste24v4);
                                break;
                            case "2":
                                inventory.setItem(setTo, hasteMax4v4);
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        }

        if (levelupShop) {
            if (leveluphealItemSlot != -1) {
                int setTo = leveluphealItemSlot;
                if (setTo < minSlot) {
                    setTo = minSlot;
                } else if (setTo > maxSlot) {
                    setTo = maxSlot;
                }
                Set<String[]> teamDatas = ListHeal.getTeamDatas(gameName);

                for (String[] strings : teamDatas) {
                    if (strings[0].equals(teamName)) {
                        String level = strings[1];
                        switch (level) {
                            case "0":
                                inventory.setItem(setTo, heal14v4);
                                break;
                            case "1":
                                inventory.setItem(setTo, healMax4v4);
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        }

        if (levelupShop) {

            if (levelupprotItemSlot != -1) {
                int setTo = levelupprotItemSlot;
                if (setTo < minSlot) {
                    setTo = minSlot;
                } else if (setTo > maxSlot) {
                    setTo = maxSlot;
                }

                Set<String[]> teamDatas = ListProt.getTeamDatas(gameName);

                for (String[] strings : teamDatas) {
                    if (strings[0].equals(teamName)) {
                        String level = strings[1];
                        switch (level) {
                            case "0":
                                inventory.setItem(setTo, prot14v4);
                                break;
                            case "1":
                                inventory.setItem(setTo, prot24v4);
                                break;
                            case "2":
                                inventory.setItem(setTo, prot34v4);
                                break;
                            case "3":
                                inventory.setItem(setTo, prot44v4);
                                break;
                            case "4":
                                inventory.setItem(setTo, protMax4v4);
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        }

        if (levelupShop) {

            if (levelupsharpItemSlot != -1) {
                int setTo = levelupsharpItemSlot;
                if (setTo < minSlot) {
                    setTo = minSlot;
                } else if (setTo > maxSlot) {
                    setTo = maxSlot;
                }

                Set<String[]> teamDatas = ListSharp.getTeamDatas(gameName);

                for (String[] strings : teamDatas) {
                    if (strings[0].equals(teamName)) {
                        String level = strings[1];
                        switch (level) {
                            case "0":
                                inventory.setItem(setTo, sharp14v4);
                                break;
                            case "1":
                                inventory.setItem(setTo, sharp24v4);
                                break;
                            case "2":
                                inventory.setItem(setTo, sharp34v4);
                                break;
                            case "3":
                                inventory.setItem(setTo, sharp44v4);
                                break;
                            case "4":
                                inventory.setItem(setTo, sharpMax4v4);
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        }

        if (levelupresItemSlot > -1) {
            int setTo = levelupresItemSlot;
            if (setTo < minSlot) {
                setTo = minSlot;
            } else if (setTo > maxSlot) {
                setTo = maxSlot;
            }
            inventory.setItem(setTo, res14v4);
        }

        player.openInventory(inventory);
    }

}
