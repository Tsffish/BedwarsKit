package github.tsffish.bedwarskit.util;

import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.game.Game;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static github.tsffish.bedwarskit.util.misc.ColorString.t;

public class RelTeamSeletorMenu {
    private static Inventory TeamSeletorMenu2v2;
    private static Inventory TeamSeletorMenu4v4;
    public static void loadTeamSeletorMenu2v2(Player player){

        Game game = BedwarsRel.getInstance().getGameManager().getGameOfPlayer(player);

        String gameName = game.getName();

        if (TeamSeletorMenu2v2 == null) {
            TeamSeletorMenu2v2 = Bukkit.createInventory(null, 3 * 9, "选择队伍 - " + gameName);
        }else {
            TeamSeletorMenu2v2.clear();
        }
        List<String> lore = new ArrayList<>();
        lore.add("ERROR");

        ItemStack selgreenItem = new ItemStack(Material.WOOL, 1);
        ItemMeta selgreenItemMeta = selgreenItem.getItemMeta();
        selgreenItemMeta.setDisplayName(t("&a绿之队"));
        lore.clear();
        lore.add(t("&e点击加入!"));
        selgreenItemMeta.setLore(lore);
        selgreenItem.setItemMeta(selgreenItemMeta);

        TeamSeletorMenu2v2.setItem( 2 * 9 + 1 + 1,selgreenItem);

        player.openInventory(TeamSeletorMenu2v2);
    }

    public static void loadTeamSeletorMenu4v4(Player player){

        TeamSeletorMenu4v4 = Bukkit.createInventory(null,3,"选择队伍");

        List<String> lore = new ArrayList<>();
        lore.add("ERROR");

    }
}
