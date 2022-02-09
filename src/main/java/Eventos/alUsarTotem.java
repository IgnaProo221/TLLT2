package Eventos;

import Extras.Items;
import Utilidades.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityResurrectEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import tlldos.tll2.TLL2;

import java.util.Objects;
import java.util.Random;
import static Utilidades.Format.format;

import static Utilidades.Format.format;

public class alUsarTotem implements Listener {
    private final TLL2 plugin;

    public alUsarTotem(TLL2 plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void totemDado(EntityResurrectEvent e) {
        try {
            if (e.getEntity().getType() == (EntityType.PLAYER)) {

                Player p = (Player) e.getEntity();

                if (p.getInventory().getItemInMainHand().getType() == Material.TOTEM_OF_UNDYING || p.getInventory().getItemInOffHand().getType() == Material.TOTEM_OF_UNDYING) {

                    int TotemCara = new Random().nextInt(6) + 1;
                    PersistentDataContainer data = p.getPersistentDataContainer();

                    //TotemsBar.anadirTC(p);
                    /*if(TotemsBar.getPorcentaje(p) == 200){
                        e.setCancelled(true);
                        Bukkit.getOnlinePlayers().forEach(player -> {
                            player.sendMessage(Format.PREFIX + format("&8&l¡Los Totems del Jugador " + p.getName() + " se han desactivado y ha muerto! &c&l(Su Porcentaje de Totems esta en 0%)"));

                        });
                        return;
                    }*/
                    if(!data.has(Utils.key("TOTEM_BAR"), PersistentDataType.INTEGER)){
                        data.set(Utils.key("TOTEM_BAR"), PersistentDataType.INTEGER, 100);
                    }else{
                        int i = data.get(Utils.key("TOTEM_BAR"), PersistentDataType.INTEGER);
                        data.set(Utils.key("TOTEM_BAR"), PersistentDataType.INTEGER, i - 5);
                    }
                    if (data.get(Utils.key("TOTEM_BAR"), PersistentDataType.INTEGER) == 0) {
                        e.setCancelled(true);

                        for (Player players : Bukkit.getOnlinePlayers()) {
                            players.sendMessage(Format.PREFIX + format("&8&l¡Los Totems del Jugador " + p.getName() + " se han desactivado y ha muerto! &c&l(Su Porcentaje de Totems esta en 0%)"));
                        }
                        return;
                    }

                    if (p.hasCooldown(Material.TOTEM_OF_UNDYING)) {

                        e.setCancelled(true);

                        for (Player players : Bukkit.getOnlinePlayers()) {

                            players.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8El Cooldown del Totem del Jugador &6&l" + p.getName() + "&8 se a Activado!"));

                        }
                        return;
                    }

                    if (p.getInventory().getItemInMainHand().equals(Items.totemBerserk()) || p.getInventory().getItemInOffHand().equals(Items.totemBerserk())) {

                        if (p.getInventory().getItemInMainHand().getType() == Material.TOTEM_OF_UNDYING && !p.getInventory().getItemInMainHand().equals(Items.totemBerserk()))
                            return;

                        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 10.0F, 2.0F);
                        p.setCooldown(Material.TOTEM_OF_UNDYING, 200);

                        for (Player players : Bukkit.getOnlinePlayers()) {

                            players.sendMessage(ChatColor.DARK_GRAY + "El Jugador " + ChatColor.RED + p.getName() + ChatColor.DARK_GRAY + " a usado un " + ChatColor.YELLOW + "Totem!♦" + ChatColor.GRAY + "  (Causa: " + causadeDaño(Objects.requireNonNull(p.getLastDamageCause())) + ChatColor.GRAY + ")");
                            players.sendMessage(ChatColor.RED + "Los Totems de " + ChatColor.YELLOW + "" + ChatColor.BOLD + p.getName() + ChatColor.RED + " Entraron en Cooldown de 10 Segundos! ");

                           Bukkit.getScheduler().runTaskLater(plugin, () -> {
                                p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100, 9));
                                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 4));
                            }, 10);
                        }
                        return;
                    }

                    if (TotemCara == 1) {
                        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 10.0F, 2.0F);

                        for (Player players : Bukkit.getOnlinePlayers()) {
                            players.sendMessage(ChatColor.DARK_GRAY + "El Jugador " + ChatColor.RED + p.getName() + ChatColor.DARK_GRAY + " a usado un " + ChatColor.YELLOW + "Totem!♦" + ChatColor.GRAY + "  (Causa: " + causadeDaño(Objects.requireNonNull(p.getLastDamageCause())) + ChatColor.GRAY + ")");
                            players.sendMessage(ChatColor.RED + "El Totem a caido en la cara " + ChatColor.YELLOW + "" + ChatColor.BOLD + "Numero 1!");
                            players.sendMessage(ChatColor.GRAY + "Efecto: " + ChatColor.RED + "Ningun Efecto");
                        }
                    } else if (TotemCara == 2) {
                        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 10.0F, 2.0F);

                        for (Player players : Bukkit.getOnlinePlayers()) {
                            players.sendMessage(ChatColor.DARK_GRAY + "El Jugador " + ChatColor.RED + p.getName() + ChatColor.DARK_GRAY + " a usado un " + ChatColor.YELLOW + "Totem!♦" + ChatColor.GRAY + "  (Causa: " + causadeDaño(Objects.requireNonNull(p.getLastDamageCause())) + ChatColor.GRAY + ")");
                            players.sendMessage(ChatColor.RED + "El Totem a caido en la cara " + ChatColor.YELLOW + "" + ChatColor.BOLD + "Numero 2!");
                            players.sendMessage(ChatColor.GRAY + "Efecto: " + ChatColor.RED + "Speed II por 10 segundos!");

                            Bukkit.getScheduler().runTaskLater(plugin, () -> p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 1)), 10);
                        }
                    } else if (TotemCara == 3) {
                        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 10.0F, 2.0F);

                        for (Player players : Bukkit.getOnlinePlayers()) {
                            players.sendMessage(ChatColor.DARK_GRAY + "El Jugador " + ChatColor.RED + p.getName() + ChatColor.DARK_GRAY + " a usado un " + ChatColor.YELLOW + "Totem!♦" + ChatColor.GRAY + "  (Causa: " + causadeDaño(Objects.requireNonNull(p.getLastDamageCause())) + ChatColor.GRAY + ")");
                            players.sendMessage(ChatColor.RED + "El Totem a caido en la cara " + ChatColor.YELLOW + "" + ChatColor.BOLD + "Numero 3!");
                            players.sendMessage(ChatColor.GRAY + "Efecto: " + ChatColor.RED + "Fuerza y Haste II por 5 segundos!");

                            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                                p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100, 1));
                                p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 100, 1));
                            }, 10);
                        }
                    } else if (TotemCara == 4) {
                        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 10.0F, 2.0F);

                        for (Player players : Bukkit.getOnlinePlayers()) {
                            players.sendMessage(ChatColor.DARK_GRAY + "El Jugador " + ChatColor.RED + p.getName() + ChatColor.DARK_GRAY + " a usado un " + ChatColor.YELLOW + "Totem!♦" + ChatColor.GRAY + "  (Causa: " + causadeDaño(Objects.requireNonNull(p.getLastDamageCause())) + ChatColor.GRAY + ")");
                            players.sendMessage(ChatColor.RED + "El Totem a caido en la cara " + ChatColor.YELLOW + "" + ChatColor.BOLD + "Numero 4!");
                            players.sendMessage(ChatColor.GRAY + "Efecto: " + ChatColor.RED + "Wither, Slowness y Veneno II por 10 segundos!");

                            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                                p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 200, 1));
                                p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 1));
                                p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 200, 1));
                            }, 10);
                        }
                    } else if (TotemCara == 5) {

                        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 10.0F, 2.0F);

                        int needTotems = 2;
                        int main = 0;
                        int off = 0;

                        if (p.getInventory().getItemInMainHand().getType().equals(Material.TOTEM_OF_UNDYING)) {
                            main = 1;
                        }
                        if (p.getInventory().getItemInOffHand().getType().equals(Material.TOTEM_OF_UNDYING)) {
                            off = 1;
                        }

                        int result = main + off;
                        int size = p.getInventory().all(Material.TOTEM_OF_UNDYING).size() + result;

                        String totemMessage;

                        if (needTotems > size) {
                            e.setCancelled(true);
                            totemMessage = ChatColor.DARK_GRAY + "El Jugador " + ChatColor.RED + p.getName() + ChatColor.DARK_GRAY + " no tenia suficientes " + ChatColor.YELLOW + "Totems " + ChatColor.DARK_GRAY + "en su inventario (" + needTotems + "/" + size + ")!♦" + ChatColor.GRAY + "  (Causa: " + causadeDaño(Objects.requireNonNull(p.getLastDamageCause())) + ChatColor.GRAY + ")";
                        } else {

                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    p.playSound(p.getLocation(), Sound.ITEM_TOTEM_USE, 5.0F, 1.0F);
                                    p.playEffect(EntityEffect.TOTEM_RESURRECT);
                                }
                            }.runTaskLater(TLL2.getPlugin(TLL2.class), 20L);

                            p.getInventory().removeItem(new ItemStack(Material.TOTEM_OF_UNDYING, 1));

                            totemMessage = ChatColor.DARK_GRAY + "El Jugador " + ChatColor.RED + p.getName() + ChatColor.DARK_GRAY + " a usado un " + ChatColor.YELLOW + "Totem!♦" + ChatColor.GRAY + "  (Causa: " + causadeDaño(Objects.requireNonNull(p.getLastDamageCause())) + ChatColor.GRAY + ")";
                        }
                        for (Player players : Bukkit.getOnlinePlayers()) {
                            players.sendMessage(totemMessage);

                            players.sendMessage(ChatColor.RED + "El Totem a caido en la cara " + ChatColor.YELLOW + "" + ChatColor.BOLD + "Numero 5!");
                            players.sendMessage(ChatColor.GRAY + "Efecto: " + ChatColor.RED + "Usar otro Totem en tu Inventario!");
                        }
                    } else if (TotemCara == 6) {
                        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 10.0F, 2.0F);
                        p.removePotionEffect(PotionEffectType.ABSORPTION);
                        p.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
                        p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
                        for (Player players : Bukkit.getOnlinePlayers()) {
                            players.sendMessage(ChatColor.DARK_GRAY + "El Jugador " + ChatColor.RED + p.getName() + ChatColor.DARK_GRAY + " a usado un " + ChatColor.YELLOW + "Totem!♦" + ChatColor.GRAY + "  (Causa: " + causadeDaño(Objects.requireNonNull(p.getLastDamageCause())) + ChatColor.GRAY + ")");
                            players.sendMessage(ChatColor.RED + "El Totem a caido en la cara " + ChatColor.YELLOW + "" + ChatColor.BOLD + "Numero 6!");
                            players.sendMessage(ChatColor.GRAY + "Efecto: " + ChatColor.RED + "Sin Efectos!");
                        }
                    }
                }
            }
        } catch (Exception ex) {
            Warn.Mutant(ex);
        }
    }

    public static String causadeDaño(EntityDamageEvent e){

        switch (e.getCause()){
            case FALL:
                return "Caída";
            case FIRE:
                return "Fuego";
            case FREEZE:
                return "Congelado";
            case LAVA:
                return "Lava";
            case VOID:
                return "Vacio";
            case MAGIC:
                return "Magia";
            case BLOCK_EXPLOSION:
                return "Explosión";
            case POISON:
                return "Envenenado";
            case THORNS:
                return "Espinado";
            case WITHER:
                return "Efecto Wither";
            case CONTACT:
                return "Contacto";
            case CRAMMING:
                return "Cramming";
            case DRAGON_BREATH:
                return "Aliento del dragon";
            case DROWNING:
                return "Ahogado";
            case FIRE_TICK:
                return "Tick del Fuego";
            case HOT_FLOOR:
                return "Piso en llamas";
            case LIGHTNING:
                return "Rayo";
            case PROJECTILE:
                return "Proyectil";
            case STARVATION:
                return "Hambre";
            case SUFFOCATION:
                return "Sofocación";
            case ENTITY_SWEEP_ATTACK:
            case ENTITY_ATTACK:
                if(e instanceof EntityDamageByEntityEvent){
                    return "Ataque de " + ((EntityDamageByEntityEvent) e).getDamager().getName();
                }
            case FALLING_BLOCK:
                return "Caída de un bloque";
            case FLY_INTO_WALL:
                return "Volar en la pared";
            case ENTITY_EXPLOSION:
                if(e instanceof EntityDamageByEntityEvent){
                    return "Explosión de " + ((EntityDamageByEntityEvent) e).getDamager().getName();
                }
            default:
                return "Desconocida";
        }
    }
}
