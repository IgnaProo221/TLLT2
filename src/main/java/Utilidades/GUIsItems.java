package Utilidades;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class GUIsItems {
    public static ItemStack glpa(){
        return new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
    }

    public static ItemStack infosacrifios(){
        List<String> sinfolore = new ArrayList<String>();
        sinfolore.add(ChatColor.GRAY + "Los Sacrificios se hacen con el Cuchillo Ceremonial");
        sinfolore.add(ChatColor.GRAY + "Cada Sacrificio otorga una recompensa Aleatoria y Bloodstone");
        sinfolore.add(ChatColor.GRAY + "Esto sera penalizado quitando 1 contenedor entero de vida");
        sinfolore.add(ChatColor.GRAY + "Tienes un Limite de quitar 5 corazones");
        sinfolore.add(ChatColor.GRAY + "Has Hecho " + Contador.ContadorS.contador + "de 5");
        ItemStack sinfo = new ItemStack(Material.RED_DYE);
        ItemMeta sinfom = sinfo.getItemMeta();
        sinfom.setDisplayName(ChatColor.RED + "Info Sacrificos");
        sinfom.setLore(sinfolore);
        return sinfo;
    }
}
