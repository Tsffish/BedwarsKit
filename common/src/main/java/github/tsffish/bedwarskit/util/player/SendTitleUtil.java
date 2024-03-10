package github.tsffish.bedwarskit.util.player;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import org.bukkit.entity.Player;

import static github.tsffish.bedwarskit.util.misc.ColorString.t;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class SendTitleUtil {
    public static void sendTitlePacket(Player player, String titleOrg, String subtitleOrg, int fadeInTime, int stayTime, int fadeOutTime) {
        String title = t(titleOrg);
        String subtitle = t(subtitleOrg);

        PacketContainer titlePacket = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.TITLE);

        // 设置标题的参数
        titlePacket.getTitleActions().write(0, EnumWrappers.TitleAction.TITLE);
        titlePacket.getIntegers().write(0, fadeInTime);
        titlePacket.getIntegers().write(1, stayTime);
        titlePacket.getIntegers().write(2, fadeOutTime);
        titlePacket.getChatComponents().write(0, WrappedChatComponent.fromText(title));

        // 设置副标题的参数
        PacketContainer subtitlePacket = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.TITLE);
        subtitlePacket.getTitleActions().write(0, EnumWrappers.TitleAction.SUBTITLE);
        subtitlePacket.getIntegers().write(0, fadeInTime);
        subtitlePacket.getIntegers().write(1, stayTime);
        subtitlePacket.getIntegers().write(2, fadeOutTime);
        subtitlePacket.getChatComponents().write(0, WrappedChatComponent.fromText(subtitle));

        try {
            ProtocolLibrary.getProtocolManager().sendServerPacket(player, titlePacket);
            ProtocolLibrary.getProtocolManager().sendServerPacket(player, subtitlePacket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
