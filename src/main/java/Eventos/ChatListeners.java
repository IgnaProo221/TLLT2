package Eventos;

import Utilidades.Format;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.potion.PotionEffectType;
import tlldos.tll2.TLL2;

import static Utilidades.Format.format;

public class ChatListeners implements Listener{
    private TLL2 plugin;
    public ChatListeners (TLL2 plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void mierdaMute(PlayerChatEvent event) {
        var player = event.getPlayer();
        if (event.getMessage().contains("Nigga") || event.getMessage().contains("Nigger") || event.getMessage().contains("nigga") || event.getMessage().contains("nigger") || event.getMessage().contains("n1gga") || event.getMessage().contains("n1ggers") || event.getMessage().contains("NIGGA") || event.getMessage().contains("NIGGERS") || event.getMessage().contains("n!gga") || event.getMessage().contains("n!ggers") || event.getMessage().contains("ni44ers") || event.getMessage().contains("Niggga") || event.getMessage().contains("niigga") || event.getMessage().contains("Niigga") || event.getMessage().contains("niiggaa") ) {
            event.setCancelled(true);
            player.kickPlayer(format("&c&lHas sido baneado permanentemente por usar un lenguaje moralmente incorrecto"));
            Bukkit.getBanList(BanList.Type.NAME).addBan(player.getName(), Format.format("&c&lHas sido baneado permanentemente por usar un lenguaje moralmente incorrecto."), null, String.valueOf(Bukkit.getConsoleSender()));
        }
        if (event.getMessage().contains("♥") || event.getMessage().contains("♦️") || event.getMessage().contains("\uE001") || event.getMessage().contains("\uE06D") || event.getMessage().contains("阿") || event.getMessage().contains("色") || event.getMessage().contains("维") || event.getMessage().contains("哦") || event.getMessage().contains("到") || event.getMessage().contains("和") || event.getMessage().contains("他") || event.getMessage().contains("米") || event.getMessage().contains("你") || event.getMessage().contains("或") || event.getMessage().contains("者") || event.getMessage().contains("世") || event.getMessage().contains("什") || event.getMessage().contains("麼") || event.getMessage().contains("☣") ) {
            event.setCancelled(true);
        }
        if (player.hasPotionEffect(PotionEffectType.LUCK)) {
        if(player.getPotionEffect(PotionEffectType.LUCK).getAmplifier() == 1){
            event.setCancelled(true);
            player.sendMessage(Format.PREFIX + format("&c¡Estas paniquiado!"));
        }else if (player.getPotionEffect(PotionEffectType.LUCK).getAmplifier() == 2){
            event.setCancelled(true);
            player.sendMessage(Format.PREFIX + format("&c¡Estas paniquiado!"));
        }else if(player.getPotionEffect(PotionEffectType.LUCK).getAmplifier() == 3){
            event.setCancelled(true);
            player.sendMessage(Format.PREFIX + format("&c¡Estas paniquiado!"));
        }else if(player.getPotionEffect(PotionEffectType.LUCK).getAmplifier() == 4){
            event.setCancelled(true);
            player.sendMessage(Format.PREFIX + format("&c¡Estas paniquiado!"));
        }
        }
    }
}
