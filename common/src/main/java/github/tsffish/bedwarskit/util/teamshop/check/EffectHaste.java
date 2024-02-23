package github.tsffish.bedwarskit.util.teamshop.check;

import github.tsffish.bedwarskit.util.teamshop.list.ListHaste;
import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.game.Game;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;


public class EffectHaste {
    static final PotionEffectType haste = PotionEffectType.FAST_DIGGING;
    public static void check(Game game) {

        String gameName = game.getName();

        BedwarsRel.getInstance().getGameManager().getGame(gameName);

        List<String[]> teamDatas = ListHaste.getTeamDatas(gameName);

        for (Player list : game.getPlayers()) {
            String teamName = game.getPlayerTeam(list).getName();

            for (String[] strings : teamDatas) {
                if (strings[0].equals(teamName)) {

                    int finallyLevel = -1;

                    String level = strings[1];
                    switch (level) {
                        case "1":
                            finallyLevel = 0;
                            break;
                        case "2":
                            finallyLevel = 1;
                        default:
                            break;
                    }
                    if (finallyLevel >= 0) {
                        list.removePotionEffect(haste);
                        list.addPotionEffect(new PotionEffect(haste, 999999, finallyLevel));
                    }

                }
            }
        }
    }
}