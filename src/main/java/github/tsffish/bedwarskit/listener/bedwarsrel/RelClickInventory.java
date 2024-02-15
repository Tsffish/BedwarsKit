package github.tsffish.bedwarskit.listener.bedwarsrel;

import github.tsffish.bedwarskit.Main;
import github.tsffish.bedwarskit.util.teamshop.RelTeamEnchant;
import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameManager;
import io.github.bedwarsrel.game.Team;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static github.tsffish.bedwarskit.config.kit.KitConfigHandler.*;
import static github.tsffish.bedwarskit.config.main.MainConfigHandler.*;
import static github.tsffish.bedwarskit.listener.bedwarsrel.RelPlayerOpenShop.openShop;
import static github.tsffish.bedwarskit.util.RelPlayerKit.setPlayerKit;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;
import static github.tsffish.bedwarskit.util.misc.MessSender.consoleSendCommand;
import static github.tsffish.bedwarskit.util.misc.MessSender.l;
import static github.tsffish.bedwarskit.util.teamshop.RelEffect.*;
import static github.tsffish.bedwarskit.util.teamshop.RelEnchant.*;
import static github.tsffish.bedwarskit.util.teamshop.RelShopLevelUp.openForPlayer2v2;
import static github.tsffish.bedwarskit.util.teamshop.RelShopLevelUp.openForPlayer4v4;
import static github.tsffish.bedwarskit.util.teamshop.RelTeamEffect.*;
import static github.tsffish.bedwarskit.util.teamshop.RelTeamEnchant.isprotValueContains;
import static github.tsffish.bedwarskit.util.teamshop.RelTeamEnchant.issharpValueContains;
import static org.bukkit.Sound.*;

public class RelClickInventory implements Listener {
    @EventHandler
    public void on(InventoryClickEvent event) {
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


        if (antiDrop) {
            if (Main.isDebug()){
            l("pd antiDrop");
            System.out.println(noMoveList);
            System.out.println(itemTypeText);
            }
            for (String list : noMoveList){
                if (itemTypeText.contains(list)){
                event.setCancelled(true);
                break;
                }
            }
        }

        if (inventoryName.contains(kitMenuTitle)){
            boolean isSelected = false;
            event.setCancelled(true);
            if (itemType == KitDefaultItemType){
                setPlayerKit(playerName,"default");
                player.sendMessage(t(meanSelKitSucc));
                isSelected = true;
            } else if (itemType == KitNoneItemType) {
                setPlayerKit(playerName,"none");
                player.sendMessage(t(meanSelKitSucc));
                isSelected = true;

            } else if (itemType == KitDefaultlessItemType) {
                setPlayerKit(playerName,"defaultless");
                player.sendMessage(t(meanSelKitSucc));
                isSelected = true;

            }

            if (isSelected){
                playerSoundSucc(player);
                player.closeInventory();
            }
        }

        if(inventoryName.equals(shopLevelup)){
            event.setCancelled(true);
        }


        if (dieOutGameItem_playAgain){
            if (itemTypeText.equals(dieOutGameItem_playAgain_ItemType.toString()) && itemStack.getItemMeta().getDisplayName().equals(dieOutGameItem_playAgain_ItemName)){
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
        if (player.getWorld().getName().contains(rushWorld) && inventoryName.equalsIgnoreCase(shopLevelup)) {

            GameManager gameManager = BedwarsRel.getInstance().getGameManager();
            Game game = gameManager.getGameOfPlayer(player);
            Team team = game.getPlayerTeam(player);


            ItemStack clickedItem = event.getCurrentItem();
            event.setCancelled(true);

            if (clickedItem == null){
                return;
            }

            if (event.isShiftClick() && event.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY){
                if (clickedItem.getType() == Material.WOOD_SWORD){
                    event.setCancelled(true);
                }
            }

            String clickedItemName = clickedItem.getItemMeta().getDisplayName();
            String mapName = game.getName();
            String teamName = team.getName();

            if (player.getWorld().getName().contains(rushWorld2v2)) {
                boolean buyFail = true;

                if (clickedItem.getType() == levelupprotItemType && clickedItem.hasItemMeta()) {

                    if (clickedItemName.equals(teamEnchItemName_prot1) && player.getInventory().contains(Material.DIAMOND, prot1Cost2v2) && !isprotValueContains(mapName, teamName, "1") && !isprotValueContains(mapName, teamName, "2") && !isprotValueContains(mapName, teamName, "3") && !isprotValueContains(mapName, teamName, "4")) {
                        player.getInventory().removeItem(new ItemStack(Material.DIAMOND, prot1Cost2v2));
                        setprot1(team);
                        RelTeamEnchant.updateTeamEnchantListprot(mapName, teamName, "1");
                        String mess = messLevelUpprot1.replace("{player}",player.getName());
                        player.sendMessage(t(mess));
                        playerSoundSucc(player);
                        buyFail = false;
                        openForPlayer2v2(player, game);
                    } else if (clickedItemName.equals(teamEnchItemName_prot2) && player.getInventory().contains(Material.DIAMOND, prot2Cost2v2) && !isprotValueContains(mapName, teamName, "2") && !isprotValueContains(mapName, teamName, "3") && !isprotValueContains(mapName, teamName, "4")) {
                        player.getInventory().removeItem(new ItemStack(Material.DIAMOND, prot2Cost2v2));
                        setprot2(team);
                        RelTeamEnchant.updateTeamEnchantListprot(mapName, teamName, "2");
                        String mess = messLevelUpprot2.replace("{player}",player.getName());
                        player.sendMessage(t(mess));
                        playerSoundSucc(player);
                        buyFail = false;
                        openForPlayer2v2(player, game);
                    } else if (clickedItemName.equals(teamEnchItemName_prot3) && player.getInventory().contains(Material.DIAMOND, prot3Cost2v2) && !isprotValueContains(mapName, teamName, "3") && !isprotValueContains(mapName, teamName, "4")) {
                        player.getInventory().removeItem(new ItemStack(Material.DIAMOND, prot3Cost2v2));
                        setprot3(team);
                        RelTeamEnchant.updateTeamEnchantListprot(mapName, teamName, "3");
                        String mess = messLevelUpprot3.replace("{player}",player.getName());
                        player.sendMessage(t(mess));
                        playerSoundSucc(player);
                        buyFail = false;
                        openForPlayer2v2(player, game);
                    } else if (clickedItemName.equals(teamEnchItemName_prot4) && player.getInventory().contains(Material.DIAMOND, prot4Cost2v2) && !isprotValueContains(mapName, teamName, "4") ) {
                        player.getInventory().removeItem(new ItemStack(Material.DIAMOND, prot4Cost2v2));
                        setprot4(team);
                        RelTeamEnchant.updateTeamEnchantListprot(mapName, teamName, "4");
                        String mess = messLevelUpprot4.replace("{player}",player.getName());
                        player.sendMessage(t(mess));
                        playerSoundSucc(player);
                        buyFail = false;
                        openForPlayer2v2(player, game);
                    }  else{
                        String mess = messLevelUpFailed.replace("{player}",player.getName());
                        player.sendMessage(t(mess));
                        buyFail = true;
                    }

                } else if (clickedItem.getType() == levelupsharpItemType && clickedItem.hasItemMeta()) {

                    if (clickedItemName.equals(teamEnchItemName_sharp1) && player.getInventory().contains(Material.DIAMOND, sharp1Cost2v2) && !issharpValueContains(mapName, teamName, "1") && !issharpValueContains(mapName, teamName, "2") && !issharpValueContains(mapName, teamName, "3") && !issharpValueContains(mapName, teamName, "4")) {
                        player.getInventory().removeItem(new ItemStack(Material.DIAMOND, sharp1Cost2v2));
                        setsharp1(team);
                        RelTeamEnchant.updateTeamEnchantListsharp(mapName, teamName, "1");
                        String mess = messLevelUpsharp1.replace("{player}",player.getName());
                        player.sendMessage(t(mess));
                        playerSoundSucc(player);
                        buyFail = false;
                        openForPlayer2v2(player, game);
                    } else if (clickedItemName.equals(teamEnchItemName_sharp2) && player.getInventory().contains(Material.DIAMOND, sharp2Cost2v2) && !issharpValueContains(mapName, teamName, "2") && !issharpValueContains(mapName, teamName, "3") && !issharpValueContains(mapName, teamName, "4")) {
                        player.getInventory().removeItem(new ItemStack(Material.DIAMOND, sharp2Cost2v2));
                        setsharp2(team);
                        RelTeamEnchant.updateTeamEnchantListsharp(mapName, teamName, "2");
                        String mess = messLevelUpsharp2.replace("{player}",player.getName());
                        player.sendMessage(t(mess));
                        playerSoundSucc(player);
                        buyFail = false;
                        openForPlayer2v2(player, game);
                    } else if (clickedItemName.equals(teamEnchItemName_sharp3) && player.getInventory().contains(Material.DIAMOND, sharp3Cost2v2) && !issharpValueContains(mapName, teamName, "3") && !issharpValueContains(mapName, teamName, "4")) {
                        player.getInventory().removeItem(new ItemStack(Material.DIAMOND, sharp3Cost2v2));
                        setsharp3(team);
                        RelTeamEnchant.updateTeamEnchantListsharp(mapName, teamName, "3");
                        String mess = messLevelUpsharp3.replace("{player}",player.getName());
                        player.sendMessage(t(mess));
                        playerSoundSucc(player);
                        buyFail = false;
                        openForPlayer2v2(player, game);
                    } else if (clickedItemName.equals(teamEnchItemName_sharp4) && player.getInventory().contains(Material.DIAMOND, sharp4Cost2v2) && !issharpValueContains(mapName, teamName, "4") ) {
                        player.getInventory().removeItem(new ItemStack(Material.DIAMOND, sharp4Cost2v2));
                        setsharp4(team);
                        RelTeamEnchant.updateTeamEnchantListsharp(mapName, teamName, "4");
                        String mess = messLevelUpsharp4.replace("{player}",player.getName());
                        player.sendMessage(t(mess));
                        playerSoundSucc(player);
                        buyFail = false;
                        openForPlayer2v2(player, game);
                    }  else{
                        String mess = messLevelUpFailed.replace("{player}",player.getName());
                        player.sendMessage(t(mess));
                        buyFail = true;

                    }

                }else if (clickedItem.getType() == leveluphealItemType && clickedItem.hasItemMeta()) {

                    if (clickedItemName.equals(teamEffItemName_heal1) && player.getInventory().contains(Material.DIAMOND, heal1Cost2v2) && !ishealValueContains(mapName, teamName, "1")) {
                        player.getInventory().removeItem(new ItemStack(Material.DIAMOND, heal1Cost2v2));
                        setheal1(team);
                        updateTeamEffectListheal(mapName, teamName, "1");
                        String mess = messLevelUpheal1.replace("{player}",player.getName());
                        player.sendMessage(t(mess));
                        playerSoundSucc(player);
                        buyFail = false;
                        openForPlayer2v2(player, game);
                    }  else{
                        String mess = messLevelUpFailed.replace("{player}",player.getName());
                        player.sendMessage(t(mess));
                        buyFail = true;
                    }

                } else if (clickedItemName.equals(teamEffItemName_haste1) && player.getInventory().contains(Material.DIAMOND, haste1Cost2v2) && !ishasteValueContains(mapName, teamName, "1") && !ishasteValueContains(mapName, teamName, "2") && !ishasteValueContains(mapName, teamName, "3") && !ishasteValueContains(mapName, teamName, "4")) {
                    player.getInventory().removeItem(new ItemStack(Material.DIAMOND, haste1Cost2v2));
                    sethaste1(team);
                    updateTeamEffectListhaste(mapName, teamName, "1");
                    String mess = messLevelUphaste1.replace("{player}",player.getName());
                    player.sendMessage(t(mess));
                    playerSoundSucc(player);
                    openForPlayer2v2(player, game);
                } else if (clickedItemName.equals(teamEffItemName_haste2) && player.getInventory().contains(Material.DIAMOND, haste2Cost2v2) && !ishasteValueContains(mapName, teamName, "2") && !ishasteValueContains(mapName, teamName, "3") && !ishasteValueContains(mapName, teamName, "4")) {
                    player.getInventory().removeItem(new ItemStack(Material.DIAMOND, haste2Cost2v2));
                    sethaste2(team);
                    updateTeamEffectListhaste(mapName, teamName, "2");
                    String mess = messLevelUphaste2.replace("{player}",player.getName());
                    player.sendMessage(t(mess));
                    playerSoundSucc(player);
                    buyFail = false;
                    openForPlayer2v2(player, game);
                }  else{
                    String mess = messLevelUpFailed.replace("{player}",player.getName());
                    player.sendMessage(t(mess));
                    buyFail = true;
                }


                if (buyFail){
                    playerSoundFail(player);
                }

            } else if (player.getWorld().getName().contains(rushWorld4v4)) {
                {
                    boolean buyFail = true;

                    if (clickedItem.getType() == levelupprotItemType && clickedItem.hasItemMeta()) {

                        if (clickedItemName.equals(teamEnchItemName_prot1) && player.getInventory().contains(Material.DIAMOND, prot1Cost4v4) && !isprotValueContains(mapName, teamName, "1") && !isprotValueContains(mapName, teamName, "2") && !isprotValueContains(mapName, teamName, "3") && !isprotValueContains(mapName, teamName, "4")) {
                            player.getInventory().removeItem(new ItemStack(Material.DIAMOND, prot1Cost4v4));
                            setprot1(team);
                            RelTeamEnchant.updateTeamEnchantListprot(mapName, teamName, "1");
                            String mess = messLevelUpprot1.replace("{player}",player.getName());
                            player.sendMessage(t(mess));
                            playerSoundSucc(player);
                            buyFail = false;
                            openForPlayer4v4(player, game);
                        } else if (clickedItemName.equals(teamEnchItemName_prot2) && player.getInventory().contains(Material.DIAMOND, prot2Cost4v4) && !isprotValueContains(mapName, teamName, "2") && !isprotValueContains(mapName, teamName, "3") && !isprotValueContains(mapName, teamName, "4")) {
                            player.getInventory().removeItem(new ItemStack(Material.DIAMOND, prot2Cost4v4));
                            setprot2(team);
                            RelTeamEnchant.updateTeamEnchantListprot(mapName, teamName, "2");
                            String mess = messLevelUpprot2.replace("{player}",player.getName());
                            player.sendMessage(t(mess));
                            playerSoundSucc(player);
                            buyFail = false;
                            openForPlayer4v4(player, game);
                        } else if (clickedItemName.equals(teamEnchItemName_prot3) && player.getInventory().contains(Material.DIAMOND, prot3Cost4v4) && !isprotValueContains(mapName, teamName, "3") && !isprotValueContains(mapName, teamName, "4")) {
                            player.getInventory().removeItem(new ItemStack(Material.DIAMOND, prot3Cost4v4));
                            setprot3(team);
                            RelTeamEnchant.updateTeamEnchantListprot(mapName, teamName, "3");
                            String mess = messLevelUpprot3.replace("{player}",player.getName());
                            player.sendMessage(t(mess));
                            playerSoundSucc(player);
                            buyFail = false;
                            openForPlayer4v4(player, game);
                        } else if (clickedItemName.equals(teamEnchItemName_prot4) && player.getInventory().contains(Material.DIAMOND, prot4Cost4v4) && !isprotValueContains(mapName, teamName, "4") ) {
                            player.getInventory().removeItem(new ItemStack(Material.DIAMOND, prot4Cost4v4));
                            setprot4(team);
                            RelTeamEnchant.updateTeamEnchantListprot(mapName, teamName, "4");
                            String mess = messLevelUpprot4.replace("{player}",player.getName());
                            player.sendMessage(t(mess));
                            playerSoundSucc(player);
                            buyFail = false;
                            openForPlayer4v4(player, game);
                        }  else{
                            String mess = messLevelUpFailed.replace("{player}",player.getName());
                            player.sendMessage(t(mess));
                            buyFail = true;
                        }

                    } else if (clickedItem.getType() == levelupsharpItemType && clickedItem.hasItemMeta()) {

                        if (clickedItemName.equals(teamEnchItemName_sharp1) && player.getInventory().contains(Material.DIAMOND, sharp1Cost4v4) && !issharpValueContains(mapName, teamName, "1") && !issharpValueContains(mapName, teamName, "2") && !issharpValueContains(mapName, teamName, "3") && !issharpValueContains(mapName, teamName, "4")) {
                            player.getInventory().removeItem(new ItemStack(Material.DIAMOND, sharp1Cost4v4));
                            setsharp1(team);
                            RelTeamEnchant.updateTeamEnchantListsharp(mapName, teamName, "1");
                            String mess = messLevelUpsharp1.replace("{player}",player.getName());
                            player.sendMessage(t(mess));
                            playerSoundSucc(player);
                            buyFail = false;
                            openForPlayer4v4(player, game);
                        } else if (clickedItemName.equals(teamEnchItemName_sharp2) && player.getInventory().contains(Material.DIAMOND, sharp2Cost4v4) && !issharpValueContains(mapName, teamName, "2") && !issharpValueContains(mapName, teamName, "3") && !issharpValueContains(mapName, teamName, "4")) {
                            player.getInventory().removeItem(new ItemStack(Material.DIAMOND, sharp2Cost4v4));
                            setsharp2(team);
                            RelTeamEnchant.updateTeamEnchantListsharp(mapName, teamName, "2");
                            String mess = messLevelUpsharp2.replace("{player}",player.getName());
                            player.sendMessage(t(mess));
                            playerSoundSucc(player);
                            buyFail = false;
                            openForPlayer4v4(player, game);
                        } else if (clickedItemName.equals(teamEnchItemName_sharp3) && player.getInventory().contains(Material.DIAMOND, sharp3Cost4v4) && !issharpValueContains(mapName, teamName, "3") && !issharpValueContains(mapName, teamName, "4")) {
                            player.getInventory().removeItem(new ItemStack(Material.DIAMOND, sharp3Cost4v4));
                            setsharp3(team);
                            RelTeamEnchant.updateTeamEnchantListsharp(mapName, teamName, "3");
                            String mess = messLevelUpsharp3.replace("{player}",player.getName());
                            player.sendMessage(t(mess));
                            playerSoundSucc(player);
                            buyFail = false;
                            openForPlayer4v4(player, game);
                        } else if (clickedItemName.equals(teamEnchItemName_sharp4) && player.getInventory().contains(Material.DIAMOND, sharp4Cost4v4) && !issharpValueContains(mapName, teamName, "4") ) {
                            player.getInventory().removeItem(new ItemStack(Material.DIAMOND, sharp4Cost4v4));
                            setsharp4(team);
                            RelTeamEnchant.updateTeamEnchantListsharp(mapName, teamName, "4");
                            String mess = messLevelUpsharp4.replace("{player}",player.getName());
                            player.sendMessage(t(mess));
                            playerSoundSucc(player);
                            buyFail = false;
                            openForPlayer4v4(player, game);
                        }  else{
                            String mess = messLevelUpFailed.replace("{player}",player.getName());
                            player.sendMessage(t(mess));
                            buyFail = true;

                        }

                    }else if (clickedItem.getType() == leveluphealItemType && clickedItem.hasItemMeta()) {

                        if (clickedItemName.equals(teamEffItemName_heal1) && player.getInventory().contains(Material.DIAMOND, heal1Cost4v4) && !ishealValueContains(mapName, teamName, "1")) {
                            player.getInventory().removeItem(new ItemStack(Material.DIAMOND, heal1Cost4v4));
                            setheal1(team);
                            updateTeamEffectListheal(mapName, teamName, "1");
                            String mess = messLevelUpheal1.replace("{player}",player.getName());
                            player.sendMessage(t(mess));
                            playerSoundSucc(player);
                            buyFail = false;
                            openForPlayer4v4(player, game);
                        }  else{
                            String mess = messLevelUpFailed.replace("{player}",player.getName());
                            player.sendMessage(t(mess));
                            buyFail = true;
                        }

                    } else if (clickedItemName.equals(teamEffItemName_haste1) && player.getInventory().contains(Material.DIAMOND, haste1Cost4v4) && !ishasteValueContains(mapName, teamName, "1") && !ishasteValueContains(mapName, teamName, "2") && !ishasteValueContains(mapName, teamName, "3") && !ishasteValueContains(mapName, teamName, "4")) {
                        player.getInventory().removeItem(new ItemStack(Material.DIAMOND, haste1Cost4v4));
                        sethaste1(team);
                        updateTeamEffectListhaste(mapName, teamName, "1");
                        String mess = messLevelUphaste1.replace("{player}",player.getName());
                        player.sendMessage(t(mess));
                        playerSoundSucc(player);
                        openForPlayer4v4(player, game);
                    } else if (clickedItemName.equals(teamEffItemName_haste2) && player.getInventory().contains(Material.DIAMOND, haste2Cost4v4) && !ishasteValueContains(mapName, teamName, "2") && !ishasteValueContains(mapName, teamName, "3") && !ishasteValueContains(mapName, teamName, "4")) {
                        player.getInventory().removeItem(new ItemStack(Material.DIAMOND, haste2Cost4v4));
                        sethaste2(team);
                        updateTeamEffectListhaste(mapName, teamName, "2");
                        String mess = messLevelUphaste2.replace("{player}",player.getName());
                        player.sendMessage(t(mess));
                        playerSoundSucc(player);
                        buyFail = false;
                        openForPlayer4v4(player, game);
                    }  else{
                        String mess = messLevelUpFailed.replace("{player}",player.getName());
                        player.sendMessage(t(mess));
                        buyFail = true;
                    }


                    if (buyFail){
                        playerSoundFail(player);
                    }

                }

            }
            }

    }
    private static void playerSoundOpen(Player player){
        player.playSound(player.getLocation(),CLICK,1,1);
    }
    private static void playerSoundSucc(Player player){
        player.playSound(player.getLocation(),ITEM_PICKUP,1,1);
    }
    private static void playerSoundFail(Player player){
        player.playSound(player.getLocation(),ITEM_BREAK,1,1);
    }
}
