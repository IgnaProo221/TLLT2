package Eventos;

import Utilidades.Mobs;
import Utilidades.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;

import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import tlldos.tll2.TLL2;

import java.util.*;

import static Extras.Items.createFragmentoSangre;
import static Utilidades.Format.format;
import static Utilidades.Format.prefix;

public class EntityListeners implements Listener {
    private final TLL2 plugin;
    public static HashMap<Player, Integer> hash = new HashMap<>();
    public EntityListeners(TLL2 plugin){
        this.plugin = plugin;
    }

    public static void addHash(Player p) {
        if (hash.containsKey(p)) {
            int value = hash.get(p);
            int result = value + 1;
            hash.remove(p);
            hash.put(p, result);
        }
        hash.put(p, 1);
    }


    public void interEffects(Player p) {
        int effect = new Random().nextInt(3);
        if (effect == 1) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 1200, 4, true, true, true));
        } else if (effect == 2) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 400, 1, true, true, true));
        } else {
            p.setFireTicks(1200);
        }
    }

    public void blightedZombieffects(Player p) {
        int effect = new Random().nextInt(5);
        if(effect == 1){
            p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 400, 9, true, true, true));
        }else if(effect == 2){
            p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 400, 9, true, true, true));
        }else if(effect == 3){
            p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 400, 9, true, true, true));
        }else if(effect == 4){
            p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 400, 9, true, true, true));
        }else{
            p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 400, 9, true, true, true));
        }
    }

    @EventHandler
    public void damageEntity(EntityDamageByEntityEvent event) {

        Random random = new Random();
        int lifestealpercentage = random.nextInt(100);
        var entity = event.getEntity();
        var damager = event.getDamager();
        if(damager instanceof Player pa){

            if(pa.getInventory().getItemInMainHand().getItemMeta() != null){
                return;
            }else if(pa.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()){
                if(pa.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 4006){
                    entity.setFreezeTicks(400);
                }else if(pa.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 4010){
                    if(lifestealpercentage > 90) {
                        if (pa.hasCooldown(Material.NETHERITE_SWORD)) {
                            return;
                        } else {
                            pa.setHealth(pa.getHealth() + 2);
                            pa.sendMessage(prefix(), format("&c¡Tu Bloodstained Saber te a curado por 1 corazon!"));
                            pa.setCooldown(Material.NETHERITE_SWORD, 400);
                        }
                    }
                }
            }else{
                return;
            }
        }



        if (entity instanceof Player p) {
            if(damager instanceof EvokerFangs z){
                if(z.getOwner() instanceof Evoker evoker){
                    if(evoker.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "FIRE_EVOKER"), PersistentDataType.STRING)){
                        entity.setFireTicks(1200);
                    }else if(evoker.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "FREEZE_EVOKER"), PersistentDataType.STRING)){
                        entity.setFreezeTicks(1200);
                    }else if(evoker.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "HEX_EVOKER"), PersistentDataType.STRING)){
                        ((Player) entity).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 400, 0, true, true, true));
                        ((Player) entity).addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 400, 4, true, true, true));
                        ((Player) entity).addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 400, 0, true, true, true));
                    }
                }
            }
            if (damager instanceof Vex) {
                var vex = (Vex) damager;
                if (vex.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "VEX_EXPLOSIVE"), PersistentDataType.STRING)) {
                    event.setCancelled(true);
                    p.getLocation().getWorld().createExplosion(vex,5, false, true);
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
            if(damager instanceof Spider){
                var spider = (Spider)damager;
                if(spider.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "AGILE_SPIDER"), PersistentDataType.STRING)){
                    p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 600, 2,true, true, true));
                }
                if(spider.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "INTER_SPIDER"), PersistentDataType.STRING)){
                    interEffects(p);
                }
                if(spider.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "SOLAR_SCORPION"), PersistentDataType.STRING)){
                    p.setFireTicks(400);
                }
                if(spider.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "BLIGHTED_SPIDER"), PersistentDataType.STRING)){
                    p.getLocation().getWorld().setType(p.getLocation(), Material.COBWEB);
                }
            }
            if(damager instanceof Slime){
                var slime = (Slime)damager;
                if(slime.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "FREEZING_SLIME"), PersistentDataType.STRING)){
                    p.setFreezeTicks(1200);
                }
            }
            if(damager instanceof Zombie){
                var zombie = (Zombie)damager;
                if(zombie.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "TIER_3"), PersistentDataType.STRING)){
                    p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 400, 2, true, true, true));
                }
                if(zombie.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "BLIGHTED_ZOMBIE"), PersistentDataType.STRING)){
                    blightedZombieffects(p);
                }
            }
            if(damager instanceof Enderman){
                var enderman = (Enderman)damager;
                if(enderman.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "BLIGHTED_ENDERMAN"), PersistentDataType.STRING)){
                    p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 400, 0, true, true, true));
                }
            }
            if(damager instanceof Phantom){
                var phantom = (Phantom)damager;
                if(phantom.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "BLIGHTED_PHANTOM"), PersistentDataType.STRING)){
                    p.setFreezeTicks(1200);
                    p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 1200, 4,true, true, true));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 1200, 4, true, true, true));
                    }
            }
        }

    }

    @EventHandler
    public void onShootbow(EntityShootBowEvent event) {

        int caca = new Random().nextInt(2);
        var entity = event.getEntity();
        if (entity instanceof Skeleton) {
            var skeleton = (Entity) entity;
            if (skeleton.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "POWERED_SKELETON"), PersistentDataType.STRING)) {
                var skull = (WitherSkull)event.getEntity().getWorld().spawnEntity(event.getEntity().getLocation().add(0, 1, 0), EntityType.WITHER_SKULL);
                skull.setYield(10);
                event.setProjectile(skull);
            }
        }
        if (entity instanceof Illusioner) {
            var illusioner = (Entity) entity;
            if (illusioner.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "DIMEN_MAGE"), PersistentDataType.STRING)) {
                var fireball = (Fireball)event.getEntity().getWorld().spawnEntity(event.getEntity().getLocation().add(0, 1, 0), EntityType.FIREBALL);
                fireball.setYield(7);
                event.setProjectile(fireball);
            }
        }
        if(entity instanceof WitherSkeleton){
            var witherskeleton = (Entity)entity;
            if(witherskeleton.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "BLIGHTED_WITHER"), PersistentDataType.STRING)){
                if(caca == 1){
                    var skull = (WitherSkull)event.getEntity().getWorld().spawnEntity(event.getEntity().getLocation().add(0,1,0), EntityType.WITHER_SKULL);
                    event.setProjectile(skull);
                }else{
                    var fireball = (Fireball)event.getEntity().getWorld().spawnEntity(event.getEntity().getLocation().add(0,1,0), EntityType.FIREBALL);
                    fireball.setYield(4);
                    event.setProjectile(fireball);
                }
            }
        }
    }


    @EventHandler
    public void onShotthit(ProjectileHitEvent event) {
        var hitblock = event.getHitBlock();
        var entity = event.getHitEntity();
        var shooter = event.getEntity().getShooter();
        var projectile = event.getEntity();

        if(projectile instanceof WitherSkull){
            if (hitblock != null) {
                hitblock.getLocation().createExplosion(projectile,1, false, true);
            } else if (entity != null) {
                entity.getLocation().createExplosion(projectile,1, false, true);
            }
        }

        if(shooter instanceof Shulker){
            var shulker = (Entity)shooter;
            if(shulker.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class),"DIMEN_SHULKER"), PersistentDataType.STRING)){
                if (hitblock != null) {
                    hitblock.getLocation().createExplosion(shulker,2, false, true);
                } else if (entity != null) {
                    entity.getLocation().createExplosion(shulker,2, false, true);
                }
            }
            }

        if (shooter instanceof Skeleton) {
            var skeleton = (Entity) shooter;
            if (skeleton.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "IGNITED_SKELETON"), PersistentDataType.STRING)) {
                if (hitblock != null) {
                    hitblock.getLocation().createExplosion(skeleton,2, false, true);
                    projectile.remove();
                } else if (entity != null) {
                    entity.getLocation().createExplosion(skeleton,2, false, true);
                    projectile.remove();
                }
            }

            if (skeleton.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "BLIGHTED_SKELETON"), PersistentDataType.STRING)) {
                if (hitblock != null) {
                    hitblock.getLocation().createExplosion(skeleton,3, false, true);
                    hitblock.getLocation().getWorld().strikeLightning(hitblock.getLocation());
                    projectile.remove();
                } else if (entity != null) {
                    entity.getLocation().createExplosion(skeleton,3,false, true);
                    entity.getLocation().getWorld().strikeLightning(entity.getLocation());
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
                    hitblock.getLocation().createExplosion(pillager,2, false, true);
                    projectile.remove();
                } else if (entity != null) {
                    entity.getLocation().createExplosion(pillager,2, false, true);
                    projectile.remove();
                }
            }
        }
        if (shooter instanceof Blaze) {
            var blaze = (Entity) shooter;
            if (blaze.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "HELLFIRE"), PersistentDataType.STRING)) {
                if (hitblock != null) {
                    hitblock.getLocation().createExplosion(blaze,3, false, true);
                } else if (entity != null) {
                    entity.getLocation().createExplosion(blaze,3, false, true);
                    entity.setFireTicks(2400);
                }
            }
        }
    }

    @EventHandler
    public void explotarRadi(EntityExplodeEvent e){
        var entity = (Entity)e.getEntity();
        if(entity instanceof Creeper creeper){

            if(creeper.getPersistentDataContainer().has(new NamespacedKey(Utils.getPlugin(), "HELLFIRE_CREEPER"), PersistentDataType.STRING)){
                entity.getLocation().getNearbyPlayers( 10, 10, 10).forEach(player -> player.setFireTicks(1200));
/*                Player nearby2 = (Player)entity.getLocation().getWorld().getNearbyPlayers(entity.getLocation(), 10.0D,10.0D,10.0D);
                nearby2.sendMessage(format("TEST"));
                nearby2.setFireTicks(1200);
 */
            }
            if(creeper.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "HIVE_MIND"), PersistentDataType.STRING)){
                Bukkit.getScheduler().runTaskLater(plugin, () ->{
                    var bee1 = (Bee)creeper.getLocation().getWorld().spawn(creeper.getLocation(), Bee.class);
                    var bee2 = (Bee)creeper.getLocation().getWorld().spawn(creeper.getLocation(), Bee.class);
                    var bee3 = (Bee)creeper.getLocation().getWorld().spawn(creeper.getLocation(), Bee.class);
                    var bee4 = (Bee)creeper.getLocation().getWorld().spawn(creeper.getLocation(), Bee.class);
                    var bee5 = (Bee)creeper.getLocation().getWorld().spawn(creeper.getLocation(), Bee.class);
                    Mobs.thePlague(bee1);
                    Mobs.thePlague(bee2);
                    Mobs.thePlague(bee3);
                    Mobs.thePlague(bee4);
                    Mobs.thePlague(bee5);
                },4L);
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
                bat.getLocation().createExplosion(bat, 8, false, true);
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



    @EventHandler
    public void witchThrow(ProjectileHitEvent e){
         var entity = e.getEntity();
         var shooter = e.getEntity().getShooter();
         if(shooter instanceof Witch) {
             var witch = (Witch) shooter;
             if (witch.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "BLIGHTED_WITCH"), PersistentDataType.STRING)) {
                 if (entity instanceof ThrownPotion) {
                     var throwpotion = (ThrownPotion) entity;

                     ItemStack s = new ItemStack(Material.SPLASH_POTION);
                     PotionMeta meta = (PotionMeta) s.getItemMeta();
                     meta.addCustomEffect(new PotionEffect(PotionEffectType.WITHER, 500, 4), true);
                     meta.addCustomEffect(new PotionEffect(PotionEffectType.SLOW, 500, 1), true);
                     meta.addCustomEffect(new PotionEffect(PotionEffectType.BLINDNESS, 500, 1), true);
                     meta.addCustomEffect(new PotionEffect(PotionEffectType.HARM, 1, 1), true);
                     s.setItemMeta(meta);

                     throwpotion.setItem(s);
                 }
             }
         }
    }

    @EventHandler
    public void fangqueExplotan(EntitySpawnEvent e){
        if(e.getEntity() instanceof EvokerFangs fangs)
            if(fangs.getOwner() instanceof Evoker evoker){
                if(evoker.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class),"EXPLOSIVE_EVOKER"),PersistentDataType.STRING)){
                    fangs.getLocation().createExplosion(evoker, 3, false, true);
                }
            }
    }
}
