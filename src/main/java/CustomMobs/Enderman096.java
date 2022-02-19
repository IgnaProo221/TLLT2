package CustomMobs;

import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.monster.EntityEnderman;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;

public class Enderman096 extends EntityEnderman{
    public Enderman096(Location loc){
        super(EntityTypes.w,((CraftWorld)loc.getWorld()).getHandle());
        this.setPosition(loc.getX(),loc.getY(),loc.getZ());

        //aqui deberia enojarse mas rapido idk
    }
}
