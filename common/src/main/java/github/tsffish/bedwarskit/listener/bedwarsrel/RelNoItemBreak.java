package github.tsffish.bedwarskit.listener.bedwarsrel;

import github.tsffish.bedwarskit.config.main.MainConfigHandler;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;

import static github.tsffish.bedwarskit.Main.isDebug;
import static github.tsffish.bedwarskit.config.main.MainConfigHandler.nobreakList;
import static github.tsffish.bedwarskit.util.misc.MessSender.le;

public class RelNoItemBreak implements Listener {
    @EventHandler
    public void on(final PlayerItemDamageEvent event) {
        if (!MainConfigHandler.NoItemBreak) {
            return;
        }
        if (nobreakList == null){
        if(isDebug()) {
            le("RelNoItemBreak", "NoBreakList == null ");
        }
            return;
        }
        if (nobreakList.isEmpty()){
            return;
        }
        ItemStack item = event.getItem();
        Material itemType = item.getType();
        String itemTypeText = itemType.toString();

        int damage = event.getDamage();

        short current = item.getDurability();
        short max = itemType.getMaxDurability();

        if (nobreakList.contains(itemTypeText)){

            if (current + damage >= max){

                event.setCancelled(true);
                item.setDurability((short) 0);

            }
        }
    }
}
