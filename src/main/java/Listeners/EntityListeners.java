package Listeners;

import Utilities.*;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
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
import static Utilities.Format.PREFIX;
import static Utilities.Format.format;

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

    public void cosmosMobs(Entity entity){
        int cosmoschance = new Random().nextInt(12);
        if(cosmoschance == 1){
            var vortilol = entity.getLocation().getWorld().spawn(entity.getLocation(), Creeper.class);
            Mobs.vortice(vortilol);
        }else if(cosmoschance == 2){
            var evokerxdlol = entity.getLocation().getWorld().spawn(entity.getLocation(), Evoker.class);
            Mobs.evokerExplosive(evokerxdlol);
        }else if(cosmoschance == 3){
            var withertroll = entity.getLocation().getWorld().spawn(entity.getLocation(), Wither.class);
            Mobs.tyranyWither(withertroll);
        }else if(cosmoschance == 4){
            var esqueletomamado = entity.getLocation().getWorld().spawn(entity.getLocation(), WitherSkeleton.class);
            Mobs.blightedWitherSkeleton(esqueletomamado);
        }else if(cosmoschance == 5){
            var jodidoghast = entity.getLocation().getWorld().spawn(entity.getLocation(),Ghast.class);
            Mobs.riftedGhast(jodidoghast);
        }else if(cosmoschance == 6){
            var zombipendejo = entity.getLocation().getWorld().spawn(entity.getLocation(), Zombie.class);
            Mobs.blightedZombi(zombipendejo);
        }else if(cosmoschance == 7){
            var brujapendeja = entity.getLocation().getWorld().spawn(entity.getLocation(), Witch.class);
            Mobs.blightedWitch(brujapendeja);
        }else if(cosmoschance == 8){
            var blaze = entity.getLocation().getWorld().spawn(entity.getLocation(), Blaze.class);
            blaze.setCustomName(format("&cHellfire"));
            blaze.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40.0);
            blaze.setHealth(40);
            blaze.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "HELLFIRE"), PersistentDataType.STRING, "HELLFIRE");
        }else  if(cosmoschance == 9){
            var slime = entity.getLocation().getWorld().spawn(entity.getLocation(), Slime.class);
            slime.setCustomName(format("Freezing Slime"));
            slime.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(60);
            slime.setHealth(60);
            slime.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(12);
            slime.setSize(12);
            slime.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "FREEZING_SLIME"), PersistentDataType.STRING, "FREEZING_SLIME");
        }else if(cosmoschance == 10){
            var magopendejo = entity.getLocation().getWorld().spawn(entity.getLocation(), Illusioner.class);
            Mobs.riftedMage(magopendejo);
        }else if(cosmoschance == 11){
            var esqueletorarito = entity.getLocation().getWorld().spawn(entity.getLocation(), Skeleton.class);
            Mobs.blightedSkeleton(esqueletorarito);
        }else{
            var aranaranaaaa = entity.getLocation().getWorld().spawn(entity.getLocation(), Spider.class);
            Mobs.blightedSpider(aranaranaaaa);
        }
    }

    public void interEffects(Player p) {
        int effect = new Random().nextInt(3);
        if (effect == 1) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 200, 4, true, true, true));
        } else if (effect == 2) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 2, true, true, true));
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
    public void witherTest(ExplosionPrimeEvent e){
        if(e.getEntity() instanceof Wither wither){
            if(wither.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "TYRANT_WITHER"), PersistentDataType.STRING)) {
                e.setRadius(20);
            }
        }
    }

    @EventHandler
    public void damageEntity(EntityDamageByEntityEvent event) {

        Random random = new Random();
        int lifestealpercentage = random.nextInt(100);
        var entity = event.getEntity();
        var damager = event.getDamager();
        if(damager instanceof Player pa){

            //if(!(pa.getInventory().getItemInMainHand().getItemMeta() == null))return;
            //if(!(pa.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()))return;
            if(pa.getInventory().getItemInMainHand() != null){
                if(pa.getInventory().getItemInMainHand().hasItemMeta()){
                    if (pa.getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.REVENGE)) {
                        if(entity instanceof Pillager || entity instanceof Vindicator || entity instanceof Evoker || entity instanceof Illusioner || entity instanceof Witch || entity instanceof Vex || entity instanceof Ravager){
                            event.setDamage(event.getDamage() * 1.25);
                        }
                    } else if (pa.getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.CRITICAL_HIT)){
                        int criticalchance = new Random().nextInt(100);
                        if(criticalchance >= 95){
                            event.setDamage(event.getDamage() * 2);
                            pa.playSound(pa.getLocation(),Sound.ITEM_TRIDENT_THROW, 10.0F, 2.0F);
                            pa.sendMessage(PREFIX,format("&c¡Tu Espada a hecho un Critico! &7(Total de " + event.getDamage() + " de Daño)"));
                        }
                    }
            if(pa.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                if (pa.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 4006) {
                    entity.setFreezeTicks(400);
                } else if (pa.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 4010) {
                    pa.setHealth(pa.getHealth() / 0.5);
                    }else if(pa.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 18129){
                    Monster monster = (Monster) entity;
                    monster.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 200,1, false, false, false));
                }
                }
            }
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
            if (damager instanceof Vex vex) {
                if(p.isBlocking())return;
                if(vex.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class),"CINDER"), PersistentDataType.STRING)){
                    p.setFireTicks(1200);
                }
                if(vex.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class),"JENGU"), PersistentDataType.STRING)){
                    p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 300, 1, true, false, true));
                }
                if(vex.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class),"DJIIN"), PersistentDataType.STRING)){
                    p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 400, 1, true, false, true));
                }
                if(vex.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class),"GRUE"), PersistentDataType.STRING)){
                    p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 400, 0, true, false, true));
                }
            }
            if(damager instanceof WitherSkeleton){
                if(p.isBlocking())return;
                var witherskeleton = (WitherSkeleton)damager;
                if(witherskeleton.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "SHATTER_GUARDIAN"), PersistentDataType.STRING)){
                    p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 2400, 3,true, true, true));
                }
            }
            if(damager instanceof IronGolem ironGolem){
                if(p.isBlocking())return;
                if(ironGolem.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class),"LAVA_GOLEM"),PersistentDataType.STRING)){
                    p.setFireTicks(1200);
                    p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS,200,0, true, false, true));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,200,0, true, false, true));
                }
            }
            if(damager instanceof Spider){
                var spider = (Spider)damager;
                if(p.isBlocking())return;
                if(spider.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "AGILE_SPIDER"), PersistentDataType.STRING)){
                    p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 200, 2,true, true, true));
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
                if(p.isBlocking())return;
                if(slime.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "FREEZING_SLIME"), PersistentDataType.STRING)){
                    p.setFreezeTicks(1200);
                }
            }
            if(damager instanceof Zombie){
                var zombie = (Zombie)damager;
                if(p.isBlocking())return;
                if(zombie.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "BLIGHTED_ZOMBIE"), PersistentDataType.STRING)){
                    blightedZombieffects(p);
                }
            }
            if(damager instanceof Enderman){
                var enderman = (Enderman)damager;
                if(p.isBlocking())return;
                if(enderman.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "BLIGHTED_ENDERMAN"), PersistentDataType.STRING)){
                    p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 400, 0, true, true, true));
                }
            }
            if(damager instanceof Phantom){
                var phantom = (Phantom)damager;
                if(phantom.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "BLIGHTED_PHANTOM"), PersistentDataType.STRING)){
                    if(p.isBlocking())return;
                    p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 100,9, true,false,true));
                    }
            }
            if(damager instanceof Vindicator vindicator){
                if(vindicator.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class),"THIEF"),PersistentDataType.STRING)){
                    var player = (Player) entity;
                    var inv = player.getInventory().getContents();
                    var pos = random.nextInt(inv.length);
                    var item = inv[pos];

                    if(item != null){
                        var drop = item.clone();
                        player.getInventory().setItem(pos, null);
                        player.getWorld().dropItemNaturally(player.getLocation().add(0, 5, 0), drop);
                    }
                }
            }
            if(damager instanceof Villager villager){
                if(villager.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class),"VILLAGER_TERRORISTA"),PersistentDataType.STRING)){
                    var player = (Player)entity;
                    player.getLocation().getWorld().createExplosion(villager,8,false,true);
                    villager.damage(1000);
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

        if(shooter instanceof Wither wither) {
            if (projectile instanceof WitherSkull) {
                if (hitblock != null) {
                    hitblock.getLocation().createExplosion(wither, 3, false, true);
                } else if (entity != null) {
                    entity.getLocation().createExplosion(wither, 3, false, true);
                }
            }
        }else{
            if (projectile instanceof WitherSkull witherSkull) {
                if (hitblock != null) {
                    hitblock.getLocation().createExplosion(witherSkull, 2, false, true);
                } else if (entity != null) {
                    entity.getLocation().createExplosion(witherSkull, 2, false, true);
                }
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
        if(e.blockList() != null){
            e.blockList().forEach(block -> {
                if(block.getType() == Material.OBSIDIAN){
                    block.breakNaturally();
                }
            });
        }
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
            if(creeper.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "COSMOS_CALAMITY"), PersistentDataType.STRING)){
                Bukkit.getScheduler().runTaskLater(plugin, () ->{
                    var cos1 = e.getLocation().getWorld().spawn(e.getLocation(), Silverfish.class);
                    var cos2 = e.getLocation().getWorld().spawn(e.getLocation(), Silverfish.class);
                    var cos3 = e.getLocation().getWorld().spawn(e.getLocation(), Silverfish.class);
                    Mobs.cosmicSilver(cos1);
                    Mobs.cosmicSilver(cos2);
                    Mobs.cosmicSilver(cos3);
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
        if(e instanceof Silverfish silverfish){
            if(silverfish.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "COSMIC_SILVERFISH"), PersistentDataType.STRING)){
                cosmosMobs(silverfish);
                Bukkit.getOnlinePlayers().forEach(player -> {
                    player.sendMessage(Format.PREFIX + format("&c&l!El Cosmos ha invocado un mob aleatorio en X: " + e.getLocation().getBlockX() + " Y: " +e.getLocation().getBlockY() +" Z: "+ e.getLocation().getBlockZ() +"!"));
                });
            }
        }
        if(e instanceof IronGolem ironGolem){
            if(ironGolem.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class),"LAVA_GOLEM"),PersistentDataType.STRING)){
                ironGolem.getLocation().getBlock().setType(Material.LAVA);
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
            p.sendMessage(format("&7¡Has sacrificado a un &6&lAldeano&7, has recibido un &6&l" + size + " &cFragmento(s) de Sangre&7!"));
        }
    }

    @EventHandler
    public void noZombPig(EntityTransformEvent e) {
        if (e.getTransformReason() == EntityTransformEvent.TransformReason.PIGLIN_ZOMBIFIED) {
            try {
                var piglin = (Piglin) e.getEntity();

                ItemStack[] contents = piglin.getEquipment().getArmorContents().clone();

                Bukkit.getScheduler().runTaskLater(plugin, () -> piglin.getEquipment().setArmorContents(contents)
                        , 10L);
            } catch (NullPointerException x) {
                x.printStackTrace();
                Warn.Mutant(x);
            }
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
    @EventHandler
    public void escudoHabiliadd(EntityDamageByEntityEvent e){
        var entity = e.getEntity();
        var damager = e.getDamager();
        if(entity instanceof Player player) {
            if (player.isBlocking()) {
                if (player.getInventory().getItemInMainHand() != null || player.getInventory().getItemInOffHand() != null) {
                    if (player.getInventory().getItemInMainHand().hasItemMeta() || player.getInventory().getItemInOffHand().hasItemMeta()) {
                        if (player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() || player.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData()) {
                            if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 5015 || player.getInventory().getItemInOffHand().getItemMeta().getCustomModelData() == 5015) {
                                if (damager instanceof Monster monster) {
                                    monster.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 99, false, false, false));
                                    monster.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 200, 0, false, false, false));
                                }
                                if(e.getCause() == EntityDamageEvent.DamageCause.LIGHTNING){
                                    e.setDamage(0);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void arcoscustoms1(EntityShootBowEvent e){
        var bow = e.getBow().getItemMeta();
        if(!bow.hasCustomModelData()){
            return;
        }
        if(bow.getCustomModelData() == 389909){
            e.getProjectile().setCustomName("explosive");
        }else if(bow.getCustomModelData() == 5080){
            e.getProjectile().setCustomName("ice");
        }else if(bow.getCustomModelData() == 27289){
            e.getProjectile().setCustomName("exo_proj");
        }
    }

    @EventHandler
    public void arcoAaaamemuero(ProjectileHitEvent e){
        var block = e.getHitBlock();
        var damaged = e.getHitEntity();
        var shooter = e.getEntity().getShooter();
        var projectile = e.getEntity();
        if(shooter instanceof Player){
            if(projectile instanceof Arrow){
                if(projectile.getCustomName() != null){
                    if(projectile.getCustomName().contains("explosive")){
                        if(damaged != null){
                            damaged.getLocation().createExplosion(projectile,3,false, true);
                        }else if(block != null){
                            block.getLocation().createExplosion(projectile,3,false,true);
                        }
                    }else if(projectile.getCustomName().contains("ice")){
                        if(damaged != null){
                            damaged.setFreezeTicks(1200);
                        }
                    }else if(projectile.getCustomName().contains("exp_proj")){
                        if(damaged != null){
                            LivingEntity livingEntity = (LivingEntity) damaged;
                            livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS,300,1,false, true, false));
                        }
                    }
                }
            }
        }
    }
}
