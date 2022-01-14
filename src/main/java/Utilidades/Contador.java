package Utilidades;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.HashMap;

public class Contador implements Listener{
    public class ContadorS implements Listener {
        public static ContadorSacrificos contador;
    }
    //ARRANCO LA PANTALLA Y BUM CALVALAND

    public class ContadorSacrificos implements Listener {
        public static HashMap<String, Integer> countPlayer = new HashMap<>();

        //Esto te puede servir para comandos que quieras añadir un valor que quieras al contador de cierta persona.
        public void addExtraCount(Player p, int count) {
            String name = p.getName();
            if (countPlayer.containsKey(name)) {
                int oldCount = countPlayer.get(name);
                int result = oldCount + count;
                countPlayer.remove(name);
                countPlayer.put(name, result);
            } else {
                countPlayer.put(name, count);
            }
        }

        //Eliminar a todos del contador
        public void clearToAllCount() {
            countPlayer.clear();
        }

        //Remover una cantidad exacta del contador, Siempre que vayas a usar este void utiliza un try por qué te causara errores.
        public void removeExactCount(Player p, int count) {
            if (countPlayer.containsKey(p.getName())) {
                int countOrigin = countPlayer.get(p.getName());
                if (count > countOrigin) {
                    throw new IllegalArgumentException("Has indicado un valor mayor al original.");
                }
                int result = countOrigin - count;
                countPlayer.remove(p.getName());
                countPlayer.put(p.getName(), result);
            } else {
                throw new IllegalArgumentException("El jugador no se encuentra en el contador.");
            }
        }

        //Esto te elimina a X jugador del contador. Siempre que vayas a usar este void utiliza un try por qué te causara errores.
        public void removeToCount(Player p) {
            if (!countPlayer.containsKey(p.getName())) {
                throw new IllegalArgumentException("El jugador que has indicado no se encuentra actualmente en el contador");
            } else {
                countPlayer.remove(p.getName());
            }
        }


        //Esto sirve para añadir naturalmente al contador, y cada vez que haga el evento que quieras, colocas esto y el nombre del jugador.
        public void addCount(Player p) {
            String name = p.getName();
            if (countPlayer.containsKey(name)) {
                int oldCount = countPlayer.get(name);
                int result = oldCount + 1;
                countPlayer.remove(name);
                countPlayer.put(name, result);
            } else {
                countPlayer.put(name, 1);
            }
        }
    }

}
