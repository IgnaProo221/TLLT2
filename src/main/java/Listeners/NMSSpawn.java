package Listeners;

import CustomMobs.*;
import Utilities.Mobs;
import net.minecraft.server.level.WorldServer;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import tlldos.tll2.TLL2;

import java.util.ArrayList;
import java.util.Random;

public class NMSSpawn implements Listener{
    TLL2 plugin;
    public NMSSpawn(TLL2 plugin){
        this.plugin = plugin;
    }





    //TODO ESTO PUEDE SALIR O MUY MAL O MUY BIEN
    @EventHandler
    public void customGen(CreatureSpawnEvent e){
        var world = e.getEntity().getWorld();
        var entity = e.getEntity();
        int chance = new Random().nextInt(100);
        if(world.getEnvironment() == World.Environment.NETHER || world.getEnvironment() == World.Environment.THE_END)return;
        if(entity instanceof Animals)return;
        if(!(e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL))return;
        if(entity.getWorld().getEnvironment() == World.Environment.NORMAL){
            int minrrspawn = new Random().nextInt(10000);
            if(minrrspawn == 1){
                int mobtype = new Random().nextInt(3);
                if(mobtype == 1){
                    entity.remove();
                    Enderman enderman = entity.getWorld().spawn(entity.getLocation(),Enderman.class);
                    Mobs.darkSpectre(enderman);
                }else if(mobtype == 2){
                    entity.remove();
                    Skeleton skeleton = entity.getWorld().spawn(entity.getLocation(),Skeleton.class);
                    Mobs.sombra(skeleton);
                }else{
                    entity.remove();
                    Endermite endermite = entity.getWorld().spawn(entity.getLocation(),Endermite.class);
                    Mobs.gorgomite(endermite);
                }
            }
        }

        if(world.getEnvironment() == World.Environment.NETHER){
            if(chance == 1){
                int spidertype = new Random().nextInt(3);
                if(spidertype == 1){
                    entity.remove();
                    Spider spider = entity.getWorld().spawn(entity.getLocation(),Spider.class);
                    Mobs.agileTarantule(spider);
                }else if(spidertype == 2){
                    entity.remove();
                    Spider spider = entity.getWorld().spawn(entity.getLocation(),Spider.class);
                    Mobs.interdimensionalVisitor(spider);
                }else{
                    entity.remove();
                    Spider spider = entity.getWorld().spawn(entity.getLocation(),Spider.class);
                    Mobs.solarScorpion(spider);
                }
            }
        }

        if(world.isThundering()){
            if(chance == 1){
                int mobtype = new Random().nextInt(5);
                if(mobtype == 1){
                    e.setCancelled(true);
                    Zombie zombie = entity.getLocation().getWorld().spawn(entity.getLocation(),Zombie.class);
                    Mobs.stoneSoldier(zombie);
                }else if(mobtype == 2){
                    e.setCancelled(true);
                    Silverfish silverfish = entity.getLocation().getWorld().spawn(entity.getLocation(),Silverfish.class);
                    Mobs.escarabajoGoliath(silverfish);
                }else if(mobtype == 3){
                    e.setCancelled(true);
                    Drowned drowned = entity.getLocation().getWorld().spawn(entity.getLocation(),Drowned.class);
                    Mobs.goblin(drowned);
                }else if(mobtype == 4){
                    e.setCancelled(true);
                    Creeper creeper = entity.getLocation().getWorld().spawn(entity.getLocation(),Creeper.class);
                    Mobs.oreCreeper(creeper);
                }else{
                    e.setCancelled(true);
                    Skeleton skeleton = entity.getLocation().getWorld().spawn(entity.getLocation(),Skeleton.class);
                    Mobs.spectreAssasin(skeleton);
                }
            }
        }
    }

    /*@EventHandler
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
    }*/

}
