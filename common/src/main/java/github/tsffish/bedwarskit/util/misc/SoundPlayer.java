package github.tsffish.bedwarskit.util.misc;

import org.bukkit.entity.Player;

import static github.tsffish.bedwarskit.Main.pluginIsDisabling;
import static github.tsffish.bedwarskit.Main.serverVersion;
import static github.tsffish.bedwarskit.util.misc.StringMgr.cantFoundSupport;
import static github.tsffish.bedwarskit.util.misc.MessSender.le;

public class SoundPlayer {
    private static final String className = "SoundPlayer";
    public static void CLICK(Player player, int pitch){
        if (serverVersion().contains("1.8")){
            try {
                player.playSound(player.getLocation(),
                        github.tsffish.bedwarskit.com.v1_8_r8.SoundPlayer.CLICK
                        ,1,pitch);
            }catch (Exception e){
                if (!pluginIsDisabling){
                    le(className, e);
                }
            }
        } else if (serverVersion().contains("1.12")){
            try {
                player.playSound(player.getLocation(),
                        github.tsffish.bedwarskit.com.v1_12_r2.SoundPlayer.CLICK
                        ,1,pitch);
            }catch (Exception e){
                if (!pluginIsDisabling){
                    le(className, e);
                }
            }
        }else if (serverVersion().contains("1.9")
                || serverVersion().contains("1.10")
                || serverVersion().contains("1.11")){
            try {
                player.playSound(player.getLocation(),
                        github.tsffish.bedwarskit.com.v1_12_r2.SoundPlayer.CLICK
                        ,1,pitch);
            }catch (Exception e){
                if (!pluginIsDisabling){
                    le(className, e);
                }
            }
        }else {
            if (!pluginIsDisabling){
                le(className,cantFoundSupport + serverVersion());
            }
        }

    }

    public static void LEVEL_UP(Player player,int pitch){
        if (serverVersion().contains("1.8")){
            try {
                player.playSound(player.getLocation(),
                        github.tsffish.bedwarskit.com.v1_8_r8.SoundPlayer.LEVEL_UP
                        ,1,pitch);
            }catch (Exception e){
                if (!pluginIsDisabling){
                    le(className, e);
                }
            }
        } else if (serverVersion().contains("1.12")){
            try {
                player.playSound(player.getLocation(),
                        github.tsffish.bedwarskit.com.v1_12_r2.SoundPlayer.LEVEL_UP
                        ,1,pitch);
            }catch (Exception e){
                if (!pluginIsDisabling){
                    le(className, e);
                }
            }
        }else if (serverVersion().contains("1.9")
                || serverVersion().contains("1.10")
                || serverVersion().contains("1.11")){
            try {
                player.playSound(player.getLocation(),
                        github.tsffish.bedwarskit.com.v1_12_r2.SoundPlayer.LEVEL_UP
                        ,1,pitch);
            }catch (Exception e){
                if (!pluginIsDisabling){
                    le(className, e);
                }
            }
        }else {
            if (!pluginIsDisabling){
                le(className,cantFoundSupport + serverVersion());
            }
        }

    }

    public static void ITEM_PICKUP(Player player, int pitch){
        if (serverVersion().contains("1.8")){
            try {
                player.playSound(player.getLocation(),
                        github.tsffish.bedwarskit.com.v1_8_r8.SoundPlayer.ITEM_PICKUP
                        ,1,pitch);
            }catch (Exception e){
                if (!pluginIsDisabling){
                    le(className, e);
                }
            }
        } else if (serverVersion().contains("1.12")){
            try {
                player.playSound(player.getLocation(),
                        github.tsffish.bedwarskit.com.v1_12_r2.SoundPlayer.ITEM_PICKUP
                        ,1,pitch);
            }catch (Exception e){
                if (!pluginIsDisabling){
                    le(className, e);
                }
            }
        }else if (serverVersion().contains("1.9")
                || serverVersion().contains("1.10")
                || serverVersion().contains("1.11")){
            try {
                player.playSound(player.getLocation(),
                        github.tsffish.bedwarskit.com.v1_12_r2.SoundPlayer.ITEM_PICKUP
                        ,1,pitch);
            }catch (Exception e){
                if (!pluginIsDisabling){
                    le(className, e);
                }
            }
        }else {
            if (!pluginIsDisabling){
                le(className,cantFoundSupport + serverVersion());
            }
        }

    }

    public static void ITEM_BREAK(Player player, int pitch){
        if (serverVersion().contains("1.8")){
            try {
                player.playSound(player.getLocation(),
                        github.tsffish.bedwarskit.com.v1_8_r8.SoundPlayer.ITEM_BREAK
                        ,1,pitch);
            }catch (Exception e){
                if (!pluginIsDisabling){
                    le(className, e);
                }
            }
        } else if (serverVersion().contains("1.12")){
            try {
                player.playSound(player.getLocation(),
                        github.tsffish.bedwarskit.com.v1_12_r2.SoundPlayer.ITEM_BREAK
                        ,1,pitch);
            }catch (Exception e){
                if (!pluginIsDisabling){
                    le(className, e);
                }
            }
        }else if (serverVersion().contains("1.9")
                || serverVersion().contains("1.10")
                || serverVersion().contains("1.11")){
            try {
                player.playSound(player.getLocation(),
                        github.tsffish.bedwarskit.com.v1_12_r2.SoundPlayer.ITEM_BREAK
                        ,1,pitch);
            }catch (Exception e){
                if (!pluginIsDisabling){
                    le(className, e);
                }
            }
        }else {
            if (!pluginIsDisabling){
                le(className,cantFoundSupport + serverVersion());
            }
        }

    }
    public static void CHICKEN_EGG_POP(Player player, int pitch){
        if (serverVersion().contains("1.8")){
            try {
                player.playSound(player.getLocation(),
                        github.tsffish.bedwarskit.com.v1_8_r8.SoundPlayer.CHICKEN_EGG_POP
                        ,1,pitch);
            }catch (Exception e){
                if (!pluginIsDisabling){
                    le(className, e);
                }
            }
        } else if (serverVersion().contains("1.12")){
            try {
                player.playSound(player.getLocation(),
                        github.tsffish.bedwarskit.com.v1_12_r2.SoundPlayer.CHICKEN_EGG_POP
                        ,1,pitch);
            }catch (Exception e){
                if (!pluginIsDisabling){
                    le(className, e);
                }
            }
        }else if (serverVersion().contains("1.9")
                || serverVersion().contains("1.10")
                || serverVersion().contains("1.11")){
            try {
                player.playSound(player.getLocation(),
                        github.tsffish.bedwarskit.com.v1_12_r2.SoundPlayer.CHICKEN_EGG_POP
                        ,1,pitch);
            }catch (Exception e){
                if (!pluginIsDisabling){
                    le(className, e);
                }
            }
        }else {

            if (!pluginIsDisabling){
                le(className,cantFoundSupport + serverVersion());
            }
        }

    }








    public static void ENDERMAN_TELEPORT(Player player, int pitch){
        if (serverVersion().contains("1.8")){
            try {
                player.playSound(player.getLocation(),
                        github.tsffish.bedwarskit.com.v1_8_r8.SoundPlayer.ENDERMAN_TELEPORT
                        ,1,1);
            }catch (Exception e){
                if (!pluginIsDisabling){
                    le(className, e);
                }
            }
        } else if (serverVersion().contains("1.12")){
            try {
                player.playSound(player.getLocation(),
                        github.tsffish.bedwarskit.com.v1_12_r2.SoundPlayer.ENDERMAN_TELEPORT
                        ,1,pitch);
            }catch (Exception e){
                if (!pluginIsDisabling){
                    le(className, e);
                }
            }
        }else if (serverVersion().contains("1.9")
                || serverVersion().contains("1.10")
                || serverVersion().contains("1.11")){
            try {
                player.playSound(player.getLocation(),
                        github.tsffish.bedwarskit.com.v1_12_r2.SoundPlayer.ENDERMAN_TELEPORT
                        ,1,pitch);
            }catch (Exception e){
                if (!pluginIsDisabling){
                    le(className, e);
                }
            }
        }else {
            if (!pluginIsDisabling){
                le(className,cantFoundSupport + serverVersion());
            }
        }

    }







    public static void ORB_PICKUP(Player player,int pitch){
        if (serverVersion().contains("1.8")){
            try {
                player.playSound(player.getLocation(),
                        github.tsffish.bedwarskit.com.v1_8_r8.SoundPlayer.ORB_PICKUP
                        ,1,pitch);
            }catch (Exception e){
                if (!pluginIsDisabling){
                    le(className, e);
                }
            }
        } else if (serverVersion().contains("1.12")){
            try {
                player.playSound(player.getLocation(),
                        github.tsffish.bedwarskit.com.v1_12_r2.SoundPlayer.ORB_PICKUP
                        ,1,1);
            }catch (Exception e){
                if (!pluginIsDisabling){
                    le(className, e);
                }
            }
        }else {
            if (!pluginIsDisabling){
                le(className,cantFoundSupport + serverVersion());
            }
        }

    }







}
