package Eventos;

import Utilidades.Mobs;
import Utilidades.TLLEntities;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;

import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import tlldos.tll2.TLL2;

import java.util.*;

import static Extras.Items.createFragmentoSangre;
import static Utilidades.Format.format;

public class EntityListeners implements Listener {
    public static HashMap<Player, Integer> hash = new HashMap<>();

    public static void addHash(Player p) {
        if (hash.containsKey(p)) {
            int value = hash.get(p);
            int result = value + 1;
            hash.remove(p);
            hash.put(p, result);
        }
        hash.put(p, 1);
    }

    @EventHandler
    public void damageEntity (EntityDamageByEntityEvent event) {

        Entity entity = event.getEntity();
        Entity damager = event.getDamager();

        if(entity instanceof Player p){
            if(damager instanceof Vex){
                var vex = (Vex)damager;
                if(vex.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "VEX_EXPLOSIVE"), PersistentDataType.STRING)){
                    p.getLocation().getWorld().createExplosion(p.getLocation(), 5, false, true);
                }
                if(vex.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "VEX_SCIENTIST"), PersistentDataType.STRING)){
                    p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 800, 0, true, false, true));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 800, 0, true, false, true));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 800, 0, true, false, true));
                }
                if(vex.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "VEX_MECHA"), PersistentDataType.STRING)){
                    p.getLocation().getWorld().strikeLightning(p.getLocation());
                    p.getLocation().getWorld().strikeLightning(p.getLocation());
                    p.getLocation().getWorld().strikeLightning(p.getLocation());
                    p.getLocation().getWorld().strikeLightning(p.getLocation());
                    p.getLocation().getWorld().strikeLightning(p.getLocation());
                }
            }
        }

        if(damager instanceof Vex){
            var vex = (Vex)damager;
            var player = (Player)entity;
            if(vex.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "VEX_EXPLOSIVE"), PersistentDataType.STRING)){
                entity.getLocation().getWorld().createExplosion(entity.getLocation(), 5, false, true);
            }
            if(vex.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "VEX_SCIENTIST"), PersistentDataType.STRING)){

            }
            if(vex.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "VEX_MECHA"), PersistentDataType.STRING)){
                entity.getLocation().getWorld().createExplosion(entity.getLocation(), 5, false, true);
            }
        }

    }




    @EventHandler
    public void onShotthit(ProjectileHitEvent event){
        var hitblock = event.getHitBlock();
        var entity = event.getHitEntity();
        var shooter = event.getEntity().getShooter();

        if (shooter instanceof Skeleton){
            var skeleton = (Entity)shooter;
            if(skeleton.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "IGNITED_SKELETON"), PersistentDataType.STRING)){
                if (hitblock != null) {
                    hitblock.getLocation().createExplosion(2, true, false);
                } else if (entity != null) {
                    entity.getLocation().createExplosion(2, true, false);
                }
            }
        }
        if(shooter instanceof Pillager){
            var pillager = (Entity)shooter;
            if(pillager.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "OVERRATED_PILLAGER"), PersistentDataType.STRING)){
                if (hitblock != null) {
                    hitblock.getLocation().createExplosion(2, false, true);
                } else if (entity != null) {
                    entity.getLocation().createExplosion(2, false, true);
                }
            }
        }
    }


    @EventHandler
    public void onDeathxd(EntityDeathEvent event){
        var e = event.getEntity();
        if(e instanceof Spider){
            var spider = (Spider)e;
            if(spider.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "PLAGUE_SPIDER"), PersistentDataType.STRING)){
                var nube = e.getLocation().getWorld().spawn(e.getLocation(), AreaEffectCloud.class);
                Mobs.plagueEntity(nube);
            }
        }
    }


    @EventHandler
    public void entityDeath (EntityDeathEvent event) {

        Entity entity = event.getEntity();
        Entity killer = event.getEntity().getKiller();

        Random random = new Random();

        int size = random.nextInt(2);
        ItemStack dropFrag = createFragmentoSangre(size);

        if (entity instanceof Villager && killer instanceof Player p) {

            event.getDrops().add(dropFrag);

            addHash(p);
            p.sendMessage(format("&7¡Has sacrificado a un &6&lAldeano&7, has recibido &6&l" + size + " &cFragmento(s) de Sangre&7!"));
        }
    }

}
