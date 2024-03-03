package github.tsffish.bedwarskit.util.task;

import github.tsffish.bedwarskit.BedwarsKit;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

import static github.tsffish.bedwarskit.BedwarsKit.isDebug;
import static github.tsffish.bedwarskit.util.RelIsCheckingPlayer.leaveCheckList;
import static github.tsffish.bedwarskit.util.misc.MessSender.l;

public class LeaveCheckTask {
    private static final BedwarsKit plugin = BedwarsKit.getInstance();
    private static List<String> leavingList = new ArrayList<>();
    public static void leaveList(String worldName) {
        if (!leavingList.contains(worldName)) {
            leavingList.add(worldName);
            if (isDebug()) {
                l(worldName + "try to leave check list");
            }
            new BukkitRunnable() {
                @Override
                public void run() {
                    World world = Bukkit.getWorld(worldName);
                    if (world.getPlayers().isEmpty()) {
                        leaveCheckList(worldName);
                        leavingList.remove(worldName);
                        if (isDebug()) {
                            l(worldName + "leaved check list");
                        }
                    }
                }
            }.runTaskLater(plugin, 40L);
        }
    }
}
