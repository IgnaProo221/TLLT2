package tasks;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import player.CustomPlayer;
import tlldos.tll2.TLL2;

public class TemperatureY extends BukkitRunnable {
    private final TLL2 plugin;

    public TemperatureY(TLL2 plugin) {
        this.plugin = plugin;
    }
    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getGameMode() == GameMode.SURVIVAL) {
                var location = player.getLocation();
                var altitude = location.getY();

                var data = CustomPlayer.fromName(player.getName()).getData();
                var temperature = data.getTemperature();

                if(altitude > 120){
                    data.setTemperature(temperature - 10);
                }
                if(altitude < -10){
                    data.setTemperature(temperature + 10);
                }
            }
        }
    }
}
