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
import net.minecraft.world.entity.animal.EntityDolphin;
import net.minecraft.world.entity.player.EntityHuman;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Biome;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftDolphin;
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

        if(entitybiome == Biome.SNOWY_TUNDRA || entitybiome == Biome.SNOWY_BEACH || entitybiome == Biome.SNOWY_TAIGA || entitybiome == Biome.SNOWY_MOUNTAINS || entitybiome == Biome.SNOWY_TAIGA_HILLS || entitybiome == Biome.SNOWY_TAIGA_MOUNTAINS
        || entitybiome == Biome.ICE_SPIKES){
            if(entity instanceof Zombie zombie && !(entity instanceof Drowned) && !(entity instanceof Husk) && !(entity instanceof ZombieVillager) && !(entity instanceof PigZombie) && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL){
                Mobs.zombiCongelado(zombie);
            }else if(entity instanceof Skeleton skeleton && !(entity instanceof Stray) && !(entity instanceof WitherSkeleton) && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL){
                Mobs.esqueletoNieve(skeleton);
            }else if(entity instanceof Spider spider && !(entity instanceof CaveSpider) && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL){
                Mobs.spiderNieve(spider);
            }else if(entity instanceof Creeper creeper && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL){
                Mobs.creeperCongelado(creeper);
            }
        }else if(entitybiome == Biome.JUNGLE || entitybiome == Biome.JUNGLE_EDGE || entitybiome == Biome.JUNGLE_HILLS || entitybiome == Biome.BAMBOO_JUNGLE_HILLS || entitybiome == Biome.BAMBOO_JUNGLE
        || entitybiome == Biome.MODIFIED_JUNGLE || entitybiome == Biome.MODIFIED_JUNGLE_EDGE){
            if(entity instanceof Zombie zombie && !(entity instanceof Drowned) && !(entity instanceof Husk) && !(entity instanceof ZombieVillager) && !(entity instanceof PigZombie) && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL){
                Mobs.mossZombie(zombie);
            }else if(entity instanceof Skeleton skeleton && !(entity instanceof Stray) && !(entity instanceof WitherSkeleton) && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL){
                Mobs.esqueletoMilitar(skeleton);
            }else if(entity instanceof Spider spider && !(entity instanceof CaveSpider) && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL){
                Mobs.spiderJungla(spider);
            }else if(entity instanceof Creeper creeper && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL){
                Mobs.mossCreeper(creeper);
            }
        }else if(entitybiome == Biome.DESERT || entitybiome == Biome.DESERT_HILLS || entitybiome == Biome.DESERT_LAKES || entitybiome == Biome.BADLANDS || entitybiome == Biome.BADLANDS_PLATEAU
        || entitybiome == Biome.ERODED_BADLANDS || entitybiome == Biome.MODIFIED_BADLANDS_PLATEAU || entitybiome == Biome.MODIFIED_WOODED_BADLANDS_PLATEAU || entitybiome == Biome.WOODED_BADLANDS_PLATEAU){
            if(entity instanceof Zombie zombie && !(entity instanceof Drowned) && !(entity instanceof Husk) && !(entity instanceof ZombieVillager) && !(entity instanceof PigZombie) && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL){
                Mobs.zombiMomia(zombie);
            }else if(entity instanceof Skeleton skeleton && !(entity instanceof Stray) && !(entity instanceof WitherSkeleton) && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL){
                Mobs.esqueletoMomia(skeleton);
            }else if(entity instanceof Spider spider && !(entity instanceof CaveSpider) && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL){
                Mobs.spiderArena(spider);
            }else if(entity instanceof Creeper creeper && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL){
                Mobs.creeperSandstone(creeper);
            }
        }

        if(entity instanceof Skeleton skeleton && !(entity instanceof Stray) && !(entity instanceof WitherSkeleton)){
            if(spawnmob > 80){
                Mobs.blightedSkeleton(skeleton);
            }else {
                int bow = new Random().nextInt(2) + 1;
                if (bow == 1) {
                    skeleton.getEquipment().setItemInMainHand(new ItemBuilder(Material.BOW).addEnchantment(Enchantment.ARROW_DAMAGE, 20).build());
                } else {
                    skeleton.getEquipment().setItemInMainHand(new ItemBuilder(Material.BOW).addEnchantment(Enchantment.ARROW_DAMAGE, 40).build());
                }
            }
            skeleton.getEquipment().setDropChance(EquipmentSlot.HAND, 0);
        } else if (entity instanceof Spider spider&& !(entity instanceof CaveSpider)) {
            if(spawnmob > 80){
                Mobs.blightedSpider(spider);
            }else{
            int spidertype = new Random().nextInt(3)+1;
            int jockeychance = new Random().nextInt(100);
            if(spidertype == 1){
                Mobs.agileTarantule(spider);
            }else if(spidertype == 2){
                Mobs.interdimensionalVisitor(spider);
            }else{
                Mobs.solarScorpion(spider);
            }

            if(jockeychance > 70){
                var skeletonpass = spider.getLocation().getWorld().spawn(spider.getLocation(),Skeleton.class);
                spider.setPassenger(skeletonpass);
            }

            }
        }else if(entity instanceof Zombie zombie && !(entity instanceof ZombieVillager) && !(entity instanceof  Husk) && !(entity instanceof Drowned)){
            if(spawnmob > 80){
                Mobs.blightedZombi(zombie);
            }else{
                int zombitype = new Random().nextInt(5)+1;
                if(zombitype == 1){
                    Mobs.zombBox(zombie);
                }else if(zombitype == 2){
                    Mobs.zombCarni(zombie);
                }else if(zombitype == 3){
                    Mobs.zombHerrero(zombie);
                }else if(zombitype == 4){
                    Mobs.zombiJinete(zombie);
                }else{
                    IronGolem ironGolem =zombie.getLocation().getWorld().spawn(zombie.getLocation(),IronGolem.class);
                    Mobs.zombObeso(ironGolem);
                }
            }
        }else if(entity instanceof Creeper creeper){
            if(spawnmob > 80){
                Mobs.blightedCreeper(creeper);
            }else{
                creeper.setPowered(true);
                creeper.setCustomName(format("&6&lPowered Creeper"));
            }
        }else if(entity instanceof Wither wither){
            wither.setCustomName(format("&6&lAdvanced Wither"));
            wither.setInvulnerableTicks(500);
            wither.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(500);
            wither.setHealth(500);
            wither.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class),"ADVANCED_WITHER"),PersistentDataType.STRING,"ADVANCED_WITHER");
        }else if(entity instanceof Dolphin dolphin){
            dolphin.setCustomName(format("&c&lYelmo de las Profundidades"));
            dolphin.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(20);
            dolphin.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40);
            dolphin.setHealth(40);
            CraftDolphin craft = ((CraftDolphin) dolphin);
            EntityDolphin entityDolphin = craft.getHandle();
            try{
                Class<? extends EntityInsentient> cl = EntityInsentient.class;
                Field gf = cl.getDeclaredField("bP");
                gf.setAccessible(true);
                PathfinderGoalSelector goal = (PathfinderGoalSelector) gf.get(entityDolphin);
                goal.a(0, new PathfinderGoalMeleeAttack(entityDolphin,1.0D,true));

                Field tf = cl.getDeclaredField("bQ");
                tf.setAccessible(true);

                PathfinderGoalSelector target = (PathfinderGoalSelector) tf.get(entityDolphin);
                target.a(0,new PathfinderGoalNearestAttackableTarget<>(entityDolphin, EntityHuman.class, 10,true,false,null));
            }catch (Exception exception){
                exception.printStackTrace();
                Warn.Mutant(exception);
            }
        }

    }
}
