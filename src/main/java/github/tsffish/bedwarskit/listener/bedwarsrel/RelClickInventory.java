package github.tsffish.bedwarskit.listener.bedwarsrel;

import github.tsffish.bedwarskit.config.MainConfigHandler;
import github.tsffish.bedwarskit.util.RelTeamEnchant;
import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameManager;
import io.github.bedwarsrel.game.Team;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Objects;

import static github.tsffish.bedwarskit.config.KitConfigHandler.*;
import static github.tsffish.bedwarskit.config.MainConfigHandler.*;
import static github.tsffish.bedwarskit.listener.bedwarsrel.RelEnchant.*;
import static github.tsffish.bedwarskit.listener.bedwarsrel.RelPlayerOpenShop.openShop;
import static github.tsffish.bedwarskit.util.RelPlayerKit.playerKitList;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;

public class RelClickInventory implements Listener {
    @EventHandler
    public void on(InventoryClickEvent event) {
        Inventory ci = event.getClickedInventory();
        

        if (ci == null) return;
        String cin = ci.getName();
        

        if (cin == null) return;
        ItemStack i = event.getCurrentItem();

        if (i == null || i.getType() == Material.AIR) return;
        Material it = i.getType();

        if (it == null) return;

        Player player = (Player) event.getWhoClicked();
        if (player == null) return;

        if (ci.getName().contains("选择队伍 - ")){
            Game game = BedwarsRel.getInstance().getGameManager().getGameOfPlayer(player);

            if(it == Material.WOOL){
                String itn = i.getItemMeta().getDisplayName();
                if (itn.contains(t("&a绿之队"))){

                    game.getTeam(relTeamName_Green).addPlayer(player);
                    player.sendMessage(t("&a你加入了 " + "&a绿之队" + "&a!"));

                } else if (itn.contains("relTeamName_Green")) {
                    game.getTeam("relTeamName_Green").addPlayer(player);
                }
            }
        }

        if (MainConfigHandler.antiDrop) {
            if (it != Material.AIR && NoMoveList.contains(it)){
                event.setCancelled(true);
            }
        }

        if (ci.getName().contains(kitMenuTitle)){

            event.setCancelled(true);
            if (it == KitDefaultItemType){
                playerKitList.put(player,"Default");
                player.sendMessage(t(meanSelKitSucc));
            } else if (it == KitNoneItemType) {
                playerKitList.put(player,"None");
                player.sendMessage(t(meanSelKitSucc));

            } else if (it == KitDefaultlessItemType) {
                playerKitList.put(player,"Defaultless");
                player.sendMessage(t(meanSelKitSucc));

            }
        }

        if(cin.equals(shopLevelup)){
            event.setCancelled(true);
        }

        if (Objects.equals(shopItem, "")) {
                if (it == Material.BOOK_AND_QUILL) {
                    if (levelupShopDelayOpen) {
                        openShop(player, 8L);
                    } else {
                        openShop(player, 0L);
                    }

                }
        }else {
            if (cin.equals(shopItem))
                if (it == Material.BOOK_AND_QUILL) {
                if (levelupShopDelayOpen) {
                    openShop(player, 8L);
                } else {
                    openShop(player, 0L);
                }

            }
        }
        if (it == LevelupItemType && player.getGameMode() == GameMode.CREATIVE){
            event.setCancelled(true);
        }

        if (player.getWorld().getName().contains(MainConfigHandler.rushWorld) && player.getGameMode() != GameMode.CREATIVE) {

            GameManager gameManager = BedwarsRel.getInstance().getGameManager();
            Game game = gameManager.getGameOfPlayer(player);
            Team team = game.getPlayerTeam(player);

            if (player.getWorld().getName().contains(rushWorld2v2)){

                if (event.getInventory().getName().equals(MainConfigHandler.shopLevelup)) {
                    ItemStack clickedItem = event.getCurrentItem();
                    event.setCancelled(true);
                    if (clickedItem != null && clickedItem.getType() == Material.BOOK_AND_QUILL && clickedItem.hasItemMeta()) {
                        String clickedItemName = clickedItem.getItemMeta().getDisplayName();
                        String mapName = game.getName();
                        String teamName = team.getName();

                        if (clickedItemName.equals(MainConfigHandler.teamEnchItemName_Prot2) && player.getInventory().contains(Material.DIAMOND, prot2Cost2v2) && !Arrays.asList(RelTeamEnchant.teamEnchantListProt.get(mapName)).contains("2") && !Arrays.asList(RelTeamEnchant.teamEnchantListProt.get(mapName)).contains("3") && !Arrays.asList(RelTeamEnchant.teamEnchantListProt.get(mapName)).contains("4")) {
                            player.getInventory().removeItem(new ItemStack(Material.DIAMOND, prot2Cost2v2));
                            setProt2(team);
                            RelTeamEnchant.teamEnchantListProt.put(mapName, new String[]{teamName, "2"});
                            String mess = messLevelUpProt2.replace("{player}",player.getName());
                            player.sendMessage(t(mess)); 
                        } else if (clickedItemName.equals(MainConfigHandler.teamEnchItemName_Prot1) && player.getInventory().contains(Material.DIAMOND, prot1Cost2v2) && !Arrays.asList(RelTeamEnchant.teamEnchantListProt.get(mapName)).contains("1") && !Arrays.asList(RelTeamEnchant.teamEnchantListProt.get(mapName)).contains("2") && !Arrays.asList(RelTeamEnchant.teamEnchantListProt.get(mapName)).contains("3") && !Arrays.asList(RelTeamEnchant.teamEnchantListProt.get(mapName)).contains("4")) {
                            player.getInventory().removeItem(new ItemStack(Material.DIAMOND, prot1Cost2v2));
                            setProt1(team);
                            RelTeamEnchant.teamEnchantListProt.put(mapName, new String[]{teamName, "1"});
                            String mess = messLevelUpProt1.replace("{player}",player.getName());
                            player.sendMessage(t(mess)); 
                        } else if (clickedItemName.equals(MainConfigHandler.teamEnchItemName_Prot3) && player.getInventory().contains(Material.DIAMOND, prot3Cost2v2) && !Arrays.asList(RelTeamEnchant.teamEnchantListProt.get(mapName)).contains("3") && !Arrays.asList(RelTeamEnchant.teamEnchantListProt.get(mapName)).contains("4")) {
                            player.getInventory().removeItem(new ItemStack(Material.DIAMOND, prot3Cost2v2));
                            setProt3(team);
                            RelTeamEnchant.teamEnchantListProt.put(mapName, new String[]{teamName, "3"});
                            String mess = messLevelUpProt3.replace("{player}",player.getName());
                            player.sendMessage(t(mess)); 
                        } else if (clickedItemName.equals(MainConfigHandler.teamEnchItemName_Prot4) && player.getInventory().contains(Material.DIAMOND, prot4Cost2v2) && !Arrays.asList(RelTeamEnchant.teamEnchantListProt.get(mapName)).contains("4")) {
                            player.getInventory().removeItem(new ItemStack(Material.DIAMOND, prot4Cost2v2));
                            setProt4(team);
                            RelTeamEnchant.teamEnchantListProt.put(mapName, new String[]{teamName, "4"});
                            String mess = messLevelUpProt4.replace("{player}",player.getName());
                            player.sendMessage(t(mess)); 
                        } else if (clickedItemName.equals(MainConfigHandler.teamEnchItemName_Sharp1) && player.getInventory().contains(Material.DIAMOND, sharp1Cost2v2) && !Arrays.asList(RelTeamEnchant.teamEnchantListSword.get(mapName)).contains("1")) {
                            player.getInventory().removeItem(new ItemStack(Material.DIAMOND, sharp1Cost2v2));
                            setSharp1(team);
                            RelTeamEnchant.teamEnchantListSword.put(mapName, new String[]{teamName, "1"});
                            String mess = messLevelUpSharp1.replace("{player}",player.getName());
                            player.sendMessage(t(mess)); 
                        } else{
                            String mess = messLevelUpFailed.replace("{player}",player.getName());
                            player.sendMessage(t(mess));
                        }

                    }
                }
            } else if (player.getWorld().getName().contains(rushWorld4v4)) {
                if (event.getInventory().getName().equals(MainConfigHandler.shopLevelup)) {
                    ItemStack clickedItem = event.getCurrentItem();
                    event.setCancelled(true);
                    if (clickedItem != null && clickedItem.getType() == Material.BOOK_AND_QUILL && clickedItem.hasItemMeta()) {
                        String clickedItemName = clickedItem.getItemMeta().getDisplayName();
                        String mapName = game.getName();
                        String teamName = team.getName();

                        if (clickedItemName.equals(MainConfigHandler.teamEnchItemName_Prot2) && player.getInventory().contains(Material.DIAMOND, prot2Cost4v4) && !Arrays.asList(RelTeamEnchant.teamEnchantListProt.get(mapName)).contains("2") && !Arrays.asList(RelTeamEnchant.teamEnchantListProt.get(mapName)).contains("3") && !Arrays.asList(RelTeamEnchant.teamEnchantListProt.get(mapName)).contains("4")) {
                            player.getInventory().removeItem(new ItemStack(Material.DIAMOND, prot2Cost4v4));
                            setProt2(team);
                            RelTeamEnchant.teamEnchantListProt.put(mapName, new String[]{teamName, "2"});
                            String mess = messLevelUpProt2.replace("{player}",player.getName());
                            player.sendMessage(t(mess)); 
                        } else if (clickedItemName.equals(MainConfigHandler.teamEnchItemName_Prot1) && player.getInventory().contains(Material.DIAMOND, prot1Cost4v4) && !Arrays.asList(RelTeamEnchant.teamEnchantListProt.get(mapName)).contains("1") && !Arrays.asList(RelTeamEnchant.teamEnchantListProt.get(mapName)).contains("2") && !Arrays.asList(RelTeamEnchant.teamEnchantListProt.get(mapName)).contains("3") && !Arrays.asList(RelTeamEnchant.teamEnchantListProt.get(mapName)).contains("4")) {
                            player.getInventory().removeItem(new ItemStack(Material.DIAMOND, prot1Cost4v4));
                            setProt1(team);
                            RelTeamEnchant.teamEnchantListProt.put(mapName, new String[]{teamName, "1"});
                            String mess = messLevelUpProt1.replace("{player}",player.getName());
                            player.sendMessage(t(mess)); 
                        } else if (clickedItemName.equals(MainConfigHandler.teamEnchItemName_Prot3) && player.getInventory().contains(Material.DIAMOND, prot3Cost4v4) && !Arrays.asList(RelTeamEnchant.teamEnchantListProt.get(mapName)).contains("3") && !Arrays.asList(RelTeamEnchant.teamEnchantListProt.get(mapName)).contains("4")) {
                            player.getInventory().removeItem(new ItemStack(Material.DIAMOND, prot3Cost4v4));
                            setProt3(team);
                            RelTeamEnchant.teamEnchantListProt.put(mapName, new String[]{teamName, "3"});
                            String mess = messLevelUpProt3.replace("{player}",player.getName());
                            player.sendMessage(t(mess)); 
                        } else if (clickedItemName.equals(MainConfigHandler.teamEnchItemName_Prot4) && player.getInventory().contains(Material.DIAMOND, prot4Cost4v4) && !Arrays.asList(RelTeamEnchant.teamEnchantListProt.get(mapName)).contains("4") ) {
                            player.getInventory().removeItem(new ItemStack(Material.DIAMOND, prot4Cost4v4));
                            setProt4(team);
                            RelTeamEnchant.teamEnchantListProt.put(mapName, new String[]{teamName, "4"});
                            String mess = messLevelUpProt4.replace("{player}",player.getName());
                            player.sendMessage(t(mess)); 
                        } else if (clickedItemName.equals(teamEnchItemName_Sharp1) && player.getInventory().contains(Material.DIAMOND, sharp1Cost4v4) && !Arrays.asList(RelTeamEnchant.teamEnchantListSword.get(mapName)).contains("1")) {
                            player.getInventory().removeItem(new ItemStack(Material.DIAMOND, sharp1Cost4v4));
                            setSharp1(team);
                            RelTeamEnchant.teamEnchantListSword.put(mapName, new String[]{teamName, "1"});
                            String mess = messLevelUpSharp1.replace("{player}",player.getName());
                            player.sendMessage(t(mess)); 
                        } else{
                            String mess = messLevelUpFailed.replace("{player}",player.getName());
                            player.sendMessage(t(mess));
                        }

                    }
                }
            }
        }

    }
}
