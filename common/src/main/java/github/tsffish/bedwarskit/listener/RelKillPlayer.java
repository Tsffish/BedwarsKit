package github.tsffish.bedwarskit.listener;

import github.tsffish.bedwarskit.util.RelCurrentStat;
import github.tsffish.bedwarskit.util.misc.SoundPlayer;
import io.github.bedwarsrel.events.BedwarsPlayerKilledEvent;
import io.github.bedwarsrel.game.Game;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.EnumMap;
import java.util.Map;
import java.util.UUID;

import static github.tsffish.bedwarskit.config.main.MainConfigHandler.*;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;
import static github.tsffish.bedwarskit.util.RelCurrentStat.updatePlayerStat;
import static io.github.bedwarsrel.com.v1_8_r3.ActionBar.sendActionBar;

public class RelKillPlayer implements Listener {
    static Map<Material, Integer> list;
    private static final Material ironIngot = Material.IRON_INGOT;
    private static final Material goldIngot = Material.GOLD_INGOT;
    private static final Material diamond = Material.DIAMOND;
    private static final Material emerald = Material.EMERALD;
    private String replaceString(
            String text,
            String kName,
            String dName,
            String kHealth,
            String dHealth,
            UUID kuuid
                                    )
    {
        return text
                .replace("{kp}", kName)
                .replace("{dp}", dName)
                .replace("{k_health}", kHealth)
                .replace("{d_health}", dHealth)
                .replace("{ohk}",RelCurrentStat.getPlayerOHKill(kuuid) + "")
                ;
    }
    @EventHandler
    public void on(final BedwarsPlayerKilledEvent e) {

        if (e.getKiller() == null && e.getPlayer() == null) {
            return;
        }
        if (!e.getKiller().isOnline()){
            return;
        }
        Game game = e.getGame();

        if (game == null) {
            return;
        }

        if (!kill_res || e.getKiller() == null || e.getKiller() == e.getPlayer()) {
            return;
        }
            Player k = e.getKiller();
            String kName = k.getName();
            UUID kuuid = k.getUniqueId();
            String kHealth = k.getHealth() + "";
            PlayerInventory kpi = k.getInventory();
            updatePlayerStat(kuuid, "k", 1);

            Player d = e.getPlayer();
            String dName = d.getName();
            UUID duuid = d.getUniqueId();
            String dHealth = d.getHealth() + "";
            PlayerInventory dpi = d.getInventory();
            updatePlayerStat(duuid, "d", 1);


            if (game.getPlayerTeam(d).getHeadTarget() == null || game.getPlayerTeam(d).getHeadTarget().getType() != Material.BED) {
                updatePlayerStat(kuuid, "f", 1);
                SoundPlayer.LEVEL_UP(k,1);
            }

            if (killfb_oneHealthKill){

                updatePlayerStat(kuuid,"ohk", 1);

                if (RelCurrentStat.getPlayerOHKill(kuuid) > 1){
                PlayerInventory pi = k.getInventory();
                ItemStack fbItem = new ItemStack(killfb_oneHealthKill_itemType);
                ItemMeta fbItemMeta = fbItem.getItemMeta();
                fbItemMeta.setDisplayName(t(killfb_oneHealthKill_itemName));
                fbItem.setItemMeta(fbItemMeta);
                pi.addItem(fbItem);
                }
            }

            int ironCount = 0;
            int goldCount = 0;
            int diamondCount = 0;
            int emeraldCount = 0;

            if (kpi == null || dpi == null) {
                return;
            }
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

                if (list == null) {
                    list = new EnumMap<>(Material.class);
                } else {
                    list.clear();
                }
                list.put(ironIngot, ironCount);
                list.put(goldIngot, goldCount);
                list.put(diamond, diamondCount);
                list.put(emerald, emeraldCount);

                if (killfb_sendmess) {

                    if (!killfb_sendmess_chat.isEmpty()) {
                        k.sendMessage(t(
                                replaceString(
                                        killfb_sendmess_chat
                                        ,kName,dName,kHealth,dHealth,kuuid
                                )
                        ));
                    }
                    if (!killfb_sendmess_title.isEmpty()) {
                        String titleReal = t(
                                replaceString(
                                        killfb_sendmess_title,
                                        kName,dName,kHealth,dHealth,kuuid
                                )
                        );
                        if (!killfb_sendmess_subtitle.isEmpty()) {
                            String subtitleReal = t(
                                    replaceString(
                                            killfb_sendmess_subtitle,
                                            kName,dName,kHealth,dHealth,kuuid
                                    )
                            );

                            k.sendTitle(titleReal, subtitleReal);
                        }
                    } else if (!killfb_sendmess_subtitle.isEmpty()) {
                        String titleReal = " ";
                        String subtitleReal = t(
                                replaceString(
                                        killfb_sendmess_subtitle,
                                        kName,dName,kHealth,dHealth,kuuid
                                )
                        );

                        k.sendTitle(titleReal, subtitleReal);
                    }
                    if (!killfb_sendmess_actionbar.isEmpty()) {
                        sendActionBar(k, t(
                                replaceString(
                                        killfb_sendmess_actionbar,
                                        kName,dName,kHealth,dHealth,kuuid
                                )
                        ));
                    }
                }

                if (kill_res_chat.isEmpty()) {
                    return;
                }

                    if (ironCount > 0) {
                        k.sendMessage(kill_res_chat
                                .replace("{res}", meanIron)
                                .replace("{count}", ironCount + "")
                        );
                                            }
                    if (goldCount > 0) {
                        k.sendMessage(kill_res_chat
                                .replace("{res}", meanGold)
                                .replace("{count}", goldCount + "")
                        );
                                            }
                    if (diamondCount > 0) {
                        k.sendMessage(kill_res_chat
                                .replace("{res}",meanDiamond)
                                .replace("{count}", diamondCount + "")
                        );
                                            }

                    if (emeraldCount > 0) {
                        k.sendMessage(kill_res_chat
                                .replace("{res}", meanEmerlad)
                                .replace("{count}", emeraldCount + "")
                        );
                                            }
    }
}
