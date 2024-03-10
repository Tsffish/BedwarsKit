package github.tsffish.bedwarskit.listener;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import github.tsffish.bedwarskit.util.player.SendActionBar;
import github.tsffish.bedwarskit.util.player.SoundPlayer;
import io.github.bedwarsrel.events.BedwarsPlayerKilledEvent;
import io.github.bedwarsrel.game.Game;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;
import java.util.UUID;

import static github.tsffish.bedwarskit.config.main.MainConfigHandler.*;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;
import static github.tsffish.bedwarskit.util.player.PlayerSender.sendMessage;
import static github.tsffish.bedwarskit.util.player.RelCurrentStat.*;
import static github.tsffish.bedwarskit.util.player.SendTitleUtil.sendTitlePacket;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class RelPlayerKilled implements Listener {
    private static final Material ironIngot = Material.IRON_INGOT;
    private static final Material goldIngot = Material.GOLD_INGOT;
    private static final Material diamond = Material.DIAMOND;
    private static final Material emerald = Material.EMERALD;
    private static final Material bed_block = Material.BED_BLOCK;

    private String replaceString(
            String text,
            String kName,
            String dName,
            String kHealth,
            String dHealth,
            String ohk
    ) {
        return text
                .replace("{kp}", kName)
                .replace("{dp}", dName)
                .replace("{k_health}", kHealth)
                .replace("{d_health}", dHealth)
                .replace("{ohk}", ohk)
                ;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void on(final BedwarsPlayerKilledEvent event) {

        if (event.getKiller() == null
                || event.getPlayer() == null
                || event.getKiller() == event.getPlayer()
        ) {
            return;
        }
        if (!event.getKiller().isOnline()) {
            return;
        }

        if (event.getGame() == null) {
            return;
        }

        Game game = event.getGame();

        Player k = event.getKiller();
        String kName = k.getName();
        UUID kuuid = k.getUniqueId();
        String kHealth = k.getHealth() + "";
        PlayerInventory kpi = k.getInventory();
        updatePlayerStat(kuuid, addKill, 1);

        Player d = event.getPlayer();
        String dName = d.getName();
        UUID duuid = d.getUniqueId();
        String dHealth = d.getHealth() + "";
        PlayerInventory dpi = d.getInventory();
        updatePlayerStat(duuid, addDeath, 1);
        updatePlayerStat(duuid, setOneHeathKill, 0);

        if (game.getPlayerTeam(d).getHeadTarget() == null
                || game.getPlayerTeam(d).getHeadTarget().getType() != bed_block) {
            updatePlayerStat(kuuid, addFinalKill, 1);
            SoundPlayer.LEVEL_UP(k, 1);

            int entityID = d.getEntityId();

            PacketContainer packet = new PacketContainer(PacketType.Play.Server.ENTITY_STATUS);
            packet.getIntegers().write(0, entityID);
            packet.getBytes().write(1, (byte) 3);
        }

        if (killfb_oneHealthKill) {

            updatePlayerStat(kuuid, addOneHeathKill, 1);

            if (getPlayerOHKill(kuuid) > 1) {
                PlayerInventory pi = k.getInventory();
                ItemStack fbItem = new ItemStack(killfb_oneHealthKill_itemType);
                ItemMeta fbItemMeta = fbItem.getItemMeta();
                fbItemMeta.setDisplayName(t(killfb_oneHealthKill_itemName));
                fbItem.setItemMeta(fbItemMeta);
                pi.addItem(fbItem);

            }
        }

        String ohk = getPlayerOHKill(kuuid) + "";


        if (kill_res) {

            int ironCount = 0;
            int goldCount = 0;
            int diamondCount = 0;
            int emeraldCount = 0;


            if (kpi != null && dpi != null) {
                int size = dpi.getSize();
                for (int i = 0; i < size; i++) {
                    if (
                            dpi.getItem(i) != null
                                    && dpi.getItem(i).getType() == ironIngot
                    ) {
                        kpi.addItem(dpi.getItem(i));
                        dpi.setItem(i, null);
                        ironCount = ironCount + dpi.getItem(i).getAmount();
                    }
                }
                for (int i = 0; i < size; i++) {
                    if (
                            dpi.getItem(i) != null
                                    && dpi.getItem(i).getType() == goldIngot
                    ) {
                        kpi.addItem(dpi.getItem(i));
                        dpi.setItem(i, null);
                        goldCount = goldCount + dpi.getItem(i).getAmount();
                    }
                }

                for (int i = 0; i < size; i++) {
                    if (
                            dpi.getItem(i) != null
                                    && dpi.getItem(i).getType() == diamond
                    ) {
                        kpi.addItem(dpi.getItem(i));
                        dpi.setItem(i, null);
                        diamondCount = diamondCount + dpi.getItem(i).getAmount();
                    }
                }

                for (int i = 0; i < size; i++) {
                    if (
                            dpi.getItem(i) != null
                                    && dpi.getItem(i).getType() == emerald
                    ) {
                        kpi.addItem(dpi.getItem(i));
                        dpi.setItem(i, null);
                        emeraldCount = emeraldCount + dpi.getItem(i).getAmount();
                    }
                }


                if (!Objects.equals(kill_res_chat, "")) {
                    if (ironCount > 0) {
                        sendMessage(k, kill_res_chat
                                .replace("{res}", meanIron)
                                .replace("{count}", ironCount + "")
                        );
                    }
                    if (goldCount > 0) {
                        sendMessage(k, kill_res_chat
                                .replace("{res}", meanGold)
                                .replace("{count}", goldCount + "")
                        );
                    }
                    if (diamondCount > 0) {
                        sendMessage(k, kill_res_chat
                                .replace("{res}", meanDiamond)
                                .replace("{count}", diamondCount + "")
                        );
                    }

                    if (emeraldCount > 0) {
                        sendMessage(k, kill_res_chat
                                .replace("{res}", meanEmerlad)
                                .replace("{count}", emeraldCount + "")
                        );
                    }
                }


            }
        }
        if (killfb_sendmess) {

            if (!Objects.equals(killfb_sendmess_chat, "")) {
                sendMessage(k, replaceString(killfb_sendmess_chat, kName, dName, kHealth, dHealth, ohk));
            }
            if (!Objects.equals(killfb_sendmess_title, "")) {
                String titleReal = replaceString(killfb_sendmess_title, kName, dName, kHealth, dHealth, ohk);
                if (!Objects.equals(killfb_sendmess_subtitle, "")) {
                    String subtitleReal = replaceString(killfb_sendmess_subtitle, kName, dName, kHealth, dHealth, ohk);

                    sendTitlePacket(k, titleReal, subtitleReal,0, 30,5);
                }
            } else if (!Objects.equals(killfb_sendmess_subtitle, "")) {
                String titleReal = " ";
                String subtitleReal = replaceString(killfb_sendmess_subtitle, kName, dName, kHealth, dHealth, ohk);

                sendTitlePacket(k, titleReal, subtitleReal,0, 30,5);
            }
            if (!Objects.equals(killfb_sendmess_actionbar, "")) {
                SendActionBar.sendActionBar(k, replaceString(killfb_sendmess_actionbar, kName, dName, kHealth, dHealth, ohk));
            }
        }
    }
}
