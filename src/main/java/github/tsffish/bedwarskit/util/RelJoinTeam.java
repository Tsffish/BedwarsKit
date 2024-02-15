package github.tsffish.bedwarskit.util;

import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.game.Game;
import io.github.bedwarsrel.game.GameManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.PlayerInventory;

public class RelJoinTeam implements Listener {
    private static final Material teamItem = Material.LEATHER_CHESTPLATE;
    @EventHandler
    public static void checkLobbyItem(Player player){
        GameManager gameManager = BedwarsRel.getInstance().getGameManager();
        Game game = gameManager.getGameOfPlayer(player);

        //PlayerInventory playerInventory = player.getInventory();
        //if (playerInventory == null) return;
        //if(lobbyleaveTeam) {
         //   Team playerTeam = game.getPlayerTeam(player);
         //   String playerTeamName = playerTeam.getName();
          //  for (ItemStack itemStack : playerInventory) {
          //      if (itemStack == null)return;
          //      Material itemType = itemStack.getType();
          //      String itemTypeText = itemType.toString();
          //      ItemMeta itemMeta = itemStack.getItemMeta();
          //      if (itemTypeText.equalsIgnoreCase(teamItem.name())) {
          //          String realItemName = lobbyleaveTeamItemName.replace("{playerTeamColor}", playerTeam.getChatColor().toString()).replace("{playerTeamName}", playerTeamName);
          //          itemMeta.setDisplayName(t(realItemName));
         //           itemStack.setItemMeta(itemMeta);
         //           break;
        //        }
        //    }
      //  }
    }
}
