package github.tsffish.bedwarskit.listener;

import github.tsffish.bedwarskit.Main;
import io.github.bedwarsrel.events.BedwarsTargetBlockDestroyedEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;
import java.util.UUID;

import static github.tsffish.bedwarskit.config.main.MainConfigHandler.*;
import static github.tsffish.bedwarskit.util.RelCurrentStat.updatePlayerStat;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;

public class RelBreakBed implements Listener {
    private static final Main plugin = Main.getInstance();
    private String bt(
            String text,
            String breakTeamColor,
            String breakTeamName,
            String breakPlayerTeamColor,
            String breakPlayerName,
            String breakPlayerTeamName) {
        return text.
                replace("{BreakTeamColor}", breakTeamColor).
                replace("{BreakTeamName}", breakTeamName).
                replace("{BreakPlayerTeamColor}", breakPlayerTeamColor).
                replace("{BreakPlayerName}", breakPlayerName).
                replace("{breakPlayerTeamName}", breakPlayerTeamName);
    }
    @EventHandler
    public void on(final BedwarsTargetBlockDestroyedEvent e) {
        if (!breakTitle) {
            return;
        }

            Player breakPlayer = e.getPlayer();
            String playerName = breakPlayer.getName();
            String breakPlayerName = e.getPlayer().getName();
            String breakPlayerTeamName = e.getGame().getPlayerTeam(breakPlayer).getName();

            String breakTeamName = e.getTeam().getName();
            String breakTeamColor = e.getTeam().getChatColor().toString();
            String breakPlayerTeamColor = e.getGame().getPlayerTeam(breakPlayer).getChatColor().toString();

            String breakTitleBreakTeamReal = bt(breakTitleBreakTeam, breakTeamColor, breakTeamName, breakPlayerTeamColor, breakPlayerName, breakPlayerTeamName);
            String breakSubtitleBreakTeamReal = bt(breakSubTitleBreakTeam, breakTeamColor, breakTeamName, breakPlayerTeamColor, breakPlayerName, breakPlayerTeamName);
            String breakTitleBreakPlayerReal = bt(breakTitleBreakPlayer, breakTeamColor, breakTeamName, breakPlayerTeamColor, breakPlayerName, breakPlayerTeamName);
            String breakSubtitleBreakPlayerReal = bt(breakSubTitleBreakPlayer, breakTeamColor, breakTeamName, breakPlayerTeamColor, breakPlayerName, breakPlayerTeamName);
            String breakTitleAllReal = bt(breakTitleAll, breakTeamColor, breakTeamName, breakPlayerTeamColor, breakPlayerName, breakPlayerTeamName);
            String breakSubtitleAllReal = bt(breakSubTitleAll, breakTeamColor, breakTeamName, breakPlayerTeamColor, breakPlayerName, breakPlayerTeamName);

            new BukkitRunnable()
            {
            @Override
            public void run()
            {
                    e.getGame().getPlayers().forEach((player) -> {
                        String playerTeam = e.getGame().getPlayerTeam(player).getName();
                        
                        if (!Objects.equals(playerName, breakPlayerName)
                                && !Objects.equals(playerTeam, breakTeamName))
                        {
                            player.sendTitle(t(breakTitleAllReal), t(breakSubtitleAllReal));
                        } else if (Objects.equals(playerTeam, breakTeamName))
                        {
                            
                            player.sendTitle(t(breakTitleBreakTeamReal), t(breakSubtitleBreakTeamReal));
                        }
                    });

                breakPlayer.sendTitle(t(breakTitleBreakPlayerReal), t(breakSubtitleBreakPlayerReal));

                UUID breakPlayerUUID = breakPlayer.getUniqueId();
                updatePlayerStat(breakPlayerUUID, "b", 1);

            }
            
        }.runTaskLater(plugin, 0L);
    }
}
