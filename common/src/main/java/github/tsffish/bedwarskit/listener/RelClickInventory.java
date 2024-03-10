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
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static github.tsffish.bedwarskit.config.kit.KitConfigHandler.*;
import static github.tsffish.bedwarskit.config.main.MainConfigHandler.*;
import static github.tsffish.bedwarskit.listener.RelOpenShop.openShop;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;
import static github.tsffish.bedwarskit.util.misc.MessSender.consoleSendCommand;
import static github.tsffish.bedwarskit.util.misc.PluginState.isBungeeMode;
import static github.tsffish.bedwarskit.util.player.PlayerSender.sendMessage;
import static github.tsffish.bedwarskit.util.player.RelPlayerKit.*;
import static github.tsffish.bedwarskit.util.player.SoundPlayer.*;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class RelClickInventory implements Listener {
    private static final Material glass_pane = Material.STAINED_GLASS_PANE;
    private static final Material diamond = Material.DIAMOND;
    private static final Material air = Material.AIR;

    @EventHandler(priority = EventPriority.HIGH)
    public void on(final InventoryClickEvent event) {
        Inventory inventory = event.getClickedInventory();

        if (inventory == null) return;
        String inventoryName = inventory.getName();

        if (inventoryName == null) {
            return;
        }
        ItemStack itemStack = event.getCurrentItem();

        if (itemStack == null || itemStack.getType() == air) {
            return;
        }
        Material itemType = itemStack.getType();

        if (itemType == null) {
            return;
        }
        String itemTypeText = itemType.toString().toUpperCase();

        Player player = (Player) event.getWhoClicked();
        if (player == null || !player.isOnline()) {
            return;
        }

        String playerName = player.getName();
        UUID uuid = player.getUniqueId();

        if (inventoryName.equals(kitMenuTitle)) {
            event.setCancelled(true);
            boolean isSelected = false;
            event.setCancelled(true);
            if (itemType == KitDefaultItemType) {
                setPlayerKit(uuid, kitNameDefault);
                isSelected = true;
            } else if (itemType == KitNoneItemType) {
                setPlayerKit(uuid, kitNameNone);
                isSelected = true;

            } else if (itemType == KitDefaultlessItemType) {
                setPlayerKit(uuid, kitNameDefaultLess);
                isSelected = true;

            }

            if (isSelected) {
                sendMessage(player,meanSelKitSucc);
                playerSoundSucc(player);
                player.closeInventory();
            }
            return;
        }

        if (dieOutGameItem_playAgain) {
            if (itemTypeText.equals(dieOutGameItem_playAgain_ItemType.toString())
                    && itemStack.getItemMeta().getDisplayName().equals(dieOutGameItem_playAgain_ItemName)) {
                String willSend = dieOutGameItem_playAgain_ClickSendCommand.replace("{player}", playerName);
                consoleSendCommand(willSend);
                return;
            }
        }

        if (levelupShopOpenMode.equals("click on item")) {
            if (itemType == LevelupItemType) {
                event.setCancelled(true);
                if (levelupShopDelayOpen) {
                    playerSoundOpen(player);
                    openShop(player, 8L);
                } else {
                    playerSoundOpen(player);
                    openShop(player, 0L);
                }
                return;
            }
        }

        if (event.getCurrentItem() != null
        ) {
            for (String string : noMoveList) {
                if (itemTypeText.contains(string)) {
                    event.setCancelled(true);
                    return;
                }
            }
        }

        if (player.getWorld().getName().contains(gameWorld)
                || isBungeeMode()) {
            if (inventoryName.equalsIgnoreCase(shopLevelup)) {

                event.setCancelled(true);
                GameManager gameManager = BedwarsRel.getInstance().getGameManager();
                Game game = gameManager.getGameOfPlayer(player);
                Team playerTeam = game.getPlayerTeam(player);

                String gameName = game.getName();

                ItemStack clickedItem = event.getCurrentItem();

                if (clickedItem == null) {
                    return;
                }


                String clickedItemName = clickedItem.getItemMeta().getDisplayName();
                String teamName = playerTeam.getName();
                Material clickedItemType = clickedItem.getType();

                boolean buyFail = true;

                if (player.getWorld().getName().contains(gameWorld2v2) || bungeeMode.equals("2v2")) {

                    if (clickedItemType == leveluphasteItemType
                            && clickedItem.hasItemMeta()) {

                        Set<String[]> teamDatas = ListHaste.getTeamDatas(gameName);

                        if (clickedItemType == leveluphasteItemType) {

                            for (String[] strings : teamDatas) {
                                if (clickedItemName.equals(teamEffItemName_Haste1)
                                        && player.getInventory().contains(diamond, haste1Cost2v2)) {
                                    if (strings[0].equals(teamName)
                                            && !strings[1].equals("2") && !strings[1].equals("1")) {
                                        int count = haste1Cost2v2;
                                        int deductedCount = 0;

                                        for (int i = 0; i < inventory.getSize(); i++) {
                                            if (deductedCount >= count) {
                                                break;
                                            }

                                            ItemStack item = inventory.getItem(i);
                                            if (item != null && item.getType() == diamond) {

                                                item.setAmount(item.getAmount() - 1);
                                                deductedCount++;

                                                if (item.getAmount() <= 0) {
                                                    inventory.setItem(i, null);
                                                }
                                            }
                                        }

                                        Set<String[]> newteamDatas = Collections.newSetFromMap(new ConcurrentHashMap<>());
                                        for (String[] data : teamDatas) {
                                            newteamDatas.add(data.clone());
                                        }

                                        for (String team : game.getTeams().keySet()) {
                                            String[] teamData = new String[]{teamName, "1"};
                                            String[][] dataArray = newteamDatas.toArray(new String[newteamDatas.size()][]);
                                            for (String[] value : dataArray) {
                                                if (value[0].equals(team)) {
                                                    newteamDatas.remove(value);
                                                    newteamDatas.add(teamData);
                                                    break;
                                                }
                                            }
                                        }
                                        ListHaste.setTeamDatas(gameName, newteamDatas);

                                        String mess = messLevelUphaste1.replace("{player}", player.getName());
                                        player.sendMessage(t(mess));
                                        buyFail = false;
                                    } else {
                                        String mess = messLevelUpFailed.replace("{player}", player.getName());
                                        player.sendMessage(t(mess));
                                    }
                                } else if (clickedItemName.equals(teamEffItemName_Haste2) && player.getInventory().contains(diamond, haste2Cost2v2)) {

                                    if (strings[0].equals(teamName)
                                            && !strings[1].equals("2")) {
                                        int count = haste2Cost2v2;
                                        int deductedCount = 0;

                                        for (int i = 0; i < inventory.getSize(); i++) {
                                            if (deductedCount >= count) {
                                                break;
                                            }

                                            ItemStack item = inventory.getItem(i);
                                            if (item != null && item.getType() == diamond) {

                                                item.setAmount(item.getAmount() - 1);
                                                deductedCount++;

                                                if (item.getAmount() <= 0) {
                                                    inventory.setItem(i, null);
                                                }
                                            }
                                        }

                                        Set<String[]> newteamDatas = Collections.newSetFromMap(new ConcurrentHashMap<>());
                                        for (String[] data : teamDatas) {
                                            newteamDatas.add(data.clone());
                                        }

                                        for (String team : game.getTeams().keySet()) {
                                            String[] teamData = new String[]{teamName, "2"};
                                            String[][] dataArray = newteamDatas.toArray(new String[newteamDatas.size()][]);
                                            for (String[] value : dataArray) {
                                                if (value[0].equals(team)) {
                                                    newteamDatas.remove(value);
                                                    newteamDatas.add(teamData);
                                                    break;
                                                }
                                            }
                                        }
                                        ListHaste.setTeamDatas(gameName, newteamDatas);

                                        String mess = messLevelUphaste2.replace("{player}", player.getName());
                                        player.sendMessage(t(mess));
                                        buyFail = false;
                                    }
                                }
                            }
                        }
                    }
                    if (clickedItemType == leveluphealItemType) {

                        Set<String[]> teamDatas = ListHeal.getTeamDatas(gameName);

                        for (String[] strings : teamDatas) {
                            if (clickedItemName.equals(teamEffItemName_Heal1)
                                    && player.getInventory().contains(diamond, heal1Cost2v2)) {
                                if (strings[0].equals(teamName)
                                        && !strings[1].equals("1")) {
                                    int count = heal1Cost2v2;
                                    int deductedCount = 0;

                                    for (int i = 0; i < inventory.getSize(); i++) {
                                        if (deductedCount >= count) {
                                            break;
                                        }

                                        ItemStack item = inventory.getItem(i);
                                        if (item != null && item.getType() == diamond) {

                                            item.setAmount(item.getAmount() - 1);
                                            deductedCount++;

                                            if (item.getAmount() <= 0) {
                                                inventory.setItem(i, null);
                                            }
                                        }
                                    }

                                    Set<String[]> newteamDatas = Collections.newSetFromMap(new ConcurrentHashMap<>());
                                    for (String[] data : teamDatas) {
                                        newteamDatas.add(data.clone());
                                    }

                                    for (String team : game.getTeams().keySet()) {
                                        String[] teamData = new String[]{teamName, "1"};
                                        String[][] dataArray = newteamDatas.toArray(new String[newteamDatas.size()][]);
                                        for (String[] value : dataArray) {
                                            if (value[0].equals(team)) {
                                                newteamDatas.remove(value);
                                                newteamDatas.add(teamData);
                                                break;
                                            }
                                        }
                                    }
                                    ListHeal.setTeamDatas(gameName, newteamDatas);

                                    String mess = messLevelUpheal1.replace("{player}", player.getName());
                                    player.sendMessage(t(mess));
                                    buyFail = false;
                                }
                            }
                        }
                    }
                    if (clickedItemType == levelupsharpItemType) {

                        Set<String[]> teamDatas = ListSharp.getTeamDatas(gameName);

                        for (String[] strings : teamDatas) {
                            if (clickedItemName.equals(teamEnchItemName_sharp1)
                                    && player.getInventory().contains(diamond, sharp1Cost2v2)) {
                                if (strings[0].equals(teamName)
                                        && !strings[1].equals("1")
                                        && !strings[1].equals("2")
                                        && !strings[1].equals("3")
                                        && !strings[1].equals("4")) {
                                    int count = sharp1Cost2v2;
                                    int deductedCount = 0;

                                    for (int i = 0; i < inventory.getSize(); i++) {
                                        if (deductedCount >= count) {
                                            break;
                                        }

                                        ItemStack item = inventory.getItem(i);
                                        if (item != null && item.getType() == diamond) {

                                            item.setAmount(item.getAmount() - 1);
                                            deductedCount++;

                                            if (item.getAmount() <= 0) {
                                                inventory.setItem(i, null);
                                            }
                                        }
                                    }

                                    Set<String[]> newteamDatas = Collections.newSetFromMap(new ConcurrentHashMap<>());
                                    for (String[] data : teamDatas) {
                                        newteamDatas.add(data.clone());
                                    }

                                    for (String team : game.getTeams().keySet()) {
                                        String[] teamData = new String[]{teamName, "1"};
                                        String[][] dataArray = newteamDatas.toArray(new String[newteamDatas.size()][]);
                                        for (String[] value : dataArray) {
                                            if (value[0].equals(team)) {
                                                newteamDatas.remove(value);
                                                newteamDatas.add(teamData);
                                                break;
                                            }
                                        }
                                    }
                                    ListSharp.setTeamDatas(gameName, newteamDatas);

                                    String mess = messLevelUpsharp1.replace("{player}", player.getName());
                                    player.sendMessage(t(mess));
                                    buyFail = false;
                                }
                            } else if (clickedItemName.equals(teamEnchItemName_sharp2)
                                    && player.getInventory().contains(diamond, sharp2Cost2v2)) {
                                if (strings[0].equals(teamName)
                                        && !strings[1].equals("2")
                                        && !strings[1].equals("3")
                                        && !strings[1].equals("4")) {
                                    int count = sharp2Cost2v2;
                                    int deductedCount = 0;

                                    for (int i = 0; i < inventory.getSize(); i++) {
                                        if (deductedCount >= count) {
                                            break;
                                        }

                                        ItemStack item = inventory.getItem(i);
                                        if (item != null && item.getType() == diamond) {

                                            item.setAmount(item.getAmount() - 1);
                                            deductedCount++;

                                            if (item.getAmount() <= 0) {
                                                inventory.setItem(i, null);
                                            }
                                        }
                                    }

                                    Set<String[]> newteamDatas = Collections.newSetFromMap(new ConcurrentHashMap<>());
                                    for (String[] data : teamDatas) {
                                        newteamDatas.add(data.clone());
                                    }

                                    for (String team : game.getTeams().keySet()) {
                                        String[] teamData = new String[]{teamName, "2"};
                                        String[][] dataArray = newteamDatas.toArray(new String[newteamDatas.size()][]);
                                        for (String[] value : dataArray) {
                                            if (value[0].equals(team)) {
                                                newteamDatas.remove(value);
                                                newteamDatas.add(teamData);
                                                break;
                                            }
                                        }
                                    }
                                    ListSharp.setTeamDatas(gameName, newteamDatas);

                                    String mess = messLevelUpsharp2.replace("{player}", player.getName());
                                    player.sendMessage(t(mess));
                                    buyFail = false;
                                }
                            } else if (clickedItemName.equals(teamEnchItemName_sharp3)
                                    && player.getInventory().contains(diamond, sharp3Cost2v2)) {
                                if (strings[0].equals(teamName)
                                        && !strings[1].equals("3")
                                        && !strings[1].equals("4")) {
                                    int count = sharp3Cost2v2;
                                    int deductedCount = 0;

                                    for (int i = 0; i < inventory.getSize(); i++) {
                                        if (deductedCount >= count) {
                                            break;
                                        }

                                        ItemStack item = inventory.getItem(i);
                                        if (item != null && item.getType() == diamond) {

                                            item.setAmount(item.getAmount() - 1);
                                            deductedCount++;

                                            if (item.getAmount() <= 0) {
                                                inventory.setItem(i, null);
                                            }
                                        }
                                    }

                                    Set<String[]> newteamDatas = Collections.newSetFromMap(new ConcurrentHashMap<>());
                                    for (String[] data : teamDatas) {
                                        newteamDatas.add(data.clone());
                                    }

                                    for (String team : game.getTeams().keySet()) {
                                        String[] teamData = new String[]{teamName, "3"};
                                        String[][] dataArray = newteamDatas.toArray(new String[newteamDatas.size()][]);
                                        for (String[] value : dataArray) {
                                            if (value[0].equals(team)) {
                                                newteamDatas.remove(value);
                                                newteamDatas.add(teamData);
                                                break;
                                            }
                                        }
                                    }
                                    ListSharp.setTeamDatas(gameName, newteamDatas);

                                    String mess = messLevelUpsharp3.replace("{player}", player.getName());
                                    player.sendMessage(t(mess));
                                    buyFail = false;
                                }
                            } else if (clickedItemName.equals(teamEnchItemName_sharp4)
                                    && player.getInventory().contains(diamond, sharp4Cost2v2)) {
                                if (strings[0].equals(teamName)
                                        && !strings[1].equals("4")) {
                                    int count = sharp1Cost2v2;
                                    int deductedCount = 0;

                                    for (int i = 0; i < inventory.getSize(); i++) {
                                        if (deductedCount >= count) {
                                            break;
                                        }

                                        ItemStack item = inventory.getItem(i);
                                        if (item != null && item.getType() == diamond) {

                                            item.setAmount(item.getAmount() - 1);
                                            deductedCount++;

                                            if (item.getAmount() <= 0) {
                                                inventory.setItem(i, null);
                                            }
                                        }
                                    }

                                    Set<String[]> newteamDatas = Collections.newSetFromMap(new ConcurrentHashMap<>());
                                    for (String[] data : teamDatas) {
                                        newteamDatas.add(data.clone());
                                    }

                                    for (String team : game.getTeams().keySet()) {
                                        String[] teamData = new String[]{teamName, "4"};
                                        String[][] dataArray = newteamDatas.toArray(new String[newteamDatas.size()][]);
                                        for (String[] value : dataArray) {
                                            if (value[0].equals(team)) {
                                                newteamDatas.remove(value);
                                                newteamDatas.add(teamData);
                                                break;
                                            }
                                        }
                                    }

                                    ListSharp.setTeamDatas(gameName, newteamDatas);

                                    String mess = messLevelUpsharp4.replace("{player}", player.getName());
                                    player.sendMessage(t(mess));
                                    buyFail = false;
                                }
                            }
                        }
                    }
                    if (clickedItemType == levelupprotItemType) {

                        Set<String[]> teamDatas = ListProt.getTeamDatas(gameName);

                        for (String[] strings : teamDatas) {
                            if (clickedItemName.equals(teamEnchItemName_prot1)
                                    && player.getInventory().contains(diamond, prot1Cost2v2)) {
                                if (strings[0].equals(teamName)
                                        && !strings[1].equals("1")
                                        && !strings[1].equals("2")
                                        && !strings[1].equals("3")
                                        && !strings[1].equals("4")) {
                                    int count = prot1Cost2v2;
                                    int deductedCount = 0;

                                    for (int i = 0; i < inventory.getSize(); i++) {
                                        if (deductedCount >= count) {
                                            break;
                                        }

                                        ItemStack item = inventory.getItem(i);
                                        if (item != null && item.getType() == diamond) {

                                            item.setAmount(item.getAmount() - 1);
                                            deductedCount++;

                                            if (item.getAmount() <= 0) {
                                                inventory.setItem(i, null);
                                            }
                                        }
                                    }

                                    Set<String[]> newteamDatas = Collections.newSetFromMap(new ConcurrentHashMap<>());
                                    for (String[] data : teamDatas) {
                                        newteamDatas.add(data.clone());
                                    }

                                    for (String team : game.getTeams().keySet()) {
                                        String[] teamData = new String[]{teamName, "1"};
                                        String[][] dataArray = newteamDatas.toArray(new String[newteamDatas.size()][]);
                                        for (String[] value : dataArray) {
                                            if (value[0].equals(team)) {
                                                newteamDatas.remove(value);
                                                newteamDatas.add(teamData);
                                                break;
                                            }
                                        }
                                    }

                                    ListProt.setTeamDatas(gameName, newteamDatas);

                                    String mess = messLevelUpprot1.replace("{player}", player.getName());
                                    player.sendMessage(t(mess));
                                    buyFail = false;
                                }
                            } else if (clickedItemName.equals(teamEnchItemName_prot2)
                                    && player.getInventory().contains(diamond, prot2Cost2v2)) {
                                if (strings[0].equals(teamName)
                                        && !strings[1].equals("2")
                                        && !strings[1].equals("3")
                                        && !strings[1].equals("4")) {
                                    int count = prot2Cost2v2;
                                    int deductedCount = 0;

                                    for (int i = 0; i < inventory.getSize(); i++) {
                                        if (deductedCount >= count) {
                                            break;
                                        }

                                        ItemStack item = inventory.getItem(i);
                                        if (item != null && item.getType() == diamond) {

                                            item.setAmount(item.getAmount() - 1);
                                            deductedCount++;

                                            if (item.getAmount() <= 0) {
                                                inventory.setItem(i, null);
                                            }
                                        }
                                    }

                                    Set<String[]> newteamDatas = Collections.newSetFromMap(new ConcurrentHashMap<>());
                                    for (String[] data : teamDatas) {
                                        newteamDatas.add(data.clone());
                                    }

                                    for (String team : game.getTeams().keySet()) {
                                        String[] teamData = new String[]{teamName, "2"};
                                        String[][] dataArray = newteamDatas.toArray(new String[newteamDatas.size()][]);
                                        for (String[] value : dataArray) {
                                            if (value[0].equals(team)) {
                                                newteamDatas.remove(value);
                                                newteamDatas.add(teamData);
                                                break;
                                            }
                                        }
                                    }

                                    ListProt.setTeamDatas(gameName, newteamDatas);

                                    String mess = messLevelUpprot2.replace("{player}", player.getName());
                                    player.sendMessage(t(mess));
                                    buyFail = false;
                                }
                            } else if (clickedItemName.equals(teamEnchItemName_prot3)
                                    && player.getInventory().contains(diamond, prot3Cost2v2)) {
                                if (strings[0].equals(teamName)
                                        && !strings[1].equals("3")
                                        && !strings[1].equals("4")) {
                                    int count = prot3Cost2v2;
                                    int deductedCount = 0;

                                    for (int i = 0; i < inventory.getSize(); i++) {
                                        if (deductedCount >= count) {
                                            break;
                                        }

                                        ItemStack item = inventory.getItem(i);
                                        if (item != null && item.getType() == diamond) {

                                            item.setAmount(item.getAmount() - 1);
                                            deductedCount++;

                                            if (item.getAmount() <= 0) {
                                                inventory.setItem(i, null);
                                            }
                                        }
                                    }

                                    Set<String[]> newteamDatas = Collections.newSetFromMap(new ConcurrentHashMap<>());
                                    for (String[] data : teamDatas) {
                                        newteamDatas.add(data.clone());
                                    }

                                    for (String team : game.getTeams().keySet()) {
                                        String[] teamData = new String[]{teamName, "3"};
                                        String[][] dataArray = newteamDatas.toArray(new String[newteamDatas.size()][]);
                                        for (String[] value : dataArray) {
                                            if (value[0].equals(team)) {
                                                newteamDatas.remove(value);
                                                newteamDatas.add(teamData);
                                                break;
                                            }
                                        }
                                    }

                                    ListProt.setTeamDatas(gameName, newteamDatas);

                                    String mess = messLevelUpprot3.replace("{player}", player.getName());
                                    player.sendMessage(t(mess));
                                    buyFail = false;
                                }
                            } else if (clickedItemName.equals(teamEnchItemName_prot4)
                                    && player.getInventory().contains(diamond, prot4Cost2v2)) {
                                if (strings[0].equals(teamName)
                                        && !strings[1].equals("4")) {
                                    int count = prot4Cost2v2;
                                    int deductedCount = 0;

                                    for (int i = 0; i < inventory.getSize(); i++) {
                                        if (deductedCount >= count) {
                                            break;
                                        }

                                        ItemStack item = inventory.getItem(i);
                                        if (item != null && item.getType() == diamond) {

                                            item.setAmount(item.getAmount() - 1);
                                            deductedCount++;

                                            if (item.getAmount() <= 0) {
                                                inventory.setItem(i, null);
                                            }
                                        }
                                    }

                                    Set<String[]> newteamDatas = Collections.newSetFromMap(new ConcurrentHashMap<>());
                                    for (String[] data : teamDatas) {
                                        newteamDatas.add(data.clone());
                                    }

                                    for (String team : game.getTeams().keySet()) {
                                        String[] teamData = new String[]{teamName, "4"};
                                        String[][] dataArray = newteamDatas.toArray(new String[newteamDatas.size()][]);
                                        for (String[] value : dataArray) {
                                            if (value[0].equals(team)) {
                                                newteamDatas.remove(value);
                                                newteamDatas.add(teamData);
                                                break;
                                            }
                                        }
                                    }

                                    ListProt.setTeamDatas(gameName, newteamDatas);

                                    String mess = messLevelUpprot4.replace("{player}", player.getName());
                                    player.sendMessage(t(mess));
                                    buyFail = false;
                                }
                            }
                        }
                    }

                } else if (player.getWorld().getName().contains(gameWorld4v4) || bungeeMode.equals("4v4")) {

                    if (clickedItemType == leveluphasteItemType
                            && clickedItem.hasItemMeta()) {

                        Set<String[]> teamDatas = ListHaste.getTeamDatas(gameName);

                        if (clickedItemType == leveluphasteItemType) {

                            for (String[] strings : teamDatas) {
                                if (clickedItemName.equals(teamEffItemName_Haste1)
                                        && player.getInventory().contains(diamond, haste1Cost4v4)) {
                                    if (strings[0].equals(teamName)
                                            && !strings[1].equals("2") && !strings[1].equals("1")) {
                                        int count = haste1Cost4v4;
                                        int deductedCount = 0;

                                        for (int i = 0; i < inventory.getSize(); i++) {
                                            if (deductedCount >= count) {
                                                break;
                                            }

                                            ItemStack item = inventory.getItem(i);
                                            if (item != null && item.getType() == diamond) {

                                                item.setAmount(item.getAmount() - 1);
                                                deductedCount++;

                                                if (item.getAmount() <= 0) {
                                                    inventory.setItem(i, null);
                                                }
                                            }
                                        }

                                        Set<String[]> newteamDatas = Collections.newSetFromMap(new ConcurrentHashMap<>());
                                        for (String[] data : teamDatas) {
                                            newteamDatas.add(data.clone());
                                        }

                                        for (String team : game.getTeams().keySet()) {
                                            String[] teamData = new String[]{teamName, "1"};
                                            String[][] dataArray = newteamDatas.toArray(new String[newteamDatas.size()][]);
                                            for (String[] value : dataArray) {
                                                if (value[0].equals(team)) {
                                                    newteamDatas.remove(value);
                                                    newteamDatas.add(teamData);
                                                    break;
                                                }
                                            }
                                        }
                                        ListHaste.setTeamDatas(gameName, newteamDatas);

                                        String mess = messLevelUphaste1.replace("{player}", player.getName());
                                        player.sendMessage(t(mess));
                                        buyFail = false;
                                    } else {
                                        String mess = messLevelUpFailed.replace("{player}", player.getName());
                                        player.sendMessage(t(mess));
                                    }
                                } else if (clickedItemName.equals(teamEffItemName_Haste2) && player.getInventory().contains(diamond, haste2Cost4v4)) {

                                    if (strings[0].equals(teamName)
                                            && !strings[1].equals("2")) {
                                        int count = haste2Cost4v4;
                                        int deductedCount = 0;

                                        for (int i = 0; i < inventory.getSize(); i++) {
                                            if (deductedCount >= count) {
                                                break;
                                            }

                                            ItemStack item = inventory.getItem(i);
                                            if (item != null && item.getType() == diamond) {

                                                item.setAmount(item.getAmount() - 1);
                                                deductedCount++;

                                                if (item.getAmount() <= 0) {
                                                    inventory.setItem(i, null);
                                                }
                                            }
                                        }

                                        Set<String[]> newteamDatas = Collections.newSetFromMap(new ConcurrentHashMap<>());
                                        for (String[] data : teamDatas) {
                                            newteamDatas.add(data.clone());
                                        }

                                        for (String team : game.getTeams().keySet()) {
                                            String[] teamData = new String[]{teamName, "2"};
                                            String[][] dataArray = newteamDatas.toArray(new String[newteamDatas.size()][]);
                                            for (String[] value : dataArray) {
                                                if (value[0].equals(team)) {
                                                    newteamDatas.remove(value);
                                                    newteamDatas.add(teamData);
                                                    break;
                                                }
                                            }
                                        }
                                        ListHaste.setTeamDatas(gameName, newteamDatas);

                                        String mess = messLevelUphaste2.replace("{player}", player.getName());
                                        player.sendMessage(t(mess));
                                        buyFail = false;
                                    }
                                }
                            }
                        }
                    }
                    if (clickedItemType == leveluphealItemType) {

                        Set<String[]> teamDatas = ListHeal.getTeamDatas(gameName);

                        for (String[] strings : teamDatas) {
                            if (clickedItemName.equals(teamEffItemName_Heal1)
                                    && player.getInventory().contains(diamond, heal1Cost4v4)) {
                                if (strings[0].equals(teamName)
                                        && !strings[1].equals("1")) {
                                    int count = heal1Cost4v4;
                                    int deductedCount = 0;

                                    for (int i = 0; i < inventory.getSize(); i++) {
                                        if (deductedCount >= count) {
                                            break;
                                        }

                                        ItemStack item = inventory.getItem(i);
                                        if (item != null && item.getType() == diamond) {

                                            item.setAmount(item.getAmount() - 1);
                                            deductedCount++;

                                            if (item.getAmount() <= 0) {
                                                inventory.setItem(i, null);
                                            }
                                        }
                                    }

                                    Set<String[]> newteamDatas = Collections.newSetFromMap(new ConcurrentHashMap<>());
                                    for (String[] data : teamDatas) {
                                        newteamDatas.add(data.clone());
                                    }

                                    for (String team : game.getTeams().keySet()) {
                                        String[] teamData = new String[]{teamName, "1"};
                                        String[][] dataArray = newteamDatas.toArray(new String[newteamDatas.size()][]);
                                        for (String[] value : dataArray) {
                                            if (value[0].equals(team)) {
                                                newteamDatas.remove(value);
                                                newteamDatas.add(teamData);
                                                break;
                                            }
                                        }
                                    }
                                    ListHeal.setTeamDatas(gameName, newteamDatas);

                                    String mess = messLevelUpheal1.replace("{player}", player.getName());
                                    player.sendMessage(t(mess));
                                    buyFail = false;
                                }
                            }
                        }
                    }
                    if (clickedItemType == levelupsharpItemType) {

                        Set<String[]> teamDatas = ListSharp.getTeamDatas(gameName);

                        for (String[] strings : teamDatas) {
                            if (clickedItemName.equals(teamEnchItemName_sharp1)
                                    && player.getInventory().contains(diamond, sharp1Cost4v4)) {
                                if (strings[0].equals(teamName)
                                        && !strings[1].equals("1")
                                        && !strings[1].equals("2")
                                        && !strings[1].equals("3")
                                        && !strings[1].equals("4")) {
                                    int count = sharp1Cost4v4;
                                    int deductedCount = 0;

                                    for (int i = 0; i < inventory.getSize(); i++) {
                                        if (deductedCount >= count) {
                                            break;
                                        }

                                        ItemStack item = inventory.getItem(i);
                                        if (item != null && item.getType() == diamond) {

                                            item.setAmount(item.getAmount() - 1);
                                            deductedCount++;

                                            if (item.getAmount() <= 0) {
                                                inventory.setItem(i, null);
                                            }
                                        }
                                    }

                                    Set<String[]> newteamDatas = Collections.newSetFromMap(new ConcurrentHashMap<>());
                                    for (String[] data : teamDatas) {
                                        newteamDatas.add(data.clone());
                                    }

                                    for (String team : game.getTeams().keySet()) {
                                        String[] teamData = new String[]{teamName, "1"};
                                        String[][] dataArray = newteamDatas.toArray(new String[newteamDatas.size()][]);
                                        for (String[] value : dataArray) {
                                            if (value[0].equals(team)) {
                                                newteamDatas.remove(value);
                                                newteamDatas.add(teamData);
                                                break;
                                            }
                                        }
                                    }
                                    ListSharp.setTeamDatas(gameName, newteamDatas);

                                    String mess = messLevelUpsharp1.replace("{player}", player.getName());
                                    player.sendMessage(t(mess));
                                    buyFail = false;
                                }
                            } else if (clickedItemName.equals(teamEnchItemName_sharp2)
                                    && player.getInventory().contains(diamond, sharp2Cost4v4)) {
                                if (strings[0].equals(teamName)
                                        && !strings[1].equals("2")
                                        && !strings[1].equals("3")
                                        && !strings[1].equals("4")) {
                                    int count = sharp2Cost4v4;
                                    int deductedCount = 0;

                                    for (int i = 0; i < inventory.getSize(); i++) {
                                        if (deductedCount >= count) {
                                            break;
                                        }

                                        ItemStack item = inventory.getItem(i);
                                        if (item != null && item.getType() == diamond) {

                                            item.setAmount(item.getAmount() - 1);
                                            deductedCount++;

                                            if (item.getAmount() <= 0) {
                                                inventory.setItem(i, null);
                                            }
                                        }
                                    }

                                    Set<String[]> newteamDatas = Collections.newSetFromMap(new ConcurrentHashMap<>());
                                    for (String[] data : teamDatas) {
                                        newteamDatas.add(data.clone());
                                    }

                                    for (String team : game.getTeams().keySet()) {
                                        String[] teamData = new String[]{teamName, "2"};
                                        String[][] dataArray = newteamDatas.toArray(new String[newteamDatas.size()][]);
                                        for (String[] value : dataArray) {
                                            if (value[0].equals(team)) {
                                                newteamDatas.remove(value);
                                                newteamDatas.add(teamData);
                                                break;
                                            }
                                        }
                                    }
                                    ListSharp.setTeamDatas(gameName, newteamDatas);

                                    String mess = messLevelUpsharp2.replace("{player}", player.getName());
                                    player.sendMessage(t(mess));
                                    buyFail = false;
                                }
                            } else if (clickedItemName.equals(teamEnchItemName_sharp3)
                                    && player.getInventory().contains(diamond, sharp3Cost4v4)) {
                                if (strings[0].equals(teamName)
                                        && !strings[1].equals("3")
                                        && !strings[1].equals("4")) {
                                    int count = sharp3Cost4v4;
                                    int deductedCount = 0;

                                    for (int i = 0; i < inventory.getSize(); i++) {
                                        if (deductedCount >= count) {
                                            break;
                                        }

                                        ItemStack item = inventory.getItem(i);
                                        if (item != null && item.getType() == diamond) {

                                            item.setAmount(item.getAmount() - 1);
                                            deductedCount++;

                                            if (item.getAmount() <= 0) {
                                                inventory.setItem(i, null);
                                            }
                                        }
                                    }

                                    Set<String[]> newteamDatas = Collections.newSetFromMap(new ConcurrentHashMap<>());
                                    for (String[] data : teamDatas) {
                                        newteamDatas.add(data.clone());
                                    }

                                    for (String team : game.getTeams().keySet()) {
                                        String[] teamData = new String[]{teamName, "3"};
                                        String[][] dataArray = newteamDatas.toArray(new String[newteamDatas.size()][]);
                                        for (String[] value : dataArray) {
                                            if (value[0].equals(team)) {
                                                newteamDatas.remove(value);
                                                newteamDatas.add(teamData);
                                                break;
                                            }
                                        }
                                    }
                                    ListSharp.setTeamDatas(gameName, newteamDatas);

                                    String mess = messLevelUpsharp3.replace("{player}", player.getName());
                                    player.sendMessage(t(mess));
                                    buyFail = false;
                                }
                            } else if (clickedItemName.equals(teamEnchItemName_sharp4)
                                    && player.getInventory().contains(diamond, sharp4Cost4v4)) {
                                if (strings[0].equals(teamName)
                                        && !strings[1].equals("4")) {
                                    int count = sharp1Cost4v4;
                                    int deductedCount = 0;

                                    for (int i = 0; i < inventory.getSize(); i++) {
                                        if (deductedCount >= count) {
                                            break;
                                        }

                                        ItemStack item = inventory.getItem(i);
                                        if (item != null && item.getType() == diamond) {

                                            item.setAmount(item.getAmount() - 1);
                                            deductedCount++;

                                            if (item.getAmount() <= 0) {
                                                inventory.setItem(i, null);
                                            }
                                        }
                                    }

                                    Set<String[]> newteamDatas = Collections.newSetFromMap(new ConcurrentHashMap<>());
                                    for (String[] data : teamDatas) {
                                        newteamDatas.add(data.clone());
                                    }

                                    for (String team : game.getTeams().keySet()) {
                                        String[] teamData = new String[]{teamName, "4"};
                                        String[][] dataArray = newteamDatas.toArray(new String[newteamDatas.size()][]);
                                        for (String[] value : dataArray) {
                                            if (value[0].equals(team)) {
                                                newteamDatas.remove(value);
                                                newteamDatas.add(teamData);
                                                break;
                                            }
                                        }
                                    }

                                    ListSharp.setTeamDatas(gameName, newteamDatas);

                                    String mess = messLevelUpsharp4.replace("{player}", player.getName());
                                    player.sendMessage(t(mess));
                                    buyFail = false;
                                }
                            }
                        }
                    }
                    if (clickedItemType == levelupprotItemType) {

                        Set<String[]> teamDatas = ListProt.getTeamDatas(gameName);

                        for (String[] strings : teamDatas) {
                            if (clickedItemName.equals(teamEnchItemName_prot1)
                                    && player.getInventory().contains(diamond, prot1Cost4v4)) {
                                if (strings[0].equals(teamName)
                                        && !strings[1].equals("1")
                                        && !strings[1].equals("2")
                                        && !strings[1].equals("3")
                                        && !strings[1].equals("4")) {
                                    int count = prot1Cost4v4;
                                    int deductedCount = 0;

                                    for (int i = 0; i < inventory.getSize(); i++) {
                                        if (deductedCount >= count) {
                                            break;
                                        }

                                        ItemStack item = inventory.getItem(i);
                                        if (item != null && item.getType() == diamond) {

                                            item.setAmount(item.getAmount() - 1);
                                            deductedCount++;

                                            if (item.getAmount() <= 0) {
                                                inventory.setItem(i, null);
                                            }
                                        }
                                    }

                                    Set<String[]> newteamDatas = Collections.newSetFromMap(new ConcurrentHashMap<>());
                                    for (String[] data : teamDatas) {
                                        newteamDatas.add(data.clone());
                                    }

                                    for (String team : game.getTeams().keySet()) {
                                        String[] teamData = new String[]{teamName, "1"};
                                        String[][] dataArray = newteamDatas.toArray(new String[newteamDatas.size()][]);
                                        for (String[] value : dataArray) {
                                            if (value[0].equals(team)) {
                                                newteamDatas.remove(value);
                                                newteamDatas.add(teamData);
                                                break;
                                            }
                                        }
                                    }

                                    ListProt.setTeamDatas(gameName, newteamDatas);

                                    String mess = messLevelUpprot1.replace("{player}", player.getName());
                                    player.sendMessage(t(mess));
                                    buyFail = false;
                                }
                            } else if (clickedItemName.equals(teamEnchItemName_prot2)
                                    && player.getInventory().contains(diamond, prot2Cost4v4)) {
                                if (strings[0].equals(teamName)
                                        && !strings[1].equals("2")
                                        && !strings[1].equals("3")
                                        && !strings[1].equals("4")) {
                                    int count = prot2Cost4v4;
                                    int deductedCount = 0;

                                    for (int i = 0; i < inventory.getSize(); i++) {
                                        if (deductedCount >= count) {
                                            break;
                                        }

                                        ItemStack item = inventory.getItem(i);
                                        if (item != null && item.getType() == diamond) {

                                            item.setAmount(item.getAmount() - 1);
                                            deductedCount++;

                                            if (item.getAmount() <= 0) {
                                                inventory.setItem(i, null);
                                            }
                                        }
                                    }

                                    Set<String[]> newteamDatas = Collections.newSetFromMap(new ConcurrentHashMap<>());
                                    for (String[] data : teamDatas) {
                                        newteamDatas.add(data.clone());
                                    }

                                    for (String team : game.getTeams().keySet()) {
                                        String[] teamData = new String[]{teamName, "2"};
                                        String[][] dataArray = newteamDatas.toArray(new String[newteamDatas.size()][]);
                                        for (String[] value : dataArray) {
                                            if (value[0].equals(team)) {
                                                newteamDatas.remove(value);
                                                newteamDatas.add(teamData);
                                                break;
                                            }
                                        }
                                    }

                                    ListProt.setTeamDatas(gameName, newteamDatas);

                                    String mess = messLevelUpprot2.replace("{player}", player.getName());
                                    player.sendMessage(t(mess));
                                    buyFail = false;
                                }
                            } else if (clickedItemName.equals(teamEnchItemName_prot3)
                                    && player.getInventory().contains(diamond, prot3Cost4v4)) {
                                if (strings[0].equals(teamName)
                                        && !strings[1].equals("3")
                                        && !strings[1].equals("4")) {
                                    int count = prot3Cost4v4;
                                    int deductedCount = 0;

                                    for (int i = 0; i < inventory.getSize(); i++) {
                                        if (deductedCount >= count) {
                                            break;
                                        }

                                        ItemStack item = inventory.getItem(i);
                                        if (item != null && item.getType() == diamond) {

                                            item.setAmount(item.getAmount() - 1);
                                            deductedCount++;

                                            if (item.getAmount() <= 0) {
                                                inventory.setItem(i, null);
                                            }
                                        }
                                    }

                                    Set<String[]> newteamDatas = Collections.newSetFromMap(new ConcurrentHashMap<>());
                                    for (String[] data : teamDatas) {
                                        newteamDatas.add(data.clone());
                                    }

                                    for (String team : game.getTeams().keySet()) {
                                        String[] teamData = new String[]{teamName, "3"};
                                        String[][] dataArray = newteamDatas.toArray(new String[newteamDatas.size()][]);
                                        for (String[] value : dataArray) {
                                            if (value[0].equals(team)) {
                                                newteamDatas.remove(value);
                                                newteamDatas.add(teamData);
                                                break;
                                            }
                                        }
                                    }

                                    ListProt.setTeamDatas(gameName, newteamDatas);

                                    String mess = messLevelUpprot3.replace("{player}", player.getName());
                                    player.sendMessage(t(mess));
                                    buyFail = false;
                                }
                            } else if (clickedItemName.equals(teamEnchItemName_prot4)
                                    && player.getInventory().contains(diamond, prot4Cost4v4)) {
                                if (strings[0].equals(teamName)
                                        && !strings[1].equals("4")) {
                                    int count = prot4Cost4v4;
                                    int deductedCount = 0;

                                    for (int i = 0; i < inventory.getSize(); i++) {
                                        if (deductedCount >= count) {
                                            break;
                                        }

                                        ItemStack item = inventory.getItem(i);
                                        if (item != null && item.getType() == diamond) {

                                            item.setAmount(item.getAmount() - 1);
                                            deductedCount++;

                                            if (item.getAmount() <= 0) {
                                                inventory.setItem(i, null);
                                            }
                                        }
                                    }

                                    Set<String[]> newteamDatas = Collections.newSetFromMap(new ConcurrentHashMap<>());
                                    for (String[] data : teamDatas) {
                                        newteamDatas.add(data.clone());
                                    }

                                    for (String team : game.getTeams().keySet()) {
                                        String[] teamData = new String[]{teamName, "4"};
                                        String[][] dataArray = newteamDatas.toArray(new String[newteamDatas.size()][]);
                                        for (String[] value : dataArray) {
                                            if (value[0].equals(team)) {
                                                newteamDatas.remove(value);
                                                newteamDatas.add(teamData);
                                                break;
                                            }
                                        }
                                    }

                                    ListProt.setTeamDatas(gameName, newteamDatas);

                                    String mess = messLevelUpprot4.replace("{player}", player.getName());
                                    player.sendMessage(t(mess));
                                    buyFail = false;
                                }
                            }
                        }
                    }

                }


                if (clickedItemType == glass_pane) {
                    buyFail = false;
                }
                if (clickedItemType == levelupresItemType) {
                    buyFail = false;
                }
                if (buyFail) {
                    playerSoundFail(player);
                    String mess = messLevelUpFailed.replace("{player}", player.getName());
                    player.sendMessage(t(mess));
                } else {
                    playerSoundSucc(player);
                    openShop(player, 1L);
                }
            }
        }
    }

    void playerSoundOpen(Player player) {
        CLICK(player, 1);
    }

    void playerSoundSucc(Player player) {
        CHICKEN_EGG_POP(player, 1);
    }

    void playerSoundFail(Player player) {
        ITEM_BREAK(player, 1);
    }
}
