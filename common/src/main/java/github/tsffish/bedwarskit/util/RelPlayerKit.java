package github.tsffish.bedwarskit.util;

import github.tsffish.bedwarskit.util.kit.KitDefault;
import github.tsffish.bedwarskit.util.kit.KitDefaultless;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static github.tsffish.bedwarskit.config.kit.KitConfigHandler.kitenable;
import static github.tsffish.bedwarskit.util.misc.MessSender.le;
import static github.tsffish.bedwarskit.util.misc.StringMgr.vauleIsWrong;
/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class RelPlayerKit {
    private static final String className = "RelPlayerKit";
    public static final String kitNameDefault = "default";
    public static final String kitNameNone = "none";
    public static final String kitNameDefaultLess = "defaultless";
    private static ConcurrentHashMap<UUID, String> playerKitList = new ConcurrentHashMap<>(101);
    public static void applykit(UUID uuid){
        if (kitenable){
        String kit = playerKitList.get(uuid);
            if (kit != null) {
                pdKit(uuid,kit);
            }
        }
    }

    public static void applykitforce(UUID uuid, String kit){
        pdKit(uuid,kit);
    }

static void pdKit(UUID uuid, String kit){
    switch (kit.toLowerCase()){
        case kitNameDefault:
            KitDefault.setKit(uuid, true);
            break;
        case kitNameDefaultLess:
            KitDefaultless.setKit(uuid, true);
            break;
        case kitNameNone:
            break;
        default:
            sendError("pdkit", kit);
            break;
    }
}
    public static String getPlayerKit(UUID uuid){
        return playerKitList.get(uuid);
    }
    public static void setPlayerKit(UUID uuid,String kit){
        playerKitList.put(uuid,kit);
}

    private static void sendError(String method, String kit){
        le(className,method + " error: " + "kit" + vauleIsWrong + ": " + kit);
    }

}
