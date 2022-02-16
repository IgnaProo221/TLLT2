package Eventos;

import Utilidades.Data;
import Utilidades.Utils;
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
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import tlldos.tll2.TLL2;

import javax.security.auth.login.LoginException;
import java.awt.Color;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.UUID;

import static Utilidades.Format.format;

public class Muerte extends ListenerAdapter implements Listener {
    private final World world;
    private final TLL2 plugin;
    private JDA jda;

    public Muerte(TLL2 plugin){
        this.plugin = plugin;
        world = Bukkit.getWorld("world");
        assert world != null;
        long segundos = (world.getWeatherDuration() / 20);
        long hours = segundos  / 1800L;
        long minutes = segundos % 1800L / 60L;
        long seconds = segundos % 60L;
        String time = String.format("%02d:%02d:%02d", hours, minutes, seconds);

        //tormenta = Bukkit.createBossBar(ChatColor.translateAlternateColorCodes('&', "&f♥        &6&lBlast Storm: " + time +  "        &f♥"), BarColor.YELLOW , BarStyle.SEGMENTED_6);
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
        if (msg.getContentRaw().equals("tll!test")) {
            MessageChannel channel = event.getChannel();
            channel.sendMessage("☠️\uD83D\uDC80 Server mutanteado por mutant \uD83D\uDC80☠️ \n" +
                    "☠️\uD83D\uDC80 https://www.youtube.com/watch?v=sOvyNa9-39c \uD83D\uDC80☠️").queue();
        }
        if (msg.getContentRaw().equals("tll!players")) {
            EmbedBuilder eb = new EmbedBuilder();
            eb.setFooter("TheLastLifeT2.jar", "https://cdn.discordapp.com/attachments/906642578013843526/943284426442436679/hardcorehearth-export.png");
            eb.setAuthor("The Last Life T2 | Servidor de Minecraft");
            eb.setTitle("Jugadores conectados en estos momentos:");
            StringBuilder jugadores = new StringBuilder();
            for (Player players : Bukkit.getOnlinePlayers()) {
                if (jugadores.length() > 0) {
                    jugadores.append('\n');
                }
                jugadores.append(players.getName() +" :heart: : "+players.getHealth());
            }
            eb.setDescription(jugadores.length() > 0 ? jugadores : "No hay jugadores conectados en estos momentos.");
            eb.setColor(new Color(252, 186, 3));
            msg.getChannel().sendMessage(eb.build()).queue();
        }
        if (msg.getContentRaw().equals("tll!blaststorm")) {
            World world = Bukkit.getWorld("world");
            long segundos = 0;
            if (world != null) {
                segundos = world.getWeatherDuration() / 20;
            }
            long hours = segundos / 1800L;
            long minutes = segundos % 1800L / 60L;
            long seconds = segundos % 60L;

            String time = String.format("%02d:%02d:%02d", hours, minutes, seconds);

            EmbedBuilder eb = new EmbedBuilder();
            eb.setFooter("TheLastLifeT2.jar", "https://cdn.discordapp.com/attachments/906642578013843526/943284426442436679/hardcorehearth-export.png");
            eb.setAuthor("The Last Life T2 | Servidor de Minecraft");
            eb.setTitle("Duración de BlastStorm:");
            eb.setDescription((segundos > 0) ? "La Duración de la BlastStorm actualmente es: " + time : "En estos momentos, no hay ninguna Blast Storm activa.");
            eb.setThumbnail("https://media.discordapp.net/attachments/830482526237753395/888118123696373790/blaststorm_icon.png");
            eb.setColor(new Color(252, 186, 3));
            msg.getChannel().sendMessage(eb.build()).queue();
        }

    }

    @EventHandler
    public void muerteEvento(PlayerDeathEvent e){
        Player p = e.getEntity();
        Location location = p.getLocation().clone();
        p.sendActionBar(format("&4&lGame Over " + p.getName()));
        Bukkit.getScoreboardManager().getMainScoreboard().getTeam("11-Muerto").addEntry(p.getName());
        World world = Bukkit.getWorld("world");
        Location loc = p.getLocation();
        PersistentDataContainer data = Data.get(p);
        data.set(Utils.key("X") , PersistentDataType.DOUBLE, loc.getX());
        data.set(Utils.key("Y") , PersistentDataType.DOUBLE, loc.getY());
        data.set(Utils.key("Z"), PersistentDataType.DOUBLE, loc.getZ());
        data.set(Utils.key("WORLD"), PersistentDataType.STRING, loc.getWorld().getName());
        Bukkit.getScheduler().runTaskLater(plugin, ()->{
            if(p.getWorld().getEnvironment() == World.Environment.NORMAL){
                Utils.pasteSchematic("overworld", p.getLocation());
                Block skullBlock = location.clone().add(0, 4, 0).getBlock();
                skullBlock.setType(Material.PLAYER_HEAD);
                BlockState state = skullBlock.getState();
                Skull skull = (Skull) state;
                UUID uuid = p.getUniqueId();
                skull.setOwningPlayer(Bukkit.getServer().getOfflinePlayer(uuid));
                skull.update();
            }else if(p.getWorld().getEnvironment() == World.Environment.NETHER){
                Utils.pasteSchematic("nether", p.getLocation());
                Block skullBlock = location.clone().add(0, 4, 0).getBlock();
                skullBlock.setType(Material.PLAYER_HEAD);
                BlockState state = skullBlock.getState();
                Skull skull = (Skull) state;
                UUID uuid = p.getUniqueId();
                skull.setOwningPlayer(Bukkit.getServer().getOfflinePlayer(uuid));
                skull.update();
            }else if(p.getWorld().getEnvironment() == World.Environment.THE_END){
                Utils.pasteSchematic("end", p.getLocation());
                Block skullBlock = location.clone().add(0, 4, 0).getBlock();
                skullBlock.setType(Material.PLAYER_HEAD);
                BlockState state = skullBlock.getState();
                Skull skull = (Skull) state;
                UUID uuid = p.getUniqueId();
                skull.setOwningPlayer(Bukkit.getServer().getOfflinePlayer(uuid));
                skull.update();
            }else{
                p.sendMessage(format("&6no se detecto el mundo y la estructura no se genero, rip bozo XD #packwatch"));
            }
        },8L);
        for (Player players : Bukkit.getOnlinePlayers()){

            players.sendTitle(format("&c&l&k|||  &6&l&kThe Last Life  &c&l&k|||"), format("&7¡El jugador " + p.getName() + " ha muerto!"), 0,80,0);
            players.playSound(players.getLocation(),"tllt2.deathsound",SoundCategory.RECORDS, 10.0F, 1.0F);
            assert world != null;

            players.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8¡El Alma de &6&l" + p.getName() + " &8ha desaparecido entre la oscuridad eterna del &8&lvacío!, &8&lsu energía se liberara para iniciar &6la &6&lBLAST STORM."));
            players.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Fatum tuum non potes effugere, &c&lsuperesse vel perit"));
            players.sendMessage(ChatColor.GRAY + "Coordenadas: X: " + p.getLocation().getBlockX() + ", Y: " + p.getLocation().getBlockY() + ", Z: " + p.getLocation().getBlockZ());
            players.sendMessage(ChatColor.GRAY + "Dimensión: " +  dimension(p.getLocation()));
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                players.sendTitle(format("&c&l&k|||  &6&l&kThe Last Life  &c&l&k|||"), format("&7¡El jugador " + p.getName() + " ha muerto!"), 0,80,0);

            }, 5);
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                players.sendTitle(format("&c&l&k|||  &6&lT&6&l&khe Last Life  &c&l&k|||"), format("&7¡El jugador " + p.getName() + " ha muerto!"), 0,80,0);

            }, 10);
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                players.sendTitle(format("&c&l&k|||  &6&lTh&6&l&ke Last Life  &c&l&k|||"), format("&7¡El jugador " + p.getName() + " ha muerto!"), 0,80,0);

            }, 15);
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                players.sendTitle(format("&c&l&k|||  &6&lThe&6&l&k Last Life  &c&l&k|||"), format("&7¡El jugador " + p.getName() + " ha muerto!"), 0,80,0);

            }, 20);
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                players.sendTitle(format("&c&l&k|||  &6&lThe L&6&l&kast Life  &c&l&k|||"), format("&7¡El jugador " + p.getName() + " ha muerto!"), 0,80,0);


            }, 25);
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                players.sendTitle(format("&c&l&k|||  &6&lThe La&6&l&kst Life  &c&l&k|||"), format("&7¡El jugador " + p.getName() + " ha muerto!"), 0,80,0);


            }, 30);
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                players.sendTitle(format("&c&l&k|||  &6&lThe Las&6&l&kt Life  &c&l&k|||"), format("&7¡El jugador " + p.getName() + " ha muerto!"), 0,80,0);


            }, 35);
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                players.sendTitle(format("&c&l&k|||  &6&lThe Las&6&l&kt Life  &c&l&k|||"), format("&7¡El jugador " + p.getName() + " ha muerto!"), 0,80,0);


            }, 40);
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                players.sendTitle(format("&c&l&k|||  &6&lThe Last&6&l&k Life  &c&l&k|||"), format("&7¡El jugador " + p.getName() + " ha muerto!"), 0,80,0);


            }, 45);
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                players.sendTitle(format("&c&l&k|||  &6&lThe Last L&6&l&kife  &c&l&k|||"), format("&7¡El jugador " + p.getName() + " ha muerto!"), 0,80,0);


            }, 50);
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                players.sendTitle(format("&c&l&k|||  &6&lThe Last Li&6&l&kfe  &c&l&k|||"), format("&7¡El jugador " + p.getName() + " ha muerto!"), 0,80,0);


            }, 55);
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                players.sendTitle(format("&c&l&k|||  &6&lThe Last Lif&6&l&ke  &c&l&k|||"), format("&7¡El jugador " + p.getName() + " ha muerto!"), 0,80,0);


            }, 60);
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                players.sendTitle(format("&c&l&k|||  &6&lThe Last Life  &c&l&k|||"), format("&7¡El jugador " + p.getName() + " ha muerto!"), 0,80,20);
                players.sendActionBar(ChatColor.GOLD + "" + ChatColor.BOLD + e.getDeathMessage());

            }, 65);
        }

        EmbedBuilder eb = new EmbedBuilder();
        TextChannel channel = jda.getTextChannelById("907663173144969278");

        LocalDate Fecha = LocalDate.now();

        LocalTime Tiempo = LocalTime.of(LocalTime.now().getHour(), LocalTime.now().getMinute(), LocalTime.now().getSecond());


        eb.setFooter("TheLastLifeT2.jar", "https://cdn.discordapp.com/attachments/906642578013843526/943284426442436679/hardcorehearth-export.png");
        eb.setAuthor("The Last Life T2 | Servidor de Minecraft");
        eb.setTitle("**¡El jugador " + p.getName() + " ha perdido su última vida!**");
        eb.setDescription(":fire:** ¡La Blast Storm invade los cielos, preparense para sufrir! **");
        eb.addField(":skull: **Causa de muerte: **", deathreason(Objects.requireNonNull(p.getLastDamageCause())), true);
        eb.addField(":beginner: **Día: **", "" + Utils.getDay(), true);
        eb.addField(":map: **Coordenadas:**",  "X: " + p.getLocation().getBlockX() + " | Y: " + p.getLocation().getBlockY() + " | Z: " + p.getLocation().getBlockZ(), true);
        eb.addField(":globe_with_meridians: **Dimensión: **", dimension(p.getLocation()),true);
        eb.addField(":low_brightness: **Fecha: **",  Fecha + "" , true);
        eb.addField(":alarm_clock: **Hora: **", "" + Tiempo,true);
        eb.setThumbnail("https://crafatar.com/renders/head/" + p.getUniqueId() + "?&overlay");
        eb.setColor(new Color(252, 186, 3));

        if (channel != null) {
            channel.sendMessage(eb.build()).queue();
        }
    }
    @EventHandler
    public void onRespawn(PlayerRespawnEvent e){
        Player p = e.getPlayer();
        PersistentDataContainer data = Data.get(p);
            try {
                double X = data.get(Utils.key("X"), PersistentDataType.DOUBLE);
                double Y = data.get(Utils.key("Y"), PersistentDataType.DOUBLE);
                double Z = data.get(Utils.key("Z"), PersistentDataType.DOUBLE);
                World w = Bukkit.getWorld(Objects.requireNonNull(data.get(Utils.key("WORLD"), PersistentDataType.STRING)));

                Bukkit.getScheduler().runTaskLater(plugin, () -> p.teleport(new Location(w, X, Y, Z)), 1L);
            } catch (NullPointerException ex) {
                Bukkit.getConsoleSender().sendMessage("RESPAWN EVENT ERROR:" + ex);
            }
    }

    public static String dimension(Location location){
        return switch (location.getWorld().getName()) {
            case "world" -> "Overworld";
            case "world_nether" -> "Nether";
            case "world_the_end" -> "The End";
            case "build_world" -> "Mundo de Builders (Pendejo)";
            case "lost_world" -> "Lost Cities";
            default -> "Desconocido";
        };
    }
    public static String deathreason(EntityDamageEvent e){
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
