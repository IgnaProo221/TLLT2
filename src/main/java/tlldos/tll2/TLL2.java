package tlldos.tll2;

import Comandos.ComandosStaff;
import Comandos.ComandosUsuarios;
import Eventos.*;
import Extras.*;
import Utilidades.Configuration;
import Utilidades.Mobs;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.event.Listener;
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
        registerListeners(
                new alUsarTotem(this),
                new Comer(this),
                new EnderPearlEvent(this),
                new Dormir(this),
                new GhastExplosion(this),
                new DanoSinEnieEvento(this),
                new PlayerEvents(this),
                new SpawnerListeners(this),
                new SpawnListeners(this),
                new EntityListeners(this),
                new BlastStormListeners(),
                new BlocksListeners(this),
                new MobsTeleports(this),
                new DropsListeners(this)
        );
    }

    private void registerListeners(Listener... listeners){
        for(Listener listener : listeners){
            getServer().getPluginManager().registerEvents(listener, this);
        }
    }
}
