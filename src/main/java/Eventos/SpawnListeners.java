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


    }

}
