package github.tsffish.bedwarskit.com.v1_12_2;

import org.bukkit.entity.Player;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class SendActionBar {
    public static void sendActionBar(Player player, String message) {
        io.github.bedwarsrel.com.v1_12_r1.ActionBar.sendActionBar(player, message);
    }
}