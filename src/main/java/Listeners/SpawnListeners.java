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
        if (withSameName) {
            for (LivingEntity nearbyEntity : entity.getLocation().getNearbyEntitiesByType(entity.getClass(), distance, distance, distance)) {

                if (nearbyEntity.getName().equalsIgnoreCase(entity.getName())) nearbyEntities.add(nearbyEntity);
            }
            if (nearbyEntities.size() >= maxPerDistance || entity.getWorld().getEntitiesByClass(entity.getClass()).size() > maxPerWorld) {
                entity.remove();
            }
        } else {
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

        int blightedProb = 80;
        if (BlastStormListeners.isEnabled()) blightedProb -= 20;

        var entity = e.getEntity();
        var pos = e.getLocation();

        var world = Bukkit.getWorld("world");
        var entitybiome = entity.getLocation().getBlock().getBiome();
        int chanceeffect = new Random().nextInt(100);
        if (Utils.getWorld().isThundering()) {
            if (chanceeffect > 80) {
                entity.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 1, false, false, false));
                entity.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0, false, false, false));
            }
        }
        if(!(entity instanceof Animals) && !(entity instanceof WaterMob)){
            if(e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL) {
                if (entity.getWorld().getEnvironment() == World.Environment.NORMAL) {
                    int spawnchance = new Random().nextInt(100);
                    if (spawnmob > 95) {
                        int type = new Random().nextInt(7);
                        if (type == 1) {
                            WitherSkeleton mob1 = entity.getWorld().spawn(entity.getLocation(), WitherSkeleton.class);
                            setCustomMobcap(mob1, 3, 1.10, 24, 20, true);
                            Mobs.abomination(mob1);
                        } else if (type == 2) {
                            Ghast ghost = entity.getWorld().spawn(entity.getLocation(), Ghast.class);
                            setCustomMobcap(ghost, 3, 1.10, 24, 20, true);
                            Mobs.blightedGhast(ghost);
                        } else if (type == 3) {
                            MagmaCube megm = entity.getWorld().spawn(entity.getLocation(), MagmaCube.class);
                            setCustomMobcap(megm, 3, 1.10, 24, 20, true);
                            megm.setCustomName(format("&6&lInferno Cube"));
                            megm.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(60);
                            megm.setHealth(60);
                            megm.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(26);
                            megm.setSize(15);
                        } else if (type == 4) {
                            PiglinBrute peglol = entity.getWorld().spawn(entity.getLocation(), PiglinBrute.class);
                            setCustomMobcap(peglol, 3, 1.10, 24, 20, true);
                            Mobs.blightedPiglin(peglol);
                        } else if (type == 5) {
                            PigZombie pegzoz = entity.getWorld().spawn(entity.getLocation(), PigZombie.class);
                            setCustomMobcap(pegzoz, 3, 1.10, 24, 20, true);
                            pegzoz.setCustomName(format("&c&lEnraged Pigman"));
                            pegzoz.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(60);
                            pegzoz.setHealth(60);
                            pegzoz.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(20);
                            CraftPigZombie craft = ((CraftPigZombie) pegzoz);
                            EntityPigZombie entityPigZombie = craft.getHandle();
                            try {
                                Class<? extends EntityInsentient> cl = EntityInsentient.class;
                                Field gf = cl.getDeclaredField("bP");
                                gf.setAccessible(true);
                                PathfinderGoalSelector goal = (PathfinderGoalSelector) gf.get(entityPigZombie);
                                goal.a(0, new PathfinderGoalMeleeAttack(entityPigZombie, 1.0D, true));

                                Field tf = cl.getDeclaredField("bQ");
                                tf.setAccessible(true);

                                PathfinderGoalSelector target = (PathfinderGoalSelector) tf.get(entityPigZombie);
                                target.a(0, new PathfinderGoalNearestAttackableTarget<>(entityPigZombie, EntityHuman.class, 10, true, false, null));
                            } catch (Exception ec) {
                                ec.printStackTrace();
                                Warn.Mutant(ec);
                            }
                        } else if (type == 6) {
                            Blaze blazze = entity.getWorld().spawn(entity.getLocation(), Blaze.class);
                            setCustomMobcap(blazze, 3, 1.10, 24, 20, true);
                            Mobs.hellfire(blazze);
                        } else {
                            Piglin faranco = entity.getWorld().spawn(entity.getLocation(), Piglin.class);
                            setCustomMobcap(faranco, 3, 1.10, 24, 20, true);
                            faranco.setCustomName(format("&b&lFrancotirador"));
                            faranco.getEquipment().setItemInMainHand(new ItemBuilder(Material.CROSSBOW).addEnchantment(Enchantment.PIERCING, 10).setUnbreakable(true).build());
                            faranco.getEquipment().setDropChance(EquipmentSlot.HAND, 0);
                            faranco.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "FRANCO"), PersistentDataType.STRING, "FRANCO");

                        }
                    }
                }else if (entity.getWorld().getEnvironment() == World.Environment.NETHER) {
                    if(spawnmob > 95){
                        int type = new Random().nextInt(9);
                        if(type == 1){
                            IronGolem ironGolem = entity.getWorld().spawn(entity.getLocation(),IronGolem.class);
                            Mobs.zombObeso(ironGolem);
                        }else if(type == 2){
                            Skeleton skeleton = entity.getWorld().spawn(entity.getLocation(),Skeleton.class);
                            Mobs.blightedSkeleton(skeleton);
                        }else if(type == 3){
                            Spider spider = entity.getWorld().spawn(entity.getLocation(),Spider.class);
                            Mobs.blightedSpider(spider);
                        }else if(type == 4){
                            Creeper creeper = entity.getWorld().spawn(entity.getLocation(),Creeper.class);
                            Mobs.blightedCreeper(creeper);
                        }else if(type == 5){
                            Creeper creeper = entity.getWorld().spawn(entity.getLocation(),Creeper.class);
                            Mobs.riftedCreeper(creeper);
                        }else if(type == 6){
                            Pillager pillager = entity.getWorld().spawn(entity.getLocation(),Pillager.class);
                            Mobs.dynamLlager(pillager);
                        }else if(type == 7){
                            Vindicator vindicator = entity.getWorld().spawn(entity.getLocation(),Vindicator.class);
                            vindicator.setCustomName(format("&6&lButcher"));
                            vindicator.getEquipment().setItemInMainHand(new ItemBuilder(Material.NETHERITE_AXE).addEnchantment(Enchantment.DAMAGE_ALL, 15).build());
                            vindicator.getEquipment().setDropChance(EquipmentSlot.HAND, 0);
                        }else if(type == 8){
                            Witch witch = entity.getWorld().spawn(entity.getLocation(),Witch.class);
                        }else{
                            Goat goat = entity.getWorld().spawn(entity.getLocation(),Goat.class);
                            goat.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(60);
                            CraftGoat craft = ((CraftGoat) goat);
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
                        }
                    }
                }
            }
        }
        if (entity instanceof Enderman enderman && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL) {
            int type = new Random().nextInt(4);
            if (type == 1) {
                Mobs.enderAberration(enderman);
            } else if (type == 2) {
                Mobs.enderFlame(enderman);
            } else if (type == 3) {
                Mobs.enderInfected(enderman);
            } else {
                Mobs.enderWatcher(enderman);
            }
        } else if (entity instanceof Skeleton skeleton && !(entity instanceof Stray) && !(entity instanceof WitherSkeleton) && (e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL)) {
            if (spawnmob > blightedProb) {
                Mobs.blightedSkeleton(skeleton);
                skeleton.getEquipment().setDropChance(EquipmentSlot.HAND, 0);
            } else if (spawnmob > 50 && entitybiome == Biome.PLAINS) {
                Mobs.roboSkele(skeleton);
                skeleton.getEquipment().setDropChance(EquipmentSlot.HAND, 0);
            } else {
                int skeletontype = new Random().nextInt(8);
                if (skeletontype == 1) {
                    skeleton.remove();
                    var wither = skeleton.getWorld().spawn(skeleton.getLocation(), WitherSkeleton.class);
                    Mobs.poweredSkeleton(wither);
                } else if (skeletontype == 2) {
                    Mobs.bullseyeSkeleton(skeleton);
                } else if (skeletontype == 3) {
                    Mobs.copperSkeleton(skeleton);
                } else if (skeletontype == 4) {
                    Mobs.ignitedSkeleton(skeleton);
                } else if (skeletontype == 5) {
                    Mobs.blizzardSkeleton(skeleton);
                } else if(skeletontype == 6){
                    skeleton.remove();
                    var wither = skeleton.getWorld().spawn(skeleton.getLocation(), WitherSkeleton.class);
                    Mobs.abomination(wither);
                } else if(skeletontype == 7){
                    if(!(skeleton.getWorld().isDayTime()) && skeleton.getWorld().getEnvironment() == World.Environment.NETHER){
                        skeleton.remove();
                        var nightmare = skeleton.getWorld().spawn(skeleton.getLocation(), Ghast.class);
                        Mobs.Nightmare(nightmare);
                    }
                }else{
                    skeleton.remove();
                    var labmob1 = skeleton.getWorld().spawn(skeleton.getLocation(),Pillager.class);
                    Mobs.madScientist(labmob1);
                }
            }
        } else if (entity instanceof Spider spider && !(entity instanceof CaveSpider) && (e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL)) {
             if (spawnmob > 50 && entitybiome == Biome.PLAINS) {
                Mobs.roboSpider(spider);
            } else {
                int spidertype = new Random().nextInt(4);
                if (spidertype == 1) {
                    Mobs.agileTarantule(spider);
                } else if (spidertype == 2) {
                    Mobs.interdimensionalVisitor(spider);
                } else if(spidertype == 3){
                    Mobs.solarScorpion(spider);
                } else{
                    Mobs.blightedSpider(spider);
                }
                var skeletonpass = spider.getLocation().getWorld().spawn(spider.getLocation(), Skeleton.class);
                spider.setPassenger(skeletonpass);
                int bow = new Random().nextInt(5);
                if (bow == 1) {
                    Mobs.ignitedSkeleton(skeletonpass);
                } else if (bow == 2) {
                    Mobs.blizzardSkeleton(skeletonpass);
                } else if (bow == 3) {
                    Mobs.copperSkeleton(skeletonpass);
                } else if(bow == 4){
                    Mobs.bullseyeSkeleton(skeletonpass);
                }else{
                    Mobs.blightedSkeleton(skeletonpass);
                }

            }
        } else if (entity instanceof Zombie zombie && !(entity instanceof ZombieVillager) && !(entity instanceof Husk) && !(entity instanceof Drowned) && !(entity instanceof PigZombie) && (e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL)){
            if (spawnmob > blightedProb) {
                Mobs.blightedZombi(zombie);
            } else if (spawnmob > 50 && entitybiome == Biome.PLAINS) {
                Mobs.roboZombi(zombie);
            } else if (spawnmob == 1) {
                    zombie.remove();
                    Mobs.Warden(zombie.getLocation().getWorld().spawn(zombie.getLocation(), IronGolem.class));
            } else {
                int zombitype = new Random().nextInt(9);
                if (zombitype == 1) {
                    Mobs.zombBox(zombie);
                } else if (zombitype == 2) {
                    Mobs.zombCarni(zombie);
                } else if (zombitype == 3) {
                    Mobs.zombHerrero(zombie);
                } else if (zombitype == 4) {
                    Mobs.zombiJinete(zombie);
                } else if (zombitype == 5) {
                    zombie.remove();
                    IronGolem ironGolem = zombie.getLocation().getWorld().spawn(zombie.getLocation(), IronGolem.class);
                    Mobs.zombObeso(ironGolem);
                } else if(zombitype == 6){
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
                }else if(zombitype == 7){
                    if(!(zombie.getWorld().isDayTime())) {
                        zombie.remove();
                        Vindicator vindicator = zombie.getWorld().spawn(zombie.getLocation(), Vindicator.class);
                        Mobs.KillerScream(vindicator);
                    }
                }else if(zombitype == 8){
                    Mobs.lushZombie(zombie);
                }else{
                    zombie.remove();
                    IronGolem ironGolem = zombie.getWorld().spawn(zombie.getLocation(),IronGolem.class);
                    Mobs.experimentWOOD(ironGolem);
                }
            }
        } else if (entity instanceof Bat bat && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL) {
            if (bat.getWorld().isThundering()) {
                int mobcaptry = new Random().nextInt(100);
                bat.remove();
                if (mobcaptry == 1) {
                    Blaze blaze = bat.getWorld().spawn(bat.getLocation(), Blaze.class);
                    Mobs.hellfire(blaze);
                }
            }
        } else if (entity instanceof Piglin piglin && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL) {
            piglin.setCustomName(format("&b&lFrancotirador"));
            piglin.getEquipment().setItemInMainHand(new ItemBuilder(Material.CROSSBOW).addEnchantment(Enchantment.PIERCING, 10).setUnbreakable(true).build());
            piglin.getEquipment().setDropChance(EquipmentSlot.HAND, 0);
            piglin.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "FRANCO"), PersistentDataType.STRING, "FRANCO");
        } else if (entity instanceof CaveSpider caveSpider && !(entity instanceof Spider)) {
            caveSpider.setCustomName(format("&6Mutacion"));
            caveSpider.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "MUTACION"), PersistentDataType.STRING, "MUTACION");
            //mutanteo exitoso
        } else if (entity instanceof PigZombie pigZombie && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL) {
            int type = new Random().nextInt(3);
            if(type == 1) {
                pigZombie.setCustomName(format("&c&lEnraged Pigman"));
                pigZombie.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(60);
                pigZombie.setHealth(60);
                pigZombie.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(20);
                CraftPigZombie craft = ((CraftPigZombie) pigZombie);
                EntityPigZombie entityPigZombie = craft.getHandle();
                try {
                    Class<? extends EntityInsentient> cl = EntityInsentient.class;
                    Field gf = cl.getDeclaredField("bP");
                    gf.setAccessible(true);
                    PathfinderGoalSelector goal = (PathfinderGoalSelector) gf.get(entityPigZombie);
                    goal.a(0, new PathfinderGoalMeleeAttack(entityPigZombie, 1.0D, true));

                    Field tf = cl.getDeclaredField("bQ");
                    tf.setAccessible(true);

                    PathfinderGoalSelector target = (PathfinderGoalSelector) tf.get(entityPigZombie);
                    target.a(0, new PathfinderGoalNearestAttackableTarget<>(entityPigZombie, EntityHuman.class, 10, true, false, null));
                } catch (Exception ec) {
                    ec.printStackTrace();
                    Warn.Mutant(ec);
                }
            }else if(type == 2){
                pigZombie.remove();
                PiglinBrute piglinBrute = pigZombie.getWorld().spawn(pigZombie.getLocation(),PiglinBrute.class);
                piglinBrute.setCustomName(format("&c&lEnraged Piglin Brute"));
                piglinBrute.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(80);
                piglinBrute.setHealth(80);
                piglinBrute.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(20);
            }else{
                pigZombie.remove();
                PiglinBrute piglinBrute = pigZombie.getWorld().spawn(pigZombie.getLocation(),PiglinBrute.class);
                Mobs.blightedPiglin(piglinBrute);
            }

        } else if (entity instanceof Blaze blaze && (e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL || e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.SPAWNER)) {
            Mobs.hellfire(blaze);
        } else if (entity instanceof Creeper creeper && (e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL || e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.CUSTOM)) {
            if (spawnmob > blightedProb) {
                Mobs.blightedCreeper(creeper);
            } else if (spawnmob > 50 && entitybiome == Biome.PLAINS) {
                Mobs.roboCreeper(creeper);
            } else {
                if (!(creeper.getWorld().isDayTime())) {
                    int aaa = new Random().nextInt(2);
                    if(aaa == 1) {
                        Mobs.Overscream(creeper);
                    }else{
                        Mobs.riftedCreeper(creeper);
                    }
                }else{
                    int ssss = new Random().nextInt(3);
                    if(ssss == 1) {
                        creeper.setPowered(true);
                        creeper.setCustomName(format("&6&lPowered Creeper"));
                        creeper.setExplosionRadius(6);
                    }else if(ssss == 2){
                        Mobs.roboMine(creeper);
                    }else{
                        Mobs.riftedCreeper(creeper);
                    }
                }
            }
        } else if (entity instanceof Salmon) {
            e.setCancelled(true);
            PufferFish pufferFish = entity.getWorld().spawn(entity.getLocation(), PufferFish.class);
            setCustomMobcap(pufferFish, 3, 1.10, 24, 20, true);
        } else if (entity instanceof MagmaCube magmaCube && !(entity instanceof Slime) && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL) {
            if (e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.SLIME_SPLIT) {
                magmaCube.setCustomName(format("&6&lInferno Cube"));
                magmaCube.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
                magmaCube.setHealth(20);
                magmaCube.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(10);
            } else {
                magmaCube.setCustomName(format("&6&lInferno Cube"));
                magmaCube.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(60);
                magmaCube.setHealth(60);
                magmaCube.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(26);
                magmaCube.setSize(15);
            }
        } else if (entity instanceof Slime slime && !(entity instanceof MagmaCube) && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL) {
            if (e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.SLIME_SPLIT) {
                slime.setCustomName(format("&2Sludge"));
                slime.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(10);
                slime.setHealth(10);
                slime.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(26);
            } else {
                slime.setSize(8);
                slime.setCustomName(format("&2Sludge"));
                slime.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(60);
                slime.setHealth(60);
                slime.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(26);
            }
        } else if (entity instanceof Husk husk) {
            husk.setCustomName(format("&6Devorador"));
            husk.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(20);
            husk.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1, false, false, false));
        } else if (entity instanceof Stray stray) {
            stray.setCustomName(format("&e&lIce Killer"));
            stray.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2, false, false, false));
            stray.getEquipment().setItemInMainHand(new ItemBuilder(Material.GOLDEN_AXE).setUnbreakable(true).addEnchantment(Enchantment.DAMAGE_ALL, 50).setUnbreakable(true).build());
            stray.getEquipment().setDropChance(EquipmentSlot.HAND, 0);
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
            hoglin.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2, false, false, false));
            hoglin.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(32);
            PiglinBrute piglinBrute = hoglin.getLocation().getWorld().spawn(hoglin.getLocation(), PiglinBrute.class);
            piglinBrute.setCustomName(format("&c&lZog-Rider"));
            piglinBrute.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(80);
            piglinBrute.setHealth(80);
            piglinBrute.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(20);
        } else if (entity instanceof Silverfish silverfish) {
            if (e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.SILVERFISH_BLOCK) {
                if (spawnmob > 80) {
                    silverfish.remove();
                    spawnRandomMob(silverfish.getLocation());
                } else {
                    silverfish.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30);
                    silverfish.setHealth(30);
                    silverfish.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(10);
                }
            }

        } else if (entity instanceof Endermite endermite) {
            if (e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.ENDER_PEARL) {
                if (spawnmob > 80) {
                    endermite.remove();
                    spawnRandomMob(endermite.getLocation());
                } else {
                    endermite.setCustomName(format("&5&lVoid Fiend"));
                    endermite.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(50);
                    endermite.setHealth(50);
                    endermite.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(30);
                }
            }
        } else if (entity instanceof Enderman enderman && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL) {
            if (spawnmob > blightedProb - 10) {
                Mobs.blightedEndermam(enderman);
            }
        } else if (entity instanceof Witch witch && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL) {
            if (spawnmob > 90) {
                witch.remove();
                Vex vex = witch.getWorld().spawn(witch.getLocation(), Vex.class);
                Mobs.Banshee(vex);
            }else{
                Mobs.blightedWitch(witch);
            }
        }else if(entity instanceof Vex vex){
            if(vex.getSummoner() instanceof Evoker evoker){
                if(evoker.getPersistentDataContainer().has(Utils.key("MAESTRY_WIZARD"),PersistentDataType.STRING)){
                    vex.remove();
                    int typepillager = new Random().nextInt(3);
                    if(typepillager == 1){
                        var mob = vex.getWorld().spawn(vex.getLocation(),Pillager.class);
                        Mobs.dynamLlager(mob);
                    }else if(typepillager == 2){
                        var mob = vex.getWorld().spawn(vex.getLocation(),Pillager.class);
                        Mobs.mountllagers(mob);
                    }else{
                        var mob = vex.getWorld().spawn(vex.getLocation(),Vindicator.class);
                        mob.setCustomName(format("&c&lButcher"));
                        mob.getEquipment().setItemInMainHand(new ItemBuilder(Material.NETHERITE_AXE).addEnchantment(Enchantment.DAMAGE_ALL, 15).build());
                        mob.getEquipment().setDropChance(EquipmentSlot.HAND, 0);
                    }
                }else{
                    int elemental = new Random().nextInt(4);
                    if(elemental == 1){
                        Mobs.cinder(vex);
                    }else if(elemental == 2){
                        Mobs.jengu(vex);
                    }else if(elemental == 3){
                        Mobs.grue(vex);
                    }else{
                        Mobs.djiin(vex);
                    }
                }
            }

        } else if (entity instanceof Ghast ghast && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL) {
            if (spawnmob > blightedProb) {
                Mobs.blightedGhast(ghast);
            }
        } else if (entity instanceof PiglinBrute piglinBrute && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL) {
            if (spawnmob > blightedProb) {
                Mobs.blightedPiglin(piglinBrute);
            }
        } else if (entity instanceof Phantom phantom && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL) {
            if (spawnmob > 80) {
                Mobs.roboPhantom(phantom);
            } else {
                Mobs.blightedPhantom(phantom);
            }
        } else if (entity instanceof PolarBear polarBear && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL) {
            polarBear.setCustomName(format("&b&lFrostbear"));
            polarBear.setAdult();
            polarBear.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(12);
            polarBear.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "FROSTBEAR"), PersistentDataType.STRING, "FROSTBEAR");
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
        } else if (entity instanceof Panda panda && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL) {
            panda.setCustomName(format("&4&lDreadbear"));
            panda.setAdult();
            panda.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(20);
            panda.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1, false, false, false));
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
        } else if (entity instanceof Bee bee) {
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
        } else if (entity instanceof Pillager pillager && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.RAID) {
            int pillatype = new Random().nextInt(2) + 1;
            if (pillatype == 1) {
                Mobs.mountllagers(pillager);
            } else {
                Mobs.dynamLlager(pillager);
            }
        } else if (entity instanceof WitherSkeleton witherSkeleton && !(entity instanceof Stray) && !(entity instanceof Skeleton) && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL) {
            int lavachance = new Random().nextInt(100);
            witherSkeleton.setCustomName(format("&6Advanced Wither Skeleton"));
            witherSkeleton.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(70);
            witherSkeleton.setHealth(70);
            witherSkeleton.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(20);
            witherSkeleton.getEquipment().setDropChance(EquipmentSlot.HAND, 0);
            witherSkeleton.getEquipment().setItemInMainHand(new ItemBuilder(Material.NETHERITE_AXE).setUnbreakable(true).addEnchantment(Enchantment.DAMAGE_ALL, 30).build());
            if (lavachance > 90) {
                witherSkeleton.remove();
                IronGolem ironGolem = witherSkeleton.getWorld().spawn(witherSkeleton.getLocation(), IronGolem.class);
                Mobs.lavaGolem(ironGolem);
            }
        } else if (entity instanceof Evoker evoker && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.RAID) {
            int evoketype = new Random().nextInt(5) + 1;
            if (evoketype == 1) {
                Mobs.evokerWind(evoker);
            } else if (evoketype == 2) {
                Mobs.evokerExplosive(evoker);
            } else if (evoketype == 3) {
                Mobs.evokerFire(evoker);
            } else if (evoketype == 4) {
                Mobs.evokerFreeze(evoker);
            } else if (evoketype == 5) {
                Mobs.evokerhex(evoker);
            }
        } else if (entity instanceof Vindicator vindicator && (e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.RAID || e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.CUSTOM)) {
            vindicator.setCustomName(format("&c&lButcher"));
            vindicator.getEquipment().setItemInMainHand(new ItemBuilder(Material.NETHERITE_AXE).addEnchantment(Enchantment.DAMAGE_ALL, 15).build());
            vindicator.getEquipment().setDropChance(EquipmentSlot.HAND, 0);
        } else if (entity instanceof Ravager ravager && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.RAID) {
            ravager.setCustomName(format("&6&lDestroyer"));
            ravager.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(34);
            ravager.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(100);
            ravager.setHealth(100);
        } else if (entity instanceof Wolf wolf) {
            wolf.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(60);
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
        } else if (entity instanceof Cat wolf) {
            wolf.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(60);
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
        } else if (entity instanceof Goat wolf) {
            wolf.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(60);
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
        } else if (entity instanceof Axolotl wolf) {
            wolf.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(60);
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
        } else if (entity instanceof Fox wolf) {
            wolf.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(60);
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
        } else if (entity instanceof PufferFish pufferFish) {
            pufferFish.setCustomName(format("&4&lRadiactive Globe"));
            pufferFish.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30);
            pufferFish.setHealth(30);
            pufferFish.setRemoveWhenFarAway(true);
            setCustomMobcap(pufferFish, 3, 1.10, 24, 20, true);
            pufferFish.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "RADIO_GLOBE"), PersistentDataType.STRING, "RADIO_GLOBE");
        }else if(entity instanceof Cow cow){
            cow.remove();
            var ravager = cow.getWorld().spawn(cow.getLocation(),Ravager.class);
            ravager.setCustomName(format("&6&lDestroyer"));
            ravager.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(34);
            ravager.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(100);
            ravager.setHealth(100);
        }else if(entity instanceof Pig pig) {
            pig.remove();
            var ravager = pig.getWorld().spawn(pig.getLocation(),Ravager.class);
            ravager.setCustomName(format("&6&lDestroyer"));
            ravager.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(34);
            ravager.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(100);
            ravager.setHealth(100);
        }else if(e.getEntity().getType() == EntityType.SQUID){
            entity.remove();
            var pufferfish = entity.getLocation().getWorld().spawn(entity.getLocation(),PufferFish.class);
        }else if(e.getEntity().getType() == EntityType.GLOW_SQUID){
            entity.remove();
            var eldergur = entity.getWorld().spawn(entity.getLocation(),ElderGuardian.class);
            Mobs.elderdestroyer(eldergur);
        }else if(entity instanceof Drowned drowned && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL){
            drowned.setCustomName(format("&6Pyromaniac of the Deeps"));
            drowned.getEquipment().setItemInMainHand(new ItemBuilder(Material.TRIDENT).build());
            drowned.getPersistentDataContainer().set(Utils.key("PYRO_DEEP"),PersistentDataType.STRING,"PYRO_DEEP");
        }

    }

    public static void spawnRandomMob(Location location) {
        int whatmob = new Random().nextInt(10) + 1;
        if (whatmob == 1) {
            var entity = location.getWorld().spawn(location, Zombie.class);
            Mobs.zombCarni(entity);
        } else if (whatmob == 2) {
            var entity = location.getWorld().spawn(location, Skeleton.class);
            Mobs.blightedSkeleton(entity);
        } else if (whatmob == 3) {
            var entity = location.getWorld().spawn(location, Creeper.class);
            Mobs.blightedCreeper(entity);
        } else if (whatmob == 4) {
            var entity = location.getWorld().spawn(location, Spider.class);
            Mobs.blightedSpider(entity);
        } else if (whatmob == 5) {
            var entity = location.getWorld().spawn(location, Enderman.class);
            Mobs.blightedEndermam(entity);
        } else if (whatmob == 6) {
            var entity = location.getWorld().spawn(location, IronGolem.class);
            Mobs.zombObeso(entity);
        } else if (whatmob == 7) {
            var entity = location.getWorld().spawn(location, Pillager.class);
            Mobs.dynamLlager(entity);
        } else if (whatmob == 8) {
            var ravager = location.getWorld().spawn(location, Ravager.class);
            ravager.setCustomName(format("&6&lDestroyer"));
            ravager.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(34);
            ravager.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(100);
            ravager.setHealth(100);
        } else if (whatmob == 9) {
            var entity = location.getWorld().spawn(location, PiglinBrute.class);
            Mobs.blightedPiglin(entity);
        } else {
            var entity = location.getWorld().spawn(location, WitherSkeleton.class);
            Mobs.abomination(entity);
        }
    }
}
