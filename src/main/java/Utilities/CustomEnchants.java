package Utilities;

import net.minecraft.core.EnumAxisCycle;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static Utilities.Format.PREFIX;
import static Utilities.Format.format;

public class CustomEnchants implements Listener {
    public static final Enchantment TELEPHATY = new EnchantmentWrapper("telephaty", "telephaty", 1);
    public static final Enchantment REVENGE = new EnchantmentWrapper("revenge", "revenge", 1);
    public static final Enchantment SMELTING_TOUCH = new EnchantmentWrapper("smelting_touch", "smelting_touch", 1);
    public static final Enchantment LUCKY_TREE = new EnchantmentWrapper("luckytree", "luckytree", 1);
    public static final Enchantment CRITICAL_HIT = new EnchantmentWrapper("critical_hit", "critical_hit", 1);
    public static final Enchantment GRAVITY = new EnchantmentWrapper("gravity", "gravity", 1);
    public static final Enchantment IMPACT = new EnchantmentWrapper("impact", "impact", 1);
    public static final Enchantment BULLSEYE = new EnchantmentWrapper("bullseye", "bullseye", 1);
    public static final Enchantment SHRIEK = new EnchantmentWrapper("shriek","shriek",1);
    public static final Enchantment WITHER_JUSTICE = new EnchantmentWrapper("wither_justice","wither_justice",1);
    public static final Enchantment PYROMANIAC = new EnchantmentWrapper("pyromaniac","pyromaniac",1);
    public static final Enchantment TACKLE = new EnchantmentWrapper("tackle","tackle",1);
    public static final Enchantment HEAT_PROTECTION = new EnchantmentWrapper("heat_protection","heat_protection",1);
    public static final Enchantment ADAPTATIVE = new EnchantmentWrapper("adaptative","adaptative",1);
    public static final Enchantment INFERNO_POWER = new EnchantmentWrapper("inferno_power","inferno_power",1);
    public static final Enchantment EXPERIENCE = new EnchantmentWrapper("experience","experience",1);
    public static final Enchantment BRUTE_FORCE = new EnchantmentWrapper("brute_force","brute_force",1);
    public static final Enchantment HEALTHY_STEP = new EnchantmentWrapper("healthy_step","healthy_step",1);

    public static void register() {
        for(Enchantment enchantment : allCustomEnchants()){
            if(!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(enchantment)){
                registerEnchantment(enchantment);
            }
        }
    }

    public static List<Enchantment> allCustomEnchants() {
        List<Enchantment> list = new ArrayList<>();

        list.add(CustomEnchants.TELEPHATY);
        list.add(CustomEnchants.REVENGE);
        list.add(CustomEnchants.SMELTING_TOUCH);
        list.add(CustomEnchants.LUCKY_TREE);
        list.add(CustomEnchants.CRITICAL_HIT);
        list.add(CustomEnchants.GRAVITY);
        list.add(CustomEnchants.IMPACT);
        list.add(CustomEnchants.BULLSEYE);
        list.add(CustomEnchants.SHRIEK);
        list.add(CustomEnchants.WITHER_JUSTICE);
        list.add(CustomEnchants.PYROMANIAC);
        list.add(CustomEnchants.TACKLE);
        list.add(CustomEnchants.ADAPTATIVE);
        list.add(CustomEnchants.HEAT_PROTECTION);
        list.add(CustomEnchants.INFERNO_POWER);
        list.add(CustomEnchants.EXPERIENCE);
        list.add(CustomEnchants.BRUTE_FORCE);
        list.add(CustomEnchants.HEALTHY_STEP);



        return list;

    }

    public static void registerEnchantment(Enchantment enchantment) {
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
            Enchantment.registerEnchantment(enchantment);
        } catch (Exception e) {
            //LOL
            e.printStackTrace();
            Warn.Mutant(e);
        }
    }


    @EventHandler
    public void onAnvil(PrepareAnvilEvent event) {
        AnvilInventory anvil = event.getInventory();

        ItemStack firstItem = anvil.getFirstItem();
        ItemStack secondItem = anvil.getSecondItem();

        ItemStack resultItem = event.getResult();

        if (firstItem == null || secondItem == null) return;

        if (!firstItem.getItemMeta().hasLore() || !secondItem.getItemMeta().hasLore()) return;

        List<String> oneItemLore = firstItem.getItemMeta().getLore();
        List<String> twoItemLore = secondItem.getItemMeta().getLore();

        if (resultItem != null) {
            if (secondItem.hasItemMeta()) {

                boolean haveCustomEnc = false;

                for (String lore : twoItemLore) {
                    for (Enchantment current : allCustomEnchants()) {
                        if (lore.contains(current.getName())) {
                            haveCustomEnc = true;
                        }
                    }
                }

                if (haveCustomEnc) {
                    event.setResult(null);
                }
            }
            if (firstItem.hasItemMeta()) {

                boolean haveCustomEnc = false;

                for (String lore : oneItemLore) {
                    for (Enchantment actEnc : allCustomEnchants()) {
                        if (lore.contains(actEnc.getName())) {
                            haveCustomEnc = true;
                        }
                    }
                }

                if (haveCustomEnc) {
                    event.setResult(null);
                }
            }
        }
    }

    public boolean hasCustomEnchants(ItemStack item) {
        if (item.hasItemMeta() && Objects.requireNonNull(item.getItemMeta()).hasLore()) {
            ItemMeta meta = item.getItemMeta();
            for (String lore : Objects.requireNonNull(meta.getLore())) {
                if (lore.toLowerCase().contains("encantamiento ancestral")) {
                    return true;
                }
            }
        }
        return false;
    }

    @EventHandler
    public void applyEnchantsCustoms(PlayerInteractEvent event){
        var p = event.getPlayer();
        if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){
            if((p.getInventory().getItemInMainHand().getType().equals(Material.NETHERITE_PICKAXE) || p.getInventory().getItemInMainHand().getType().equals(Material.NETHERITE_AXE) || p.getInventory().getItemInMainHand().getType().equals(Material.NETHERITE_SHOVEL)) && (p.getInventory().getItemInOffHand().getType().equals(Material.ENCHANTED_BOOK) && p.getInventory().getItemInOffHand().getItemMeta().hasEnchant(CustomEnchants.TELEPHATY))){
                ItemStack pickaxe = p.getInventory().getItemInMainHand();

                if (hasCustomEnchants(pickaxe)) return;

                List<String> lore;

                if (pickaxe.getItemMeta().hasLore()) {
                    lore = pickaxe.getLore();
                } else {
                    lore = new ArrayList<>();
                }

                lore.add(format("&6Encantamiento Ancestral: &eTelephaty"));

                pickaxe.addUnsafeEnchantment(CustomEnchants.TELEPHATY, 1);
                pickaxe.setLore(lore);

                p.sendMessage(PREFIX,format("&7Has usado una Toma Ancestral"));

                p.playSound(p.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.BLOCKS,10.0F,-1.0F);
                p.getInventory().getItemInOffHand().setType(Material.AIR);
            }else if((p.getInventory().getItemInMainHand().getType().equals(Material.NETHERITE_PICKAXE) || p.getInventory().getItemInMainHand().getType().equals(Material.NETHERITE_SHOVEL)) && (p.getInventory().getItemInOffHand().getType().equals(Material.ENCHANTED_BOOK) && p.getInventory().getItemInOffHand().getItemMeta().hasEnchant(CustomEnchants.SMELTING_TOUCH))){
                ItemStack pickaxx = p.getInventory().getItemInMainHand();

                if (hasCustomEnchants(pickaxx)) return;

                List<String> lorez;
                if(pickaxx.getItemMeta().hasLore()){
                    lorez = pickaxx.getLore();
                }else{
                    lorez = new ArrayList<>();
                }
                lorez.add(format("&6Encantamiento Ancestral: &eSmelting Touch"));

                pickaxx.addUnsafeEnchantment(CustomEnchants.SMELTING_TOUCH, 1);
                pickaxx.setLore(lorez);

                p.sendMessage(PREFIX,format("&7Has usado una Toma Ancestral"));

                p.playSound(p.getLocation(),Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.BLOCKS,10.0F,-1.0F);
                p.getInventory().getItemInOffHand().setType(Material.AIR);
            }else if(p.getInventory().getItemInMainHand().getType().equals(Material.NETHERITE_SWORD)  && (p.getInventory().getItemInOffHand().getType().equals(Material.ENCHANTED_BOOK) && p.getInventory().getItemInOffHand().getItemMeta().hasEnchant(CustomEnchants.REVENGE))){
                ItemStack pickaxe3 = p.getInventory().getItemInMainHand();

                if (hasCustomEnchants(pickaxe3)) return;

                List<String> lorve;

                if(pickaxe3.getItemMeta().hasLore()){
                    lorve = pickaxe3.getLore();
                }else{
                    lorve  = new ArrayList<>();
                }

                lorve.add(format("&6Encantamiento Ancestral: &eRevenge"));

                pickaxe3.addUnsafeEnchantment(CustomEnchants.REVENGE, 1);
                pickaxe3.setLore(lorve);

                p.sendMessage(PREFIX,format("&7Has usado una Toma Ancestral"));

                p.playSound(p.getLocation(),Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.BLOCKS,10.0F,-1.0F);
                p.getInventory().getItemInOffHand().setType(Material.AIR);
            }else if(p.getInventory().getItemInMainHand().getType().equals(Material.NETHERITE_SWORD)  && (p.getInventory().getItemInOffHand().getType().equals(Material.ENCHANTED_BOOK) && p.getInventory().getItemInOffHand().getItemMeta().hasEnchant(CustomEnchants.SHRIEK))){
                ItemStack pickaxe3 = p.getInventory().getItemInMainHand();

                if (hasCustomEnchants(pickaxe3)) return;

                List<String> lorve;

                if(pickaxe3.getItemMeta().hasLore()){
                    lorve = pickaxe3.getLore();
                }else{
                    lorve  = new ArrayList<>();
                }

                lorve.add(format("&6Encantamiento Ancestral: &eShriek"));

                pickaxe3.addUnsafeEnchantment(CustomEnchants.SHRIEK, 1);
                pickaxe3.setLore(lorve);

                p.sendMessage(PREFIX,format("&7Has usado una Toma Ancestral"));

                p.playSound(p.getLocation(),Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.BLOCKS,10.0F,-1.0F);
                p.getInventory().getItemInOffHand().setType(Material.AIR);
            }else if(p.getInventory().getItemInMainHand().getType().equals(Material.NETHERITE_SWORD)  && (p.getInventory().getItemInOffHand().getType().equals(Material.ENCHANTED_BOOK) && p.getInventory().getItemInOffHand().getItemMeta().hasEnchant(CustomEnchants.CRITICAL_HIT))) {
                ItemStack pickax43 = p.getInventory().getItemInMainHand();

                if (hasCustomEnchants(pickax43)) return;

                List<String> lorvez;

                if (pickax43.getItemMeta().hasLore()) {
                    lorvez = pickax43.getLore();
                } else {
                    lorvez= new ArrayList<>();
                }

                lorvez.add(format("&6Encantamiento Ancestral: &eCritical Hit"));

                pickax43.addUnsafeEnchantment(CustomEnchants.CRITICAL_HIT, 1);
                pickax43.setLore(lorvez);

                p.sendMessage(PREFIX,format("&7Has usado una Toma Ancestral"));

                p.playSound(p.getLocation(),Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.BLOCKS,10.0F,-1.0F);
                p.getInventory().getItemInOffHand().setType(Material.AIR);
            } else if (p.getInventory().getItemInMainHand().getType().equals(Material.BOW) && (p.getInventory().getItemInOffHand().getType().equals(Material.ENCHANTED_BOOK) && p.getInventory().getItemInOffHand().getItemMeta().hasEnchant(CustomEnchants.BULLSEYE))) {
                List<String> lorvez;

                ItemStack pickax43 = p.getInventory().getItemInMainHand();

                if (hasCustomEnchants(pickax43)) return;

                if(pickax43.getItemMeta().hasLore()){
                    lorvez = pickax43.getLore();
                }else{
                    lorvez= new ArrayList<>();
                }
                lorvez.add(format("&6Encantamiento Ancestral: &eBullsEye"));

                pickax43.addUnsafeEnchantment(CustomEnchants.BULLSEYE, 1);
                pickax43.setLore(lorvez);

                p.sendMessage(PREFIX,format("&7Has usado una Toma Ancestral"));

                p.playSound(p.getLocation(),Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.BLOCKS,10.0F,-1.0F);
                p.getInventory().getItemInOffHand().setType(Material.AIR);
            }else if (p.getInventory().getItemInMainHand().getType().equals(Material.NETHERITE_CHESTPLATE) && (p.getInventory().getItemInOffHand().getType().equals(Material.ENCHANTED_BOOK) && p.getInventory().getItemInOffHand().getItemMeta().hasEnchant(CustomEnchants.WITHER_JUSTICE))) {
                List<String> lorvez;

                ItemStack pickax43 = p.getInventory().getItemInMainHand();

                if (hasCustomEnchants(pickax43)) return;

                if(pickax43.getItemMeta().hasLore()){
                    lorvez = pickax43.getLore();
                }else{
                    lorvez= new ArrayList<>();
                }
                lorvez.add(format("&6Encantamiento Ancestral: &eWhiter Justice"));

                pickax43.addUnsafeEnchantment(CustomEnchants.WITHER_JUSTICE, 1);
                pickax43.setLore(lorvez);

                p.sendMessage(PREFIX,format("&7Has usado una Toma Ancestral"));

                p.playSound(p.getLocation(),Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.BLOCKS,10.0F,-1.0F);
                p.getInventory().getItemInOffHand().setType(Material.AIR);
            }else if (p.getInventory().getItemInMainHand().getType().equals(Material.BOW) && (p.getInventory().getItemInOffHand().getType().equals(Material.ENCHANTED_BOOK) && p.getInventory().getItemInOffHand().getItemMeta().hasEnchant(CustomEnchants.PYROMANIAC))) {
                List<String> lorvez;

                ItemStack pickax43 = p.getInventory().getItemInMainHand();

                if (hasCustomEnchants(pickax43)) return;

                if(pickax43.getItemMeta().hasLore()){
                    lorvez = pickax43.getLore();
                }else{
                    lorvez= new ArrayList<>();
                }
                lorvez.add(format("&6Encantamiento Ancestral: &ePyromaniac"));

                pickax43.addUnsafeEnchantment(CustomEnchants.PYROMANIAC, 1);
                pickax43.setLore(lorvez);

                p.sendMessage(PREFIX,format("&7Has usado una Toma Ancestral"));

                p.playSound(p.getLocation(),Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.BLOCKS,10.0F,-1.0F);
                p.getInventory().getItemInOffHand().setType(Material.AIR);
            }else if (p.getInventory().getItemInMainHand().getType().equals(Material.SHIELD) && (p.getInventory().getItemInOffHand().getType().equals(Material.ENCHANTED_BOOK) && p.getInventory().getItemInOffHand().getItemMeta().hasEnchant(CustomEnchants.TACKLE))) {
                List<String> lorvez;

                ItemStack pickax43 = p.getInventory().getItemInMainHand();

                if (hasCustomEnchants(pickax43)) return;

                if(pickax43.getItemMeta().hasLore()){
                    lorvez = pickax43.getLore();
                }else{
                    lorvez= new ArrayList<>();
                }
                lorvez.add(format("&6Encantamiento Ancestral: &eTackle"));

                pickax43.addUnsafeEnchantment(CustomEnchants.TACKLE, 1);
                pickax43.setLore(lorvez);

                p.sendMessage(PREFIX,format("&7Has usado una Toma Ancestral"));

                p.playSound(p.getLocation(),Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.BLOCKS,10.0F,-1.0F);
                p.getInventory().getItemInOffHand().setType(Material.AIR);
            }else if (p.getInventory().getItemInMainHand().getType().equals(Material.NETHERITE_HELMET) && (p.getInventory().getItemInOffHand().getType().equals(Material.ENCHANTED_BOOK) && p.getInventory().getItemInOffHand().getItemMeta().hasEnchant(CustomEnchants.ADAPTATIVE))) {
                List<String> lorvez;

                ItemStack pickax43 = p.getInventory().getItemInMainHand();

                if (hasCustomEnchants(pickax43)) return;

                if(pickax43.getItemMeta().hasLore()){
                    lorvez = pickax43.getLore();
                }else{
                    lorvez= new ArrayList<>();
                }
                lorvez.add(format("&6Encantamiento Ancestral: &eAdaptative"));

                pickax43.addUnsafeEnchantment(CustomEnchants.ADAPTATIVE, 1);
                pickax43.setLore(lorvez);

                p.sendMessage(PREFIX,format("&7Has usado una Toma Ancestral"));

                p.playSound(p.getLocation(),Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.BLOCKS,10.0F,-1.0F);
                p.getInventory().getItemInOffHand().setType(Material.AIR);
            }else if (p.getInventory().getItemInMainHand().getType().equals(Material.NETHERITE_LEGGINGS) && (p.getInventory().getItemInOffHand().getType().equals(Material.ENCHANTED_BOOK) && p.getInventory().getItemInOffHand().getItemMeta().hasEnchant(CustomEnchants.HEAT_PROTECTION))) {
                List<String> lorvez;

                ItemStack pickax43 = p.getInventory().getItemInMainHand();

                if (hasCustomEnchants(pickax43)) return;

                if(pickax43.getItemMeta().hasLore()){
                    lorvez = pickax43.getLore();
                }else{
                    lorvez= new ArrayList<>();
                }
                lorvez.add(format("&6Encantamiento Ancestral: &eHeat Protection"));

                pickax43.addUnsafeEnchantment(CustomEnchants.HEAT_PROTECTION, 1);
                pickax43.setLore(lorvez);

                p.sendMessage(PREFIX,format("&7Has usado una Toma Ancestral"));

                p.playSound(p.getLocation(),Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.BLOCKS,10.0F,-1.0F);
                p.getInventory().getItemInOffHand().setType(Material.AIR);
            }else if (p.getInventory().getItemInMainHand().getType().equals(Material.BLAZE_ROD) && (p.getInventory().getItemInOffHand().getType().equals(Material.ENCHANTED_BOOK) && p.getInventory().getItemInOffHand().getItemMeta().hasEnchant(CustomEnchants.INFERNO_POWER))) {
                List<String> lorvez;

                ItemStack pickax43 = p.getInventory().getItemInMainHand();

                if (hasCustomEnchants(pickax43)) return;

                if(pickax43.getItemMeta().hasLore()){
                    lorvez = pickax43.getLore();
                }else{
                    lorvez= new ArrayList<>();
                }
                lorvez.add(format("&6Encantamiento Ancestral: &eInferno Power"));

                pickax43.addUnsafeEnchantment(CustomEnchants.INFERNO_POWER, 1);
                pickax43.setLore(lorvez);

                p.sendMessage(PREFIX,format("&7Has usado una Toma Ancestral"));

                p.playSound(p.getLocation(),Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.BLOCKS,10.0F,-1.0F);
                p.getInventory().getItemInOffHand().setType(Material.AIR);
            }else if (p.getInventory().getItemInMainHand().getType().equals(Material.NETHERITE_BOOTS) && (p.getInventory().getItemInOffHand().getType().equals(Material.ENCHANTED_BOOK) && p.getInventory().getItemInOffHand().getItemMeta().hasEnchant(CustomEnchants.HEALTHY_STEP))) {
                List<String> lorvez;

                ItemStack pickax43 = p.getInventory().getItemInMainHand();

                if (hasCustomEnchants(pickax43)) return;

                if(pickax43.getItemMeta().hasLore()){
                    lorvez = pickax43.getLore();
                }else{
                    lorvez= new ArrayList<>();
                }
                lorvez.add(format("&6Encantamiento Ancestral: &eHealthy Step"));

                pickax43.addUnsafeEnchantment(CustomEnchants.HEALTHY_STEP, 1);
                pickax43.setLore(lorvez);

                p.sendMessage(PREFIX,format("&7Has usado una Toma Ancestral"));

                p.playSound(p.getLocation(),Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.BLOCKS,10.0F,-1.0F);
                p.getInventory().getItemInOffHand().setType(Material.AIR);
            }else if (p.getInventory().getItemInMainHand().getType().equals(Material.SHIELD) && (p.getInventory().getItemInOffHand().getType().equals(Material.ENCHANTED_BOOK) && p.getInventory().getItemInOffHand().getItemMeta().hasEnchant(CustomEnchants.BRUTE_FORCE))) {
                List<String> lorvez;

                ItemStack pickax43 = p.getInventory().getItemInMainHand();

                if (hasCustomEnchants(pickax43)) return;

                if(pickax43.getItemMeta().hasLore()){
                    lorvez = pickax43.getLore();
                }else{
                    lorvez= new ArrayList<>();
                }
                lorvez.add(format("&6Encantamiento Ancestral: &eBrute Force"));

                pickax43.addUnsafeEnchantment(CustomEnchants.BRUTE_FORCE, 1);
                pickax43.setLore(lorvez);

                p.sendMessage(PREFIX,format("&7Has usado una Toma Ancestral"));

                p.playSound(p.getLocation(),Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.BLOCKS,10.0F,-1.0F);
                p.getInventory().getItemInOffHand().setType(Material.AIR);
            }
        }
    }

}
