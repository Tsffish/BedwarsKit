package github.tsffish.bedwarskit.config.misc;

import github.tsffish.bedwarskit.BedwarsKit;
import github.tsffish.bedwarskit.util.misc.StringMgr;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import static github.tsffish.bedwarskit.util.misc.MessSender.l;
import static github.tsffish.bedwarskit.util.misc.PluginState.isDebug;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class ConfigVersionChecker {

    private static final BedwarsKit plugin = BedwarsKit.getInstance();

    public static void checkAndRenameConfig(File configFile, String expectedVersion) {
        if (!configFile.exists()) {
            l("配置文件不存在: " + configFile.getName() + ",正在保存新的...");
            plugin.saveResource(configFile.getName(), false);
            return;
        }

        YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
        String currentVersion = config.getString("config-version", "");

        if (!currentVersion.equals(expectedVersion)) {
            // 格式化当前时间
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_M_d_H_m_s");
            String timeStamp = dateFormat.format(new Date());

            // 构建新的文件名
            String newFileName = "_old_" + timeStamp;
            File renamedFile = new File(configFile.getParent(), configFile.getName().replaceFirst("\\.yml$", "") + newFileName + ".yml");

            // 尝试重命名文件
            boolean success = configFile.renameTo(renamedFile);

            if (success) {
                l(StringMgr.meanConfig_renamedTo
                        .replace("{orgName}", configFile.getName())
                        .replace("{newName}", renamedFile.getName())
                );
                l(StringMgr.meanConfig_versionNotMatch
                        .replace("{orgName}", configFile.getName())
                        .replace("{exVersion}", expectedVersion)
                );
                plugin.saveResource(configFile.getName(), false);
            } else {
                l(configFile.getName() + "  配置文件重命名失败.");
            }
        } else {
            if (isDebug()) {
                l(configFile.getName() + " 配置版本匹配，无需重命名.");
            }
        }
    }
}
