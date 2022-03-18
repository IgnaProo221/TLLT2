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
    public static  String NO_TXT = ChatColor.GOLD + "¡Necesitas el texture pack para jugar!\n " +
            ChatColor.RED + "1. Descarga el texture pack. \n" +
            ChatColor.GOLD + "Este esta en menu principal. \n" +
            ChatColor.RED + "2. Después dirigete a las opciones del juego y pontelo. \n" +
            ChatColor.GREEN + "Una vez hecho: \n" +
            ChatColor.RED + "3. Activa el texture pack y\n" + // mensaje de arriba
            ChatColor.WHITE + "después selecciona el lenguaje custom: 'The Last Life' ";
    //PORQUE STATIC PUBLIC

    public RPListeners(TLL2 instance){
        this.instance = instance;
    }

    @EventHandler
    public void onResourcePackChange(PlayerLocaleChangeEvent e){
        var player = e.getPlayer();
        var locale = e.getLocale().toString();
        if(locale.contains("TLL002_en_us") || locale.contains("TLL002_es_ar") || locale.contains("TLL002_es_cl") ||locale.contains("TLL002_es_es") || locale.contains("TLL002_es_mx")){ // el número uno es la versión del IDIOMA rp, si se cambia, debes cambiar lo demas (Incluye pack.mcmeta) y actualizar el rp
            player.sendMessage("");
        } else player.kickPlayer(NO_TXT + ChatColor.RED + "\n Hay una nueva actualización del texture pack, ¡descarga la ultima!");
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
