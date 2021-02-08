package us.danny.nearbyplayerdetector;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

import static us.danny.nearbyplayerdetector.Config.*;

public class Main extends JavaPlugin {

    private final List<Player> subscribedPlayers;

    public Main(){
        subscribedPlayers = new ArrayList<>();
    }

    @Override
    public void onEnable() {
        this.getCommand("togglePlayerDetector").setExecutor(new CommandTogglePlayerDetector(this, subscribedPlayers));
        new ScoreboardUpdater(this, subscribedPlayers).runTaskTimer(this, 0,  TICKS_BETWEEN_SCOREBOARD_UPDATE);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(subscribedPlayers), this);
        System.out.println(NAME + " Enabled");
    }

    @Override
    public void onDisable() {
        System.out.println(NAME + " Disabled");
    }
}