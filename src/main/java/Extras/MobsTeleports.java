package Extras;

import Utilities.Utils;
import io.papermc.paper.event.entity.EntityMoveEvent;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.persistence.PersistentDataType;
import tlldos.tll2.TLL2;

import java.util.Random;

public class MobsTeleports implements Listener {
    private TLL2 plugin;
    public MobsTeleports(TLL2 plugin){
        this.plugin = plugin;
    }

    public void teleport(Entity e, int locX, int locY, int locZ, World world) {
        for (int i = 0; i < 64; ++i) {
            if (eq(e, locX, locY, locZ, world)) {
                return;
            }
        }
    }

    private boolean eq(Entity e, int locX, int locY, int locZ, World world) {
        Random random = new Random();

        double x = locX + (random.nextDouble() - 0.5D) * 64.0D;
        double y = locY + (double) (random.nextInt(64) - 32);
        double z = locZ + (random.nextDouble() - 0.5D) * 64.0D;

        Block b = world.getBlockAt((int)x, (int)y, (int)z);

        while (b.getY() > 0 && b.getType().isAir()) b = b.getRelative(BlockFace.DOWN);

        if (b.getY() <= 0) return false;
        if(b.isSolid()) return false;


        return e.teleport(new Location(world, x, b.getY() + 1, z));
    }




    @EventHandler
    public void creeperTp(EntityDamageEvent event){
        Random random = new Random();

        int tp = random.nextInt(100);

        if (event.getCause() != EntityDamageEvent.DamageCause.VOID) {

            if(event.getEntity() instanceof Skeleton c){
                if (c.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "SPECTRE"), PersistentDataType.STRING)) {
                    int locX = c.getLocation().getBlockX();
                    int locY = c.getLocation().getBlockY();
                    int locZ = c.getLocation().getBlockZ();
                    if (tp > 90) {

                        c.playEffect(EntityEffect.TELEPORT_ENDER);

                        c.getWorld().playSound(c.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 5.0F, 0.0F);

                        teleport(c, locX, locY, locZ, c.getWorld());
                    }
                }
            }else if(event.getEntity() instanceof Creeper creeper){
                    int locX = creeper.getLocation().getBlockX();
                    int locY = creeper.getLocation().getBlockY();
                    int locZ = creeper.getLocation().getBlockZ();
                    if (tp > 90) {

                        creeper.playEffect(EntityEffect.TELEPORT_ENDER);

                        creeper.getWorld().playSound(creeper.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 5.0F, 0.0F);

                        teleport(creeper, locX, locY, locZ, creeper.getWorld());
                    }
            }
            /*if (event.getEntity() instanceof Creeper c) {

                int locX = c.getLocation().getBlockX();
                int locY = c.getLocation().getBlockY();
                int locZ = c.getLocation().getBlockZ();


                if (tp < 60) {

                    c.playEffect(EntityEffect.TELEPORT_ENDER);

                    c.getWorld().playSound(c.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 5.0F, 0.0F);

                    teleport(c, locX, locY, locZ, c.getWorld());
                }
            } else if (event.getEntity() instanceof Ghast z) {

                int locX = z.getLocation().getBlockX();
                int locY = z.getLocation().getBlockY();
                int locZ = z.getLocation().getBlockZ();


                if (tp < 60) {

                    z.playEffect(EntityEffect.TELEPORT_ENDER);

                    z.getWorld().playSound(z.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 5.0F, 0.0F);

                    teleport(z, locX, locY, locZ, z.getWorld());
                }
            }*/
        }
    }

    @EventHandler
    public void tpmove(EntityMoveEvent e){
        Random random = new Random();

        int tp = random.nextInt(10000);
        if(e.getEntity() instanceof Skeleton c){
            if (c.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "SPECTRE"), PersistentDataType.STRING)) {
            int locX = c.getLocation().getBlockX();
            int locY = c.getLocation().getBlockY();
            int locZ = c.getLocation().getBlockZ();
            if (tp == 1) {

                c.playEffect(EntityEffect.TELEPORT_ENDER);

                c.getWorld().playSound(c.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 5.0F, 0.0F);

                teleport(c, locX, locY, locZ, c.getWorld());
               }
            }
        }else if(e.getEntity() instanceof Creeper creeper){
                int locX = creeper.getLocation().getBlockX();
                int locY = creeper.getLocation().getBlockY();
                int locZ = creeper.getLocation().getBlockZ();
                if (tp == 1) {

                    creeper.playEffect(EntityEffect.TELEPORT_ENDER);

                    creeper.getWorld().playSound(creeper.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 5.0F, 0.0F);

                    teleport(creeper, locX, locY, locZ, creeper.getWorld());
                }
        }
    }

}
