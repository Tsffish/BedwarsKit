package github.tsffish.bedwarskit.util.misc;

import github.tsffish.bedwarskit.config.main.MainConfigHandler;
import org.bukkit.entity.Player;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import static github.tsffish.bedwarskit.listener.PluginDisable.pluginIsDisabling;
import static github.tsffish.bedwarskit.util.misc.ColorString.t;
import static github.tsffish.bedwarskit.util.misc.MessSender.le;
import static github.tsffish.bedwarskit.util.misc.PluginState.serverVersion;
import static github.tsffish.bedwarskit.util.misc.StringMgr.cantFoundSupport;

public class SendTab {
    private static final String className = "SendTab";
    public static void sendTabData(Player player, List<String> headerList, List<String> footerList) {
        ListIterator<String> headerIterator = headerList.listIterator();
        while (headerIterator.hasNext()) {
            headerIterator.set(t(headerIterator.next()));
        }

        ListIterator<String> footerIterator = footerList.listIterator();
        while (footerIterator.hasNext()) {
            footerIterator.set(t(footerIterator.next()));
        }

        if (serverVersion().contains("1.8")) {
            try {
                Method method = github.tsffish.bedwarskit.com.v1_8_r8.SendTab.class.getMethod("sendTab", Player.class, List.class, List.class);
                method.invoke(null, player, headerList, footerList);
            } catch (Exception e) {
                if (!pluginIsDisabling) {
                    le(className, e);
                }
            }
        } else if (serverVersion().contains("1.12")) {
            try {
                Method method = github.tsffish.bedwarskit.com.v1_12_r2.SendTab.class.getMethod("sendTab", Player.class, List.class, List.class);
                method.invoke(null, player, headerList, footerList);

            } catch (Exception e) {
                if (!pluginIsDisabling) {
                    le(className, e);
                }
            }
        }else if (serverVersion().contains("1.9")
                || serverVersion().contains("1.10")
                || serverVersion().contains("1.11")){
            try {
                Method method = github.tsffish.bedwarskit.com.v1_12_r2.SendTab.class.getMethod("sendTab", Player.class, List.class, List.class);
                method.invoke(null, player, headerList, footerList);

            } catch (Exception e) {
                if (!pluginIsDisabling) {
                    le(className, e);
                }
            }

        } else {
            if (!pluginIsDisabling) {
                le(className, cantFoundSupport + serverVersion());
            }
        }
    }

    public static void sendTabData(Player player, String header, String footer) {
        if (serverVersion().contains("1.8")) {
            try {
                Method method = github.tsffish.bedwarskit.com.v1_8_r8.SendTab.class.getMethod("sendTab", Player.class, String.class, String.class);
                method.invoke(null, player, t(header), t(footer));
            } catch (Exception e) {
                if (!pluginIsDisabling) {
                    le(className, e);
                }
            }
        } else if (serverVersion().contains("1.12")) {
            try {
                Method method = github.tsffish.bedwarskit.com.v1_12_r2.SendTab.class.getMethod("sendTab", Player.class, String.class, String.class);
                method.invoke(null, player, t(header), t(footer));

            } catch (Exception e) {
                if (!pluginIsDisabling) {
                    le(className, e);
                }
            }
        } else if (serverVersion().contains("1.9")
                || serverVersion().contains("1.10")
                || serverVersion().contains("1.11")){
            try {
                Method method = github.tsffish.bedwarskit.com.v1_12_r2.SendTab.class.getMethod("sendTab", Player.class, String.class, String.class);
                method.invoke(null, player, t(header), t(footer));

            } catch (Exception e) {
                if (!pluginIsDisabling) {
                    le(className, e);
                }
            }
        }else {
            if (!pluginIsDisabling) {
                le("SendTab", "can found support for this version: " + serverVersion());
            }
        }
    }

    public static void sendTab(Player player) {
        List<String> headerList = new ArrayList<>(20);
        List<String> footerList = new ArrayList<>(20);

        String head;
        String foot;
        if (MainConfigHandler.tab_is_multiLine) {
            headerList.addAll(MainConfigHandler.tab_headList);
            footerList.addAll(MainConfigHandler.tab_footList);
            sendTabData(player, headerList, footerList);
        } else {
            head = MainConfigHandler.tab_head;
            foot = MainConfigHandler.tab_foot;
            sendTabData(player, head, foot);
        }


    }

}
