package github.tsffish.bedwarskit.listener.bedwarsrel;

import github.tsffish.bedwarskit.config.MainConfigHandler;
import github.tsffish.bedwarskit.util.RelShopLevelUp;
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
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

import static github.tsffish.bedwarskit.config.MainConfigHandler.*;
import static github.tsffish.bedwarskit.util.ColorString.t;

public class RelClickInventory implements Listener {
    @EventHandler
    public void on(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();
        if (event.getCurrentItem() != null) {
            Material currentItemType = event.getCurrentItem().getType();
            if (MainConfigHandler.antiDrop && currentItemType != null && currentItemType != Material.AIR && NoMoveList.contains(event.getCurrentItem().getType())) {
                event.setCancelled(true);
            }
        }

        if (event.getCurrentItem() != null && event.getCurrentItem().getType() != null && event.getCurrentItem().getType() == MainConfigHandler.LevelupItem && event.getInventory().getName().contains(shopItem)) {
            String worldName = event.getWhoClicked().getWorld().getName();
            if (worldName.contains(MainConfigHandler.rushWorld)) {
                if (worldName.contains(MainConfigHandler.rushWorld2v2)) {
                    event.setCancelled(true);
                    player.openInventory(RelShopLevelUp.i2);
                } else if (worldName.contains(MainConfigHandler.rushWorld4v4)) {
                    event.setCancelled(true);
                    player.openInventory(RelShopLevelUp.i4);
                }
            }

        }

        if (player.getGameMode() == GameMode.CREATIVE){
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
                            RelEnchant.setProt2(team);
                            RelTeamEnchant.teamEnchantListProt.put(mapName, new String[]{teamName, "2"});
                            String mess = messLevelUpProt2.replace("{player}",player.getName());
                            player.sendMessage(t(mess)); // 替换为您的成功提示信息
                        } else if (clickedItemName.equals(MainConfigHandler.teamEnchItemName_Prot1) && player.getInventory().contains(Material.DIAMOND, prot1Cost2v2) && !Arrays.asList(RelTeamEnchant.teamEnchantListProt.get(mapName)).contains("1") && !Arrays.asList(RelTeamEnchant.teamEnchantListProt.get(mapName)).contains("2") && !Arrays.asList(RelTeamEnchant.teamEnchantListProt.get(mapName)).contains("3") && !Arrays.asList(RelTeamEnchant.teamEnchantListProt.get(mapName)).contains("4")) {
                            player.getInventory().removeItem(new ItemStack(Material.DIAMOND, prot1Cost2v2));
                            RelEnchant.setProt1(team);
                            RelTeamEnchant.teamEnchantListProt.put(mapName, new String[]{teamName, "1"});
                            String mess = messLevelUpProt1.replace("{player}",player.getName());
                            player.sendMessage(t(mess)); // 替换为您的成功提示信息
                        } else if (clickedItemName.equals(MainConfigHandler.teamEnchItemName_Prot3) && player.getInventory().contains(Material.DIAMOND, prot3Cost2v2) && !Arrays.asList(RelTeamEnchant.teamEnchantListProt.get(mapName)).contains("3") && !Arrays.asList(RelTeamEnchant.teamEnchantListProt.get(mapName)).contains("4")) {
                            player.getInventory().removeItem(new ItemStack(Material.DIAMOND, prot3Cost2v2));
                            RelEnchant.setProt3(team);
                            RelTeamEnchant.teamEnchantListProt.put(mapName, new String[]{teamName, "3"});
                            String mess = messLevelUpProt3.replace("{player}",player.getName());
                            player.sendMessage(t(mess)); // 替换为您的成功提示信息
                        } else if (clickedItemName.equals(MainConfigHandler.teamEnchItemName_Prot4) && player.getInventory().contains(Material.DIAMOND, prot4Cost2v2) && !Arrays.asList(RelTeamEnchant.teamEnchantListProt.get(mapName)).contains("4")) {
                            player.getInventory().removeItem(new ItemStack(Material.DIAMOND, prot4Cost2v2));
                            RelEnchant.setProt4(team);
                            RelTeamEnchant.teamEnchantListProt.put(mapName, new String[]{teamName, "4"});
                            String mess = messLevelUpProt4.replace("{player}",player.getName());
                            player.sendMessage(t(mess)); // 替换为您的成功提示信息
                        } else if (clickedItemName.equals(MainConfigHandler.teamEnchItemName_Sharp1) && player.getInventory().contains(Material.DIAMOND, sharp1Cost2v2) && !Arrays.asList(RelTeamEnchant.teamEnchantListSword.get(mapName)).contains("1")) {
                            player.getInventory().removeItem(new ItemStack(Material.DIAMOND, sharp1Cost2v2));
                            RelEnchant.setSharp1(team);
                            RelTeamEnchant.teamEnchantListSword.put(mapName, new String[]{teamName, "1"});
                            String mess = messLevelUpSharp1.replace("{player}",player.getName());
                            player.sendMessage(t(mess)); // 替换为您的成功提示信息
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
                            RelEnchant.setProt2(team);
                            RelTeamEnchant.teamEnchantListProt.put(mapName, new String[]{teamName, "2"});
                            String mess = messLevelUpProt2.replace("{player}",player.getName());
                            player.sendMessage(t(mess)); // 替换为您的成功提示信息
                        } else if (clickedItemName.equals(MainConfigHandler.teamEnchItemName_Prot1) && player.getInventory().contains(Material.DIAMOND, prot1Cost4v4) && !Arrays.asList(RelTeamEnchant.teamEnchantListProt.get(mapName)).contains("1") && !Arrays.asList(RelTeamEnchant.teamEnchantListProt.get(mapName)).contains("2") && !Arrays.asList(RelTeamEnchant.teamEnchantListProt.get(mapName)).contains("3") && !Arrays.asList(RelTeamEnchant.teamEnchantListProt.get(mapName)).contains("4")) {
                            player.getInventory().removeItem(new ItemStack(Material.DIAMOND, prot1Cost4v4));
                            RelEnchant.setProt1(team);
                            RelTeamEnchant.teamEnchantListProt.put(mapName, new String[]{teamName, "1"});
                            String mess = messLevelUpProt1.replace("{player}",player.getName());
                            player.sendMessage(t(mess)); // 替换为您的成功提示信息
                        } else if (clickedItemName.equals(MainConfigHandler.teamEnchItemName_Prot3) && player.getInventory().contains(Material.DIAMOND, prot3Cost4v4) && !Arrays.asList(RelTeamEnchant.teamEnchantListProt.get(mapName)).contains("3") && !Arrays.asList(RelTeamEnchant.teamEnchantListProt.get(mapName)).contains("4")) {
                            player.getInventory().removeItem(new ItemStack(Material.DIAMOND, prot3Cost4v4));
                            RelEnchant.setProt3(team);
                            RelTeamEnchant.teamEnchantListProt.put(mapName, new String[]{teamName, "3"});
                            String mess = messLevelUpProt3.replace("{player}",player.getName());
                            player.sendMessage(t(mess)); // 替换为您的成功提示信息
                        } else if (clickedItemName.equals(MainConfigHandler.teamEnchItemName_Prot4) && player.getInventory().contains(Material.DIAMOND, prot4Cost4v4) && !Arrays.asList(RelTeamEnchant.teamEnchantListProt.get(mapName)).contains("4") ) {
                            player.getInventory().removeItem(new ItemStack(Material.DIAMOND, prot4Cost4v4));
                            RelEnchant.setProt4(team);
                            RelTeamEnchant.teamEnchantListProt.put(mapName, new String[]{teamName, "4"});
                            String mess = messLevelUpProt4.replace("{player}",player.getName());
                            player.sendMessage(t(mess)); // 替换为您的成功提示信息
                        } else if (clickedItemName.equals(teamEnchItemName_Sharp1) && player.getInventory().contains(Material.DIAMOND, sharp1Cost4v4) && !Arrays.asList(RelTeamEnchant.teamEnchantListSword.get(mapName)).contains("1")) {
                            player.getInventory().removeItem(new ItemStack(Material.DIAMOND, sharp1Cost4v4));
                            RelEnchant.setSharp1(team);
                            RelTeamEnchant.teamEnchantListSword.put(mapName, new String[]{teamName, "1"});
                            String mess = messLevelUpSharp1.replace("{player}",player.getName());
                            player.sendMessage(t(mess)); // 替换为您的成功提示信息
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
