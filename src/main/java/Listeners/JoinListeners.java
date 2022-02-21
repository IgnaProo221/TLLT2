package Listeners;

import Utilities.Utils;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
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
        var data = p.getPersistentDataContainer();
        var dataTemperatura = data.get(new NamespacedKey(plugin, "temperatura"), PersistentDataType.INTEGER);
        var dataMaestria = data.get(new NamespacedKey(plugin,"maestrialvl"),PersistentDataType.INTEGER);
        var dataMaestriaExp = data.get(new NamespacedKey(plugin,"maestriaexp"),PersistentDataType.INTEGER);
        var inmunity = data.get(new NamespacedKey(plugin,"inmunity"),PersistentDataType.INTEGER);

        if(dataTemperatura == null){
            data.set(Utils.key("temperatura"), PersistentDataType.INTEGER, 30);
        }
        if(dataMaestria == null){
            data.set(Utils.key("maestrialvl"),PersistentDataType.INTEGER,0);
        }
        if(dataMaestriaExp == null){
            data.set(Utils.key("maestriaexp"),PersistentDataType.INTEGER,0);
        }
        if(inmunity == null){
            data.set(Utils.key("inmunity"),PersistentDataType.INTEGER,0);
        }
    }
}
