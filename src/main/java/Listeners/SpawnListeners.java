package Listeners;

import CustomMobs.Argus;
import CustomMobs.JineteZ;
import Utilities.Mobs;
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

import static Utilities.Format.format;

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
        var entity = e.getEntity();
        var pos = e.getLocation();
        var world = Bukkit.getWorld("world");
        var entitybiome = entity.getLocation().getBlock().getBiome();

        if(entitybiome == Biome.SNOWY_TUNDRA || entitybiome == Biome.SNOWY_BEACH || entitybiome == Biome.SNOWY_TAIGA || entitybiome == Biome.SNOWY_MOUNTAINS || entitybiome == Biome.SNOWY_TAIGA_HILLS || entitybiome == Biome.SNOWY_TAIGA_MOUNTAINS
        || entitybiome == Biome.ICE_SPIKES){
            if(entity instanceof Zombie zombie && !(entity instanceof Drowned) && !(entity instanceof Husk) && !(entity instanceof ZombieVillager) && !(entity instanceof PigZombie) && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL){
                Mobs.zombiCongelado(zombie);
            }else if(entity instanceof Skeleton skeleton && !(entity instanceof Stray) && !(entity instanceof WitherSkeleton) && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL){
                Mobs.esqueletoNieve(skeleton);
            }else if(entity instanceof Spider spider && !(entity instanceof CaveSpider) && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL){
                Mobs.spiderNieve(spider);
            }else if(entity instanceof Creeper creeper && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL){
                Mobs.creeperCongelado(creeper);
            }
        }else if(entitybiome == Biome.JUNGLE || entitybiome == Biome.JUNGLE_EDGE || entitybiome == Biome.JUNGLE_HILLS || entitybiome == Biome.BAMBOO_JUNGLE_HILLS || entitybiome == Biome.BAMBOO_JUNGLE
        || entitybiome == Biome.MODIFIED_JUNGLE || entitybiome == Biome.MODIFIED_JUNGLE_EDGE){
            if(entity instanceof Zombie zombie && !(entity instanceof Drowned) && !(entity instanceof Husk) && !(entity instanceof ZombieVillager) && !(entity instanceof PigZombie) && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL){
                Mobs.mossZombie(zombie);
            }else if(entity instanceof Skeleton skeleton && !(entity instanceof Stray) && !(entity instanceof WitherSkeleton) && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL){
                Mobs.esqueletoMilitar(skeleton);
            }else if(entity instanceof Spider spider && !(entity instanceof CaveSpider) && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL){
                Mobs.spiderJungla(spider);
            }else if(entity instanceof Creeper creeper && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL){
                Mobs.mossCreeper(creeper);
            }
        }else if(entitybiome == Biome.DESERT || entitybiome == Biome.DESERT_HILLS || entitybiome == Biome.DESERT_LAKES || entitybiome == Biome.BADLANDS || entitybiome == Biome.BADLANDS_PLATEAU
        || entitybiome == Biome.ERODED_BADLANDS || entitybiome == Biome.MODIFIED_BADLANDS_PLATEAU || entitybiome == Biome.MODIFIED_WOODED_BADLANDS_PLATEAU || entitybiome == Biome.WOODED_BADLANDS_PLATEAU){
            if(entity instanceof Zombie zombie && !(entity instanceof Drowned) && !(entity instanceof Husk) && !(entity instanceof ZombieVillager) && !(entity instanceof PigZombie) && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL){
                Mobs.zombiMomia(zombie);
            }else if(entity instanceof Skeleton skeleton && !(entity instanceof Stray) && !(entity instanceof WitherSkeleton) && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL){
                Mobs.esqueletoMomia(skeleton);
            }else if(entity instanceof Spider spider && !(entity instanceof CaveSpider) && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL){
                Mobs.spiderArena(spider);
            }else if(entity instanceof Creeper creeper && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL){
                Mobs.creeperSandstone(creeper);
            }
        }
    }
}
