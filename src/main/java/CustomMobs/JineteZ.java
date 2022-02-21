package CustomMobs;

import Utilities.Format;
import net.minecraft.network.chat.ChatComponentText;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.animal.horse.EntityHorseZombie;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class JineteZ extends EntityHorseZombie{
    public JineteZ(Location loc){
        super(EntityTypes.bf,((CraftWorld)loc.getWorld()).getHandle());
        this.setPosition(loc.getX(),loc.getY(),loc.getZ());
        this.setCustomName(new ChatComponentText(Format.format("&6Jinete Zombi")));
        this.getBukkitCreature().setRemoveWhenFarAway(true);
        this.getBukkitCreature().addPotionEffect(new PotionEffect(PotionEffectType.SPEED,Integer.MAX_VALUE, 1,false, false, false));
    }
}
