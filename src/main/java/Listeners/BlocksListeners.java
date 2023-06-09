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
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import tlldos.tll2.TLL2;

import java.util.Collection;
import java.util.Random;

import static Utilities.Format.PREFIX;
import static Utilities.Format.format;

public class BlocksListeners implements Listener{
    private TLL2 plugin;
    public BlocksListeners(TLL2 plugin){
        this.plugin = plugin;
    }

    //TODO Soy un inutil
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
            if (p.getPotionEffect(PotionEffectType.LUCK).getAmplifier() >= 2) {
                e.setCancelled(true);
                p.sendMessage(Format.PREFIX + format("&c¡Estas paniquiado!"));
            }
        }
    }

    @EventHandler
    public void miedodeMierdaLol(BlockBreakEvent e) {
        var p = e.getPlayer();
        if (p.hasPotionEffect(PotionEffectType.LUCK)) {
            if (p.getPotionEffect(PotionEffectType.LUCK).getAmplifier() >= 2) {
                e.setCancelled(true);
                p.sendMessage(Format.PREFIX + format("&c¡Estas paniquiado!"));
            }
        }
    }


    @EventHandler
    public void picoHabilidad(BlockBreakEvent e){
        var p = e.getPlayer();
        var block = e.getBlock();

        /* if(NBTEditor.contains(p.getInventory().getItemInMainHand(),1,"holafelipe")){
            p.sendMessage("debUG LOL");
        } */

        if(p.getWorld().isThundering()){
            if (block.getType().name().toLowerCase().contains("ore")){
                int chancedenodrop = new Random().nextInt(100);
                if(chancedenodrop > 80){
                    e.setDropItems(false);
                }
            }
        }

        if(p.getInventory().getItemInMainHand().hasItemMeta()){
            if(p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()){
                if(p.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 236){
                    if(block.getType() == Material.CARROTS || block.getType() == Material.POTATOES || block.getType() == Material.BEETROOTS){
                        int chancelol = new Random().nextInt(100);
                        if(chancelol > 80){
                            e.setDropItems(false);
                            e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(),new ItemStack(Material.ENCHANTED_GOLDEN_APPLE));
                        }
                    }
                }
            }
        }

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
                    }
                }
            }
        }
        if(p.getInventory().getItemInMainHand().hasItemMeta()){
            if(p.getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.EXPERIENCE)){
                e.setExpToDrop(e.getExpToDrop() * 2);
            }
        }

        if(p.getInventory().getItemInMainHand().hasItemMeta()){
            if(p.getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.LUCKY_TREE)){
                if(e.getBlock().getType().toString().contains("_log")){
                    int chance = new Random().nextInt(100);
                    if(chance > 80){
                        p.sendMessage(PREFIX,format("&6Has encontrado Manzanas Doradas!"));
                        p.getLocation().getWorld().dropItemNaturally(p.getLocation(),new ItemStack(Material.GOLDEN_APPLE, 5));
                    }
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
                }
            }
        }

        if(p.getInventory().getItemInMainHand() != null){
            if(p.getInventory().getItemInMainHand().hasItemMeta()){
                if(p.getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.SMELTING_TOUCH)){
                    Location location = block.getLocation();
                    if(block.getType() == Material.IRON_ORE || block.getType() == Material.DEEPSLATE_IRON_ORE){
                        e.setDropItems(false);
                        location.getWorld().dropItemNaturally(location,new ItemStack(Material.IRON_INGOT));
                    }else if(block.getType() == Material.GOLD_ORE || block.getType() == Material.DEEPSLATE_GOLD_ORE){
                        e.setDropItems(false);
                        location.getWorld().dropItemNaturally(location,new ItemStack(Material.GOLD_INGOT));
                    }else if(block.getType() == Material.ANCIENT_DEBRIS){
                        e.setDropItems(false);
                        location.getWorld().dropItemNaturally(location,new ItemStack(Material.NETHERITE_SCRAP));
                    }else if(block.getType() == Material.COPPER_ORE || block.getType() == Material.DEEPSLATE_COPPER_ORE){
                        e.setDropItems(false);
                        location.getWorld().dropItemNaturally(location,new ItemStack(Material.COPPER_INGOT));
                    }else if(block.getType() == Material.SAND){
                        e.setDropItems(false);
                        location.getWorld().dropItemNaturally(location,new ItemStack(Material.GLASS));
                    }
                }
            }
        }
    }

}
