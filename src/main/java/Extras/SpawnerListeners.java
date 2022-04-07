package Extras;

import Utilities.ItemBuilder;
import Utilities.Mobs;
import Utilities.Warn;
import net.minecraft.world.entity.EntityInsentient;
import net.minecraft.world.entity.ai.goal.PathfinderGoalMeleeAttack;
import net.minecraft.world.entity.ai.goal.PathfinderGoalSelector;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalNearestAttackableTarget;
import net.minecraft.world.entity.monster.EntityPigZombie;
import net.minecraft.world.entity.player.EntityHuman;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPigZombie;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.persistence.PersistentDataType;
import tlldos.tll2.TLL2;

import java.lang.reflect.Field;

import static Utilities.Format.format;

public class SpawnerListeners implements Listener {
    private TLL2 plugin;

    public SpawnerListeners(TLL2 plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void spawnerSpawn(CreatureSpawnEvent event) {
        Entity en = event.getEntity();
        if (en instanceof Bee) {
            if (event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.SPAWNER) {
                if(en.getCustomName().equalsIgnoreCase("a")){
                    event.setCancelled(true);
                    var silver = en.getLocation().getWorld().spawn(en.getLocation(), Silverfish.class);
                    Mobs.labSilver(silver);
                }
                if(en.getCustomName().equalsIgnoreCase("b")){
                    event.setCancelled(true);
                    var pillager = en.getLocation().getWorld().spawn(en.getLocation(), Pillager.class);
                    Mobs.madScientist(pillager);
                }
                if(en.getCustomName().equalsIgnoreCase("c")){
                    event.setCancelled(true);
                    var zombie = en.getLocation().getWorld().spawn(en.getLocation(), Zombie.class);
                    Mobs.mechaZombie(zombie);
                }
                if(en.getCustomName().equalsIgnoreCase("d")){
                    event.setCancelled(true);
                    var irongolem = en.getLocation().getWorld().spawn(en.getLocation(), IronGolem.class);
                    Mobs.exoGolem(irongolem);
                }
                if(en.getCustomName().equalsIgnoreCase("a1")){
                    event.setCancelled(true);
                    var mage = en.getWorld().spawn(en.getLocation(),Evoker.class);
                    Mobs.evokerhex(mage);
                }
                if(en.getCustomName().equalsIgnoreCase("b2")){
                    event.setCancelled(true);
                    var mage = en.getWorld().spawn(en.getLocation(),Evoker.class);
                    Mobs.evokerFreeze(mage);
                }
                if(en.getCustomName().equalsIgnoreCase("c3")){
                    event.setCancelled(true);
                    var mage = en.getWorld().spawn(en.getLocation(),Evoker.class);
                    Mobs.evokerFire(mage);
                }
                if(en.getCustomName().equalsIgnoreCase("d4")){
                    event.setCancelled(true);
                    var mage = en.getWorld().spawn(en.getLocation(),Evoker.class);
                    Mobs.evokerWind(mage);
                }
                if(en.getCustomName().equalsIgnoreCase("e5")){
                    event.setCancelled(true);
                    var mage = en.getWorld().spawn(en.getLocation(),Evoker.class);
                    Mobs.evokerExplosive(mage);
                }
                if(en.getCustomName().equalsIgnoreCase("f6")){
                    event.setCancelled(true);
                    var mage = en.getWorld().spawn(en.getLocation(),Pillager.class);
                    Mobs.dynamLlager(mage);
                }
                if(en.getCustomName().equalsIgnoreCase("g7")){
                    event.setCancelled(true);
                    var mage = en.getWorld().spawn(en.getLocation(),Evoker.class);
                    Mobs.maestryWizard(mage);
                }
            }
        }else if(en instanceof Silverfish) {
            if (event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.SPAWNER) {
                if (en.getCustomName().equalsIgnoreCase("a")) {
                    event.setCancelled(true);
                    var lushz = en.getLocation().getWorld().spawn(en.getLocation(), Zombie.class);
                    Mobs.lushZombie(lushz);
                }
                if (en.getCustomName().equalsIgnoreCase("b")) {
                    event.setCancelled(true);
                    var lushz = en.getLocation().getWorld().spawn(en.getLocation(), Skeleton.class);
                    Mobs.lushSkeleton(lushz);
                }
                if (en.getCustomName().equalsIgnoreCase("c")) {
                    event.setCancelled(true);
                    var lushz = en.getLocation().getWorld().spawn(en.getLocation(), IronGolem.class);
                    Mobs.experimentWOOD(lushz);
                }
                if (en.getCustomName().equalsIgnoreCase("d")) {
                    event.setCancelled(true);
                    var lushz = en.getLocation().getWorld().spawn(en.getLocation(), Creeper.class);
                    Mobs.roboMine(lushz);
                }
                if (en.getCustomName().equalsIgnoreCase("f")) {
                    event.setCancelled(true);
                    var lushz = en.getLocation().getWorld().spawn(en.getLocation(), Creeper.class);
                    Mobs.mossCreeper(lushz);
                }
                if (en.getCustomName().equalsIgnoreCase("g")) {
                    event.setCancelled(true);
                    var lushz = en.getLocation().getWorld().spawn(en.getLocation(), Spider.class);
                    Mobs.spiderJungla(lushz);
                }
                if (en.getCustomName().equalsIgnoreCase("viento")) {
                    event.setCancelled(true);
                    Mobs.agileTarantule(en.getLocation().getWorld().spawn(en.getLocation(),Spider.class));
                }
                if (en.getCustomName().equalsIgnoreCase("fea")) {
                    event.setCancelled(true);
                    Mobs.blightedSpider(en.getLocation().getWorld().spawn(en.getLocation(),Spider.class));
                }
                if (en.getCustomName().equalsIgnoreCase("feados")) {
                    event.setCancelled(true);
                    Mobs.blightedWitch(en.getLocation().getWorld().spawn(en.getLocation(),Witch.class));
                }
                if (en.getCustomName().equalsIgnoreCase("infinium")) {
                    event.setCancelled(true);
                    Mobs.vortice(en.getLocation().getWorld().spawn(en.getLocation(),Creeper.class));
                }
                if (en.getCustomName().equalsIgnoreCase("funny")) {
                    event.setCancelled(true);
                    Mobs.cosmicSilver(en.getLocation().getWorld().spawn(en.getLocation(),Silverfish.class));
                }
                if (en.getCustomName().equalsIgnoreCase("elmejor")) {
                    event.setCancelled(true);
                    Mobs.elderdestroyer(en.getLocation().getWorld().spawn(en.getLocation(),ElderGuardian.class));
                }
                if (en.getCustomName().equalsIgnoreCase("porque")) {
                    event.setCancelled(true);
                    en.getWorld().spawn(en.getLocation(),PufferFish.class);
                }
                if (en.getCustomName().equalsIgnoreCase("ayno")) {
                    event.setCancelled(true);
                    Wither wither = en.getWorld().spawn(en.getLocation(),Wither.class);
                    Mobs.advancedwither(wither);
                }
                if (en.getCustomName().equalsIgnoreCase("wowoslayer")) {
                    event.setCancelled(true);
                    Piglin piglin = en.getWorld().spawn(en.getLocation(),Piglin.class);
                    piglin.setCustomName(format("&b&lFrancotirador"));
                    piglin.getEquipment().setItemInMainHand(new ItemBuilder(Material.CROSSBOW).addEnchantment(Enchantment.PIERCING, 10).setUnbreakable(true).build());
                    piglin.getEquipment().setDropChance(EquipmentSlot.HAND, 0);
                    piglin.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "FRANCO"), PersistentDataType.STRING, "FRANCO");
                }
                if (en.getCustomName().equalsIgnoreCase("existia")) {
                    event.setCancelled(true);
                    Mobs.lavaGolem(en.getLocation().getWorld().spawn(en.getLocation(),IronGolem.class));
                }
                if (en.getCustomName().equalsIgnoreCase("asc")) {
                    event.setCancelled(true);
                    PigZombie pigZombie = en.getWorld().spawn(en.getLocation(),PigZombie.class);
                    pigZombie.setCustomName(format("&c&lEnraged Pigman"));
                    pigZombie.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(60);
                    pigZombie.setHealth(60);
                    pigZombie.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(20);
                    CraftPigZombie craft = ((CraftPigZombie) pigZombie);
                    EntityPigZombie entityPigZombie = craft.getHandle();
                    try {
                        Class<? extends EntityInsentient> cl = EntityInsentient.class;
                        Field gf = cl.getDeclaredField("bP");
                        gf.setAccessible(true);
                        PathfinderGoalSelector goal = (PathfinderGoalSelector) gf.get(entityPigZombie);
                        goal.a(0, new PathfinderGoalMeleeAttack(entityPigZombie, 1.0D, true));

                        Field tf = cl.getDeclaredField("bQ");
                        tf.setAccessible(true);

                        PathfinderGoalSelector target = (PathfinderGoalSelector) tf.get(entityPigZombie);
                        target.a(0, new PathfinderGoalNearestAttackableTarget<>(entityPigZombie, EntityHuman.class, 10, true, false, null));
                    } catch (Exception ec) {
                        ec.printStackTrace();
                        Warn.Mutant(ec);
                    }
                }
            }
        }else if(en instanceof Endermite) {
            if (event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.SPAWNER) {
                if (en.getCustomName().equalsIgnoreCase("a")) {
                    event.setCancelled(true);
                    var moblol = en.getWorld().spawn(en.getLocation(), Vex.class);
                    Mobs.Banshee(moblol);
                }
                if (en.getCustomName().equalsIgnoreCase("b")) {
                    event.setCancelled(true);
                    var moblol = en.getWorld().spawn(en.getLocation(), Ghast.class);
                    Mobs.Nightmare(moblol);
                }
                if (en.getCustomName().equalsIgnoreCase("c")) {
                    event.setCancelled(true);
                    var moblol = en.getWorld().spawn(en.getLocation(), Vindicator.class);
                    Mobs.KillerScream(moblol);
                }
                if (en.getCustomName().equalsIgnoreCase("d")) {
                    event.setCancelled(true);
                    var moblol = en.getWorld().spawn(en.getLocation(), Creeper.class);
                    Mobs.Overscream(moblol);
                }
                if (en.getCustomName().equalsIgnoreCase("e")) {
                    event.setCancelled(true);
                    var moblol = en.getWorld().spawn(en.getLocation(), Blaze.class);
                    Mobs.hellfire(moblol);
                }
                if (en.getCustomName().equalsIgnoreCase("f")) {
                    event.setCancelled(true);
                    var moblol = en.getWorld().spawn(en.getLocation(), Blaze.class);
                    Mobs.InfernoLord(moblol);
                }
            }
        }
    }
}
