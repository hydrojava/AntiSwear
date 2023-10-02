package cf.sslyi.antiswear;

import org.bukkit.ChatColor;

public class Utils {

    public static String parseColorCodes (final String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
