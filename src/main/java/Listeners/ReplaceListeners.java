package Listeners;

import Utilities.Mobs;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import tlldos.tll2.TLL2;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ReplaceListeners implements Listener{
    private TLL2 plugin;
    public ReplaceListeners(TLL2 plugin){
        this.plugin = plugin;
    }

    //TODO :v
    @EventHandler
    public void onEntityLoad(ChunkLoadEvent event){

    }

}
