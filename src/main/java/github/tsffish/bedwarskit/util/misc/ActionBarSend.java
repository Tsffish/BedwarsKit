package github.tsffish.bedwarskit.util.misc;

import org.bukkit.entity.Player;

import static github.tsffish.bedwarskit.util.misc.ColorString.t;

public class ActionBarSend {

    public static void sendV1_8(Player player, String text){
        if (player == null || !player.isOnline()){
            return;
        }
        io.github.bedwarsrel.com.v1_8_r3.ActionBar.sendActionBar(player, t(text));

    }
    public static void sendV1_12(Player player, String text){
        if (player == null || !player.isOnline()){
            return;
        }
        io.github.bedwarsrel.com.v1_12_r1.ActionBar.sendActionBar(player, t(text));

    }
}
