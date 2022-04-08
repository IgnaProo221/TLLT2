package Listeners;

import Extras.Items;
import Utilities.Format;
import Utilities.Mobs;
import Utilities.Utils;
import io.papermc.paper.event.entity.EntityMoveEvent;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import tlldos.tll2.TLL2;

import java.util.Random;

import static Utilities.Format.format;

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
                    if (ironGolem.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "WARDEN"), PersistentDataType.STRING)) {
                        int attacklol = new Random().nextInt(100);
                        if(attacklol == 1){
                            for(Player p : ironGolem.getWorld().getNearbyPlayers(ironGolem.getLocation(),10,10,10)) {
                                if(p == null)return;
                                p.playSound(p.getLocation(),Sound.ENTITY_ENDER_DRAGON_GROWL,SoundCategory.HOSTILE,10.0F, -1.0F);
                                p.sendMessage(Format.PREFIX,Format.format("&3¡El Warden a lanzado un sonido aturdidor!"));
                                p.damage(40,ironGolem);
                                Vector vector = p.getEyeLocation().getDirection().multiply(-3);
                                p.setVelocity(vector);
                            }
                        }else {
                        ironGolem.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1200, 4, false, false, false));
                        ironGolem.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1200, 1, false, false, false));
                    }
                }
            }
        }
    }


    @EventHandler
    public void infernolordAttacks1(ProjectileHitEvent e) {
        var projectile = e.getEntity();
        var shooter = projectile.getShooter();
        var hitentity = e.getHitEntity();
        var hitblock = e.getHitBlock();
        if(projectile instanceof SmallFireball){
            if(shooter instanceof Blaze blaze){
                if(blaze.getPersistentDataContainer().has(Utils.key("INFERNO_LORD"),PersistentDataType.STRING)){
                    int fireballtype = new Random().nextInt(5);
                    if(fireballtype == 1){
                        if(hitentity != null){
                            hitentity.getLocation().createExplosion(blaze,7,false,false);
                            hitentity.setFireTicks(1200);
                        }
                        if(hitblock != null){
                            hitblock.getLocation().createExplosion(blaze,7,false,true);
                        }
                    }else if(fireballtype == 2){
                        if(hitentity != null){
                            hitentity.getWorld().strikeLightning(hitentity.getLocation());
                            hitentity.setFireTicks(1200);
                        }
                        if(hitblock != null){
                            hitblock.getWorld().strikeLightning(hitblock.getLocation());
                        }
                    }else if(fireballtype == 3){
                        if(hitentity != null) {
                            LivingEntity livingEntity = (LivingEntity)hitentity;
                            Vector vector = livingEntity.getEyeLocation().getDirection().multiply(-3);
                            livingEntity.setVelocity(vector);
                            hitentity.setFireTicks(1200);
                        }
                    }else if(fireballtype == 4){
                        if(hitblock != null) {
                            hitblock.setType(Material.MAGMA_BLOCK);
                            hitblock.getWorld().strikeLightning(hitblock.getLocation());
                        }
                    }else{
                        if(hitentity != null){
                            LivingEntity livingEntity = (LivingEntity)hitentity;
                            livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION,200,9,true,false,true));
                            livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.WITHER,200,9,true,false,true));
                            livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,200,9,true,false,true));
                            livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING,200,9,true,false,true));
                            livingEntity.setFireTicks(1200);
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void infernolordAttacks2(EntityDamageByEntityEvent e){
        var damaged = e.getEntity();
        var damager = e.getDamager();
        if(damager instanceof Player player){
            if(damaged instanceof Blaze blaze) {
                if (blaze.getPersistentDataContainer().has(Utils.key("INFERNO_LORD"), PersistentDataType.STRING)) {
                    int attackchance = new Random().nextInt(100);
                    if (attackchance > 95) {
                        int x = new Random().nextInt(7) + 1;
                        int z = new Random().nextInt(7) + 1;
                        int x1 = new Random().nextInt(7) + 1;
                        int z1 = new Random().nextInt(7) + 1;
                        Blaze hell1 = blaze.getLocation().add(x, 0, z).getWorld().spawn(blaze.getLocation(), Blaze.class);
                        Blaze hell2 = blaze.getLocation().add(x1, 0, z1).getWorld().spawn(blaze.getLocation(), Blaze.class);
                        Mobs.hellfire(hell1);
                        Mobs.hellfire(hell2);
                        for(Player p : blaze.getWorld().getNearbyPlayers(blaze.getLocation(),10,10,10)){
                            p.sendMessage(Format.PREFIX,Format.format("&6&lEl Inferno Lord invoca sus Aliados!"));
                        }
                    }else if(attackchance == 1){
                        blaze.getWorld().spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, blaze.getLocation(),1);
                        for(Player p : blaze.getWorld().getNearbyPlayers(blaze.getLocation(),10,10,10)) {
                            if(p == null)return;
                            p.playSound(p.getLocation(),Sound.ENTITY_WITHER_SPAWN,SoundCategory.HOSTILE,10.0F, -1.0F);
                            p.sendMessage(Format.PREFIX,Format.format("&6¡El Inferno Lord deja una Explosion Ignifuga!"));
                            p.damage(60,blaze);
                            p.setFireTicks(1200);
                            Vector vector = p.getEyeLocation().getDirection().multiply(-3);
                            p.setVelocity(vector);
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void infernolordAttacks3(EntityMoveEvent e){
        var entity = e.getEntity();
        if(entity instanceof Blaze blaze){
            if(blaze.getPersistentDataContainer().has(Utils.key("INFERNO_LORD"),PersistentDataType.STRING)){
                if(blaze.getLocation().getBlock().isLiquid()){
                    int aurachance = new Random().nextInt(100);
                    if(aurachance == 1){
                        for(Player p : blaze.getWorld().getNearbyPlayers(blaze.getLocation(),10,10,10)){
                            p.sendMessage(Format.PREFIX,Format.format("&6Un Aura Misteriosa esta haciendote daño!"));
                            p.damage(40,blaze);
                        }
                    }
                }
            }
        }
    }


    public static EndBoss boss;

    public static EndBoss getBoss(){
        return boss;
    }
    public static void setBoss(EndBoss boss) {
        BossesListeners.boss = boss;
    }

    @EventHandler
    public void erebusSpawn(CreatureSpawnEvent e){
        if(e.getEntity() instanceof Wither wither){
            if(wither.getPersistentDataContainer().has(Utils.key("EREBUS"),PersistentDataType.STRING)) {
                if (wither.isValid() && !wither.isDead()) {
                    Bukkit.getConsoleSender().sendMessage("EL MOB SE LE METE EL TASK BIEN");
                    BossesListeners.setBoss(new EndBoss(TLL2.getInstance(), wither));
                    BossesListeners.getBoss().runTaskTimer(TLL2.getInstance(), 0, 400);
                }
            }
        }
    }

    @EventHandler
    public void erebusskulls(ProjectileHitEvent e){
        var shooter = e.getEntity().getShooter();
        var projectile = e.getEntity();
        var hitentity = e.getHitEntity();
        var hitblock = e.getHitBlock();
        if(shooter instanceof Wither wither){
            if(projectile instanceof WitherSkull witherSkull){
                if(wither.getPersistentDataContainer().has(Utils.key("EREBUS"),PersistentDataType.STRING)){
                    int attacklol = new Random().nextInt(3);
                    if(attacklol == 1){
                        if(hitentity != null){
                            hitentity.getWorld().createExplosion(hitentity.getLocation(),8,false,true);
                        }else if(hitblock != null){
                            hitblock.getWorld().createExplosion(hitblock.getLocation(),8,false,true);
                        }
                    }else if(attacklol == 2){
                        witherSkull.setCharged(true);
                        if(hitentity != null){
                            SpawnListeners.spawnRandomMob(hitentity.getLocation());
                        }else if(hitblock != null){
                            SpawnListeners.spawnRandomMob(hitblock.getLocation());
                        }
                    }else{
                        witherSkull.setCharged(true);
                        if(hitentity != null){
                            AreaEffectCloud toxic = hitentity.getWorld().spawn(hitentity.getLocation(),AreaEffectCloud.class);
                            toxic.setParticle(Particle.CAMPFIRE_SIGNAL_SMOKE);
                            toxic.setRadius(5);
                            toxic.setBasePotionData(new PotionData(PotionType.POISON));
                            toxic.addCustomEffect(new PotionEffect(PotionEffectType.WITHER, 200, 3), true);
                            toxic.addCustomEffect(new PotionEffect(PotionEffectType.WEAKNESS, 200, 3), true);
                            toxic.addCustomEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 200, 3), true);
                        }else if(hitblock != null){
                            AreaEffectCloud toxic = hitblock.getWorld().spawn(hitblock.getLocation(),AreaEffectCloud.class);
                            toxic.setParticle(Particle.CAMPFIRE_SIGNAL_SMOKE);
                            toxic.setRadius(5);
                            toxic.setBasePotionData(new PotionData(PotionType.POISON));
                            toxic.addCustomEffect(new PotionEffect(PotionEffectType.WITHER, 200, 3), true);
                            toxic.addCustomEffect(new PotionEffect(PotionEffectType.WEAKNESS, 200, 3), true);
                            toxic.addCustomEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 200, 3), true);
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void erebusProtection(EntityDamageByEntityEvent e){
        var damaged = e.getEntity();
        var damager = e.getDamager();
        if(damager instanceof Player p){
            if(damaged instanceof Wither wither){
                if(wither.getPersistentDataContainer().has(Utils.key("EREBUS"),PersistentDataType.STRING)){
                    if(p.getInventory().getItemInMainHand() != null && p.getInventory().getItemInMainHand().getType() == Material.POPPY){
                        e.setCancelled(true);
                        p.sendMessage(format("&7&l[&8&lEREBUS&7&l] &4&l> &c&l¿Intentas Oneshotearme? ¡PIENSALO OTRA VEZ!"));
                        p.damage(9999,wither);
                    }else{
                        int randomlol = new Random().nextInt(100);
                        if(randomlol > 90){
                            p.sendMessage(format("&7&l[&8&lEREBUS&7&l] &4&l> &c&l¿Me Intentas Atacar " + p.getName() + "? ¡Haber si Sobrevivies a esto!"));
                            wither.getWorld().createExplosion(wither.getLocation(),6,true,false);
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void erebusDeath(EntityDeathEvent e){
        var entity = e.getEntity();
        if(entity instanceof Wither wither){
            if(wither.getPersistentDataContainer().has(Utils.key("EREBUS"),PersistentDataType.STRING)){
                EndBoss.isDead = true;
                for(Player player : Bukkit.getOnlinePlayers()){
                    player.sendMessage(format("&7&l[&8&lEREBUS&7&l] &4&l> &7..."));
                    Bukkit.getScheduler().runTaskLater(instance,()->{
                        player.sendMessage(format("&7&l[&8&lEREBUS&7&l] &4&l> &7Al parecer..."));
                    },80L);
                    Bukkit.getScheduler().runTaskLater(instance,()->{
                        player.sendMessage(format("&7&l[&8&lEREBUS&7&l] &4&l> &7No soy lo suficientemente fuerte para ustedes..."));
                    },160L);
                    Bukkit.getScheduler().runTaskLater(instance,()->{
                        player.sendMessage(format("&7&l[&8&lEREBUS&7&l] &4&l> &7Supongo que es mi final..."));
                    },240L);
                    Bukkit.getScheduler().runTaskLater(instance,()->{
                        player.sendMessage(format("&7&l[&8&lEREBUS&7&l] &4&l> &7Pero antes..."));
                    },320L);
                    Bukkit.getScheduler().runTaskLater(instance,()->{
                        player.sendMessage(format("&7&l[&8&lEREBUS&7&l] &4&l> &7Su regreso a casa sera imposible"));
                    },400L);
                    Bukkit.getScheduler().runTaskLater(instance,()->{
                        player.sendMessage(format("&7&l[&8&lEREBUS&7&l] &4&l> &7Estan atrapados conmigo aquí, en un vacio infinito del que jamas podrán escapar"));
                    },480L);
                    Bukkit.getScheduler().runTaskLater(instance,()->{
                        player.sendMessage(format("&7&l[&8&lEREBUS&7&l] &4&l> &7..."));
                    },560L);
                    Bukkit.getScheduler().runTaskLater(instance,()->{
                        player.sendMessage(format("&7&l[&8&lEREBUS&7&l] &4&l> &7Hasta Nunca."));
                    },640L);
                }
            }
        }
    }

    @EventHandler
    public void soulsDeath(EntityDeathEvent e){
        if(e.getEntity() instanceof Skeleton skeleton){
            Player arphz = Bukkit.getPlayer("TheArphz");
            Player salva = Bukkit.getPlayer("SalvaGamerVZ");
            Player johan = Bukkit.getPlayer("JohanBigCum");
            Player diego = Bukkit.getPlayer("Diegot_Manc");
            Player gatin = Bukkit.getPlayer("Gatin72");
            if(skeleton.getPersistentDataContainer().has(Utils.key("ARPHZ"),PersistentDataType.STRING)){
                if(arphz == null){
                    Bukkit.getConsoleSender().sendMessage("ARPHZ A MUERTO PERO NO ESTABA EN EL SERVER");
                }else{
                    arphz.teleport(skeleton.getLocation());
                    soulRespawnLol(arphz);
                }
            }
            if(skeleton.getPersistentDataContainer().has(Utils.key("SALVA"),PersistentDataType.STRING)){
                if(salva == null){
                    Bukkit.getConsoleSender().sendMessage("SALVA A MUERTO PERO NO ESTABA EN EL SERVER");
                }else{
                    salva.teleport(skeleton.getLocation());
                    soulRespawnLol(salva);
                }
            }
            if(skeleton.getPersistentDataContainer().has(Utils.key("DIEGO"),PersistentDataType.STRING)){
                if(diego == null){
                    Bukkit.getConsoleSender().sendMessage("DIEGOT A MUERTO PERO NO ESTABA EN EL SERVER");
                }else{
                    diego.teleport(skeleton.getLocation());
                    soulRespawnLol(diego);
                }
            }
            if(skeleton.getPersistentDataContainer().has(Utils.key("GATIN"),PersistentDataType.STRING)){
                if(gatin == null){
                    Bukkit.getConsoleSender().sendMessage("GATIN A MUERTO PERO NO ESTABA EN EL SERVER");
                }else{
                    gatin.teleport(skeleton.getLocation());
                    soulRespawnLol(gatin);
                }
            }
            if(skeleton.getPersistentDataContainer().has(Utils.key("JOHAN"),PersistentDataType.STRING)){
                if(johan == null){
                    Bukkit.getConsoleSender().sendMessage("JOHAN A MUERTO PERO NO ESTABA EN EL SERVER");
                }else{
                    johan.teleport(skeleton.getLocation());
                    soulRespawnLol(johan);
                }
            }
        }
    }

    public void soulRespawnLol(Player player){
        player.setGameMode(GameMode.SURVIVAL);
        player.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(20);
        player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(13);
        player.getInventory().setItem(0, Items.nanoTech());
        player.getInventory().setItem(1, Items.umbraDrill());
        player.getInventory().setItem(2, Items.touchofdarkness());
        player.getInventory().setItem(3, new ItemStack(Material.ENCHANTED_GOLDEN_APPLE,64));
        player.getInventory().setItem(4,Items.shadowRupture());
        player.getInventory().setItem(5, Items.unluckyIdol());
        player.getInventory().setItem(6, Items.shadowTotem());
        player.getInventory().setItem(7, Items.undyingStaff());
        player.getInventory().setItem(8, Items.totemRestorer());
        player.getInventory().setItem(9, Items.unluckyIdol());
        player.getInventory().setItem(10, Items.shadowTotem());
        player.getInventory().setItem(11, new ItemStack(Material.TOTEM_OF_UNDYING));
        player.getInventory().setItem(12, new ItemStack(Material.TOTEM_OF_UNDYING));
        player.getInventory().setItem(13, new ItemStack(Material.TOTEM_OF_UNDYING));
        player.getInventory().setItem(14, new ItemStack(Material.TOTEM_OF_UNDYING));
        player.getInventory().setItem(15, new ItemStack(Material.TOTEM_OF_UNDYING));
        player.getInventory().setItem(16, new ItemStack(Material.TOTEM_OF_UNDYING));
        player.getInventory().setItem(17, new ItemStack(Material.ARROW,64));
        player.getInventory().setItem(18, Items.unluckyIdol());
        player.getInventory().setItem(19, Items.shadowTotem());
        player.getInventory().setItem(20, new ItemStack(Material.TOTEM_OF_UNDYING));
        player.getInventory().setItem(21, new ItemStack(Material.TOTEM_OF_UNDYING));
        player.getInventory().setItem(22, new ItemStack(Material.TOTEM_OF_UNDYING));
        player.getInventory().setItem(23, new ItemStack(Material.TOTEM_OF_UNDYING));
        player.getInventory().setItem(24, new ItemStack(Material.TOTEM_OF_UNDYING));
        player.getInventory().setItem(25, new ItemStack(Material.TOTEM_OF_UNDYING));
        player.getInventory().setItem(26, new ItemStack(Material.COBBLESTONE,64));
        player.getInventory().setItem(27, Items.unluckyIdol());
        player.getInventory().setItem(28, Items.shadowTotem());
        player.getInventory().setItem(29, new ItemStack(Material.ENDER_PEARL,16));
        player.getInventory().setItem(30, Items.crystalApple(64));
        player.getInventory().setItem(31, Items.fireHook());
        player.getInventory().setItem(32, Items.umbraShell());
        player.getInventory().setItem(33, new ItemStack(Material.TOTEM_OF_UNDYING));
        player.getInventory().setItem(34, new ItemStack(Material.TOTEM_OF_UNDYING));
        player.getInventory().setItem(35, new ItemStack(Material.TOTEM_OF_UNDYING));
        player.getInventory().setItem(103, Items.burnHelmet());
        player.getInventory().setItem(102, Items.burnChestplate());
        player.getInventory().setItem(101, Items.burnLeggings());
        player.getInventory().setItem(100, Items.burnBoots());
        player.getInventory().setItem(-106, new ItemStack(Material.TOTEM_OF_UNDYING));
        player.sendTitle(format("&c&l¡HAS REVIVIDO!"),format("&4&l¡AYUDA A LOS DEMAS A SOBREVIVIR!"));
    }





}
