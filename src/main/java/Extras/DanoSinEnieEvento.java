package Extras;

import Utilidades.Mobs;
import org.bukkit.ChatColor;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import tlldos.tll2.TLL2;

public class DanoSinEnieEvento implements Listener{
    private TLL2 plugin;
    public DanoSinEnieEvento(TLL2 plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void cancelledDamage(EntityDamageEvent e) {
        Entity entity = e.getEntity();


        if(entity instanceof Blaze){
            if(e.getCause().equals(EntityDamageEvent.DamageCause.DROWNING)){
                e.setCancelled(true);
            }
        }


        if (entity instanceof Ghast) {
            if(e.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) || e.getCause().equals(EntityDamageEvent.DamageCause.BLOCK_EXPLOSION)){
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void danoPro(EntityDamageEvent e){

        Entity entity = e.getEntity();

        if(entity instanceof Player player) {
            if(e.getCause() == EntityDamageEvent.DamageCause.DROWNING){
                e.setCancelled(true);
                player.damage(10000);
            }
        }
    }

}
