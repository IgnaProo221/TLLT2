package Listeners;

import CustomMobs.Argus;
import CustomMobs.JineteZ;
import Utilities.ItemBuilder;
import Utilities.Mobs;
import Utilities.Utils;
import Utilities.Warn;
import net.minecraft.server.level.WorldServer;
import net.minecraft.world.entity.EntityInsentient;
import net.minecraft.world.entity.ai.goal.PathfinderGoalMeleeAttack;
import net.minecraft.world.entity.ai.goal.PathfinderGoalSelector;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalNearestAttackableTarget;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.monster.EntityPigZombie;
import net.minecraft.world.entity.player.EntityHuman;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Biome;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.entity.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import tlldos.tll2.TLL2;

import static Utilities.Format.format;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class SpawnListeners implements Listener {
    private TLL2 plugin;

    public SpawnListeners(TLL2 plugin) {
        this.plugin = plugin;
    }
    public void setCustomMobcap(LivingEntity entity, int maxPerDistance, double multiplier, int distance, int maxPerWorld, boolean withSameName) {
        ArrayList<LivingEntity> nearbyEntities = new ArrayList<>();
        maxPerDistance *= multiplier;
        maxPerWorld *= multiplier;
        if(withSameName){
            for (LivingEntity nearbyEntity : entity.getLocation().getNearbyEntitiesByType(entity.getClass() ,distance, distance, distance)) {

                if (nearbyEntity.getName().equalsIgnoreCase(entity.getName())) nearbyEntities.add(nearbyEntity);
            }
            if(nearbyEntities.size() >= maxPerDistance || entity.getWorld().getEntitiesByClass(entity.getClass()).size() > maxPerWorld) {
                entity.remove();
            }
        }else {
            for (LivingEntity nearbyEntity : entity.getLocation().getNearbyEntitiesByType(entity.getClass(), distance, distance, distance)) {
                nearbyEntities.add(nearbyEntity);
            }
            if (nearbyEntities.size() >= maxPerDistance || entity.getWorld().getEntitiesByClass(entity.getClass()).size() > maxPerWorld) {
                entity.remove();
            }
        }
    }


    @EventHandler
    public void spawnMob(CreatureSpawnEvent e) {
        Random random = new Random();
        int spawnmob = random.nextInt(100);
        var entity = e.getEntity();
        var pos = e.getLocation();
        var world = Bukkit.getWorld("world");
        var entitybiome = entity.getLocation().getBlock().getBiome();
        int chanceeffect = new Random().nextInt(100);
        if(Utils.getWorld().isThundering()) {
            if (chanceeffect > 80) {
                entity.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 1, false, false, false));
                entity.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0, false, false, false));
            }
        }
        if (entitybiome == Biome.SNOWY_TUNDRA || entitybiome == Biome.SNOWY_BEACH || entitybiome == Biome.SNOWY_TAIGA || entitybiome == Biome.SNOWY_MOUNTAINS || entitybiome == Biome.SNOWY_TAIGA_HILLS || entitybiome == Biome.SNOWY_TAIGA_MOUNTAINS
                || entitybiome == Biome.ICE_SPIKES) {
            if (entity instanceof Zombie zombie && !(entity instanceof Drowned) && !(entity instanceof Husk) && !(entity instanceof ZombieVillager) && !(entity instanceof PigZombie) && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL) {
                Mobs.zombiCongelado(zombie);
            } else if (entity instanceof Skeleton skeleton && !(entity instanceof Stray) && !(entity instanceof WitherSkeleton) && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL) {
                Mobs.esqueletoNieve(skeleton);
            } else if (entity instanceof Spider spider && !(entity instanceof CaveSpider) && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL) {
                Mobs.spiderNieve(spider);
            } else if (entity instanceof Creeper creeper && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL) {
                Mobs.creeperCongelado(creeper);
            }
        } else if (entitybiome == Biome.JUNGLE || entitybiome == Biome.JUNGLE_EDGE || entitybiome == Biome.JUNGLE_HILLS || entitybiome == Biome.BAMBOO_JUNGLE_HILLS || entitybiome == Biome.BAMBOO_JUNGLE
                || entitybiome == Biome.MODIFIED_JUNGLE || entitybiome == Biome.MODIFIED_JUNGLE_EDGE) {
            if (entity instanceof Zombie zombie && !(entity instanceof Drowned) && !(entity instanceof Husk) && !(entity instanceof ZombieVillager) && !(entity instanceof PigZombie) && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL) {
                Mobs.mossZombie(zombie);
            } else if (entity instanceof Skeleton skeleton && !(entity instanceof Stray) && !(entity instanceof WitherSkeleton) && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL) {
                Mobs.esqueletoMilitar(skeleton);
            } else if (entity instanceof Spider spider && !(entity instanceof CaveSpider) && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL) {
                Mobs.spiderJungla(spider);
            } else if (entity instanceof Creeper creeper && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL) {
                Mobs.mossCreeper(creeper);
            }
        } else if (entitybiome == Biome.DESERT || entitybiome == Biome.DESERT_HILLS || entitybiome == Biome.DESERT_LAKES || entitybiome == Biome.BADLANDS || entitybiome == Biome.BADLANDS_PLATEAU
                || entitybiome == Biome.ERODED_BADLANDS || entitybiome == Biome.MODIFIED_BADLANDS_PLATEAU || entitybiome == Biome.MODIFIED_WOODED_BADLANDS_PLATEAU || entitybiome == Biome.WOODED_BADLANDS_PLATEAU) {
            if (entity instanceof Zombie zombie && !(entity instanceof Drowned) && !(entity instanceof Husk) && !(entity instanceof ZombieVillager) && !(entity instanceof PigZombie) && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL) {
                Mobs.zombiMomia(zombie);
            } else if (entity instanceof Skeleton skeleton && !(entity instanceof Stray) && !(entity instanceof WitherSkeleton) && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL) {
                Mobs.esqueletoMomia(skeleton);
            } else if (entity instanceof Spider spider && !(entity instanceof CaveSpider) && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL) {
                Mobs.spiderArena(spider);
            } else if (entity instanceof Creeper creeper && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL) {
                Mobs.creeperSandstone(creeper);
            }
        }else if (entity instanceof Skeleton skeleton && !(entity instanceof Stray) && !(entity instanceof WitherSkeleton) && (e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL)) {
            if (spawnmob > 80) {
                Mobs.blightedSkeleton(skeleton);
                skeleton.getEquipment().setDropChance(EquipmentSlot.HAND, 0);
            }else if(spawnmob > 50 && entitybiome == Biome.PLAINS){
                Mobs.roboSkele(skeleton);
                skeleton.getEquipment().setDropChance(EquipmentSlot.HAND, 0);
            } else {
                int skeletontype = new Random().nextInt(6);
                if(skeletontype == 1){
                    skeleton.remove();
                    var wither = skeleton.getWorld().spawn(skeleton.getLocation(),WitherSkeleton.class);
                    Mobs.poweredSkeleton(wither);
                }else if(skeletontype == 2){
                    Mobs.bullseyeSkeleton(skeleton);
                }else if(skeletontype == 3){
                    Mobs.copperSkeleton(skeleton);
                }else if(skeletontype == 4){
                    Mobs.ignitedSkeleton(skeleton);
                }else if(skeletontype == 5){
                    Mobs.blizzardSkeleton(skeleton);
                }else{
                    skeleton.remove();
                    var wither = skeleton.getWorld().spawn(skeleton.getLocation(),WitherSkeleton.class);
                    Mobs.abomination(wither);
                }
            }
        } else if (entity instanceof Spider spider && !(entity instanceof CaveSpider) && (e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL)) {
            if (spawnmob > 80) {
                Mobs.blightedSpider(spider);
            }else if(spawnmob > 50 && entitybiome == Biome.PLAINS){
                Mobs.roboSpider(spider);
            } else {
                int spidertype = new Random().nextInt(3) + 1;
                int jockeychance = new Random().nextInt(100);
                if (spidertype == 1) {
                    Mobs.agileTarantule(spider);
                } else if (spidertype == 2) {
                    Mobs.interdimensionalVisitor(spider);
                } else {
                    Mobs.solarScorpion(spider);
                }

                if (jockeychance > 70) {
                    var skeletonpass = spider.getLocation().getWorld().spawn(spider.getLocation(), Skeleton.class);
                    spider.setPassenger(skeletonpass);
                    int bow = new Random().nextInt(4);
                    if (bow == 1) {
                        Mobs.ignitedSkeleton(skeletonpass);
                    }else if(bow == 2){
                        Mobs.blizzardSkeleton(skeletonpass);
                    }else if(bow == 3){
                        Mobs.copperSkeleton(skeletonpass);
                    }else{
                        Mobs.bullseyeSkeleton(skeletonpass);
                    }
                }

            }
        } else if (entity instanceof Zombie zombie && !(entity instanceof ZombieVillager) && !(entity instanceof Husk) && !(entity instanceof Drowned) && !(entity instanceof PigZombie) && (e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL)) {
            if (spawnmob > 80) {
                Mobs.blightedZombi(zombie);
            } else if (spawnmob > 50 && entitybiome == Biome.PLAINS) {
                Mobs.roboZombi(zombie);
            } else if (zombie.getLocation().getY() < 0) {
                int wardenchance = new Random().nextInt(100);
                if (wardenchance == 1) {
                    zombie.remove();
                    Mobs.Warden(zombie.getLocation().getWorld().spawn(zombie.getLocation(), IronGolem.class));
                }
            } else {
                int zombitype = new Random().nextInt(6) + 1;
                if (zombitype == 1) {
                    Mobs.zombBox(zombie);
                } else if (zombitype == 2) {
                    Mobs.zombCarni(zombie);
                } else if (zombitype == 3) {
                    Mobs.zombHerrero(zombie);
                } else if (zombitype == 4) {
                    Mobs.zombiJinete(zombie);
                } else if (zombitype == 5) {
                    IronGolem ironGolem = zombie.getLocation().getWorld().spawn(zombie.getLocation(), IronGolem.class);
                    Mobs.zombObeso(ironGolem);
                } else {
                    zombie.setBaby(true);
                    zombie.getEquipment().setHelmet(new ItemBuilder(Material.DIAMOND_HELMET).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).build());
                    zombie.getEquipment().setChestplate(new ItemBuilder(Material.DIAMOND_CHESTPLATE).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).build());
                    zombie.getEquipment().setLeggings(new ItemBuilder(Material.DIAMOND_LEGGINGS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).build());
                    zombie.getEquipment().setBoots(new ItemBuilder(Material.DIAMOND_BOOTS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).build());
                    zombie.getEquipment().setItemInMainHand(new ItemBuilder(Material.DIAMOND_SWORD).addEnchantment(Enchantment.DAMAGE_ALL, 20).build());
                    zombie.getEquipment().setDropChance(EquipmentSlot.HEAD, 0);
                    zombie.getEquipment().setDropChance(EquipmentSlot.CHEST, 0);
                    zombie.getEquipment().setDropChance(EquipmentSlot.LEGS, 0);
                    zombie.getEquipment().setDropChance(EquipmentSlot.FEET, 0);
                    zombie.getEquipment().setDropChance(EquipmentSlot.HAND, 0);
                }
            }
        }else  if(entity instanceof Bat bat && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL) {
            if (bat.getWorld().isThundering()) {
                int mobcaptry = new Random().nextInt(1000);
                bat.remove();
                if(mobcaptry == 1) {
                    Blaze blaze = bat.getWorld().spawn(bat.getLocation(), Blaze.class);
                    Mobs.hellfire(blaze);
                }
            }
        }else if(entity instanceof Piglin piglin && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL){
            piglin.setCustomName(format("&b&lFrancotirador"));
            piglin.getEquipment().setItemInMainHand(new ItemBuilder(Material.CROSSBOW).addEnchantment(Enchantment.PIERCING,10).setUnbreakable(true).build());
            piglin.getEquipment().setDropChance(EquipmentSlot.HAND,0);
            piglin.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class),"FRANCO"),PersistentDataType.STRING,"FRANCO");
        }else if(entity instanceof CaveSpider caveSpider && !(entity instanceof Spider)){
            caveSpider.setCustomName(format("&6Mutacion"));
            caveSpider.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class),"MUTACION"),PersistentDataType.STRING,"MUTACION");
            //mutanteo exitoso
        }else if(entity instanceof PigZombie pigZombie && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL){
            CraftPigZombie craft = ((CraftPigZombie) pigZombie);
            EntityPigZombie entityPigZombie = craft.getHandle();
            try{
                Class<? extends EntityInsentient> cl = EntityInsentient.class;
                Field gf = cl.getDeclaredField("bP");
                gf.setAccessible(true);
                PathfinderGoalSelector goal = (PathfinderGoalSelector) gf.get(entityPigZombie);
                goal.a(0, new PathfinderGoalMeleeAttack(entityPigZombie,1.0D,true));

                Field tf = cl.getDeclaredField("bQ");
                tf.setAccessible(true);

                PathfinderGoalSelector target = (PathfinderGoalSelector) tf.get(entityPigZombie);
                target.a(0,new PathfinderGoalNearestAttackableTarget<>(entityPigZombie, EntityHuman.class, 10,true,false,null));
            }catch (Exception ec){
                ec.printStackTrace();
                Warn.Mutant(ec);
            }
            int chancedebrute = new Random().nextInt(100);
            if(chancedebrute >= 90){
                pigZombie.remove();
                PiglinBrute piglinBrute = pigZombie.getWorld().spawn(pigZombie.getLocation(),PiglinBrute.class);
                piglinBrute.setRemoveWhenFarAway(true);
            }

        }else if(entity instanceof Blaze blaze){
            Mobs.hellfire(blaze);
        } else if (entity instanceof Creeper creeper && (e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL || e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.CUSTOM)) {
            if (spawnmob > 80) {
                Mobs.blightedCreeper(creeper);
            } else if (spawnmob > 50 && entitybiome == Biome.PLAINS) {
                Mobs.roboCreeper(creeper);
            } else {
                creeper.setPowered(true);
                creeper.setCustomName(format("&6&lPowered Creeper"));
            }
        }else if(entity instanceof Husk husk) {
            husk.setCustomName(format("&6Devorador"));
            husk.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(20);
            husk.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1, false, false, false));
        }else if(entity instanceof Stray stray){
            stray.setCustomName(format("&e&lIce Killer"));
            stray.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2, false, false, false));
            stray.getEquipment().setItemInMainHand(new ItemBuilder(Material.GOLDEN_AXE).setUnbreakable(true).addEnchantment(Enchantment.DAMAGE_ALL,50).setUnbreakable(true).build());
            stray.getEquipment().setDropChance(EquipmentSlot.HAND,0);
        } else if (entity instanceof Wither wither) {
            wither.setCustomName(format("&6&lAdvanced Wither"));
            wither.setInvulnerableTicks(500);
            wither.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(500);
            wither.setHealth(500);
            wither.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "ADVANCED_WITHER"), PersistentDataType.STRING, "ADVANCED_WITHER");
        } else if (entity instanceof Dolphin dolphin) {
            dolphin.setCustomName(format("&c&lYelmo de las Profundidades"));
            dolphin.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(40);
            dolphin.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40);
            dolphin.setHealth(40);
            CraftDolphin craft = ((CraftDolphin) dolphin);
            EntityDolphin entityDolphin = craft.getHandle();
            try {
                Class<? extends EntityInsentient> cl = EntityInsentient.class;
                Field gf = cl.getDeclaredField("bP");
                gf.setAccessible(true);
                PathfinderGoalSelector goal = (PathfinderGoalSelector) gf.get(entityDolphin);
                goal.a(0, new PathfinderGoalMeleeAttack(entityDolphin, 1.0D, true));

                Field tf = cl.getDeclaredField("bQ");
                tf.setAccessible(true);

                PathfinderGoalSelector target = (PathfinderGoalSelector) tf.get(entityDolphin);
                target.a(0, new PathfinderGoalNearestAttackableTarget<>(entityDolphin, EntityHuman.class, 10, true, false, null));
            } catch (Exception exception) {
                exception.printStackTrace();
                Warn.Mutant(exception);
            }
        } else if (entity instanceof Hoglin hoglin && !(entity instanceof Zoglin) && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL) {
            hoglin.setCustomName(format("&c&lHoglin"));
            hoglin.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1, false, false, false));
            hoglin.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(12);
            PiglinBrute piglinBrute = hoglin.getLocation().getWorld().spawn(hoglin.getLocation(), PiglinBrute.class);
            piglinBrute.setCustomName(format("&c&lZog-Rider"));
        } else if (entity instanceof Silverfish silverfish) {
            if (e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.SILVERFISH_BLOCK) {
                if (spawnmob > 90) {
                    silverfish.remove();
                    spawnRandomMob(silverfish.getLocation());
                }else{
                    silverfish.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30);
                    silverfish.setHealth(30);
                    silverfish.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(10);
                }
            }

        } else if (entity instanceof Endermite endermite) {
            if (e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.ENDER_PEARL) {
                if (spawnmob > 90) {
                    endermite.remove();
                    spawnRandomMob(endermite.getLocation());
                }else{
                    endermite.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30);
                    endermite.setHealth(30);
                    endermite.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(10);
                }
            }
        }else if(entity instanceof Enderman enderman && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL){
            if(spawnmob > 90){
                Mobs.blightedEndermam(enderman);
            }
        }else if(entity instanceof Witch witch && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL) {
            if (spawnmob > 80) {
                Mobs.blightedWitch(witch);
            }
        }else if(entity  instanceof Ghast ghast && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL){
            if(spawnmob > 80){
                Mobs.blightedGhast(ghast);
            }
        }else if(entity instanceof PiglinBrute piglinBrute && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL){
            if(spawnmob > 80){
                Mobs.blightedPiglin(piglinBrute);
            }
        }else if(entity instanceof Phantom phantom && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL){
            if(spawnmob > 80){
                Mobs.blightedPhantom(phantom);
            }else if(spawnmob > 50 && entitybiome == Biome.PLAINS){
                Mobs.roboPhantom(phantom);
            }
        }else if(entity instanceof PolarBear polarBear && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL){
            polarBear.setCustomName(format("&b&lFrostbear"));
            polarBear.setAdult();
            polarBear.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(12);
            polarBear.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class),"FROSTBEAR"),PersistentDataType.STRING,"FROSTBEAR");
            CraftPolarBear craft = ((CraftPolarBear) polarBear);
            EntityPolarBear entityPolarBear = craft.getHandle();
            try {
                Class<? extends EntityInsentient> cl = EntityInsentient.class;
                Field gf = cl.getDeclaredField("bP");
                gf.setAccessible(true);
                PathfinderGoalSelector goal = (PathfinderGoalSelector) gf.get(entityPolarBear);
                goal.a(0, new PathfinderGoalMeleeAttack(entityPolarBear, 1.0D, true));

                Field tf = cl.getDeclaredField("bQ");
                tf.setAccessible(true);

                PathfinderGoalSelector target = (PathfinderGoalSelector) tf.get(entityPolarBear);
                target.a(0, new PathfinderGoalNearestAttackableTarget<>(entityPolarBear, EntityHuman.class, 10, true, false, null));
            } catch (Exception exception) {
                exception.printStackTrace();
                Warn.Mutant(exception);
            }
        }else if(entity instanceof Panda panda && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL){
            panda.setCustomName(format("&4&lDreadbear"));
            panda.setAdult();
            panda.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(20);
            panda.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,Integer.MAX_VALUE,1,false,false,false));
            CraftPanda craft = ((CraftPanda) panda);
            EntityPanda entityPanda = craft.getHandle();
            try {
                Class<? extends EntityInsentient> cl = EntityInsentient.class;
                Field gf = cl.getDeclaredField("bP");
                gf.setAccessible(true);
                PathfinderGoalSelector goal = (PathfinderGoalSelector) gf.get(entityPanda);
                goal.a(0, new PathfinderGoalMeleeAttack(entityPanda, 1.0D, true));

                Field tf = cl.getDeclaredField("bQ");
                tf.setAccessible(true);

                PathfinderGoalSelector target = (PathfinderGoalSelector) tf.get(entityPanda);
                target.a(0, new PathfinderGoalNearestAttackableTarget<>(entityPanda, EntityHuman.class, 10, true, false, null));
            } catch (Exception exception) {
                exception.printStackTrace();
                Warn.Mutant(exception);
            }
        }else if(entity instanceof Bee bee){
            CraftBee craftBee = ((CraftBee) bee);
            EntityBee beejoke = craftBee.getHandle();
            try {
                Class<? extends EntityInsentient> cl = EntityInsentient.class;
                Field gf = cl.getDeclaredField("bP");
                gf.setAccessible(true);
                PathfinderGoalSelector goal = (PathfinderGoalSelector) gf.get(beejoke);
                goal.a(0, new PathfinderGoalMeleeAttack(beejoke, 1.0D, true));

                Field tf = cl.getDeclaredField("bQ");
                tf.setAccessible(true);

                PathfinderGoalSelector target = (PathfinderGoalSelector) tf.get(beejoke);
                target.a(0, new PathfinderGoalNearestAttackableTarget<>(beejoke, EntityHuman.class, 10, true, false, null));
            } catch (Exception exception) {
                exception.printStackTrace();
                Warn.Mutant(exception);
            }
        }else if(entity instanceof Pillager pillager && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.RAID) {
            int pillatype = new Random().nextInt(2) + 1;
            if (pillatype == 1) {
                Mobs.mountllagers(pillager);
            } else {
                Mobs.dynamLlager(pillager);
            }
        }else if(entity instanceof WitherSkeleton witherSkeleton && !(entity instanceof Stray) && !(entity instanceof Skeleton) && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL){
            int lavachance = new Random().nextInt(100);
            if(lavachance > 90){
                witherSkeleton.remove();
                IronGolem ironGolem = witherSkeleton.getWorld().spawn(witherSkeleton.getLocation(),IronGolem.class);
                Mobs.lavaGolem(ironGolem);
            }
        }else if(entity instanceof Evoker evoker && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.RAID){
            int evoketype = new Random().nextInt(5)+1;
            if(evoketype == 1){
                Mobs.evokerWind(evoker);
            }else if(evoketype == 2){
                Mobs.evokerExplosive(evoker);
            }else if(evoketype == 3){
                Mobs.evokerFire(evoker);
            }else if(evoketype == 4){
                Mobs.evokerFreeze(evoker);
            }else if(evoketype == 5){
                Mobs.evokerhex(evoker);
            }
        }else if(entity instanceof Vindicator vindicator && (e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.RAID || e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.CUSTOM)){
            vindicator.setCustomName(format("&c&lButcher"));
            vindicator.getEquipment().setItemInMainHand(new ItemBuilder(Material.NETHERITE_AXE).addEnchantment(Enchantment.DAMAGE_ALL,15).build());
            vindicator.getEquipment().setDropChance(EquipmentSlot.HAND,0);
        }else if(entity instanceof Ravager ravager && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.RAID){
            ravager.setCustomName(format("&6&lDestroyer"));
            ravager.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(34);
            ravager.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(100);
            ravager.setHealth(100);
        }else if(entity instanceof Wolf wolf){
            CraftWolf craft = ((CraftWolf) wolf);
            EntityWolf entityWolf = craft.getHandle();
            try {
                Class<? extends EntityInsentient> cl = EntityInsentient.class;
                Field gf = cl.getDeclaredField("bP");
                gf.setAccessible(true);
                PathfinderGoalSelector goal = (PathfinderGoalSelector) gf.get(entityWolf);
                goal.a(0, new PathfinderGoalMeleeAttack(entityWolf, 1.0D, true));

                Field tf = cl.getDeclaredField("bQ");
                tf.setAccessible(true);

                PathfinderGoalSelector target = (PathfinderGoalSelector) tf.get(entityWolf);
                target.a(0, new PathfinderGoalNearestAttackableTarget<>(entityWolf, EntityHuman.class, 10, true, false, null));
            } catch (Exception exception) {
                exception.printStackTrace();
                Warn.Mutant(exception);
            }
        }else if(entity instanceof Cat wolf){
            CraftCat craft = ((CraftCat) wolf);
            EntityCat entityWolf = craft.getHandle();
            try {
                Class<? extends EntityInsentient> cl = EntityInsentient.class;
                Field gf = cl.getDeclaredField("bP");
                gf.setAccessible(true);
                PathfinderGoalSelector goal = (PathfinderGoalSelector) gf.get(entityWolf);
                goal.a(0, new PathfinderGoalMeleeAttack(entityWolf, 1.0D, true));

                Field tf = cl.getDeclaredField("bQ");
                tf.setAccessible(true);

                PathfinderGoalSelector target = (PathfinderGoalSelector) tf.get(entityWolf);
                target.a(0, new PathfinderGoalNearestAttackableTarget<>(entityWolf, EntityHuman.class, 10, true, false, null));
            } catch (Exception exception) {
                exception.printStackTrace();
                Warn.Mutant(exception);
            }
        }else if(entity instanceof Goat wolf){
            CraftGoat craft = ((CraftGoat) wolf);
            net.minecraft.world.entity.animal.goat.Goat entityWolf = craft.getHandle();
            try {
                Class<? extends EntityInsentient> cl = EntityInsentient.class;
                Field gf = cl.getDeclaredField("bP");
                gf.setAccessible(true);
                PathfinderGoalSelector goal = (PathfinderGoalSelector) gf.get(entityWolf);
                goal.a(0, new PathfinderGoalMeleeAttack(entityWolf, 1.0D, true));

                Field tf = cl.getDeclaredField("bQ");
                tf.setAccessible(true);

                PathfinderGoalSelector target = (PathfinderGoalSelector) tf.get(entityWolf);
                target.a(0, new PathfinderGoalNearestAttackableTarget<>(entityWolf, EntityHuman.class, 10, true, false, null));
            } catch (Exception exception) {
                exception.printStackTrace();
                Warn.Mutant(exception);
            }
        }else if(entity instanceof Axolotl wolf){
            CraftAxolotl craft = ((CraftAxolotl) wolf);
            net.minecraft.world.entity.animal.axolotl.Axolotl entityWolf = craft.getHandle();
            try {
                Class<? extends EntityInsentient> cl = EntityInsentient.class;
                Field gf = cl.getDeclaredField("bP");
                gf.setAccessible(true);
                PathfinderGoalSelector goal = (PathfinderGoalSelector) gf.get(entityWolf);
                goal.a(0, new PathfinderGoalMeleeAttack(entityWolf, 1.0D, true));

                Field tf = cl.getDeclaredField("bQ");
                tf.setAccessible(true);

                PathfinderGoalSelector target = (PathfinderGoalSelector) tf.get(entityWolf);
                target.a(0, new PathfinderGoalNearestAttackableTarget<>(entityWolf, EntityHuman.class, 10, true, false, null));
            } catch (Exception exception) {
                exception.printStackTrace();
                Warn.Mutant(exception);
            }
        }else if(entity instanceof Fox wolf){
            CraftFox craft = ((CraftFox) wolf);
            EntityFox entityWolf = craft.getHandle();
            try {
                Class<? extends EntityInsentient> cl = EntityInsentient.class;
                Field gf = cl.getDeclaredField("bP");
                gf.setAccessible(true);
                PathfinderGoalSelector goal = (PathfinderGoalSelector) gf.get(entityWolf);
                goal.a(0, new PathfinderGoalMeleeAttack(entityWolf, 1.0D, true));

                Field tf = cl.getDeclaredField("bQ");
                tf.setAccessible(true);

                PathfinderGoalSelector target = (PathfinderGoalSelector) tf.get(entityWolf);
                target.a(0, new PathfinderGoalNearestAttackableTarget<>(entityWolf, EntityHuman.class, 10, true, false, null));
            } catch (Exception exception) {
                exception.printStackTrace();
                Warn.Mutant(exception);
            }
        }else if(entity instanceof  PufferFish pufferFish){
            pufferFish.setCustomName(format("&4&lRadiactive Globe"));
            pufferFish.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30);
            pufferFish.setHealth(30);
            pufferFish.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class),"RADIO_GLOBE"),PersistentDataType.STRING,"RADIO_GLOBE");
        }

    }

    public static void spawnRandomMob(Location location){
        int whatmob = new Random().nextInt(10) +1;
        if(whatmob == 1){
            var entity = location.getWorld().spawn(location, Zombie.class);
            Mobs.zombCarni(entity);
        }else if(whatmob == 2){
            var entity =location.getWorld().spawn(location,Skeleton.class);
            Mobs.blightedSkeleton(entity);
        }else if(whatmob == 3){
            var entity = location.getWorld().spawn(location,Creeper.class);
            Mobs.blightedCreeper(entity);
        }else if(whatmob == 4){
            var entity =location.getWorld().spawn(location,Spider.class);
            Mobs.blightedSpider(entity);
        }else if(whatmob == 5){
            var entity = location.getWorld().spawn(location,Enderman.class);
            Mobs.blightedEndermam(entity);
        }else if(whatmob == 6){
            var entity =location.getWorld().spawn(location,IronGolem.class);
            Mobs.zombObeso(entity);
        }else if(whatmob == 7){
            var entity =location.getWorld().spawn(location,Pillager.class);
            Mobs.dynamLlager(entity);
        }else if(whatmob == 8){
            var ravager = location.getWorld().spawn(location,Ravager.class);
            ravager.setCustomName(format("&6&lDestroyer"));
            ravager.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(34);
            ravager.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(100);
            ravager.setHealth(100);
        }else if(whatmob == 9){
            var entity = location.getWorld().spawn(location,PiglinBrute.class);
            Mobs.blightedPiglin(entity);
        }else{
            var entity = location.getWorld().spawn(location,WitherSkeleton.class);
            Mobs.abomination(entity);
        }
    }
}
