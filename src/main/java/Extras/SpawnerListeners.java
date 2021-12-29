package Extras;

import Utilidades.Mobs;
import Utilidades.TLLEntities;
import org.bukkit.Location;
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
        }
    }
}
