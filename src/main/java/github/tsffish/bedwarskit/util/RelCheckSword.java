package github.tsffish.bedwarskit.util;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
public class RelCheckSword {
    public static void checkInvHasSword(Player player) {
        PlayerInventory pi = player.getInventory();
        String playerName = player.getName();
        GameMode pig = player.getGameMode();
        boolean isCM = pig == GameMode.CREATIVE;
        boolean isSP = pig == GameMode.SPECTATOR;

        Material woodSword = Material.WOOD_SWORD;
        Material stoneSword = Material.STONE_SWORD;
        Material ironSword = Material.IRON_SWORD;
        Material diamondSword = Material.DIAMOND_SWORD;
        ItemStack ws = new ItemStack(woodSword);
        if(!isCM && !isSP && !RelPlayerKit.getPlayerKit(playerName).equalsIgnoreCase("none")) {

    if (pi.contains(woodSword)) {
        if (pi.contains(stoneSword) || pi.contains(ironSword) || pi.contains(diamondSword)) {
            pi.remove(woodSword);
        }
    } else if (!pi.contains(woodSword) && !pi.contains(stoneSword) && !pi.contains(ironSword) && !pi.contains(diamondSword)) {
        if (player.getItemOnCursor().getType() != ws.getType()){

        pi.addItem(ws);
        }
    }
}
    }
}
