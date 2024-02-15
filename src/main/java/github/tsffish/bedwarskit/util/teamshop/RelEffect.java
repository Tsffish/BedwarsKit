package github.tsffish.bedwarskit.util.teamshop;

import io.github.bedwarsrel.game.Team;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class RelEffect {


    public static void sethaste1(Team team) {
        PotionEffect potionEffect = new PotionEffect(PotionEffectType.FAST_DIGGING,
                99999,0,false) {
        };

        for (Player player : team.getPlayers()){
            player.removePotionEffect(potionEffect.getType());
            player.addPotionEffect(potionEffect);
        }
    }

    public static void sethaste2(Team team) {
        PotionEffect potionEffect = new PotionEffect(PotionEffectType.FAST_DIGGING,
                99999,1,false) {
        };

        for (Player player : team.getPlayers()){
                player.removePotionEffect(potionEffect.getType());
                player.addPotionEffect(potionEffect);
        }
    }


    public static void setheal1(Team team) {
        PotionEffect potionEffect = new PotionEffect(PotionEffectType.REGENERATION,
                10 * 20,0,false) {
        };

        for (Player player : team.getPlayers()){

            player.addPotionEffect(potionEffect);
        }
    }

}
