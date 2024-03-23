package github.tsffish.bedwarskit.listener;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.reflect.StructureModifier;
import github.tsffish.bedwarskit.BedwarsKit;
import org.bukkit.entity.Player;

import static github.tsffish.bedwarskit.util.PacketUtil.PacketReader.getFloat;
import static github.tsffish.bedwarskit.util.PacketUtil.PacketWriter.writeFloat;
import static github.tsffish.bedwarskit.util.player.PlayerUtil.setPlayerFlying;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class RelPlayerSp {
    private static final BedwarsKit plugin = BedwarsKit.getInstance();
    private static final float spMode = 3;
    private static final float suMode = 0;
    private static final PacketType gsc = PacketType.Play.Server.GAME_STATE_CHANGE;
    private static boolean setUpAlready;

    static {
        setUpAlready = false;
    }

    public static void setupPacketListener() {
        if (!setUpAlready) {
            setUpAlready = true;
            ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();

            protocolManager.addPacketListener(new PacketAdapter(plugin, ListenerPriority.HIGHEST, PacketType.Play.Server.GAME_STATE_CHANGE) {
                @Override
                public void onPacketSending(PacketEvent e) {
                    PacketType current = e.getPacketType();

                    if (current == gsc) {

                        PacketContainer pc = e.getPacket();

                        StructureModifier<Float> smF = getFloat(pc);

                        float value = smF.read(0);

                        if (value == spMode) {

                            Player p = e.getPlayer();

                            writeFloat(smF, suMode);
                            setPlayerFlying(p);
                        }
                    }
                }
            });
        }
    }
}

