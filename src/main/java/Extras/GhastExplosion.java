package Extras;

import org.bukkit.ChatColor;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import tlldos.tll2.TLL2;

public class GhastExplosion implements Listener{
    private TLL2 plugin;

    public GhastExplosion(TLL2 plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void GhastExplosionPower(ProjectileLaunchEvent e) {
        if (e.getEntity().getShooter() instanceof Ghast && e.getEntity() instanceof Fireball) {
            Fireball fireball = (Fireball) e.getEntity();
            fireball.setYield(7);
        }
    }
}
