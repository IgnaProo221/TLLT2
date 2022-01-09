package Eventos;

import Utilidades.Format;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;
import tlldos.tll2.TLL2;

import java.util.Iterator;
import java.util.Random;

import static Eventos.Muerte.tormenta;

public class BlastStorm implements Listener {


    @EventHandler
    public void onBlastStormStart(StartBlastStormEvent e) {
        int tierLevel = new Random().nextInt(3) + 1;

        for(Player player : Bukkit.getOnlinePlayers()){
            e.addPotionTiers(player, tierLevel);
            e.tierTime(tierLevel);
            tormenta.setVisible(true);
            tormenta.addPlayer(player);
        }


        World world = TLL2.getPlugin(TLL2.class).world;

        world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        world.setTime(18000);

        Bukkit.getScheduler().runTaskLater(TLL2.getPlugin(TLL2.class), new Runnable() {
            @Override
            public void run() {
                for(Player player : Bukkit.getOnlinePlayers()) {
                    player.playSound(player.getLocation(), Sound.BLOCK_END_PORTAL_SPAWN, 10.0F, -1.0F);
                    player.sendTitle(Format.format(e.getTitleStorm(tierLevel)), Format.format(e.getSubtitleStorm(tierLevel)));
                }
            }
        }, 1L);
    }

    @EventHandler
    public void setBlastStorm(WeatherChangeEvent e) {
        if (e.toWeatherState()) {
            Random random = new Random();
            int chance = 2 * Muerte.Dia() - 1;

            int a = 90;
            int b = Muerte.Dia() + 45;

            if (chance > a) {
                a = a + chance - b;
            }
            if (random.nextInt(a) < chance) {
                StartBlastStormEvent start = new StartBlastStormEvent();
                Bukkit.getPluginManager().callEvent(start);
            }else{
                e.setCancelled(true);
            }
        }
    }


}
