package Utilidades;

import org.bukkit.Bukkit;

public class Warn {

    public static void Mutant(Exception e) {
        Bukkit.broadcastMessage("ERROR || Ha ocurrido un error interno, avisale a Mutant o algún admin sobre este error: " + e);

        for (int i = 0; i < 3; i++) {
            Bukkit.broadcastMessage("###############################");
        }
    }
}
