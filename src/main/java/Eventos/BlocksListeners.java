package Eventos;

import Utilidades.Format;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.potion.PotionEffectType;
import tlldos.tll2.TLL2;

import static Utilidades.Format.format;

public class BlocksListeners implements Listener{
    private TLL2 plugin;
    public BlocksListeners(TLL2 plugin){
        this.plugin = plugin;
    }


    @EventHandler
    public void spawnerXd(BlockExplodeEvent e){
        var spawner = e.getBlock();
        if(spawner.getType().equals(Material.SPAWNER)){
            e.setCancelled(true);
        }
    }

    /*@EventHandler
    public void miedoXd(BlockPlaceEvent e){
        var p = e.getPlayer();
        if()return;
        if(!(p.getPotionEffect(PotionEffectType.LUCK).getAmplifier() == 2))return;
         if (p.getPotionEffect(PotionEffectType.LUCK).getAmplifier() == 2){
            e.setCancelled(true);
            p.sendMessage(Format.PREFIX + format("&cEstas Paniqueando!"));
        }else if(p.getPotionEffect(PotionEffectType.LUCK).getAmplifier() == 3){
            e.setCancelled(true);
            p.sendMessage(Format.PREFIX + format("&cEstas Paniqueando!"));
        }
    }*/

    /*@EventHandler
    public void miedodeMierdaLol(BlockBreakEvent e){
        var p = e.getPlayer();
        if(!(p.getPotionEffect(PotionEffectType.LUCK).getAmplifier() == 2)){
            return;
        }else if (p.getPotionEffect(PotionEffectType.LUCK).getAmplifier() == 2){
            e.setCancelled(true);
            p.sendMessage(Format.PREFIX + format("&cEstas Paniqueando!"));
        }else if(p.getPotionEffect(PotionEffectType.LUCK).getAmplifier() == 3){
            e.setCancelled(true);
            p.sendMessage(Format.PREFIX + format("&cEstas Paniqueando!"));
        }
    }*/
}
