package CustomMobs;

import Utilidades.Format;
import net.minecraft.network.chat.ChatComponentText;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.ai.attributes.GenericAttributes;
import net.minecraft.world.entity.ai.goal.PathfinderGoalMeleeAttack;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalNearestAttackableTarget;
import net.minecraft.world.entity.animal.horse.EntityHorseZombie;
import net.minecraft.world.entity.player.EntityHuman;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class JineteZ extends EntityHorseZombie{
    public JineteZ(Location loc){
        super(EntityTypes.bf,((CraftWorld)loc.getWorld()).getHandle());
        this.setPosition(loc.getX(),loc.getY(),loc.getZ());
        this.setCustomName(new ChatComponentText(Format.format("&6Jinete Zombi")));
        this.getAttributeMap().b(GenericAttributes.f);
        this.getAttributeInstance(GenericAttributes.f).setValue(10);
        this.getBukkitCreature().addPotionEffect(new PotionEffect(PotionEffectType.SPEED,Integer.MAX_VALUE, 1,false, false, false));
    }
    @Override
    public void initPathfinder(){
        super.initPathfinder();
        this.bP.a(0,new PathfinderGoalMeleeAttack(this,1.0D,true));
        this.bQ.a(1,new PathfinderGoalNearestAttackableTarget<>(this, EntityHuman.class,true));
    }
}
