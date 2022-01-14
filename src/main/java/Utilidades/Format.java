package Utilidades;

import org.bukkit.ChatColor;

public class Format {

    //TODO esto se podria hacer en Utils y nos ahorrariamos una clase si les da la gana claro

    public static String format (String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static String prefix (){
        return ChatColor.translateAlternateColorCodes('&',"&6&lThe&c&lLast&6&lLife &7➤ ");
    }
}
