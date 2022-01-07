package Extras;

import Utilidades.Mobs;
import net.minecraft.world.damagesource.DamageSource;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.persistence.PersistentDataType;
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

        if(entity instanceof Skeleton){
            if(e.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) || e.getCause().equals(EntityDamageEvent.DamageCause.BLOCK_EXPLOSION)){
                if(entity.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "IGNITED_SKELETON"), PersistentDataType.STRING)){
                 e.setCancelled(true);
                }
            }
        }

        if(entity instanceof Pillager){
            if(e.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) || e.getCause().equals(EntityDamageEvent.DamageCause.BLOCK_EXPLOSION)){
                if(entity.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "OVERRATED_PILLAGER"), PersistentDataType.STRING)){
                    e.setCancelled(true);
                }
            }
        }


        if (entity instanceof Ghast) {
            if(e.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) || e.getCause().equals(EntityDamageEvent.DamageCause.BLOCK_EXPLOSION)){
                e.setCancelled(true);
            }
        }

        if (entity instanceof Bat) {
            if(e.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) || e.getCause().equals(EntityDamageEvent.DamageCause.BLOCK_EXPLOSION)){
                e.setCancelled(true);
            }
        }
    }




    @EventHandler
    public void danoProdos(EntityDamageByEntityEvent e){
            Entity entity = e.getEntity();
        if(e.getCause() == null){
            return;
        }
        if(e.getEntity().getLastDamageCause() == null){
            return;
        }

            if(entity instanceof Player player){
                if(player.getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK){
                    if(e.getDamager() instanceof Spider spider){
                        if(spider.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "SOLAR_SCORPION"), PersistentDataType.STRING)){
                            if(e.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK){
                                e.setDamage(e.getDamage() * 8);
                            }
                        }
                    }
                }
            }
    }





    @EventHandler
    public void danoPro(EntityDamageEvent e){
        Entity entity = e.getEntity();
        if(entity instanceof Player ){
            if(e.getCause() == null){
                return;
            }
            if(e.getEntity().getLastDamageCause() == null){
                return;
            }

            if(e.getCause() == EntityDamageEvent.DamageCause.DROWNING){
                e.setDamage(100000);
            }
            if(e.getCause() == EntityDamageEvent.DamageCause.VOID){
                e.setDamage(100000);
            }
            if(e.getCause() == EntityDamageEvent.DamageCause.CONTACT){
                e.setDamage(100000);
            }
            if(e.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK) {
                e.setDamage(e.getDamage() * 5);
            }
            if(e.getCause() == EntityDamageEvent.DamageCause.FIRE){
                e.setDamage(e.getDamage() * 6);
            }
            if(e.getCause() == EntityDamageEvent.DamageCause.FREEZE){
                e.setDamage(e.getDamage() * 10);
            }
            if(e.getCause() == EntityDamageEvent.DamageCause.SUFFOCATION){
                e.setDamage(e.getDamage() * 10);
            }
            if(e.getCause() == EntityDamageEvent.DamageCause.LAVA){
                e.setDamage(e.getDamage() * 10);
            }
            if(e.getCause() == EntityDamageEvent.DamageCause.HOT_FLOOR){
                e.setDamage(e.getDamage() * 10);
            }
        }
    }

}
