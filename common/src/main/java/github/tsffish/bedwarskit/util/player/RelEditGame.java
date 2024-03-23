package github.tsffish.bedwarskit.util.player;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class RelEditGame {
    private static Set<UUID> editGamePlayer;

    static {
        editGamePlayer = Collections.newSetFromMap(new ConcurrentHashMap<>());
    }

    public static void addEditGamePlayer(UUID uuid) {
        editGamePlayer.add(uuid);
    }

    public static boolean isEditGamePlayer(UUID uuid) {
        return editGamePlayer.contains(uuid);
    }

    public static void removeEditGamePlayer(UUID uuid) {
        editGamePlayer.remove(uuid);
    }
}
