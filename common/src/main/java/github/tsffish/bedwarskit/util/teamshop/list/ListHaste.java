package github.tsffish.bedwarskit.util.teamshop.list;

import io.github.bedwarsrel.game.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ListHaste {

    private static ConcurrentHashMap<String, List<String[]>> listMap = new ConcurrentHashMap<>(24);
    public static void setTeamDatas(String gameName, List<String[]> newTeamDatas) {
        List<String[]> teamDatas = getTeamDatas(gameName);
        if (teamDatas == null) {
            teamDatas = new ArrayList<>();
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
    }

    public static List<String[]> getTeamDatas(String gameName){
        return listMap.get(gameName);
    }

    public static void setTeamDatasDefault(String gameName, List<Team> teamList) {
        List<String[]> teamDatas = getTeamDatas(gameName);
        if (teamDatas == null || teamDatas.size() <= 0) {
            teamDatas = new ArrayList<>();
            for (Team team : teamList) {
                String[] teamInfo = {team.getName(), "0"}; // 默认设置队伍的 level 为 0
                teamDatas.add(teamInfo);
            }
        } else {
            for (Team team : teamList) {
                boolean found = false;
                for (String[] teamInfo : teamDatas) {
                    if (teamInfo[0].equals(team.getName())) {
                        teamInfo[1] = "0"; // 将队伍的等级设置为 0
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    String[] newTeamInfo = {team.getName(), "0"};
                    teamDatas.add(newTeamInfo);
                }
            }
        }

        ListHaste.setTeamDatas(gameName, teamDatas);
    }


}

