package us.danny.nearbyplayerdetector;

import org.bukkit.ChatColor;

public class Config {
    public static final String NAME = "[NearbyPlayerDetector]";
    public static final String SCOREBOARD_NAME = "Closest Player";
    public static final String SCOREBOARD_SEPARATOR = "    ------------";

    public static final String CLOSEST_PLAYER_NAME_TEAM_STRING = "a";
    public static final String CLOSEST_PLAYER_HP_TEAM_STRING = "b";
    public static final String CLOSEST_PLAYER_DIST_TEAM_STRING = "c";

    public static final String CLOSEST_PLAYER_NAME_ENTRY = ChatColor.AQUA + "" + ChatColor.WHITE;
    public static final String CLOSEST_PLAYER_HP_ENTRY = ChatColor.BLUE + "" + ChatColor.WHITE;
    public static final String CLOSEST_PLAYER_DIST_ENTRY = ChatColor.BLACK + "" + ChatColor.WHITE;

    public static final String NO_OTHER_PLAYERS_PLACEHOLDER = "No other players";
    public static final String HP_PREFIX = "HP  : ";
    public static final String DISTANCE_PREFIX = "DIST: ";
    public static final int TICKS_BETWEEN_SCOREBOARD_UPDATE = 8;
    public static final int MAX_SCORE = 99;

    public static final String ENABLE_MESSAGE = "Enabled Player Detector";
    public static final String DISABLE_MESSAGE = "Disabled Player Detector";
}