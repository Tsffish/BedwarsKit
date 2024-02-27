package github.tsffish.bedwarskit.listener;

import github.tsffish.bedwarskit.Main;
import github.tsffish.bedwarskit.config.kit.KitConfigHandler;
import github.tsffish.bedwarskit.config.main.MainConfigHandler;
import github.tsffish.bedwarskit.util.RelCurrentStat;
import github.tsffish.bedwarskit.util.misc.SoundPlayer;
import github.tsffish.bedwarskit.util.teamshop.check.EffectHaste;
import github.tsffish.bedwarskit.util.teamshop.check.EffectHeal;
import github.tsffish.bedwarskit.util.teamshop.check.EnchatProt;
import github.tsffish.bedwarskit.util.teamshop.check.EnchatSharp;
import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.events.BedwarsPlayerJoinTeamEvent;
import io.github.bedwarsrel.events.BedwarsPlayerJoinedEvent;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameState;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;
import java.util.UUID;

import static github.tsffish.bedwarskit.Main.isDebug;
import static github.tsffish.bedwarskit.Main.isLastestVersion;
import static github.tsffish.bedwarskit.config.kit.KitConfigHandler.kitDefault;
import static github.tsffish.bedwarskit.config.lang.LangConfigHandler.update_tip;
import static github.tsffish.bedwarskit.config.main.MainConfigHandler.*;
import static github.tsffish.bedwarskit.util.RelArmorList.*;
import static github.tsffish.bedwarskit.util.RelCurrentStat.*;
import static github.tsffish.bedwarskit.util.RelIsCheckingPlayer.isInCheckList;
import static github.tsffish.bedwarskit.util.RelIsCheckingPlayer.joinCheckList;
import static github.tsffish.bedwarskit.util.RelPlayerKit.getPlayerKit;
import static github.tsffish.bedwarskit.util.RelPlayerKit.setPlayerKit;
import static github.tsffish.bedwarskit.util.RelScoreBoard.updateScoreBoard;
import static github.tsffish.bedwarskit.util.kit.KitOpenItem.kitMenuItem;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;
import static github.tsffish.bedwarskit.util.misc.MessSender.l;
import static github.tsffish.bedwarskit.util.misc.SendActionBar.sendActionBar;
import static github.tsffish.bedwarskit.util.misc.SendTab.sendTab;

public class RelPlayerJoin implements Listener {
    private static final Main plugin = Main.getInstance();
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
        if (event.getGame() == null) return;
        Game game = event.getGame();
        if (event.getPlayer() == null) {
            return;
        }
        Player player = event.getPlayer();
        if (!player.isOnline()) {
            return;
        }
        String playerName = player.getName();
        UUID uuid = player.getUniqueId();

        if (!game.getPlayers().contains(player)) {
            return;
        }
        if (getPlayerKit(uuid) == null) {
            setPlayerKit(uuid, kitDefault);
        }

        playAgainItem = new ItemStack(dieOutGameItem_playAgain_ItemType, 1);
        ItemMeta playAgainItemMeta = playAgainItem.getItemMeta();
        playAgainItemMeta.setDisplayName(dieOutGameItem_playAgain_ItemName);
        playAgainItem.setItemMeta(playAgainItemMeta);

        setDefaultPlayerStat(uuid);

        if (game.getState() == GameState.RUNNING
                && game.getPlayerTeam(player) != null
                && game.getPlayerTeam(player).getHeadTarget() != null
                && game.getPlayerTeam(player).getHeadTarget().getType() != Material.BED) {
            addPlayerIsOut(uuid);
        }

        if (!player.getInventory().contains(kitMenuItem)) {
            player.getInventory().addItem(kitMenuItem);
        }

        World world = player.getWorld();
        String worldName = world.getName();

        if (!isInCheckList(worldName)) {
            joinCheckList(worldName);
            if (isDebug()) {
                l("joinCheckList: " + worldName);
            }
            new BukkitRunnable() {
                public void run() {
                    // Sync的一瞬间会判断为空，无法正常应用
                    //if (world.getPlayers().isEmpty()){
                    //    leaveCheckList(worldName);
                    //    if (isDebug()){
                    //        l("leaveCheckList: " + worldName);
                    //    }
                    //    cancel();
                    //}

                    if (tab) {
                        sendTab(player);
                        if (isDebug()) {
                            l("sendTab: " + playerName);
                        }
                    }
                    if (customScoreboard) {
                        updateScoreBoard(game);
                    }
                    if (game.getState() == GameState.WAITING) {

                        if (KitConfigHandler.kitenable) {
                            if (KitConfigHandler.kitMenuItemGive) {

                                for (Player player : game.getPlayers()) {
                                    PlayerInventory inventory = player.getInventory();
                                    if (!inventory.contains(kitMenuItem)) {
                                        inventory.addItem(kitMenuItem);
                                    }
                                }
                            }
                        }

                        if (player.getGameMode() == GameMode.SPECTATOR) {
                            player.setGameMode(GameMode.SURVIVAL);
                        }


                    } else if (game.getState() == GameState.RUNNING) {
                        for (Player player : game.getPlayers()) {
                            if (player != null && player.isOnline()) {

                                PlayerInventory pi = player.getInventory();

                                ItemStack chain = new ItemStack(chainPriceType, chainPrice);
                                ItemStack iron = new ItemStack(ironPriceType, ironPrice);
                                ItemStack diamond = new ItemStack(diamondPriceType, diamondPrice);

                                if (PlayerisOut(uuid)) {
                                    if (player.isOnline()
                                            && !player.isDead()
                                            && player.getGameMode() != GameMode.SURVIVAL) {
                                        if (dieOutGameItem_playAgain) {

                                            if (!pi.contains(playAgainItem)) {
                                                pi.setItem(dieOutGameItem_playAgain_ItemSlot,
                                                        playAgainItem);
                                            }
                                        }

                                    }
                                } else {

                                    if (player.getHealth() >= 0 && !player.isDead()) {

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


                                        if (isDebug()) {
                                            if (RelCurrentStat.PlayerisOut(uuid)) {
                                                sendActionBar(player, "You are in PlayerisOut");
                                            } else {
                                                sendActionBar(player, "You are Not in PlayerisOut");
                                            }
                                        }


                                        if (pi.contains(upToChainArmor)) {
                                            if (hasArmorChain(uuid)
                                                    || hasArmorIron(uuid)
                                                    || hasArmorDiamond(uuid)) {
                                                pi.remove(upToChainArmor);
                                                pi.addItem(chain);
                                                return;
                                            }

                                            addArmorChain(uuid);
                                            pi.remove(upToChainArmor);
                                            pi.setLeggings(chain1);
                                            pi.setBoots(chain2);
                                        }

                                        if (pi.contains(upToIronArmor)) {
                                            if (hasArmorIron(uuid)
                                                    || hasArmorDiamond(uuid)) {
                                                pi.remove(upToIronArmor);
                                                pi.addItem(iron);
                                                return;
                                            }

                                            removeArmorChain(uuid);
                                            addArmorIron(uuid);

                                            pi.remove(upToIronArmor);
                                            pi.setLeggings(iron1);
                                            pi.setBoots(iron2);
                                        }

                                        if (pi.contains(upToDiamondArmor)) {
                                            if (hasArmorDiamond(uuid)) {
                                                pi.remove(upToDiamondArmor);
                                                pi.addItem(diamond);
                                                return;
                                            }

                                            addArmorDiamond(uuid);
                                            removeArmorIron(uuid);
                                            removeArmorChain(uuid);

                                            pi.remove(upToDiamondArmor);
                                            pi.setLeggings(dm1);
                                            pi.setBoots(dm2);
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
    public void on(final PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player == null || !player.isOnline()) {
            return;
        }
        if (player.isOp()) {
            if (!isLastestVersion()) {
                if (update_reportOp) {
                    if (update_tip != null && !update_tip.isEmpty()) {
                        for (String list : update_tip) {
                            player.sendMessage(t(list));
                        }
                    }
                }
            }
        }
    }
               void checkTeamLevelUp(Game game) {
                    EffectHaste.check(game);
                    EffectHeal.check(game);
                    EnchatSharp.check(game);
                    EnchatProt.check(game);
                }





    private static void playerSoundSucc(Player player){
        SoundPlayer.CHICKEN_EGG_POP(player, 1);
    }
    @EventHandler
    public void on(BedwarsPlayerJoinTeamEvent event){
        Player player = event.getPlayer();
        if (player == null || !player.isOnline()) return;
        if (BedwarsRel.getInstance() == null) return;

        String teamColor = event.getTeam().getChatColor().toString();
        String teamName = event.getTeam().getName();

        playerSoundSucc(player);
        if (!MainConfigHandler.lobbyjoinTeamMess_chat.isEmpty()) {
            player.sendMessage(MainConfigHandler.lobbyjoinTeamMess_chat
                    .replace("{teamColor}",teamColor)
                    .replace("{teamName}",teamName));
        }
        if (!Objects.equals(MainConfigHandler.lobbyjoinTeamMess_title, "")) {
            String titleReal = t(MainConfigHandler.lobbyjoinTeamMess_title
                    .replace("{teamColor}",teamColor)
                    .replace("{teamName}",teamName));
            if (!Objects.equals(MainConfigHandler.lobbyjoinTeamMess_subtitle, "")) {
                String subtitleReal = t(MainConfigHandler.lobbyjoinTeamMess_subtitle
                        .replace("{teamColor}",teamColor)
                        .replace("{teamName}",teamName));

                player.sendTitle(titleReal, subtitleReal);
            }
        } else if (!Objects.equals(MainConfigHandler.lobbyjoinTeamMess_subtitle, "")) {
            String titleReal = " ";
            String subtitleReal = t(MainConfigHandler.lobbyjoinTeamMess_subtitle
                    .replace("{teamColor}",teamColor)
                    .replace("{teamName}",teamName));

            player.sendTitle(titleReal, subtitleReal);
        }
        if (!MainConfigHandler.lobbyjoinTeamMess_actionbar.isEmpty()) {
            sendActionBar(player, t(MainConfigHandler.lobbyjoinTeamMess_actionbar
                    .replace("{teamColor}",teamColor)
                    .replace("{teamName}",teamName)));
        }


    }
}