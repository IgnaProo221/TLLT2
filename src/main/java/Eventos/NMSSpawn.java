package Eventos;

import CustomMobs.FlyingNightmare;
import Utilidades.Mobs;
import Utilidades.Warn;
import net.minecraft.world.entity.Entity;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.v1_18_R1.CraftWorld;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.LivingEntity;
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

            if ((int)(Math.random() * 10) == 1){
                FlyingNightmare flyingNightmare = new FlyingNightmare(e.getLocation());
                CraftWorld world = ((CraftWorld)e.getLocation().getWorld());
                LivingEntity elpep = (LivingEntity)flyingNightmare;
                elpep.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(50);
                elpep.setHealth(50);
                elpep.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class),"FLYING_NIGHTMARE"), PersistentDataType.STRING, "FLYING_NIGHTMARE");
                world.addEntityToWorld(flyingNightmare, CreatureSpawnEvent.SpawnReason.CUSTOM);
            }else if((int)(Math.random() * 10) == 10){
                var dymensesenta = e.getLocation().getWorld().spawn(e.getLocation(), Creeper.class);
                Mobs.riftedCreeper(dymensesenta);
                CraftWorld world = ((CraftWorld)e.getLocation().getWorld());
                world.addEntityToWorld((Entity) dymensesenta, CreatureSpawnEvent.SpawnReason.CUSTOM);
            }
        }catch (Exception exception){
            exception.printStackTrace();
            Warn.Mutant(exception);
        }
    }
}
