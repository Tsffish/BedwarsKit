package github.tsffish.bedwarskit.util;

import github.tsffish.bedwarskit.config.main.MainConfigHandler;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static github.tsffish.bedwarskit.util.misc.MessSender.le;

public class RelPlayerTab {
    private static void sendTabData(Player player, List<String> headerList, List<String> footerList) {
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
        try {
            Field field = packet.getClass().getDeclaredField("b");
            field.setAccessible(true);
            field.set(packet, tabFooter);
        } catch (Exception e) {
            le("RelPlayerTab", e);
        }

        connection.sendPacket(packet);
    }
    private static void sendTabData(Player player, String header, String footer) {
        CraftPlayer craftPlayer = (CraftPlayer) player;
        PlayerConnection connection = craftPlayer.getHandle().playerConnection;

        IChatBaseComponent tabHeader = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + header + "\"}");

        IChatBaseComponent tabFooter = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + footer + "\"}");

        PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter(tabHeader);
        try {
            Field field = packet.getClass().getDeclaredField("b");
            field.setAccessible(true);
            field.set(packet, tabFooter);
        } catch (Exception e) {
            le("RelPlayerTab", e);
        }

        connection.sendPacket(packet);
    }


    public static void sendTab(Player player) {
        List<String> headerList = new ArrayList<>(20);
        List<String> footerList = new ArrayList<>(20);

        String head;
        String foot;
        if (MainConfigHandler.tab_is_multiLine){
            headerList.addAll(MainConfigHandler.tab_headList);
            footerList.addAll(MainConfigHandler.tab_footList);
            sendTabData(player,headerList,footerList);
        }else {
            head = MainConfigHandler.tab_head;
            foot = MainConfigHandler.tab_foot;
            sendTabData(player,head,foot);
        }


    }

}
