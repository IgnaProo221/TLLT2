package tlldos.tll2;

import Comandos.ComandosStaff;
import Comandos.ComandosUsuarios;
import Eventos.*;
import Extras.*;
import Utilidades.Configuration;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public final class TLL2 extends JavaPlugin {
    public World world;

    private Configuration config;


    @Override
    public void onEnable() {
        try {
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
            //tormentaTick();
            getCommand("thelastlife").setExecutor(new ComandosUsuarios());
            getCommand("tllstaff").setExecutor(new ComandosStaff());
        } catch (Exception e){
            getServer().getConsoleSender().sendMessage("######################################################");
            getServer().getConsoleSender().sendMessage("######################################################");
            getServer().getConsoleSender().sendMessage("######################################################");
            getServer().getConsoleSender().sendMessage("A OCURRIDO UN ERROR FATAL Y EL PLUGIN NO PUDO SER INICIADO");
            getServer().getConsoleSender().sendMessage("EL SERVER SE AUTO-CERRARA PARA EVITAR ALGUN TIPO DE EXPLOIT");
            getServer().getConsoleSender().sendMessage("ERROR: " + e);
            getServer().getConsoleSender().sendMessage("######################################################");
            getServer().getConsoleSender().sendMessage("######################################################");
            getServer().getConsoleSender().sendMessage("######################################################");
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "stop");
        }
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "El Plugin se deshabilito correctamente!");
    }

    public void cargarEventos(){
        getServer().getPluginManager().registerEvents(new alUsarTotem(this), this);
        getServer().getPluginManager().registerEvents(new AlEntrar(this),this);
        getServer().getPluginManager().registerEvents(new Comer(this), this);
        getServer().getPluginManager().registerEvents(new EnderPearlEvent(this), this);
        getServer().getPluginManager().registerEvents(new Dormir(this),this);
        getServer().getPluginManager().registerEvents(new GhastExplosion(this),this);
        getServer().getPluginManager().registerEvents(new DanoSinEnieEvento(this),this);
       // getServer().getPluginManager().registerEvents(new BlastStorm(),this);
        getServer().getPluginManager().registerEvents(new PlayerEvents(), this);
        getServer().getPluginManager().registerEvents(new SpawnListeners(this),this);
        getServer().getPluginManager().registerEvents(new SpawnerListeners(this), this);
        getServer().getPluginManager().registerEvents(new EntityListeners(this), this);
        getServer().getPluginManager().registerEvents(new BlastStormListeners(this), this);
        getServer().getPluginManager().registerEvents(new BlocksListeners(this),this);
        getServer().getPluginManager().registerEvents(new MobsTeleports(this),this);
    }
    /*public void tormentaTick(){
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

     */


}
