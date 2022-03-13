package Listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLocaleChangeEvent;
import tlldos.tll2.TLL2;

import java.util.Random;

public class RPListeners implements Listener{
    TLL2 instance;
    static public String NO_TXT = ChatColor.GOLD + "¡Necesitas el texture pack para jugar!\n " +
            ChatColor.RED + "1. Descarga el texture pack. \n" +
            ChatColor.GOLD + "Este esta en el canal de Recursos del Discord. \n" +
            ChatColor.RED + "2. Pega el archivo en resourcepack en la carpeta de .minecraft. \n" +
            ChatColor.GREEN + "Una vez hecho: \n" +
            ChatColor.RED + "3. Activa el texture pack y\n" + // mensaje de arriba
            ChatColor.GOLD + "después selecciona el lenguaje custom: 'The Last Life' ";

    public RPListeners(TLL2 instance){
        this.instance = instance;
    }

    @EventHandler
    public void onResourcePackChange(PlayerLocaleChangeEvent e){
        var player = e.getPlayer();
        var locale = player.getLocale();
        if(locale.contains("TLL001_en_us") || locale.contains("TLL001_es_ar") || locale.contains("TLL001_es_cl") ||locale.contains("TLL001_es_es") || locale.contains("TLL001_es_mx")){ // el número uno es la versión del IDIOMA rp, si se cambia, debes cambiar lo demas (Incluye pack.mcmeta) y actualizar el rp
            player.sendMessage("");
        } else player.kickPlayer(NO_TXT);
    }
    @EventHandler
    public void mantenimientoOn(PlayerLocaleChangeEvent event){
        if(TLL2.mantenimiento){
            if(!event.getPlayer().isOp()) {
                    event.getPlayer().kickPlayer("&c&l¡El Servidor esta en mantenimiento! Vuelva mas tarde.");
            }
        }
    }
}
