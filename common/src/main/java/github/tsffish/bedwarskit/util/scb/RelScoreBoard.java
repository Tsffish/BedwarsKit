package github.tsffish.bedwarskit.util.scb;

import github.tsffish.bedwarskit.util.task.*;
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
import static github.tsffish.bedwarskit.config.scb.ScbConfigHandler.*;
import static github.tsffish.bedwarskit.config.task.TaskConfigHandler.*;
import static github.tsffish.bedwarskit.util.PluginState.isDebug;
import static github.tsffish.bedwarskit.util.RelTeamColor.*;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;
import static github.tsffish.bedwarskit.util.misc.MathUtil.formatTime;
import static github.tsffish.bedwarskit.util.PluginState.isBungeeMode;
import static github.tsffish.bedwarskit.util.misc.MessSender.le;
import static github.tsffish.bedwarskit.util.player.RelCurrentStat.*;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class RelScoreBoard {
    private static final Material bed_block = Material.BED_BLOCK;
    private static final GameState waiting = GameState.WAITING;
    private static final GameState running = GameState.RUNNING;
    private static final DisplaySlot sidebar = DisplaySlot.SIDEBAR;
    private static final String scoreBoardMode = "dummy";
    private static final String scoreBoardName2v2 = "2v2";
    private static final String scoreBoardName4v4 = "4v4";

    public static void updateScoreBoard(String gameName) {
        try {
            ConcurrentHashMap<Integer, String> taskMap = new ConcurrentHashMap<>(42);
            GameManager gameManager = BedwarsRel.getInstance().getGameManager();
            Game game = gameManager.getGame(gameName);

            if (game.getState() == running) {

                String worldname = game.getRegion().getWorld().getName();
                String regionName = game.getRegionName();
                int gameTimeLeft = game.getTimeLeft();
                String formatTimeLeft = formatTime(gameTimeLeft);

                String lastTaskName = "";
                String lastTaskTimeLeftText = "";

                if (gametask_spawntime_tasks_iron1 > 0 && TaskIron1.getTaskTimeLeft(gameName) > 0) {
                    taskMap.put(TaskIron1.getTaskTimeLeft(gameName), gametask_name_iron1);
                }
                if (gametask_spawntime_tasks_iron2 > 0 && TaskIron2.getTaskTimeLeft(gameName) > 0) {
                    taskMap.put(TaskIron2.getTaskTimeLeft(gameName), gametask_name_iron2);
                }
                if (gametask_spawntime_tasks_iron3 > 0 && TaskIron3.getTaskTimeLeft(gameName) > 0) {
                    taskMap.put(TaskIron3.getTaskTimeLeft(gameName), gametask_name_iron3);
                }
                if (gametask_spawntime_tasks_iron4 > 0 && TaskIron4.getTaskTimeLeft(gameName) > 0) {
                    taskMap.put(TaskIron4.getTaskTimeLeft(gameName), gametask_name_iron4);
                }

                if (gametask_spawntime_tasks_gold1 > 0 && TaskGold1.getTaskTimeLeft(gameName) > 0) {
                    taskMap.put(TaskGold1.getTaskTimeLeft(gameName), gametask_name_gold1);
                }
                if (gametask_spawntime_tasks_gold2 > 0 && TaskGold2.getTaskTimeLeft(gameName) > 0) {
                    taskMap.put(TaskGold2.getTaskTimeLeft(gameName), gametask_name_gold2);
                }
                if (gametask_spawntime_tasks_gold3 > 0 && TaskGold3.getTaskTimeLeft(gameName) > 0) {
                    taskMap.put(TaskGold3.getTaskTimeLeft(gameName), gametask_name_gold3);
                }
                if (gametask_spawntime_tasks_gold4 > 0 && TaskGold4.getTaskTimeLeft(gameName) > 0) {
                    taskMap.put(TaskGold4.getTaskTimeLeft(gameName), gametask_name_gold4);
                }

                if (gametask_spawntime_tasks_diamond1 > 0 && TaskDiamond1.getTaskTimeLeft(gameName) > 0) {
                    taskMap.put(TaskDiamond1.getTaskTimeLeft(gameName), gametask_name_diamond1);
                }
                if (gametask_spawntime_tasks_diamond2 > 0 && TaskDiamond2.getTaskTimeLeft(gameName) > 0) {
                    taskMap.put(TaskDiamond2.getTaskTimeLeft(gameName), gametask_name_diamond2);
                }
                if (gametask_spawntime_tasks_diamond3 > 0 && TaskDiamond3.getTaskTimeLeft(gameName) > 0) {
                    taskMap.put(TaskDiamond3.getTaskTimeLeft(gameName), gametask_name_diamond3);
                }
                if (gametask_spawntime_tasks_diamond4 > 0 && TaskDiamond4.getTaskTimeLeft(gameName) > 0) {
                    taskMap.put(TaskDiamond4.getTaskTimeLeft(gameName), gametask_name_diamond4);
                }
                if (gametask_spawntime_tasks_emerald1 > 0 && TaskEmerald1.getTaskTimeLeft(gameName) > 0) {
                    taskMap.put(TaskEmerald1.getTaskTimeLeft(gameName), gametask_name_emerald1);
                }
                if (gametask_spawntime_tasks_emerald2 > 0 && TaskEmerald2.getTaskTimeLeft(gameName) > 0) {
                    taskMap.put(TaskEmerald2.getTaskTimeLeft(gameName), gametask_name_emerald2);
                }
                if (gametask_spawntime_tasks_emerald3 > 0 && TaskEmerald3.getTaskTimeLeft(gameName) > 0) {
                    taskMap.put(TaskEmerald3.getTaskTimeLeft(gameName), gametask_name_emerald3);
                }
                if (gametask_spawntime_tasks_emerald4 > 0 && TaskEmerald4.getTaskTimeLeft(gameName) > 0) {
                    taskMap.put(TaskEmerald4.getTaskTimeLeft(gameName), gametask_name_emerald4);
                }

                if (gametask_time_healthset1 > 0 && TaskHealthSet1.getTaskTimeLeft(gameName) > 0) {
                    taskMap.put(TaskHealthSet1.getTaskTimeLeft(gameName), gametask_name_healthset1);
                }
                if (gametask_time_healthset2 > 0 && TaskHealthSet2.getTaskTimeLeft(gameName) > 0) {
                    taskMap.put(TaskHealthSet2.getTaskTimeLeft(gameName), gametask_name_healthset2);
                }
                if (gametask_time_healthset3 > 0 && TaskHealthSet3.getTaskTimeLeft(gameName) > 0) {
                    taskMap.put(TaskHealthSet3.getTaskTimeLeft(gameName), gametask_name_healthset3);
                }
                if (gametask_time_healthset4 > 0 && TaskHealthSet4.getTaskTimeLeft(gameName) > 0) {
                    taskMap.put(TaskHealthSet4.getTaskTimeLeft(gameName), gametask_name_healthset4);
                }

                if (gametask_finalbattle_time > 0 && TaskFinalBattle.getTaskTimeLeft(gameName) > 0) {
                    taskMap.put(TaskFinalBattle.getTaskTimeLeft(gameName), gametask_name_finalbattle);
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

                boolean isInRushWorld = worldname.contains(gameWorld);
                if (isInRushWorld || isBungeeMode()) {

                    boolean isInRush2v2 = worldname.contains(gameWorld2v2);
                    boolean isInRush4v4 = worldname.contains(gameWorld4v4);

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

                    String redTeamIsMe = "";
                    String yellowTeamIsMe = "";
                    String greenTeamIsMe = "";
                    String blueTeamIsMe = "";

                    String pinkTeamIsMe = "";
                    String aquaTeamIsMe = "";
                    String whiteTeamIsMe = "";
                    String grayTeamIsMe = "";

                    String redTeamIsAlive = "";

                    if (red != null) {
                        if (red.isDead(game)) {
                            redTeamIsAlive = meanTeamStat_Dead;
                        } else {
                            redTeamIsAlive = meanTeamStat_Alive;
                        }
                    }

                    String redTeamBedStat = "";

                    if (red != null) {
                        if (red.getTargetHeadBlock().getBlock().getType() != bed_block) {
                            redTeamBedStat = meanBedStat_No;
                        } else {
                            redTeamBedStat = meanBedStat_Yes;
                        }
                    }

                    String greenTeamIsAlive = "";

                    if (green != null) {
                        if (green.isDead(game)) {
                            greenTeamIsAlive = meanTeamStat_Dead;
                        } else {
                            greenTeamIsAlive = meanTeamStat_Alive;
                        }
                    }

                    String greenTeamBedStat = "";

                    if (green != null) {
                        if (green.getTargetHeadBlock().getBlock().getType() != bed_block) {
                            greenTeamBedStat = meanBedStat_No;
                        } else {
                            greenTeamBedStat = meanBedStat_Yes;
                        }
                    }


                    String blueTeamIsAlive = "";

                    if (blue != null) {
                        if (blue.isDead(game)) {
                            blueTeamIsAlive = meanTeamStat_Dead;
                        } else {
                            blueTeamIsAlive = meanTeamStat_Alive;
                        }
                    }

                    String blueTeamBedStat = "";

                    if (blue != null) {
                        if (blue.getTargetHeadBlock().getBlock().getType() != bed_block) {
                            blueTeamBedStat = meanBedStat_No;
                        } else {
                            blueTeamBedStat = meanBedStat_Yes;
                        }
                    }

                    String yellowTeamIsAlive = "";

                    if (yellow != null) {
                        if (yellow.isDead(game)) {
                            yellowTeamIsAlive = meanTeamStat_Dead;
                        } else {
                            yellowTeamIsAlive = meanTeamStat_Alive;
                        }
                    }

                    String yellowTeamBedStat = "";

                    if (yellow != null) {
                        if (yellow.getTargetHeadBlock().getBlock().getType() != bed_block) {
                            yellowTeamBedStat = meanBedStat_No;
                        } else {
                            yellowTeamBedStat = meanBedStat_Yes;
                        }
                    }

                    String aquaTeamIsAlive = "";

                    if (aqua != null) {
                        if (aqua.isDead(game)) {
                            aquaTeamIsAlive = meanTeamStat_Dead;
                        } else {
                            aquaTeamIsAlive = meanTeamStat_Alive;
                        }
                    }

                    String aquaTeamBedStat = "";

                    if (aqua != null) {
                        if (aqua.getTargetHeadBlock().getBlock().getType() != bed_block) {
                            aquaTeamBedStat = meanBedStat_No;
                        } else {
                            aquaTeamBedStat = meanBedStat_Yes;
                        }
                    }

                    String pinkTeamIsAlive = "";

                    if (pink != null) {
                        if (pink.isDead(game)) {
                            pinkTeamIsAlive = meanTeamStat_Dead;
                        } else {
                            pinkTeamIsAlive = meanTeamStat_Alive;
                        }
                    }

                    String pinkTeamBedStat = "";

                    if (pink != null) {
                        if (pink.getTargetHeadBlock().getBlock().getType() != bed_block) {
                            pinkTeamBedStat = meanBedStat_No;
                        } else {
                            pinkTeamBedStat = meanBedStat_Yes;
                        }
                    }

                    String grayTeamIsAlive = "";

                    if (gray != null) {
                        if (gray.isDead(game)) {
                            grayTeamIsAlive = meanTeamStat_Dead;
                        } else {
                            grayTeamIsAlive = meanTeamStat_Alive;
                        }
                    }

                    String grayTeamBedStat = "";

                    if (gray != null) {
                        if (gray.getTargetHeadBlock().getBlock().getType() != bed_block) {
                            grayTeamBedStat = meanBedStat_No;
                        } else {
                            grayTeamBedStat = meanBedStat_Yes;
                        }
                    }
                    String whiteTeamIsAlive = "";

                    if (white != null) {
                        if (white.isDead(game)) {
                            whiteTeamIsAlive = meanTeamStat_Dead;
                        } else {
                            whiteTeamIsAlive = meanTeamStat_Alive;
                        }
                    }

                    String whiteTeamBedStat = "";

                    if (white != null) {
                        if (white.getTargetHeadBlock().getBlock().getType() != bed_block) {
                            whiteTeamBedStat = meanBedStat_No;
                        } else {
                            whiteTeamBedStat = meanBedStat_Yes;
                        }
                    }

                    if (green != null && green.getTargetHeadBlock() != null && green.getTargetHeadBlock().getBlock() != null) {
                        Material blockType = green.getTargetHeadBlock().getBlock().getType();
                        if (blockType != null && blockType == bed_block) {
                            greenTeamStat = teamStat_BedYes.replace("{aliveCount}", green.getPlayers().size() + "");
                        } else if (!green.getPlayers().isEmpty()) {
                            greenTeamStat = teamStat_BedNo.replace("{aliveCount}", green.getPlayers().size() + "");
                        } else {
                            greenTeamStat = teamStat_None.replace("{aliveCount}", green.getPlayers().size() + "");
                        }
                    }

                    if (red != null && red.getTargetHeadBlock() != null && red.getTargetHeadBlock().getBlock() != null) {
                        Material blockType = red.getTargetHeadBlock().getBlock().getType();
                        if (blockType != null && blockType == bed_block) {
                            redTeamStat = teamStat_BedYes.replace("{aliveCount}", red.getPlayers().size() + "");
                        } else if (!red.getPlayers().isEmpty()) {
                            redTeamStat = teamStat_BedNo.replace("{aliveCount}", red.getPlayers().size() + "");
                        } else {
                            redTeamStat = teamStat_None.replace("{aliveCount}", red.getPlayers().size() + "");
                        }
                    }

                    if (blue != null && blue.getTargetHeadBlock() != null && blue.getTargetHeadBlock().getBlock() != null) {
                        Material blockType = blue.getTargetHeadBlock().getBlock().getType();
                        if (blockType != null && blockType == bed_block) {
                            blueTeamStat = teamStat_BedYes.replace("{aliveCount}", blue.getPlayers().size() + "");
                        } else if (!blue.getPlayers().isEmpty()) {
                            blueTeamStat = teamStat_BedNo.replace("{aliveCount}", blue.getPlayers().size() + "");
                        } else {
                            blueTeamStat = teamStat_None.replace("{aliveCount}", blue.getPlayers().size() + "");
                        }
                    }

                    if (yellow != null && yellow.getTargetHeadBlock() != null && yellow.getTargetHeadBlock().getBlock() != null) {
                        Material blockType = yellow.getTargetHeadBlock().getBlock().getType();
                        if (blockType != null && blockType == bed_block) {
                            yellowTeamStat = teamStat_BedYes.replace("{aliveCount}", yellow.getPlayers().size() + "");
                        } else if (!yellow.getPlayers().isEmpty()) {
                            yellowTeamStat = teamStat_BedNo.replace("{aliveCount}", yellow.getPlayers().size() + "");
                        } else {
                            yellowTeamStat = teamStat_None.replace("{aliveCount}", yellow.getPlayers().size() + "");
                        }
                    }


                    if (pink != null && pink.getTargetHeadBlock() != null && pink.getTargetHeadBlock().getBlock() != null) {
                        Material blockType = pink.getTargetHeadBlock().getBlock().getType();
                        if (blockType != null && blockType == bed_block) {
                            pinkTeamStat = teamStat_BedYes.replace("{aliveCount}", pink.getPlayers().size() + "");
                        } else if (!pink.getPlayers().isEmpty()) {
                            pinkTeamStat = teamStat_BedNo.replace("{aliveCount}", pink.getPlayers().size() + "");
                        } else {
                            pinkTeamStat = teamStat_None.replace("{aliveCount}", pink.getPlayers().size() + "");
                        }
                    }

                    if (aqua != null && aqua.getTargetHeadBlock() != null && aqua.getTargetHeadBlock().getBlock() != null) {
                        Material blockType = aqua.getTargetHeadBlock().getBlock().getType();
                        if (blockType != null && blockType == bed_block) {
                            aquaTeamStat = teamStat_BedYes.replace("{aliveCount}", aqua.getPlayers().size() + "");
                        } else if (!aqua.getPlayers().isEmpty()) {
                            aquaTeamStat = teamStat_BedNo.replace("{aliveCount}", aqua.getPlayers().size() + "");
                        } else {
                            aquaTeamStat = teamStat_None.replace("{aliveCount}", aqua.getPlayers().size() + "");
                        }
                    }

                    if (gray != null && gray.getTargetHeadBlock() != null && gray.getTargetHeadBlock().getBlock() != null) {
                        Material blockType = gray.getTargetHeadBlock().getBlock().getType();
                        if (blockType != null && blockType == bed_block) {
                            grayTeamStat = teamStat_BedYes.replace("{aliveCount}", gray.getPlayers().size() + "");
                        } else if (!gray.getPlayers().isEmpty()) {
                            grayTeamStat = teamStat_BedNo.replace("{aliveCount}", gray.getPlayers().size() + "");
                        } else {
                            grayTeamStat = teamStat_None.replace("{aliveCount}", gray.getPlayers().size() + "");
                        }
                    }

                    if (white != null && white.getTargetHeadBlock() != null && white.getTargetHeadBlock().getBlock() != null) {
                        Material blockType = white.getTargetHeadBlock().getBlock().getType();
                        if (blockType != null && blockType == bed_block) {
                            whiteTeamStat = teamStat_BedYes.replace("{aliveCount}", white.getPlayers().size() + "");
                        } else if (!white.getPlayers().isEmpty()) {
                            whiteTeamStat = teamStat_BedNo.replace("{aliveCount}", white.getPlayers().size() + "");
                        } else {
                            whiteTeamStat = teamStat_None.replace("{aliveCount}", white.getPlayers().size() + "");
                        }
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


                    for (Player player : game.getPlayers()) {
                        UUID playerUUID = player.getUniqueId();

                        if (gameManager.getGameOfPlayer(player) == game) {
                            Team playerTeam = game.getPlayerTeam(player);

                            if (playerTeam != null) {
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
                            }


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

                                            .replace("{yellowTeamIsAlive}", yellowTeamIsAlive)
                                            .replace("{yellowTeamBedStat}", yellowTeamBedStat)

                                            .replace("{greenTeamIsAlive}", greenTeamIsAlive)
                                            .replace("{greenTeamBedStat}", greenTeamBedStat)

                                            .replace("{redTeamIsAlive}", redTeamIsAlive)
                                            .replace("{redTeamBedStat}", redTeamBedStat)

                                            .replace("{blueTeamIsAlive}", blueTeamIsAlive)
                                            .replace("{blueTeamBedStat}", blueTeamBedStat)

                                            .replace("{aquaTeamIsAlive}", aquaTeamIsAlive)
                                            .replace("{aquaTeamBedStat}", aquaTeamBedStat)

                                            .replace("{pinkTeamIsAlive}", pinkTeamIsAlive)
                                            .replace("{pinkTeamBedStat}", pinkTeamBedStat)

                                            .replace("{whiteTeamIsAlive}", whiteTeamIsAlive)
                                            .replace("{whiteTeamBedStat}", whiteTeamBedStat)

                                            .replace("{grayTeamIsAlive}", grayTeamIsAlive)
                                            .replace("{grayTeamBedStat}", grayTeamBedStat)

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
                                            .replace("{kill}", getPlayerKill(playerUUID) + "")
                                            .replace("{fkill}", getPlayerFinalKill(playerUUID) + "")
                                            .replace("{death}", getPlayerDeath(playerUUID) + "")
                                            .replace("{bed}", getPlayerBreakBed(playerUUID) + "")
                                            .replace("{kd}", getPlayerKD(playerUUID) + "")
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

                                            .replace("{yellowTeamIsAlive}", yellowTeamIsAlive)
                                            .replace("{yellowTeamBedStat}", yellowTeamBedStat)

                                            .replace("{greenTeamIsAlive}", greenTeamIsAlive)
                                            .replace("{greenTeamBedStat}", greenTeamBedStat)

                                            .replace("{redTeamIsAlive}", redTeamIsAlive)
                                            .replace("{redTeamBedStat}", redTeamBedStat)

                                            .replace("{blueTeamIsAlive}", blueTeamIsAlive)
                                            .replace("{blueTeamBedStat}", blueTeamBedStat)

                                            .replace("{aquaTeamIsAlive}", aquaTeamIsAlive)
                                            .replace("{aquaTeamBedStat}", aquaTeamBedStat)

                                            .replace("{pinkTeamIsAlive}", pinkTeamIsAlive)
                                            .replace("{pinkTeamBedStat}", pinkTeamBedStat)

                                            .replace("{whiteTeamIsAlive}", whiteTeamIsAlive)
                                            .replace("{whiteTeamBedStat}", whiteTeamBedStat)

                                            .replace("{grayTeamIsAlive}", grayTeamIsAlive)
                                            .replace("{grayTeamBedStat}", grayTeamBedStat)


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
                                            .replace("{kill}", getPlayerKill(playerUUID) + "")
                                            .replace("{fkill}", getPlayerFinalKill(playerUUID) + "")
                                            .replace("{death}", getPlayerDeath(playerUUID) + "")
                                            .replace("{bed}", getPlayerBreakBed(playerUUID) + "")
                                            .replace("{kd}", getPlayerKD(playerUUID) + "")
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
                            }
                        } else if (game.getState() == waiting) {
                            Scoreboard scoreboard = game.getScoreboard();
                            player.setScoreboard(scoreboard);
                        }
                    }
                }
            } else if (game.getState() == waiting) {
                Scoreboard scoreboard = game.getScoreboard();
                for (Player player : game.getPlayers()) {
                    player.setScoreboard(scoreboard);
                }
            } else if (game.getState() == waiting) {
                Scoreboard scoreboard = game.getScoreboard();
                for (Player player : game.getPlayers()) {
                    player.setScoreboard(scoreboard);
                }
            }
        }catch (Exception e){
            if (isDebug()) {
                le(className, e);
            }
        }
    }
    private static final String className = RelScoreBoard.class.getSimpleName();
}