package github.tsffish.bedwarskit.listener.bedwarsrel;

import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameState;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

public class RelPlayerDrop implements Listener {

        @EventHandler
        public void on(PlayerDropItemEvent event) {
            Player player = event.getPlayer();
            Game game = BedwarsRel.getInstance().getGameManager().getGameOfPlayer(player);
            // 检查游戏状态是否为RUNNING
            if (game != null && player != null && player.isOnline() && game.getState() == GameState.RUNNING && player.getGameMode() == GameMode.SURVIVAL) {

                ItemStack droppedItem = event.getItemDrop().getItemStack();

                if (droppedItem.getType() == Material.WOOD_SWORD) { // 检查是否丢弃的是木剑
                    boolean hasOtherSword = false; // 是否有其他剑的标志位
                    ItemStack[] inventory = player.getInventory().getContents(); // 获取玩家背包物品数组

                    for (ItemStack item : inventory) {
                        if (item != null && item.getType().name().endsWith("_SWORD") && item != droppedItem) {
                            // 判断是否有除了木剑以外的任何剑，排除掉刚刚丢弃的木剑
                            hasOtherSword = true;
                        }
                    }

                    if (!hasOtherSword) {
                        event.setCancelled(true); // 取消丢弃事件
                    }
                }
            }
            }
        }
