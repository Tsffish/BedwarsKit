package github.tsffish.bedwarskit.util.teamshop;

import github.tsffish.bedwarskit.Main;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameState;
import io.github.bedwarsrel.game.Team;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static github.tsffish.bedwarskit.config.main.MainConfigHandler.teamEff_Heal_dis;

public class RelCheckEffect {

    public static void checkEffectHaste(Game game) {
        for (Map.Entry<String, String[]> entry : RelTeamEffect.getTeamEffectListhaste().entrySet()) {
            String mapName = entry.getKey();
            String[] effectData = entry.getValue();

            String teamName = effectData[0];
            String effectLevel = effectData[1];

            if (game != null) {
                List<Team> teams = new ArrayList<>(game.getPlayingTeams());
                for (Team team : teams) {
                    for (Player player : team.getPlayers()) {

                        if (game.getRegion().getName().equalsIgnoreCase(mapName) && teamName.equals(team.getName()) && game.getState() == GameState.RUNNING) {
                                switch (effectLevel) {
                                    case "1":
                                        player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 999999, 1));
                                        break;
                                    case "2":
                                        player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 999999, 1));
                                        break;
                                    default:
                                        break;
                                }

                        }
                    }
                }
            }
        }
    }
    public static void checkEffectHeal(Game game) {
        for (Map.Entry<String, String[]> entry : RelTeamEffect.getTeamEffectListheal().entrySet()) {
            String mapName = entry.getKey();
            String[] effectData = entry.getValue();

            String teamName = effectData[0];
            String effectLevel = effectData[1];

            if (game != null) {
                List<Team> teams = new ArrayList<>(game.getPlayingTeams());
                for (Team team : teams) {
                    for (Player player : team.getPlayers()) {

                        if (Objects.equals(game.getRegion().getName(), mapName) && Objects.equals(teamName, team.getName()) && game.getState() == GameState.RUNNING) {


                            Location playerloc = player.getLocation();
                            Location teamloc = game.getPlayerTeam(player).getSpawnLocation();

                            if (playerloc.distance(teamloc) < teamEff_Heal_dis) {
                                if (Main.isDebug()){
                                    player.sendMessage("You are in heal dis");
                                }
                                if (effectLevel.equals("1")) {
                                    player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 10 * 20, 0));
                                }
                            }else{
                                if (Main.isDebug()){
                                    player.sendMessage("YOu are not in heal dis");
                                }
                            }
                            }
                    }
                }
            }
        }
    }
}