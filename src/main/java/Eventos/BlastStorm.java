package Eventos;

import Utilidades.Format;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import tlldos.tll2.TLL2;

import java.util.Iterator;
import java.util.Random;

import static Eventos.Muerte.tormenta;

public class BlastStorm implements Listener {


    @EventHandler
    public void onBlastStormStart(StartBlastStormEvent e) {
        int tierLevel = new Random().nextInt(3);

        for(Player player : Bukkit.getOnlinePlayers()){
            e.addPotionTiers(player, tierLevel);
            tormenta.setVisible(true);
            tormenta.addPlayer(player);
        }


        World world = TLL2.getPlugin(TLL2.class).world;

        world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);

        Bukkit.getScheduler().runTaskLater(TLL2.getPlugin(TLL2.class), new Runnable() {
            @Override
            public void run() {
                for(Player player : Bukkit.getOnlinePlayers()) {
                    player.playSound(player.getLocation(), Sound.BLOCK_END_PORTAL_SPAWN, 10.0F, -1.0F);
                    player.sendTitle(Format.format(e.getTitleStorm(tierLevel)), Format.format(e.getSubtitleStorm(tierLevel)));
                }
            }
        }, 100L);
    }

    @EventHandler
    public void setBlastStorm(WeatherChangeEvent e) {
        if (e.toWeatherState()) {
            StartBlastStormEvent start = new StartBlastStormEvent();
            Bukkit.getPluginManager().callEvent(start);
        }
    }


}
