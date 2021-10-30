package Eventos;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Skull;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import tlldos.tll2.TLL2;

import javax.security.auth.login.LoginException;
import java.awt.Color;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;
import java.util.UUID;

public class Muerte extends ListenerAdapter implements Listener {
    private World world;
    public static BossBar tormenta;
    private TLL2 plugin;
    private JDA jda;
    public Muerte(TLL2 plugin){
        this.plugin = plugin;
        world = Bukkit.getWorld("world");
        long segundos = (long) (world.getWeatherDuration() / 20);
        long hours = segundos  / 1800L;
        long minutes = segundos % 1800L / 60L;
        long seconds = segundos % 60L;
        String time = String.format("%02d:%02d:%02d", hours, minutes, seconds);

        tormenta = Bukkit.createBossBar(ChatColor.translateAlternateColorCodes('&', "&f♥        &6&lBlast Storm: " + time +  "        &f♥"), BarColor.YELLOW , BarStyle.SEGMENTED_6);
        try {
            this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
            jda = JDABuilder.createDefault("ODM0MTI1Mjg3NDUyNzA0Nzc5.YH8VtQ.353ChgCa7fFCqi3rq-vIOWimrMg").build();
            jda.getPresence().setActivity(Activity.watching("⚡ The Last Life SMP T2 Server ⚡"));
            jda.addEventListener(this);
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message msg = event.getMessage();
        if (msg.getContentRaw().equals("tll!moai")) {
            MessageChannel channel = event.getChannel();
            channel.sendMessage(":moyai::moyai::moyai:").queue();
        }
        if (msg.getContentRaw().equals("tll!jugadores")) {
            EmbedBuilder eb = new EmbedBuilder();
            eb.setFooter("TheLastLifeT2.jar", "https://media.discordapp.net/attachments/830482526237753395/874379476212019210/transparentexd.png?width=588&height=588");
            eb.setAuthor("The Last Life T2 | Servidor de Minecraft");
            eb.setTitle("Jugadores Conectados:");
            StringBuilder jugadores = new StringBuilder();
            for (Player players : Bukkit.getOnlinePlayers()) {
                if (jugadores.length() > 0) {
                    jugadores.append('\n');
                }
                jugadores.append(players.getName());
            }
            eb.setDescription(jugadores.length() > 0 ? jugadores : "No hay Jugadores Conectados");
            eb.setColor(new Color(252, 186, 3));
            msg.getChannel().sendMessage(eb.build()).queue();
        }
        if (msg.getContentRaw().equals("tll!tormenta")) {
            World world = Bukkit.getWorld("world");
            long segundos = (long) (world.getWeatherDuration() / 20);
            long hours = segundos / 1800L;
            long minutes = segundos % 1800L / 60L;
            long seconds = segundos % 60L;

            String time = String.format("%02d:%02d:%02d", hours, minutes, seconds);

            EmbedBuilder eb = new EmbedBuilder();
            eb.setFooter("TheLastLifeT2.jar", "https://media.discordapp.net/attachments/830482526237753395/874379476212019210/transparentexd.png?width=588&height=588");
            eb.setAuthor("The Last Life T2 | Servidor de Minecraft");
            eb.setTitle("Duracion de BlastStorm");
            eb.setDescription((segundos > 0) ? "La Duracion de la BlastStorm Actual es: " + time : "No Hay una Blast Storm Activa!");
            eb.setThumbnail("https://media.discordapp.net/attachments/830482526237753395/888118123696373790/blaststorm_icon.png");
            eb.setColor(new Color(252, 186, 3));
            msg.getChannel().sendMessage(eb.build()).queue();
        }

    }

    @EventHandler
    public void muerteEvento(PlayerDeathEvent e){
        Player p = e.getEntity();
        World world = Bukkit.getWorld("world");
        for (Player players : Bukkit.getOnlinePlayers()){
            players.playSound(players.getLocation(), Sound.ENTITY_ILLUSIONER_PREPARE_MIRROR, 10.0F, -1.0F);
            players.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 500, 0, true, false,true));
            players.sendTitle(ChatColor.GOLD + "" + ChatColor.MAGIC + "TheLastLife", ChatColor.DARK_GRAY + "" + ChatColor.MAGIC + "the end is near", 0, 80, 0);
            world.setTime(4000);
            players.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8La Alma de &6&l" + p.getName() + " &8a desaparecido entre la oscuridad eterna del &8&lVacio!, &8&lsu energia se liberara para iniciar &6la &6&lBLAST STORM!"));
            players.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Fatum tuum non potes effugere, &c&lsuperesse vel perit"));
            players.sendMessage(ChatColor.GRAY + "Coordenadas: X: " + p.getLocation().getBlockX() + ", Y: " + p.getLocation().getBlockY() + ", Z: " + p.getLocation().getBlockZ());
            players.sendMessage(ChatColor.GRAY + "Dimension: " +  p.getWorld().getEnvironment());
            Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                @Override
                public void run() {
                    players.sendTitle(ChatColor.GOLD + "" + ChatColor.BOLD + "The End Begins...", ChatColor.DARK_GRAY + "" + ChatColor.MAGIC + "the end is near", 0, 80, 0);
                    players.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 80, 1, false, false, false));
                    players.playSound(players.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_DEATH, 10.0F, -1.0F);
                    world.setTime(5000);
                    cabezaEstructura(p, p.getLocation());
                }
            }, 40);
            Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                @Override
                public void run() {
                    players.sendTitle(ChatColor.GOLD + "" + ChatColor.BOLD + "The Light Fades...", ChatColor.DARK_GRAY + "" + ChatColor.MAGIC + "the end is near", 0, 80, 0);
                    players.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 80, 1, false, false, false));
                    players.playSound(players.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_AMBIENT, 10.0F, -1.0F);
                    world.setTime(6000);

                }
            }, 80);
            Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                @Override
                public void run() {
                    players.sendTitle(ChatColor.GOLD + "" + ChatColor.BOLD + "No One is Safe...", ChatColor.DARK_GRAY + "" + ChatColor.MAGIC + "the end is near", 0, 80, 0);
                    players.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 80, 1, false, false, false));
                    players.playSound(players.getLocation(), Sound.ENTITY_GUARDIAN_AMBIENT, 10.0F, -1.0F);
                    world.setTime(7000);
                }
            }, 120);
            Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                @Override
                public void run() {
                    players.sendTitle(ChatColor.GOLD + "" + ChatColor.BOLD + "You can´t Escape", ChatColor.DARK_GRAY + "" + ChatColor.MAGIC + "the end is near", 0, 80, 0);
                    players.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 80, 1, false, false, false));
                    players.playSound(players.getLocation(), Sound.ENTITY_WITHER_AMBIENT, 10.0F, -1.0F);
                    world.setTime(8000);
                }
            }, 160);
            Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                @Override
                public void run() {
                    players.sendTitle(ChatColor.GOLD + "" + ChatColor.BOLD + "Its Over", ChatColor.DARK_GRAY + "" + ChatColor.MAGIC + "the end is near", 0, 80, 0);
                    players.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 80, 1, false, false, false));
                    players.playSound(players.getLocation(), Sound.ENTITY_PHANTOM_DEATH, 10.0F, -1.0F);
                    world.setTime(9000);
                }
            }, 200);
            Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                @Override
                public void run() {
                    players.sendTitle(ChatColor.translateAlternateColorCodes('&', "&4&kzzz &6&lTHE LAST LIFE &4&kzzz"), ChatColor.translateAlternateColorCodes('&', "&8El Fin del Jugador " + p.getName() + " &8Comienza!"), 0, 160, 20);
                    players.playSound(players.getLocation(), Sound.BLOCK_END_PORTAL_SPAWN, 10.0F, -1.0F);
                    players.sendActionBar(ChatColor.GOLD + "" + ChatColor.BOLD + e.getDeathMessage());
                    world.setTime(10000);
                }
            }, 240);
            Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                @Override
                public void run() {
                    world.setTime(11000);
                }
            }, 320);
            Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                @Override
                public void run() {
                    players.playSound(players.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, 20.0F, -1.0F);
                    players.playSound(players.getLocation(), Sound.ENTITY_SKELETON_HORSE_DEATH, 10.F, -1.0F);
                    world.setTime(12000);
                }
            }, 340);
            Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                @Override
                public void run() {
                    world.setTime(13000);
                }
            }, 360);
            Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                @Override
                public void run() {
                    world.setTime(14000);
                }
            }, 380);
            Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                @Override
                public void run() {
                    world.setTime(16000);
                }
            }, 400);
        }

        EmbedBuilder eb = new EmbedBuilder();
        TextChannel channel = jda.getTextChannelById("830482526237753395");

        LocalDate Fecha = LocalDate.now();

        LocalTime Tiempo = LocalTime.now();

        eb.setFooter("TheLastLifeT2.jar", "https://media.discordapp.net/attachments/830482526237753395/874379476212019210/transparentexd.png?width=588&height=588");
        eb.setAuthor("The Last Life T2 | Servidor de Minecraft");
        eb.setTitle("**El Fin del Jugador " + p.getName() + " comienza!**");
        eb.setDescription(":fire: **Inicia la Blast Storm con Duracion de " + Dia() + " Hora(s)!** :fire:");
        eb.addField(":skull:**Causa de Muerte: **", e.getDeathMessage(), true);
        eb.addField(":beginner:**Dia: **" + Dia(), "", false);
        eb.addField(":map:**Cordernadas:**",  "X: " + p.getLocation().getBlockX() + " | Y: " + p.getLocation().getBlockY() + " | Z: " + p.getLocation().getBlockZ(), true);
        eb.addField(":low_brightness:**Fecha: **" + Fecha, "", false);
        eb.addField(":alarm_clock:**Hora: **" + Tiempo,"",false);
        eb.setThumbnail("https://crafatar.com/renders/head/" + p.getUniqueId() + ".png");
        eb.setColor(new Color(252, 186, 3));

        channel.sendMessage(eb.build()).queue();

    }
    public void cabezaEstructura(Player p, Location location) {
        location.clone().add(0, -1, 0).getBlock().setType(Material.DEEPSLATE_TILES);
        location.clone().add(1, -1, 0).getBlock().setType(Material.DEEPSLATE_TILES);
        location.clone().add(-1, -1, 0).getBlock().setType(Material.DEEPSLATE_TILES);
        location.clone().add(0, -1, 1).getBlock().setType(Material.DEEPSLATE_TILES);
        location.clone().add(0, -1, -1).getBlock().setType(Material.DEEPSLATE_TILES);
        location.clone().add(1, -1, -1).getBlock().setType(Material.DEEPSLATE_TILES);
        location.clone().add(1, -1, 1).getBlock().setType(Material.DEEPSLATE_TILES);
        location.clone().add(-1, -1, 1).getBlock().setType(Material.DEEPSLATE_TILES);
        location.clone().add(-1, -1, -1).getBlock().setType(Material.DEEPSLATE_TILES);
        location.clone().add(2, -1, 0).getBlock().setType(Material.DEEPSLATE_TILE_SLAB);
        location.clone().add(-2, -1, 0).getBlock().setType(Material.DEEPSLATE_TILE_SLAB);
        location.clone().add(0, -1, -2).getBlock().setType(Material.DEEPSLATE_TILE_SLAB);
        location.clone().add(0, -1, 2).getBlock().setType(Material.DEEPSLATE_TILE_SLAB);
        location.clone().add(2, -1, -1).getBlock().setType(Material.DEEPSLATE_TILE_SLAB);
        location.clone().add(2, -1, 1).getBlock().setType(Material.DEEPSLATE_TILE_SLAB);
        location.clone().add(2, -1, -2).getBlock().setType(Material.DEEPSLATE_TILES);
        location.clone().add(2, -1, 2).getBlock().setType(Material.DEEPSLATE_TILES);
        location.clone().add(1, -1, -2).getBlock().setType(Material.DEEPSLATE_TILE_SLAB);
        location.clone().add(1, -1, 2).getBlock().setType(Material.DEEPSLATE_TILE_SLAB);
        location.clone().add(-1, -1, -2).getBlock().setType(Material.DEEPSLATE_TILE_SLAB);
        location.clone().add(-1, -1, 2).getBlock().setType(Material.DEEPSLATE_TILE_SLAB);
        location.clone().add(-2, -1, 2).getBlock().setType(Material.DEEPSLATE_TILES);
        location.clone().add(-2, -1, -2).getBlock().setType(Material.DEEPSLATE_TILES);
        location.clone().add(-2, -1, -1).getBlock().setType(Material.DEEPSLATE_TILE_SLAB);
        location.clone().add(-2, -1, 1).getBlock().setType(Material.DEEPSLATE_TILE_SLAB);


        location.clone().add(-2, 0, 2).getBlock().setType(Material.DEEPSLATE_BRICK_WALL);
        location.clone().add(-2, 0, -2).getBlock().setType(Material.DEEPSLATE_BRICK_WALL);
        location.clone().add(2, 0, 2).getBlock().setType(Material.DEEPSLATE_BRICK_WALL);
        location.clone().add(2, 0, -2).getBlock().setType(Material.DEEPSLATE_BRICK_WALL);

        location.clone().add(-2, 1, 2).getBlock().setType(Material.DEEPSLATE_BRICK_WALL);
        location.clone().add(-2, 1, -2).getBlock().setType(Material.DEEPSLATE_BRICK_WALL);
        location.clone().add(2, 1, 2).getBlock().setType(Material.DEEPSLATE_BRICK_WALL);
        location.clone().add(2, 1, -2).getBlock().setType(Material.DEEPSLATE_BRICK_WALL);

        location.clone().add(-2, 2, 2).getBlock().setType(Material.DEEPSLATE_BRICK_WALL);
        location.clone().add(-2, 2, -2).getBlock().setType(Material.DEEPSLATE_BRICK_WALL);
        location.clone().add(2, 2, 2).getBlock().setType(Material.DEEPSLATE_BRICK_WALL);
        location.clone().add(2, 2, -2).getBlock().setType(Material.DEEPSLATE_BRICK_WALL);


        location.clone().add(1, 0, 0).getBlock().setType(Material.COBBLED_DEEPSLATE_SLAB);
        location.clone().add(-1, 0, 0).getBlock().setType(Material.COBBLED_DEEPSLATE_SLAB);
        location.clone().add(0, 0, 1).getBlock().setType(Material.COBBLED_DEEPSLATE_SLAB);
        location.clone().add(0, 0, -1).getBlock().setType(Material.COBBLED_DEEPSLATE_SLAB);
        location.clone().add(1, 0, -1).getBlock().setType(Material.COBBLED_DEEPSLATE_SLAB);
        location.clone().add(1, 0, 1).getBlock().setType(Material.COBBLED_DEEPSLATE_SLAB);
        location.clone().add(-1, 0, 1).getBlock().setType(Material.COBBLED_DEEPSLATE_SLAB);
        location.clone().add(-1, 0, -1).getBlock().setType(Material.COBBLED_DEEPSLATE_SLAB);


        location.clone().add(0, 3, 0).getBlock().setType(Material.DEEPSLATE_BRICKS);
        location.clone().add(1, 3, 0).getBlock().setType(Material.DEEPSLATE_BRICKS);
        location.clone().add(-1, 3, 0).getBlock().setType(Material.DEEPSLATE_BRICKS);
        location.clone().add(0, 3, 1).getBlock().setType(Material.DEEPSLATE_BRICKS);
        location.clone().add(0, 3, -1).getBlock().setType(Material.DEEPSLATE_BRICKS);
        location.clone().add(1, 3, -1).getBlock().setType(Material.DEEPSLATE_BRICKS);
        location.clone().add(1, 3, 1).getBlock().setType(Material.DEEPSLATE_BRICKS);
        location.clone().add(-1, 3, 1).getBlock().setType(Material.DEEPSLATE_BRICKS);
        location.clone().add(-1, 3, -1).getBlock().setType(Material.DEEPSLATE_BRICKS);
        location.clone().add(2, 3, 0).getBlock().setType(Material.DEEPSLATE_BRICKS);
        location.clone().add(-2, 3, 0).getBlock().setType(Material.DEEPSLATE_BRICKS);
        location.clone().add(0, 3, -2).getBlock().setType(Material.DEEPSLATE_BRICKS);
        location.clone().add(0, 3, 2).getBlock().setType(Material.DEEPSLATE_BRICKS);
        location.clone().add(2, 3, -1).getBlock().setType(Material.DEEPSLATE_BRICKS);
        location.clone().add(2, 3, 1).getBlock().setType(Material.DEEPSLATE_BRICKS);
        location.clone().add(2, 3, -2).getBlock().setType(Material.DEEPSLATE_BRICKS);
        location.clone().add(2, 3, 2).getBlock().setType(Material.DEEPSLATE_BRICKS);
        location.clone().add(1, 3, -2).getBlock().setType(Material.DEEPSLATE_BRICKS);
        location.clone().add(1, 3, 2).getBlock().setType(Material.DEEPSLATE_BRICKS);
        location.clone().add(-1, 3, -2).getBlock().setType(Material.DEEPSLATE_BRICKS);
        location.clone().add(-1, 3, 2).getBlock().setType(Material.DEEPSLATE_BRICKS);
        location.clone().add(-2, 3, 2).getBlock().setType(Material.DEEPSLATE_BRICKS);
        location.clone().add(-2, 3, -2).getBlock().setType(Material.DEEPSLATE_BRICKS);
        location.clone().add(-2, 3, -1).getBlock().setType(Material.DEEPSLATE_BRICKS);
        location.clone().add(-2, 3, 1).getBlock().setType(Material.DEEPSLATE_BRICKS);
        location.clone().add(0, 4, 0).getBlock().setType(Material.LIGHTNING_ROD);
        p.getLocation().getBlock().setType(Material.AMETHYST_BLOCK);
        Block skullBlock = location.clone().add(0, 1, 0).getBlock();
        skullBlock.setType(Material.PLAYER_HEAD);
        BlockState state = skullBlock.getState();
        Skull skull = (Skull) state;
        UUID uuid = p.getUniqueId();
        skull.setOwningPlayer(Bukkit.getServer().getOfflinePlayer(uuid));
        skull.update();
    }





    public static int Dia() {
        LocalDate FechaActual = LocalDate.now();

        LocalDate FechaInicio = LocalDate.parse("2021-10-06");

        return (int) ChronoUnit.DAYS.between(FechaInicio, FechaActual);
    }

    @EventHandler
    public void cambiodeClima(WeatherChangeEvent e){
        if(!e.toWeatherState()){
            for(Player players : Bukkit.getOnlinePlayers()) {
                tormenta.setVisible(false);
                tormenta.removePlayer(players);
                world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, true);
            }
        }
    }

    @EventHandler
    public void tormentaBlastStorm(PlayerDeathEvent e) {
        Player p = e.getEntity();
        World world = Bukkit.getWorld("world");
            Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                @Override
                public void run() {
                    String minutos = " horas!";
                    if (Dia() < 1) {
                        minutos = " hora(s)";
                    }
                    world.setTime(18000);
                    world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
                    String Tormentajaja = Bukkit.getWorld("world").isThundering() ? "weather thunder " + ((Bukkit.getWorld("world").getWeatherDuration() / 20) + (Dia() * 1800)) : "weather thunder " + (Dia() * 1800);
                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), Tormentajaja);
                    Iterator iteplayer = Bukkit.getServer().getOnlinePlayers().iterator();
                    while (iteplayer.hasNext()) {
                        Player p = (Player) iteplayer.next();
                        p.playSound(p.getLocation(), Sound.ENTITY_WITHER_SPAWN, 10.0F, (float) -1.0);
                        p.sendTitle("♥" + ChatColor.GOLD + "" + ChatColor.BOLD +  "      ¡BLAST STORM!      " + ChatColor.WHITE +"♥", ChatColor.GOLD + "Comienza la BlastStorm con Duracion de " + Dia() + "" + minutos);
                        tormenta.setVisible(true);
                        tormenta.addPlayer(p);
                    }
                }
            }, 420L);
        }
}
