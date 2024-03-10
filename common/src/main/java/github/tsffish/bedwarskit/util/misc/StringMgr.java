package github.tsffish.bedwarskit.util.misc;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class StringMgr {
    public static String vauleIsWrong = "(?)";
    public static String vauleIsNull = "(?)";
    public static String cantFoundSupport = "(?)";
    public static String finishLoadConfig = "(?)";
    public static String meanAuthor = "(?)";
    public static String meanConfig_renamedTo = "(?)";
    public static String meanConfig_versionNotMatch = "(?)";
    public static String meanCommandReged = "(?)";
    public static String meanConfigLoad = "(?)";
    public static String meanDebugDisable = "(?)";
    public static String meanDebugEnable = "(?)";
    public static String meanRegExListener = "(?)";
    public static String meanRegExListenerSucc = "(?)";
    public static String meanCommandIsPlayerOnly = "(?)";
    public static String meanEditGameToggleToTrue = "(?)";
    public static String meanEditGameToggleToFalse = "(?)";
    public static String pluginDisable = "(?)";
    public static String meanJavaAccessDenied = "(?)";
    public static String meanEntityNameSetTo = "(?)";
    public static String meanEntityNameRemove = "(?)";
    private static boolean langForce = false;
    private static String langForceLang = "(?)";
    public static void loadCurrentLang(String languageOrg) {
        String language = languageOrg;
        if (langForce){
            language = langForceLang;
        }

        if (language.equals("zh")) {
            meanAuthor = "作者";
            meanConfig_renamedTo = "{orgName}" + " 配置文件已被重命名为: " + "{newName}";
            meanConfig_versionNotMatch = "配置文件 " + "{orgName}" + " 不匹配当前版本{exVersion} ,正在保存新的...";
            vauleIsWrong = " 该值填写错误 ";
            vauleIsNull = " 该值不存在 ";
            cantFoundSupport = "找不到适合此版本的方法: ";
            finishLoadConfig = "已加载完成";
            meanCommandReged = "&a指令已注册";
            meanConfigLoad = "&a配置已加载";

            meanDebugDisable = "调试模式已关闭";
            meanDebugEnable = "调试模式已开启";
            meanRegExListener = "编辑游戏模式已开启, 正在注册额外的事件监听器";
            meanRegExListenerSucc = "已注册额外的事件监听器";
            meanEditGameToggleToTrue = "已开启编辑游戏模式 手持命名牌右键实体来给实体命名为升级商店(config.yml中levelupShopOpenModeEntityName的值) ，手持物品展示框右键实体来给实体命名为物品商店(config.yml中shopItemEntityName的值) ，手持红石右键实体来删除实体的名字，手持纸右键实体来离开编辑模式";
            meanEditGameToggleToFalse = "已关闭编辑游戏模式";
            pluginDisable = "插件已关闭.";

            meanCommandIsPlayerOnly = "此指令只能由玩家执行！";
            meanJavaAccessDenied = "文件访问被拒绝";

            meanEntityNameSetTo = "&a实体名已从&r '{orgName}' &a设置为 ->&r '{newName}'";
            meanEntityNameRemove = "&a已清理实体名字，原名 -> '{orgName}'";

        } else {
            meanAuthor = "Author";
            meanConfig_renamedTo = "The configuration file {orgName}" + " has been renamed to: " + "{newName}";
            meanConfig_versionNotMatch = "Config " + "{orgName}" + " Does not match the current version, saving a new one...";
            vauleIsWrong = " vaule is wrong ";
            vauleIsNull = " vaule is null ";
            cantFoundSupport = "can't found support for this version: ";
            finishLoadConfig = "Finish Load Config";
            meanCommandReged = "&aCommand registered";
            meanConfigLoad = "&aConfig loaded";

            meanDebugDisable = "Debug now Disabled";
            meanDebugEnable = "Debug now Enabled";
            meanRegExListener = "Game editing mode enabled, registering additional listeners";
            meanRegExListenerSucc = "Registered additional event listeners";
            meanEditGameToggleToTrue = "The editing game mode has been activated. Hold the naming card and right-click on the entity to name it as an upgrade store (the value of levelupShopOpenModeEntityName in config.yml), hold the item display box and right-click on the entity to name it as an item store (the value of shopitemEntityName in config.yml), hold the redstone and right-click on the entity to delete its name, and hold the paper and right-click to leave edit mode.";
            meanEditGameToggleToFalse = "Edit game mode to false";
            pluginDisable = "Disabled.";
            meanCommandIsPlayerOnly = "This command can only be executed by player!";
            meanJavaAccessDenied = "File access denied";

            meanEntityNameSetTo = "&aThe entity name has been set from&r '{orgName}' &ato&r -> '{newName}'";
            meanEntityNameRemove = "&aClear Entity Name, Org ->&r '{orgName}'";
        }
    }

    public static final String relName = "BedwarsRel";
    public static final String msgline = "================================";
    public static final String pluginName = "BedwarsKit";
}
