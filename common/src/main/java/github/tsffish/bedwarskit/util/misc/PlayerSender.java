package github.tsffish.bedwarskit.util.misc;

import org.bukkit.entity.Player;

import static github.tsffish.bedwarskit.util.misc.ColorString.t;

public class PlayerSender {
    public static void sendTitle(Player player, String title, String subtitle){
        player.sendTitle(t(title),t(subtitle));
    }
    public static void sendMessage(Player player, String message){
        player.sendMessage(t(message));
    }
}
