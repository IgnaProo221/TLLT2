package tasks;

import Utilities.Mobs;
import Utilities.Utils;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import tlldos.tll2.TLL2;

import java.util.Random;

public class SpawnTask extends BukkitRunnable {
    private final TLL2 plugin;

    public SpawnTask(TLL2 plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getGameMode() == GameMode.SURVIVAL) {
                if (Utils.getWorld().isThundering()) { //Nose si esto es en blastStorm pero si lo es cambienlo ha "BlastStormListeners.IsActive()"
                    int chance = new Random().nextInt(1000);
                    Location l = player.getLocation();
                    if (chance == 1) {
                        Random r = new Random();
                        int pX = (r.nextBoolean() ? -1 : 1) * (r.nextInt(25)) + 15;
                        int pZ = (r.nextBoolean() ? -1 : 1) * (r.nextInt(25)) + 15;
                        int y = (int) l.getY();

                        Block block = l.getWorld().getBlockAt(l.getBlockX() + pX, y, l.getBlockZ() + pZ);
                        Block up = block.getRelative(BlockFace.UP);

                        if (block.getType() != Material.AIR && up.getType() == Material.AIR && !(block.isLiquid() && !(block.isSolid()) && player.getWorld().getEnvironment() == World.Environment.NORMAL)) {
                            Creeper creeper = block.getLocation().getWorld().spawn(block.getLocation(), Creeper.class);
                            Mobs.roboMine(creeper);
                        }
                    }
                }
            }
        }
    }
}
