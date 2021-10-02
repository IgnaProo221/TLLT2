package Extras;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Items{
    public static ItemStack FungaClu(){
        ItemStack fc = new ItemStack(Material.DRIED_KELP);
        ItemMeta fcm = fc.getItemMeta();
        fcm.setDisplayName(ChatColor.GRAY + "Fungal Clumps");
        fc.setItemMeta(fcm);
        return fc;
    }

    public static ItemStack CataclysPear(){
        ItemStack cp = new ItemStack(Material.ENDER_PEARL);
        ItemMeta cpm = cp.getItemMeta();
        cpm.setDisplayName(ChatColor.GRAY + "" +ChatColor.BOLD + "Cataclysm Pearl");
        cp.setItemMeta(cpm);
        return cp;
    }
}
