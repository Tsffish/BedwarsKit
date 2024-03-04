package github.tsffish.bedwarskit.com.v1_8_r8;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class ActionBarSender {
 public static void sendActionBar(Player player, String string){
  io.github.bedwarsrel.com.v1_8_r3.ActionBar.sendActionBar(player, string);
 }
}