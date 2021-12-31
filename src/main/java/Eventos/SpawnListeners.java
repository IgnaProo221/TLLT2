package Eventos;

import Utilidades.Mobs;
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

           if(en instanceof Skeleton){
               var skeleton = (Skeleton)en;
               ItemStack ai = new ItemStack(Material.BOW);
               ItemMeta meta = ai.getItemMeta();
               meta.addEnchant(Enchantment.ARROW_DAMAGE, 20,true);
               ai.setItemMeta(meta);

               skeleton.setCustomName(format("&6Ignited &cSkeleton"));
               skeleton.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30.0);
               skeleton.setHealth(30);
               skeleton.getEquipment().setItemInMainHand(ai);
               skeleton.getEquipment().setHelmet(new ItemStack(Material.GLASS));
               skeleton.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "IGNITED_SKELETON"), PersistentDataType.STRING, "IGNITED_SKELETON");
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

        if (en instanceof Vex self) {

            this.spawnVexClass(self);

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

    public void spawnSkeletonClass(Skeleton entity, WitherSkeleton entity2){
        int type = new Random().nextInt(5);

        if(type == 1){
            Mobs.ignitedSkeleton(entity);
        }else if(type == 2){
            Mobs.blizzardSkeleton(entity);
        }else if(type == 3){
            Mobs.copperSkeleton(entity);
        }else if(type == 4){
            Mobs.bullseyeSkeleton(entity2);
        }else{
            Mobs.poweredSkeleton(entity2);
        }

    }

}
