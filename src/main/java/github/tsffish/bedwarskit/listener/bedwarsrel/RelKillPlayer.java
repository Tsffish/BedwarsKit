package github.tsffish.bedwarskit.listener.bedwarsrel;

import github.tsffish.bedwarskit.config.MainConfigHandler;
import io.github.bedwarsrel.events.BedwarsPlayerKilledEvent;
import io.github.bedwarsrel.game.Game;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.PlayerInventory;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

import static github.tsffish.bedwarskit.util.RelCurrentStat.ups;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;
import static io.github.bedwarsrel.com.v1_8_r3.ActionBar.sendActionBar;

public class RelKillPlayer implements Listener {
    static Map<Material, Integer> list;

    @EventHandler
    public void on(BedwarsPlayerKilledEvent e) {

        if (e.getKiller() != null && e.getPlayer() != null){

        Game game = e.getGame();

        if (MainConfigHandler.kill_res && e.getKiller() != null && e.getKiller() != e.getPlayer()) {
            Player k = e.getKiller();
            String kName = k.getName();
            String kHealth = k.getHealth() + "";
            PlayerInventory kpi = k.getInventory();
            ups(k, "k", 1);

            Player d = e.getPlayer();
            String dName = d.getName();
            String dHealth = d.getHealth() + "";
            PlayerInventory dpi = d.getInventory();
            ups(d, "d", 1);


            if (game.getPlayerTeam(d).getHeadTarget() == null || game.getPlayerTeam(d).getHeadTarget().getType() != Material.BED) {
                ups(k, "f", 1);
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

                if (list == null){
                    list = new EnumMap<>(Material.class);
                }else {
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
                        );
                    }
                    if (!Objects.equals(MainConfigHandler.killfb_sendmess_title, "")) {
                        String titleReal = t(MainConfigHandler.killfb_sendmess_title
                                .replace("{kp}", kName)
                                .replace("{dp}", dName)
                                .replace("{k_health}", kHealth)
                                .replace("{d_health}", dHealth)
                        );
                        if (!Objects.equals(MainConfigHandler.killfb_sendmess_subtitle, "")) {
                            String subtitleReal = t(MainConfigHandler.killfb_sendmess_subtitle
                                    .replace("{kp}", kName)
                                    .replace("{dp}", dName)
                                    .replace("{k_health}", kHealth)
                                    .replace("{d_health}", dHealth)
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
                        );

                        k.sendTitle(titleReal, subtitleReal);
                    }
                    if (!Objects.equals(MainConfigHandler.killfb_sendmess_actionbar, "")) {
                        sendActionBar(k, t(MainConfigHandler.killfb_sendmess_actionbar
                                .replace("{kp}", kName)
                                .replace("{dp}", dName)
                                .replace("{k_health}", kHealth)
                                .replace("{d_health}", dHealth)
                        ));
                    }


                }


                if (!Objects.equals(MainConfigHandler.kill_res_chat, "")){
                        if (ironCount > 0){
                            k.sendMessage(MainConfigHandler.kill_res_chat
                                    .replace("{res}",MainConfigHandler.meanIron)
                                    .replace("{count}",ironCount + "")
                            );
                        }

                        if (goldCount > 0){
                            k.sendMessage(MainConfigHandler.kill_res_chat
                                    .replace("{res}",MainConfigHandler.meanGold)
                                    .replace("{count}",goldCount + "")
                            );


                        }
                        if (diamondCount > 0){

                            k.sendMessage(MainConfigHandler.kill_res_chat
                                    .replace("{res}",MainConfigHandler.meanDiamond)
                                    .replace("{count}",diamondCount + "")
                            );



                        }

                        if (emeraldCount > 0){

                            k.sendMessage(MainConfigHandler.kill_res_chat
                                    .replace("{res}",MainConfigHandler.meanEmerlad)
                                    .replace("{count}",emeraldCount + "")
                            );



                        }
                    }
                }


            }
        }
            }
        }
