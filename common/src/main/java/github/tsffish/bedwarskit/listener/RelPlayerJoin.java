package github.tsffish.bedwarskit.listener;

import github.tsffish.bedwarskit.BedwarsKit;
import github.tsffish.bedwarskit.config.kit.KitConfigHandler;
import github.tsffish.bedwarskit.util.GetMaterial;
import github.tsffish.bedwarskit.util.player.SoundPlayer;
import github.tsffish.bedwarskit.util.teamshop.haste.EffectHaste;
import github.tsffish.bedwarskit.util.teamshop.heal.EffectHeal;
import github.tsffish.bedwarskit.util.teamshop.prot.EnchatProt;
import github.tsffish.bedwarskit.util.teamshop.sharp.EnchatSharp;
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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;
import java.util.UUID;

import static github.tsffish.bedwarskit.config.lang.LangConfigHandler.update_tip;
import static github.tsffish.bedwarskit.config.main.MainConfigHandler.*;
import static github.tsffish.bedwarskit.config.scb.ScbConfigHandler.customScoreboard;
import static github.tsffish.bedwarskit.util.PluginState.isDebug;
import static github.tsffish.bedwarskit.util.PluginState.isLastestVersion;
import static github.tsffish.bedwarskit.util.WorldCheckList.isInCheckList;
import static github.tsffish.bedwarskit.util.WorldCheckList.joinCheckList;
import static github.tsffish.bedwarskit.util.kit.KitOpenItem.kitMenuItem;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;
import static github.tsffish.bedwarskit.util.player.PlayerSender.sendMessage;
import static github.tsffish.bedwarskit.util.player.PlayerSender.sendTitle;
import static github.tsffish.bedwarskit.util.player.RelArmorList.*;
import static github.tsffish.bedwarskit.util.player.RelCurrentStat.*;
import static github.tsffish.bedwarskit.util.player.RelPlayerKit.getPlayerKit;
import static github.tsffish.bedwarskit.util.player.RelPlayerKit.setPlayerKit;
import static github.tsffish.bedwarskit.util.player.SendActionBar.sendActionBar;
import static github.tsffish.bedwarskit.util.player.SendTab.sendTab;
import static github.tsffish.bedwarskit.util.scb.RelScoreBoard.updateScoreBoard;
import static github.tsffish.bedwarskit.util.task.TaskLeaveCheck.leaveList;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class RelPlayerJoin implements Listener {
    private static final BedwarsKit plugin = BedwarsKit.getInstance();
    private static final ItemStack bot = new ItemStack(Material.GLASS_BOTTLE);
    private static final ItemStack chain1 = new ItemStack(Material.CHAINMAIL_LEGGINGS);
    private static final ItemStack chain2 = new ItemStack(Material.CHAINMAIL_BOOTS);
    private static final ItemStack iron1 = new ItemStack(Material.IRON_LEGGINGS);
    private static final ItemStack iron2 = new ItemStack(Material.IRON_BOOTS);
    private static final ItemStack dm1 = new ItemStack(Material.DIAMOND_LEGGINGS);
    private static final ItemStack dm2 = new ItemStack(Material.DIAMOND_BOOTS);
    private static final GameMode spectator = GameMode.SPECTATOR;
    private static final GameState running = GameState.RUNNING;
    private static final GameState waiting = GameState.WAITING;
    private static final PotionEffectType invsType = PotionEffectType.INVISIBILITY;
    private static final PotionEffect invs = new PotionEffect(invsType, 9999, 0);
    private static ItemStack playAgainItem;
    private static Material bed_blockType = GetMaterial.BED_BLOCK();
    private static ItemStack bed_item = new ItemStack(GetMaterial.BED_ITEM());
    void check(Game game) {
        EffectHaste.check(game);
        EffectHeal.check(game);
        EnchatProt.check(game);
        EnchatSharp.check(game);
    }

    void playerSoundSucc(Player player) {
        SoundPlayer.CHICKEN_EGG_POP(player, 1);
    }

    String replaceString(
            String text,
            String teamColor,
            String teamName
    ) {
        return text
                .replace("{teamColor}", teamColor)
                .replace("{teamName}", teamName)
                ;
    }

    @EventHandler
    public void on(final BedwarsPlayerJoinedEvent event) {
        if (event.getGame() == null) {
            return;
        }
        Game game = event.getGame();

        if (event.getPlayer() == null) {
            return;
        }
        Player player = event.getPlayer();

        if (!player.isOnline()) {
            return;
        }
        UUID playerUUID = player.getUniqueId();

        if (!game.getPlayers().contains(player)) {
            return;
        }
        if (getPlayerKit(playerUUID) == null) {
            setPlayerKit(playerUUID, KitConfigHandler.kitDefault);
        }

        playAgainItem = new ItemStack(dieOutGameItem_playAgain_ItemType, 1);
        ItemMeta playAgainItemMeta = playAgainItem.getItemMeta();
        playAgainItemMeta.setDisplayName(dieOutGameItem_playAgain_ItemName);
        playAgainItem.setItemMeta(playAgainItemMeta);

        setDefaultPlayerStat(playerUUID);

        if (bed_blockType != null) {
            if (
                    game.getState() == running
                            && game.getPlayerTeam(player) != null
                            && game.getPlayerTeam(player).getHeadTarget() != null
                            && game.getPlayerTeam(player).getHeadTarget().getType() != bed_blockType
            ) {
                addPlayerIsOut(playerUUID);
                game.addProtection(player);
            }
        }
        game.getPlayers().forEach(players -> {
            SoundPlayer.CHICKEN_EGG_POP(players, 2);
        });

        World world = player.getWorld();
        String worldName = world.getName();

        if (!isInCheckList(worldName)) {
            joinCheckList(worldName);
            new BukkitRunnable() {
                public void run() {

                    if (!isInCheckList(worldName)) {
                        cancel();
                    }

                    if (world.getPlayers().isEmpty()) {
                        leaveList(worldName);
                    }
                    if (tab) {
                        for (Player list : game.getPlayers()) {
                            sendTab(list);
                        }
                    }

                    if (customScoreboard) {
                        updateScoreBoard(game.getName());
                    }
                    if (game.getState() == waiting) {

                        if (KitConfigHandler.kitenable) {

                            if (KitConfigHandler.kitMenuItemGive) {

                                for (Player player : game.getPlayers()) {
                                    PlayerInventory pi = player.getInventory();
                                    if (!pi.contains(kitMenuItem)) {
                                        pi.addItem(kitMenuItem);
                                    }

                                }
                            }
                        }

                    } else if (game.getState() == running) {

                        ItemStack chain = new ItemStack(chainPriceType, chainPrice);
                        ItemStack iron = new ItemStack(ironPriceType, ironPrice);
                        ItemStack diamond = new ItemStack(diamondPriceType, diamondPrice);

                        for (Player player : game.getPlayers()) {
                            if (player != null && player.isOnline()) {

                                if (isDebug()) {
                                    String youHaveProt;
                                    String YouAreSpe;
                                    String gameMode = player.getGameMode().toString();
                                    if (game.getRespawnProtections().containsKey(player)) {
                                        youHaveProt = "You has prot";
                                    } else {
                                        youHaveProt = "You not has prot";
                                    }
                                    if (game.isSpectator(player)) {
                                        YouAreSpe = "You are Spe";
                                    } else {
                                        YouAreSpe = "You are not Spe";
                                    }

                                    sendActionBar(player, youHaveProt + " | " + YouAreSpe + " | " + gameMode);
                                }
                                PlayerInventory pi = player.getInventory();

                                if (getPlayerisOut(playerUUID) && game.isSpectator(player)) {
                                    if (dieOutGameItem_playAgain) {
                                        if (!pi.contains(playAgainItem)) {
                                            pi.setItem(
                                                    dieOutGameItem_playAgain_ItemSlot,
                                                    playAgainItem);
                                        }
                                    }
                                    player.setGameMode(spectator);

                                    player.addPotionEffect(invs);
                                    player.setAllowFlight(true);
                                } else {
                                    if (player.getHealth() >= 0 && !player.isDead()) {

                                        if (cleanBottle && pi.contains(bot)) {
                                            pi.remove(bot);
                                        }
                                        if (cleanBed && pi.contains(bed_item)) {
                                            pi.remove(bed_item);
                                        }


                                        if (pi.contains(upToDiamondArmor)) {
                                            if (hasArmorDiamond(playerUUID)) {
                                                pi.remove(upToDiamondArmor);
                                                pi.addItem(diamond);
                                                return;
                                            }

                                            addArmorDiamond(playerUUID);
                                            removeArmorIron(playerUUID);
                                            removeArmorChain(playerUUID);

                                            pi.remove(upToDiamondArmor);
                                            pi.setLeggings(dm1);
                                            pi.setBoots(dm2);
                                        }

                                        if (pi.contains(upToIronArmor)) {
                                            if (hasArmorIron(playerUUID)
                                                    || hasArmorDiamond(playerUUID)) {
                                                pi.remove(upToIronArmor);
                                                pi.addItem(iron);
                                                return;
                                            }

                                            removeArmorChain(playerUUID);
                                            addArmorIron(playerUUID);

                                            pi.remove(upToIronArmor);
                                            pi.setLeggings(iron1);
                                            pi.setBoots(iron2);
                                        }

                                        if (pi.contains(upToChainArmor)) {
                                            if (hasArmorChain(playerUUID)
                                                    || hasArmorIron(playerUUID)
                                                    || hasArmorDiamond(playerUUID)) {
                                                pi.remove(upToChainArmor);
                                                pi.addItem(chain);
                                                return;
                                            }

                                            addArmorChain(playerUUID);
                                            pi.remove(upToChainArmor);
                                            pi.setLeggings(chain1);
                                            pi.setBoots(chain2);
                                        }
                                    }
                                }
                            }
                        }

                        check(game);
                    }
                }
            }.runTaskTimer(plugin, 0L, 20L);
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

    @EventHandler
    public void on(BedwarsPlayerJoinTeamEvent event) {
        Player player = event.getPlayer();
        if (player == null || !player.isOnline()) return;
        if (BedwarsRel.getInstance() == null) return;

        String teamColor = event.getTeam().getChatColor().toString();
        String teamName = event.getTeam().getName();

        playerSoundSucc(player);
        if (!Objects.equals(lobbyjoinTeamMess_chat, "")) {
            sendMessage(player, replaceString(lobbyjoinTeamMess_chat,
                    teamColor, teamName));
        }
        if (!Objects.equals(lobbyjoinTeamMess_title, "")) {
            String titleReal = t(replaceString(lobbyjoinTeamMess_title,
                    teamColor, teamName));
            if (!Objects.equals(lobbyjoinTeamMess_subtitle, "")) {
                String subtitleReal = t(replaceString(lobbyjoinTeamMess_subtitle,
                        teamColor, teamName));

                sendTitle(player, titleReal, subtitleReal);
            }
        } else if (!Objects.equals(lobbyjoinTeamMess_subtitle, "")) {
            String titleReal = " ";
            String subtitleReal = t(replaceString(lobbyjoinTeamMess_subtitle,
                    teamColor, teamName));

            sendTitle(player, titleReal, subtitleReal);
        }
        if (!Objects.equals(lobbyjoinTeamMess_actionbar, "")) {
            sendActionBar(player, t(replaceString(lobbyjoinTeamMess_actionbar,
                    teamColor, teamName)));
        }
    }
}
