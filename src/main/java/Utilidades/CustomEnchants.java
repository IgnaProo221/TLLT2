package Utilidades;

import com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory;
import org.bukkit.enchantments.Enchantment;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

public class CustomEnchants{
    public static final Enchantment TELEPHATY = new EnchantmentWrapper("telephaty","telephaty", 1);
    public static void register(){
        boolean registered = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(TELEPHATY);
        if(!registered){
            registerEnchantment(TELEPHATY);
        }
    }
    public static void registerEnchantment(Enchantment enchantment){
        boolean registered = true;
        try{
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null,true);
            Enchantment.registerEnchantment(enchantment);
        }catch (Exception e){
            //LOL
            registered = false;
            e.printStackTrace();
            Warn.Mutant(e);
        }
    }
}
