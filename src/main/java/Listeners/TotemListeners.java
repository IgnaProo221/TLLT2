package Listeners;

import Extras.Items;
import Utilities.*;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
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
import player.CustomPlayer;
import player.PlayerData;
import tlldos.tll2.TLL2;

import java.awt.Color;
import java.util.Objects;
import java.util.Random;
import static Utilities.Format.format;

public class TotemListeners implements Listener {
    private final TLL2 plugin;

    public TotemListeners(TLL2 plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void totemDado(EntityResurrectEvent e) {
        try {
            if (e.getEntity().getType() == (EntityType.PLAYER)) {

                Player p = (Player) e.getEntity();


                if (p.getInventory().getItemInMainHand().getType() == Material.TOTEM_OF_UNDYING || p.getInventory().getItemInOffHand().getType() == Material.TOTEM_OF_UNDYING) {

                    //TotemsBar.anadirTC(p);
                    /*if(TotemsBar.getPorcentaje(p) == 200){
                        e.setCancelled(true);
                        Bukkit.getOnlinePlayers().forEach(player -> {
                            player.sendMessage(Format.PREFIX + format("&8&l¡Los Totems del Jugador " + p.getName() + " se han desactivado y ha muerto! &c&l(Su Porcentaje de Totems esta en 0%)"));

                        });
                        return;
                    }*/

                    /*if (!data.has(Utils.key("TOTEM_BAR"), PersistentDataType.INTEGER)) {
                        data.set(Utils.key("TOTEM_BAR"), PersistentDataType.INTEGER, 100);
                    } else {
                        int i = data.get(Utils.key("TOTEM_BAR"), PersistentDataType.INTEGER);
                        data.set(Utils.key("TOTEM_BAR"), PersistentDataType.INTEGER, i - 5);
                    }
                    if (data.get(Utils.key("TOTEM_BAR"), PersistentDataType.INTEGER) <= 0) {
                        e.setCancelled(true);

                        for (Player players : Bukkit.getOnlinePlayers()) {
                            players.sendMessage(Format.PREFIX + format("&8&l¡Los tótems del jugador " + p.getName() + " se han desactivado y ha muerto! &c&l(Su porcentaje de tótems esta en 0%)."));
                        }
                        return;
                    }*/

                    CustomPlayer customPlayer = CustomPlayer.fromName(p.getName());
                    PlayerData data = customPlayer.getData();


                    if (p.hasCooldown(Material.TOTEM_OF_UNDYING)) {

                        e.setCancelled(true);

                        for (Player players : Bukkit.getOnlinePlayers()) {

                            players.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8¡El cooldown del tótem del jugador &6&l" + p.getName() + "&8 se ha activado!"));

                        }
                        return;
                    }


                    // Sistema de dados

                    int head = new Random().nextInt(6);

                    String globalMessage = format("&8¡El jugador &c&l"+ p.getName() + "&8 ha usado un&c tótem! &7(Causa: " + causadeDaño(Objects.requireNonNull(p.getLastDamageCause())) + "&7)");

                    if (hasLavaSigil(p)) {

                        for (Entity living : p.getNearbyEntities(10, 10, 10)) {
                            if (!(living instanceof Player || living instanceof Item || living instanceof Villager || living instanceof IronGolem || living instanceof ArmorStand || living instanceof ItemFrame)) {
                                living.setFireTicks(1200);
                            }
                        }

                        globalMessage = format("&8El Jugador &c&l" + p.getName() + " &8uso un &e&lTótem Especial &7(Totem: &6&lSigilo de Magma)");

                    }

                    if (head == 5) {
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


                        if (needTotems > size) {
                            e.setCancelled(true);
                            globalMessage = format("&8El jugador &c&l" + p.getName() + "&8 no tenia suficientes&c tótems &8en su inventario. &7(&6&l" + size + "&7/&6&l" + needTotems + "&7)&8! &7(Causa: " + causadeDaño(Objects.requireNonNull(p.getLastDamageCause())) + "&7)");
                        } else {

                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    p.playSound(p.getLocation(), Sound.ITEM_TOTEM_USE, 5.0F, 1.0F);
                                    p.playEffect(EntityEffect.TOTEM_RESURRECT);
                                }
                            }.runTaskLater(TLL2.getPlugin(TLL2.class), 20L);

                            p.getInventory().removeItem(new ItemStack(Material.TOTEM_OF_UNDYING, 1));


                            globalMessage = format("&8¡El jugador &c&l"+ p.getName() + "&8 ha usado " + needTotems + "&c tótems! &7(Causa: " + causadeDaño(Objects.requireNonNull(p.getLastDamageCause())) + "&7)");
                        }

                    }


                    String totemMsg = globalMessage;
                    diceBot(head, p);
                    for(Player players : Bukkit.getOnlinePlayers()){
                        players.sendMessage(totemMsg);
                        players.sendMessage(format("&7El dado ha caído en la cara&5&l número " + head));
                        totemeffects(head, p, players);
                    }
                }
            }
        } catch (Exception ex) {
            Warn.Mutant(ex);
        }
    }


    public boolean hasLavaSigil(Player p){
        boolean has = false;
        if(p.getInventory().getItemInMainHand() != null){
            if (p.getInventory().getItemInMainHand().getType() == Material.TOTEM_OF_UNDYING && (p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 901823)) {
                has = true;
            }
        }
        if(p.getInventory().getItemInOffHand() != null){
            if (p.getInventory().getItemInOffHand().getType() == Material.TOTEM_OF_UNDYING && (p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getItemMeta().getCustomModelData() == 901823)) {
                has = true;
            }
        }
        return has;
    }

    public void totemeffects(int head, Player p, Player players) {
        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 10.0F, 2.0F);
        if (head == 1){


        } else if (head == 2) {
            players.sendMessage(ChatColor.DARK_GRAY + "Efecto: " + ChatColor.AQUA + "" + ChatColor.BOLD + "Speed II por 10 segundos.");
            Bukkit.getScheduler().runTaskLater(plugin, () -> p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 1)), 10);
        } else if (head == 3) {
            players.sendMessage(ChatColor.DARK_GRAY + "Efecto: " + ChatColor.AQUA + "" + ChatColor.BOLD + "Fuerza y Haste II por 5 segundos.");

            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100, 1));
                p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 100, 1));
            }, 10);

        } else if (head == 4) {
            players.sendMessage(ChatColor.DARK_GRAY + "Efecto: " + ChatColor.AQUA + "" + ChatColor.BOLD + "Wither, Slowness y Veneno II por 10 segundos.");

            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 200, 1));
                p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 1));
                p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 200, 1));
            }, 10);

        } else if (head == 5) {

            players.sendMessage(ChatColor.DARK_GRAY + "Efecto: " + ChatColor.AQUA + "" + ChatColor.BOLD + "Usar otro tótem en tu inventario.");


        } else {
            if (head > 6) {
                Bukkit.getLogger().info("El dado ha caído en una cara mayor al limite (6).");
                return;
            }

            players.sendMessage(ChatColor.DARK_GRAY + "Efecto: " + ChatColor.AQUA + "" + ChatColor.BOLD + "No se aplican los efectos positivos");


            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                p.removePotionEffect(PotionEffectType.ABSORPTION);
                p.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
                p.removePotionEffect(PotionEffectType.REGENERATION);
            }, 5L);

        }
    }


    public void diceBot(int head, Player p) {
        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 10.0F, 2.0F);
        if (head == 1) {
            EmbedBuilder eb = new EmbedBuilder();
            TextChannel channel = DeathListeners.getJda().getTextChannelById("949669572179533854");

            eb.setFooter("TheLastLifeT2.jar", "https://cdn.discordapp.com/attachments/906642578013843526/943284426442436679/hardcorehearth-export.png");
            eb.setAuthor("The Last Life T2 | Servidor de Minecraft");
            eb.setTitle("**¡El jugador " + p.getName() + " ha usado un Tótem!**");
            eb.addField(":skull: **Causa: **", causadeDaño(Objects.requireNonNull(p.getLastDamageCause())), true);
            eb.addField(":beginner: **Día: **", "" + Utils.getDay(), true);
            eb.addField(":map: **Coordenadas:**", "X: " + p.getLocation().getBlockX() + " | Y: " + p.getLocation().getBlockY() + " | Z: " + p.getLocation().getBlockZ(), true);
            eb.addField(":game_die: **Numero**", "1.- Sin Efectos", true);
            eb.setThumbnail("https://media.discordapp.net/attachments/906642578013843526/949674113314738236/dado1.png");
            eb.setColor(new Color(252, 186, 3));
            if (channel != null) {
                channel.sendMessage(eb.build()).queue();
            }

        } else if (head == 2) {
            EmbedBuilder eb = new EmbedBuilder();
            TextChannel channel = DeathListeners.getJda().getTextChannelById("949669572179533854");

            eb.setFooter("TheLastLifeT2.jar", "https://cdn.discordapp.com/attachments/906642578013843526/943284426442436679/hardcorehearth-export.png");
            eb.setAuthor("The Last Life T2 | Servidor de Minecraft");
            eb.setTitle("**¡El jugador " + p.getName() + " ha usado un Tótem!**");
            eb.addField(":skull: **Causa: **", causadeDaño(Objects.requireNonNull(p.getLastDamageCause())), true);
            eb.addField(":beginner: **Día: **", "" + Utils.getDay(), true);
            eb.addField(":map: **Coordenadas:**", "X: " + p.getLocation().getBlockX() + " | Y: " + p.getLocation().getBlockY() + " | Z: " + p.getLocation().getBlockZ(), true);
            eb.addField(":game_die: **Numero**", "2.- Speed II por 10 segundos", true);
            eb.setThumbnail("https://cdn.discordapp.com/attachments/906642578013843526/949674197188214824/dado2.png");
            eb.setColor(new Color(252, 186, 3));
            if (channel != null) {
                channel.sendMessage(eb.build()).queue();
            }
        } else if (head == 3) {
            EmbedBuilder eb = new EmbedBuilder();
            TextChannel channel = DeathListeners.getJda().getTextChannelById("949669572179533854");

            eb.setFooter("TheLastLifeT2.jar", "https://cdn.discordapp.com/attachments/906642578013843526/943284426442436679/hardcorehearth-export.png");
            eb.setAuthor("The Last Life T2 | Servidor de Minecraft");

            eb.setTitle("**¡El jugador " + p.getName() + " ha usado un Tótem!**");

            eb.addField(":skull: **Causa: **", causadeDaño(Objects.requireNonNull(p.getLastDamageCause())), true);
            eb.addField(":beginner: **Día: **", "" + Utils.getDay(), true);
            eb.addField(":map: **Coordenadas:**", "X: " + p.getLocation().getBlockX() + " | Y: " + p.getLocation().getBlockY() + " | Z: " + p.getLocation().getBlockZ(), true);
            eb.addField(":game_die: **Numero**", "3.- Fuerza y Haste II por 5 segundos", true);

            eb.setThumbnail("https://cdn.discordapp.com/attachments/906642578013843526/949674264490045480/dado3.png");

            eb.setColor(new Color(252, 186, 3));

            if (channel != null) {
                channel.sendMessage(eb.build()).queue();
            }
        } else if (head == 4) {
            EmbedBuilder eb = new EmbedBuilder();

            TextChannel channel = DeathListeners.getJda().getTextChannelById("949669572179533854");

            eb.setFooter("TheLastLifeT2.jar", "https://cdn.discordapp.com/attachments/906642578013843526/943284426442436679/hardcorehearth-export.png");
            eb.setAuthor("The Last Life T2 | Servidor de Minecraft");

            eb.setTitle("**¡El jugador " + p.getName() + " ha usado un Tótem!**");

            eb.addField(":skull: **Causa: **", causadeDaño(Objects.requireNonNull(p.getLastDamageCause())), true);
            eb.addField(":beginner: **Día: **", "" + Utils.getDay(), true);
            eb.addField(":map: **Coordenadas:**", "X: " + p.getLocation().getBlockX() + " | Y: " + p.getLocation().getBlockY() + " | Z: " + p.getLocation().getBlockZ(), true);
            eb.addField(":game_die: **Numero**", "4.- Wither, Slowness y Veneno II por 10 segundos", true);

            eb.setThumbnail("https://cdn.discordapp.com/attachments/906642578013843526/949674332517441586/dado4.png");

            eb.setColor(new Color(252, 186, 3));

            if (channel != null) {
                channel.sendMessage(eb.build()).queue();
            }

        } else if (head == 5) {

            EmbedBuilder eb = new EmbedBuilder();

            TextChannel channel = DeathListeners.getJda().getTextChannelById("949669572179533854");

            eb.setFooter("TheLastLifeT2.jar", "https://cdn.discordapp.com/attachments/906642578013843526/943284426442436679/hardcorehearth-export.png");
            eb.setAuthor("The Last Life T2 | Servidor de Minecraft");

            eb.setTitle("**¡El jugador " + p.getName() + " ha usado un Tótem!**");

            eb.addField(":skull: **Causa: **", causadeDaño(Objects.requireNonNull(p.getLastDamageCause())), true);
            eb.addField(":beginner: **Día: **", "" + Utils.getDay(), true);
            eb.addField(":map: **Coordenadas:**", "X: " + p.getLocation().getBlockX() + " | Y: " + p.getLocation().getBlockY() + " | Z: " + p.getLocation().getBlockZ(), true);
            eb.addField(":game_die: **Numero**", "5.- Usar otro totem en tu Inventario", true);

            eb.setThumbnail("https://cdn.discordapp.com/attachments/906642578013843526/949674389157326908/dado5.png");

            eb.setColor(new Color(252, 186, 3));

            if (channel != null) {
                channel.sendMessage(eb.build()).queue();
            }

        } else {
            if (head > 6) {
                Bukkit.getLogger().info("El dado ha caído en una cara mayor al limite (6).");
                return;
            }
            EmbedBuilder eb = new EmbedBuilder();

            TextChannel channel = DeathListeners.getJda().getTextChannelById("949669572179533854");

            eb.setFooter("TheLastLifeT2.jar", "https://cdn.discordapp.com/attachments/906642578013843526/943284426442436679/hardcorehearth-export.png");
            eb.setAuthor("The Last Life T2 | Servidor de Minecraft");

            eb.setTitle("**¡El jugador " + p.getName() + " ha usado un Tótem!**");

            eb.addField(":skull: **Causa: **", causadeDaño(Objects.requireNonNull(p.getLastDamageCause())), true);
            eb.addField(":beginner: **Día: **", "" + Utils.getDay(), true);
            eb.addField(":map: **Coordenadas:**", "X: " + p.getLocation().getBlockX() + " | Y: " + p.getLocation().getBlockY() + " | Z: " + p.getLocation().getBlockZ(), true);
            eb.addField(":game_die: **Numero**", "6.- Sin efectos del Totem", true);

            eb.setThumbnail("https://cdn.discordapp.com/attachments/906642578013843526/949674451472097310/dado6.pn");

            eb.setColor(new Color(252, 186, 3));

            if (channel != null) {
                channel.sendMessage(eb.build()).queue();
            }
        }
    }

    public static String causadeDaño(EntityDamageEvent e) {

        switch (e.getCause()) {
            case FALL:
                return "Caída";
            case FIRE:
                return "Fuego";
            case FREEZE:
                return "Congelado";
            case LAVA:
                return "Lava";
            case VOID:
                return "Vacío";
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
                return "Aliento del dragón";
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
                if (e instanceof EntityDamageByEntityEvent) {
                    return "Ataque de " + ((EntityDamageByEntityEvent) e).getDamager().getName();
                }
            case FALLING_BLOCK:
                return "Caída de un bloque";
            case FLY_INTO_WALL:
                return "Volar en la pared";
            case ENTITY_EXPLOSION:
                if (e instanceof EntityDamageByEntityEvent) {
                    return "Explosión de " + ((EntityDamageByEntityEvent) e).getDamager().getName();
                }
            default:
                return "Desconocida";
        }
    }
    public boolean hasCustomModelData(Player p){
        return ((p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) || (p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData()));
    }

}
