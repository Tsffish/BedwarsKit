package github.tsffish.bedwarskit.util;

import github.tsffish.bedwarskit.config.main.MainConfigHandler;
import org.bukkit.DyeColor;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class RelTeamColor {
    public static final String RED_TEAM_COLOR_NAME = "RED";
    public static final String BLUE_TEAM_COLOR_NAME = "BLUE";
    public static final String GREEN_TEAM_COLOR_NAME = "GREEN";
    public static final String YELLOW_TEAM_COLOR_NAME = "YELLOW";
    public static final String AQUA_TEAM_COLOR_NAME = "AQUA";
    public static final String WHITE_TEAM_COLOR_NAME = "WHITE";
    public static final String GRAY_TEAM_COLOR_NAME = "GRAY";
    public static final String PINK_TEAM_COLOR_NAME = "LIGHT_PURPLE";
    public static final String ORANGE_TEAM_COLOR_NAME = "GOLD";

    public static String dyeColorToRelName(DyeColor color) {

        switch (color) {
            case RED:
                return RED_TEAM_COLOR_NAME;
            case BLUE:
                return BLUE_TEAM_COLOR_NAME;
            case YELLOW:
                return YELLOW_TEAM_COLOR_NAME;
            case CYAN:
                return AQUA_TEAM_COLOR_NAME;
            case WHITE:
                return WHITE_TEAM_COLOR_NAME;
            case PINK:
                return PINK_TEAM_COLOR_NAME;
            case GREEN:
                return GREEN_TEAM_COLOR_NAME;
            case LIME:
                return GREEN_TEAM_COLOR_NAME;
            case GRAY:
                return GRAY_TEAM_COLOR_NAME;
            case ORANGE:
                return ORANGE_TEAM_COLOR_NAME;
            default:
                return " ";
        }
    }

    public static String dyeColorToTeamName(DyeColor color) {

        switch (color) {
            case RED:
                return MainConfigHandler.relTeamName_Red;
            case BLUE:
                return MainConfigHandler.relTeamName_Blue;
            case YELLOW:
                return MainConfigHandler.relTeamName_Yellow;
            case GREEN:
                return MainConfigHandler.relTeamName_Green;
            case CYAN:
                return MainConfigHandler.relTeamName_Aqua;
            case WHITE:
                return MainConfigHandler.relTeamName_White;
            case PINK:
                return MainConfigHandler.relTeamName_Pink;
            case LIME:
                return MainConfigHandler.relTeamName_Green;
            case GRAY:
                return MainConfigHandler.relTeamName_Gray;
            case ORANGE:
                return MainConfigHandler.relTeamName_Orange;
            default:
                return " ";
        }
    }
}