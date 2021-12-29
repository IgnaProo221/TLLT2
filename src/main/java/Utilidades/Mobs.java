package Utilidades;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.checkerframework.checker.units.qual.A;
import tlldos.tll2.TLL2;


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

    public static void tntEnderPearl(TNTPrimed self) {
        self.setYield(5);
        self.setFuseTicks(40);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "TNTENDERPEARL"), PersistentDataType.STRING, "TNTENDERPEARL");
    }


    ///Mobs de Nieve

    public static  void zombiCongelado(Zombie self){
        self.setCustomName(ChatColor.AQUA + "Zombi Congelado");
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30.0);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(6.0);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "ZOMBI_CONGELADO"), PersistentDataType.STRING, "ZOMBI_CONGELADO");
    }

    public static void esqueletoNieve(Skeleton self){
        ItemStack arco1 = new ItemStack(Material.BOW);
        ItemMeta meta = arco1.getItemMeta();
        meta.addEnchant(Enchantment.ARROW_DAMAGE, 5, true);
        arco1.setItemMeta(meta);

        //Aqui deberia ir la flecha con Slowness que el esqueleto se supone que deberia disparar



        self.setCustomName(ChatColor.AQUA + "Snow Skeleton");
        self.setMaxHealth(30);
        self.setHealth(30);
        self.getEquipment().setItemInMainHand(arco1);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "ESQUELETO_NIEVE"), PersistentDataType.STRING, "ESQUELETO_NIEVE");
    }

    public static void aranaNieve(Spider self){

        self.setCustomName(ChatColor.AQUA + "Snow Spider");
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30.0);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(7.0);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "SNOW_SPIDER"), PersistentDataType.STRING, "SNOW_SPIDER");
    }










    ///mobs del laboratorio
    public static void mechaZombie(Zombie self){
        self.setCustomName(format("&6Mecha Undead"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30.0);
        self.setHealth(30);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(10.0);
        self.getAttribute(Attribute.GENERIC_ATTACK_KNOCKBACK).setBaseValue(1.0);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "MECHA_ZOMBIE"), PersistentDataType.STRING, "MECHA_ZOMBIE");
    }

    public static void exoGolem(IronGolem self){
        self.setCustomName(format("&6Exo Golem"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(50.0);
        self.setHealth(50);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "EXO_GOLEM"), PersistentDataType.STRING, "EXO_GOLEM");
        ///pongan que el golem este enojado porfavor
    }

    public static void labSilver(Silverfish self){
        self.setCustomName(format("&4Lab Silverfish"));
        self.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(10.0);
        self.setHealth(10);
        self.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(5.0);
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "LAB_SILVERFISH"), PersistentDataType.STRING, "LAB_SILVERFISH");
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

        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "MAD_SCIENTIST"), PersistentDataType.STRING, "MAD_SCIENTIST");

    }


}
