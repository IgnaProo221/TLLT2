package CustomMobs;

import net.minecraft.network.chat.ChatComponentText;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.ai.attributes.GenericAttributes;
import net.minecraft.world.entity.ai.goal.PathfinderGoalMeleeAttack;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalNearestAttackableTarget;
import net.minecraft.world.entity.animal.EntityCow;
import net.minecraft.world.entity.player.EntityHuman;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;

import static Utilities.Format.format;

public class HostileTest extends EntityCow{
    public HostileTest(Location loc){
        super(EntityTypes.n,((CraftWorld)loc.getWorld()).getHandle());
        this.setPosition(loc.getX(),loc.getY(),loc.getZ());
        this.setCustomName(new ChatComponentText(format("&6Vaca")));
        this.getAttributeMap().b(GenericAttributes.f);
        this.getAttributeInstance(GenericAttributes.f).setValue(20);
    }

    @Override
    public void initPathfinder(){
        super.initPathfinder();
        this.bP.a(0,new PathfinderGoalMeleeAttack(this,1.0D,true));
        this.bQ.a(1,new PathfinderGoalNearestAttackableTarget<>(this, EntityHuman.class,true));
    }
}
