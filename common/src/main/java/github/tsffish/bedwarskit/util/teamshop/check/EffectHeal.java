package github.tsffish.bedwarskit.util.teamshop.check;

import github.tsffish.bedwarskit.util.teamshop.list.ListHeal;
import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.game.Game;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

import static github.tsffish.bedwarskit.config.main.MainConfigHandler.teamEff_Heal_dis;


public class EffectHeal {
    static final PotionEffectType heal = PotionEffectType.REGENERATION;
    public static void check(Game game) {

        String gameName = game.getName();

        BedwarsRel.getInstance().getGameManager().getGame(gameName);

        List<String[]> teamDatas = ListHeal.getTeamDatas(gameName);

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
                            break;
                        default:
                            break;
                    }

                    if (finallyLevel >= 0){

                    Location spawnLoc = BedwarsRel
                            .getInstance()
                            .getGameManager()
                            .getGameOfPlayer(list)
                            .getPlayerTeam(list)
                            .getSpawnLocation();

                    Location current = list.getLocation();

                    if (current.distance(spawnLoc) < teamEff_Heal_dis) {

                        list.addPotionEffect(
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