package github.tsffish.bedwarskit.util.misc;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class StringMgr {
    public static final String msgline = "\u003d\u003d\u003d\u003d\u003d\u003d\u003d\u003d\u003d\u003d\u003d\u003d\u003d\u003d\u003d\u003d\u003d\u003d\u003d\u003d\u003d\u003d\u003d\u003d\u003d\u003d\u003d\u003d\u003d\u003d\u003d\u003d";
    public static final String pluginName = "\u0042\u0065\u0064\u0077\u0061\u0072\u0073\u004b\u0069\u0074";
    public static String meanCommandReged = "(?)";
    public static String meanConfigLoad = "(?)";
    public static String pluginDisable = "(?)";
    public static String meanAuthor = "(?)";

    public static void loadCurrentLang(String languageOrg) {
        String language = languageOrg;

        if (language.equals("zh")) {
            meanAuthor = "\u4f5c\u8005";
            meanCommandReged = "&a\u6307\u4ee4\u5df2\u6ce8\u518c";
            meanConfigLoad = "&a\u914d\u7f6e\u5df2\u52a0\u8f7d";
            pluginDisable = "\u63d2\u4ef6\u5df2\u5173\u95ed";
        } else {
            meanAuthor = "Author";
            meanCommandReged = "&aCommand registered";
            meanConfigLoad = "&aConfig loaded";
            pluginDisable = "Disabled.";
        }
    }
}
