package CustomMobs;


import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.monster.EntityGhast;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;

import static Utilidades.Format.format;

public class FlyingNightmare extends EntityGhast {
    public FlyingNightmare(Location loc){
        super(EntityTypes.F,((CraftWorld) loc.getWorld()).getHandle());

        initAttributes();


        this.d(loc.getX(),loc.getY(),loc.getZ());


      //  this.setCustomName(new TextComponent(format("&c&lFlying Nightmare")));
        this.setHealth(40);
        //this.removeWhenFarAway(1);
    }

    public void initAttributes(){
        this.craftAttributes.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(1.2);
        this.craftAttributes.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40.0);

    }

}
