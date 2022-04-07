package Extras;

import Utilities.Format;
import Utilities.Utils;
import Utilities.Warn;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import player.CustomPlayer;
import player.PlayerData;
import tlldos.tll2.TLL2;

import static Utilities.Format.PREFIX;
import static Utilities.Format.format;

public class EventosItems {

    private final TLL2 plugin;

    public EventosItems(TLL2 plugin){
        this.plugin = plugin;
    }

    public static void temperatura(Player p) {
      try{
          p.sendMessage(Format.PREFIX, format("&cTEMPERATURA TEST: TIENES 69 GRADOS LOL XD"));
      }catch (Exception e){
          e.printStackTrace();
          Warn.Mutant(e);
      }
    }

    public static void totemrestorerEvent(Player p, TLL2 plugin) {
        try{
            PlayerData data = CustomPlayer.fromName(p.getName()).getData();
            p.sendMessage(Format.PREFIX, format("&cSe han reiniciado el porcentaje de tótems correctamente."));
            p.playEffect(EntityEffect.TOTEM_RESURRECT);
            data.setTotemspercentage(100);
            Bukkit.getScheduler().runTaskLater(TLL2.getPlugin(TLL2.class), () -> {
                p.playEffect(EntityEffect.TOTEM_RESURRECT);
            }, 20);
            Bukkit.getScheduler().runTaskLater(TLL2.getPlugin(TLL2.class), () -> {
                p.playEffect(EntityEffect.TOTEM_RESURRECT);
            }, 40);
        }catch (Exception e){
            e.printStackTrace();
            Warn.Mutant(e);
        }
    }
    public static void crystalHealthup(Player p){
        if(p.getHealth() < 40) {
            p.sendMessage(Format.PREFIX, format("&c¡Has aumentado tu vida maxima!"));
            p.setMaxHealth(p.getMaxHealth() + 2);
            p.playSound(p.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 10.0F, -1.0F);
        }else{
            p.sendMessage(Format.PREFIX, format("&c¡No puedes aumentar mas tu vida! (Existe un limite)"));
        }
    }

    public static void deathmessagefake(Player p,Player players){
        players.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8¡El Alma de &6&l" + p.getName() + " &8ha desaparecido entre la oscuridad eterna del &8&lvacío!, &8&lsu energía se liberara para iniciar &6la &6&lBLAST STORM."));
        players.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Fatum tuum non potes effugere, &c&lsuperesse vel perit"));
        players.sendMessage(ChatColor.GRAY + "Coordenadas: X: " + p.getLocation().getBlockX() + ", Y: " + p.getLocation().getBlockY() + ", Z: " + p.getLocation().getBlockZ());
        players.sendMessage(ChatColor.GRAY + "Dimensión: Overworld"); //perdon
    }

    public static void animacion(Player p, Player players){
            players.sendTitle(format("&c&l&k|||  &6&l&kThe Last Life  &c&l&k|||"), format("&7¡El jugador " + p.getName() + " ha muerto!"), 0,80,0);
            players.playSound(players.getLocation(),"tllt2.deathsound", SoundCategory.RECORDS, 10.0F, 1.0F);
            Bukkit.getScheduler().runTaskLater(Utils.getPlugin(), () -> {
                players.sendTitle(format("&c&l&k|||  &6&l&kThe Last Life  &c&l&k|||"), format("&7¡El jugador " + p.getName() + " ha muerto!"), 0,80,0);

            }, 5);
            Bukkit.getScheduler().runTaskLater(Utils.getPlugin(), () -> {
                players.sendTitle(format("&c&l&k|||  &6&lT&6&l&khe Last Life  &c&l&k|||"), format("&7¡El jugador " + p.getName() + " ha muerto!"), 0,80,0);

            }, 10);
            Bukkit.getScheduler().runTaskLater(Utils.getPlugin(), () -> {
                players.sendTitle(format("&c&l&k|||  &6&lTh&6&l&ke Last Life  &c&l&k|||"), format("&7¡El jugador " + p.getName() + " ha muerto!"), 0,80,0);

            }, 15);
            Bukkit.getScheduler().runTaskLater(Utils.getPlugin(), () -> {
                players.sendTitle(format("&c&l&k|||  &6&lThe&6&l&k Last Life  &c&l&k|||"), format("&7¡El jugador " + p.getName() + " ha muerto!"), 0,80,0);

            }, 20);
            Bukkit.getScheduler().runTaskLater(Utils.getPlugin(), () -> {
                players.sendTitle(format("&c&l&k|||  &6&lThe L&6&l&kast Life  &c&l&k|||"), format("&7¡El jugador " + p.getName() + " ha muerto!"), 0,80,0);


            }, 25);
            Bukkit.getScheduler().runTaskLater(Utils.getPlugin(), () -> {
                players.sendTitle(format("&c&l&k|||  &6&lThe La&6&l&kst Life  &c&l&k|||"), format("&7¡El jugador " + p.getName() + " ha muerto!"), 0,80,0);


            }, 30);
            Bukkit.getScheduler().runTaskLater(Utils.getPlugin(), () -> {
                players.sendTitle(format("&c&l&k|||  &6&lThe Las&6&l&kt Life  &c&l&k|||"), format("&7¡El jugador " + p.getName() + " ha muerto!"), 0,80,0);


            }, 35);
            Bukkit.getScheduler().runTaskLater(Utils.getPlugin(), () -> {
                players.sendTitle(format("&c&l&k|||  &6&lThe Las&6&l&kt Life  &c&l&k|||"), format("&7¡El jugador " + p.getName() + " ha muerto!"), 0,80,0);


            }, 40);
            Bukkit.getScheduler().runTaskLater(Utils.getPlugin(), () -> {
                players.sendTitle(format("&c&l&k|||  &6&lThe Last&6&l&k Life  &c&l&k|||"), format("&7¡El jugador " + p.getName() + " ha muerto!"), 0,80,0);


            }, 45);
            Bukkit.getScheduler().runTaskLater(Utils.getPlugin(), () -> {
                players.sendTitle(format("&c&l&k|||  &6&lThe Last L&6&l&kife  &c&l&k|||"), format("&7¡El jugador " + p.getName() + " ha muerto!"), 0,80,0);


            }, 50);
            Bukkit.getScheduler().runTaskLater(Utils.getPlugin(), () -> {
                players.sendTitle(format("&c&l&k|||  &6&lThe Last Li&6&l&kfe  &c&l&k|||"), format("&7¡El jugador " + p.getName() + " ha muerto!"), 0,80,0);


            }, 55);
            Bukkit.getScheduler().runTaskLater(Utils.getPlugin(), () -> {
                players.sendTitle(format("&c&l&k|||  &6&lThe Last Lif&6&l&ke  &c&l&k|||"), format("&7¡El jugador " + p.getName() + " ha muerto!"), 0,80,0);


            }, 60);
            Bukkit.getScheduler().runTaskLater(Utils.getPlugin(), () -> {
                players.sendTitle(format("&c&l&k|||  &6&lThe Last Life  &c&l&k|||"), format("&7¡El jugador " + p.getName() + " ha muerto!"), 0,80,20);

            }, 65);
    }


    public static void paralizison(Player p, PlayerData data){
        p.playSound(p.getLocation(), "tllt2.zapsoundeffect",SoundCategory.HOSTILE,10.0F,1.0F);
        p.sendMessage(PREFIX,format("&c¡Has sido &c&lParalizado&c!"));
        data.setParalizis(1);
    }
    public static void paralizisoff(Player p, PlayerData data){
        p.sendMessage(PREFIX,format("&c¡Tu Paralizis desapareció!"));
        data.setParalizis(0);
    }

    public static void discordxd(Player p){
        p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1200,2, true, false, true));
        p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1200,1, true, false, true));
        p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 1200,1, true, false, true));
        p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1200,0, true, false, true));
        p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 1200,0, true, false, true));
        p.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 1200,1, true, false, true));
        p.playSound(p.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_AMBIENT, 10.0F, 2.0F);
        p.getLocation().getNearbyPlayers( 10, 10, 10).forEach(player ->
                player.playSound(player.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_AMBIENT, 10.0F, 2.0F)
        );
        p.getLocation().getNearbyPlayers( 10, 10, 10).forEach(player ->
                player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1200,2, true, false, true))
        );
        p.getLocation().getNearbyPlayers( 10, 10, 10).forEach(player ->
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1200,1, true, false, true))
        );
        p.getLocation().getNearbyPlayers( 10, 10, 10).forEach(player ->
                player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 1200,1, true, false, true))
        );
        p.getLocation().getNearbyPlayers( 10, 10, 10).forEach(player ->
                player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 1200,0, true, false, true))
        );
        p.getLocation().getNearbyPlayers( 10, 10, 10).forEach(player ->
                player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1200,0, true, false, true))
        );
        p.getLocation().getNearbyPlayers( 10, 10, 10).forEach(player ->
                player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 1200,1, true, false, true))
        );
    }
}
