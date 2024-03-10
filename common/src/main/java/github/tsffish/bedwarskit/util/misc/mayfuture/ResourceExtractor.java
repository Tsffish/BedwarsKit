package github.tsffish.bedwarskit.util.misc.mayfuture;

import github.tsffish.bedwarskit.BedwarsKit;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;

import static github.tsffish.bedwarskit.config.misc.ConfigVersionChecker.checkAndRenameConfig;
import static github.tsffish.bedwarskit.util.misc.MessSender.l;
import static github.tsffish.bedwarskit.util.misc.StringMgr.meanJavaAccessDenied;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class ResourceExtractor {

    private static final BedwarsKit plugin = BedwarsKit.getInstance();

    public static void ensureResource(String fileName, String language, String expectedVersion) {
        File dataFolder = plugin.getDataFolder();
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }

        File targetFile = new File(dataFolder, fileName);

        // 如果目标文件已存在，则不执行任何操作
        if (targetFile.exists()) {
            checkAndRenameConfig(targetFile, expectedVersion);
            return;
        }

        // 如果语言是"zh"，则创建"zh"文件夹并复制文件
        if ("zh".equals(language)) {
            File zhFolder = new File(dataFolder, "zh");
            if (!zhFolder.exists()) {
                zhFolder.mkdirs();
            }

            File sourceFile = new File(zhFolder, fileName);

            try {
                Files.copy(sourceFile.toPath(), targetFile.toPath());
            } catch (IOException e) {
                // 处理异常，例如打印日志或通知用户
                e.printStackTrace();
            }
        } else {
            // 如果语言不是"zh"，直接复制文件到 DataFolder
            copyResource(fileName, language);
        }
    }

    public static void copyResource(String fileName, String language) {
        File folder = plugin.getDataFolder();
        if (!folder.exists()) {
            folder.mkdirs();
        }

        String resourcePath = language + File.separator + fileName;
        File targetFile = new File(folder, language + File.separator + fileName);

        try (InputStream in = plugin.getResource(resourcePath)) {
            if (in == null) {
                throw new IOException("Resource not found: " + resourcePath);
            }
            if (!targetFile.getParentFile().exists()) {
                targetFile.getParentFile().mkdirs();
            }
            Files.copy(in, targetFile.toPath());
        } catch (AccessDeniedException ex) {
            l(meanJavaAccessDenied);
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}