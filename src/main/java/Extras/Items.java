package Extras;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

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
        cpm.setCustomModelData(400);
        cp.setItemMeta(cpm);
        return cp;
    }
    public static ItemStack CloudMarsh() {
        List<String> cmlore = new ArrayList<String>();
        cmlore.add(ChatColor.GRAY + "Sientete en las Nubes...");
        cmlore.add(ChatColor.GOLD + "Otorga Levitacion, Slow Falling y Regeneracion");
        ItemStack cm = new ItemStack(Material.GLOW_BERRIES);
        ItemMeta cmm = cm.getItemMeta();
        cmm.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Cloudy Marshmallow");
        cmm.setCustomModelData(4001);
        cmm.setLore(cmlore);
        cm.setItemMeta(cmm);
        return cm;
    }
    public static ItemStack ToteBeserk(){
        List<String> tblore = new ArrayList<String>();
        tblore.add(ChatColor.GRAY + "Otorga Fuerza X y Speed V por");
        tblore.add(ChatColor.GRAY + "5 segundos!");
        tblore.add(ChatColor.RED + "Pone un Cooldown de Totem de 10 segundos");
        ItemStack tb = new ItemStack(Material.TOTEM_OF_UNDYING);
        ItemMeta tbm = tb.getItemMeta();
        tbm.setDisplayName(ChatColor.RED + "Totem Berserk");
        tbm.setCustomModelData(4002);
        tbm.setLore(tblore);
        tb.setItemMeta(tbm);
        return tb;
    }
    public static ItemStack BloodShard() {
        List<String> bslore = new ArrayList<String>();
        bslore.add(ChatColor.GRAY + "Un Sacrificio Justo.");
        ItemStack bs = new ItemStack(Material.IRON_NUGGET);
        ItemMeta bsm = bs.getItemMeta();
        bsm.setDisplayName(ChatColor.RED + "Blood Shard");
        bsm.setCustomModelData(4003);
        bsm.setLore(bslore);
        bs.setItemMeta(bsm);
        return bs;
    }
}
