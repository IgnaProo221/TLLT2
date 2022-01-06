package Extras;

import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.block.data.type.Fire;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.persistence.PersistentDataType;
import tlldos.tll2.TLL2;

public class GhastExplosion implements Listener{
    private TLL2 plugin;

    public GhastExplosion(TLL2 plugin){
        this.plugin = plugin;
    }


    @EventHandler
    public void caca(ProjectileLaunchEvent e) {
        if (e.getEntity().getShooter() instanceof Ghast && e.getEntity() instanceof Fireball) {
            Fireball fireball = (Fireball) e.getEntity();
            fireball.setYield(5);
        }
    }
    @EventHandler
    public void Test(ProjectileLaunchEvent e) {
        if (e.getEntity().getShooter() instanceof Ghast && e.getEntity() instanceof Fireball) {
            if (((Ghast) e.getEntity().getShooter()).getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "DESERT_GHAST"), PersistentDataType.STRING)) {
                Fireball fireball = (Fireball) e.getEntity();
                fireball.setYield(10);
            }
        }
    }
}
