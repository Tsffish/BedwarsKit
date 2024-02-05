package github.tsffish.bedwarskit.listener;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.Objects;

import static github.tsffish.bedwarskit.config.MainConfigHandler.*;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;

public class PlayerDamageHandler implements Listener {

    @EventHandler
    public void on(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
            Player damagedPlayer = (Player) e.getEntity();
            Player attackingPlayer = (Player) e.getDamager();

            if (damagefb_attackBlood){
          switch (damagefb_attackBloodMode.toLowerCase()) {
                case "single":
                    damagedPlayer.getWorld().playEffect(damagedPlayer.getLocation(), Effect.STEP_SOUND, 152);
            break;
               case "player":
                   damagedPlayer.getWorld().playEffect(damagedPlayer.getLocation(), Effect.STEP_SOUND, 152);
                   damagedPlayer.getWorld().playEffect(damagedPlayer.getLocation().add(1, 0, 0), Effect.STEP_SOUND, 152);
                   break;
               case "box":
                   for (int x = -1; x <= 1; x++) {
                       for (int y = -1; y <= 1; y++) {
                           for (int z = -1; z <= 1; z++) {
                               Location location = damagedPlayer.getLocation().add(x, y, z);
                               damagedPlayer.getWorld().playEffect(location, Effect.STEP_SOUND, 152);
                           }
                        }
                   }
                    break;
                default:
                    break;
            }
            }

            if (damagefb_Title){
                String damagefb_attackTitleReal;
                double damage = e.getDamage();
                if (Objects.equals(damagefb_attackTitle, "")) {
                    damagefb_attackTitleReal = " ";
                }else {
                    damagefb_attackTitleReal = damagefb_attackTitle.replace("{damage}",damage + "");
                }
            String damagefb_attackSubTitleReal = damagefb_attackSubTitle.replace("{damage}",damage + "");
            // 向攻击别人的玩家发送伤害值
            attackingPlayer.sendTitle(t(damagefb_attackTitleReal) ,t(damagefb_attackSubTitleReal));
            }
        }
    }
}
