package Utilidades;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemBuilder {

    private final ItemStack itemStack;

    public ItemBuilder(Material material, int amount){
        itemStack = new ItemStack(material, amount);
    }

    public ItemBuilder(Material material){
        this(material, 1);
    }

    public ItemBuilder setName(String name){
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(Component.text(name));
        itemStack.setItemMeta(itemMeta);

        return this;
    }

    public ItemBuilder setLore(List<Component> list){
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.lore(list);
        itemStack.setItemMeta(itemMeta);

        return this;
    }

    public ItemBuilder setCustomModelData(int modelData){
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setCustomModelData(modelData);
        itemStack.setItemMeta(itemMeta);

        return this;
    }

    public ItemBuilder setUnbreakable(boolean unbreakable){
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setUnbreakable(unbreakable);
        itemStack.setItemMeta(itemMeta);

        return this;
    }

    public ItemBuilder setItemFlags(ItemFlag... itemFlags){
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.addItemFlags(itemFlags);
        itemStack.setItemMeta(itemMeta);

        return this;
    }

    public ItemBuilder addEnchantment(Enchantment enchantment, int level){
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.addEnchant(enchantment, level, true);
        itemStack.setItemMeta(itemMeta);

        return this;
    }

    public ItemStack build(){
        return itemStack;
    }
}
