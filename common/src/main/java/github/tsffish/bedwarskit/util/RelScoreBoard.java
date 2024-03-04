package github.tsffish.bedwarskit.util;

import github.tsffish.bedwarskit.util.misc.GetBlockType;
import github.tsffish.bedwarskit.util.task.game.*;
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
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


import static github.tsffish.bedwarskit.config.main.MainConfigHandler.*;
import static github.tsffish.bedwarskit.config.task.TaskConfigHandler.*;
import static github.tsffish.bedwarskit.util.RelCurrentStat.*;
import static github.tsffish.bedwarskit.util.RelTeamColorName.*;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;
import static github.tsffish.bedwarskit.util.misc.MathUtil.formatTime;
import static github.tsffish.bedwarskit.util.misc.MessSender.le;
import static github.tsffish.bedwarskit.util.misc.PluginState.isBungeeMode;


public class RelScoreBoard {
    private static final Material bed = GetBlockType.BED_BLOCK();
    private static ConcurrentHashMap<Integer, String> taskMap;
    private static final GameState waiting = GameState.WAITING;
    private static final GameState running = GameState.RUNNING;
    private static final DisplaySlot sidebar = DisplaySlot.SIDEBAR;
    private static final String scoreBoardMode = "dummy";
    private static final String scoreBoardName2v2 = "2v2";
    private static final String scoreBoardName4v4 = "4v4";
    public static void updateScoreBoard(Game game) {

        if (bed == null) {
            le("RelScoreBoard", "can't get bed Material");
            return;
        }

        GameManager gameManager = BedwarsRel.getInstance().getGameManager();
        for (Player player : game.getPlayers()) {
            UUID uuid = player.getUniqueId();

            if (gameManager.getGameOfPlayer(player) == game) {

                if (game.getState() == running) {

                    String gameName = game.getName();
                    String worldname = game.getRegion().getWorld().getName();
                    String regionName = game.getRegionName();
                    int gameTimeLeft = game.getTimeLeft();
                    String formatTimeLeft = formatTime(gameTimeLeft);

                    String lastTaskName = "";
                    String lastTaskTimeLeftText = "";


                    if (taskMap == null) {
                        taskMap = new ConcurrentHashMap<>(42);
                    } else {
                        taskMap.clear();
                    }

                    if (gametask_spawntime_tasks_iron1 > 0 && Iron1.getTaskTimeLeft(gameName) > 0) {
                        taskMap.put(Iron1.getTaskTimeLeft(gameName), gametask_name_iron1);
                    }
                    if (gametask_spawntime_tasks_iron2 > 0 && Iron2.getTaskTimeLeft(gameName) > 0) {
                        taskMap.put(Iron2.getTaskTimeLeft(gameName), gametask_name_iron2);
                    }
                    if (gametask_spawntime_tasks_iron3 > 0 && Iron3.getTaskTimeLeft(gameName) > 0) {
                        taskMap.put(Iron3.getTaskTimeLeft(gameName), gametask_name_iron3);
                    }
                    if (gametask_spawntime_tasks_iron4 > 0 && Iron4.getTaskTimeLeft(gameName) > 0) {
                        taskMap.put(Iron4.getTaskTimeLeft(gameName), gametask_name_iron4);
                    }

                    if (gametask_spawntime_tasks_gold1 > 0 && Gold1.getTaskTimeLeft(gameName) > 0) {
                        taskMap.put(Gold1.getTaskTimeLeft(gameName), gametask_name_gold1);
                    }
                    if (gametask_spawntime_tasks_gold2 > 0 && Gold2.getTaskTimeLeft(gameName) > 0) {
                        taskMap.put(Gold2.getTaskTimeLeft(gameName), gametask_name_gold2);
                    }
                    if (gametask_spawntime_tasks_gold3 > 0 && Gold3.getTaskTimeLeft(gameName) > 0) {
                        taskMap.put(Gold3.getTaskTimeLeft(gameName), gametask_name_gold3);
                    }
                    if (gametask_spawntime_tasks_gold4 > 0 && Gold4.getTaskTimeLeft(gameName) > 0) {
                        taskMap.put(Gold4.getTaskTimeLeft(gameName), gametask_name_gold4);
                    }

                    if (gametask_spawntime_tasks_diamond1 > 0 && Diamond1.getTaskTimeLeft(gameName) > 0) {
                        taskMap.put(Diamond1.getTaskTimeLeft(gameName), gametask_name_diamond1);
                    }
                    if (gametask_spawntime_tasks_diamond2 > 0 && Diamond2.getTaskTimeLeft(gameName) > 0) {
                        taskMap.put(Diamond2.getTaskTimeLeft(gameName), gametask_name_diamond2);
                    }
                    if (gametask_spawntime_tasks_diamond3 > 0 && Diamond3.getTaskTimeLeft(gameName) > 0) {
                        taskMap.put(Diamond3.getTaskTimeLeft(gameName), gametask_name_diamond3);
                    }
                    if (gametask_spawntime_tasks_diamond4 > 0 && Diamond4.getTaskTimeLeft(gameName) > 0) {
                        taskMap.put(Diamond4.getTaskTimeLeft(gameName), gametask_name_diamond4);
                    }
                    if (gametask_spawntime_tasks_emerald1 > 0 && Emerald1.getTaskTimeLeft(gameName) > 0) {
                        taskMap.put(Emerald1.getTaskTimeLeft(gameName), gametask_name_emerald1);
                    }
                    if (gametask_spawntime_tasks_emerald2 > 0 && Emerald2.getTaskTimeLeft(gameName) > 0) {
                        taskMap.put(Emerald2.getTaskTimeLeft(gameName), gametask_name_emerald2);
                    }
                    if (gametask_spawntime_tasks_emerald3 > 0 && Emerald3.getTaskTimeLeft(gameName) > 0) {
                        taskMap.put(Emerald3.getTaskTimeLeft(gameName), gametask_name_emerald3);
                    }
                    if (gametask_spawntime_tasks_emerald4 > 0 && Emerald4.getTaskTimeLeft(gameName) > 0) {
                        taskMap.put(Emerald4.getTaskTimeLeft(gameName), gametask_name_emerald4);
                    }

                    if (gametask_finalbattle_time > 0 && FinalBattle.getTaskTimeLeft(gameName) > 0) {
                        taskMap.put(FinalBattle.getTaskTimeLeft(gameName), gametask_name_finalbattle);
                    }


                    if (!taskMap.isEmpty()) {
                        int minValue = Integer.MAX_VALUE;
                        String minTaskName = null;

                        for (Map.Entry<Integer, String> entry : taskMap.entrySet()) {
                            Integer key = entry.getKey();
                            String taskName = entry.getValue();
                            if (game.getState() != running) {
                                return;
                            }

                            if (key < minValue) {
                                minValue = key;
                                minTaskName = taskName;
                            }
                        }

                        if (minTaskName != null) {
                            if (minTaskName.equals(meanGameEnd)) {
                                lastTaskTimeLeftText = t(formatTimeLeft);
                            } else {
                                lastTaskTimeLeftText = minValue + t(meanSecond);
                            }
                            lastTaskName = t(minTaskName);
                        }
                    } else {
                        int minValue = 0;
                        lastTaskTimeLeftText = minValue + t(formatTime(gameTimeLeft));
                        String minTaskName = t(meanGameEnd);
                        lastTaskName = t(minTaskName);
                    }
                    LocalDate currentDate = LocalDate.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                    String formattedDate = currentDate.format(formatter);

                    boolean isInRushWorld = worldname.contains(rushWorld);
                    if (isInRushWorld || isBungeeMode()) {

                        boolean isInRush2v2 = worldname.contains(rushWorld2v2);
                        boolean isInRush4v4 = worldname.contains(rushWorld4v4);

                        String redTeamName = relTeamName_Red;
                        String yellowTeamName = relTeamName_Yellow;
                        String greenTeamName = relTeamName_Green;
                        String blueTeamName = relTeamName_Blue;

                        String pinkTeamName = relTeamName_Pink;
                        String aquaTeamName = relTeamName_Aqua;
                        String grayTeamName = relTeamName_Gray;
                        String whiteTeamName = relTeamName_White;

                        Team red = game.getTeam(redTeamName);
                        Team blue = game.getTeam(blueTeamName);
                        Team green = game.getTeam(greenTeamName);
                        Team yellow = game.getTeam(yellowTeamName);

                        Team pink = game.getTeam(pinkTeamName);
                        Team aqua = game.getTeam(aquaTeamName);
                        Team gray = game.getTeam(grayTeamName);
                        Team white = game.getTeam(whiteTeamName);

                        Team playerTeam = game.getPlayerTeam(player);

                        int pinkTeamPlayer = 0;
                        int aquaTeamPlayer = 0;
                        int grayTeamPlayer = 0;
                        int whiteTeamPlayer = 0;

                        int redTeamPlayer = 0;
                        int blueTeamPlayer = 0;
                        int greenTeamPlayer = 0;
                        int yellowTeamPlayer = 0;

                        String redTeamNameShort = String.valueOf(redTeamName.charAt(0));
                        String blueTeamNameShort = String.valueOf(blueTeamName.charAt(0));
                        String greenTeamNameShort = String.valueOf(greenTeamName.charAt(0));
                        String yellowTeamNameShort = String.valueOf(yellowTeamName.charAt(0));

                        String pinkTeamNameShort = String.valueOf(pinkTeamName.charAt(0));
                        String aquaTeamNameShort = String.valueOf(aquaTeamName.charAt(0));
                        String whiteTeamNameShort = String.valueOf(whiteTeamName.charAt(0));
                        String grayTeamNameShort = String.valueOf(grayTeamName.charAt(0));

                        String redTeamStat = "";
                        String yellowTeamStat = "";
                        String greenTeamStat = "";
                        String blueTeamStat = "";

                        String pinkTeamStat = "";
                        String aquaTeamStat = "";
                        String whiteTeamStat = "";
                        String grayTeamStat = "";

                        String redTeamIsMe;
                        String yellowTeamIsMe;
                        String greenTeamIsMe;
                        String blueTeamIsMe;

                        String pinkTeamIsMe;
                        String aquaTeamIsMe;
                        String whiteTeamIsMe;
                        String grayTeamIsMe;


                        if (green != null && green.getTargetHeadBlock() != null && green.getTargetHeadBlock().getBlock() != null) {
                            Material blockType = green.getTargetHeadBlock().getBlock().getType();
                            if (blockType != null && blockType == bed) {
                                greenTeamStat = meanTeamBedYes.replace("{aliveCount}", green.getPlayers().size() + "");
                            } else if (!green.getPlayers().isEmpty()) {
                                greenTeamStat = meanTeamBedNo.replace("{aliveCount}", green.getPlayers().size() + "");
                            } else {
                                greenTeamStat = meanTeamNone.replace("{aliveCount}", green.getPlayers().size() + "");
                            }
                        }

                        if (red != null && red.getTargetHeadBlock() != null && red.getTargetHeadBlock().getBlock() != null) {
                            Material blockType = red.getTargetHeadBlock().getBlock().getType();
                            if (blockType != null && blockType == bed) {
                                redTeamStat = meanTeamBedYes.replace("{aliveCount}", red.getPlayers().size() + "");
                            } else if (!red.getPlayers().isEmpty()) {
                                redTeamStat = meanTeamBedNo.replace("{aliveCount}", red.getPlayers().size() + "");
                            } else {
                                redTeamStat = meanTeamNone.replace("{aliveCount}", red.getPlayers().size() + "");
                            }
                        }

                        if (blue != null && blue.getTargetHeadBlock() != null && blue.getTargetHeadBlock().getBlock() != null) {
                            Material blockType = blue.getTargetHeadBlock().getBlock().getType();
                            if (blockType != null && blockType == bed) {
                                blueTeamStat = meanTeamBedYes.replace("{aliveCount}", blue.getPlayers().size() + "");
                            } else if (!blue.getPlayers().isEmpty()) {
                                blueTeamStat = meanTeamBedNo.replace("{aliveCount}", blue.getPlayers().size() + "");
                            } else {
                                blueTeamStat = meanTeamNone.replace("{aliveCount}", blue.getPlayers().size() + "");
                            }
                        }

                        if (yellow != null && yellow.getTargetHeadBlock() != null && yellow.getTargetHeadBlock().getBlock() != null) {
                            Material blockType = yellow.getTargetHeadBlock().getBlock().getType();
                            if (blockType != null && blockType == bed) {
                                yellowTeamStat = meanTeamBedYes.replace("{aliveCount}", yellow.getPlayers().size() + "");
                            } else if (!yellow.getPlayers().isEmpty()) {
                                yellowTeamStat = meanTeamBedNo.replace("{aliveCount}", yellow.getPlayers().size() + "");
                            } else {
                                yellowTeamStat = meanTeamNone.replace("{aliveCount}", yellow.getPlayers().size() + "");
                            }
                        }


                        if (pink != null && pink.getTargetHeadBlock() != null && pink.getTargetHeadBlock().getBlock() != null) {
                            Material blockType = pink.getTargetHeadBlock().getBlock().getType();
                            if (blockType != null && blockType == bed) {
                                pinkTeamStat = meanTeamBedYes.replace("{aliveCount}", pink.getPlayers().size() + "");
                            } else if (!pink.getPlayers().isEmpty()) {
                                pinkTeamStat = meanTeamBedNo.replace("{aliveCount}", pink.getPlayers().size() + "");
                            } else {
                                pinkTeamStat = meanTeamNone.replace("{aliveCount}", pink.getPlayers().size() + "");
                            }
                        }

                        if (aqua != null && aqua.getTargetHeadBlock() != null && aqua.getTargetHeadBlock().getBlock() != null) {
                            Material blockType = aqua.getTargetHeadBlock().getBlock().getType();
                            if (blockType != null && blockType == bed) {
                                aquaTeamStat = meanTeamBedYes.replace("{aliveCount}", aqua.getPlayers().size() + "");
                            } else if (!aqua.getPlayers().isEmpty()) {
                                aquaTeamStat = meanTeamBedNo.replace("{aliveCount}", aqua.getPlayers().size() + "");
                            } else {
                                aquaTeamStat = meanTeamNone.replace("{aliveCount}", aqua.getPlayers().size() + "");
                            }
                        }

                        if (gray != null && gray.getTargetHeadBlock() != null && gray.getTargetHeadBlock().getBlock() != null) {
                            Material blockType = gray.getTargetHeadBlock().getBlock().getType();
                            if (blockType != null && blockType == bed) {
                                grayTeamStat = meanTeamBedYes.replace("{aliveCount}", gray.getPlayers().size() + "");
                            } else if (!gray.getPlayers().isEmpty()) {
                                grayTeamStat = meanTeamBedNo.replace("{aliveCount}", gray.getPlayers().size() + "");
                            } else {
                                grayTeamStat = meanTeamNone.replace("{aliveCount}", gray.getPlayers().size() + "");
                            }
                        }

                        if (white != null && white.getTargetHeadBlock() != null && white.getTargetHeadBlock().getBlock() != null) {
                            Material blockType = white.getTargetHeadBlock().getBlock().getType();
                            if (blockType != null && blockType == bed) {
                                whiteTeamStat = meanTeamBedYes.replace("{aliveCount}", white.getPlayers().size() + "");
                            } else if (!white.getPlayers().isEmpty()) {
                                whiteTeamStat = meanTeamBedNo.replace("{aliveCount}", white.getPlayers().size() + "");
                            } else {
                                whiteTeamStat = meanTeamNone.replace("{aliveCount}", white.getPlayers().size() + "");
                            }
                        }

                        if (RED_TEAM_COLOR_NAME.equals(playerTeam.getColor().name())) {
                            redTeamIsMe = meanYou.replace("{teamColor}", playerTeam.getChatColor().toString());
                        } else {
                            redTeamIsMe = meanNotYou.replace("{teamColor}", playerTeam.getChatColor().toString());
                        }

                        if (BLUE_TEAM_COLOR_NAME.equals(playerTeam.getColor().name())) {
                            blueTeamIsMe = meanYou.replace("{teamColor}", playerTeam.getChatColor().toString());
                        } else {
                            blueTeamIsMe = meanNotYou.replace("{teamColor}", playerTeam.getChatColor().toString());
                        }

                        if (GREEN_TEAM_COLOR_NAME.equals(playerTeam.getColor().name())) {
                            greenTeamIsMe = meanYou.replace("{teamColor}", playerTeam.getChatColor().toString());
                        } else {
                            greenTeamIsMe = meanNotYou.replace("{teamColor}", playerTeam.getChatColor().toString());
                        }

                        if (YELLOW_TEAM_COLOR_NAME.equals(playerTeam.getColor().name())) {
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

                        if (red != null && red.getPlayers() != null && !red.getPlayers().isEmpty()) {
                            redTeamPlayer = red.getPlayers().size();
                        }

                        if (blue != null && blue.getPlayers() != null && !blue.getPlayers().isEmpty()) {
                            blueTeamPlayer = blue.getPlayers().size();
                        }

                        if (green != null && green.getPlayers() != null && !green.getPlayers().isEmpty()) {
                            greenTeamPlayer = green.getPlayers().size();
                        }

                        if (yellow != null && yellow.getPlayers() != null && !yellow.getPlayers().isEmpty()) {
                            yellowTeamPlayer = yellow.getPlayers().size();
                        }

                        if (pink != null && pink.getPlayers() != null && !pink.getPlayers().isEmpty()) {
                            pinkTeamPlayer = pink.getPlayers().size();
                        }

                        if (aqua != null && aqua.getPlayers() != null && !aqua.getPlayers().isEmpty()) {
                            aquaTeamPlayer = aqua.getPlayers().size();
                        }

                        if (gray != null && gray.getPlayers() != null && !gray.getPlayers().isEmpty()) {
                            grayTeamPlayer = gray.getPlayers().size();
                        }

                        if (white != null && white.getPlayers() != null && !white.getPlayers().isEmpty()) {
                            whiteTeamPlayer = white.getPlayers().size();
                        }

                        ScoreboardManager mgr = Bukkit.getScoreboardManager();

                        if (isInRush2v2 || bungeeMode.equals("2v2")) {

                            Scoreboard scoreboard = mgr.getNewScoreboard();
                            Objective objective = scoreboard.registerNewObjective(scoreBoardName2v2, scoreBoardMode);

                            Map<Integer, String> ScoreBoard2v2LineReal = new HashMap<>(18);


                            for (Map.Entry<Integer, String> entry : ScoreBoard2v2Line.entrySet()) {
                                int score = entry.getKey();
                                String string = entry.getValue();

                                String stringReal = string
                                        .replace("{pinkTeamNameShort}", pinkTeamNameShort)
                                        .replace("{whiteTeamNameShort}", whiteTeamNameShort)
                                        .replace("{aquaTeamNameShort}", aquaTeamNameShort)
                                        .replace("{grayTeamNameShort}", grayTeamNameShort)

                                        .replace("{blueTeamNameShort}", blueTeamNameShort)
                                        .replace("{redTeamNameShort}", redTeamNameShort)
                                        .replace("{yellowTeamNameShort}", yellowTeamNameShort)
                                        .replace("{greenTeamNameShort}", greenTeamNameShort)

                                        .replace("{pinkTeamName}", aquaTeamName)
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

                                        .replace("{pinkTeamPlayer}", pinkTeamPlayer + "")
                                        .replace("{aquaTeamPlayer}", aquaTeamPlayer + "")
                                        .replace("{grayTeamPlayer}", grayTeamPlayer + "")
                                        .replace("{whiteTeamPlayer}", whiteTeamPlayer + "")

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

                                        .replace("{redTeamPlayer}", redTeamPlayer + "")
                                        .replace("{blueTeamPlayer}", blueTeamPlayer + "")
                                        .replace("{greenTeamPlayer}", greenTeamPlayer + "")
                                        .replace("{yellowTeamPlayer}", yellowTeamPlayer + "")

                                        .replace("{timeleft-s}", gameTimeLeft + "")
                                        .replace("{timeleft-m}", gameTimeLeft / 60 + "")
                                        .replace("{timeleft-h}", gameTimeLeft / 3600 + "")
                                        .replace("{timeleft-d}", currentDate.getDayOfMonth() + "")
                                        .replace("{timeleft-mo}", currentDate.getMonthValue() + "")
                                        .replace("{timeleft-y}", currentDate.getYear() + "")
                                        .replace("{date}", formattedDate)
                                        .replace("{ip}", serverIp != null ? serverIp : "")
                                        .replace("{bw}", meanBedwars != null ? meanBedwars : "")
                                        .replace("{mode}", mean2v2Mode != null ? mean2v2Mode : "")
                                        .replace("{kill}", getPlayerKill(uuid) + "")
                                        .replace("{fkill}", getPlayerFinalKill(uuid) + "")
                                        .replace("{death}", getPlayerDeath(uuid) + "")
                                        .replace("{bed}", getPlayerBreakBed(uuid) + "")
                                        .replace("{kd}", getPlayerKD(uuid) + "")
                                        .replace("{game}", gameName != null ? gameName : "")
                                        .replace("{region}", regionName != null ? regionName : "")
                                        .replace("{world}", worldname)
                                        .replace("{lastTaskName}", lastTaskName)
                                        .replace("{lastTaskTimeLeft}", lastTaskTimeLeftText)
                                        .replace("{formatTimeLeft}", formatTimeLeft);
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

                            if (!RealScoreBoard2v2Title.isEmpty()) {
                                objective.setDisplayName(t(RealScoreBoard2v2Title));
                            } else {
                                objective.setDisplayName(" ");
                            }
                            if (!RealScoreBoard2v2Line01.isEmpty())
                                objective.getScore(RealScoreBoard2v2Line01).setScore(1);
                            if (!RealScoreBoard2v2Line02.isEmpty())
                                objective.getScore(RealScoreBoard2v2Line02).setScore(2);
                            if (!RealScoreBoard2v2Line03.isEmpty())
                                objective.getScore(RealScoreBoard2v2Line03).setScore(3);
                            if (!RealScoreBoard2v2Line04.isEmpty())
                                objective.getScore(RealScoreBoard2v2Line04).setScore(4);
                            if (!RealScoreBoard2v2Line05.isEmpty())
                                objective.getScore(RealScoreBoard2v2Line05).setScore(5);
                            if (!RealScoreBoard2v2Line06.isEmpty())
                                objective.getScore(RealScoreBoard2v2Line06).setScore(6);
                            if (!RealScoreBoard2v2Line07.isEmpty())
                                objective.getScore(RealScoreBoard2v2Line07).setScore(7);
                            if (!RealScoreBoard2v2Line08.isEmpty())
                                objective.getScore(RealScoreBoard2v2Line08).setScore(8);
                            if (!RealScoreBoard2v2Line09.isEmpty())
                                objective.getScore(RealScoreBoard2v2Line09).setScore(9);
                            if (!RealScoreBoard2v2Line10.isEmpty())
                                objective.getScore(RealScoreBoard2v2Line10).setScore(10);
                            if (!RealScoreBoard2v2Line11.isEmpty())
                                objective.getScore(RealScoreBoard2v2Line11).setScore(11);
                            if (!RealScoreBoard2v2Line12.isEmpty())
                                objective.getScore(RealScoreBoard2v2Line12).setScore(12);
                            if (!RealScoreBoard2v2Line13.isEmpty())
                                objective.getScore(RealScoreBoard2v2Line13).setScore(13);
                            if (!RealScoreBoard2v2Line14.isEmpty())
                                objective.getScore(RealScoreBoard2v2Line14).setScore(14);
                            if (!RealScoreBoard2v2Line15.isEmpty())
                                objective.getScore(RealScoreBoard2v2Line15).setScore(15);
                            if (!RealScoreBoard2v2Line16.isEmpty())
                                objective.getScore(RealScoreBoard2v2Line16).setScore(16);

                            objective.setDisplaySlot(sidebar);
                            player.setScoreboard(scoreboard);
                        } else if (isInRush4v4 || bungeeMode.equals("4v4")) {

                            Scoreboard scoreboard = mgr.getNewScoreboard();
                            Objective objective = scoreboard.registerNewObjective(scoreBoardName4v4, scoreBoardMode);

                            Map<Integer, String> ScoreBoard4v4LineReal = new HashMap<>(18);


                            for (Map.Entry<Integer, String> entry : ScoreBoard4v4Line.entrySet()) {
                                int score = entry.getKey();
                                String string = entry.getValue();

                                String stringReal = string
                                        .replace("{pinkTeamNameShort}", pinkTeamNameShort)
                                        .replace("{whiteTeamNameShort}", whiteTeamNameShort)
                                        .replace("{aquaTeamNameShort}", aquaTeamNameShort)
                                        .replace("{grayTeamNameShort}", grayTeamNameShort)

                                        .replace("{blueTeamNameShort}", blueTeamNameShort)
                                        .replace("{redTeamNameShort}", redTeamNameShort)
                                        .replace("{yellowTeamNameShort}", yellowTeamNameShort)
                                        .replace("{greenTeamNameShort}", greenTeamNameShort)

                                        .replace("{pinkTeamName}", aquaTeamName)
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

                                        .replace("{pinkTeamPlayer}", pinkTeamPlayer + "")
                                        .replace("{aquaTeamPlayer}", aquaTeamPlayer + "")
                                        .replace("{grayTeamPlayer}", grayTeamPlayer + "")
                                        .replace("{whiteTeamPlayer}", whiteTeamPlayer + "")

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

                                        .replace("{redTeamPlayer}", redTeamPlayer + "")
                                        .replace("{blueTeamPlayer}", blueTeamPlayer + "")
                                        .replace("{greenTeamPlayer}", greenTeamPlayer + "")
                                        .replace("{yellowTeamPlayer}", yellowTeamPlayer + "")

                                        .replace("{timeleft-s}", gameTimeLeft + "")
                                        .replace("{timeleft-m}", gameTimeLeft / 60 + "")
                                        .replace("{timeleft-h}", gameTimeLeft / 3600 + "")
                                        .replace("{timeleft-d}", currentDate.getDayOfMonth() + "")
                                        .replace("{timeleft-mo}", currentDate.getMonthValue() + "")
                                        .replace("{timeleft-y}", currentDate.getYear() + "")
                                        .replace("{date}", formattedDate)
                                        .replace("{ip}", serverIp != null ? serverIp : "")
                                        .replace("{bw}", meanBedwars != null ? meanBedwars : "")
                                        .replace("{mode}", mean4v4Mode != null ? mean4v4Mode : "")
                                        .replace("{kill}", getPlayerKill(uuid) + "")
                                        .replace("{fkill}", getPlayerFinalKill(uuid) + "")
                                        .replace("{death}", getPlayerDeath(uuid) + "")
                                        .replace("{bed}", getPlayerBreakBed(uuid) + "")
                                        .replace("{kd}", getPlayerKD(uuid) + "")
                                        .replace("{game}", gameName != null ? gameName : "")
                                        .replace("{region}", regionName != null ? regionName : "")
                                        .replace("{world}", worldname)
                                        .replace("{lastTaskName}", lastTaskName)
                                        .replace("{lastTaskTimeLeft}", lastTaskTimeLeftText)
                                        .replace("{formatTimeLeft}", formatTimeLeft);
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

                            if (!RealScoreBoard4v4Title.isEmpty()) {
                                objective.setDisplayName(t(RealScoreBoard4v4Title));
                            } else {
                                objective.setDisplayName(" ");
                            }
                            if (!RealScoreBoard4v4Line01.isEmpty())
                                objective.getScore(RealScoreBoard4v4Line01).setScore(1);
                            if (!RealScoreBoard4v4Line02.isEmpty())
                                objective.getScore(RealScoreBoard4v4Line02).setScore(2);
                            if (!RealScoreBoard4v4Line03.isEmpty())
                                objective.getScore(RealScoreBoard4v4Line03).setScore(3);
                            if (!RealScoreBoard4v4Line04.isEmpty())
                                objective.getScore(RealScoreBoard4v4Line04).setScore(4);
                            if (!RealScoreBoard4v4Line05.isEmpty())
                                objective.getScore(RealScoreBoard4v4Line05).setScore(5);
                            if (!RealScoreBoard4v4Line06.isEmpty())
                                objective.getScore(RealScoreBoard4v4Line06).setScore(6);
                            if (!RealScoreBoard4v4Line07.isEmpty())
                                objective.getScore(RealScoreBoard4v4Line07).setScore(7);
                            if (!RealScoreBoard4v4Line08.isEmpty())
                                objective.getScore(RealScoreBoard4v4Line08).setScore(8);
                            if (!RealScoreBoard4v4Line09.isEmpty())
                                objective.getScore(RealScoreBoard4v4Line09).setScore(9);
                            if (!RealScoreBoard4v4Line10.isEmpty())
                                objective.getScore(RealScoreBoard4v4Line10).setScore(10);
                            if (!RealScoreBoard4v4Line11.isEmpty())
                                objective.getScore(RealScoreBoard4v4Line11).setScore(11);
                            if (!RealScoreBoard4v4Line12.isEmpty())
                                objective.getScore(RealScoreBoard4v4Line12).setScore(12);
                            if (!RealScoreBoard4v4Line13.isEmpty())
                                objective.getScore(RealScoreBoard4v4Line13).setScore(13);
                            if (!RealScoreBoard4v4Line14.isEmpty())
                                objective.getScore(RealScoreBoard4v4Line14).setScore(14);
                            if (!RealScoreBoard4v4Line15.isEmpty())
                                objective.getScore(RealScoreBoard4v4Line15).setScore(15);
                            if (!RealScoreBoard4v4Line16.isEmpty())
                                objective.getScore(RealScoreBoard4v4Line16).setScore(16);

                            objective.setDisplaySlot(sidebar);
                            player.setScoreboard(scoreboard);

                        } else if (game.getState() == waiting) {
                            Scoreboard scoreboard = game.getScoreboard();
                            player.setScoreboard(scoreboard);
                        }
                    }
                } else if (game.getState() == waiting) {
                    Scoreboard scoreboard = game.getScoreboard();
                    player.setScoreboard(scoreboard);
                }
            }else if (game.getState() == waiting) {
                Scoreboard scoreboard = game.getScoreboard();
                player.setScoreboard(scoreboard);
            }
        }
    }
}