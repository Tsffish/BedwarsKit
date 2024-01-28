package github.tsffish.bedwarskit.command;

import github.tsffish.bedwarskit.util.RelTeamEnchant;
import io.github.bedwarsrel.BedwarsRel;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandInfo implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) { // 检查执行者是否为玩家
            Player player = (Player) sender;

            if (player.isOp()) { // 检查玩家是否为op
                player.sendMessage("BedwarsKit Current");
                if (BedwarsRel.getInstance() == null){
                    player.sendMessage("BedwarsRel.getInstance() == null");
                }else {
                    player.sendMessage("BedwarsRel.getInstance() now not null");
                }
                player.sendMessage("teamEnchantListSword:");
                for(String[] list : RelTeamEnchant.teamEnchantListSword.values()){
                    player.sendMessage(list);
                }
                player.sendMessage("teamEnchantListProt:");
                for(String[] list : RelTeamEnchant.teamEnchantListProt.values()){
                    player.sendMessage(list);
                }

                return true;
            }
        } else {
            sender.sendMessage("BedwarsKit Is Running.Debug info: Use Player send command.");
            return true;
        }

        return false;
    }
}

