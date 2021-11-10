package Extras;

import Utilidades.Mobs;
import net.minecraft.world.damagesource.DamageSource;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftEntity;
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

    public void setDamageSource(Player p, double ammount) {
        net.minecraft.world.entity.Entity en = ((CraftEntity)p).getHandle();
        en.damageEntity(DamageSource.h, (float)ammount);
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
    public void danoPro(EntityDamageEvent e,Player p, double ammount){
        Entity entity = e.getEntity();
        if(entity instanceof  Player){
            if(e.getCause() == EntityDamageEvent.DamageCause.DROWNING){
                e.setCancelled(true);
                setDamageSource(p, 1000.0D);
            }
        }
    }

}
