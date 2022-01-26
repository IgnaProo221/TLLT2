package Extras;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static Utilidades.Format.format;

public class Items {
    public static ItemStack FungaClu() {
        ItemStack fc = new ItemStack(Material.DRIED_KELP);
        ItemMeta fcm = fc.getItemMeta();
        fcm.setDisplayName(ChatColor.GRAY + "Fungal Clumps");
        fc.setItemMeta(fcm);
        return fc;
    }

    public static ItemStack CataclysPear() {
        ItemStack cp = new ItemStack(Material.ENDER_PEARL);
        ItemMeta cpm = cp.getItemMeta();
        cpm.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "Cataclysm Pearl");
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

    public static ItemStack ToteBeserk() {
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
        meta.setLore(lore);
        meta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setUnbreakable(true);
        frag.setItemMeta(meta);
        return frag;
    }

    public static ItemStack termometroItem() {
        ItemStack term = new ItemStack(Material.AMETHYST_SHARD);
        ItemMeta termmeta = term.getItemMeta();
        termmeta.setCustomModelData(4004);
        termmeta.setDisplayName(format("&6&lTermómetro"));
        termmeta.setUnbreakable(true);
        term.setItemMeta(termmeta);
        return term;
    }

    public static ItemStack totemRestorer() {
        ItemStack totres = new ItemStack(Material.PRISMARINE_CRYSTALS);
        ItemMeta totresmeta = totres.getItemMeta();
        totresmeta.setCustomModelData(4005);
        totresmeta.setDisplayName(format("&6&lTotem Restorer"));
        totresmeta.addEnchant(Enchantment.LUCK, 1, true);
        totresmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        totresmeta.setUnbreakable(true);
        totres.setItemMeta(totresmeta);
        return totres;
    }

    public static ItemStack fallenSword() {
        ItemStack falsw = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta falswmeta = falsw.getItemMeta();
        falswmeta.setUnbreakable(true);
        falswmeta.setCustomModelData(4006);
        falswmeta.setDisplayName(format("&c&lFallen Rage"));
        falswmeta.addEnchant(Enchantment.SWEEPING_EDGE, 3, true);
        List<String> lore = new ArrayList<>();
        lore.add(format("&6&lHABILIDAD: &eSubzero Edge"));
        lore.add(format("&7Otorga el Efecto de Congelacion a los"));
        lore.add(format("&7mobs afectados por su filo."));
        lore.add("");
        lore.add("");
        lore.add(format("&c&lEs Recomendable no usar Fire Aspect en esta Espada"));
        falswmeta.setLore(lore);
        falsw.setItemMeta(falswmeta);

        return falsw;
    }

    public static ItemStack bloodSaber(){
        ItemStack bloodsa = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta meta = bloodsa.getItemMeta();
        meta.setDisplayName(format("&c&lBloodstained Saber"));
        meta.setUnbreakable(true);
        meta.setCustomModelData(4010);
        List<String> lore = new ArrayList<>();
        lore.add(format(""));
        lore.add(format("&6&lHABILIDAD: &eLifesteal"));
        lore.add(format("&7La Espada tiene un Chance de robar vida al"));
        lore.add(format("&7golpear una Entidad"));
        lore.add(format(""));
        lore.add(format("&c¡Al Hacer esto la Espada se pondra en Cooldown!"));
        meta.setLore(lore);
        bloodsa.setItemMeta(meta);
        return bloodsa;
    }

    public static ItemStack crystalHeart(){
        ItemStack crysher = new ItemStack(Material.RED_DYE);
        ItemMeta meta = crysher.getItemMeta();
        meta.setCustomModelData(4069);
        meta.setDisplayName(format("&cCrystal Heart"));
        List<String> lore = new ArrayList<>();
        lore.add(format(""));
        lore.add(format("&7Rumores dicen que los Corazones de Cristal"));
        lore.add(format("&7son Objetos magicos capaz de volverte mas Resistente"));
        lore.add(format("&7pero tienen un Limite."));
        meta.setLore(lore);
        crysher.setItemMeta(meta);
        return crysher;
    }

    public static ItemStack varaDis(){
        ItemStack varadiscord = new ItemStack(Material.STICK);
        ItemMeta meta = varadiscord.getItemMeta();
        meta.setDisplayName(format("&d&lVara de la Discordia"));
        meta.setUnbreakable(true);
        meta.setCustomModelData(5002);
        List<String> lore = new ArrayList<>();
        lore.add(format(""));
        lore.add(format("&6&lHABILIDAD: Discordia Absoluta"));
        lore.add(format("&7Otorga Buffs a Jugadores Alrededor tuyo"));
        lore.add(format("&7(y a ti Mismo)"));
        lore.add(format(""));
        lore.add(format("&6&lBUFFS:"));
        lore.add(format("&7Fuerza III, Speed II, Regeneracion II, Resistencia I"));
        lore.add(format("&7Resistencia al Fuego y Health Boost II"));
        lore.add(format("&71 minuto"));
        lore.add(format(""));
        meta.setLore(lore);
        varadiscord.setItemMeta(meta);
        return varadiscord;
    }

}
