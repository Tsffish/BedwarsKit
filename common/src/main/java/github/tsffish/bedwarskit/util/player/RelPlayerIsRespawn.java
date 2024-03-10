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
public class RelPlayerIsRespawn {
    private static Set<UUID> playerRespawn = Collections.newSetFromMap(new ConcurrentHashMap<>());

    public static boolean getPlayerRespawn(UUID uuid) {
        return playerRespawn.contains(uuid);
    }

    public static void addPlayerRespawn(UUID uuid) {
        playerRespawn.add(uuid);
    }

    public static void removePlayerRespawn(UUID uuid) {
        playerRespawn.remove(uuid);
    }
}
