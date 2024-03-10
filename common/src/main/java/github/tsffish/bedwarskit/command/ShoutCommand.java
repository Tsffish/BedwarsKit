package github.tsffish.bedwarskit.command;

import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameState;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static github.tsffish.bedwarskit.config.rel.RelConfigHandler.shoutPrefix;
import static github.tsffish.bedwarskit.util.misc.ChatColor.red;
import static github.tsffish.bedwarskit.util.misc.StringMgr.meanCommandIsPlayerOnly;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class ShoutCommand implements CommandExecutor {
    private static final GameState running = GameState.RUNNING;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (BedwarsRel.getInstance() == null) {
                return true;
            }

            Game game = BedwarsRel.getInstance().getGameManager().getGameOfPlayer(player);
            if (game != null && game.getState() == running) {

                if (args.length > 0) {
                    StringBuilder message = new StringBuilder(shoutPrefix);
                    for (String arg : args) {
                        message.append(arg).append(" ");
                    }
                    String finalMessage = message.toString().trim();
                    player.chat(finalMessage);
                }
                return true;
            }
        } else {
            sender.sendMessage(red + meanCommandIsPlayerOnly);
        }
        return true;
    }
}
