package Utilities;

import jdk.jshell.execution.Util;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.PlayerDisguise;
import net.minecraft.world.entity.EntityInsentient;
import net.minecraft.world.entity.ai.goal.PathfinderGoalMeleeAttack;
import net.minecraft.world.entity.ai.goal.PathfinderGoalSelector;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalNearestAttackableTarget;
import net.minecraft.world.entity.animal.EntityBee;
import net.minecraft.world.entity.animal.EntityIronGolem;
import net.minecraft.world.entity.monster.EntityEnderman;
import net.minecraft.world.entity.player.EntityHuman;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftBee;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftEnderman;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftIronGolem;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.Listener;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import tlldos.tll2.TLL2;


import java.lang.reflect.Field;
import java.util.ArrayList;

import static Utilities.Format.format;

public class Mobs implements Listener {
    TLL2 plugin;

    public Mobs(TLL2 plugin) {
        this.plugin = plugin;
    }

    public static void tntZomb(TNTPrimed self) {
        self.setYield(5);
        self.setFuseTicks(60);
    }


    ///Mobs de Nieve

    public static void zombiCongelado(Zombie self) {
        self.setCustomName(ChatColor.AQUA + "Zombi Congelado");
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(55);
        self.setHealth(55);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(20);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "ICE_MOB"), PersistentDataType.STRING, "ICE_MOB");
    }

    public static void esqueletoNieve(Skeleton self) {
        self.setCustomName(ChatColor.AQUA + "Snow Skeleton");
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(55);
        self.setHealth(55);
        self.getEquipment().setItemInMainHand(new ItemBuilder(Material.BOW).addEnchantment(Enchantment.ARROW_DAMAGE, 50).build());
        self.getEquipment().setDropChance(EquipmentSlot.HAND, 0);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "ICE_SKELETON"), PersistentDataType.STRING, "ICE_SKELETON");
    }

    public static void spiderNieve(Spider self) {
        self.setCustomName(ChatColor.AQUA + "Snow Spider");
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(55);
        self.setHealth(55);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(20);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "ICE_MOB"), PersistentDataType.STRING, "ICE_MOB");
    }

    public static void creeperCongelado(Creeper self) {
        self.setCustomName(format("&bCreeper Congelado"));
        self.setPowered(true);
        self.setExplosionRadius(6);
        self.setMaxFuseTicks(20);
        self.setFuseTicks(20);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "ICE_CREEPER"), PersistentDataType.STRING, "ICE_CREEPER");
    }

    public static void yeti(IronGolem self) {
        self.setCustomName(format("&b&lYeti"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(120);
        self.setHealth(120);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(45);
        self.getPersistentDataContainer().set(Utils.key("YETI"), PersistentDataType.STRING, "YETI");
        CraftIronGolem craft = ((CraftIronGolem) self);
        EntityIronGolem entityIronGolem = craft.getHandle();
        try {
            Class<? extends EntityInsentient> cl = EntityInsentient.class;
            Field gf = cl.getDeclaredField("bP");
            gf.setAccessible(true);
            PathfinderGoalSelector goal = (PathfinderGoalSelector) gf.get(entityIronGolem);
            goal.a(0, new PathfinderGoalMeleeAttack(entityIronGolem, 1.0D, true));

            Field tf = cl.getDeclaredField("bQ");
            tf.setAccessible(true);

            PathfinderGoalSelector target = (PathfinderGoalSelector) tf.get(entityIronGolem);
            target.a(0, new PathfinderGoalNearestAttackableTarget<>(entityIronGolem, EntityHuman.class, 10, true, false, null));
        } catch (Exception e) {
            e.printStackTrace();
            Warn.Mutant(e);
        }
    }

    public static void freezeSlime(Slime self) {
        self.setCustomName(format("&b&lFreeze Slime"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(60);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(20);
        self.getPersistentDataContainer().set(Utils.key("FREEZING_SLIME"), PersistentDataType.STRING, "FREEZE_SLIME");
    }


    ///mobs de Jungla
    public static void spiderJungla(Spider self) {
        self.setCustomName(format("&2Jungle Spider"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(55);
        self.setHealth(55);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(20);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "JUNGLE_MOB"), PersistentDataType.STRING, "JUNGLE_MOB");
    }

    public static void mossZombie(Zombie self) {
        self.setCustomName(format("&2Moss Zombie"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(55);
        self.setHealth(55);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(20);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "JUNGLE_MOB"), PersistentDataType.STRING, "JUNGLE_MOB");
    }

    public static void esqueletoMilitar(Skeleton self) {
        self.setCustomName(format("&2Vines Skeleton"));
        ItemStack flecha1 = new ItemStack(Material.TIPPED_ARROW, 64);
        PotionMeta flecha1efect = (PotionMeta) flecha1.getItemMeta();
        flecha1efect.setBasePotionData(new PotionData(PotionType.POISON));
        flecha1efect.addCustomEffect(new PotionEffect(PotionEffectType.POISON, 600, 9), true);
        flecha1.setItemMeta(flecha1efect);
        self.getEquipment().setItemInMainHand(new ItemBuilder(Material.BOW).addEnchantment(Enchantment.ARROW_DAMAGE, 50).build());
        self.getEquipment().setDropChance(EquipmentSlot.HAND, 0);
        self.getEquipment().setItemInOffHand(flecha1);
        self.getEquipment().setDropChance(EquipmentSlot.OFF_HAND, 0);
    }

    public static void mossCreeper(Creeper self) {
        self.setCustomName(format("&2Moss Creeper"));
        self.setPowered(true);
        self.setExplosionRadius(6);
        self.setMaxFuseTicks(20);
        self.setFuseTicks(20);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "MOSS_CREEPER"), PersistentDataType.STRING, "MOSS_CREEPER");
    }

    public static void infectedVex(Vex self) {
        self.setCustomName(format("&a&lInfected Vex"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30);
        self.setHealth(30);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(21);
        self.getEquipment().setItemInMainHand(new ItemBuilder(Material.DIAMOND_AXE).addEnchantment(Enchantment.DAMAGE_ALL, 10).build());
        self.getEquipment().setDropChance(EquipmentSlot.HAND, 0);
        self.getPersistentDataContainer().set(Utils.key("INFECTED_VEX"), PersistentDataType.STRING, "INFECTED_VEX");
    }

    public static void venomousArcher(WitherSkeleton self) {
        self.setCustomName(format("&2&lVenomous Archer"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(60);
        self.setHealth(60);
        ItemStack flecha1 = new ItemStack(Material.TIPPED_ARROW, 64);
        PotionMeta flecha1efect = (PotionMeta) flecha1.getItemMeta();
        flecha1efect.setBasePotionData(new PotionData(PotionType.POISON));
        flecha1efect.addCustomEffect(new PotionEffect(PotionEffectType.WITHER, 600, 9), true);
        flecha1efect.addCustomEffect(new PotionEffect(PotionEffectType.SLOW, 600, 9), true);
        flecha1efect.addCustomEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 600, 9), true);
        flecha1efect.addCustomEffect(new PotionEffect(PotionEffectType.BLINDNESS, 600, 9), true);
        flecha1.setItemMeta(flecha1efect);
        self.getEquipment().setItemInMainHand(new ItemBuilder(Material.BOW).addEnchantment(Enchantment.ARROW_DAMAGE, 50).build());
        self.getEquipment().setItemInOffHand(flecha1);
        self.getEquipment().setDropChance(EquipmentSlot.HAND, 0);
        self.getEquipment().setDropChance(EquipmentSlot.OFF_HAND, 0);
    }


    ///Mobs de Arena

    public static void zombiMomia(Zombie self) {
        self.setCustomName(format("&6Mummy"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(55);
        self.setHealth(55);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(20);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "SAND_MOB"), PersistentDataType.STRING, "SAND_MOB");
    }

    public static void esqueletoMomia(Skeleton self) {
        self.setCustomName(format("&6Mummy Skeleton"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(55);
        self.setHealth(55);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(20);
        self.getEquipment().setItemInMainHand(new ItemBuilder(Material.DIAMOND_SWORD).addEnchantment(Enchantment.DAMAGE_ALL, 50).build());
        self.getEquipment().setDropChance(EquipmentSlot.HAND, 0);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "SAND_MOB"), PersistentDataType.STRING, "SAND_MOB");
    }

    public static void spiderArena(Spider self) {
        self.setCustomName(format("&6Sand Spider"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(55);
        self.setHealth(55);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(20);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "SAND_MOB"), PersistentDataType.STRING, "SAND_MOB");
    }

    public static void creeperSandstone(Creeper self) {
        self.setCustomName(format("&6Sandstone Creeper"));
        self.setPowered(true);
        self.setExplosionRadius(6);
        self.setMaxFuseTicks(20);
        self.setFuseTicks(20);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "SANDSTONE_CREEPER"), PersistentDataType.STRING, "SANDSTONE_CREEPER");
    }

    public static void ghastDesert(Ghast self) {
        self.setCustomName(format("&e&lDesert Ghast"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(50.0);
        self.setHealth(50);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "DESERT_GHAST"), PersistentDataType.STRING, "DESERT_GHAST");
    }

    public static void desertScorpion(Spider self) {
        self.setCustomName(format("&e&lDesert Scorpion"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(65);
        self.setHealth(65);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(30);
        self.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1, false, false, false));
        self.getPersistentDataContainer().set(Utils.key("DESERT_SCORPION"), PersistentDataType.STRING, "DESERT_SCORPION");
    }


    ///Mobs Blighted

    public static void blightedZombi(Zombie self) {
        self.setCustomName(format("&4&lBlighted Zombie"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(150);
        self.setHealth(150);
        self.setBaby(false);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(50);
        self.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100000, 2, false, false, false));
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "BLIGHTED_ZOMBIE"), PersistentDataType.STRING, "BLIGHTED_ZOMBIE");
    }

    public static void blightedSkeleton(Skeleton self) {
        ItemStack bow1 = new ItemStack(Material.BOW);
        ItemMeta meta = bow1.getItemMeta();
        meta.setUnbreakable(true);
        meta.addEnchant(Enchantment.ARROW_DAMAGE, 150, true);
        bow1.setItemMeta(meta);
        self.setCustomName(format("&6&lBlighted Skeleton"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(80);
        self.setHealth(80);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "BLIGHTED_SKELETON"), PersistentDataType.STRING, "BLIGHTED_SKELETON");
    }

    public static void blightedWitherSkeleton(WitherSkeleton self) {
        self.setCustomName(format("&4&lBlighted Wither Skeleton"));
        ItemStack bow1 = new ItemStack(Material.BOW);
        ItemMeta meta = bow1.getItemMeta();
        meta.setUnbreakable(true);
        meta.addEnchant(Enchantment.ARROW_DAMAGE, 150, true);
        bow1.setItemMeta(meta);
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(80);
        self.setHealth(80);
        self.getEquipment().setItemInMainHand(bow1);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "BLIGHTED_WITHER"), PersistentDataType.STRING, "BLIGHTED_WITHER");
    }

    public static void blightedCreeper(Creeper self) {
        self.setCustomName(format("&c&lBlighted Creeper"));
        self.setExplosionRadius(15);
        self.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100000, 2, false, false, false));
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "BLIGHTED_CREEPER"), PersistentDataType.STRING, "BLIGHTED_CREEPER");
    }

    public static void blightedSpider(Spider self) {
        self.setCustomName(format("&6&lBlighted Spider"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(90);
        self.setHealth(90);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(55);
        self.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, 2, false, false, false));
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "BLIGHTED_SPIDER"), PersistentDataType.STRING, "BLIGHTED_SPIDER");
    }

    public static void blightedEndermam(Enderman self) {
        self.setCustomName(format("&5&lBlighted Enderman"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(95);
        self.setHealth(95);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(60);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "BLIGHTED_ENDERMAN"), PersistentDataType.STRING, "BLIGHTED_ENDERMAN");
        CraftEnderman craft = ((CraftEnderman) self);
        EntityEnderman entityEnderman = craft.getHandle();
        try {
            Class<? extends EntityInsentient> cl = EntityInsentient.class;
            Field gf = cl.getDeclaredField("bP");
            gf.setAccessible(true);
            PathfinderGoalSelector goal = (PathfinderGoalSelector) gf.get(entityEnderman);
            goal.a(0, new PathfinderGoalMeleeAttack(entityEnderman, 1.0D, true));

            Field tf = cl.getDeclaredField("bQ");
            tf.setAccessible(true);

            PathfinderGoalSelector target = (PathfinderGoalSelector) tf.get(entityEnderman);
            target.a(0, new PathfinderGoalNearestAttackableTarget<>(entityEnderman, EntityHuman.class, 10, true, false, null));
        } catch (Exception e) {
            e.printStackTrace();
            Warn.Mutant(e);
        }

    }

    public static void blightedPiglin(PiglinBrute self) {
        ItemStack es = new ItemStack(Material.NETHERITE_AXE);
        ItemMeta meta = es.getItemMeta();
        meta.addEnchant(Enchantment.DAMAGE_ALL, 90, true);
        meta.setUnbreakable(true);
        es.setItemMeta(meta);
        self.setCustomName(format("&c&lBlighted Piglin Brute"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(65);
        self.setHealth(65);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(35);
        self.getEquipment().setItemInMainHand(es);
        self.getEquipment().setDropChance(EquipmentSlot.HAND, 0);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "BLIGHTED_BRUTE"), PersistentDataType.STRING, "BLIGHTED_BRUTE");
    }

    public static void blightedPhantom(Phantom self) {
        self.setCustomName(format("&6&lBlighted Phantom"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(85);
        self.setHealth(85);
        self.setSize(7);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(60);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "BLIGHTED_PHANTOM"), PersistentDataType.STRING, "BLIGHTED_PHANTOM");
    }

    public static void blightedGhast(Ghast self) {
        self.setCustomName(format("&4&lBlighted Ghast"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(55);
        self.setHealth(55);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "BLIGHTED_GHAST"), PersistentDataType.STRING, "BLIGHTED_GHAST");
    }

    public static void blightedWitch(Witch self) {
        self.setCustomName(format("&6&lBlighted Witch"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(45);
        self.setHealth(45);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "BLIGHTED_WITCH"), PersistentDataType.STRING, "BLIGHTED_WITCH");
    }


    public static void riftedGhast(Ghast self) {
        self.setCustomName(format("&b&lDimensional Ghost"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(50);
        setCustomMobcap(self, 3, 1.10, 24, 20, true);
        self.setHealth(50);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "DIMEN_GHOST"), PersistentDataType.STRING, "DIMEN_GHOST");
    }

    public static void riftedCreeper(Creeper self) {
        self.setCustomName(format("&b&lDimensional Dynamite"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(50);
        self.setHealth(50);
        self.setPowered(true);
        self.setExplosionRadius(10);
        self.setMaxFuseTicks(20);
        self.setFuseTicks(20);
        self.getPersistentDataContainer().set(Utils.key("DIMEN_DYNAM"), PersistentDataType.STRING, "DIMEN_DYNAM");
    }

    public static void riftedMage(Illusioner self) {
        self.setCustomName(format("&b&lDimensional Mage"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(50);
        setCustomMobcap(self, 7, 1.10, 24, 20, true);
        self.setHealth(50);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "DIMEN_MAGE"), PersistentDataType.STRING, "DIMEN_MAGE");
    }

    public static void riftedShulker(Shulker self) {
        self.setCustomName(format("&b&lDimensional Shulker"));
        self.setColor(DyeColor.BLUE);
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40);
        self.setHealth(40);
        setCustomMobcap(self, 7, 1.10, 24, 20, true);
        self.setRemoveWhenFarAway(true);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "DIMEN_SHULKER"), PersistentDataType.STRING, "DIMEN_SHULKER");
    }

    public static void riftedWither(WitherSkeleton self) {
        ItemStack b = new ItemStack(Material.BOW);
        ItemMeta meta = b.getItemMeta();
        meta.addEnchant(Enchantment.ARROW_DAMAGE, 50, true);
        meta.addEnchant(Enchantment.ARROW_FIRE, 1, true);
        meta.addEnchant(Enchantment.ARROW_KNOCKBACK, 50, true);
        meta.setUnbreakable(true);
        b.setItemMeta(meta);

        self.setCustomName(format("&b&lDimensional Wither"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(50);
        self.getEquipment().setItemInMainHand(new ItemStack(Material.BOW));
        self.setHealth(50);
        self.getEquipment().setItemInMainHand(b);
        self.getEquipment().setDropChance(EquipmentSlot.HAND, 0);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "DIMEN_WITHERSK"), PersistentDataType.STRING, "DIMEN_WITHERSK");
    }

    public static void evokerExplosive(Evoker self) {
        self.setCustomName(format("&c&lChaotic Conjurer"));

        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "EXPLOSIVE_EVOKER"), PersistentDataType.STRING, "EXPLOSIVE_EVOKER");
    }

    public static void evokerFire(Evoker self) {
        self.setCustomName(format("&6&lHellvoker"));
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "FIRE_EVOKER"), PersistentDataType.STRING, "FIRE_EVOKER");
    }

    public static void evokerFreeze(Evoker self) {
        self.setCustomName(format("&b&lFreezeloger"));
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "FREEZE_EVOKER"), PersistentDataType.STRING, "FREEZE_EVOKER");
    }

    public static void evokerhex(Evoker self) {
        self.setCustomName(format("&5&lWizard"));
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "HEX_EVOKER"), PersistentDataType.STRING, "HEX_EVOKER");
    }

    public static void evokerWind(Evoker self) {
        self.setCustomName(format("&f&lWindyloger"));
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "WIND_EVOKER"), PersistentDataType.STRING, "HEX_EVOKER");
    }


    public static void cinder(Vex self) {
        self.setCustomName(format("&6&lCinder"));
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(8);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "CINDER"), PersistentDataType.STRING, "CINDER");
    }

    public static void jengu(Vex self) {
        self.setCustomName(format("&1&lJengu"));
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(8);
        self.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(1.3);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "JENGU"), PersistentDataType.STRING, "JENGU");
    }

    public static void djiin(Vex self) {
        self.setCustomName(format("&f&lDjiin"));
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(8);
        self.getAttribute(Attribute.GENERIC_ATTACK_KNOCKBACK).setBaseValue(2.3);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "DJIIN"), PersistentDataType.STRING, "DJIIN");
    }

    public static void grue(Vex self) {
        self.setCustomName(format("&8&lGrue"));
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(8);
        self.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(1);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "GRUE"), PersistentDataType.STRING, "GRUE");
    }


    public static void lavaGolem(IronGolem self) {
        self.setCustomName(format("&6&lLava Golem"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(150);
        self.setHealth(150);
        self.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 0, false, false, false));
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "LAVA_GOLEM"), PersistentDataType.STRING, "LAVA_GOLEM");
        CraftIronGolem craft = ((CraftIronGolem) self);
        EntityIronGolem entityIronGolem = craft.getHandle();
        try {
            Class<? extends EntityInsentient> cl = EntityInsentient.class;
            Field gf = cl.getDeclaredField("bP");
            gf.setAccessible(true);
            PathfinderGoalSelector goal = (PathfinderGoalSelector) gf.get(entityIronGolem);
            goal.a(0, new PathfinderGoalMeleeAttack(entityIronGolem, 1.0D, true));

            Field tf = cl.getDeclaredField("bQ");
            tf.setAccessible(true);

            PathfinderGoalSelector target = (PathfinderGoalSelector) tf.get(entityIronGolem);
            target.a(0, new PathfinderGoalNearestAttackableTarget<>(entityIronGolem, EntityHuman.class, 10, true, false, null));
        } catch (Exception e) {
            e.printStackTrace();
            Warn.Mutant(e);
        }
        self.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 0, false, false, false));
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "LAVA_GOLEM"), PersistentDataType.STRING, "LAVA_GOLEM");
    }


    public static void vortice(Creeper self) {
        self.setCustomName(format("&bVortice"));
        self.setExplosionRadius(50);
        self.setMaxFuseTicks(10);
        self.setFuseTicks(10);
        self.setPowered(true);
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(70);
        self.setHealth(70);
        self.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 3, false, false, false));
        self.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0, false, false, false));
    }

    public static void stardust(Ghast self) {
        self.setCustomName(format("&1&lStardust"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(90);
        self.setHealth(90);
        self.getPersistentDataContainer().set(Utils.key("STARDUST"), PersistentDataType.STRING, "STARDUST");
    }

    public static void solaris(Phantom self) {
        self.setCustomName(format("&6&lSolaris"));
        self.setSize(14);
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(120);
        self.setHealth(120);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(90);
        self.setShouldBurnInDay(false);
    }


    public static void cosmicSilver(Silverfish self) {
        self.setCustomName(format("&d&lCosmic Ocean"));
        self.setHealth(1);
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(1);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(20.0);
        self.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 3, false, false, false));
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "COSMIC_SILVERFISH"), PersistentDataType.STRING, "COSMIC_SILVERFISH");
    }

    public static void elderdestroyer(ElderGuardian self) {
        self.setCustomName(format("&4&lElder Destroyer"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(120);
        self.setHealth(120);
        self.setRemoveWhenFarAway(true);
        setCustomMobcap(self, 3, 1.10, 24, 20, true);
        self.getPersistentDataContainer().set(Utils.key("ELDER_DESTROYER"), PersistentDataType.STRING, "ELDER_DESTROYER");
    }

    public static void tyranyWither(Wither self) {
        self.setCustomName(format("&e&lUndying &a&lWither"));
        self.setInvulnerableTicks(500);
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(500);
        self.setHealth(500);
        self.setRemoveWhenFarAway(true);
        self.setPersistent(false);
        setCustomMobcap(self, 3, 1.10, 24, 20, true);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "UNDYING_TITAN"), PersistentDataType.STRING, "UNDYING_TITAN");
    }

    public static void galaxyCalamity(Creeper self) {
        self.setCustomName(format("&b&lCosmos &dCalamity"));
        self.setPowered(true);
        self.setMaxFuseTicks(10);
        self.setFuseTicks(10);
        self.setExplosionRadius(0);
        self.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 4, false, false, false));
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "COSMOS_CALAMITY"), PersistentDataType.STRING, "COSMOS_CALAMITY");
    }

    public static void ladronFel(Vindicator self) {
        self.setCustomName(format("&cLadrón"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40);
        self.setHealth(40);
        self.getEquipment().setItemInMainHand(new ItemStack(Material.GOLD_INGOT));
        self.getEquipment().setDropChance(EquipmentSlot.HAND, 0);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "THIEF"), PersistentDataType.STRING, "THIEF");
    }

    public static void dynamLlager(Pillager self) {
        self.setCustomName(format("&c&lDynamllager"));
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "DYNAMLLAGER"), PersistentDataType.STRING, "DYNAMLLAGER");
    }

    public static void mountllagers(Pillager self) {
        self.setCustomName(format("&f&lMountllager"));
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "MOUNTLLAGER"), PersistentDataType.STRING, "MOUNTLLAGER");
    }

    public static void maestryWizard(Evoker self) {
        self.setCustomName(format("&b&lMaestry Wizard"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(100);
        self.setHealth(100);
        self.getPersistentDataContainer().set(Utils.key("MAESTRY_WIZARD"), PersistentDataType.STRING, "MAESTRY_WIZARD");
    }


    public static void setCustomMobcap(LivingEntity entity, int maxPerDistance, double multiplier, int distance, int maxPerWorld, boolean withSameName) {
        ArrayList<LivingEntity> nearbyEntities = new ArrayList<>();
        maxPerDistance *= (multiplier * entity.getWorld().getPlayers().stream().filter(player -> player.getGameMode() == GameMode.SURVIVAL).count() / 2);
        maxPerWorld *= (multiplier * entity.getWorld().getPlayers().stream().filter(player -> player.getGameMode() == GameMode.SURVIVAL).count());
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


    public static void InfernoLord(Blaze self) {
        self.setCustomName(format("&6&lInferno Lord"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(400);
        self.setHealth(400);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(50);
        self.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).equals(100);
        self.getPersistentDataContainer().set(Utils.key("INFERNO_LORD"), PersistentDataType.STRING, "INFERNO_LORD");
    }

    public static void Warden(IronGolem self) {
        self.setCustomName(format("&3&lWarden"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(400);
        self.setHealth(400);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(20);
        self.setSilent(true);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "WARDEN"), PersistentDataType.STRING, "WARDEN");
        setCustomMobcap(self, 3, 1.10, 24, 20, true);
        CraftIronGolem craft = ((CraftIronGolem) self);
        EntityIronGolem entityIronGolem = craft.getHandle();
        try {
            Class<? extends EntityInsentient> cl = EntityInsentient.class;
            Field gf = cl.getDeclaredField("bP");
            gf.setAccessible(true);
            PathfinderGoalSelector goal = (PathfinderGoalSelector) gf.get(entityIronGolem);
            goal.a(0, new PathfinderGoalMeleeAttack(entityIronGolem, 1.0D, true));

            Field tf = cl.getDeclaredField("bQ");
            tf.setAccessible(true);

            PathfinderGoalSelector target = (PathfinderGoalSelector) tf.get(entityIronGolem);
            target.a(0, new PathfinderGoalNearestAttackableTarget<>(entityIronGolem, EntityHuman.class, 10, true, false, null));
        } catch (Exception e) {
            e.printStackTrace();
            Warn.Mutant(e);
        }
    }

    public static void Erebus(Wither self) {
        self.setCustomName(format("&6&k||| &4&lEREBUS &6&k|||"));
        self.getBossBar().setColor(BarColor.RED);
        self.getBossBar().addFlag(BarFlag.DARKEN_SKY);
        self.getBossBar().addFlag(BarFlag.CREATE_FOG);
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(3000);
        self.setHealth(3000);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(100);
        self.getPersistentDataContainer().set(Utils.key("EREBUS"), PersistentDataType.STRING, "EREBUS");
    }


    public static void escarabajoGoliath(Silverfish self) {
        self.setCustomName(format("&8Escarabajo Goliath"));
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(14);
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30);
        self.setHealth(30);
        self.getAttribute(Attribute.GENERIC_ATTACK_KNOCKBACK).setBaseValue(100);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "MINER_LEVEL_1"), PersistentDataType.STRING, "MINER_LEVEL_1");
    }

    public static void stoneSoldier(Zombie self) {
        self.setCustomName(format("&7Stone Soldier"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(10);
        self.setHealth(40);
        self.getEquipment().setHelmet(new ItemStack(Material.STONE));
        self.getEquipment().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
        self.getEquipment().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
        self.getEquipment().setBoots(new ItemStack(Material.IRON_BOOTS));
        self.getEquipment().setItemInMainHand(new ItemStack(Material.IRON_SWORD));
        self.getEquipment().setDropChance(EquipmentSlot.HAND, 0);
        self.getEquipment().setDropChance(EquipmentSlot.HEAD, 0);
        self.getEquipment().setDropChance(EquipmentSlot.CHEST, 0);
        self.getEquipment().setDropChance(EquipmentSlot.LEGS, 0);
        self.getEquipment().setDropChance(EquipmentSlot.FEET, 0);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "MINER_LEVEL_1"), PersistentDataType.STRING, "MINER_LEVEL_1");
    }

    public static void goblin(Drowned self) {
        self.setCustomName(format("&1Goblin"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(10);
        self.setHealth(40);
        self.setBaby();
        self.getEquipment().setItemInMainHand(new ItemStack(Material.IRON_AXE));
        self.getEquipment().setDropChance(EquipmentSlot.HAND, 0);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "MINER_LEVEL_1"), PersistentDataType.STRING, "MINER_LEVEL_1");
    }

    public static void oreCreeper(Creeper self) {
        self.setCustomName(format("&7Ore Creeper"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30);
        self.setHealth(30);
        self.setExplosionRadius(5);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "MINER_LEVEL_2"), PersistentDataType.STRING, "MINER_LEVEL_2");
    }

    public static void spectreAssasin(Skeleton self) {
        self.setCustomName(format("&7&lSpectre Assassin"));
        self.setShouldBurnInDay(false);
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(60);
        self.setHealth(60);
        self.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1, false, false, false));
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(20);
        self.getEquipment().setItemInMainHand(new ItemBuilder(Material.NETHERITE_SWORD).addEnchantment(Enchantment.DAMAGE_ALL, 20).build());
        self.getEquipment().setDropChance(EquipmentSlot.HAND, 0);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "SPECTRE"), PersistentDataType.STRING, "SPECTRE");
    }

    public static void lostGolem(IronGolem self) {
        self.setCustomName(format("&8&lLost Golem"));
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "MINER_LEVEL_3"), PersistentDataType.STRING, "MINER_LEVEL_3");
        CraftIronGolem craft = ((CraftIronGolem) self);
        EntityIronGolem entityIronGolem = craft.getHandle();
        try {
            Class<? extends EntityInsentient> cl = EntityInsentient.class;
            Field gf = cl.getDeclaredField("bP");
            gf.setAccessible(true);
            PathfinderGoalSelector goal = (PathfinderGoalSelector) gf.get(entityIronGolem);
            goal.a(0, new PathfinderGoalMeleeAttack(entityIronGolem, 1.0D, true));

            Field tf = cl.getDeclaredField("bQ");
            tf.setAccessible(true);

            PathfinderGoalSelector target = (PathfinderGoalSelector) tf.get(entityIronGolem);
            target.a(0, new PathfinderGoalNearestAttackableTarget<>(entityIronGolem, EntityHuman.class, 10, true, false, null));
        } catch (Exception e) {
            e.printStackTrace();
            Warn.Mutant(e);
        }
    }


    public static void hellfireCreeper(Creeper self) {
        self.setCustomName(format("&6Hellfire Creeper"));
        self.setPowered(true);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "HELLFIRE_CREEPER"), PersistentDataType.STRING, "HELLFIRE_CREEPER");
    }

    public static void hellfire(Blaze self) {
        self.setCustomName(format("&6&lHellfire"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(50);
        self.setHealth(50);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "HELLFIRE"), PersistentDataType.STRING, "HELLFIRE");
    }

    public static void hiveMind(Creeper self) {
        self.setCustomName(format("&6Hive Mind"));
        self.setMaxFuseTicks(1);
        self.setFuseTicks(1);
        self.setExplosionRadius(1);
        self.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, 2, false, false, false));
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "HIVE_MIND"), PersistentDataType.STRING, "HIVE_MIND");
    }

    public static void thePlague(Bee self) {
        self.setCustomName(format("&2Plague"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30);
        self.setHealth(30);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(10);
        CraftBee craft = ((CraftBee) self);
        EntityBee entityBee = craft.getHandle();
        try {
            Class<? extends EntityInsentient> cl = EntityInsentient.class;
            Field gf = cl.getDeclaredField("bP");
            gf.setAccessible(true);
            PathfinderGoalSelector goal = (PathfinderGoalSelector) gf.get(entityBee);
            goal.a(0, new PathfinderGoalMeleeAttack(entityBee, 1.0D, true));

            Field tf = cl.getDeclaredField("bQ");
            tf.setAccessible(true);

            PathfinderGoalSelector target = (PathfinderGoalSelector) tf.get(entityBee);
            target.a(0, new PathfinderGoalNearestAttackableTarget<>(entityBee, EntityHuman.class, 10, true, false, null));
        } catch (Exception e) {
            e.printStackTrace();
            Warn.Mutant(e);
        }
        self.setAnger(Integer.MAX_VALUE);

    }


    //Darkness mobs
    public static void Banshee(Vex self) {
        self.setCustomName(format("&8&lBanshee"));
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(15);
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30);
        self.setHealth(30);
        self.getPersistentDataContainer().set(Utils.key("BANSHEE"), PersistentDataType.STRING, "BANSHEE");
    }

    public static void KillerScream(Vindicator self) {
        self.setCustomName(format("&8&lKillerscream"));
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(30);
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(70);
        self.setHealth(70);
        self.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1000);
        self.getPersistentDataContainer().set(Utils.key("KILLERSCREAM"), PersistentDataType.STRING, "KILLERSCREAM");
    }

    public static void Nightmare(Ghast self) {
        self.setCustomName(format("&8&lNightmare"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(100);
        self.setHealth(100);
        self.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1000);
        self.getPersistentDataContainer().set(Utils.key("NIGHTMARE"), PersistentDataType.STRING, "NIGHTMARE");
    }

    public static void Overscream(Creeper self) {
        self.setCustomName(format("&8&lOverscream"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(50);
        self.setHealth(50);
        self.setExplosionRadius(7);
        self.setMaxFuseTicks(10);
        self.setFuseTicks(10);
        self.getPersistentDataContainer().set(Utils.key("OVERSCREAM"), PersistentDataType.STRING, "OVERSCREAM");
    }

    public static void enderFlame(Enderman self) {
        self.setCustomName(format("&5&lShadowflame Enderman"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(50);
        self.setHealth(50);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(20);
        self.getPersistentDataContainer().set(Utils.key("ENDER_FLAME"), PersistentDataType.STRING, "ENDER_FLAME");
    }

    public static void enderInfected(Enderman self) {
        self.setCustomName(format("&a&lInfected Enderman"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(50);
        self.setHealth(50);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(20);
        self.getPersistentDataContainer().set(Utils.key("ENDER_INFECTED"), PersistentDataType.STRING, "ENDER_INFECTED");
    }

    public static void enderWatcher(Enderman self) {
        self.setCustomName(format("&5&lWatcher"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(85);
        self.setHealth(85);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(34);
        CraftEnderman craft = ((CraftEnderman) self);
        EntityEnderman entityEnderman = craft.getHandle();
        try {
            Class<? extends EntityInsentient> cl = EntityInsentient.class;
            Field gf = cl.getDeclaredField("bP");
            gf.setAccessible(true);
            PathfinderGoalSelector goal = (PathfinderGoalSelector) gf.get(entityEnderman);
            goal.a(0, new PathfinderGoalMeleeAttack(entityEnderman, 1.0D, true));

            Field tf = cl.getDeclaredField("bQ");
            tf.setAccessible(true);

            PathfinderGoalSelector target = (PathfinderGoalSelector) tf.get(entityEnderman);
            target.a(0, new PathfinderGoalNearestAttackableTarget<>(entityEnderman, EntityHuman.class, 10, true, false, null));
        } catch (Exception e) {
            e.printStackTrace();
            Warn.Mutant(e);
        }
    }

    public static void enderAberration(Enderman self) {
        self.setCustomName(format("&bEnder Aberration"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40);
        self.setHealth(40);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(19);
        CraftEnderman craft = ((CraftEnderman) self);
        EntityEnderman entityEnderman = craft.getHandle();
        try {
            Class<? extends EntityInsentient> cl = EntityInsentient.class;
            Field gf = cl.getDeclaredField("bP");
            gf.setAccessible(true);
            PathfinderGoalSelector goal = (PathfinderGoalSelector) gf.get(entityEnderman);
            goal.a(0, new PathfinderGoalMeleeAttack(entityEnderman, 1.0D, true));

            Field tf = cl.getDeclaredField("bQ");
            tf.setAccessible(true);

            PathfinderGoalSelector target = (PathfinderGoalSelector) tf.get(entityEnderman);
            target.a(0, new PathfinderGoalNearestAttackableTarget<>(entityEnderman, EntityHuman.class, 10, true, false, null));
        } catch (Exception e) {
            e.printStackTrace();
            Warn.Mutant(e);
        }
    }

    //arañas
    public static void plagueSpider(Spider self) {
        self.setCustomName(format("&2Plague Spider"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40);
        self.setHealth(40);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(16.0);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "PLAGUE_SPIDER"), PersistentDataType.STRING, "PLAGUE_SPIDER");
    }

    public static void solarScorpion(Spider self) {
        self.setCustomName(format("&6Solar Scorpion"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(50);
        self.setHealth(40);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(20.0);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "SOLAR_SCORPION"), PersistentDataType.STRING, "SOLAR_SCORPION");
    }

    public static void agileTarantule(Spider self) {
        self.setCustomName(format("&bTarantula Rompevientos"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40);
        self.setHealth(40);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(20.0);
        self.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10000000, 2, false, false, false));
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "AGILE_SPIDER"), PersistentDataType.STRING, "AGILE_SPIDER");
    }

    public static void interdimensionalVisitor(Spider self) {
        self.setCustomName(format("&5Visitante Interdimensional"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40);
        self.setHealth(40);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(20.0);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "INTER_SPIDER"), PersistentDataType.STRING, "INTER_SPIDER");
    }

    public static void sombra(Skeleton self) {
        self.setSilent(true);
        self.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0, false, false, false));
        self.setCustomName(format("&5&lSombra"));
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "SHADE"), PersistentDataType.STRING, "SHADE");
    }

    public static void darkSpectre(Enderman self) {
        self.setCustomName(format("&8&lDark Spectre"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(50);
        self.setHealth(50);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(20);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "DARK_SPECTRE"), PersistentDataType.STRING, "DARK_SPECTRE");
        CraftEnderman craft = ((CraftEnderman) self);
        EntityEnderman entityEnderman = craft.getHandle();
        try {
            Class<? extends EntityInsentient> cl = EntityInsentient.class;
            Field gf = cl.getDeclaredField("bP");
            gf.setAccessible(true);
            PathfinderGoalSelector goal = (PathfinderGoalSelector) gf.get(entityEnderman);
            goal.a(0, new PathfinderGoalMeleeAttack(entityEnderman, 1.0D, true));

            Field tf = cl.getDeclaredField("bQ");
            tf.setAccessible(true);

            PathfinderGoalSelector target = (PathfinderGoalSelector) tf.get(entityEnderman);
            target.a(0, new PathfinderGoalNearestAttackableTarget<>(entityEnderman, EntityHuman.class, 10, true, false, null));
        } catch (Exception e) {
            e.printStackTrace();
            Warn.Mutant(e);
        }
    }

    public static void gorgomite(Endermite self) {
        self.setCustomName(format("&6&lGorgomite"));
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(18);
    }

    public static void advancedwither(Wither wither){
        wither.setCustomName(format("&6&lAdvanced Wither"));
        wither.setInvulnerableTicks(500);
        wither.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(500);
        wither.setHealth(500);
        wither.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "ADVANCED_WITHER"), PersistentDataType.STRING, "ADVANCED_WITHER");
    }


    //Almas
    public static void arphz(Skeleton self) {
        ItemStack cabeza = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta)cabeza.getItemMeta();
        skullMeta.setOwner("TheArphz");
        cabeza.setItemMeta(skullMeta);
        self.getEquipment().setHelmet(cabeza);
        self.setInvisible(true);
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(200);
        self.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(200);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(40);
        self.setCustomName(format("&8&lT??Ar??z"));
        self.setHealth(200);
        self.getEquipment().setItemInMainHand(new ItemBuilder(Material.NETHERITE_AXE).addEnchantment(Enchantment.DAMAGE_ALL,50).build());
        self.getEquipment().setDropChance(EquipmentSlot.HAND,0);
        self.getPersistentDataContainer().set(Utils.key("ARPHZ"),PersistentDataType.STRING,"ARPHZ");
    }
    public static void salva(Skeleton self){
        ItemStack cabeza = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta)cabeza.getItemMeta();
        skullMeta.setOwner("SalvaGamerVZ");
        cabeza.setItemMeta(skullMeta);
        self.getEquipment().setHelmet(cabeza);
        self.setInvisible(true);
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(200);
        self.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(200);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(40);
        self.setCustomName(format("&8&lS??lv??Gam???VZ"));
        self.setHealth(200);
        self.getEquipment().setItemInMainHand(new ItemBuilder(Material.NETHERITE_AXE).addEnchantment(Enchantment.DAMAGE_ALL,50).build());
        self.getEquipment().setDropChance(EquipmentSlot.HAND,0);
        self.getPersistentDataContainer().set(Utils.key("SALVA"),PersistentDataType.STRING,"SALVA");
    }
    public static void diego(Skeleton self){
        ItemStack cabeza = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta)cabeza.getItemMeta();
        skullMeta.setOwner("Diegot_Manc");
        cabeza.setItemMeta(skullMeta);
        self.getEquipment().setHelmet(cabeza);
        self.setInvisible(true);
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(200);
        self.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(200);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(40);
        self.setCustomName(format("&8&lDi???_?an?"));
        self.setHealth(200);
        self.getEquipment().setItemInMainHand(new ItemBuilder(Material.NETHERITE_AXE).addEnchantment(Enchantment.DAMAGE_ALL,50).build());
        self.getEquipment().setDropChance(EquipmentSlot.HAND,0);
        self.getPersistentDataContainer().set(Utils.key("DIEGO"),PersistentDataType.STRING,"DIEGO");
    }
    public static void gatin(Skeleton self){
        ItemStack cabeza = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta)cabeza.getItemMeta();
        skullMeta.setOwner("Gatin72");
        cabeza.setItemMeta(skullMeta);
        self.getEquipment().setHelmet(cabeza);
        self.setInvisible(true);
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(200);
        self.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(200);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(40);
        self.setCustomName(format("&8&lG??in7?"));
        self.setHealth(200);
        self.getEquipment().setItemInMainHand(new ItemBuilder(Material.NETHERITE_AXE).addEnchantment(Enchantment.DAMAGE_ALL,50).build());
        self.getEquipment().setDropChance(EquipmentSlot.HAND,0);
        self.getPersistentDataContainer().set(Utils.key("GATIN"),PersistentDataType.STRING,"GATIN");
    }
    public static void johan(Skeleton self){
        ItemStack cabeza = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta)cabeza.getItemMeta();
        skullMeta.setOwner("JohanBigCum");
        cabeza.setItemMeta(skullMeta);
        self.getEquipment().setHelmet(cabeza);
        self.setInvisible(true);
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(200);
        self.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(200);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(40);
        self.setCustomName(format("&8&lJ???nC???"));
        self.setHealth(200);
        self.getEquipment().setItemInMainHand(new ItemBuilder(Material.NETHERITE_AXE).addEnchantment(Enchantment.DAMAGE_ALL,50).build());
        self.getEquipment().setDropChance(EquipmentSlot.HAND,0);
        self.getPersistentDataContainer().set(Utils.key("JOHAN"),PersistentDataType.STRING,"JOHAN");
    }



    ///mobs del laboratorio
    public static void mechaZombie(Zombie self){
        self.setCustomName(format("&6Undead Scientist"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30.0);
        self.setHealth(30);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(10.0);
    }

    public static void exoGolem(IronGolem self){
        self.setCustomName(format("&c&lExperiment JD78K"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(150.0);
        self.setHealth(150);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(25.0);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "EXPERIMENT_1"), PersistentDataType.STRING, "EXPERIMENT_1");
        CraftIronGolem craft = ((CraftIronGolem) self);
        EntityIronGolem entityIronGolem = craft.getHandle();
        try{
            Class<? extends EntityInsentient> cl = EntityInsentient.class;
            Field gf = cl.getDeclaredField("bP");
            gf.setAccessible(true);
            PathfinderGoalSelector goal = (PathfinderGoalSelector) gf.get(entityIronGolem);
            goal.a(0, new PathfinderGoalMeleeAttack(entityIronGolem,1.0D,true));

            Field tf = cl.getDeclaredField("bQ");
            tf.setAccessible(true);

            PathfinderGoalSelector target = (PathfinderGoalSelector) tf.get(entityIronGolem);
            target.a(0,new PathfinderGoalNearestAttackableTarget<>(entityIronGolem, EntityHuman.class, 10,true,false,null));
        }catch (Exception e){
            e.printStackTrace();
            Warn.Mutant(e);
        }
    }

    public static void labSilver(Silverfish self){
        self.setCustomName(format("&4Labrat"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20.0);
        self.setHealth(20);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(7.0);
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
        flecha1efect.addCustomEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 400, 1), true);
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


    }




    public static void zombHerrero(Zombie self){
        self.setCustomName(format("&6Zombi Herrero"));
        self.setShouldBurnInDay(false);
        self.setBaby(false);
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40.0);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(30);
        self.setHealth(40);
        ItemStack mazo = new ItemStack(Material.NETHERITE_AXE);
        ItemMeta mazom = mazo.getItemMeta();
        mazom.setUnbreakable(true);
        mazom.setCustomModelData(18189);
        mazo.setItemMeta(mazom);
        self.getEquipment().setItemInMainHand(mazo);
        self.getEquipment().setDropChance(EquipmentSlot.HAND,0);
    }

    public static void zombCarni(Zombie self){
        self.setCustomName(format("&6Zombi Carnicero"));
        self.setShouldBurnInDay(false);
        self.setBaby(false);
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40.0);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(12);
        self.setHealth(40);
        ItemStack machete = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta machetem = machete.getItemMeta();
        machetem.setUnbreakable(true);
        machetem.setCustomModelData(5723);
        machete.setItemMeta(machetem);
        self.getEquipment().setItemInMainHand(machete);
        self.getEquipment().setDropChance(EquipmentSlot.HAND,0);
    }
    public static void zombObeso(IronGolem self){
        self.setCustomName(format("&6Zombi Destructor"));
        self.setRemoveWhenFarAway(true);
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(80.0);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(50);
        self.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE,2,false,false,false));
        self.setHealth(80);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class),"ZO1"),PersistentDataType.STRING,"ZO1");
        self.setSilent(true);
        CraftIronGolem craft = ((CraftIronGolem) self);
        EntityIronGolem entityIronGolem = craft.getHandle();
        try{
            Class<? extends EntityInsentient> cl = EntityInsentient.class;
            Field gf = cl.getDeclaredField("bP");
            gf.setAccessible(true);
            PathfinderGoalSelector goal = (PathfinderGoalSelector) gf.get(entityIronGolem);
            goal.a(0, new PathfinderGoalMeleeAttack(entityIronGolem,1.0D,true));

            Field tf = cl.getDeclaredField("bQ");
            tf.setAccessible(true);

            PathfinderGoalSelector target = (PathfinderGoalSelector) tf.get(entityIronGolem);
            target.a(0,new PathfinderGoalNearestAttackableTarget<>(entityIronGolem, EntityHuman.class, 10,true,false,null));
        }catch (Exception e){
            e.printStackTrace();
            Warn.Mutant(e);
        }
    }

    public static void zombiJinete(Zombie self){
        self.setCustomName(format("&6Zombi Jinete"));
        self.setShouldBurnInDay(false);
        self.setBaby(false);
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40.0);
        self.setHealth(40);
        self.getEquipment().setItemInMainHand(new ItemStack(Material.IRON_SWORD));
        self.getEquipment().setHelmet(new ItemStack(Material.IRON_HELMET));
        self.getEquipment().setDropChance(EquipmentSlot.HAND, 0);
        self.getEquipment().setDropChance(EquipmentSlot.HEAD, 0);
    }
    public static void caballoJinete(ZombieHorse self){
        self.setCustomName(format("&6Caballo Zombi"));
    }


    public static void zombBox(Zombie self){
        self.setCustomName(format("&6Zombi Boxxeador"));
        self.setShouldBurnInDay(false);
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40.0);
        self.setHealth(40);
        self.setBaby(false);
        self.getAttribute(Attribute.GENERIC_ATTACK_KNOCKBACK).setBaseValue(10.0D);
        self.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(100.0D);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(14.0D);
    }




    ///esqueletos randoms
    public static void ignitedSkeleton(Skeleton self){
        ItemStack ai = new ItemStack(Material.BOW);
        ItemMeta meta = ai.getItemMeta();
        meta.addEnchant(Enchantment.ARROW_DAMAGE, 150,true);
        ai.setItemMeta(meta);
        self.setCustomName(format("&6&lHellfire Archer"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30.0);
        self.setHealth(30);
        self.getEquipment().setItemInMainHand(ai);
        self.getEquipment().setHelmet(new ItemStack(Material.AIR));
        self.getEquipment().setChestplate(new ItemStack(Material.AIR));
        self.getEquipment().setLeggings(new ItemStack(Material.AIR));
        self.getEquipment().setBoots(new ItemStack(Material.AIR));
        self.getEquipment().setDropChance(EquipmentSlot.HAND, 0);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "IGNITED_SKELETON"), PersistentDataType.STRING, "IGNITED_SKELETON");
    }

    public static void blizzardSkeleton(Skeleton self){
        ItemStack ai = new ItemStack(Material.BOW);
        ItemMeta meta = ai.getItemMeta();
        meta.addEnchant(Enchantment.ARROW_DAMAGE, 150,true);
        meta.addEnchant(Enchantment.ARROW_KNOCKBACK, 40,true);
        ai.setItemMeta(meta);
        self.setCustomName(format("&b&lSnow Sniper"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30.0);
        self.setHealth(30);
        self.getEquipment().setItemInMainHand(ai);
        self.getEquipment().setHelmet(new ItemStack(Material.AIR));
        self.getEquipment().setChestplate(new ItemStack(Material.AIR));
        self.getEquipment().setLeggings(new ItemStack(Material.AIR));
        self.getEquipment().setBoots(new ItemStack(Material.AIR));
        self.getEquipment().setDropChance(EquipmentSlot.HAND, 0);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "BLIZZARD_SKELETON"), PersistentDataType.STRING, "BLIZZARD_SKELETON");
    }

    public static void copperSkeleton(Skeleton self){
        ItemStack ai = new ItemStack(Material.BOW);
        ItemMeta meta = ai.getItemMeta();
        meta.addEnchant(Enchantment.ARROW_DAMAGE, 150,true);
        meta.addEnchant(Enchantment.ARROW_FIRE, 20,true);
        ai.setItemMeta(meta);
        self.setCustomName(format("&e&lCopper Robot"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30.0);
        self.setHealth(30);
        self.getEquipment().setItemInMainHand(ai);
        self.getEquipment().setHelmet(new ItemStack(Material.LIGHTNING_ROD));
        self.getEquipment().setChestplate(new ItemStack(Material.AIR));
        self.getEquipment().setLeggings(new ItemStack(Material.AIR));
        self.getEquipment().setBoots(new ItemStack(Material.AIR));
        self.getEquipment().setDropChance(EquipmentSlot.HAND, 0);
        self.getEquipment().setDropChance(EquipmentSlot.HEAD, 0);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "COPPER_SKELETON"), PersistentDataType.STRING, "COPPER_SKELETON");
    }

    public static void bullseyeSkeleton(Skeleton  self){
        ItemStack ai = new ItemStack(Material.BOW);
        ItemMeta meta = ai.getItemMeta();
        meta.addEnchant(Enchantment.ARROW_DAMAGE, 150,true);
        ai.setItemMeta(meta);
        self.setCustomName(format("&4&lBullseye Archer"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(50.0);
        self.setHealth(50);
        self.getEquipment().setItemInMainHand(ai);
        self.getEquipment().setHelmet(new ItemStack(Material.AIR));
        self.getEquipment().setChestplate(new ItemStack(Material.AIR));
        self.getEquipment().setLeggings(new ItemStack(Material.AIR));
        self.getEquipment().setBoots(new ItemStack(Material.AIR));
        self.getEquipment().setDropChance(EquipmentSlot.HAND, 0);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "BULLSEYE_SKELETON"), PersistentDataType.STRING, "BULLSEYE_SKELETON");
    }
    public static void poweredSkeleton(WitherSkeleton self){
        ItemStack ai = new ItemStack(Material.BOW);
        ItemMeta meta = ai.getItemMeta();
        meta.addEnchant(Enchantment.ARROW_DAMAGE, 150,true);
        ai.setItemMeta(meta);
        self.setCustomName(format("&5&lUndead Reaper"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(50.0);
        self.setHealth(50);
        self.getEquipment().setItemInMainHand(ai);
        self.getEquipment().setHelmet(new ItemStack(Material.AIR));
        self.getEquipment().setChestplate(new ItemStack(Material.AIR));
        self.getEquipment().setLeggings(new ItemStack(Material.AIR));
        self.getEquipment().setBoots(new ItemStack(Material.AIR));
        self.getEquipment().setDropChance(EquipmentSlot.HAND, 0);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "POWERED_SKELETON"), PersistentDataType.STRING, "POWERED_SKELETON");
    }
    public static void abomination(WitherSkeleton self){
        self.setCustomName(format("&6&lAbomination"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(80);
        self.setHealth(80);
        self.getEquipment().setHelmet(new ItemStack(Material.AIR));
        self.getEquipment().setChestplate(new ItemStack(Material.AIR));
        self.getEquipment().setLeggings(new ItemStack(Material.AIR));
        self.getEquipment().setBoots(new ItemStack(Material.AIR));
        self.getEquipment().setDropChance(EquipmentSlot.HAND, 0);
        self.getEquipment().setItemInMainHand(new ItemBuilder(Material.BOW).addEnchantment(Enchantment.ARROW_DAMAGE,150).addEnchantment(Enchantment.ARROW_FIRE,10).addEnchantment(Enchantment.ARROW_KNOCKBACK,20).setUnbreakable(true).build());
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class),"ABOMINATION"),PersistentDataType.STRING,"ABOMINATION");
    }




    ///cacaplayo
   //Grosero
    //:(








    //PARASITOS Y ESAS COSAS
    public static void parasite(Endermite self){
        self.setCustomName(format("&1Parasite"));
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class),"PARASITE_MINOR"),PersistentDataType.STRING,"PARASITE_MINOR");
    }
    public static void parasite2(Bee self){
        self.setCustomName(format("&1Parasite"));
        self.setAnger(Integer.MAX_VALUE);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class),"PARASITE_MINOR"),PersistentDataType.STRING,"PARASITE_MINOR");
    }
    public static void minorparasite1(CaveSpider self){
        self.setCustomName(format("&1Incomplete Parasite"));
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class),"PARASITE_MINOR"),PersistentDataType.STRING,"PARASITE_MINOR");
    }
    public static void minorparasite2(Vex self){
        self.setCustomName(format("&1Flying Parasite"));
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class),"PARASITE_MINOR"),PersistentDataType.STRING,"PARASITE_MINOR");
    }

    //PARASITOS NORMALES
    public static void parasitez(Zombie self){
        self.setCustomName(format("&1&lParasite Z"));
        self.setShouldBurnInDay(false);
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40);
        self.setHealth(40);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(20);
        self.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(20);
        self.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2, false, false, false));
        self.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, 1, false, false, false));
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class),"PARASITE_NORMAL"),PersistentDataType.STRING,"PARASITE_NORMAL");
    }
    public static void parasitearcher1(Skeleton self){
        self.setCustomName(format("&1&lParasite Archer"));
        self.setShouldBurnInDay(false);
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40);
        self.setHealth(40);
        ItemStack flecha1 = new ItemStack(Material.TIPPED_ARROW, 64);
        PotionMeta flecha1efect = (PotionMeta) flecha1.getItemMeta();
        flecha1efect.setBasePotionData(new PotionData(PotionType.SLOWNESS));
        flecha1efect.addCustomEffect(new PotionEffect(PotionEffectType.SLOW, 400, 1), true);
        flecha1efect.addCustomEffect(new PotionEffect(PotionEffectType.POISON, 400, 1), true);
        flecha1efect.addCustomEffect(new PotionEffect(PotionEffectType.CONFUSION, 400, 1), true);
        flecha1.setItemMeta(flecha1efect);
        ItemStack bowed = new ItemStack(Material.BOW);
        ItemMeta meta = bowed.getItemMeta();
        meta.setUnbreakable(true);
        meta.addEnchant(Enchantment.ARROW_DAMAGE, 50, true);
        meta.addEnchant(Enchantment.ARROW_FIRE,1,true);
        bowed.setItemMeta(meta);
        self.getEquipment().setItemInMainHandDropChance(0);
        self.getEquipment().setItemInOffHandDropChance(0);
        self.getEquipment().setItemInOffHand(flecha1);
        self.getEquipment().setItemInMainHand(bowed);
    }



    // EXO MOBS
    public static void roboZombi(Zombie self){
        self.setCustomName(format("&4&lExo-Undead"));
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(20);
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(65);
        self.setHealth(65);
        self.setBaby(false);
        self.setShouldBurnInDay(false);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class),"EXO_MELEE"),PersistentDataType.STRING,"EXO_MELEE");
    }
    public static void roboSkele(Skeleton self){
        self.setCustomName(format("&4&lExo-Archer"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(65);
        self.setHealth(65);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class),"EXO_DISTANCE"),PersistentDataType.STRING,"EXO_DISTANCE");
    }
    public static void roboCreeper(Creeper self){
        self.setCustomName(format("&4&lExo-Dynamite"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(65);
        self.setHealth(65);
        self.setExplosionRadius(7);
        self.setPowered(true);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class),"EXO_EXPLODE"),PersistentDataType.STRING,"EXO_EXPLODE");
    }
    public static void roboSpider(Spider self){
        self.setCustomName(format("&4&lExo-Spider"));
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(24);
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(55);
        self.setHealth(55);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class),"EXO_MELEE"),PersistentDataType.STRING,"EXO_MELEE");
    }
    public static void roboPhantom(Phantom self){
        self.setCustomName(format("&4&lExo-Ghost"));
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(24);
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(65);
        self.setHealth(65);
        self.setShouldBurnInDay(false);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class),"EXO_MELEE"),PersistentDataType.STRING,"EXO_MELEE");
    }

    //MOBS DEL PLANETOIDE

    public static void lushZombie(Zombie self){
        self.setCustomName(format("&2&lLush Zombie"));
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(20);
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40);
        self.setHealth(40);
    }
    public static void lushSkeleton(Skeleton self){
        self.setCustomName(format("&2&lLush Skeleton"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40);
        self.setHealth(40);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class),"LUSH_SKELETON"),PersistentDataType.STRING,"LUSH_SKELETON");
    }
    public static void roboMine(Creeper self){
        self.setCustomName(format("&4&lRobo-Mine"));
        self.setMaxFuseTicks(10);
        self.setFuseTicks(10);
        self.setExplosionRadius(5);
    }
    public static void experimentWOOD(IronGolem self){
        self.setCustomName(format("&2&lExperiment &4&lW00D34"));
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(35);
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(200);
        self.setHealth(200);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class),"EXPERIMENT_W"),PersistentDataType.STRING,"EXPERIMENT_W");
        setCustomMobcap(self, 5, 1.10, 8, 30, true);
        CraftIronGolem craft = ((CraftIronGolem) self);
        EntityIronGolem entityIronGolem = craft.getHandle();
        try{
            Class<? extends EntityInsentient> cl = EntityInsentient.class;
            Field gf = cl.getDeclaredField("bP");
            gf.setAccessible(true);
            PathfinderGoalSelector goal = (PathfinderGoalSelector) gf.get(entityIronGolem);
            goal.a(0, new PathfinderGoalMeleeAttack(entityIronGolem,1.0D,true));

            Field tf = cl.getDeclaredField("bQ");
            tf.setAccessible(true);

            PathfinderGoalSelector target = (PathfinderGoalSelector) tf.get(entityIronGolem);
            target.a(0,new PathfinderGoalNearestAttackableTarget<>(entityIronGolem, EntityHuman.class, 10,true,false,null));
        }catch (Exception e){
            e.printStackTrace();
            Warn.Mutant(e);
        }
    }

}
