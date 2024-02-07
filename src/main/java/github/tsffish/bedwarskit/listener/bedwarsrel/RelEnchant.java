package github.tsffish.bedwarskit.listener.bedwarsrel;

import github.tsffish.bedwarskit.util.RelTeamEnchant;
import io.github.bedwarsrel.events.BedwarsGameStartedEvent;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.Team;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.Map;

public class RelEnchant implements Listener {
    @EventHandler
    public void on(BedwarsGameStartedEvent event) {
        Game game = event.getGame();

        if (game == null) return;
        Map<String, Team> teams = game.getTeams();

        String mapName = game.getRegion().getName();
        for (Team team : teams.values()) {

            String teamName = team.getName();
            String[] values = RelTeamEnchant.teamEnchantListSword.get(mapName);
            if (values == null) {
                values = new String[3];
            }
            values[0] = teamName;
            values[1] = "0";
            RelTeamEnchant.teamEnchantListSword.put(mapName, values);

            String[] values1 = RelTeamEnchant.teamEnchantListProt.get(mapName);
            if (values1 == null) {
                values1 = new String[3];
            }
            values1[0] = teamName;
            values1[1] = "0";
            RelTeamEnchant.teamEnchantListProt.put(mapName, values1);

        }
    }





    public static void setSharp1(Team team) {
        Enchantment enchantment = Enchantment.DAMAGE_ALL;
        int enchantmentLevel = 1;

        for (Player player : team.getPlayers()){
            PlayerInventory pi = player.getInventory();

            ItemStack[] itemStacks = pi.getContents();
            for (ItemStack list : itemStacks){
                if (list != null && list.getType().toString().contains("SWORD")){
                    list.addEnchantment(enchantment, enchantmentLevel);
                }
            }
        }
    }

    public static void setProt1(Team team) {
        Enchantment enchantment = Enchantment.PROTECTION_ENVIRONMENTAL;
        int enchantmentLevel = 1;

        for (Player player : team.getPlayers()){
            PlayerInventory playerInventory = player.getInventory();
            ItemStack helmet = playerInventory.getHelmet();
            ItemStack chest = playerInventory.getChestplate();
            ItemStack leg = playerInventory.getLeggings();
            ItemStack boots = playerInventory.getBoots();

            helmet.addEnchantment(enchantment, enchantmentLevel);
            chest.addEnchantment(enchantment, enchantmentLevel);
            leg.addEnchantment(enchantment, enchantmentLevel);
            boots.addEnchantment(enchantment, enchantmentLevel);
        }
    }

    public static void setProt2(Team team) {
        Enchantment enchantment = Enchantment.PROTECTION_ENVIRONMENTAL;
        int enchantmentLevel = 2;

        for (Player player : team.getPlayers()){
            PlayerInventory playerInventory = player.getInventory();
            ItemStack helmet = playerInventory.getHelmet();
            ItemStack chest = playerInventory.getChestplate();
            ItemStack leg = playerInventory.getLeggings();
            ItemStack boots = playerInventory.getBoots();

            helmet.addEnchantment(enchantment, enchantmentLevel);
            chest.addEnchantment(enchantment, enchantmentLevel);
            leg.addEnchantment(enchantment, enchantmentLevel);
            boots.addEnchantment(enchantment, enchantmentLevel);
        }
    }

    public static void setProt3(Team team) {
        Enchantment enchantment = Enchantment.PROTECTION_ENVIRONMENTAL;
        int enchantmentLevel = 3;

        for (Player player : team.getPlayers()){
            PlayerInventory playerInventory = player.getInventory();
            ItemStack helmet = playerInventory.getHelmet();
            ItemStack chest = playerInventory.getChestplate();
            ItemStack leg = playerInventory.getLeggings();
            ItemStack boots = playerInventory.getBoots();

            helmet.addEnchantment(enchantment, enchantmentLevel);
            chest.addEnchantment(enchantment, enchantmentLevel);
            leg.addEnchantment(enchantment, enchantmentLevel);
            boots.addEnchantment(enchantment, enchantmentLevel);
        }
    }

    public static void setProt4(Team team) {
        Enchantment enchantment = Enchantment.PROTECTION_ENVIRONMENTAL;
        int enchantmentLevel = 4;

        for (Player player : team.getPlayers()){
            PlayerInventory playerInventory = player.getInventory();
            ItemStack helmet = playerInventory.getHelmet();
            ItemStack chest = playerInventory.getChestplate();
            ItemStack leg = playerInventory.getLeggings();
            ItemStack boots = playerInventory.getBoots();

            helmet.addEnchantment(enchantment, enchantmentLevel);
            chest.addEnchantment(enchantment, enchantmentLevel);
            leg.addEnchantment(enchantment, enchantmentLevel);
            boots.addEnchantment(enchantment, enchantmentLevel);
        }
    }


}
