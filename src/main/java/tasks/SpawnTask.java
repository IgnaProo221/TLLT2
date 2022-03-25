package tasks;

import Utilities.ItemBuilder;
import Utilities.Mobs;
import Utilities.Utils;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkeleton;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.scheduler.BukkitRunnable;
import tlldos.tll2.TLL2;

import java.util.Random;

import static Utilities.Format.format;

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

                        if (block.getType() != Material.AIR && up.getType() == Material.AIR && !(block.isLiquid() && !(block.isSolid()) && player.getWorld().getEnvironment() == World.Environment.NORMAL && (block.getType() == Material.GRASS || block.getType() == Material.SAND || block.getType() == Material.STONE || block.getType() == Material.COBBLESTONE))) {
                            int type = new Random().nextInt(2);
                            if(type == 1){
                                MagmaCube magmaCube = block.getWorld().spawn(block.getLocation(),MagmaCube.class);
                                magmaCube.setCustomName(format("&6&lInferno Cube"));
                                magmaCube.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(60);
                                magmaCube.setHealth(60);
                                magmaCube.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(26);
                                magmaCube.setSize(15);
                            }else{
                                WitherSkeleton witherSkeleton = block.getWorld().spawn(block.getLocation(),WitherSkeleton.class);
                                witherSkeleton.setCustomName(format("&6Advanced Wither Skeleton"));
                                witherSkeleton.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(70);
                                witherSkeleton.setHealth(70);
                                witherSkeleton.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(20);
                                witherSkeleton.getEquipment().setDropChance(EquipmentSlot.HAND,0);
                                witherSkeleton.getEquipment().setItemInMainHand(new ItemBuilder(Material.NETHERITE_AXE).setUnbreakable(true).addEnchantment(Enchantment.DAMAGE_ALL,30).build());
                            }
                        }
                    }
                }
            }
        }
    }
}
