package Eventos;

import Extras.Items;
import Utilidades.Format;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
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
    public void bambuInutil(BlockBreakEvent e){
        if(e.getBlock().getType() == Material.BAMBOO || e.getBlock().getType() == Material.BAMBOO_SAPLING){
            e.getBlock().getLocation().createExplosion(10,true,true);
        }
    }

    @EventHandler
    public void miedoXd(BlockPlaceEvent e){
        var p = e.getPlayer();
        if (p.hasPotionEffect(PotionEffectType.LUCK)) {
            if (p.getPotionEffect(PotionEffectType.LUCK).getAmplifier() == 3) {
                e.setCancelled(true);
                p.sendMessage(Format.PREFIX + format("&cEstas Paniqueando!"));
            }else if(p.getPotionEffect(PotionEffectType.LUCK).getAmplifier() == 4){
                e.setCancelled(true);
                p.sendMessage(Format.PREFIX + format("&cEstas Paniqueando!"));
            }
        }
    }

    @EventHandler
    public void miedodeMierdaLol(BlockBreakEvent e) {
        var p = e.getPlayer();
        if (p.hasPotionEffect(PotionEffectType.LUCK)) {
            if (p.getPotionEffect(PotionEffectType.LUCK).getAmplifier() == 3) {
                e.setCancelled(true);
                p.sendMessage(Format.PREFIX + format("&cEstas Paniqueando!"));
            }else if(p.getPotionEffect(PotionEffectType.LUCK).getAmplifier() == 4){
                e.setCancelled(true);
                p.sendMessage(Format.PREFIX + format("&cEstas Paniqueando!"));
            }
        }
    }

    @EventHandler
    public void picoHabilidad(BlockBreakEvent e){
        var p = e.getPlayer();
        var block = e.getBlock();
        if(block.getType() == Material.DEEPSLATE_DIAMOND_ORE){
            if(p.getInventory().getItemInMainHand() != null){
                if(p.getInventory().getItemInMainHand().hasItemMeta()){
                    if(p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()){
                        if(p.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 6660){
                            if(p.getInventory().firstEmpty() == -1){
                                p.sendMessage(format("&6&lEXO-SCAN: &7¡Tu Inventario esta Lleno!"));
                            }else{
                                p.getInventory().addItem(Items.rareGem());
                            }
                        }
                    }else{
                        return;
                    }
                }else{
                    return;
                }
            }
        }
    }

}
