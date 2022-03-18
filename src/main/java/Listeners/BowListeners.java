package Listeners;

import Utilities.CustomEnchants;
import Utilities.Format;
import io.papermc.paper.event.entity.EntityMoveEvent;
import org.bukkit.Bukkit;
import org.bukkit.EntityEffect;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import tlldos.tll2.TLL2;

import java.util.Random;

public class BowListeners implements Listener{
    TLL2 plugin;
    public BowListeners(TLL2 plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void enchantBowlol(EntityShootBowEvent e){
        var projectile = e.getProjectile();
        var shooter = e.getEntity();
        if(shooter instanceof Player player){
            if(projectile instanceof Arrow arrow){
                if(e.getBow() != null && e.getBow().hasItemMeta() && e.getBow().getItemMeta().hasEnchant(CustomEnchants.GRAVITY)){
                    int gravitychance = new Random().nextInt(100);
                    if(gravitychance >= 90){
                        player.sendMessage(Format.PREFIX,Format.format("&7El efecto de gravedad se aplico en tu flecha!"));
                        arrow.setGravity(false);
                    }
                }
            }
        }
    }

    @EventHandler
    public void adjasaslasjbow(ProjectileLaunchEvent e){
        var entity = e.getEntity();
        if(entity instanceof Arrow arrow){
            if(arrow.getShooter() instanceof Player p){
                if(p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.BULLSEYE)){
                    arrow.setDamage(arrow.getDamage() + 1);
                }
            }
        }
    }

    @EventHandler
    public void enchantLolxdbow(ProjectileHitEvent e){
        var projectile = e.getEntity();
        var shooter = e.getEntity().getShooter();
        var damaged = e.getHitEntity();
        if(shooter instanceof Player p){
            if(projectile instanceof Arrow arrow) {
                if (p.getInventory().getItemInMainHand() != null && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.PYROMANIAC)) {
                    if (damaged != null) {
                        int cacapepe = new Random().nextInt(100);
                        if (cacapepe >= 80) {
                            damaged.getWorld().playSound(damaged.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 10.0F, 1.0F);
                            damaged.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, damaged.getLocation(), 1);
                            damaged.getWorld().getNearbyEntities(damaged.getLocation(), 10, 10, 10, entity -> entity instanceof LivingEntity).forEach(entity -> {
                                LivingEntity livingEntity = (LivingEntity) entity;
                                livingEntity.damage(25);
                            });
                        }
                    }
                }
            }
        }
    }
}
