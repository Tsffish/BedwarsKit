package github.tsffish.bedwarskit.util;

import org.bukkit.inventory.ItemStack;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static github.tsffish.bedwarskit.Main.pluginIsDisabling;
import static github.tsffish.bedwarskit.Main.serverVersion;
import static github.tsffish.bedwarskit.util.misc.MessSender.le;
import static github.tsffish.bedwarskit.util.misc.StringMgr.cantFoundSupport;

public class GetItem {
    private static final String className = "GetItem";
    public static ItemStack GRAY_STAINED_GLASS_PANE(){
        if (serverVersion().contains("1.8")){
            try {
                Method method = github.tsffish.bedwarskit.com.v1_8_r8.GetItem.class.getMethod("GRAY_STAINED_GLASS_PANE");
                return (ItemStack) method.invoke(null);
            }catch (Exception e){
                if (!pluginIsDisabling){
                    le(className, e);
                    return null;
                }
            }
        } else if (serverVersion().contains("1.12")){
            try {
                Method method = github.tsffish.bedwarskit.com.v1_12_r2.GetItem.class.getMethod("GRAY_STAINED_GLASS_PANE");
                return (ItemStack) method.invoke(null);
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
                Method method = github.tsffish.bedwarskit.com.v1_12_r2.GetItem.class.getMethod("GRAY_STAINED_GLASS_PANE");
                return (ItemStack) method.invoke(null);
            }catch (Exception e){
                if (!pluginIsDisabling){
                    le(className, e);
                    return null;
                }
            }
        }else{
            if (!pluginIsDisabling){
                le(className,cantFoundSupport + serverVersion());
                return null;
            }
        }
        return null;
    }
}
