package Listeners;

import Utilities.Mobs;
import Utilities.Warn;
import net.minecraft.world.entity.EntityInsentient;
import net.minecraft.world.entity.ai.goal.PathfinderGoalMeleeAttack;
import net.minecraft.world.entity.ai.goal.PathfinderGoalSelector;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalNearestAttackableTarget;
import net.minecraft.world.entity.animal.EntityCat;
import net.minecraft.world.entity.animal.EntityFox;
import net.minecraft.world.entity.animal.EntityGolem;
import net.minecraft.world.entity.animal.EntityWolf;
import net.minecraft.world.entity.player.EntityHuman;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.entity.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import tlldos.tll2.TLL2;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

import static Utilities.Format.format;

public class ReplaceListeners implements Listener{
    private TLL2 plugin;
    public ReplaceListeners(TLL2 plugin){
        this.plugin = plugin;
    }

    //TODO necesito probar esto
    @EventHandler
    public void onEntityLoad(ChunkLoadEvent event){
        for(Entity entity : event.getChunk().getEntities()) {
            if (entity != null) return;
                if (entity instanceof Wolf wolf) {
                    wolf.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(60);
                    CraftWolf craft = ((CraftWolf) wolf);
                    EntityWolf entityWolf = craft.getHandle();
                    try {
                        Class<? extends EntityInsentient> cl = EntityInsentient.class;
                        Field gf = cl.getDeclaredField("bP");
                        gf.setAccessible(true);
                        PathfinderGoalSelector goal = (PathfinderGoalSelector) gf.get(entityWolf);
                        goal.a(0, new PathfinderGoalMeleeAttack(entityWolf, 1.0D, true));

                        Field tf = cl.getDeclaredField("bQ");
                        tf.setAccessible(true);

                        PathfinderGoalSelector target = (PathfinderGoalSelector) tf.get(entityWolf);
                        target.a(0, new PathfinderGoalNearestAttackableTarget<>(entityWolf, EntityHuman.class, 10, true, false, null));
                    } catch (Exception exception) {
                        exception.printStackTrace();
                        Warn.Mutant(exception);
                    }
                } else if (entity instanceof Cat wolf) {
                    wolf.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(60);
                    CraftCat craft = ((CraftCat) wolf);
                    EntityCat entityWolf = craft.getHandle();
                    try {
                        Class<? extends EntityInsentient> cl = EntityInsentient.class;
                        Field gf = cl.getDeclaredField("bP");
                        gf.setAccessible(true);
                        PathfinderGoalSelector goal = (PathfinderGoalSelector) gf.get(entityWolf);
                        goal.a(0, new PathfinderGoalMeleeAttack(entityWolf, 1.0D, true));

                        Field tf = cl.getDeclaredField("bQ");
                        tf.setAccessible(true);

                        PathfinderGoalSelector target = (PathfinderGoalSelector) tf.get(entityWolf);
                        target.a(0, new PathfinderGoalNearestAttackableTarget<>(entityWolf, EntityHuman.class, 10, true, false, null));
                    } catch (Exception exception) {
                        exception.printStackTrace();
                        Warn.Mutant(exception);
                    }
                } else if (entity instanceof Fox wolf) {
                    wolf.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(60);
                    CraftFox craft = ((CraftFox) wolf);
                    EntityFox entityWolf = craft.getHandle();
                    try {
                        Class<? extends EntityInsentient> cl = EntityInsentient.class;
                        Field gf = cl.getDeclaredField("bP");
                        gf.setAccessible(true);
                        PathfinderGoalSelector goal = (PathfinderGoalSelector) gf.get(entityWolf);
                        goal.a(0, new PathfinderGoalMeleeAttack(entityWolf, 1.0D, true));

                        Field tf = cl.getDeclaredField("bQ");
                        tf.setAccessible(true);

                        PathfinderGoalSelector target = (PathfinderGoalSelector) tf.get(entityWolf);
                        target.a(0, new PathfinderGoalNearestAttackableTarget<>(entityWolf, EntityHuman.class, 10, true, false, null));
                    } catch (Exception exception) {
                        exception.printStackTrace();
                        Warn.Mutant(exception);
                    }
                } else if (entity instanceof Goat wolf) {
                    wolf.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(60);
                    CraftGoat craft = ((CraftGoat) wolf);
                    net.minecraft.world.entity.animal.goat.Goat entityWolf = craft.getHandle();
                    try {
                        Class<? extends EntityInsentient> cl = EntityInsentient.class;
                        Field gf = cl.getDeclaredField("bP");
                        gf.setAccessible(true);
                        PathfinderGoalSelector goal = (PathfinderGoalSelector) gf.get(entityWolf);
                        goal.a(0, new PathfinderGoalMeleeAttack(entityWolf, 1.0D, true));

                        Field tf = cl.getDeclaredField("bQ");
                        tf.setAccessible(true);

                        PathfinderGoalSelector target = (PathfinderGoalSelector) tf.get(entityWolf);
                        target.a(0, new PathfinderGoalNearestAttackableTarget<>(entityWolf, EntityHuman.class, 10, true, false, null));
                    } catch (Exception exception) {
                        exception.printStackTrace();
                        Warn.Mutant(exception);
                    }
                } else if (entity instanceof Axolotl wolf) {
                    wolf.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(60);
                    CraftAxolotl craft = ((CraftAxolotl) wolf);
                    net.minecraft.world.entity.animal.axolotl.Axolotl entityWolf = craft.getHandle();
                    try {
                        Class<? extends EntityInsentient> cl = EntityInsentient.class;
                        Field gf = cl.getDeclaredField("bP");
                        gf.setAccessible(true);
                        PathfinderGoalSelector goal = (PathfinderGoalSelector) gf.get(entityWolf);
                        goal.a(0, new PathfinderGoalMeleeAttack(entityWolf, 1.0D, true));

                        Field tf = cl.getDeclaredField("bQ");
                        tf.setAccessible(true);

                        PathfinderGoalSelector target = (PathfinderGoalSelector) tf.get(entityWolf);
                        target.a(0, new PathfinderGoalNearestAttackableTarget<>(entityWolf, EntityHuman.class, 10, true, false, null));
                    } catch (Exception exception) {
                        exception.printStackTrace();
                        Warn.Mutant(exception);
                    }
                }else if(entity instanceof Cow cow){
                    cow.remove();
                    Ravager ravager = cow.getWorld().spawn(cow.getLocation(),Ravager.class);
                    ravager.setCustomName(format("&6&lDestroyer"));
                    ravager.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(34);
                    ravager.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(100);
                    ravager.setHealth(100);
                }else if(entity instanceof Pig pig){
                    pig.remove();
                    Ravager ravager = pig.getWorld().spawn(pig.getLocation(),Ravager.class);
                    ravager.setCustomName(format("&6&lDestroyer"));
                    ravager.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(34);
                    ravager.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(100);
                    ravager.setHealth(100);
                }else if(entity instanceof Squid squid){
                    squid.remove();
                    PufferFish pufferFish = squid.getWorld().spawn(squid.getLocation(),PufferFish.class);

                }

        }
    }

}
