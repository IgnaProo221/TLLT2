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
import java.util.Random;
import java.util.stream.Collectors;

public class SpawnListeners implements Listener {
    private TLL2 plugin;

    public SpawnListeners(TLL2 plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void spawnMob(CreatureSpawnEvent e) {
        Random random = new Random();
        int spawnmob = random.nextInt(100);
        var entity = e.getEntity();
        var pos = e.getLocation();
        var world = Bukkit.getWorld("world");
        var entitybiome = entity.getLocation().getBlock().getBiome();
        if(entity instanceof Monster monster){
            if(monster.getWorld().isThundering()){
                int fuerzachance = new Random().nextInt(100);
                if(fuerzachance > 90){
                    monster.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,Integer.MAX_VALUE,1,false,false,false));
                }
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
        }else if (entity instanceof Skeleton skeleton && !(entity instanceof Stray) && !(entity instanceof WitherSkeleton) && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL) {
            if (spawnmob > 80) {
                Mobs.blightedSkeleton(skeleton);
                skeleton.getEquipment().setDropChance(EquipmentSlot.HAND, 0);
            }else if(spawnmob > 50 && entitybiome == Biome.PLAINS){
                Mobs.roboSkele(skeleton);
                skeleton.getEquipment().setDropChance(EquipmentSlot.HAND, 0);
            } else {
                int bow = new Random().nextInt(2) + 1;
                if (bow == 1) {
                    skeleton.getEquipment().setItemInMainHand(new ItemBuilder(Material.BOW).addEnchantment(Enchantment.ARROW_DAMAGE, 20).build());
                    skeleton.getEquipment().setDropChance(EquipmentSlot.HAND, 0);
                } else {
                    skeleton.getEquipment().setItemInMainHand(new ItemBuilder(Material.BOW).addEnchantment(Enchantment.ARROW_DAMAGE, 40).build());
                    skeleton.getEquipment().setDropChance(EquipmentSlot.HAND, 0);
                }
            }
        } else if (entity instanceof Spider spider && !(entity instanceof CaveSpider) && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL) {
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
                    int bow = new Random().nextInt(2) + 1;
                    if (bow == 1) {
                        skeletonpass.getEquipment().setDropChance(EquipmentSlot.HAND, 0);
                        skeletonpass.getEquipment().setItemInMainHand(new ItemBuilder(Material.BOW).addEnchantment(Enchantment.ARROW_DAMAGE, 20).build());
                    } else {
                        skeletonpass.getEquipment().setDropChance(EquipmentSlot.HAND, 0);
                        skeletonpass.getEquipment().setItemInMainHand(new ItemBuilder(Material.BOW).addEnchantment(Enchantment.ARROW_DAMAGE, 40).build());
                    }
                }

            }
        } else if (entity instanceof Zombie zombie && !(entity instanceof ZombieVillager) && !(entity instanceof Husk) && !(entity instanceof Drowned) && !(entity instanceof PigZombie) && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL) {
            if (spawnmob > 80) {
                Mobs.blightedZombi(zombie);
            }else if(spawnmob > 50 && entitybiome == Biome.PLAINS) {
                Mobs.roboZombi(zombie);
            }else if(zombie.getLocation().getY() < 0){
                int wardenchance = new Random().nextInt(100);
                if(wardenchance == 1){
                    zombie.remove();
                    IronGolem ironGolem = zombie.getLocation().getWorld().spawn(zombie.getLocation(),IronGolem.class);
                    Mobs.Warden(ironGolem);
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
                } else if(zombitype == 5){
                    IronGolem ironGolem = zombie.getLocation().getWorld().spawn(zombie.getLocation(), IronGolem.class);
                    Mobs.zombObeso(ironGolem);
                }else{
                    zombie.setBaby(true);
                }
            }
        } else if (entity instanceof Creeper creeper && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL) {
            if (spawnmob > 80) {
                Mobs.blightedCreeper(creeper);
            }else if(spawnmob > 50 && entitybiome == Biome.PLAINS){
                Mobs.roboCreeper(creeper);
            } else {
                creeper.setPowered(true);
                creeper.setCustomName(format("&6&lPowered Creeper"));
            }
        } else if (entity instanceof Wither wither) {
            wither.setCustomName(format("&6&lAdvanced Wither"));
            wither.setInvulnerableTicks(500);
            wither.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(500);
            wither.setHealth(500);
            wither.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "ADVANCED_WITHER"), PersistentDataType.STRING, "ADVANCED_WITHER");
        } else if (entity instanceof Dolphin dolphin) {
            dolphin.setCustomName(format("&c&lYelmo de las Profundidades"));
            dolphin.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(20);
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
        }else if(entity instanceof Pillager pillager && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.RAID){
            int pillatype = new Random().nextInt(2)+1;
            if(pillatype == 1){
                Mobs.mountllagers(pillager);
            }else{
                Mobs.dynamLlager(pillager);
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
        }else if(entity instanceof Vindicator vindicator && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.RAID){
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

    public void spawnRandomMob(Location location){
        int whatmob = new Random().nextInt(10) +1;
        if(whatmob == 1){
            location.getWorld().spawn(location, Zombie.class);
        }else if(whatmob == 2){
            location.getWorld().spawn(location,Skeleton.class);
        }else if(whatmob == 3){
            location.getWorld().spawn(location,Creeper.class);
        }else if(whatmob == 4){
            location.getWorld().spawn(location,Spider.class);
        }else if(whatmob == 5){
            location.getWorld().spawn(location,Enderman.class);
        }else if(whatmob == 6){
            location.getWorld().spawn(location,Wither.class);
        }else if(whatmob == 7){
            location.getWorld().spawn(location,Pillager.class);
        }else if(whatmob == 8){
            location.getWorld().spawn(location,Ravager.class);
        }else if(whatmob == 9){
            location.getWorld().spawn(location,PiglinBrute.class);
        }else{
            location.getWorld().spawn(location,WitherSkeleton.class);
        }
    }
}
