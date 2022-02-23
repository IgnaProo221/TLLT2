package Utilities;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CustomEnchants implements Listener {
    public static final Enchantment TELEPHATY = new EnchantmentWrapper("telephaty","telephaty", 1);
    public static final Enchantment REVENGE = new EnchantmentWrapper("revenge","revenge",1);
    public static final Enchantment SMELTING_TOUCH = new EnchantmentWrapper("smelting_touch","smelting_touch",1);
    public static final Enchantment LUCKY_TREE = new EnchantmentWrapper("luckytree","luckytree",1);
    public static final Enchantment CRITICAL_HIT = new EnchantmentWrapper("critical_hit","critical_hit",1);
    public static final Enchantment HUNTER = new EnchantmentWrapper("hunter","hunter",1);
    public static final Enchantment ECHO = new EnchantmentWrapper("echo","echo",1);
    public static final Enchantment LIFESTEAL = new EnchantmentWrapper("lifesteal","lifesteal",1);
    public static final Enchantment GRAVITY = new EnchantmentWrapper("gravity","gravity",1);
    public static final Enchantment IMPACT = new EnchantmentWrapper("impact","impact",1);
    public static final Enchantment BULLSEYE = new EnchantmentWrapper("bullseye","bullseye", 1);

    public static void register() {
        boolean registered = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(TELEPHATY);
        boolean getvenganza = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(REVENGE);
        boolean getsmelting = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(SMELTING_TOUCH);
        boolean getlucktree = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(LUCKY_TREE);
        boolean getcriticalhit = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(CRITICAL_HIT);
        boolean gethunter = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(HUNTER);
        boolean getgravity = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(GRAVITY);
        boolean getimpact = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(IMPACT);
        boolean getbullseye = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(BULLSEYE);
        if (!registered) {
            registerEnchantment(TELEPHATY);
        }
        if(!getvenganza){
            registerEnchantment(REVENGE);
        }
        if(!getsmelting){
            registerEnchantment(SMELTING_TOUCH);
        }
        if(!getlucktree){
            registerEnchantment(LUCKY_TREE);
        }
        if(!getcriticalhit){
            registerEnchantment(CRITICAL_HIT);
        }
        if(!gethunter){
            registerEnchantment(HUNTER);
        }
        if(!getgravity){
            registerEnchantment(GRAVITY);
        }
        if(!getimpact){
            registerEnchantment(IMPACT);
        }
        if(!getbullseye){
            registerEnchantment(BULLSEYE);
        }
    }

    public List<Enchantment> allCustomEnchants() {
        List<Enchantment> list = new ArrayList<>();

        list.add(CustomEnchants.TELEPHATY);
        list.add(CustomEnchants.REVENGE);
        list.add(CustomEnchants.SMELTING_TOUCH);
        list.add(CustomEnchants.LUCKY_TREE);
        list.add(CustomEnchants.CRITICAL_HIT);
        list.add(CustomEnchants.GRAVITY);
        list.add(CustomEnchants.IMPACT);
        list.add(CustomEnchants.BULLSEYE);

        return list;

    }

    public static void registerEnchantment(Enchantment enchantment){
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null,true);
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

}
