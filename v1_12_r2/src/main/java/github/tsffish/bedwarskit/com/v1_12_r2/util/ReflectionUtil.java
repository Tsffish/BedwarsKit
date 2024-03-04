package github.tsffish.bedwarskit.com.v1_12_r2.util;

import java.lang.reflect.Field;
/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class ReflectionUtil {
    public static Class<?> getNMSClass(String className) {
        String version = getVersion();

        try {
            String fullName = "net.minecraft.server." + version + "." + className;
            return Class.forName(fullName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getVersion() {
        String packageName = ReflectionUtil.class.getPackage().getName();
        return packageName.substring(packageName.lastIndexOf(".") + 1);
    }

    public static Object getFieldValue(Object instance, String fieldName) {
        try {
            Field field = instance.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(instance);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}

