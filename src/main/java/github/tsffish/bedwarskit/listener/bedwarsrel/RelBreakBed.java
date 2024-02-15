package github.tsffish.bedwarskit.listener.bedwarsrel;

import github.tsffish.bedwarskit.config.main.MainConfigHandler;
import github.tsffish.bedwarskit.util.RelCurrentStat;
import io.github.bedwarsrel.events.BedwarsTargetBlockDestroyedEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

import static github.tsffish.bedwarskit.util.RelBreakTitle.bt;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;

public class RelBreakBed implements Listener {
    private static Plugin plugin = github.tsffish.bedwarskit.Main.getPlugin(github.tsffish.bedwarskit.Main.class);
    @EventHandler
    public void on(BedwarsTargetBlockDestroyedEvent e) {
        if (MainConfigHandler.breakTitle) {

            Player breakPlayer = e.getPlayer();
            String playerName = breakPlayer.getName();
            String breakPlayerName = e.getPlayer().getName();
            String breakPlayerTeamName = e.getGame().getPlayerTeam(breakPlayer).getName();

            String breakTeamName = e.getTeam().getName();
            String breakTeamColor = e.getTeam().getChatColor().toString();
            String breakPlayerTeamColor = e.getGame().getPlayerTeam(breakPlayer).getChatColor().toString();

            String breakTitleBreakTeamReal = bt(MainConfigHandler.breakTitleBreakTeam, breakTeamColor, breakTeamName, breakPlayerTeamColor, breakPlayerName, breakPlayerTeamName);
            String breakSubtitleBreakTeamReal = bt(MainConfigHandler.breakSubTitleBreakTeam, breakTeamColor, breakTeamName, breakPlayerTeamColor, breakPlayerName, breakPlayerTeamName);
            String breakTitleBreakPlayerReal = bt(MainConfigHandler.breakTitleBreakPlayer, breakTeamColor, breakTeamName, breakPlayerTeamColor, breakPlayerName, breakPlayerTeamName);
            String breakSubtitleBreakPlayerReal = bt(MainConfigHandler.breakSubTitleBreakPlayer, breakTeamColor, breakTeamName, breakPlayerTeamColor, breakPlayerName, breakPlayerTeamName);
            String breakTitleAllReal = bt(MainConfigHandler.breakTitleAll, breakTeamColor, breakTeamName, breakPlayerTeamColor, breakPlayerName, breakPlayerTeamName);
            String breakSubtitleAllReal = bt(MainConfigHandler.breakSubTitleAll, breakTeamColor, breakTeamName, breakPlayerTeamColor, breakPlayerName, breakPlayerTeamName);

            new BukkitRunnable()
            {
            @Override
            public void run()
            {
                    
                    e.getGame().getPlayers().forEach((player) -> {
                        String playerTeam = e.getGame().getPlayerTeam(player).getName();
                        
                        if (!Objects.equals(playerName, breakPlayerName) && !Objects.equals(playerTeam, breakTeamName))
                        {
                            player.sendTitle(t(breakTitleAllReal), t(breakSubtitleAllReal));
                        } else if (Objects.equals(playerTeam, breakTeamName))
                        {
                            
                            player.sendTitle(t(breakTitleBreakTeamReal), t(breakSubtitleBreakTeamReal));
                        }
                    }
                    );

                breakPlayer.sendTitle(t(breakTitleBreakPlayerReal), t(breakSubtitleBreakPlayerReal));

                String breakPlayerName = breakPlayer.getName();
                RelCurrentStat.updatePlayerStat(breakPlayerName, "b", 1);
            }
            
        }.runTaskLater(plugin, 0L);
    }
    }
}
