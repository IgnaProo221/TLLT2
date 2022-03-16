package tasks;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import player.CustomPlayer;
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

                var data = CustomPlayer.fromName(player.getName()).getData();
                var temperature = data.getTemperature();

                if(block == Material.ICE || block == Material.BLUE_ICE || block == Material.PACKED_ICE || block == Material.FROSTED_ICE){
                    data.setTemperature(temperature - 10);
                }
                if(block == Material.MAGMA_BLOCK || block == Material.TORCH || block == Material.CAMPFIRE){
                    data.setTemperature(temperature + 10);
                }
                if(block == Material.SOUL_CAMPFIRE || block == Material.SOUL_TORCH){
                    data.setTemperature(temperature - 5);
                }
                if(block == Material.LAVA){
                    data.setTemperature(temperature + 5);
                }
                if(block == Material.WATER){
                    data.setTemperature(temperature - 20);
                }
            }
        }
    }
}
