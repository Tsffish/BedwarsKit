package github.tsffish.bedwarskit.util.player;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import github.tsffish.bedwarskit.config.main.MainConfigHandler;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

import static github.tsffish.bedwarskit.util.PluginState.isDebug;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;
import static github.tsffish.bedwarskit.util.misc.MessSender.le;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class SendTab {
    private static final String className = SendTab.class.getSimpleName();
private static void sendTabData(Player player, List<String> header, List<String> footer) {
    try {
        PacketContainer packet = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.PLAYER_LIST_HEADER_FOOTER);

        StringBuilder headerBuilder = new StringBuilder();
        for (int i = 0; i < header.size(); i++) {
            headerBuilder.append(header.get(i));
            if (i < header.size() - 1) {
                headerBuilder.append("\n");
            }
        }

        StringBuilder footerBuilder = new StringBuilder();
        for (int i = 0; i < footer.size(); i++) {
            footerBuilder.append(footer.get(i));
            if (i < footer.size() - 1) {
                footerBuilder.append("\n");
            }
        }

        packet.getChatComponents().write(0, WrappedChatComponent.fromText(headerBuilder.toString()));
        packet.getChatComponents().write(1, WrappedChatComponent.fromText(footerBuilder.toString()));

        ProtocolLibrary.getProtocolManager().sendServerPacket(player, packet);
    }   catch (Exception e){
        if (isDebug()) {
            le(className, e);
        }
    }
}

private static void sendTabData(Player player, String header, String footer) {
    try {
        PacketContainer packet = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.PLAYER_LIST_HEADER_FOOTER);

        packet.getChatComponents().write(0, WrappedChatComponent.fromText(header));
        packet.getChatComponents().write(1, WrappedChatComponent.fromText(footer));

        ProtocolLibrary.getProtocolManager().sendServerPacket(player, packet);
    }   catch (Exception e){
        if (isDebug()) {
            le(className, e);
        }
    }
}

    public static void sendTab(Player player) {
        List<String> headerList = new ArrayList<>(20);
        List<String> footerList = new ArrayList<>(20);

        String head;
        String foot;
        if (MainConfigHandler.tab_is_multiLine) {
            for (String string : MainConfigHandler.tab_headList){
                headerList.add(t(string));
            }
           for (String string : MainConfigHandler.tab_footList){
                footerList.add(t(string));
            }
            sendTabData(player, headerList, footerList);
        } else {
            head = t(MainConfigHandler.tab_head);
            foot = t(MainConfigHandler.tab_foot);
            sendTabData(player, head, foot);
        }


    }

}
