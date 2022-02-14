package Eventos;

import Utilidades.Format;
import Utilidades.Utils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Biome;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.persistence.PersistentDataType;
import tlldos.tll2.TLL2;

public class Mecanica12 implements Listener{
    //SI, ES LA MECANICA DE LA TEMPERATURA, PERO TENGO EL RICH PRESENCE Y ME DA PEREZA PONERME EN DESCONECTADO
    TLL2 plugin;
    public Mecanica12(TLL2 plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void tempLol(PlayerMoveEvent e){
        var p = e.getPlayer();
        var data = p.getPersistentDataContainer();
        var dataTemperatura = data.get(new NamespacedKey(plugin, "temperatura"), PersistentDataType.INTEGER);
        var subirTemp = (dataTemperatura == null ? 20 : ++dataTemperatura);
        var bajarTemp = (dataTemperatura == null ? 20 : --dataTemperatura);
        int caca = data.get(new NamespacedKey(plugin, "temperatura"),PersistentDataType.INTEGER);
        Bukkit.getScheduler().runTaskTimer(plugin, ()->{
            if(p.getGameMode() == GameMode.SURVIVAL) {
                if (p.getLocation().getWorld().getBiome(p.getLocation()) == Biome.PLAINS) {
                    data.set(new NamespacedKey(plugin, "temperatura"), PersistentDataType.INTEGER, caca + 10);
                }else if (p.getLocation().getWorld().getBiome(p.getLocation()) == Biome.ICE_SPIKES) {
                    data.set(new NamespacedKey(plugin, "temperatura"), PersistentDataType.INTEGER, caca - 10);
                }

                if (data.get(Utils.key("temperatura"), PersistentDataType.INTEGER) >= 120) {
                    p.damage(1000);
                    p.setFireTicks(210);
                    p.sendMessage(Format.PREFIX, Format.format("&c¡Estas recibiendo Daño por estar a Temperaturas Elevadas!"));
                }
            }
        },0L,200L);
    }
}
