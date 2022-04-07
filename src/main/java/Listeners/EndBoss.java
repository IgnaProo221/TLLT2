package Listeners;

import net.minecraft.data.Main;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.entity.Wither;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import tlldos.tll2.TLL2;

import java.util.Random;

import static Utilities.Format.PREFIX;
import static Utilities.Format.format;

public class EndBoss extends BukkitRunnable{
    public Wither wither;
    public static Boolean isDead;
    TLL2 plugin;
    public EndBoss(TLL2 plugin, Wither wither){
        this.plugin = plugin;
        this.wither = wither;
        isDead = false;
    }


    @Override
    public void run() {
        if(isDead|| wither.isDead()){
            cancel();
            return;
        }else{
            int attacktype = new Random().nextInt(7);
            for(Player player : Bukkit.getOnlinePlayers()){
                if(!(player.getGameMode() == GameMode.SURVIVAL))return;
                if(attacktype == 1){
                    cotmAttack(player);
                }else if(attacktype == 2){
                    tntAttack(player);
                }else if(attacktype == 3){
                    lightingAttack(player);
                }else if(attacktype == 4){
                    explosion1attack(player);
                }else if(attacktype == 5){
                    explosion2attack(player);
                }else if(attacktype == 6){
                    totemAttack(player);
                }else{
                    throwAttack(player);
                }
            }
        }
    }

    public void cotmAttack(Player player){
        player.sendMessage(format("&7&l[&8&lEREBUS&7&l] &4&l> &c&l¡La Maldición de la Luna acabara con TODOS!"));
        player.addPotionEffect(new PotionEffect(PotionEffectType.UNLUCK,1200,4,true,false,true));
        player.playSound(player.getLocation(), Sound.AMBIENT_CAVE,10.0F,-1.0F);
    }
    public void tntAttack(Player player){
        int randomx = new Random().nextInt(5) + 5;
        int randomz = new Random().nextInt(5) + 5;
        wither.playEffect(EntityEffect.TELEPORT_ENDER);
        wither.setInvisible(true);
        wither.setInvulnerable(true);
        wither.setAI(false);
        player.sendMessage(format("&7&l[&8&lEREBUS&7&l] &4&l> &c&l¡Bienvenidos a su Infierno Personal!"));
        player.getWorld().spawn(player.getLocation().add(randomx,0,randomz), TNTPrimed.class);
        player.getWorld().spawn(player.getLocation().add(randomx,0,randomz), TNTPrimed.class);
        player.getWorld().spawn(player.getLocation().add(randomx,0,randomz), TNTPrimed.class);
        Bukkit.getScheduler().runTaskLater(plugin,()->{
            player.getWorld().spawn(player.getLocation().add(randomx,0,randomz), TNTPrimed.class);
            player.getWorld().spawn(player.getLocation().add(randomx,0,randomz), TNTPrimed.class);
            player.getWorld().spawn(player.getLocation().add(randomx,0,randomz), TNTPrimed.class);
        },60);
        Bukkit.getScheduler().runTaskLater(plugin,()->{
            player.getWorld().spawn(player.getLocation().add(randomx,0,randomz), TNTPrimed.class);
            player.getWorld().spawn(player.getLocation().add(randomx,0,randomz), TNTPrimed.class);
            player.getWorld().spawn(player.getLocation().add(randomx,0,randomz), TNTPrimed.class);
        },120);
        Bukkit.getScheduler().runTaskLater(plugin,()->{
            player.getWorld().spawn(player.getLocation().add(randomx,0,randomz), TNTPrimed.class);
            player.getWorld().spawn(player.getLocation().add(randomx,0,randomz), TNTPrimed.class);
            player.getWorld().spawn(player.getLocation().add(randomx,0,randomz), TNTPrimed.class);
            wither.setInvisible(false);
            wither.setInvulnerable(false);
            wither.setAI(true);
            player.sendMessage(format("&7&l[&8&lEREBUS&7&l] &4&l> &c&l¡Espero que se hayan Divertido!"));
        },180);
    }
    public void lightingAttack(Player player){
        player.sendMessage(format("&7&l[&8&lEREBUS&7&l] &4&l> &c&l¡Todos ustedes no pertenecen a este mundo!"));
        player.getWorld().strikeLightning(player.getLocation());
        player.getWorld().strikeLightning(player.getLocation());
        player.getWorld().strikeLightning(player.getLocation());
    }
    public void explosion1attack(Player player){
        player.sendMessage(format("&7&l[&8&lEREBUS&7&l] &4&l> &c&l¡Sera Mejor que corran!"));
        wither.setInvulnerableTicks(400);
    }
    public void explosion2attack(Player player){
        player.sendMessage(format("&7&l[&8&lEREBUS&7&l] &4&l> &c&l¡Estan Acabados! ¡No tienen Oportunidad contra Mi Fuerza!"));
        wither.getWorld().createExplosion(wither.getLocation(),8,false,true);
        Bukkit.getScheduler().runTaskLater(plugin,()->{
            wither.getWorld().createExplosion(wither.getLocation(),8,false,true);
        },40);
        Bukkit.getScheduler().runTaskLater(plugin,()->{
            wither.getWorld().createExplosion(wither.getLocation(),8,false,true);
        },80);
        Bukkit.getScheduler().runTaskLater(plugin,()->{
            wither.getWorld().createExplosion(wither.getLocation(),8,false,true);
        },120);
    }
    public void totemAttack(Player player){
        player.sendMessage(format("&7&l[&8&lEREBUS&7&l] &4&l> &c&l¡Hora de Tirar sus Dados!"));
        player.playEffect(EntityEffect.TOTEM_RESURRECT);
        SpawnListeners.spawnRandomMob(player.getLocation());
        Bukkit.getScheduler().runTaskLater(plugin,()->{
            player.sendMessage(format("&7&l[&8&lEREBUS&7&l] &4&l> &c&lEspero que disfruten su nuevo compañero!"));
        },60L);
    }
    public void throwAttack(Player player){
        player.sendMessage(format("&7&l[&8&lEREBUS&7&l] &4&l> &c&l¡Disfruten los cielos mientras caen hacia su muerte!"));
        Vector vector = player.getEyeLocation().getDirection().multiply(-5);
        player.setVelocity(vector);
    }
}
