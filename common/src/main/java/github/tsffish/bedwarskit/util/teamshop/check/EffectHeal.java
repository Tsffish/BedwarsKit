package github.tsffish.bedwarskit.util.teamshop.check;

import github.tsffish.bedwarskit.util.teamshop.list.ListHeal;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.Team;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Set;

import static github.tsffish.bedwarskit.config.main.MainConfigHandler.teamEff_Heal_dis;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class EffectHeal {
    static final PotionEffectType heal = PotionEffectType.REGENERATION;

    public static void check(Game game) {

        String gameName = game.getName();

        if (ListHeal.getTeamDatas(gameName) == null) {
            return;
        }

        Set<String[]> teamDatas = ListHeal.getTeamDatas(gameName);

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
                            break;
                        default:
                            break;
                    }

                    if (finallyLevel < 0) {
                        return;
                    }
                    Location spawnLoc = team.getSpawnLocation();
                    if (!team.getPlayers().isEmpty()) {
                        for (Player player : team.getPlayers()) {

                            Location current = player.getLocation();

                            if (current.distance(spawnLoc) < teamEff_Heal_dis) {

                                player.addPotionEffect(
                                        new PotionEffect(
                                                heal,
                                                10 * 20,
                                                finallyLevel));
                            }
                        }
                    }
                }
            }
        }
    }
}