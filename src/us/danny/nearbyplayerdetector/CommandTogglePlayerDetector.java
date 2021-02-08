package us.danny.nearbyplayerdetector;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

import static us.danny.nearbyplayerdetector.Config.*;

public class CommandTogglePlayerDetector implements CommandExecutor {

    private final JavaPlugin plugin;
    private final ScoreboardFactory scoreboardFactory;
    private final List<Player> subscribedPlayerList;


    public CommandTogglePlayerDetector(JavaPlugin plugin, List<Player> subscribedPlayerList){
        this.plugin = plugin;
        this.scoreboardFactory = new ScoreboardFactory();
        this.subscribedPlayerList = subscribedPlayerList;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player player = (Player)commandSender;
            if(subscribedPlayerList.contains(player)){
                subscribedPlayerList.remove(player);
                player.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
                player.sendMessage(DISABLE_MESSAGE);
            }
            else{
                player.setScoreboard(scoreboardFactory.makeScoreboard(plugin, player));
                subscribedPlayerList.add(player);
                player.sendMessage(ENABLE_MESSAGE);
            }
            return true;
        }
        else {
            return false;
        }
    }
}
