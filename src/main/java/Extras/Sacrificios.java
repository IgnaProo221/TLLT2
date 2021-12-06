package Extras;

import Utilidades.Warn;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import tlldos.tll2.TLL2;

import java.util.Random;

public class Sacrificios {


    public static void start(Player self) {
        try {

            int boltsSize = new Random().nextInt(5);

            Random random = new Random();

            int rangeMax = 14;
            int rangeMin = -14;

            int X = random.nextInt((rangeMax - rangeMin) + 1) + rangeMin;
            int Z = random.nextInt((rangeMax - rangeMin) + 1) + rangeMin;

            int clx = self.getLocation().getBlockX();
            int clz = self.getLocation().getBlockZ();

            int Y = self.getWorld().getHighestBlockYAt(clx, clz);

            Location newLocation = new Location(self.getWorld(), clx, Y, clz).add(X, 1, Z);

            for (int i = 0; i < boltsSize; i++) {
                self.getWorld().strikeLightningEffect(newLocation);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Warn.Mutant(e);
        }
    }
}
