package github.tsffish.bedwarskit.util.misc;

import github.tsffish.bedwarskit.Main;
import github.tsffish.bedwarskit.config.MainConfigLoad;
import org.bukkit.Bukkit;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

import static github.tsffish.bedwarskit.config.ErrorConfigHandler.er;
import static github.tsffish.bedwarskit.util.misc.InfoLogger.l;

public class ConfigVersionCheck {
    public static String path_configVersion = "config-version";
    public static void ccv(String CurrentVersion){
        Yaml yaml = new Yaml();
        File configFile = new File(Bukkit.getPluginManager().getPlugin(Main.pluginName).getDataFolder().getName() + path_configVersion);

        try {
            // 获取config-version的值
            String version = Bukkit.getPluginManager().getPlugin(Main.pluginName).getConfig().getString("config-version");

            // 检查版本是否等于1.9.5
            if (!version.equals(CurrentVersion)) {
                // 生成新的文件名
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
                String newFileName = "config_old_" + dateFormat.format(new Date()) + ".yml";


                // 另存为新的文件
                File newFile = new File(newFileName);
                FileWriter writer = new FileWriter(newFile);
                yaml.dump(configFile, writer);

                l("old config.yml now save to :" + newFileName);

                try {
                    Files.deleteIfExists(configFile.toPath());
                } catch (IOException e) {
                    er("ConfigVersionCheck", "delete org config file", e);
                    e.printStackTrace();
                }

            }else {

            }
        } catch (IOException e) {
            er("ConfigVersionCheck", path_configVersion, e);
            e.printStackTrace();
        }
    }


}

