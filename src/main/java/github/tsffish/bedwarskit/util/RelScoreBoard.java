package github.tsffish.bedwarskit.util;

import github.tsffish.bedwarskit.config.MainConfigHandler;
import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameManager;
import io.github.bedwarsrel.game.GameState;
import io.github.bedwarsrel.game.Team;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static github.tsffish.bedwarskit.config.MainConfigHandler.*;
import static github.tsffish.bedwarskit.util.RelTeamColorName.*;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;

public class RelScoreBoard {
    public static void updateScoreBoard(Player player) {

        GameManager gm = BedwarsRel.getInstance().getGameManager();
        Game game = gm.getGameOfPlayer(player);
        if (game != null && game.getState() == GameState.RUNNING) {

            LocalDate currentDate = LocalDate.now();
            // 定义日期格式
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            String formattedDate = currentDate.format(formatter);

            String worldname = game.getRegion().getWorld().getName();
            if (worldname.contains(MainConfigHandler.rushWorld)) {
                Material bed = Material.BED_BLOCK;

                String RedTeamName = relTeamName_Red;
                String YellowTeamName = relTeamName_Yellow;
                String GreenTeamName = relTeamName_Green;
                String BlueTeamName = relTeamName_Blue;

                String redName = RED_TEAM_COLOR_NAME;
                String blueName = BLUE_TEAM_COLOR_NAME;
                String yellowName = YELLOW_TEAM_COLOR_NAME;
                String greenName = GREEN_TEAM_COLOR_NAME;

                ScoreboardManager mgr = Bukkit.getScoreboardManager();

                if (worldname.contains(MainConfigHandler.rushWorld2v2)) {
                    // 2v2 ScoreBoard
                    String pinkName = PINK_TEAM_COLOR_NAME;
                    String aquaName = AQUA_TEAM_COLOR_NAME;
                    String grayName = GRAY_TEAM_COLOR_NAME;
                    String whiteName = WHITE_TEAM_COLOR_NAME;

                    String PinkTeamName = relTeamName_Pink;
                    String AquaTeamName = relTeamName_Aqua;
                    String GrayTeamName = relTeamName_Gray;
                    String WhiteTeamName = relTeamName_White;

                    int gameTimeLeft = game.getTimeLeft();

                    String redTeamStat = "redTeamStat: ERROR";
                    String yellowTeamStat = "yellowTeamStat: ERROR";
                    String greenTeamStat = "greenTeamStat: ERROR";
                    String blueTeamStat = "blueTeamStat: ERROR";

                    String pinkTeamStat = "pinkTeamStat: ERROR";
                    String aquaTeamStat = "aquaTeamStat: ERROR";
                    String grayTeamStat = "grayTeamStat: ERROR";
                    String whiteTeamStat = "whiteTeamStat: ERROR";

                    String redTeamIsMe;
                    String yellowTeamIsMe;
                    String greenTeamIsMe;
                    String blueTeamIsMe;

                    String pinkTeamIsMe;
                    String aquaTeamIsMe;
                    String grayTeamIsMe;
                    String whiteTeamIsMe;

                    Team red = game.getTeam(RedTeamName);
                    Team blue = game.getTeam(BlueTeamName);
                    Team green = game.getTeam(GreenTeamName);
                    Team yellow = game.getTeam(YellowTeamName);

                    Team pink = game.getTeam(PinkTeamName);
                    Team aqua = game.getTeam(AquaTeamName);
                    Team gray = game.getTeam(GrayTeamName);
                    Team white = game.getTeam(WhiteTeamName);

                    Team playerTeam = game.getPlayerTeam(player);

                    Scoreboard scoreboard = mgr.getNewScoreboard();
                    Objective objective = scoreboard.registerNewObjective("2v2", "dummy");

                    if (green.getTargetHeadBlock() != null && green.getTargetHeadBlock().getBlock() != null) {
                        Material blockType = green.getTargetHeadBlock().getBlock().getType();
                        if (blockType != null && blockType == bed) {
                            greenTeamStat = MainConfigHandler.meanTeamBedYes.replace("{aliveCount}", green.getPlayers().size() + "");
                        } else if (green.getPlayers().size() >= 1) {
                            greenTeamStat = MainConfigHandler.meanTeamBedNo.replace("{aliveCount}", green.getPlayers().size() + "");
                        } else {
                            greenTeamStat = MainConfigHandler.meanTeamNone.replace("{aliveCount}", green.getPlayers().size() + "");
                        }
                    }

                    if (red.getTargetHeadBlock() != null && red.getTargetHeadBlock().getBlock() != null) {
                        Material blockType = red.getTargetHeadBlock().getBlock().getType();
                        if (blockType != null && blockType == bed) {
                            redTeamStat = MainConfigHandler.meanTeamBedYes.replace("{aliveCount}", red.getPlayers().size() + "");
                        } else if (red.getPlayers().size() >= 1) {
                            redTeamStat = MainConfigHandler.meanTeamBedNo.replace("{aliveCount}", red.getPlayers().size() + "");
                        } else {
                            redTeamStat = MainConfigHandler.meanTeamNone.replace("{aliveCount}", red.getPlayers().size() + "");
                        }
                    }

                    if (blue.getTargetHeadBlock() != null && blue.getTargetHeadBlock().getBlock() != null) {
                        Material blockType = blue.getTargetHeadBlock().getBlock().getType();
                        if (blockType != null && blockType == bed) {
                            blueTeamStat = MainConfigHandler.meanTeamBedYes.replace("{aliveCount}", blue.getPlayers().size() + "");
                        } else if (blue.getPlayers().size() >= 1) {
                            blueTeamStat = MainConfigHandler.meanTeamBedNo.replace("{aliveCount}", blue.getPlayers().size() + "");
                        } else {
                            blueTeamStat = MainConfigHandler.meanTeamNone.replace("{aliveCount}", blue.getPlayers().size() + "");
                        }
                    }

                    if (yellow.getTargetHeadBlock() != null && yellow.getTargetHeadBlock().getBlock() != null) {
                        Material blockType = yellow.getTargetHeadBlock().getBlock().getType();
                        if (blockType != null && blockType == bed) {
                            yellowTeamStat = MainConfigHandler.meanTeamBedYes.replace("{aliveCount}", yellow.getPlayers().size() + "");
                        } else if (yellow.getPlayers().size() >= 1) {
                            yellowTeamStat = MainConfigHandler.meanTeamBedNo.replace("{aliveCount}", yellow.getPlayers().size() + "");
                        } else {
                            yellowTeamStat = MainConfigHandler.meanTeamNone.replace("{aliveCount}", yellow.getPlayers().size() + "");
                        }
                    }


                    if (pink.getTargetHeadBlock() != null && pink.getTargetHeadBlock().getBlock() != null) {
                        Material blockType = pink.getTargetHeadBlock().getBlock().getType();
                        if (blockType != null && blockType == bed) {
                            pinkTeamStat = MainConfigHandler.meanTeamBedYes.replace("{aliveCount}", pink.getPlayers().size() + "");
                        } else if (pink.getPlayers().size() >= 1) {
                            pinkTeamStat = MainConfigHandler.meanTeamBedNo.replace("{aliveCount}", pink.getPlayers().size() + "");
                        } else {
                            pinkTeamStat = MainConfigHandler.meanTeamNone.replace("{aliveCount}", pink.getPlayers().size() + "");
                        }
                    }

                    if (aqua.getTargetHeadBlock() != null && aqua.getTargetHeadBlock().getBlock() != null) {
                        Material blockType = aqua.getTargetHeadBlock().getBlock().getType();
                        if (blockType != null && blockType == bed) {
                            aquaTeamStat = MainConfigHandler.meanTeamBedYes.replace("{aliveCount}", aqua.getPlayers().size() + "");
                        } else if (aqua.getPlayers().size() >= 1) {
                            aquaTeamStat = MainConfigHandler.meanTeamBedNo.replace("{aliveCount}", aqua.getPlayers().size() + "");
                        } else {
                            aquaTeamStat = MainConfigHandler.meanTeamNone.replace("{aliveCount}", aqua.getPlayers().size() + "");
                        }
                    }

                    if (gray.getTargetHeadBlock() != null && gray.getTargetHeadBlock().getBlock() != null) {
                        Material blockType = gray.getTargetHeadBlock().getBlock().getType();
                        if (blockType != null && blockType == bed) {
                            grayTeamStat = MainConfigHandler.meanTeamBedYes.replace("{aliveCount}", gray.getPlayers().size() + "");
                        } else if (gray.getPlayers().size() >= 1) {
                            grayTeamStat = MainConfigHandler.meanTeamBedNo.replace("{aliveCount}", gray.getPlayers().size() + "");
                        } else {
                            grayTeamStat = MainConfigHandler.meanTeamNone.replace("{aliveCount}", gray.getPlayers().size() + "");
                        }
                    }

                    if (white.getTargetHeadBlock() != null && white.getTargetHeadBlock().getBlock() != null) {
                        Material blockType = white.getTargetHeadBlock().getBlock().getType();
                        if (blockType != null && blockType == bed) {
                            whiteTeamStat = MainConfigHandler.meanTeamBedYes.replace("{aliveCount}", white.getPlayers().size() + "");
                        } else if (white.getPlayers().size() >= 1) {
                            whiteTeamStat = MainConfigHandler.meanTeamBedNo.replace("{aliveCount}", white.getPlayers().size() + "");
                        } else {
                            whiteTeamStat = MainConfigHandler.meanTeamNone.replace("{aliveCount}", white.getPlayers().size() + "");
                        }
                    }


                    if (redName.equals(playerTeam.getColor().name())) {
                        redTeamIsMe = meanYou.replace("{teamColor}", playerTeam.getChatColor().toString());
                    } else {
                        redTeamIsMe = meanNotYou.replace("{teamColor}", playerTeam.getChatColor().toString());
                    }

                    if (blueName.equals(playerTeam.getColor().name())) {
                        blueTeamIsMe = meanYou.replace("{teamColor}", playerTeam.getChatColor().toString());
                    } else {
                        blueTeamIsMe = meanNotYou.replace("{teamColor}", playerTeam.getChatColor().toString());
                    }

                    if (greenName.equals(playerTeam.getColor().name())) {
                        greenTeamIsMe = meanYou.replace("{teamColor}", playerTeam.getChatColor().toString());
                    } else {
                        greenTeamIsMe = meanNotYou.replace("{teamColor}", playerTeam.getChatColor().toString());
                    }

                    if (yellowName.equals(playerTeam.getColor().name())) {
                        yellowTeamIsMe = meanYou.replace("{teamColor}", playerTeam.getChatColor().toString());
                    } else {
                        yellowTeamIsMe = meanNotYou.replace("{teamColor}", playerTeam.getChatColor().toString());
                    }

                    if (pinkName.equals(playerTeam.getColor().name())) {
                        pinkTeamIsMe = meanYou.replace("{teamColor}", playerTeam.getChatColor().toString());
                    } else {
                        pinkTeamIsMe = meanNotYou.replace("{teamColor}", playerTeam.getChatColor().toString());
                    }

                    if (aquaName.equals(playerTeam.getColor().name())) {
                        aquaTeamIsMe = meanYou.replace("{teamColor}", playerTeam.getChatColor().toString());
                    } else {
                        aquaTeamIsMe = meanNotYou.replace("{teamColor}", playerTeam.getChatColor().toString());
                    }

                    if (grayName.equals(playerTeam.getColor().name())) {
                        grayTeamIsMe = meanYou.replace("{teamColor}", playerTeam.getChatColor().toString());
                    } else {
                        grayTeamIsMe = meanNotYou.replace("{teamColor}", playerTeam.getChatColor().toString());
                    }

                    if (whiteName.equals(playerTeam.getColor().name())) {
                        whiteTeamIsMe = meanYou.replace("{teamColor}", playerTeam.getChatColor().toString());
                    } else {
                        whiteTeamIsMe = meanNotYou.replace("{teamColor}", playerTeam.getChatColor().toString());
                    }

                    int redTeamPlayer = 0;
                    int blueTeamPlayer = 0;
                    int greenTeamPlayer = 0;
                    int yellowTeamPlayer = 0;

                    if (red.getPlayers() != null && red.getPlayers().size() >= 1){
                        redTeamPlayer = red.getPlayers().size();
                    }

                    if (blue.getPlayers() != null && blue.getPlayers().size() >= 1){
                        blueTeamPlayer = blue.getPlayers().size();
                    }

                    if (green.getPlayers() != null && green.getPlayers().size() >= 1){
                        greenTeamPlayer = green.getPlayers().size();
                    }

                    if (yellow.getPlayers() != null && yellow.getPlayers().size() >= 1){
                        yellowTeamPlayer = yellow.getPlayers().size();
                    }

                    int pinkTeamPlayer = 0;
                    int aquaTeamPlayer = 0;
                    int grayTeamPlayer = 0;
                    int whiteTeamPlayer = 0;

                    if (pink.getPlayers() != null && pink.getPlayers().size() >= 1){
                        pinkTeamPlayer = pink.getPlayers().size();
                    }

                    if (aqua.getPlayers() != null && aqua.getPlayers().size() >= 1){
                        aquaTeamPlayer = aqua.getPlayers().size();
                    }

                    if (gray.getPlayers() != null && gray.getPlayers().size() >= 1){
                        grayTeamPlayer = gray.getPlayers().size();
                    }


                    if (white.getPlayers() != null && white.getPlayers().size() >= 1){
                        whiteTeamPlayer = white.getPlayers().size();
                    }

                    Map<Integer, String> ScoreBoard2v2LineReal = new HashMap<>();

                    for (Map.Entry<Integer, String> entry : ScoreBoard2v2Line.entrySet()) {
                        int score = entry.getKey();
                        String string = entry.getValue();

                        String stringReal = string
                                .replace("{PinkTeamName}", PinkTeamName)
                                .replace("{AquaTeamName}", AquaTeamName)
                                .replace("{GrayTeamName}", GrayTeamName)
                                .replace("{WhiteTeamName}", WhiteTeamName)

                                .replace("{pinkTeamStat}", pinkTeamStat)
                                .replace("{aquaTeamStat}", aquaTeamStat)
                                .replace("{grayTeamStat}", grayTeamStat)
                                .replace("{whiteTeamStat}", whiteTeamStat)

                                .replace("{pinkTeamIsMe}", pinkTeamIsMe)
                                .replace("{aquaTeamIsMe}", aquaTeamIsMe)
                                .replace("{grayTeamIsMe}", grayTeamIsMe)
                                .replace("{whiteTeamIsMe}", whiteTeamIsMe)

                                .replace("{pinkTeamPlayer}",pinkTeamPlayer + "")
                                .replace("{aquaTeamPlayer}",aquaTeamPlayer + "")
                                .replace("{grayTeamPlayer}",grayTeamPlayer + "")
                                .replace("{whiteTeamPlayer}",whiteTeamPlayer + "")

                                .replace("{RedTeamName}", RedTeamName)
                                .replace("{BlueTeamName}", BlueTeamName)
                                .replace("{GreenTeamName}", GreenTeamName)
                                .replace("{YellowTeamName}", YellowTeamName)

                                .replace("{redTeamStat}", redTeamStat)
                                .replace("{blueTeamStat}", blueTeamStat)
                                .replace("{greenTeamStat}", greenTeamStat)
                                .replace("{yellowTeamStat}", yellowTeamStat)

                                .replace("{redTeamIsMe}", redTeamIsMe)
                                .replace("{blueTeamIsMe}", blueTeamIsMe)
                                .replace("{greenTeamIsMe}", greenTeamIsMe)
                                .replace("{yellowTeamIsMe}", yellowTeamIsMe)

                                .replace("{redTeamPlayer}",redTeamPlayer + "")
                                .replace("{blueTeamPlayer}",blueTeamPlayer + "")
                                .replace("{greenTeamPlayer}",greenTeamPlayer + "")
                                .replace("{yellowTeamPlayer}",yellowTeamPlayer + "")

                                .replace("{timeleft-s}", gameTimeLeft + "")
                                .replace("{timeleft-m}", gameTimeLeft / 60 + "")
                                .replace("{timeleft-h}", gameTimeLeft / 3600 + "")
                                .replace("{timeleft-d}", currentDate.getDayOfMonth() + "")
                                .replace("{timeleft-mo}", currentDate.getMonthValue() + "")
                                .replace("{timeleft-y}", currentDate.getYear() + "")
                                .replace("{date}", formattedDate)
                                .replace("{ip}", serverIp)
                                .replace("{bw}", MainConfigHandler.meanBedwars)
                                .replace("{mode}", MainConfigHandler.mean2v2Mode)
                                .replace("{kill}", RelCurrentStat.playerKill.get(player).toString())
                                .replace("{fkill}", RelCurrentStat.playerFKill.get(player).toString())
                                .replace("{death}", RelCurrentStat.playerDeath.get(player).toString())
                                .replace("{bed}", RelCurrentStat.playerBreakBed.get(player).toString())
                                .replace("{kd}", RelCurrentStat.playerKD.get(player).toString())
                                .replace("{game}",game.getName())
                                .replace("{region}",game.getRegionName())
                                .replace("{world}",game.getRegion().getWorld().getName())
                                ;


                        ScoreBoard2v2LineReal.put(score, stringReal);
                    }


                    String RealScoreBoard2v2Title = t(ScoreBoard2v2LineReal.get(100));
                    String RealScoreBoard2v2Line01 = t(ScoreBoard2v2LineReal.get(1));
                    String RealScoreBoard2v2Line02 = t(ScoreBoard2v2LineReal.get(2));
                    String RealScoreBoard2v2Line03 = t(ScoreBoard2v2LineReal.get(3));
                    String RealScoreBoard2v2Line04 = t(ScoreBoard2v2LineReal.get(4));
                    String RealScoreBoard2v2Line05 = t(ScoreBoard2v2LineReal.get(5));
                    String RealScoreBoard2v2Line06 = t(ScoreBoard2v2LineReal.get(6));
                    String RealScoreBoard2v2Line07 = t(ScoreBoard2v2LineReal.get(7));
                    String RealScoreBoard2v2Line08 = t(ScoreBoard2v2LineReal.get(8));
                    String RealScoreBoard2v2Line09 = t(ScoreBoard2v2LineReal.get(9));
                    String RealScoreBoard2v2Line10 = t(ScoreBoard2v2LineReal.get(10));
                    String RealScoreBoard2v2Line11 = t(ScoreBoard2v2LineReal.get(11));
                    String RealScoreBoard2v2Line12 = t(ScoreBoard2v2LineReal.get(12));
                    String RealScoreBoard2v2Line13 = t(ScoreBoard2v2LineReal.get(13));
                    String RealScoreBoard2v2Line14 = t(ScoreBoard2v2LineReal.get(14));
                    String RealScoreBoard2v2Line15 = t(ScoreBoard2v2LineReal.get(15));
                    String RealScoreBoard2v2Line16 = t(ScoreBoard2v2LineReal.get(16));

                    if (!RealScoreBoard2v2Title.isEmpty()){
                        objective.setDisplayName(t(RealScoreBoard2v2Title));
                    }else {
                        objective.setDisplayName(" ");
                    }
                    if (!RealScoreBoard2v2Line01.isEmpty()) objective.getScore(RealScoreBoard2v2Line01).setScore(1);
                    if (!RealScoreBoard2v2Line02.isEmpty()) objective.getScore(RealScoreBoard2v2Line02).setScore(2);
                    if (!RealScoreBoard2v2Line03.isEmpty()) objective.getScore(RealScoreBoard2v2Line03).setScore(3);
                    if (!RealScoreBoard2v2Line04.isEmpty()) objective.getScore(RealScoreBoard2v2Line04).setScore(4);
                    if (!RealScoreBoard2v2Line05.isEmpty()) objective.getScore(RealScoreBoard2v2Line05).setScore(5);
                    if (!RealScoreBoard2v2Line06.isEmpty()) objective.getScore(RealScoreBoard2v2Line06).setScore(6);
                    if (!RealScoreBoard2v2Line07.isEmpty()) objective.getScore(RealScoreBoard2v2Line07).setScore(7);
                    if (!RealScoreBoard2v2Line08.isEmpty()) objective.getScore(RealScoreBoard2v2Line08).setScore(8);
                    if (!RealScoreBoard2v2Line09.isEmpty()) objective.getScore(RealScoreBoard2v2Line09).setScore(9);
                    if (!RealScoreBoard2v2Line10.isEmpty()) objective.getScore(RealScoreBoard2v2Line10).setScore(10);
                    if (!RealScoreBoard2v2Line11.isEmpty()) objective.getScore(RealScoreBoard2v2Line11).setScore(11);
                    if (!RealScoreBoard2v2Line12.isEmpty()) objective.getScore(RealScoreBoard2v2Line12).setScore(12);
                    if (!RealScoreBoard2v2Line13.isEmpty()) objective.getScore(RealScoreBoard2v2Line13).setScore(13);
                    if (!RealScoreBoard2v2Line14.isEmpty()) objective.getScore(RealScoreBoard2v2Line14).setScore(14);
                    if (!RealScoreBoard2v2Line15.isEmpty()) objective.getScore(RealScoreBoard2v2Line15).setScore(15);
                    if (!RealScoreBoard2v2Line16.isEmpty()) objective.getScore(RealScoreBoard2v2Line16).setScore(16);

                    objective.setDisplaySlot(DisplaySlot.SIDEBAR);
                    player.setScoreboard(scoreboard);

                } else if (worldname.contains(MainConfigHandler.rushWorld4v4)) {
                    // 4v4 ScoreBoard


                    int gameTimeLeft = game.getTimeLeft();
                    String redTeamStat = "redTeamStat: ERROR";
                    String yellowTeamStat = "yellowTeamStat: ERROR";
                    String greenTeamStat = "greenTeamStat: ERROR";
                    String blueTeamStat = "blueTeamStat: ERROR";

                    String redTeamIsMe;
                    String yellowTeamIsMe;
                    String greenTeamIsMe;
                    String blueTeamIsMe;

                    Team red = game.getTeam(RedTeamName);
                    Team blue = game.getTeam(BlueTeamName);
                    Team green = game.getTeam(GreenTeamName);
                    Team yellow = game.getTeam(YellowTeamName);
                    Team playerTeam = game.getPlayerTeam(player);

                    Scoreboard scoreboard = mgr.getNewScoreboard();
                    Objective objective = scoreboard.registerNewObjective("4v4", "dummy");

                    if (green.getTargetHeadBlock() != null && green.getTargetHeadBlock().getBlock() != null) {
                        Material blockType = green.getTargetHeadBlock().getBlock().getType();
                        if (blockType != null && blockType == bed) {
                            greenTeamStat = MainConfigHandler.meanTeamBedYes.replace("{aliveCount}", green.getPlayers().size() + "");
                        } else if (green.getPlayers().size() >= 1) {
                            greenTeamStat = MainConfigHandler.meanTeamBedNo.replace("{aliveCount}", green.getPlayers().size() + "");
                        } else {
                            greenTeamStat = MainConfigHandler.meanTeamNone.replace("{aliveCount}", green.getPlayers().size() + "");
                        }
                    }

                    if (red.getTargetHeadBlock() != null && red.getTargetHeadBlock().getBlock() != null) {
                        Material blockType = red.getTargetHeadBlock().getBlock().getType();
                        if (blockType != null && blockType == bed) {
                            redTeamStat = MainConfigHandler.meanTeamBedYes.replace("{aliveCount}", red.getPlayers().size() + "");
                        } else if (red.getPlayers().size() >= 1) {
                            redTeamStat = MainConfigHandler.meanTeamBedNo.replace("{aliveCount}", red.getPlayers().size() + "");
                        } else {
                            redTeamStat = MainConfigHandler.meanTeamNone.replace("{aliveCount}", red.getPlayers().size() + "");
                        }
                    }

                    if (blue.getTargetHeadBlock() != null && blue.getTargetHeadBlock().getBlock() != null) {
                        Material blockType = blue.getTargetHeadBlock().getBlock().getType();
                        if (blockType != null && blockType == bed) {
                            blueTeamStat = MainConfigHandler.meanTeamBedYes.replace("{aliveCount}", blue.getPlayers().size() + "");
                        } else if (blue.getPlayers().size() >= 1) {
                            blueTeamStat = MainConfigHandler.meanTeamBedNo.replace("{aliveCount}", blue.getPlayers().size() + "");
                        } else {
                            blueTeamStat = MainConfigHandler.meanTeamNone.replace("{aliveCount}", blue.getPlayers().size() + "");
                        }
                    }

                    if (yellow.getTargetHeadBlock() != null && yellow.getTargetHeadBlock().getBlock() != null) {
                        Material blockType = yellow.getTargetHeadBlock().getBlock().getType();
                        if (blockType != null && blockType == bed) {
                            yellowTeamStat = MainConfigHandler.meanTeamBedYes.replace("{aliveCount}", yellow.getPlayers().size() + "");
                        } else if (yellow.getPlayers().size() >= 1) {
                            yellowTeamStat = MainConfigHandler.meanTeamBedNo.replace("{aliveCount}", yellow.getPlayers().size() + "");
                        } else {
                            yellowTeamStat = MainConfigHandler.meanTeamNone.replace("{aliveCount}", yellow.getPlayers().size() + "");
                        }
                    }


                    if (redName.equals(playerTeam.getColor().name())) {
                        redTeamIsMe = meanYou.replace("{teamColor}", playerTeam.getChatColor().toString());
                    } else {
                        redTeamIsMe = meanNotYou.replace("{teamColor}", playerTeam.getChatColor().toString());
                    }

                    if (blueName.equals(playerTeam.getColor().name())) {
                        blueTeamIsMe = meanYou.replace("{teamColor}", playerTeam.getChatColor().toString());
                    } else {
                        blueTeamIsMe = meanNotYou.replace("{teamColor}", playerTeam.getChatColor().toString());
                    }

                    if (greenName.equals(playerTeam.getColor().name())) {
                        greenTeamIsMe = meanYou.replace("{teamColor}", playerTeam.getChatColor().toString());
                    } else {
                        greenTeamIsMe = meanNotYou.replace("{teamColor}", playerTeam.getChatColor().toString());
                    }

                    if (yellowName.equals(playerTeam.getColor().name())) {
                        yellowTeamIsMe = meanYou.replace("{teamColor}", playerTeam.getChatColor().toString());
                    } else {
                        yellowTeamIsMe = meanNotYou.replace("{teamColor}", playerTeam.getChatColor().toString());
                    }

                    int redTeamPlayer = 0;
                    int blueTeamPlayer = 0;
                    int greenTeamPlayer = 0;
                    int yellowTeamPlayer = 0;

                    if (red.getPlayers() != null && red.getPlayers().size() >= 1){
                        redTeamPlayer = red.getPlayers().size();
                    }

                    if (blue.getPlayers() != null && blue.getPlayers().size() >= 1){
                        blueTeamPlayer = blue.getPlayers().size();
                    }

                    if (green.getPlayers() != null && green.getPlayers().size() >= 1){
                        greenTeamPlayer = green.getPlayers().size();
                    }

                    if (yellow.getPlayers() != null && yellow.getPlayers().size() >= 1){
                        yellowTeamPlayer = yellow.getPlayers().size();
                    }

                    Map<Integer, String> ScoreBoard4v4LineReal = new HashMap<>();

                    for (Map.Entry<Integer, String> entry : ScoreBoard4v4Line.entrySet()) {
                        int score = entry.getKey();
                        String string = entry.getValue();

                        String stringReal = string

                                .replace("{RedTeamName}", RedTeamName)
                                .replace("{BlueTeamName}", BlueTeamName)
                                .replace("{GreenTeamName}", GreenTeamName)
                                .replace("{YellowTeamName}", YellowTeamName)

                                .replace("{redTeamStat}", redTeamStat)
                                .replace("{blueTeamStat}", blueTeamStat)
                                .replace("{greenTeamStat}", greenTeamStat)
                                .replace("{yellowTeamStat}", yellowTeamStat)

                                .replace("{redTeamIsMe}", redTeamIsMe)
                                .replace("{blueTeamIsMe}", blueTeamIsMe)
                                .replace("{greenTeamIsMe}", greenTeamIsMe)
                                .replace("{yellowTeamIsMe}", yellowTeamIsMe)

                                .replace("{redTeamPlayer}",redTeamPlayer + "")
                                .replace("{blueTeamPlayer}",blueTeamPlayer + "")
                                .replace("{greenTeamPlayer}",greenTeamPlayer + "")
                                .replace("{yellowTeamPlayer}",yellowTeamPlayer + "")

                                .replace("{timeleft-s}", gameTimeLeft + "")
                                .replace("{timeleft-m}", gameTimeLeft / 60 + "")
                                .replace("{timeleft-h}", gameTimeLeft / 3600 + "")
                                .replace("{timeleft-d}", currentDate.getDayOfMonth() + "")
                                .replace("{timeleft-mo}", currentDate.getMonthValue() + "")
                                .replace("{timeleft-y}", currentDate.getYear() + "")
                                .replace("{date}", formattedDate)
                                .replace("{ip}", serverIp)
                                .replace("{bw}", MainConfigHandler.meanBedwars)
                                .replace("{mode}", MainConfigHandler.mean2v2Mode)
                                .replace("{kill}", RelCurrentStat.playerKill.get(player).toString())
                                .replace("{fkill}", RelCurrentStat.playerFKill.get(player).toString())
                                .replace("{death}", RelCurrentStat.playerDeath.get(player).toString())
                                .replace("{bed}", RelCurrentStat.playerBreakBed.get(player).toString())
                                .replace("{kd}", RelCurrentStat.playerKD.get(player).toString())
                                .replace("{game}",game.getName())
                                .replace("{region}",game.getRegionName())
                                .replace("{world}",game.getRegion().getWorld().getName())
                                ;
                        ScoreBoard4v4LineReal.put(score, stringReal);
                    }

                    String RealScoreBoard4v4Title = t(ScoreBoard4v4LineReal.get(100));
                    String RealScoreBoard4v4Line01 = t(ScoreBoard4v4LineReal.get(1));
                    String RealScoreBoard4v4Line02 = t(ScoreBoard4v4LineReal.get(2));
                    String RealScoreBoard4v4Line03 = t(ScoreBoard4v4LineReal.get(3));
                    String RealScoreBoard4v4Line04 = t(ScoreBoard4v4LineReal.get(4));
                    String RealScoreBoard4v4Line05 = t(ScoreBoard4v4LineReal.get(5));
                    String RealScoreBoard4v4Line06 = t(ScoreBoard4v4LineReal.get(6));
                    String RealScoreBoard4v4Line07 = t(ScoreBoard4v4LineReal.get(7));
                    String RealScoreBoard4v4Line08 = t(ScoreBoard4v4LineReal.get(8));
                    String RealScoreBoard4v4Line09 = t(ScoreBoard4v4LineReal.get(9));
                    String RealScoreBoard4v4Line10 = t(ScoreBoard4v4LineReal.get(10));
                    String RealScoreBoard4v4Line11 = t(ScoreBoard4v4LineReal.get(11));
                    String RealScoreBoard4v4Line12 = t(ScoreBoard4v4LineReal.get(12));
                    String RealScoreBoard4v4Line13 = t(ScoreBoard4v4LineReal.get(13));
                    String RealScoreBoard4v4Line14 = t(ScoreBoard4v4LineReal.get(14));
                    String RealScoreBoard4v4Line15 = t(ScoreBoard4v4LineReal.get(15));
                    String RealScoreBoard4v4Line16 = t(ScoreBoard4v4LineReal.get(16));

                    if (!RealScoreBoard4v4Title.isEmpty()){
                        objective.setDisplayName(t(RealScoreBoard4v4Title));
                    }else {
                        objective.setDisplayName(" ");
                    }
                    if (!RealScoreBoard4v4Line01.isEmpty()) objective.getScore(RealScoreBoard4v4Line01).setScore(1);
                    if (!RealScoreBoard4v4Line02.isEmpty()) objective.getScore(RealScoreBoard4v4Line02).setScore(2);
                    if (!RealScoreBoard4v4Line03.isEmpty()) objective.getScore(RealScoreBoard4v4Line03).setScore(3);
                    if (!RealScoreBoard4v4Line04.isEmpty()) objective.getScore(RealScoreBoard4v4Line04).setScore(4);
                    if (!RealScoreBoard4v4Line05.isEmpty()) objective.getScore(RealScoreBoard4v4Line05).setScore(5);
                    if (!RealScoreBoard4v4Line06.isEmpty()) objective.getScore(RealScoreBoard4v4Line06).setScore(6);
                    if (!RealScoreBoard4v4Line07.isEmpty()) objective.getScore(RealScoreBoard4v4Line07).setScore(7);
                    if (!RealScoreBoard4v4Line08.isEmpty()) objective.getScore(RealScoreBoard4v4Line08).setScore(8);
                    if (!RealScoreBoard4v4Line09.isEmpty()) objective.getScore(RealScoreBoard4v4Line09).setScore(9);
                    if (!RealScoreBoard4v4Line10.isEmpty()) objective.getScore(RealScoreBoard4v4Line10).setScore(10);
                    if (!RealScoreBoard4v4Line11.isEmpty()) objective.getScore(RealScoreBoard4v4Line11).setScore(11);
                    if (!RealScoreBoard4v4Line12.isEmpty()) objective.getScore(RealScoreBoard4v4Line12).setScore(12);
                    if (!RealScoreBoard4v4Line13.isEmpty()) objective.getScore(RealScoreBoard4v4Line13).setScore(13);
                    if (!RealScoreBoard4v4Line14.isEmpty()) objective.getScore(RealScoreBoard4v4Line14).setScore(14);
                    if (!RealScoreBoard4v4Line15.isEmpty()) objective.getScore(RealScoreBoard4v4Line15).setScore(15);
                    if (!RealScoreBoard4v4Line16.isEmpty()) objective.getScore(RealScoreBoard4v4Line16).setScore(16);

                    objective.setDisplaySlot(DisplaySlot.SIDEBAR);
                    player.setScoreboard(scoreboard);

                } else {
                    Scoreboard scoreboard = game.getScoreboard();
                    player.setScoreboard(scoreboard);
                }
            }
        }
    }
}
