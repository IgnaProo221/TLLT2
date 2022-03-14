package Listeners;

import Utilities.Format;
import io.papermc.paper.event.entity.EntityMoveEvent;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import tlldos.tll2.TLL2;

import java.util.Random;

public class BossesListeners implements Listener{
    TLL2 instance;
    public BossesListeners(TLL2 instance){
        this.instance = instance;
    }

    @EventHandler
    public void wardenSounds(EntityMoveEvent event){
        var entity = event.getEntity();
        if(entity instanceof IronGolem ironGolem){
            if(ironGolem.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class),"WARDEN"), PersistentDataType.STRING)){
                int chancesounds = new Random().nextInt(100);
                int soundstype = new Random().nextInt(3);
                if(chancesounds > 90){
                    if(soundstype == 1){
                        for(Player p : ironGolem.getWorld().getNearbyPlayers(ironGolem.getLocation(),10,10,10)){
                            p.playSound(p.getLocation(), Sound.BLOCK_SCULK_SENSOR_CLICKING, SoundCategory.HOSTILE,10.0F,-1.0F);
                        }
                    }else if(soundstype == 2){
                        for(Player p : ironGolem.getWorld().getNearbyPlayers(ironGolem.getLocation(),10,10,10)){
                            p.playSound(p.getLocation(), Sound.BLOCK_SCULK_SENSOR_PLACE, SoundCategory.HOSTILE,10.0F,-1.0F);
                        }
                    }else{
                        for(Player p : ironGolem.getWorld().getNearbyPlayers(ironGolem.getLocation(),10,10,10)){
                            p.playSound(p.getLocation(), Sound.BLOCK_SCULK_SENSOR_CLICKING_STOP, SoundCategory.HOSTILE,10.0F,-1.0F);
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void wardenSounds2(EntityDamageByEntityEvent e){
        var damager = e.getDamager();
        var damaged = e.getEntity();
        if(damager instanceof IronGolem ironGolem){
            if(ironGolem.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class),"WARDEN"), PersistentDataType.STRING)){
                    for(Player p : ironGolem.getWorld().getNearbyPlayers(ironGolem.getLocation(),10,10,10)) {
                        p.playSound(p.getLocation(), Sound.BLOCK_SCULK_SENSOR_FALL, SoundCategory.HOSTILE, 10.0F, -1.0F);
                    }
            }
        }
        if(damager instanceof Player){
            if(damaged instanceof IronGolem ironGolem){
                if(ironGolem.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class),"WARDEN"), PersistentDataType.STRING)){
                    for(Player p : ironGolem.getWorld().getNearbyPlayers(ironGolem.getLocation(),10,10,10)) {
                        p.playSound(p.getLocation(), Sound.BLOCK_SCULK_SENSOR_BREAK, SoundCategory.HOSTILE, 10.0F, -1.0F);
                    }
                }
            }
        }
    }

    @EventHandler
    public void wardenAttacks1(EntityDamageByEntityEvent e){
        var damager = e.getDamager();
        var damaged = e.getEntity();
        if(damager instanceof Player){
            if(damaged instanceof IronGolem ironGolem){
                if(ironGolem.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class),"WARDEN"), PersistentDataType.STRING)){
                    ironGolem.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,1200,4,false,false,false));
                    ironGolem.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,1200,1,false,false,false));
                }
            }
        }
    }

    @EventHandler
    public void wardenAttacks2(EntityMoveEvent event){
        var entity = event.getEntity();
        if(entity instanceof IronGolem ironGolem) {
            if (ironGolem.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "WARDEN"), PersistentDataType.STRING)) {
                int attackchance = new Random().nextInt(100);
                if(attackchance == 1){
                    for(Player p : ironGolem.getWorld().getNearbyPlayers(ironGolem.getLocation(),10,10,10)) {
                        if(p == null)return;
                        p.playSound(p.getLocation(),Sound.ENTITY_ENDER_DRAGON_GROWL,SoundCategory.HOSTILE,10.0F, -1.0F);
                        p.sendMessage(Format.PREFIX,Format.format("&3¡El Warden a lanzado un sonido aturdidor!"));
                        p.damage(40,ironGolem);
                        Vector vector = p.getEyeLocation().getDirection().multiply(-3);
                        p.setVelocity(vector);
                    }
                }
            }
        }}

}
