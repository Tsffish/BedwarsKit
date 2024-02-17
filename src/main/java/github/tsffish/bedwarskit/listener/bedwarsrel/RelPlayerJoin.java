package github.tsffish.bedwarskit.listener.bedwarsrel;

import github.tsffish.bedwarskit.config.kit.KitConfigHandler;
import io.github.bedwarsrel.events.BedwarsPlayerJoinedEvent;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameState;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import static github.tsffish.bedwarskit.Main.isLastestVersion;
import static github.tsffish.bedwarskit.config.kit.KitConfigHandler.kitDefault;
import static github.tsffish.bedwarskit.config.lang.LangConfigHandler.update_tip;
import static github.tsffish.bedwarskit.config.main.MainConfigHandler.*;
import static github.tsffish.bedwarskit.util.RelArmorList.*;
import static github.tsffish.bedwarskit.util.RelCheckSword.checkInvHasSword;
import static github.tsffish.bedwarskit.util.RelCurrentStat.*;
import static github.tsffish.bedwarskit.util.RelIsCheckingPlayer.isInCheckList;
import static github.tsffish.bedwarskit.util.RelIsCheckingPlayer.joinCheckList;
import static github.tsffish.bedwarskit.util.RelPlayerKit.getPlayerKit;
import static github.tsffish.bedwarskit.util.RelPlayerKit.setPlayerKit;
import static github.tsffish.bedwarskit.util.RelPlayerTab.sendTab;
import static github.tsffish.bedwarskit.util.RelScoreBoard.updateScoreBoard;
import static github.tsffish.bedwarskit.util.kit.MenuItem.kitMenuItem;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;
import static github.tsffish.bedwarskit.util.teamshop.RelCheckEffect.checkEffectHaste;
import static github.tsffish.bedwarskit.util.teamshop.RelCheckEffect.checkEffectHeal;
import static github.tsffish.bedwarskit.util.teamshop.RelCheckEnchant.checkEnchantArmor;
import static github.tsffish.bedwarskit.util.teamshop.RelCheckEnchant.checkEnchantSword;

public class RelPlayerJoin implements Listener {
    private static Plugin plugin = github.tsffish.bedwarskit.Main.getPlugin(github.tsffish.bedwarskit.Main.class);
    static ItemStack playAgainItem;
    static final ItemStack bot = new ItemStack(Material.GLASS_BOTTLE);
    static final ItemStack bed = new ItemStack(Material.BED);
    static final ItemStack chain1 = new ItemStack(Material.CHAINMAIL_LEGGINGS);
    static final ItemStack chain2 = new ItemStack(Material.CHAINMAIL_BOOTS);
    static final ItemStack iron1 = new ItemStack(Material.IRON_LEGGINGS);
    static final ItemStack iron2 = new ItemStack(Material.IRON_BOOTS);
    static final ItemStack dm1 = new ItemStack(Material.DIAMOND_LEGGINGS);
    static final ItemStack dm2 = new ItemStack(Material.DIAMOND_BOOTS);

    @EventHandler
    public void on(final BedwarsPlayerJoinedEvent event) {
        Game game = event.getGame();
        Player player = event.getPlayer();
        if (game == null || player == null || !player.isOnline()) {
            return;
        }
        String playerName = player.getName();
        if (!game.getPlayers().contains(player)) {
            return;
        }
        if (getPlayerKit(playerName) == null) {
            setPlayerKit(playerName, kitDefault);
        }

        playAgainItem = new ItemStack(dieOutGameItem_playAgain_ItemType, 1);
        ItemMeta playAgainItemMeta = playAgainItem.getItemMeta();
        playAgainItemMeta.setDisplayName(dieOutGameItem_playAgain_ItemName);
        playAgainItem.setItemMeta(playAgainItemMeta);

        setDefaultPlayerStat(player.getName());

        if (game.getState() == GameState.RUNNING && game.getPlayerTeam(player) != null && game.getPlayerTeam(player).getHeadTarget() != null && game.getPlayerTeam(player).getHeadTarget().getType() != Material.BED) {
            addPlayerIsOut(playerName);
        }

        if (!player.getInventory().contains(kitMenuItem)) {
            player.getInventory().addItem(kitMenuItem);
        }

        World world = player.getWorld();
        String worldName = world.getName();
        if (!isInCheckList(worldName)) {
            new BukkitRunnable() {
                public void run() {
                    joinCheckList(worldName);
                    if (tab) {
                        sendTab(player);
                    }
                    if (customScoreboard) {
                        updateScoreBoard(player);
                    }
                    if (game.getState() == GameState.WAITING) {
                        if (KitConfigHandler.kitenable) {
                            if (KitConfigHandler.kitMenuItemGive) {

                                for (Player player : game.getPlayers()) {
                                    //checkLobbyItem(player);
                                    PlayerInventory inventory = player.getInventory();
                                    if (!inventory.contains(kitMenuItem)) {
                                        inventory.addItem(kitMenuItem);
                                    }
                                }
                            }
                        }
                    } else if (game.getState() == GameState.RUNNING) {
                        for (Player player : game.getPlayers()) {
                            if (player != null && player.isOnline()) {
                                String playerName = player.getPlayer().getName();

                                PlayerInventory pi = player.getInventory();

                                ItemStack chain = new ItemStack(chainPriceType, chainPrice);
                                ItemStack iron = new ItemStack(ironPriceType, ironPrice);
                                ItemStack diamond = new ItemStack(diamondPriceType, diamondPrice);

                                if (PlayerisOut(playerName)) {
                                    if (dieOutGameItem_playAgain) {

                                        if (!pi.contains(playAgainItem)) {
                                            pi.addItem(playAgainItem);
                                        }
                                    }
                                } else {

                                    if (player.getHealth() >= 0) {

                                        checkInvHasSword(player);

                                        checkTeamLevelUp(game);

                                        if (cleanBottle) {
                                            if (pi.contains(bot)) {
                                                pi.remove(bot);
                                            }
                                        }

                                        if (cleanBed) {
                                            if (pi.contains(bed)) {
                                                pi.remove(bed);
                                            }
                                        }

                                        if (pi.contains(upToChainArmor)) {
                                            if (armorChain.contains(playerName) || armorIron.contains(playerName) || armorDiamond.contains(playerName)) {
                                                pi.remove(upToChainArmor);
                                                pi.addItem(chain);
                                                return;
                                            }

                                            armorChain.add(playerName);
                                            pi.remove(upToChainArmor);
                                            player.getInventory().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
                                            player.getInventory().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
                                        }

                                        if (pi.contains(upToIronArmor)) {
                                            if (armorIron.contains(playerName) || armorDiamond.contains(playerName)) {
                                                pi.remove(upToIronArmor);
                                                pi.addItem(iron);
                                                return;
                                            }

                                            armorIron.add(playerName);
                                            armorChain.remove(playerName);
                                            armorDiamond.remove(playerName);
                                            pi.remove(upToIronArmor);
                                            player.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
                                            player.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
                                        }

                                        if (pi.contains(upToDiamondArmor)) {
                                            if (armorDiamond.contains(playerName)) {
                                                pi.remove(upToDiamondArmor);
                                                pi.addItem(diamond);
                                                return;
                                            }

                                            armorDiamond.add(playerName);
                                            armorIron.remove(playerName);
                                            armorChain.remove(playerName);
                                            pi.remove(upToDiamondArmor);
                                            player.getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
                                            player.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
                                        }
                                    }
                                }
                            }
                            }
                    }
                }
                        }.runTaskTimer(plugin, 0L, 19L);
        }
    }
    @EventHandler
    public void on(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player == null || !player.isOnline()) {
            return;
        }
        if (player.isOp()) {
            if (!isLastestVersion()) {
                if (update_reportOp) {
                    if (update_tip != null) {
                        for (String list : update_tip) {
                            player.sendMessage(t(list));
                        }
                    }
                }
            }
        }

    }
                void checkTeamLevelUp(Game game) {
                    checkEffectHaste(game);
                    checkEffectHeal(game);

                    checkEnchantArmor(game);
                    checkEnchantSword(game);
                }
            }
