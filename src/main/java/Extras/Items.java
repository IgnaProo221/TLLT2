package Extras;

import Utilities.CustomEnchants;
import Utilities.Format;
import Utilities.ItemBuilder;
import Utilities.NBTEditor;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static Utilities.Format.format;

public class Items {

    public static ItemStack ringOfAbaddon(){
        return new ItemBuilder(Material.IRON_NUGGET)
                .setName(format("&cRing of Abaddon"))
                .build();
    }

    public static ItemStack brimStone(){
       return new ItemBuilder(Material.GOLDEN_APPLE)
               .setName(format("&cBrimstone"))
               .setCustomModelData(15315)
               .build();
    }

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
        lore.add(Component.text(ChatColor.GRAY + "Sientete en las nubes..."));
        lore.add(Component.text(ChatColor.GOLD + "Te otorgara Levitación, Slow Falling y Regeneración por un tiempo."));

        return new ItemBuilder(Material.GLOW_BERRIES)
                .setName(Format.format("&f&lCloudy Marshmallow"))
                .setLore(lore)
                .setCustomModelData(4001)
                .build();
    }

    public static ItemStack totemBerserk() {
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(ChatColor.GRAY + "Te otorgara Fuerza X y Speed V por"));
        lore.add(Component.text(ChatColor.GRAY + "5 segundos!"));
        lore.add(Component.text(ChatColor.RED + "(Este al gastarlo tiene un cooldown de tótem de 10 segundos)"));

        return new ItemBuilder(Material.TOTEM_OF_UNDYING)
                .setName(ChatColor.RED + "Totem Berserk")
                .setCustomModelData(4002)
                .setLore(lore)
                .build();
    }

    public static ItemStack bloodShard() {
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(ChatColor.GRAY + "Un sacrificio justo."));

        return new ItemBuilder(Material.IRON_NUGGET)
                .setName(ChatColor.RED + "Blood Stone")
                .setLore(lore)
                .setCustomModelData(4003)
                .build();
    }

    public static ItemStack crystalApple(int amount) {
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(ChatColor.GRAY + "Otorga Regeneración III y Resistencia"));
        return new ItemBuilder(Material.GOLDEN_APPLE, amount)
                .setName(format("&7Crystal Apple"))
                .setLore(lore)
                .setCustomModelData(4033) // No se cual es el custom model data de la manzanita
                .build();
    }

    public static ItemStack createDaga() {
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("&7Has &eClick Derecho &7para iniciar el sacrificio.")));
        lore.add(Component.text(format("&7Utiliza &8/thelastlife sacrificio &7para ver")));
        lore.add(Component.text(format("&7mas información.")));

        return new ItemBuilder(Material.IRON_SWORD)
                .setName("&7Daga Ceremonial")
                .setCustomModelData(4778)
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
                //.addEnchantment(Enchantment.ARROW_INFINITE, 1)
                //.setItemFlags(ItemFlag.HIDE_ENCHANTS)
                .setCustomModelData(4983)
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
                .setCustomModelData(4455)
                .addEnchantment(Enchantment.LUCK, 1)
                .setItemFlags(ItemFlag.HIDE_ENCHANTS)
                .setUnbreakable(true)
                .build();
    }

    public static ItemStack fallenSword() {
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("&6&lHABILIDAD: &eSubzero Edge")));
        lore.add(Component.text(format("&7Otorga el efecto de congelación a los")));
        lore.add(Component.text(format("&7mobs golpeados por su filo.")));
        lore.add(Component.text(""));
        lore.add(Component.text(""));
        lore.add(Component.text(format("&c&l(Es recomendable no usar Fire Aspect en esta Espada.)")));

        return new ItemBuilder(Material.NETHERITE_SWORD)
                .setName(format("&b&lFrostbite"))
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
        lore.add(Component.text(format("&7La espada tiene un chance de robar vida al")));
        lore.add(Component.text(format("&7golpear una entidad")));
        lore.add(Component.text(format("")));
        lore.add(Component.text(format("&c¡Al Hacer esto la espada se pondra en cooldown!")));

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
        lore.add(Component.text(format("&7Rumores dicen que los corazones de Cristal")));
        lore.add(Component.text(format("&7son objetos magicos capaz de volverte mas resistente")));
        lore.add(Component.text(format("&7pero este tiene un limite.")));

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
        lore.add(Component.text(format("&7Otorgara Buffs a jugadores que esten alrededor tuyo")));
        lore.add(Component.text(format("&7(y a ti también).")));
        lore.add(Component.text(format("")));
        lore.add(Component.text(format("&6&lBUFFS:")));
        lore.add(Component.text(format("&7Fuerza III, Speed II, Regeneración II, Resistencia I")));
        lore.add(Component.text(format("&7Resistencia al Fuego y Health Boost II")));
        lore.add(Component.text(format("&71 minuto.")));
        lore.add(Component.text(format("")));

        return new ItemBuilder(Material.STICK)
                .setName(format("&d&lVara de la Discordia"))
                .setUnbreakable(true)
                .setCustomModelData(5002)
                .setLore(lore)
                .build();
    }

    public static ItemStack celulaEnergia(){
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("")));
        lore.add(Component.text(format("&7Celulas antiguas usadas por los laboratorios")));
        lore.add(Component.text(format("&7su energía proviene de algo desconocido.")));
        lore.add(Component.text(format("")));
        return new ItemBuilder(Material.GOLD_NUGGET).setName(format("&4Celulas de Energía")).setLore(lore).setCustomModelData(4111).build();
    }

    public static ItemStack metaldes(){
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("")));
        lore.add(Component.text(format("&7Metal de una fuente desconocida")));
        lore.add(Component.text(format("&7esta tiene una fuerza sobrenatural...")));
        lore.add(Component.text(format("")));
        return  new ItemBuilder(Material.IRON_NUGGET).setName(format("&6Metal Desconocido")).setLore(lore).setCustomModelData(5663).build();
    }

    public static ItemStack exoShield(){
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("&6&lHABILIDAD: &eNano Protección")));
        lore.add(Component.text(format("&7Si el escudo es dañado por una Entidad")));
        lore.add(Component.text(format("&7¡este sera paralizado!")));
        lore.add(Component.text(format("&7al tenerlo en cualquier seras inmune al daño por Rayos")));
        return new ItemBuilder(Material.SHIELD).setName(format("&6&lExo-Shield")).setLore(lore).setUnbreakable(true).setCustomModelData(5015).build();
    }

    public static ItemStack iceShot(){
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("&6&lHABILIDAD: &eTiro Congelado")));
        lore.add(Component.text(format("&7Si una flecha impacata contra un mob")));
        lore.add(Component.text(format("&7¡este sera congelado por un tiempo!")));
        return new ItemBuilder(Material.BOW).setName(format("&b&lIce-Shot")).setLore(lore).setUnbreakable(true).setCustomModelData(5080).build();
    }

    public static ItemStack exoDrill(){
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("&6&lHABILIDAD: &e&lExo-Scan")));
        lore.add(Component.text(format("&7Ciertos bloques dropearan items extras")));
        lore.add(Component.text(format("&7Esos Items se añadiran a tu inventario.")));
        return new ItemBuilder(Material.NETHERITE_PICKAXE).setName(format("&6&lExo-Driller")).setLore(lore).setUnbreakable(true).setCustomModelData(6660).addEnchantment(Enchantment.DIG_SPEED, 5).build();
    }

    public static ItemStack exoTotem(){
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("&6&lHABILIDAD: &e&lNanotech")));
        lore.add(Component.text(format("&7Al activarse los Mobs alrededor de un radio de 15")));
        lore.add(Component.text(format("&7bloques recibiran un debuff de Velocidad y Fuerza.")));
        return new ItemBuilder(Material.TOTEM_OF_UNDYING).setName(format("&7&lExo-Totem")).setLore(lore).setUnbreakable(true).setCustomModelData(6891).build();
    }
    public static ItemStack exoSword(){
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("")));
        lore.add(Component.text(format("&6&lHABILIDAD: &e&lSabotaje")));
        lore.add(Component.text(format("&7Los golpes de esta espada hace que los Mobs")));
        lore.add(Component.text(format("&7sean debilitados y realentizados momentáneamente.")));
        lore.add(Component.text(format("")));
        return new ItemBuilder(Material.NETHERITE_SWORD).setName(format("&7&lExo-Blade")).setLore(lore).setUnbreakable(true).setCustomModelData(18129).build();
    }
    public static ItemStack exoBow(){
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("")));
        lore.add(Component.text(format("&6&lHABILIDAD: &e&lSabotaje")));
        lore.add(Component.text(format("&7Los impactos de flecha a una Entidad hace")));
        lore.add(Component.text(format("&7que esta sea debilitado y realentizados momentáneamente.")));
        lore.add(Component.text(format("")));
        return new ItemBuilder(Material.BOW).setName(format("&7&lExo-Bow")).setLore(lore).setUnbreakable(true).setCustomModelData(27289).build();
    }




    public static ItemStack pyroCross(){
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("&6&lHABILIDAD: &e&lPirotecnia")));
        lore.add(Component.text(format("&7Las Flechas que caigan al suelo o golpeen una entidad")));
        lore.add(Component.text(format("&7Explotarán.")));
        return new ItemBuilder(Material.CROSSBOW).setName(format("&6&lCross-pyro")).setLore(lore).setUnbreakable(true).setCustomModelData(389909).build();
    }

    public static ItemStack temperatureCooler(){
        return new ItemBuilder(Material.APPLE).setName("&bCooler Fruit").setCustomModelData(8883).build();
    }
    public static ItemStack temperatureHot(){
        return new ItemBuilder(Material.APPLE).setName("&6Hot Fruit").setCustomModelData(8993).build();
    }
    public static ItemStack sigilodeInmunidad(){
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("")));
        lore.add(Component.text(format("&7Al usar el Tótem, conseguirás &eInvulnerabilidad")));
        lore.add(Component.text(format("&7por 10 segundos.")));
        lore.add(Component.text(""));
        lore.add(Component.text(format("&c&l¡Tus Tótems se pondrán en cooldown de 20 segundos!")));
        return new ItemBuilder(Material.TOTEM_OF_UNDYING).setName(format("&e&lSigilo de Inmortalidad")).setLore(lore).setCustomModelData(6389).build();
    }





    //Umbra Tools
    public static ItemStack umbraRod(){
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("")));
        lore.add(Component.text(format("&7Chance de Pescar Shade Mobs y otros Items")));
        lore.add(Component.text(format("")));
        return new ItemBuilder(Material.FISHING_ROD).setName(format("&5&lUmbra Rod")).setLore(lore).setUnbreakable(true).setCustomModelData(102038461).build();
    }
    public static ItemStack umbraDrill(){
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("")));
        lore.add(Component.text(format("&7Viene con Telekinesis y Experience")));
        lore.add(Component.text(format("&c&lEvitara Maldiciones en Dias Futuros")));
        return new ItemBuilder(Material.NETHERITE_PICKAXE).setName(format("&5&lUmbra Drill")).setLore(lore).setUnbreakable(true).addEnchantment(Enchantment.DIG_SPEED,7).setCustomModelData(736535).addCustomEnchant(CustomEnchants.TELEPHATY, 1).addCustomEnchant(CustomEnchants.EXPERIENCE, 1).build();
    }
    public static ItemStack umbraAxe(){
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("")));
        lore.add(Component.text(format("&7Viene con Telekinesis y Lucky Tree")));
        lore.add(Component.text(format("&c&lEvitara Maldiciones en Dias Futuros")));
        return new ItemBuilder(Material.NETHERITE_AXE).setName(format("&5&lUmbra Axe")).setLore(lore).setUnbreakable(true).addEnchantment(Enchantment.DIG_SPEED,7).setCustomModelData(77353518).addCustomEnchant(CustomEnchants.TELEPHATY, 1).addCustomEnchant(CustomEnchants.LUCKY_TREE, 1).build();
    }
    public static ItemStack umbraShovel(){
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("")));
        lore.add(Component.text(format("&7Viene con Smelting Touch")));
        lore.add(Component.text(format("&c&lEvitara Maldiciones en Dias Futuros")));
        return new ItemBuilder(Material.NETHERITE_SHOVEL).setName(format("&5&lUmbra Shovel")).setLore(lore).setUnbreakable(true).addEnchantment(Enchantment.DIG_SPEED,7).setCustomModelData(5463739).addCustomEnchant(CustomEnchants.SMELTING_TOUCH, 1).build();
    }
    public static ItemStack umbraHoe(){
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("")));
        lore.add(Component.text(format("&7Sacando plantaciones de Zanahorias, Papas y Beetroots")));
        lore.add(Component.text(format("&7Podras sacar Manzanas Doradas Encantadas")));
        lore.add(Component.text(format("&c&lEvitara Maldiciones en Dias Futuros")));
        return new ItemBuilder(Material.NETHERITE_HOE).setName(format("&5&lUmbra Hoe")).setLore(lore).setUnbreakable(true).addEnchantment(Enchantment.KNOCKBACK,5).setCustomModelData(236).build();
    }
    public static ItemStack touchofdarkness(){
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("")));
        lore.add(Component.text(format("&8&lSe parte de la Oscuridad, vuélvete uno con las Sombras")));
        lore.add(Component.text(format("&7Tus flechas Infligen Wither, Slowness y Weakness")));
        lore.add(Component.text(format("")));
        return new ItemBuilder(Material.BOW).setName(format("&8&lTouch of Darkness")).setLore(lore).setUnbreakable(true).addEnchantment(Enchantment.ARROW_DAMAGE,10).setCustomModelData(91817).build();
    }


    public static ItemStack umbraShell(){
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("")));
        lore.add(Component.text(format("&8&lSe parte de la Oscuridad, vuélvete uno con las Sombras")));
        lore.add(Component.text(format("&7Evita el Daño de Caída y Rayos, al bloquear un ataque el Mob")));
        lore.add(Component.text(format("&7es Debilitado y Ralentizado")));
        lore.add(Component.text(format("")));
        return new ItemBuilder(Material.SHIELD).setName(format("&8&lUmbra Shield")).setLore(lore).setUnbreakable(true).setCustomModelData(11282349).build();
    }
    public static ItemStack shadowRupture(){
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("")));
        lore.add(Component.text(format("&8&lSe parte de la Oscuridad, vuélvete uno con las Sombras")));
        lore.add(Component.text(format("&7Este Tridente puede ser usado de 2 maneras distintas")));
        lore.add(Component.text(format("&8Teletransportate en las Sombras con &cClick Izquierdo")));
        lore.add(Component.text(format("&7o Impulsarte en el agua &cManteniendo Click Derecho")));
        lore.add(Component.text(format("")));
        return new ItemBuilder(Material.TRIDENT).setName(format("&8&lShadow Rupture")).setLore(lore).setUnbreakable(true).setCustomModelData(236363).addEnchantment(Enchantment.RIPTIDE,5).setItemFlags(ItemFlag.HIDE_ENCHANTS).build();
    }
    public static ItemStack shadowTotem(){
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("")));
        lore.add(Component.text(format("&8&lSe parte de la Oscuridad, vuélvete uno con las Sombras")));
        lore.add(Component.text(format("&7Este Totem te hara Inmortal por unos segundos")));
        lore.add(Component.text(format("&8&lEvita el Efecto de Dados")));
        lore.add(Component.text(format("&c&lPero tu Porcentaje de Totems es completamente Modificado")));
        lore.add(Component.text(format("")));
        return new ItemBuilder(Material.TOTEM_OF_UNDYING).setName(format("&8&lDarkness Idol")).setLore(lore).setUnbreakable(true).setCustomModelData(387309111).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,1).setItemFlags(ItemFlag.HIDE_ENCHANTS).build();
    }








    //ENCANTAMIENTOS
    public static ItemStack teleTome(){
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("&6&lEncantamiento Ancestral: &e&lTelephaty")));
        lore.add(Component.text(format("&7Los Bloques que piques iran a tu inventario")));
        return new ItemBuilder(Material.ENCHANTED_BOOK).setName("&e&lAncient Tome").setUnbreakable(true).addCustomEnchant(CustomEnchants.TELEPHATY, 1).setLore(lore).build();
    }
    public static ItemStack pichaTome(){
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("&6&lEncantamiento Ancestral: &e&lSmelting Touch")));
        lore.add(Component.text(format("&7Algunos ores dropean su mineral cocinado")));
        return new ItemBuilder(Material.ENCHANTED_BOOK).setName("&e&lAncient Tome").setUnbreakable(true).addCustomEnchant(CustomEnchants.SMELTING_TOUCH, 1).setLore(lore).build();
    }
    public static ItemStack lucktreeTome(){
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("&6&lEncantamiento Ancestral: &e&lLucky Tree")));
        lore.add(Component.text(format("&7Tienes chance de dropear mas de 1 tronco")));
        return new ItemBuilder(Material.ENCHANTED_BOOK).setName("&e&lAncient Tome").setUnbreakable(true).addCustomEnchant(CustomEnchants.LUCKY_TREE, 1).setLore(lore).build();
    }
    public static ItemStack revengeTome(){
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("&6&lEncantamiento Ancestral: &e&lRevenge")));
        lore.add(Component.text(format("&7Haces un 20% mas de daño a los Illagers")));
        return new ItemBuilder(Material.ENCHANTED_BOOK).setName("&e&lAncient Tome").setUnbreakable(true).addCustomEnchant(CustomEnchants.REVENGE, 1).setLore(lore).build();
    }
    public static ItemStack critihitTome(){
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("&6&lEncantamiento Ancestral: &e&lCritical Hit")));
        lore.add(Component.text(format("&7Chance de que hagas un Ataque Critico al Mob")));
        return new ItemBuilder(Material.ENCHANTED_BOOK).setName("&e&lAncient Tome").setUnbreakable(true).addCustomEnchant(CustomEnchants.CRITICAL_HIT, 1).setLore(lore).build();
    }

    public static ItemStack bullsEyeTome() {
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("&6&lEncantamiento Ancestral: &e&lBullsEye")));
        lore.add(Component.text(format("&7La Flecha hace mas Daño mientras mas bloques recorre")));
        return new ItemBuilder(Material.ENCHANTED_BOOK).setName("&e&lAncient Tome").setUnbreakable(true).addCustomEnchant(CustomEnchants.BULLSEYE, 1).setLore(lore).build();
    }
    public static ItemStack shriekTome(){
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("&6&lEncantamiento Ancestral: &e&lShriek")));
        lore.add(Component.text(format("&7Haces mas Daño a los Mobs de Sculk")));
        return new ItemBuilder(Material.ENCHANTED_BOOK).setName("&e&lAncient Tome").setUnbreakable(true).addCustomEnchant(CustomEnchants.SHRIEK, 1).setLore(lore).build();
    }
    public static ItemStack whiterjusticeTome(){
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("&6&lEncantamiento Ancestral: &e&lWither Justice")));
        lore.add(Component.text(format("&7Reduce el Daño por Explosion")));
        return new ItemBuilder(Material.ENCHANTED_BOOK).setName("&e&lAncient Tome").setUnbreakable(true).addCustomEnchant(CustomEnchants.WITHER_JUSTICE, 1).setLore(lore).build();
    }
    public static ItemStack pyroTome(){
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("&6&lEncantamiento Ancestral: &e&lPyromaniac")));
        lore.add(Component.text(format("&7Tus Flechas pueden hacer un daño en area")));
        return new ItemBuilder(Material.ENCHANTED_BOOK).setName("&e&lAncient Tome").setUnbreakable(true).addCustomEnchant(CustomEnchants.PYROMANIAC, 1).setLore(lore).build();
    }
    public static ItemStack tackleTome(){
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("&6&lEncantamiento Ancestral: &e&lTackle")));
        lore.add(Component.text(format("&7Los Mobs son lanzados despues de golpear tu escudo")));
        return new ItemBuilder(Material.ENCHANTED_BOOK).setName("&e&lAncient Tome").setUnbreakable(true).addCustomEnchant(CustomEnchants.TACKLE, 1).setLore(lore).build();
    }
    public static ItemStack heatproteTome(){
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("&6&lEncantamiento Ancestral: &e&lHeat Protection")));
        lore.add(Component.text(format("&7Reduce el daño por Fuego, Lava y Magma Blocks")));
        return new ItemBuilder(Material.ENCHANTED_BOOK).setName("&e&lAncient Tome").setUnbreakable(true).addCustomEnchant(CustomEnchants.HEAT_PROTECTION, 1).setLore(lore).build();
    }
    public static ItemStack adaptativeTome(){
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("&6&lEncantamiento Ancestral: &e&lAdaptative")));
        lore.add(Component.text(format("&7El Efecto de Hipotermia y Hipertermia 1 ya no se aplican")));
        return new ItemBuilder(Material.ENCHANTED_BOOK).setName("&e&lAncient Tome").setUnbreakable(true).addCustomEnchant(CustomEnchants.ADAPTATIVE, 1).setLore(lore).build();
    }
    public static ItemStack infernopowerTome(){
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("&6&lEncantamiento Ancestral: &e&lInferno Power")));
        lore.add(Component.text(format("&7Mejora tu Ember Sceptre, haciendo que lance Fireballs que hacen mas daño!")));
        return new ItemBuilder(Material.ENCHANTED_BOOK).setName("&e&lAncient Tome").setUnbreakable(true).addCustomEnchant(CustomEnchants.INFERNO_POWER, 1).setLore(lore).build();
    }
    public static ItemStack bruteforceTome(){
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("&6&lEncantamiento Ancestral: &e&lBrute Force")));
        lore.add(Component.text(format("&7")));
        return new ItemBuilder(Material.ENCHANTED_BOOK).setName("&e&lAncient Tome").setUnbreakable(true).addCustomEnchant(CustomEnchants.INFERNO_POWER, 1).setLore(lore).build();
    }
    public static ItemStack healthystepTome(){
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("&6&lEncantamiento Ancestral: &e&lInferno Power")));
        lore.add(Component.text(format("&7Mejora tu Ember Sceptre, haciendo que lance Fireballs que hacen mas daño!")));
        return new ItemBuilder(Material.ENCHANTED_BOOK).setName("&e&lAncient Tome").setUnbreakable(true).addCustomEnchant(CustomEnchants.INFERNO_POWER, 1).setLore(lore).build();
    }



    public static ItemStack pieCalor(){
        return new ItemBuilder(Material.PUMPKIN_PIE).setName(format("&6&lMagma Pie")).build();
    }
    public static ItemStack pieFrio(){
        return new ItemBuilder(Material.PUMPKIN_PIE).setName(format("&b&lIce Pie")).build();
    }


    public static ItemStack fireHook(){
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("")));
        lore.add(Component.text(format("&7Te impulsa hacia la direccion que estas mirando")));
        lore.add(Component.text(format("&6&l¡Te Implusa el doble si estas sobre Lava!")));
        lore.add(Component.text(format("")));
        return new ItemBuilder(Material.FISHING_ROD).setLore(lore).setUnbreakable(true).setCustomModelData(1736).setName(format("&6&lFireHook")).build();
    }

    public static ItemStack emberSceptre(){
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("")));
        lore.add(Component.text(format("&7Lanza una rafaga de Proyectiles hacia donde")));
        lore.add(Component.text(format("&7estas mirando")));
        lore.add(Component.text(format("")));
        return new ItemBuilder(Material.BLAZE_ROD).setLore(lore).setUnbreakable(true).setCustomModelData(938745122).setName(format("&c&lEmber Sceptre")).build();
    }


    public static ItemStack sunshineRelic(){
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("")));
        lore.add(Component.text(format("&6&o¿Can you feel the sunshine?")));
        lore.add(Component.text(format("")));
        lore.add(Component.text(format("&7Irradia energía que te purifica de Maldiciones Futuras")));
        lore.add(Component.text(format("&5&k||| &5&lTu Futuro Brilla Intensamente &5&k|||")));
        return new ItemBuilder(Material.ORANGE_DYE).setLore(lore).setUnbreakable(true).setCustomModelData(777).setName(format("&6&lSunshine Relic")).build();
    }




    //NO DEJEN QUE FEL TOQUE ESTO PORFAVOR
    public static ItemStack hyperion(){
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("&7Gear Score: &d615")));
        lore.add(Component.text(format("&7Damage: &c+260")));
        lore.add(Component.text(format("&7Strength: &c+150")));
        lore.add(Component.text(format("&7Intelligence: &a+350")));
        lore.add(Component.text(format("&7Ferocity: &a+30")));
        lore.add(Component.text(format("")));
        lore.add(Component.text(format("")));
        lore.add(Component.text(format("&7Deals +&a50% &7damage to")));
        lore.add(Component.text(format("&7Withers. Grants &c+1 Damage")));
        lore.add(Component.text(format("&7and &a+2 &bIntelligence")));
        lore.add(Component.text(format("&7per &cCatacombs &7level")));
        lore.add(Component.text(format("")));
        lore.add(Component.text(format("&7Your Catacombs level: &c0")));
        lore.add(Component.text(format("")));
        lore.add(Component.text(format("&eRight-Click to use your class ability!")));
        lore.add(Component.text(format("")));
        lore.add(Component.text(format("&8This item can be reforged")));
        lore.add(Component.text(format("&cRequires &aCatacombs Floor VII")));
        lore.add(Component.text(format("&7Completion")));
        lore.add(Component.text(format("&6&lLEGENDARY DUNGEON SWORD")));
        return new ItemBuilder(Material.IRON_SWORD).setName(format("&6Hyperion")).setUnbreakable(true).setItemFlags(ItemFlag.HIDE_UNBREAKABLE).setLore(lore).setCustomModelData(84984389).build();
    }





    //Blood Armor
    public static ItemStack bloodyHelmet(){
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("")));
        lore.add(Component.text(format("&7Una pieza de armadura hecha de sangre de millones.")));
        lore.add(Component.text(format("&7Tener el Set Completo otorgara 6 corazones extras.")));
        lore.add(Component.text(format("")));
        return new ItemBuilder(Material.NETHERITE_HELMET).setName(format("&c&lBloodstained Helmet")).setLore(lore).setUnbreakable(true).setCustomModelData(8010).build();
    }
    public static ItemStack bloodyChestplate(){
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("")));
        lore.add(Component.text(format("&7Una pieza de armadura hecha con sangre de millones.")));
        lore.add(Component.text(format("&7Tener el set completo te otorgara 6 corazones extras.")));
        lore.add(Component.text(format("")));
        return new ItemBuilder(Material.NETHERITE_CHESTPLATE).setName(format("&c&lBloodstained Chestplate")).setLore(lore).setUnbreakable(true).setCustomModelData(8010).build();
    }
    public static ItemStack bloodyLeggings(){
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("")));
        lore.add(Component.text(format("&7Una pieza de armadura hecha con sangre de millones.")));
        lore.add(Component.text(format("&7Tener el set completo te otorgara 6 corazones extras.")));
        lore.add(Component.text(format("")));
        return new ItemBuilder(Material.NETHERITE_LEGGINGS).setName(format("&c&lBloodstained Leggings")).setLore(lore).setUnbreakable(true).setCustomModelData(8010).build();
    }
    public static ItemStack bloodyBoots(){
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("")));
        lore.add(Component.text(format("&7Un set de armadura hecho de la sangre de millones.")));
        lore.add(Component.text(format("&7Tener el set completo te otorgara 6 corazones extras.")));
        lore.add(Component.text(format("")));
        return new ItemBuilder(Material.NETHERITE_BOOTS).setName(format("&c&lBloodstained Boots")).setLore(lore).setUnbreakable(true).setCustomModelData(8010).build();
    }



    //EXO ARMOR

    public static ItemStack exoHelmet(){
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("")));
        lore.add(Component.text(format("&7Un set de armadura con pedazos de robots decaidos")));
        lore.add(Component.text(format("&7Tener el set completo te dara buffs variados y")));
        lore.add(Component.text(format("&7otorgara inmunidad a la &eParalización.")));
        lore.add(Component.text(format("")));
        return new ItemBuilder(Material.NETHERITE_HELMET).setName(format("&7&lExo-Helmet")).setLore(lore).setUnbreakable(true).setCustomModelData(47399).build();
    }
    public static ItemStack exoChestplate(){
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("")));
        lore.add(Component.text(format("&7Un set de armadura con pedazos de robots decaidos")));
        lore.add(Component.text(format("&7Tener el set completo te dara buffs variados y")));
        lore.add(Component.text(format("&7otorgara inmunidad a la &eParalización.")));
        lore.add(Component.text(format("")));
        return new ItemBuilder(Material.NETHERITE_CHESTPLATE).setName(format("&7&lExo-Chestplate")).setLore(lore).setUnbreakable(true).setCustomModelData(47399).build();
    }
    public static ItemStack exoLeggings(){
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("")));
        lore.add(Component.text(format("&7Un set de armadura con pedazos de robots decaidos")));
        lore.add(Component.text(format("&7Tener el set completo te dara buffs variados y")));
        lore.add(Component.text(format("&7otorgara inmunidad a la &eParalización.")));
        lore.add(Component.text(format("")));
        return new ItemBuilder(Material.NETHERITE_LEGGINGS).setName(format("&7&lExo-Leggings")).setLore(lore).setUnbreakable(true).setCustomModelData(47399).build();
    }
    public static ItemStack exoBoots(){
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("")));
        lore.add(Component.text(format("&7Un set de armadura con pedazos de robots decaidos")));
        lore.add(Component.text(format("&7Tener el set completo te dara buffs variados y")));
        lore.add(Component.text(format("&7otorgara inmunidad a la &eParalización.")));
        lore.add(Component.text(format("")));
        return new ItemBuilder(Material.NETHERITE_BOOTS).setName(format("&7&lExo-Boots")).setLore(lore).setUnbreakable(true).setCustomModelData(47399).build();
    }


    //UMBRA ARMOR
    public static ItemStack umbraHelmet(){
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("")));
        lore.add(Component.text(format("&7Un set de armadura con la oscuridad y el inferno")));
        lore.add(Component.text(format("&7en su Interior")));
        lore.add(Component.text(format("&7Tener el set completo te dara buffs variados,")));
        lore.add(Component.text(format("&7otorgara inmunidad a la &eParalización &7y durante")));
        lore.add(Component.text(format("&7las Noches tendrás el &cDoble de corazones extras&7.")));
        lore.add(Component.text(format("")));
        return new ItemBuilder(Material.NETHERITE_HELMET).setLore(lore).setName(format("&5&lUmbra Helmet")).setUnbreakable(true).setCustomModelData(6761618).build();
    }
    public static ItemStack umbraChestplate(){
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("")));
        lore.add(Component.text(format("&7Un set de armadura con la oscuridad y el inferno")));
        lore.add(Component.text(format("&7en su Interior")));
        lore.add(Component.text(format("&7Tener el set completo te dara buffs variados,")));
        lore.add(Component.text(format("&7otorgara inmunidad a la &eParalización &7y durante")));
        lore.add(Component.text(format("&7las Noches tendrás el &cDoble de corazones extras&7.")));
        lore.add(Component.text(format("")));
        return new ItemBuilder(Material.NETHERITE_CHESTPLATE).setLore(lore).setName(format("&5&lUmbra Chestplate")).setUnbreakable(true).setCustomModelData(6761618).build();
    }
    public static ItemStack umbraLeggings(){
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("")));
        lore.add(Component.text(format("&7Un set de armadura con la oscuridad y el inferno")));
        lore.add(Component.text(format("&7en su Interior")));
        lore.add(Component.text(format("&7Tener el set completo te dara buffs variados,")));
        lore.add(Component.text(format("&7otorgara inmunidad a la &eParalización &7y durante")));
        lore.add(Component.text(format("&7las Noches tendrás el &cDoble de corazones extras&7.")));
        lore.add(Component.text(format("")));
        return new ItemBuilder(Material.NETHERITE_LEGGINGS).setLore(lore).setName(format("&5&lUmbra Leggings")).setUnbreakable(true).setCustomModelData(6761618).build();
    }
    public static ItemStack umbraBoots(){
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("")));
        lore.add(Component.text(format("&7Un set de armadura con la oscuridad y el inferno")));
        lore.add(Component.text(format("&7en su Interior")));
        lore.add(Component.text(format("&7Tener el set completo te dara buffs variados,")));
        lore.add(Component.text(format("&7otorgara inmunidad a la &eParalización &7y durante")));
        lore.add(Component.text(format("&7las Noches tendrás el &cDoble de corazones extras&7.")));
        lore.add(Component.text(format("")));
        return new ItemBuilder(Material.NETHERITE_BOOTS).setLore(lore).setName(format("&5&lUmbra Boots")).setUnbreakable(true).setCustomModelData(6761618).build();
    }



    public static ItemStack rareGem(){
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("&7Un mineral misterioso y apreciado")));
        lore.add(Component.text(format("&7extraídos de las cuevas pronfundas de Deepslate.")));
        return new ItemBuilder(Material.DIAMOND).setName(format("&cRare Gem")).setLore(lore).setCustomModelData(5420).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setItemFlags(ItemFlag.HIDE_ENCHANTS).build();
    }
    public static ItemStack nanoTech(){
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("")));
        lore.add(Component.text(format("&6&lHABILIDAD: &e&lQuantum Edge")));
        lore.add(Component.text(format("&7Debilita, Realentiza y Infecta al Mob afectado")));
        lore.add(Component.text(format("&7por su Filo")));
        lore.add(Component.text(format("")));
        return new ItemBuilder(Material.NETHERITE_SWORD).setName(format("&8&lNano-Tech")).setLore(lore).setCustomModelData(382388).setUnbreakable(true).build();
    }
    public static ItemStack undyingStaff(){
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("&6&lHABILIDAD: &e&lUndying Blood")));
        lore.add(Component.text(format("&7Te Otorgara Inmortalidad por 15 segundos")));
        lore.add(Component.text(format("&8Cooldown de &c5 minutos")));
        return new ItemBuilder(Material.BLAZE_ROD).setName(format("&e&lUndying Spectre")).setLore(lore).setCustomModelData(8736689).setUnbreakable(true).build();
    }
    public static ItemStack unluckyIdol(){
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("&7Este Tótem evitara la suerte de los Dados")));
        lore.add(Component.text(format("&c&l¡Este Totem usara otros 4 totems de tu Inventario!")));
        return new ItemBuilder(Material.TOTEM_OF_UNDYING).setName(format("&c&lUnlucky Idol")).setLore(lore).setCustomModelData(28171219).setUnbreakable(true).build();
    }
    public static ItemStack luminiteArtifact(){
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("&7Al tenerlo en tu Inventario hay un 1% de")));
        lore.add(Component.text(format("&7Negar el Daño Recibido")));
        return new ItemBuilder(Material.NETHERITE_SCRAP).setName(format("&b&lMedallon de Luminita")).setLore(lore).setCustomModelData(999378).build();
    }

}
