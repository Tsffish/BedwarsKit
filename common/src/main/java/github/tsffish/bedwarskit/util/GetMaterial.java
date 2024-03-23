package github.tsffish.bedwarskit.util;

import org.bukkit.Material;

import java.lang.reflect.Method;

import static github.tsffish.bedwarskit.config.lang.LangConfigHandler.cantFoundSupport;
import static github.tsffish.bedwarskit.util.PluginState.serverVersion;
import static github.tsffish.bedwarskit.util.misc.MessSender.le;

public class GetMaterial {
    private static final String className = GetMaterial.class.getSimpleName();
    private static final String className1_8 = "github.tsffish.bedwarskit.com.v1_8_8." + className;
    private static final String className1_12 = "github.tsffish.bedwarskit.com.v1_12_2." + className;
    private static final String className1_16 = "github.tsffish.bedwarskit.com.v1_16_5." + className;

    public static Material STAINED_GLASS_PANE() {
        if (serverVersion().contains("1.8")) {
            try {
                Class<?> aClass = Class.forName(className1_8);

                Method method = aClass.getDeclaredMethod("STAINED_GLASS_PANE");

                return (Material) method.invoke(null);
            } catch (Exception e) {
                le(className, e);
            }
        } else if (serverVersion().contains("1.12")
                || serverVersion().contains("1.11")
                || serverVersion().contains("1.10")
                || serverVersion().contains("1.9")) {
            try {
                Class<?> aClass = Class.forName(className1_12);

                Method method = aClass.getMethod("STAINED_GLASS_PANE");

                return (Material) method.invoke(null);
            } catch (Exception e) {
                le(className, e);
            }
        } else if (serverVersion().contains("1.16")
                || serverVersion().contains("1.15")
                || serverVersion().contains("1.14")
                || serverVersion().contains("1.13")) {
            try {
                Class<?> aClass = Class.forName(className1_16);

                Method method = aClass.getMethod("STAINED_GLASS_PANE");

                return (Material) method.invoke(null);
            } catch (Exception e) {
                le(className, e);
                le(className, cantFoundSupport + serverVersion());
            }
        }else {
                      try {
                Class<?> aClass = Class.forName(className1_16);

                Method method = aClass.getMethod("STAINED_GLASS_PANE");

                return (Material) method.invoke(null);
            } catch (Exception e) {
                le(className, e);
                le(className, cantFoundSupport + serverVersion());
            }
        }
        return null;
    }

    public static Material BED_BLOCK() {
        if (serverVersion().contains("1.8")) {
            try {
                Class<?> aClass = Class.forName(className1_8);

                Method method = aClass.getDeclaredMethod("BED_BLOCK");

                return (Material) method.invoke(null);
            } catch (Exception e) {
                le(className, e);
            }
        } else if (serverVersion().contains("1.12")
                || serverVersion().contains("1.11")
                || serverVersion().contains("1.10")
                || serverVersion().contains("1.9")) {
            try {
                Class<?> aClass = Class.forName(className1_12);

                Method method = aClass.getMethod("BED_BLOCK");

                return (Material) method.invoke(null);
            } catch (Exception e) {
                le(className, e);
            }
        } else if (serverVersion().contains("1.16")
                || serverVersion().contains("1.15")
                || serverVersion().contains("1.14")
                || serverVersion().contains("1.13")) {
            try {
                Class<?> aClass = Class.forName(className1_16);

                Method method = aClass.getMethod("BED_BLOCK");

                return (Material) method.invoke(null);
            } catch (Exception e) {
                le(className, e);
                le(className, cantFoundSupport + serverVersion());
            }
        }else {
                      try {
                Class<?> aClass = Class.forName(className1_16);

                Method method = aClass.getMethod("BED_BLOCK");

                return (Material) method.invoke(null);
            } catch (Exception e) {
                le(className, e);
                le(className, cantFoundSupport + serverVersion());
            }
        }
        return null;
    }

    public static Material BED_ITEM() {
        if (serverVersion().contains("1.8")) {
            try {
                Class<?> aClass = Class.forName(className1_8);

                Method method = aClass.getDeclaredMethod("BED_ITEM");

                return (Material) method.invoke(null);
            } catch (Exception e) {
                le(className, e);
            }
        } else if (serverVersion().contains("1.12")
                || serverVersion().contains("1.11")
                || serverVersion().contains("1.10")
                || serverVersion().contains("1.9")) {
            try {
                Class<?> aClass = Class.forName(className1_12);

                Method method = aClass.getMethod("BED_ITEM");

                return (Material) method.invoke(null);
            } catch (Exception e) {
                le(className, e);
            }
        } else if (serverVersion().contains("1.16")
                || serverVersion().contains("1.15")
                || serverVersion().contains("1.14")
                || serverVersion().contains("1.13")) {
            try {
                Class<?> aClass = Class.forName(className1_16);

                Method method = aClass.getMethod("BED_ITEM");

                return (Material) method.invoke(null);
            } catch (Exception e) {
                le(className, e);
                le(className, cantFoundSupport + serverVersion());
            }
        }else {
                      try {
                Class<?> aClass = Class.forName(className1_16);

                Method method = aClass.getMethod("BED_ITEM");

                return (Material) method.invoke(null);
            } catch (Exception e) {
                le(className, e);
                le(className, cantFoundSupport + serverVersion());
            }
        }
        return null;
    }

    public static Material WOOD_SWORD() {
        if (serverVersion().contains("1.8")) {
            try {
                Class<?> aClass = Class.forName(className1_8);

                Method method = aClass.getDeclaredMethod("WOOD_SWORD");

                return (Material) method.invoke(null);
            } catch (Exception e) {
                le(className, e);
            }
        } else if (serverVersion().contains("1.12")
                || serverVersion().contains("1.11")
                || serverVersion().contains("1.10")
                || serverVersion().contains("1.9")) {
            try {
                Class<?> aClass = Class.forName(className1_12);

                Method method = aClass.getMethod("WOOD_SWORD");

                return (Material) method.invoke(null);
            } catch (Exception e) {
                le(className, e);
            }
        } else if (serverVersion().contains("1.16")
                || serverVersion().contains("1.15")
                || serverVersion().contains("1.14")
                || serverVersion().contains("1.13")) {
            try {
                Class<?> aClass = Class.forName(className1_16);

                Method method = aClass.getMethod("WOOD_SWORD");

                return (Material) method.invoke(null);
            } catch (Exception e) {
                le(className, e);
                le(className, cantFoundSupport + serverVersion());
            }
        }else {
                      try {
                Class<?> aClass = Class.forName(className1_16);

                Method method = aClass.getMethod("WOOD_SWORD");

                return (Material) method.invoke(null);
            } catch (Exception e) {
                le(className, e);
                le(className, cantFoundSupport + serverVersion());
            }
        }
        return null;
    }

    public static Material WOOD_PICKAXE() {
        if (serverVersion().contains("1.8")) {
            try {
                Class<?> aClass = Class.forName(className1_8);

                Method method = aClass.getDeclaredMethod("WOOD_PICKAXE");

                return (Material) method.invoke(null);
            } catch (Exception e) {
                le(className, e);
            }
        } else if (serverVersion().contains("1.12")
                || serverVersion().contains("1.11")
                || serverVersion().contains("1.10")
                || serverVersion().contains("1.9")) {
            try {
                Class<?> aClass = Class.forName(className1_12);

                Method method = aClass.getMethod("WOOD_PICKAXE");

                return (Material) method.invoke(null);
            } catch (Exception e) {
                le(className, e);
            }
        } else if (serverVersion().contains("1.16")
                || serverVersion().contains("1.15")
                || serverVersion().contains("1.14")
                || serverVersion().contains("1.13")) {
            try {
                Class<?> aClass = Class.forName(className1_16);

                Method method = aClass.getMethod("WOOD_PICKAXE");

                return (Material) method.invoke(null);
            } catch (Exception e) {
                le(className, e);
                le(className, cantFoundSupport + serverVersion());
            }
        }else {
                      try {
                Class<?> aClass = Class.forName(className1_16);

                Method method = aClass.getMethod("WOOD_PICKAXE");

                return (Material) method.invoke(null);
            } catch (Exception e) {
                le(className, e);
                le(className, cantFoundSupport + serverVersion());
            }
        }
        return null;
    }

    public static Material WOOD_AXE() {
        if (serverVersion().contains("1.8")) {
            try {
                Class<?> aClass = Class.forName(className1_8);

                Method method = aClass.getDeclaredMethod("WOOD_AXE");

                return (Material) method.invoke(null);
            } catch (Exception e) {
                le(className, e);
            }
        } else if (serverVersion().contains("1.12")
                || serverVersion().contains("1.11")
                || serverVersion().contains("1.10")
                || serverVersion().contains("1.9")) {
            try {
                Class<?> aClass = Class.forName(className1_12);

                Method method = aClass.getMethod("WOOD_AXE");

                return (Material) method.invoke(null);
            } catch (Exception e) {
                le(className, e);
            }
        } else if (serverVersion().contains("1.16")
                || serverVersion().contains("1.15")
                || serverVersion().contains("1.14")
                || serverVersion().contains("1.13")) {
            try {
                Class<?> aClass = Class.forName(className1_16);

                Method method = aClass.getMethod("WOOD_AXE");

                return (Material) method.invoke(null);
            } catch (Exception e) {
                le(className, e);
                le(className, cantFoundSupport + serverVersion());
            }
        }else {
                      try {
                Class<?> aClass = Class.forName(className1_16);

                Method method = aClass.getMethod("WOOD_AXE");

                return (Material) method.invoke(null);
            } catch (Exception e) {
                le(className, e);
                le(className, cantFoundSupport + serverVersion());
            }
        }
        return null;
    }

}
