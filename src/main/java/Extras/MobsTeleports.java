package Extras;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import tlldos.tll2.TLL2;

import java.util.Random;

public class MobsTeleports implements Listener {
    private TLL2 plugin;
    public MobsTeleports(TLL2 plugin){
        this.plugin = plugin;
    }
    public boolean teleport(Entity e, int locX, int locY, int locZ, World world) {
        for (int i = 0; i < 64; ++i) {
            if (eq(e, locX, locY, locZ, world)) {
                return true;
            }
        }

        return false;
    }
    private boolean eq(Entity e, int locX, int locY, int locZ, World world) {
        Random random = new Random();

        double x = locX + (random.nextDouble() - 0.5D) * 64.0D;
        double y = locY + (double) (random.nextInt(64) - 32);
        double z = locZ + (random.nextDouble() - 0.5D) * 64.0D;

        Block b = world.getBlockAt((int)x, (int)y, (int)z);

        while (b.getY() > 0 && b.getType().isAir()) b = b.getRelative(BlockFace.DOWN);

        if (b.getY() <= 0) return false;

        return e.teleport(new Location(world, x, b.getY() + 1, z));
    }


    @EventHandler
    public void creeperTp(EntityDamageEvent event){
        Random random   = new Random();
        int tp = random.nextInt(100);
        if(event.getCause() == EntityDamageEvent.DamageCause.SUFFOCATION){
            if(event.getEntity() instanceof Creeper c){
                int locX = c.getLocation().getBlockX();
                int locY = c.getLocation().getBlockY();
                int locZ = c.getLocation().getBlockZ();
                teleport(c,locX,locY,locZ, c.getWorld());
            }
        }else if(event.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK){
            if(event.getEntity() instanceof Creeper c){
                if(tp > 80){
                    int locX = c.getLocation().getBlockX();
                    int locY = c.getLocation().getBlockY();
                    int locZ = c.getLocation().getBlockZ();
                    teleport(c,locX,locY,locZ, c.getWorld());
                }
            }
        }
    }
}
