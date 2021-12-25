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
        Iterator<? extends Player> var2 = Bukkit.getOnlinePlayers().iterator();

        Player onlinePlayers = var2.next();

        e.addPotionTiers(onlinePlayers, tierLevel);

        tormenta.setVisible(true);
        tormenta.addPlayer(onlinePlayers);

        World world = TLL2.getPlugin(TLL2.class).world;

        world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        world.setTime(18000);

        Bukkit.getScheduler().runTaskLater(TLL2.getPlugin(TLL2.class), new Runnable() {
            @Override
            public void run() {
                onlinePlayers.playSound(onlinePlayers.getLocation(), Sound.BLOCK_END_PORTAL_SPAWN, 10.0F, -1.0F);
                onlinePlayers.sendTitle(Format.format(e.getTitleStorm(tierLevel)), Format.format(e.getSubtitleStorm(tierLevel)));
            }
        }, 1L);
    }

    @EventHandler
    public void setBlastStorm(WeatherChangeEvent e) {
        if (e.toWeatherState()) {
            StartBlastStormEvent start = new StartBlastStormEvent();
            Bukkit.getPluginManager().callEvent(start);
        }
    }


}
