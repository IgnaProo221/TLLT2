package CustomMobs;

import net.minecraft.network.chat.ChatComponentText;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.ai.attributes.GenericAttributes;
import net.minecraft.world.entity.animal.EntityIronGolem;
import net.minecraft.world.entity.monster.EntityGhast;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.persistence.PersistentDataType;
import tlldos.tll2.TLL2;

public class DreadNightmare extends EntityGhast{
    public DreadNightmare(Location location){
        super(EntityTypes.F,((CraftWorld)location.getWorld()).getHandle());
        this.setPosition(location.getX(),location.getY(),location.getZ());
        this.setCustomName(new ChatComponentText(ChatColor.DARK_GREEN + "Dread Nightmare"));
        this.getBukkitEntity().getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class),"DREAD_NIGHTMARE"), PersistentDataType.STRING,"DREAD_NIGHTMARE");
        this.getAttributeInstance(GenericAttributes.a).setValue(100.0D);
        this.setHealth(100);
    }
}
