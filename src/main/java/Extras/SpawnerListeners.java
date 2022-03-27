package Extras;

import Utilities.Mobs;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import tlldos.tll2.TLL2;

public class SpawnerListeners implements Listener {
    private TLL2 plugin;

    public SpawnerListeners(TLL2 plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void spawnerSpawn(CreatureSpawnEvent event) {
        Entity en = event.getEntity();
        if (en instanceof Bee) {
            if (event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.SPAWNER) {
                if(en.getCustomName().equalsIgnoreCase("a")){
                    event.setCancelled(true);
                    var silver = en.getLocation().getWorld().spawn(en.getLocation(), Silverfish.class);
                    Mobs.labSilver(silver);
                }
                if(en.getCustomName().equalsIgnoreCase("b")){
                    event.setCancelled(true);
                    var pillager = en.getLocation().getWorld().spawn(en.getLocation(), Pillager.class);
                    Mobs.madScientist(pillager);
                }
                if(en.getCustomName().equalsIgnoreCase("c")){
                    event.setCancelled(true);
                    var zombie = en.getLocation().getWorld().spawn(en.getLocation(), Zombie.class);
                    Mobs.mechaZombie(zombie);
                }
                if(en.getCustomName().equalsIgnoreCase("d")){
                    event.setCancelled(true);
                    var irongolem = en.getLocation().getWorld().spawn(en.getLocation(), IronGolem.class);
                    Mobs.exoGolem(irongolem);
                }
            }
        }else if(en instanceof Silverfish) {
            if (event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.SPAWNER) {
                if (en.getCustomName().equalsIgnoreCase("a")) {
                    event.setCancelled(true);
                    var lushz = en.getLocation().getWorld().spawn(en.getLocation(), Zombie.class);
                    Mobs.lushZombie(lushz);
                }
                if (en.getCustomName().equalsIgnoreCase("b")) {
                    event.setCancelled(true);
                    var lushz = en.getLocation().getWorld().spawn(en.getLocation(), Skeleton.class);
                    Mobs.lushSkeleton(lushz);
                }
                if (en.getCustomName().equalsIgnoreCase("c")) {
                    event.setCancelled(true);
                    var lushz = en.getLocation().getWorld().spawn(en.getLocation(), IronGolem.class);
                    Mobs.experimentWOOD(lushz);
                }
                if (en.getCustomName().equalsIgnoreCase("d")) {
                    event.setCancelled(true);
                    var lushz = en.getLocation().getWorld().spawn(en.getLocation(), Creeper.class);
                    Mobs.roboMine(lushz);
                }
                if (en.getCustomName().equalsIgnoreCase("f")) {
                    event.setCancelled(true);
                    var lushz = en.getLocation().getWorld().spawn(en.getLocation(), Creeper.class);
                    Mobs.mossCreeper(lushz);
                }
                if (en.getCustomName().equalsIgnoreCase("g")) {
                    event.setCancelled(true);
                    var lushz = en.getLocation().getWorld().spawn(en.getLocation(), Spider.class);
                    Mobs.spiderJungla(lushz);
                }
            }
        }else if(en instanceof Endermite){
            if(en.getCustomName().equalsIgnoreCase("a")){
                event.setCancelled(true);
                var moblol = en.getWorld().spawn(en.getLocation(),Vex.class);
                Mobs.Banshee(moblol);
            }
            if(en.getCustomName().equalsIgnoreCase("b")){
                event.setCancelled(true);
                var moblol = en.getWorld().spawn(en.getLocation(),Ghast.class);
                Mobs.Nightmare(moblol);
            }
            if(en.getCustomName().equalsIgnoreCase("c")){
                event.setCancelled(true);
                var moblol = en.getWorld().spawn(en.getLocation(),Vindicator.class);
                Mobs.KillerScream(moblol);
            }
            if(en.getCustomName().equalsIgnoreCase("d")){
                event.setCancelled(true);
                var moblol = en.getWorld().spawn(en.getLocation(),Creeper.class);
                Mobs.Overscream(moblol);
            }
            if(en.getCustomName().equalsIgnoreCase("e")){
                event.setCancelled(true);
                var moblol = en.getWorld().spawn(en.getLocation(),Blaze.class);
                Mobs.hellfire(moblol);
            }
            if(en.getCustomName().equalsIgnoreCase("f")){
                event.setCancelled(true);
                var moblol = en.getWorld().spawn(en.getLocation(),Blaze.class);
                Mobs.InfernoLord(moblol);
            }
        }else if(en instanceof Slime){
            if(en.getCustomName().equalsIgnoreCase("a")){
                event.setCancelled(true);
                var mage = en.getWorld().spawn(en.getLocation(),Evoker.class);
                Mobs.evokerhex(mage);
            }
            if(en.getCustomName().equalsIgnoreCase("b")){
                event.setCancelled(true);
                var mage = en.getWorld().spawn(en.getLocation(),Evoker.class);
                Mobs.evokerFreeze(mage);
            }
            if(en.getCustomName().equalsIgnoreCase("c")){
                event.setCancelled(true);
                var mage = en.getWorld().spawn(en.getLocation(),Evoker.class);
                Mobs.evokerFire(mage);
            }
            if(en.getCustomName().equalsIgnoreCase("d")){
                event.setCancelled(true);
                var mage = en.getWorld().spawn(en.getLocation(),Evoker.class);
                Mobs.evokerWind(mage);
            }
            if(en.getCustomName().equalsIgnoreCase("e")){
                event.setCancelled(true);
                var mage = en.getWorld().spawn(en.getLocation(),Evoker.class);
                Mobs.evokerExplosive(mage);
            }
            if(en.getCustomName().equalsIgnoreCase("f")){
                event.setCancelled(true);
                var mage = en.getWorld().spawn(en.getLocation(),Pillager.class);
                Mobs.dynamLlager(mage);
            }
            if(en.getCustomName().equalsIgnoreCase("g")){
                event.setCancelled(true);
                var mage = en.getWorld().spawn(en.getLocation(),Evoker.class);
                Mobs.maestryWizard(mage);
            }
        }
    }
}
