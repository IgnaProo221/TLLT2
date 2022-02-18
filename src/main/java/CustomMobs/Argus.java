package CustomMobs;

import net.kyori.adventure.text.TextComponent;
import net.minecraft.network.chat.ChatComponentText;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.monster.EntityVex;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;

import static Utilidades.Format.format;

public class Argus extends EntityVex{
    public Argus(Location loc){
        super(EntityTypes.aU,((CraftWorld) loc.getWorld()).getHandle());
        this.setPosition(loc.getX(),loc.getY(),loc.getZ());
        this.setHealth(10);
        this.setCustomName(new ChatComponentText(format("&2&lArgus")));
    }
}
