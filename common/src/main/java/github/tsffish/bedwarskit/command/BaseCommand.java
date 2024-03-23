package github.tsffish.bedwarskit.command;

import github.tsffish.bedwarskit.BedwarsKit;
import github.tsffish.bedwarskit.listener.EditPlayerClick;
import github.tsffish.bedwarskit.listener.EditPlayerLookEntity;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.PluginManager;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

import static github.tsffish.bedwarskit.config.lang.LangConfigHandler.*;
import static github.tsffish.bedwarskit.config.main.MainConfigLoad.loadMainConfig;
import static github.tsffish.bedwarskit.util.PluginState.*;
import static github.tsffish.bedwarskit.util.misc.ChatColor.*;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;
import static github.tsffish.bedwarskit.util.misc.MessSender.l;
import static github.tsffish.bedwarskit.util.misc.StringMgr.*;
import static github.tsffish.bedwarskit.util.player.PlayerSender.sendMessage;
import static github.tsffish.bedwarskit.util.player.RelEditGame.*;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class BaseCommand implements CommandExecutor {
    private static final BedwarsKit plugin = BedwarsKit.getInstance();
    private static boolean regAlready;

    static {
        regAlready = false;
    }

    private static void showPluginInfo(CommandSender sender) {
        sender.sendMessage(green + msgline);
        sender.sendMessage(" ");
        sender.sendMessage(white + pluginName + " " + aqua + pluginVersion());
        sender.sendMessage(" ");
        sender.sendMessage(white + meanAuthor + ": " + yellow + getAuthor());
        sender.sendMessage(" ");
        sender.sendMessage(green + msgline);
    }

    private static void helpMsg(CommandSender sender) {
        sender.sendMessage(" ");
        sender.sendMessage(white + pluginName + " " + aqua + "Commands:");
        sender.sendMessage(" ");
        sender.sendMessage(" " + white + "/bwk" + yellow + " Display this help information.");
        sender.sendMessage(" " + white + "/bwk reload" + yellow + " Reload configuration file.");
        sender.sendMessage(" ");
    }

    public static void toggleEdit(Player player) {
        if (!player.isOp()) {
            return;
        }

        UUID playerUUID = player.getUniqueId();
        if (isEditGamePlayer(playerUUID)) {
            removeEditGamePlayer(playerUUID);
            new BukkitRunnable() {
                public void run() {
                    if (player.isOnline()) {
                        PlayerInventory pi = player.getInventory();
                        pi.remove(Material.PAPER);
                        pi.remove(Material.NAME_TAG);
                        pi.remove(Material.REDSTONE);
                        pi.remove(Material.ITEM_FRAME);
                    }
                }
            }.runTaskLater(plugin, 20L);
        } else {
            addEditGamePlayer(playerUUID);
            EditPlayerLookEntity.runTask(player);
            if (!regAlready) {
                regAlready = true;
                l(yellow + meanRegExListener);
                PluginManager pluginManager = Bukkit.getPluginManager();
                pluginManager.registerEvents(new EditPlayerClick(), plugin);
                l(green + meanRegExListenerSucc);
            }

        }

        if (isEditGamePlayer(playerUUID)) {
            sendMessage(player, meanEditGameToggleToTrue);
        } else {
            sendMessage(player, meanEditGameToggleToFalse);
        }
    }

    public boolean onCommand(
            CommandSender sender,
            Command command,
            String label,
            String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!sender.isOp()) {
                showPluginInfo(sender);
            } else {
                if (args.length > 0) {
                    if (args[0].equalsIgnoreCase("reload")) {
                        reloadConfig(sender);
                        return true;
                    } else if (args[0].equalsIgnoreCase("debug")) {
                        toggleDebug(sender);
                        return true;
                    } else if (args[0].equalsIgnoreCase("edit")) {
                        toggleEdit(player);
                        return true;
                    }
                }

                if (isDebug()) {
                    showDebugInfo(sender);
                } else {
                    showHelpMess(sender);
                }
            }
        } else {
            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("reload")) {
                    reloadConfig(sender);
                    return true;
                } else if (args[0].equalsIgnoreCase("debug")) {
                    toggleDebug(sender);
                    return true;
                } else if (args[0].equalsIgnoreCase("edit")) {
                    sender.sendMessage(red + meanCommandIsPlayerOnly);
                    return true;
                }
            }

            if (isDebug()) {
                showDebugInfo(sender);
            } else {
                showHelpMess(sender);
            }

        }
        return true;
    }

    private void reloadConfig(CommandSender sender) {
        loadMainConfig(sender, false);
    }

    private void toggleDebug(CommandSender sender) {
        changeIsDebug();
        if (isDebug()) {
            sender.sendMessage(meanDebugEnable);
        } else {
            sender.sendMessage(meanDebugDisable);
        }
    }

    private void showDebugInfo(CommandSender sender) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
        }

    }

    private void showHelpMess(CommandSender sender) {
        if (command_help == null) {
            sender.sendMessage(green + msgline);
            helpMsg(sender);
            sender.sendMessage(green + msgline);
        } else {
            for (String list : command_help) {
                sender.sendMessage(t(list));
            }
        }
    }
}

