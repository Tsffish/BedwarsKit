package github.tsffish.bedwarskit.util;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.Objects;

import static github.tsffish.bedwarskit.util.RelPlayerKit.playerKitList;

public class RelCheckSword {
    public static void gs(Player player) {
        PlayerInventory pi = player.getInventory();
        GameMode pig = player.getGameMode();
        boolean isCM = pig == GameMode.CREATIVE;
        boolean isSP = pig == GameMode.SPECTATOR;

        Material woodSword = Material.WOOD_SWORD;
        Material stoneSword = Material.STONE_SWORD;
        Material ironSword = Material.IRON_SWORD;
        Material diamondSword = Material.DIAMOND_SWORD;
        ItemStack ws = new ItemStack(woodSword);
        if(!isCM && !isSP && !Objects.equals(playerKitList.get(player), "None")) {

    if (pi.contains(woodSword)) {
        if (pi.contains(stoneSword) || pi.contains(ironSword) || pi.contains(diamondSword)) {
            pi.remove(woodSword);
        }
    } else if (!pi.contains(stoneSword) && !pi.contains(ironSword) && !pi.contains(diamondSword)) {
        pi.addItem(ws);
    }
}
    }
}
