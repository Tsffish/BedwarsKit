package github.tsffish.bedwarskit.util;

import github.tsffish.bedwarskit.config.kit.KitConfigHandler;
import github.tsffish.bedwarskit.util.kit.KitDefault;
import github.tsffish.bedwarskit.util.kit.KitDefaultless;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

import static github.tsffish.bedwarskit.util.misc.ErrorCause.vauleIsWrong;
import static github.tsffish.bedwarskit.util.misc.MessSender.le;

public class RelPlayerKit {
    private static Map<String, String> playerKitList = new HashMap<>(100);
    public static void applykit(Player player){
        String playerName = player.getName();
        if (KitConfigHandler.kitenable){
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
                        sendError("setkit",kit);
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
                sendError("setforcekit", kit);
                break;
        }
}
    public static String getPlayerKit(String playerName){
        return playerKitList.get(playerName);
    }

    public static void setPlayerKit(String playerName,String kit){
        playerKitList.put(playerName,kit);
}


    private static void sendError(String method,String kit){
        le("RelPlayerKit",method + " error: " + "kit" + vauleIsWrong() + ": " + kit);
    }

}
