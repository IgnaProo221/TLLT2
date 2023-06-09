package Utilities;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.ClipboardHolder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import tlldos.tll2.TLL2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Utils {

    private static final Plugin plugin = TLL2.getPlugin(TLL2.class);

    public static World getWorld(){
        return Bukkit.getWorld("world");
    }

    public static Plugin getPlugin() {
        return plugin;
    }

    public static NamespacedKey key(String key){
        return new NamespacedKey(plugin, key);
    }
    
    private static int Day() {
        LocalDate FechaActual = LocalDate.now();

        LocalDate FechaInicio = LocalDate.parse("2022-03-08");

        return (int) ChronoUnit.DAYS.between(FechaInicio, FechaActual);
    }
    private static int NewDayAfterStorm(){
        LocalDate FechaActual = LocalDate.now();
        LocalDate FechaInicio = LocalDate.parse("2022-03-20");
        return (int) ChronoUnit.DAYS.between(FechaInicio,FechaActual);
    }
    
    public static int getDay(){
        return Day();
    }
    public static int getNewDay(){return NewDayAfterStorm();}
    
    public static FileConfiguration getConfig() {
        return plugin.getConfig();
    }

    public static void reloadConfig() {
        try {
            getConfig().save("config.yml");
            getConfig().load("config.yml");
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static void pasteSchematic(String filename, Location loc){
        if(loc.getY() < -64){
            loc.setY(0);
        }

        int X = loc.getBlockX();
        int Y = loc.getBlockY();
        int Z = loc.getBlockZ();

        File file = new File(plugin.getDataFolder().getPath() + "/schematics/" + filename + ".schem");
        com.sk89q.worldedit.world.World adaptedWorld = BukkitAdapter.adapt(loc.getWorld());
        ClipboardFormat format = ClipboardFormats.findByFile(file);

        try {
            assert format != null;
            try (ClipboardReader reader = format.getReader(new FileInputStream(file))) {
                Clipboard clipboard = reader.read();
                try (EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(adaptedWorld,-1)) {
                    Operation operation = new ClipboardHolder(clipboard).createPaste(editSession).to(BlockVector3.at(X, Y, Z)).copyEntities(true)
                            .ignoreAirBlocks(true).build();
                    Bukkit.getConsoleSender().sendMessage("La Schematic se pego en " + loc.getBlock().getX() +"  "+loc.getBlock().getY() +"  "+loc.getBlock().getZ());
                    try {
                        Operations.complete(operation);
                        editSession.flushSession();

                    } catch (WorldEditException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
