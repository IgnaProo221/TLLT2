package CustomMobs;

import Utilidades.Format;
import net.minecraft.sounds.SoundEffect;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.ai.attributes.GenericAttributes;
import net.minecraft.world.entity.monster.EntitySkeleton;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.persistence.PersistentDataType;
import tlldos.tll2.TLL2;

public class UltraSniper extends EntitySkeleton{
    public UltraSniper(Location location){
        super(EntityTypes.aB,((CraftWorld)location.getWorld()).getHandle());
        this.setPosition(location.getX(),location.getY(),location.getZ());
        this.getBukkitCreature().setCustomName(Format.format("&7&lUltra Sniper"));
        this.getAttributeInstance(GenericAttributes.a).setValue(70.0);
        this.setHealth(70);
        this.getBukkitCreature().getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class),"ULTRA_SNIPER"), PersistentDataType.STRING,"ULTRA_SNIPER");
    }
}
