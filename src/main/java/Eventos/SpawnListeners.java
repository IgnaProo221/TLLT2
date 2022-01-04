package Eventos;

import Utilidades.Mobs;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import tlldos.tll2.TLL2;

import static Utilidades.Format.format;
import java.util.Random;

public class SpawnListeners implements Listener{
    private TLL2 plugin;

    public SpawnListeners (TLL2 plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void spawnMob(CreatureSpawnEvent e) {
        Random random = new Random();
        var en = e.getEntity();
        var pos = e.getLocation();
           if (en instanceof Zombie) {
               var zombie = (Zombie) en;
               zombie.setCustomName(format("&6Zombi Tier I"));
               zombie.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(7.0);
               zombie.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30.0);
               zombie.setHealth(30);
               zombie.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, 0, false, false, false));
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
