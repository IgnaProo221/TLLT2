package Extras;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;

public class Crafteos {
    public static ShapedRecipe craftDaga(){

        ShapedRecipe daga_craft = new ShapedRecipe(NamespacedKey.minecraft("daga_cer"),Items.createDaga());
        daga_craft.shape("FFF","FEF","FFF");
        daga_craft.setIngredient('F',Items.createFragmentoSangre(1));
        daga_craft.setIngredient('E',Material.IRON_SWORD);
        return daga_craft;
    }
    public static ShapedRecipe craftBloodsaber(){
        ShapedRecipe blood_saber_craft = new ShapedRecipe(NamespacedKey.minecraft("blood_saber"),Items.bloodSaber());
        blood_saber_craft.shape(" B ",
                                " E ",
                                " F ");
        blood_saber_craft.setIngredient('B',Items.bloodShard());
        blood_saber_craft.setIngredient('E',Material.NETHERITE_SWORD);
        blood_saber_craft.setIngredient('F',Items.createFragmentoSangre(24));
        return blood_saber_craft;
    }
}
