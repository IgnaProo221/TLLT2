package Eventos;

import CustomMobs.Argus;
import Utilidades.Mobs;
import Utilidades.Warn;
import net.minecraft.server.level.WorldServer;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Biome;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import tlldos.tll2.TLL2;

import static Utilidades.Format.format;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class SpawnListeners implements Listener {
    private TLL2 plugin;

    public SpawnListeners(TLL2 plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void spawnMob(CreatureSpawnEvent e) {
        Random random = new Random();
        int spawnmob = random.nextInt(100);
        var en = e.getEntity();
        var pos = e.getLocation();
        var world = Bukkit.getWorld("world");

        if (en instanceof Zombie self && !(en instanceof Husk) && !(en instanceof Drowned) && !(en instanceof PigZombie) && !(en instanceof ZombieVillager)){
            spawnZombiClass(self);
        } else if (en instanceof Creeper self && en.getLocation().getWorld().getEnvironment() == World.Environment.NORMAL){
            spawnCreeperBlight(self);
        } else if (en instanceof Skeleton self && en.getLocation().getWorld().getEnvironment() == World.Environment.NORMAL) {
            this.spawnSkeletonClass(self);

        }else if (en instanceof Spider self && !(en instanceof CaveSpider)) {
            this.spawnSpiderVariant(self);
        }else if (en instanceof Pillager) {
            var pillager = (Pillager) en;

            ItemStack ac = new ItemStack(Material.CROSSBOW);
            ItemMeta meta = ac.getItemMeta();
            meta.addEnchant(Enchantment.QUICK_CHARGE, 3, true);
            ac.setItemMeta(meta);

            var rocket = new ItemStack(Material.FIREWORK_ROCKET, 64);
            var rocketMeta = (FireworkMeta) rocket.getItemMeta();
            var fireworkEffect = FireworkEffect.builder().withColor(Color.OLIVE).withFade(Color.RED).build();
            rocketMeta.addEffect(fireworkEffect);
            rocket.setItemMeta(rocketMeta);

            pillager.setCustomName(format("&cPillager Generico Explosivo lol"));
            pillager.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40.0);
            pillager.setHealth(40);
            pillager.getEquipment().setItemInMainHand(ac);
            pillager.getEquipment().setItemInOffHand(rocket);
            pillager.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "OVERRATED_PILLAGER"), PersistentDataType.STRING, "OVERRATED_PILLAGER");
        }
        if(en instanceof Wither wither){
            Mobs.tyranyWither(wither);
        }
        if(en instanceof Silverfish silverfish){
            Mobs.cosmicSilver(silverfish);
        }

        if (en instanceof Vindicator) {
            var vindi = (Vindicator) en;
            ItemStack asx = new ItemStack(Material.NETHERITE_AXE);
            ItemMeta meta = asx.getItemMeta();
            meta.addEnchant(Enchantment.DAMAGE_ALL, 5, true);
            asx.setItemMeta(meta);

            vindi.setCustomName(format("&6Butcher"));
            vindi.getEquipment().setItemInMainHand(asx);
            vindi.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(45.0);
            vindi.setHealth(45);
        }

        if (en instanceof Ravager) {
            var ravager = (Ravager) en;
            ravager.setCustomName(format("&6Destroyer"));
            ravager.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(12.0);
            ravager.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(80.0);
            ravager.setHealth(80);
        }

        if (en instanceof Blaze) {
            var blaze = (Blaze) en;
            blaze.setCustomName(format("&cHellfire"));
            blaze.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40.0);
            blaze.setHealth(40);
            blaze.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "HELLFIRE"), PersistentDataType.STRING, "HELLFIRE");
        }

        if (en instanceof Vex self){
            this.spawnVexClass(self);
        }
        if (en instanceof Husk) {
            Location l = en.getLocation().clone();
            e.setCancelled(true);
            l.getWorld().spawn(l, Blaze.class);
        }

        if (en instanceof Rabbit self) {
            self.remove();
            if(self.getWorld().getLivingEntities().stream().filter(entity1 -> entity1 instanceof Creeper).map(Creeper.class::cast).collect(Collectors.toList()).size() < 60){
                var creepercx = self.getLocation().getWorld().spawn(self.getLocation(), Creeper.class);
                Mobs.vortice(creepercx);
            }
        }


        if (en instanceof PigZombie zombipig) {
            if(spawnmob < 50){
                var randomskeleton = zombipig.getLocation().getWorld().spawn(zombipig.getLocation(),Skeleton.class);
                Mobs.blightedSkeleton(randomskeleton);
                zombipig.setPassenger(randomskeleton);
                zombipig.setCustomName(format("&cPigman"));
                zombipig.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(10.0);
                zombipig.setAngry(true);
                zombipig.setAnger(Integer.MAX_VALUE);
            }else {
                zombipig.setCustomName(format("&cPigman"));
                zombipig.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(10.0);
                zombipig.setAngry(true);
                zombipig.setAnger(Integer.MAX_VALUE);
            }
        }
        /*if (en instanceof Bat bat) {
            if (world.getName().contains("build_world")) {
                en.remove();
            } else {
                bat.setCustomName(format("&cExplosive Bat"));
                bat.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "EXPLOSIVE_BAT"), PersistentDataType.STRING, "EXPLOSIVE_BAT");
            }
        }*/
        if (en instanceof WitherSkeleton self && en.getWorld().getEnvironment().equals(World.Environment.NETHER)){
            spawnWitherSkeleton(self);

        }

        if (en instanceof Chicken self){
            self.remove();
            if(self.getWorld().getLivingEntities().stream().filter(entity1 -> entity1 instanceof Vindicator).map(Vindicator.class::cast).collect(Collectors.toList()).size() < 30){
                var vindicator = (Vindicator)self.getLocation().getWorld().spawn(self.getLocation(), Vindicator.class);
            }
        }

        if (en instanceof Salmon self){
            self.remove();
            if(self.getWorld().getLivingEntities().stream().filter(entity1 -> entity1 instanceof PufferFish).map(PufferFish.class::cast).collect(Collectors.toList()).size() < 70){
                var pufferfish = self.getLocation().getWorld().spawn(self.getLocation(), PufferFish.class);
                pufferfish.setCustomName(format("&cPufferfish"));
            }
        }
        if (en instanceof Slime && !(en instanceof MagmaCube)) {
            var slime = (Slime) en;
            if (e.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.SLIME_SPLIT)) {
                slime.setCustomName(format("Freezing Slime"));
                slime.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(10.0);
                slime.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "FREEZING_SLIME"), PersistentDataType.STRING, "FREEZING_SLIME");
            } else {
                slime.setCustomName(format("Freezing Slime"));
                slime.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(60);
                slime.setHealth(60);
                slime.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(12);
                slime.setSize(12);
                slime.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "FREEZING_SLIME"), PersistentDataType.STRING, "FREEZING_SLIME");
            }
        }

        if (en instanceof Guardian && !(en instanceof ElderGuardian)) {
            var guardian = (Guardian) en;
            guardian.setCustomName(format("&6Caca"));
            guardian.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40.0);
            guardian.setHealth(40);
            guardian.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, 1, false, false, false));
        }
        if(en instanceof Evoker self){
            evokerClass(self);
        }

        if(en instanceof PiglinBrute self){
            spawnBrute(self);
        }
        if(en instanceof Witch self){
            spawnWitch(self);
        }
        if(en instanceof Ghast self && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL){
            spawnGhast(self);
        }
        if(en instanceof Phantom self){
            spawnPhantom(self);
        }
        if(en instanceof Enderman self){
            if(self.getWorld().getEnvironment().equals(World.Environment.THE_END)){
                if(spawnmob < 30){
                    endSpawn(self);
                }else{
                    self.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1000000,3, false, false, false));
                }
            }else {
                spawnEnderman(self);
            }
        }
    }

    public void spawnVexClass(Vex sheep) {
        Random random = new Random();
        int vexes = random.nextInt(5);
        if (vexes == 1) {
            Mobs.cinder(sheep);
        } else if (vexes == 2) {
            Mobs.jengu(sheep);
        } else if (vexes == 3) {
            Mobs.djiin(sheep);
        } else if (vexes == 4) {
            Argus argus = new Argus(sheep.getLocation());
            WorldServer world = ((CraftWorld) sheep.getLocation().getWorld()).getHandle();
            world.addEntity(argus, CreatureSpawnEvent.SpawnReason.NATURAL);
            Bukkit.getServer().getConsoleSender().sendMessage("Entidad: " + argus.getEntityType().id);
        }  else {
            Mobs.grue(sheep);
        }
    }

    public void spawnWitherSkeleton(WitherSkeleton witherskeleton) {

        int type = new Random().nextInt(3);

        if (type == 1) {
            ItemStack caca = new ItemStack(Material.NETHERITE_SWORD);
            ItemMeta meta = caca.getItemMeta();
            meta.addEnchant(Enchantment.DAMAGE_ALL, 20, true);
            meta.setUnbreakable(true);
            caca.setItemMeta(meta);

            witherskeleton.setCustomName(format("&cShattered Guardian"));
            witherskeleton.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(45.0);
            witherskeleton.setHealth(45);
            witherskeleton.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(10.0);
            witherskeleton.getEquipment().setHelmet(new ItemStack(Material.NETHERITE_HELMET));
            witherskeleton.getEquipment().setChestplate(new ItemStack(Material.NETHERITE_CHESTPLATE));
            witherskeleton.getEquipment().setLeggings(new ItemStack(Material.NETHERITE_LEGGINGS));
            witherskeleton.getEquipment().setBoots(new ItemStack(Material.NETHERITE_BOOTS));
            witherskeleton.getEquipment().setItemInMainHand(caca);
            witherskeleton.getEquipment().setDropChance(EquipmentSlot.HEAD, 0);
            witherskeleton.getEquipment().setDropChance(EquipmentSlot.CHEST, 0);
            witherskeleton.getEquipment().setDropChance(EquipmentSlot.LEGS, 0);
            witherskeleton.getEquipment().setDropChance(EquipmentSlot.FEET, 0);
            witherskeleton.getEquipment().setDropChance(EquipmentSlot.HAND, 0);
            witherskeleton.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "SHATTER_GUARDIAN"), PersistentDataType.STRING, "SHATTER_GUARDIAN");
        } else if(type == 2){
            Mobs.blightedWitherSkeleton(witherskeleton);
        }else{
            witherskeleton.remove();
            var irongolem = (IronGolem)witherskeleton.getLocation().getWorld().spawn(witherskeleton.getLocation(),IronGolem.class);
            Mobs.lavaGolem(irongolem);
        }

    }

    public void spawnSkeletonClass(Skeleton skeleton) {
        int type = new Random().nextInt(7);
        if (type == 1) {
            Mobs.ignitedSkeleton(skeleton);
        } else if (type == 2) {
            Mobs.blizzardSkeleton(skeleton);
        } else if (type == 3) {
            Mobs.copperSkeleton(skeleton);
        } else if (type == 4) {
            Mobs.bullseyeSkeleton(skeleton);
        } else if (type == 5){
            Mobs.poweredSkeleton(skeleton);
        }else if(type == 6){
            Mobs.blightedSkeleton(skeleton);
        }else {
            skeleton.remove();
            var illusioner = skeleton.getLocation().getWorld().spawn(skeleton.getLocation(), Illusioner.class);
            Mobs.riftedMage(illusioner);
        }

    }

    public void spawnSpiderVariant(Spider spider) {
        int type = new Random().nextInt(5);
        if (type == 1) {
            Mobs.plagueSpider(spider);
        } else if (type == 2) {
            Mobs.solarScorpion(spider);
        } else if (type == 3) {
            Mobs.interdimensionalVisitor(spider);
        } else if(type == 4){
            Mobs.agileTarantule(spider);
        }else {
            Mobs.blightedSpider(spider);
        }
    }


    public void spawnZombiClass(Zombie self){
        int type = new Random().nextInt(6);
        if(type == 1){
            Mobs.zombBox(self);
        }else if(type == 2){
            Mobs.zombHerrero(self);
        }else if(type == 3){
            Mobs.zombCarni(self);
        }else if(type == 4){
            self.remove();
            var iron = self.getLocation().getWorld().spawn(self.getLocation(),IronGolem.class);
            Mobs.zombObeso(iron);
        }else if(type == 5){
            Mobs.zombiJinete(self);
            var callabo = self.getLocation().getWorld().spawn(self.getLocation(),ZombieHorse.class);
            Mobs.caballoJinete(callabo);
            callabo.setPassenger(self);
        }else{
            Mobs.blightedZombi(self);
        }
    }

    public void spawnCreeperBlight(Creeper creeper) {
        int type = new Random().nextInt(4);
        if (type == 1) {
            creeper.setCustomName(format("&cCharged Creeper"));
            creeper.setPowered(true);
            creeper.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20.0);
            creeper.setHealth(20);
        }  else if(type == 2){
            Mobs.blightedCreeper(creeper);
        }else if(type == 3){
            Mobs.riftedCreeper(creeper);
        }else{
            Mobs.hiveMind(creeper);
        }
    }
    public void spawnBrute(PiglinBrute brute) {
        int type = new Random().nextInt(2);
        if(type == 1){
            brute.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, 1, 1));
        }else{
            Mobs.blightedPiglin(brute);
        }

    }
    public void spawnGhast(Ghast ghast) {
        int type = new Random().nextInt(3);
        if(type == 1){
            ghast.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, 1, 1));
        }else if(type == 2){
            Mobs.blightedGhast(ghast);
        }else{
            Mobs.riftedGhast(ghast);
        }

    }
    public void spawnWitch(Witch witch) {
        int type = new Random().nextInt(2);
        if(type == 1){
            witch.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, 1, 1));
        }else{
Mobs.blightedWitch(witch);
        }

    }
    public void spawnPhantom(Phantom phantom) {
        int type = new Random().nextInt(2);
        if(type == 1){
            phantom.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, 1, 1));
        }else{
            Mobs.blightedPhantom(phantom);
        }

    }
    public void spawnEnderman(Enderman enderman){
        int type = new Random().nextInt(3);
        if(type == 1){
            enderman.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, 1, 1));
        }else if(type == 2){
Mobs.blightedEndermam(enderman);
        }else{
            enderman.remove();
            var vindicacaca = enderman.getLocation().getWorld().spawn(enderman.getLocation(),Vindicator.class);
            Mobs.ladronFel(vindicacaca);
        }
    }

    public void evokerClass(Evoker evoker){
        int mobspawn = new Random().nextInt(4);
        if(mobspawn == 1){
            Mobs.evokerExplosive(evoker);
        }else if(mobspawn == 2){
            Mobs.evokerFire(evoker);
        }else if(mobspawn == 3){
            Mobs.evokerFreeze(evoker);
        }else{
            Mobs.evokerhex(evoker);
        }
    }
    public void endSpawn(Enderman enderman){
        int mobspawn = new Random().nextInt(6);
        if(mobspawn == 1){
            enderman.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1000000,1, false, false, false));
        }else if (mobspawn == 2){
            enderman.remove();
            var illusioner = enderman.getLocation().getWorld().spawn(enderman.getLocation(), Illusioner.class);
            Mobs.riftedMage(illusioner);
        }else if(mobspawn == 3){
            enderman.remove();
            var creeper = enderman.getLocation().getWorld().spawn(enderman.getLocation(), Creeper.class);
            Mobs.riftedCreeper(creeper);
        }else if(mobspawn == 4) {
            enderman.remove();
            var ghast = enderman.getLocation().getWorld().spawn(enderman.getLocation(), Ghast.class);
            Mobs.riftedGhast(ghast);
        }else if(mobspawn == 5){
            enderman.remove();
            var shulker = enderman.getLocation().getWorld().spawn(enderman.getLocation(), Shulker.class);
            Mobs.riftedShulker(shulker);
        }else {
            enderman.remove();
            var witherskeleton = enderman.getLocation().getWorld().spawn(enderman.getLocation(), WitherSkeleton.class);
            Mobs.riftedWither(witherskeleton);
        }
    }

    public void elementalSpawn(Sheep sheep){
        Random random = new Random();
        int vexes = random.nextInt(4);
        if(vexes == 1){
            sheep.remove();
            var vex = sheep.getLocation().getWorld().spawn(sheep.getLocation(), Vex.class);
            Mobs.cinder(vex);
        }else if(vexes == 2){
            sheep.remove();
            var vex = sheep.getLocation().getWorld().spawn(sheep.getLocation(), Vex.class);
            Mobs.jengu(vex);
        }else if(vexes == 3){
            sheep.remove();
            var vex = sheep.getLocation().getWorld().spawn(sheep.getLocation(), Vex.class);
            Mobs.djiin(vex);
        }else{
            sheep.remove();
            var vex = sheep.getLocation().getWorld().spawn(sheep.getLocation(), Vex.class);
            Mobs.grue(vex);
        }
    }

}
