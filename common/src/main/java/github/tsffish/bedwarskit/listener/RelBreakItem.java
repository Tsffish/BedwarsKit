package github.tsffish.bedwarskit.listener;

import github.tsffish.bedwarskit.config.main.MainConfigHandler;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;

import static github.tsffish.bedwarskit.BedwarsKit.isDebug;
import static github.tsffish.bedwarskit.config.main.MainConfigHandler.nobreakList;
import static github.tsffish.bedwarskit.util.misc.MessSender.le;

public class RelBreakItem implements Listener {
    private static final String className = "RelNoItemBreak";
    @EventHandler
    public void on(final PlayerItemDamageEvent event) {

        if (!MainConfigHandler.NoItemBreak) {return;}
        if (nobreakList == null){if(isDebug()) {le(className, "NoBreakList == null ");}return;}
        if (nobreakList.isEmpty()){if(isDebug()) {le(className, "NoBreakList is empty ");}return;}

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
