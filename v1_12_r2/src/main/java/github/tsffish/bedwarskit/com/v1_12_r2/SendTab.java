package github.tsffish.bedwarskit.com.v1_12_r2;

import github.tsffish.bedwarskit.util.ReflectionUtil;
import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.Packet;
import net.minecraft.server.v1_12_R1.PlayerConnection;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Objects;

public class SendTab {
    public static void sendTab(Player player, List<String> headerList, List<String> footerList) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        CraftPlayer craftPlayer = (CraftPlayer) player;
        PlayerConnection connection = craftPlayer.getHandle().playerConnection;
        IChatBaseComponent headerComponent = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + headerList + "\"}");
        IChatBaseComponent footerComponent = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + footerList + "\"}");

        // 构建 PacketPlayOutPlayerListHeader 实例
        Constructor<?> constructor = Objects.requireNonNull(ReflectionUtil.getNMSClass("PacketPlayOutPlayerListHeader")).getDeclaredConstructor(IChatBaseComponent.class);
        Object packetHeader = constructor.newInstance(headerComponent);

        // 构建 PacketPlayOutPlayerListFooter 实例
        Constructor<?> constructor2 = Objects.requireNonNull(ReflectionUtil.getNMSClass("PacketPlayOutPlayerListFooter")).getDeclaredConstructor(IChatBaseComponent.class);
        Object packetFooter = constructor2.newInstance(footerComponent);

        // 发送包
        connection.sendPacket((Packet) packetHeader);
        connection.sendPacket((Packet) packetFooter);
    }
    public static void sendTab(Player player, String header, String footer) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {

        CraftPlayer craftPlayer = (CraftPlayer) player;
        PlayerConnection connection = craftPlayer.getHandle().playerConnection;
        IChatBaseComponent headerComponent = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + header + "\"}");
        IChatBaseComponent footerComponent = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + footer + "\"}");

        // 构建 PacketPlayOutPlayerListHeader 实例
        Constructor<?> constructor = Objects.requireNonNull(ReflectionUtil.getNMSClass("PacketPlayOutPlayerListHeader")).getDeclaredConstructor(IChatBaseComponent.class);
        Object packetHeader = constructor.newInstance(headerComponent);

        // 构建 PacketPlayOutPlayerListFooter 实例
        Constructor<?> constructor2 = Objects.requireNonNull(ReflectionUtil.getNMSClass("PacketPlayOutPlayerListFooter")).getDeclaredConstructor(IChatBaseComponent.class);
        Object packetFooter = constructor2.newInstance(footerComponent);

        // 发送包
        connection.sendPacket((Packet) packetHeader);
        connection.sendPacket((Packet) packetFooter);
    }
}
