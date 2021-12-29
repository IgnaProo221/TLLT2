package Extras;

import Utilidades.Mobs;
import Utilidades.TLLEntities;
import org.bukkit.Location;
import org.bukkit.entity.Bee;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Silverfish;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import tlldos.tll2.TLL2;

public class SpawnerListeners implements Listener {
    private TLL2 plugin;

    public SpawnerListeners(TLL2 plugin) {
        this.plugin = plugin;
    }


//AYUDEN EN ESTO PORFA
//COMO HAGO QUE LA ENTIDAD QUE SPAWNEE SEA UNO DE LOS TLL ENTITIES




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

            }
        }
    }
}
