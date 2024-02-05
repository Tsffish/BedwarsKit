package github.tsffish.bedwarskit.listener.bedwarsrel;

import github.tsffish.bedwarskit.config.MainConfigHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;

public class RelNoItemBreak implements Listener {
    @EventHandler
    public void on(PlayerItemDamageEvent event) {
        if (!MainConfigHandler.NoItemBreak) return;

        ItemStack item = event.getItem();
        short currentDurability = item.getDurability();
        if (item.getType().toString().contains("SWORD")) {
            if (currentDurability + event.getDamage() >= item.getType().getMaxDurability()){
                event.setCancelled(true);
            item.setDurability((short) 0);
            }
                }
        }
}
