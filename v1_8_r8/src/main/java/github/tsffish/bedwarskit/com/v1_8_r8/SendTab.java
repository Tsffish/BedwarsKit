package github.tsffish.bedwarskit.com.v1_8_r8;


import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;

import java.util.List;

public class SendTab {
    public static void sendTab(Player player, List<String> headerList, List<String> footerList) throws NoSuchFieldException, IllegalAccessException {
        CraftPlayer craftPlayer = (CraftPlayer) player;
        PlayerConnection connection = craftPlayer.getHandle().playerConnection;

        StringBuilder headerBuilder = new StringBuilder(20);

            if (!headerList.isEmpty()) {
                for (String line : headerList) {
                    headerBuilder.append(line).append("\n");
                }
            }
        IChatBaseComponent tabHeader = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + headerBuilder + "\"}");

        StringBuilder footerBuilder = new StringBuilder(20);


            if (!footerList.isEmpty()) {
                for (String line : footerList) {
                    footerBuilder.append(line).append("\n");
                }
            }

        IChatBaseComponent tabFooter = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + footerBuilder + "\"}");

        PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter(tabHeader);
        Field field = packet.getClass().getDeclaredField("b");
        field.setAccessible(true);
        field.set(packet, tabFooter);

        connection.sendPacket(packet);
    }
    public static void sendTab(Player player, String header, String footer) throws NoSuchFieldException, IllegalAccessException {
        CraftPlayer craftPlayer = (CraftPlayer) player;
        PlayerConnection connection = craftPlayer.getHandle().playerConnection;

        IChatBaseComponent tabHeader = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + header + "\"}");

        IChatBaseComponent tabFooter = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + footer + "\"}");

        PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter(tabHeader);
            Field field = packet.getClass().getDeclaredField("b");
            field.setAccessible(true);
            field.set(packet, tabFooter);

        connection.sendPacket(packet);
    }
}
