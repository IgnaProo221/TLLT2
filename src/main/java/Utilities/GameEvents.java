package Utilities;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import static Utilities.Format.format;

public class GameEvents {
    
    
    public static class BlightedRage {
        private static boolean running;

        private static int remaining;

        private static BossBar bar;
        private static String title;

        public static void startEvent(int hours) {
            running = true;

            int ticks = hours * 60 * 60 * 20;

            int hrs = ticks / 3600;
            int minAndSec = ticks % 3600;
            int min = minAndSec / 60;
            int sec = minAndSec % 60;

            remaining = sec;

            String time = String.format("%02d:%02d:%02d", hrs, min, sec);

            title = format("&5&kTLL &6&lBLIGHTED RAGE &8|| &6" + time + " &5&kTLL");

            if (bar == null) {
                bar = Bukkit.createBossBar(title, BarColor.YELLOW, BarStyle.SEGMENTED_20);
            }

            bar.setVisible(true);

            for (Player current : Bukkit.getOnlinePlayers()) {
                bar.addPlayer(current);
            }

            new BukkitRunnable() {
                @Override
                public void run() {
                    if (remaining > 1) {

                        remaining--;
                        bar.setTitle(title);

                    } else {

                        stopEvent();

                    }
                }
            }.runTaskTimer(Utils.getPlugin(), 0L, 20L);
        }

        public static boolean isStarted() {
            return running;
        }

        public static void stopEvent() {
            running = false;

            for (Player current : Bukkit.getOnlinePlayers()) {
                if (bar != null) {
                    bar.removePlayer(current);

                    bar.setVisible(false);
                }

                current.sendMessage(format("&6&lBLIGHTED RAGE &8> &eEl evento ha sido terminado."));
            }
        }
    }
}
