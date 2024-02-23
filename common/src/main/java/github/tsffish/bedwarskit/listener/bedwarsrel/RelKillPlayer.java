package github.tsffish.bedwarskit.listener.bedwarsrel;

import github.tsffish.bedwarskit.config.main.MainConfigHandler;
import github.tsffish.bedwarskit.util.RelCurrentStat;
import github.tsffish.bedwarskit.util.player.SoundPlayer;
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
import java.util.Objects;

import static github.tsffish.bedwarskit.config.main.MainConfigHandler.*;
import static github.tsffish.bedwarskit.util.RelCurrentStat.updatePlayerStat;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;
import static io.github.bedwarsrel.com.v1_8_r3.ActionBar.sendActionBar;

public class RelKillPlayer implements Listener {
    static Map<Material, Integer> list;
    @EventHandler
    public void on(final BedwarsPlayerKilledEvent e) {

        if (e.getKiller() != null && e.getPlayer() != null){
        if (e.getKiller().isOnline()){
        Game game = e.getGame();
        if (game == null) return;

        if (MainConfigHandler.kill_res && e.getKiller() != null && e.getKiller() != e.getPlayer()) {
            Player k = e.getKiller();
            String kName = k.getName();
            String kHealth = k.getHealth() + "";
            PlayerInventory kpi = k.getInventory();
            updatePlayerStat(kName, "k", 1);

            Player d = e.getPlayer();
            String dName = d.getName();
            String dHealth = d.getHealth() + "";
            PlayerInventory dpi = d.getInventory();
            updatePlayerStat(dName, "d", 1);


            if (game.getPlayerTeam(d).getHeadTarget() == null || game.getPlayerTeam(d).getHeadTarget().getType() != Material.BED) {
                updatePlayerStat(kName, "f", 1);
                SoundPlayer.LEVEL_UP(k,1);
            }


            if (killfb_oneHealthKill){

                updatePlayerStat(kName,"ohk", 1);

                if (RelCurrentStat.getPlayerOHKill(kName) > 1){
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

            if (kpi != null && dpi != null) {
                int size = dpi.getSize();
                for (int i = 0; i < size; i++) {
                    if (dpi.getItem(i) != null && dpi.getItem(i).getType() == Material.IRON_INGOT) {
                        kpi.addItem(dpi.getItem(i));
                        dpi.setItem(i, null);
                        ironCount = ironCount + dpi.getItem(i).getAmount();
                    }
                }
                for (int i = 0; i < size; i++) {
                    if (dpi.getItem(i) != null && dpi.getItem(i).getType() == Material.GOLD_INGOT) {
                        kpi.addItem(dpi.getItem(i));
                        dpi.setItem(i, null);
                        goldCount = goldCount + dpi.getItem(i).getAmount();
                    }
                }

                for (int i = 0; i < size; i++) {
                    if (dpi.getItem(i) != null && dpi.getItem(i).getType() == Material.DIAMOND) {
                        kpi.addItem(dpi.getItem(i));
                        dpi.setItem(i, null);
                        diamondCount = diamondCount + dpi.getItem(i).getAmount();
                    }
                }

                for (int i = 0; i < size; i++) {
                    if (dpi.getItem(i) != null && dpi.getItem(i).getType() == Material.EMERALD) {
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
                list.put(Material.IRON_INGOT, ironCount);
                list.put(Material.GOLD_INGOT, goldCount);
                list.put(Material.DIAMOND, diamondCount);
                list.put(Material.EMERALD, emeraldCount);

                if (MainConfigHandler.killfb_sendmess) {

                    if (!Objects.equals(MainConfigHandler.killfb_sendmess_chat, "")) {
                        k.sendMessage(MainConfigHandler.killfb_sendmess_chat
                                .replace("{kp}", kName)
                                .replace("{dp}", dName)
                                .replace("{k_health}", kHealth)
                                .replace("{d_health}", dHealth)
                                .replace("{ohk}",RelCurrentStat.getPlayerOHKill(kName) + "")
                        );
                    }
                    if (!Objects.equals(MainConfigHandler.killfb_sendmess_title, "")) {
                        String titleReal = t(MainConfigHandler.killfb_sendmess_title
                                .replace("{kp}", kName)
                                .replace("{dp}", dName)
                                .replace("{k_health}", kHealth)
                                .replace("{d_health}", dHealth)
                                .replace("{ohk}",RelCurrentStat.getPlayerOHKill(kName) + "")
                        );
                        if (!Objects.equals(MainConfigHandler.killfb_sendmess_subtitle, "")) {
                            String subtitleReal = t(MainConfigHandler.killfb_sendmess_subtitle
                                    .replace("{kp}", kName)
                                    .replace("{dp}", dName)
                                    .replace("{k_health}", kHealth)
                                    .replace("{d_health}", dHealth)
                                    .replace("{ohk}",RelCurrentStat.getPlayerOHKill(kName) + "")
                            );

                            k.sendTitle(titleReal, subtitleReal);
                        }
                    } else if (!Objects.equals(MainConfigHandler.killfb_sendmess_subtitle, "")) {
                        String titleReal = " ";
                        String subtitleReal = t(MainConfigHandler.killfb_sendmess_subtitle
                                .replace("{kp}", kName)
                                .replace("{dp}", dName)
                                .replace("{k_health}", kHealth)
                                .replace("{d_health}", dHealth)
                                .replace("{ohk}",RelCurrentStat.getPlayerOHKill(kName) + "")
                        );

                        k.sendTitle(titleReal, subtitleReal);
                    }
                    if (!Objects.equals(MainConfigHandler.killfb_sendmess_actionbar, "")) {
                        sendActionBar(k, t(MainConfigHandler.killfb_sendmess_actionbar
                                .replace("{kp}", kName)
                                .replace("{dp}", dName)
                                .replace("{k_health}", kHealth)
                                .replace("{d_health}", dHealth)
                                .replace("{ohk}",RelCurrentStat.getPlayerOHKill(kName) + "")
                        ));
                    }


                }


                if (!Objects.equals(MainConfigHandler.kill_res_chat, "")) {
                    if (ironCount > 0) {
                        k.sendMessage(MainConfigHandler.kill_res_chat
                                .replace("{res}", MainConfigHandler.meanIron)
                                .replace("{count}", ironCount + "")
                        );
                    }

                    if (goldCount > 0) {
                        k.sendMessage(MainConfigHandler.kill_res_chat
                                .replace("{res}", MainConfigHandler.meanGold)
                                .replace("{count}", goldCount + "")
                        );


                    }
                    if (diamondCount > 0) {

                        k.sendMessage(MainConfigHandler.kill_res_chat
                                .replace("{res}", MainConfigHandler.meanDiamond)
                                .replace("{count}", diamondCount + "")
                        );


                    }

                    if (emeraldCount > 0) {

                        k.sendMessage(MainConfigHandler.kill_res_chat
                                .replace("{res}", MainConfigHandler.meanEmerlad)
                                .replace("{count}", emeraldCount + "")
                        );


                    }
                }
            }

        }
            }
        }
            }
        }
