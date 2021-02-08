package us.danny.nearbyplayerdetector;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import static us.danny.nearbyplayerdetector.Config.*;

public class ScoreboardFactory {

    public ScoreboardFactory(){}

    public Scoreboard makeScoreboard(JavaPlugin plugin, Player player) {
        Scoreboard toRet = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective health = toRet.registerNewObjective("health", "health");
        health.setDisplaySlot(DisplaySlot.PLAYER_LIST);

        Objective objective = toRet.registerNewObjective(SCOREBOARD_NAME, "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        Team playerNameTeam;
        Team playerHPTeam;
        Team playerDistTeam;
        playerNameTeam = toRet.registerNewTeam(CLOSEST_PLAYER_NAME_TEAM_STRING);
        playerNameTeam.addEntry(CLOSEST_PLAYER_NAME_ENTRY);
        playerHPTeam = toRet.registerNewTeam(CLOSEST_PLAYER_HP_TEAM_STRING);
        playerHPTeam.setPrefix(HP_PREFIX);
        playerHPTeam.addEntry(CLOSEST_PLAYER_HP_ENTRY);
        playerDistTeam = toRet.registerNewTeam(CLOSEST_PLAYER_DIST_TEAM_STRING);
        playerDistTeam.setPrefix(DISTANCE_PREFIX);
        playerDistTeam.addEntry(CLOSEST_PLAYER_DIST_ENTRY);

        int scoreCounter = MAX_SCORE;
        Score separatorScore = objective.getScore(SCOREBOARD_SEPARATOR);
        separatorScore.setScore(scoreCounter);

        Score nameScore = objective.getScore(CLOSEST_PLAYER_NAME_ENTRY);
        nameScore.setScore(--scoreCounter);

        Score hpScore = objective.getScore(CLOSEST_PLAYER_HP_ENTRY);
        hpScore.setScore(--scoreCounter);

        Score distScore = objective.getScore(CLOSEST_PLAYER_DIST_ENTRY);
        distScore.setScore(--scoreCounter);

        return toRet;
    }
}