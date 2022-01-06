package Eventos;

import Utilidades.Mobs;
import Utilidades.Warn;
import net.minecraft.world.entity.EntityInsentient;
import net.minecraft.world.entity.ai.goal.PathfinderGoalMeleeAttack;
import net.minecraft.world.entity.ai.goal.PathfinderGoalSelector;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalNearestAttackableTarget;
import net.minecraft.world.entity.monster.EntityPigZombie;
import net.minecraft.world.entity.player.EntityHuman;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Biome;
import org.bukkit.craftbukkit.v1_18_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_18_R1.entity.CraftPigZombie;
import net.minecraft.server.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import tlldos.tll2.TLL2;

import static Utilidades.Format.format;

import java.lang.reflect.Field;
import java.util.Random;

public class SpawnListeners implements Listener{
    private TLL2 plugin;

    public SpawnListeners (TLL2 plugin){
        this.plugin = plugin;
    }

    Random random = new Random();
    int spawnmob = random.nextInt(100);

    @EventHandler
    public void spawnMob(CreatureSpawnEvent e) {
        var en = e.getEntity();
        var pos = e.getLocation();
           if (en instanceof Zombie) {
               var zombie = (Zombie) en;
               if(spawnmob <= 30){
                   zombie.setCustomName(format("TNT Monster"));
                   zombie.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(7.0);
                   zombie.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30.0);
                   zombie.setHealth(30);
                   zombie.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "TNT_MONSTER"), PersistentDataType.STRING, "TNT_MONSTER");
               }else{
                   zombie.setCustomName(format("&6Zombi Tier I"));
                   zombie.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(7.0);
                   zombie.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30.0);
                   zombie.setHealth(30);
                   zombie.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, 0, false, false, false));
               }
           }

           if (en instanceof Creeper) {
               var creeper = (Creeper) en;
               creeper.setCustomName(format("&cCharged Creeper"));
               creeper.setPowered(true);
               creeper.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20.0);
               creeper.setHealth(20);
           }

           if(en instanceof Skeleton self){
               this.spawnSkeletonClass(self);
           }

           if(en instanceof Spider){
               var spider = (Spider)en;
               spider.setCustomName(format("&4Plague Spider"));
               spider.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30.0);
               spider.setHealth(30);
               spider.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(10.0);
               spider.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "PLAGUE_SPIDER"), PersistentDataType.STRING, "PLAGUE_SPIDER");
           }

        if(en instanceof Pillager){
            var pillager = (Pillager)en;

            ItemStack ac = new ItemStack(Material.CROSSBOW);
            ItemMeta meta = ac.getItemMeta();
            meta.addEnchant(Enchantment.MULTISHOT, 1, true);
            meta.addEnchant(Enchantment.QUICK_CHARGE, 3, true);
            ac.setItemMeta(meta);

            pillager.setCustomName(format("&cPillager Generico Explosivo lol"));
            pillager.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40.0);
            pillager.setHealth(40);
            pillager.getEquipment().setItemInMainHand(ac);
            pillager.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "OVERRATED_PILLAGER"), PersistentDataType.STRING, "OVERRATED_PILLAGER");
        }

        if(en instanceof Vindicator){
            var vindi = (Vindicator)en;
            ItemStack asx = new ItemStack(Material.NETHERITE_AXE);
            ItemMeta meta = asx.getItemMeta();
            meta.addEnchant(Enchantment.DAMAGE_ALL, 5, true);
            asx.setItemMeta(meta);

            vindi.setCustomName(format("&6Butcher"));
            vindi.getEquipment().setItemInMainHand(asx);
            vindi.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(45.0);
            vindi.setHealth(45);
        }

        if(en instanceof Ravager){
            var ravager = (Ravager)en;
            ravager.setCustomName(format("&6Destroyer"));
            ravager.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(12.0);
            ravager.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(80.0);
            ravager.setHealth(80);
        }

        if(en instanceof Blaze){
            var blaze = (Blaze)en;
            blaze.setCustomName(format("&cHellfire"));
            blaze.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40.0);
            blaze.setHealth(40);
            blaze.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "HELLFIRE"), PersistentDataType.STRING, "HELLFIRE");
        }

        if (en instanceof Vex self) {

            this.spawnVexClass(self);

        }
        if(en instanceof Husk){
            Location l = en.getLocation().clone();
            e.setCancelled(true);
            l.getWorld().spawn(l, Blaze.class);
        }

        if(en instanceof Rabbit){
            var rabbit = (Rabbit)en;
            if(rabbit.getLocation().getBlock().getBiome().equals(Biome.DESERT)){
                e.setCancelled(true);
                var ghast = en.getLocation().getWorld().spawn(en.getLocation(),Ghast.class);
                Mobs.ghastDesert(ghast);
            }
        }

        if(en instanceof Pig){
            e.setCancelled(true);
            var pigbrute = en.getLocation().getWorld().spawn(en.getLocation(), PiglinBrute.class);
            Mobs.piglinBrutedim(pigbrute);
        }
        if(en instanceof PigZombie){
            var zombipig = (PigZombie)en;
            zombipig.setCustomName(format("&cEnraged Pigman"));
            zombipig.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(10.0);


            CraftPigZombie craft = ((CraftPigZombie) zombipig);
            EntityPigZombie zombiPig = craft.getHandle();


            try {
                Class<? extends EntityInsentient> cl = EntityInsentient.class;
                Field gf = cl.getDeclaredField("bR");
                gf.setAccessible(true);

                Field tf = cl.getDeclaredField("bS");
                tf.setAccessible(true);

                PathfinderGoalSelector goal = (PathfinderGoalSelector) gf.get(zombiPig);
                PathfinderGoalSelector target = (PathfinderGoalSelector) tf.get(zombiPig);

                goal.a(0, new PathfinderGoalMeleeAttack(zombiPig, 1.0D, true));
                target.a(0, new PathfinderGoalNearestAttackableTarget<>(zombiPig, EntityHuman.class, 10, true, false, null));

                gf.setAccessible(false);
                tf.setAccessible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
                Warn.Mutant(ex);
            }
        }
        if(en instanceof Bat){
            var bat = (Bat)en;
            bat.setCustomName(format("&cExplosive Bat"));
            bat.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "EXPLOSIVE_BAT"), PersistentDataType.STRING, "EXPLOSIVE_BAT");
        }
        if(en instanceof WitherSkeleton){
            var witherskeleton =(WitherSkeleton)en;

            ItemStack caca = new ItemStack(Material.NETHERITE_SWORD);
            ItemMeta meta = caca.getItemMeta();
            meta.addEnchant(Enchantment.DAMAGE_ALL, 20,true);
            meta.setUnbreakable(true);
            caca.setItemMeta(meta);

            witherskeleton.setCustomName(format("&cShattered Guardian"));
            witherskeleton.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(45.0);
            witherskeleton.setHealth(45);
            witherskeleton.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(10.0);
            witherskeleton.getEquipment().setHelmet(new ItemStack(Material.NETHERITE_HELMET));
            witherskeleton.getEquipment().setChestplate(new ItemStack(Material.NETHERITE_CHESTPLATE));
            witherskeleton.getEquipment().setLeggings(new ItemStack(Material.NETHERITE_LEGGINGS));
            witherskeleton.getEquipment().setBoots(new ItemStack(Material.NETHERITE_BOOTS));
            witherskeleton.getEquipment().setItemInMainHand(caca);
            witherskeleton.getEquipment().setDropChance(EquipmentSlot.HEAD,0);
            witherskeleton.getEquipment().setDropChance(EquipmentSlot.CHEST,0);
            witherskeleton.getEquipment().setDropChance(EquipmentSlot.LEGS,0);
            witherskeleton.getEquipment().setDropChance(EquipmentSlot.FEET,0);
            witherskeleton.getEquipment().setDropChance(EquipmentSlot.HAND,0);
            witherskeleton.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "SHATTER_GUARDIAN"), PersistentDataType.STRING, "SHATTER_GUARDIAN");
        }
        if(en instanceof Chicken){
            e.setCancelled(true);
            en.getLocation().getWorld().spawn(en.getLocation(), Vindicator.class);

        }
        if(en instanceof Slime){
            var slime = (Slime)en;
            if(e.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.SLIME_SPLIT)){
                slime.setCustomName(format("Freezing Slime"));
                slime.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(10.0);
                slime.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "FREEZING_SLIME"), PersistentDataType.STRING, "FREEZING_SLIME");
            }else{
                slime.setCustomName(format("Freezing Slime"));
                slime.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(60);
                slime.setHealth(60);
                slime.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(12);
                slime.setSize(12);
                slime.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "FREEZING_SLIME"), PersistentDataType.STRING, "FREEZING_SLIME");
            }
        }

       if(en instanceof Guardian){
           var guardian = (Guardian)en;
           guardian.setCustomName(format("&6Caca"));
           guardian.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40.0);
           guardian.setHealth(40);
           guardian.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, 1, false, false, false));
       }

        if(en instanceof Cod){
            e.setCancelled(true);
            en.getLocation().getWorld().spawn(en.getLocation(), Guardian.class);
        }
    }

    public void spawnVexClass(Vex entity) {

        int type = new Random().nextInt(4);

        if (type == 1) {
            Mobs.vexExplosive(entity);
        } else if (type == 2) {
            Mobs.vexExecution(entity);
        } else if (type == 3) {
            Mobs.vexScientist(entity);
        } else {
            Mobs.vexMecha(entity);
        }
    }

    public void spawnSkeletonClass(Skeleton skeleton){
        int type = new Random().nextInt(5);
        if(type == 1){
            Mobs.ignitedSkeleton(skeleton);
        }else if(type == 2){
            Mobs.blizzardSkeleton(skeleton);
        }else if(type == 3){
            Mobs.copperSkeleton(skeleton);
        }else if(type == 4){
            Mobs.bullseyeSkeleton(skeleton);
        }else{
            Mobs.poweredSkeleton(skeleton);
        }

    }

}
