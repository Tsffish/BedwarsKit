package github.tsffish.bedwarskit.util.misc.reglistener;

import github.tsffish.bedwarskit.BedwarsKit;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import static github.tsffish.bedwarskit.listener.PluginDisable.pluginIsDisabling;
import static github.tsffish.bedwarskit.util.misc.MessSender.le;
import static github.tsffish.bedwarskit.util.misc.PluginState.serverVersion;
import static github.tsffish.bedwarskit.util.misc.StringMgr.cantFoundSupport;
/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class RelPickupItem {
    private static final BedwarsKit plugin = BedwarsKit.getInstance();
    private static final String className = "RelPickupItem";
    public static void regListener(){
        PluginManager pluginManager = Bukkit.getPluginManager();
        if (serverVersion().contains("1.8")){
            try {
                pluginManager.registerEvents(new github.tsffish.bedwarskit.com.v1_8_r8.listener.RelPickupItem(),plugin);
            }catch (Exception e){
                if (!pluginIsDisabling){
                    le(className, e);
                }
            }
        } else if (serverVersion().contains("1.12")){
            try {
                pluginManager.registerEvents(new github.tsffish.bedwarskit.com.v1_12_r2.listener.RelPickupItem(),plugin);
            }catch (Exception e){
                if (!pluginIsDisabling){
                    le(className, e);
                }
            }
        }else if (serverVersion().contains("1.9")
                || serverVersion().contains("1.10")
                || serverVersion().contains("1.11")){
            try {
                pluginManager.registerEvents(new github.tsffish.bedwarskit.com.v1_12_r2.listener.RelPickupItem(),plugin);
            }catch (Exception e){
                if (!pluginIsDisabling){
                    le(className, e);
                }
            }
        }else {
            if (!pluginIsDisabling){
                le(className,cantFoundSupport + serverVersion());
            }
        }
    }
}
