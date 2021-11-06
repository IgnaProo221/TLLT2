package Eventos;

import Extras.Items;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityResurrectEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import tlldos.tll2.TLL2;

import java.util.Objects;
import java.util.Random;

public class alUsarTotem implements Listener {
    private TLL2 plugin;
    public alUsarTotem(TLL2 plugin){
        this.plugin = plugin;
    }
    @EventHandler
    public void totemDado(EntityResurrectEvent e){
        if (e.getEntity().getType() == (EntityType.PLAYER)) {
            Player p = (Player)e.getEntity();
            if(p.getInventory().getItemInMainHand().getType() == Material.TOTEM_OF_UNDYING || p.getInventory().getItemInOffHand().getType() == Material.TOTEM_OF_UNDYING) {
                int TotemCara = new Random().nextInt(12) + 1;
                if (p.hasCooldown(Material.TOTEM_OF_UNDYING)){
                    e.setCancelled(true);
                    for (Player players : Bukkit.getOnlinePlayers()){
                        players.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8El Cooldown del Totem del Jugador &6&l" + p.getName() + "&8 se a Activado!"));
                    }
                }
                if(p.getInventory().getItemInMainHand().equals(Items.ToteBeserk()) || p.getInventory().getItemInOffHand().equals(Items.ToteBeserk())){
                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 10.0F, 2.0F);
                    p.setCooldown(Material.TOTEM_OF_UNDYING, 200);
                    for(Player players : Bukkit.getOnlinePlayers()){
                        players.sendMessage(ChatColor.DARK_GRAY + "El Jugador " + ChatColor.RED + p.getName() + ChatColor.DARK_GRAY + " a usado un " + ChatColor.YELLOW + "Totem!♦" + ChatColor.GRAY + "  (Causa: " + causadeDaño(Objects.requireNonNull(p.getLastDamageCause())) + ChatColor.GRAY + ")" );
                        players.sendMessage(ChatColor.RED + "Los Totems de " + ChatColor.YELLOW + "" + ChatColor.BOLD + p.getName() + ChatColor.RED + " Entraron en Cooldown de 10 Segundos! ");
                        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                            @Override
                            public void run() {
                                p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100, 9));
                                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 4));
                            }
                        },10);
                    }
                    return;
                }
                if(TotemCara == 1){
                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 10.0F, 2.0F);
                    for(Player players : Bukkit.getOnlinePlayers()){
                        players.sendMessage(ChatColor.DARK_GRAY + "El Jugador " + ChatColor.RED + p.getName() + ChatColor.DARK_GRAY + " a usado un " + ChatColor.YELLOW + "Totem!♦" + ChatColor.GRAY + "  (Causa: " + causadeDaño(Objects.requireNonNull(p.getLastDamageCause())) + ChatColor.GRAY + ")" );
                        players.sendMessage(ChatColor.RED + "El Totem a caido en la cara " + ChatColor.YELLOW + "" + ChatColor.BOLD + "Numero 1!");
                        players.sendMessage(ChatColor.GRAY + "Efecto: " + ChatColor.RED + "Ningun Efecto");
                    }
                } else if (TotemCara == 2){
                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 10.0F, 2.0F);
                    for(Player players : Bukkit.getOnlinePlayers()){
                        players.sendMessage(ChatColor.DARK_GRAY + "El Jugador " + ChatColor.RED + p.getName() + ChatColor.DARK_GRAY + " a usado un " + ChatColor.YELLOW + "Totem!♦" + ChatColor.GRAY + "  (Causa: " + causadeDaño(Objects.requireNonNull(p.getLastDamageCause())) + ChatColor.GRAY + ")" );
                        players.sendMessage(ChatColor.RED + "El Totem a caido en la cara " + ChatColor.YELLOW + "" + ChatColor.BOLD + "Numero 2!");
                        players.sendMessage(ChatColor.GRAY + "Efecto: " + ChatColor.RED + "Veneno III por 10 segundos!");
                        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                            @Override
                            public void run() {
                                p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 400, 2));
                            }
                        },10);
                    }
                } else if (TotemCara == 3){
                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 10.0F, 2.0F);
                    for(Player players : Bukkit.getOnlinePlayers()){
                        players.sendMessage(ChatColor.DARK_GRAY + "El Jugador " + ChatColor.RED + p.getName() + ChatColor.DARK_GRAY + " a usado un " + ChatColor.YELLOW + "Totem!♦" + ChatColor.GRAY + "  (Causa: " + causadeDaño(Objects.requireNonNull(p.getLastDamageCause())) + ChatColor.GRAY + ")" );
                        players.sendMessage(ChatColor.RED + "El Totem a caido en la cara " + ChatColor.YELLOW + "" + ChatColor.BOLD + "Numero 3!");
                        players.sendMessage(ChatColor.GRAY + "Efecto: " + ChatColor.RED + "Slowness II por 20 segundos!");
                        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                            @Override
                            public void run() {
                                p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 400, 1));
                            }
                        },10);
                    }
                } else if (TotemCara == 4){
                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 10.0F, 2.0F);
                    for(Player players : Bukkit.getOnlinePlayers()){
                        players.sendMessage(ChatColor.DARK_GRAY + "El Jugador " + ChatColor.RED + p.getName() + ChatColor.DARK_GRAY + " a usado un " + ChatColor.YELLOW + "Totem!♦" + ChatColor.GRAY + "  (Causa: " + causadeDaño(Objects.requireNonNull(p.getLastDamageCause())) + ChatColor.GRAY + ")" );
                        players.sendMessage(ChatColor.RED + "El Totem a caido en la cara " + ChatColor.YELLOW + "" + ChatColor.BOLD + "Numero 4!");
                        players.sendMessage(ChatColor.GRAY + "Efecto: " + ChatColor.RED + "Wither V por 10 segundos!");
                        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                            @Override
                            public void run() {
                                p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 200, 4));
                            }
                        },10);
                    }
                } else if (TotemCara == 5){
                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 10.0F, 2.0F);
                    for(Player players : Bukkit.getOnlinePlayers()){
                        players.sendMessage(ChatColor.DARK_GRAY + "El Jugador " + ChatColor.RED + p.getName() + ChatColor.DARK_GRAY + " a usado un " + ChatColor.YELLOW + "Totem!♦" + ChatColor.GRAY + "  (Causa: " + causadeDaño(Objects.requireNonNull(p.getLastDamageCause())) + ChatColor.GRAY + ")" );
                        players.sendMessage(ChatColor.RED + "El Totem a caido en la cara " + ChatColor.YELLOW + "" + ChatColor.BOLD + "Numero 5!");
                        players.sendMessage(ChatColor.GRAY + "Efecto: " + ChatColor.RED + "Mining Fatigue V por 30 segundos!");
                        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                            @Override
                            public void run() {
                                p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 600, 4));
                            }
                        },10);
                    }
                } else if (TotemCara == 6){
                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 10.0F, 2.0F);
                    for(Player players : Bukkit.getOnlinePlayers()){
                        players.sendMessage(ChatColor.DARK_GRAY + "El Jugador " + ChatColor.RED + p.getName() + ChatColor.DARK_GRAY + " a usado un " + ChatColor.YELLOW + "Totem!♦" + ChatColor.GRAY + "  (Causa: " + causadeDaño(Objects.requireNonNull(p.getLastDamageCause())) + ChatColor.GRAY + ")" );
                        players.sendMessage(ChatColor.RED + "El Totem a caido en la cara " + ChatColor.YELLOW + "" + ChatColor.BOLD + "Numero 6!");
                        players.sendMessage(ChatColor.GRAY + "Efecto: " + ChatColor.RED + "Blindness  por 30 segundos!");
                        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                            @Override
                            public void run() {
                                p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 600, 0));
                            }
                        },10);
                    }
                } else if (TotemCara == 7){
                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 10.0F, 2.0F);
                    Creeper cr = (Creeper) p.getWorld().spawn(p.getLocation().clone(), Creeper.class);
                    cr.setPowered(true);
                    cr.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100000, 2));
                    for(Player players : Bukkit.getOnlinePlayers()){
                        players.sendMessage(ChatColor.DARK_GRAY + "El Jugador " + ChatColor.RED + p.getName() + ChatColor.DARK_GRAY + " a usado un " + ChatColor.YELLOW + "Totem!♦" + ChatColor.GRAY + "  (Causa: " + causadeDaño(Objects.requireNonNull(p.getLastDamageCause())) + ChatColor.GRAY + ")" );
                        players.sendMessage(ChatColor.RED + "El Totem a caido en la cara " + ChatColor.YELLOW + "" + ChatColor.BOLD + "Numero 7!");
                        players.sendMessage(ChatColor.GRAY + "Efecto: " + ChatColor.RED + "Creeper Cargado en su Posicion");
                    }
                }else if (TotemCara == 8){
                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 10.0F, 2.0F);
                    for(Player players : Bukkit.getOnlinePlayers()){
                        players.sendMessage(ChatColor.DARK_GRAY + "El Jugador " + ChatColor.RED + p.getName() + ChatColor.DARK_GRAY + " a usado un " + ChatColor.YELLOW + "Totem!♦" + ChatColor.GRAY + "  (Causa: " + causadeDaño(Objects.requireNonNull(p.getLastDamageCause())) + ChatColor.GRAY + ")" );
                        players.sendMessage(ChatColor.RED + "El Totem a caido en la cara " + ChatColor.YELLOW + "" + ChatColor.BOLD + "Numero 8!");
                        players.sendMessage(ChatColor.GRAY + "Efecto: " + ChatColor.RED + "Hambre III por 3 minutos y Instant Damage III");
                        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                            @Override
                            public void run() {
                                p.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 3600, 2));
                                p.addPotionEffect(new PotionEffect(PotionEffectType.HARM, 1, 2));
                            }
                        },10);
                    }
                }else if (TotemCara == 9){
                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 10.0F, 2.0F);
                    for(Player players : Bukkit.getOnlinePlayers()){
                        players.sendMessage(ChatColor.DARK_GRAY + "El Jugador " + ChatColor.RED + p.getName() + ChatColor.DARK_GRAY + " a usado un " + ChatColor.YELLOW + "Totem!♦" + ChatColor.GRAY + "  (Causa: " + causadeDaño(Objects.requireNonNull(p.getLastDamageCause())) + ChatColor.GRAY + ")" );
                        players.sendMessage(ChatColor.RED + "El Totem a caido en la cara " + ChatColor.YELLOW + "" + ChatColor.BOLD + "Numero 9!");
                        players.sendMessage(ChatColor.GRAY + "Efecto: " + ChatColor.RED + "Weakness I por 3 minutos y Instant Damage III");
                        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                            @Override
                            public void run() {
                                p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 3600, 0));
                                p.addPotionEffect(new PotionEffect(PotionEffectType.HARM, 1, 2));
                            }
                        },10);
                    }
                }else if (TotemCara == 10){
                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 10.0F, 2.0F);
                    Ghast gh = (Ghast) p.getWorld().spawn(p.getLocation().clone(), Ghast.class);
                    gh.setCustomName(ChatColor.GOLD + "Undying Ghast");
                    gh.setMaxHealth(50);
                    gh.setHealth(50);

                    for(Player players : Bukkit.getOnlinePlayers()){
                        players.sendMessage(ChatColor.DARK_GRAY + "El Jugador " + ChatColor.RED + p.getName() + ChatColor.DARK_GRAY + " a usado un " + ChatColor.YELLOW + "Totem!♦" + ChatColor.GRAY + "  (Causa: " + causadeDaño(Objects.requireNonNull(p.getLastDamageCause())) + ChatColor.GRAY + ")" );
                        players.sendMessage(ChatColor.RED + "El Totem a caido en la cara " + ChatColor.YELLOW + "" + ChatColor.BOLD + "Numero 10!");
                        players.sendMessage(ChatColor.GRAY + "Efecto: " + ChatColor.RED + "Undying Ghast en su Posicion!");
                    }
                }
                else if (TotemCara == 11){
                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 10.0F, 2.0F);
                    p.getWorld().spawnEntity(p.getLocation(), EntityType.VEX);
                    p.getWorld().spawnEntity(p.getLocation(), EntityType.VEX);
                    p.getWorld().spawnEntity(p.getLocation(), EntityType.VEX);
                    p.getWorld().spawnEntity(p.getLocation(), EntityType.VEX);
                    p.getWorld().spawnEntity(p.getLocation(), EntityType.VEX);
                    p.getWorld().spawnEntity(p.getLocation(), EntityType.VEX);
                    p.getWorld().spawnEntity(p.getLocation(), EntityType.VEX);
                    p.getWorld().spawnEntity(p.getLocation(), EntityType.VEX);
                    p.getWorld().spawnEntity(p.getLocation(), EntityType.VEX);
                    p.getWorld().spawnEntity(p.getLocation(), EntityType.VEX);
                    for(Player players : Bukkit.getOnlinePlayers()){
                        players.sendMessage(ChatColor.DARK_GRAY + "El Jugador " + ChatColor.RED + p.getName() + ChatColor.DARK_GRAY + " a usado un " + ChatColor.YELLOW + "Totem!♦" + ChatColor.GRAY + "  (Causa: " + causadeDaño(Objects.requireNonNull(p.getLastDamageCause())) + ChatColor.GRAY + ")" );
                        players.sendMessage(ChatColor.RED + "El Totem a caido en la cara " + ChatColor.YELLOW + "" + ChatColor.BOLD + "Numero 11!");
                        players.sendMessage(ChatColor.GRAY + "Efecto: " + ChatColor.RED + "10 Vexes en su Posicion");
                    }
                }
                else if (TotemCara == 12){
                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 10.0F, 2.0F);
                    p.setCooldown(Material.TOTEM_OF_UNDYING, 100);
                    for(Player players : Bukkit.getOnlinePlayers()){
                        players.sendMessage(ChatColor.DARK_GRAY + "El Jugador " + ChatColor.RED + p.getName() + ChatColor.DARK_GRAY + " a usado un " + ChatColor.YELLOW + "Totem!♦" + ChatColor.GRAY + "  (Causa: " + causadeDaño(Objects.requireNonNull(p.getLastDamageCause())) + ChatColor.GRAY + ")" );
                        players.sendMessage(ChatColor.RED + "El Totem a caido en la cara " + ChatColor.YELLOW + "" + ChatColor.BOLD + "Numero 12!");
                        players.sendMessage(ChatColor.GRAY + "Efecto: " + ChatColor.DARK_RED + "" + ChatColor.BOLD + "Cooldown de Totem por 5 segundos");
                    }
                }
            }
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
