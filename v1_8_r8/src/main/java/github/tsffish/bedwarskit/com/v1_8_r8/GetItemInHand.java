package github.tsffish.bedwarskit.com.v1_8_r8;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GetItemInHand {
    public static ItemStack getItemInHand(Player player){
        return player.getInventory().getItemInHand();
    }
}
