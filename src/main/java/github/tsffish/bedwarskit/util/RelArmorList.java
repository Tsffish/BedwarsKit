package github.tsffish.bedwarskit.util;

import java.util.ArrayList;
import java.util.List;

public class RelArmorList {
    private static List<String> armorChain = new ArrayList<>(100);
    private static List<String> armorIron = new ArrayList<>(100);
    private static List<String> armorDiamond = new ArrayList<>(100);

    public static void addArmorChain(String playerName){
        armorChain.add(playerName);
    }
    public static boolean hasArmorChain(String playerName){
        return armorChain.contains(playerName);
    }
    public static void removeArmorChain(String playerName){
        armorChain.remove(playerName);
    }

    public static void addArmorIron(String playerName){
        armorIron.add(playerName);
    }
    public static boolean hasArmorIron(String playerName){
        return armorIron.contains(playerName);
    }
    public static void removeArmorIron(String playerName){
        armorIron.remove(playerName);
    }

    public static void addArmorDiamond(String playerName){
        armorDiamond.add(playerName);
    }
    public static boolean hasArmorDiamond(String playerName){
        return armorDiamond.contains(playerName);
    }
    public static void removeArmorDiamond(String playerName){
        armorDiamond.remove(playerName);
    }


}