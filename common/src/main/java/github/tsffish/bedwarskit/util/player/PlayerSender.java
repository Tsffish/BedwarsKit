package github.tsffish.bedwarskit.util.player;

import org.bukkit.entity.Player;

import static github.tsffish.bedwarskit.util.misc.ColorString.t;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class PlayerSender {
    public static void sendTitle(Player player, String title, String subtitle) {
        player.sendTitle(t(title), t(subtitle));
    }

    public static void sendMessage(Player player, String message) {
        player.sendMessage(t(message));
    }
}
