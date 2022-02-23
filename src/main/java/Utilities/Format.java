package Utilities;

import org.bukkit.ChatColor;

public class Format {

    //TODO esto se podría hacer en Utils y nos ahorraríamos una clase si les da la gana claro

    public final static String PREFIX = format("&6&lThe&c&lLast&6&lLife &8➤ ");

    public static String format (String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

}
