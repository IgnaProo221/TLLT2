package Extras;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static Utilidades.Format.format;

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
        bsm.setDisplayName(ChatColor.RED + "Blood Stone");
        bsm.setCustomModelData(4003);
        bsm.setLore(bslore);
        bs.setItemMeta(bsm);
        return bs;
    }

    public static ItemStack CrystalApple() {
        List<String> bslore = new ArrayList<String>();
        bslore.add(ChatColor.GRAY + "Regeneracion III y Resistencia");
        bslore.add(ChatColor.GRAY + "Pero tu habilidad de Minar y Golpear");
        bslore.add(ChatColor.GRAY + "estan Debilitadas");
        ItemStack bs = new ItemStack(Material.IRON_NUGGET);
        ItemMeta bsm = bs.getItemMeta();
        bsm.setDisplayName(ChatColor.GRAY + "Crystal Apple");
        bsm.setCustomModelData(4003);
        bsm.setLore(bslore);
        bs.setItemMeta(bsm);
        return bs;
    }

    public static ItemStack createDaga() {
        ItemStack dag = new ItemStack(Material.IRON_SWORD);
        ItemMeta m = dag.getItemMeta();
        m.setDisplayName(format("&7Daga Ceremonial"));
        List<String> lore = new ArrayList<>();
        lore.add(format("&7Has &eClick Derecho &7para iniciar el sacrificio."));
        lore.add(format("&7Utiliza &8/thelastlife sacrificio &7para ver"));
        lore.add(format("&7mas información."));
        lore.add("");
        lore.add(format("&7Nivel: &bRaro&7."));
        m.setLore(lore);
        dag.setItemMeta(m);
        return dag;
    }

    public static ItemStack createFragmentoSangre(int size) {
        ItemStack frag = new ItemStack(Material.RED_DYE, size);
        ItemMeta meta = frag.getItemMeta();
        Objects.requireNonNull(meta).setDisplayName(format("&cFragmento de Sangre"));
        List<String> lore = new ArrayList<>();
        lore.add(format("&7El &cFragmento de sangre &7se puede"));
        lore.add(format("&7usar crafteando la &cDaga Ceremonial&7."));
        lore.add("");
        lore.add(format("&7Nivel: &eComún&7."));
        meta.setLore(lore);
        meta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setUnbreakable(true);
        frag.setItemMeta(meta);
        return frag;
    }
}
