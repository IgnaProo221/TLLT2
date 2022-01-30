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
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
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
            eb.setFooter("TheLastLifeT2.jar", "https://media.discordapp.net/attachments/830482526237753395/874379476212019210/transparentexd.png?width=588&height=588");
            eb.setAuthor("The Last Life T2 | Servidor de Minecraft");
            eb.setTitle("Jugadores Conectados:");
            StringBuilder jugadores = new StringBuilder();
            for (Player players : Bukkit.getOnlinePlayers()) {
                if (jugadores.length() > 0) {
                    jugadores.append('\n');
                }
                jugadores.append(players.getName() +" :heart: : "+players.getHealth());
            }
            eb.setDescription(jugadores.length() > 0 ? jugadores : "No hay Jugadores Conectados");
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
        Location location = p.getLocation().clone();
        p.sendActionBar(format("&4&lGame Over " + p.getName()));
        World world = Bukkit.getWorld("world");
        Location loc = p.getLocation();
        PersistentDataContainer data = Data.get(p);
        data.set(Utils.key("X") , PersistentDataType.DOUBLE, loc.getX());
        data.set(Utils.key("Y") , PersistentDataType.DOUBLE, loc.getY());
        data.set(Utils.key("Z"), PersistentDataType.DOUBLE, loc.getZ());
        data.set(Utils.key("WORLD"), PersistentDataType.STRING, loc.getWorld().getName());
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
        for (Player players : Bukkit.getOnlinePlayers()){

            players.sendTitle(format("&c&l&k|||  &6&l&kThe Last Life  &c&l&k|||"), format("&7El Jugador " + p.getName() + " ha Muerto!"), 0,80,0);
            players.playSound(players.getLocation(),"tllt2.deathsound",SoundCategory.RECORDS, 10.0F, 1.0F);
            assert world != null;

            players.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8El Alma de &6&l" + p.getName() + " &8a desaparecido entre la oscuridad eterna del &8&lVacio!, &8&lsu energia se liberara para iniciar &6la &6&lBLAST STORM!"));
            players.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Fatum tuum non potes effugere, &c&lsuperesse vel perit"));
            players.sendMessage(ChatColor.GRAY + "Coordenadas: X: " + p.getLocation().getBlockX() + ", Y: " + p.getLocation().getBlockY() + ", Z: " + p.getLocation().getBlockZ());
            players.sendMessage(ChatColor.GRAY + "Dimension: " +  dimension(p.getLocation()));
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                players.sendTitle(format("&c&l&k|||  &6&l&kThe Last Life  &c&l&k|||"), format("&7El Jugador " + p.getName() + " ha Muerto!"), 0,80,0);

            }, 5);
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                players.sendTitle(format("&c&l&k|||  &6&lT&6&l&khe Last Life  &c&l&k|||"), format("&7El Jugador " + p.getName() + " ha Muerto!"), 0,80,0);

            }, 10);
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                players.sendTitle(format("&c&l&k|||  &6&lTh&6&l&ke Last Life  &c&l&k|||"), format("&7El Jugador " + p.getName() + " ha Muerto!"), 0,80,0);

            }, 15);
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                players.sendTitle(format("&c&l&k|||  &6&lThe&6&l&k Last Life  &c&l&k|||"), format("&7El Jugador " + p.getName() + " ha Muerto!"), 0,80,0);

            }, 20);
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                players.sendTitle(format("&c&l&k|||  &6&lThe L&6&l&kast Life  &c&l&k|||"), format("&7El Jugador " + p.getName() + " ha Muerto!"), 0,80,0);


            }, 25);
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                players.sendTitle(format("&c&l&k|||  &6&lThe La&6&l&kst Life  &c&l&k|||"), format("&7El Jugador " + p.getName() + " ha Muerto!"), 0,80,0);


            }, 30);
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                players.sendTitle(format("&c&l&k|||  &6&lThe Las&6&l&kt Life  &c&l&k|||"), format("&7El Jugador " + p.getName() + " ha Muerto!"), 0,80,0);


            }, 35);
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                players.sendTitle(format("&c&l&k|||  &6&lThe Las&6&l&kt Life  &c&l&k|||"), format("&7El Jugador " + p.getName() + " ha Muerto!"), 0,80,0);


            }, 40);
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                players.sendTitle(format("&c&l&k|||  &6&lThe Last&6&l&k Life  &c&l&k|||"), format("&7El Jugador " + p.getName() + " ha Muerto!"), 0,80,0);


            }, 45);
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                players.sendTitle(format("&c&l&k|||  &6&lThe Last L&6&l&kife  &c&l&k|||"), format("&7El Jugador " + p.getName() + " ha Muerto!"), 0,80,0);


            }, 50);
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                players.sendTitle(format("&c&l&k|||  &6&lThe Last Li&6&l&kfe  &c&l&k|||"), format("&7El Jugador " + p.getName() + " ha Muerto!"), 0,80,0);


            }, 55);
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                players.sendTitle(format("&c&l&k|||  &6&lThe Last Lif&6&l&ke  &c&l&k|||"), format("&7El Jugador " + p.getName() + " ha Muerto!"), 0,80,0);


            }, 60);
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                players.sendTitle(format("&c&l&k|||  &6&lThe Last Life  &c&l&k|||"), format("&7El Jugador " + p.getName() + " ha Muerto!"), 0,80,20);
                players.sendActionBar(ChatColor.GOLD + "" + ChatColor.BOLD + e.getDeathMessage());

            }, 65);
        }

        EmbedBuilder eb = new EmbedBuilder();
        TextChannel channel = jda.getTextChannelById("907663173144969278");

        LocalDate Fecha = LocalDate.now();

        LocalTime Tiempo = LocalTime.now();


        eb.setFooter("TheLastLifeT2.jar", "https://media.discordapp.net/attachments/830482526237753395/874379476212019210/transparentexd.png?width=588&height=588");
        eb.setAuthor("The Last Life T2 | Servidor de Minecraft");
        eb.setTitle("**El Jugador " + p.getName() + " a muerto!**");
        eb.setDescription(":fire:** ¡La Blast Storm invade los Cielos, preparense para Sufrir! **:fire:");
        eb.addField(":skull: **Causa de Muerte: **", e.getDeathMessage(), true);
        eb.addField(":beginner: **Dia: **" + Utils.getDay(), "", true);
        eb.addField(":map: **Cordernadas:**",  "X: " + p.getLocation().getBlockX() + " | Y: " + p.getLocation().getBlockY() + " | Z: " + p.getLocation().getBlockZ() + " Mundo: " + dimension(p.getLocation()), true);
        eb.addField(":low_brightness: **Fecha: **" + Fecha, "", true);
        eb.addField(":alarm_clock: **Hora: **" + Tiempo,"",true);
        eb.setThumbnail("https://crafatar.com/renders/head/" + p.getUniqueId() + ".png");
        eb.setColor(new Color(252, 186, 3));

        if (channel != null) {
            channel.sendMessage(eb.build()).queue();
        }
    }
    @EventHandler
    public void onRespawn(PlayerRespawnEvent e){
        Player p = e.getPlayer();
        PersistentDataContainer data = Data.get(p);
        if(!e.getRespawnFlags().equals(PlayerRespawnEvent.RespawnFlag.END_PORTAL)) {
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
    }

    public static String dimension(Location location){
        return switch (location.getWorld().getEnvironment()) {
            case NORMAL -> "Overworld";
            case NETHER -> "Nether";
            case THE_END -> "The End";
            default -> "Desconocido";
        };
    }



}
