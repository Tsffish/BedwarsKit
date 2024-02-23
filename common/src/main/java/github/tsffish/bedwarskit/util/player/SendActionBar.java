package github.tsffish.bedwarskit.util.player;

import github.tsffish.bedwarskit.Main;
import org.bukkit.entity.Player;

import java.lang.reflect.Method;

import static github.tsffish.bedwarskit.Main.serverVersion;
import static github.tsffish.bedwarskit.util.misc.MessSender.le;
import static github.tsffish.bedwarskit.util.misc.StringMgr.cantFoundSupport;

public class SendActionBar {
    private static final String className = "SendActionbar";
    public static void sendActionBar(Player player, String string){
        if (serverVersion().contains("1.8")){
            try {
                Method method = github.tsffish.bedwarskit.com.v1_8_r8.ActionBarSender.class.getMethod("sendActionBar", Player.class, String.class);
                method.invoke(null, player, string);
            }catch (Exception e){
                if (!Main.pluginIsDisabling){
                    le(className, e);
                }
            }
        } else if (serverVersion().contains("1.12")){
            try {

                Method method = github.tsffish.bedwarskit.com.v1_12_r2.ActionBarSender.class.getMethod("sendActionBar", Player.class, String.class);
                method.invoke(null, player, string);
            }catch (Exception e){
                if (!Main.pluginIsDisabling){
                    le(className,e);
                }
            }
        } else if (serverVersion().contains("1.9")
                || serverVersion().contains("1.10")
                || serverVersion().contains("1.11")){
            try {

                Method method = github.tsffish.bedwarskit.com.v1_12_r2.ActionBarSender.class.getMethod("sendActionBar", Player.class, String.class);
                method.invoke(null, player, string);
            }catch (Exception e){
                if (!Main.pluginIsDisabling){
                    le(className,e);
                }
            }
        }else {
            le(className, cantFoundSupport + serverVersion());
        }
    }
}
