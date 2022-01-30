package Eventos;

import Utilidades.Format;
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
    public void mierdaMute(PlayerChatEvent event){
        var player = event.getPlayer();
        if(player.getPotionEffect(PotionEffectType.LUCK).getAmplifier() == 0){
            event.setCancelled(true);
            player.sendMessage(Format.PREFIX + format("&cEstas Paniqueando!"));
        }else if(player.getPotionEffect(PotionEffectType.LUCK).getAmplifier() == 1){
            event.setCancelled(true);
            player.sendMessage(Format.PREFIX + format("&cEstas Paniqueando!"));
        }else if (player.getPotionEffect(PotionEffectType.LUCK).getAmplifier() == 2){
            event.setCancelled(true);
            player.sendMessage(Format.PREFIX + format("&cEstas Paniqueando!"));
        }else if(player.getPotionEffect(PotionEffectType.LUCK).getAmplifier() == 3){
            event.setCancelled(true);
            player.sendMessage(Format.PREFIX + format("&cEstas Paniqueando!"));
        }
    }
}
