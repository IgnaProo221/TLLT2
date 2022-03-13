package Listeners;

import Utilities.Data;
import Utilities.Mobs;
import Utilities.Utils;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPistonEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import player.CustomPlayer;
import player.PlayerData;
import tlldos.tll2.TLL2;

import java.util.Random;

import static Utilities.Format.PREFIX;
import static Utilities.Format.format;

public class MaestriaExp implements Listener{
    private static MaestriaExp instance;
    TLL2 plugin;
    public MaestriaExp(TLL2 plugin){
        this.plugin = plugin;
        instance = this;
    }
    public static MaestriaExp getInstance(){
        return instance;
    }

    static public String hp_plus = format("&3MAESTRIA &8> Tu Vida Máxima ha aumentado!");
    static public String att_plus = format("&3MAESTRIA &8> Tu Daño Base ha aumentado!");
    static public String def_plus = format("&3MAESTRIA &8> Tu Defensa Base ha aumentado!");

    @EventHandler
    public void expLol(BlockBreakEvent e){
        var p = e.getPlayer();
        var block = e.getBlock();
        //PersistentDataContainer data = Data.get(p);

        if (e.getBlock().getState().hasMetadata("no_exp")) {
            plugin.getLocations().remove(block.getLocation());
            return;
        }

        if (!block.getType().name().toLowerCase().contains("ore"))
            return;

        PlayerData data = CustomPlayer.fromName(p.getName()).getData();
        int level = data.getMasteryLevel();
        int exp = data.getMasteryExp();

        if (level >= 30) return;
        p.sendActionBar(format("&bMinería: " + exp + " / " + maxExpNecesary(level)));
        p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10.0F, 2.0F);

        data.checkMasteryLevel(p, getGiveExp(block));
    }


    @EventHandler
    public void avoidDupeXP(BlockPlaceEvent e){
        var p = e.getPlayer();
        var block = e.getBlockPlaced();
        var state = block.getState();

        if(block.getType().name().toLowerCase().contains("ore") || (block.getType() == Material.COAL_ORE || block.getType() == Material.COPPER_ORE || block.getType() == Material.IRON_ORE
        || block.getType() == Material.GOLD_ORE || block.getType() == Material.LAPIS_ORE || block.getType() == Material.REDSTONE_ORE
        || block.getType() == Material.EMERALD_ORE || block.getType() == Material.DIAMOND_ORE || block.getType() == Material.NETHER_QUARTZ_ORE
        || block.getType() == Material.NETHER_GOLD_ORE || block.getType() == Material.DEEPSLATE_DIAMOND_ORE || block.getType() == Material.DEEPSLATE_EMERALD_ORE
        || block.getType() == Material.DEEPSLATE_LAPIS_ORE || block.getType() == Material.DEEPSLATE_REDSTONE_ORE || block.getType() == Material.DEEPSLATE_GOLD_ORE
        || block.getType() == Material.DEEPSLATE_IRON_ORE || block.getType() == Material.DEEPSLATE_COPPER_ORE || block.getType() == Material.DEEPSLATE_COAL_ORE)) {

            state.setMetadata("no_exp", new FixedMetadataValue(plugin,"true"));

            plugin.getLocations().add(block.getLocation());
        }
    }
/*
    @EventHandler
    public void onPiston(BlockPistonExtendEvent e){

        e.getBlocks().forEach(block -> {
            if(block.getType().name().toLowerCase().contains("ore") || block.hasMetadata("no_exp")){
                e.setCancelled(true);
            }
        });
    }
    @EventHandler
    public void onPiston(BlockPistonEvent e){
        if(e.getBlock().getType().name().toLowerCase().contains("ore")|| e.getBlock().hasMetadata("no_exp")) {
            e.setCancelled(true);
        }
    } */


    @EventHandler
    public void apareciolacreatura(BlockBreakEvent event){
        var p = event.getPlayer();
        PlayerData data = CustomPlayer.fromName(p.getName()).getData();
        var level10 = data.hasReachedLevel10();
        var level20 = data.hasReachedLevel20();
        var level30 = data.hasReachedLevel30();
        int minerspawn = new Random().nextInt(100);
        if (!event.getBlock().getType().name().toLowerCase().contains("ore"))
            return;
        /*if (event.getBlock().getState().hasMetadata("no_exp")) {
            plugin.getLocations().remove(event.getBlock().getLocation());
            return;
        }

         */
        if(minerspawn == 1){
            if(level10){
                p.sendTitle(format("&c&l¡PELIGRO!"),format("&cUn mob va a spawnear"),0,180,0);
                p.playSound(p.getLocation(), Sound.BLOCK_BEACON_DEACTIVATE, 10.0F, -1.0F);
                Bukkit.getScheduler().runTaskLater(plugin,()->{
                    event.getBlock().getLocation().createExplosion(5,false,true);
                },175);
                Bukkit.getScheduler().runTaskLater(plugin,()->{
                    int randommob = new Random().nextInt(3);
                    if(randommob == 1){
                        var gogblinn = event.getBlock().getLocation().getWorld().spawn(event.getBlock().getLocation(), Drowned.class);
                        Mobs.goblin(gogblinn);
                    }else if(randommob == 2){
                        var stonelolxd = event.getBlock().getLocation().getWorld().spawn(event.getBlock().getLocation(), Zombie.class);
                        Mobs.stoneSoldier(stonelolxd);
                    }else{
                        var escarabajolol = event.getBlock().getLocation().getWorld().spawn(event.getBlock().getLocation(), Silverfish.class);
                        Mobs.escarabajoGoliath(escarabajolol);
                    }
                },180L);
            }
        }else if(minerspawn == 2){
            if(level20){
                p.sendTitle(format("&c&l¡PELIGRO!"),format("&cUn mob va a spawnear"),0,180,0);
                p.playSound(p.getLocation(), Sound.BLOCK_BEACON_DEACTIVATE, 10.0F, -1.0F);
                Bukkit.getScheduler().runTaskLater(plugin,()->{
                    event.getBlock().getLocation().createExplosion(5,false,true);
                },175);
                Bukkit.getScheduler().runTaskLater(plugin,()->{
                    int randommob = new Random().nextInt(2);
                    if(randommob == 1){
                        var creeperore = event.getBlock().getLocation().getWorld().spawn(event.getBlock().getLocation(), Creeper.class);
                        Mobs.oreCreeper(creeperore);
                    }else {
                        var spectreass = event.getBlock().getLocation().getWorld().spawn(event.getBlock().getLocation(),Skeleton.class);
                        Mobs.spectreAssasin(spectreass);
                    }
                },180L);
            }
        }else if(minerspawn == 3){
            if(level30){
                p.sendTitle(format("&c&l¡PELIGRO!"),format("&cUn mob va a spawnear"),0,180,0);
                p.playSound(p.getLocation(), Sound.BLOCK_BEACON_DEACTIVATE, 10.0F, -1.0F);
                Bukkit.getScheduler().runTaskLater(plugin,()->{
                    event.getBlock().getLocation().createExplosion(5,false,true);
                },175);
                Bukkit.getScheduler().runTaskLater(plugin,()->{
                    var irongollol = event.getBlock().getLocation().getWorld().spawn(event.getBlock().getLocation(),IronGolem.class);
                    Mobs.lostGolem(irongollol);
                },180L);
            }
        }
    }



    public int getGiveExp(Block block) {
        /*return switch (block.getType()){
            case COAL_ORE, COPPER_ORE, NETHER_QUARTZ_ORE, NETHER_GOLD_ORE -> 10;
            case IRON_ORE, GOLD_ORE, LAPIS_ORE -> 25;
            case REDSTONE_ORE, DEEPSLATE_COAL_ORE, DEEPSLATE_COPPER_ORE, DEEPSLATE_REDSTONE_ORE, DEEPSLATE_LAPIS_ORE, DIAMOND_ORE -> 35;
            case EMERALD_ORE, DEEPSLATE_DIAMOND_ORE -> 60;
            case DEEPSLATE_IRON_ORE, DEEPSLATE_GOLD_ORE -> 30;
            case DEEPSLATE_EMERALD_ORE -> 85;
            default -> 0;
            //Todo lo mismo que arriba revisar bien porfavor
        };*/
        if(block.getType() == Material.COAL_ORE){
            return 10;
        }else if(block.getType() == Material.IRON_ORE){
            return 35;
        }else if(block.getType() == Material.COPPER_ORE){
            return 10;
        }else if(block.getType() == Material.GOLD_ORE){
            return 35;
        }else if(block.getType() == Material.LAPIS_ORE){
            return 25;
        }else if(block.getType() == Material.REDSTONE_ORE){
            return 25;
        }else if(block.getType() == Material.EMERALD_ORE){
            return 10;
        }else if(block.getType() == Material.DIAMOND_ORE){
            return 35;
        }else if(block.getType() == Material.NETHER_QUARTZ_ORE){
            return 20;
        }else if(block.getType() == Material.NETHER_GOLD_ORE){
            return 20;
        }else if(block.getType() == Material.DEEPSLATE_COAL_ORE){
            return 25;
        }else if(block.getType() == Material.DEEPSLATE_COPPER_ORE){
           return 35;
        }else if(block.getType() == Material.DEEPSLATE_IRON_ORE){
            return 45;
        }else if(block.getType() == Material.DEEPSLATE_GOLD_ORE){
            return 45;
        }else if(block.getType() == Material.DEEPSLATE_REDSTONE_ORE){
            return 35;
        }else if(block.getType() == Material.DEEPSLATE_LAPIS_ORE){
            return 35;
        }else if(block.getType() == Material.DEEPSLATE_DIAMOND_ORE){
            return 60;
        }else if(block.getType() == Material.DEEPSLATE_EMERALD_ORE){
            return 60;
        } else {
            return 1;
        }
    }

    public int maxExpNecesary(int level) {
        return 1500 * level;
    }

    // TODO Mover al nuevo sistema de Datos
    /*

    //Mastery Level Desing
    public int getMasteryExp(Player p) {
        PersistentDataContainer data = Data.get(p);

        return data.has(Utils.key("maestriaexp"), PersistentDataType.INTEGER) ? data.get(Utils.key("maestriaexp"), PersistentDataType.INTEGER) : 0;
    }

    public int getMasteryLevel(Player p) {
        PersistentDataContainer data = Data.get(p);

        return data.has(Utils.key("maestrialvl"), PersistentDataType.INTEGER) ? data.get(Utils.key("maestrialvl"), PersistentDataType.INTEGER) : 1;
    }

    public void setMasteryEXP(Player p, int exp) {
        PersistentDataContainer data = Data.get(p);

        data.set(Utils.key("maestriaexp"), PersistentDataType.INTEGER, exp);
    }

    public void setMasteryLevel(Player p, int level) {
        PersistentDataContainer data = Data.get(p);

        int max = 30;

        if (level >= max) {
            return;
        }

        data.set(Utils.key("maestrialvl"), PersistentDataType.INTEGER, level);
    }

    public void giveLevel(Player p, int levelAdd) {
        PersistentDataContainer data = Data.get(p);

        int level = data.get(Utils.key("maestrialvl"), PersistentDataType.INTEGER);

        data.set(Utils.key("maestrialvl"), PersistentDataType.INTEGER, level + levelAdd);
    }
     */
}
