package Extras;

import Utilities.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static Utilities.Format.format;

public class MobDrops{
    public static ItemStack blighdrop1(int amount){
        ItemStack s = new ItemStack(Material.ROTTEN_FLESH);
        ItemMeta meta = s.getItemMeta();
        meta.setDisplayName(format("&cBlighted Flesh"));
        List<String> lore = new ArrayList<>();
        lore.add(format("&7Carne de una bestia mutada en un laboratorio secreto."));
        meta.setLore(lore);
        s.setItemMeta(meta);
        s.setAmount(amount);
        return s;
    }
    public static ItemStack blighdrop2(int amount){
        ItemStack s = new ItemStack(Material.BONE);
        ItemMeta meta = s.getItemMeta();
        meta.setDisplayName(format("&cBlighted Bone"));
        List<String> lore = new ArrayList<>();
        lore.add(format("&7Huesos de un terrorrifico viviente esqueletico."));
        meta.setLore(lore);
        s.setItemMeta(meta);
        s.setAmount(amount);
        return s;
    }
    public static ItemStack blighdrop3(int amount){
        ItemStack s = new ItemStack(Material.SPIDER_EYE);
        ItemMeta meta = s.getItemMeta();
        meta.setDisplayName(format("&cBlighted Eye"));
        List<String> lore = new ArrayList<>();
        lore.add(format("&7Ojos de una pesadilla arthropoda."));
        meta.setLore(lore);
        s.setItemMeta(meta);
        s.setAmount(amount);
        return s;
    }
    public static ItemStack blighdrop4(int amount){
        ItemStack s = new ItemStack(Material.GUNPOWDER);
        ItemMeta meta = s.getItemMeta();
        meta.setDisplayName(format("&cBlighted Powder"));
        List<String> lore = new ArrayList<>();
        lore.add(format("&7Pólvora de un monstruo nuclear."));
        meta.setLore(lore);
        s.setItemMeta(meta);
        s.setAmount(amount);
        return s;
    }
    public static ItemStack blighdrop5(int amount){
        ItemStack s = new ItemStack(Material.PHANTOM_MEMBRANE);
        ItemMeta meta = s.getItemMeta();
        meta.setDisplayName(format("&cBlighted Membrane"));
        List<String> lore = new ArrayList<>();
        lore.add(format("&7Membrana de un peligro volador."));
        meta.setLore(lore);
        s.setItemMeta(meta);
        s.setAmount(amount);
        return s;
    }
    public static ItemStack blighdrop6(int amount){
        ItemStack s = new ItemStack(Material.GLOWSTONE_DUST);
        ItemMeta meta = s.getItemMeta();
        meta.setDisplayName(format("&cBlighted Dust"));
        List<String> lore = new ArrayList<>();
        lore.add(format("&7Polvo de una bruja experta."));
        meta.setLore(lore);
        s.setItemMeta(meta);
        s.setAmount(amount);
        return s;
    }
    public static ItemStack blighdrop7(int amount){
        ItemStack s = new ItemStack(Material.GHAST_TEAR);
        ItemMeta meta = s.getItemMeta();
        meta.setDisplayName(format("&cBlighted Tear"));
        List<String> lore = new ArrayList<>();
        lore.add(format("&7Lagrimas de un fantasma explosivo."));
        meta.setLore(lore);
        s.setItemMeta(meta);
        s.setAmount(amount);
        return s;
    }
    public static ItemStack blighdrop8(int amount){
        ItemStack s = new ItemStack(Material.PORKCHOP);
        ItemMeta meta = s.getItemMeta();
        meta.setDisplayName(format("&cBlighted Porkchop"));
        List<String> lore = new ArrayList<>();
        lore.add(format("&7Chuleta de un cerdo bastante... ¿Sobrenatural?"));
        meta.setLore(lore);
        s.setItemMeta(meta);
        s.setAmount(amount);
        return s;
    }
    public static ItemStack blighdrop9(int amount){
        ItemStack s = new ItemStack(Material.ENDER_PEARL);
        ItemMeta meta = s.getItemMeta();
        meta.setDisplayName(format("&cBlighted Pearl"));
        List<String> lore = new ArrayList<>();
        lore.add(format("&7Perla de un ente misterioso"));
        lore.add(format(""));
        lore.add(format("&cEsta Perla te otorgara Speed I al caer."));
        meta.setLore(lore);
        s.setItemMeta(meta);
        s.setAmount(amount);
        return s;
    }
    public static ItemStack terrorEssence(int amount){
        ItemStack s = new ItemStack(Material.PRISMARINE_CRYSTALS);
        ItemMeta smeta = s.getItemMeta();
        smeta.setDisplayName(format("&6&lEsencia del Terror"));
        List<String> lore = new ArrayList<>();
        lore.add(format(""));
        lore.add(format("&7Esencia provenientes de criaturas"));
        lore.add(format("&7mutadas, emana un poder extraño"));
        lore.add(format(""));
        smeta.setLore(lore);
        s.setItemMeta(smeta);
        s.setAmount(amount);
        return s;
    }
    public static ItemStack phantomHeart(){
        return new ItemBuilder(Material.GOLDEN_APPLE).setName(format("&d&lPhantom Heart")).setUnbreakable(true).build();
    }
}
