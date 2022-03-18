package tasks;

import Utilities.Utils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import player.CustomPlayer;
import tlldos.tll2.TLL2;

public class TemperatureTask extends BukkitRunnable {

    private final TLL2 plugin;

    public TemperatureTask(TLL2 plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if(player.getGameMode() == GameMode.SURVIVAL) {
                var location = player.getLocation();
                var biomes = location.getBlock().getBiome();

                var data = CustomPlayer.fromName(player.getName()).getData();
                var temperature = data.getTemperature();

                if (biomes == Biome.DESERT || biomes == Biome.DESERT_LAKES || biomes == Biome.DESERT_HILLS || biomes == Biome.BADLANDS || biomes == Biome.BADLANDS_PLATEAU || biomes == Biome.ERODED_BADLANDS ||
                        biomes == Biome.MODIFIED_BADLANDS_PLATEAU || biomes == Biome.JUNGLE || biomes == Biome.JUNGLE_EDGE || biomes == Biome.JUNGLE_HILLS || biomes == Biome.BAMBOO_JUNGLE_HILLS || biomes == Biome.BAMBOO_JUNGLE ||
                        biomes == Biome.MODIFIED_JUNGLE || biomes == Biome.MODIFIED_JUNGLE_EDGE || biomes == Biome.SAVANNA || biomes == Biome.SAVANNA_PLATEAU || biomes == Biome.SHATTERED_SAVANNA ||
                        biomes == Biome.SHATTERED_SAVANNA_PLATEAU) {
                    data.setTemperature(temperature + 10);
                }
                if (biomes == Biome.ICE_SPIKES || biomes == Biome.SNOWY_BEACH || biomes == Biome.SNOWY_MOUNTAINS || biomes == Biome.SNOWY_TAIGA || biomes == Biome.SNOWY_TAIGA_HILLS || biomes == Biome.SNOWY_TAIGA_MOUNTAINS
                        || biomes == Biome.SNOWY_TUNDRA || biomes == Biome.FROZEN_OCEAN || biomes == Biome.DEEP_FROZEN_OCEAN || biomes == Biome.COLD_OCEAN || biomes == Biome.DEEP_COLD_OCEAN || biomes == Biome.LUSH_CAVES) {
                    data.setTemperature(temperature - 10);
                }
                if (biomes == Biome.PLAINS || biomes == Biome.FOREST || biomes == Biome.BIRCH_FOREST || biomes == Biome.BIRCH_FOREST_HILLS ||
                        biomes == Biome.FLOWER_FOREST || biomes == Biome.SUNFLOWER_PLAINS || biomes == Biome.DRIPSTONE_CAVES || biomes == Biome.NETHER_WASTES || biomes == Biome.BASALT_DELTAS || biomes == Biome.CRIMSON_FOREST || biomes == Biome.WARPED_FOREST
                        || biomes == Biome.SOUL_SAND_VALLEY || biomes == Biome.LUKEWARM_OCEAN || biomes == Biome.WARM_OCEAN || biomes == Biome.DEEP_WARM_OCEAN || biomes == Biome.DEEP_LUKEWARM_OCEAN || biomes == Biome.WOODED_HILLS) {
                    data.setTemperature(temperature + 5);
                }
                if (biomes == Biome.BEACH || biomes == Biome.DARK_FOREST || biomes == Biome.DARK_FOREST_HILLS || biomes == Biome.END_BARRENS || biomes == Biome.END_HIGHLANDS || biomes == Biome.END_MIDLANDS || biomes == Biome.MUSHROOM_FIELD_SHORE || biomes == Biome.MUSHROOM_FIELDS
                        || biomes == Biome.OCEAN || biomes == Biome.RIVER || biomes == Biome.STONE_SHORE || biomes == Biome.SWAMP || biomes == Biome.WOODED_MOUNTAINS) {
                    data.setTemperature(temperature - 5);
                }


                if (Utils.getWorld().hasStorm() && player.getWorld().getName().equalsIgnoreCase(Utils.getWorld().getName())) {
                    data.setTemperature(data.getTemperature() - 4);
                    if (player.getWorld().isThundering()) data.setTemperature(data.getTemperature() - 6);
                }
            }
        }
    }
}
