package Eventos;

import Utilidades.Mobs;
import Utilidades.Warn;
<<<<<<< HEAD
import net.minecraft.world.entity.Entity;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.LivingEntity;
=======
import org.bukkit.entity.*;
>>>>>>> c3242743cd2d4882f4a530b227accfaea4cd72df
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.persistence.PersistentDataType;
import tlldos.tll2.TLL2;

public class NMSSpawn implements Listener{
    TLL2 plugin;
    public NMSSpawn(TLL2 plugin){
        this.plugin = plugin;
    }
    @EventHandler
    public void nmsqueSpawnea(CreatureSpawnEvent e){
        try{
            if(!(e.getEntity() instanceof Animals)) return;
            if(e.getLocation().getBlock().isLiquid()) return;

            if ((int)(Math.random() * 10) == 100){

            }
        }catch (Exception exception){
            exception.printStackTrace();
            Warn.Mutant(exception);
        }
    }
}
