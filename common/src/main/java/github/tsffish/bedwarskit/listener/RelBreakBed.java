package github.tsffish.bedwarskit.listener;

import github.tsffish.bedwarskit.BedwarsKit;
import io.github.bedwarsrel.events.BedwarsTargetBlockDestroyedEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

import static github.tsffish.bedwarskit.config.main.MainConfigHandler.*;
import static github.tsffish.bedwarskit.util.player.PlayerSender.sendTitle;
import static github.tsffish.bedwarskit.util.player.RelCurrentStat.addBreakBed;
import static github.tsffish.bedwarskit.util.player.RelCurrentStat.updatePlayerStat;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class RelBreakBed implements Listener {
    private static final BedwarsKit plugin = BedwarsKit.getInstance();

    private String replaceStrings(
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
    public void on(final BedwarsTargetBlockDestroyedEvent event) {
        if (!breakTitle) {
            return;
        }
        new BukkitRunnable() {
            @Override
            public void run() {

                Player breakPlayer = event.getPlayer();
                String playerName = breakPlayer.getName();
                String breakPlayerName = event.getPlayer().getName();
                String breakPlayerTeamName = event.getGame().getPlayerTeam(breakPlayer).getName();

                String breakTeamName = event.getTeam().getName();
                String breakTeamColor = event.getTeam().getChatColor().toString();
                String breakPlayerTeamColor = event.getGame().getPlayerTeam(breakPlayer).getChatColor().toString();

                String breakTitleBreakTeamReal = replaceStrings(breakTitleBreakTeam, breakTeamColor, breakTeamName, breakPlayerTeamColor, breakPlayerName, breakPlayerTeamName);
                String breakSubtitleBreakTeamReal = replaceStrings(breakSubTitleBreakTeam, breakTeamColor, breakTeamName, breakPlayerTeamColor, breakPlayerName, breakPlayerTeamName);
                String breakTitleBreakPlayerReal = replaceStrings(breakTitleBreakPlayer, breakTeamColor, breakTeamName, breakPlayerTeamColor, breakPlayerName, breakPlayerTeamName);
                String breakSubtitleBreakPlayerReal = replaceStrings(breakSubTitleBreakPlayer, breakTeamColor, breakTeamName, breakPlayerTeamColor, breakPlayerName, breakPlayerTeamName);
                String breakTitleAllReal = replaceStrings(breakTitleAll, breakTeamColor, breakTeamName, breakPlayerTeamColor, breakPlayerName, breakPlayerTeamName);
                String breakSubtitleAllReal = replaceStrings(breakSubTitleAll, breakTeamColor, breakTeamName, breakPlayerTeamColor, breakPlayerName, breakPlayerTeamName);


                event.getGame().getPlayers().forEach((player) -> {
                    String playerTeam = event.getGame().getPlayerTeam(player).getName();

                    if (!playerName.equals(breakPlayerName)
                            && !playerTeam.equals(breakTeamName)) {
                        sendTitle(player, breakTitleAllReal, breakSubtitleAllReal);
                    } else if (playerTeam.equals(breakTeamName)) {

                        sendTitle(player, breakTitleBreakTeamReal, breakSubtitleBreakTeamReal);
                    }
                });

                sendTitle(breakPlayer, breakTitleBreakPlayerReal, breakSubtitleBreakPlayerReal);

                UUID breakPlayerUUID = breakPlayer.getUniqueId();
                updatePlayerStat(breakPlayerUUID, addBreakBed, 1);

            }

        }.runTaskLater(plugin, 0L);
    }
}
