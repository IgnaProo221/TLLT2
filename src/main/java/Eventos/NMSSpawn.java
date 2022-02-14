package Eventos;

import CustomMobs.Argus;
import CustomMobs.DreadNightmare;
import CustomMobs.UltraSniper;
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
        if (!(e.getEntity() instanceof Animals) || e.getLocation().getBlock().isLiquid()) return;
        if (spawn < 20) {
            int mob = random.nextInt(2);
            if(mob == 1) {
                DreadNightmare dreadNightmare = new DreadNightmare(e.getLocation());
                WorldServer world = ((CraftWorld) e.getEntity().getWorld()).getHandle();

                world.addEntity(dreadNightmare, CreatureSpawnEvent.SpawnReason.CUSTOM);
                Bukkit.getServer().getConsoleSender().sendMessage("Entidad: " + dreadNightmare.getEntityType().id);
            }else if(mob == 2){
                UltraSniper sniper = new UltraSniper(e.getLocation());
                WorldServer worldServer = ((CraftWorld)e.getEntity().getWorld()).getHandle();
                worldServer.addEntity(sniper, CreatureSpawnEvent.SpawnReason.CUSTOM);
                Bukkit.getServer().getConsoleSender().sendMessage("Entidad: " + sniper.getEntityType().id);
            }
        }
    }

}
