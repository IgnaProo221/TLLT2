package Listeners;

import Utilities.Format;
import Utilities.Mobs;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import tlldos.tll2.TLL2;

import java.util.Random;

public class WorldEventsListeners implements Listener{
    TLL2 plugin;
    public WorldEventsListeners(TLL2 plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void lostCitissss(PlayerChangedWorldEvent event){
        var p = event.getPlayer();
        if(event.getPlayer().getWorld().getName().equalsIgnoreCase("lost_world")){
            if(p.getGameMode() != GameMode.SURVIVAL){
                p.getServer().getConsoleSender().sendMessage("El Jugador " + p.getName() + " entro al Lost Cities.");
                p.setGameMode(GameMode.SPECTATOR);
            }else{
                Bukkit.getOnlinePlayers().forEach(player -> {
                    player.sendMessage(Format.PREFIX, Format.format("&c&l¡El Jugador " + p.getName() + " ha entrado al Lost Cities!"));
                });
            }
        }
    }

    @EventHandler
    public void lostCityspawns(CreatureSpawnEvent e){
        Random random = new Random();
        var entity = e.getEntity();
        if(entity.getWorld().getName().equalsIgnoreCase("lost_world")) {
            if (entity instanceof Monster mom) {
                parasiteregularSpawn(mom);
            }
        }
    }

    public void parasiteregularSpawn(Monster monster){
        int creatures = new Random().nextInt(6);
        if(creatures == 1){
            monster.remove();
            var endermite = monster.getLocation().getWorld().spawn(monster.getLocation(), Endermite.class);
            Mobs.parasite(endermite);
        }else if(creatures == 2){
            monster.remove();
            var bee = monster.getLocation().getWorld().spawn(monster.getLocation(), Bee.class);
            Mobs.parasite2(bee);
        }else if(creatures == 3){
            monster.remove();
            var vex = monster.getLocation().getWorld().spawn(monster.getLocation(), Vex.class);
            Mobs.minorparasite2(vex);
        }else if(creatures == 4){
            monster.remove();
            var cavespider = monster.getLocation().getWorld().spawn(monster.getLocation(), CaveSpider.class);
            Mobs.minorparasite1(cavespider);
        }else if(creatures == 5){
            monster.remove();
            var zombi = monster.getLocation().getWorld().spawn(monster.getLocation(),Zombie.class);
            Mobs.parasitez(zombi);
        }else{
            monster.remove();
            var skeleton = monster.getLocation().getWorld().spawn(monster.getLocation(), Skeleton.class);
            Mobs.parasitearcher1(skeleton);
        }
    }
}
