package Listeners;

import Utilities.Data;
import Utilities.Utils;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import tlldos.tll2.TLL2;

public class JoinListeners implements Listener {
     TLL2 plugin;
    public static BossBar tormenta;
    public JoinListeners(TLL2 plugin){
        this.plugin = plugin;
    }

    @EventHandler
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

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        PersistentDataContainer container = e.getPlayer().getPersistentDataContainer();
        PersistentDataContainer data = Data.get(e.getPlayer()); //Todo se que es lo mismo pero tengo que revisar
        var dataMaestria = data.get(new NamespacedKey(plugin,"maestrialvl"),PersistentDataType.INTEGER);
        var dataMaestriaExp = data.get(new NamespacedKey(plugin,"maestriaexp"),PersistentDataType.INTEGER);
        var dataMaestria2 = container.get(new NamespacedKey(plugin,"maestrialvl"),PersistentDataType.INTEGER);
        var dataMaestriaExp2 = container.get(new NamespacedKey(plugin,"maestriaexp"),PersistentDataType.INTEGER);
        if(!dataMaestria.equals(dataMaestria2)){
            data.set(Utils.key("maestrialvl"),PersistentDataType.INTEGER,dataMaestria);
            container.set(Utils.key("maestrialvl"),PersistentDataType.INTEGER,dataMaestria);
        }
        if(!dataMaestriaExp.equals(dataMaestriaExp2)){
            data.set(Utils.key("maestriaexp"),PersistentDataType.INTEGER,dataMaestria);
            container.set(Utils.key("maestriaexp"),PersistentDataType.INTEGER,dataMaestria);
        }
    }
}
