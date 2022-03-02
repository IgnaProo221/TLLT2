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
       return new ItemBuilder(Material.IRON_NUGGET)
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
        lore.add(Component.text(ChatColor.GRAY + "Regeneración III y Resistencia"));
        lore.add(Component.text(ChatColor.GRAY + "Pero tu habilidad de Minar y Golpear"));
        lore.add(Component.text(ChatColor.GRAY + "seran debilitadas."));

        return new ItemBuilder(Material.GOLDEN_APPLE, amount)
                .setName(ChatColor.GRAY + "Crystal Apple")
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
                .addEnchantment(Enchantment.ARROW_INFINITE, 1)
                .setItemFlags(ItemFlag.HIDE_ENCHANTS)
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
        lore.add(Component.text(format("&7sean debilitados momentáneamente.")));
        lore.add(Component.text(format("")));
        return new ItemBuilder(Material.NETHERITE_SWORD).setName(format("&7&lExo-Blade")).setLore(lore).setUnbreakable(true).setCustomModelData(18129).build();
    }
    public static ItemStack exoBow(){
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("")));
        lore.add(Component.text(format("&6&lHABILIDAD: &e&lSabotaje")));
        lore.add(Component.text(format("&7Los impactos de flecha a una Entidad hace")));
        lore.add(Component.text(format("&7que esta sea debilitado momentáneamente.")));
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
        return new ItemBuilder(Material.GOLDEN_APPLE).setName("&bCooler Fruit").setCustomModelData(8883).build();
    }
    public static ItemStack temperatureHot(){
        return new ItemBuilder(Material.GOLDEN_APPLE).setName("&6Hot Fruit").setCustomModelData(8993).build();
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

    public static ItemStack testEnchant(){
        //TODO usen este item si hay algun enchant bug para probarlo
        ItemStack dou = new ItemBuilder(Material.NETHERITE_PICKAXE).setName("&6Test Pickaxe").setUnbreakable(true).addCustomEnchant(CustomEnchants.SMELTING_TOUCH, 1).build();
        dou = NBTEditor.set(dou,1,"holafelipe");
        return dou;
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












    public static ItemStack rareGem(){
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(format("&7Un mineral misterioso y apreciado")));
        lore.add(Component.text(format("&7extraídos de las cuevas pronfundas de Deepslate.")));
        return new ItemBuilder(Material.DIAMOND).setName(format("&cRare Gem")).setLore(lore).setCustomModelData(5420).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setItemFlags(ItemFlag.HIDE_ENCHANTS).build();
    }

}
