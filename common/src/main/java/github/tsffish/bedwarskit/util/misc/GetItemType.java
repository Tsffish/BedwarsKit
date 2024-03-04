package github.tsffish.bedwarskit.util.misc;

import org.bukkit.Material;

import java.lang.reflect.Method;

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
public class GetItemType {
    private static final String className = "GetItemType";
    public static Material WOOD_SWORD(){
        if (serverVersion().contains("1.8")){
            try {
                Method method = github.tsffish.bedwarskit.com.v1_8_r8.GetItemType.class.getMethod("WOOD_SWORD");
                return (Material) method.invoke(null);
            }catch (Exception e){
                if (!pluginIsDisabling){
                    le(className, e);
                    return null;
                }
            }
        } else if (serverVersion().contains("1.12")){
            try {
                Method method = github.tsffish.bedwarskit.com.v1_12_r2.GetItemType.class.getMethod("WOOD_SWORD");
                return (Material) method.invoke(null);
            }catch (Exception e){
                if (!pluginIsDisabling){
                    le(className, e);
                    return null;
                }
            }
        }else if (serverVersion().contains("1.9")
                || serverVersion().contains("1.10")
                || serverVersion().contains("1.11")){
            try {
                Method method = github.tsffish.bedwarskit.com.v1_12_r2.GetItemType.class.getMethod("WOOD_SWORD");
                return (Material) method.invoke(null);
            }catch (Exception e){
                if (!pluginIsDisabling){
                    le(className, e);
                    return null;
                }
            }
            }else {
            if (!pluginIsDisabling){
                le(className,cantFoundSupport + serverVersion());
                return null;
            }
        }
        return null;
    }
}
