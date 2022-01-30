package Extras;

import Utilidades.Format;
import Utilidades.ItemBuilder;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static Utilidades.Format.format;

public class Items {
    public static ItemStack fungalClumps() {
        return new ItemBuilder(Material.DRIED_KELP)
                .setName(format("&7Fungal Clumps"))
                .build();
    }

    public static ItemStack cataclysmPearl() {
        return new ItemBuilder(Material.ENDER_PEARL)
                .setName(format("&7&lCataclysm Pearl"))
                .setCustomModelData(400)
                .build();
    }

    public static ItemStack cloudMarsh() {
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(ChatColor.GRAY + "Sientete en las Nubes..."));
        lore.add(Component.text(ChatColor.GOLD + "Otorga Levitacion, Slow Falling y Regeneracion"));

        return new ItemBuilder(Material.GLOW_BERRIES)
                .setName(Format.format("&f&lCloudy Marshmallow"))
                .setLore(lore)
                .setCustomModelData(4001)
                .build();
    }

    public static ItemStack totemBerserk() {
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(ChatColor.GRAY + "Otorga Fuerza X y Speed V por"));
        lore.add(Component.text(ChatColor.GRAY + "5 segundos!"));
        lore.add(Component.text(ChatColor.RED + "Pone un Cooldown de Totem de 10 segundos"));

        return new ItemBuilder(Material.TOTEM_OF_UNDYING)
                .setName(ChatColor.RED + "Totem Berserk")
                .setCustomModelData(4002)
                .setLore(lore)
                .build();
    }

    public static ItemStack bloodShard() {
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(ChatColor.GRAY + "Un Sacrificio Justo."));

        return new ItemBuilder(Material.IRON_NUGGET)
                .setName(ChatColor.RED + "Blood Stone")
                .setLore(lore)
                .setCustomModelData(4003)
                .build();
    }

    public static ItemStack crystalApple() {
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(ChatColor.GRAY + "Regeneracion III y Resistencia"));
        lore.add(Component.text(ChatColor.GRAY + "Pero tu habilidad de Minar y Golpear"));
        lore.add(Component.text(ChatColor.GRAY + "Pero tu habilidad de Minar y Golpear"));

        return new ItemBuilder(Material.GOLDEN_APPLE)
                .setName(ChatColor.GRAY + "Crystal Apple")
                .setLore(lore)
                .setCustomModelData(4003) // No se cual es el custom model data de la manzanita
                .build();
    }

    public static ItemStack createDaga() {
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("&7Has &eClick Derecho &7para iniciar el sacrificio.")));
        lore.add(Component.text(format("&7Utiliza &8/thelastlife sacrificio &7para ver")));
        lore.add(Component.text(format("&7mas información.")));

        return new ItemBuilder(Material.IRON_SWORD)
                .setName("&7Daga Ceremonial")
                .setLore(lore)
                .build();
    }

    public static ItemStack createFragmentoSangre(int size) {
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("&7El &cFragmento de sangre &7se puede")));
        lore.add(Component.text(format("&7usar crafteando la &cDaga Ceremonial&7.")));

        return new ItemBuilder(Material.RED_DYE, size)
                .setName(format("&cFragmento de Sangre"))
                .setLore(lore)
                .addEnchantment(Enchantment.ARROW_INFINITE, 1)
                .setItemFlags(ItemFlag.HIDE_ENCHANTS)
                .setUnbreakable(true)
                .build();
    }

    public static ItemStack termometroItem() {
        return new ItemBuilder(Material.AMETHYST_SHARD)
                .setName(format("&6&lTermómetro"))
                .setCustomModelData(4004)
                .setUnbreakable(true)
                .build();
    }

    public static ItemStack totemRestorer() {
        return new ItemBuilder(Material.PRISMARINE_CRYSTALS)
                .setName(format("&6&lTotem Restorer"))
                .setCustomModelData(4005)
                .addEnchantment(Enchantment.LUCK, 1)
                .setItemFlags(ItemFlag.HIDE_ENCHANTS)
                .setUnbreakable(true)
                .build();
    }

    public static ItemStack fallenSword() {
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("&6&lHABILIDAD: &eSubzero Edge")));
        lore.add(Component.text(format("&7Otorga el Efecto de Congelacion a los")));
        lore.add(Component.text(format("&7mobs afectados por su filo.")));
        lore.add(Component.text(""));
        lore.add(Component.text(""));
        lore.add(Component.text(format("&c&lEs Recomendable no usar Fire Aspect en esta Espada")));

        return new ItemBuilder(Material.NETHERITE_SWORD)
                .setName(format("&c&lFallen Rage"))
                .setCustomModelData(4006)
                .setLore(lore)
                .addEnchantment(Enchantment.SWEEPING_EDGE, 3)
                .setUnbreakable(true)
                .build();
    }

    public static ItemStack bloodSaber(){
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("")));
        lore.add(Component.text(format("&6&lHABILIDAD: &eLifesteal")));
        lore.add(Component.text(format("&7La Espada tiene un Chance de robar vida al")));
        lore.add(Component.text(format("&7golpear una Entidad")));
        lore.add(Component.text(format("")));
        lore.add(Component.text(format("&c¡Al Hacer esto la Espada se pondra en Cooldown!")));

        return new ItemBuilder(Material.NETHERITE_SWORD)
                .setName(format("&c&lBloodstained Saber"))
                .setUnbreakable(true)
                .setCustomModelData(4010)
                .setLore(lore)
                .build();
    }

    public static ItemStack crystalHeart(){
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("")));
        lore.add(Component.text(format("&7Rumores dicen que los Corazones de Cristal")));
        lore.add(Component.text(format("&7son Objetos magicos capaz de volverte mas Resistente")));
        lore.add(Component.text(format("&7pero tienen un Limite.")));

        return new ItemBuilder(Material.RED_DYE)
                .setName(format("&cCrystal Heart"))
                .setCustomModelData(4069)
                .setLore(lore)
                .build();
    }

    public static ItemStack varaDis(){
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("")));
        lore.add(Component.text(format("&6&lHABILIDAD: Discordia Absoluta")));
        lore.add(Component.text(format("&7Otorga Buffs a Jugadores Alrededor tuyo")));
        lore.add(Component.text(format("&7(y a ti Mismo)")));
        lore.add(Component.text(format("")));
        lore.add(Component.text(format("&6&lBUFFS:")));
        lore.add(Component.text(format("&7Fuerza III, Speed II, Regeneración II, Resistencia I")));
        lore.add(Component.text(format("&7Resistencia al Fuego y Health Boost II")));
        lore.add(Component.text(format("&71 minuto")));
        lore.add(Component.text(format("")));

        return new ItemBuilder(Material.STICK)
                .setName(format("&d&lVara de la Discordia"))
                .setUnbreakable(true)
                .setCustomModelData(5002)
                .setLore(lore)
                .build();
    }

}
