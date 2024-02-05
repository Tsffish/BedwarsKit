package github.tsffish.bedwarskit.util;

import github.tsffish.bedwarskit.config.KitConfigHandler;
import github.tsffish.bedwarskit.config.kit.KitDefault;
import github.tsffish.bedwarskit.config.kit.KitNone;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

import static github.tsffish.bedwarskit.util.misc.ErrorLogger.le;

public class RelPlayerKit {
    public static Map<Player, String> playerKitList = new HashMap<>();
    public static void setkit(Player player){

        if (KitConfigHandler.kitenable){
        String kit = playerKitList.get(player);
            if (kit != null) {
                switch (kit.toLowerCase()) {
                    case "default":
                        KitDefault.setKit(player, true);
                        break;
                    case "none":
                        KitNone.setKit(player);
                        break;
                    default:
                        le("RelPlayerKit setkit Error: kit is wrong vaule : " + kit);
                        break;
                }
            }
    }
}

public static void setforcekit(Player player, String kit){
        switch (kit){
            case "Default":
                KitDefault.setKit(player, true);
            case "None":
                KitNone.setKit(player);
            default:
                le("RelPlayerKit setforcekit Error: kit is wrong vaule : " + kit);
                break;

        }



}

}
