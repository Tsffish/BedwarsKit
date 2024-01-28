package github.tsffish.bedwarskit.util;

import github.tsffish.bedwarskit.listener.bedwarsrel.RelEnchant;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameState;
import io.github.bedwarsrel.game.Team;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class RelCheckEnchant {

    public static void checkEnchantArmor(Game game) {
        for (Map.Entry<String, String[]> entry : RelTeamEnchant.teamEnchantListProt.entrySet()) {
            String mapName = entry.getKey();
            String[] enchantData = entry.getValue();

            String teamName = enchantData[0];
            String enchantLevel = enchantData[1];

            // 根据enchantLevel给玩家附魔
            if (game != null) {
                List<Team> teams = new ArrayList<>(game.getPlayingTeams()); // 转换成ArrayList
                for (Team team : teams) {
                    for (Player player : team.getPlayers()) {
                        if (Objects.equals(game.getRegion().getName(), mapName) && Objects.equals(teamName, team.getName()) && game.getState() == GameState.RUNNING) {
                            ItemStack[] armor = player.getInventory().getArmorContents();
                            for (ItemStack item : armor) {
                                ItemMeta itemMeta = item.getItemMeta();
                                if (itemMeta != null) {
                                    switch (enchantLevel) {
                                        case "1":
                                            itemMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
                                            break;
                                        case "2":
                                            itemMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
                                            break;
                                        case "3":
                                            itemMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
                                            break;
                                        case "4":
                                            itemMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
                                            break;
                                        default:
                                            break;
                                    }
                                    item.setItemMeta(itemMeta);
                                }
                            }
                            player.getInventory().setArmorContents(armor);
                        }
                    }
                }
            }
        }
    }

    public static void checkEnchantSword(Game game) {
        for (Map.Entry<String, String[]> entry : RelTeamEnchant.teamEnchantListSword.entrySet()) {
            String mapName = entry.getKey();
            String[] enchantData = entry.getValue();

            String teamName = enchantData[0];

            // 根据enchantLevel给玩家附魔
            if (game != null) {
                List<Team> teams = new ArrayList<>(game.getPlayingTeams()); // 转换成ArrayList
                for (Team team : teams) {
                        if (Objects.equals(game.getRegion().getName(), mapName)  && Objects.equals(teamName, team.getName())&& game.getState() == GameState.RUNNING) {
                            RelEnchant.setSharp1(team);
                    }
                }
            }
        }
    }

}
