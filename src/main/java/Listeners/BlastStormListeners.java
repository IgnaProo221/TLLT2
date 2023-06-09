package Listeners;

import Utilities.Format;
import Utilities.Utils;
import org.bukkit.*;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.weather.ThunderChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import java.util.Random;

import static Utilities.Format.format;

public class BlastStormListeners implements Listener {

    private static BossBar bossBar;
    private static Integer TaskBossBarID = 0;

    private static boolean active = false;


    public static void createBossBar() {
        if (bossBar == null) {
            bossBar = Bukkit.createBossBar(Format.format("&f马         &6&lBlast Storm: " + getTime() +  "         &f马"), BarColor.YELLOW, BarStyle.SEGMENTED_6);
        }

        TaskBossBarID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Utils.getPlugin(), () -> {
            bossBar.setTitle(Format.format("&f马         &6&lBlast Storm: " + getTime() +  "         &f马"));
        }, 0L, 20L);
    }
    @EventHandler
    public void onBlastStormStart(StartBlastStormEvent e) {
        int tierLevel = new Random().nextInt(3) + 1;

        createBossBar();

        Bukkit.getOnlinePlayers().forEach(player -> {
            e.addPotionTiers(player, tierLevel);
            bossBar.setVisible(true);
            bossBar.addPlayer(player);
        });

        World world = Utils.getWorld();
        active = true;

        world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        world.setTime(18000);
        Bukkit.getScheduler().scheduleSyncDelayedTask(Utils.getPlugin(), () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.playSound(player.getLocation(),"tllt2.stormsound", SoundCategory.RECORDS,10.0F, 1.0F);
                player.sendTitle(Format.format(e.getTitleStorm(tierLevel)), Format.format(e.getSubtitleStorm(tierLevel)));
            }
        }, 2L);
    }
    @EventHandler
    public void onBlastStormEnd(StopBlastStormEvent e){
        Bukkit.getOnlinePlayers().forEach(bossBar::removePlayer);
        if(TaskBossBarID != null) {
            Bukkit.getScheduler().cancelTask(TaskBossBarID);
            TaskBossBarID = null;
        }
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "weather clear 999999"); // nose cuantos 9 son pero hola:
        active = false;
    }

    private static String getTime(){
        if(Utils.getWorld().getWeatherDuration() > 0){
            long segundos = (long) (Utils.getWorld().getWeatherDuration() / 20);
            long hours = segundos / 3600L;
            long minutes = segundos % 3600L / 60L;
            long seconds = segundos % 60L;

            return String.format("%02d:%02d:%02d", hours, minutes, seconds);
        }
        return " ";
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (BlastStormListeners.isEnabled() && bossBar != null) {
            bossBar.addPlayer(e.getPlayer());
        }

    }
    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        if(bossBar != null && bossBar.getPlayers().contains(e.getPlayer())){
            bossBar.removePlayer(e.getPlayer());
        }
    }
    @EventHandler
    public void onDeathBlastStorm(PlayerDeathEvent e) {
        World world = Bukkit.getWorld("world");
        Bukkit.getScheduler().runTaskLater(Utils.getPlugin(), () -> {

            if (world != null) {
                world.setTime(18000);
                world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
            }

            int days = Utils.getNewDay();

            int time = world.isThundering() ? world.getWeatherDuration() / 20 + days * 1800 : days * 1800;

            if (time >= Integer.MAX_VALUE) {
                Bukkit.getConsoleSender().sendMessage("El tiempo de la tormenta ha superado el tiempo maximo.");
                return;
            }

            String setThunder = "weather thunder " + time;

            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), setThunder);

            StartBlastStormEvent start = new StartBlastStormEvent();
            Bukkit.getPluginManager().callEvent(start);
        }, 200L);
    }




    @EventHandler
    public void onThunderChange(ThunderChangeEvent e){
        if(!e.toThunderState()){
            Bukkit.getLogger().info("asd2");
            for(Player players : Bukkit.getOnlinePlayers()) {
                bossBar.removePlayer(players);
                players.sendMessage(Format.PREFIX, format("&6&lLos cielos se han despejado... por ahora."));
                players.playSound(players.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10.0F, 0.5F);

            }
            bossBar.setVisible(false);
            Utils.getWorld().setGameRule(GameRule.DO_DAYLIGHT_CYCLE, true);


            StopBlastStormEvent event = new StopBlastStormEvent(StopBlastStormEvent.Cause.NATURAL);
            Bukkit.getPluginManager().callEvent(event);
        }
    }


        //Testear antes de poner @EventHandler
    public void onWeatherChange(WeatherChangeEvent e){
        if(BlastStormListeners.isEnabled() && e.toWeatherState()) {
            e.setCancelled(true);
            Bukkit.getLogger().info("Ha comenzado una tormenta natural cuando ya estaba una blast storm activa");
        }else if(e.toWeatherState() && !BlastStormListeners.isEnabled()) {
            Bukkit.getScheduler().runTask(Utils.getPlugin(), () -> e.setCancelled(true));
        }
    }
    public static boolean isEnabled() {
        return active;
    }
}
