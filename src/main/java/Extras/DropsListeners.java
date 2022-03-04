package Extras;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.persistence.PersistentDataType;
import tlldos.tll2.TLL2;

public class DropsListeners implements Listener{
    private TLL2 plugin;
    public DropsListeners(TLL2 plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void dropsyeso(EntityDeathEvent e){
        var entity = e.getEntity();
        if(entity instanceof Zombie zombie){
            if(zombie.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "BLIGHTED_ZOMBIE"), PersistentDataType.STRING)){
                e.getDrops().clear();
                e.getDrops().add(MobDrops.blighdrop1());
            }
        }else if(entity instanceof Skeleton skeleton){
            if(skeleton.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "BLIGHTED_SKELETON"), PersistentDataType.STRING)){
                e.getDrops().clear();
                e.getDrops().add(MobDrops.blighdrop2());
            }
        }else if(entity instanceof Creeper creeper){
            if(creeper.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "BLIGHTED_CREEPER"), PersistentDataType.STRING)){
                e.getDrops().clear();
                e.getDrops().add(MobDrops.blighdrop4());
            }
        }else if(entity instanceof Spider spider){
            if(spider.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "BLIGHTED_SPIDER"), PersistentDataType.STRING)){
                e.getDrops().clear();
                e.getDrops().add(MobDrops.blighdrop3());
            }
        }else if(entity instanceof Phantom phantom){
            if(phantom.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "BLIGHTED_PHANTOM"), PersistentDataType.STRING)){
                e.getDrops().clear();
                e.getDrops().add(MobDrops.blighdrop5());
            }
        }else if(entity instanceof Witch witch){
            if(witch.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "BLIGHTED_WITCH"), PersistentDataType.STRING)){
                e.getDrops().clear();
                e.getDrops().add(MobDrops.blighdrop6());
            }
        }else if(entity instanceof Ghast ghast){
            if(ghast.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "BLIGHTED_GHAST"), PersistentDataType.STRING)){
                e.getDrops().clear();
                e.getDrops().add(MobDrops.blighdrop7());
            }
        }else if(entity instanceof PiglinBrute brute){
            if(brute.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "BLIGHTED_BRUTE"), PersistentDataType.STRING)){
                e.getDrops().clear();
                e.getDrops().add(MobDrops.blighdrop8());
            }
        }else if(entity instanceof Enderman enderman){
            if(enderman.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "BLIGHTED_ENDERMAN"), PersistentDataType.STRING)){
                e.getDrops().clear();
                e.getDrops().add(MobDrops.blighdrop9());
            }
        }else if(entity instanceof IronGolem ironGolem){
            if(ironGolem.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class),"EXPERIMENT_1"),PersistentDataType.STRING)){
                e.getDrops().clear();
                e.getDrops().add(Items.metaldes());
                e.getDrops().add(Items.celulaEnergia());
            }
        }
    }

}
