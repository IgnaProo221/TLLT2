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

                    if (hasUnluckyIdol(p)) {
                        int needTotems = 4;
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
                            for(Player player : Bukkit.getOnlinePlayers()){
                                player.sendMessage(format("&8El jugador &c&l" + p.getName() + "&8 no tenia suficientes&c tótems &8en su inventario. &7(&6&l" + size + "&7/&6&l" + needTotems + "&7)&8! &7(Causa: " + causadeDaño(Objects.requireNonNull(p.getLastDamageCause())) + "&7)"));
                            }
                        } else {

                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    p.playSound(p.getLocation(), Sound.ITEM_TOTEM_USE, 5.0F, 1.0F);
                                    p.playEffect(EntityEffect.TOTEM_RESURRECT);
                                }
                            }.runTaskLater(TLL2.getPlugin(TLL2.class), 20L);
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    p.playSound(p.getLocation(), Sound.ITEM_TOTEM_USE, 5.0F, 1.0F);
                                    p.playEffect(EntityEffect.TOTEM_RESURRECT);
                                }
                            }.runTaskLater(TLL2.getPlugin(TLL2.class), 40L);
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    p.playSound(p.getLocation(), Sound.ITEM_TOTEM_USE, 5.0F, 1.0F);
                                    p.playEffect(EntityEffect.TOTEM_RESURRECT);
                                }
                            }.runTaskLater(TLL2.getPlugin(TLL2.class), 60L);

                            p.getInventory().removeItem(new ItemStack(Material.TOTEM_OF_UNDYING, 3));
                            for (Player player : Bukkit.getOnlinePlayers()){
                                player.sendMessage(format("&8El Jugador &c&l" + p.getName() + " &8uso un &e&lTótem Especial &7(Totem: &c&lUnlucky Idol) &7(Causa: "  + causadeDaño(Objects.requireNonNull(p.getLastDamageCause())) + "&7)"));
                            }
                            EmbedBuilder eb = new EmbedBuilder();
                            TextChannel channel = DeathListeners.getJda().getTextChannelById("949669572179533854");

                            eb.setFooter("TheLastLifeT2.jar", "https://cdn.discordapp.com/attachments/906642578013843526/943284426442436679/hardcorehearth-export.png");
                            eb.setAuthor("The Last Life T2 | Servidor de Minecraft");

                            eb.setTitle("**¡El jugador " + p.getName() + " ha usado un Ídolo del Infortunio!**");

                            eb.addField(":skull: **Causa: **", causadeDaño(Objects.requireNonNull(p.getLastDamageCause())), true);
                            eb.addField(":beginner: **Día: **", "" + Utils.getDay(), true);
                            eb.addField(":map: **Coordenadas:**", "X: " + p.getLocation().getBlockX() + " | Y: " + p.getLocation().getBlockY() + " | Z: " + p.getLocation().getBlockZ(), true);

                            eb.setThumbnail("https://cdn.discordapp.com/attachments/906642578013843526/949674264490045480/dado3.png");

                            eb.setColor(new Color(252, 186, 3));

                            if (channel != null) {
                                channel.sendMessage(eb.build()).queue();
                            }
                        }
                        return;
                    }


                    if (head == 5) {
                        int needTotems = 3;
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
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    p.playSound(p.getLocation(), Sound.ITEM_TOTEM_USE, 5.0F, 1.0F);
                                    p.playEffect(EntityEffect.TOTEM_RESURRECT);
                                }
                            }.runTaskLater(TLL2.getPlugin(TLL2.class), 40L);

                            p.getInventory().removeItem(new ItemStack(Material.TOTEM_OF_UNDYING, 2));


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




    public void totemeffects(int head, Player p, Player players) {
        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 10.0F, 2.0F);
        if (head == 1){
            players.sendMessage(ChatColor.DARK_GRAY + "Efecto: " + ChatColor.AQUA + "" + ChatColor.BOLD + "Efectos Destructivos");
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                p.removePotionEffect(PotionEffectType.ABSORPTION);
                p.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
                p.removePotionEffect(PotionEffectType.REGENERATION);
                p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,400,1,true,false,true));
                p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING,400,1,true,false,true));
                p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,400,1,true,false,true));
                p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION,400,1,true,false,true));
                p.addPotionEffect(new PotionEffect(PotionEffectType.UNLUCK,400,0,true,false,true));
            }, 5L);

        } else if (head == 2) {
            players.sendMessage(ChatColor.DARK_GRAY + "Efecto: " + ChatColor.AQUA + "" + ChatColor.BOLD + "Bad Omen V permanente");
            Bukkit.getScheduler().runTaskLater(plugin,()->{
                p.addPotionEffect(new PotionEffect(PotionEffectType.BAD_OMEN,Integer.MAX_VALUE,4,true,false,true));
            },5L);
        } else if (head == 3) {
            players.sendMessage(ChatColor.DARK_GRAY + "Efecto: " + ChatColor.AQUA + "" + ChatColor.BOLD + "Mob Random.");
            SpawnListeners.spawnRandomMob(p.getLocation());
        } else if (head == 4) {
            players.sendMessage(ChatColor.DARK_GRAY + "Efecto: " + ChatColor.AQUA + "" + ChatColor.BOLD + "Cooldown Universal");
            p.setCooldown(Material.GOLDEN_APPLE,200);
            p.setCooldown(Material.ENCHANTED_GOLDEN_APPLE,200);
            p.setCooldown(Material.ENDER_PEARL,200);
            p.setCooldown(Material.SHIELD,200);
        } else if (head == 5) {
            players.sendMessage(ChatColor.DARK_GRAY + "Efecto: " + ChatColor.AQUA + "" + ChatColor.BOLD + "Usar 3 tótems");
        } else {
            if (head > 6) {
                Bukkit.getLogger().info("El dado ha caído en una cara mayor al limite (6).");
                return;
            }

            players.sendMessage(ChatColor.DARK_GRAY + "Efecto: " + ChatColor.AQUA + "" + ChatColor.BOLD + "CotM 3 por 1 minuto");
            Bukkit.getScheduler().runTaskLater(plugin,()->{
                p.addPotionEffect(new PotionEffect(PotionEffectType.UNLUCK,1200,2,true,false,true));
            },5L);

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
            eb.addField(":game_die: **Número**", "1.- Varios Efectos Contrarios", true);
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
            eb.addField(":game_die: **Número**", "2.- Bad Omen V infinito", true);
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
            eb.addField(":game_die: **Número**", "3.- Mob random en tu posicion", true);

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
            eb.addField(":game_die: **Número**", "4.- Cooldown Universal", true);

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
            eb.addField(":game_die: **Número**", "5.- Usas 3 totems en vez de 1", true);

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
            eb.addField(":game_die: **Número**", "6.- CotM 3 por 1 minuto", true);

            eb.setThumbnail("https://cdn.discordapp.com/attachments/906642578013843526/949674451472097310/dado6.png");

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

    public boolean hasUnluckyIdol(Player p){
        boolean has = false;
        if(p.getInventory().getItemInMainHand() != null){
            if (p.getInventory().getItemInMainHand().getType() == Material.TOTEM_OF_UNDYING && (p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 28171219)) {
                has = true;
            }
        }
        if(p.getInventory().getItemInOffHand() != null){
            if (p.getInventory().getItemInOffHand().getType() == Material.TOTEM_OF_UNDYING && (p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getItemMeta().getCustomModelData() == 28171219)) {
                has = true;
            }
        }
        return has;
    }

}
