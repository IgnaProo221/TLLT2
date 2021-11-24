package Eventos;

import Utilidades.Mobs;
import Utilidades.TLLEntities;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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
    }



    @EventHandler
    public void entityDeath (EntityDeathEvent event) {

        Entity entity = event.getEntity();
        Player killer = event.getEntity().getKiller();

        Random random = new Random();

        int size = random.nextInt(2);
        ItemStack dropFrag = createFragmentoSangre(size);

        if (entity instanceof Villager && killer instanceof Player) {

            event.getDrops().add(dropFrag);

            addHash((Player) killer);
            ((Player) killer).sendMessage(format("&7¡Has sacrificado a un &6&lAldeano&7, has recibido &6&l" + size + " &cFragmento(s) de Sangre&7!"));
        }
    }

}
