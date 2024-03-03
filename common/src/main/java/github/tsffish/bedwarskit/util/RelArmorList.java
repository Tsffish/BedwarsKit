package github.tsffish.bedwarskit.util;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RelArmorList {
    private static List<UUID> armorChain = new ArrayList<>(101);
    private static List<UUID> armorIron = new ArrayList<>(101);
    private static List<UUID> armorDiamond = new ArrayList<>(101);
    public static void addArmorChain(UUID uuid){
        armorChain.add(uuid);
    }
    public static boolean hasArmorChain(UUID uuid){
        return armorChain.contains(uuid);
    }
    public static void removeArmorChain(UUID uuid){
        armorChain.remove(uuid);
    }
    public static void addArmorIron(UUID uuid){
        armorIron.add(uuid);
    }
    public static boolean hasArmorIron(UUID uuid){
        return armorIron.contains(uuid);
    }
    public static void removeArmorIron(UUID uuid){
        armorIron.remove(uuid);
    }
    public static void addArmorDiamond(UUID uuid){
        armorDiamond.add(uuid);
    }
    public static boolean hasArmorDiamond(UUID uuid){
        return armorDiamond.contains(uuid);
    }
    public static void removeArmorDiamond(UUID uuid){
        armorDiamond.remove(uuid);
    }
}