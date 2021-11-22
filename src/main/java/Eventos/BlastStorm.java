package Eventos;

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

        onlinePlayers.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 1));
        onlinePlayers.playSound(onlinePlayers.getLocation(), Sound.ENTITY_WITHER_SPAWN, 10.0F, -1.0F);
        onlinePlayers.sendTitle("♥" + ChatColor.GOLD + "" + ChatColor.BOLD + "      ¡BLAST STORM!      " + ChatColor.WHITE + "♥", ChatColor.RED + "¡Que Comienze el FIN!");

        tormenta.setVisible(true);
        tormenta.addPlayer(onlinePlayers);

        World world = TLL2.getPlugin().world;

        world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);

        new BukkitRunnable() {
            @Override
            public void run() {
                onlinePlayers.playSound(onlinePlayers.getLocation(), Sound.BLOCK_END_PORTAL_SPAWN, 10.0F, -1.0F);
                onlinePlayers.sendTitle(e.getTitleStorm(tierLevel), e.getSubtitleStorm(tierLevel));
            }
        }.runTaskLater(TLL2.getPlugin(), 100L);
    }

    @EventHandler
    public void setBlastStorm(WeatherChangeEvent e) {
        if (e.toWeatherState()) {
            StartBlastStormEvent start = new StartBlastStormEvent();
            Bukkit.getPluginManager().callEvent(start);
        }
    }


}
