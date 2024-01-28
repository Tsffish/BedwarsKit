package github.tsffish.bedwarskit;

import github.tsffish.bedwarskit.command.CommandInfo;
import github.tsffish.bedwarskit.config.MainConfigHandler;
import github.tsffish.bedwarskit.listener.bedwarsrel.*;
import github.tsffish.bedwarskit.util.RelShopLevelUp;
import github.tsffish.bedwarskit.util.RelTeamEnchant;
import io.github.bedwarsrel.BedwarsRel;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class Main extends JavaPlugin implements Listener {
    Logger l = this.getLogger();
    public void onEnable() {
        this.l.info("Enabling");
        PluginManager pm = Bukkit.getPluginManager();

        getCommand("bwk").setExecutor(new CommandInfo());

        if (pm.getPlugin("BedwarsRel") != null) {
            MainConfigHandler.loadMainConfig(null, true);

            if (BedwarsRel.getInstance() == null){
            RelTeamEnchant.loadMapTeam(100L);
            }else{
                RelTeamEnchant.loadMapTeam(1L);
            }

            RelShopLevelUp.loadLevelUpInv();
            pm.registerEvents(new RelBreakBed(), this);
            pm.registerEvents(new RelBreakCorrect(), this);

            pm.registerEvents(new RelClickInventory(), this);
            pm.registerEvents(new RelEnchant(), this);

            pm.registerEvents(new RelFoodLock(), this);
            pm.registerEvents(new RelGameOver(), this);

            pm.registerEvents(new RelGameStarted(), this);

            pm.registerEvents(new RelKillRes(),this);
            //pm.registerEvents(new RelPlaceCorrect(), this);
            pm.registerEvents(new RelNoItemBreak(), this);

            pm.registerEvents(new RelPlayerDeath(), this);
            pm.registerEvents(new RelPlayerDrop(), this);

            pm.registerEvents(new RelPlayerLeave(), this);
            pm.registerEvents(new RelPlayerMove(), this);

            pm.registerEvents(new RelPlayerRespawn(), this);
            pm.registerEvents(new RelPlayerTeleport(), this);

            this.l.info("Found BedwarsRel, relevant support enabled");
        } else {
            this.l.info("BedwarsRel not found, unable to enable related support");
        }

    }
        public void onDisable () {
            l.info("uninstalling");
        }

}