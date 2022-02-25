package Listeners;

import Extras.Items;
import Utilities.CustomEnchants;
import Utilities.Format;
import Utilities.NBTEditor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import tlldos.tll2.TLL2;

import java.util.Collection;

import static Utilities.Format.format;

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
                p.sendMessage(Format.PREFIX + format("&c¡Estas paniquiado!"));
            }else if(p.getPotionEffect(PotionEffectType.LUCK).getAmplifier() == 4){
                e.setCancelled(true);
                p.sendMessage(Format.PREFIX + format("&c¡Estas paniquiado!"));
            }
        }
    }

    @EventHandler
    public void miedodeMierdaLol(BlockBreakEvent e) {
        var p = e.getPlayer();
        if (p.hasPotionEffect(PotionEffectType.LUCK)) {
            if (p.getPotionEffect(PotionEffectType.LUCK).getAmplifier() == 3) {
                e.setCancelled(true);
                p.sendMessage(Format.PREFIX + format("&c¡Estas paniquiado!"));
            }else if(p.getPotionEffect(PotionEffectType.LUCK).getAmplifier() == 4){
                e.setCancelled(true);
                p.sendMessage(Format.PREFIX + format("&c¡Estas paniquiado!"));
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
                                p.sendMessage(format("&6&lEXO-SCAN: &7¡Tu inventario esta lleno!"));
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

        if(p.getInventory().getItemInMainHand() != null){
            if(p.getInventory().getItemInMainHand().hasItemMeta()){
                if(p.getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.TELEPHATY)) {
                    if (block.getType() == Material.CHEST || block.getType() == Material.FURNACE || block.getType() == Material.BLAST_FURNACE
                            || block.getType() == Material.SMOKER || block.getType() == Material.BARREL) return;
                    if (p.getInventory().firstEmpty() == -1) return;

                    e.setDropItems(false);
                    Collection<ItemStack> drop = block.getDrops(p.getInventory().getItemInMainHand());
                    if (drop.isEmpty()) return;
                    p.getInventory().addItem(drop.iterator().next());
                }else{
                    return;
                }
            }else{
                return;
            }
        }

        if(p.getInventory().getItemInMainHand() != null){
            Bukkit.getConsoleSender().sendMessage("Item no es Null");
            if(p.getInventory().getItemInMainHand().hasItemMeta()){
                Bukkit.getConsoleSender().sendMessage("Meta no es Null");
                if(p.getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.SMELTING_TOUCH)){
                    Bukkit.getConsoleSender().sendMessage("Tiene Smelting Touch");
                    Location location = block.getLocation();
                    if(block.getType() == Material.IRON_ORE || block.getType() == Material.DEEPSLATE_IRON_ORE){
                        Bukkit.getConsoleSender().sendMessage("Es un bloque valido");
                        e.setDropItems(false);
                        location.getWorld().dropItemNaturally(location,new ItemStack(Material.IRON_INGOT));
                    }else if(block.getType() == Material.GOLD_ORE || block.getType() == Material.DEEPSLATE_GOLD_ORE){
                        Bukkit.getConsoleSender().sendMessage("Es un bloque valido");
                        e.setDropItems(false);
                        location.getWorld().dropItemNaturally(location,new ItemStack(Material.GOLD_INGOT));
                    }else if(block.getType() == Material.ANCIENT_DEBRIS){
                        Bukkit.getConsoleSender().sendMessage("Es un bloque valido");
                        e.setDropItems(false);
                        location.getWorld().dropItemNaturally(location,new ItemStack(Material.NETHERITE_SCRAP));
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
