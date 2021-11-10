package tlldos.tll2;

import Comandos.ComandosStaff;
import Comandos.ComandosUsuarios;
import Eventos.*;
import Extras.DanoSinEnieEvento;
import Extras.EnderPearlEvent;
import Extras.GhastExplosion;
import Utilidades.Configuration;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import team.unnamed.gui.core.GUIListeners;

public final class TLL2 extends JavaPlugin {
    private World world;
    public static BossBar tormenta;
    private Configuration config;
    public static TLL2 plugin;


    @Override
    public void onEnable() {
        saveDefaultConfig();
        getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "_______________________________________________________________________");
        getServer().getConsoleSender().sendMessage(ChatColor.GOLD +
                "  _______ _      _        _______ ___  \n" +
                " |__   __| |    | |      |__   __|__ \\ \n" +
                "    | |  | |    | |         | |     ) |\n" +
                "    | |  | |    | |         | |    / / \n" +
                "    | |  | |____| |____     | |   / /_ \n" +
                "    |_|  |______|______|    |_|  |____|");
        getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "THE LAST LIFE T2 >>> " + ChatColor.YELLOW + "TheLastLifeT2Test.jar se cargo correctamente!");
        getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "_______________________________________________________________________");
        world = Bukkit.getWorld("world");
        new Muerte(this);
        cargarEventos();
        tormentaTick();
        getCommand("thelastlife").setExecutor(new ComandosUsuarios());
        getCommand("tllstaff").setExecutor(new ComandosStaff());

    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "El Plugin se deshabilito correctamente!");
    }
    public void cargarEventos(){
        getServer().getPluginManager().registerEvents(new GUIListeners(),this);
        getServer().getPluginManager().registerEvents(new alUsarTotem(this), this);
        getServer().getPluginManager().registerEvents(new AlEntrar(this),this);
        getServer().getPluginManager().registerEvents(new Comer(this), this);
        getServer().getPluginManager().registerEvents(new EnderPearlEvent(this), this);
        getServer().getPluginManager().registerEvents(new Dormir(this),this);
        getServer().getPluginManager().registerEvents(new GhastExplosion(this),this);
        getServer().getPluginManager().registerEvents(new DanoSinEnieEvento(this),this);
    }
    public void tormentaTick(){
        if(world.getWeatherDuration() != 0) {
            Bukkit.getScheduler().runTaskTimer(this, new Runnable() {
                @Override
                public void run() {
                    if (world.hasStorm()) {
                        for(Player players : Bukkit.getOnlinePlayers()){
                            long segundos = (long) (world.getWeatherDuration() / 20);
                            long hours = segundos  / 3600L;
                            long minutes = segundos % 3600L / 60L;
                            long seconds = segundos % 60L;
                            String time = String.format("%02d:%02d:%02d", hours, minutes, seconds);

                            Muerte.tormenta.setTitle(ChatColor.translateAlternateColorCodes('&', "&f♥        &6&lBlast Storm: " + time +  "        &f♥"));
                        }
                    }
                }
            },0,20L);
        }
    }

    public static TLL2 getPlugin() {
        return plugin;
    }
}
