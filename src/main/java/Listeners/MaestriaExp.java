package Listeners;

import Utilities.Data;
import Utilities.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.TileState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import tlldos.tll2.TLL2;

import static Utilities.Format.format;

public class MaestriaExp implements Listener{
    TLL2 plugin;
    public MaestriaExp(TLL2 plugin){
        this.plugin = plugin;
    }


    @EventHandler
    public void expLol(BlockBreakEvent e){
        var p = e.getPlayer();
        var block = e.getBlock();

        TileState a = (TileState)block.getState();

        if (a.getPersistentDataContainer().has(Utils.key("no_exp"), PersistentDataType.INTEGER)) {
            return;
        }
        if (!block.getType().name().toLowerCase().contains("ore")) {
            return;
        }

        int level = getMasteryLevel(p);
        int exp = getMasteryExp(p);

        int asd = level - 1;

        p.sendActionBar(format("&bMineria: " + exp + " / "));
        p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10.0F, 2.0F);

        if (exp >= maxExpNecesary(p)) {
            setMasteryEXP(p, -maxExpNecesary(p));
            giveLevel(p, 1);

            p.playSound(p.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_DEPLETE, 10.0F, 2.0F);

            p.sendTitle(format("&b¡NUEVO NIVEL!"), format("&8" + asd + "&c -> &7" + level));

            Bukkit.getOnlinePlayers().forEach(player -> {
                player.sendMessage(format("&3MAESTRIA &8> &c&l" + p.getName() + "&7 ha aumentado su nivel. &e" + asd + "&8 >> &6" + level));
            });
            return;
        }

        setMasteryEXP(p, getMasteryExp(p) + getGiveExp(block));

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
        || block.getType() == Material.DEEPSLATE_IRON_ORE || block.getType() == Material.DEEPSLATE_COPPER_ORE || block.getType() == Material.DEEPSLATE_COAL_ORE) {

            TileState data = (TileState)state;

            data.getPersistentDataContainer().set(Utils.key("no_exp"), PersistentDataType.INTEGER, 1);
            data.update();
        }
    }


    public int getGiveExp(Block block) {
        if(block.getType() == Material.COAL_ORE){
            return 1;
        }else if(block.getType() == Material.IRON_ORE){
            return 5;
        }else if(block.getType() == Material.COPPER_ORE){
            return 1;
        }else if(block.getType() == Material.GOLD_ORE){
            return 5;
        }else if(block.getType() == Material.LAPIS_ORE){
            return 5;
        }else if(block.getType() == Material.REDSTONE_ORE){
            return 3;
        }else if(block.getType() == Material.EMERALD_ORE){
            return 10;
        }else if(block.getType() == Material.DIAMOND_ORE){
            return 3;
        }else if(block.getType() == Material.NETHER_QUARTZ_ORE){
            return 1;
        }else if(block.getType() == Material.NETHER_GOLD_ORE){
            return 1;
        }else if(block.getType() == Material.DEEPSLATE_COAL_ORE){
            return 3;
        }else if(block.getType() == Material.DEEPSLATE_COPPER_ORE){
           return 3;
        }else if(block.getType() == Material.DEEPSLATE_IRON_ORE){
            return 7;
        }else if(block.getType() == Material.DEEPSLATE_GOLD_ORE){
            return 7;
        }else if(block.getType() == Material.DEEPSLATE_REDSTONE_ORE){
            return 3;
        }else if(block.getType() == Material.DEEPSLATE_LAPIS_ORE){
            return 3;
        }else if(block.getType() == Material.DEEPSLATE_DIAMOND_ORE){
            return 10;
        }else if(block.getType() == Material.DEEPSLATE_EMERALD_ORE){
            return 15;
        } else {
            return 0;
        }
    }


    public int maxExpNecesary(Player p) {
        return 1000 * getMasteryLevel(p);
    }












    //Mastery Level Desing


    public int getMasteryExp(Player p) {
        PersistentDataContainer data = Data.get(p);

        return data.has(Utils.key("maestriaexp"), PersistentDataType.INTEGER) ? data.get(Utils.key("maestriaexp"), PersistentDataType.INTEGER) : 0;
    }

    public int getMasteryLevel(Player p) {
        PersistentDataContainer data = Data.get(p);

        return data.has(Utils.key("maestrialvl"), PersistentDataType.INTEGER) ? data.get(Utils.key("maestrialvl"), PersistentDataType.INTEGER) : 0;
    }

    public void setMasteryEXP(Player p, int exp) {
        PersistentDataContainer data = Data.get(p);

        int required = 1000;

        if (exp >= required) {
            int source = exp / required;

            giveLevel(p, getMasteryExp(p) + source);
        }

        data.set(Utils.key("maestriaexp"), PersistentDataType.INTEGER, exp);
    }

    public void setMasteryLevel(Player p, int level) {
        PersistentDataContainer data = Data.get(p);

        int max = 20;

        if (level >= max) {
            return;
        }

        data.set(Utils.key("maestrialvl"), PersistentDataType.INTEGER, level);
    }

    public void giveLevel(Player p, int var1) {
        PersistentDataContainer data = Data.get(p);

        int level = data.get(Utils.key("maestriaexp"), PersistentDataType.INTEGER) + var1;

        data.set(Utils.key("maestriaexp"), PersistentDataType.INTEGER, level);
    }
}
