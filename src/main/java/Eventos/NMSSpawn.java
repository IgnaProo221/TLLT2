package Eventos;

import CustomMobs.*;
import com.sk89q.worldedit.world.entity.EntityTypes;
import net.minecraft.server.level.WorldServer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import tlldos.tll2.TLL2;

import java.util.Random;

public class NMSSpawn implements Listener{
    TLL2 plugin;
    public NMSSpawn(TLL2 plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void customGen(EntitySpawnEvent e) {
        Random random = new Random();
        int spawn = random.nextInt(100);
        if (!(e.getEntity() instanceof Animals) || e.getLocation().getBlock().isLiquid() || !(e.getEntity() instanceof Bee  )) return;
        if (spawn < 20) {
            e.setCancelled(true);
            int mob = random.nextInt(3);
            if(mob == 1) {
                DreadNightmare dreadNightmare = new DreadNightmare(e.getLocation());
                WorldServer world = ((CraftWorld) e.getEntity().getWorld()).getHandle();
                world.addEntity(dreadNightmare, CreatureSpawnEvent.SpawnReason.CUSTOM);
                Bukkit.getServer().getConsoleSender().sendMessage("Entidad: " + dreadNightmare.getEntityType().id);
            }else if(mob == 2){
                HostileTest hostileTest = new HostileTest(e.getLocation());
                WorldServer worldServer = ((CraftWorld)e.getEntity().getWorld()).getHandle();
                worldServer.addEntity(hostileTest, CreatureSpawnEvent.SpawnReason.CUSTOM);
                Bukkit.getServer().getConsoleSender().sendMessage("Entidad: " + hostileTest.getEntityType().id);
            }else{
                AldeanoT aldeanoT = new AldeanoT(e.getLocation());
                WorldServer worldServer = ((CraftWorld)e.getEntity().getWorld()).getHandle();
                worldServer.addEntity(aldeanoT, CreatureSpawnEvent.SpawnReason.CUSTOM);
                Bukkit.getServer().getConsoleSender().sendMessage("Entidad: "+aldeanoT  .getEntityType().id);
            }
        }
    }

}
