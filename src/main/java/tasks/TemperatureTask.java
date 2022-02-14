package tasks;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
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

                var data = player.getPersistentDataContainer();
                var temperature = data.get(new NamespacedKey(plugin, "temperatura"), PersistentDataType.INTEGER);

                if(temperature == null) {
                    data.set(new NamespacedKey(plugin, "temperatura"), PersistentDataType.INTEGER,  30);
                    return;
                }

                if (biomes == Biome.PLAINS) {
                    data.set(new NamespacedKey(plugin, "temperatura"), PersistentDataType.INTEGER, temperature + 10);
                } else if (biomes == Biome.ICE_SPIKES) {
                    data.set(new NamespacedKey(plugin, "temperatura"), PersistentDataType.INTEGER, temperature - 10);
                }

                if (temperature >= 120) {
                    player.sendMessage("tas caliente");
                }
            }
        }
    }
}
