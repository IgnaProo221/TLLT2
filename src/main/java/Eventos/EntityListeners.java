package Eventos;

import Utilidades.Mobs;
import Utilidades.TLLEntities;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

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


    }


    ///
    ///
    ///
    /// SON LAS 12:00 PM, ESTOY CANSADO QUE LA MIERDA
    /// NO TENGO LA MENOR IDEA DE COMO HACER ESTO, PERO NECESITO QUE EL PILLAGER DISPARE Y SE EQUIPE LA FLECHA
    /// PARA QUE LA DISPARE INFINITAMENTE
    /// LEPEPOS SI ESTAS LEYENDO ESTO PERDON PERO NO ENTENDI NI CULO DE LO QUE ME EXPLICASTE :,V
    /// BTW NECESITO UNA MANERA DE COMO SPAWNEAR LOS MOBS DE Mobs.class PARA TESTEARLOS GRACIAS
    ///
    ///
    ///






    @EventHandler
    public void onshotEntity(ProjectileLaunchEvent e){
        ItemStack flecha1 = new ItemStack(Material.TIPPED_ARROW);
        PotionMeta flecha1efect = (PotionMeta) flecha1.getItemMeta();
        flecha1efect.setBasePotionData(new PotionData(PotionType.SLOWNESS));
        flecha1efect.addCustomEffect(new PotionEffect(PotionEffectType.SLOW, 400, 1), true);
        flecha1efect.addCustomEffect(new PotionEffect(PotionEffectType.POISON, 400, 1), true);
        flecha1efect.addCustomEffect(new PotionEffect(PotionEffectType.CONFUSION, 400, 1), true);
        flecha1.setItemMeta(flecha1efect);


        if(e.getEntity().getShooter() instanceof Pillager){
            Pillager p = (Pillager) e.getEntity();
            if(p.getCustomName().equalsIgnoreCase(format("&cMad Scientist"))){
                p.getEquipment().setItemInOffHand(flecha1);
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
