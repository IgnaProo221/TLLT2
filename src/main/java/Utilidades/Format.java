package Utilidades;

import org.bukkit.ChatColor;

public class Format {

    //TODO esto se podria hacer en Utils y nos ahorrariamos una clase si les da la gana claro

    public final static String PREFIX = format("&6&lThe&c&lLast&6&lLife &7➤ ");

    public static String format (String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

}
