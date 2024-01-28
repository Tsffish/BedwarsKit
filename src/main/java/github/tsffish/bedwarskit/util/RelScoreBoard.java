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

import static github.tsffish.bedwarskit.config.MainConfigHandler.meanNotYou;
import static github.tsffish.bedwarskit.config.MainConfigHandler.meanYou;
import static github.tsffish.bedwarskit.util.RelTeamColorName.*;
import static github.tsffish.bedwarskit.util.RelTeamName.*;

public class RelScoreBoard {
    public static void updateScoreBoard(Player player) {

        GameManager gm = BedwarsRel.getInstance().getGameManager();
        Game game = gm.getGameOfPlayer(player);
        if (game != null && game.getState() == GameState.RUNNING) {

            LocalDate currentDate = LocalDate.now();
            // 定义日期格式
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            String formattedDate = currentDate.format(formatter);

            String formattedDateReal = MainConfigHandler.dateDisplay.replace("{date}", formattedDate);

            String worldname = game.getRegion().getWorld().getName();
            if (worldname.contains(MainConfigHandler.rushWorld)) {

                Material bed = Material.BED_BLOCK;

                String RedTeamName = RED_TEAM_NAME;
                String YellowTeamName = YELLOW_TEAM_NAME;
                String GreenTeamName = GREEN_TEAM_NAME;
                String BlueTeamName = BLUE_TEAM_NAME;

                String PinkTeamName = PINK_TEAM_NAME;
                String AquaTeamName = AQUA_TEAM_NAME;
                String GrayTeamName = GRAY_TEAM_NAME;
                String WhiteTeamName = WHITE_TEAM_NAME;


                String redName = RED_TEAM_COLOR_NAME;
                String blueName = BLUE_TEAM_COLOR_NAME;
                String yellowName = YELLOW_TEAM_COLOR_NAME;
                String greenName = GREEN_TEAM_COLOR_NAME;

                String pinkName = PINK_TEAM_COLOR_NAME;
                String aquaName = AQUA_TEAM_COLOR_NAME;
                String grayName = GRAY_TEAM_COLOR_NAME;
                String whiteName = WHITE_TEAM_COLOR_NAME;


                ScoreboardManager mgr = Bukkit.getScoreboardManager();

                if (worldname.contains(MainConfigHandler.rushWorld2v2)) {
                    // 2v2 ScoreBoard
                    // 4v4 ScoreBoard
                    int gameTimeLeft = game.getTimeLeft();

                    String redTeamStat = "redTeamStat";
                    String yellowTeamStat = "yellowTeamStat";
                    String greenTeamStat = "greenTeamStat";
                    String blueTeamStat = "blueTeamStat";

                    String pinkTeamStat = "pinkTeamStat";
                    String aquaTeamStat = "aquaTeamStat";
                    String grayTeamStat = "grayTeamStat";
                    String whiteTeamStat = "whiteTeamStat";

                    String redTeamIsMe = "redTeamIsMe";
                    String yellowTeamIsMe = "yellowTeamIsMe";
                    String greenTeamIsMe = "greenTeamIsMe";
                    String blueTeamIsMe = "blueTeamIsMe";

                    String pinkTeamIsMe = "pinkTeamIsMe";
                    String aquaTeamIsMe = "aquaTeamIsMe";
                    String grayTeamIsMe = "grayTeamIsMe";
                    String whiteTeamIsMe = "whiteTeamIsMe";

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
                        objective.setDisplayName(ColorString.t(MainConfigHandler.scoreboardTitle));
                        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
                        player.setScoreboard(scoreboard);

                        if (green.getTargetHeadBlock() != null && green.getTargetHeadBlock().getBlock() != null) {
                            Material blockType = green.getTargetHeadBlock().getBlock().getType();
                            if (blockType != null && blockType == bed) {
                                greenTeamStat = MainConfigHandler.meanTeamBedYes;
                            } else if (green.getPlayers().size() >= 1) {
                                greenTeamStat = MainConfigHandler.meanTeamBedNo.replace("{aliveCount}", green.getPlayers().size() + "");
                            } else {
                                greenTeamStat = MainConfigHandler.meanTeamNone;
                            }
                        }

                        if (red.getTargetHeadBlock() != null && red.getTargetHeadBlock().getBlock() != null) {
                            Material blockType = red.getTargetHeadBlock().getBlock().getType();
                            if (blockType != null && blockType == bed) {
                                redTeamStat = MainConfigHandler.meanTeamBedYes;
                            } else if (red.getPlayers().size() >= 1) {
                                redTeamStat = MainConfigHandler.meanTeamBedNo.replace("{aliveCount}", red.getPlayers().size() + "");
                            } else {
                                redTeamStat = MainConfigHandler.meanTeamNone;
                            }
                        }

                        if (blue.getTargetHeadBlock() != null && blue.getTargetHeadBlock().getBlock() != null) {
                            Material blockType = blue.getTargetHeadBlock().getBlock().getType();
                            if (blockType != null && blockType == bed) {
                                blueTeamStat = MainConfigHandler.meanTeamBedYes;
                            } else if (blue.getPlayers().size() >= 1) {
                                blueTeamStat = MainConfigHandler.meanTeamBedNo.replace("{aliveCount}", blue.getPlayers().size() + "");
                            } else {
                                blueTeamStat = MainConfigHandler.meanTeamNone;
                            }
                        }

                        if (yellow.getTargetHeadBlock() != null && yellow.getTargetHeadBlock().getBlock() != null) {
                            Material blockType = yellow.getTargetHeadBlock().getBlock().getType();
                            if (blockType != null && blockType == bed) {
                                yellowTeamStat = MainConfigHandler.meanTeamBedYes;
                            } else if (yellow.getPlayers().size() >= 1) {
                                yellowTeamStat = MainConfigHandler.meanTeamBedNo.replace("{aliveCount}", yellow.getPlayers().size() + "");
                            } else {
                                yellowTeamStat = MainConfigHandler.meanTeamNone;
                            }
                        }


                        if (pink.getTargetHeadBlock() != null && pink.getTargetHeadBlock().getBlock() != null) {
                            Material blockType = pink.getTargetHeadBlock().getBlock().getType();
                            if (blockType != null && blockType == bed) {
                                pinkTeamStat = MainConfigHandler.meanTeamBedYes;
                            } else if (pink.getPlayers().size() >= 1) {
                                pinkTeamStat = MainConfigHandler.meanTeamBedNo.replace("{aliveCount}", pink.getPlayers().size() + "");
                            } else {
                                pinkTeamStat = MainConfigHandler.meanTeamNone;
                            }
                        }

                        if (aqua.getTargetHeadBlock() != null && aqua.getTargetHeadBlock().getBlock() != null) {
                            Material blockType = aqua.getTargetHeadBlock().getBlock().getType();
                            if (blockType != null && blockType == bed) {
                                aquaTeamStat = MainConfigHandler.meanTeamBedYes;
                            } else if (aqua.getPlayers().size() >= 1) {
                                aquaTeamStat = MainConfigHandler.meanTeamBedNo.replace("{aliveCount}", aqua.getPlayers().size() + "");
                            } else {
                                aquaTeamStat = MainConfigHandler.meanTeamNone;
                            }
                        }

                        if (gray.getTargetHeadBlock() != null && gray.getTargetHeadBlock().getBlock() != null) {
                            Material blockType = gray.getTargetHeadBlock().getBlock().getType();
                            if (blockType != null && blockType == bed) {
                                grayTeamStat = MainConfigHandler.meanTeamBedYes;
                            } else if (gray.getPlayers().size() >= 1) {
                                grayTeamStat = MainConfigHandler.meanTeamBedNo.replace("{aliveCount}", gray.getPlayers().size() + "");
                            } else {
                                grayTeamStat = MainConfigHandler.meanTeamNone;
                            }
                        }

                        if (white.getTargetHeadBlock() != null && white.getTargetHeadBlock().getBlock() != null) {
                            Material blockType = white.getTargetHeadBlock().getBlock().getType();
                            if (blockType != null && blockType == bed) {
                                whiteTeamStat = MainConfigHandler.meanTeamBedYes;
                            } else if (white.getPlayers().size() >= 1) {
                                whiteTeamStat = MainConfigHandler.meanTeamBedNo.replace("{aliveCount}", white.getPlayers().size() + "");
                            } else {
                                whiteTeamStat = MainConfigHandler.meanTeamNone;
                            }
                        }




                        if (redName.equals(playerTeam.getColor().name())) {
                            redTeamIsMe = meanYou.replace("{teamColor}", playerTeam.getChatColor().toString());
                        } else {
                            redTeamIsMe = meanNotYou;
                        }

                        if (blueName.equals(playerTeam.getColor().name())) {
                            blueTeamIsMe = meanYou.replace("{teamColor}", playerTeam.getChatColor().toString());
                        } else {
                            blueTeamIsMe = meanNotYou;
                        }

                        if (greenName.equals(playerTeam.getColor().name())) {
                            greenTeamIsMe = meanYou.replace("{teamColor}", playerTeam.getChatColor().toString());
                        } else {
                            greenTeamIsMe = meanNotYou;
                        }

                        if (yellowName.equals(playerTeam.getColor().name())) {
                            yellowTeamIsMe = meanYou.replace("{teamColor}", playerTeam.getChatColor().toString());
                        } else {
                            yellowTeamIsMe = meanNotYou;
                        }

                        if (pinkName.equals(playerTeam.getColor().name())) {
                            pinkTeamIsMe = meanYou.replace("{teamColor}", playerTeam.getChatColor().toString());
                        } else {
                            pinkTeamIsMe = meanNotYou;
                        }

                        if (aquaName.equals(playerTeam.getColor().name())) {
                            aquaTeamIsMe = meanYou.replace("{teamColor}", playerTeam.getChatColor().toString());
                        } else {
                            aquaTeamIsMe = meanNotYou;
                        }

                        if (grayName.equals(playerTeam.getColor().name())) {
                            grayTeamIsMe = meanYou.replace("{teamColor}", playerTeam.getChatColor().toString());
                        } else {
                            grayTeamIsMe = meanNotYou;
                        }

                        if (whiteName.equals(playerTeam.getColor().name())) {
                            whiteTeamIsMe = meanYou.replace("{teamColor}", playerTeam.getChatColor().toString());
                        } else {
                            whiteTeamIsMe = meanNotYou;
                        }

                        String line12Real = ColorString.t(formattedDateReal);
                        String line11Real = ColorString.t("&a" + gameTimeLeft + "&f秒之后&b游戏结束");
                        String line10Real = ColorString.t("  ");
                        String line9Real = ColorString.t(redTeamIsMe + "&c" + RedTeamName + " " + redTeamStat);
                        String line8Real = ColorString.t(blueTeamIsMe + "&9" + BlueTeamName + " " + blueTeamStat);
                        String line7Real = ColorString.t(greenTeamIsMe + "&a" + GreenTeamName + " " + greenTeamStat);
                        String line6Real = ColorString.t(yellowTeamIsMe + "&e" + YellowTeamName + " " + yellowTeamStat);
                        String line5Real = ColorString.t(pinkTeamIsMe + "&d" + PinkTeamName + " " + pinkTeamStat);
                        String line4Real = ColorString.t(aquaTeamIsMe + "&b" + AquaTeamName + " " + aquaTeamStat);
                        String line3Real = ColorString.t(grayTeamIsMe + "&7" + GrayTeamName + " " + grayTeamStat);
                        String line2Real = ColorString.t(whiteTeamIsMe + "&f" + WhiteTeamName + " " + whiteTeamStat);
                        String line1Real = ColorString.t("   ");
                        String line0Real = ColorString.t(MainConfigHandler.serverIp);

                        objective.getScore(line12Real).setScore(12);
                        objective.getScore(line11Real).setScore(11);
                        objective.getScore(line10Real).setScore(10);
                        objective.getScore(line9Real).setScore(9);
                        objective.getScore(line8Real).setScore(8);
                        objective.getScore(line7Real).setScore(7);
                        objective.getScore(line6Real).setScore(6);
                        objective.getScore(line5Real).setScore(5);
                        objective.getScore(line4Real).setScore(4);
                        objective.getScore(line3Real).setScore(3);
                        objective.getScore(line2Real).setScore(2);
                        objective.getScore(line1Real).setScore(1);
                        objective.getScore(line0Real).setScore(0);

                } else if (worldname.contains(MainConfigHandler.rushWorld4v4)) {
                    // 4v4 ScoreBoard
                    int gameTimeLeft = game.getTimeLeft();
                    String redTeamStat = "redTeamStat";
                    String yellowTeamStat = "yellowTeamStat";
                    String greenTeamStat = "greenTeamStat";
                    String blueTeamStat = "blueTeamStat";

                    String redTeamIsMe = "redTeamIsMe";
                    String yellowTeamIsMe = "yellowTeamIsMe";
                    String greenTeamIsMe = "greenTeamIsMe";
                    String blueTeamIsMe = "blueTeamIsMe";

                        Team red = game.getTeam(RedTeamName);
                        Team blue = game.getTeam(BlueTeamName);
                        Team green = game.getTeam(GreenTeamName);
                        Team yellow = game.getTeam(YellowTeamName);
                        Team playerTeam = game.getPlayerTeam(player);

                        Scoreboard scoreboard = mgr.getNewScoreboard();
                        Objective objective = scoreboard.registerNewObjective("4v4", "dummy");
                        objective.setDisplayName(ColorString.t(MainConfigHandler.scoreboardTitle));
                        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
                        player.setScoreboard(scoreboard);

                        if (green.getTargetHeadBlock() != null && green.getTargetHeadBlock().getBlock() != null) {
                            Material blockType = green.getTargetHeadBlock().getBlock().getType();
                            if (blockType != null && blockType == bed) {
                                greenTeamStat = MainConfigHandler.meanTeamBedYes;
                            } else if (green.getPlayers().size() >= 1) {
                                greenTeamStat = MainConfigHandler.meanTeamBedNo.replace("{aliveCount}", green.getPlayers().size() + "");
                            } else {
                                greenTeamStat = MainConfigHandler.meanTeamNone;
                            }
                        }

                        if (red.getTargetHeadBlock() != null && red.getTargetHeadBlock().getBlock() != null) {
                            Material blockType = red.getTargetHeadBlock().getBlock().getType();
                            if (blockType != null && blockType == bed) {
                                redTeamStat = MainConfigHandler.meanTeamBedYes;
                            } else if (red.getPlayers().size() >= 1) {
                                redTeamStat = MainConfigHandler.meanTeamBedNo.replace("{aliveCount}", red.getPlayers().size() + "");
                            } else {
                                redTeamStat = MainConfigHandler.meanTeamNone;
                            }
                        }

                        if (blue.getTargetHeadBlock() != null && blue.getTargetHeadBlock().getBlock() != null) {
                            Material blockType = blue.getTargetHeadBlock().getBlock().getType();
                            if (blockType != null && blockType == bed) {
                                blueTeamStat = MainConfigHandler.meanTeamBedYes;
                            } else if (blue.getPlayers().size() >= 1) {
                                blueTeamStat = MainConfigHandler.meanTeamBedNo.replace("{aliveCount}", blue.getPlayers().size() + "");
                            } else {
                                blueTeamStat = MainConfigHandler.meanTeamNone;
                            }
                        }

                        if (yellow.getTargetHeadBlock() != null && yellow.getTargetHeadBlock().getBlock() != null) {
                            Material blockType = yellow.getTargetHeadBlock().getBlock().getType();
                            if (blockType != null && blockType == bed) {
                                yellowTeamStat = MainConfigHandler.meanTeamBedYes;
                            } else if (yellow.getPlayers().size() >= 1) {
                                yellowTeamStat = MainConfigHandler.meanTeamBedNo.replace("{aliveCount}", yellow.getPlayers().size() + "");
                            } else {
                                yellowTeamStat = MainConfigHandler.meanTeamNone;
                            }
                        }


                        if (redName.equals(playerTeam.getColor().name())) {
                            redTeamIsMe = meanYou.replace("{teamColor}", playerTeam.getChatColor().toString());
                        } else {
                            redTeamIsMe = meanNotYou;
                        }

                        if (blueName.equals(playerTeam.getColor().name())) {
                            blueTeamIsMe = meanYou.replace("{teamColor}", playerTeam.getChatColor().toString());
                        } else {
                            blueTeamIsMe = meanNotYou;
                        }

                        if (greenName.equals(playerTeam.getColor().name())) {
                            greenTeamIsMe = meanYou.replace("{teamColor}", playerTeam.getChatColor().toString());
                        } else {
                            greenTeamIsMe = meanNotYou;
                        }

                        if (yellowName.equals(playerTeam.getColor().name())) {
                            yellowTeamIsMe = meanYou.replace("{teamColor}", playerTeam.getChatColor().toString());
                        } else {
                            yellowTeamIsMe = meanNotYou;
                        }

                        String line8Real = ColorString.t(formattedDateReal);
                        String line7Real = ColorString.t("&a" + gameTimeLeft + "&f秒之后&b游戏结束");
                        String line6Real = ColorString.t("  ");
                        String line5Real = ColorString.t(redTeamIsMe + "&c" + RedTeamName + " " + redTeamStat);
                        String line4Real = ColorString.t(blueTeamIsMe + "&9" + BlueTeamName + " " + blueTeamStat);
                        String line3Real = ColorString.t(greenTeamIsMe + "&a" + GreenTeamName + " " + greenTeamStat);
                        String line2Real = ColorString.t(yellowTeamIsMe + "&e" + YellowTeamName + " " + yellowTeamStat);
                        String line1Real = ColorString.t("   ");
                        String line0Real = ColorString.t(MainConfigHandler.serverIp);

                        objective.getScore(line8Real).setScore(8);
                        objective.getScore(line7Real).setScore(7);
                        objective.getScore(line6Real).setScore(6);
                        objective.getScore(line5Real).setScore(5);
                        objective.getScore(line4Real).setScore(4);
                        objective.getScore(line3Real).setScore(3);
                        objective.getScore(line2Real).setScore(2);
                        objective.getScore(line1Real).setScore(1);
                        objective.getScore(line0Real).setScore(0);

                    } else {
                        Scoreboard scoreboard = game.getScoreboard();
                        player.setScoreboard(scoreboard);
                    }
            }
        }
    }
}
