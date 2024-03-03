package github.tsffish.bedwarskit.util.misc;

import org.bukkit.ChatColor;

import static github.tsffish.bedwarskit.BedwarsKit.author;
import static github.tsffish.bedwarskit.BedwarsKit.pluginVersion;
import static github.tsffish.bedwarskit.util.misc.ChatColor.*;
import static github.tsffish.bedwarskit.util.misc.ChatColor.red;
import static github.tsffish.bedwarskit.util.misc.MessSender.l;
import static github.tsffish.bedwarskit.util.misc.StringMgr.*;

public class SendStartUpInfo {
    public static void sendPluginStartUpInfo(ChatColor color){
        l(color + msgline);
        l(" ");
        l(white + pluginName + " " + aqua + pluginVersion());
        l(" ");
        l(white + "Author: " + yellow  + author());
        l(" ");
        l(color + msgline);

        if (color == red){
            l(red + relName + " not found, unable to enable related support");
        }
        if (color == green){
            l(green + relName + " found, related support enable");
        }
    }
}
