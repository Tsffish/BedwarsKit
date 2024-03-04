package github.tsffish.bedwarskit.command;

import github.tsffish.bedwarskit.config.main.MainConfigLoad;
import github.tsffish.bedwarskit.util.RelIsCheckingPlayer;
import github.tsffish.bedwarskit.util.teamshop.list.ListHaste;
import io.github.bedwarsrel.BedwarsRel;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;

import java.util.UUID;

import static github.tsffish.bedwarskit.config.lang.LangConfigHandler.command_help;
import static github.tsffish.bedwarskit.config.main.MainConfigHandler.giveProtEnchList;
import static github.tsffish.bedwarskit.config.main.MainConfigHandler.giveSharpEnchList;
import static github.tsffish.bedwarskit.util.RelCurrentStat.getPlayerOHKill;
import static github.tsffish.bedwarskit.util.misc.ChatColor.*;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;
import static github.tsffish.bedwarskit.util.misc.PluginState.*;
import static github.tsffish.bedwarskit.util.misc.StringMgr.*;
/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class CommandInfo implements CommandExecutor {
    public boolean onCommand(
            CommandSender sender,
            Command command,
            String label,
            String[] args) {

    if (sender instanceof Player) {
        if (!sender.isOp()) {
            showPluginInfo(sender);
        } else {
            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("reload")){
                    reloadConfig(sender);
                    return true;
                } else
                if (args[0].equalsIgnoreCase("debug")){
                    toggleDebug(sender);
                    return true;
                }
            }

            if(isDebug()){
                showDebugInfo(sender);
            }else {
                showHelpMess(sender);
            }
        }
    } else {
        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("reload")){
                reloadConfig(sender);
                return true;
            } else
            if (args[0].equalsIgnoreCase("debug")){
                toggleDebug(sender);
                return true;
            }
        }

        if(isDebug()){
            showDebugInfo(sender);
        }else {
            showHelpMess(sender);
        }

    }
    return true;
}
    private void reloadConfig(CommandSender sender) {
        MainConfigLoad.loadMainConfig(sender, false);
    }
    private void toggleDebug(CommandSender sender){
        changeIsDebug();
        if (isDebug()){
            sender.sendMessage("Debug now Enabled");
        }else {
            sender.sendMessage("Debug now Disabled");
        }
    }
    private void showDebugInfo(CommandSender sender){

        sender.sendMessage("BedwarsKit Current");

        if (BedwarsRel.getInstance() == null) {
            sender.sendMessage("BedwarsRel.getInstance() == null");
        } else {
            sender.sendMessage("BedwarsRel.getInstance() now not null");
        }

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if(RelIsCheckingPlayer.isInCheckList(player.getWorld().getName())) {
                player.sendMessage(player.getWorld().getName() + " Is in CheckLIst" );
            }else{
                player.sendMessage(player.getWorld().getName() + " Is Not in CheckLIst" );

            }

            String gameName = BedwarsRel.getInstance().getGameManager().getGameOfPlayer(player).getName();

            player.sendMessage("HasteList:");
            for (String[] strings : ListHaste.getTeamDatas(gameName)){
                player.sendMessage(strings[0] + ": " + strings[1]);
            }


            player.sendMessage("giveSharpEnchList:");
            for (String s: giveSharpEnchList){
                player.sendMessage(s);
            }

            player.sendMessage("giveProtEnchList:");
            for (String s: giveProtEnchList){
                player.sendMessage(s);
            }

            player.sendMessage("getPlayerOHKill:");

            UUID uuid = player.getUniqueId();
            player.sendMessage(getPlayerOHKill(uuid) + "");

        }
    }
    private static void showPluginInfo(CommandSender sender) {
        sender.sendMessage(green + msgline);
        sender.sendMessage(" ");
        sender.sendMessage(white + pluginName + " " + aqua + pluginVersion());
        sender.sendMessage(" ");
        sender.sendMessage(white + "Author: " + yellow + getAuthor());
        sender.sendMessage(" ");
        sender.sendMessage(green + msgline);
    }

    private void showHelpMess(CommandSender sender) {
    if (command_help == null){
        PluginManager pm = Bukkit.getPluginManager();
        if (pm.getPlugin(relName) != null) {
            sender.sendMessage(green + msgline);
            helpMsg(sender);
            sender.sendMessage(green + msgline);
        } else {
            sender.sendMessage(red + msgline);
            helpMsg(sender);
            sender.sendMessage(red + msgline);
        }
    }else {
        for(String list : command_help){
            sender.sendMessage(t(list));
        }
    }
}
    private static void helpMsg(CommandSender sender) {
        sender.sendMessage(" ");
        sender.sendMessage(white + pluginName + " " + aqua + "Commands:");
        sender.sendMessage(" ");
        sender.sendMessage(" " + white + "/bwk" + yellow + " Display this help information.");
        sender.sendMessage(" " + white + "/bwk reload" + yellow + " Reload configuration file.");
        sender.sendMessage(" ");
    }
    }

