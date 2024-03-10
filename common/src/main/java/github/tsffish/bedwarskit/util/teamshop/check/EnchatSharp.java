package github.tsffish.bedwarskit.util.teamshop.check;

import github.tsffish.bedwarskit.util.teamshop.list.ListSharp;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.Team;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Set;

import static github.tsffish.bedwarskit.config.main.MainConfigHandler.giveSharpEnchList;
import static github.tsffish.bedwarskit.util.player.GetItemInHand.getItemInHand;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class EnchatSharp {
    private static final Enchantment sharp = Enchantment.DAMAGE_ALL;

    public static void check(Game game) {

        String gameName = game.getName();

        if (ListSharp.getTeamDatas(gameName) == null) {
            return;
        }

        Set<String[]> teamDatas = ListSharp.getTeamDatas(gameName);

        for (Team team : game.getPlayingTeams()) {
            String teamName = team.getName();

            for (String[] strings : teamDatas) {
                if (strings[0].equals(teamName)) {

                    int finallyLevel = 0;

                    String level = strings[1];
                    switch (level) {
                        case "1":
                            finallyLevel = 1;
                            break;
                        case "2":
                            finallyLevel = 2;
                            break;
                        case "3":
                            finallyLevel = 3;
                            break;
                        case "4":
                            finallyLevel = 4;
                            break;
                        default:
                            break;
                    }

                    if (finallyLevel < 1) {
                        return;
                    }
                    for (Player player : team.getPlayers()) {
                        ItemStack item = getItemInHand(player);
                        if (item != null) {
                            for (String string : giveSharpEnchList) {
                                String itemTypeText = item.getType().toString().toUpperCase();
                                if (itemTypeText.contains(string)) {
                                    item.addEnchantment(sharp, finallyLevel);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}