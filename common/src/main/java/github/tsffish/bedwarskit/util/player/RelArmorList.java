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
public class RelArmorList {
    private static Set<UUID> armorChain;
    private static Set<UUID> armorIron;
    private static Set<UUID> armorDiamond;

    static {
        armorChain = Collections.newSetFromMap(new ConcurrentHashMap<>());
        armorIron = Collections.newSetFromMap(new ConcurrentHashMap<>());
        armorDiamond = Collections.newSetFromMap(new ConcurrentHashMap<>());
    }

    public static void addArmorChain(UUID uuid) {
        armorChain.add(uuid);
    }

    public static boolean hasArmorChain(UUID uuid) {
        return armorChain.contains(uuid);
    }

    public static void removeArmorChain(UUID uuid) {
        armorChain.remove(uuid);
    }

    public static void addArmorIron(UUID uuid) {
        armorIron.add(uuid);
    }

    public static boolean hasArmorIron(UUID uuid) {
        return armorIron.contains(uuid);
    }

    public static void removeArmorIron(UUID uuid) {
        armorIron.remove(uuid);
    }

    public static void addArmorDiamond(UUID uuid) {
        armorDiamond.add(uuid);
    }

    public static boolean hasArmorDiamond(UUID uuid) {
        return armorDiamond.contains(uuid);
    }

    public static void removeArmorDiamond(UUID uuid) {
        armorDiamond.remove(uuid);
    }
}