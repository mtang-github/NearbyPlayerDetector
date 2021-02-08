package us.danny.nearbyplayerdetector;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.List;

public class PlayerQuitListener implements Listener {

    private final List<Player> subscribedPlayers;

    public PlayerQuitListener(List<Player> subscribedPlayers) {
        this.subscribedPlayers = subscribedPlayers;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        subscribedPlayers.remove(event.getPlayer());
    }
}