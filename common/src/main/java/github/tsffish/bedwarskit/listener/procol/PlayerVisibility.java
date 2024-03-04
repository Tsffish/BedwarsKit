package github.tsffish.bedwarskit.listener.procol;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import github.tsffish.bedwarskit.BedwarsKit;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerVisibility {
    private static final Set<Player> hiddenPlayers = Collections.newSetFromMap(new ConcurrentHashMap<>());
    private static final BedwarsKit plugin = BedwarsKit.getInstance();
    public static void setupPacketListener() {
        ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();

        protocolManager.addPacketListener(new PacketAdapter(plugin, PacketType.Play.Server.ENTITY_METADATA) {
            @Override
            public void onPacketSending(PacketEvent event) {
                if (event.getPacketType() == PacketType.Play.Server.ENTITY_METADATA) {
                    int entityId = event.getPacket().getIntegers().read(0);
                    Player receiver = event.getPlayer();
                    for (Player hiddenPlayer : hiddenPlayers) {
                        if (hiddenPlayer.getEntityId() == entityId && !receiver.equals(hiddenPlayer)) {
                            event.setCancelled(true);
                        }
                    }
                }
            }
        });
    }

    public static void hidePlayer(Player player) {
        hiddenPlayers.add(player);
    }

    public static void showPlayer(Player player) {
        hiddenPlayers.remove(player);
    }

    public static boolean isPlayerHidden(Player player) {
        return hiddenPlayers.contains(player);
    }
}

