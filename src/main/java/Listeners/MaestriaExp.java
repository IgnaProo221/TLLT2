package Listeners;

import Utilities.Data;
import Utilities.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.TileState;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import tlldos.tll2.TLL2;

import static Utilities.Format.PREFIX;
import static Utilities.Format.format;

public class MaestriaExp implements Listener{
    TLL2 plugin;
    public MaestriaExp(TLL2 plugin){
        this.plugin = plugin;
    }

    static public String hp_plus = format("&3MAESTRIA &8> Tu Vida Maxima a aumentado!");
    static public String att_plus = format("&3MAESTRIA &8> Tu Daño Base a aumentado!");
    static public String def_plus = format("&3MAESTRIA &8> Tu Defensa Base a aumentado!");

    @EventHandler
    public void expLol(BlockBreakEvent e){
        var p = e.getPlayer();
        var block = e.getBlock();

        TileState a = (TileState)block.getState();
        BlockState c = (BlockState)block.getState();

        if (c.getMetadata(p.getName()).contains("no_exp")) {
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
            if(getMasteryLevel(p) == 1){
                p.sendMessage(hp_plus);
                p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() + 2);
            }else if(getMasteryLevel(p) == 2){
                p.sendMessage(att_plus);
                p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue() + 0.60);
            }else if(getMasteryLevel(p) == 3){
                p.sendMessage(def_plus);
                p.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(p.getAttribute(Attribute.GENERIC_ARMOR).getBaseValue() + 1);
            }else if(getMasteryLevel(p) == 4){
                p.sendMessage(hp_plus);
                p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() + 2);
            }
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

            //TileState data = (TileState)state;
            BlockState mecago = state;
            mecago.setMetadata("no_exp", new FixedMetadataValue(plugin,"true"));
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
