package Extras;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
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
    public void cancelledDamage(EntityDamageEvent e) {
        Entity entity = e.getEntity();

        if (entity instanceof Ghast) {

            Ghast ghast = (Ghast)entity;
            String customName = ghast.getCustomName();
            String needName = ChatColor.GOLD + "Undying Ghast";

            if (customName == null) {
                return;
            }

            if (customName.equals(needName)) {

                if (e.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION || e.getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION) {

                    e.setCancelled(true);
                }
            }
        }
    }
}
