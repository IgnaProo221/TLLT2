package Eventos;

import Utilidades.Mobs;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import tlldos.tll2.TLL2;

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
        if(en instanceof Chicken){
            e.setCancelled(true);
            var zombie = en.getLocation().getWorld().spawn(en.getLocation(), Zombie.class);
                Mobs.mechaZombie(zombie);

        }
        if(en instanceof Skeleton){
            e.setCancelled(true);
            var pillager = en.getLocation().getWorld().spawn(en.getLocation(), Pillager.class);
                Mobs.madScientist(pillager);

        }
        if(en instanceof Spider){
             e.setCancelled(true);
            var silver = en.getLocation().getWorld().spawn(en.getLocation(), Silverfish.class);
                Mobs.labSilver(silver);

        }
    }


}
