package github.tsffish.bedwarskit.util.teamshop;

import github.tsffish.bedwarskit.config.main.MainConfigHandler;
import io.github.bedwarsrel.events.BedwarsGameStartedEvent;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.Team;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Map;

import static github.tsffish.bedwarskit.util.teamshop.RelTeamEffect.setDefaultTeamEff;
import static github.tsffish.bedwarskit.util.teamshop.RelTeamEnchant.setDefaultTeamEnch;

public class RelEnchant {
    public static void setsharp1(Team team) {
        Enchantment enchantment = Enchantment.DAMAGE_ALL;
        int enchantmentLevel = 1;

        for (Player player : team.getPlayers()){
            PlayerInventory pi = player.getInventory();

            ItemStack[] itemStacks = pi.getContents();
            for (ItemStack list : itemStacks){
                if (list != null){
                    Material itemType = list.getType();
                    String itemTypeText = itemType.toString();
                    if (MainConfigHandler.giveSharpEnchList.contains(itemTypeText)){
                    list.addEnchantment(enchantment, enchantmentLevel);
                    }
                }
            }
        }
    }

    public static void setsharp2(Team team) {
        Enchantment enchantment = Enchantment.DAMAGE_ALL;
        int enchantmentLevel = 2;

        for (Player player : team.getPlayers()){
            PlayerInventory pi = player.getInventory();

            ItemStack[] itemStacks = pi.getContents();
            for (ItemStack list : itemStacks){
                if (list != null){
                    Material itemType = list.getType();
                    String itemTypeText = itemType.toString();
                    if (MainConfigHandler.giveSharpEnchList.contains(itemTypeText)){
                        list.addEnchantment(enchantment, enchantmentLevel);
                    }
                }
            }
        }
    }
    public static void setsharp3(Team team) {
        Enchantment enchantment = Enchantment.DAMAGE_ALL;
        int enchantmentLevel = 3;

        for (Player player : team.getPlayers()){
            PlayerInventory pi = player.getInventory();

            ItemStack[] itemStacks = pi.getContents();
            for (ItemStack list : itemStacks){
                if (list != null){
                    Material itemType = list.getType();
                    String itemTypeText = itemType.toString();
                    if (MainConfigHandler.giveSharpEnchList.contains(itemTypeText)){
                        list.addEnchantment(enchantment, enchantmentLevel);
                    }
                }
            }
        }
    }
    public static void setsharp4(Team team) {
        Enchantment enchantment = Enchantment.DAMAGE_ALL;
        int enchantmentLevel = 4;

        for (Player player : team.getPlayers()){
            PlayerInventory pi = player.getInventory();

            ItemStack[] itemStacks = pi.getContents();
            for (ItemStack list : itemStacks){
                if (list != null){
                    Material itemType = list.getType();
                    String itemTypeText = itemType.toString();
                    if (MainConfigHandler.giveSharpEnchList.contains(itemTypeText)){
                        list.addEnchantment(enchantment, enchantmentLevel);
                    }
                }
            }
        }
    }



    public static void setprot1(Team team) {
        Enchantment enchantment = Enchantment.PROTECTION_ENVIRONMENTAL;
        int enchantmentLevel = 1;

        for (Player player : team.getPlayers()){
            PlayerInventory pi = player.getInventory();

            ItemStack[] itemStacks = pi.getArmorContents();
            for (ItemStack list : itemStacks){
                if (list != null){
                    Material itemType = list.getType();
                    String itemTypeText = itemType.toString();
                    if (MainConfigHandler.giveProtEnchList.contains(itemTypeText)){
                        list.addEnchantment(enchantment, enchantmentLevel);
                    }
                }
            }
        }

    }

    public static void setprot2(Team team) {
        Enchantment enchantment = Enchantment.PROTECTION_ENVIRONMENTAL;
        int enchantmentLevel = 2;

        for (Player player : team.getPlayers()){
            PlayerInventory pi = player.getInventory();

            ItemStack[] itemStacks = pi.getArmorContents();
            for (ItemStack list : itemStacks){
                if (list != null){
                    Material itemType = list.getType();
                    String itemTypeText = itemType.toString();
                    if (MainConfigHandler.giveProtEnchList.contains(itemTypeText)){
                        list.addEnchantment(enchantment, enchantmentLevel);
                    }
                }
            }
        }
    }

    public static void setprot3(Team team) {
        Enchantment enchantment = Enchantment.PROTECTION_ENVIRONMENTAL;
        int enchantmentLevel = 3;

        for (Player player : team.getPlayers()){
            PlayerInventory pi = player.getInventory();

            ItemStack[] itemStacks = pi.getArmorContents();
            for (ItemStack list : itemStacks){
                if (list != null){
                    Material itemType = list.getType();
                    String itemTypeText = itemType.toString();
                    if (MainConfigHandler.giveProtEnchList.contains(itemTypeText)){
                        list.addEnchantment(enchantment, enchantmentLevel);
                    }
                }
            }
        }
    }

    public static void setprot4(Team team) {
        Enchantment enchantment = Enchantment.PROTECTION_ENVIRONMENTAL;
        int enchantmentLevel = 4;

        for (Player player : team.getPlayers()){
            PlayerInventory pi = player.getInventory();

            ItemStack[] itemStacks = pi.getArmorContents();
            for (ItemStack list : itemStacks){
                if (list != null){
                    Material itemType = list.getType();
                    String itemTypeText = itemType.toString();
                    if (MainConfigHandler.giveProtEnchList.contains(itemTypeText)){
                        list.addEnchantment(enchantment, enchantmentLevel);
                    }
                }
            }
        }
    }
}
