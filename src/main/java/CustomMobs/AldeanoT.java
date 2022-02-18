package CustomMobs;

import net.kyori.adventure.text.TextComponent;
import net.minecraft.network.chat.ChatComponentText;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.ai.attributes.GenericAttributes;
import net.minecraft.world.entity.ai.goal.PathfinderGoalMeleeAttack;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalNearestAttackableTarget;
import net.minecraft.world.entity.npc.EntityVillager;
import net.minecraft.world.entity.player.EntityHuman;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import tlldos.tll2.TLL2;

import static Utilidades.Format.format;

public class AldeanoT extends EntityVillager{
    public AldeanoT(Location loc){
        super(EntityTypes.aV,((CraftWorld)loc.getWorld()).getHandle());
        this.setPosition(loc.getX(),loc.getY(),loc.getZ());
        this.setCustomName(new ChatComponentText(format("&cAldeano Terrorista")));
        this.getAttributeMap().b(GenericAttributes.f);
        this.getAttributeInstance(GenericAttributes.f).setValue(1);
        this.getBukkitCreature().getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class),"VILLAGER_TERRORISTA"), PersistentDataType.STRING,"VILLAGER_TERRORISTA");
        this.getBukkitCreature().getEquipment().setItemInMainHand(new ItemStack(Material.TNT));
        this.getBukkitCreature().getEquipment().setDropChance(EquipmentSlot.HAND,0);
    }
    @Override
    public void initPathfinder(){
        super.initPathfinder();
        this.bP.a(0,new PathfinderGoalMeleeAttack(this,1.0D,true));
        this.bQ.a(1,new PathfinderGoalNearestAttackableTarget<>(this, EntityHuman.class,true));
    }
}
