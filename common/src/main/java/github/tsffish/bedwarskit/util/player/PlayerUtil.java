package github.tsffish.bedwarskit.util.player;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.HashMap;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class PlayerUtil {
    private static final Material diamond = Material.DIAMOND;

    public static boolean isPlayerHaveDiamond(Player player, int exAmount) {
        PlayerInventory pi = player.getInventory();
        boolean haveDiamond = false;
        int totalDiamondAmount = 0;

        ItemStack[] items = pi.getContents();
        for (ItemStack item : items) {
            if (item != null) {
                if (item.getType() == diamond) {
                    haveDiamond = true;
                    totalDiamondAmount += item.getAmount();
                }
            }
        }

        return haveDiamond && totalDiamondAmount >= exAmount;
    }

public static boolean removeDiamond(Player player, int exAmount) {
    PlayerInventory pi = player.getInventory();
    int remainingAmountToRemove = exAmount;

    HashMap<Integer, ? extends ItemStack> diamondSlots = pi.all(Material.DIAMOND);
    for (ItemStack item : diamondSlots.values()) {
        int currentAmount = item.getAmount();
        if (currentAmount <= remainingAmountToRemove) {
            remainingAmountToRemove -= currentAmount;
            pi.removeItem(item);
        } else {
            item.setAmount(currentAmount - remainingAmountToRemove);
            remainingAmountToRemove = 0;
            break;
        }
    }

    return remainingAmountToRemove == 0;
}


    public static void setPlayerFlying(Player player) {
        if (!player.getAllowFlight()) {
            player.setAllowFlight(true);
        }
        player.setFlying(true);
    }

    public static void setPlayerNotFlying(Player player) {
        player.setFlying(false);
        if (player.getAllowFlight()) {
            player.setAllowFlight(false);
        }
    }
}
