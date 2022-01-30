package Extras;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static Utilidades.Format.format;

public class MobDrops{
    public static ItemStack blighdrop1(){
        ItemStack s = new ItemStack(Material.ROTTEN_FLESH);
        ItemMeta meta = s.getItemMeta();
        meta.setDisplayName(format("&cBlighted Flesh"));
        List<String> lore = new ArrayList<>();
        lore.add(format("&7Carne de una bestia mutada"));
        meta.setLore(lore);
        s.setItemMeta(meta);
        return s;
    }
    public static ItemStack blighdrop2(){
        ItemStack s = new ItemStack(Material.BONE);
        ItemMeta meta = s.getItemMeta();
        meta.setDisplayName(format("&cBlighted Bone"));
        List<String> lore = new ArrayList<>();
        lore.add(format("&7Huesos de un terror viviente"));
        meta.setLore(lore);
        s.setItemMeta(meta);
        return s;
    }
    public static ItemStack blighdrop3(){
        ItemStack s = new ItemStack(Material.SPIDER_EYE);
        ItemMeta meta = s.getItemMeta();
        meta.setDisplayName(format("&cBlighted Eye"));
        List<String> lore = new ArrayList<>();
        lore.add(format("&7Ojo de una pesadilla artrópoda"));
        meta.setLore(lore);
        s.setItemMeta(meta);
        return s;
    }
    public static ItemStack blighdrop4(){
        ItemStack s = new ItemStack(Material.GUNPOWDER);
        ItemMeta meta = s.getItemMeta();
        meta.setDisplayName(format("&cBlighted Powder"));
        List<String> lore = new ArrayList<>();
        lore.add(format("&7Pólvora de un monstruo nuclear"));
        meta.setLore(lore);
        s.setItemMeta(meta);
        return s;
    }
    public static ItemStack blighdrop5(){
        ItemStack s = new ItemStack(Material.PHANTOM_MEMBRANE);
        ItemMeta meta = s.getItemMeta();
        meta.setDisplayName(format("&cBlighted Membrane"));
        List<String> lore = new ArrayList<>();
        lore.add(format("&7Membrana de un peligro volador"));
        meta.setLore(lore);
        s.setItemMeta(meta);
        return s;
    }
    public static ItemStack blighdrop6(){
        ItemStack s = new ItemStack(Material.GLOWSTONE_DUST);
        ItemMeta meta = s.getItemMeta();
        meta.setDisplayName(format("&cBlighted Dust"));
        List<String> lore = new ArrayList<>();
        lore.add(format("&7Polvo de una Bruja Experta"));
        meta.setLore(lore);
        s.setItemMeta(meta);
        return s;
    }
    public static ItemStack blighdrop7(){
        ItemStack s = new ItemStack(Material.GHAST_TEAR);
        ItemMeta meta = s.getItemMeta();
        meta.setDisplayName(format("&cBlighted Tear"));
        List<String> lore = new ArrayList<>();
        lore.add(format("&7Lagrimas de un fantasma explosivo"));
        meta.setLore(lore);
        s.setItemMeta(meta);
        return s;
    }
    public static ItemStack blighdrop8(){
        ItemStack s = new ItemStack(Material.PORKCHOP);
        ItemMeta meta = s.getItemMeta();
        meta.setDisplayName(format("&cBlighted Porkchop"));
        List<String> lore = new ArrayList<>();
        lore.add(format("&7Chuleta de un cerdo bastante raro"));
        meta.setLore(lore);
        s.setItemMeta(meta);
        return s;
    }
    public static ItemStack blighdrop9(){
        ItemStack s = new ItemStack(Material.ENDER_PEARL);
        ItemMeta meta = s.getItemMeta();
        meta.setDisplayName(format("&cBlighted Pearl"));
        List<String> lore = new ArrayList<>();
        lore.add(format("&7Perla de un ente misterioso"));
        lore.add(format(""));
        lore.add(format("&cEsta Perla otorga Speed I al Caer"));
        meta.setLore(lore);
        s.setItemMeta(meta);
        return s;
    }
}
