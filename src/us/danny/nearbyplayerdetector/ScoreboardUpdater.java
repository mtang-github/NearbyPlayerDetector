package us.danny.nearbyplayerdetector;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.List;

import static us.danny.nearbyplayerdetector.Config.*;

public class ScoreboardUpdater extends BukkitRunnable {

    private final JavaPlugin plugin;
    private final List<Player> subscribedPlayers;

    public ScoreboardUpdater(JavaPlugin plugin, List<Player> subscribedPlayers){
        this.plugin = plugin;
        this.subscribedPlayers = subscribedPlayers;
    }

    @Override
    public void run() {
        updateScoreboards();
    }

    private void updateScoreboards(){
        for(Player p : subscribedPlayers){
            updateSingleScoreboard(p);
        }
    }

    private void updateSingleScoreboard(Player player){
        Player closestPlayer = null;
        double distanceToClosestPlayer = Double.MAX_VALUE;
        for(Player p : plugin.getServer().getOnlinePlayers()){
            if(p != player && isValidPlayer(p)){
                double distance = player.getLocation().distance(p.getLocation());
                if(distance < distanceToClosestPlayer){
                    distanceToClosestPlayer = distance;
                    closestPlayer = p;
                }
            }
        }

        Scoreboard scoreboard = player.getScoreboard();
        if(closestPlayer == null){
            setPlayerName(scoreboard);
            setPlayerHP(scoreboard);
            setPlayerDist(scoreboard);
        }else {
            setPlayerName(scoreboard, closestPlayer.getName());
            setPlayerHP(scoreboard, closestPlayer.getHealth());
            setPlayerDist(scoreboard, distanceToClosestPlayer);
        }
    }

    private boolean isValidPlayer(Player p){
        return !p.isDead() && (p.getGameMode() == GameMode.SURVIVAL || p.getGameMode() == GameMode.ADVENTURE);
    }

    private void setPlayerName(Scoreboard scoreboard, String name){
        Team playerNameTeam = scoreboard.getTeam(CLOSEST_PLAYER_NAME_TEAM_STRING);
        playerNameTeam.setPrefix(name);
    }

    private void setPlayerName(Scoreboard scoreboard){
        Team playerNameTeam = scoreboard.getTeam(CLOSEST_PLAYER_NAME_TEAM_STRING);
        playerNameTeam.setPrefix(NO_OTHER_PLAYERS_PLACEHOLDER);
    }

    private void setPlayerHP(Scoreboard scoreboard, double hp){
        Team playerHPTeam = scoreboard.getTeam(CLOSEST_PLAYER_HP_TEAM_STRING);
        playerHPTeam.setSuffix(formatDouble(hp));
    }

    private void setPlayerHP(Scoreboard scoreboard){
        Team playerHPTeam = scoreboard.getTeam(CLOSEST_PLAYER_HP_TEAM_STRING);
        playerHPTeam.setSuffix("");
    }

    private void setPlayerDist(Scoreboard scoreboard, double dist){
        Team playerDistTeam = scoreboard.getTeam(CLOSEST_PLAYER_DIST_TEAM_STRING);
        playerDistTeam.setSuffix(formatDouble(dist));
    }

    private void setPlayerDist(Scoreboard scoreboard){
        Team playerDistTeam = scoreboard.getTeam(CLOSEST_PLAYER_DIST_TEAM_STRING);
        playerDistTeam.setSuffix("");
    }

    private String formatDouble(double d){
        return String.format("%,.1f", d);
    }
}
