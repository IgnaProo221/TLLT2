package tasks;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import tlldos.tll2.TLL2;

public class TemperatureBlocks extends BukkitRunnable{
    private final TLL2 plugin;
    public TemperatureBlocks(TLL2 plugin){
        this.plugin = plugin;
    }
    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getGameMode() == GameMode.SURVIVAL) {
                var location = player.getLocation();
                var block = location.getBlock().getType();

                var data = player.getPersistentDataContainer();
                var temperature = data.get(new NamespacedKey(plugin, "temperatura"), PersistentDataType.INTEGER);

                if (temperature == null) {
                    data.set(new NamespacedKey(plugin, "temperatura"), PersistentDataType.INTEGER, 30);
                    return;
                }
                if(block == Material.ICE || block == Material.BLUE_ICE || block == Material.PACKED_ICE || block == Material.FROSTED_ICE){
                    data.set(new NamespacedKey(plugin, "temperatura"), PersistentDataType.INTEGER, temperature - 10);
                }else if(block == Material.MAGMA_BLOCK || block == Material.TORCH || block == Material.CAMPFIRE){
                    data.set(new NamespacedKey(plugin, "temperatura"), PersistentDataType.INTEGER, temperature + 10);
                }else if(block == Material.SOUL_CAMPFIRE || block == Material.SOUL_TORCH || block == Material.WATER){
                    data.set(new NamespacedKey(plugin, "temperatura"), PersistentDataType.INTEGER, temperature - 5);
                }else if(block == Material.LAVA){
                    data.set(new NamespacedKey(plugin, "temperatura"), PersistentDataType.INTEGER, temperature + 5);
                }
            }
        }
    }
}
