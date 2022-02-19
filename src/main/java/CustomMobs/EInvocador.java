package CustomMobs;

import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.monster.EntityEvoker;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;

public class EInvocador extends EntityEvoker{
    public EInvocador(Location loc){
        super(EntityTypes.y,((CraftWorld)loc.getWorld()).getHandle());
        this.setPosition(loc.getX(),loc.getY(),loc.getZ());

        //Pichavoker (necesito estudiar la IA tambien )
    }
}
