package CustomMobs;


import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Ghast;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import org.bukkit.craftbukkit.v1_18_R1.CraftWorld;

import static Utilidades.Format.format;

public class FlyingNightmare extends Ghast{
    public FlyingNightmare(Location loc){
        super(EntityType.GHAST,((CraftWorld) loc.getWorld()).getHandle());

        initAttributes();


        this.setPos(loc.getX(),loc.getY(),loc.getZ());


        this.setCustomName(new TextComponent(format("&c&lFlying Nightmare")));
        this.setHealth(40);
        this.removeWhenFarAway(1);
    }

    public void initAttributes(){
        this.craftAttributes.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(1.2);
        this.craftAttributes.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40.0);

    }

}
