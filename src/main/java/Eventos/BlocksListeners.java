package Eventos;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import tlldos.tll2.TLL2;

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
}
