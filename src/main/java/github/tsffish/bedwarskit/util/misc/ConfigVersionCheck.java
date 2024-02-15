package github.tsffish.bedwarskit.util.misc;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

import static github.tsffish.bedwarskit.Main.pluginName;
import static github.tsffish.bedwarskit.config.misc.ErrorConfigHandler.er;
import static github.tsffish.bedwarskit.util.misc.MessSender.l;
import static github.tsffish.bedwarskit.util.misc.MessSender.le;

public class ConfigVersionCheck {
    private static Plugin plugin = github.tsffish.bedwarskit.Main.getPlugin(github.tsffish.bedwarskit.Main.class);
    public static String path_configVersion = "config-version";
    public static void ccv(String CurrentVersion){
        Yaml yaml = new Yaml();
        File configFile = new File(Bukkit.getPluginManager().getPlugin(pluginName()).getDataFolder().getName() + path_configVersion);

        try {
            
            String version = Bukkit.getPluginManager().getPlugin(pluginName()).getConfig().getString("config-version");

            
            if (!version.equals(CurrentVersion)) {
                
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
                String newFileName = "config_old_" + dateFormat.format(new Date()) + ".yml";


                
                File newFile = new File(newFileName);
                FileWriter writer = new FileWriter(newFile);
                yaml.dump(plugin.getDataFolder() ,writer);

                l("old config.yml now save to :" + newFileName);

                try {
                    Files.deleteIfExists(configFile.toPath());
                } catch (IOException e) {
                    er("ConfigVersionCheck", "delete org config file", e);
                    le("ConfigVersionCheck",e);
                }

            }
        } catch (IOException e) {
            er("ConfigVersionCheck", path_configVersion, e);
            le("ConfigVersionCheck",e);
        }
    }
}
