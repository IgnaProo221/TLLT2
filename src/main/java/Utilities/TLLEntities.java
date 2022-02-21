package Utilities;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import tlldos.tll2.TLL2;

import java.util.HashMap;


public class TLLEntities {
    public static HashMap<String, EntityType> customCreature = new HashMap<>();

    public TLLEntities() {
        customCreature.put("TEST_ZOMBIE", EntityType.ZOMBIE);
        customCreature.put("ZOMBI_CONGELADO", EntityType.ZOMBIE);
        customCreature.put("ESQUELETO_NIEVE", EntityType.SKELETON);
        customCreature.put("SNOW_SPIDER", EntityType.SPIDER);
        customCreature.put("MECHA_ZOMBIE", EntityType.ZOMBIE);
        customCreature.put("EXO_GOLEM", EntityType.IRON_GOLEM);
        customCreature.put("MAD_SCIENTIST", EntityType.PILLAGER);
        customCreature.put("LAB_SILVERFISH", EntityType.SILVERFISH);
    }

    public static boolean hasCustomCreature (EntityType type, String name) {
        return TLLEntities.customCreature.containsKey(name) && TLLEntities.customCreature.containsValue(type);
    }

    public static void setCustomCreature(HashMap<String, EntityType> customCreature) {
        TLLEntities.customCreature = customCreature;
    }

    public static HashMap<String, EntityType> getCustomCreature() {
        return customCreature;
    }

    public static void setTLLEntity(Entity entity, String name) {
        entity.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "TLL_ENTITY"), PersistentDataType.STRING, name);
    }

    public static boolean isTLLEntity(Entity entity) {
        return entity.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "TLL_ENTITY"), PersistentDataType.STRING);
    }

    public static String getTLLEntity (Entity entity) {
        Plugin plugin = TLL2.getPlugin(TLL2.class);
        if (entity.getPersistentDataContainer().has(new NamespacedKey(plugin, "TLL_ENTITY"),PersistentDataType.STRING)){
            return entity.getPersistentDataContainer().get(new NamespacedKey(plugin, "TLL_ENTITY"), PersistentDataType.STRING);
        } else {
            return null;
        }
    }


}
