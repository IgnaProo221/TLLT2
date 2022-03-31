package tasks;

import Utilities.Utils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import player.CustomPlayer;
import tlldos.tll2.TLL2;

public class TemperatureBlocks extends BukkitRunnable {
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
                var downBlock  = location.add(0.0D, -1.0D, 0.0D).clone().getBlock().getType();

                var data = CustomPlayer.fromName(player.getName()).getData();
                var temperature = data.getTemperature();

                if(downBlock == Material.ICE || downBlock == Material.BLUE_ICE || downBlock == Material.PACKED_ICE || downBlock == Material.FROSTED_ICE){
                    if(Utils.getWorld().isThundering()) {
                        data.setTemperature(temperature - 20);
                    } else{
                        data.setTemperature(temperature - 10);
                    }
                }
                if(downBlock == Material.MAGMA_BLOCK || block == Material.TORCH || block == Material.CAMPFIRE){
                    if(Utils.getWorld().isThundering()) {
                        data.setTemperature(temperature + 20);
                    } else{
                        data.setTemperature(temperature + 10);
                    }
                }
                if(block == Material.SOUL_CAMPFIRE || block == Material.SOUL_TORCH){
                    if(Utils.getWorld().isThundering()) {
                        data.setTemperature(temperature - 10);
                    } else{
                        data.setTemperature(temperature - 5);
                    }
                }
                if(block == Material.LAVA){
                    if(Utils.getWorld().isThundering()) {
                        data.setTemperature(temperature + 15);
                    } else{
                        data.setTemperature(temperature + 5);
                    }
                }
                if(block == Material.WATER){
                    if(Utils.getWorld().isThundering()) {
                        data.setTemperature(temperature - 15);
                    } else{
                        data.setTemperature(temperature - 5);
                    }
                }
            }
        }
    }
}
