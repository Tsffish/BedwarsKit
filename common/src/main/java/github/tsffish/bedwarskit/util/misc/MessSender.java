package github.tsffish.bedwarskit.util.misc;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import java.util.logging.Logger;

import static github.tsffish.bedwarskit.util.PluginState.pluginNameConsole;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class MessSender {
    private static final ConsoleCommandSender console = Bukkit.getConsoleSender();
    private static final Logger l = Bukkit.getLogger();

    public static void l(String string) {
        console.sendMessage(t(pluginNameConsole() + " " + string));
    }

    public static void le(String name, String casue) {
        l.warning(t(pluginNameConsole() + " " + name + " " + casue));
    }

    public static void le(String className, Exception exception) {
        exception.printStackTrace();
    }

    public static void consoleSendCommand(String command) {
        Bukkit.dispatchCommand(console, command);
    }

    public static void sendMessageSender(CommandSender sender, String mess) {
        sender.sendMessage(t(mess));
    }
}
