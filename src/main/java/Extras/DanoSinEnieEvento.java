package Extras;

import org.bukkit.GameMode;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffectType;
import tlldos.tll2.TLL2;

public class DanoSinEnieEvento implements Listener{
    private TLL2 plugin;
    public DanoSinEnieEvento(TLL2 plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void cancelledDamage(EntityDamageEvent e) {
        Entity entity = e.getEntity();

        if(!(entity instanceof Player)){
            if(e.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) || e.getCause().equals(EntityDamageEvent.DamageCause.BLOCK_EXPLOSION)){
                e.setCancelled(true);
            }
        }

        if(entity instanceof Blaze || entity instanceof Enderman){
            if(e.getCause().equals(EntityDamageEvent.DamageCause.DROWNING)){
                e.setCancelled(true);
            }
        }
    }




    @EventHandler
    public void danoProdos(EntityDamageEvent e) {
           if(e.isCancelled()){
               return;
           }
           if (e.getEntity() instanceof Player) {
               Player p = (Player)e.getEntity();
               if(!e.isCancelled()) {
                   if(p.getLastDamageCause() instanceof EntityDamageByEntityEvent){ 
                       if(((EntityDamageByEntityEvent) p.getLastDamageCause()).getDamager() instanceof LivingEntity){
                           LivingEntity monster = (LivingEntity) ((EntityDamageByEntityEvent) p.getLastDamageCause()).getDamager();
                           if (monster.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "SOLAR_SCORPION"), PersistentDataType.STRING)) {
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
        if(entity instanceof Player player){
            if(e.getEntity().getLastDamageCause() == null){
                return;
            }
            if(player.getGameMode() == GameMode.SPECTATOR){
                if(e.getCause() == EntityDamageEvent.DamageCause.VOID){
                    e.setCancelled(true);
                }
            }else if(player.hasPotionEffect(PotionEffectType.UNLUCK)) {
                if (player.getPotionEffect(PotionEffectType.UNLUCK).getAmplifier() == 1) {
                    if (e.getCause() != EntityDamageEvent.DamageCause.SUICIDE || e.getCause() != EntityDamageEvent.DamageCause.THORNS || e.getCause() != EntityDamageEvent.DamageCause.POISON || e.getCause() != EntityDamageEvent.DamageCause.ENTITY_SWEEP_ATTACK || e.getCause() != EntityDamageEvent.DamageCause.VOID) {
                        e.setDamage(e.getDamage() * 2);
                    }else if(e.getCause() == EntityDamageEvent.DamageCause.FALL){
                        e.setDamage(e.getDamage() * 5);
                    }else if(e.getCause() == EntityDamageEvent.DamageCause.FREEZE){
                        e.setDamage(e.getDamage() * 5);
                    }else if(e.getCause() == EntityDamageEvent.DamageCause.LIGHTNING){
                        e.setDamage(e.getDamage() * 5);
                    }
                } else if (player.getPotionEffect(PotionEffectType.UNLUCK).getAmplifier() == 2) {
                    if (e.getCause() != EntityDamageEvent.DamageCause.SUICIDE || e.getCause() != EntityDamageEvent.DamageCause.THORNS || e.getCause() != EntityDamageEvent.DamageCause.POISON || e.getCause() != EntityDamageEvent.DamageCause.ENTITY_SWEEP_ATTACK || e.getCause() != EntityDamageEvent.DamageCause.VOID) {
                        e.setDamage(e.getDamage() * 3);
                    }else if(e.getCause() == EntityDamageEvent.DamageCause.FALL){
                        e.setDamage(e.getDamage() * 6);
                    }else if(e.getCause() == EntityDamageEvent.DamageCause.FREEZE){
                        e.setDamage(e.getDamage() * 6);
                    }else if(e.getCause() == EntityDamageEvent.DamageCause.LIGHTNING){
                        e.setDamage(e.getDamage() * 6);
                    }
                } else if (player.getPotionEffect(PotionEffectType.UNLUCK).getAmplifier() == 3) {
                    if (e.getCause() != EntityDamageEvent.DamageCause.SUICIDE || e.getCause() != EntityDamageEvent.DamageCause.THORNS || e.getCause() != EntityDamageEvent.DamageCause.POISON || e.getCause() != EntityDamageEvent.DamageCause.ENTITY_SWEEP_ATTACK || e.getCause() != EntityDamageEvent.DamageCause.VOID) {
                        e.setDamage(e.getDamage() * 4);
                    }else if(e.getCause() == EntityDamageEvent.DamageCause.FALL){
                        e.setDamage(e.getDamage() * 7);
                    }else if(e.getCause() == EntityDamageEvent.DamageCause.FREEZE){
                        e.setDamage(e.getDamage() * 7);
                    }else if(e.getCause() == EntityDamageEvent.DamageCause.LIGHTNING){
                        e.setDamage(e.getDamage() * 7);
                    }
                }
            }else if(player.hasPotionEffect(PotionEffectType.LUCK)){
                if(player.getPotionEffect(PotionEffectType.LUCK).getAmplifier() == 3){
                    if(e.getCause() == EntityDamageEvent.DamageCause.FALL || e.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK){
                        e.setDamage(e.getDamage() * 4);
                    }
                }else if(player.getPotionEffect(PotionEffectType.LUCK).getAmplifier() == 4){
                    if(e.getCause() == EntityDamageEvent.DamageCause.FALL || e.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK){
                        e.setDamage(e.getDamage() * 5);
                    }
                }
            }else{
                if(e.getCause() == EntityDamageEvent.DamageCause.FALL){
                    e.setDamage(e.getDamage() * 4);
                }else if(e.getCause() == EntityDamageEvent.DamageCause.FREEZE){
                    e.setDamage(e.getDamage() * 4);
                }else if(e.getCause() == EntityDamageEvent.DamageCause.LIGHTNING){
                    e.setDamage(e.getDamage() * 4);
                }
            }
        }
    }

}
