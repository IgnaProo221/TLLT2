package Listeners;

import Utilities.Data;
import Utilities.Format;
import Utilities.Utils;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BossBar;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import player.CustomPlayer;
import tlldos.tll2.TLL2;

import java.io.IOException;

public class JoinListeners implements Listener {
    private final TLL2 plugin;
    public static BossBar tormenta;
    public JoinListeners(TLL2 plugin){
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onLogin(PlayerLoginEvent e) {
        if(TLL2.mantenimiento && !e.getPlayer().isOp()){
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                e.setKickMessage(Format.format("&c&l¡El Servidor esta en Mantenimiento! Vuelva mas tarde."));
                CustomPlayer.fromName(e.getPlayer().getName()).getData().saveData(e.getPlayer());
                e.setResult(PlayerLoginEvent.Result.KICK_OTHER);
            }, 20L);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        CustomPlayer.fromName(e.getPlayer().getName()).getData().saveData(e.getPlayer());
        CustomPlayer player = CustomPlayer.addPlayer(p.getName(), p.getUniqueId());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onLeave(PlayerQuitEvent e) {
        CustomPlayer.fromName(e.getPlayer().getName()).getData().saveData(e.getPlayer());
        CustomPlayer.removePlayer(e.getPlayer().getName());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onKick(PlayerKickEvent e) {
        CustomPlayer.fromName(e.getPlayer().getName()).getData().saveData(e.getPlayer());
        CustomPlayer.removePlayer(e.getPlayer().getName());
    }

    //@EventHandler
    public void zTest(PlayerJoinEvent e){
        Player p = e.getPlayer();
        var data = Data.get(p);
        var dataTemperatura = data.get(new NamespacedKey(plugin, "temperatura"), PersistentDataType.INTEGER);
        var dataMaestria = data.get(new NamespacedKey(plugin,"maestrialvl"),PersistentDataType.INTEGER);
        var dataMaestriaExp = data.get(new NamespacedKey(plugin,"maestriaexp"),PersistentDataType.INTEGER);
        var inmunity = data.get(new NamespacedKey(plugin,"inmunity"),PersistentDataType.INTEGER);
        var level10 = data.get(new NamespacedKey(plugin,"reachedlvl10"),PersistentDataType.INTEGER);
        var level20 = data.get(new NamespacedKey(plugin,"reachedlvl20"),PersistentDataType.INTEGER);
        var level30 = data.get(new NamespacedKey(plugin,"reachedlvl30"),PersistentDataType.INTEGER);
        var extra_health =  data.get(Utils.key("maestry_health"), PersistentDataType.INTEGER);
        var negative_health = data.get(Utils.key("negative_health"), PersistentDataType.INTEGER);
        /*
        if (Utils.getConfig().contains("Maestria." + p.getName())) {

            int level;
            int exp;

            int expConfig = Utils.getConfig().getInt("Maestria." + p.getName() + ".EXP");
            int levelConfig = Utils.getConfig().getInt("Maestria." + p.getName() + ".Level");

            if (data.has(Utils.key("maestrialvl"), PersistentDataType.INTEGER)) {

                level = dataMaestria;

            } else {
                level = Utils.getConfig().getInt("Maestria." + p.getName() + ".Level");
            }

            if (data.has(Utils.key("maestriaexp"), PersistentDataType.INTEGER)) {

                exp = dataMaestriaExp;

            } else {
                exp = Utils.getConfig().getInt("Maestria." + p.getName() + ".EXP");
            }

            data.set(Utils.key("maestriaexp"), PersistentDataType.INTEGER, exp);
            data.set(Utils.key("maestrialvl"), PersistentDataType.INTEGER, level);

            Bukkit.getLogger().info("se ha registrado los PersistentData: maestriaexp y maestrialvl al jugador " + p.getName() + ".");
            Bukkit.getLogger().info("EXP: " + exp);
            Bukkit.getLogger().info("Level: " + level);

        }
         */

        if(negative_health == null){
            data.set(Utils.key("negative_health"), PersistentDataType.INTEGER, 0);
        }

        if(extra_health == null){
            data.set(Utils.key("maestry_health"), PersistentDataType.INTEGER, 0);
        }

        if(dataTemperatura == null){
            data.set(Utils.key("temperatura"), PersistentDataType.INTEGER, 30);

        }

        if(dataMaestria == null){
            data.set(Utils.key("maestrialvl"),PersistentDataType.INTEGER,1);
        }

        if(dataMaestriaExp == null){
            data.set(Utils.key("maestriaexp"),PersistentDataType.INTEGER,0);
        }

        if(inmunity == null){
            data.set(Utils.key("inmunity"),PersistentDataType.INTEGER,0);
        }
        if(level10 == null){
            data.set(Utils.key("reachedlvl10"),PersistentDataType.INTEGER,0);
        }

        if(level20 == null){
            data.set(Utils.key("reachedlvl20"),PersistentDataType.INTEGER,0);
        }

        if(level30 == null){
            data.set(Utils.key("reachedlvl30"),PersistentDataType.INTEGER,0);
        }
    }

    //@EventHandler
    public void onQuit(PlayerQuitEvent e) {
        PersistentDataContainer container = e.getPlayer().getPersistentDataContainer();
        PersistentDataContainer data = Data.get(e.getPlayer()); //Todo se que es lo mismo pero tengo que revisar
        var dataMaestria = data.get(new NamespacedKey(plugin, "maestrialvl"), PersistentDataType.INTEGER);
        var dataMaestriaExp = data.get(new NamespacedKey(plugin, "maestriaexp"), PersistentDataType.INTEGER);
        var dataMaestria2 = container.get(new NamespacedKey(plugin, "maestrialvl"), PersistentDataType.INTEGER);
        var dataMaestriaExp2 = container.get(new NamespacedKey(plugin, "maestriaexp"), PersistentDataType.INTEGER);
        if (!dataMaestria.equals(dataMaestria2)) {
            data.set(Utils.key("maestrialvl"), PersistentDataType.INTEGER, dataMaestria);
            container.set(Utils.key("maestrialvl"), PersistentDataType.INTEGER, dataMaestria);
        }
        if (!dataMaestriaExp.equals(dataMaestriaExp2)) {
            data.set(Utils.key("maestriaexp"), PersistentDataType.INTEGER, dataMaestria);
            container.set(Utils.key("maestriaexp"), PersistentDataType.INTEGER, dataMaestria);
        }

        /*
        try {

            // No se para que es lo de arriba pero bale
            // Intento de solucion a lo de perder el nivel-exp de la maestria

            int level;
            int exp;

            if (data.has(Utils.key("maestrialvl"), PersistentDataType.INTEGER)) {
                level = dataMaestria;
            } else {
                level = 1;
            }

            if (data.has(Utils.key("maestriaexp"), PersistentDataType.INTEGER)) {
                exp = dataMaestriaExp;
            } else {
                exp = 0;
            }

            Utils.getConfig().set("Maestria." + e.getPlayer().getName() + ".Level", level);
            Utils.getConfig().set("Maestria." + e.getPlayer().getName() + ".EXP", exp);

            Utils.reloadConfig();

            Bukkit.getConsoleSender().sendMessage("Se ha almacenado en el config el nivel y EXP de " + e.getPlayer().getName() + ".");
            // Basicamente se recoge el nivel del jugador (si es null es 1, si no pues es el nivel del jugador) y este se coloca en el config para almacenarlo
            // Despues al entrar y si el jugador esta en el config.yml (Maestria), si no tiene nivel, recoge los datos del config, si no, pues del data, y despues al data se le añade los valores recogidos

        } catch (Exception x) {
            x.printStackTrace();

            Bukkit.getConsoleSender().sendMessage("Se ha ocacionado un error al guardar el nivel-exp de maestria del jugador (" + e.getPlayer().getName()
                    + ") al archivo: config.yml");
        }
         */
    }
}
