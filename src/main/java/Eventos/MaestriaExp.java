package Eventos;

import Utilidades.Utils;
import net.minecraft.network.chat.ChatComponentText;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.TileState;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.persistence.PersistentDataType;
import tlldos.tll2.TLL2;

import static Utilidades.Format.format;

public class MaestriaExp implements Listener{
    TLL2 plugin;
    public MaestriaExp(TLL2 plugin){
        this.plugin = plugin;
    }


    @EventHandler
    public void expLol(BlockBreakEvent e){
        var p = e.getPlayer();
        var block = e.getBlock();
        var data = p.getPersistentDataContainer();
        var state = block.getState();
        var tilestate = (TileState)state;
        var dataMaestria = data.get(new NamespacedKey(plugin,"maestrialvl"), PersistentDataType.INTEGER);
        var dataMaestriaExp = data.get(new NamespacedKey(plugin,"maestriaexp"),PersistentDataType.INTEGER);


        if(dataMaestriaExp == null){
            data.set(Utils.key("maestriaexp"),PersistentDataType.INTEGER,0);
        }

        if(block.getType() == Material.COAL_ORE){
            data.set(new NamespacedKey(plugin,"maestriaexp"),PersistentDataType.INTEGER,dataMaestriaExp + 1);
            p.sendActionBar(format("&bMineria: " + dataMaestriaExp + " / "));
        }else if(block.getType() == Material.IRON_ORE){
            data.set(new NamespacedKey(plugin,"maestriaexp"),PersistentDataType.INTEGER,dataMaestriaExp + 5);
            p.sendActionBar(format("&bMineria: " + dataMaestriaExp + " / "));
        }else if(block.getType() == Material.COPPER_ORE){
            data.set(new NamespacedKey(plugin,"maestriaexp"),PersistentDataType.INTEGER,dataMaestriaExp + 1);
            p.sendActionBar(format("&bMineria: " + dataMaestriaExp + " / "));
        }else if(block.getType() == Material.GOLD_ORE){
            data.set(new NamespacedKey(plugin,"maestriaexp"),PersistentDataType.INTEGER,dataMaestriaExp + 5);
            p.sendActionBar(format("&bMineria: " + dataMaestriaExp + " / "));
        }else if(block.getType() == Material.LAPIS_ORE){
            data.set(new NamespacedKey(plugin,"maestriaexp"),PersistentDataType.INTEGER,dataMaestriaExp + 5);
            p.sendActionBar(format("&bMineria: " + dataMaestriaExp + " / "));
        }else if(block.getType() == Material.REDSTONE_ORE){
            data.set(new NamespacedKey(plugin,"maestriaexp"),PersistentDataType.INTEGER,dataMaestriaExp + 3);
            p.sendActionBar(format("&bMineria: " + dataMaestriaExp + " / "));
        }else if(block.getType() == Material.EMERALD_ORE){
            data.set(new NamespacedKey(plugin,"maestriaexp"),PersistentDataType.INTEGER,dataMaestriaExp + 10);
            p.sendActionBar(format("&bMineria: " + dataMaestriaExp + " / "));
        }else if(block.getType() == Material.DIAMOND_ORE){
            data.set(new NamespacedKey(plugin,"maestriaexp"),PersistentDataType.INTEGER,dataMaestriaExp + 3);
            p.sendActionBar(format("&bMineria: " + dataMaestriaExp + " / "));
        }else if(block.getType() == Material.NETHER_QUARTZ_ORE){
            data.set(new NamespacedKey(plugin,"maestriaexp"),PersistentDataType.INTEGER,dataMaestriaExp + 1);
            p.sendActionBar(format("&bMineria: " + dataMaestriaExp + " / "));
        }else if(block.getType() == Material.NETHER_GOLD_ORE){
            data.set(new NamespacedKey(plugin,"maestriaexp"),PersistentDataType.INTEGER,dataMaestriaExp + 1);
            p.sendActionBar(format("&bMineria: " + dataMaestriaExp + " / "));
        }else if(block.getType() == Material.DEEPSLATE_COAL_ORE){
            data.set(new NamespacedKey(plugin,"maestriaexp"),PersistentDataType.INTEGER,dataMaestriaExp + 3);
            p.sendActionBar(format("&bMineria: " + dataMaestriaExp + " / "));
        }else if(block.getType() == Material.DEEPSLATE_COPPER_ORE){
            data.set(new NamespacedKey(plugin,"maestriaexp"),PersistentDataType.INTEGER,dataMaestriaExp + 3);
            p.sendActionBar(format("&bMineria: " + dataMaestriaExp + " / "));
        }else if(block.getType() == Material.DEEPSLATE_IRON_ORE){
            data.set(new NamespacedKey(plugin,"maestriaexp"),PersistentDataType.INTEGER,dataMaestriaExp + 7);
            p.sendActionBar(format("&bMineria: " + dataMaestriaExp + " / "));
        }else if(block.getType() == Material.DEEPSLATE_GOLD_ORE){
            data.set(new NamespacedKey(plugin,"maestriaexp"),PersistentDataType.INTEGER,dataMaestriaExp + 7);
            p.sendActionBar(format("&bMineria: " + dataMaestriaExp + " / "));
        }else if(block.getType() == Material.DEEPSLATE_REDSTONE_ORE){
            data.set(new NamespacedKey(plugin,"maestriaexp"),PersistentDataType.INTEGER,dataMaestriaExp + 3);
            p.sendActionBar(format("&bMineria: " + dataMaestriaExp + " / "));
        }else if(block.getType() == Material.DEEPSLATE_LAPIS_ORE){
            data.set(new NamespacedKey(plugin,"maestriaexp"),PersistentDataType.INTEGER,dataMaestriaExp + 3);
            p.sendActionBar(format("&bMineria: " + dataMaestriaExp + " / "));
        }else if(block.getType() == Material.DEEPSLATE_DIAMOND_ORE){
            data.set(new NamespacedKey(plugin,"maestriaexp"),PersistentDataType.INTEGER,dataMaestriaExp + 10);
            p.sendActionBar(format("&bMineria: " + dataMaestriaExp + " / "));
        }else if(block.getType() == Material.DEEPSLATE_EMERALD_ORE){
            data.set(new NamespacedKey(plugin,"maestriaexp"),PersistentDataType.INTEGER,dataMaestriaExp + 15);
            p.sendActionBar(format("&bMineria: " + dataMaestriaExp + " / "));
        }

    }


    @EventHandler
    public void evitarDupeexp(BlockPlaceEvent e){
        var p = e.getPlayer();
        var block = e.getBlockPlaced();
        var state = block.getState();
        if(block.getType() == Material.COAL_ORE || block.getType() == Material.COPPER_ORE || block.getType() == Material.IRON_ORE
        || block.getType() == Material.GOLD_ORE || block.getType() == Material.LAPIS_ORE || block.getType() == Material.REDSTONE_ORE
        || block.getType() == Material.EMERALD_ORE || block.getType() == Material.DIAMOND_ORE || block.getType() == Material.NETHER_QUARTZ_ORE
        || block.getType() == Material.NETHER_GOLD_ORE || block.getType() == Material.DEEPSLATE_DIAMOND_ORE || block.getType() == Material.DEEPSLATE_EMERALD_ORE
        || block.getType() == Material.DEEPSLATE_LAPIS_ORE || block.getType() == Material.DEEPSLATE_REDSTONE_ORE || block.getType() == Material.DEEPSLATE_GOLD_ORE
        || block.getType() == Material.DEEPSLATE_IRON_ORE || block.getType() == Material.DEEPSLATE_COPPER_ORE || block.getType() == Material.DEEPSLATE_COAL_ORE){
            TileState tileState = (TileState) state;
            tileState.getPersistentDataContainer().set(Utils.key("no_exp"),PersistentDataType.INTEGER,1);
            tileState.update();
        }
    }
}
