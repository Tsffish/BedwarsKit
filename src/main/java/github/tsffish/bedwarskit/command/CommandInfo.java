package github.tsffish.bedwarskit.command;

import github.tsffish.bedwarskit.config.MainConfigLoad;
import github.tsffish.bedwarskit.util.RelTeamEnchant;
import io.github.bedwarsrel.BedwarsRel;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Iterator;
public class CommandInfo implements CommandExecutor {public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!(sender instanceof Player)) {
        sender.sendMessage("BedwarsKit Is Running.Debug info: Use Player send command.");
        return true;
    } else {
        Player player = (Player)sender;
        if (!player.isOp()) {
            return false;
        } else {
            if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
                a(sender);
                return true;
            }

            player.sendMessage("BedwarsKit Current");

            if (BedwarsRel.getInstance() == null) {
                player.sendMessage("BedwarsRel.getInstance() == null");
            } else {
                player.sendMessage("BedwarsRel.getInstance() now not null");
            }

            player.sendMessage("teamEnchantListSword:");
            Iterator<String[]> iterator = RelTeamEnchant.teamEnchantListSword.values().iterator();

            String[] list;
            while(iterator.hasNext()) {
                list = iterator.next();
                player.sendMessage(list);
            }

            player.sendMessage("teamEnchantListProt:");
            iterator = RelTeamEnchant.teamEnchantListProt.values().iterator();

            while(iterator.hasNext()) {
                list = iterator.next();
                player.sendMessage(list);
            }

            return true;
        }
    }
}

    private void a(CommandSender sender) {
        MainConfigLoad.loadMainConfig(sender, false);
    }

}
