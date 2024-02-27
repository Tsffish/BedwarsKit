package github.tsffish.bedwarskit.util.misc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static github.tsffish.bedwarskit.Main.pluginIsDisabling;
import static github.tsffish.bedwarskit.Main.serverVersion;
import static github.tsffish.bedwarskit.util.misc.MessSender.le;
import static github.tsffish.bedwarskit.util.misc.StringMgr.cantFoundSupport;

public class DisableVillagerAI {
    private static final String className = "DisableVillagerAI";
    public static void disableVillagerAI(String worldName){
        if (serverVersion().contains("1.8")){
            try {
                Method method = github.tsffish.bedwarskit.com.v1_8_r8.DisableVillagerAI.class.
                        getMethod("disableVillagerAI", String.class);
                method.invoke(null, worldName);
            }catch (Exception e){
                if (!pluginIsDisabling){
                    le(className, e);
                }
            }
        } else if (serverVersion().contains("1.12")){
            try {
                Method method = github.tsffish.bedwarskit.com.v1_12_r2.DisableVillagerAI.class.
                        getMethod("disableVillagerAI", String.class);
                method.invoke(null, worldName);

            }catch (Exception e){
                if (!pluginIsDisabling){
                    le(className, e);
                }
            }
        }else if (serverVersion().contains("1.9")
        || serverVersion().contains("1.10")
        || serverVersion().contains("1.11")) {
            try {
                Method method = github.tsffish.bedwarskit.com.v1_12_r2.DisableVillagerAI.class.
                        getMethod("disableVillagerAI", String.class);
                method.invoke(null, worldName);

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
