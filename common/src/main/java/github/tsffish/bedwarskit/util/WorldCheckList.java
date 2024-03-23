package github.tsffish.bedwarskit.util;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class WorldCheckList {
    private static Set<String> checkList;
    static {
        checkList = Collections.newSetFromMap(new ConcurrentHashMap<>(17));
    }
    public static void joinCheckList(String worldName) {
        checkList.add(worldName);
    }
    public static void leaveCheckList(String worldName) {
        checkList.remove(worldName);
    }
    public static boolean isInCheckList(String worldName) {
        return checkList.contains(worldName);
    }
}
