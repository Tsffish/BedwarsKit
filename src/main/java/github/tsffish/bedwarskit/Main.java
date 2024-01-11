//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package github.tsffish.bedwarskit;

import github.tsffish.bedwarskit.config.MainConfigHandler;
import github.tsffish.bedwarskit.listener.bedwars1058.Yi058PlayerRespawn;
import github.tsffish.bedwarskit.listener.bedwarsrel.RelBreakBed;
import github.tsffish.bedwarskit.listener.bedwarsrel.RelBreakCorrect;
import github.tsffish.bedwarskit.listener.bedwarsrel.RelClickInventory;
import github.tsffish.bedwarskit.listener.bedwarsrel.RelFoodLock;
import github.tsffish.bedwarskit.listener.bedwarsrel.RelGameStarted;
import github.tsffish.bedwarskit.listener.bedwarsrel.RelPlayerClear;
import github.tsffish.bedwarskit.listener.bedwarsrel.RelPlayerDeath;
import github.tsffish.bedwarskit.listener.bedwarsrel.RelPlayerDrop;
import github.tsffish.bedwarskit.listener.bedwarsrel.RelPlayerMove;
import github.tsffish.bedwarskit.listener.bedwarsrel.RelPlayerRespawn;
import github.tsffish.bedwarskit.listener.bedwarsrel.RelPlayerTeleport;
import github.tsffish.bedwarskit.util.RelArmorColor;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
    Logger l = this.getLogger();

    public Main() {
    }

    public void onLoad() {
        MainConfigHandler.loadMainConfig();
        RelArmorColor.loadRelArmorColor();
    }

    public void onEnable() {
        this.l.info("正在启用");
        PluginManager pm = Bukkit.getPluginManager();
        if (pm.getPlugin("BedWars1058") != null) {
            pm.registerEvents(new Yi058PlayerRespawn(), this);
        }

        if (pm.getPlugin("BedwarsRel") != null) {
            pm.registerEvents(new RelPlayerClear(), this);
            pm.registerEvents(new RelPlayerMove(), this);
            pm.registerEvents(new RelBreakBed(), this);
            pm.registerEvents(new RelBreakCorrect(), this);
            pm.registerEvents(new RelClickInventory(), this);
            pm.registerEvents(new RelFoodLock(), this);
            pm.registerEvents(new RelGameStarted(), this);
            pm.registerEvents(new RelPlayerDeath(), this);
            pm.registerEvents(new RelPlayerDrop(), this);
            pm.registerEvents(new RelPlayerRespawn(), this);
            pm.registerEvents(new RelPlayerTeleport(), this);
        }
    }

    public void onDisable() {
        this.l.info("正在卸载");
    }
}
