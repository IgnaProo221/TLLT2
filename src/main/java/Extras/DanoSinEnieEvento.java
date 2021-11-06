package Extras;

import org.bukkit.ChatColor;
import org.bukkit.entity.Ghast;
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
    public void evitarDano(EntityDamageEvent e){
        Ghast gh = (Ghast) e.getEntity();
        if(gh.getCustomName() != null&&  gh.getCustomName().equalsIgnoreCase(ChatColor.GOLD + "Undying Ghast")){
           if(e.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) || e.getCause().equals(EntityDamageEvent.DamageCause.BLOCK_EXPLOSION)){
               e.setCancelled(true);
           }

        }
    }
}
