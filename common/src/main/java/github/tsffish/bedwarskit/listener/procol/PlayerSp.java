package github.tsffish.bedwarskit.listener.procol;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import github.tsffish.bedwarskit.BedwarsKit;

import static github.tsffish.bedwarskit.util.misc.MessSender.l;
import static github.tsffish.bedwarskit.util.misc.PluginState.isDebug;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class PlayerSp {
    private static final BedwarsKit plugin = BedwarsKit.getInstance();
    private static boolean setUpAlready = false;

    public static void setupPacketListener() {
        if (!setUpAlready) {
            setUpAlready = true;
            ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();

            protocolManager.addPacketListener(new PacketAdapter(plugin, ListenerPriority.HIGHEST, PacketType.Play.Server.GAME_STATE_CHANGE) {
                @Override
                public void onPacketSending(PacketEvent event) {
                    if (event.getPacketType() == PacketType.Play.Server.GAME_STATE_CHANGE) {

                        int reason = event.getPacket().getIntegers().read(0);

                        float value = event.getPacket().getFloat().read(0);

                        if (isDebug()) {
                            l("reasion: " + reason + ",value: " + value);
                        }

                        if (value == 3) {
                            event.getPacket().getFloat().write(0, 0.0f);
                        }
                    }
                }
            });
        }
    }
}

