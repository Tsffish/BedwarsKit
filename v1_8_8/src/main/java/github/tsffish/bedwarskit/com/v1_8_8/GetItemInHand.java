package github.tsffish.bedwarskit.com.v1_8_8;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class GetItemInHand {
    public static ItemStack getItemInHand(Player player) {
        return player.getInventory().getItemInHand();
    }
}
