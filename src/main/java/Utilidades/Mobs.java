package Utilidades;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.EnderMan;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.boss.BarColor;
import org.bukkit.craftbukkit.v1_18_R1.entity.CraftBee;
import org.bukkit.craftbukkit.v1_18_R1.entity.CraftEnderman;
import org.bukkit.craftbukkit.v1_18_R1.entity.CraftIronGolem;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.Listener;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import tlldos.tll2.TLL2;


import java.lang.reflect.Field;

import static Utilidades.Format.format;

public class Mobs implements Listener{
    TLL2 plugin;
    public Mobs(TLL2 plugin){
        this.plugin = plugin;
    }

    public static void zombiTest(Zombie self) {

        self.setCustomName("Zombi Renegado");
        self.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
        self.getEquipment().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
        self.getEquipment().setItemInMainHand(new ItemStack(Material.NETHERITE_SWORD));
        self.getEquipment().setItemInOffHand(new ItemStack(Material.SHIELD));
        self.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10000,2));
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "TESTZOMBIE"), PersistentDataType.STRING, "TESTZOMBIE");
    }

    public static void tntZomb(TNTPrimed self) {
        self.setYield(5);
        self.setFuseTicks(60);
    }


    ///Mobs de Nieve

    public static  void zombiCongelado(Zombie self){
        self.setCustomName(ChatColor.AQUA + "Zombi Congelado");
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30.0);
        self.setHealth(30);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(6.0);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "ZOMBI_CONGELADO"), PersistentDataType.STRING, "ZOMBI_CONGELADO");
    }

    public static void esqueletoNieve(Skeleton self){
        self.setCustomName(ChatColor.AQUA + "Snow Skeleton");
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30);
        self.setHealth(30);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "ESQUELETO_NIEVE"), PersistentDataType.STRING, "ESQUELETO_NIEVE");
    }

    public static void aranaNieve(Spider self){
        self.setCustomName(ChatColor.AQUA + "Snow Spider");
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30.0);
        self.setHealth(30);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(7.0);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "SNOW_SPIDER"), PersistentDataType.STRING, "SNOW_SPIDER");
    }


    public static void creeperCongelado(Creeper self){
        self.setCustomName(format("&bCreeper Congelado"));
        self.setExplosionRadius(3);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "CREEPER_CONGELADO"), PersistentDataType.STRING, "CREEPER_CONGELADO");
    }


    ///mobs de Jungla
    public static void arañaJungla(Spider self){
        self.setCustomName(format("&2Jungle Spider"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30);
        self.setHealth(30);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(6.0);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "JUNGLE_SPIDER"), PersistentDataType.STRING, "JUNGLE_SPIDER");
    }
    public static void mossZombie(Zombie self){
        self.setCustomName(format("&2Moss Zombie"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
        self.setHealth(20);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(4.0);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "MOSS_ZOMBIE"), PersistentDataType.STRING, "MOSS_ZOMBIE");
    }
    public static void esqueletoMilitar(Skeleton self){
        self.setCustomName(format("&2Esqueleto Militar"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
        self.setHealth(20);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(4.0);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "MILITAR_SKELETON"), PersistentDataType.STRING, "MILITAR_SKELETON");
    }
    public static void mossCreeper(Creeper self){
        self.setCustomName(format("&2Moss Creeper"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
        self.setHealth(20);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "MOSS_CREEPER"), PersistentDataType.STRING, "MOSS_CREEPER");
    }


    ///Mobs de Arena

    public static void zombiMomia(Zombie self){
        self.setCustomName(format("&6Mummy"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
        self.setHealth(20);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "MUMMY_ZOMBIE"), PersistentDataType.STRING, "MUMMY_ZOMBIE");
    }
    public static void esqueletoMomia(Skeleton self){
        self.setCustomName(format("&6Mummy Skeleton"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
        self.setHealth(20);
        self.getEquipment().setItemInMainHand(new ItemStack(Material.STONE_SWORD));
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "MUMMY_SKELETON"), PersistentDataType.STRING, "MUMMY_SKELETON");
    }

    public static void arañaArena(Spider self){
        self.setCustomName(format("&6Sand Spider"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
        self.setHealth(20);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "SAND_SPIDER"), PersistentDataType.STRING, "SAND_SPIDER");
    }

    public static void creeperSandstone(Creeper self){
        self.setCustomName(format("&6Sandstone Creeper"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
        self.setHealth(20);
        self.setFuseTicks(25);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "SANDSTONE_CREEPER"), PersistentDataType.STRING, "SANDSTONE_CREEPER");
    }

    ///Mobs Blighted

    public static void blightedZombi(Zombie self){
        self.setCustomName(format("&4&lBlighted Zombie"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(50);
        self.setHealth(50);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(30);
        self.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100000, 1, false, false, false));
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "BLIGHTED_ZOMBIE"), PersistentDataType.STRING, "BLIGHTED_ZOMBIE");
    }

    public static void blightedSkeleton(Skeleton self){
        ItemStack bow1 = new ItemStack(Material.BOW);
        ItemMeta meta = bow1.getItemMeta();
        meta.setUnbreakable(true);
        meta.addEnchant(Enchantment.ARROW_DAMAGE, 50,true);
        bow1.setItemMeta(meta);
        self.setCustomName(format("&6&lBlighted Skeleton"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(50);
        self.setHealth(50);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "BLIGHTED_SKELETON"), PersistentDataType.STRING, "BLIGHTED_SKELETON");
    }

    public static void blightedWitherSkeleton(WitherSkeleton self){
        self.setCustomName(format("&4&lBlighted Wither Skeleton"));
        ItemStack bow1 = new ItemStack(Material.BOW);
        ItemMeta meta = bow1.getItemMeta();
        meta.setUnbreakable(true);
        meta.addEnchant(Enchantment.ARROW_DAMAGE, 50,true);
        bow1.setItemMeta(meta);
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(50);
        self.setHealth(50);
        self.getEquipment().setItemInMainHand(bow1);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "BLIGHTED_WITHER"), PersistentDataType.STRING, "BLIGHTED_WITHER");
    }

    public static void blightedCreeper(Creeper self){
        self.setCustomName(format("&c&lBlighted Creeper"));
        self.setExplosionRadius(8);
        self.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100000, 1, false, false, false));
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "BLIGHTED_CREEPER"), PersistentDataType.STRING, "BLIGHTED_CREEPER");
    }

    public static void blightedSpider(Spider self){
        self.setCustomName(format("&6&lBlighted Spider"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(50);
        self.setHealth(50);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(35);
        self.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, 1, false, false, false));
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "BLIGHTED_SPIDER"), PersistentDataType.STRING, "BLIGHTED_SPIDER");
    }

    public static void blightedEndermam(Enderman self){
        self.setCustomName(format("&5&lBlighted Enderman"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(75);
        self.setHealth(75);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(30);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "BLIGHTED_ENDERMAN"), PersistentDataType.STRING, "BLIGHTED_ENDERMAN");
        /*CraftEnderman craft = ((CraftEnderman) self);
        EnderMan entityEnderman = craft.getHandle();

        try {
            entityEnderman.goalSelector.addGoal(0, new NearestAttackableTargetGoal<>(entityEnderman, ServerPlayer.class,true, false));

        } catch (Exception e) {
            e.printStackTrace();
            Warn.Mutant(e);
        }*/

    }

    public static void blightedPiglin(PiglinBrute self){
        ItemStack es = new ItemStack(Material.NETHERITE_AXE);
        ItemMeta meta = es.getItemMeta();
        meta.addEnchant(Enchantment.DAMAGE_ALL, 30,true);
        meta.setUnbreakable(true);
        es.setItemMeta(meta);
        self.setCustomName(format("&c&lBlighted Piglin Brute"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(65);
        self.setHealth(65);
        self.getEquipment().setItemInMainHand(es);
        self.getEquipment().setDropChance(EquipmentSlot.HAND, 0);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "BLIGHTED_BRUTE"), PersistentDataType.STRING, "BLIGHTED_BRUTE");
    }

    public static void blightedPhantom(Phantom self){
        self.setCustomName(format("&6&lBlighted Phantom"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(55);
        self.setHealth(55);
        self.setSize(4);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "BLIGHTED_PHANTOM"), PersistentDataType.STRING, "BLIGHTED_PHANTOM");
    }

    public static void blightedGhast(Ghast self){
        self.setCustomName(format("&4&lBlighted Ghast"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(55);
        self.setHealth(55);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "BLIGHTED_GHAST"), PersistentDataType.STRING, "BLIGHTED_GHAST");
    }
    public static void blightedWitch(Witch self){
        self.setCustomName(format("&6&lBlighted Witch"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(45);
        self.setHealth(45);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "BLIGHTED_WITCH"), PersistentDataType.STRING, "BLIGHTED_WITCH");
    }




    public static void ghastDesert(Ghast self) {

        self.setCustomName(format("&e&lDesert Ghast"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(50.0);
        self.setHealth(50);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "DESERT_GHAST"), PersistentDataType.STRING, "DESERT_GHAST");
    }


    public static void riftedGhast(Ghast self){
        self.setCustomName(format("&b&lDimensional Ghost"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(50);
        self.setHealth(50);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "DIMEN_GHOST"), PersistentDataType.STRING, "DIMEN_GHOST");
    }
    public static void riftedCreeper(Creeper self){
        self.setCustomName(format("&b&lDimensional Dynamite"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(50);
        self.setHealth(50);
        self.setPowered(true);
        self.setExplosionRadius(10);
        self.setMaxFuseTicks(20);
        self.setFuseTicks(20);

    }
    public static void riftedMage(Illusioner self){
        self.setCustomName(format("&b&lDimensional Mage"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(50);
        self.setHealth(50);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "DIMEN_MAGE"), PersistentDataType.STRING, "DIMEN_MAGE");
    }
    public static void riftedShulker(Shulker self){
        self.setCustomName(format("&b&lDimensional Shulker"));
        self.setColor(DyeColor.BLUE);
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(1);
        self.setHealth(1);
        self.setRemoveWhenFarAway(true);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "DIMEN_SHULKER"), PersistentDataType.STRING, "DIMEN_SHULKER");
    }
    public static void riftedWither(WitherSkeleton self){
        ItemStack b = new ItemStack(Material.BOW);
        ItemMeta meta = b.getItemMeta();
        meta.addEnchant(Enchantment.ARROW_DAMAGE,50,true);
        meta.addEnchant(Enchantment.ARROW_FIRE,1,true);
        meta.addEnchant(Enchantment.ARROW_KNOCKBACK,50,true);
        meta.setUnbreakable(true);
        b.setItemMeta(meta);

        self.setCustomName(format("&b&lDimensional Wither"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(50);
        self.getEquipment().setItemInMainHand(new ItemStack(Material.BOW));
        self.setHealth(50);
        self.getEquipment().setItemInMainHand(b);
        self.getEquipment().setDropChance(EquipmentSlot.HAND,0);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "DIMEN_WITHERSK"), PersistentDataType.STRING, "DIMEN_WITHERSK");
    }

    public static void evokerExplosive(Evoker self){
        self.setCustomName(format("&c&lExplosive Evoker"));

        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "EXPLOSIVE_EVOKER"), PersistentDataType.STRING, "EXPLOSIVE_EVOKER");
    }
    public static void evokerFire(Evoker self){
        self.setCustomName(format("&6&lHellfire Evoker"));
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "FIRE_EVOKER"), PersistentDataType.STRING, "FIRE_EVOKER");
    }
    public static void evokerFreeze(Evoker self){
        self.setCustomName(format("&c&lBlizzard Evoker"));
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "FREEZE_EVOKER"), PersistentDataType.STRING, "FREEZE_EVOKER");
    }
    public static void evokerhex(Evoker self){
        self.setCustomName(format("&4&lHex Evoker"));
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "HEX_EVOKER"), PersistentDataType.STRING, "HEX_EVOKER");
    }





    public static void cinder(Vex self){
        self.setCustomName(format("&6&lCinder"));
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(8);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "CINDER"), PersistentDataType.STRING, "CINDER");
    }
    public static void jengu(Vex self){
        self.setCustomName(format("&1&lJengu"));
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(8);
        self.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(1.3);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "JENGU"), PersistentDataType.STRING, "JENGU");
    }
    public static void djiin(Vex self){
        self.setCustomName(format("&f&lDjiin"));
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(8);
        self.getAttribute(Attribute.GENERIC_ATTACK_KNOCKBACK).setBaseValue(2.3);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "DJIIN"), PersistentDataType.STRING, "DJIIN");
    }
    public static void grue(Vex self){
        self.setCustomName(format("&8&lGrue"));
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(8);
        self.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(1);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "GRUE"), PersistentDataType.STRING, "GRUE");
    }



    public static void lavaGolem(IronGolem self){
        self.setCustomName(format("&6&lLava Golem"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(150);
        self.setHealth(150);
        self.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE,Integer.MAX_VALUE,0, false, false, false));
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class),"LAVA_GOLEM"),PersistentDataType.STRING, "LAVA_GOLEM");
        CraftIronGolem craft = ((CraftIronGolem) self);
        net.minecraft.world.entity.animal.IronGolem entityGolem = craft.getHandle();
            try {
                entityGolem.targetSelector.addGoal(0, new MeleeAttackGoal(entityGolem, 1.0D, true));
                entityGolem.goalSelector.addGoal(0, new NearestAttackableTargetGoal<>(entityGolem, ServerPlayer.class,true, false));
            } catch (Exception e) {
                e.printStackTrace();
                Warn.Mutant(e);
            }
    }

    public static void vortice(Creeper self){
        self.setCustomName(format("&bVorticé"));
        self.setExplosionRadius(40);
        self.setMaxFuseTicks(10);
        self.setFuseTicks(10);
        self.setPowered(true);
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(50);
        self.setHealth(50);
        self.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(1.4);
        self.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,Integer.MAX_VALUE, 0, false, false, false));
    }

    public static void cosmicSilver(Silverfish self){
        self.setCustomName(format("&d&lCosmic Silverfish"));
        self.setHealth(1);
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(1);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(20.0);
        self.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(1.2);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class),"COSMIC_SILVERFISH"), PersistentDataType.STRING, "COSMIC_SILVERFISH");
    }

    public static void tyranyWither(Wither self){
        self.setCustomName(format("&6&k||| &c&lTyrant Wither &6&k|||"));
        self.setInvulnerableTicks(500);
        self.getBossBar().setColor(BarColor.RED);
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(500);
        self.setHealth(500);
        self.setRemoveWhenFarAway(true);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class),"TYRANT_WITHER"), PersistentDataType.STRING, "TYRANT_WITHER");
    }





























    public static void hellfireCreeper(Creeper self){
        self.setCustomName(format("&6Hellfire Creeper"));
        self.setPowered(true);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "HELLFIRE_CREEPER"), PersistentDataType.STRING, "HELLFIRE_CREEPER");
    }

    public static void hiveMind(Creeper self){
        self.setCustomName(format("&6Hive Mind"));
        self.setMaxFuseTicks(1);
        self.setFuseTicks(1);
        self.setExplosionRadius(1);
        self.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, 2, false, false, false));
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "HIVE_MIND"), PersistentDataType.STRING, "HIVE_MIND");
    }
    public static void thePlague(Bee self){
        self.setCustomName(format("&2Plague"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30);
        self.setHealth(30);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(10);


        CraftBee craft = ((CraftBee) self);
        net.minecraft.world.entity.animal.Bee entityBee = craft.getHandle();

        try {
            entityBee.targetSelector.addGoal(0, new MeleeAttackGoal(entityBee, 1.0D, true));
            entityBee.goalSelector.addGoal(0, new NearestAttackableTargetGoal<>(entityBee, ServerPlayer.class,true, false));
        } catch (Exception e) {
            e.printStackTrace();
            Warn.Mutant(e);
        }
    }



    public static void piglinBrutedim(PiglinBrute self){

        ItemStack ax = new ItemStack(Material.NETHERITE_AXE);
        ItemMeta meta = ax.getItemMeta();
        meta.addEnchant(Enchantment.DAMAGE_ALL,10, true);
        meta.setUnbreakable(true);
        ax.setItemMeta(meta);

        self.setCustomName(format("&6Dimensional Brute"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(50.0);
        self.setHealth(50);

        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(10.0);
        self.getEquipment().setItemInMainHand(ax);
        self.getEquipment().setDropChance(EquipmentSlot.HAND, 0);
        self.getEquipment().setDropChance(EquipmentSlot.HEAD, 0);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "DIMENSIONAL_BRUTE"), PersistentDataType.STRING, "DIMENSIONAL_BRUTE");

    }








    //arañas
    public static void plagueSpider(Spider self){
        self.setCustomName(format("&2Plague Spider"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40);
        self.setHealth(40);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(16.0);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "PLAGUE_SPIDER"), PersistentDataType.STRING, "PLAGUE_SPIDER");
    }
    public static void solarScorpion(Spider self){
        self.setCustomName(format("&6Solar Scorpion"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(50);
        self.setHealth(40);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(14.0);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "SOLAR_SCORPION"), PersistentDataType.STRING, "SOLAR_SCORPION");
    }
    public static void agileTarantule(Spider self){
        self.setCustomName(format("&bTarantula Rompevientos"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40);
        self.setHealth(40);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(14.0);
        self.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10000000,2, false, false, false));
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "AGILE_SPIDER"), PersistentDataType.STRING, "AGILE_SPIDER");
    }
    public static void interdimensionalVisitor(Spider self){
        self.setCustomName(format("&5Visitante Interdimensional"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40);
        self.setHealth(40);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(14.0);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "INTER_SPIDER"), PersistentDataType.STRING, "INTER_SPIDER");
    }








    ///mobs del laboratorio
    public static void mechaZombie(Zombie self){
        self.setCustomName(format("&6Mecha Undead"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30.0);
        self.setHealth(30);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(10.0);
        self.getAttribute(Attribute.GENERIC_ATTACK_KNOCKBACK).setBaseValue(5.0);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "MECHA_ZOMBIE"), PersistentDataType.STRING, "MECHA_ZOMBIE");
    }

    public static void exoGolem(IronGolem self){
        self.setCustomName(format("&6Exo Golem"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(50.0);
        self.setHealth(50);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "EXO_GOLEM"), PersistentDataType.STRING, "EXO_GOLEM");
        ///pongan que el golem este enojado porfavor

        CraftIronGolem craft = ((CraftIronGolem) self);
        net.minecraft.world.entity.animal.IronGolem entityGolem = craft.getHandle();

        try {
            entityGolem.targetSelector.addGoal(0, new MeleeAttackGoal(entityGolem, 1.0D, true));
            entityGolem.goalSelector.addGoal(0, new NearestAttackableTargetGoal<>(entityGolem, ServerPlayer.class,true, false));
        } catch (Exception e) {
            e.printStackTrace();
            Warn.Mutant(e);
        }
    }

    public static void labSilver(Silverfish self){
        self.setCustomName(format("&4Lab Silverfish"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(10.0);
        self.setHealth(10);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(5.0);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "LAB_SILVERFISH"), PersistentDataType.STRING, "LAB_SILVERFISH");
    }

    public static void plagueEntity(AreaEffectCloud self){
        self.setColor(Color.GREEN);
        self.setBasePotionData(new PotionData(PotionType.POISON));
        self.addCustomEffect(new PotionEffect(PotionEffectType.WITHER, 200, 3), true);
        self.addCustomEffect(new PotionEffect(PotionEffectType.WEAKNESS, 200, 3), true);
        self.addCustomEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 200, 3), true);
    }



    public static void madScientist(Pillager self){
        ItemStack flecha1 = new ItemStack(Material.TIPPED_ARROW, 64);
        PotionMeta flecha1efect = (PotionMeta) flecha1.getItemMeta();
        flecha1efect.setBasePotionData(new PotionData(PotionType.SLOWNESS));
        flecha1efect.addCustomEffect(new PotionEffect(PotionEffectType.SLOW, 400, 1), true);
        flecha1efect.addCustomEffect(new PotionEffect(PotionEffectType.POISON, 400, 1), true);
        flecha1efect.addCustomEffect(new PotionEffect(PotionEffectType.CONFUSION, 400, 1), true);
        flecha1.setItemMeta(flecha1efect);

        ItemStack ballesta1 = new ItemStack(Material.CROSSBOW);
        ItemMeta ballesta1m = ballesta1.getItemMeta();
        ballesta1m.setUnbreakable(true);
        ballesta1m.addEnchant(Enchantment.QUICK_CHARGE, 2, true);
        ballesta1m.addEnchant(Enchantment.PIERCING, 1, true);
        ballesta1.setItemMeta(ballesta1m);

        self.setCustomName(format("&cMad Scientist"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30.0);
        self.setHealth(30);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(10.0);
        self.getAttribute(Attribute.GENERIC_ATTACK_KNOCKBACK).setBaseValue(1.0);
        self.getEquipment().setItemInMainHand(ballesta1);
        self.getEquipment().setItemInOffHand(flecha1);
        self.getEquipment().setDropChance(EquipmentSlot.HAND, 0);
        self.getEquipment().setDropChance(EquipmentSlot.OFF_HAND, 0);

        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "MAD_SCIENTIST"), PersistentDataType.STRING, "MAD_SCIENTIST");

    }


    ///vexes que se me ocurrieron
    public static void vexExplosive(Vex self){
        self.setCustomName(format("&cExplosive Vex"));
        self.getEquipment().setItemInMainHand(new ItemStack(Material.TNT));
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "VEX_EXPLOSIVE"), PersistentDataType.STRING, "VEX_EXPLOSIVE");
        self.getEquipment().setDropChance(EquipmentSlot.HAND, 0);
        self.getEquipment().setDropChance(EquipmentSlot.HEAD, 0);
    }

    public static void vexScientist(Vex self){

        ItemStack caca = new ItemStack(Material.BLAZE_ROD);
        ItemMeta meta = caca.getItemMeta();
        meta.addEnchant(Enchantment.DAMAGE_ALL,5,true);
        caca.setItemMeta(meta);

        self.setCustomName(format("&cScientist Vex"));
        self.getEquipment().setItemInMainHand(caca);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "VEX_SCIENTIST"), PersistentDataType.STRING, "VEX_SCIENTIST");
        self.getEquipment().setDropChance(EquipmentSlot.HAND, 0);
        self.getEquipment().setDropChance(EquipmentSlot.HEAD, 0);
    }

    public static void vexMecha(Vex self){

        ItemStack caca = new ItemStack(Material.DIAMOND_HOE);
        ItemMeta meta = caca.getItemMeta();
        meta.addEnchant(Enchantment.DAMAGE_ALL,10,true);
        caca.setItemMeta(meta);

        self.setCustomName(format("&cMecha Vex"));
        self.getEquipment().setItemInMainHand(caca);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "VEX_MECHA"), PersistentDataType.STRING, "VEX_MECHA");
        self.getEquipment().setDropChance(EquipmentSlot.HAND, 0);
        self.getEquipment().setDropChance(EquipmentSlot.HEAD, 0);
    }

    public static void vexExecution(Vex self){

        ItemStack caca = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta meta = caca.getItemMeta();
        meta.addEnchant(Enchantment.DAMAGE_ALL,15,true);
        caca.setItemMeta(meta);

        self.setCustomName(format("&cExecutioner Vex"));
        self.getEquipment().setItemInMainHand(caca);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "VEX_EXECUTION"), PersistentDataType.STRING, "VEX_EXECUTION");
        self.getEquipment().setDropChance(EquipmentSlot.HAND, 0);
        self.getEquipment().setDropChance(EquipmentSlot.HEAD, 0);
    }



    ///esqueletos randoms
    public static void ignitedSkeleton(Skeleton self){
        ItemStack ai = new ItemStack(Material.BOW);
        ItemMeta meta = ai.getItemMeta();
        meta.addEnchant(Enchantment.ARROW_DAMAGE, 20,true);
        ai.setItemMeta(meta);
        self.setCustomName(format("&6Ignited &cSkeleton"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30.0);
        self.setHealth(30);
        self.getEquipment().setItemInMainHand(ai);
        self.getEquipment().setHelmet(new ItemStack(Material.GLASS));
        self.getEquipment().setDropChance(EquipmentSlot.HAND, 0);
        self.getEquipment().setDropChance(EquipmentSlot.HEAD, 0);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "IGNITED_SKELETON"), PersistentDataType.STRING, "IGNITED_SKELETON");
    }

    public static void blizzardSkeleton(Skeleton self){
        ItemStack ai = new ItemStack(Material.BOW);
        ItemMeta meta = ai.getItemMeta();
        meta.addEnchant(Enchantment.ARROW_DAMAGE, 30,true);
        meta.addEnchant(Enchantment.ARROW_KNOCKBACK, 20,true);
        ai.setItemMeta(meta);
        self.setCustomName(format("&bBlizzard &cSkeleton"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30.0);
        self.setHealth(30);
        self.getEquipment().setItemInMainHand(ai);
        self.getEquipment().setHelmet(new ItemStack(Material.PACKED_ICE));
        self.getEquipment().setDropChance(EquipmentSlot.HAND, 0);
        self.getEquipment().setDropChance(EquipmentSlot.HEAD, 0);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "BLIZZARD_SKELETON"), PersistentDataType.STRING, "BLIZZARD_SKELETON");
    }

    public static void copperSkeleton(Skeleton self){
        ItemStack ai = new ItemStack(Material.BOW);
        ItemMeta meta = ai.getItemMeta();
        meta.addEnchant(Enchantment.ARROW_DAMAGE, 20,true);
        meta.addEnchant(Enchantment.ARROW_FIRE, 20,true);
        ai.setItemMeta(meta);
        self.setCustomName(format("&eCopper &cSkeleton"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30.0);
        self.setHealth(30);
        self.getEquipment().setItemInMainHand(ai);
        self.getEquipment().setHelmet(new ItemStack(Material.LIGHTNING_ROD));
        self.getEquipment().setDropChance(EquipmentSlot.HAND, 0);
        self.getEquipment().setDropChance(EquipmentSlot.HEAD, 0);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "COPPER_SKELETON"), PersistentDataType.STRING, "COPPER_SKELETON");
    }

    public static void bullseyeSkeleton(Skeleton  self){
        ItemStack ai = new ItemStack(Material.BOW);
        ItemMeta meta = ai.getItemMeta();
        meta.addEnchant(Enchantment.ARROW_DAMAGE, 99,true);
        ai.setItemMeta(meta);
        self.setCustomName(format("&4Bullseye &cSkeleton"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(50.0);
        self.setHealth(50);
        self.getEquipment().setItemInMainHand(ai);
        self.getEquipment().setHelmet(new ItemStack(Material.OBSERVER));
        self.getEquipment().setDropChance(EquipmentSlot.HAND, 0);
        self.getEquipment().setDropChance(EquipmentSlot.HEAD, 0);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "BULLSEYE_SKELETON"), PersistentDataType.STRING, "BULLSEYE_SKELETON");
    }
    public static void poweredSkeleton(Skeleton self){
        ItemStack ai = new ItemStack(Material.BOW);
        ItemMeta meta = ai.getItemMeta();
        meta.addEnchant(Enchantment.ARROW_DAMAGE, 20,true);
        ai.setItemMeta(meta);
        self.setCustomName(format("&5Powered &cSkeleton"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(50.0);
        self.setHealth(50);
        self.getEquipment().setItemInMainHand(ai);
        self.getEquipment().setHelmet(new ItemStack(Material.TINTED_GLASS));
        self.getEquipment().setDropChance(EquipmentSlot.HAND, 0);
        self.getEquipment().setDropChance(EquipmentSlot.HEAD, 0);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "POWERED_SKELETON"), PersistentDataType.STRING, "POWERED_SKELETON");
    }




    ///cacaplayo


    public static void variante1Tier(Zombie self){
        self.setCustomName(format("&6Zombi Tier I"));
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(7.0);
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30.0);
        self.setHealth(30);
        self.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, 0, false, false, false));
    }
    public static void variante2Tier(Zombie self){
        self.setCustomName(format("&6Zombi Tier II"));
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(11.0);
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40.0);
        self.setHealth(30);
        self.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, 1, false, false, false));

    }
    public static void variante3Tier(Zombie self){
        self.setCustomName(format("&6Zombi Tier III"));
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(7.0);
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(49.0);
        self.setHealth(30);
        self.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, 1, false, false, false));
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "TIER_3"), PersistentDataType.STRING, "TIER_3");

    }
    public static void tntMonster(Zombie self){
        self.setCustomName(format("&6TNT Monster"));
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(7.0);
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30.0);
        self.setHealth(30);
        self.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, 0, false, false, false));
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "TNT_MONSTER"), PersistentDataType.STRING, "TNT_MONSTER");
    }







}
