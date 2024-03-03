package github.tsffish.bedwarskit;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import github.tsffish.bedwarskit.command.CommandInfo;
import github.tsffish.bedwarskit.config.main.MainConfigLoad;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static github.tsffish.bedwarskit.util.misc.ChatColor.green;
import static github.tsffish.bedwarskit.util.misc.MessSender.l;
import static github.tsffish.bedwarskit.util.misc.SendStartUpInfo.sendPluginStartUpInfo;
import static github.tsffish.bedwarskit.util.misc.StringMgr.pluginName;
import static github.tsffish.bedwarskit.util.misc.bstats.StartMetrics.startMetrics;

public class BedwarsKit extends JavaPlugin {
    private static final String pluginVersion = "1.9.56";
    public static String pluginVersion(){
        return pluginVersion;
    }
    private static final String pluginNameConsole = "[" + pluginName + "]";
    public static String pluginNameConsole(){
        return pluginNameConsole;
    }
    private static final String author = "Tsffish";
    public static String author(){
        return author;
    }
    private static boolean isDebug = false;
    public static boolean isDebug(){
        return isDebug;
    }
    public static void changeIsDebug(){
        isDebug = !isDebug;
    }
    private static boolean isLastestVersion;
    public static boolean getIsLastestVersion(){
        return isLastestVersion;
    }
    public static void setIsLastestVersion(boolean setTo){
        isLastestVersion = setTo;
    }
    private static final int spigotId = 105616;
    public static int spigotId(){
        return spigotId;
    }
    public static String language;
    public static String language() {
        return language;
    }
    private static BedwarsKit instance;
    public static BedwarsKit getInstance() {
        return instance;
    }
    public static boolean isBungeeMode;
    public static boolean isBungeeMode() {
        return isBungeeMode;
    }
    private static String serverVersion;
    public static String serverVersion(){
        return serverVersion;
    }
    private static List<Player> hiddenPlayers = new ArrayList<>();
    public void onEnable() {
        serverVersion = getServer().getVersion();
        Locale currentLocale = Locale.getDefault();
        language = currentLocale.getLanguage();

        if (isDebug){l("Server Version: " + serverVersion);l("language: " + language);}

        instance = this;

                sendPluginStartUpInfo(green);

                MainConfigLoad.loadMainConfig(null, true);

                getCommand("bwk").setExecutor(new CommandInfo());
                getCommand("bwk reload").setExecutor(new CommandInfo());
                l("Command registered");

                handlerHub();

                if (!isDebug) {startMetrics();}

                ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();

                protocolManager.addPacketListener(new PacketAdapter(this, PacketType.Play.Server.ENTITY_METADATA) {
                    @Override
                    public void onPacketSending(PacketEvent event) {
                        if (event.getPacketType() == PacketType.Play.Server.ENTITY_METADATA) {
                            int entityId = event.getPacket().getIntegers().read(0);
                            Player receiver = event.getPlayer();
                            for (Player hiddenPlayer : hiddenPlayers) {
                                if (hiddenPlayer.getEntityId() == entityId && !receiver.equals(hiddenPlayer)) {
                                    event.setCancelled(true);
                                }
                            }
                        }
                    }
                });
    }
     void handlerHub(){
         FileConfiguration config = Bukkit.spigot().getConfig();
         isBungeeMode = config.getBoolean("settings.bungeecord", false);
         if (isBungeeMode) {
             if (isDebug){
             System.out.println("using BungeeCord");
             }
         } else {
             if (isDebug){
             System.out.println("not using BungeeCord");
             }
         }
    }
    // 一个方法来添加玩家到隐藏列表
    public static void hidePlayer(Player player) {
        if (!hiddenPlayers.contains(player)) {
            hiddenPlayers.add(player);
        }
    }

    // 一个方法来从隐藏列表中移除玩家
    public static void showPlayer(Player player) {
        hiddenPlayers.remove(player);
    }
    public static boolean getPlayerIsHide(Player player){
        return hiddenPlayers.contains(player);
    }
        public void onDisable ()
        {
            l("Disabled.");
        }
}