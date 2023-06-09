package Extras;

import Utilities.ItemBuilder;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.awt.*;
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
    public static ItemStack exoCore(int amount){
        return new ItemBuilder(Material.PRISMARINE_SHARD,amount).setName(format("&6&lExo-Core")).setUnbreakable(true).build();
    }
    public static ItemStack blackRose(){
        return new ItemBuilder(Material.BLACK_DYE).setName(format("&8&lBlack Rose")).setUnbreakable(true).build();
    }
    public static ItemStack darkstaff(){
        return new ItemBuilder(Material.BLAZE_ROD).setName(format("&7&lDark Staff")).setUnbreakable(true).build();
    }
    public static ItemStack hellfireRod(){
        return new ItemBuilder(Material.BLAZE_ROD).setName(format("&6&lHellfire Rod")).setCustomModelData(108239139).build();
    }
    public static ItemStack infernalbloom(){
        return new ItemBuilder(Material.BLAZE_POWDER).setName(format("&4&lInfernobloom")).setCustomModelData(120312).build();
    }
    public static ItemStack darknessEssence(int amount){
        List<net.kyori.adventure.text.Component> lore = new ArrayList<>();
        lore.add(Component.text(format("&7Esencia de la oscuridad, proveniente")));
        lore.add(Component.text(format("&7de las Sombras desconocidas que rondan")));
        lore.add(Component.text(format("&7este mundo")));
        return new ItemBuilder(Material.FEATHER, amount).setName(format("&8&lEsencia de Oscuridad")).setLore(lore).setCustomModelData(988311).build();
    }
}
