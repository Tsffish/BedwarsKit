package github.tsffish.bedwarskit.util;

import github.tsffish.bedwarskit.Main;
import github.tsffish.bedwarskit.config.MainConfigHandler;
import github.tsffish.bedwarskit.util.gametask.*;
import github.tsffish.bedwarskit.util.misc.SecondToTime;
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
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static github.tsffish.bedwarskit.Main.console;
import static github.tsffish.bedwarskit.config.MainConfigHandler.*;
import static github.tsffish.bedwarskit.util.RelTeamColorName.*;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;

public class RelScoreBoard {


    public static Map<Integer, String> taskMap;
    public static String lastTaskName = "";
    public static int lastTaskTimeLeft;
    public static String LastTaskTimeLeftText;
    public static int gameTimeLeft;
    public static String formatTimeLeft;
    public static void updateScoreBoard(Player player) {

        GameManager gm = BedwarsRel.getInstance().getGameManager();
        Game game = gm.getGameOfPlayer(player);
        if (game != null && game.getState() == GameState.RUNNING) {
            gameTimeLeft = game.getTimeLeft();
            formatTimeLeft = SecondToTime.formatSecondsToTimeString(gameTimeLeft);
            String gameName = game.getName();
            int gameTimeLeft = game.getTimeLeft();
            String formatTimeLeft = SecondToTime.formatSecondsToTimeString(gameTimeLeft);

            if (taskMap == null) {
                taskMap = new HashMap<>(18);


            }else {
                taskMap.clear();
            }


            if (gametask_spawntime_tasks_iron1 > 0) {
                taskMap.put(TaskIron1.getTaskTimeLeft(gameName), gametask_name_iron1);
            }
            if (gametask_spawntime_tasks_iron2 > 0) {
                taskMap.put(TaskIron2.getTaskTimeLeft(gameName), gametask_name_iron2);
            }
            if (gametask_spawntime_tasks_iron3 > 0) {
                taskMap.put(TaskIron3.getTaskTimeLeft(gameName), gametask_name_iron3);
            }
            if (gametask_spawntime_tasks_iron4 > 0) {
                taskMap.put(TaskIron4.getTaskTimeLeft(gameName), gametask_name_iron4);
            }

            if (gametask_spawntime_tasks_gold1 > 0) {
                taskMap.put(TaskGold1.getTaskTimeLeft(gameName), gametask_name_gold1);
            }
            if (gametask_spawntime_tasks_gold2 > 0) {
                taskMap.put(TaskGold2.getTaskTimeLeft(gameName), gametask_name_gold2);
            }
            if (gametask_spawntime_tasks_gold3 > 0) {
                taskMap.put(TaskGold3.getTaskTimeLeft(gameName), gametask_name_gold3);
            }
            if (gametask_spawntime_tasks_gold4 > 0) {
                taskMap.put(TaskGold4.getTaskTimeLeft(gameName), gametask_name_gold4);
            }


            if (gametask_spawntime_tasks_diamond1 > 0) {
                taskMap.put(TaskDiamond1.getTaskTimeLeft(gameName), gametask_name_diamond1);
            }
            if (gametask_spawntime_tasks_diamond2 > 0) {
                taskMap.put(TaskDiamond2.getTaskTimeLeft(gameName), gametask_name_diamond2);
            }
            if (gametask_spawntime_tasks_diamond3 > 0) {
                taskMap.put(TaskDiamond3.getTaskTimeLeft(gameName), gametask_name_diamond3);
            }
            if (gametask_spawntime_tasks_diamond4 > 0) {
                taskMap.put(TaskDiamond4.getTaskTimeLeft(gameName), gametask_name_diamond4);
            }
            if (gametask_spawntime_tasks_emerald1 > 0) {
                taskMap.put(TaskEmerald1.getTaskTimeLeft(gameName), gametask_name_emerald1);
            }
            if (gametask_spawntime_tasks_emerald2 > 0) {
                taskMap.put(TaskEmerald2.getTaskTimeLeft(gameName), gametask_name_emerald2);
            }
            if (gametask_spawntime_tasks_emerald3 > 0) {
                taskMap.put(TaskEmerald3.getTaskTimeLeft(gameName), gametask_name_emerald3);
            }
            if (gametask_spawntime_tasks_emerald4 > 0) {
                taskMap.put(TaskEmerald4.getTaskTimeLeft(gameName), gametask_name_emerald4);
            }
            if (gametask_finalbattle_time > 0) {
                taskMap.put(TaskFinalBattle.getTaskTimeLeft(gameName), gametask_name_finalbattle);
            }

            if (!taskMap.isEmpty()) {
                if (Main.isDebug) {
                    for (String s : taskMap.values()) {
                        console.sendMessage(s);
                    }
                }
                int minVaule = 0;
                String minTaskName = " ";
                int maxValue = Integer.MAX_VALUE;
                synchronized (taskMap) {
                    Iterator<Map.Entry<Integer, String>> iterator = taskMap.entrySet().iterator();
                    while (iterator.hasNext()) {
                        Map.Entry<Integer, String> entry = iterator.next();
                        Integer key = entry.getKey();
                        String taskName = entry.getValue();
                        if (game.getState() != GameState.RUNNING) return;

                        if (key < minVaule || key < maxValue) {
                            minVaule = key;
                            minTaskName = taskName;
                            if (minTaskName.equals(meanGameEnd)){
                                LastTaskTimeLeftText = t(formatTimeLeft);
                            } else {
                                LastTaskTimeLeftText = minVaule + t(meanSecond);
                            }
                            lastTaskName = t(minTaskName);
                        }
                        iterator.remove();
                    }
                }


            } else {
                int minVaule = 0;
                LastTaskTimeLeftText = minVaule + t(meanSecond);
                String minTaskName = t(meanGameEnd);
                lastTaskName = t(minTaskName);
            }

            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            String formattedDate = currentDate.format(formatter);

            String worldname = game.getRegion().getWorld().getName();
            if (worldname.contains(MainConfigHandler.rushWorld)) {
                Material bed = Material.BED_BLOCK;

                String redTeamName = relTeamName_Red;
                String yellowTeamName = relTeamName_Yellow;
                String greenTeamName = relTeamName_Green;
                String blueTeamName = relTeamName_Blue;

                String redName = RED_TEAM_COLOR_NAME;
                String blueName = BLUE_TEAM_COLOR_NAME;
                String yellowName = YELLOW_TEAM_COLOR_NAME;
                String greenName = GREEN_TEAM_COLOR_NAME;

                ScoreboardManager mgr = Bukkit.getScoreboardManager();

                if (worldname.contains(MainConfigHandler.rushWorld2v2)) {
                    

                    String pinkTeamName = relTeamName_Pink;
                    String aquaTeamName = relTeamName_Aqua;
                    String grayTeamName = relTeamName_Gray;
                    String whiteTeamName = relTeamName_White;

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

                    Team red = game.getTeam(redTeamName);
                    Team blue = game.getTeam(blueTeamName);
                    Team green = game.getTeam(greenTeamName);
                    Team yellow = game.getTeam(yellowTeamName);

                    Team pink = game.getTeam(pinkTeamName);
                    Team aqua = game.getTeam(aquaTeamName);
                    Team gray = game.getTeam(grayTeamName);
                    Team white = game.getTeam(whiteTeamName);

                    Team playerTeam = game.getPlayerTeam(player);

                    Scoreboard scoreboard = mgr.getNewScoreboard();
                    Objective objective = scoreboard.registerNewObjective("2v2", "dummy");

                    if (green.getTargetHeadBlock() != null && green.getTargetHeadBlock().getBlock() != null) {
                        Material blockType = green.getTargetHeadBlock().getBlock().getType();
                        if (blockType != null && blockType == bed) {
                            greenTeamStat = MainConfigHandler.meanTeamBedYes.replace("{aliveCount}", green.getPlayers().size() + "");
                        } else if (green.getPlayers().size() > 0) {
                            greenTeamStat = MainConfigHandler.meanTeamBedNo.replace("{aliveCount}", green.getPlayers().size() + "");
                        } else {
                            greenTeamStat = MainConfigHandler.meanTeamNone.replace("{aliveCount}", green.getPlayers().size() + "");
                        }
                    }

                    if (red.getTargetHeadBlock() != null && red.getTargetHeadBlock().getBlock() != null) {
                        Material blockType = red.getTargetHeadBlock().getBlock().getType();
                        if (blockType != null && blockType == bed) {
                            redTeamStat = MainConfigHandler.meanTeamBedYes.replace("{aliveCount}", red.getPlayers().size() + "");
                        } else if (red.getPlayers().size() > 0) {
                            redTeamStat = MainConfigHandler.meanTeamBedNo.replace("{aliveCount}", red.getPlayers().size() + "");
                        } else {
                            redTeamStat = MainConfigHandler.meanTeamNone.replace("{aliveCount}", red.getPlayers().size() + "");
                        }
                    }

                    if (blue.getTargetHeadBlock() != null && blue.getTargetHeadBlock().getBlock() != null) {
                        Material blockType = blue.getTargetHeadBlock().getBlock().getType();
                        if (blockType != null && blockType == bed) {
                            blueTeamStat = MainConfigHandler.meanTeamBedYes.replace("{aliveCount}", blue.getPlayers().size() + "");
                        } else if (blue.getPlayers().size() > 0) {
                            blueTeamStat = MainConfigHandler.meanTeamBedNo.replace("{aliveCount}", blue.getPlayers().size() + "");
                        } else {
                            blueTeamStat = MainConfigHandler.meanTeamNone.replace("{aliveCount}", blue.getPlayers().size() + "");
                        }
                    }

                    if (yellow.getTargetHeadBlock() != null && yellow.getTargetHeadBlock().getBlock() != null) {
                        Material blockType = yellow.getTargetHeadBlock().getBlock().getType();
                        if (blockType != null && blockType == bed) {
                            yellowTeamStat = MainConfigHandler.meanTeamBedYes.replace("{aliveCount}", yellow.getPlayers().size() + "");
                        } else if (yellow.getPlayers().size() > 0) {
                            yellowTeamStat = MainConfigHandler.meanTeamBedNo.replace("{aliveCount}", yellow.getPlayers().size() + "");
                        } else {
                            yellowTeamStat = MainConfigHandler.meanTeamNone.replace("{aliveCount}", yellow.getPlayers().size() + "");
                        }
                    }


                    if (pink.getTargetHeadBlock() != null && pink.getTargetHeadBlock().getBlock() != null) {
                        Material blockType = pink.getTargetHeadBlock().getBlock().getType();
                        if (blockType != null && blockType == bed) {
                            pinkTeamStat = MainConfigHandler.meanTeamBedYes.replace("{aliveCount}", pink.getPlayers().size() + "");
                        } else if (pink.getPlayers().size() > 0) {
                            pinkTeamStat = MainConfigHandler.meanTeamBedNo.replace("{aliveCount}", pink.getPlayers().size() + "");
                        } else {
                            pinkTeamStat = MainConfigHandler.meanTeamNone.replace("{aliveCount}", pink.getPlayers().size() + "");
                        }
                    }

                    if (aqua.getTargetHeadBlock() != null && aqua.getTargetHeadBlock().getBlock() != null) {
                        Material blockType = aqua.getTargetHeadBlock().getBlock().getType();
                        if (blockType != null && blockType == bed) {
                            aquaTeamStat = MainConfigHandler.meanTeamBedYes.replace("{aliveCount}", aqua.getPlayers().size() + "");
                        } else if (aqua.getPlayers().size() > 0) {
                            aquaTeamStat = MainConfigHandler.meanTeamBedNo.replace("{aliveCount}", aqua.getPlayers().size() + "");
                        } else {
                            aquaTeamStat = MainConfigHandler.meanTeamNone.replace("{aliveCount}", aqua.getPlayers().size() + "");
                        }
                    }

                    if (gray.getTargetHeadBlock() != null && gray.getTargetHeadBlock().getBlock() != null) {
                        Material blockType = gray.getTargetHeadBlock().getBlock().getType();
                        if (blockType != null && blockType == bed) {
                            grayTeamStat = MainConfigHandler.meanTeamBedYes.replace("{aliveCount}", gray.getPlayers().size() + "");
                        } else if (gray.getPlayers().size() > 0) {
                            grayTeamStat = MainConfigHandler.meanTeamBedNo.replace("{aliveCount}", gray.getPlayers().size() + "");
                        } else {
                            grayTeamStat = MainConfigHandler.meanTeamNone.replace("{aliveCount}", gray.getPlayers().size() + "");
                        }
                    }

                    if (white.getTargetHeadBlock() != null && white.getTargetHeadBlock().getBlock() != null) {
                        Material blockType = white.getTargetHeadBlock().getBlock().getType();
                        if (blockType != null && blockType == bed) {
                            whiteTeamStat = MainConfigHandler.meanTeamBedYes.replace("{aliveCount}", white.getPlayers().size() + "");
                        } else if (white.getPlayers().size() > 0) {
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

                    if (PINK_TEAM_COLOR_NAME.equals(playerTeam.getColor().name())) {
                        pinkTeamIsMe = meanYou.replace("{teamColor}", playerTeam.getChatColor().toString());
                    } else {
                        pinkTeamIsMe = meanNotYou.replace("{teamColor}", playerTeam.getChatColor().toString());
                    }

                    if (AQUA_TEAM_COLOR_NAME.equals(playerTeam.getColor().name())) {
                        aquaTeamIsMe = meanYou.replace("{teamColor}", playerTeam.getChatColor().toString());
                    } else {
                        aquaTeamIsMe = meanNotYou.replace("{teamColor}", playerTeam.getChatColor().toString());
                    }

                    if (GRAY_TEAM_COLOR_NAME.equals(playerTeam.getColor().name())) {
                        grayTeamIsMe = meanYou.replace("{teamColor}", playerTeam.getChatColor().toString());
                    } else {
                        grayTeamIsMe = meanNotYou.replace("{teamColor}", playerTeam.getChatColor().toString());
                    }

                    if (WHITE_TEAM_COLOR_NAME.equals(playerTeam.getColor().name())) {
                        whiteTeamIsMe = meanYou.replace("{teamColor}", playerTeam.getChatColor().toString());
                    } else {
                        whiteTeamIsMe = meanNotYou.replace("{teamColor}", playerTeam.getChatColor().toString());
                    }

                    int redTeamPlayer = 0;
                    int blueTeamPlayer = 0;
                    int greenTeamPlayer = 0;
                    int yellowTeamPlayer = 0;

                    if (red.getPlayers() != null && red.getPlayers().size() > 0){
                        redTeamPlayer = red.getPlayers().size();
                    }

                    if (blue.getPlayers() != null && blue.getPlayers().size() > 0){
                        blueTeamPlayer = blue.getPlayers().size();
                    }

                    if (green.getPlayers() != null && green.getPlayers().size() > 0){
                        greenTeamPlayer = green.getPlayers().size();
                    }

                    if (yellow.getPlayers() != null && yellow.getPlayers().size() > 0){
                        yellowTeamPlayer = yellow.getPlayers().size();
                    }

                    int pinkTeamPlayer = 0;
                    int aquaTeamPlayer = 0;
                    int grayTeamPlayer = 0;
                    int whiteTeamPlayer = 0;

                    if (pink.getPlayers() != null && pink.getPlayers().size() > 0){
                        pinkTeamPlayer = pink.getPlayers().size();
                    }

                    if (aqua.getPlayers() != null && aqua.getPlayers().size() > 0){
                        aquaTeamPlayer = aqua.getPlayers().size();
                    }

                    if (gray.getPlayers() != null && gray.getPlayers().size() > 0){
                        grayTeamPlayer = gray.getPlayers().size();
                    }


                    if (white.getPlayers() != null && white.getPlayers().size() > 0){
                        whiteTeamPlayer = white.getPlayers().size();
                    }

                    Map<Integer, String> ScoreBoard2v2LineReal = new HashMap<>(17);

                    for (Map.Entry<Integer, String> entry : ScoreBoard2v2Line.entrySet()) {
                        int score = entry.getKey();
                        String string = entry.getValue();

                        String stringReal = string
                                .replace("{pinkTeamName}", pinkTeamName)
                                .replace("{aquaTeamName}", aquaTeamName)
                                .replace("{grayTeamName}", grayTeamName)
                                .replace("{whiteTeamName}", whiteTeamName)

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

                                .replace("{redTeamName}", redTeamName)
                                .replace("{blueTeamName}", blueTeamName)
                                .replace("{greenTeamName}", greenTeamName)
                                .replace("{yellowTeamName}", yellowTeamName)

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
                                .replace("{LastTaskName}",lastTaskName)
                                .replace("{LastTaskTimeLeft}", LastTaskTimeLeftText)
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
                    

                    String redTeamStat = "redTeamStat: ERROR";
                    String yellowTeamStat = "yellowTeamStat: ERROR";
                    String greenTeamStat = "greenTeamStat: ERROR";
                    String blueTeamStat = "blueTeamStat: ERROR";

                    String redTeamIsMe;
                    String yellowTeamIsMe;
                    String greenTeamIsMe;
                    String blueTeamIsMe;

                    Team red = game.getTeam(redTeamName);
                    Team blue = game.getTeam(blueTeamName);
                    Team green = game.getTeam(greenTeamName);
                    Team yellow = game.getTeam(yellowTeamName);
                    Team playerTeam = game.getPlayerTeam(player);

                    Scoreboard scoreboard = mgr.getNewScoreboard();
                    Objective objective = scoreboard.registerNewObjective("4v4", "dummy");

                    if (green.getTargetHeadBlock() != null && green.getTargetHeadBlock().getBlock() != null) {
                        Material blockType = green.getTargetHeadBlock().getBlock().getType();
                        if (blockType != null && blockType == bed) {
                            greenTeamStat = MainConfigHandler.meanTeamBedYes.replace("{aliveCount}", green.getPlayers().size() + "");
                        } else if (green.getPlayers().size() > 0) {
                            greenTeamStat = MainConfigHandler.meanTeamBedNo.replace("{aliveCount}", green.getPlayers().size() + "");
                        } else {
                            greenTeamStat = MainConfigHandler.meanTeamNone.replace("{aliveCount}", green.getPlayers().size() + "");
                        }
                    }

                    if (red.getTargetHeadBlock() != null && red.getTargetHeadBlock().getBlock() != null) {
                        Material blockType = red.getTargetHeadBlock().getBlock().getType();
                        if (blockType != null && blockType == bed) {
                            redTeamStat = MainConfigHandler.meanTeamBedYes.replace("{aliveCount}", red.getPlayers().size() + "");
                        } else if (red.getPlayers().size() > 0) {
                            redTeamStat = MainConfigHandler.meanTeamBedNo.replace("{aliveCount}", red.getPlayers().size() + "");
                        } else {
                            redTeamStat = MainConfigHandler.meanTeamNone.replace("{aliveCount}", red.getPlayers().size() + "");
                        }
                    }

                    if (blue.getTargetHeadBlock() != null && blue.getTargetHeadBlock().getBlock() != null) {
                        Material blockType = blue.getTargetHeadBlock().getBlock().getType();
                        if (blockType != null && blockType == bed) {
                            blueTeamStat = MainConfigHandler.meanTeamBedYes.replace("{aliveCount}", blue.getPlayers().size() + "");
                        } else if (blue.getPlayers().size() > 0) {
                            blueTeamStat = MainConfigHandler.meanTeamBedNo.replace("{aliveCount}", blue.getPlayers().size() + "");
                        } else {
                            blueTeamStat = MainConfigHandler.meanTeamNone.replace("{aliveCount}", blue.getPlayers().size() + "");
                        }
                    }

                    if (yellow.getTargetHeadBlock() != null && yellow.getTargetHeadBlock().getBlock() != null) {
                        Material blockType = yellow.getTargetHeadBlock().getBlock().getType();
                        if (blockType != null && blockType == bed) {
                            yellowTeamStat = MainConfigHandler.meanTeamBedYes.replace("{aliveCount}", yellow.getPlayers().size() + "");
                        } else if (yellow.getPlayers().size() > 0) {
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

                    if (red.getPlayers() != null && red.getPlayers().size() > 0){
                        redTeamPlayer = red.getPlayers().size();
                    }

                    if (blue.getPlayers() != null && blue.getPlayers().size() > 0){
                        blueTeamPlayer = blue.getPlayers().size();
                    }

                    if (green.getPlayers() != null && green.getPlayers().size() > 0){
                        greenTeamPlayer = green.getPlayers().size();
                    }

                    if (yellow.getPlayers() != null && yellow.getPlayers().size() > 0){
                        yellowTeamPlayer = yellow.getPlayers().size();
                    }

                    Map<Integer, String> ScoreBoard4v4LineReal = new HashMap<>(17);

                    for (Map.Entry<Integer, String> entry : ScoreBoard4v4Line.entrySet()) {
                        int score = entry.getKey();
                        String string = entry.getValue();

                        String stringReal = string

                                .replace("{redTeamName}", redTeamName)
                                .replace("{blueTeamName}", blueTeamName)
                                .replace("{greenTeamName}", greenTeamName)
                                .replace("{yellowTeamName}", yellowTeamName)

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
                                .replace("{LastTaskName}",lastTaskName)
                                .replace("{LastTaskTimeLeft}", LastTaskTimeLeftText)
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
