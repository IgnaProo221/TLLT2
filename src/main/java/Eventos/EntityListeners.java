package Eventos;

import Utilidades.Mobs;
import Utilidades.TLLEntities;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;

import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import tlldos.tll2.TLL2;

import java.util.*;

import static Extras.Items.createFragmentoSangre;
import static Utilidades.Format.format;

public class EntityListeners implements Listener {
    public static HashMap<Player, Integer> hash = new HashMap<>();

    public static void addHash(Player p) {
        if (hash.containsKey(p)) {
            int value = hash.get(p);
            int result = value + 1;
            hash.remove(p);
            hash.put(p, result);
        }
        hash.put(p, 1);
    }

    @EventHandler
    public void damageEntity (EntityDamageByEntityEvent event) {

        Entity entity = event.getEntity();
        Entity damager = event.getDamager();

        if (TLLEntities.isTLLEntity(damager) && "ZOMBI_CONGELADO".equals(TLLEntities.getTLLEntity(damager))) {

            if (entity instanceof Player p) {

                p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 1));

            }
        }

        if (TLLEntities.isTLLEntity(damager) && "MECHA_ZOMBIE".equals(TLLEntities.getTLLEntity(damager))) {

            if (entity instanceof Player p) {

                p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 400, 0));

            }
        }
        if (TLLEntities.isTLLEntity(damager) && "LAB_SILVERFISH".equals(TLLEntities.getTLLEntity(damager))) {

            if (entity instanceof Player p) {

                p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 1200, 0));

            }
        }
    }




    @EventHandler
    public void onShotthit(ProjectileHitEvent event){
        var e = event.getEntity();
        var shooter = (Entity) event.getEntity().getShooter();
        var damaged = (Entity) event.getHitEntity();

        if (shooter instanceof Skeleton){
            var skeleton = (Entity)shooter;
            if(skeleton.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "IGNITED_SKELETON"), PersistentDataType.STRING)){
                damaged.getLocation().createExplosion(5);
            }
        }
    }
    

    @EventHandler
    public void entityDeath (EntityDeathEvent event) {

        Entity entity = event.getEntity();
        Entity killer = event.getEntity().getKiller();

        Random random = new Random();

        int size = random.nextInt(2);
        ItemStack dropFrag = createFragmentoSangre(size);

        if (entity instanceof Villager && killer instanceof Player p) {

            event.getDrops().add(dropFrag);

            addHash(p);
            p.sendMessage(format("&7¡Has sacrificado a un &6&lAldeano&7, has recibido &6&l" + size + " &cFragmento(s) de Sangre&7!"));
        }
    }

}
