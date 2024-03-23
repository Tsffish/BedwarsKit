package github.tsffish.bedwarskit.util.teamshop.haste;

import io.github.bedwarsrel.game.Team;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static github.tsffish.bedwarskit.util.PluginState.isDebug;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class ListHaste {
    private static ConcurrentHashMap<String, Set<String[]>> listMap;

    static {
        listMap = new ConcurrentHashMap<>(24);
    }

    public static void setTeamDatas(String gameName, Set<String[]> newTeamDatas) {
        Set<String[]> teamDatas = getTeamDatas(gameName);
        if (teamDatas == null) {
            teamDatas = Collections.newSetFromMap(new ConcurrentHashMap<>());
        }

        for (String[] teamInfo : newTeamDatas) {
            boolean found = false;
            for (String[] oldTeamInfo : teamDatas) {
                if (oldTeamInfo[0].equals(teamInfo[0])) {
                    oldTeamInfo[1] = teamInfo[1];
                    found = true;
                    break;
                }
            }
            if (!found) {
                String[] newTeamInfo = {teamInfo[0], teamInfo[1]};
                teamDatas.add(newTeamInfo);
            }
        }

        listMap.put(gameName, teamDatas);
        if (isDebug()) {
            System.out.println("ListHaste:");
            System.out.println("setTeamDatas:");
            for (String[] string : teamDatas) {
                System.out.println(string[0] + ": " + string[1]);
            }
        }

    }

    public static Set<String[]> getTeamDatas(String gameName) {
        return listMap.get(gameName);
    }

    public static void setTeamDatasDefault(String gameName, List<Team> teamList) {
        Set<String[]> teamDatas = getTeamDatas(gameName);
        if (teamDatas == null) {
            teamDatas = Collections.newSetFromMap(new ConcurrentHashMap<>());
        }

        for (Team team : teamList) {
            boolean found = false;
            for (String[] teamInfo : teamDatas) {
                if (teamInfo[0].equals(team.getName())) {
                    teamInfo[1] = "0";
                    found = true;
                    break;
                }
            }
            if (!found) {
                String[] newTeamInfo = {team.getName(), "0"};
                teamDatas.add(newTeamInfo);
            }
        }

        ListHaste.setTeamDatas(gameName, teamDatas);
    }


}

