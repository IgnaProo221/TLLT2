package Utilidades;

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

    public static void register() {
        boolean registered = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(TELEPHATY);
        if (!registered) {
            registerEnchantment(TELEPHATY);
        }
    }

    public List<Enchantment> allCustomEnchants() {
        List<Enchantment> list = new ArrayList<>();

        list.add(CustomEnchants.TELEPHATY);

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
