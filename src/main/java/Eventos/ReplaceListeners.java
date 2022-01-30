package Eventos;

import Utilidades.Mobs;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import tlldos.tll2.TLL2;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ReplaceListeners implements Listener{
    private TLL2 plugin;
    public ReplaceListeners(TLL2 plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void chunkLol(ChunkLoadEvent e){
        for (LivingEntity liv : Arrays.stream(e.getChunk().getEntities()).filter(entity -> entity instanceof LivingEntity).map(LivingEntity.class::cast).collect(Collectors.toList())) {
            remplazoMob(liv);
        }
    }


    public void remplazoMob(LivingEntity entity){
        if(entity instanceof Cow){
            entity.remove();
            var vort = entity.getLocation().getWorld().spawn(entity.getLocation(), Creeper.class);
            Mobs.vortice(vort);
        }
        if(entity instanceof Chicken){
            entity.remove();
            var silvercos = entity.getLocation().getWorld().spawn(entity.getLocation(), Silverfish.class);
            Mobs.cosmicSilver(silvercos);
        }
        if(entity instanceof Rabbit){
            entity.remove();
            var ghastdou = entity.getLocation().getWorld().spawn(entity.getLocation(), Ghast.class);
            Mobs.riftedGhast(ghastdou);
        }
    }

}
