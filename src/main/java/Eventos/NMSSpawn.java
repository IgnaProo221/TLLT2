package Eventos;

import CustomMobs.Argus;
import CustomMobs.DreadNightmare;
import Utilidades.Mobs;
import Utilidades.Warn;
import net.minecraft.server.level.WorldServer;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.persistence.PersistentDataType;
import tlldos.tll2.TLL2;

import java.util.Random;

public class NMSSpawn implements Listener{
    TLL2 plugin;
    public NMSSpawn(TLL2 plugin){
        this.plugin = plugin;
    }
    @EventHandler
    public void customGen(EntitySpawnEvent e){
        Random random = new Random();
        int spawnlol = random.nextInt(100);
            if (!(e.getEntity() instanceof Animals)) return;
            if (e.getLocation().getBlock().isLiquid()) return;
            if (spawnlol < 30) {
                Argus argus = new Argus(e.getLocation());
                CraftWorld craftWorld = (CraftWorld) e.getLocation().getWorld();
                craftWorld.addEntity(argus, CreatureSpawnEvent.SpawnReason.CUSTOM);
                Bukkit.getServer().getConsoleSender().sendMessage("Debug 1 nms");
            } else if (spawnlol < 31) {
                DreadNightmare dreadNightmare = new DreadNightmare(e.getLocation());
                CraftWorld craftWorld = (CraftWorld)e.getLocation().getWorld();
                craftWorld.addEntity(dreadNightmare, CreatureSpawnEvent.SpawnReason.CUSTOM);
                Bukkit.getServer().getConsoleSender().sendMessage("Debug 2 nms");
            }
    }
}
