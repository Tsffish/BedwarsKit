package github.tsffish.bedwarskit.util.teamshop.check;

import github.tsffish.bedwarskit.config.main.MainConfigHandler;
import github.tsffish.bedwarskit.util.teamshop.list.ListProt;
import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.game.Game;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class EnchatProt {

    public static void check(Game game) {

        String gameName = game.getName();

        BedwarsRel.getInstance().getGameManager().getGame(gameName);

        List<String[]> teamDatas = ListProt.getTeamDatas(gameName);

        for (Player list : game.getPlayers()) {
            String teamName = game.getPlayerTeam(list).getName();

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

                    if (finallyLevel == 0) return;
                    ItemStack[] inventory = list.getInventory().getArmorContents();
                    for (ItemStack item :inventory) {
                        if (item != null){
                            for (String string : MainConfigHandler.giveProtEnchList){
                                    if (item.getType().toString().toUpperCase().contains(string)){
                                    item.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,finallyLevel);
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