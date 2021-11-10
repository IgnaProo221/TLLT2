package Utilidades;

import org.bukkit.ChatColor;

public class Format {

    public static String format (String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
