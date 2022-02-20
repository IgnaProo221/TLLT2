package Eventos;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLocaleChangeEvent;
import tlldos.tll2.TLL2;

import java.util.Random;

public class AsegurarRp implements Listener{
    TLL2 instance;
    Random random = new Random();
    static public String NO_TXT = ChatColor.GOLD + "¡Necesitas el texture pack para jugar!\n " +
            ChatColor.RED + "1. Descarga el texture pack. \n" +
            ChatColor.GOLD + "Este esta en el canal de Recursos del Discord. \n" +
            ChatColor.RED + "2. Pega el archivo en resourcepack en la carpeta de .minecraft. \n" +
            ChatColor.GOLD + "Una vez hecho: \n" +
            ChatColor.RED + "3. Activa el texture pack y\n" + // mensaje de arriba
            ChatColor.GOLD + "después selecciona el lenguaje custom: 'The Last Life' ";

    public AsegurarRp(TLL2 instance){
        this.instance = instance;
    }

    @EventHandler
    public void onResourcePackChange(PlayerLocaleChangeEvent e){
        var player = e.getPlayer();
        var locale = e.getLocale().toString();
        if(locale.contains("TLL01_en_us") || locale.contains("TLL01_es_ar") || locale.contains("TLL01_es_cl") ||locale.contains("TLL01_es_es") || locale.contains("TLL01_es_mx")){ // el número uno es la versión del IDIOMA rp, si se cambia, debes cambiar lo demas (Incluye pack.mcmeta) y actualizar el rp
            player.sendMessage("");
        } else player.kickPlayer(NO_TXT);
    }
}
