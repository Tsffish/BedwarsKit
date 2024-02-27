package github.tsffish.bedwarskit.listener;

import github.tsffish.bedwarskit.util.teamshop.list.ListHaste;
import github.tsffish.bedwarskit.util.teamshop.list.ListHeal;
import github.tsffish.bedwarskit.util.teamshop.list.ListProt;
import github.tsffish.bedwarskit.util.teamshop.list.ListSharp;
import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameManager;
import io.github.bedwarsrel.game.Team;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;

import static github.tsffish.bedwarskit.config.kit.KitConfigHandler.*;
import static github.tsffish.bedwarskit.config.main.MainConfigHandler.*;
import static github.tsffish.bedwarskit.listener.RelPlayerOpenShop.openShop;
import static github.tsffish.bedwarskit.util.RelPlayerKit.*;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;
import static github.tsffish.bedwarskit.util.misc.MessSender.consoleSendCommand;
import static github.tsffish.bedwarskit.util.misc.SoundPlayer.*;
import static github.tsffish.bedwarskit.util.teamshop.ShopMenu.openForPlayer2v2;
import static github.tsffish.bedwarskit.util.teamshop.ShopMenu.openForPlayer4v4;

public class RelClickInventory implements Listener {
    private static final Material woodSword = Material.WOOD_SWORD;
    @EventHandler
    public void on(final InventoryClickEvent event) {
        Inventory inventory = event.getClickedInventory();

        if (inventory == null) return;
        String inventoryName = inventory.getName();

        if (inventoryName == null) return;
        ItemStack itemStack = event.getCurrentItem();

        if (itemStack == null || itemStack.getType() == Material.AIR) return;
        Material itemType = itemStack.getType();

        if (itemType == null) return;
        String itemTypeText = itemType.toString().toUpperCase();

        Player player = (Player) event.getWhoClicked();
        if (player == null || !player.isOnline()) return;

        String playerName = player.getName();
        UUID uuid = player.getUniqueId();

        if (inventoryName.contains(kitMenuTitle)) {
            boolean isSelected = false;
            event.setCancelled(true);
            if (itemType == KitDefaultItemType) {
                setPlayerKit(uuid, kitNameDefault);
                player.sendMessage(t(meanSelKitSucc));
                isSelected = true;
            } else if (itemType == KitNoneItemType) {
                setPlayerKit(uuid, kitNameNone);
                player.sendMessage(t(meanSelKitSucc));
                isSelected = true;

            } else if (itemType == KitDefaultlessItemType) {
                setPlayerKit(uuid, kitNameDefaultLess);
                player.sendMessage(t(meanSelKitSucc));
                isSelected = true;

            }

            if (isSelected) {
                playerSoundSucc(player);
                player.closeInventory();
            }
        }

        if (inventoryName.equals(shopLevelup)) {
            event.setCancelled(true);
        }


        if (dieOutGameItem_playAgain) {
            if (itemTypeText.equals(dieOutGameItem_playAgain_ItemType.toString())
                    && itemStack.getItemMeta().getDisplayName().equals(dieOutGameItem_playAgain_ItemName)) {
                String willSend = dieOutGameItem_playAgain_ClickSendCommand.replace("{player}", playerName);
                consoleSendCommand(willSend);
            }
        }

        if (itemType == LevelupItemType) {
            if (levelupShopDelayOpen) {
                playerSoundOpen(player);
                openShop(player, 8L);
            } else {
                playerSoundOpen(player);
                openShop(player, 0L);
            }
        }
        if (player.getWorld().getName().contains(rushWorld)
                && inventoryName.equalsIgnoreCase(shopLevelup)) {

            GameManager gameManager = BedwarsRel.getInstance().getGameManager();
            Game game = gameManager.getGameOfPlayer(player);
            Team playerTeam = game.getPlayerTeam(player);

            String gameName = game.getName();

            ItemStack clickedItem = event.getCurrentItem();
            event.setCancelled(true);

            if (clickedItem == null) {
                return;
            }

            if (event.getCurrentItem() != null
                    && event.getCurrentItem().getType() == woodSword) {
                ItemStack cursorItem = event.getCursor();
                if (cursorItem != null
                        && cursorItem.getType() == woodSword) {
                    event.setCancelled(true);
                }
            }

            String clickedItemName = clickedItem.getItemMeta().getDisplayName();
            String teamName = playerTeam.getName();
            Material clickedItemType = clickedItem.getType();

            boolean buyFail = true;

            if (player.getWorld().getName().contains(rushWorld2v2)) {
                {

                    if (clickedItemType == leveluphasteItemType
                            && clickedItem.hasItemMeta()) {

                        List<String[]> teamDatas = ListHaste.getTeamDatas(gameName);

                        if (clickedItemType == leveluphasteItemType) {

                            for (String[] strings : teamDatas) {
                                if (clickedItemName.equals(teamEffItemName_Haste1)
                                        && player.getInventory().contains(Material.DIAMOND, haste1Cost2v2)) {
                                    if (strings[0].equals(teamName)
                                            && !strings[1].equals("2") && !strings[1].equals("1")) {
                                        player.getInventory().removeItem(new ItemStack(Material.DIAMOND, haste1Cost2v2));

                                        List<String[]> newteamDatas = new ArrayList<>(9);
                                        for (String[] data : teamDatas) {
                                            newteamDatas.add(data.clone());
                                        }

                                        for (String team : game.getTeams().keySet()) {
                                            String[] teamData = new String[]{teamName, "1"};
                                            for (int i = 0; i < newteamDatas.size(); i++) {
                                                if (newteamDatas.get(i)[0].equals(team)) {
                                                    newteamDatas.set(i, teamData);
                                                    break;
                                                }
                                            }
                                        }

                                        teamDatas = new ArrayList<>(newteamDatas);
                                        ListHaste.setTeamDatas(gameName, teamDatas);

                                        String mess = messLevelUphaste1.replace("{player}", player.getName());
                                        player.sendMessage(t(mess));
                                        buyFail = false;
                                    } else {
                                        String mess = messLevelUpFailed.replace("{player}", player.getName());
                                        player.sendMessage(t(mess));
                                    }
                                } else if (clickedItemName.equals(teamEffItemName_Haste2) && player.getInventory().contains(Material.DIAMOND, haste2Cost2v2)) {

                                    if (strings[0].equals(teamName)
                                            && !strings[1].equals("2")) {
                                        player.getInventory().removeItem(new ItemStack(Material.DIAMOND, haste2Cost2v2));

                                        List<String[]> newteamDatas = new ArrayList<>(9);
                                        for (String[] data : teamDatas) {
                                            newteamDatas.add(data.clone());
                                        }

                                        for (String team : game.getTeams().keySet()) {
                                            String[] teamData = new String[]{teamName, "2"};
                                            for (int i = 0; i < newteamDatas.size(); i++) {
                                                if (newteamDatas.get(i)[0].equals(team)) {
                                                    newteamDatas.set(i, teamData);
                                                    break;
                                                }
                                            }
                                        }

                                        teamDatas = new ArrayList<>(newteamDatas);
                                        ListHaste.setTeamDatas(gameName, teamDatas);

                                        String mess = messLevelUphaste2.replace("{player}", player.getName());
                                        player.sendMessage(t(mess));
                                        buyFail = false;
                                    }
                                }
                            }
                        }
                    }
                    if (clickedItemType == leveluphealItemType) {

                        List<String[]> teamDatas = ListHeal.getTeamDatas(gameName);

                        for (String[] strings : teamDatas) {
                            if (clickedItemName.equals(teamEffItemName_Heal1)
                                    && player.getInventory().contains(Material.DIAMOND, heal1Cost2v2)) {
                                if (strings[0].equals(teamName)
                                        && !strings[1].equals("1")) {
                                    player.getInventory().removeItem(new ItemStack(Material.DIAMOND, heal1Cost2v2));

                                    List<String[]> newteamDatas = new ArrayList<>(9);
                                    for (String[] data : teamDatas) {
                                        newteamDatas.add(data.clone());
                                    }

                                    for (String team : game.getTeams().keySet()) {
                                        String[] teamData = new String[]{teamName, "1"};
                                        for (int i = 0; i < newteamDatas.size(); i++) {
                                            if (newteamDatas.get(i)[0].equals(team)) {
                                                newteamDatas.set(i, teamData);
                                                break;
                                            }
                                        }
                                    }

                                    teamDatas = new ArrayList<>(newteamDatas);
                                    ListHeal.setTeamDatas(gameName, teamDatas);

                                    String mess = messLevelUpheal1.replace("{player}", player.getName());
                                    player.sendMessage(t(mess));
                                    buyFail = false;
                                }
                            }
                        }
                    }
                    if (clickedItemType == levelupsharpItemType) {

                        List<String[]> teamDatas = ListSharp.getTeamDatas(gameName);

                        for (String[] strings : teamDatas) {
                            if (clickedItemName.equals(teamEnchItemName_sharp1)
                                    && player.getInventory().contains(Material.DIAMOND, sharp1Cost2v2)) {
                                if (strings[0].equals(teamName)
                                        && !strings[1].equals("1")
                                        && !strings[1].equals("2")
                                        && !strings[1].equals("3")
                                        && !strings[1].equals("4")) {
                                    player.getInventory().removeItem(new ItemStack(Material.DIAMOND, sharp1Cost2v2));

                                    List<String[]> newteamDatas = new ArrayList<>(9);
                                    for (String[] data : teamDatas) {
                                        newteamDatas.add(data.clone());
                                    }

                                    for (String team : game.getTeams().keySet()) {
                                        String[] teamData = new String[]{teamName, "1"};
                                        for (int i = 0; i < newteamDatas.size(); i++) {
                                            if (newteamDatas.get(i)[0].equals(team)) {
                                                newteamDatas.set(i, teamData);
                                                break;
                                            }
                                        }
                                    }

                                    teamDatas = new ArrayList<>(newteamDatas);
                                    ListSharp.setTeamDatas(gameName, teamDatas);

                                    String mess = messLevelUpsharp1.replace("{player}", player.getName());
                                    player.sendMessage(t(mess));
                                    buyFail = false;
                                }
                            } else if (clickedItemName.equals(teamEnchItemName_sharp2)
                                    && player.getInventory().contains(Material.DIAMOND, sharp2Cost2v2)) {
                                if (strings[0].equals(teamName)
                                        && !strings[1].equals("2")
                                        && !strings[1].equals("3")
                                        && !strings[1].equals("4")) {
                                    player.getInventory().removeItem(new ItemStack(Material.DIAMOND, sharp2Cost2v2));

                                    List<String[]> newteamDatas = new ArrayList<>(9);
                                    for (String[] data : teamDatas) {
                                        newteamDatas.add(data.clone());
                                    }

                                    for (String team : game.getTeams().keySet()) {
                                        String[] teamData = new String[]{teamName, "2"};
                                        for (int i = 0; i < newteamDatas.size(); i++) {
                                            if (newteamDatas.get(i)[0].equals(team)) {
                                                newteamDatas.set(i, teamData);
                                                break;
                                            }
                                        }
                                    }

                                    teamDatas = new ArrayList<>(newteamDatas);
                                    ListSharp.setTeamDatas(gameName, teamDatas);

                                    String mess = messLevelUpsharp2.replace("{player}", player.getName());
                                    player.sendMessage(t(mess));
                                    buyFail = false;
                                }
                            } else if (clickedItemName.equals(teamEnchItemName_sharp3)
                                    && player.getInventory().contains(Material.DIAMOND, sharp3Cost2v2)) {
                                if (strings[0].equals(teamName)
                                        && !strings[1].equals("3")
                                        && !strings[1].equals("4")) {
                                    player.getInventory().removeItem(new ItemStack(Material.DIAMOND, sharp3Cost2v2));

                                    List<String[]> newteamDatas = new ArrayList<>(9);
                                    for (String[] data : teamDatas) {
                                        newteamDatas.add(data.clone());
                                    }

                                    for (String team : game.getTeams().keySet()) {
                                        String[] teamData = new String[]{teamName, "3"};
                                        for (int i = 0; i < newteamDatas.size(); i++) {
                                            if (newteamDatas.get(i)[0].equals(team)) {
                                                newteamDatas.set(i, teamData);
                                                break;
                                            }
                                        }
                                    }

                                    teamDatas = new ArrayList<>(newteamDatas);
                                    ListSharp.setTeamDatas(gameName, teamDatas);

                                    String mess = messLevelUpsharp3.replace("{player}", player.getName());
                                    player.sendMessage(t(mess));
                                    buyFail = false;
                                }
                            } else if (clickedItemName.equals(teamEnchItemName_sharp4)
                                    && player.getInventory().contains(Material.DIAMOND, sharp4Cost2v2)) {
                                if (strings[0].equals(teamName)
                                        && !strings[1].equals("4")) {
                                    player.getInventory().removeItem(new ItemStack(Material.DIAMOND, sharp4Cost2v2));

                                    List<String[]> newteamDatas = new ArrayList<>(9);
                                    for (String[] data : teamDatas) {
                                        newteamDatas.add(data.clone());
                                    }

                                    for (String team : game.getTeams().keySet()) {
                                        String[] teamData = new String[]{teamName, "4"};
                                        for (int i = 0; i < newteamDatas.size(); i++) {
                                            if (newteamDatas.get(i)[0].equals(team)) {
                                                newteamDatas.set(i, teamData);
                                                break;
                                            }
                                        }
                                    }

                                    teamDatas = new ArrayList<>(newteamDatas);
                                    ListSharp.setTeamDatas(gameName, teamDatas);

                                    String mess = messLevelUpsharp4.replace("{player}", player.getName());
                                    player.sendMessage(t(mess));
                                    buyFail = false;
                                }
                            }
                        }
                    }
                    if (clickedItemType == levelupprotItemType) {

                        List<String[]> teamDatas = ListProt.getTeamDatas(gameName);

                        for (String[] strings : teamDatas) {
                            if (clickedItemName.equals(teamEnchItemName_prot1)
                                    && player.getInventory().contains(Material.DIAMOND, prot1Cost2v2)) {
                                if (strings[0].equals(teamName)
                                        && !strings[1].equals("1")
                                        && !strings[1].equals("2")
                                        && !strings[1].equals("3")
                                        && !strings[1].equals("4")) {
                                    player.getInventory().removeItem(new ItemStack(Material.DIAMOND, prot1Cost2v2));

                                    List<String[]> newteamDatas = new ArrayList<>(9);
                                    for (String[] data : teamDatas) {
                                        newteamDatas.add(data.clone());
                                    }

                                    for (String team : game.getTeams().keySet()) {
                                        String[] teamData = new String[]{teamName, "1"};
                                        for (int i = 0; i < newteamDatas.size(); i++) {
                                            if (newteamDatas.get(i)[0].equals(team)) {
                                                newteamDatas.set(i, teamData);
                                                break;
                                            }
                                        }
                                    }

                                    teamDatas = new ArrayList<>(newteamDatas);
                                    ListProt.setTeamDatas(gameName, teamDatas);

                                    String mess = messLevelUpprot1.replace("{player}", player.getName());
                                    player.sendMessage(t(mess));
                                    buyFail = false;
                                }
                            } else if (clickedItemName.equals(teamEnchItemName_prot2)
                                    && player.getInventory().contains(Material.DIAMOND, prot2Cost2v2)) {
                                if (strings[0].equals(teamName)
                                        && !strings[1].equals("2")
                                        && !strings[1].equals("3")
                                        && !strings[1].equals("4")) {
                                    player.getInventory().removeItem(new ItemStack(Material.DIAMOND, prot2Cost2v2));

                                    List<String[]> newteamDatas = new ArrayList<>(9);
                                    for (String[] data : teamDatas) {
                                        newteamDatas.add(data.clone());
                                    }

                                    for (String team : game.getTeams().keySet()) {
                                        String[] teamData = new String[]{teamName, "2"};
                                        for (int i = 0; i < newteamDatas.size(); i++) {
                                            if (newteamDatas.get(i)[0].equals(team)) {
                                                newteamDatas.set(i, teamData);
                                                break;
                                            }
                                        }
                                    }

                                    teamDatas = new ArrayList<>(newteamDatas);
                                    ListProt.setTeamDatas(gameName, teamDatas);

                                    String mess = messLevelUpprot2.replace("{player}", player.getName());
                                    player.sendMessage(t(mess));
                                    buyFail = false;
                                }
                            } else if (clickedItemName.equals(teamEnchItemName_prot3)
                                    && player.getInventory().contains(Material.DIAMOND, prot3Cost2v2)) {
                                if (strings[0].equals(teamName)
                                        && !strings[1].equals("3")
                                        && !strings[1].equals("4")) {
                                    player.getInventory().removeItem(new ItemStack(Material.DIAMOND, prot3Cost2v2));

                                    List<String[]> newteamDatas = new ArrayList<>(9);
                                    for (String[] data : teamDatas) {
                                        newteamDatas.add(data.clone());
                                    }

                                    for (String team : game.getTeams().keySet()) {
                                        String[] teamData = new String[]{teamName, "3"};
                                        for (int i = 0; i < newteamDatas.size(); i++) {
                                            if (newteamDatas.get(i)[0].equals(team)) {
                                                newteamDatas.set(i, teamData);
                                                break;
                                            }
                                        }
                                    }

                                    teamDatas = new ArrayList<>(newteamDatas);
                                    ListProt.setTeamDatas(gameName, teamDatas);

                                    String mess = messLevelUpprot3.replace("{player}", player.getName());
                                    player.sendMessage(t(mess));
                                    buyFail = false;
                                }
                            } else if (clickedItemName.equals(teamEnchItemName_prot4)
                                    && player.getInventory().contains(Material.DIAMOND, prot4Cost2v2)) {
                                if (strings[0].equals(teamName)
                                        && !strings[1].equals("4")) {
                                    player.getInventory().removeItem(new ItemStack(Material.DIAMOND, prot4Cost2v2));

                                    List<String[]> newteamDatas = new ArrayList<>(9);
                                    for (String[] data : teamDatas) {
                                        newteamDatas.add(data.clone());
                                    }

                                    for (String team : game.getTeams().keySet()) {
                                        String[] teamData = new String[]{teamName, "4"};
                                        for (int i = 0; i < newteamDatas.size(); i++) {
                                            if (newteamDatas.get(i)[0].equals(team)) {
                                                newteamDatas.set(i, teamData);
                                                break;
                                            }
                                        }
                                    }

                                    teamDatas = new ArrayList<>(newteamDatas);
                                    ListProt.setTeamDatas(gameName, teamDatas);

                                    String mess = messLevelUpprot4.replace("{player}", player.getName());
                                    player.sendMessage(t(mess));
                                    buyFail = false;
                                }
                            }
                        }
                    }
                    if (buyFail) {
                        playerSoundFail(player);
                    } else {
                        openForPlayer2v2(player, game);
                        playerSoundSucc(player);
                    }
                }
            } else if (player.getWorld().getName().contains(rushWorld4v4)) {
                {

                    if (clickedItemType == leveluphasteItemType
                            && clickedItem.hasItemMeta()) {

                        List<String[]> teamDatas = ListHaste.getTeamDatas(gameName);

                        if (clickedItemType == leveluphasteItemType) {

                            for (String[] strings : teamDatas) {
                                if (clickedItemName.equals(teamEffItemName_Haste1)
                                        && player.getInventory().contains(Material.DIAMOND, haste1Cost4v4)) {
                                    if (strings[0].equals(teamName)
                                            && !strings[1].equals("2") && !strings[1].equals("1")) {
                                        player.getInventory().removeItem(new ItemStack(Material.DIAMOND, haste1Cost4v4));

                                        List<String[]> newteamDatas = new ArrayList<>(9);
                                        for (String[] data : teamDatas) {
                                            newteamDatas.add(data.clone());
                                        }

                                        for (String team : game.getTeams().keySet()) {
                                            String[] teamData = new String[]{teamName, "1"};
                                            for (int i = 0; i < newteamDatas.size(); i++) {
                                                if (newteamDatas.get(i)[0].equals(team)) {
                                                    newteamDatas.set(i, teamData);
                                                    break;
                                                }
                                            }
                                        }

                                        teamDatas = new ArrayList<>(newteamDatas);
                                        ListHaste.setTeamDatas(gameName, teamDatas);

                                        String mess = messLevelUphaste1.replace("{player}", player.getName());
                                        player.sendMessage(t(mess));
                                        buyFail = false;
                                    } else {
                                        String mess = messLevelUpFailed.replace("{player}", player.getName());
                                        player.sendMessage(t(mess));
                                    }
                                } else if (clickedItemName.equals(teamEffItemName_Haste2) && player.getInventory().contains(Material.DIAMOND, haste2Cost4v4)) {

                                    if (strings[0].equals(teamName)
                                            && !strings[1].equals("2")) {
                                        player.getInventory().removeItem(new ItemStack(Material.DIAMOND, haste2Cost4v4));

                                        List<String[]> newteamDatas = new ArrayList<>(9);
                                        for (String[] data : teamDatas) {
                                            newteamDatas.add(data.clone());
                                        }

                                        for (String team : game.getTeams().keySet()) {
                                            String[] teamData = new String[]{teamName, "2"};
                                            for (int i = 0; i < newteamDatas.size(); i++) {
                                                if (newteamDatas.get(i)[0].equals(team)) {
                                                    newteamDatas.set(i, teamData);
                                                    break;
                                                }
                                            }
                                        }

                                        teamDatas = new ArrayList<>(newteamDatas);
                                        ListHaste.setTeamDatas(gameName, teamDatas);

                                        String mess = messLevelUphaste2.replace("{player}", player.getName());
                                        player.sendMessage(t(mess));
                                        buyFail = false;
                                    }
                                }
                            }
                        }
                    }
                    if (clickedItemType == leveluphealItemType) {

                        List<String[]> teamDatas = ListHeal.getTeamDatas(gameName);

                        for (String[] strings : teamDatas) {
                            if (clickedItemName.equals(teamEffItemName_Heal1)
                                    && player.getInventory().contains(Material.DIAMOND, heal1Cost4v4)) {
                                if (strings[0].equals(teamName)
                                        && !strings[1].equals("1")) {
                                    player.getInventory().removeItem(new ItemStack(Material.DIAMOND, heal1Cost4v4));

                                    List<String[]> newteamDatas = new ArrayList<>(9);
                                    for (String[] data : teamDatas) {
                                        newteamDatas.add(data.clone());
                                    }

                                    for (String team : game.getTeams().keySet()) {
                                        String[] teamData = new String[]{teamName, "1"};
                                        for (int i = 0; i < newteamDatas.size(); i++) {
                                            if (newteamDatas.get(i)[0].equals(team)) {
                                                newteamDatas.set(i, teamData);
                                                break;
                                            }
                                        }
                                    }

                                    teamDatas = new ArrayList<>(newteamDatas);
                                    ListHeal.setTeamDatas(gameName, teamDatas);

                                    String mess = messLevelUpheal1.replace("{player}", player.getName());
                                    player.sendMessage(t(mess));
                                    buyFail = false;
                                }
                            }
                        }
                    }
                    if (clickedItemType == levelupsharpItemType) {

                        List<String[]> teamDatas = ListSharp.getTeamDatas(gameName);

                        for (String[] strings : teamDatas) {
                            if (clickedItemName.equals(teamEnchItemName_sharp1)
                                    && player.getInventory().contains(Material.DIAMOND, sharp1Cost4v4)) {
                                if (strings[0].equals(teamName)
                                        && !strings[1].equals("1")
                                        && !strings[1].equals("2")
                                        && !strings[1].equals("3")
                                        && !strings[1].equals("4")) {
                                    player.getInventory().removeItem(new ItemStack(Material.DIAMOND, sharp1Cost4v4));

                                    List<String[]> newteamDatas = new ArrayList<>(9);
                                    for (String[] data : teamDatas) {
                                        newteamDatas.add(data.clone());
                                    }

                                    for (String team : game.getTeams().keySet()) {
                                        String[] teamData = new String[]{teamName, "1"};
                                        for (int i = 0; i < newteamDatas.size(); i++) {
                                            if (newteamDatas.get(i)[0].equals(team)) {
                                                newteamDatas.set(i, teamData);
                                                break;
                                            }
                                        }
                                    }

                                    teamDatas = new ArrayList<>(newteamDatas);
                                    ListSharp.setTeamDatas(gameName, teamDatas);

                                    String mess = messLevelUpsharp1.replace("{player}", player.getName());
                                    player.sendMessage(t(mess));
                                    buyFail = false;
                                }
                            } else if (clickedItemName.equals(teamEnchItemName_sharp2)
                                    && player.getInventory().contains(Material.DIAMOND, sharp2Cost4v4)) {
                                if (strings[0].equals(teamName)
                                        && !strings[1].equals("2")
                                        && !strings[1].equals("3")
                                        && !strings[1].equals("4")) {
                                    player.getInventory().removeItem(new ItemStack(Material.DIAMOND, sharp2Cost4v4));

                                    List<String[]> newteamDatas = new ArrayList<>(9);
                                    for (String[] data : teamDatas) {
                                        newteamDatas.add(data.clone());
                                    }

                                    for (String team : game.getTeams().keySet()) {
                                        String[] teamData = new String[]{teamName, "2"};
                                        for (int i = 0; i < newteamDatas.size(); i++) {
                                            if (newteamDatas.get(i)[0].equals(team)) {
                                                newteamDatas.set(i, teamData);
                                                break;
                                            }
                                        }
                                    }

                                    teamDatas = new ArrayList<>(newteamDatas);
                                    ListSharp.setTeamDatas(gameName, teamDatas);

                                    String mess = messLevelUpsharp2.replace("{player}", player.getName());
                                    player.sendMessage(t(mess));
                                    buyFail = false;
                                }
                            } else if (clickedItemName.equals(teamEnchItemName_sharp3)
                                    && player.getInventory().contains(Material.DIAMOND, sharp3Cost4v4)) {
                                if (strings[0].equals(teamName)
                                        && !strings[1].equals("3")
                                        && !strings[1].equals("4")) {
                                    player.getInventory().removeItem(new ItemStack(Material.DIAMOND, sharp3Cost4v4));

                                    List<String[]> newteamDatas = new ArrayList<>(9);
                                    for (String[] data : teamDatas) {
                                        newteamDatas.add(data.clone());
                                    }

                                    for (String team : game.getTeams().keySet()) {
                                        String[] teamData = new String[]{teamName, "3"};
                                        for (int i = 0; i < newteamDatas.size(); i++) {
                                            if (newteamDatas.get(i)[0].equals(team)) {
                                                newteamDatas.set(i, teamData);
                                                break;
                                            }
                                        }
                                    }

                                    teamDatas = new ArrayList<>(newteamDatas);
                                    ListSharp.setTeamDatas(gameName, teamDatas);

                                    String mess = messLevelUpsharp3.replace("{player}", player.getName());
                                    player.sendMessage(t(mess));
                                    buyFail = false;
                                }
                            } else if (clickedItemName.equals(teamEnchItemName_sharp4)
                                    && player.getInventory().contains(Material.DIAMOND, sharp4Cost4v4)) {
                                if (strings[0].equals(teamName)
                                        && !strings[1].equals("4")) {
                                    player.getInventory().removeItem(new ItemStack(Material.DIAMOND, sharp4Cost4v4));

                                    List<String[]> newteamDatas = new ArrayList<>(9);
                                    for (String[] data : teamDatas) {
                                        newteamDatas.add(data.clone());
                                    }

                                    for (String team : game.getTeams().keySet()) {
                                        String[] teamData = new String[]{teamName, "4"};
                                        for (int i = 0; i < newteamDatas.size(); i++) {
                                            if (newteamDatas.get(i)[0].equals(team)) {
                                                newteamDatas.set(i, teamData);
                                                break;
                                            }
                                        }
                                    }

                                    teamDatas = new ArrayList<>(newteamDatas);
                                    ListSharp.setTeamDatas(gameName, teamDatas);

                                    String mess = messLevelUpsharp4.replace("{player}", player.getName());
                                    player.sendMessage(t(mess));
                                    buyFail = false;
                                }
                            }
                        }
                    }
                    if (clickedItemType == levelupprotItemType) {

                        List<String[]> teamDatas = ListProt.getTeamDatas(gameName);

                        for (String[] strings : teamDatas) {
                            if (clickedItemName.equals(teamEnchItemName_prot1)
                                    && player.getInventory().contains(Material.DIAMOND, prot1Cost4v4)) {
                                if (strings[0].equals(teamName)
                                        && !strings[1].equals("1")
                                        && !strings[1].equals("2")
                                        && !strings[1].equals("3")
                                        && !strings[1].equals("4")) {
                                    player.getInventory().removeItem(new ItemStack(Material.DIAMOND, prot1Cost4v4));

                                    List<String[]> newteamDatas = new ArrayList<>(9);
                                    for (String[] data : teamDatas) {
                                        newteamDatas.add(data.clone());
                                    }

                                    for (String team : game.getTeams().keySet()) {
                                        String[] teamData = new String[]{teamName, "1"};
                                        for (int i = 0; i < newteamDatas.size(); i++) {
                                            if (newteamDatas.get(i)[0].equals(team)) {
                                                newteamDatas.set(i, teamData);
                                                break;
                                            }
                                        }
                                    }

                                    teamDatas = new ArrayList<>(newteamDatas);
                                    ListProt.setTeamDatas(gameName, teamDatas);

                                    String mess = messLevelUpprot1.replace("{player}", player.getName());
                                    player.sendMessage(t(mess));
                                    buyFail = false;
                                }
                            } else if (clickedItemName.equals(teamEnchItemName_prot2)
                                    && player.getInventory().contains(Material.DIAMOND, prot2Cost4v4)) {
                                if (strings[0].equals(teamName)
                                        && !strings[1].equals("2")
                                        && !strings[1].equals("3")
                                        && !strings[1].equals("4")) {
                                    player.getInventory().removeItem(new ItemStack(Material.DIAMOND, prot2Cost4v4));

                                    List<String[]> newteamDatas = new ArrayList<>(9);
                                    for (String[] data : teamDatas) {
                                        newteamDatas.add(data.clone());
                                    }

                                    for (String team : game.getTeams().keySet()) {
                                        String[] teamData = new String[]{teamName, "2"};
                                        for (int i = 0; i < newteamDatas.size(); i++) {
                                            if (newteamDatas.get(i)[0].equals(team)) {
                                                newteamDatas.set(i, teamData);
                                                break;
                                            }
                                        }
                                    }

                                    teamDatas = new ArrayList<>(newteamDatas);
                                    ListProt.setTeamDatas(gameName, teamDatas);

                                    String mess = messLevelUpprot2.replace("{player}", player.getName());
                                    player.sendMessage(t(mess));
                                    buyFail = false;
                                }
                            } else if (clickedItemName.equals(teamEnchItemName_prot3)
                                    && player.getInventory().contains(Material.DIAMOND, prot3Cost4v4)) {
                                if (strings[0].equals(teamName)
                                        && !strings[1].equals("3")
                                        && !strings[1].equals("4")) {
                                    player.getInventory().removeItem(new ItemStack(Material.DIAMOND, prot3Cost4v4));

                                    List<String[]> newteamDatas = new ArrayList<>(9);
                                    for (String[] data : teamDatas) {
                                        newteamDatas.add(data.clone());
                                    }

                                    for (String team : game.getTeams().keySet()) {
                                        String[] teamData = new String[]{teamName, "3"};
                                        for (int i = 0; i < newteamDatas.size(); i++) {
                                            if (newteamDatas.get(i)[0].equals(team)) {
                                                newteamDatas.set(i, teamData);
                                                break;
                                            }
                                        }
                                    }

                                    teamDatas = new ArrayList<>(newteamDatas);
                                    ListProt.setTeamDatas(gameName, teamDatas);

                                    String mess = messLevelUpprot3.replace("{player}", player.getName());
                                    player.sendMessage(t(mess));
                                    buyFail = false;
                                }
                            } else if (clickedItemName.equals(teamEnchItemName_prot4)
                                    && player.getInventory().contains(Material.DIAMOND, prot4Cost4v4)) {
                                if (strings[0].equals(teamName)
                                        && !strings[1].equals("4")) {
                                    player.getInventory().removeItem(new ItemStack(Material.DIAMOND, prot4Cost4v4));

                                    List<String[]> newteamDatas = new ArrayList<>(9);
                                    for (String[] data : teamDatas) {
                                        newteamDatas.add(data.clone());
                                    }

                                    for (String team : game.getTeams().keySet()) {
                                        String[] teamData = new String[]{teamName, "4"};
                                        for (int i = 0; i < newteamDatas.size(); i++) {
                                            if (newteamDatas.get(i)[0].equals(team)) {
                                                newteamDatas.set(i, teamData);
                                                break;
                                            }
                                        }
                                    }

                                    teamDatas = new ArrayList<>(newteamDatas);
                                    ListProt.setTeamDatas(gameName, teamDatas);

                                    String mess = messLevelUpprot4.replace("{player}", player.getName());
                                    player.sendMessage(t(mess));
                                    buyFail = false;
                                }
                            }
                        }
                    }
                    if (buyFail) {
                        playerSoundFail(player);
                    } else {
                        openForPlayer4v4(player, game);
                        playerSoundSucc(player);
                    }
                }

                if (buyFail) {
                    playerSoundFail(player);
                    String mess = messLevelUpFailed.replace("{player}", player.getName());
                    player.sendMessage(t(mess));
                } else {
                    playerSoundSucc(player);
                    openForPlayer4v4(player, game);
                }
            }
        }
        // End
    }

    void playerSoundOpen(Player player){
        CLICK(player, 1);
    }
    void playerSoundSucc(Player player){
        CHICKEN_EGG_POP(player,1);
    }
    void playerSoundFail(Player player){
        ITEM_BREAK(player, 1);
    }
}
