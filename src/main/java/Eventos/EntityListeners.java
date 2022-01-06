package Eventos;

import Utilidades.Mobs;
import Utilidades.TLLEntities;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
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
    public void damageEntity(EntityDamageByEntityEvent event) {

        Entity entity = event.getEntity();
        Entity damager = event.getDamager();

        if (entity instanceof Player p) {
            if (damager instanceof Vex) {
                var vex = (Vex) damager;
                if (vex.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "VEX_EXPLOSIVE"), PersistentDataType.STRING)) {
                    p.getLocation().getWorld().createExplosion(p.getLocation(), 5, false, true);
                }
                if (vex.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "VEX_SCIENTIST"), PersistentDataType.STRING)) {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 800, 0, true, false, true));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 800, 0, true, false, true));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 800, 0, true, false, true));
                }
                if (vex.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "VEX_MECHA"), PersistentDataType.STRING)) {
                    p.getLocation().getWorld().strikeLightning(p.getLocation());
                }
            }
            if(damager instanceof WitherSkeleton){
                var witherskeleton = (WitherSkeleton)damager;
                if(witherskeleton.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "SHATTER_GUARDIAN"), PersistentDataType.STRING)){
                    p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 2400, 3,true, true, true));
                }
            }
            if(damager instanceof Slime){
                var slime = (Slime)damager;
                if(slime.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "FREEZING_SLIME"), PersistentDataType.STRING)){
                    p.setFreezeTicks(1200);
                }
            }
        }

    }

    @EventHandler
    public void onShootbow(EntityShootBowEvent event) {
        var entity = event.getEntity();
        if (entity instanceof Skeleton) {
            var skeleton = (Entity) entity;
            if (skeleton.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "POWERED_SKELETON"), PersistentDataType.STRING)) {
                var skull = (WitherSkull)event.getEntity().getWorld().spawnEntity(event.getEntity().getLocation().add(0, 1, 0), EntityType.WITHER_SKULL);
                skull.setCharged(true);
                skull.setYield(10);
                event.setProjectile(skull);
            }
        }
    }


    @EventHandler
    public void onShotthit(ProjectileHitEvent event) {
        var hitblock = event.getHitBlock();
        var entity = event.getHitEntity();
        var shooter = event.getEntity().getShooter();
        var projectile = event.getEntity();

        if (shooter instanceof Skeleton) {
            var skeleton = (Entity) shooter;
            if (skeleton.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "IGNITED_SKELETON"), PersistentDataType.STRING)) {
                if (hitblock != null) {
                    hitblock.getLocation().createExplosion(2, false, true);
                    projectile.remove();
                } else if (entity != null) {
                    entity.getLocation().createExplosion(2, false, true);
                    projectile.remove();
                }
            }
            if (skeleton.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "COPPER_SKELETON"), PersistentDataType.STRING)) {
                if (hitblock != null) {
                    hitblock.getLocation().getWorld().strikeLightning(hitblock.getLocation());
                } else if (entity != null) {
                    entity.getLocation().getWorld().strikeLightning(entity.getLocation());
                }
            }
        }
        if (shooter instanceof Pillager) {
            var pillager = (Entity) shooter;
            if (pillager.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "OVERRATED_PILLAGER"), PersistentDataType.STRING)) {
                if (hitblock != null) {
                    hitblock.getLocation().createExplosion(2, false, true);
                    projectile.remove();
                } else if (entity != null) {
                    entity.getLocation().createExplosion(2, false, true);
                    projectile.remove();
                }
            }
        }
        if (shooter instanceof Blaze) {
            var blaze = (Entity) shooter;
            if (blaze.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "HELLFIRE"), PersistentDataType.STRING)) {
                if (hitblock != null) {
                    hitblock.getLocation().createExplosion(3, false, true);
                } else if (entity != null) {
                    entity.getLocation().createExplosion(3, false, true);
                    entity.setFireTicks(2400);
                }
            }
        }

    }


    @EventHandler
    public void onDeathxd(EntityDeathEvent event) {
        var e = event.getEntity();
        if (e instanceof Spider) {
            var spider = (Spider) e;
            if (spider.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "PLAGUE_SPIDER"), PersistentDataType.STRING)) {
                var nube = e.getLocation().getWorld().spawn(e.getLocation(), AreaEffectCloud.class);
                Mobs.plagueEntity(nube);
            }
        }
        if(e instanceof Zombie){
            var zombie = (Zombie) e;
            if(zombie.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "TNT_MONSTER"), PersistentDataType.STRING)){
                var tnt = e.getLocation().getWorld().spawn(e.getLocation(), TNTPrimed.class);
                Mobs.tntZomb(tnt);
            }
        }
        if(e instanceof Bat){
            var bat = (Bat)e;
            if(bat.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "EXPLOSIVE_BAT"), PersistentDataType.STRING)){
                bat.getLocation().createExplosion(8, false, true);
            }
        }
    }


    @EventHandler
    public void entityDeath(EntityDeathEvent event) {

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

    @EventHandler
    public void noZombPig(EntityTransformEvent e) {
        if (e.getTransformReason() == EntityTransformEvent.TransformReason.PIGLIN_ZOMBIFIED) {
            e.setCancelled(true);
        }
    }
}
