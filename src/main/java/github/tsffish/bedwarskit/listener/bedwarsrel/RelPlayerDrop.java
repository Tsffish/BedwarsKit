package github.tsffish.bedwarskit.listener.bedwarsrel;

import github.tsffish.bedwarskit.config.MainConfigHandler;
import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.Inventory;

public class RelPlayerDrop implements Listener {
    public boolean antiDrop;
    Material woodSword;
    Material stoneSword;
    Material ironSword;
    Material diamondSword;

    public RelPlayerDrop() {
        this.antiDrop = MainConfigHandler.antiDrop;
        this.woodSword = Material.WOOD_SWORD;
        this.stoneSword = Material.STONE_SWORD;
        this.ironSword = Material.IRON_SWORD;
        this.diamondSword = Material.DIAMOND_SWORD;
    }

    @EventHandler
    public void on(PlayerDropItemEvent e) {
        Player p = e.getPlayer();
        Inventory inv = p.getInventory();
        Material dropItemType = e.getItemDrop().getItemStack().getType();
        GameManager gm = BedwarsRel.getInstance().getGameManager();
        Game game = gm.getGameOfPlayer(p);
        if (this.antiDrop && game != null && game.getTimeLeft() != game.getLength()) {
            if (dropItemType == this.woodSword) {
                if(game.getTimeLeft() == game.getLength()){
                    e.setCancelled(true);
                    inv.remove(woodSword);
                }

                if (!inv.contains(this.stoneSword) && !inv.contains(this.ironSword) && !inv.contains(this.diamondSword)) {
                    e.setCancelled(true);
                }

                if (MainConfigHandler.NoDropList.contains(dropItemType)) {
                    e.setCancelled(true);
                }
            }

            if (MainConfigHandler.NoDropList.contains(dropItemType)) {
                e.setCancelled(true);
            }
        }

    }
}
