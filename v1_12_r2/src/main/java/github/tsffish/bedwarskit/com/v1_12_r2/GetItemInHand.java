package github.tsffish.bedwarskit.com.v1_12_r2;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class GetItemInHand {
    private static final Material air = Material.AIR;
    public static ItemStack getItemInHand(Player player){
        if (player.getInventory().getItemInMainHand() != null
        && player.getInventory().getItemInMainHand().getType() != air){
        return player.getInventory().getItemInMainHand();
        }
        return getItemInOffHand(player);
    }

    public static ItemStack getItemInOffHand(Player player){
        return player.getInventory().getItemInOffHand();
    }
}
