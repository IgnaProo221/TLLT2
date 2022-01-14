package Eventos;

import Utilidades.Format;
import Utilidades.Utils;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import java.util.Objects;
import java.util.Random;

import static Utilidades.Format.format;
import static Utilidades.Format.prefix;

public class BlastStormListeners implements Listener {

    private static final BossBar bossBar = Bukkit.createBossBar(Format.format("&f♥        &6&lBlast Storm: " + getTime() +  "        &f♥"), BarColor.YELLOW, BarStyle.SEGMENTED_6);
    private static Integer TaskBossBarID = 0;


    public static void createBossBar() {
        TaskBossBarID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Utils.getPlugin(), () -> {
            Bukkit.createBossBar(Format.format("&f♥        &6&lBlast Storm: " + getTime() +  "        &f♥"), BarColor.YELLOW, BarStyle.SEGMENTED_6);
        }, 0L, 20L);
    }
    @EventHandler
    public void onBlastStormStart(StartBlastStormEvent e) {
        int tierLevel = new Random().nextInt(3) + 1;

        Bukkit.getOnlinePlayers().forEach(player -> {
            e.addPotionTiers(player, tierLevel);
            bossBar.setVisible(true);
            bossBar.addPlayer(player);
        });
        createBossBar();


        World world = Utils.getWorld();

        world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        world.setTime(18000);
        Bukkit.getScheduler().scheduleSyncDelayedTask(Utils.getPlugin(), () -> {
            for(Player player : Bukkit.getOnlinePlayers()) {
                player.playSound(player.getLocation(), Sound.BLOCK_END_PORTAL_SPAWN, 10.0F, -1.0F);
                player.sendTitle(Format.format(e.getTitleStorm(tierLevel)), Format.format(e.getSubtitleStorm(tierLevel)));
            }
        }, 2L);
    }
    @EventHandler
    public void onBlastStormEnd(StopBlastStormEvent e){
        Bukkit.getScheduler().cancelTask(TaskBossBarID);
        TaskBossBarID = null;
        //Weather clear y lo demas
        Bukkit.getOnlinePlayers().forEach(bossBar::removePlayer);
    }

    private static String getTime(){
        if(Utils.getWorld().hasStorm()) {
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
        if (Utils.getWorld().hasStorm()) {
            bossBar.addPlayer(e.getPlayer());
        }

    }
    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        if(bossBar.getPlayers().contains(e.getPlayer())){
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
            String setThunder = Objects.requireNonNull(Bukkit.getWorld("world")).isThundering() ? "weather thunder " + (Utils.getWorld().getWeatherDuration() / 20) + (Utils.getDay() * 1800) : "weather thunder " + (Utils.getDay() * 1800);

            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), setThunder);

            StartBlastStormEvent start = new StartBlastStormEvent();
            Bukkit.getPluginManager().callEvent(start);
        }, 200L);
    }



    @EventHandler //no entiendo para que es esto pero ok
    public void setBlastStorm(WeatherChangeEvent e) {
        if (e.toWeatherState()) {
            Random random = new Random();
            int chance = 2 * Utils.getDay() - 1;

            int a = 90;
            int b = Utils.getDay() + 45;

            if (chance > a) {
                a = a + chance - b;
            }
            if (random.nextInt(a) < chance) {
                StartBlastStormEvent start = new StartBlastStormEvent();
                Bukkit.getPluginManager().callEvent(start);
            }else{
                e.setCancelled(true);
            }
        }else if(!e.toWeatherState()){
            for(Player players : Bukkit.getOnlinePlayers()) {
                bossBar.removePlayer(players);
                players.sendMessage(prefix(), format("&6&lLos cielos se despejaron... por ahora."));
                players.playSound(players.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10.0F, 0.5F);

            }
            bossBar.setVisible(false);
            Utils.getWorld().setGameRule(GameRule.DO_DAYLIGHT_CYCLE, true);
            StopBlastStormEvent event = new StopBlastStormEvent(StopBlastStormEvent.Cause.NATURAL);
            Bukkit.getPluginManager().callEvent(event);
        }
    }

}