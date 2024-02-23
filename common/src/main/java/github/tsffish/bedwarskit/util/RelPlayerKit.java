package github.tsffish.bedwarskit.util;

import github.tsffish.bedwarskit.util.kit.KitDefault;
import github.tsffish.bedwarskit.util.kit.KitDefaultless;
import org.bukkit.entity.Player;

import java.util.concurrent.ConcurrentHashMap;

import static github.tsffish.bedwarskit.config.kit.KitConfigHandler.kitenable;
import static github.tsffish.bedwarskit.util.misc.MessSender.le;
import static github.tsffish.bedwarskit.util.misc.StringMgr.vauleIsWrong;

public class RelPlayerKit {
    static ConcurrentHashMap<String, String> playerKitList = new ConcurrentHashMap<>(100);
    public static void applykit(Player player){
        String playerName = player.getName();
        if (kitenable){
        String kit = playerKitList.get(playerName);
            if (kit != null) {
                switch (kit.toLowerCase()) {
                    case "default":
                        KitDefault.setKit(player, true);
                        break;
                    case "defaultless":
                        KitDefaultless.setKit(player, true);
                        break;
                    case "none":
                        break;
                    default:
                        sendError("applykit",kit);
                        break;
                }
            }
    }
}

public static void applykitforce(Player player, String kit){
        switch (kit.toLowerCase()){
            case "default":
                KitDefault.setKit(player, true);
                break;
            case "defaultless":
                KitDefaultless.setKit(player, true);
                break;
            case "none":
                break;
            default:
                sendError("applykitforce", kit);
                break;
        }
}

static void pdKit(Player player, String kit){
    switch (kit.toLowerCase()){
        case "default":
            KitDefault.setKit(player, true);
            break;
        case "defaultless":
            KitDefaultless.setKit(player, true);
            break;
        case "none":
            break;
        default:
            sendError("applykitforce", kit);
            break;
    }
}










    private static final String className = "RelPlayerKit";

    public static String getPlayerKit(String playerName){
        return playerKitList.get(playerName);
    }

    public static void setPlayerKit(String playerName,String kit){
        playerKitList.put(playerName,kit);
}


    static void sendError(String method, String kit){
        le(className,method + " error: " + "kit" + vauleIsWrong + ": " + kit);
    }

}
