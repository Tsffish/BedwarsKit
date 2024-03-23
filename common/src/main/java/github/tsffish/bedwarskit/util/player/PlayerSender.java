package github.tsffish.bedwarskit.util.player;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import org.bukkit.entity.Player;

import static github.tsffish.bedwarskit.util.misc.ColorString.t;
import static github.tsffish.bedwarskit.util.misc.MessSender.le;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class PlayerSender {
    public static void sendTitle(Player player, String title, String subtitle) {
        player.sendTitle(t(title), t(subtitle));
    }

    public static void sendMessage(Player player, String message) {
        player.sendMessage(t(message));
    }

    public static void sendTitle(Player player, String titleOrg, String subtitleOrg, int fadeInTime, int stayTime, int fadeOutTime) {
        String title = t(titleOrg);
        String subtitle = t(subtitleOrg);

        PacketContainer titlePacket = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.TITLE);

        titlePacket.getTitleActions().write(0, EnumWrappers.TitleAction.TITLE);
        titlePacket.getIntegers().write(0, fadeInTime);
        titlePacket.getIntegers().write(1, stayTime);
        titlePacket.getIntegers().write(2, fadeOutTime);
        titlePacket.getChatComponents().write(0, WrappedChatComponent.fromText(title));

        PacketContainer subtitlePacket = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.TITLE);
        subtitlePacket.getTitleActions().write(0, EnumWrappers.TitleAction.SUBTITLE);
        subtitlePacket.getIntegers().write(0, fadeInTime);
        subtitlePacket.getIntegers().write(1, stayTime);
        subtitlePacket.getIntegers().write(2, fadeOutTime);
        subtitlePacket.getChatComponents().write(0, WrappedChatComponent.fromText(subtitle));

        try {
            ProtocolManager mgr = ProtocolLibrary.getProtocolManager();
            mgr.sendServerPacket(player, titlePacket);
            mgr.sendServerPacket(player, subtitlePacket);
        } catch (Exception e) {
            le(className,e);
        }
    }
    private static final String className = PlayerSender.class.getSimpleName();
    public static void updatePlayerStatePreventDied(Player player){
    PacketContainer packet = new PacketContainer(PacketType.Play.Server.GAME_STATE_CHANGE);
    packet.getIntegers().write(0, 6);
        ProtocolLibrary.getProtocolManager().sendServerPacket(player, packet);
    }
}
