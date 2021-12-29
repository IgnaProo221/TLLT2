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
        if(en instanceof Zombie){
            if(random.nextInt(100) == 10){
                e.setCancelled(true);
                var zombie = (Zombie)en;
                Mobs.mechaZombie(zombie);
            }
        }
        if(en instanceof Skeleton){
            if(random.nextInt(100) == 10){
                e.setCancelled(true);
                var pillager = (Pillager)en;
                Mobs.madScientist(pillager);
            }
        }
        if(en instanceof Spider){
            if(random.nextInt(100) == 10){
                e.setCancelled(true);
                var iron = (IronGolem)en;
                Mobs.exoGolem(iron);
            }
        }
    }


}
