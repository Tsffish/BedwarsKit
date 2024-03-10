package github.tsffish.bedwarskit.util.teamshop.check;

import github.tsffish.bedwarskit.util.teamshop.list.ListHaste;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.Team;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Set;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class EffectHaste {
    static final PotionEffectType haste = PotionEffectType.FAST_DIGGING;

    public static void check(Game game) {

        String gameName = game.getName();

        if (ListHaste.getTeamDatas(gameName) == null) {
            return;
        }

        Set<String[]> teamDatas = ListHaste.getTeamDatas(gameName);

        for (Team team : game.getPlayingTeams()) {
            String teamName = team.getName();

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
                    if (finallyLevel < 0) {
                        return;
                    }
                    if (!team.getPlayers().isEmpty()) {
                        for (Player player : team.getPlayers()) {
                            player.removePotionEffect(haste);
                            player.addPotionEffect(new PotionEffect(haste, 999999, finallyLevel));
                        }
                    }
                }
            }
        }
    }
}