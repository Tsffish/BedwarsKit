package github.tsffish.bedwarskit.util;

import github.tsffish.bedwarskit.config.main.MainConfigHandler;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.Team;
import org.bukkit.Material;

import static github.tsffish.bedwarskit.config.main.MainConfigHandler.meanNotYou;
import static github.tsffish.bedwarskit.config.main.MainConfigHandler.meanYou;

public class RelGameStat {
    private static String yellowTeamName;
    private static String blueTeamName;
    private static String greenTeamName;
    private static String redTeamName;
    private static String whiteTeamName;
    private static String aquaTeamName;
    private static String pinkTeamName;
    private static String grayTeamName;
    private static String orangeTeamName;
    private static final Material bed = Material.BED;
    public static Team getTeam(Game game, String string){
        if (game.getTeam(string) != null){
            return game.getTeam(string);
        }else {
            return null;
        }
    }

    public static String getTeamIsMe(String myTeamColorName, String getTeamColorName){

        if (myTeamColorName.equals(getTeamColorName)){
                return meanYou.replace("{teamColor}", myTeamColorName);
            } else {
                return meanNotYou.replace("{teamColor}", myTeamColorName);
        }



    }

    public static String getTeamStat(Team team){
        if (team != null) {
            if (team.getTargetHeadBlock() != null
                    && team.getTargetHeadBlock().getBlock() != null) {
                Material blockType = team.getTargetHeadBlock().getBlock().getType();
                if (blockType != null && blockType == bed) {
                    return MainConfigHandler.meanTeamBedYes.replace("{aliveCount}", team.getPlayers().size() + "");
                } else if (team.getPlayers().size() > 0) {
                    return MainConfigHandler.meanTeamBedNo.replace("{aliveCount}", team.getPlayers().size() + "");
                } else {
                    return MainConfigHandler.meanTeamNone.replace("{aliveCount}", team.getPlayers().size() + "");
                }
            }
        }
        return " ";
    }
    public static String getTeamPlayer(Team team){
        if (team != null) {
                if (team.getPlayers().size() > 0) {
                    return team.getPlayers().size() + "";
                } else {
                    return "0";
                }
        }else {
            return "0";
        }
    }
    public static String getTeamBedStat(Team team){
        if (team != null) {
            if (team.getTargetHeadBlock() != null
                    && team.getTargetHeadBlock().getBlock() != null) {
                Material blockType = team.getTargetHeadBlock().getBlock().getType();
                if (blockType != null && blockType == bed) {
                    //return MainConfigHandler.meanTeamBedStatYes;
                } else if (team.getPlayers().size() > 0) {
                    //return MainConfigHandler.meanTeamBedStatNo;
                } else {
                    return " ";
                }
            }
        }
        return " ";
    }

}
