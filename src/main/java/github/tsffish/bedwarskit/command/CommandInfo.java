package github.tsffish.bedwarskit.command;

import github.tsffish.bedwarskit.Main;
import github.tsffish.bedwarskit.config.MainConfigLoad;
import github.tsffish.bedwarskit.util.RelTeamEnchant;
import io.github.bedwarsrel.BedwarsRel;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;

import java.util.Iterator;

import static github.tsffish.bedwarskit.Main.*;

public class CommandInfo implements CommandExecutor {public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

    if (sender instanceof Player) {
        if (!sender.isOp()) {
            sender.sendMessage(ChatColor.GREEN + " ================================");
            sender.sendMessage(" ");
            sender.sendMessage( ChatColor.WHITE + pluginName + " " + ChatColor.AQUA + pluginName);
            sender.sendMessage(" ");
            sender.sendMessage(ChatColor.WHITE + "Author: " + ChatColor.YELLOW  + "Tsffish");
            sender.sendMessage(" ");
            sender.sendMessage(ChatColor.GREEN + " ================================");
            return true;
        } else {
            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("reload")){
                    a(sender);
                    return true;
                } else
                if (args[0].equalsIgnoreCase("debug")){
                    Main.isDebug = !Main.isDebug;
                    if (Main.isDebug){
                        sender.sendMessage("Debug now Enabled");
                    }else {
                        sender.sendMessage("Debug now Disabled");
                    }
                    return true;
                }
            }

            if(isDebug){

                sender.sendMessage("BedwarsKit Current");

                if (BedwarsRel.getInstance() == null) {
                    sender.sendMessage("BedwarsRel.getInstance() == null");
                } else {
                    sender.sendMessage("BedwarsRel.getInstance() now not null");
                }

                sender.sendMessage("teamEnchantListSword:");
                Iterator<String[]> iterator = RelTeamEnchant.teamEnchantListSword.values().iterator();

                String[] list;
                while (iterator.hasNext()) {
                    list = iterator.next();
                    sender.sendMessage(list);
                }

                sender.sendMessage("teamEnchantListProt:");
                iterator = RelTeamEnchant.teamEnchantListProt.values().iterator();

                while (iterator.hasNext()) {
                    list = iterator.next();
                    sender.sendMessage(list);
                }
            }else {
                showHelpMess(sender);
            }

            return true;
        }
    } else {

        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("reload")){
                a(sender);
                return true;
            } else
            if (args[0].equalsIgnoreCase("debug")){
                Main.isDebug = !Main.isDebug;
                if (Main.isDebug){
                    sender.sendMessage("Debug now Enabled");
                }else {
                    sender.sendMessage("Debug now Disabled");
                }
                return true;
            }
        }

        if(isDebug){

            sender.sendMessage("BedwarsKit Current");

            if (BedwarsRel.getInstance() == null) {
                sender.sendMessage("BedwarsRel.getInstance() == null");
            } else {
                sender.sendMessage("BedwarsRel.getInstance() now not null");
            }

            sender.sendMessage("teamEnchantListSword:");
            Iterator<String[]> iterator = RelTeamEnchant.teamEnchantListSword.values().iterator();

            String[] list;
            while (iterator.hasNext()) {
                list = iterator.next();
                sender.sendMessage(list);
            }

            sender.sendMessage("teamEnchantListProt:");
            iterator = RelTeamEnchant.teamEnchantListProt.values().iterator();

            while (iterator.hasNext()) {
                list = iterator.next();
                sender.sendMessage(list);
            }
        }else {
            showHelpMess(sender);
        }

        return true;
    }
}
    private void a(CommandSender sender) {
        MainConfigLoad.loadMainConfig(sender, false);
    }

    private void showHelpMess(CommandSender sender){
        PluginManager pm = Bukkit.getPluginManager();
        if (pm.getPlugin("BedwarsRel") != null) {
            sender.sendMessage(ChatColor.GREEN + " ================================");
            sender.sendMessage(" ");
            sender.sendMessage( ChatColor.WHITE + pluginName + " " + ChatColor.AQUA + "Commands:");
            sender.sendMessage(" ");
            sender.sendMessage(" " + ChatColor.WHITE + "/bwk" + ChatColor.YELLOW  + " Display this help information.");
            sender.sendMessage(" " + ChatColor.WHITE + "/bwk reload" + ChatColor.YELLOW  + " Reload configuration file.");
            sender.sendMessage(" " + ChatColor.WHITE + "/bwk debug" + ChatColor.YELLOW  + " Switch debugging mode.");
            sender.sendMessage(" ");
            sender.sendMessage(ChatColor.GREEN + " ================================");
        } else {
            sender.sendMessage(ChatColor.RED + " ================================");
            sender.sendMessage(" ");
            sender.sendMessage( ChatColor.WHITE + pluginName + " " + ChatColor.AQUA + "Commands:");
            sender.sendMessage(" ");
            sender.sendMessage(" " + ChatColor.WHITE + "/bwk" + ChatColor.YELLOW  + " Display this help information.");
            sender.sendMessage(" " + ChatColor.WHITE + "/bwk reload" + ChatColor.YELLOW  + " Reload configuration file.");
            sender.sendMessage(" " + ChatColor.WHITE + "/bwk debug" + ChatColor.YELLOW  + " Switch debugging mode.");
            sender.sendMessage(" ");
            sender.sendMessage(ChatColor.RED + " ================================");
        }
    }
}
